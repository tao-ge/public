package com.ycnet.frms.vo.sectRoute;

import java.util.List;

public class SectRouteResult {

	private Integer allFiberNo;
	
	private String devName;
	
	private List<SectRouteGroup> srgList;
	
	private List<RouteText> routeList;
	
	
	public List<RouteText> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<RouteText> routeList) {
		this.routeList = routeList;
	}


	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Integer getAllFiberNo() {
		return allFiberNo;
	}

	public void setAllFiberNo(Integer allFiberNo) {
		this.allFiberNo = allFiberNo;
	}

	public List<SectRouteGroup> getSrgList() {
		return srgList;
	}

	public void setSrgList(List<SectRouteGroup> srgList) {
		this.srgList = srgList;
	}


	
}
