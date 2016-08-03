package com.cyf.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

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
}
