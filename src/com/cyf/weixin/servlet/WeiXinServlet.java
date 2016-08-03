package com.cyf.weixin.servlet;    
  
import javax.servlet.http.HttpServletRequest;

import com.cyf.weixin.service.WeiXinService; 
  
public class WeiXinServlet extends StringServletBase {  
  
	private int count = 0; 
    private static final long serialVersionUID = 1L;  
  
    private WeiXinService wxService = new WeiXinService();

  
    @Override  
    protected String parseString(HttpServletRequest request){  
    	try {
    		if(count==0){
    			count++;
    			return wxService.check(request);
    		}else {
    			return wxService.replay(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
    }  
  
}