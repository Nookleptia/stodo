importPackage(java.awt, java.awt.event)
importPackage(Packages.javax.swing)
importClass(java.lang.System)
importClass(java.lang.reflect.Constructor)

function buildPluginMenu(){
	var menuPlugin = new JMenu();
	menuPlugin.setText("Plugin");
	menuPlugin.setIcon(new ImageIcon("imgs/plugin.png"));
	var menuItemListPlugin = new JMenuItem();
	menuItemListPlugin.setText("list plugins");
	menuItemListPlugin.addActionListener(
	new JavaAdapter(
		ActionListener, {
			actionPerformed : function(event){
				var plFrame = new JFrame("plugins list");
				var epNote = new JEditorPane();
				var s = "";
				pluginList = application.getPluginList();
				for(var i = 0; i<pluginList.size();i++){
					var pi = pluginList.get(i);
					s += pi.getName()+":"+pi.getDescription()+"\n";
				}
				epNote.setText(s);
				epNote.setEditable(false);
				plFrame.add(epNote, BorderLayout.CENTER);
				plFrame.setSize(200,200);
				plFrame.setLocationRelativeTo(null);
				plFrame.setVisible(true);
			}
		}
	)
	);
	
	menuPlugin.add(menuItemListPlugin);
	
	return menuPlugin;	
}

function buildHelpMenu(){
	var menuHelp = new JMenu();
	menuHelp.setText("Help");
	
	var menuItemHelp = new JMenuItem();
	menuItemHelp.setText("Help");
	
	menuItemHelp.addActionListener(
	new JavaAdapter(
		ActionListener, {
			actionPerformed : function(event){
				importPackage(Packages.org.someone.dialog);
				var hDialog = new HelpDialog(null, "This is Help");
			}
		}
	)
	);
	
	menuHelp.add(menuItemHelp);
	
	return menuHelp;
}

//this function will be invoked from java code, MainFrame...
function _customizeMenuBar_(menuBar){
	menuBar.add(buildPluginMenu());
	//menuBar.add(buildHelpMenu());
}