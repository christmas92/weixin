package com.cyf.weixin.message;

public class Message {
	private String toUser;
	private String fromUser;
	private long createTime;
	public Message(String toUser,String fromUser) {
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.createTime = System.currentTimeMillis();
	}
	
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
}
