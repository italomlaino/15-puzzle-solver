package com.bf.searcher;

import java.util.Collection;

import com.solver.TreeNode;

public abstract class Searcher<T> {

	protected TreeNode<T> tree;
	protected Collection<TreeNode<T>> list;

	public Searcher(TreeNode<T> tree) {
		this.tree = tree.getRoot();
	}

	public void init() {
		this.list = createList();

		list.add(this.tree);
	}

	public TreeNode<T> next() {

		if (list.isEmpty()) {
			return null;
		}

		return nextItem();
	}

	protected abstract Collection<TreeNode<T>> createList();

	protected abstract TreeNode<T> nextItem();

	public void index(TreeNode<T> node) {
		list.add(node);
	}
}