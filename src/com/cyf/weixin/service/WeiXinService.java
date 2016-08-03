package com.cyf.weixin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;

import com.cyf.util.DEC;
import com.cyf.util.XmlUtil;
import com.cyf.weixin.message.MessageFactory;
import com.cyf.weixin.message.NewsMsg;
import com.cyf.weixin.message.TextMsg;
import com.cyf.weixin.str.Context;
import com.cyf.weixin.str.News;

public class WeiXinService {
	@SuppressWarnings("unchecked")
	private HashMap<String, String> parseRequestXml(HttpServletRequest request) throws Exception{  

        HashMap<String, String> map = new HashMap<String, String>();    
        Document document = XmlUtil.createXML(request);  

        Element root = document.getRootElement();    

        List<Element> elementList = root.elements();    
          
        for (Element e : elementList){  
            map.put(e.getName(), e.getText());  
        }  
        return map;    
    }
	
	public String replay(HttpServletRequest request) throws Exception{
		Map<String, String> map = parseRequestXml(request);
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
