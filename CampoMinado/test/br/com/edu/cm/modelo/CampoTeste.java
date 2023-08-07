package br.com.edu.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
