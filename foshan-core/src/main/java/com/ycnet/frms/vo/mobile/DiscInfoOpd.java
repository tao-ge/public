package com.ycnet.frms.vo.mobile;

import java.util.Date;

public class DiscInfoOpd {

	private Long discId;

	private Long devId;

	private Long groupId;

	private String side;

	private String discCode;

	private String discName;

	private Integer portNum;

	private String remark;

	private Date createTime;

	private Long createUser;

	private Date lastModifyTime;

	private Long lastModifyUser;

	public Long getDiscId() {
		return discId;
	}

	public Long getDevId() {
		return devId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public String getSide() {
		return side;
	}

	public String getDiscCode() {
		return discCode;
	}

	public String getDiscName() {
		return discName;
	}

	public Integer getPortNum() {
		return portNum;
	}

	public String getRemark() {
		return remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public Long getLastModifyUser() {
		return lastModifyUser;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public void setDiscName(String discName) {
		this.discName = discName;
	}

	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public void setLastModifyUser(Long lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
}
