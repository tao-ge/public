package com.ycnet.frms.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Facility {
    private Long devId;

    private String devCode;

    private String devName;

    private String devType;

    private String devModel;

    private String longitude;

    private String latitude;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date completeDate;

    private String devAddr;

    private String devDesc;

    private Integer fiberDiscNum;
    
    private Long pdevId;

    private String devImei;

    private String devMac;

    private String areaCode1;

    private String areaCode2;
    
    private BigDecimal codeBaiduX;
    private BigDecimal codeBaiduY;

    private String site;

    private String room;

    private Long proId;

    private Long orgId;

    private String flag;

    private String surveyResult;
    
    private String imgType;
    
    private String codeA;
    
    private String codeZ;
    
    private BigDecimal baiduX;
    
    private BigDecimal baiduY;
    
    private String isTranslated;
    
    private Date createTime;
    
    private Long createUser;
    
    private Date lastModifyTime;
    
    private Long lastModifyUser;
    
    private String areaName;//汇聚区名称
    
    private String areaCode;
    
    private String checkType; //0.未核查  1.锁定 2.已核查
    
    private Long checkUserId;//核查人ID
    
    private String jiFang;
    
    private String jiGui;
    
    private String side;
    
    private String groupName;
    
    private String devTypeName;
    
    private Integer isShow;//是否在页面坐标展示
    
	//是否为居前井
  	private String isbefwell;
      
  	//设施几通
    private String devKind;
    
    private String zgDevName;//资管导入设施名称
    
    private String devMarking;//设施喷码
    
    private String devState;//设施状态 0 未核对 1 正常 2 新增 3修改 4删除
    
    private String devStateA;
    
    private String devStateZ;
    
    private Long wellId;
    
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

	public String getDevMarking() {
		return devMarking;
	}

	public void setDevMarking(String devMarking) {
		this.devMarking = devMarking;
	}


    
    
	public String getZgDevName() {
		return zgDevName;
	}

	public void setZgDevName(String zgDevName) {
		this.zgDevName = zgDevName;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public BigDecimal getCodeBaiduX() {
		return codeBaiduX;
	}

	public void setCodeBaiduX(BigDecimal codeBaiduX) {
		this.codeBaiduX = codeBaiduX;
	}

	public BigDecimal getCodeBaiduY() {
		return codeBaiduY;
	}

	public void setCodeBaiduY(BigDecimal codeBaiduY) {
		this.codeBaiduY = codeBaiduY;
	}

	public String getDevTypeName() {
		return devTypeName;
	}

	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}

	public String getJiFang() {
		return jiFang;
	}

	public void setJiFang(String jiFang) {
		this.jiFang = jiFang;
	}

	public String getJiGui() {
		return jiGui;
	}

	public void setJiGui(String jiGui) {
		this.jiGui = jiGui;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public Long getPdevId() {
		return pdevId;
	}

	public void setPdevId(Long pdevId) {
		this.pdevId = pdevId;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
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

	public Long getWellId() {
		return wellId;
	}

	public void setWellId(Long wellId) {
		this.wellId = wellId;
	}

}