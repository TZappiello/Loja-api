package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.exception.PedidoNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.PedidoRepository;

@Service
public class CadastroPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public PedidoEntity buscarSeTiver(Long id) {
		PedidoEntity pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException(id));

		return pedido;
	}
}
