package com.ycnet.frms.vo.sectRoute;

public class SectRouteFiber {

	private String fiberPort;
	
	private String routeText;
	
	private String port01;
	
	private Integer isflag;//是否有光路

	
	
	public Integer getIsflag() {
		return isflag;
	}

	public void setIsflag(Integer isflag) {
		this.isflag = isflag;
	}

	public String getFiberPort() {
		return fiberPort; 
	}

	public void setFiberPort(String fiberPort) {
		this.fiberPort = fiberPort;
	}

	public String getRouteText() {
		return routeText;
	}

	public void setRouteText(String routeText) {
		this.routeText = routeText;
	}

	public String getPort01() {
		return port01;
	}

	public void setPort01(String port01) {
		this.port01 = port01;
	}
	
	
}
