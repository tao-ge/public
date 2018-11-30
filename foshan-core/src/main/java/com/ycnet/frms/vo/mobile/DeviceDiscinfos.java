package com.ycnet.frms.vo.mobile;

public class DeviceDiscinfos {

	 private Long discId;

	private String contrSeq;// 序号

	private String discCode;
	
	private String discContrCode;

	
	

	public String getDiscContrCode() {
		return discContrCode;
	}

	public void setDiscContrCode(String discContrCode) {
		this.discContrCode = discContrCode;
	}

	public String getContrSeq() {
		return contrSeq;
	}

	public String getDiscCode() {
		return discCode;
	}


	public void setContrSeq(String contrSeq) {
		this.contrSeq = contrSeq;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public Long getDiscId() {
		return discId;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}
}
