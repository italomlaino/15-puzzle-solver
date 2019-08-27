package com.solver;

import com.bf.BFSolver;
import com.bf.searcher.PriorityFirstSearcher;
import com.bf.searcher.priority.FComparator;
import com.model.State;

public class AStarSolver extends BFSolver<AStarTreeNode<State>> implements
		UseHeuristic {

	private static final String NAME = "A*";

	private Heuristic heuristic;

	public AStarSolver(State state, Heuristic heuristic) {
		super(state, new PriorityFirstSearcher(new AStarTreeNode<State>(state),
				new FComparator()));

		this.heuristic = heuristic;
	}

	@Override
	protected void exploreLater(AStarTreeNode<State> node) {
		node.setH(heuristic.calculateH(node.getValue()));

		super.exploreLater(node);
	}

	@Override
	protected AStarTreeNode<State> createNode(State value) {
		return new AStarTreeNode<State>(value);
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
