package com.scrambler;

import java.util.Random;

import com.model.Direction;
import com.model.State;

public class Scrambler {

	public static final int SCRAMBLER_EASY = 500;
	public static final int SCRAMBLER_MEDIUM = 1000;
	public static final int SCRAMBLER_HARD = 2000;

	public static State scramble(int n, int steps) {

		State state = new State(n);
		state = scramble(state, steps);

		return state;
	}

	public static State scramble(State state, int steps) {

		if (!state.isPossible()) {
			state = new State(state.getWidth());
		}

		Direction old = null;

		Random random = new Random();
		for (int i = 0; i < steps; i++) {

			Direction[] directions = state.getAllValidMoveDirections();

			Direction direction;

			do {
				direction = directions[random.nextInt(directions.length)];

				if (old != null) {
					if (direction.isReverse(old)) {
						continue;
					}
				}
			} while (false);

			state = state.move(direction);

			old = direction;
		}

		return state;
	}
}