package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.PedidoDTO;
import com.zap.lojazap.api.assember.PedidoModelAssembler;
import com.zap.lojazap.domaindois.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoModelAssembler pedidoModel;
	
//	@GetMapping
//	public List<RestauranteDTO> listar() {
//		return restauranteModelAssembler.toCollectionDTO(restauranteRepository.findAll());
//	}
	
	@GetMapping
	public List<PedidoDTO> listar(){
		return pedidoModel.toCollectionDTO(pedidoRepository.findAll());
	}
}
