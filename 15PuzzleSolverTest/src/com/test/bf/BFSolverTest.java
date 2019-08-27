package com.test.bf;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.heuristic.ManhattanHeuristic;
import com.model.Solution;
import com.model.State;
import com.solver.AStarSolver;
import com.solver.BFSSolver;
import com.solver.DFSSolver;
import com.solver.Solver;

public class BFSolverTest {

	@Test
	public void testBreadthFirst1() throws Exception {
		String data = "5,1,2,8,7,6,0,4,3";
		State state = new State(data);

		Solver solver = new BFSSolver(state);

		Solution solution = solver.getSolution();

		assertEquals(43130, solution.getStatistic().getExplored());
		assertEquals(91361, solution.getStatistic().getGenerated());
		assertEquals(20, solution.getPathLength());
	}

	@Test
	public void testBreadthFirst2() throws Exception {
		String data = "1,2,0,4,5,3,7,8,6";
		State state = new State(data);

		Solver solver = new BFSSolver(state);

		Solution solution = solver.getSolution();

		assertEquals(6, solution.getStatistic().getExplored());
		assertEquals(13, solution.getStatistic().getGenerated());
	}

	@Test
	public void testDepthFirst1() throws Exception {
		String data = "1,2,0,4,5,3,7,8,6";
		State state = new State(data);

		Solver solver = new DFSSolver(state);

		Solution solution = solver.getSolution();

		assertEquals(2, solution.getStatistic().getExplored());
		assertEquals(4, solution.getStatistic().getGenerated());
		assertEquals(2, solution.getPathLength());
	}

	@Test
	public void testAStar1() throws Exception {
		String data = "0,1,3,4,2,5,7,8,6";
		State state = new State(data);

		Solver solver = new AStarSolver(state, new ManhattanHeuristic());

		Solution solution = solver.getSolution();

		assertEquals(4, solution.getStatistic().getExplored());
		assertEquals(9, solution.getStatistic().getGenerated());
		assertEquals(4, solution.getPathLength());
	}

	@Test
	public void testAStar2() throws Exception {
		String data = "2,1,4,3,0,5,6,7,8";
		State state = new State(data);

		Solver solver = new AStarSolver(state, new ManhattanHeuristic());

		Solution solution = solver.getSolution();

		assertEquals(1852, solution.getStatistic().getExplored());
		assertEquals(3299, solution.getStatistic().getGenerated());
		assertEquals(24, solution.getPathLength());
	}

	@Test
	public void testAStar3() throws Exception {
		String data = "7,1,3,4,0,5,2,8,6";
		State state = new State(data);

		Solver solver = new AStarSolver(state, new ManhattanHeuristic());

		Solution solution = solver.getSolution();

		System.out.println(solution.toString());

		assertEquals(153, solution.getStatistic().getExplored());
		assertEquals(240, solution.getStatistic().getGenerated());
		assertEquals(14, solution.getPathLength());
	}

	// @Test
	// public void testIDA1() throws Exception {
	// String data = "7,1,3,4,0,5,2,8,6";
	// State state = new State(data);
	//
	// Solver solver = new IDASolver(state, new ManhattanHeuristic());
	//
	// Solution solution = solver.getSolution();
	//
	// System.out.println(solution.toString());
	//
	// assertEquals(153, solution.getStatistic().getExplored());
	// assertEquals(240, solution.getStatistic().getGenerated());
	// assertEquals(14, solution.getPathLength());
	// }
}
