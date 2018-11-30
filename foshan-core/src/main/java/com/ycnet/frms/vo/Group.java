package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Lines;

public class Group {

	private Long devId;
	
	private Long devIdG;
	
	private String devType;
	
	private String side;
	
	private Long groupId;
	
	private String groupName;
	
	private String groupDesc;
	
	private List<Disc> discs;
	
	private List<Lines> lines;
	
	private String codMac;
	
	private int discNum;
	
	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}


	
	public Long getDevIdG() {
		return devIdG;
	}

	public void setDevIdG(Long devIdG) {
		this.devIdG = devIdG;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
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

	public List<Disc> getDiscs() {
		return discs;
	}

	public void setDiscs(List<Disc> discs) {
		this.discs = discs;
	}
	
	

	public List<Lines> getLines() {
		return lines;
	}

	public void setLines(List<Lines> lines) {
		this.lines = lines;
	}

	public String getCodMac() {
		return codMac;
	}

	public void setCodMac(String codMac) {
		this.codMac = codMac;
	}

	public int getDiscNum() {
		return discNum;
	}

	public void setDiscNum(int discNum) {
		this.discNum = discNum;
	}

	
}
