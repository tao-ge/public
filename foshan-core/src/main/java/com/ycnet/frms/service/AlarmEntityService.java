package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.mobile.AlarmEntityMobile;
import com.ycnet.frms.vo.mobile.FacilityAlarmListMobile;


public interface AlarmEntityService {

	/**
	 * 
	* @Title: queryAlarmList 
	* @Description: 告警信息列表 
	* @param @param session
	* @param @param userId
	* @param @param pb
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月12日 下午7:56:08 
	* @version V1.0
	 */
	List<AlarmEntityMobile> queryAlarmList(PageBean pb, Long orgId,AlarmEntityMobile aem);

	/**
	 * 
	* @Title: modifAlarm 
	* @Description: 批量处理告警信息 
	* @param @param session
	* @param @param userId
	* @param @param alarmIds
	* @param @param dealSituation
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月13日 上午10:27:26 
	* @version V1.0
	 * @param users 
	 */
	int modifAlarm(Users users, String alarmIds, String dealSituation);

	/**
	 * 
	* @Title: queryAlarmInfo 
	* @Description: 告警详细信息 
	* @param @param session
	* @param @param userId
	* @param @param alarmId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月13日 上午10:39:37 
	* @version V1.0
	 * @param pb 
	 */
	FacilityAlarmListMobile queryAlarmInfo(Long devId, PageBean pb); 

}
