package com.ycnet.frms.vo.mobile;

public class DeviceDiscinfoMobile {

	private Long discId;
	
	private String lastReportData;
	
	private Long portNum;
	
	private String bind;//是否绑定 0 否 1 是
	
	private String discContrCode;//端子控制器编码
	
	private String remark;
	
	private String discName;
	
	private	String discCode;

	public Long getDiscId() {
		return discId;
	}

	public String getLastReportData() {
		return lastReportData;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}

	public void setLastReportData(String lastReportData) {
		this.lastReportData = lastReportData;
	}

	public Long getPortNum() {
		return portNum;
	}

	public String getBind() {
		return bind;
	}

	public void setPortNum(Long portNum) {
		this.portNum = portNum;
	}

	public void setBind(String bind) {
		this.bind = bind;
	}

	public String getDiscContrCode() {
		return discContrCode;
	}

	public void setDiscContrCode(String discContrCode) {
		this.discContrCode = discContrCode;
	}

	public String getRemark() {
		return remark;
	}

	public String getDiscName() {
		return discName;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setDiscName(String discName) {
		this.discName = discName;
	}

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}
}
