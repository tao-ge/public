package com.ycnet.frms.vo.mobile;

public class DeviceDiscinfoEntityMobile {
	private Long discId;

    private Long codId;
    
    private String discCode;

    private Long fiberDiscId;
    
    private String port01;
    
    private String isOccup;//0空闲 1 占用
    
    private String devicOccupy="2";//0  空闲 1 占用 2 未绑定

    private String discContrCode;
    
	public Long getDiscId() {
		return discId;
	}

	public Long getCodId() {
		return codId;
	}

	public String getDiscContrCode() {
		return discContrCode;
	}

	public Long getFiberDiscId() {
		return fiberDiscId;
	}

	public String getPort01() {
		return port01;
	}

	public String getDevicOccupy() {
		return devicOccupy;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}

	public void setCodId(Long codId) {
		this.codId = codId;
	}

	public void setDiscContrCode(String discContrCode) {
		this.discContrCode = discContrCode;
	}

	public void setFiberDiscId(Long fiberDiscId) {
		this.fiberDiscId = fiberDiscId;
	}

	public void setPort01(String port01) {
		this.port01 = port01;
	}

	public void setDevicOccupy(String devicOccupy) {
		this.devicOccupy = devicOccupy;
	}

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public String getIsOccup() {
		return isOccup;
	}

	public void setIsOccup(String isOccup) {
		this.isOccup = isOccup;
	}

}
