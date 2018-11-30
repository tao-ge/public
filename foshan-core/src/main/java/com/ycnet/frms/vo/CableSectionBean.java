package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.CableSection;

public class CableSectionBean extends CableSection {
	
	
	
	
	private String devAName;
	
	private String devZName;
	private Long zgDevIdA; 
	private Long zgDevIdZ;
	
	private Long devId;
	
	private String zgDevAName;
	private String zgDevZName;

	private String cableName;
	
	private String surveyResultName;
	
	//是否成端  0 否 1是 2一端为空
	private String isTerminat = "1";
	private String ids;
	//光缆段下光纤为直熔
	private String investment;
	
	private String zInvestment;
	//光缆段下光纤占用
	private String inused;

	//光缆段下光纤未被占用
	private String notInused;

	private String devTypeA;//A段设施类型
	
	private String devTypeZ;//Z段设施类型
	
	private String isJF = "0";//是否一端为机房 否 0 是 1
	
	private String isCD = "0";//是否成端  否 0 是 1
	
	private String aSecName;//直熔A光缆名称
	
	private String zSecName;//直熔Z端光缆名称
	
	private String aSectCode;//直熔A端光缆编码
	
	private String zSectCode;//直熔Z端光缆编码
	
	private Long aSectId;//直熔A端光缆ID
	private Long zSectId;//直熔Z端光缆ID
	
	private List<CableSectionBean> cabinetList;//机房下机柜光缆段集合
	
	
	

	public Long getaSectId() {
		return aSectId;
	}

	public void setaSectId(Long aSectId) {
		this.aSectId = aSectId;
	}

	public Long getzSectId() {
		return zSectId;
	}

	public void setzSectId(Long zSectId) {
		this.zSectId = zSectId;
	}

	public String getzInvestment() {
		return zInvestment;
	}

	public void setzInvestment(String zInvestment) {
		this.zInvestment = zInvestment;
	}

	public String getaSecName() {
		return aSecName;
	}

	public void setaSecName(String aSecName) {
		this.aSecName = aSecName;
	}

	public String getzSecName() {
		return zSecName;
	}

	public void setzSecName(String zSecName) {
		this.zSecName = zSecName;
	}

	public String getaSectCode() {
		return aSectCode;
	}

	public void setaSectCode(String aSectCode) {
		this.aSectCode = aSectCode;
	}

	public String getzSectCode() {
		return zSectCode;
	}

	public void setzSectCode(String zSectCode) {
		this.zSectCode = zSectCode;
	}

	public List<CableSectionBean> getCabinetList() {
		return cabinetList;
	}

	public void setCabinetList(List<CableSectionBean> cabinetList) {
		this.cabinetList = cabinetList;
	}

	public String getIsCD() {
		return isCD;
	}

	public void setIsCD(String isCD) {
		this.isCD = isCD;
	}

	public String getIsJF() {
		return isJF;
	}

	public void setIsJF(String isJF) {
		this.isJF = isJF;
	}

	public String getDevTypeA() {
		return devTypeA;
	}

	public void setDevTypeA(String devTypeA) {
		this.devTypeA = devTypeA;
	}

	public String getDevTypeZ() {
		return devTypeZ;
	}

	public void setDevTypeZ(String devTypeZ) {
		this.devTypeZ = devTypeZ;
	}

	public String getInvestment() {
		return investment;
	}

	public void setInvestment(String investment) {
		this.investment = investment;
	}

	public String getInused() {
		return inused;
	}

	public void setInused(String inused) {
		this.inused = inused;
	}

	public String getNotInused() {
		return notInused;
	}

	public void setNotInused(String notInused) {
		this.notInused = notInused;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	

	public String getCableName() {
		return cableName;
	}

	public void setCableName(String cableName) {
		this.cableName = cableName;
	}

	public String getDevAName() {
		return devAName;
	}

	public void setDevAName(String devAName) {
		this.devAName = devAName;
	}

	public String getDevZName() {
		return devZName;
	}

	public void setDevZName(String devZName) {
		this.devZName = devZName;
	}

	public String getSurveyResultName() {
		return surveyResultName;
	}

	public void setSurveyResultName(String surveyResultName) {
		this.surveyResultName = surveyResultName;
	}

	public String getIsTerminat() {
		return isTerminat;
	}

	public void setIsTerminat(String isTerminat) {
		this.isTerminat = isTerminat;
	}




	
	
}
