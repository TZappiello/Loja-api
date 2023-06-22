package com.zap.lojazap.domaindois.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.enums.StatusPedido;
import com.zap.lojazap.domaindois.exception.NegocioException;

@Service
public class FluxoPedidoService {

	@Autowired
	private CadastroPedidoService cadastroPedido;
	
	@Transactional
	public void confirmar(Long pedidoId) {
		
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(pedidoId);
		
		if(!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(
					String.format("Status do pedido %d não pode ser alterado de %s para %s",
							pedido.getId(),
							pedido.getStatus().getDescricao(),
							StatusPedido.CONFIRMADO.getDescricao()));
		}
		
		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());;
	}
	
	@Transactional
	public void entregar(Long pedidoId) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(pedidoId);
		
		if(pedido.getStatus().equals(StatusPedido.CANCELADO)) {
			throw new NegocioException(
						String.format("O status do pedido %d não pode ser alterado de %s para %s ",
									pedido.getId(), 
									pedido.getStatus().getDescricao(), 
									StatusPedido.ENTREGUE.getDescricao()));
		}
		
		pedido.setStatus(StatusPedido.ENTREGUE);
		pedido.setDataEntrega(OffsetDateTime.now());
	}
}
