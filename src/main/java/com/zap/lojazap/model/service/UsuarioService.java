package com.zap.lojazap.model.service;

import com.zap.lojazap.model.entity.Usuario;

public interface UsuarioService {

	Usuario autenticar(String nome, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);

}
