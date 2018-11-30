package com.ycnet.frms.bean;

import java.util.Date;
import java.util.List;

import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.service.impl.UtilsService;

public class Route {
    private Long routeId;
    private Long is_entering;
   
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

    private Date createTime;
    
    private String routeText;
    
    private Long orgId;
    
    private Long devId;
    
    private String areaCode1;
    private String area_code1; 
	private String devName;
	
	private String secName;
	
	private Long fnum1;
	
	private String port01;
	
	private Long fnum2;
	
	private String zcodeType;
    
	private String txPort;
	
	private String devCode;
	
	private String devType;
	
	private String portCode1;
	
	private String portCode2;
	
	private Integer id;
	
	private String secCode;
	private String route_name;
	private Long route_id;
	 
	private Long isentering;
	 public String getRoutetext() {
		return routetext;
	}

	public void setRoutetext(String routetext) {
		this.routetext = routetext;
	}

	private String routetext;
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

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	public String getArea_code1() {
			return area_code1;
		}

		public void setArea_code1(String area_code1) {
			this.area_code1 = area_code1;
		}
	public Long getIs_entering() {
		return is_entering;
	}

	public void setIs_entering(Long is_entering) {
		this.is_entering = is_entering;
	}


	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getPortCode1() {
		return portCode1;
	}

	public void setPortCode1(String portCode1) {
		this.portCode1 = portCode1;
	}

	public String getPortCode2() {
		return portCode2;
	}

	public void setPortCode2(String portCode2) {
		this.portCode2 = portCode2;
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

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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