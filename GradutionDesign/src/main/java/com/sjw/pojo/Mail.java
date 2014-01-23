package com.sjw.pojo;

public class Mail {
	private int unreadnum;
	private int total;
	private String date;
	private String subject;
	private String content;
	private int messagenum;
	private String receivers;
	private boolean flags;
	private String sender;
	private boolean withattach;
	private String[] attachnames;
	
	public boolean isWithattach() {
		return withattach;
	}
	public void setWithattach(boolean withattach) {
		this.withattach = withattach;
	}
	public int getUnreadnum() {
		return unreadnum;
	}
	public void setUnreadnum(int unreadnum) {
		this.unreadnum = unreadnum;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public int getMessagenum() {
		return messagenum;
	}
	public void setMessagenum(int messagenum) {
		this.messagenum = messagenum;
	}
	public String getReceivers() {
		return receivers;
	}
	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}
	public boolean getFlags() {
		return flags;
	}
	public void setFlags(boolean flags) {
		this.flags = flags;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String[] getAttachnames() {
		return attachnames;
	}
	public void setAttachnames(String[] attachnames) {
		this.attachnames = attachnames;
	}
	
}
