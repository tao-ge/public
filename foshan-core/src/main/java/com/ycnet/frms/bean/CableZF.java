package com.ycnet.frms.bean;

import java.math.BigDecimal;

/**
 * 
* @ClassName: CableZF 
* @Description: 导出数据库新建类,数据生成用 
* @author DZY  
* @date 2017年10月23日 下午2:29:17 
* @version V1.0
 */
public class CableZF {
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
    
    private Long proId;

    private Long orgId;

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
    
}
