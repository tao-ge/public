package com.ycnet.frms.vo;
/**
 * 光缆路由查询条件
 * @author 海阔天空
 *
 */
public class CableRouteCondition {
	
	
	private String devId;//设施id
	private String devCode;//设施代码
	private Long orgId;//组织机构id
	private String areaCode;//区域代码
	private String surveyResult;//普查状态
	private String notFacility;//是否去除无设施
	
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getDevCode() {
		return devCode;
	}
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}


	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getSurveyResult() {
		return surveyResult;
	}
	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}
	public String getNotFacility() {
		return notFacility;
	}
	public void setNotFacility(String notFacility) {
		this.notFacility = notFacility;
	}
	

}
