package com.zap.lojazap.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.input.CidadeIdInput;
import com.zap.lojazap.api.input.CozinhaIdInput;
import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.EstadoEntity;

@Component
public class CozinhaModelInputAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CozinhaEntity toDTOObject(CozinhaIdInput cozinhaIdInput) {
		return modelMapper.map(cozinhaIdInput, CozinhaEntity.class);
		/* RestauranteEntity restaurante = new RestauranteEntity();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		
		CozinhaEntity cozinha = new CozinhaEntity();
		cozinha.setId(restauranteInput.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		return restaurante; */
	}
	
	public void copyToDtoObject(CozinhaIdInput cozinhaIdInput, CozinhaEntity cozinhaEntity) {
		/* PARA EVITAR  org.hibernate.HibernateException: 
		 * identifier of an instance of com.zap.lojazap.domaindois.entities.CozinhaEntity was altered from 1 to 2*/
		cozinhaIdInput.setId(cozinhaEntity.getId());
		modelMapper.map(cozinhaIdInput, cozinhaEntity);
	}
}
