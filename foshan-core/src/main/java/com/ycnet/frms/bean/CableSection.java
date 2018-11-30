package com.ycnet.frms.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ycnet.frms.vo.CableSectionImageUrlBean;

public class CableSection {
	private Long sectId;

	private String sectCode;

	private String secName;

	private String cableModel;

	private BigDecimal cableLen;

	private Long fiberNum;

	private Date completeDate;

	private Long devIdA;

	private String devCodeA;

	private Long devIdZ;

	private String devCodeZ;

	private Long cableId;

	private Long orgId;

	private String surveyResult;

	private Date createTime;

	private Long createUser;

	private Date lastModifyTime;

	private Long lastModifyUser;

	private String typeA;// A端设备类型

	private String typeZ;// Z端设备类型

	// private Long lineId;

	private String aset;// a端 dev_name

	private String zset;// z端 dev_name

	private String zcode;

	private String acode;

	private Facility afacility;

	private Facility zfacility;

	private Long fiberNo;

	private String zgDevAName;

	private String zgDevZName;

	// public Long getLineId() {
	// return lineId;
	// }
	//
	// public void setLineId(Long lineId) {
	// this.lineId = lineId;
	// }
	private String codeA;

	private String sectDec;// 光缆描述

	private String remark; // 光缆备注
	
	private int verifyNum = 0;
	
	private String fiberInfo;
	
	private List<CableSectionImageUrlBean> imgUrlList;
	
	private Long subtubeId;//子管ID
	
	private Long checkfiberCount;//现场核查纤芯数
	
	private Long subtubeRefSectId;//绑定光缆段与子管(或者管孔)表ID
	
	public Long getSubtubeId() {
		return subtubeId;
	}

	public void setSubtubeId(Long subtubeId) {
		this.subtubeId = subtubeId;
	}

	public List<CableSectionImageUrlBean> getImgUrlList() {
		return imgUrlList;
	}

	public void setImgUrlList(List<CableSectionImageUrlBean> imgUrlList) {
		this.imgUrlList = imgUrlList;
	}

	public String getFiberInfo() {
		return fiberInfo;
	}

	public void setFiberInfo(String fiberInfo) {
		this.fiberInfo = fiberInfo;
	}

	public int getVerifyNum() {
		return verifyNum;
	}

	public void setVerifyNum(int verifyNum) {
		this.verifyNum = verifyNum;
	}

	public String getSectDec() {
		return sectDec;
	}

	public void setSectDec(String sectDec) {
		this.sectDec = sectDec;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    
	
	public String getZgDevAName() {
		return zgDevAName;
	}

	public void setZgDevAName(String zgDevAName) {
		this.zgDevAName = zgDevAName;
	}

	public String getZgDevZName() {
		return zgDevZName;
	}

	public void setZgDevZName(String zgDevZName) {
		this.zgDevZName = zgDevZName;
	}

	private String codeZ;

	private String isEndfibercable;// 是否为末端光缆 0否 1是

	private Long zgDevIdA;// 资管导入A端设施

	private Long zgDevIdZ;// 资管导入Z端设施

	private Long zgFiberNum;// 资管纤芯总数

	private String sectState;// 光缆状态 0未核对 1正常 2新增 3修改 4删除

	private String isTerminat;

	private String zgSecName; // 资管导入光缆名称

	public String getZgSecName() {
		return zgSecName;
	}

	public void setZgSecName(String zgSecName) {
		this.zgSecName = zgSecName;
	}

	public String getIsTerminat() {
		return isTerminat;
	}

	public void setIsTerminat(String isTerminat) {
		this.isTerminat = isTerminat;
	}

	public String getIsEndfibercable() {
		return isEndfibercable;
	}

	public void setIsEndfibercable(String isEndfibercable) {
		this.isEndfibercable = isEndfibercable;
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

	public String getTypeA() {
		return typeA;
	}

	public void setTypeA(String typeA) {
		this.typeA = typeA;
	}

	public String getTypeZ() {
		return typeZ;
	}

	public String getZcode() {
		return zcode;
	}

	public void setZcode(String zcode) {
		this.zcode = zcode;
	}

	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public String getAset() {
		return aset;
	}

	public void setAset(String aset) {
		this.aset = aset;
	}

	public String getZset() {
		return zset;
	}

	public void setZset(String zset) {
		this.zset = zset;
	}

	public Facility getAfacility() {
		return afacility;
	}

	public void setAfacility(Facility afacility) {
		this.afacility = afacility;
	}

	public Facility getZfacility() {
		return zfacility;
	}

	public void setZfacility(Facility zfacility) {
		this.zfacility = zfacility;
	}

	public void setTypeZ(String typeZ) {
		this.typeZ = typeZ;
	}

	public Long getFiberNo() {
		return fiberNo;
	}

	public void setFiberNo(Long fiberNo) {
		this.fiberNo = fiberNo;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public String getSectCode() {
		return sectCode;
	}

	public void setSectCode(String sectCode) {
		this.sectCode = sectCode;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public String getCableModel() {
		return cableModel;
	}

	public void setCableModel(String cableModel) {
		this.cableModel = cableModel;
	}

	public BigDecimal getCableLen() {
		return cableLen;
	}

	public void setCableLen(BigDecimal cableLen) {
		this.cableLen = cableLen;
	}

	public Long getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Long fiberNum) {
		this.fiberNum = fiberNum;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public Long getDevIdA() {
		return devIdA;
	}

	public void setDevIdA(Long devIdA) {
		this.devIdA = devIdA;
	}

	public String getDevCodeA() {
		return devCodeA;
	}

	public void setDevCodeA(String devCodeA) {
		this.devCodeA = devCodeA;
	}

	public Long getDevIdZ() {
		return devIdZ;
	}

	public void setDevIdZ(Long devIdZ) {
		this.devIdZ = devIdZ;
	}

	public String getDevCodeZ() {
		return devCodeZ;
	}

	public void setDevCodeZ(String devCodeZ) {
		this.devCodeZ = devCodeZ;
	}

	public Long getCableId() {
		return cableId;
	}

	public void setCableId(Long cableId) {
		this.cableId = cableId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getSurveyResult() {
		return surveyResult;
	}

	public void setSurveyResult(String surveyResult) {
		this.surveyResult = surveyResult;
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

	public Long getZgDevIdA() {
		return zgDevIdA;
	}

	public void setZgDevIdA(Long zgDevIdA) {
		this.zgDevIdA = zgDevIdA;
	}

	public Long getZgDevIdZ() {
		return zgDevIdZ;
	}

	public void setZgDevIdZ(Long zgDevIdZ) {
		this.zgDevIdZ = zgDevIdZ;
	}

	public Long getZgFiberNum() {
		return zgFiberNum;
	}

	public void setZgFiberNum(Long zgFiberNum) {
		this.zgFiberNum = zgFiberNum;
	}

	public String getSectState() {
		return sectState;
	}

	public void setSectState(String sectState) {
		this.sectState = sectState;
	}

	public Long getCheckfiberCount() {
		return checkfiberCount;
	}

	public void setCheckfiberCount(Long checkfiberCount) {
		this.checkfiberCount = checkfiberCount;
	}

	public Long getSubtubeRefSectId() {
		return subtubeRefSectId;
	}

	public void setSubtubeRefSectId(Long subtubeRefSectId) {
		this.subtubeRefSectId = subtubeRefSectId;
	}
	
}