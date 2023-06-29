package com.zap.lojazap.domaindois.service;

import java.util.List;

import com.zap.lojazap.domaindois.entities.model.VendaDiaria;
import com.zap.lojazap.domaindois.filter.VendaDiariaFilter;

public interface VendaQueryService {

	List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro, String timeOffset); 
}
