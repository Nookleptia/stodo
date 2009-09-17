package org.free.todolist;

import javax.swing.SwingUtilities;

import org.free.todolist.ui.MainFrame;

public class STodo {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				MainFrame lMain = new MainFrame("My todo list");
				lMain.initUI();
			}
		});
	}
}
