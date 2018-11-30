package com.ycnet.frms.vo;

import java.util.List;

public class Group1 {
	private String side;
	
	private Long groupId;
	
	private int portNum;
	private int discNum;
	
	private String remark;
	
	private List<Disc1> discs;
	
	private String groupName;
	
	private String groupDesc;//分组描述
	
	private Long discColNo;//端口序列号
	
	private Long discRowNo;//熔纤盘序号
	
	private String prot01;//端子编码


	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public int getPortNum() {
		return portNum;
	}

	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}

	public List<Disc1> getDiscs() {
		return discs;
	}

	public void setDiscs(List<Disc1> discs) {
		this.discs = discs;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public Long getDiscColNo() {
		return discColNo;
	}

	public void setDiscColNo(Long discColNo) {
		this.discColNo = discColNo;
	}

	public Long getDiscRowNo() {
		return discRowNo;
	}

	public void setDiscRowNo(Long discRowNo) {
		this.discRowNo = discRowNo;
	}

	public String getProt01() {
		return prot01;
	}

	public void setProt01(String prot01) {
		this.prot01 = prot01;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public int getDiscNum() {
		return discNum;
	}

	public void setDiscNum(int discNum) {
		this.discNum = discNum;
	}
	
	
}
