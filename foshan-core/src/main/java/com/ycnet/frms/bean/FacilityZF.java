package com.ycnet.frms.bean;

import java.math.BigDecimal;

/**
 * 
* @ClassName: FacilityZF 
* @Description: 导出数据库新建类,数据生成用
* @author DZY  
* @date 2017年10月23日 上午9:02:05 
* @version V1.0
 */
public class FacilityZF {
	//46
    private Long devId;

    private String devCode;

    private String devName;

    private String devType;

    private String devModel;

    private String longitude;

    private String latitude;
    
    private String devAddr;

    private String devDesc;

    private Integer fiberDiscNum;
    
    private Long pdevId;

    private String areaCode;
    
    private String areaCode1;

    private String areaCode2;
    
    private String site;

    private String room;

    private Long proId;

    private Long orgId;

    private String flag;

    private String surveyResult;
    
    private BigDecimal baiduX;
    
    private BigDecimal baiduY;
    
    private String isTranslated;
    
    private Long createUser;
    
    private Long lastModifyUser;
    
    private String checkType; //0.未核查  1.锁定 2.已核查
    
    private Long checkUserId;//核查人ID
	//是否为居前井
  	private String isbefwell;
      
  	//设施几通
    private String devKind;

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
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

	public String getDevModel() {
		return devModel;
	}

	public void setDevModel(String devModel) {
		this.devModel = devModel;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public String getDevDesc() {
		return devDesc;
	}

	public void setDevDesc(String devDesc) {
		this.devDesc = devDesc;
	}

	public Integer getFiberDiscNum() {
		return fiberDiscNum;
	}

	public void setFiberDiscNum(Integer fiberDiscNum) {
		this.fiberDiscNum = fiberDiscNum;
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

	public String getAreaCode2() {
		return areaCode2;
	}

	public void setAreaCode2(String areaCode2) {
		this.areaCode2 = areaCode2;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSurveyResult() {
		return surveyResult;
	}

	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
	}

	public BigDecimal getBaiduX() {
		return baiduX;
	}

	public void setBaiduX(BigDecimal baiduX) {
		this.baiduX = baiduX;
	}

	public BigDecimal getBaiduY() {
		return baiduY;
	}

	public void setBaiduY(BigDecimal baiduY) {
		this.baiduY = baiduY;
	}

	public String getIsTranslated() {
		return isTranslated;
	}

	public void setIsTranslated(String isTranslated) {
		this.isTranslated = isTranslated;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Long getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(Long lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public Long getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getIsbefwell() {
		return isbefwell;
	}

	public void setIsbefwell(String isbefwell) {
		this.isbefwell = isbefwell;
	}

	public String getDevKind() {
		return devKind;
	}

	public void setDevKind(String devKind) {
		this.devKind = devKind;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}
