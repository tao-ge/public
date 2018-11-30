package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.LineImage;
import com.ycnet.frms.bean.PortImg;

public class JumperTerraceInfo {
	private String devName;//所属设施名称
	
	private String code;//本端端口编码
	
	private String fiberNum;//纤芯序号
	
	private String fiberNo;//纤芯数量
	
	private String secName;//成端所属光缆段名称
	
	private List<LineImage> lineImgUrl;//端子图片路径
	
	private String srvName;//业务描述(传输系统)
	
	private String routeText;//文本路由
	
	private String fiberPlace;//跳纤另一端位置

	private String isOccup;
	
	private String routeName;
	
	
	

	public String getRouteName() {
		return routeName==null ?"":routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getIsOccup() {
		return "0".equals(isOccup)?"未占用":"已占用";
	}

	public void setIsOccup(String isOccup) {
		this.isOccup = isOccup;
	}

	public String getFiberNo() {
		return fiberNo==null?"":fiberNo;
	}

	public void setFiberNo(String fiberNo) {
		this.fiberNo = fiberNo;
	}

	public String getSecName() {
		return secName==null?"":secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public String getDevName() {
		return devName==null?"":devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getCode() {
		return code==null?"":code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFiberNum() {
		return fiberNum==null?"":fiberNum;
	}

	public void setFiberNum(String fiberNum) {
		this.fiberNum = fiberNum;
	}

	
	
	

	public List<LineImage> getLineImgUrl() {
		return lineImgUrl;
	}

	public void setLineImgUrl(List<LineImage> lineImgUrl) {
		this.lineImgUrl = lineImgUrl;
	}

	public String getSrvName() {
		return srvName==null?"":srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public String getRouteText() {
		return routeText==null?"":routeText;
	}

	public void setRouteText(String routeText) {
		this.routeText = routeText;
	}

	public String getFiberPlace() {
		return fiberPlace==null?"":fiberPlace;
	}

	public void setFiberPlace(String fiberPlace) {
		this.fiberPlace = fiberPlace;
	}
	
	
}
