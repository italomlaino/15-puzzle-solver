package com.solver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.model.Direction;

public class TreeNode<T> {

	private TreeNode<T> parent;
	private Direction direction;

	private T value;

	protected int f;
	private int g;

	public TreeNode(T value) {
		this(null, value);
	}

	public TreeNode(TreeNode<T> parent, T value) {
		this.parent = parent;
		this.value = value;
		this.g = 0;
	}

	public TreeNode<T> getRoot() {

		TreeNode<T> current = this;

		while (current.getParent() != null) {
			current = current.getParent();
		}

		return current;
	}

	public TreeNode<T>[] getPathToRoot() {
		List<TreeNode<T>> values = new ArrayList<>();

		TreeNode<T> current = this;

		while (current != null) {
			values.add(0, current);

			current = current.getParent();

		}

		return values.toArray((TreeNode<T>[]) Array.newInstance(values.get(0)
				.getClass(), 0));
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNode other = (TreeNode) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;

		if (parent != null) {
			setG(parent.getG() + 1);
		} else {
			setG(0);
		}
	}

	public T getValue() {
		return value;
	}

	public int getG() {
		return g;
	}

	private void setG(int g) {
		this.g = g;

		updateF();
	}

	public int getF() {
		return f;
	}

	protected void updateF() {
		f = g;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {

		String s = "";

		if (direction != null) {
			s = direction.toString();
		} else {
			s = value.toString() + "( Initial )";
		}

		return s;
	}

	public void dispose() {
		setParent(null);
	}
}