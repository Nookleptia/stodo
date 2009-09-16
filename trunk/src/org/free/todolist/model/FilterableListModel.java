package org.free.todolist.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class FilterableListModel extends AbstractListModel implements
		DocumentListener {
	private static final long serialVersionUID = -2409529218176332776L;
	
	List<Object> list;
	List<Object> filteredList;
	String lastFilter = "";

	public FilterableListModel() {
		list = new ArrayList<Object>();
		filteredList = new ArrayList<Object>();
	}

	public void addElement(Object element) {
		list.add(element);
		filter(lastFilter);
	}

	public int getSize() {
		return filteredList.size();
	}

	public Object getElementAt(int index) {
		Object returnValue;
		if (index < filteredList.size()) {
			returnValue = filteredList.get(index);
		} else {
			returnValue = null;
		}
		return returnValue;
	}
	
	public void removeElement(int index){
		list.remove(index);
		filter(lastFilter);
	}
	
	void filter(String search) {
		filteredList.clear();
		for (Object element : list) {
			if (element.toString().indexOf(search, 0) != -1) {
				filteredList.add(element);
			}
		}
		fireContentsChanged(this, 0, getSize());
	}

	// DocumentListener Methods
	public void insertUpdate(DocumentEvent event) {
		Document doc = event.getDocument();
		try {
			lastFilter = doc.getText(0, doc.getLength());
			filter(lastFilter);
		} catch (BadLocationException ble) {
			System.err.println("Bad location: " + ble);
		}
	}

	public void removeUpdate(DocumentEvent event) {
		Document doc = event.getDocument();
		try {
			lastFilter = doc.getText(0, doc.getLength());
			filter(lastFilter);
		} catch (BadLocationException ble) {
			System.err.println("Bad location: " + ble);
		}
	}

	public void changedUpdate(DocumentEvent event) {
	}

	public void clear() {
		list.clear();
		filteredList.clear();
	}
}
