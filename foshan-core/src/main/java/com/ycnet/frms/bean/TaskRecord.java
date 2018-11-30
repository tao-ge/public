package com.ycnet.frms.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TaskRecord {
    private Long taskId;
	private Long cableId;
	private Long userId;
	private String username;

	private String valuename;
   

	private String devType;

    private String routeName;

    private String routeScheme;

    private String lebt;

    private String textRouting;
    private String resourcetextrouting;
	private String facilityterminal;
	private String  terminalinformation;//端子信息
	
	private String transmissionsystem;//传输系统

	private String imgUrl;

    private String areaCode1;

    private String flag;

    private Date createTime;
    
    private String lightPathType;
    
    private String remark;
  private String  tasktype;//任务类型
  private String fiberopticcablename;//光纤名称
  private String tasktypecx;//查询的任务类型
  private Long finesome;//纤芯数
  private String contentmodification;//修改内容
  private String remarkglan;//光缆备注
  private String areadevCode;
  private String areaCode;
  private String logcontent;
  private String logtype;
  private String sectid;
  private String routename;
  private String secname;
  private String taskrecordid;
  private String allottee;
  private String assigner;
  private String questlog;
  private Long roleId;
  private String sign;
  private Date allocatedtime;
  private Date finishtime;
  
  private String inforenterver;
  private String inforenterverys;
  private String routeid;
  @DateTimeFormat(pattern = "yyyy-MM-dd") 
  private Date accessTime;
  private Long version;
  public Long getVersion() {
	return version;
}

public void setVersion(Long version) {
	this.version = version;
}

public String getDevIds() {
	return devIds;
}

public void setDevIds(String devIds) {
	this.devIds = devIds;
}

private String devIds;



public Date getAccessTime() {
	return accessTime;
}

public void setAccessTime(Date accessTime) {
	this.accessTime = accessTime;
}

public String getDevCode() {
	return devCode;
}

public void setDevCode(String devCode) {
	this.devCode = devCode;
}

public String getDevName() {
	return devName;
}

public void setDevName(String devName) {
	this.devName = devName;
}

public String getDevTypeName() {
	return devTypeName;
}

public void setDevTypeName(String devTypeName) {
	this.devTypeName = devTypeName;
}

public String getDevModel() {
	return devModel;
}

public void setDevModel(String devModel) {
	this.devModel = devModel;
}

public String getDevId() {
	return devId;
}

public void setDevId(String devId) {
	this.devId = devId;
}

private String devCode;
  private String devName;
  private String devTypeName;
  private String devModel;
  private String devId;
 

public String getUserCode() {
	return userCode;
}

public void setUserCode(String userCode) {
	this.userCode = userCode;
}

public String getUserPwd() {
	return userPwd;
}

public void setUserPwd(String userPwd) {
	this.userPwd = userPwd;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}

public String getMobilePhone() {
	return mobilePhone;
}

public void setMobilePhone(String mobilePhone) {
	this.mobilePhone = mobilePhone;
}

public String getContactPhone() {
	return contactPhone;
}

public void setContactPhone(String contactPhone) {
	this.contactPhone = contactPhone;
}

public String getLastLoginTime() {
	return lastLoginTime;
}

public void setLastLoginTime(String lastLoginTime) {
	this.lastLoginTime = lastLoginTime;
}

public String getMobileImei() {
	return mobileImei;
}

public void setMobileImei(String mobileImei) {
	this.mobileImei = mobileImei;
}

private String userCode;
  private String userPwd;
  private String sex;
  private String mobilePhone;
  private String contactPhone;
  private String lastLoginTime;
  
  private String mobileImei;
  
  public String getOrgId() {
	return orgId;
}

public void setOrgId(String orgId) {
	this.orgId = orgId;
}

private String orgId;
  public String getFinishtimes() {
	return finishtimes;
}

public void setFinishtimes(String finishtimes) {
	this.finishtimes = finishtimes;
}

private String finishtimes;

public String getSign() {
	return sign;
}

public void setSign(String sign) {
	this.sign = sign;
}

public Long getRoleId() {
	return roleId;
}

public void setRoleId(Long roleId) {
	this.roleId = roleId;
}

public Date getAllocatedtime() {
	return allocatedtime;
}

public void setAllocatedtime(Date allocatedtime) {
	this.allocatedtime = allocatedtime;
}

public Date getFinishtime() {
	return finishtime;
}

public void setFinishtime(Date finishtime) {
	this.finishtime = finishtime;
}

  public String getInforenterverys() {
	return inforenterverys;
}

public void setInforenterverys(String inforenterverys) {
	this.inforenterverys = inforenterverys;
}

public String getInforenterver() {
	return inforenterver;
}

public void setInforenterver(String inforenterver) {
	this.inforenterver = inforenterver;
}

private String userallottee;
  public String getUserallottee() {
		return userallottee;
	}

	public void setUserallottee(String userallottee) {
		this.userallottee = userallottee;
	}
  public String getTaskrecordid() {
	return taskrecordid;
}

public void setTaskrecordid(String taskrecordid) {
	this.taskrecordid = taskrecordid;
}

public String getAllottee() {
	return allottee;
}

public void setAllottee(String allottee) {
	this.allottee = allottee;
}

public String getAssigner() {
	return assigner;
}

public void setAssigner(String assigner) {
	this.assigner = assigner;
}

public String getQuestlog() {
	return questlog;
}

public void setQuestlog(String questlog) {
	this.questlog = questlog;
}



public String getSecname() {
	return secname;
}

public void setSecname(String secname) {
	this.secname = secname;
}

public String getRoutename() {
	return routename;
}

public void setRoutename(String routename) {
	this.routename = routename;
}

public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
  public String getSectid() {
	return sectid;
}

public void setSectid(String sectid) {
	this.sectid = sectid;
}

public String getRouteid() {
	return routeid;
}

public void setRouteid(String routeid) {
	this.routeid = routeid;
}
public String getValuename() {
		return valuename;
	}

	public void setValuename(String valuename) {
		this.valuename = valuename;
	}
  public String getAreadevCode() {
	return areadevCode;
}

public void setAreadevCode(String areadevCode) {
	this.areadevCode = areadevCode;
}

public String getAreaCode() {
	return areaCode;
}

public void setAreaCode(String areaCode) {
	this.areaCode = areaCode;
}

public String getLogcontent() {
	return logcontent;
}

public void setLogcontent(String logcontent) {
	this.logcontent = logcontent;
}

public String getLogtype() {
	return logtype;
}

public void setLogtype(String logtype) {
	this.logtype = logtype;
}


  public Long getCableId() {
		return cableId;
	}

	public void setCableId(Long cableId) {
		this.cableId = cableId;
	}

  public String getTasktypecx() {
	return tasktypecx;
}

public void setTasktypecx(String tasktypecx) {
	this.tasktypecx = tasktypecx;
}

                 
  public Long getFinesome() {
	return finesome;
}

public void setFinesome(Long finesome) {
	this.finesome = finesome;
}

  public String getRemarkglan() {
	return remarkglan;
}

public void setRemarkglan(String remarkglan) {
	this.remarkglan = remarkglan;
}

  public String getFiberopticcablename() {
	return fiberopticcablename;
}

public void setFiberopticcablename(String fiberopticcablename) {
	this.fiberopticcablename = fiberopticcablename;
}



public String getContentmodification() {
	return contentmodification;
}

public void setContentmodification(String contentmodification) {
	this.contentmodification = contentmodification;
}

    public String getTasktype() {
	return tasktype;
}

public void setTasktype(String tasktype) {
	this.tasktype = tasktype;
}

	public String getTerminalinformation() {
		return terminalinformation;
	}

	public void setTerminalinformation(String terminalinformation) {
		this.terminalinformation = terminalinformation;
	}
    public String getTransmissionsystem() {
		return transmissionsystem;
	}

	public void setTransmissionsystem(String transmissionsystem) {
		this.transmissionsystem = transmissionsystem;
	}
	public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDevType() {
        return devType;
    }
    
    public void setDevType(String devType) {
        this.devType = devType == null ? null : devType.trim();
    }
    public String getFacilityterminal() {
		return facilityterminal;
	}

	public void setFacilityterminal(String facilityterminal) {
		this.facilityterminal = facilityterminal;
	}
	public String getResourcetextrouting() {
		return resourcetextrouting;
	}

	public void setResourcetextrouting(String resourcetextrouting) {
		this.resourcetextrouting = resourcetextrouting;
	}
    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public String getRouteScheme() {
        return routeScheme;
    }

    public void setRouteScheme(String routeScheme) {
        this.routeScheme = routeScheme == null ? null : routeScheme.trim();
    }

    public String getLebt() {
        return lebt;
    }

    public void setLebt(String lebt) {
        this.lebt = lebt == null ? null : lebt.trim();
    }

    public String getTextRouting() {
        return textRouting;
    }

    public void setTextRouting(String textRouting) {
        this.textRouting = textRouting == null ? null : textRouting.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getAreaCode1() {
        return areaCode1;
    }

    public void setAreaCode1(String areaCode1) {
        this.areaCode1 = areaCode1 == null ? null : areaCode1.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    
	public String getLightPathType() {
		return lightPathType;
	}

	public void setLightPathType(String lightPathType) {
		this.lightPathType = lightPathType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}