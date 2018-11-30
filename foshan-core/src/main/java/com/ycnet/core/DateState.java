package com.ycnet.core;

/**
 * 
* @ClassName: DevState 
* @Description: 设施状态 
* @author (刘沧海)  
* @date 2017年12月1日 下午4:22:18 
* @version V1.0
 */
public enum DateState {

	
	NOCHECK("0"),           //未核对
	NORMAL("1"),    	    //正常
	NEWLY("2"),             //新增
	MODIF("3"),				//修改
	ZGDEL("4"),				//资管删除
	NEWDEL("5"),			//新增删除
	DOUBT("6"),				//存疑
	NONE("")
	;
	
	private String value;
	
	private  DateState(String value)
	{
		this.value=value;
	}
	
	@Override
	public String toString()
	{
		return this.value;
	}
	
	public static DateState toType(String value)
	{
		for(DateState t: DateState.values())
		{
			if(t.value.equals(value))
				return t;
		}
		return NONE;
	}
}
