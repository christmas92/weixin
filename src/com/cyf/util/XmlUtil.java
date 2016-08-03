package com.cyf.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtil {
	public static Document createXML(String text) throws Exception{
		return DocumentHelper.parseText(text);  	
	}
	
	public static Document createXML(HttpServletRequest request) throws Exception{
 
        StringBuffer sb = new StringBuffer();  
        InputStream is = request.getInputStream();  
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
        BufferedReader br = new BufferedReader(isr);  
        String s = "";  
        while ((s = br.readLine()) != null) {  
            sb.append(s);  
        }  
        String xml = sb.toString(); 
		return DocumentHelper.parseText(xml);  	
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> parseRequestXml(HttpServletRequest request) throws Exception{  

        HashMap<String, String> map = new HashMap<String, String>();    
        Document document = XmlUtil.createXML(request);  

        Element root = document.getRootElement();    

		List<Element> elementList = root.elements();    
          
        for (Element e : elementList){  
            map.put(e.getName(), e.getText());  
        }  
        return map;    
    }
}
