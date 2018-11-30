package com.ycnet.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ASCIIUtil {

	
	/**
	 * 将字符串转成ASCII的java方法
	* @Title: stringToAscii 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param value
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年7月26日 上午10:00:19 
	* @version V1.0
	 */
	public static String stringToAscii(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    char[] chars = value.toCharArray();   
	    for (int i = 0; i < chars.length; i++) {  
	        if(i != chars.length - 1)  
	        {  
	            sbu.append((int)chars[i]).append(",");  
	        }  
	        else {  
	            sbu.append((int)chars[i]);  
	        }  
	    }  
	    return sbu.toString().replaceAll(",", "");  
	}  
	
	/**
	 * 将ASCII转成字符串的java方法
	* @Title: asciiToString 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param value
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年7月26日 上午10:00:29 
	* @version V1.0
	 */
	public static String asciiToString(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    String[] chars = value.split(",");  
	    for (int i = 0; i < chars.length; i++) {  
	        sbu.append((char) Integer.parseInt(chars[i]));  
	    }  
	    return sbu.toString();  
	}  
	
	
	public static void main(String[] args) {
		String str = "860111027646375";
		String asciiResult = stringToAscii(str);
		System.out.println(asciiResult);
		String stringResult = asciiToString(asciiResult);
		System.out.println(stringResult);
		
		System.out.println(String.format("%010d", -1));
		System.out.println(Integer.toHexString(1440));
		System.out.println(String.format("%0"+3+"d", 1).replace("1", "0"));
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>();
		map.put("a","aaa"); 
		map.put("b","bbb"); 
		map.put("c","ccc"); 
		list.add(map);
		list.add(map);
		String json=JSONArray.fromObject(list).toString();
		System.out.println(json);
		
		
		System.out.println(ASCIIUtil.stringToAscii("1312312").replaceAll(",", ""));
	}
}
