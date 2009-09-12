package org.free.todolist.data;

public class SimpleTextMail {
	private String sendTo;
	private String ccTo;
	private String subject;
	private String content;
	
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getCcTo() {
		return ccTo;
	}
	public void setCcTo(String ccTo) {
		this.ccTo = ccTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
