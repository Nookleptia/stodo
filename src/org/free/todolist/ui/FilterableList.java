package org.free.todolist.ui;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.free.todolist.model.FilterableListModel;

public class FilterableList extends JList {
	private static final long serialVersionUID = 2827679372675804255L;
	private JTextField input;

	public FilterableList() {
		FilterableListModel model = new FilterableListModel();
		setModel(model);
	}

	/**
	 * Associates filtering document listener to text component.
	 */
	public void installFilterField(JTextField input) {
		if (input != null) {
			this.input = input;
			FilterableListModel model = (FilterableListModel) getModel();
			input.getDocument().addDocumentListener(model);
		}
	}

	/**
	 * Disassociates filtering document listener from text component.
	 */
	public void uninstallFilterField(JTextField input) {
		if (input != null) {
			FilterableListModel model = (FilterableListModel) getModel();
			input.getDocument().removeDocumentListener(model);
			this.input = null;
		}
	}

	/**
	 * Doesn't let model change to non-filtering variety
	 */
	public void setModel(ListModel model) {
		if (!(model instanceof FilterableListModel)) {
			throw new IllegalArgumentException();
		} else {
			super.setModel(model);
		}
	}

	/**
	 * Adds item to model of list
	 */
	public void addElement(Object element) {
		((FilterableListModel) getModel()).addElement(element);
	}
	
	/**
	 * get the filterable list model of current list
	 * @return
	 */
	public FilterableListModel getContents(){
		return (FilterableListModel)getModel();
	}

}
