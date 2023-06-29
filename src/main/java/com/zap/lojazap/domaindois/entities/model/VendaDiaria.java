package com.zap.lojazap.domaindois.entities.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiaria {
	//troquei o nome do pacote de dto para model
	private Date data;
	private Long totalVendas;
	private BigDecimal totalFaturado;
}
