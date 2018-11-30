package com.ycnet.frms.vo;

import java.util.List;

public class Disc {
	
	private String discCode;
	
	private String remark;
	
	private Integer row;
	
	private Long discId;
	
	private String discName;
	
	private Integer portNum;
	
	private String model;
	
	private Long discRowno;//熔纤盘序号
	
	private String discFiberNum;
	
	private List<Port> ports;
	
	private String bind;// 是否绑定 0 否 1 是
	
	private String discContrCode;//端子控制器编码
	
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

	public List<Port> getPorts() {
		return ports;
	}

	public void setPorts(List<Port> ports) {
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

	public Long getDiscRowno() {
		return discRowno;
	}

	public void setDiscRowno(Long discRowno) {
		this.discRowno = discRowno;
	}

	public String getDiscFiberNum() {
		return discFiberNum;
	}

	public void setDiscFiberNum(String discFiberNum) {
		this.discFiberNum = discFiberNum;
	}

	public String getBind() {
		return bind;
	}

	public void setBind(String bind) {
		this.bind = bind;
	}

	public String getDiscContrCode() {
		return discContrCode;
	}

	public void setDiscContrCode(String discContrCode) {
		this.discContrCode = discContrCode;
	}

	
	
	
}
