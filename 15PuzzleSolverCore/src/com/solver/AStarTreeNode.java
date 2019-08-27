package com.solver;

public class AStarTreeNode<T> extends TreeNode<T> {

	private int h;

	public AStarTreeNode(T value) {
		super(value);
	}

	public AStarTreeNode(AStarTreeNode<T> perant, T value) {
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
		f = getG() + h;
	}
}
