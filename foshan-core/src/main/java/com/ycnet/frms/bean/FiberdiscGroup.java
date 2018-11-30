package com.ycnet.frms.bean;

import java.util.Date;
import java.util.List;

public class FiberdiscGroup {
    private Long groupId;

    private Long devId;

    private String side;

    private Integer discNum;

    private Integer portNum;
    
    private String groupName;
    
    private String groupType;
    
    private String groupDesc;
    
    private Date createTime;
    
    private Long createUser;
    
    private Date lastModifyTime;
    
    private Long lastModifyUser;

    private Integer startIndex;
    
    private String createUserName;//创建人名称
    
    private String lastUserName;//最后修改人名称
    
    private String isInvest;//是否为熔纤盘分组
    
    private String discCode;//熔纤盘编码
    
    private List<Discinfo> discinfoList;//直熔盘集合 (is_invest = '1')


	public List<Discinfo> getDiscinfoList() {
		return discinfoList;
	}

	public void setDiscinfoList(List<Discinfo> discinfoList) {
		this.discinfoList = discinfoList;
	}

	public String getDiscCode() {
		return discCode;
	}

	public void setDiscCode(String discCode) {
		this.discCode = discCode;
	}

	private List<?> list;
    
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}


	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getLastUserName() {
		return lastUserName;
	}

	public void setLastUserName(String lastUserName) {
		this.lastUserName = lastUserName;
	}

	public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Integer getDiscNum() {
        return discNum;
    }

    public void setDiscNum(Integer discNum) {
        this.discNum = discNum;
    }

    public Integer getPortNum() {
        return portNum;
    }

    public void setPortNum(Integer portNum) {
        this.portNum = portNum;
    }

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
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

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public String getIsInvest() {
		return isInvest;
	}

	public void setIsInvest(String isInvest) {
		this.isInvest = isInvest;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
	
}