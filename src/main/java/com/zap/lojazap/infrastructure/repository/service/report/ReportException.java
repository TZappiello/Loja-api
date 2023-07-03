package com.zap.lojazap.infrastructure.repository.service.report;

public class ReportException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ReportException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReportException(String message) {
		super(message);
	}

	
}
