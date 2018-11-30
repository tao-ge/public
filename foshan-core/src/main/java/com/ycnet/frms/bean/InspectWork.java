package com.ycnet.frms.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class InspectWork {
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
    
    private String userNameWork;//巡检处理人名称
    
    private String pushType;//推送用，区分推送类型	alarm 报警推送	inspect 巡检任务推送
    
    private String orgId;//组织机构ID，推送用
    
	private String pushTo;//区分推送到哪个平台  40 正式服务器   128 测试服务器
    
    private String userCode;//用户代码
    
    public String getUserNameWork() {
		return userNameWork;
	}

	public void setUserNameWork(String userNameWork) {
		this.userNameWork = userNameWork;
	}

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

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPushTo() {
		return pushTo;
	}

	public void setPushTo(String pushTo) {
		this.pushTo = pushTo;
	}

}