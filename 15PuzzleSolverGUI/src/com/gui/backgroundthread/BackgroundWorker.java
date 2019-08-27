package com.gui.backgroundthread;

import javax.swing.SwingWorker;

import com.gui.MainGUI;
import com.model.Solution;
import com.solver.Solver;

public class BackgroundWorker extends SwingWorker<Solution, Object> {

	private Solution solution;
	private Solver solver;

	public BackgroundWorker(Solver solver) {
		this.solver = solver;
		this.solution = null;
	}

	@Override
	protected Solution doInBackground() throws Exception {

		if (solver == null) {
			return null;
		}

		this.solution = solver.getSolution();

		return solution;
	}

	@Override
	protected void done() {
		String s;

		if (solution != null) {
			s = solver.getName() + "\n" + solution.toString();

			MainGUI.getInstance().getNavegator().setPath(solution.getPath());
		} else if (isCancelled()) {
			s = "Stopped!";
		} else {
			s = "Not solvable! (" + solver.getInitial().toString() + ")";
		}

		MainGUI.getInstance().log(s);
		MainGUI.getInstance().unlock();
	}

	public void cancel() {
		if (solver != null) {
			solver.stop();
		}

		cancel(true);
	}
}
