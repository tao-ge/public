package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.FiberdiscGroup;

public class FiberdiscGroupBean {

	private FiberdiscGroup fiberdiscGroup;
	
	List<Discinfo> discinfos;

	public FiberdiscGroup getFiberdiscGroup() {
		return fiberdiscGroup;
	}

	public void setFiberdiscGroup(FiberdiscGroup fiberdiscGroup) {
		this.fiberdiscGroup = fiberdiscGroup;
	}

	public List<Discinfo> getDiscinfos() {
		return discinfos;
	}

	public void setDiscinfos(List<Discinfo> discinfos) {
		this.discinfos = discinfos;
	}

	
}
