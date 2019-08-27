package com.solver;

import com.bf.searcher.DepthFirstSearcher;
import com.bf.searcher.Searcher;
import com.model.Direction;
import com.model.Solution;
import com.model.State;
import com.model.Statistic;

public class IDASolver extends Solver implements UseHeuristic {

	private volatile boolean stopped;
	private Statistic statistic;

	private static final String NAME = "IDA*";

	private Heuristic heuristic;
	private Searcher<State> searcher;
	private TreeNode<State> current;

	public IDASolver(State initial, Heuristic heuristic) {
		super(initial);

		this.searcher = new DepthFirstSearcher<State>(new TreeNode<State>(
				initial));
		this.heuristic = heuristic;

		searcher.init();
	}

	@Override
	public Solution getSolution() {

		if (getInitial() == null) {
			return null;
		}

		if (!getInitial().isPossible()) {
			return null;
		}

		int maxCost = heuristic.calculateH(getInitial());

		current = null;

		statistic = new Statistic();
		statistic.registerStartTime();

		stopped = false;

		while (!stopped) {

			current = searcher.next();
			if (current == null) {
				throw new IllegalAccessError(
						"Oops, something went wrong... Current = NULL");
			}

			if (current.getValue().isFinal()) {
				statistic.registerEndTime();

				break;
			}

			statistic.registerExplored();

			Direction[] directions = current.getValue()
					.getAllValidMoveDirections();

			for (Direction direction : directions) {

				if (current.getDirection() != null) {
					if (current.getDirection().isReverse(direction)) {
						continue;
					}
				}

				State possibility = current.getValue().move(direction);
				TreeNode<State> node = new TreeNode<State>(current, possibility);
				node.setDirection(direction);

				int cost = node.getG() + heuristic.calculateH(possibility);
				if (cost <= maxCost) {
					searcher.index(node);
				} else {
					node.dispose();
				}

			}
		}

		if (stopped) {
			return null;
		}

		TreeNode<State>[] path = current.getPathToRoot();

		Solution solution = new Solution(path, statistic);

		return solution;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public Heuristic getHeuristic() {
		return heuristic;
	}

	@Override
	public void stop() {
		stopped = true;
	}
}
