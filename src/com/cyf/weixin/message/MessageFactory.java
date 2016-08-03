package com.cyf.weixin.message;

public class MessageFactory {
	public NewsMsg createNews(String toUser,String fromUser){
		return new NewsMsg(toUser, fromUser);
	}
	
	public TextMsg createText(String toUser,String fromUser,String Content){
		return new TextMsg(toUser, fromUser, Content);
	}
	
	
	/*public static void main(String[] args) {
		MessageFactory factory = new MessageFactory();
		TextMsg text = factory.createText("to","from","test");
		NewsMsg news = factory.createNews("to","from",2);
		news.addItem("title1", "description1", "picurl1", "url1");
		news.addItem("2222", "2222222", "2222", "2222");
		System.out.println(news.createMsg());
	}*/
}
