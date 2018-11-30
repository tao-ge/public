package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.Piping;

public class PipingBean extends Piping{
	//管口下的光缆段列表
	private List<PipingCableBean> pipingCableList;
	
	private String wellName;//井名称
	
	private String wellNumber;//井喷码
	
	private String wellType;//井类型
	
	private List<SubtubeBean> subtudeList;//大孔下的子孔
	
	private List<CableSection> sectionList;//光缆段集合
	
	

	public String getWellNumber() {
		return wellNumber;
	}

	public void setWellNumber(String wellNumber) {
		this.wellNumber = wellNumber;
	}

	public String getWellType() {
		return wellType;
	}

	public void setWellType(String wellType) {
		this.wellType = wellType;
	}

	public String getWellName() {
		return wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	public List<PipingCableBean> getPipingCableList() {
		return pipingCableList;
	}
	
	public void setPipingCableList(List<PipingCableBean> pipingCableList) {
		this.pipingCableList = pipingCableList;
	}

	public List<SubtubeBean> getSubtudeList() {
		return subtudeList;
	}

	public void setSubtudeList(List<SubtubeBean> subtudeList) {
		this.subtudeList = subtudeList;
	}

	public List<CableSection> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<CableSection> sectionList) {
		this.sectionList = sectionList;
	}
		
	
		
}
