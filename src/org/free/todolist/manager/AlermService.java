package org.free.todolist.manager;

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
		Task nt = new Alert(item.getId());
		taskMgr.scheduleTask(nt, 1000);
	}
	
	public void cancelAlert(TodoItem item){
		Task nt = new Alert(item.getId());
		taskMgr.cancelTask(nt.getId());
	}
	
	class Alert extends GeneralTask{
		
		public Alert(String taskId) {
			super(taskId);
		}
		
		public void execute(){
			
		}
	}
}
