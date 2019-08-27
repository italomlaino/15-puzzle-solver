package com.gui.stateviewer;

import java.awt.Point;
import java.awt.event.MouseEvent;

import com.model.State;

public class SetupMouseBehavior extends MouseBehavior {

	public SetupMouseBehavior(StateViewer viewer) {
		super(viewer);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		StateViewer viewer = getViewer();
		State state = viewer.getState();

		Point p1 = viewer.getPointRelativeToMatrix(e.getPoint());

		if (viewer.getSelected() == null) {
			viewer.setSelected(p1);
		} else {
			Point p2 = viewer.getSelected();

			if (state.isValidCoords(p2.x, p2.y)) {
				State move = state.swap(p1, p2);
				viewer.setState(move);
			} else {
				viewer.setSelected(null);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}