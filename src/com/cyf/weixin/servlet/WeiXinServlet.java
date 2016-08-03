package com.cyf.weixin.servlet;    
  
import javax.servlet.http.HttpServletRequest;

import com.cyf.weixin.service.WeiXinService;
import com.cyf.weixin.util.SignUtil;  
  
public class WeiXinServlet extends StringServletBase {  
  
	private int count = 0; 
    private static final long serialVersionUID = 1L;  
  
    private WeiXinService wxService = new WeiXinService();

    protected String check(HttpServletRequest request) {   
        String signature = request.getParameter("signature");  
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            return echostr;  
        }  
        return null;  
    }
  
    @Override  
    protected String parseString(HttpServletRequest request){  
        //΢���״���֤  
    	//return check(request);
    	try {
    		if(count==0){
    			count++;
    			return check(request);
    		}else {
    			return wxService.replay(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
    }  
  
}