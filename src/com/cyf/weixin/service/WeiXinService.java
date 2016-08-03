package com.cyf.weixin.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.cyf.util.DEC;
import com.cyf.util.XmlUtil;
import com.cyf.weixin.message.MessageFactory;
import com.cyf.weixin.message.NewsMsg;
import com.cyf.weixin.message.TextMsg;
import com.cyf.weixin.str.Context;
import com.cyf.weixin.str.News;
import com.cyf.weixin.util.SignUtil;

public class WeiXinService {
	
	//初始接口配置校验
	public String check(HttpServletRequest request) {   
        String signature = request.getParameter("signature");  
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            return echostr;  
        }  
        return null;  
    }
	
	public String replay(HttpServletRequest request) throws Exception{
		Map<String, String> map = XmlUtil.parseRequestXml(request);
		if(map == null){
			return "";
		}
		String MsgType = map.get("MsgType");
		String toUser = map.get("FromUserName");
		String fromUser = map.get("ToUserName");
		MessageFactory factory = new MessageFactory();
		switch(MsgType){
			case "text":
					String content = map.get("Content");
					switch(content){
						case "1":
							NewsMsg news = factory.createNews(toUser, fromUser);
							news.addItem("test", 
									"description", 
									"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png", 
									"https://www.baidu.com/");
							return news.createMsg();
						case "2":
							return factory.createText(toUser, fromUser, "test").createMsg();
					}		
					break;
			case "event":
				String event = map.get("Event");
				switch(event){
					case "subscribe":
						return new TextMsg(toUser,fromUser,Context.substr).createMsg();
					case "CLICK":
						String eventkey = map.get("EventKey");
						if(eventkey.equals("ygh")){
							NewsMsg news = new NewsMsg(toUser,fromUser);
							news.addItem(News.vote.title, 
									News.vote.description, 
									News.vote.picurl, 
									News.vote.url+DEC.encode("cyf_lost", toUser).replace('/', '&'));
							return news.createMsg();
						}
						break;
				}
			default:
				return "";
		}
		return "";
	}
}
