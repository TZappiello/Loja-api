package com.zap.lojazap.core.security.authorizationserver;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.zap.lojazap.domaindois.entities.UsuarioEntity;

import lombok.Getter;

@Getter
public class AuthUser extends User {
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String fullname;
	

	public AuthUser(UsuarioEntity usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		
		this.userId = usuario.getId();
		this.fullname = usuario.getNome();
	}
}
