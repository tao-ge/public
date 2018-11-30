package com.ycnet.frms.vo;

public class LocateInfo {

	private Long devId;
	
	private Long sectId;
	
	private String discCode;
	
	private Integer fromFiberNum;

	private Integer endFiberNum;
	
	private Integer fromPortNum;
	
	private String side;
	
	private String direction;
	
	private Long userId;

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public Integer getFromFiberNum() {
		return fromFiberNum;
	}

	public void setFromFiberNum(Integer fromFiberNum) {
		this.fromFiberNum = fromFiberNum;
	}

	public Integer getEndFiberNum() {
		return endFiberNum;
	}

	public void setEndFiberNum(Integer endFiberNum) {
		this.endFiberNum = endFiberNum;
	}

	public Integer getFromPortNum() {
		return fromPortNum;
	}

	public void setFromPortNum(Integer fromPortNum) {
		this.fromPortNum = fromPortNum;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
