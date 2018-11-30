package com.ycnet.frms.vo;

import com.ycnet.frms.bean.Route;

public class RouteBean extends Route{
	private Long route_id;
	
	private Long devId;
	
	private String code;
	
	private String adevName;
	
	private String zdevName;
	
	private String startCode;//模糊查询光路名称用
	
	private String devName;//设施名称
	
	private Long orgId;
	private Long routeId;
	private String secName;
	
	private Long fnum1;
	
	private String port01;
	
	private Long fnum2;
	
	private String routeText;
	
	private String zcode;
	
	private String zcodeType;
	
	private String devCode;
	
	private String protCode1;
	
	private String protCode2;
	private Long isentering;
	private Integer id;
	private String startTime;
	private String endTime;
	private long roleId;
	//文本路由字段 佟盛玮 2017.10.17
	private String routetext;
	
	private String devCode1;
	
	private String devName1;
	
	private Long xuHao;
	
	private Long shuLiang;
	
	private String lightType;
	
	private Double cableLen;
	
	private String waneState;
	
	private String lightWane;
	

	private String lightLen;
	
	
	
	public String getLightLen() {
		return lightLen;
	}
	public void setLightLen(String lightLen) {
		this.lightLen = lightLen;
	}
	public Double getCableLen() {
		return cableLen;
	}
	public void setCableLen(Double cableLen) {
		this.cableLen = cableLen;
	}
	public String getWaneState() {
		return waneState;
	}
	public void setWaneState(String waneState) {
		this.waneState = waneState;
	}
	public String getLightWane() {
		return lightWane;
	}
	public void setLightWane(String lightWane) {
		this.lightWane = lightWane;
	}
	public String getLightType() {
		return lightType;
	}
	public void setLightType(String lightType) {
		this.lightType = lightType;
	}
	public String getDevCode1() {
		return devCode1;
	}
	public void setDevCode1(String devCode1) {
		this.devCode1 = devCode1;
	}
	public String getDevName1() {
		return devName1;
	}
	public void setDevName1(String devName1) {
		this.devName1 = devName1;
	}
	public Long getXuHao() {
		return xuHao;
	}
	public void setXuHao(Long xuHao) {
		this.xuHao = xuHao;
	}
	public Long getShuLiang() {
		return shuLiang;
	}
	public void setShuLiang(Long shuLiang) {
		this.shuLiang = shuLiang;
	}
	public String getRoutetext() {
		return routetext;
	}
	public void setRoutetext(String routetext) {
		this.routetext = routetext;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getIsentering() {
		return isentering;
	}
	public void setIsentering(Long isentering) {
		this.isentering = isentering;
	}
	public Long getRoute_id() {
		return route_id;
	}
	public void setRoute_id(Long route_id) {
		this.route_id = route_id;
	}
	
	private Long sectId;
	
	private String secCode;
	
	private String areaCode1;
	
	private String areadevCode1;
	                
	public Long getRouteId() {
		return routeId;
	}
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}
	public String getAreadevCode1() {
		return areadevCode1;
	}
	public void setAreadevCode1(String areadevCode1) {
		this.areadevCode1 = areadevCode1;
	}
	public String getAreaCode1() {
		return areaCode1;
	}
	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}
	public String getSecCode() {
		return secCode;
	}
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
	public String getProtCode1() {
		return protCode1;
	}
	public void setProtCode1(String protCode1) {
		this.protCode1 = protCode1;
	}
	public String getProtCode2() {
		return protCode2;
	}
	public void setProtCode2(String protCode2) {
		this.protCode2 = protCode2;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDevCode() {
		return devCode;
	}
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
	private String devType;
	
	public String getSecName() {
		return secName;
	}
	public void setSecName(String secName) {
		this.secName = secName;
	}
	public Long getFnum1() {
		return fnum1;
	}
	public void setFnum1(Long fnum1) {
		this.fnum1 = fnum1;
	}
	public String getPort01() {
		return port01;
	}
	public void setPort01(String port01) {
		this.port01 = port01;
	}
	public Long getFnum2() {
		return fnum2;
	}
	public void setFnum2(Long fnum2) {
		this.fnum2 = fnum2;
	}
	public String getRouteText() {
		return routeText;
	}
	public void setRouteText(String routeText) {
		this.routeText = routeText;
	}
	public String getZcode() {
		return zcode;
	}
	public void setZcode(String zcode) {
		this.zcode = zcode;
	}
	public String getZcodeType() {
		return zcodeType;
	}
	public void setZcodeType(String zcodeType) {
		this.zcodeType = zcodeType;
	}
	public String getStartCode() {
		return startCode;
	}
	public void setStartCode(String startCode) {
		this.startCode = startCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getDevId() {
		return devId;
	}
	public void setDevId(Long devId) {
		this.devId = devId;
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
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Long getSectId() {
		return sectId;
	}
	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}


	
}