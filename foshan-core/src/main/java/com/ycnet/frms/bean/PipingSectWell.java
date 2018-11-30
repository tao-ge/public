package com.ycnet.frms.bean;

public class PipingSectWell {
    private Long pipingWellId;

    private Long pipingSectId;

    private Long wellId;

    private Long spaceId;

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
}