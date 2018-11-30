package com.ycnet.frms.vo;

import java.util.Date;

public class DeviceLockStatusEntityVo {
    private Long lockStatusId;

    private Long devId;

    private Long lockId01;

    private Long lockId02;

    private Date colTime;

    private Date rptTime;

    private String lockOpen01;

    private String doorOpen01;

    private String lockOpen02;

    private String doorOpen02;

    private String temp;//温度

    private String battery;//电量

    private String tilt;//倾斜

    private String signals;//信号

    private String alarmSign;

    private String dealSign;

    private String curStatus;

    private Long orgId;
    
    private String devType;
    
    private String devName;
    
    private String devCode;
    
    private String areaCode1;
    
    private String devAddr;
    
    private String codName;
    
    private Long codId;
    
    private String codMac;//蓝牙MAC
    
    private String codImei;//网络imei
    
    private Date createTime;
    
    private Long createUser;
    
    private String createUserName;//创建人姓名
    
    private Date lastModifyTime;
    
    private Long lastModifyUser;
    
    private String lastModifyUserName;//最后修改人姓名
    
    private String hardVersion;//版本号
    
    private String startTime;
    
    private String endTime;
    
    private String alarm;//是否报警
    
    private String oprStyle;
    
    private String hardState;//硬件更新返回状态 0升级成功 1发送HTTP错误 2获取HTTP失败 3下载文件失败 4文件大小错误 5打开文件失败 6文件检验码错误' AFTER `work_state`; 


    public Long getLockStatusId() {
        return lockStatusId;
    }

    public void setLockStatusId(Long lockStatusId) {
        this.lockStatusId = lockStatusId;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Date getColTime() {
        return colTime;
    }

    public void setColTime(Date colTime) {
        this.colTime = colTime;
    }

    public Date getRptTime() {
        return rptTime;
    }

    public void setRptTime(Date rptTime) {
        this.rptTime = rptTime;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery == null ? null : battery.trim();
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt == null ? null : tilt.trim();
    }

    public String getSignals() {
        return signals;
    }

    public void setSignals(String signals) {
        this.signals = signals == null ? null : signals.trim();
    }

    public String getAlarmSign() {
        return alarmSign;
    }

    public void setAlarmSign(String alarmSign) {
        this.alarmSign = alarmSign == null ? null : alarmSign.trim();
    }

    public String getDealSign() {
        return dealSign;
    }

    public void setDealSign(String dealSign) {
        this.dealSign = dealSign == null ? null : dealSign.trim();
    }

    public String getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus == null ? null : curStatus.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(String areaCode1) {
		this.areaCode1 = areaCode1;
	}

	public String getDevAddr() {
		return devAddr;
	}

	public void setDevAddr(String devAddr) {
		this.devAddr = devAddr;
	}

	public String getCodName() {
		return codName;
	}

	public void setCodName(String codName) {
		this.codName = codName;
	}

	public Long getCodId() {
		return codId;
	}

	public void setCodId(Long codId) {
		this.codId = codId;
	}

	public String getCodMac() {
		return codMac;
	}

	public void setCodMac(String codMac) {
		this.codMac = codMac;
	}

	public String getCodImei() {
		return codImei;
	}

	public void setCodImei(String codImei) {
		this.codImei = codImei;
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

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getLastModifyUserName() {
		return lastModifyUserName;
	}

	public void setLastModifyUserName(String lastModifyUserName) {
		this.lastModifyUserName = lastModifyUserName;
	}

	public String getHardVersion() {
		return hardVersion;
	}

	public void setHardVersion(String hardVersion) {
		this.hardVersion = hardVersion;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}

	public Long getLockId01() {
		return lockId01;
	}

	public void setLockId01(Long lockId01) {
		this.lockId01 = lockId01;
	}

	public Long getLockId02() {
		return lockId02;
	}

	public void setLockId02(Long lockId02) {
		this.lockId02 = lockId02;
	}

	public String getLockOpen01() {
		return lockOpen01;
	}

	public void setLockOpen01(String lockOpen01) {
		this.lockOpen01 = lockOpen01;
	}

	public String getDoorOpen01() {
		return doorOpen01;
	}

	public void setDoorOpen01(String doorOpen01) {
		this.doorOpen01 = doorOpen01;
	}

	public String getLockOpen02() {
		return lockOpen02;
	}

	public void setLockOpen02(String lockOpen02) {
		this.lockOpen02 = lockOpen02;
	}

	public String getDoorOpen02() {
		return doorOpen02;
	}

	public void setDoorOpen02(String doorOpen02) {
		this.doorOpen02 = doorOpen02;
	}

	public String getOprStyle() {
		return oprStyle;
	}

	public void setOprStyle(String oprStyle) {
		this.oprStyle = oprStyle;
	}

	public String getHardState() {
		return hardState;
	}

	public void setHardState(String hardState) {
		this.hardState = hardState;
	}

    
}