package com.ycnet.frms.vo.mobile;

import java.util.Date;

	public class BindDiscNumMobile {

	private Long codId;

    private Long devId;

    private String discContrId;
    
    private Integer bindDiscNum;

	public Long getCodId() {
		return codId;
	}

	public Long getDevId() {
		return devId;
	}

	public String getDiscContrId() {
		return discContrId;
	}

	public int getBindDiscNum() {
		return bindDiscNum;
	}

	public void setCodId(Long codId) {
		this.codId = codId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setDiscContrId(String discContrId) {
		this.discContrId = discContrId;
	}

	public void setBindDiscNum(int bindDiscNum) {
		this.bindDiscNum = bindDiscNum;
	}
    

}
