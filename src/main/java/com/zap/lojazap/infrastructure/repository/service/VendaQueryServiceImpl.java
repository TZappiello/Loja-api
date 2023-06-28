package com.zap.lojazap.infrastructure.repository.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zap.lojazap.domaindois.entities.model.VendaDiaria;
import com.zap.lojazap.domaindois.filter.VendaDiariaFilter;
import com.zap.lojazap.domaindois.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@Override
	public List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro) {
		return null;
	}

}
