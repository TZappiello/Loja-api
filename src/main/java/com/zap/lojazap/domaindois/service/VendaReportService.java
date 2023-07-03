package com.zap.lojazap.domaindois.service;

import com.zap.lojazap.domaindois.filter.VendaDiariaFilter;

public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset); 
}
