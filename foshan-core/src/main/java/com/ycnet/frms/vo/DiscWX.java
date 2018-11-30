package com.ycnet.frms.vo;

import java.util.List;

public class DiscWX {
	
	private String discCode;
	
	private String remark;
	
	private Integer row;
	
	private Long discId;
	
	private String discName;
	
	private Integer portNum;
	
	private String model;
	
	private List<PortWX> ports;
	
	public Long getDiscId() {
		return discId;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public List<PortWX> getPorts() {
		return ports;
	}

	public void setPorts(List<PortWX> ports) {
		this.ports = ports;
	}

	public String getDiscName() {
		return discName;
	}

	public void setDiscName(String discName) {
		this.discName = discName;
	}

	public Integer getPortNum() {
		return portNum;
	}

	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	
}
