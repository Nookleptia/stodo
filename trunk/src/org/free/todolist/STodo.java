package org.free.todolist;

import java.util.List;

import javax.swing.JFrame;
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
	private MainFrame mainFrame;
	
	public STodo(MainFrame frame){
		this.mainFrame = frame;
	}

	public void initEnv(){
		PluginManager pManager = TodoPluginManager.getInstance();
		Plugin system = 
			new TodoPlugin("scripts/system.js", "system", "system initialize");
		Plugin menubar = 
			new TodoPlugin("scripts/menubar.js", "menubar", "application menubar");
		pManager.install(system);
		pManager.install(menubar);
	}
	
	public void activePlugin(String scriptFile){
		Plugin newPlugin = new TodoPlugin(scriptFile, scriptFile, scriptFile);
		TodoPluginManager.getInstance().install(newPlugin);
	}
	
	public List<Plugin> getPluginList(){
		return TodoPluginManager.getInstance().listPlugins();
	}
	
	public MainFrame getUI(){
		return mainFrame;
	}
	
	public void launch(){
		mainFrame.initUI();
	}
	
	public static void main(String[] args){
		STodo sTodo = new STodo(new MainFrame("My todo list"));
		sTodo.initEnv();
		Plugin system = TodoPluginManager.getInstance().getPlugin("system");
		system.putValueToContext("application", sTodo);
		system.execute("_init_", new Object());
	}
}
