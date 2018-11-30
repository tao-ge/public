package com.ycnet.frms.bean;

import java.util.Date;
import java.util.List;

public class Well {
    private Long wellId;

    private Long devId;

    private String wellName;

    private String wellNumber;

    private String longitude;

    private String latitude;

    private String baiduX;

    private String baiduY;

    private String isFormerbureau;

    private String wellKind;

    private String isBranch;

    private String prorightType;
    
    private String areaCode;//所属片区编码

	private String wellState;

	private String isImport;

    private Date createTime;

    private Long createUser;

    private Date lastModifyTime;

    private Long lastModifyUser;

    private Long orgId;
    
    private String wellType;
    
    private String zgWellName;//资管导入井名称
    
    private String zgWellNumber;//资管导入井喷码
    
    private String remark; //备注信息
    
    private Integer isFormerbureauState;
    
    
    
    public Integer getIsFormerbureauState() {
		return isFormerbureauState;
	}

	public void setIsFormerbureauState(Integer isFormerbureauState) {
		this.isFormerbureauState = isFormerbureauState;
	}

	public String getAreaCode() {
  		return areaCode;
  	}

  	public void setAreaCode(String areaCode) {
  		this.areaCode = areaCode;
  	}
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getZgWellName() {
		return zgWellName;
	}

	public void setZgWellName(String zgWellName) {
		this.zgWellName = zgWellName;
	}

	public String getZgWellNumber() {
		return zgWellNumber;
	}

	public void setZgWellNumber(String zgWellNumber) {
		this.zgWellNumber = zgWellNumber;
	}

	public Long getWellId() {
        return wellId;
    }

    public void setWellId(Long wellId) {
        this.wellId = wellId;
    }

    public String getWellState() {
		return wellState;
	}

	public void setWellState(String wellState) {
		this.wellState = wellState;
	}
	
    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public String getWellName() {
        return wellName;
    }

    public void setWellName(String wellName) {
        this.wellName = wellName == null ? null : wellName.trim();
    }

    public String getWellNumber() {
        return wellNumber;
    }

    public void setWellNumber(String wellNumber) {
        this.wellNumber = wellNumber == null ? null : wellNumber.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getBaiduX() {
        return baiduX;
    }

    public void setBaiduX(String baiduX) {
        this.baiduX = baiduX == null ? null : baiduX.trim();
    }

    public String getBaiduY() {
        return baiduY;
    }

    public void setBaiduY(String baiduY) {
        this.baiduY = baiduY == null ? null : baiduY.trim();
    }

    public String getIsFormerbureau() {
        return isFormerbureau;
    }

    public void setIsFormerbureau(String isFormerbureau) {
        this.isFormerbureau = isFormerbureau == null ? null : isFormerbureau.trim();
    }

    public String getWellKind() {
        return wellKind;
    }

    public void setWellKind(String wellKind) {
        this.wellKind = wellKind == null ? null : wellKind.trim();
    }

    public String getIsBranch() {
        return isBranch;
    }

    public void setIsBranch(String isBranch) {
        this.isBranch = isBranch == null ? null : isBranch.trim();
    }

    public String getProrightType() {
        return prorightType;
    }

    public void setProrightType(String prorightType) {
        this.prorightType = prorightType == null ? null : prorightType.trim();
    }

    public String getWellType() {
        return wellType;
    }

    public void setWellType(String wellType) {
        this.wellType = wellType == null ? null : wellType.trim();
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport == null ? null : isImport.trim();
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