package com.ycnet.frms.vo.mobile.ocii;

public class OcIdentifInput {
    private Long identId;

    private String type;//设施或井类型 1 井 0 设施

    private Integer sectNum;//光缆数量

    private Long wellDevId;//设施或井ID

    private Integer startSeq;//开始光缆序号

	public Long getIdentId() {
		return identId;
	}

	public void setIdentId(Long identId) {
		this.identId = identId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSectNum() {
		return sectNum;
	}

	public void setSectNum(Integer sectNum) {
		this.sectNum = sectNum;
	}

	public Long getWellDevId() {
		return wellDevId;
	}

	public void setWellDevId(Long wellDevId) {
		this.wellDevId = wellDevId;
	}

	public Integer getStartSeq() {
		return startSeq;
	}

	public void setStartSeq(Integer startSeq) {
		this.startSeq = startSeq;
	}
    

}