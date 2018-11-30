package com.ycnet.frms.vo.mobile.ocii;

public class OcPipingResult {
    private Long pipingId;

    private Long pipingSectId;

    private Short diameter;

    private Short subtubeCount;

    private Short usedsubtubeCount;

    private Short unusedsubtubeCount;

    private String isOccupy;

    private String isImport;

    private String pipingState;

    public Long getPipingId() {
        return pipingId;
    }

    public void setPipingId(Long pipingId) {
        this.pipingId = pipingId;
    }

    public Long getPipingSectId() {
        return pipingSectId;
    }

    public void setPipingSectId(Long pipingSectId) {
        this.pipingSectId = pipingSectId;
    }

    public Short getDiameter() {
        return diameter;
    }

    public void setDiameter(Short diameter) {
        this.diameter = diameter;
    }

    public Short getSubtubeCount() {
        return subtubeCount;
    }

    public void setSubtubeCount(Short subtubeCount) {
        this.subtubeCount = subtubeCount;
    }

    public Short getUsedsubtubeCount() {
        return usedsubtubeCount;
    }

    public void setUsedsubtubeCount(Short usedsubtubeCount) {
        this.usedsubtubeCount = usedsubtubeCount;
    }

    public Short getUnusedsubtubeCount() {
        return unusedsubtubeCount;
    }

    public void setUnusedsubtubeCount(Short unusedsubtubeCount) {
        this.unusedsubtubeCount = unusedsubtubeCount;
    }

    public String getIsOccupy() {
        return isOccupy;
    }

    public void setIsOccupy(String isOccupy) {
        this.isOccupy = isOccupy == null ? null : isOccupy.trim();
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport == null ? null : isImport.trim();
    }

    public String getPipingState() {
        return pipingState;
    }

    public void setPipingState(String pipingState) {
        this.pipingState = pipingState == null ? null : pipingState.trim();
    }

}