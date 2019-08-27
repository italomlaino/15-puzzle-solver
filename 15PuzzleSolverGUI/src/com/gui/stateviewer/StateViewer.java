package com.gui.stateviewer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import com.model.State;

public class StateViewer extends JComponent {

	private State state;
	private byte[][] data;
	private boolean isFinal;

	private int squareHeight;
	private int squareWidth;

	private Font font;
	private BasicStroke stroke;
	private RenderingHints renderHints;

	private Point selected;

	private boolean mouseBehaviorEnabled;
	private MouseBehavior mouseBehavior;

	public StateViewer() {
		this(null);
	}

	public StateViewer(State state) {
		setDoubleBuffered(true);

		setState(state);

		changeToSetupMode();

		this.stroke = new BasicStroke(1);
		this.renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		this.renderHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2d = getGraphics2D(g);

		if (isFinal) {
			g2d.setColor(Color.green);
		} else {
			g2d.setColor(Color.white);
		}

		g2d.fillRect(0, 0, getSize().width, getSize().height);

		if (state == null) {
			return;
		}

		updateSquareDimension();

		g2d.setFont(font);
		g2d.setColor(Color.black);

		g2d.setStroke(stroke);

		for (int j = 0; j < data.length; j++) {

			for (int i = 0; i < data[0].length; i++) {

				int x = (int) (i * squareWidth);
				int y = (int) (j * squareHeight);

				if (selected != null && selected.distance(i, j) == 0) {
					g2d.setColor(Color.red);
					g2d.fillRect(x, y, squareWidth, squareHeight);
				}

				g2d.setColor(Color.black);
				g2d.drawRect(x, y, squareWidth, squareHeight);

				String s = Integer.toString(data[j][i]);
				FontMetrics metrics = g2d.getFontMetrics();

				x += (squareWidth - 2 * 1 - metrics.getStringBounds(s, g)
						.getWidth()) / 2;
				y += (squareHeight - 2 * 1 + metrics.getStringBounds(s, g)
						.getHeight()) / 2;

				if (data[j][i] != State.BLANK) {
					g2d.setColor(Color.black);
					g2d.drawString(s, x, y);
				}
			}
		}
	}

	public void reset() {
		selected = null;

		revalidate();
		repaint();
	}

	public void updateSquareDimension() {
		squareHeight = getHeight() / data.length;
		squareWidth = getWidth() / data[0].length;

		font = new Font("Arial", Font.PLAIN,
				(int) (squareWidth * 0.15 + squareHeight * 0.15));
	}

	private Graphics2D getGraphics2D(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHints(renderHints);

		return g2d;
	}

	public Point getPointRelativeToMatrix(Point src) {
		int x = (int) (src.getX() / squareWidth);
		int y = (int) (src.getY() / squareHeight);

		return new Point(x, y);
	}

	public void enableMouseBehavior() {

		if (!mouseBehaviorEnabled) {
			addMouseListener(mouseBehavior);
			addMouseMotionListener(mouseBehavior);

			mouseBehaviorEnabled = true;
		}
	}

	public void disableMouseBehavior() {

		if (mouseBehaviorEnabled) {
			removeMouseListener(mouseBehavior);
			removeMouseMotionListener(mouseBehavior);

			mouseBehaviorEnabled = false;
		}

	}

	private void changeMouseBehavior(MouseBehavior newMouseBehavior) {

		if (mouseBehavior != null) {
			removeMouseListener(mouseBehavior);
			removeMouseMotionListener(mouseBehavior);
		}

		if (newMouseBehavior != null) {
			addMouseListener(newMouseBehavior);
			addMouseMotionListener(newMouseBehavior);
		}

		mouseBehavior = newMouseBehavior;
	}

	public void changeToPlayMode() {

		if (state != null) {
			changeMouseBehavior(new PlayMouseBehavior(this));
		}

	}

	public void changeToSetupMode() {

		if (state != null) {
			changeMouseBehavior(new SetupMouseBehavior(this));
		}

	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;

		if (state == null) {
			disableMouseBehavior();
		} else {
			this.data = state.getData();
			this.isFinal = state.isFinal();

			updateSquareDimension();

			enableMouseBehavior();
		}

		reset();
	}

	public Point getSelected() {
		return selected;
	}

	public void setSelected(Point selected) {
		this.selected = selected;

		revalidate();
		repaint();
	}
}