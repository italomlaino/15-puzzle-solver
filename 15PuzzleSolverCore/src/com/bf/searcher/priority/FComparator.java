package com.bf.searcher.priority;

import java.util.Comparator;

import com.model.State;
import com.solver.TreeNode;

public class FComparator implements Comparator<TreeNode<State>> {

	@Override
	public int compare(TreeNode<State> o1, TreeNode<State> o2) {

		int f1 = o1.getF();
		int f2 = o2.getF();

		int result = f1 - f2;

		if (result == 0) {
			result = o2.getG() - o1.getG();
		}

		return result;
	}

}
