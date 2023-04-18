package com.zap.lojazap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
			.basePath("/cozinhas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

/*	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	// happyPath
	@Test
	public void testarCadastraCozinhaComSucesso() {

		// cenário
		CozinhaEntity novaCozinha = new CozinhaEntity();
		novaCozinha.setNome("DaLu");

		// ação
		novaCozinha = cadastroCozinha.adicionar(novaCozinha);

		// validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	// unhappyPath
	@Test
//	@org.junit.Test(expected =  ConstraintViolationException.class)		
	public void testarCadastroCozinhaSemNome() {

		// cenário
		CozinhaEntity novaCozinha = new CozinhaEntity();
		novaCozinha.setNome(null);

		// ação
		novaCozinha = cadastroCozinha.adicionar(novaCozinha);

		// validação
//		assertThrows(ConstraintViolationException.class, null);
	}

	// @org.junit.Test(expected = EntidadeEmUsoException.class)
	@Test
	public void testarCozinhaQuandoExcluirEmUso() {
		cadastroCozinha.excluir(1L);
	}

	// @org.junit.Test(expected = CozinhaNaoEncontradaException.class)
	@Test
	public void testarCozinhaQuandoExcluiCozinhaInexistente() {
		cadastroCozinha.excluir(20L);
	}*/
}
