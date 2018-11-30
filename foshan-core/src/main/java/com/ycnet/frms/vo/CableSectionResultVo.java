
package com.ycnet.frms.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ycnet.frms.bean.CableSection;

/**
 * @author YE
 *
 */
public class CableSectionResultVo {

	private CableSection caleSection;
	
	private String adevName;
	
	private String zdevName;
	
	private String devCodeA;
	
	private String devCodeZ;
	
	private Long fiberNo;
	
	private Long sectId;
	
	private String secCode;
	
	private String secName;
	
	private String zcode;
	
	private String acode;
	
	private Long fiberNum;
	
	private Long orgId;
	
	private String aaddr;
	
	private String zaddr;
	
	private Long cableSectNum;
	
	private Long cableLen;
	
	private Long adevId;
	
	private Long zdevId;
	
	private String isEndfibercable;//是否为末端光缆 0否 1是
	
	private String faDevState;
	
	private String fzDevState;
	
	private String sectState;
	
	private String adevCode;
	
	private String zdevCode;
	
	//光缆段下光纤为直熔
	private String investment;
		//光缆段下光纤成端
	private String inused;
	//光缆段下光纤未成端
	private String notInused;
		
	private String adevType;//A端设施类型
	
	private String zdevType;//Z端设施类型
	
	private Long pdevIdA;//A端所属设施ID
	
	private Long pdevIdZ;//Z端所属设施ID

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

	
		
	public String getAdevCode() {
		return adevCode;
	}

	public void setAdevCode(String adevCode) {
		this.adevCode = adevCode;
	}

	public String getZdevCode() {
		return zdevCode;
	}

	public void setZdevCode(String zdevCode) {
		this.zdevCode = zdevCode;
	}

	public String getSectState() {
		return sectState;
	}

	public void setSectState(String sectState) {
		this.sectState = sectState;
	}

	public String getFaDevState() {
		return faDevState;
	}

	public void setFaDevState(String faDevState) {
		this.faDevState = faDevState;
	}

	public String getFzDevState() {
		return fzDevState;
	}

	public void setFzDevState(String fzDevState) {
		this.fzDevState = fzDevState;
	}

	public String getIsEndfibercable() {
		return isEndfibercable;
	}

	public void setIsEndfibercable(String isEndfibercable) {
		this.isEndfibercable = isEndfibercable;
	}
	public Long getAdevId() {
		return adevId;
	}

	public void setAdevId(Long adevId) {
		this.adevId = adevId;
	}

	public Long getZdevId() {
		return zdevId;
	}

	public void setZdevId(Long zdevId) {
		this.zdevId = zdevId;
	}

	public Long getCableLen() {
		return cableLen;
	}

	public void setCableLen(Long cableLen) {
		this.cableLen = cableLen;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date completeDate;

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

	public String getAaddr() {
		return aaddr;
	}

	public void setAaddr(String aaddr) {
		this.aaddr = aaddr;
	}

	public String getZaddr() {
		return zaddr;
	}

	public void setZaddr(String zaddr) {
		this.zaddr = zaddr;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getAdevName() {
		return adevName;
	}

	public void setAdevName(String adevName) {
		this.adevName = adevName;
	}

	public String getZdevName() {
		return zdevName;
	}

	public void setZdevName(String zdevName) {
		this.zdevName = zdevName;
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

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
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

	public Long getFiberNum() {
		return fiberNum;
	}

	public void setFiberNum(Long fiberNum) {
		this.fiberNum = fiberNum;
	}

	public CableSection getCaleSection() {
		return caleSection;
	}

	public void setCaleSection(CableSection caleSection) {
		this.caleSection = caleSection;
	}

	public String getAdevType() {
		return adevType;
	}

	public void setAdevType(String adevType) {
		this.adevType = adevType;
	}

	public String getZdevType() {
		return zdevType;
	}

	public void setZdevType(String zdevType) {
		this.zdevType = zdevType;
	}

	public Long getPdevIdA() {
		return pdevIdA;
	}

	public void setPdevIdA(Long pdevIdA) {
		this.pdevIdA = pdevIdA;
	}

	public Long getPdevIdZ() {
		return pdevIdZ;
	}

	public void setPdevIdZ(Long pdevIdZ) {
		this.pdevIdZ = pdevIdZ;
	}

	
}


