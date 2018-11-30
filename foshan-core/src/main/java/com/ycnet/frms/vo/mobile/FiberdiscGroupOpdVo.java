package com.ycnet.frms.vo.mobile;

import java.util.List;

public class FiberdiscGroupOpdVo {
	
	private int discNum;
	
	private String groupName;
	
	private String groupDesc;
	
	private Long groupId;
	
	private String groupType;
	
	private List<DiscInfoOpd> list;

	public int getDiscNum() {
		return discNum;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public Long getGroupId() {
		return groupId;
	}

	public List<DiscInfoOpd> getList() {
		return list;
	}

	public void setDiscNum(int discNum) {
		this.discNum = discNum;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setList(List<DiscInfoOpd> list) {
		this.list = list;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
}
