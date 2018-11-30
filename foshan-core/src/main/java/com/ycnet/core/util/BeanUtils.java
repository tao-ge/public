package com.ycnet.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtils {

	public static void copy(Object parent,Object child)
	{
		if(child.getClass().getSuperclass() != parent.getClass())
		{
			return;
		}
		Class<?> parentClass = parent.getClass();
		Field[] fields = parentClass.getDeclaredFields();
		for(int i = 0;i<fields.length;i++)
		{
			Field f = fields[i];
			Class<?> type = f.getType();
			try {
					Method m = parentClass.getMethod("get"+upperHeadChar(f.getName()));
					Object o = m.invoke(parent);
					Method m1 = parentClass.getMethod("set"+upperHeadChar(f.getName()),type);
					m1.invoke(child, o);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	private static String upperHeadChar(String in){  
        String head=in.substring(0,1);  
        String out=head.toUpperCase()+in.substring(1,in.length());  
        return out;  
    } 
}
