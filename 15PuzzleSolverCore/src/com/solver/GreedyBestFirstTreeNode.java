package com.solver;

public class GreedyBestFirstTreeNode<T> extends TreeNode<T> {

	private int h;

	public GreedyBestFirstTreeNode(T value) {
		super(value);
	}

	public GreedyBestFirstTreeNode(GreedyBestFirstTreeNode<T> perant, T value) {
		super(perant, value);
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;

		updateF();
	}

	@Override
	protected void updateF() {
		f = h;
	}
}
