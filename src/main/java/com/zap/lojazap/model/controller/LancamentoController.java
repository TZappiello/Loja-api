//package com.zap.lojazap.model.controller;
//
//
//import java.util.List;
//import java.util.Optional;
//
//import org.apache.catalina.connector.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.zap.lojazap.model.dto.AtualizarStatusDTO;
//import com.zap.lojazap.model.dto.LancamentoDTO;
//import com.zap.lojazap.model.entity.Lancamento;
//import com.zap.lojazap.model.entity.Usuario;
//import com.zap.lojazap.model.enums.StatusLancamento;
//import com.zap.lojazap.model.enums.TipoLancamento;
//import com.zap.lojazap.model.exception.RegraDeNegocioException;
//import com.zap.lojazap.model.service.LancamentoService;
//import com.zap.lojazap.model.service.UsuarioService;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequestMapping("/lancamentos")
//@RequiredArgsConstructor
//public class LancamentoController {
//
//	@Autowired
//	private LancamentoService lancamentoService;
//
//	@Autowired
//	private UsuarioService usuarioService;
//
//	@GetMapping
//	public ResponseEntity<?> buscar (
//			//@RequestParam java.util.Map<String, String> params
//			@RequestParam(value = "descricao", required = false) String descricao,
//			@RequestParam(value = "mes", required = false) Integer mes,
//			@RequestParam(value = "ano", required = false) Integer ano,
//			@RequestParam(value = "tipo", required = false) TipoLancamento tipo,
//			@RequestParam(value = "usuario") Long idUsuario
//			) {
//		Lancamento lancamentoFiltro = new Lancamento();
//		lancamentoFiltro.setDescricao(descricao);
//		lancamentoFiltro.setMes(mes);
//		lancamentoFiltro.setAno(ano);
//		lancamentoFiltro.setTipo(tipo);
//		
//		Optional<Usuario> usuario = usuarioService.obterPorId(idUsuario);
//		if(!usuario.isPresent()) {
//			return ResponseEntity.badRequest().body("Não foi possivel fazer a consulta desse usuario o mesmo não foi encontrado");
//		} else {
//			lancamentoFiltro.setUsuario(usuario.get());
//		}
//		
//		List<Lancamento> lancamento = lancamentoService.buscar(lancamentoFiltro);
//		return ResponseEntity.ok(lancamento);
//	}
//	 
//	@PostMapping
//	public ResponseEntity<?> salvar(@RequestBody LancamentoDTO dto) {
//		try {
//			Lancamento entidade = converter(dto);
//			entidade = lancamentoService.salvar(entidade);
//			return new ResponseEntity<Object>(entidade, HttpStatus.CREATED);
//		} catch (RegraDeNegocioException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//
//	}
//
//	@PutMapping("{id}")
//	public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody LancamentoDTO dto) {
//		return lancamentoService.obterPorId(id).map(entity -> {
//			try {
//				Lancamento lancamento = converter(dto);
//				lancamento.setId(entity.getId());
//				lancamentoService.atualizar(lancamento);
//				return ResponseEntity.ok(lancamento);
//
//			} catch (RegraDeNegocioException e) {
//				return ResponseEntity.badRequest().body(e.getMessage());
//			}
//
//		}).orElseGet(() -> new ResponseEntity<>("Lancamento não encontrado na base de Dados", HttpStatus.BAD_REQUEST));
//
//	}
//	
//	@PutMapping("{id}/atualizar-status")
//	public ResponseEntity<?> atualizarStatus(@PathVariable("id") Long id, @RequestBody AtualizarStatusDTO dto) {
//		return lancamentoService.obterPorId(id).map(entity ->{
//			StatusLancamento statusSelecionado = StatusLancamento.valueOf(dto.getStatus());
//			
//			if(statusSelecionado == null) {
//				return ResponseEntity.badRequest().body("Não foi possível atualizar o lançamento, envie um lançamento valido");
//			}
//			try {
//				entity.setStatus(statusSelecionado);
//				lancamentoService.atualizar(entity);
//				return ResponseEntity.ok(entity);
//				
//			} catch (RegraDeNegocioException e) {
//				return ResponseEntity.badRequest().body(e.getMessage());
//			}
//		}).orElseGet(() -> new ResponseEntity<>("Lancamento não encontrado na base de Dados", HttpStatus.BAD_REQUEST));
//	}
//	
//	@DeleteMapping("{id}")
//	public ResponseEntity<?> deletar(@PathVariable Long id) {
//		return lancamentoService.obterPorId(id).map(res ->{
//			lancamentoService.deletar(res);
//			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
//		}).orElseGet(() -> new ResponseEntity<>("Lancamento não encontrado na base de Dados", HttpStatus.BAD_REQUEST));
//	}
//
//	private Lancamento converter(LancamentoDTO dto) {
//		Lancamento lancamento = new Lancamento();
//		lancamento.setId(dto.getId());
//		lancamento.setDescricao(dto.getDescricao());
//		lancamento.setAno(dto.getAno());
//		lancamento.setMes(dto.getMes());
//		lancamento.setValor(dto.getValor());
//
//		Usuario usuario = usuarioService.obterPorId(dto.getUsuario())
//				.orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado para o Id informado!"));
//
//		lancamento.setUsuario(usuario);
//		
//		if(dto.getTipo() != null) {
//			lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));			
//		}
//		
//		if(dto.getStatus() != null) {
//			lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));			
//		}
//		
//		if(dto.getStatus()== null) {
//			lancamento.setStatus(StatusLancamento.PENDENTE);
//		}
//
//		return lancamento;
//	}
//}
