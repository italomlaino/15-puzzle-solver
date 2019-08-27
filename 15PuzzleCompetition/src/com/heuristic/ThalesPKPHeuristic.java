package com.heuristic;

import com.model.State;
import com.solver.Heuristic;

public class ThalesPKPHeuristic extends Heuristic {

	public static final String NAME = "ThalesPKP Heuristic";

	@Override
	public int calculateH(State state) {
		int n = state.getHeight();
		byte[][] estado = state.getData();

		int cont = 0;
		int[][] resposta = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cont++;
				if (cont == n * n)
					resposta[i][j] = 0;
				else
					resposta[i][j] = cont;
			}
		}

		cont = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					for (int l = 0; l < n; l++) {
						if (estado[i][j] == resposta[k][l] && estado[i][j] != 0) {
							cont = cont + Math.abs(l - j) + Math.abs(i - k);
						}
					}
				}
			}
		}

		return cont / 2;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
