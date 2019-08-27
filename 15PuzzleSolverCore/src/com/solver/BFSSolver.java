package com.solver;

import com.bf.BFSolver;
import com.bf.searcher.BreadthFirstSearcher;
import com.model.State;

public class BFSSolver extends BFSolver<TreeNode<State>> {

	private static final String NAME = "Breadth-First";

	public BFSSolver(State state) {
		super(state,
				new BreadthFirstSearcher<State>(new TreeNode<State>(state)));
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	protected TreeNode<State> createNode(State value) {
		return new TreeNode<State>(value);
	}
}
