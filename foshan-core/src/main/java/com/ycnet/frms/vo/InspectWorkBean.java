package com.ycnet.frms.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ycnet.frms.bean.InspectWork;

public class InspectWorkBean extends  InspectWork{
    private Long workId;

    private Long workerId;

    private String workType;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date endTime;

    private Long userId;

    private String remark;

    private String devIds;
    
    private String userName;
    
    private String workName;

    
    
    
    public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDevIds() {
        return devIds;
    }

    public void setDevIds(String devIds) {
        this.devIds = devIds == null ? null : devIds.trim();
    }
}