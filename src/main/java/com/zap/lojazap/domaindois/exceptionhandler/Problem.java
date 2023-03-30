package com.zap.lojazap.domaindois.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	private String userMessage;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss") 
	private LocalDateTime timestamp;
	private List<Field> fields;
	
	@Getter
	@Builder
	public static class Field{
		
		private String name;
		private String userMessage;
	}
	
}
