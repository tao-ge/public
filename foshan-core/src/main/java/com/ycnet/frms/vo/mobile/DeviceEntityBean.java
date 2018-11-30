package com.ycnet.frms.vo.mobile;

public class DeviceEntityBean {
	
	private Long codId;
	
	private String codName;
	
	private String codCode;
	
	private Long devId;
	
	private String codMac;

	public Long getCodId() {
		return codId;
	}

	public String getCodName() {
		return codName;
	}

	public String getCodCode() {
		return codCode;
	}

	public Long getDevId() {
		return devId;
	}

	public void setCodId(Long codId) {
		this.codId = codId;
	}

	public void setCodName(String codName) {
		this.codName = codName;
	}

	public void setCodCode(String codCode) {
		this.codCode = codCode;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getCodMac() {
		return codMac;
	}

	public void setCodMac(String codMac) {
		this.codMac = codMac;
	}
}
