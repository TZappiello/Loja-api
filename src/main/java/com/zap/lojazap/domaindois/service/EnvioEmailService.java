package com.zap.lojazap.domaindois.service;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;

public interface EnvioEmailService {

	void enviar(Mensagem mensagem);
	
	@Getter
	@Builder
	class Mensagem {
		
		private Set<String> destinatario;
		private String assunto;
		private String corpo;
		
	}
}
