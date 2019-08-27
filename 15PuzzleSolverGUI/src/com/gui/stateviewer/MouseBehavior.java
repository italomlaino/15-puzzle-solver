package com.gui.stateviewer;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class MouseBehavior implements MouseListener,
		MouseMotionListener {

	private StateViewer viewer;

	public MouseBehavior(StateViewer viewer) {
		this.viewer = viewer;
	}

	public StateViewer getViewer() {
		return viewer;
	}
}