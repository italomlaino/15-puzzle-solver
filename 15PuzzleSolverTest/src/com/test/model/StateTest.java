package com.test.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.model.State;

public class StateTest {

	@Test
	public void stateTest1() throws Exception {
		String s = "7,1,3,4,0,5,2,8,6";
		State state = new State(s);

		byte[][] expected = { { 7, 1, 3 }, { 4, 0, 5 }, { 2, 8, 6 } };

		boolean result = Arrays.deepEquals(expected, state.getData());
		assertEquals(true, result);
	}
}
