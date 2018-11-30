package com.ycnet.frms.bean;

import java.math.BigDecimal;

/**
 * 
* @ClassName: CableSectionZF 
* @Description: 导出数据库新建类,数据生成用 
* @author DZY 
* @date 2017年10月23日 下午1:24:01 
* @version V1.0
 */
public class CableSectionZF {
	private Long sectId;

    private String sectCode;

    private String secName;

    private String cableModel;

    private BigDecimal cableLen;

    private Long fiberNum;

    private Long devIdA;

    private String devCodeA;

    private Long devIdZ;

    private String devCodeZ;

    private Long cableId;

    private Long orgId;

    private String surveyResult;
    
    private Long createUser;
    
    private Long lastModifyUser;
    
    private String isEndfibercable;

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

	public String getIsEndfibercable() {
		return isEndfibercable;
	}

	public void setIsEndfibercable(String isEndfibercable) {
		this.isEndfibercable = isEndfibercable;
	}
	
}
