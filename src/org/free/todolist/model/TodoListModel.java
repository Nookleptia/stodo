package org.free.todolist.model;


import javax.swing.DefaultListModel;

public class TodoListModel extends DefaultListModel{
	private static final long serialVersionUID = -4871239511733274509L;
	
	public TodoListModel(){
		
	}
	
	public Object getElementAt(int index){
		return super.getElementAt(index);
	}
	
	public int getSize(){
		return super.getSize();
	}
	
	public void addElement(Object obj){
		super.addElement(obj);
	}
	
	public boolean removeElement(Object obj){
		return super.removeElement(obj);
	}
}