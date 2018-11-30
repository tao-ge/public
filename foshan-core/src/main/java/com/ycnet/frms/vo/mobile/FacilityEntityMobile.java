package com.ycnet.frms.vo.mobile;

import java.math.BigDecimal;
import java.util.Date;

public class FacilityEntityMobile {
    private Long devId;

    private String devCode;

    private String devName;
    
    private Integer fiberDiscNum;

	public Long getDevId() {
		return devId;
	}

	public String getDevCode() {
		return devCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Integer getFiberDiscNum() {
		return fiberDiscNum;
	}

	public void setFiberDiscNum(Integer fiberDiscNum) {
		this.fiberDiscNum = fiberDiscNum;
	}

   
}