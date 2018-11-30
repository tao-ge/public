package com.ycnet.frms.bean;

import java.util.Date;

public class ResourceDataLog {
    private Long resLogId;

    private String resLogType;

    private String handleType;

    private Long handleId;

    private Long modifyUserId;
    
    private String modifyUserName;

    private Date modifyTime;
    
    private String dateStart;
    
    private String dateEnd;
    
    private String hisContent;

    private String nowContent;

    public Long getResLogId() {
        return resLogId;
    }

    public void setResLogId(Long resLogId) {
        this.resLogId = resLogId;
    }

    public String getResLogType() {
        return resLogType;
    }

    public void setResLogType(String resLogType) {
        this.resLogType = resLogType == null ? null : resLogType.trim();
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType == null ? null : handleType.trim();
    }

    public Long getHandleId() {
        return handleId;
    }

    public void setHandleId(Long handleId) {
        this.handleId = handleId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    public String getHisContent() {
        return hisContent;
    }

    public void setHisContent(String hisContent) {
        this.hisContent = hisContent == null ? null : hisContent.trim();
    }

    public String getNowContent() {
        return nowContent;
    }

    public void setNowContent(String nowContent) {
        this.nowContent = nowContent == null ? null : nowContent.trim();
    }

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

}