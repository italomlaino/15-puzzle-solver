package com.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.model.Direction;

public class DirectionTest {

	@Test
	public void testIsReverse() {
		Direction up = Direction.Up;
		Direction down = Direction.Down;
		Direction left = Direction.Left;
		Direction right = Direction.Right;

		assertEquals(true, up.isReverse(down));
		assertEquals(false, up.isReverse(up));
		assertEquals(false, up.isReverse(left));
		assertEquals(false, up.isReverse(right));

		assertEquals(true, down.isReverse(up));
		assertEquals(false, down.isReverse(down));
		assertEquals(false, down.isReverse(left));
		assertEquals(false, down.isReverse(right));
	}

}
