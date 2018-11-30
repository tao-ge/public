package com.ycnet.frms.bean;

import java.util.Date;

public class WxUnlock {

	private Long applyId; 		// 微信远程开锁申请ID
	private Long devId; 		// 申请开锁的设施ID
	private Long applyUser; 	// 申请人ID
	private String isOpenlock; 	// 是否已开锁
	private Date operatTime; 	// 操作时间
	private String remark; 		// 备注信息
	private Long orgId; 		// 组织机构ID
	private Long codId;			// 中控器设备ID
	
	private Users user;
	
	public Long getApplyId() {
		return applyId;
	}
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	public Long getDevId() {
		return devId;
	}
	public void setDevId(Long devId) {
		this.devId = devId;
	}
	public Long getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(Long applyUser) {
		this.applyUser = applyUser;
	}
	public String getIsOpenlock() {
		return isOpenlock;
	}
	public void setIsOpenlock(String isOpenlock) {
		this.isOpenlock = isOpenlock;
	}
	public Date getOperatTime() {
		if(this.operatTime == null) {
			return new Date();
		}
		return operatTime;
	}
	public void setOperatTime(Date operatTime) {
		this.operatTime = operatTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getCodId() {
		return codId;
	}
	public void setCodId(Long codId) {
		this.codId = codId;
	}
	
	public Users getUser() {
		user = new Users();
		user.setOrgId(this.orgId);
		return user;
	}
	
	@Override
	public String toString() {
		return "WxUnlock [applyId=" + applyId + ", devId=" + devId + ", applyUser=" 
				+ applyUser + ", isOpenlock=" + isOpenlock + " operatTime=" 
				+ operatTime + ", remark=" + remark + ", orgId=" + orgId + ", codId ="
				+ codId + "]";
	}
	
}
