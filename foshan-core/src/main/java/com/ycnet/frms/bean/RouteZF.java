package com.ycnet.frms.bean;

/**
 * 
* @ClassName: RouteZF 
* @Description: 导出数据库新建类,数据生成用
* @author DZY
* @date 2017年10月23日 下午2:39:21 
* @version V1.0
 */
public class RouteZF {
	private Long routeId;
    
	private String routeName;

    private String acode;

    private Long adevId;
    
    private String aotherName;

    private String zcode;

    private Long zdevId;

    private String zotherName;
    
    private String devIds;

    private String codes;
    
    private String fibers;

    private Long orgId;
    
    private String areaCode1;
    
	private Long isentering;

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public Long getAdevId() {
		return adevId;
	}

	public void setAdevId(Long adevId) {
		this.adevId = adevId;
	}

	public String getAotherName() {
		return aotherName;
	}

	public void setAotherName(String aotherName) {
		this.aotherName = aotherName;
	}

	public String getZcode() {
		return zcode;
	}

	public void setZcode(String zcode) {
		this.zcode = zcode;
	}

	public Long getZdevId() {
		return zdevId;
	}

	public void setZdevId(Long zdevId) {
		this.zdevId = zdevId;
	}

	public String getZotherName() {
		return zotherName;
	}

	public void setZotherName(String zotherName) {
		this.zotherName = zotherName;
	}

	public String getDevIds() {
		return devIds;
	}

	public void setDevIds(String devIds) {
		this.devIds = devIds;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public String getFibers() {
		return fibers;
	}

	public void setFibers(String fibers) {
		this.fibers = fibers;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public Long getIsentering() {
		return isentering;
	}

	public void setIsentering(Long isentering) {
		this.isentering = isentering;
	}
}
