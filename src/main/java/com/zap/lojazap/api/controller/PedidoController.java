package com.zap.lojazap.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.CozinhaDTO;
import com.zap.lojazap.api.DTO.PedidoDTO;
import com.zap.lojazap.api.DTO.PedidoResumoDTO;
import com.zap.lojazap.api.assember.PedidoModelAssembler;
import com.zap.lojazap.api.assember.PedidoModelInputAssembler;
import com.zap.lojazap.api.assember.PedidoResumoModelAssembler;
import com.zap.lojazap.api.input.CozinhaIdInput;
import com.zap.lojazap.api.input.PedidoInput;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.exception.NegocioException;
import com.zap.lojazap.domaindois.exception.PedidoNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.PedidoRepository;
import com.zap.lojazap.domaindois.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoModelAssembler pedidoModel;
	
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModel;
	
	@Autowired
	private CadastroPedidoService cadastroPedido;
	
	@Autowired
	private PedidoModelInputAssembler PedidoModelInput;
	
	@GetMapping
	public List<PedidoResumoDTO> listar(){
		return pedidoResumoModel.toCollectionDTO(pedidoRepository.findAll());
	}
	
	@GetMapping("{id}")
	public PedidoDTO poId(@PathVariable Long id) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(id);
		
		return pedidoModel.toDTO(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDTO adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
		try {
			PedidoEntity pedidoEntity = PedidoModelInput.toDTOObject(pedidoInput);
			
			return pedidoModel.toDTO(cadastroPedido.adicionar(pedidoEntity));
			
		} catch (PedidoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}

	//@PostMapping
	//public CozinhaDTO adicionar(@RequestBody @Valid CozinhaIdInput cozinhaIput) {
	//	CozinhaEntity cozinhaEntity = cozinhaModelInputAssembler.toDTOObject(cozinhaIput);
	//	cozinhaEntity = cadastroCozinha.adicionar(cozinhaEntity);
	//	
	//	return cozinhaModelAssembler.toDTO(cozinhaEntity);
