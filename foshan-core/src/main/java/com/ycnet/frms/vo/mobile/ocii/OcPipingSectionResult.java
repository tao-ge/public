package com.ycnet.frms.vo.mobile.ocii;

import java.util.List;


public class OcPipingSectionResult {
	
	private Long pipingSectId;

	private String pipingName;

	private String pipingSectType;

	private String phyLen;
	
	private String isImport;
	
	private Long awellId;//A端井ID
	
	private String awellName;//A端井设施名称
	
	private String typeA;//A端类型
	
	private Long zwellId;//Z端井ID
	
	private String zwellName;//Z端井设施名称
	
	private String typeZ;//Z端类型
	
	
	private Long aspaceId;//A端面ID
	
	private Long zspaceId;//Z端面ID
	
	private List<OcPipingResult> pipingBeanList;//管孔集合
	
	private List<OcSpaceResult> aspaceList;//A端面
	
	private List<OcSpaceResult> zspaceList;//Z端面

	
	
	public String getIsImport() {
		return isImport;
	}

	public void setIsImport(String isImport) {
		this.isImport = isImport;
	}

	public Long getPipingSectId() {
		return pipingSectId;
	}

	public void setPipingSectId(Long pipingSectId) {
		this.pipingSectId = pipingSectId;
	}

	public String getPipingName() {
		return pipingName;
	}

	public void setPipingName(String pipingName) {
		this.pipingName = pipingName;
	}

	public String getPipingSectType() {
		return pipingSectType;
	}

	public void setPipingSectType(String pipingSectType) {
		this.pipingSectType = pipingSectType;
	}

	public String getPhyLen() {
		return phyLen;
	}

	public void setPhyLen(String phyLen) {
		this.phyLen = phyLen;
	}

	public Long getAwellId() {
		return awellId;
	}

	public void setAwellId(Long awellId) {
		this.awellId = awellId;
	}

	public String getAwellName() {
		return awellName;
	}

	public void setAwellName(String awellName) {
		this.awellName = awellName;
	}

	public String getTypeA() {
		return typeA;
	}

	public void setTypeA(String typeA) {
		this.typeA = typeA;
	}

	public Long getZwellId() {
		return zwellId;
	}

	public void setZwellId(Long zwellId) {
		this.zwellId = zwellId;
	}

	public String getZwellName() {
		return zwellName;
	}

	public void setZwellName(String zwellName) {
		this.zwellName = zwellName;
	}

	public String getTypeZ() {
		return typeZ;
	}

	public void setTypeZ(String typeZ) {
		this.typeZ = typeZ;
	}

	public Long getAspaceId() {
		return aspaceId;
	}

	public void setAspaceId(Long aspaceId) {
		this.aspaceId = aspaceId;
	}

	public Long getZspaceId() {
		return zspaceId;
	}

	public void setZspaceId(Long zspaceId) {
		this.zspaceId = zspaceId;
	}

	
	public List<OcPipingResult> getPipingBeanList() {
		return pipingBeanList;
	}

	public void setPipingBeanList(List<OcPipingResult> pipingBeanList) {
		this.pipingBeanList = pipingBeanList;
	}

	public List<OcSpaceResult> getAspaceList() {
		return aspaceList;
	}

	public void setAspaceList(List<OcSpaceResult> aspaceList) {
		this.aspaceList = aspaceList;
	}

	public List<OcSpaceResult> getZspaceList() {
		return zspaceList;
	}

	public void setZspaceList(List<OcSpaceResult> zspaceList) {
		this.zspaceList = zspaceList;
	}

	
	
}
