package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.DeviceAlarmEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.DeviceAlarmBean;
import com.ycnet.frms.vo.DeviceAlarmConditionBean;
import com.ycnet.frms.vo.DeviceAlarmEntityBean;

public interface DeviceAlarmEntityService {

	/**
	 * 页面报警信息查询
	* @Title: queryDeviceAlarm 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pb
	* @param @param deviceAlarmEntity
	* @param @param orgId
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月15日 下午2:41:14 
	* @version V1.0
	 */
	PageBean queryDeviceAlarm(PageBean pb, DeviceAlarmEntityBean deviceAlarmEntity, Long orgId);

	/**
	 * 查询报警详情
	* @Title: queryDeviceAlarm 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param alarmId
	* @param @param orgId
	* @param @return    入参
	* @return DeviceAlarmEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月16日 上午9:20:55 
	* @version V1.0
	 */
	DeviceAlarmEntityBean queryDeviceAlarmInfo(Long alarmId);

	/**
	 * 查询报警记录
	* @Title: queryDeviceAlarmList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return List<DeviceAlarmEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月20日 下午5:05:50 
	* @version V1.0
	 */
	PageBean queryDeviceAlarmListByConditions(Long devId, PageBean pb, Long orgId);

	/**
	 *  报警处理保存
	* @Title: deviceAlarmPresentationSave 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceAlarm
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月27日 上午11:33:06 
	* @version V1.0
	 */
	int deviceAlarmPresentationSave(DeviceAlarmEntity deviceAlarm, Users user);

}
