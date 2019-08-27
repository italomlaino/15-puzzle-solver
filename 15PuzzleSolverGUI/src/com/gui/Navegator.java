package com.gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.model.State;
import com.solver.TreeNode;

public class Navegator extends JList<TreeNode<State>> implements
		ListSelectionListener {

	private TreeNode<State>[] path;

	private DefaultListModel<TreeNode<State>> listModel;

	public Navegator(TreeNode<State>[] path) {
		this.path = path;

		listModel = new DefaultListModel<TreeNode<State>>();
		setModel(listModel);

		update();

		addListSelectionListener(this);
	}

	private void update() {
		listModel.clear();

		if (path == null) {
			return;
		}

		for (int i = 0; i < path.length; i++) {
			listModel.add(i, path[i]);
		}
	}

	public TreeNode<State>[] getPath() {
		return path;
	}

	public void setPath(TreeNode<State>[] path) {
		this.path = path;

		update();
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

		if (enabled) {
			addListSelectionListener(this);
		} else {
			removeListSelectionListener(this);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		TreeNode<State> node = getSelectedValue();

		if (node != null) {

			State state = getSelectedValue().getValue();
			if (state != null) {
				MainGUI.getInstance().getStateViewer().setState(state);
			}
		}
	}
}
