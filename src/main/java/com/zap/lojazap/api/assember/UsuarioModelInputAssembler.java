package com.zap.lojazap.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.input.UsuarioIdInput;
import com.zap.lojazap.api.input.UsuarioInputAtualizar;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;

@Component
public class UsuarioModelInputAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioEntity toDTOObject(UsuarioIdInput usuarioIdInput) {
		return modelMapper.map(usuarioIdInput, UsuarioEntity.class);
	}
	
	public void copyToDtoObject(UsuarioIdInput usuarioInput, UsuarioEntity usuarioEntity) {
		/* PARA EVITAR  org.hibernate.HibernateException: 
		 * identifier of an instance of com.zap.lojazap.domaindois.entities.CozinhaEntity was altered from 1 to 2*/
		modelMapper.map(usuarioInput, usuarioEntity);
	}
	
	public void copyToDtoObjectUsuarioSemSenha(UsuarioInputAtualizar inputAtualizar, UsuarioEntity usuarioEntity) {
		modelMapper.map(inputAtualizar, usuarioEntity);
	}
}
