package com.ycnet.frms.bean;

import java.util.Date;

public class Lines implements Comparable<Lines>{
    private Long lineId;

    private String srvName;

    private String lineType;

    private String acode;

    private Long adevId;

    private String zcode;

    private Long zdevId;

    private Long sectId;

    private Long fiberNo;
    
    private String unknownPointName;

    private Long orgId;

    private String surveyResult;
    
    private Date createTime;
    
    private Long createUser;
    
    private Date lastModifyTime;
    
    private Long lastModifyUser;
    
    private Integer num;
    
    

    public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getSrvName() {
        return srvName;
    }

    public void setSrvName(String srvName) {
        this.srvName = srvName;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }

    public Long getAdevId() {
        return adevId;
    }

    public void setAdevId(Long adevId) {
        this.adevId = adevId;
    }

    public String getZcode() {
        return zcode;
    }

    public void setZcode(String zcode) {
        this.zcode = zcode;
    }

    public Long getZdevId() {
        return zdevId;
    }

    public void setZdevId(Long zdevId) {
        this.zdevId = zdevId;
    }

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public Long getFiberNo() {
        return fiberNo;
    }

    public void setFiberNo(Long fiberNo) {
        this.fiberNo = fiberNo;
    }

    
    public String getUnknownPointName() {
		return unknownPointName;
	}

	public void setUnknownPointName(String unknownPointName) {
		this.unknownPointName = unknownPointName;
	}

	public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getSurveyResult() {
        return surveyResult;
    }

    public void setSurveyResult(String surveyResult) {
        this.surveyResult = surveyResult;
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

	@Override
	public int compareTo(Lines o) {
		if(o.sectId==null||o.fiberNo==null||sectId==null||fiberNo==null)
			return 0;
		if(sectId.longValue()==o.sectId.longValue())
		{
			return  (int)(fiberNo - o.fiberNo);
		}
		return 0;
	}
    
    
}