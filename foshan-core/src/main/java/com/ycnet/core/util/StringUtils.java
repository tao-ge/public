package com.ycnet.core.util;

public class StringUtils {
	

	/**
	 * 左补零
	 * @param num
	 * @param len
	 * @return
	 */
	public static String leftZeroFill(int num,int len)
	{
		return String.format("%0" + len + "d", num);
	}
	
	public static String concat(String split,String... args)
	{
		split = split==null||"".equals(split.trim())?"-":split;
		StringBuffer sb = new StringBuffer("");
		for(String temp:args)
		{
			if(sb.length()==0)
				sb.append(temp);
			else
				sb.append(temp==null||temp.trim().length()==0?"":split+temp);
		}
		return sb.toString();
	}
	
	public static String genPoint(Object... args)
	{
		String str = "";
		for(Object o: args)
		{
			if(o instanceof Integer)
				str = concat("-",str,leftZeroFill((Integer)o,2));
			else if(o instanceof Long)
				str = concat("-",str,leftZeroFill(((Long)o).intValue(),2));
			else
				if(o!=null)
					str = concat("-",str,o.toString());
		}
		
		return str;
	}
	
	public static String concatRouteName(String acode,String zcode)
	{
		return acode +"<==>"+zcode;
	}
	
	public static String getRouteJoin()
	{
		return "-->";
	}
}
