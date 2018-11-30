package com.ycnet.frms.bean;

public class WorkorderRoles {
    private Long roleId;

    private String roleName;

    private String remark;
    
    private Long orgId;
    
    private String actRoles;
    
    public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getActRoles() {
		return actRoles;
	}

	public void setActRoles(String actRoles) {
		this.actRoles = actRoles;
	}
}