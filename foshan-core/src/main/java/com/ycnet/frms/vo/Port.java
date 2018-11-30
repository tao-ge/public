package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Lines;

public  class Port
{
	private Integer col;
	
	private String code;
	
	private List<Lines> lines;
	
	private String remark;
	
	private String isCd;
	
	private String port01;
	
	private Long discColno;//端子序列号
	
	
	
	private Long fiberNum;//纤芯序号
	
	private String isOccup;//占用状态
	
	private String occup;//端子状态  0 空闲,1 占用 2 未上报 3 设备显示未占用4 设备显示占用
	
	private Integer fiberDiscId;
	
	public String getIsCd() {
		return isCd;
	}

	public void setIsCd(String isCd) {
		this.isCd = isCd;
	}

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus()
	{
		if(lines==null||lines.size()==0)
			return "0"; //未使用
		else if(lines.size()==1)
			return "1"; //空闲
		else 
			return "2";//占用
	}
	
	

	public void setLines(List<Lines> lines) {
		this.lines = lines;
	}

	public String getRemark() {
		remark = "";
		if(lines==null) return remark;
		for(Lines l:lines)
		{
			String srvName=l.getSrvName();
			remark +=srvName==null?"":srvName;
		}
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPort01() {
		return port01;
	}

	public void setPort01(String port01) {
		this.port01 = port01;
	}

	public Long getDiscColno() {
		return discColno;
	}

	public void setDiscColno(Long discColno) {
		this.discColno = discColno;
	}

	public Long getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Long fiberNum) {
		this.fiberNum = fiberNum;
	}

	public String getIsOccup() {
		return isOccup;
	}

	public void setIsOccup(String isOccup) {
		this.isOccup = isOccup;
	}

	public List<Lines> getLines() {
		return lines;
	}

	public Integer getFiberDiscId() {
		return fiberDiscId;
	}

	public void setFiberDiscId(Integer fiberDiscId) {
		this.fiberDiscId = fiberDiscId;
	}

	public String getOccup() {
		return occup;
	}

	public void setOccup(String occup) {
		this.occup = occup;
	}
	
	
	
}