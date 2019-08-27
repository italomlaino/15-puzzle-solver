package com.solver;

import com.model.Solution;
import com.model.State;

public abstract class Solver {

	private State initial;

	public Solver(State initial) {
		this.initial = initial;
	}

	public abstract Solution getSolution();

	public abstract void stop();

	public abstract String getName();

	public State getInitial() {
		return initial;
	}
}
