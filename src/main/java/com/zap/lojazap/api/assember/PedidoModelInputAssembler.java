package com.zap.lojazap.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.input.CidadeIdInput;
import com.zap.lojazap.api.input.PedidoInput;
import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.EstadoEntity;
import com.zap.lojazap.domaindois.entities.PedidoEntity;

@Component
public class PedidoModelInputAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PedidoEntity toDTOObject(PedidoInput pedidoIdInput) {
		return modelMapper.map(pedidoIdInput, PedidoEntity.class);
		
	}
	
	public void copyToDtoObject(PedidoInput pedidoInput, PedidoEntity pedidoEntity) {
		/* PARA EVITAR  org.hibernate.HibernateException: 
		 * identifier of an instance of com.zap.lojazap.domaindois.entities.CozinhaEntity was altered from 1 to 2*/
//		cidadeEntity.setEstado(new EstadoEntity());
		
		modelMapper.map(pedidoInput, pedidoEntity);
	}
}
