package org.free.todolist.manager;

import javax.swing.JOptionPane;

import org.free.todolist.model.TodoItem;

public class AlermService {
	private AlermService(){
		taskMgr = TaskManager.getInstance();
	}
	
	private static AlermService alerm;
	private static TaskManager taskMgr;
	
	public static AlermService getInstance(){
		synchronized(AlermService.class){
			if(alerm == null){
				alerm = new AlermService();
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
			JOptionPane.showMessageDialog(
					null, 
					item.getDesc(), 
					"Task need to do now!!", 
					JOptionPane.INFORMATION_MESSAGE);
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
