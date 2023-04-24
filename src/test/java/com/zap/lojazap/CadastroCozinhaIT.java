package com.zap.lojazap;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;
import com.zap.lojazap.util.DatabaseCleaner;
import com.zap.lojazap.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
		
	@BeforeEach
	private void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		cadastraCozinhaCorreto = ResourceUtils.getContentFromResource(
				"/json/cadastra-cozinha.json");
		
		databaseCleaner.clearTables();
		adicionarParaTeste();
	}

	private static final int COZINHA_INEXISTENTE = 100;
	private int quantidadeCozinhas;
	private String cadastraCozinhaCorreto;
	private CozinhaEntity cozinhaChinesa;
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveConterQuantidadeCozinhasSalvas_QuandoConsultarTodasCozinhas() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(quantidadeCozinhas));
			//.body("nome", Matchers.hasItem("Chinesa"));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoForCadastradoUmaNovaCozinha() {
		RestAssured.given()
			.body(cadastraCozinhaCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		RestAssured.given()
			.pathParam("cozinhaId", cozinhaChinesa.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaChinesa.getNome()));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		RestAssured.given()
			.pathParam("cozinhaId", COZINHA_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void adicionarParaTeste() {
		CozinhaEntity cozinha1 = new CozinhaEntity();
		cozinha1.setNome("Brasileira");
		cozinhaRepository.save(cozinha1);
		
		cozinhaChinesa = new CozinhaEntity();
		cozinhaChinesa.setNome("Chinesa");
		cozinhaRepository.save(cozinhaChinesa);
		
		quantidadeCozinhas = (int) cozinhaRepository.count();
		
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
