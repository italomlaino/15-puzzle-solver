package com.test.solver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.model.State;
import com.solver.TreeNode;

public class TreeNodeTest {

	@Test
	public void testEquals() throws Exception {
		String data1 = "2,1,4,3,0,5,6,7,8";
		State state1 = new State(data1);
		TreeNode<State> node1 = new TreeNode<>(state1);

		String data2 = "2,1,4,3,0,5,6,7,8";
		State state2 = new State(data2);
		TreeNode<State> node2 = new TreeNode<>(state2);

		assertEquals(true, state1.equals(state2));
		assertEquals(true, state1.hashCode() == state2.hashCode());
		assertEquals(true, node1.equals(node2));
		assertEquals(true, node1.hashCode() == node2.hashCode());
	}
}
