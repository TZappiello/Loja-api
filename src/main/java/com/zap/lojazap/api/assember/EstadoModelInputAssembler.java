package com.zap.lojazap.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.input.EstadoIdInput;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.EstadoEntity;

@Component
public class EstadoModelInputAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EstadoEntity toDTOObject(EstadoIdInput estadoIdInput) {
		return modelMapper.map(estadoIdInput, EstadoEntity.class);

	}
	
	public void copyToDtoObject(EstadoIdInput eInput, EstadoEntity estadoEntity) {
		/* PARA EVITAR  org.hibernate.HibernateException: 
		 * identifier of an instance of com.zap.lojazap.domaindois.entities.CozinhaEntity was altered from 1 to 2*/
		eInput.setId(estadoEntity.getId());
		modelMapper.map(eInput, estadoEntity);
	}
}
