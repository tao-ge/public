package com.ycnet.frms.vo;

import java.util.List;

import com.ycnet.frms.bean.Route;


/**
 * 光路图信息
 * @author 海阔天空
 *
 */
public class RouteChart extends Route{
	
	private Long devId;
	
	private String code;
	
	private String adevName;
	
	private String zdevName;
	
	private String startCode;//模糊查询光路名称用

	private List<RoutChartLinkData> chartlinks;
	private List<RoutChartNodeData> chartNodes;
	private String routetext;
	public String getRoutetext() {
		return routetext;
	}
	public void setRoutetext(String routetext) {
		this.routetext = routetext;
	}
	public List<RoutChartLinkData> getChartlinks() {
		return chartlinks;
	}
	public void setChartlinks(List<RoutChartLinkData> chartlinks) {
		this.chartlinks = chartlinks;
	}
	public List<RoutChartNodeData> getChartNodes() {
		return chartNodes;
	}
	public void setChartNodes(List<RoutChartNodeData> chartNodes) {
		this.chartNodes = chartNodes;
	}
	public Long getDevId() {
		return devId;
	}
	public void setDevId(Long devId) {
		this.devId = devId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAdevName() {
		return adevName;
	}
	public void setAdevName(String adevName) {
		this.adevName = adevName;
	}
	public String getZdevName() {
		return zdevName;
	}
	public void setZdevName(String zdevName) {
		this.zdevName = zdevName;
	}
	public String getStartCode() {
		return startCode;
	}
	public void setStartCode(String startCode) {
		this.startCode = startCode;
	}
	
	
	
}
