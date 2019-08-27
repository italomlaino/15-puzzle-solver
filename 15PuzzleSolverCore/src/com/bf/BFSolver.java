package com.bf;

import java.util.HashSet;
import java.util.Set;

import com.bf.searcher.Searcher;
import com.model.Direction;
import com.model.Solution;
import com.model.State;
import com.model.Statistic;
import com.solver.Solver;
import com.solver.TreeNode;

public abstract class BFSolver<T extends TreeNode<State>> extends Solver {

	private volatile boolean stopped;
	private Statistic statistic;

	private Searcher<State> searcher;

	private Set<State> explored;
	private TreeNode<State> current;

	public BFSolver(State state, Searcher<State> searcher) {
		super(state);

		this.searcher = searcher;

		searcher.init();
	}

	protected void exploreLater(T node) {
		node.setParent(current);

		searcher.index(node);
	}

	@Override
	public Solution getSolution() {

		if (getInitial() == null) {
			return null;
		}

		if (!getInitial().isPossible()) {
			return null;
		}

		current = null;

		explored = new HashSet<>();

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
			explored.add(current.getValue());

			Direction[] directions = current.getValue()
					.getAllValidMoveDirections();

			for (Direction direction : directions) {
				State possibility = current.getValue().move(direction);

				if (!explored.contains(possibility)) {
					T node = createNode(possibility);
					node.setDirection(direction);

					statistic.registerGenerated();

					exploreLater(node);
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

	protected abstract T createNode(State value);

	@Override
	public void stop() {
		stopped = true;
	}

	public Searcher<State> getSearcher() {
		return searcher;
	}
}
