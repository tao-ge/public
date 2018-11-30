package com.ycnet.frms.vo;

public class FacilityConditionBean {

	 private String devCode;

     private String devName;
     
     private String devList;
     
     private String surveyResult;
     
     private Long pdevId;
     
     private Integer pageno;        
     
     private String areaCode1;
	private String areaCode;
     private String site;
     
     private String devType;
     
     private String devModel;
     
     private String room;
     
     private String devImei;

     private String devMac;
     
     private String pdevName;
     
     private int isNullPoint;
     
     private Long orgId;

     private Double curLng;

     private Double curLat;
     
     private Double distance;
     
     private String codeA;
     
     private String codeZ;
     
     private String checkType; //0.未核查  1.锁定 2.已核查  
     
     private Integer isDel; //查询设施状态 0未确认  1已确认  2已删除
     
     private String devState;//设施状态 
     
     private Integer devStateall;
     
     private String existLngLat;//有无经纬度
     
     private String xLog;//用来标记，无特定用途
     
     private String[] devTypes;//类型数组集合
     
	public String getxLog() {
		return xLog;
	}

	public void setxLog(String xLog) {
		this.xLog = xLog;
	}

	public Integer getDevStateall() {
		return devStateall;
	}

	public void setDevStateall(Integer devStateall) {
		this.devStateall = devStateall;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public String getAreaCode() {
 		return areaCode;
 	}

 	public void setAreaCode(String areaCode) {
 		this.areaCode = areaCode;
 	}
	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getCodeA() {
		return codeA;
	}

	public void setCodeA(String codeA) {
		this.codeA = codeA;
	}

	public String getCodeZ() {
		return codeZ;
	}

	public void setCodeZ(String codeZ) {
		this.codeZ = codeZ;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getIsNullPoint() {
		return isNullPoint;
	}

	public void setIsNullPoint(int isNullPoint) {
		this.isNullPoint = isNullPoint;
	}

	public String getPdevName() {
		return pdevName;
	}

	public void setPdevName(String pdevName) {
		this.pdevName = pdevName;
	}

	public String getDevList() {
		return devList;
	}

	public void setDevList(String devList) {
		this.devList = devList;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public Integer getPageno() {
		return pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

	public String getSurveyResult() {
		return surveyResult;
	}

	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}

	public Long getPdevId() {
		return pdevId;
	}

	public void setPdevId(Long pdevId) {
		this.pdevId = pdevId;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDevModel() {
		return devModel;
	}

	public void setDevModel(String devModel) {
		this.devModel = devModel;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDevImei() {
		return devImei;
	}

	public void setDevImei(String devImei) {
		this.devImei = devImei;
	}

	public String getDevMac() {
		return devMac;
	}

	public void setDevMac(String devMac) {
		this.devMac = devMac;
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getExistLngLat() {
		return existLngLat;
	}

	public void setExistLngLat(String existLngLat) {
		this.existLngLat = existLngLat;
	}

	public String[] getDevTypes() {
		return devTypes;
	}

	public void setDevTypes(String[] devTypes) {
		this.devTypes = devTypes;
	}



}
