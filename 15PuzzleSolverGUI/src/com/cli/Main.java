package com.cli;

import javax.swing.SwingUtilities;

import com.gui.MainGUI;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					MainGUI.getInstance().setVisible(true);
				} catch (Exception ex) {
					System.out.println(ex.toString());
				}

			}

		});

	}
}
