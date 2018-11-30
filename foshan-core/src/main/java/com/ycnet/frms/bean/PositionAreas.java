package com.ycnet.frms.bean;

public class PositionAreas {
    private String areaCode;

    private String areaName;

    private String areaRank;

    private String parentAreaCode;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getAreaRank() {
        return areaRank;
    }

    public void setAreaRank(String areaRank) {
        this.areaRank = areaRank == null ? null : areaRank.trim();
    }

    public String getParentAreaCode() {
        return parentAreaCode;
    }

    public void setParentAreaCode(String parentAreaCode) {
        this.parentAreaCode = parentAreaCode == null ? null : parentAreaCode.trim();
    }
}