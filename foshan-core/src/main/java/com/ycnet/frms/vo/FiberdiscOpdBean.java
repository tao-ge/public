package com.ycnet.frms.vo;

import java.util.List;

public class FiberdiscOpdBean {
	
	private String adevName;//本端
	
	private String port01;
	
	private String code;//对端端子编码
	
	private String routeText;
	
	private String srvName;
	
	private List<String> imgsUrls;
	
	private String zdevName;//对端设施名称

	public String getAdevName() {
		return adevName;
	}

	public String getPort01() {
		return port01;
	}

	public String getCode() {
		return code;
	}

	public String getRouteText() {
		return routeText;
	}

	public String getSrvName() {
		return srvName;
	}

	public List<String> getImgsUrls() {
		return imgsUrls;
	}

	public String getZdevName() {
		return zdevName;
	}

	public void setAdevName(String adevName) {
		this.adevName = adevName;
	}

	public void setPort01(String port01) {
		this.port01 = port01;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setRouteText(String routeText) {
		this.routeText = routeText;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public void setImgsUrls(List<String> imgsUrls) {
		this.imgsUrls = imgsUrls;
	}

	public void setZdevName(String zdevName) {
		this.zdevName = zdevName;
	}

}
