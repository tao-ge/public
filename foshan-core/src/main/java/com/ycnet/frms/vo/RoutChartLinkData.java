package com.ycnet.frms.vo;

public class RoutChartLinkData extends LinkData {
	
	
	private String sourceName;//设施起始点名称
	private String targetName;//设施目标点名称
	
	
	public String getSourceName() {
		return sourceName;
	}
	
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	public String getTargetName() {
		return targetName;
	}
	
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	} 
}
