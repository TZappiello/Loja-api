package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.PedidoEntity;

@Service
public class FluxoPedidoService {

	@Autowired
	private CadastroPedidoService cadastroPedido;
	
	@Transactional
	public void confirmar(Long pedidoId) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(pedidoId);
		
		pedido.confirmar();
	}
	
	@Transactional
	public void entregar(Long pedidoId) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(pedidoId);

		pedido.entregar();
	}
	
	@Transactional
	public void cancelar(Long pedidoId) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(pedidoId);
		
		pedido.cancelar();
	}
}
