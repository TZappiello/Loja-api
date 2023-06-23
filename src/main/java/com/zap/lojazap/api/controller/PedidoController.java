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

import com.zap.lojazap.api.DTO.PedidoDTO;
import com.zap.lojazap.api.DTO.PedidoResumoDTO;
import com.zap.lojazap.api.assember.PedidoModelAssembler;
import com.zap.lojazap.api.assember.PedidoModelInputAssembler;
import com.zap.lojazap.api.assember.PedidoResumoModelAssembler;
import com.zap.lojazap.api.input.PedidoInput;
import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
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
	
	@GetMapping("{codigo}")
	public PedidoDTO poId(@PathVariable String codigo) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(codigo);
		
		return pedidoModel.toDTO(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDTO adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			PedidoEntity pedidoEntity = PedidoModelInput.toDTOObject(pedidoInput);
			
//			UsuarioEntity usuarioEntity= new UsuarioEntity();
//			usuarioEntity.setId(1L);
//			pedidoEntity.setCliente(usuarioEntity);
			
			pedidoEntity.setCliente(new UsuarioEntity());
			pedidoEntity.getCliente().setId(1L);
			
			pedidoEntity = cadastroPedido.adicionar(pedidoEntity);
			
			return pedidoModel.toDTO(pedidoEntity);
			
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
}

