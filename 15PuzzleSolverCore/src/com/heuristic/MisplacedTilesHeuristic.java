package com.heuristic;

import java.awt.Point;

import com.model.State;
import com.solver.Heuristic;

public class MisplacedTilesHeuristic extends Heuristic {

	private static final String NAME = "Misplaced Tiles Heuristic";

	private Point real;

	public MisplacedTilesHeuristic() {
		real = new Point();
	}

	@Override
	public int calculateH(State state) {

		int misplaced = 0;

		for (int j = 0; j < state.getHeight(); j++) {

			for (int i = 0; i < state.getWidth(); i++) {

				byte value = state.getData()[j][i];

				real.setLocation(i, j);
				Point expected = state.getExpectedLocationByValue(value);

				if (getDistance(expected, real) != 0) {
					misplaced++;
				}
			}
		}

		return misplaced;
	}

	private int getDistance(Point p1, Point p2) {
		int dx = p1.x - p2.x;
		int dy = p1.y - p2.y;

		return Math.abs(dx) + Math.abs(dy);
	}

	@Override
	public String toString() {
		return NAME;
	}
}
