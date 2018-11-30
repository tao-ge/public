package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.FiberdiscGroup;

public class FiberdiscGroupVo extends FiberdiscGroup{
	
	private Long sectId;
	
	private Long fiberNum;
	
	private Long fiberDiscId;
	
	private Long discRowNo;
	
	private String discCode;
	
	private String discName;
	
	private Long discId;
	
	private List<?> list;
	
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	

	public Long getDiscRowNo() {
		return discRowNo;
	}

	public void setDiscRowNo(Long discRowNo) {
		this.discRowNo = discRowNo;
	}

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public Long getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Long fiberNum) {
		this.fiberNum = fiberNum;
	}

	public Long getFiberDiscId() {
		return fiberDiscId;
	}

	public void setFiberDiscId(Long fiberDiscId) {
		this.fiberDiscId = fiberDiscId;
	}

	private Disc[] disc;

	public Disc[] getDisc() {
		return disc;
	}

	public void setDisc(Disc[] disc) {
		this.disc = disc;
	}
	public String getDiscName() {
		return discName;
	}

	public void setDiscName(String discName) {
		this.discName = discName;
	}

	public Long getDiscId() {
		return discId;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}

	
	
}
