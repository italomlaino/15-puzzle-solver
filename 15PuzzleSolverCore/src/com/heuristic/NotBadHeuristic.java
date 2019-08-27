package com.heuristic;

import java.awt.Point;

import com.model.State;
import com.solver.Heuristic;

public class NotBadHeuristic extends Heuristic {

	private static final String NAME = "NotBad Heuristic";

	private Point real;

	public NotBadHeuristic() {
		real = new Point();
	}

	@Override
	public int calculateH(State state) {
		int h = 0;

		for (int j = 0; j < state.getHeight(); j++) {

			byte maxRow = -1;
			byte maxColumn = -1;
			for (int i = 0; i < state.getWidth(); i++) {

				byte value = state.getData()[j][i];

				real.setLocation(i, j);
				Point expected = state.getExpectedLocationByValue(value);

				int distance = getDistance(expected, real);
				h += distance;

				if (value != 0) {
					if (distance != 0) {
						if (real.getY() == expected.getY()) {
							if (value > maxRow) {
								maxRow = value;
							} else {
								h += 2;
							}
						}

//						if (real.getX() == expected.getX()) {
//							if (value > maxColumn) {
//								maxColumn = value;
//							} else {
//								h += 2;
//							}
//						}
					}
				}
			}
		}

		return h;
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
