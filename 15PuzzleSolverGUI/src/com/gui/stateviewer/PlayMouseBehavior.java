package com.gui.stateviewer;

import java.awt.Point;
import java.awt.event.MouseEvent;

import com.model.State;

public class PlayMouseBehavior extends MouseBehavior {

	public PlayMouseBehavior(StateViewer viewer) {
		super(viewer);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		StateViewer viewer = getViewer();
		State state = viewer.getState();

		Point p = viewer.getPointRelativeToMatrix(e.getPoint());

		for (Point point : getViewer().getState().getAllValidMovePoints()) {
			if (p.equals(point)) {
				State move = state.swapBlank(p);
				viewer.setState(move);
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