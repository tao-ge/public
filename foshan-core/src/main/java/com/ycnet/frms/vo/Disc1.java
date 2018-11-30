package com.ycnet.frms.vo;

import java.util.List;

public class Disc1 {
	private Long discId;
	
	private Long fiberDiscId;
	
	private int discRowNo;
	
	private int portNum;
	
	private String discCode;
	
	private String discName;
	
	private List<Port1> ports;


	
	public Long getFiberDiscId() {
		return fiberDiscId;
	}

	public void setFiberDiscId(Long fiberDiscId) {
		this.fiberDiscId = fiberDiscId;
	}

	
	public int getDiscRowNo() {
		return discRowNo;
	}

	public void setDiscRowNo(int discRowNo) {
		this.discRowNo = discRowNo;
	}

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public List<Port1> getPorts() {
		return ports;
	}

	public void setPorts(List<Port1> ports) {
		this.ports = ports;
	}

	public Long getDiscId() {
		return discId;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}

	public int getPortNum() {
		return portNum;
	}

	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}

	public String getDiscName() {
		return discName;
	}

	public void setDiscName(String discName) {
		this.discName = discName;
	}
	
	
	
}
