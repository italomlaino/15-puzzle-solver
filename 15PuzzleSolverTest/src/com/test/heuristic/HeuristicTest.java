package com.test.heuristic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.heuristic.ManhattanHeuristic;
import com.heuristic.MisplacedTilesHeuristic;
import com.heuristic.NotBadHeuristic;
import com.model.State;

public class HeuristicTest {

	@Test
	public void ManhattanHeuristicTest() throws Exception {
		String data = "2,5,1,0,6,3,4,7,8";
		State state = new State(data);

		ManhattanHeuristic heuristic = new ManhattanHeuristic();

		assertEquals(28, heuristic.calculateH(state));
	}

	@Test
	public void MisplacedTilesHeuristic() throws Exception {
		String data = "2,5,1,0,6,3,4,7,8";
		State state = new State(data);

		MisplacedTilesHeuristic heuristic = new MisplacedTilesHeuristic();

		assertEquals(16, heuristic.calculateH(state));
	}

	@Test
	public void NotBadHeuristic() throws Exception {
		String data = "2,0,1,6,5,3,4,7,8";
		State state = new State(data);

		NotBadHeuristic notBad = new NotBadHeuristic();

		assertEquals(18, notBad.calculateH(state));

	}

}
