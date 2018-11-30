package com.ycnet.core;

public enum FacilityType {
	/*光交(设施)*/
	GJ("GJ",4),
	/*光缆段*/
	GL("GL",5),
	/*熔纤盘(法兰盘)*/
	FL("FL",2),
	/*分光器*/
	FG("FG",2),
	
	;
	
	private String name;
	
	private int len;
	

	private FacilityType(String name,int len)
	{
		this.name = name ;
		this.len = len;
	}
	
	public String toString()
	{
		return this.name ;
	}
	
	public String toString(int num)
	{
		return this.name  + String.format("%0" + this.len +"d", num);
	}
}
