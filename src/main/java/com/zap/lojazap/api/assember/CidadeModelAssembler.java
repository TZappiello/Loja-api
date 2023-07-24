package com.zap.lojazap.api.assember;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.controller.CidadeController;
import com.zap.lojazap.api.controller.EstadoController;
import com.zap.lojazap.api.dto.CidadeDTO;
import com.zap.lojazap.api.dto.CozinhaDTO;
import com.zap.lojazap.api.dto.RestauranteDTO;
import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Component
public class CidadeModelAssembler extends 
		RepresentationModelAssemblerSupport<CidadeEntity, CidadeDTO > {

	@Autowired
	private ModelMapper modelMapper;

	
	public CidadeModelAssembler( ) {
		super(CidadeController.class, CidadeDTO.class);
	}
	
	@Override
	public CidadeDTO toModel(CidadeEntity cidadeEntity) {
		 CidadeDTO cidadeDto = modelMapper.map(cidadeEntity, CidadeDTO.class);
		
			
			var link = linkTo(methodOn(CidadeController.class)
					.porId(cidadeDto.getId())).withSelfRel();
			
			cidadeDto.add(link);
			
//			cidadeDto.add(Link.of("http://localhost:8080/cidades/1"));
			
			cidadeDto.add(linkTo(CidadeController.class)
					.withRel("cidades"));
			
			cidadeDto.getEstado().add(linkTo(EstadoController.class)
					.slash(cidadeDto.getEstado().getId()).withSelfRel());
			
		return cidadeDto;
	}
	
	@Override
	public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends CidadeEntity> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(CidadeController.class).withSelfRel());
	}
	
//	public List<CidadeDTO> toCollectionDTO(List<CidadeEntity> cidades){
//		
//		return cidades.stream()
//			.map(cidade -> toModel(cidade))
//			.collect(Collectors.toList());
//	}
}
