package com.zap.lojazap.domaindois.entities.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiaria {
	//troquei o nome do pacote de dto para model
	private LocalDate data;
	private Long totalVendas;
	private BigDecimal totalFaturado;
}
