package com.ycnet.core;

public enum LineType {

	/**
	 * 光纤
	 */
	FIBER("01"),                //光纤
	JUMPER("02"),           //跳纤，接头
	VIRTUAL("05"),           //虚拟线
	NONE("")
	;
	
	private String value;
	
	private  LineType(String value)
	{
		this.value=value;
	}
	
	@Override
	public String toString()
	{
		return this.value;
	}
	
	public static LineType toType(String value)
	{
		for(LineType t: LineType.values())
		{
			if(t.value.equals(value))
				return t;
		}
		return NONE;
	}
}
