package org.free.todolist.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.free.todolist.model.TodoItem;

/**
 * This is the data-service of <code>sTodo</code>, it provides
 * operations on data such as insert, update, delete, and search
 * it a single instance in this <code>sTodo</code>application.
 * 
 * @author juntao.qiu@gmail.com
 *
 */
public class DataService {
	private String message;
	private boolean status;
	
	private DataService(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static DataService instance;
	
	public static DataService getInstance(){
		synchronized(DataService.class){
			if(instance == null){
				instance = new DataService();
			}
		}
		
		return instance;
	}
	
	/**
	 * insert new <code>TodoItem</code> in to database <code>stodoitem</code>
	 * 
	 * @param todo the entity of a todo-bean
	 * @return status of whether the operation is success or faild.
	 */
	public boolean addItem(TodoItem todo){
		String query = "INSERT INTO stodoitem (type, desc, timeout, period, note, status) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:stodoitem");
			PreparedStatement pstat = con.prepareStatement(query);
			pstat.setString(1, todo.getType());
			pstat.setString(2, todo.getDesc());
			pstat.setString(3, todo.getTimeout());
			pstat.setString(4, todo.getPeriod());
			pstat.setString(5, todo.getNote());
			pstat.setString(6, todo.getStatus());
			
			pstat.execute();
			
			ResultSet rs = pstat.getGeneratedKeys();
			while(rs.next()){
				int id = rs.getInt(1);
				todo.setId(String.valueOf(id));
			}
			status = true;
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			message = e.getMessage();
			status = false;
		}
		
		return status;
	}
	
	/**
	 * remove <code>TodoItem</code> entity from database
	 * 
	 * @param todo todo-item entity
	 * @return
	 */
	public boolean removeItem(TodoItem todo){
		String query = "DELETE FROM stodoitem WHERE itemid="+todo.getId();
		try{
			Connection con = DriverManager.getConnection("jdbc:sqlite:stodoitem");
			Statement stat = con.createStatement();
			stat.execute(query);
			status = true;
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			message = e.getMessage();
			status = false;
		}
		
		return status;
	}
	
	/**
	 * update all fields of a <code>todoitem</code> in database.
	 * @param todo
	 * @return
	 */
	public boolean updateItem(TodoItem todo){
		String query = "UPDATE stodoitem SET type=?, desc=?, timeout=?, period=?, note=?, status=? WHERE itemid="+todo.getId();
		try{
			Connection con = DriverManager.getConnection("jdbc:sqlite:stodoitem");
			PreparedStatement pstat = con.prepareStatement(query);
			pstat.setString(1, todo.getType());
			pstat.setString(2, todo.getDesc());
			pstat.setString(3, todo.getTimeout());
			pstat.setString(4, todo.getPeriod());
			pstat.setString(5, todo.getNote());
			pstat.setString(6, todo.getStatus());
			
			pstat.execute();
			status = true;
			
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			message = e.getMessage();
			status = false;
		}
		
		return status;
	}
	
	/**
	 * search the database, by <code>by</code> (which is the column name in database),
	 * and value, the real text like 123, tomorrow, etc.
	 * 
	 * @param by the column in database
	 * @param value the value of the column
	 * @return
	 */
	public List<TodoItem> searchList(String by, String value){
		List<TodoItem> list = new LinkedList<TodoItem>();
		String query = "SELECT itemid, type, desc, timeout, period, status, note FROM stodoitem WHERE "+by+" LIKE \""+value+"\"";
		try{
			Connection con = DriverManager.getConnection("jdbc:sqlite:stodoitem");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next()){
				TodoItem item = new TodoItem();
				item.setId(String.valueOf(rs.getInt("itemid")));
				item.setDesc(rs.getString("desc"));
				item.setType(rs.getString("type"));
				item.setTimeout(rs.getString("timeout"));
				item.setPeriod(rs.getString("period"));
				item.setStatus(rs.getString("status"));
				item.setNote(rs.getString("note"));
				
				list.add(item);
			}
			status = true;
		}catch(Exception e){
			e.printStackTrace();
			message = e.getMessage();
			status = false;
		}
		
		return list;
	}
	
	/**
	 * get error message
	 * @return null if no error, the error message otherwise
	 */
	public String getMessage(){
		return message;
	}
}
