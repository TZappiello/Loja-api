package com.zap.lojazap.api.assember;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.PedidoDTO;
import com.zap.lojazap.api.dto.PedidoResumoDTO;
import com.zap.lojazap.domaindois.entities.PedidoEntity;

@Component
public class PedidoResumoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoResumoDTO toDTO(PedidoEntity pedidoEntity) {
		return modelMapper.map(pedidoEntity, PedidoResumoDTO.class);
	}
	
	public List<PedidoResumoDTO> toCollectionDTO(List<PedidoEntity> pedidos){
		
		return pedidos.stream()
			.map(pedido -> toDTO(pedido))
			.collect(Collectors.toList());
	}
}
