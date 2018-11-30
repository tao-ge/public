package com.ycnet.frms.bean;

public class Roles {
    private Long roleId;

    private String roleName;

    private String remark;
    
    private String directFlg;
    
    private String[] chosePages;
    
    private Long userId; 
    
    private Long orgId;//组织机构
    
    public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getDirectFlg() {
		return directFlg;
	}

	public void setDirectFlg(String directFlg) {
		this.directFlg = directFlg;
	}

	public String[] getChosePages() {
		return chosePages;
	}

	public void setChosePages(String[] chosePages) {
		this.chosePages = chosePages;
	}
}