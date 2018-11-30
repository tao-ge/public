package com.ycnet.frms.vo;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.CableSection;

public class CableSectionConditionBean {
	
	private Long devId;
	
	private Long sectId;
	
	private String typeA;//A端设备类型
	
	private String typeZ;//Z端设备类型
	
	private String sectState;
	
	private Long orgId;
	
	private String secName;
	
	private String sectCode;
	
	
	
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getSectState() {
		return sectState;
	}

	public void setSectState(String sectState) {
		this.sectState = sectState;
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

	public void setTypeZ(String typeZ) {
		this.typeZ = typeZ;
	}

	public CableSection getCableSection() {
		return cableSection;
	}


	private CableSection cableSection;
	
	public CableSection getCablesection() {
		return cableSection;
	}

	public void setCableSection(CableSection cableSection) {
		this.cableSection = cableSection;
	}


	private int pageNo;
	
	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}


	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	private PageBean pageBean;

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getSecName() {
		return secName;
	}

	public String getSectCode() {
		return sectCode;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public void setSectCode(String sectCode) {
		this.sectCode = sectCode;
	}
	
	

}
