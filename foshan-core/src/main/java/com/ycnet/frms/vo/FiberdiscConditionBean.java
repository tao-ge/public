package com.ycnet.frms.vo;

import com.ycnet.core.util.PageBean;

public class FiberdiscConditionBean {

	private Long devId;
	
	private String side;
	
	private String discCode;
	
	private Long discRowNo;
	
	private Long sectId;
	
	private Integer fiberNum;
	
	private String port01;
	
	private PageBean pb;

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}
	
	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public Long getDiscRowNo() {
		return discRowNo;
	}

	public void setDiscRowNo(Long discRowNo) {
		this.discRowNo = discRowNo;
	}

	public PageBean getPb() {
		return pb;
	}

	public void setPb(PageBean pb) {
		this.pb = pb;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public Integer getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Integer fiberNum) {
		this.fiberNum = fiberNum;
	}

	public String getPort01() {
		return port01;
	}

	public void setPort01(String port01) {
		this.port01 = port01;
	}
	
}
