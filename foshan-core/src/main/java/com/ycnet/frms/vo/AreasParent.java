package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Areas;

public class AreasParent {
	private String parentAreaCode;

    private String parentAreaName;

    private String parentAreaRank;

    private String parentParentAreaCode;
    
    private List<Areas> areas;
    
    private String codeAndName;//汇聚去名子和编码

	public String getParentAreaCode() {
		return parentAreaCode;
	}

	public void setParentAreaCode(String parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}

	public String getParentAreaName() {
		return parentAreaName;
	}

	public void setParentAreaName(String parentAreaName) {
		this.parentAreaName = parentAreaName;
	}

	public String getParentAreaRank() {
		return parentAreaRank;
	}

	public void setParentAreaRank(String parentAreaRank) {
		this.parentAreaRank = parentAreaRank;
	}

	public String getParentParentAreaCode() {
		return parentParentAreaCode;
	}

	public void setParentParentAreaCode(String parentParentAreaCode) {
		this.parentParentAreaCode = parentParentAreaCode;
	}

	public List<Areas> getAreas() {
		return areas;
	}

	public void setAreas(List<Areas> areas) {
		this.areas = areas;
	}

	/**
	 * @return the codeAndName
	 */
	public String getCodeAndName() {
		return codeAndName;
	}

	/**
	 * @param codeAndName the codeAndName to set
	 */
	public void setCodeAndName(String codeAndName) {
		this.codeAndName = codeAndName;
	}

    
    
}
