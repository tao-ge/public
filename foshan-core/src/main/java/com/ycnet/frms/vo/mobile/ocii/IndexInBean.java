package com.ycnet.frms.vo.mobile.ocii;

import java.util.List;
import java.util.Set;

/**
 * 首页查询接收类
* @ClassName: IndexBean 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author YHT(作者)  
* @date 2018年10月16日 下午1:11:56 
* @version V1.0
 */
public class IndexInBean{
	
	private Long orgId;

    private Integer distance;

    private String type;

    private String devName;

    private String devState;
    
    private Double curLng;

    private Double curLat;

    private Integer maxNum;
    
	List<IndexPoint> pointList;
	
	Set<IndexLine> lineSet;

	
	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public Double getCurLng() {
		return curLng;
	}

	public void setCurLng(Double curLng) {
		this.curLng = curLng;
	}

	public Double getCurLat() {
		return curLat;
	}

	public void setCurLat(Double curLat) {
		this.curLat = curLat;
	}

	public List<IndexPoint> getPointList() {
		return pointList;
	}

	public void setPointList(List<IndexPoint> pointList) {
		this.pointList = pointList;
	}

	public Set<IndexLine> getLineSet() {
		return lineSet;
	}

	public void setLineSet(Set<IndexLine> lineSet) {
		this.lineSet = lineSet;
	}

    
    
   
}
