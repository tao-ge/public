package com.ycnet.frms.vo;

import java.util.ArrayList;
import java.util.List;

import com.ycnet.frms.bean.Fiberdisc;

public class FiberdiscInfo {

	//熔纤盘/插板编码
	private String discCode;
	
	//端子数量
	private Integer portNum;
	
	//使用端口情况
	private List<Fiberdisc> used;
	
	//未使用端口情况
	private List<Fiberdisc> unused;
	
	//所有端口
	private List<Fiberdisc> all = new ArrayList<Fiberdisc>();

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	public Integer getPortNum() {
		return portNum;
	}

	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}

	public List<Fiberdisc> getUsed() {
		return used;
	}

	public void setUsed(List<Fiberdisc> used) {
		this.used = used;
	}

	public List<Fiberdisc> getUnused() {
		return unused;
	}

	public void setUnused(List<Fiberdisc> unused) {
		this.unused = unused;
	}

	public List<Fiberdisc> getAll() {
		 all.clear();
		 all.addAll(used);
		 all.addAll(unused);
		 return all;
	}

	
	
	
}

