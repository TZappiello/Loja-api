package com.zap.lojazap.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.input.RestauranteInput;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Component
public class RestauranteModelInputAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public RestauranteEntity toDTOObject(RestauranteInput restauranteInput ) {
		return modelMapper.map(restauranteInput, RestauranteEntity.class);
		/* RestauranteEntity restaurante = new RestauranteEntity();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		
		CozinhaEntity cozinha = new CozinhaEntity();
		cozinha.setId(restauranteInput.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		return restaurante; */
	}
	
	public void copyToDtoObject(RestauranteInput restauranteInput, RestauranteEntity restauranteEntity) {
		/* PARA EVITAR  org.hibernate.HibernateException: 
		 * identifier of an instance of com.zap.lojazap.domaindois.entities.CozinhaEntity was altered from 1 to 2*/
		restauranteEntity.setCozinha(new CozinhaEntity());
		
		modelMapper.map(restauranteInput, restauranteEntity);
	}
}
