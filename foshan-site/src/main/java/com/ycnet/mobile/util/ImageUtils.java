package com.ycnet.mobile.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.Base64Utils;

import sun.misc.BASE64Encoder;

public class ImageUtils {

	public static boolean generatorImage(String imgStr,String imgFilePath)
	{
		if(imgStr==null||imgFilePath==null)
			return false;
		
		return true;
	}
	
	public static String imageConvertStr(String imgStr)
	{
		byte[] data= null;
		if(imgStr ==null)
			return "";
		BASE64Encoder encoder = new BASE64Encoder();
		
		InputStream in = null;
		try {
			in =  new FileInputStream("d:/ad.jpg");
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encoder.encode(data);
		
	}
	
	public static void main(String[] args) {
		String s = ImageUtils.imageConvertStr("");
		System.out.println(s.length());
	}
}
