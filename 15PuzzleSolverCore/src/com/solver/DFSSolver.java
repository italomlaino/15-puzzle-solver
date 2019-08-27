package com.solver;

import com.bf.BFSolver;
import com.bf.searcher.DepthFirstSearcher;
import com.model.State;

public class DFSSolver extends BFSolver<TreeNode<State>> {

	private static final String NAME = "Depth-First";

	public DFSSolver(State state) {
		super(state, new DepthFirstSearcher<State>(new TreeNode<State>(state)));
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
