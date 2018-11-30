package com.ycnet.frms.vo;

import java.util.List;

public class AreasBean {
	
	  private String areaCode;
	
	  private String areaName;
	   
	  private List<Areas1> area1List;//市

	public String getAreaCode() {
		return areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public List<Areas1> getArea1List() {
		return area1List;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setArea1List(List<Areas1> area1List) {
		this.area1List = area1List;
	}
	  
}
