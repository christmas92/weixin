 /**   
 *@Description:  ΢����֤  
 */   
package com.cyf.weixin.util;    
  
import java.util.Arrays;  
  
import com.cyf.util.Encrypt;  
import com.cyf.weixin.config.Config;  
  
public class SignUtil {  
      
    /** 
     * @param signature 
     * @param timestamp 
     * @param nonce 
     * @return 
     * @Author:cyf 
     * @Description: 
     */  
    public static boolean checkSignature(String signature, String timestamp, String nonce) {  
        String[] arr = new String[] { Config.TOKEN, timestamp, nonce };  

        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();    
        for (int i = 0; i < arr.length; i++) {    
            content.append(arr[i]);    
        } 

        return signature == null ? false : signature.equals(Encrypt.encrypt(content.toString(), "SHA-1"));  
    }  
  
} 