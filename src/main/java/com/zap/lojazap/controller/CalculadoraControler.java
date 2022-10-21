//package com.zap.lojazap.controller;
//
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("calculadora")
//public class CalculadoraControler {
//	
//	@GetMapping("/soma/{a}/{b}")
//	public int soma(@PathVariable int a, @PathVariable int b) {
//		return a + b;
//	}
//
//	@GetMapping("/subtrair")
//	public int soma2(
//			@RequestParam(value = "a") int a, 
//			@RequestParam(value = "b") int b) {
//		return a - b;
//	}
//}
