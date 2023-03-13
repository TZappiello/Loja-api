package com.zap.lojazap.domaindois.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?>  hadleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		
		Problem problem = CreateProblemBuilder(status,problemType, detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex,
			WebRequest request){
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
		
	}
			
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?>  handleNegocioException(NegocioException ex,
			WebRequest request){
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
	

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = Problem.builder()
					.title(status.getReasonPhrase())
					.status(status.value())
					.build();
		} else if(body instanceof String) {
			body = Problem.builder()
					.title((String) body)
					.status(status.value())
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder CreateProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
				
	}
}
