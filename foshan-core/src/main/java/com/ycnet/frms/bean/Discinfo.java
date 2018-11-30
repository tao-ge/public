package com.ycnet.frms.bean;

import java.util.Date;
import java.util.List;

public class Discinfo {
    private Long discId;

    private Long devId;

    private Long groupId;

    private String side;

    private String discCode;

    private String discName;

    private Integer portNum;

    private String remark;
    
    private Date createTime;
    
    private Long createUser;
    
    private Date lastModifyTime;
    
    private Long lastModifyUser;
    
    private String fiberNoA;//A端纤芯详情
    
    private String fiberNoB;//Z端纤芯详情
    
    private String sevNameA;//A端光缆名称
    
    private String sevNameZ;//Z端光缆名称
    
    public String getSevNameA() {
		return sevNameA;
	}

	public void setSevNameA(String sevNameA) {
		this.sevNameA = sevNameA;
	}

	public String getSevNameZ() {
		return sevNameZ;
	}

	public void setSevNameZ(String sevNameZ) {
		this.sevNameZ = sevNameZ;
	}

	public String getFiberNoA() {
		return fiberNoA;
	}

	public void setFiberNoA(String fiberNoA) {
		this.fiberNoA = fiberNoA;
	}

	public String getFiberNoB() {
		return fiberNoB;
	}

	public void setFiberNoB(String fiberNoB) {
		this.fiberNoB = fiberNoB;
	}

	private List<Fiberdisc> fibList;
    
    

    public Long getDiscId() {
        return discId;
    }

    public void setDiscId(Long discId) {
        this.discId = discId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getDiscCode() {
        return discCode;
    }

    public void setDiscCode(String discCode) {
        this.discCode = discCode;
    }

    public String getDiscName() {
        return discName;
    }

    public void setDiscName(String discName) {
        this.discName = discName;
    }

    public Integer getPortNum() {
        return portNum;
    }

    public void setPortNum(Integer portNum) {
        this.portNum = portNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Long getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(Long lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public List<Fiberdisc> getFibList() {
		return fibList;
	}

	public void setFibList(List<Fiberdisc> fibList) {
		this.fibList = fibList;
	}

}