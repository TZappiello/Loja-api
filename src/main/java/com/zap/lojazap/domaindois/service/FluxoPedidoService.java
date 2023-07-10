package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {

	@Autowired
	private CadastroPedidoService cadastroPedido;
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	@Transactional
	public void confirmar(String codigo) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(codigo);
		pedido.confirmar();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("O pedido de código <strong> " + pedido.getCodigo() + " </strong> foi confirmado" )
				.destinatario(pedido.getCliente().getEmail())
//				.destinatario("teste@mail.com.br")  PODERIA ENVIAR MAIS EMAIL
				.build(); 
		
		envioEmail.enviar(mensagem);
		
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
