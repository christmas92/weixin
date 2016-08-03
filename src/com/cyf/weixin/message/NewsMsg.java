package com.cyf.weixin.message;

public class NewsMsg extends Message{
	private static final int maxArticles = 10;
	private static final String MsgType = "news";
	private String[] items;
	private int articlecount;
	
	public NewsMsg(String toUser,String fromUser) {
		super(toUser, fromUser);
		this.articlecount = 0;
		items = new String[maxArticles];
	}

	public void addItem(String title,String description,String picurl,String url){
		String xml = "<item>"
				  + "<Title><![CDATA[" + title + "]]></Title>"
	        	  + "<Description><![CDATA[ " + description + " ]]></Description>"
		       	  + "<PicUrl><![CDATA[ " + picurl + " ]]></PicUrl>"
			      + "<Url><![CDATA[ " + url + " ]]></Url>"
			      + "</item>";
		if(articlecount>10){
			System.out.println("ERROR, too many articles");
		}else {
			items[articlecount++] = xml;
		}
		
	}
	
	public String createMsg(){
		String xml = "<xml>"
				   + "<ToUserName><![CDATA[" + super.getToUser() + "]]></ToUserName>"
				   + "<FromUserName><![CDATA[" + super.getFromUser() + "]]></FromUserName>"
				   + "<CreateTime>" + super.getCreateTime() + "</CreateTime>"
				   + "<MsgType><![CDATA[" + MsgType + "]]></MsgType>"
				   + "<ArticleCount>" + articlecount + "</ArticleCount>"
				   + "<Articles>";
		for (int i = 0; i < articlecount; i++) {
			xml = xml + items[i];
		}		   
		xml = xml + "</Articles>" + "</xml>";
		return xml;
	}

	public static void main(String[] args) {
		NewsMsg msg = new NewsMsg("toUser","fromUser");
		msg.addItem("title1","description1","picurl1","url1");
		msg.addItem("title2","description2","picurl2","url2");
		System.out.println(msg.createMsg());
	}
}

