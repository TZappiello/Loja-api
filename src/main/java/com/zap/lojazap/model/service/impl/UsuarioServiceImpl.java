//package com.zap.lojazap.model.service.impl;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.zap.lojazap.model.entity.Usuario;
//import com.zap.lojazap.model.exception.ErroAtenticacao;
//import com.zap.lojazap.model.exception.RegraDeNegocioException;
//import com.zap.lojazap.model.repository.UsuarioRepository;
//import com.zap.lojazap.model.service.UsuarioService;
//
//@Service
//public class UsuarioServiceImpl implements UsuarioService {
//	
//	@Autowired
//	private UsuarioRepository usuarioRepository;	
//
//	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
//		super();
//		this.usuarioRepository = usuarioRepository;
//	}
//
//	@Override
//	public Usuario autenticar(String email, String senha) {
//		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
//		
//		if(!usuario.isPresent()) {
//			throw new ErroAtenticacao("Usuario não cadatrado no sistema!");
//		}
//		
//		if(!usuario.get().getSenha().equals(senha)) {
//			throw new ErroAtenticacao("Senha inválida!");			
//		}
//		
//		return usuario.get();
//	}
//
//	@Override
//	@Transactional
//	public Usuario salvarUsuario(Usuario usuario) {
//		validarEmail(usuario.getEmail());
//		return usuarioRepository.save(usuario);
//	}
//
//	@Override
//	public void validarEmail(String email) {
//		boolean existe = usuarioRepository.existsByEmail(email);
//		if(existe) {
//			throw new RegraDeNegocioException("Esse email ja está cadastrado!");
//		}
//		
//	}
//
//	@Override
//	public Optional<Usuario> obterPorId(Long id) {
//		return usuarioRepository.findById(id);
//	}
//
//}
