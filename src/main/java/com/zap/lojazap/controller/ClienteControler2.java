//package com.zap.lojazap.controller;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.zap.lojazap.domain.model.entities.Cliente2;
//
//@RestController
//public class ClienteControler2 {
//	
//	@GetMapping("/clientes2")
//	public List<Cliente2> listar(){
//		Cliente2 c1 = new Cliente2();
//		c1.setId(1L);
//		c1.setNome("Bob");
//		c1.setEmail("bob@mail.com");
//		c1.setTelefone("11 1234-5678");		
//		
//		Cliente2 c2 = new Cliente2();
//		c2.setId(2L);
//		c2.setNome("Jane");
//		c2.setEmail("jane@mail.com");
//		c2.setTelefone("11 4321-8765");
//		
//		return Arrays.asList(c1, c2);
//	}
//	
//	@GetMapping("/teste")
//	@ResponseBody
//	public String mostrar() {
//		return "Teste de visualização";
//	}
//
//}
