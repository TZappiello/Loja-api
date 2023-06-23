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
	public void confirmar(String codigo) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(codigo);
		
		pedido.confirmar();
	}
	
	@Transactional
	public void entregar(String codigo) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(codigo);

		pedido.entregar();
	}
	
	@Transactional
	public void cancelar(String codigo) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(codigo);
		
		pedido.cancelar();
	}
}
