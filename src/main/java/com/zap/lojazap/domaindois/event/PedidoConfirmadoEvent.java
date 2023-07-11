package com.zap.lojazap.domaindois.event;

import com.zap.lojazap.domaindois.entities.PedidoEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor	
public class PedidoConfirmadoEvent {

	private PedidoEntity pedido;
}
