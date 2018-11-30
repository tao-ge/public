package com.ycnet.frms.vo;

import java.util.Date;
import java.util.List;

import com.ycnet.frms.bean.WorkorderLightline;
import com.ycnet.frms.bean.WorkorderRoutes;


/**
 * 
* @ClassName: WorkorderBean 
* @Description: TODO(用于光路新增,调整,停闭) 
* @author (刘沧海)  
* @date 2017年11月7日 下午1:44:27 
* @version V1.0
 */
public class WorkorderBean {
	
	private Date createTime;        	//创建时间
	
	private Date currentTime; 			//到达当前环节时间
	
	private String currentSession;		//当前所在环节
	
	private String  userName;				//当前处理人
	
	private String serialNumber;		//流水号
	
	private String orderTitle;  		//工单标题
	
	private String routeName;			//光路名称
	
	private String adevName;			//A端站点
	
	private String zdevName;			//Z端站点
	
	private String adevNameRoom;		//A端机房
	
	private String zdevNameRoom;		//Z端机房
	
	private Integer pageno;    			//页数

	private String createTimeSta;		//高级查询:创建时间:开始
	
	private String createTimeEnd;		//高级查询:创建时间:结束
	
	private String currentSta;			//高级查询,到达当前环节时间:开始
	
	private String currentEnd;			//高级查询,到达当前环节时间:结束
	
	private String areaCode;			//所属区
	
	private Long designId;
	
	private Long workorderId;
	
	private List<WorkorderLightline> woklineList;//工单下面的光路
	
	private List<WorkorderRoutes> wokRouteList;//调度工单下的设施
	
	private String orderNumber;
	
	private String orderType;
	
	private String areaName;
	
	private Long processInstanceId;
	
	
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(Long workorderId) {
		this.workorderId = workorderId;
	}

	public List<WorkorderRoutes> getWokRouteList() {
		return wokRouteList;
	}

	public void setWokRouteList(List<WorkorderRoutes> wokRouteList) {
		this.wokRouteList = wokRouteList;
	}

	public Long getDesignId() {
		return designId;
	}

	public void setDesignId(Long designId) {
		this.designId = designId;
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

	public Integer getPageno() {
		return pageno;
	}

	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}

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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public String getAdevName() {
		return adevName;
	}

	public void setAdevName(String adevName) {
		this.adevName = adevName;
	}

	public String getZdevName() {
		return zdevName;
	}

	public void setZdevName(String zdevName) {
		this.zdevName = zdevName;
	}

	public String getAdevNameRoom() {
		return adevNameRoom;
	}

	public void setAdevNameRoom(String adevNameRoom) {
		this.adevNameRoom = adevNameRoom;
	}

	public String getZdevNameRoom() {
		return zdevNameRoom;
	}

	public void setZdevNameRoom(String zdevNameRoom) {
		this.zdevNameRoom = zdevNameRoom;
	}

	public List<WorkorderLightline> getWoklineList() {
		return woklineList;
	}

	public void setWoklineList(List<WorkorderLightline> woklineList) {
		this.woklineList = woklineList;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}
