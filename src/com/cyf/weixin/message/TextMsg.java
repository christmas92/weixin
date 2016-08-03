package com.cyf.weixin.message;

public class TextMsg extends Message{
	private static final String MsgType = "text";
	private String Content;
	
	public TextMsg(String toUser,String fromUser,String Content ) {
		super(toUser, fromUser);
		this.Content = Content;
	}
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
	public String createMsg(){
		String xml = "<xml>"
				   + "<ToUserName><![CDATA[" + super.getToUser() + "]]></ToUserName>"
				   + "<FromUserName><![CDATA[" + super.getFromUser() + "]]></FromUserName>"
				   + "<CreateTime>" + super.getCreateTime() + "</CreateTime>"
				   + "<MsgType><![CDATA[" + MsgType + "]]></MsgType>"
				   + "<Content><![CDATA[" + Content + "]]></Content>"
				   + "</xml>";
		return xml;
	}
}
