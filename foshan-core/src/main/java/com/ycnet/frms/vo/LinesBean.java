package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Lines;

public class LinesBean extends Lines{

	private String changeType;//1,解跳纤	2,更改跳纤
	
	private String isTerminat;
	
    private List<String> picList;//图片路径集合
	
	public String getIsTerminat() {
		return isTerminat;
	}

	public void setIsTerminat(String isTerminat) {
		this.isTerminat = isTerminat;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}


	
	

	
}
