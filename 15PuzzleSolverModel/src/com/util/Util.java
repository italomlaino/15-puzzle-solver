package com.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Util {

	// FIXME
	public static byte[][] copyMatrix(byte[][] matrix) {

		int m = matrix.length;
		int n = matrix[0].length;
		byte[][] copy = new byte[m][];

		for (int j = 0; j < m; j++) {
			copy[j] = Arrays.copyOf(matrix[j], n);
		}

		return copy;
	}

	public static byte[] copyMatrixToArray(byte[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		byte[] clone = new byte[m * n];

		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				clone[i + j * n] = matrix[j][i];
			}
		}

		return clone;
	}

	public static <T> T[] copyArray(Class<T> cl, T[] src) {
		if (src == null || cl == null) {
			return null;
		}

		T[] copy = (T[]) Array.newInstance(cl, src.length);
		System.arraycopy(src, 0, copy, 0, src.length);

		return copy;
	}

}