package com.competition.model;

import com.solver.Heuristic;

public class Player {

	private Heuristic heuristic;

	public Player(Heuristic heuristic) {
		this.heuristic = heuristic;
	}

	public Heuristic getHeuristic() {
		return heuristic;
	}
}
