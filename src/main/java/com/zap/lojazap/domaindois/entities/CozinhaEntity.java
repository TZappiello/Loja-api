package com.zap.lojazap.domaindois.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@JsonRootName("gastronomia") muda o nome em XML
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cozinha")
@Entity
public class CozinhaEntity {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@JsonIgnore  Ignora essa propriedade
//	@JsonProperty("titulo") muda o nome em JSON e XML
	@Column(nullable = false)
	private String nome;

	@JsonIgnore // ignora a serialização da id.
	@OneToMany(mappedBy = "cozinha")
	private List<RestauranteEntity> restaurantes = new ArrayList<>();
}
