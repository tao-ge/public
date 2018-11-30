package com.ycnet.frms.vo;

/**
 * 
* @ClassName: ConnectTwoCableSectionBean 
* @Description: TODO(用于直熔的两个光缆段传参) 
* @author (lch)  
* @date 2017年12月17日 下午2:19:19 
* @version V1.0
 */
public class ConnectTwoCableSectionBean {
	
	private Long devId;
	
	private String discCode;			//熔纤盘编码
	
	private Long discId;				//盘ID
	
	private String remark;				//盘备注
	
	private Integer portNum;			//直熔盘纤芯数
	
	private Long sectIdA;
	
	private Long fiberNoStartA;			//A光缆段纤芯序号起始
	
	private Long fiberNoEndA;
	
	private Long sectIdZ;
	
	private Long fiberNoStartZ;			//Z光缆段纤芯序号起始
	
	private Long fiberNoEndZ;
	
//	private List<CableSectionTwos> caSectwoList;
	
	

	
//	public List<CableSectionTwos> getCaSectwoList() {
//		return caSectwoList;
//	}
//
//	public void setCaSectwoList(List<CableSectionTwos> caSectwoList) {
//		this.caSectwoList = caSectwoList;
//	}

	public Long getDevId() {
		return devId;
	}

	public Long getSectIdA() {
		return sectIdA;
	}

	public void setSectIdA(Long sectIdA) {
		this.sectIdA = sectIdA;
	}

	public Long getFiberNoStartA() {
		return fiberNoStartA;
	}

	public void setFiberNoStartA(Long fiberNoStartA) {
		this.fiberNoStartA = fiberNoStartA;
	}

	public Long getFiberNoEndA() {
		return fiberNoEndA;
	}

	public void setFiberNoEndA(Long fiberNoEndA) {
		this.fiberNoEndA = fiberNoEndA;
	}

	public Long getSectIdZ() {
		return sectIdZ;
	}

	public void setSectIdZ(Long sectIdZ) {
		this.sectIdZ = sectIdZ;
	}

	public Long getFiberNoStartZ() {
		return fiberNoStartZ;
	}

	public void setFiberNoStartZ(Long fiberNoStartZ) {
		this.fiberNoStartZ = fiberNoStartZ;
	}

	public Long getFiberNoEndZ() {
		return fiberNoEndZ;
	}

	public void setFiberNoEndZ(Long fiberNoEndZ) {
		this.fiberNoEndZ = fiberNoEndZ;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}


	public Long getDiscId() {
		return discId;
	}

	public void setDiscId(Long discId) {
		this.discId = discId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPortNum() {
		return portNum;
	}

	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}
	
	
	
}
