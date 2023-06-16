package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.PedidoDTO;
import com.zap.lojazap.api.DTO.PedidoResumoDTO;
import com.zap.lojazap.api.assember.PedidoModelAssembler;
import com.zap.lojazap.api.assember.PedidoResumoModelAssembler;
import com.zap.lojazap.domaindois.entities.PedidoEntity;
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
	
	@GetMapping
	public List<PedidoResumoDTO> listar(){
		return pedidoResumoModel.toCollectionDTO(pedidoRepository.findAll());
	}
	
	@GetMapping("{id}")
	public PedidoDTO poId(@PathVariable Long id) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(id);
		
		return pedidoModel.toDTO(pedido);
	}
}
