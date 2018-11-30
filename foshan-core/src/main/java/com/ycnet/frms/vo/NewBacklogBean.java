package com.ycnet.frms.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
* @ClassName: NewBacklogBean 
* @Description: TODO(光路新增-我的待办) 
* @author (刘沧海)  
* @date 2017年11月7日 上午9:16:46 
* @version V1.0
 */
public class NewBacklogBean {

	private Date createTime;        	//创建时间
	private Date currentTime; 			//到达当前环节时间
	
	private String currentSession;		//当前所在环节
	
	private String  userName;				//当前处理人
	
	
	private String orderTitle;  		//工单标题
	
	private String routeName;			//光路名称
	
	private String aDevName;			//A端站点
	
	private String zDevName;			//Z端站点
	
	private String aroomDevName;		//A端机房
	
	private String zroomDevName;		//Z端机房
	
	private Integer pageno;    			//页数

	private String createTimeSta;		//高级查询:创建时间:开始
	
	private String createTimeEnd;		//高级查询:创建时间:结束
	
	private String currentSta;			//高级查询,到达当前环节时间:开始
	
	private String currentEnd;			//高级查询,到达当前环节时间:结束
	
	private Long workorderId ;			//工单ID
	
	private String orderNumber;
	
	private String processName;			//当前流程名称
	private Date processTime;			//到达当前流程时间
	
	private String urgency;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public String getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(String currentSession) {
		this.currentSession = currentSession;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getaDevName() {
		return aDevName;
	}

	public void setaDevName(String aDevName) {
		this.aDevName = aDevName;
	}

	public String getzDevName() {
		return zDevName;
	}

	public void setzDevName(String zDevName) {
		this.zDevName = zDevName;
	}

	public String getAroomDevName() {
		return aroomDevName;
	}

	public void setAroomDevName(String aroomDevName) {
		this.aroomDevName = aroomDevName;
	}

	public String getZroomDevName() {
		return zroomDevName;
	}

	public void setZroomDevName(String zroomDevName) {
		this.zroomDevName = zroomDevName;
	}

	public Integer getPageno() {
		return pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

	public String getCreateTimeSta() {
		return createTimeSta;
	}

	public void setCreateTimeSta(String createTimeSta) {
		this.createTimeSta = createTimeSta;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCurrentSta() {
		return currentSta;
	}

	public void setCurrentSta(String currentSta) {
		this.currentSta = currentSta;
	}

	public String getCurrentEnd() {
		return currentEnd;
	}

	public void setCurrentEnd(String currentEnd) {
		this.currentEnd = currentEnd;
	}

	public Long getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(Long workorderId) {
		this.workorderId = workorderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	
	
	
	
}
