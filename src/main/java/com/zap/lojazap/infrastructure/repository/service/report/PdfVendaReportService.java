package com.zap.lojazap.infrastructure.repository.service.report;

import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.filter.VendaDiariaFilter;
import com.zap.lojazap.domaindois.service.VendaReportService;

@Service
public class PdfVendaReportService implements VendaReportService {

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		return null;
	}

}
