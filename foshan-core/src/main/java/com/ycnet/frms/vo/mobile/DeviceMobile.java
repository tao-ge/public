package com.ycnet.frms.vo.mobile;

import java.util.Date;
import java.util.List;

import org.apache.poi.poifs.property.Parent;
import org.junit.runners.Parameterized.Parameter;

public class DeviceMobile {
	private Long codId;
	
	private String codCode;
	
	private String codName;
	
	private String codMac;
	
	private String codImei;
	
	private Date createTime;
	
	//private Integer createUser;
	
	private String codState;
	
	private String userName;
	
	//private List<DiscinfoGroupMobile> discinfoGroupList;

	public Long getCodId() {
		return codId;
	}

	public String getCodCode() {
		return codCode;
	}

	public String getCodMac() {
		return codMac;
	}

	public String getCodImei() {
		return codImei;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCodState() {
		return codState;
	}

	public void setCodId(Long codId) {
		this.codId = codId;
	}

	public void setCodCode(String codCode) {
		this.codCode = codCode;
	}


	public void setCodMac(String codMac) {
		this.codMac = codMac;
	}

	public void setCodImei(String codImei) {
		this.codImei = codImei;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCodState(String codState) {
		this.codState = codState;
	}

	public String getCodName() {
		return codName;
	}

	public void setCodName(String codName) {
		this.codName = codName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
