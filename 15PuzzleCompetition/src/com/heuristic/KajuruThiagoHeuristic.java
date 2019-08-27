package com.heuristic;

import com.model.State;
import com.solver.Heuristic;

public class KajuruThiagoHeuristic extends Heuristic {

	public static final String NAME = "NotBad Heuristic";

	@Override
	public int calculateH(State state) {
		int dimensao = state.getHeight();
		byte[][] tabela = state.getData();

		int cont = 0;
		int pos = 0;
		int lin2_espaco = 0;
		int col2_espaco = 0;
		for (int l = 0; l < dimensao; l++) {
			for (int c = 0; c < dimensao; c++) {
				if (tabela[l][c] == 0) {
					lin2_espaco = l;
					col2_espaco = c;
				}
			}
		}
		for (int l = 0; l < dimensao; l++)
			for (int c = 0; c < dimensao; c++) {
				int bloco = tabela[l][c];
				if (bloco != 0) {
					for (int i = 0; i < 4; i++) {
						int lin_movimento = 0;
						int col_movimento = 0;

						if (i == 0) {
							lin_movimento = -1;
						} else if (i == 1) {
							lin_movimento = +1;
						} else if (i == 2) {
							col_movimento = -1;
						} else if (i == 3) {
							col_movimento = +1;
						}
						int lin_espaco2 = lin_movimento + lin2_espaco;
						int col_espaco2 = col_movimento + col2_espaco;

						if ((lin_espaco2 >= 0) && (lin_espaco2 < dimensao)
								&& (col_espaco2 >= 0)
								&& (col_espaco2 < dimensao)) {
							int linha = (bloco - 1) / dimensao;
							int coluna = (bloco - 1) % dimensao;
							pos = Math.abs(l - linha) + Math.abs(c - coluna);
							cont = cont + pos;
						}
					}
				}
			}
		// System.out.println(cont);
		return cont;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
