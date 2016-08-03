 /**   
 *@Description: JAVAʵ�ֳ��������㷨 
 */   
package com.cyf.util;    
  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
    
public class Encrypt {  
  
    /** 
     * @param str ��Ҫ���ܵ��ַ��� 
     * @param encName ��������  MD5 SHA-1 SHA-256 
     * @return 
     * @Author:cyf   
     * @Description: ʵ�ֶ��ַ����ļ��� 
     */  
    public static String encrypt(String str, String encName){  
        String reStr = null;  
        try {  
            MessageDigest md5 = MessageDigest.getInstance(encName);  
            byte[] bytes = md5.digest(str.getBytes());  
            StringBuffer stringBuffer = new StringBuffer();  
            for (byte b : bytes){  
                int bt = b&0xff;  
                if (bt < 16){  
                    stringBuffer.append(0);  
                }   
                stringBuffer.append(Integer.toHexString(bt));  
            }  
            reStr = stringBuffer.toString();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return reStr;  
    }  
      
    public static void main(String[] args) {  
        System.out.println(Encrypt.encrypt("nihao", null));  
    }  
      
}  