package com.zap.lojazap;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.service.CadastroCozinhaService;



@RunWith(SpringRunner.class)
@SpringBootTest
class LojaApiApplicationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	//happyPath
	@Test
	public void testarCadastraCozinhaComSucesso() {
		
		//cenário
		CozinhaEntity novaCozinha = new CozinhaEntity();
		novaCozinha.setNome("DaLu");
		
		//ação
		novaCozinha = cadastroCozinha.adicionar(novaCozinha);
		
		
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	//unhappyPath
	@Test
//	@org.junit.Test(expected =  ConstraintViolationException.class)		
	public void testarCadastroCozinhaSemNome() {
		
		//cenário
		CozinhaEntity novaCozinha = new CozinhaEntity();
		novaCozinha.setNome(null);
		
		//ação
		novaCozinha = cadastroCozinha.adicionar(novaCozinha);
		
		//validação
//		assertThrows(ConstraintViolationException.class, null);
	}
	
//	@org.junit.Test(expected =  EntidadeEmUsoException.class)	
	@Test
	public void testarCozinhaQuandoExcluirEmUso() {
		cadastroCozinha.excluir(1L);
	}
	
//	@org.junit.Test(expected =  CozinhaNaoEncontradaException.class)	
	@Test
	public void testarCozinhaQuandoExcluiCozinhaInexistente() {
		cadastroCozinha.excluir(20L);
	}
}
