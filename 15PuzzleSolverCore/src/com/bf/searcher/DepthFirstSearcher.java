package com.bf.searcher;

import java.util.Collection;
import java.util.LinkedList;

import com.solver.TreeNode;

public class DepthFirstSearcher<T> extends Searcher<T> {

	private LinkedList<TreeNode<T>> stack;

	public DepthFirstSearcher(TreeNode<T> tree) {
		super(tree);
	}

	@Override
	protected TreeNode<T> nextItem() {
		return stack.pollLast();
	}

	@Override
	protected Collection<TreeNode<T>> createList() {
		stack = new LinkedList<>();

		return stack;
	}
}