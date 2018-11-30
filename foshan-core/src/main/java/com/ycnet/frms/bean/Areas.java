package com.ycnet.frms.bean;

public class Areas {
    private String areaCode;

    private String areaName;

    private String areaRank;

    private String parentAreaCode;
   

	private String terminalinformation;
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaRank() {
        return areaRank;
    }

    public void setAreaRank(String areaRank) {
        this.areaRank = areaRank;
    }

    public String getParentAreaCode() {
        return parentAreaCode;
    }

    public void setParentAreaCode(String parentAreaCode) {
        this.parentAreaCode = parentAreaCode;
    }
    public String getTerminalinformation() {
		return terminalinformation;
	}

	public void setTerminalinformation(String terminalinformation) {
		this.terminalinformation = terminalinformation;
	}

}