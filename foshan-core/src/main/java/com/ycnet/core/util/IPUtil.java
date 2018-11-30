package com.ycnet.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPUtil {

	public static String getV4IP(){
		String ip = "";
		String chinaz = "http://www.882667.com/";
//		String chinaz = "http://www.ip138.com/";
		
		
		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		try {
			url = new URL(chinaz);
			urlConnection = (HttpURLConnection) url.openConnection();
		    in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			while((read=in.readLine())!=null){
				inputLine.append(read+"\r\n");
			}
			//System.out.println(inputLine.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
//		System.out.println(inputLine.toString());
//		Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
		Pattern p = Pattern.compile("\\<span class\\=\"shenlansezi\">(.*?)\\<\\/span>");

		Matcher m = p.matcher(inputLine.toString());
		if(m.find()){
			String ipstr = m.group(1);
			ip = ipstr;
			//System.out.println(ipstr);
		}
		return ip;
	}
	
	public static void main(String[] args) {
		System.out.println("444");
		System.out.println(getV4IP());
	}
	
	public static String getRemoteHost40Name() {
		String pmName="";
		String ip = IPUtil.getV4IP();
		if (ip.equals("120.77.183.40")) {
			pmName="传输网络资源普查系统";
		}else {
			pmName="传输网络智慧管理系统";
		}
		return pmName;
	}
}
  
    