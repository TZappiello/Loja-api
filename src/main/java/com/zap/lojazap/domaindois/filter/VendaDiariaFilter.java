package com.zap.lojazap.domaindois.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDiariaFilter {

	private Long restauranteId;
	
	@DateTimeFormat(iso= ISO.DATE_TIME)
	private OffsetDateTime dataCriacao;
	
	@DateTimeFormat(iso= ISO.DATE_TIME)
	private OffsetDateTime dataFim;
}
