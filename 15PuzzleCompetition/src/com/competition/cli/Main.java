package com.competition.cli;

import java.util.ArrayList;
import java.util.List;

import com.competition.model.Competition;
import com.competition.model.Player;
import com.heuristic.KajuruThiagoHeuristic;
import com.heuristic.ManhattanHeuristic;
import com.heuristic.NotBadHeuristic;
import com.heuristic.RodrigoHenriqueHeuristic;
import com.heuristic.ThalesPKPHeuristic;
import com.model.State;
import com.solver.AStarSolver;
import com.solver.Heuristic;

public class Main {

	public static void main(String[] args) throws Exception {
		State state = new State("2 1 0 4 6 5 8 7 3");
		AStarSolver solver = new AStarSolver(state, new NotBadHeuristic());
		System.out.println(solver.getSolution().toString());
		return;

		// Heuristic heuristic1 = new ManhattanHeuristic();
		// Heuristic heuristic2 = new NotBadHeuristic();
		// Heuristic heuristic3 = new ThalesPKPHeuristic();
		// Heuristic heuristic4 = new KajuruThiagoHeuristic();
		// Heuristic heuristic5 = new RodrigoHenriqueHeuristic();
		//
		// Player player1 = new Player(heuristic1);
		// Player player2 = new Player(heuristic2);
		// Player player3 = new Player(heuristic3);
		// Player player4 = new Player(heuristic4);
		// Player player5 = new Player(heuristic5);
		//
		// List<Player> players = new ArrayList<>();
		// players.add(player1);
		// players.add(player2);
		// players.add(player3);
		// players.add(player4);
		// players.add(player5);
		//
		// Competition competition = new Competition(players);
		// competition.start();
		//
		// String report = competition.getReport();
		//
		// System.out.println(report);
	}
}
