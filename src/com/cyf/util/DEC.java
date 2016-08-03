package com.cyf.util;


import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;



public class DEC {
	public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
	  /**
	   * DES算法，加密
	   *
	   * @param data 待加密字符串
	   * @param key 加密私钥，长度不能够小于8位
	   * @return 加密后的字节数组，一般结合Base64编码使用
	   * @throws CryptException 异常
	   */
	  public static String encode(String key,String data) throws Exception
	  {
	    return encode(key, data.getBytes());
	  }
	  /**
	   * DES算法，加密
	   *
	   * @param data 待加密字符串
	   * @param key 加密私钥，长度不能够小于8位
	   * @return 加密后的字节数组，一般结合Base64编码使用
	   * @throws CryptException 异常
	   */
	  public static String encode(String key,byte[] data) throws Exception
	  {
	    try
	    {
	        DESKeySpec dks = new DESKeySpec(key.getBytes());
	  
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	      //key的长度不能够小于8位字节
	      Key secretKey = keyFactory.generateSecret(dks);
	      Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
	      IvParameterSpec iv = new IvParameterSpec(key.getBytes());
	      AlgorithmParameterSpec paramSpec = iv;
	      cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);
	  
	      byte[] bytes = cipher.doFinal(data);
	  
	  
//	      return byte2hex(bytes);
	      //return new String(Base64.encode(bytes));
	      return new String(Base64.encodeBase64(bytes));
	    } catch (Exception e)
	    {
	      throw new Exception(e);
	    }
	  }
	  
	  /**
	   * DES算法，解密
	   *
	   * @param data 待解密字符串
	   * @param key 解密私钥，长度不能够小于8位
	   * @return 解密后的字节数组
	   * @throws Exception 异常
	   */
	  public static byte[] decode(String key,byte[] data) throws Exception
	  {
	    try
	    {
	        DESKeySpec dks = new DESKeySpec(key.getBytes());
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	      //key的长度不能够小于8位字节
	      Key secretKey = keyFactory.generateSecret(dks);
	      Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
	      IvParameterSpec iv = new IvParameterSpec(key.getBytes());
	      AlgorithmParameterSpec paramSpec = iv;
	      cipher.init(Cipher.DECRYPT_MODE, secretKey,paramSpec);
	      return cipher.doFinal(data);
	    } catch (Exception e)
	    {
	      throw new Exception(e);
	    }
	  }
	  
	  /**
	   * 获取编码后的值
	   * @param key
	   * @param data
	   * @return
	   * @throws Exception
	   */
	  public static String decodeValue(String key,String data) 
	  {
	    byte[] datas;
	    String value = null;
	        try {
	  
	            //datas = decode(key, Base64.decode(data.getBytes()));
	            datas = decode(key, Base64.decodeBase64(data.getBytes()));
	            value = new String(datas);
	        } catch (Exception e) {
	            value = "";
	        }
	    return value;
	  }
	  
	  public static void main(String[] args) throws Exception {
		  String key = "cyf_9212";
		  String value = "oSXx9s_SXCGxmCTQpKuiZHfdW-dQ";
		  String encode = encode(key, value);
		  String decode = decodeValue(key, encode);
		  System.out.println(encode);
		  System.out.println(decode);
	  }
}
