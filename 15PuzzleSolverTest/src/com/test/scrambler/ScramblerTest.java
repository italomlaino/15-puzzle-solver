package com.test.scrambler;

import org.junit.Test;

import com.model.State;
import com.scrambler.Scrambler;

public class ScramblerTest {

	@Test
	public void scrambleTest() {
		State state = new State(5);
		System.out.println(state.toString());

		state = Scrambler.scramble(state, Scrambler.SCRAMBLER_HARD);
		System.out.println(state.toString());
	}

}