package com.ycnet.frms.bean;

public class OcPipingSectWell {
    private Long pipingWellId;

    private Long pipingSectId;

    private Long wellId;

    private Long spaceId;
    
    private String isWell;

    private Long orgId;
    
    public String getIsWell() {
		return isWell;
	}

	public void setIsWell(String isWell) {
		this.isWell = isWell;
	}

	public Long getPipingWellId() {
        return pipingWellId;
    }

    public void setPipingWellId(Long pipingWellId) {
        this.pipingWellId = pipingWellId;
    }

    public Long getPipingSectId() {
        return pipingSectId;
    }

    public void setPipingSectId(Long pipingSectId) {
        this.pipingSectId = pipingSectId;
    }

    public Long getWellId() {
        return wellId;
    }

    public void setWellId(Long wellId) {
        this.wellId = wellId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}