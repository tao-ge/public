package com.ycnet.frms.vo.mobile;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DeviceEntityMobile {
    private Long codId;

    private String codCode;

    private String codName;

    private String codMac;

    private Long devId;
    
    private String devType;
    
    private String devCode;

    private String codState;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time;
    
    private Long user;
    
    private String userName;
    
    private String exist;//中控器存在具体信息

    
   

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public Long getCodId() {
		return codId;
	}

	public String getCodCode() {
		return codCode;
	}

	public String getCodName() {
		return codName;
	}

	public String getCodMac() {
		return codMac;
	}

	public Long getDevId() {
		return devId;
	}

	public String getDevCode() {
		return devCode;
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

	public void setCodName(String codName) {
		this.codName = codName;
	}

	public void setCodMac(String codMac) {
		this.codMac = codMac;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public void setCodState(String codState) {
		this.codState = codState;
	}

	public Date getTime() {
		return time;
	}

	public Long getUser() {
		return user;
	}

	public String getUserName() {
		return userName;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExist() {
		return exist;
	}

	public void setExist(String exist) {
		this.exist = exist;
	}
}