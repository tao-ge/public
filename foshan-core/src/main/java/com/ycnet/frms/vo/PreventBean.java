package com.ycnet.frms.vo;

public class PreventBean {
	
	private String imei;//设备imei
	
	private String platform;//所属平台
	
	private String platform_from = "01";//所属下游平台标记 固定
	
	private String expired_time;//远程开锁90   其他配置信息90000
	
	private String value;////下行数据

	public String getImei() {
		return imei;
	}

	public String getPlatform() {
		return platform;
	}

	public String getPlatform_from() {
		return platform_from;
	}

	public String getExpired_time() {
		return expired_time;
	}

	public String getValue() {
		return value;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setPlatform_from(String platform_from) {
		this.platform_from = platform_from;
	}

	public void setExpired_time(String expired_time) {
		this.expired_time = expired_time;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
