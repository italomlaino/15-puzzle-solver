package com.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.util.Util;

public class State implements Cloneable {

	public static final byte BLANK = 0;

	private int height;
	private int width;
	private byte[][] data;
	private boolean isFinal;
	private boolean isPossible;

	private Point blank;

	public State(int n) {
		if (n < 3) {
			n = 3;
		}

		this.data = constructFinalStateData(n);
		this.height = data.length;
		this.width = this.height;

		blank = findBlank(data);

		this.isFinal = checkIsFinal();
		this.isPossible = checkIsPossible();
	}

	public State(String s) throws Exception {
		this(parseData(s));
	}

	public State(byte[][] data) throws Exception {
		if (!isValidData(data)) {
			throw new Exception("Invalid data!");
		}

		this.data = Util.copyMatrix(data);
		this.height = data.length;
		this.width = this.height;

		blank = findBlank(data);

		this.isFinal = checkIsFinal();
		this.isPossible = checkIsPossible();
	}

	public boolean isFinal() {
		return isFinal;
	}

	public boolean isPossible() {
		return isPossible;
	}

	private boolean checkIsFinal() {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				int expected;

				if ((i == width - 1) && (j == height - 1)) {
					expected = BLANK;
				} else {
					expected = j * width + i + 1;
				}

				if (expected != data[j][i]) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean checkIsPossible() {
		int N = 0;
		byte[] array = Util.copyMatrixToArray(data);

		for (int i = 0; i < array.length - 1; i++) {

			if (array[i] == State.BLANK) {
				continue;
			}

			for (int j = i + 1; j < array.length; j++) {

				if (array[j] == State.BLANK) {
					continue;
				}

				if (array[i] > array[j]) {
					N++;
				}
			}
		}

		if (width % 2 == 0) {
			if ((height - blank.y) % 2 == 0) {
				if (N % 2 != 0) {
					return true;
				}
			} else {
				if (N % 2 == 0) {
					return true;
				}
			}
		} else {
			if (N % 2 == 0) {
				return true;
			}
		}

		return false;
	}

	public boolean isValidCoords(int x, int y) {

		if (x < 0 || x >= width) {
			return false;
		}

		if (y < 0 || y >= height) {
			return false;
		}

		return true;
	}

	public Point getExpectedLocationByValue(byte value) {
		int y;
		int x;

		if (value == 0) {

			y = height - 1;
			x = width - 1;

		} else {

			y = (value - 1) / height;
			x = (value - 1) % width;

		}

		return new Point(x, y);
	}

	public Point[] getAllValidMovePoints() {

		List<Point> points = new ArrayList<>();

		for (Direction direction : Direction.values()) {
			int nX = (int) (blank.getX() + direction.getDx());
			int nY = (int) (blank.getY() + direction.getDy());

			if (isValidCoords(nX, nY)) {
				points.add(new Point(nX, nY));
			}
		}

		return points.toArray(new Point[0]);
	}

	public Direction[] getAllValidMoveDirections() {

		List<Direction> directions = new ArrayList<>();

		for (Direction direction : Direction.values()) {
			int nX = (int) (blank.getX() + direction.getDx());
			int nY = (int) (blank.getY() + direction.getDy());

			if (isValidCoords(nX, nY)) {
				directions.add(direction);
			}
		}

		return directions.toArray(new Direction[0]);
	}

	public State move(Direction direction) {
		int nX = blank.x + direction.getDx();
		int nY = blank.y + direction.getDy();

		State clone;
		if (isValidCoords(nX, nY)) {
			Point p = new Point(nX, nY);

			clone = swapBlank(p);
		} else {
			clone = clone();
		}

		return clone;
	}

	public State swapBlank(Point p) {

		State clone = swap(p, blank);

		return clone;
	}

	public State swap(Point p1, Point p2) {
		State clone = clone();

		byte temp1 = clone.data[p1.y][p1.x];
		byte temp2 = clone.data[p2.y][p2.x];

		clone.data[p1.y][p1.x] = temp2;
		clone.data[p2.y][p2.x] = temp1;

		if (temp1 == BLANK) {
			clone.blank = (Point) p2.clone();
		} else if (temp2 == BLANK) {
			clone.blank = (Point) p1.clone();
		}

		clone.isFinal = clone.checkIsFinal();
		clone.isPossible = clone.checkIsPossible();

		return clone;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				sb.append(data[j][i]);
				sb.append(" ");
			}
		}

		return sb.toString();
	}

	public State clone() {

		State clone = new State(height);
		clone.data = Util.copyMatrix(data);
		clone.blank = blank;
		clone.isFinal = isFinal;
		clone.isPossible = isPossible;

		return clone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		for (int j = 0; j < data.length; j++) {
			for (int i = 0; i < data[0].length; i++) {
				result = prime * result + data[j][i];
			}
		}
		result = prime * result + height;

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (!Arrays.deepEquals(data, other.data))
			return false;
		if (height != other.height)
			return false;
		return true;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public byte[][] getData() {
		return Util.copyMatrix(data);
	}

	public Point getBlank() {
		return blank;
	}

	private static byte[][] parseData(String s) {
		String[] ss = s.split("[ .,;]+");

		int n = (int) Math.sqrt(ss.length);

		byte[][] data = new byte[n][n];

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				data[j][i] = Byte.parseByte(ss[j * n + i]);
			}
		}

		return data;
	}

	public static byte[][] constructFinalStateData(int n) {

		byte[][] data = new byte[n][n];

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				data[j][i] = (byte) (j * n + i + 1);
			}
		}
		data[n - 1][n - 1] = BLANK;

		return data;
	}

	private static boolean isValidData(byte[][] data) {

		int height = data.length;
		int width = data[0].length;

		if (height < 3) {
			return false;
		}

		if (height != width) {
			return false;
		}

		if (findBlank(data) == null) {
			return false;
		}

		for (int n = 1; n < height * width - 1; n++) {

			boolean found = false;
			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {

					if (data[j][i] == n) {
						found = true;
						break;
					}
				}

				if (found) {
					break;
				}
			}

			if (!found) {
				return false;
			}
		}

		return true;
	}

	private static Point findBlank(byte[][] data) {

		int height = data.length;
		int width = data[0].length;

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (data[j][i] == BLANK) {
					return new Point(i, j);
				}
			}
		}

		return null;
	}
}
