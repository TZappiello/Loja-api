//package com.zap.lojazap.model.controller;
//
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.zap.lojazap.model.dto.UsuarioDTO;
//import com.zap.lojazap.model.entity.Usuario;
//import com.zap.lojazap.model.exception.ErroAtenticacao;
//import com.zap.lojazap.model.exception.RegraDeNegocioException;
//import com.zap.lojazap.model.service.LancamentoService;
//import com.zap.lojazap.model.service.UsuarioService;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/usuarios")
//@RequiredArgsConstructor
//public class UsuarioController {
//
//	@Autowired
//	private final UsuarioService usuarioService;
//	
//	@Autowired
//	private LancamentoService lancamentoService;
//	
//	@PostMapping("/autenticar")
//	public ResponseEntity<Object> autenticarUsuario(@RequestBody UsuarioDTO dto) {
//
//		try {
//			Usuario usuarioAutenticado = usuarioService.autenticar(dto.getEmail(), dto.getSenha());
//			return ResponseEntity.ok(usuarioAutenticado);
//		} catch (ErroAtenticacao e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
//
//	@PostMapping
//	public ResponseEntity<Object> salvar(@RequestBody UsuarioDTO dto) {
//
//		Usuario usuario = Usuario.builder().nome(dto.getNome()).email(dto.getEmail()).senha(dto.getSenha()).build();
//
//		try {
//			Usuario salvarUsuario = usuarioService.salvarUsuario(usuario);
//			return new ResponseEntity<Object>(salvarUsuario, HttpStatus.CREATED);
//		} catch (RegraDeNegocioException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
//	
//	@GetMapping("{id}/saldo")
//	public ResponseEntity<BigDecimal> obterSaldo( @PathVariable("id") Long id ) {
//		Optional<Usuario> usuario = usuarioService.obterPorId(id);
//		
//		if(!usuario.isPresent()) {
//			return new ResponseEntity<BigDecimal>(HttpStatus.NOT_FOUND);
//		}
//		
//		BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
//		return ResponseEntity.ok(saldo);
//	}
//	
////	@DeleteMapping("{id}")
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
