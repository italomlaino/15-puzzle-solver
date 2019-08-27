package com.competition.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.model.Solution;
import com.model.State;
import com.scrambler.Scrambler;
import com.solver.AStarSolver;
import com.solver.Solver;

public class Competition {

	public static final int SIZE = 3;
	public static final int NUMBER_STEPS = 5000;
	public static final int NUMBER_TESTS = 50;

	private List<Player> players;

	private String report;

	public Competition(List<Player> players) {
		this.players = new ArrayList<>(players);
	}

	public void start() {
		StringBuilder sb = new StringBuilder();
		sb.append("-------------------------------\n");
		sb.append("\n");
		sb.append("Competition was started!\n");
		sb.append("\n");
		sb.append("-------------------------------\n");

		int[] incorrectPathLength = new int[players.size()];
		long[] totalExploredStates = new long[players.size()];

		State state = new State(SIZE);
		for (int i = 0; i < NUMBER_TESTS; i++) {
			state = Scrambler.scramble(state, NUMBER_STEPS);

			int[] pathLength= new int[players.size()];

			sb.append("Round ");
			sb.append(i);
			sb.append("\n");
			sb.append("State = ");
			sb.append(state.toString());
			sb.append("\n");
			for (int j = 0; j < players.size(); j++) {
				Solver solver = new AStarSolver(state, players.get(j)
						.getHeuristic());

				Solution solution = solver.getSolution();

				pathLength[j] = solution.getPathLength();

				sb.append(players.get(j).getHeuristic().toString());
				sb.append("\t\t\t\t");
				sb.append(pathLength[j]);
				sb.append("\t\t\t\t");
				sb.append(solution.getStatistic().getExplored());
				sb.append("\n");

				totalExploredStates[j] += solution.getStatistic().getExplored();
			}

			int min = getMin(pathLength);

			Integer[] incorrectValues = getIncorrectValueIndexes(min,
					pathLength);

			for (int j = 0; j < incorrectValues.length; j++) {
				incorrectPathLength[incorrectValues[j]]++;
			}

			sb.append("\n");
		}

		sb.append("-------------------------------\n");

		sb.append("Overview\n");
		sb.append("Heuristic\t\t\tN. of Incorrect Path Length\t\t\tTotal Explored\n");

		for (int j = 0; j < players.size(); j++) {
			sb.append(players.get(j).getHeuristic().toString());
			sb.append("\t\t\t\t");
			sb.append(incorrectPathLength[j]);
			sb.append("\t\t\t\t");
			sb.append(totalExploredStates[j]);
			sb.append("\n");
		}

		report = sb.toString();
	}

	public String getReport() {
		return report;
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	private Integer[] getIncorrectValueIndexes(int value, int[] pathLength) {

		List<Integer> incorrects = new ArrayList<>();

		for (int i = 0; i < pathLength.length; i++) {
			if (pathLength[i] != value) {
				incorrects.add(i);
			}
		}

		return incorrects.toArray(new Integer[0]);
	}

	private int getMin(int[] values) {

		int min = values[0];

		for (int i = 1; i < values.length; i++) {
			if (min > values[i]) {
				min = values[i];
			}
		}

		return min;
	}
}
