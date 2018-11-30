package com.ycnet.frms.bean;

import java.util.Date;

public class FiberdiscGroupEntity {
    private Long groupId;

    private Long devId;

    private String side;

    private Integer discNum;

    private Integer portNum;

    private String groupName;

    private String groupDesc;

    private String ptnNeIdA;

    private String ptnNeIdZ;

    private String isInvest;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;
    
    private Integer startIndex;

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
        this.side = side == null ? null : side.trim();
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
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc == null ? null : groupDesc.trim();
    }

    public String getPtnNeIdA() {
        return ptnNeIdA;
    }

    public void setPtnNeIdA(String ptnNeIdA) {
        this.ptnNeIdA = ptnNeIdA == null ? null : ptnNeIdA.trim();
    }

    public String getPtnNeIdZ() {
        return ptnNeIdZ;
    }

    public void setPtnNeIdZ(String ptnNeIdZ) {
        this.ptnNeIdZ = ptnNeIdZ == null ? null : ptnNeIdZ.trim();
    }

    public String getIsInvest() {
        return isInvest;
    }

    public void setIsInvest(String isInvest) {
        this.isInvest = isInvest == null ? null : isInvest.trim();
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
}