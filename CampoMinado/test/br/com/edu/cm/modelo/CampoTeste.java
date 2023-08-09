package br.com.edu.cm.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.edu.cm.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo;

	@BeforeEach
	void inciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVizinhoDistancia1() {
		Campo vizinho = new Campo(3, 2);
		boolean result = campo.adicionarVizinho(vizinho);

		assertTrue(result);
	}

	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2, 2);
		boolean result = campo.adicionarVizinho(vizinho);

		assertTrue(result);
	}

	@Test
	void testeNaoVizinhoDistancia2() {
		Campo vizinho = new Campo(1, 1);
		boolean result = campo.adicionarVizinho(vizinho);

		assertFalse(result);
	}

	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}

	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});

	}

	@Test
	void testeAbrirComVizinhos1() {
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);

		campo22.adicionarVizinho(campo11);
		campo.adicionarVizinho(campo22);
		campo.abrir();

		assertTrue(campo22.isAberto() && campo11.isAberto());
	}

	@Test
	void testeAbrirComVizinhos2() {
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);

		campo12.minar();
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		campo.adicionarVizinho(campo22);
		campo.abrir();

		assertTrue(campo22.isAberto() && campo11.isFechado());
	}

	@Test
	void testeReiniciarCampo() {
		campo.reiniciar();
		assertFalse(campo.isMarcado() && campo.isAberto() && campo.isMinado());
	}

	@Test
	public void testObjetivoAlcancadoDesvendado() {
		campo.abrir();
		assertTrue(campo.objetivoAlcancado());
	}

	@Test
	public void testObjetivoAlcancadoProtegido() {
		campo.minar();
		campo.alternarMarcacao();
		assertTrue(campo.objetivoAlcancado());
	}

	@Test
	public void testReiniciar() {
		campo.minar();
		campo.alternarMarcacao();
		campo.abrir();
		campo.reiniciar();
		assertFalse(campo.isMinado());
		assertFalse(campo.isMarcado());
		assertFalse(campo.isAberto());
	}

	@Test
	void testeToStringMarcado() {
		campo.alternarMarcacao();
		assertEquals("x", campo.toString());
	}

	@Test
	void testeToStringAbertoMinado() {
		campo.abrir();
		campo.minar();
		assertEquals("*", campo.toString());
	}

	@Test
	public void testToStringVizinhancaSegura() {
		Campo vizinhoSeguro = new Campo(1, 1);
		campo.adicionarVizinho(vizinhoSeguro);
		campo.abrir();
		assertEquals(" ", campo.toString());
	}

	@Test
	public void testToStringVizinhancaMinada() {
	    Campo vizinhoMinado = new Campo(2, 2);
	    vizinhoMinado.minar();
	    campo.adicionarVizinho(vizinhoMinado);
	    campo.abrir();
	    assertEquals("1", campo.toString());
	}


	@Test
	public void testToStringFechado() {
		assertEquals("?", campo.toString());
	}
}
