package com.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import com.gui.backgroundthread.BackgroundWorker;
import com.gui.stateviewer.StateViewer;
import com.heuristic.KajuruThiagoHeuristic;
import com.heuristic.ManhattanHeuristic;
import com.heuristic.MisplacedTilesHeuristic;
import com.heuristic.NotBadHeuristic;
import com.model.State;
import com.scrambler.Scrambler;
import com.solver.AStarSolver;
import com.solver.BFSSolver;
import com.solver.DFSSolver;
import com.solver.GreedyBestFirstSolver;
import com.solver.Heuristic;
import com.solver.Solver;

public class MainGUI extends JFrame {

	private static final String TITLE = "15PuzzleSolver";
	private static final String MESSAGE_ABOUT = "\nÍtalo Macedo\nVitor Takahashi";

	private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);

	private static final double DEFAULT_PERCENTAGE_DIV_HOR = 0.999;
	private static final double DEFAULT_PERCENTAGE_DIV_VER = 0.7;

	private static MainGUI instance;

	private StateViewer stateViewer;
	private Navegator navegator;
	private JTextArea console;

	private JMenuBar menuBar;

	private JMenu mniSolver;
	private JMenuItem mniSolverStop;
	private JMenuItem mniSolverSolve;

	private JMenu mnOptions;
	private JMenu mnOptionsSize;
	private JRadioButtonMenuItem mniOptionsSize8;
	private JRadioButtonMenuItem mniOptionsSize15;
	private JRadioButtonMenuItem mniOptionsSizeN;
	private JMenu mnOptionsScramble;
	private JMenuItem mniOptionsScrambleHard;
	private JMenuItem mniOptionsScrambleMedium;
	private JMenuItem mniOptionsScrambleEasy;
	private JMenu mnOptionsAlgorithm;
	private JRadioButtonMenuItem mniOptionsAlgorithmAStar;
	private JRadioButtonMenuItem mniOptionsAlgorithmGreedyBestFirst;
	private JRadioButtonMenuItem mniOptionsAlgorithmDepth;
	private JRadioButtonMenuItem mniOptionsAlgorithmBreadth;
	private JMenu mnOptionsHeuristic;
	private JRadioButtonMenuItem mniOptionsHeuristicMisplaced;
	private JRadioButtonMenuItem mniOptionsHeuristicManhattan;
	private JRadioButtonMenuItem mniOptionsHeuristicNotBad;
	private JRadioButtonMenuItem mniOptionHeuristicThalesPKP;
	private JRadioButtonMenuItem mniOptionHeuristicKajuruThiago;

	private JMenu mnHelp;
	private JMenuItem mniHelpAbout;

	private BackgroundWorker worker;

	private MainGUI() {
		initComponents();
	}

	private void initComponents() {
		setTitle(TITLE);
		setSize(DEFAULT_SIZE);
		setPreferredSize(DEFAULT_SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setJMenuBar(buildMenuBar());

		console = new JTextArea();
		console.setEditable(false);
		DefaultCaret caret = (DefaultCaret) console.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

		JScrollPane consoleScrollPane = new JScrollPane(console);
		consoleScrollPane.setAutoscrolls(false);
		JTabbedPane paneConsole = new JTabbedPane();
		paneConsole.add("Console", consoleScrollPane);

		stateViewer = new StateViewer(new State(3));
		JScrollPane stateViewerScrollPane = new JScrollPane(stateViewer);
		JTabbedPane paneStateViewer = new JTabbedPane();
		paneStateViewer.add("Viewer", stateViewerScrollPane);

		navegator = new Navegator(null);
		JScrollPane navegatorScrollPane = new JScrollPane(navegator);
		JTabbedPane paneNavegator = new JTabbedPane();
		paneNavegator.addTab("Navegator", navegatorScrollPane);

		Container container = getContentPane();

		JSplitPane splitpH = new JSplitPane();
		splitpH.setOneTouchExpandable(true);
		splitpH.setLeftComponent(paneStateViewer);
		splitpH.setRightComponent(paneNavegator);
		splitpH.setDividerLocation(DEFAULT_PERCENTAGE_DIV_HOR);
		splitpH.setResizeWeight(DEFAULT_PERCENTAGE_DIV_HOR);

		JSplitPane splitpV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitpV.setOneTouchExpandable(true);
		splitpV.setLeftComponent(splitpH);
		splitpV.setRightComponent(paneConsole);
		splitpV.setDividerLocation(DEFAULT_PERCENTAGE_DIV_VER);
		splitpV.setResizeWeight(DEFAULT_PERCENTAGE_DIV_VER);

		container.add(splitpV, BorderLayout.CENTER);
	}

	private JMenuBar buildMenuBar() {
		JMenuItem mniFileExit = new JMenuItem("Exit");
		mniFileExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.getInstance().dispose();
			}
		});

		JMenuItem mniFileNew = new JMenuItem("New");
		mniFileNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean ok = false;

				while (!ok) {
					String s = (String) JOptionPane.showInputDialog(null,
							"Enter the new configuration:", "New",
							JOptionPane.INFORMATION_MESSAGE);

					if (s == null) {
						return;
					}

					try {
						State state = new State(s);
						stateViewer.setState(state);

						ok = true;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,
								"Invalid configuration!");
					}
				}
			}
		});

		JMenu mnFile = new JMenu("File");
		mnFile.add(mniFileNew);
		mnFile.addSeparator();
		mnFile.add(mniFileExit);

		mniOptionsSize8 = new JRadioButtonMenuItem("8-Puzzle (3 * 3)");
		mniOptionsSize8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				State state = new State(3);
				stateViewer.setState(state);

				navegator.setPath(null);
			}
		});
		mniOptionsSize8.setSelected(true);

		mniOptionsSize15 = new JRadioButtonMenuItem("15-Puzzle (4 * 4)");
		mniOptionsSize15.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				State state = new State(4);
				stateViewer.setState(state);

				navegator.setPath(null);
			}
		});

		mniOptionsSizeN = new JRadioButtonMenuItem("Custom (n * n)");
		mniOptionsSizeN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Byte n = null;

				while (n == null || n < 3) {
					String s = (String) JOptionPane.showInputDialog(null,
							"Enter the value of the n:", "Custom (n * n)",
							JOptionPane.INFORMATION_MESSAGE);

					if (s == null) {
						mniOptionsSize8.setSelected(true);
						return;
					}

					try {
						n = Byte.parseByte(s);
					} catch (NumberFormatException exc) {
						n = null;
					}
				}

				State state = new State(n);
				stateViewer.setState(state);

				navegator.setPath(null);

			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(mniOptionsSize8);
		group.add(mniOptionsSize15);
		group.add(mniOptionsSizeN);

		mnOptionsSize = new JMenu("Size");
		mnOptionsSize.add(mniOptionsSize8);
		mnOptionsSize.add(mniOptionsSize15);
		mnOptionsSize.add(mniOptionsSizeN);

		mniOptionsScrambleEasy = new JMenuItem("Easy");
		mniOptionsScrambleEasy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scramble(Scrambler.SCRAMBLER_EASY);
			}
		});

		mniOptionsScrambleMedium = new JMenuItem("Medium");
		mniOptionsScrambleMedium.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scramble(Scrambler.SCRAMBLER_MEDIUM);
			}
		});

		mniOptionsScrambleHard = new JMenuItem("Hard");
		mniOptionsScrambleHard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scramble(Scrambler.SCRAMBLER_HARD);
			}
		});

		mnOptionsScramble = new JMenu("Scramble");
		mnOptionsScramble.add(mniOptionsScrambleEasy);
		mnOptionsScramble.add(mniOptionsScrambleMedium);
		mnOptionsScramble.add(mniOptionsScrambleHard);

		mniOptionsHeuristicMisplaced = new JRadioButtonMenuItem(
				"Misplaced Tiles");
		mniOptionsHeuristicManhattan = new JRadioButtonMenuItem("Manhattan");
		mniOptionsHeuristicManhattan.setSelected(true);

		mniOptionsHeuristicNotBad = new JRadioButtonMenuItem("NotBad");

		mniOptionHeuristicThalesPKP = new JRadioButtonMenuItem("ThalesPKP");
		mniOptionHeuristicKajuruThiago = new JRadioButtonMenuItem(
				"KajuruThiago");

		group = new ButtonGroup();
		group.add(mniOptionsHeuristicMisplaced);
		group.add(mniOptionsHeuristicManhattan);
		group.add(mniOptionsHeuristicNotBad);
		group.add(mniOptionHeuristicThalesPKP);
		group.add(mniOptionHeuristicKajuruThiago);

		mnOptionsHeuristic = new JMenu("Heuristic");
		mnOptionsHeuristic.add(mniOptionsHeuristicMisplaced);
		mnOptionsHeuristic.add(mniOptionsHeuristicManhattan);
		mnOptionsHeuristic.add(mniOptionsHeuristicNotBad);
		// mnOptionsHeuristic.add(mniOptionHeuristicThalesPKP);
		// mnOptionsHeuristic.add(mniOptionHeuristicKajuruThiago);

		mniOptionsAlgorithmAStar = new JRadioButtonMenuItem("A*");
		mniOptionsAlgorithmAStar.setSelected(true);
		mniOptionsAlgorithmAStar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mnOptionsHeuristic.setEnabled(true);
			}
		});

		mniOptionsAlgorithmGreedyBestFirst = new JRadioButtonMenuItem(
				"Greedy Best-First");
		mniOptionsAlgorithmGreedyBestFirst
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						mnOptionsHeuristic.setEnabled(true);
					}
				});

		mniOptionsAlgorithmBreadth = new JRadioButtonMenuItem("Breadth-First");
		mniOptionsAlgorithmBreadth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mnOptionsHeuristic.setEnabled(false);
			}
		});

		mniOptionsAlgorithmDepth = new JRadioButtonMenuItem("Depth-First");
		mniOptionsAlgorithmDepth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mnOptionsHeuristic.setEnabled(false);
			}
		});

		group = new ButtonGroup();
		group.add(mniOptionsAlgorithmAStar);
		group.add(mniOptionsAlgorithmGreedyBestFirst);
		group.add(mniOptionsAlgorithmBreadth);
		group.add(mniOptionsAlgorithmDepth);

		mnOptionsAlgorithm = new JMenu("Algorithm");
		mnOptionsAlgorithm.add(mniOptionsAlgorithmAStar);
		mnOptionsAlgorithm.add(mniOptionsAlgorithmGreedyBestFirst);
		mnOptionsAlgorithm.add(mniOptionsAlgorithmBreadth);
		mnOptionsAlgorithm.add(mniOptionsAlgorithmDepth);

		mnOptions = new JMenu("Options");
		mnOptions.add(mnOptionsSize);
		mnOptions.add(mnOptionsScramble);
		mnOptions.add(mnOptionsAlgorithm);
		mnOptions.add(mnOptionsHeuristic);

		mniSolverStop = new JMenuItem("Stop");
		mniSolverSolve = new JMenuItem("Solve!");
		mniSolverSolve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solve();
			}
		});

		mniSolverStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
		mniSolverStop.setEnabled(false);

		mniSolver = new JMenu("Solver");
		mniSolver.add(mniSolverStop);
		mniSolver.addSeparator();
		mniSolver.add(mniSolverSolve);

		mniHelpAbout = new JMenuItem("About");
		mniHelpAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});

		mnHelp = new JMenu("Help");
		mnHelp.add(mniHelpAbout);

		menuBar = new JMenuBar();
		menuBar.add(mnFile);
		menuBar.add(mniSolver);
		menuBar.add(mnOptions);
		menuBar.add(mnHelp);

		return menuBar;
	}

	private Solver getSolver() {
		State state = stateViewer.getState();

		Solver solver;
		if (mniOptionsAlgorithmBreadth.isSelected()) {
			solver = new BFSSolver(state);
		} else if (mniOptionsAlgorithmDepth.isSelected()) {
			solver = new DFSSolver(state);
		} else {

			Heuristic heuristic;
			if (mniOptionsHeuristicManhattan.isSelected()) {
				heuristic = new ManhattanHeuristic();
			} else if (mniOptionsHeuristicMisplaced.isSelected()) {
				heuristic = new MisplacedTilesHeuristic();
			} else if (mniOptionsHeuristicNotBad.isSelected()) {
				heuristic = new KajuruThiagoHeuristic();
			} else {
				heuristic = new ManhattanHeuristic();
			}

			if (mniOptionsAlgorithmAStar.isSelected()) {
				solver = new AStarSolver(state, heuristic);
			} else {
				solver = new GreedyBestFirstSolver(state, heuristic);
			}
		}

		return solver;
	}

	public void about() {
		JOptionPane.showMessageDialog(MainGUI.getInstance(), MESSAGE_ABOUT,
				"About", JOptionPane.INFORMATION_MESSAGE);
	}

	public void scramble(int steps) {
		State state = stateViewer.getState();
		state = Scrambler.scramble(state, steps);

		stateViewer.setState(state);
	}

	public void stop() {
		if (worker != null && !worker.isDone()) {
			worker.cancel();
		}

		unlock();

	}

	public void solve() {
		lock();
		log("Searching... Please wait!");

		worker = new BackgroundWorker(getSolver());
		worker.execute();
	}

	public void log(String s) {
		console.setText(null);
		console.setText(s);
	}

	public void lock() {
		stateViewer.disableMouseBehavior();
		mniSolverStop.setEnabled(true);
		mniSolverSolve.setEnabled(false);
		mnOptions.setEnabled(false);
		navegator.setEnabled(false);
	}

	public void unlock() {
		stateViewer.enableMouseBehavior();
		mniSolverStop.setEnabled(false);
		mniSolverSolve.setEnabled(true);
		mnOptions.setEnabled(true);
		navegator.setEnabled(true);
	}

	public StateViewer getStateViewer() {
		return stateViewer;
	}

	public Navegator getNavegator() {
		return navegator;
	}

	public static MainGUI getInstance() {

		if (instance == null) {
			instance = new MainGUI();
		}

		return instance;
	}
}