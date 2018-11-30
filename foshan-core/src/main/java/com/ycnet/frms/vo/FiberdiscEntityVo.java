package com.ycnet.frms.vo;

public class FiberdiscEntityVo {
	private Long fiberDiscId;
	
	private Long fiberNum;
	
	private String port01;
	
	private Long discRowNo;//熔纤盘序号
	
	private Long discColNo;//端口列序号
	
	private String isOccup;//占用情况 0未占用 1已占用
	
	private String isSheath;//是否铠装尾纤 0否 1是 2未核查
	
	private String lightLen;//光路长度(单位：km)
	
	private String lightWane;//光衰(单位：db/km)
	
	private String remark;//成端备注

	private Long sectId;//光缆段ID
	
	public Long getFiberDiscId() {
		return fiberDiscId;
	}

	public void setFiberDiscId(Long fiberDiscId) {
		this.fiberDiscId = fiberDiscId;
	}

	public Long getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Long fiberNum) {
		this.fiberNum = fiberNum;
	}

	public String getPort01() {
		return port01;
	}

	public void setPort01(String port01) {
		this.port01 = port01;
	}

	public Long getDiscRowNo() {
		return discRowNo;
	}

	public void setDiscRowNo(Long discRowNo) {
		this.discRowNo = discRowNo;
	}

	public Long getDiscColNo() {
		return discColNo;
	}

	public void setDiscColNo(Long discColNo) {
		this.discColNo = discColNo;
	}

	public String getIsOccup() {
		return isOccup;
	}

	public void setIsOccup(String isOccup) {
		this.isOccup = isOccup;
	}

	public String getIsSheath() {
		return isSheath;
	}

	public void setIsSheath(String isSheath) {
		this.isSheath = isSheath;
	}

	public String getLightLen() {
		return lightLen;
	}

	public void setLightLen(String lightLen) {
		this.lightLen = lightLen;
	}

	public String getLightWane() {
		return lightWane;
	}

	public void setLightWane(String lightWane) {
		this.lightWane = lightWane;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}
	
	
	
}
