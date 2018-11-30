package com.ycnet.frms.vo;

import java.util.List;

public class Areas1 {

	  private String areaCode1;
	
	  private String areaName1;
	   
	  private List<Areas2> area2List;//区


	public List<Areas2> getArea2List() {
		return area2List;
	}

	public void setArea2List(List<Areas2> area2List) {
		this.area2List = area2List;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public String getAreaName1() {
		return areaName1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public void setAreaName1(String areaName1) {
		this.areaName1 = areaName1;
	}

}
