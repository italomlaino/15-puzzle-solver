package com.heuristic;

import com.model.State;

import de.spieleck.app.cngram.NGramImpl;

public class RodrigoHenriqueHeuristic extends ManhattanHeuristic {

	public static final String NAME = "RodrigoHenrique Heuristic";

	private State goal;

	@Override
	public int calculateH(State state) {
		if (goal == null || goal.getHeight() != state.getHeight()
				|| goal.getWidth() != state.getWidth()) {
			goal = new State(state.getWidth());
		}

		int h = super.calculateH(state);

		NGramImpl ngram1 = new NGramImpl(goal.toString());
		NGramImpl ngram2 = new NGramImpl(state.toString());

		h += 0.35 * ngram2.compareTo(ngram1);

		return h;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
