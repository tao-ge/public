package com.ycnet.frms.vo.mobile;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class DeviceEntityDtoBean{
	private Long codId;

	private Long devId;
	
	private String devType;
	
    private String codCode;

    private String codName;

    private String codMac;

    private Date lastModifyTime;

    private String devNameRoom;
    
    private String devAddr;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date currentTime;

    
    
    
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

	public Date getLastModifyTime() {
		return lastModifyTime;
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

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}


	public String getDevNameRoom() {
		return devNameRoom;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevNameRoom(String devNameRoom) {
		this.devNameRoom = devNameRoom;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

   
	
}
