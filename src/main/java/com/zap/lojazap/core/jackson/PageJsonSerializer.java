package com.zap.lojazap.core.jackson;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

	@Override
	public void serialize(Page<?> page, JsonGenerator gen, 
			SerializerProvider serializers) throws IOException {

		gen.writeStartObject(); //inicia o objeto da paginação
		
		gen.writeObjectField("content", page.getContent());  //filtra os campos que queremos
		gen.writeObjectField("size", page.getSize());
		gen.writeObjectField("tamanho", page.getSize()); // pode ser o nome que eu quiser de retorno do corpo
		gen.writeObjectField("TotalElements", page.getTotalElements());
		gen.writeObjectField("totalPages", page.getTotalPages());
		gen.writeObjectField("number", page.getNumber());
		gen.writeObjectField("numberElements", page.getNumberOfElements());
		
		gen.writeEndObject(); //fim do objeto
		
	}

}
