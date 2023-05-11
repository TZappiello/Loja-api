package com.zap.lojazap.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.input.CidadeIdInput;
import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.EstadoEntity;

@Component
public class CidadeModelInputAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CidadeEntity toDTOObject(CidadeIdInput inputAssembler) {
		return modelMapper.map(inputAssembler, CidadeEntity.class);
		/* RestauranteEntity restaurante = new RestauranteEntity();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		
		CozinhaEntity cozinha = new CozinhaEntity();
		cozinha.setId(restauranteInput.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		return restaurante; */
	}
	
	public void copyToDtoObject(CidadeIdInput restauranteInput, CidadeEntity cidadeEntity) {
		/* PARA EVITAR  org.hibernate.HibernateException: 
		 * identifier of an instance of com.zap.lojazap.domaindois.entities.CozinhaEntity was altered from 1 to 2*/
		cidadeEntity.setEstado(new EstadoEntity());
		
		modelMapper.map(restauranteInput, cidadeEntity);
	}
}
