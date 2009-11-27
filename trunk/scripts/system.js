/*
 * put all initialize code here
 */
function _init_(){
	//set look and feel to windows
	application.getUI().setLookAndFeel("windows");
	
	//load some new scripts
	application.activePlugin("scripts/help.js");
	application.activePlugin("scripts/util.js");
	
	//launch the main frame
	application.launch();
}