//package com.zap.lojazap.controller;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.zap.lojazap.domain.model.entities.Cliente;
//
//@RestController
//public class ClienteController {
//
//	@GetMapping("/clientes")
//	public List<Cliente> listar() {
//		Cliente cliente1 = new Cliente();
//		cliente1.setId(1L);
//		cliente1.setNome("Ana");
//		cliente1.setEmail("ana@mail.com");
//		cliente1.setTelefone("11 9999-99999");
//		
//		Cliente cliente2 = new Cliente();
//		cliente2.setId(2L);
//		cliente2.setNome("Joao");
//		cliente2.setEmail("joao@mail.com");
//		cliente2.setTelefone("11 88888-8888");
//		
//		return Arrays.asList(cliente1, cliente2);
//	}
//	
//	
//}
