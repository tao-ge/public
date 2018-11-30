package com.ycnet.frms.bean;

import java.util.Date;

public class OcIdentifPort {
    private Long portId;

    private String porttCode;

    private Integer portSeq;

    private Long groupId;

    private Long identId;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;
    
    private Long cableWellId;//光缆点ID
    

    public Long getCableWellId() {
		return cableWellId;
	}

	public void setCableWellId(Long cableWellId) {
		this.cableWellId = cableWellId;
	}

	public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public String getPorttCode() {
        return porttCode;
    }

    public void setPorttCode(String porttCode) {
        this.porttCode = porttCode == null ? null : porttCode.trim();
    }

    public Integer getPortSeq() {
        return portSeq;
    }

    public void setPortSeq(Integer portSeq) {
        this.portSeq = portSeq;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getIdentId() {
        return identId;
    }

    public void setIdentId(Long identId) {
        this.identId = identId;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}