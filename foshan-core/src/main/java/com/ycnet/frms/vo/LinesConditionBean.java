package com.ycnet.frms.vo;

import com.ycnet.core.util.PageBean;

public class LinesConditionBean {

	private String lineType;
	
	private Long sectId;
	
	private Long devId;
	
	private Long adevId;
	
	private String acode;
	
	private Long zdevId;
	
	private String zcode;
	
	private Integer fiberNo;
	
	private String srvName;
	
	private PageBean pb;
	
	public LinesConditionBean()
	{
		
	}
	public LinesConditionBean(String lineType,Long sectId,Long devId)
	{
		this.lineType = lineType;
		this.sectId = sectId;
		this.devId = devId;
	}

	public LinesConditionBean(String lineType,Long sectId,Long devId,String srvName)
	{
		this(lineType,sectId,devId);
		this.srvName = srvName;
	}
	
	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}
	
	public Integer getFiberNo() {
		return fiberNo;
	}
	public void setFiberNo(Integer fiberNo) {
		this.fiberNo = fiberNo;
	}
	public String getSrvName() {
		return srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public PageBean getPb() {
		return pb;
	}

	public void setPb(PageBean pb) {
		this.pb = pb;
	}
	public Long getAdevId() {
		return adevId;
	}
	public void setAdevId(Long adevId) {
		this.adevId = adevId;
	}
	public String getAcode() {
		return acode;
	}
	public void setAcode(String acode) {
		this.acode = acode;
	}
	public Long getZdevId() {
		return zdevId;
	}
	public void setZdevId(Long zdevId) {
		this.zdevId = zdevId;
	}
	public String getZcode() {
		return zcode;
	}
	public void setZcode(String zcode) {
		this.zcode = zcode;
	}
	
	
}
