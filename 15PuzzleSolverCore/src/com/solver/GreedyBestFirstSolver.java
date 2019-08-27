package com.solver;

import com.bf.BFSolver;
import com.bf.searcher.PriorityFirstSearcher;
import com.bf.searcher.priority.FComparator;
import com.model.State;

public class GreedyBestFirstSolver extends
		BFSolver<GreedyBestFirstTreeNode<State>> implements UseHeuristic {

	private static final String NAME = "Greedy Best-First";

	private Heuristic heuristic;

	public GreedyBestFirstSolver(State state, Heuristic heuristic) {
		super(state, new PriorityFirstSearcher(
				new GreedyBestFirstTreeNode<State>(state), new FComparator()));

		this.heuristic = heuristic;
	}

	@Override
	protected void exploreLater(GreedyBestFirstTreeNode<State> node) {
		node.setH(heuristic.calculateH(node.getValue()));

		super.exploreLater(node);
	}

	@Override
	protected GreedyBestFirstTreeNode<State> createNode(State value) {
		return new GreedyBestFirstTreeNode<State>(value);
	}

	@Override
	public String getName() {
		String s;

		s = NAME;

		if (heuristic != null) {
			s += " (With " + heuristic.toString() + ")";
		}

		return s;
	}

	@Override
	public Heuristic getHeuristic() {
		return heuristic;
	}
}
