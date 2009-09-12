package org.free.todolist.model;

/**
 * todo item is a simple tree data-model, it can be used to build a swing-jtree
 * component
 * 
 * @author juntao.qiu@china.jinfonet.com
 * 
 */
public class TodoItem {
	private String id;
	private String desc;

	private String type;

	private String timeout;
	private String period;

	private String status;

	private String note;

	public TodoItem() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer
		.append("[")
			.append("desc : "+desc)
			.append(",type : "+type)
			.append(",timeout : "+timeout)
			.append(",period : "+period)
			.append(",status : "+status)
			.append(",note : "+note)
		.append("]");
		
		return buffer.toString();
	}

	public static void main(String[] args) {
	}
}
