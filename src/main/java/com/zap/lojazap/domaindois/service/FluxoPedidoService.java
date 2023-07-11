package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.repository.PedidoRepository;

@Service
public class FluxoPedidoService {

	@Autowired
	private CadastroPedidoService cadastroPedido;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public void confirmar(String codigo) {
		PedidoEntity pedido = cadastroPedido.buscarSeTiver(codigo);
		pedido.confirmar();
		
		pedidoRepository.save(pedido);
		/* var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build(); 
		
//				.corpo("O pedido de código <strong> " + pedido.getCodigo() + " </strong> foi confirmado" )	PODEMOS FAZER ASSIM!
//				.destinatario("teste@mail.com.br")  PODERIA ENVIAR MAIS EMAIL
		
		envioEmail.enviar(mensagem); */  //AQUI VAI SER USADO UM SERVICE!
		
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
