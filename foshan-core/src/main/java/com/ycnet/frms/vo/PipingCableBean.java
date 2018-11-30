package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Piping;
import com.ycnet.frms.bean.PipingCable;

public class PipingCableBean extends PipingCable{
	private String papingCableName;
	
	private List<PipingBean> pipingList; //管孔集合
	
	public List<PipingBean> getPipingList() {
		return pipingList;
	}

	public void setPipingList(List<PipingBean> pipingList) {
		this.pipingList = pipingList;
	}
	public String getPapingCableName() {
		return papingCableName;
	}

	public void setPapingCableName(String papingCableName) {
		this.papingCableName = papingCableName;
	}
	
}
