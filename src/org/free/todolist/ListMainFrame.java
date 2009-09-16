package org.free.todolist;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.free.todolist.data.TodoItemListBuilder;
import org.free.todolist.export.Exporter;
import org.free.todolist.export.HTMLExporter;
import org.free.todolist.model.FilterableListModel;
import org.free.todolist.model.TodoItem;
import org.free.todolist.ui.EditTaskDialog;
import org.free.todolist.ui.FilterableList;
import org.free.todolist.ui.NewMailDialog;
import org.free.todolist.ui.NewTaskDialog;
import org.free.todolist.ui.PreferenceDialog;
import org.free.todolist.ui.TodoListCellRenderer;

/**
 * 
 * @author juntao.qiu@gmail.com
 * 
 * created : 
 * 
 * modified : 2009/09/16
 *
 */
public class ListMainFrame extends JFrame{
	private static final long serialVersionUID = 320412556766404024L;
	
	private NewTaskDialog newTaskDialog;
	private PreferenceDialog preferenceDialog;
	
	private TrayIcon trayIcon;
	
	private SystemTray systemTray;
	
	private JPopupMenu pmOnItem;
	
	private FilterableList ftodolist;
	
	public ListMainFrame(String title){
		super(title);
	}

	class ListItemListener extends MouseAdapter{
		public void mouseEntered(MouseEvent e){
			JList list = (JList)e.getSource();
			int index = list.locationToIndex(e.getPoint());
			if(index <= 0)return;
			TodoItem item = (TodoItem)list.getModel().getElementAt(index);
			String tooltip = 
				"Desc : "+item.getDesc()+
				", Status : "+item.getStatus()+
				", Timeout:"+item.getTimeout();
			list.setToolTipText(
					"<html>"+tooltip+
					"</html>"
			);
		}
		
		public void mouseExited(MouseEvent e){
			JList list = (JList)e.getSource();
			list.setToolTipText("");
		}
		
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				JList list = (JList)e.getSource();
				int index = list.locationToIndex(e.getPoint());
				TodoItem item = (TodoItem)list.getModel().getElementAt(index);
					
