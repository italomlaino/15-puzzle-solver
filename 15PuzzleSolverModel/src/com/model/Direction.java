package com.model;

public enum Direction {
	Left(-1, 0), Right(1, 0), Up(0, -1), Down(0, 1);

	private int dx;
	private int dy;

	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public boolean isReverse(Direction direction) {

		if (dx != -direction.getDx()) {
			return false;
		}

		if (dy != -direction.getDy()) {
			return false;
		}

		return true;
	}
}
