package com.ycnet.frms.bean;

import java.util.Date;

public class WorkorderOrganizition {
    private Long orgId;

    private String orgName;

    private String orgAddress;

    private Long orders;

    private String remark;

    private String code1;

    private String code2;

    private String contactPhone;

    private String ip;

    private Long port;

    private String lowTempThd;

    private String highTempShd;

    private String humidityShd;

    private String tilt;

    private String batteryThd;

    private Long colFreq;

    private Long heartRate;

    private String mobileVer;

    private String mobileVerUrl;

    private String devVer;

    private String devVerUrl;
    
    private Date createData;
    
    private String mid;
    
    private String pid;
    
    private String directFlg;
    
    private String prov;
    
    private String city;
    
    private String inforEnterver;//资管录入版本
    
    private String codeName1;
    private Long roleId;

    private String devPlatform;//所属平台
    
	private String roles;//角色
	
	private String pages;//功能权限
	
	private String actRoles;//流程角色
    
    public String getDevPlatform() {
		return devPlatform;
	}

	public void setDevPlatform(String devPlatform) {
		this.devPlatform = devPlatform;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getInforEnterver() {
		return inforEnterver;
	}

	public void setInforEnterver(String inforEnterver) {
		this.inforEnterver = inforEnterver;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public String getLowTempThd() {
        return lowTempThd;
    }

    public void setLowTempThd(String lowTempThd) {
        this.lowTempThd = lowTempThd;
    }

    public String getHighTempShd() {
        return highTempShd;
    }

    public void setHighTempShd(String highTempShd) {
        this.highTempShd = highTempShd;
    }

    public String getHumidityShd() {
        return humidityShd;
    }

    public void setHumidityShd(String humidityShd) {
        this.humidityShd = humidityShd;
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt;
    }

    public String getBatteryThd() {
        return batteryThd;
    }

    public void setBatteryThd(String batteryThd) {
        this.batteryThd = batteryThd;
    }

    public Long getColFreq() {
        return colFreq;
    }

    public void setColFreq(Long colFreq) {
        this.colFreq = colFreq;
    }

    public Long getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Long heartRate) {
        this.heartRate = heartRate;
    }

    public String getMobileVer() {
        return mobileVer;
    }

    public void setMobileVer(String mobileVer) {
        this.mobileVer = mobileVer;
    }

    public String getMobileVerUrl() {
        return mobileVerUrl;
    }

    public void setMobileVerUrl(String mobileVerUrl) {
        this.mobileVerUrl = mobileVerUrl;
    }

    public String getDevVer() {
        return devVer;
    }

    public void setDevVer(String devVer) {
        this.devVer = devVer;
    }

    public String getDevVerUrl() {
        return devVerUrl;
    }

    public void setDevVerUrl(String devVerUrl) {
        this.devVerUrl = devVerUrl;
    }

	public Date getCreateData() {
		return createData;
	}

	public void setCreateData(Date createData) {
		this.createData = createData;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getDirectFlg() {
		return directFlg;
	}

	public void setDirectFlg(String directFlg) {
		this.directFlg = directFlg;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCodeName1() {
		return codeName1;
	}

	public void setCodeName1(String codeName1) {
		this.codeName1 = codeName1;
	}

	public String getActRoles() {
		return actRoles;
	}

	public void setActRoles(String actRoles) {
		this.actRoles = actRoles;
	}
	
	
}