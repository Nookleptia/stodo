package org.free.todolist.manager;

import javax.swing.JDialog;

import org.free.todolist.model.TodoItem;
import org.free.todolist.ui.AlarmDialog;

/**
 * 
 * @author juntao.qiu@gmail.com
 *
 */
public class AlarmService {
	private AlarmService(){
		taskMgr = TaskManager.getInstance();
	}
	
	private static AlarmService alerm;
	private static TaskManager taskMgr;
	
	public static AlarmService getInstance(){
		synchronized(AlarmService.class){
			if(alerm == null){
				alerm = new AlarmService();
			}
		}
		return alerm;
	}
	
	public void addTodoItem(TodoItem item){
		Task nt = new Alert(item);
		taskMgr.scheduleTask(nt, 10000);
	}
	
	public void cancelAlert(TodoItem item){
		Task nt = new Alert(item);
		taskMgr.cancelTask(nt.getId());
	}
	
	class Alert implements Task{
		private TodoItem item;
		
		public Alert(TodoItem item) {
			this.item = item;
		}
		
		public void execute(){
			AlarmDialog dialog = new AlarmDialog(item);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setAlwaysOnTop(true);
			dialog.setLocationRelativeTo(null);
			dialog.setSize(386, 160);
			dialog.setVisible(true);
		}

		public String getId() {
			return item.getId();
		}

		public void setTaskContext(TaskContext context) {
			
		}

		public void setTaskListener(TaskListener listener) {
			
		}
	}
}
