package com.zap.lojazap.domaindois.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "grupo")
@Data
@Entity
public class GrupoEntity {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@ManyToMany
	@JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"), 
			inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private Set<PermissaoEntity> permissoes = new HashSet<>();

	public boolean desassociarPermissao(PermissaoEntity permissao) {
		return getPermissoes().remove(permissao);
	}
	
	public boolean associarPermissao(PermissaoEntity permissao) {
		return getPermissoes().add(permissao);
	}
}
