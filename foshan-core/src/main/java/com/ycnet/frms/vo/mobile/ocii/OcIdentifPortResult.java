package com.ycnet.frms.vo.mobile.ocii;

public class OcIdentifPortResult {
	
    private Long portId;

    private String porttCode;

    private Integer portSeq;

    private Long cableWellId;//光缆点ID

    private Integer sectPointSeq;//光缆序号
    
    private String isRecogn;//是否识别完成
    
    private Long sectId;//光缆段ID
    
    private String secName;//光缆序号

    
	public String getIsRecogn() {
		return isRecogn;
	}

	public void setIsRecogn(String isRecogn) {
		this.isRecogn = isRecogn;
	}

	public Long getPortId() {
		return portId;
	}

	public void setPortId(Long portId) {
		this.portId = portId;
	}

	public String getPorttCode() {
		return porttCode;
	}

	public void setPorttCode(String porttCode) {
		this.porttCode = porttCode;
	}

	public Integer getPortSeq() {
		return portSeq;
	}

	public void setPortSeq(Integer portSeq) {
		this.portSeq = portSeq;
	}

	public Long getCableWellId() {
		return cableWellId;
	}

	public void setCableWellId(Long cableWellId) {
		this.cableWellId = cableWellId;
	}

	public Integer getSectPointSeq() {
		return sectPointSeq;
	}

	public void setSectPointSeq(Integer sectPointSeq) {
		this.sectPointSeq = sectPointSeq;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}
    
    
}