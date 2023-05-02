package com.zap.lojazap.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.CozinhaDTO;
import com.zap.lojazap.api.DTO.RestauranteDTO;
import com.zap.lojazap.api.input.RestauranteInput;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.exception.NegocioException;
import com.zap.lojazap.domaindois.repository.RestauranteRepository;
import com.zap.lojazap.domaindois.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<RestauranteDTO> listar() {
		return toCollectionDTO(restauranteRepository.findAll());
	}

	@GetMapping("/{id}")
	public RestauranteDTO porId(@PathVariable Long id) {
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiver(id);
		
		return toDTO(restaurante);	
	}


	
	@GetMapping("/por-taxa")
	public List<RestauranteEntity> porTaxa(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal){
		return restauranteRepository.queryBytaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
//	@RequestParam(value = "nomeParteInstituicao", defaultValue = "", required = false)
	
	@GetMapping("/por-nome-frete")
	public List<RestauranteEntity> porNomeETaxa(
			 String nome,
			 BigDecimal taxaInicial, 
			 BigDecimal taxaFinal
			){
		return restauranteRepository.buscar(nome, taxaInicial, taxaFinal);
	}

	@GetMapping("/com-frete-gratis")
	public List<RestauranteEntity> restauranteComFreteGratis(String nome){
//		var comFreteGratis = new RestauranteComFreteGratisSpec();
//		var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
		
		return restauranteRepository.freteGratis(nome);
	}
	
	@GetMapping("/count")
	public int countCozinha( @RequestParam Long cozinha){
		return restauranteRepository.countByCozinhaId(cozinha);
	}
	
	@GetMapping("/exists")
	public boolean exists(@RequestParam String nome) {
		return restauranteRepository.existsByNome(nome);
	}
	
	@GetMapping("/por-nome-id")
	public List<RestauranteEntity> porNomeECozinhaId(@RequestParam String nome, @RequestParam Long cozinha){
		return restauranteRepository.buscarPorNome(nome, cozinha);
	}
	
	@PostMapping
	public RestauranteDTO adicionar(@RequestBody 
						@Valid 				//@Validated(Groups.CozinhaId.class)
					RestauranteInput restauranteInput) {
		try {
			RestauranteEntity restaurante = toDTOObject(restauranteInput);
			
			return toDTO(cadastroRestaurante.cadastrar(restaurante));

		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public RestauranteDTO atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {

		try {
			RestauranteEntity restaurante = toDTOObject(restauranteInput);
			
			RestauranteEntity restauranteId = cadastroRestaurante.buscarSeTiver(id);
			
			BeanUtils.copyProperties(restaurante, restauranteId, "id", "formasPagamento", "endereco", "dataCadastro");
			return toDTO(cadastroRestaurante.cadastrar(restauranteId));
		
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
		
		
	}
	
	private RestauranteDTO toDTO(RestauranteEntity restauranteEntity) {
		CozinhaDTO cozinhaDTO = new CozinhaDTO();
		cozinhaDTO.setId(restauranteEntity.getCozinha().getId());
		cozinhaDTO.setNome(restauranteEntity.getCozinha().getNome());
		
		RestauranteDTO restauranteDTO = new RestauranteDTO();
		restauranteDTO.setId(restauranteEntity.getId());
		restauranteDTO.setNome(restauranteEntity.getNome());
		restauranteDTO.setCozinha(cozinhaDTO);
		return restauranteDTO;
	}
	
	private List<RestauranteDTO> toCollectionDTO(List<RestauranteEntity> restaurantes){
		
		return restaurantes.stream()
			.map(restaurante -> toDTO(restaurante))
			.collect(Collectors.toList());
	}
	
	private RestauranteEntity toDTOObject(RestauranteInput restauranteInput ) {
		RestauranteEntity restaurante = new RestauranteEntity();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		
		CozinhaEntity cozinha = new CozinhaEntity();
		cozinha.setId(restauranteInput.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		return restaurante;
	}
	
	/*@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody RestauranteEntity restaurante) {
		try {
			Optional<RestauranteEntity> restauranteId = restauranteRepository.findById(id);
			
			if (restauranteId.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			if (restauranteId.isPresent()) {
				BeanUtils.copyProperties(restaurante, restauranteId.get(), "id", "formasPagamento", "endereco", "dataCadastro");
				cadastroRestaurante.cadastrar(restauranteId.get());
			}
			
			return ResponseEntity.ok(restaurante);
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}
	}*/
	
	/*@GetMapping("/{id}")
	public ResponseEntity<RestauranteEntity> porId(@PathVariable Long id) {
		Optional<RestauranteEntity> restaurante = restauranteRepository.findById(id);

		if (restaurante.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(restaurante.get());
	}
	
	@PatchMapping("/{id}")	
	public ResponseEntity<?>atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos){
		Optional<RestauranteEntity> restauranteId = restauranteRepository.findById(id);
		
		if (restauranteId == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restauranteId);
		
		return atualizar(id, restauranteId);
	}

	private void merge(Map<String, Object> camposOrigem, RestauranteEntity restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		RestauranteEntity restauranteOrigem = objectMapper.convertValue(camposOrigem, RestauranteEntity.class);
		
		camposOrigem.forEach((nomePropriedade, valorPropriedade)->{
			Field field = ReflectionUtils.findField(RestauranteEntity.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			System.err.println(nomePropriedade + " = "+ valorPropriedade);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}*/
}
