package com.ycnet.frms.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author YE
 *
 */
public class Cable {
    private Long cableId;

    private String cableCode;

    private String cableName;

    private String typeA;

    private String devA;

    private String typeZ;

    private String devZ;

    private BigDecimal cableLen;

    private Long fiberNum;

    private Long cableSectNum;
    
    //
    private String acode;
    
    private String zcode;
    
    private String zcodeE;
    
    private String acodeE;
    
    private String areaCode1;
    
    private String astat;//A端占有状态
    
    private String zstat;//Z端占有状态
    
    private String txAcodeSta;//Z端跳纤开始位置
    private String txAcodeEnd;//Z端跳纤结束位置
    private String txZcodeSta;//A端跳纤开始位置
    private String txZcodeEnd;//A端跳纤结束位置
    
    private String cdAcodeSta;//A端成端开始位置
    private String cdAcodeEnd;//A端成端结束位置
    private String cdZcodeSta;//Z端成端开始位置
    private String cdZcodeEnd;//Z端成端结束位置
    
    private Integer aseize;
    private Integer zseize;
    
    private String aCodeDev;
    private String zCodeDev;

    private Long proId;

    private Long orgId;
    
    private Long sectId;
    
    private Long fiberNo;
    
    private String devCodeA;
    
    private String devCodeZ;
    
    private String zdevName;
    
    private String adevName;

    private String secName;
	private String secCode;
    
	private String isTerminat;//是否成端
	
	private String isEndfibercable;//是否为末端光缆 0否 1是
	
	private String devState;//设施状态
	
	private String isTerminat1;//是否成端
	
	private Long orderDevIdA;//A端排序ID
	
	private Long orderDevIdZ;//Z端排序ID
	
	private String sectState;
	
	private String equalityIds;
	
	
	
	public String getEqualityIds() {
		return equalityIds;
	}

	public void setEqualityIds(String equalityIds) {
		this.equalityIds = equalityIds;
	}

	public String getSectState() {
		return sectState;
	}

	public void setSectState(String sectState) {
		this.sectState = sectState;
	}

	public String getIsEndfibercable() {
		return isEndfibercable;
	}

	public void setIsEndfibercable(String isEndfibercable) {
		this.isEndfibercable = isEndfibercable;
	}

	public String getIsTerminat() {
		return isTerminat;
	}

	public void setIsTerminat(String isTerminat) {
		this.isTerminat = isTerminat;
	}

	public String getACodeDev() {
		return aCodeDev;
	}

	public void setACodeDev(String aCodeDev) {
		this.aCodeDev = aCodeDev;
	}

	public String getZCodeDev() {
		return zCodeDev;
	}

	public void setZCodeDev(String zCodeDev) {
		this.zCodeDev = zCodeDev;
	}

	public String getCdAcodeSta() {
		return cdAcodeSta;
	}

	public void setCdAcodeSta(String cdAcodeSta) {
		this.cdAcodeSta = cdAcodeSta;
	}

	public String getCdAcodeEnd() {
		return cdAcodeEnd;
	}

	public void setCdAcodeEnd(String cdAcodeEnd) {
		this.cdAcodeEnd = cdAcodeEnd;
	}

	public String getCdZcodeSta() {
		return cdZcodeSta;
	}

	public void setCdZcodeSta(String cdZcodeSta) {
		this.cdZcodeSta = cdZcodeSta;
	}

	public String getCdZcodeEnd() {
		return cdZcodeEnd;
	}

	public void setCdZcodeEnd(String cdZcodeEnd) {
		this.cdZcodeEnd = cdZcodeEnd;
	}


	

	public Integer getAseize() {
		return aseize;
	}

	public void setAseize(Integer aseize) {
		this.aseize = aseize;
	}

	public Integer getZseize() {
		return zseize;
	}

	public void setZseize(Integer zseize) {
		this.zseize = zseize;
	}

	public String getaCodeDev() {
		return aCodeDev;
	}

