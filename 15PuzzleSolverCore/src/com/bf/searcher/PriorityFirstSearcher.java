package com.bf.searcher;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

import com.model.State;
import com.solver.TreeNode;

public class PriorityFirstSearcher extends Searcher<State> {

	private PriorityQueue<TreeNode<State>> priorityQueue;

	private Comparator<TreeNode<State>> comparator;

	public PriorityFirstSearcher(TreeNode<State> tree,
			Comparator<TreeNode<State>> comparator) {
		super(tree);

		this.comparator = comparator;
	}

	@Override
	protected TreeNode<State> nextItem() {
		return priorityQueue.poll();
	}

	@Override
	protected Collection<TreeNode<State>> createList() {
		priorityQueue = new PriorityQueue<TreeNode<State>>(1000, comparator);

		return priorityQueue;
	}
}
