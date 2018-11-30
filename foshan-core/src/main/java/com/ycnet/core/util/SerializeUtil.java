package com.ycnet.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ycnet.frms.vo.FiberRoute;

public class SerializeUtil {

	public static byte[] serialize(Object obj){
		byte[] bytes=null;
		ObjectOutputStream oos = null;
		ByteArrayOutputStream bos =null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			bytes= bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	@SuppressWarnings("unchecked")
	public static <T>  T unserialize(byte[] bytes,Class<T> clazz){
		T obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = (T)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}
