package com.zap.lojazap.api.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdInput {

	@NotNull
	private Long id;
}
