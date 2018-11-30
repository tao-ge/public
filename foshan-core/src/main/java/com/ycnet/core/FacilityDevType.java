package com.ycnet.core;

/**
 * 
* @ClassName: FacilityDevType 
* @Description: TODO(设施类型) 
* @author (刘沧海)  
* @date 2017年12月1日 下午3:56:09 
* @version V1.0
 */
public enum FacilityDevType {

	/**
	 * 光纤
	 */
	ONU("01"),   			//光交箱
	OTB("02"),				//光终端盒
	FDB("03"),				//光分纤箱
	OFS("04"),				//光缆接头
	ODF("05"),           	//机柜 
	SFP("06"),				//分光器
	POLE("10"),				//杆
	WELL("11"),				//井
	GUA("13"),				//挂墙
	MARST("14"),			//标石
	BURIAL("15"), 			//直埋
	ROOM("20"),             //机房
	PTN("30"),				//PTN
	OLT("31"),				//OLT
	SDH("32"),				//SDH
	BBU("33"),				//BBU
	NONE("")
	;
	
	private String value;
	
	private  FacilityDevType(String value)
	{
		this.value=value;
	}
	
	@Override
	public String toString()
	{
		return this.value;
	}
	
	public static FacilityDevType toType(String value)
	{
		for(FacilityDevType t: FacilityDevType.values())
		{
			if(t.value.equals(value))
				return t;
		}
		return NONE;
	}
}
