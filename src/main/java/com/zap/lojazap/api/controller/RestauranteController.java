package com.zap.lojazap.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.assember.RestauranteModelAssembler;
import com.zap.lojazap.api.assember.RestauranteModelInputAssembler;
import com.zap.lojazap.api.dto.RestauranteDTO;
import com.zap.lojazap.api.input.RestauranteInput;
import com.zap.lojazap.core.security.CheckSecurity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.exception.CidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.exception.CozinhaNaoEncontradaException;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.exception.NegocioException;
import com.zap.lojazap.domaindois.exception.RestauranteNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.RestauranteRepository;
import com.zap.lojazap.domaindois.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;

	@Autowired
	private RestauranteModelInputAssembler restauranteModelInputAssembler;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<RestauranteDTO> listar() {
		return restauranteModelAssembler.toCollectionDTO(restauranteRepository.findAll());
	}

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/{id}")
	public RestauranteDTO porId(@PathVariable Long id) {
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiver(id);

		return restauranteModelAssembler.toDTO(restaurante);
	}

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/por-taxa")
	public List<RestauranteEntity> porTaxa(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal) {
		return restauranteRepository.queryBytaxaFreteBetween(taxaInicial, taxaFinal);
	}

//	@RequestParam(value = "nomeParteInstituicao", defaultValue = "", required = false)

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/por-nome-frete")
	public List<RestauranteEntity> porNomeETaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.buscar(nome, taxaInicial, taxaFinal);
	}

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/com-frete-gratis")
	public List<RestauranteEntity> restauranteComFreteGratis(String nome) {
//		var comFreteGratis = new RestauranteComFreteGratisSpec();
//		var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

		return restauranteRepository.freteGratis(nome);
	}

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/count")
	public int countCozinha(@RequestParam Long cozinha) {
		return restauranteRepository.countByCozinhaId(cozinha);
	}

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/exists")
	public boolean exists(@RequestParam String nome) {
		return restauranteRepository.existsByNome(nome);
	}

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/por-nome-id")
	public List<RestauranteEntity> porNomeECozinhaId(@RequestParam String nome, @RequestParam Long cozinha) {
		return restauranteRepository.buscarPorNome(nome, cozinha);
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@PostMapping
	public RestauranteDTO adicionar(@RequestBody @Valid // @Validated(Groups.CozinhaId.class)
	RestauranteInput restauranteInput) {
		try {
			RestauranteEntity restaurante = restauranteModelInputAssembler.toDTOObject(restauranteInput);

			return restauranteModelAssembler.toDTO(cadastroRestaurante.cadastrar(restaurante));

		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			throw new NegocioException(e.getMessage());
		}
	}

	@CheckSecurity.Restaurantes.PodeEditar
	@PutMapping("/{id}")
	public RestauranteDTO atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {

		try {
//			RestauranteEntity restaurante = restauranteModelInputAssembler.toDTOObject(restauranteInput);

			RestauranteEntity restauranteAtual = cadastroRestaurante.buscarSeTiver(id);

			restauranteModelInputAssembler.copyToDtoObject(restauranteInput, restauranteAtual);

//			BeanUtils.copyProperties(restaurante, restauranteId, "id", "formasPagamento", "endereco", "dataCadastro");
			return restauranteModelAssembler.toDTO(cadastroRestaurante.cadastrar(restauranteAtual));

		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@CheckSecurity.Restaurantes.PodeEditar
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long id) {
	
		cadastroRestaurante.ativo(id);
	}

	@CheckSecurity.Restaurantes.PodeEditar
	@DeleteMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long id) {
		
		cadastroRestaurante.inativo(id);
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> ids) {
		try {
			cadastroRestaurante.ativarMultiplos(ids);
			
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> ids) {
		try {
			cadastroRestaurante.inativarMultiplos(ids);

		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long restauranteId) {
		cadastroRestaurante.fechar(restauranteId);
	}

	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PutMapping("/{restauranteId}/aberto")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long restauranteId) {
		cadastroRestaurante.abrir(restauranteId);
	}
	/*
	 * @PutMapping("/{id}") public ResponseEntity<Object> atualizar(@PathVariable
	 * Long id, @RequestBody RestauranteEntity restaurante) { try {
	 * Optional<RestauranteEntity> restauranteId =
	 * restauranteRepository.findById(id);
	 * 
	 * if (restauranteId.isEmpty()) { return ResponseEntity.notFound().build(); }
	 * 
	 * if (restauranteId.isPresent()) { BeanUtils.copyProperties(restaurante,
	 * restauranteId.get(), "id", "formasPagamento", "endereco", "dataCadastro");
	 * cadastroRestaurante.cadastrar(restauranteId.get()); }
	 * 
	 * return ResponseEntity.ok(restaurante);
	 * 
	 * } catch (EntidadeNaoEncontradaException e) { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	 * 
	 * } catch (EntidadeEmUsoException e) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	 * 
	 * } }
	 */

	/*
	 * @GetMapping("/{id}") public ResponseEntity<RestauranteEntity>
	 * porId(@PathVariable Long id) { Optional<RestauranteEntity> restaurante =
	 * restauranteRepository.findById(id);
	 * 
	 * if (restaurante.isEmpty()) { return ResponseEntity.notFound().build(); }
	 * 
	 * return ResponseEntity.ok().body(restaurante.get()); }
	 * 
	 * @PatchMapping("/{id}") public ResponseEntity<?>atualizarParcial(@PathVariable
	 * Long id, @RequestBody Map<String, Object> campos){
	 * Optional<RestauranteEntity> restauranteId =
	 * restauranteRepository.findById(id);
	 * 
	 * if (restauranteId == null) { return ResponseEntity.notFound().build(); }
	 * 
	 * merge(campos, restauranteId);
	 * 
	 * return atualizar(id, restauranteId); }
	 * 
	 * private void merge(Map<String, Object> camposOrigem, RestauranteEntity
	 * restauranteDestino) { ObjectMapper objectMapper = new ObjectMapper();
	 * RestauranteEntity restauranteOrigem = objectMapper.convertValue(camposOrigem,
	 * RestauranteEntity.class);
	 * 
	 * camposOrigem.forEach((nomePropriedade, valorPropriedade)->{ Field field =
	 * ReflectionUtils.findField(RestauranteEntity.class, nomePropriedade);
	 * field.setAccessible(true);
	 * 
	 * Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
	 * 
	 * System.err.println(nomePropriedade + " = "+ valorPropriedade);
	 * 
	 * ReflectionUtils.setField(field, restauranteDestino, novoValor); }); }
	 */
}
