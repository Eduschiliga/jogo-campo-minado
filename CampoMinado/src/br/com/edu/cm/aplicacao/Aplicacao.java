package br.com.edu.cm.aplicacao;

import br.com.edu.cm.modelo.Tabuleiro;
import br.com.edu.cm.visao.TabuleiroConsole;

public class Aplicacao {
	public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tabuleiro);

	}
}
