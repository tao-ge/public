package com.ycnet.frms.vo;

import java.util.Date;
import java.util.List;

import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.RouteNode;
import com.ycnet.frms.service.impl.UtilsService;

public class LightPath {
    
	private Long routeId;
	
    private String aotherName;

    private String zotherName;
    
    private Long pathId;

    private Long devId;

    private String devType;

    private Long orgId;

    private Long sectId;

    private String areaCode1;

    private String devName;

    private String secName;

    private Long fnum1;

    private Long fnum2;

    private Long id;

    private String port01;

    private String zcodeType;

    private String txPort;

    private String devCode;

    private String secCode;

    private Date createTime;

    private String routeText;
	

	
	public Long getPathId() {
		return pathId;
	}

	public void setPathId(Long pathId) {
		this.pathId = pathId;
	}

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public Long getSectId() {
		return sectId;
	}

	public void setSectId(Long sectId) {
		this.sectId = sectId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getTxPort() {
		return txPort;
	}

	public void setTxPort(String txPort) {
		this.txPort = txPort;
	}


	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

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

	public String getZcodeType() {
		return zcodeType;
	}

	public void setZcodeType(String zcodeType) {
		this.zcodeType = zcodeType;
	}

	public void setRouteText(String routeText) {
		this.routeText = routeText;
	}

	private List<RouteNode> nodes;    

	public List<RouteNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<RouteNode> nodes) {
		this.nodes = nodes;
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

	public String getAotherName() {
		return aotherName;
	}

	public void setAotherName(String aotherName) {
		this.aotherName = aotherName;
	}

	public String getZotherName() {
		return zotherName;
	}

	public void setZotherName(String zotherName) {
		this.zotherName = zotherName;
	}

	public String getRouteText()
    {
    	
    	if(nodes==null||nodes.size()==0)
    		return routeText;
    	StringBuffer routeText1 = new StringBuffer("");
    	if(!(aotherName==null||"".equals(aotherName)))
		{
    		routeText1.append(aotherName).append(StringUtils.getRouteJoin());
		}
    	for(int i = 0;i<nodes.size();i++)
    	{
    		RouteNode node = nodes.get(i);
    		Facility f = UtilsService.getFacility(node.getDevId());
    		if(i==0&&f!=null)
    		{
    			routeText1.append(f.getDevName()).append("(").append(node.getCode()).append(")");
    			if(node.getFiberNo()!=null)
    				routeText1.append("(No:"+(node.getFiberNo()==null?"-":node.getFiberNo())+")");
    		}
    		else if(i>0&&f!=null){
    			routeText1.append(StringUtils.getRouteJoin()).append(f.getDevName()).append("(").append(node.getCode()).append(")");
    			if(node.getFiberNo()!=null)
    				routeText1.append("(No:"+(node.getFiberNo()==null?"-":node.getFiberNo())+")");
    		}
    			
    	}
    	
    	if(!(zotherName==null||"".equals(zotherName)))
		{
    		routeText1.append(StringUtils.getRouteJoin()).append(zotherName);
		}
    	
    	return routeText1.toString();
    }
}