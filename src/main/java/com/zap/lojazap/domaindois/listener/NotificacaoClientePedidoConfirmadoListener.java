package com.zap.lojazap.domaindois.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.event.PedidoConfirmadoEvent;
import com.zap.lojazap.domaindois.service.EnvioEmailService;
import com.zap.lojazap.domaindois.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmail;
	
	@EventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		PedidoEntity pedido = event.getPedido();
	
		 var mensagem = Mensagem.builder()
		.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
		.corpo("pedido-confirmado.html")
		.variavel("pedido", pedido)
		.destinatario(pedido.getCliente().getEmail())
		.build(); 

		 envioEmail.enviar(mensagem);
	}
}