				EditTaskDialog editTaskDialog = 
					new EditTaskDialog(ListMainFrame.this, "Edit exist task", item);
				editTaskDialog.setLocationRelativeTo(null);//center of the screen
				editTaskDialog.setVisible(true);
			}
		}
	}
	
    class PopupListener extends MouseAdapter implements ActionListener{
		JPopupMenu popupMenu;
		Component selected;
		Point point;
		
		PopupListener(JPopupMenu popupMenu) {
			this.popupMenu = popupMenu;
			initEventHandlers();
		}

		private void initEventHandlers(){
			Component[] menus = popupMenu.getComponents();
			for(Component item : menus){
				if(!(item instanceof JPopupMenu.Separator)){
					((JMenuItem)item).addActionListener(this);
				}
			}
		}
		
		public void mousePressed(MouseEvent e) {
			showPopupMenu(e);
		}

		public void mouseReleased(MouseEvent e) {
			showPopupMenu(e);
		}

		private void showPopupMenu(MouseEvent e) {
			if (e.isPopupTrigger()) {
				selected = e.getComponent();
				point = e.getPoint();
				if(selected instanceof JList){
					JList list = (JList)selected;
					int index = list.locationToIndex(point);
					list.setSelectedIndex(index);
				}
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}

		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			JList list = (JList)selected;
			int index = list.locationToIndex(point);
			TodoItem item = (TodoItem)list.getModel().getElementAt(index);
			
			if(command.equals("Delete item")){
				if(JOptionPane.showConfirmDialog(
						ListMainFrame.this, 
						"Are you sure you want to delete item?", 
						"??", 
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					deleteItem(item);//not delete the item from database by now.	
				}
			}else if(command.equals("Edit item")){
				EditTaskDialog editTaskDialog = 
					new EditTaskDialog(ListMainFrame.this, "Edit exist task", item);
				editTaskDialog.setLocationRelativeTo(null);
				editTaskDialog.setVisible(true);
			}else if(command.equals("Mail this item")){
				NewMailDialog newMailDialog = 
					new NewMailDialog(ListMainFrame.this, "Send this item ", item);
				newMailDialog.setLocationRelativeTo(null);
				newMailDialog.setVisible(true);
			}else if(command.equals("Get help")){
				//show help doc
			}
		}
	}
    
	public void initUI(){
    	try{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	}catch(Exception e){
    		System.err.println(e.getMessage());
    	}
    	
    	JMenuItem delMenuItem = new JMenuItem("Delete item", new ImageIcon("imgs/delete.gif"));
    	JMenuItem editMenuItem = new JMenuItem("Edit item", new ImageIcon("imgs/edit2.gif"));
    	JMenuItem mailMenuItem = new JMenuItem("Mail this item", new ImageIcon("imgs/mail.gif"));
    	JMenuItem helpMenuItem = new JMenuItem("Get help", new ImageIcon("imgs/help.gif"));
    	
    	pmOnItem = new JPopupMenu("Edit menu");
    	pmOnItem.addSeparator();
    	pmOnItem.add(delMenuItem);
    	pmOnItem.add(editMenuItem);
    	pmOnItem.add(mailMenuItem);
    	pmOnItem.addSeparator();
    	pmOnItem.add(helpMenuItem);
    	
    	MouseListener popupListener = new PopupListener(pmOnItem);
    	
    	systemTray = SystemTray.getSystemTray();
		try {
			trayIcon = new TrayIcon(ImageIO.read(new File("imgs/icon.png")));
			systemTray.add(trayIcon);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		addWindowListener(new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
				dispose();
			}
		});

		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					setExtendedState(NORMAL);
				setVisible(true);
			}
		});
		
		trayIcon.setToolTip("Simple todo manager");
		
		PopupMenu popup = new PopupMenu("sTodo");
		MenuItem iexit = new MenuItem("Exit");
		iexit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int y = JOptionPane.showConfirmDialog(
						null, 
						"Confirm exit", 
						"Confirm Exit Dialog", 
						JOptionPane.YES_NO_OPTION);
				if(y == JOptionPane.YES_OPTION){System.exit(0);}
			}
		});
		
		MenuItem show = new MenuItem("Show");
		show.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setExtendedState(NORMAL);
				setVisible(true);
			}
		});
		
		popup.add(iexit);
		popup.add(show);
		
		trayIcon.setPopupMenu(popup);
		
    	TodoItemListBuilder builder = new TodoItemListBuilder();
    	List<TodoItem> tlist = builder.getTodoItems();
    	
    	ftodolist = new FilterableList();
    	for(TodoItem item : tlist){
    		ftodolist.addElement(item);
    	}
    	
    	ftodolist.setCellRenderer(new TodoListCellRenderer());
    	ftodolist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	JScrollPane sp = new JScrollPane(ftodolist);
    	
    	ftodolist.addMouseListener(new ListItemListener());
    	ftodolist.addMouseListener(popupListener);
    	
    	JMenuBar mbar = new JMenuBar();
    	JMenu fileMenu = new JMenu("File");
    	JMenuItem newTask = new JMenuItem("New task", new ImageIcon("imgs/schedule_new.gif"));
    	newTask.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
    	newTask.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(newTaskDialog == null){
					newTaskDialog = new NewTaskDialog(ListMainFrame.this, "New Task");
				}
				newTaskDialog.setLocationRelativeTo(null);
				newTaskDialog.setVisible(true);
			}
    	});
    	
    	JMenuItem exit = new JMenuItem("Exit", new ImageIcon("imgs/close.gif"));
    	exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
    	exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int y = JOptionPane.showConfirmDialog(
						null, 
						"Confirm exit", 
						"Confirm Exit Dialog", 
						JOptionPane.YES_NO_OPTION);
				if(y == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
    		
    	});
    	
    	fileMenu.add(newTask);
    	fileMenu.add(exit);
    	
    	JMenu editMenu = new JMenu("Edit");
    	JMenuItem exportText = new JMenuItem("Export Text", new ImageIcon("imgs/exptotext.gif"));
    	JMenuItem exportExcel = new JMenuItem("Export Excel", new ImageIcon("imgs/exptoexcel.gif"));
    	JMenuItem exportHtml = new JMenuItem("Export HTML", new ImageIcon("imgs/exptohtml.gif"));
    	
    	exportHtml.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				List<TodoItem> list = new LinkedList<TodoItem>();
				for(int i = 0;i < ftodolist.getContents().getSize();i++){
					list.add((TodoItem)ftodolist.getContents().getElementAt(i));
				}
				Exporter exporter = new HTMLExporter(list, "temp.html");
				exporter.store();
			}
    	});
    	
    	JMenuItem settings = new JMenuItem("Preference", new ImageIcon("imgs/customize.gif"));
    	
    	settings.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			if(preferenceDialog == null){
    				preferenceDialog = new PreferenceDialog(ListMainFrame.this, "Preference");
    			}
    			preferenceDialog.setLocationRelativeTo(null);
    			preferenceDialog.setVisible(true);
    		}
    	});
    	
    	editMenu.add(exportText);
    	editMenu.add(exportExcel);
    	editMenu.add(exportHtml);
    	editMenu.addSeparator();
    	editMenu.add(settings);
    	
    	
    	mbar.add(fileMenu);
    	mbar.add(editMenu);
    	
    	setJMenuBar(mbar);
    	
    	final JToolBar toolbar = new JToolBar();
    	final JTextField filter = new JTextField();
    	ftodolist.installFilterField(filter);
    	toolbar.add(filter, BorderLayout.CENTER);
    	toolbar.setVisible(false);
    	
    	KeyListener searchTrigger = new KeyListener(){
			public void keyPressed(KeyEvent e) {
    			if(e.getKeyCode() == KeyEvent.VK_F &&
    					e.getModifiers() == KeyEvent.CTRL_MASK || e.getKeyCode() == KeyEvent.VK_SLASH){
    				if(toolbar.isVisible()){
    					toolbar.setVisible(false);
    				}else{
    					toolbar.setVisible(true);
    					filter.requestFocus();
    				}
    			}
			}

			public void keyReleased(KeyEvent e) {}

			public void keyTyped(KeyEvent e) {}
    	};
    	
    	KeyListener escapeTrigger = new KeyListener(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					if(toolbar.isVisible()){
						filter.setText("");
						toolbar.setVisible(false);
						ListMainFrame.this.requestFocus();
					}
				}
			}

			public void keyReleased(KeyEvent e) {}

			public void keyTyped(KeyEvent e) {}
    	};
    	
    	filter.addKeyListener(escapeTrigger);
    	
    	addKeyListener(searchTrigger);
    	ftodolist.addKeyListener(searchTrigger);
    	
    	JMenuItem search = new JMenuItem("Search", new ImageIcon("imgs/filter.gif"));
    	search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				toolbar.setVisible(true);
			}
    	});
    	
    	editMenu.add(search);
    	
    	getContentPane().add(toolbar, BorderLayout.NORTH);
    	
    	getContentPane().add(sp);
    	setSize(400, 650);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(false);
    	setLocationRelativeTo(null);//center of the screen
    	setFocusable(true);
    	setVisible(true);
	}
	
	public void updateList(TodoItem item){
		FilterableListModel model = ftodolist.getContents();
		
		for(int i = 0;i < model.getSize();i++){
			TodoItem titem = (TodoItem)model.getElementAt(i);
			if(titem.getId().equals(item.getId())){
				model.removeElement(i);
				model.addElement(item);
				return;
			}
		}
		
		model.addElement(item);
	}
	
	public void deleteItem(TodoItem item){
		FilterableListModel model = ftodolist.getContents();
		
		for(int i = 0;i < model.getSize();i++){
			TodoItem titem = (TodoItem)model.getElementAt(i);
			if(titem.getId().equals(item.getId())){
				model.removeElement(i);
				break;
			}
		}
	}
	
	public void refreshModel(List<TodoItem> list){
		FilterableListModel model = ftodolist.getContents();
		model.clear();
		
		for(TodoItem item : list){
			model.addElement(item);
		}
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				ListMainFrame lMain = new ListMainFrame("My todo list");
				lMain.initUI();
			}
		});
	}
	
}
