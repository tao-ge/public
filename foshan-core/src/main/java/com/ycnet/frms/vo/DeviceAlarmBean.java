package com.ycnet.frms.vo;

import com.ycnet.frms.bean.DeviceAlarm;



public class DeviceAlarmBean extends DeviceAlarm {
	
	private String dealSignName;
	
	private String isCheck;//是否显示查看
	
	private String imgUrl;//异常开门图片路径
	
	private String devPlatform;//设备所属平台  
	
	private String pushType;//推送用，区分推送类型	alarm 报警推送	inspect 巡检任务推送
	
	private String orgId;//组织机构ID，推送用
	
	private String pushTo;//区分推送到哪个平台  40 正式服务器   128 测试服务器
	
	private String opStyleName;
	private String lockStatusName;//锁状态中文名
	private String doorStatusName;//门状态中文名
	private String isAlarmName;//是否报警中文状态
	
	private String tempAlarm;//温度报警标志 1正常  0报警
	private String humiAlarm;//湿度报警标志 1正常  0报警
	private String battAlarm;//电量报警标志 1正常  0报警
	private String tiltAlarm;//倾斜报警标志 1正常  0报警
	private String doorOpenErr;//异常开门
	private String dateTime;//格式化后的日期
	private String devStatu;//设备状态0 正常 1 工作 2报警
	
    private String temp;//温度
    
    private String humidity;//湿度
    
    private String tilt;//倾斜
    
    private String battery;//电量
    
    private String signals;//信号

	public String getDealSignName() {
		return dealSignName;
	}

	public void setDealSignName(String dealSignName) {
		this.dealSignName = dealSignName;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDevPlatform() {
		return devPlatform;
	}

	public void setDevPlatform(String devPlatform) {
		this.devPlatform = devPlatform;
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

	public String getPushTo() {
		return pushTo;
	}

	public void setPushTo(String pushTo) {
		this.pushTo = pushTo;
	}

	public String getOpStyleName() {
		return opStyleName;
	}

	public void setOpStyleName(String opStyleName) {
		this.opStyleName = opStyleName;
	}

	public String getLockStatusName() {
		return lockStatusName;
	}

	public void setLockStatusName(String lockStatusName) {
		this.lockStatusName = lockStatusName;
	}

	public String getDoorStatusName() {
		return doorStatusName;
	}

	public void setDoorStatusName(String doorStatusName) {
		this.doorStatusName = doorStatusName;
	}

	public String getIsAlarmName() {
		return isAlarmName;
	}

	public void setIsAlarmName(String isAlarmName) {
		this.isAlarmName = isAlarmName;
	}

	public String getTempAlarm() {
		return tempAlarm;
	}

	public void setTempAlarm(String tempAlarm) {
		this.tempAlarm = tempAlarm;
	}

	public String getHumiAlarm() {
		return humiAlarm;
	}

	public void setHumiAlarm(String humiAlarm) {
		this.humiAlarm = humiAlarm;
	}

	public String getBattAlarm() {
		return battAlarm;
	}

	public void setBattAlarm(String battAlarm) {
		this.battAlarm = battAlarm;
	}

	public String getTiltAlarm() {
		return tiltAlarm;
	}

	public void setTiltAlarm(String tiltAlarm) {
		this.tiltAlarm = tiltAlarm;
	}

	public String getDoorOpenErr() {
		return doorOpenErr;
	}

	public void setDoorOpenErr(String doorOpenErr) {
		this.doorOpenErr = doorOpenErr;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDevStatu() {
		return devStatu;
	}

	public void setDevStatu(String devStatu) {
		this.devStatu = devStatu;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getTilt() {
		return tilt;
	}

	public void setTilt(String tilt) {
		this.tilt = tilt;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getSignals() {
		return signals;
	}

	public void setSignals(String signals) {
		this.signals = signals;
	}
	
	

}
