package com.ycnet.frms.bean;

import java.math.BigDecimal;
import java.util.Date;

public class FacilityEntity {
    private Long devId;

    private String devCode;

    private String devName;

    private String devType;

    private String devModel;

    private String longitude;

    private String latitude;

    private Date completeDate;

    private String devAddr;

    private String devDesc;

    private Integer fiberDiscNum;

    private Long pdevId;

    private String devImei;

    private String devMac;

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

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private String devKind;

    private String isbefwell;

    private String checkType;

    private Long checkUserId;

    private String inforEnterVer;

    private String zgDevName;

    private String devState;

    private String devMarking;
    
    private String codeA;
    
    private String codeZ;
    
    private String devStateA;
    
    private String devStateZ;
    
    private String imgType;

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
        this.devCode = devCode == null ? null : devCode.trim();
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName == null ? null : devName.trim();
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType == null ? null : devType.trim();
    }

    public String getDevModel() {
        return devModel;
    }

    public void setDevModel(String devModel) {
        this.devModel = devModel == null ? null : devModel.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getDevAddr() {
        return devAddr;
    }

    public void setDevAddr(String devAddr) {
        this.devAddr = devAddr == null ? null : devAddr.trim();
    }

    public String getDevDesc() {
        return devDesc;
    }

    public void setDevDesc(String devDesc) {
        this.devDesc = devDesc == null ? null : devDesc.trim();
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

    public String getDevImei() {
        return devImei;
    }

    public void setDevImei(String devImei) {
        this.devImei = devImei == null ? null : devImei.trim();
    }

    public String getDevMac() {
        return devMac;
    }

    public void setDevMac(String devMac) {
        this.devMac = devMac == null ? null : devMac.trim();
    }

    public String getAreaCode1() {
        return areaCode1;
    }

    public void setAreaCode1(String areaCode1) {
        this.areaCode1 = areaCode1 == null ? null : areaCode1.trim();
    }

    public String getAreaCode2() {
        return areaCode2;
    }

    public void setAreaCode2(String areaCode2) {
        this.areaCode2 = areaCode2 == null ? null : areaCode2.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
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
        this.flag = flag == null ? null : flag.trim();
    }

    public String getSurveyResult() {
        return surveyResult;
    }

    public void setSurveyResult(String surveyResult) {
        this.surveyResult = surveyResult == null ? null : surveyResult.trim();
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
        this.isTranslated = isTranslated == null ? null : isTranslated.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(Long lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public String getDevKind() {
        return devKind;
    }

    public void setDevKind(String devKind) {
        this.devKind = devKind == null ? null : devKind.trim();
    }

    public String getIsbefwell() {
        return isbefwell;
    }

    public void setIsbefwell(String isbefwell) {
        this.isbefwell = isbefwell == null ? null : isbefwell.trim();
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType == null ? null : checkType.trim();
    }

    public Long getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(Long checkUserId) {
        this.checkUserId = checkUserId;
    }

    public String getInforEnterVer() {
        return inforEnterVer;
    }

    public void setInforEnterVer(String inforEnterVer) {
        this.inforEnterVer = inforEnterVer == null ? null : inforEnterVer.trim();
    }

    public String getZgDevName() {
        return zgDevName;
    }

    public void setZgDevName(String zgDevName) {
        this.zgDevName = zgDevName == null ? null : zgDevName.trim();
    }

    public String getDevState() {
        return devState;
    }

    public void setDevState(String devState) {
        this.devState = devState == null ? null : devState.trim();
    }

    public String getDevMarking() {
        return devMarking;
    }

    public void setDevMarking(String devMarking) {
        this.devMarking = devMarking == null ? null : devMarking.trim();
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

	public String getDevStateA() {
		return devStateA;
	}

	public void setDevStateA(String devStateA) {
		this.devStateA = devStateA;
	}

	public String getDevStateZ() {
		return devStateZ;
	}

	public void setDevStateZ(String devStateZ) {
		this.devStateZ = devStateZ;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
}