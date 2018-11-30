package com.ycnet.frms.vo;


import java.util.List;

import com.ycnet.frms.bean.FacilityImg;

public class FacilityImgConditionBean extends FacilityImg{
    

	private String startTime;

    private String endTime;

	private String imgTypes1;
    
    private String imgTypes2;
    
    private String imgTypes3;
    
    private List<String> imgTypesList;
    
    private Long inspectId;
    
    
    public List<String> getImgTypesList() {
		return imgTypesList;
	}

	public void setImgTypesList(List<String> imgTypesList) {
		this.imgTypesList = imgTypesList;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
	 public String getImgTypes1() {
		return imgTypes1;
	}

	public void setImgTypes1(String imgTypes1) {
		this.imgTypes1 = imgTypes1;
	}

	public String getImgTypes2() {
		return imgTypes2;
	}

	public void setImgTypes2(String imgTypes2) {
		this.imgTypes2 = imgTypes2;
	}

	public String getImgTypes3() {
		return imgTypes3;
	}

	public void setImgTypes3(String imgTypes3) {
		this.imgTypes3 = imgTypes3;
	}

	public Long getInspectId() {
		return inspectId;
	}

	public void setInspectId(Long inspectId) {
		this.inspectId = inspectId;
	}
    
}