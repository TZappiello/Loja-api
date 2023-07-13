package com.zap.lojazap.domaindois.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.event.PedidoCanceladoEvent;
import com.zap.lojazap.domaindois.service.EnvioEmailService;
import com.zap.lojazap.domaindois.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {

	@Autowired
	private EnvioEmailService envioEmail;
	
//	@EventListener
	@TransactionalEventListener //	ESSA ANOTAÇÃO FAZ O ENVIO DO EMAIL APOS O COMMIT
	public void aoConfirmarPedido(PedidoCanceladoEvent event) {
		PedidoEntity pedido = event.getPedido();
	
		 var mensagem = Mensagem.builder()
		.assunto(pedido.getRestaurante().getNome() + " - Pedido cancelado")
		.corpo("pedido-cancelado.html")
		.variavel("pedido", pedido)
		.destinatario(pedido.getCliente().getEmail())
		.build(); 

		 envioEmail.enviar(mensagem);
	}
}
