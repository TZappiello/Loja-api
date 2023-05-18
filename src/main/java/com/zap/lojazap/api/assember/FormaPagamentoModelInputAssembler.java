package com.zap.lojazap.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.input.FormaPagamentoInput;
import com.zap.lojazap.domaindois.entities.FormaPagamentoEntity;

@Component
public class FormaPagamentoModelInputAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public FormaPagamentoEntity toDTOObject(FormaPagamentoInput pagamentoIdInput) {
		return modelMapper.map(pagamentoIdInput, FormaPagamentoEntity.class);

	}
	
	public void copyToDtoObject(FormaPagamentoInput pagamentoInput, FormaPagamentoEntity entity) {
		/* PARA EVITAR  org.hibernate.HibernateException: 
		 * identifier of an instance of com.zap.lojazap.domaindois.entities.CozinhaEntity was altered from 1 to 2*/
		pagamentoInput.setId(entity.getId());
		modelMapper.map(pagamentoInput, entity);
	}
}
