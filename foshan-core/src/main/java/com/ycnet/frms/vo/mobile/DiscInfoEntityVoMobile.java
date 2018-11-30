package com.ycnet.frms.vo.mobile;

import java.util.List;


public class DiscInfoEntityVoMobile {
	
	private Integer discState0;//端子控制器状态 为0（已绑定）的个数  
	
	private Integer fiberDiscNum;//熔纤盘(法兰盘)数量
	
	private String devName;//设施名称
	
	private String devCode;//设施名称
	
	private Long devId;//设施ID
	
	public Integer getDiscState0() {
		return discState0;
	}

	public void setDiscState0(Integer discState0) {
		this.discState0 = discState0;
	}

	public Integer getFiberDiscNum() {
		return fiberDiscNum;
	}

	public void setFiberDiscNum(Integer fiberDiscNum) {
		this.fiberDiscNum = fiberDiscNum;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

}
