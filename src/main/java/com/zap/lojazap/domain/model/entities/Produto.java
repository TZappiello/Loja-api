//package com.zap.lojazap.domain.model.entities;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//
//import com.sun.istack.NotNull;
//
//@Entity
//@Table(name = "produto")
//public class Produto {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//
//	@NotNull
//	@NotBlank
//	private String nome;
////	
////	@Min(0)
////	private double preco;
////	
////	@Min(0)
////	@Max(1)
////	private double desconto;
//
//	
//	
//	public Produto() {
//	}
//
//	public Produto( String nome) {
//		this.nome = nome;
////		this.preco = preco;
////		this.desconto = desconto;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
////
////	public double getPreco() {
////		return preco;
////	}
////
////	public void setPreco(double preco) {
////		this.preco = preco;
////	}
////
////	public double getDesconto() {
////		return desconto;
////	}
////
////	public void setDesconto(double desconto) {
////		this.desconto = desconto;
////	}
//	
//
//
//}
