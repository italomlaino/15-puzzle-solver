package com.bf.searcher;

import java.util.Collection;
import java.util.LinkedList;

import com.solver.TreeNode;

public class BreadthFirstSearcher<T> extends Searcher<T> {

	private LinkedList<TreeNode<T>> queue;

	public BreadthFirstSearcher(TreeNode<T> tree) {
		super(tree);
	}

	@Override
	protected TreeNode<T> nextItem() {
		return queue.poll();
	}

	@Override
	protected Collection<TreeNode<T>> createList() {
		queue = new LinkedList<>();

		return queue;
	}
}