package com.cyf.weixin.message;

public class MessageFactory {
	public NewsMsg createNews(String toUser,String fromUser){
		return new NewsMsg(toUser, fromUser);
	}
	
	public TextMsg createText(String toUser,String fromUser,String Content){
		return new TextMsg(toUser, fromUser, Content);
	}
	
}
