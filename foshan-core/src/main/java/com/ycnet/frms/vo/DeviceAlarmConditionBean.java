package com.ycnet.frms.vo;



public class DeviceAlarmConditionBean {
	

    private String devCode;//设施编码

    private String devName;//设施名称
    
    private String alarmTypes;//报警类型
    
    
    private String alarmTimeStart;//报警采集时间开始时间
    private String alarmTimeEnd;//报警采集时间结束时间
    
    private String dealSign;//报警处理标志

    private Integer pageno;
    
    private String fName;//所属设施名称
    
	public Integer getPageno() {
		return pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getAlarmTypes() {
		return alarmTypes;
	}

	public void setAlarmTypes(String alarmTypes) {
		this.alarmTypes = alarmTypes;
	}


	public String getDealSign() {
		return dealSign;
	}

	public void setDealSign(String dealSign) {
		this.dealSign = dealSign;
	}

	public String getAlarmTimeStart() {
		return alarmTimeStart;
	}

	public void setAlarmTimeStart(String alarmTimeStart) {
		this.alarmTimeStart = alarmTimeStart;
	}

	public String getAlarmTimeEnd() {
		return alarmTimeEnd;
	}

	public void setAlarmTimeEnd(String alarmTimeEnd) {
		this.alarmTimeEnd = alarmTimeEnd;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	
	
    
    

}
