package org.free.todolist;

import java.util.List;

import javax.swing.SwingUtilities;

import org.free.todolist.plugin.Plugin;
import org.free.todolist.plugin.PluginManager;
import org.free.todolist.plugin.TodoPlugin;
import org.free.todolist.plugin.TodoPluginManager;
import org.free.todolist.ui.MainFrame;

/**
 * the main entry of sTodo
 * 
 * @author juntao.qiu@gmail.com
 *
 */
public class STodo {
	public void initEnv(){
		PluginManager pManager = TodoPluginManager.getInstance();
		
		Plugin menuBar = new TodoPlugin("scripts/menubar.js", "menubar", "menubar plguin");
		Plugin style = new TodoPlugin("scripts/style.js", "style", "style plugin");
		
		pManager.install(menuBar);
		pManager.install(style);
		
		List<Plugin> plist = pManager.listPlugins();
		menuBar.putValueToContext("pluginList", plist);
	}
	
	public static void main(String[] args){
		STodo sTodo = new STodo();
		sTodo.initEnv();
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				MainFrame lMain = new MainFrame("My todo list");
				lMain.initUI();
			}
		});
	}
}