	public void setaCodeDev(String aCodeDev) {
		this.aCodeDev = aCodeDev;
	}

	public String getzCodeDev() {
		return zCodeDev;
	}

	public void setzCodeDev(String zCodeDev) {
		this.zCodeDev = zCodeDev;
	}

	public String getTxAcodeSta() {
		return txAcodeSta;
	}

	public void setTxAcodeSta(String txAcodeSta) {
		this.txAcodeSta = txAcodeSta;
	}

	public String getTxAcodeEnd() {
		return txAcodeEnd;
	}

	public void setTxAcodeEnd(String txAcodeEnd) {
		this.txAcodeEnd = txAcodeEnd;
	}

	public String getTxZcodeSta() {
		return txZcodeSta;
	}

	public void setTxZcodeSta(String txZcodeSta) {
		this.txZcodeSta = txZcodeSta;
	}

	public String getTxZcodeEnd() {
		return txZcodeEnd;
	}

	public void setTxZcodeEnd(String txZcodeEnd) {
		this.txZcodeEnd = txZcodeEnd;
	}

	public String getAstat() {
		return astat;
	}

	public void setAstat(String astat) {
		this.astat = astat;
	}

	public String getZstat() {
		return zstat;
	}

	public void setZstat(String zstat) {
		this.zstat = zstat;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public String getZcodeE() {
		return zcodeE;
	}

	public void setZcodeE(String zcodeE) {
		this.zcodeE = zcodeE;
	}

	public String getAcodeE() {
		return acodeE;
	}

	public void setAcodeE(String acodeE) {
		this.acodeE = acodeE;
	}

	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public String getZcode() {
		return zcode;
	}

	public void setZcode(String zcode) {
		this.zcode = zcode;
	}


	
    
    public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

    public String getZdevName() {
		return zdevName;
	}

	public void setZdevName(String zdevName) {
		this.zdevName = zdevName;
	}

	public String getAdevName() {
		return adevName;
	}

	public void setAdevName(String adevName) {
		this.adevName = adevName;
	}


	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date completeDate;

   

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public Long getFiberNo() {
		return fiberNo;
	}

	public void setFiberNo(Long fiberNo) {
		this.fiberNo = fiberNo;
	}

	public String getDevCodeA() {
		return devCodeA;
	}

	public void setDevCodeA(String devCodeA) {
		this.devCodeA = devCodeA;
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

    public String getCableCode() {
        return cableCode;
    }

    public void setCableCode(String cableCode) {
        this.cableCode = cableCode;
    }

    public String getCableName() {
        return cableName;
    }

    public void setCableName(String cableName) {
        this.cableName = cableName;
    }

    public String getTypeA() {
        return typeA;
    }

    public void setTypeA(String typeA) {
        this.typeA = typeA;
    }

    public String getDevA() {
        return devA;
    }

    public void setDevA(String devA) {
        this.devA = devA;
    }

    public String getTypeZ() {
        return typeZ;
    }

    public void setTypeZ(String typeZ) {
        this.typeZ = typeZ;
    }

    public String getDevZ() {
        return devZ;
    }

    public void setDevZ(String devZ) {
        this.devZ = devZ;
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

    public Long getCableSectNum() {
        return cableSectNum;
    }

    public void setCableSectNum(Long cableSectNum) {
        this.cableSectNum = cableSectNum;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
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

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public String getIsTerminat1() {
		return isTerminat1;
	}

	public void setIsTerminat1(String isTerminat1) {
		this.isTerminat1 = isTerminat1;
	}

	public Long getOrderDevIdA() {
		return orderDevIdA;
	}

	public void setOrderDevIdA(Long orderDevIdA) {
		this.orderDevIdA = orderDevIdA;
	}

	public Long getOrderDevIdZ() {
		return orderDevIdZ;
	}

	public void setOrderDevIdZ(Long orderDevIdZ) {
		this.orderDevIdZ = orderDevIdZ;
	}
}