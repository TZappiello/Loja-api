package com.zap.lojazap.domaindois.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.FormaPagamentoEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;
import com.zap.lojazap.domaindois.exception.RestauranteNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

//	private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removida, pois está em uso";

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CadastroCidadesService cadastroCidades;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Transactional
	public RestauranteEntity cadastrar(RestauranteEntity restaurante) {

//		Optional<RestauranteEntity> taxa = restauranteRepository.findTaxaByTaxaFrete(restaurante.getTaxaFrete());
//		Optional<RestauranteEntity> contem = restauranteRepository.findNomeCompletoByNome(restaurante.getNome());
		
		Long cidadeId = restaurante.getEndereco().getCidade().getId();

//		if(taxa.isPresent()) {
//			throw new RestauranteNaoEncontradoException(restaurante.getId());
//		}
//		
//		if(contem.isPresent()) {
//			throw new  RestauranteNaoEncontradoException(restaurante.getId());
//		}

		CozinhaEntity cozinha = cadastroCozinha.buscarSeTiver(restaurante.getCozinha().getId());
		CidadeEntity cidade = cadastroCidades.buscarSeTiver(cidadeId);
	

//		Long cozinhaId = restaurante.getCozinha().getId();
//		CozinhaEntity cozinha = cozinhaRepository.findById(cozinhaId)
//				.orElseThrow(()-> new EntidadeNaoEncontradaException(
//						String.format("Não existe cozinha cadastra com código %d ", cozinhaId)));

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);	
			
		return restauranteRepository.save(restaurante);

	}

	public RestauranteEntity buscarSeTiver(Long id) {
		return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException(id));
	}

	@Transactional
	public void ativo(Long id) {
		RestauranteEntity restaurante = buscarSeTiver(id);
//		restaurante.setAtivo(true);
		restaurante.ativar();
	}

	@Transactional
	public void inativo(Long id) {
		RestauranteEntity restaurante = buscarSeTiver(id);
//		restaurante.setAtivo(false);
		restaurante.inativar();
	}
	
	@Transactional
	public void ativarMultiplos(List<Long> restauranteIds) {
		restauranteIds.forEach(this::ativo); //aqui estou referenciando o metodo de ativo
	}

	@Transactional
	public void inativarMultiplos(List<Long> restaurantesIds) {
		restaurantesIds.forEach(this::inativo);	
	}
	
	@Transactional
	public void fechar(Long id) {
		RestauranteEntity restaurante = buscarSeTiver(id);
//		restaurante.setAberto(false);
		restaurante.fechar();
	}

	@Transactional
	public void abrir(Long id) {
		RestauranteEntity restaurante = buscarSeTiver(id);
		restaurante.setAberto(true);
//		restaurante.abrir();
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		RestauranteEntity restaurante = buscarSeTiver(restauranteId);
		FormaPagamentoEntity formaPagamento = cadastroFormaPagamento.buscarSeTiver(formaPagamentoId);
		
		restaurante.desassociarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		RestauranteEntity restaurante = buscarSeTiver(restauranteId);
		FormaPagamentoEntity formaPagamento = cadastroFormaPagamento.buscarSeTiver(formaPagamentoId);
		
		restaurante.associarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void desassociarUsuario(Long restauranteId, Long usuarioId) {
		RestauranteEntity restaurante = buscarSeTiver(restauranteId);
		UsuarioEntity usuario = cadastroUsuario.buscarSeTiver(usuarioId);
		
		restaurante.getUsuarios().remove(usuario);
//		restaurante.desassociarUsuario(usuario);
	}

	@Transactional
	public void associarUsuario(Long restauranteId, Long usuarioId) {
		RestauranteEntity restaurante = buscarSeTiver(restauranteId);
		UsuarioEntity usuario = cadastroUsuario.buscarSeTiver(usuarioId);
		
		restaurante.getUsuarios().add(usuario);
//		restaurante.associarUsuario(usuario);
	}
}
