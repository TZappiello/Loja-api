//package com.zap.lojazap.controller;
//
//
//import java.awt.print.Pageable;
//import java.util.Optional;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.zap.lojazap.domain.model.entities.Produto;
//import com.zap.lojazap.domain.model.repositores.ProdutoRepository;
//
//@CrossOrigin
//@RestController
//@RequestMapping("produtos")
//public class ProdutoController {
//	
//	@Autowired
//	private ProdutoRepository produtoRepository;
//	
//	@GetMapping
//	public Iterable<Produto> buscarTodos() {
//		return produtoRepository.findAll();
//	}
//	
//	@GetMapping("/{id}")
//	public Optional<Produto> buscarPorId(@PathVariable int id) {
//		return produtoRepository.findById(id);
//	}
//		
//	@GetMapping("/nome/{parteNome}")
//	public Iterable<Produto> buscarPorNome(@PathVariable String parteNome) {
//		//return produtoRepository.findByNomeContainingIgnoreCase(parteNome);
//		return produtoRepository.encontrarPorNome(parteNome);
//	}
//	
//	
//	@GetMapping("/paginador/{nPagina}/{qtedPagina}")
//	public Iterable<Produto> BuscarPaginador(
//			@PathVariable int nPagina, @PathVariable int qtedPagina){
//		if(qtedPagina >= 5) qtedPagina = 5;
//		if(nPagina >= 2) nPagina = 1;
//		PageRequest page = PageRequest.of(nPagina, qtedPagina);
//		return produtoRepository.findAll(page);
//	}
//	
//	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT })
//	public @ResponseBody Produto salvaProduto(@Valid Produto produto) {
//		produtoRepository.save(produto);
//		return produto;
//	}
//	
////	@PutMapping
////	public Produto alterarProdutu(@Valid Produto produto) {
////		produtoRepository.save(produto);
////		return produto;
////	}
////	
//	
//	@DeleteMapping("/{id}")
//	public void deletarProduto(@PathVariable int id) {
//		produtoRepository.deleteById(id);
//	}
//	
//}
