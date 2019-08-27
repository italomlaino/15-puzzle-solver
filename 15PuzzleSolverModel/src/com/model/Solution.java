package com.model;

import com.solver.TreeNode;
import com.util.Util;

public class Solution {

	private TreeNode<State>[] path;

	private Statistic statistic;

	public Solution(TreeNode<State>[] path, Statistic statistic) {
		this.path = Util.copyArray(TreeNode.class, path);
		this.statistic = statistic;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nSolution found!");
		sb.append("\n");
		sb.append("\nExplored states: ");
		sb.append(statistic.getExplored());
		sb.append("\nGenerated states: ");
		sb.append(statistic.getGenerated());
		sb.append("\nElapsed time: ");
		sb.append(((double) statistic.getElapsedTime() / 1000000000));
		sb.append("s");

		sb.append("\n\nInitial State: ");
		sb.append(path[0].getValue().toString());
		sb.append("\nPath length (without initial): ");
		sb.append(path.length - 1);
		sb.append("\n\nPath:");
		for (TreeNode<State> current : path) {
			sb.append("\n");
			sb.append((current.toString()));
		}

		return sb.toString();
	}

	public State getInitial() {
		return path[0].getValue();
	}

	public TreeNode<State>[] getPath() {
		TreeNode<State>[] copy = Util.copyArray(TreeNode.class, path);

		return copy;
	}

	public int getPathLength() {
		return path.length - 1;
	}

	public Statistic getStatistic() {
		return statistic;
	}
}