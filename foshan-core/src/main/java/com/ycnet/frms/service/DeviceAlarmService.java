package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.DeviceStatus;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.DeviceAlarmBean;
import com.ycnet.frms.vo.DeviceAlarmConditionBean;

public interface DeviceAlarmService {
	
	/**
	 * 按条件查询数据，支持分页、高级查询
	 * @param bean
	 * @param user
	 * @param pb
	 * @return
	 */
	PageBean queryByConditionBean(DeviceAlarmConditionBean bean,Users user,PageBean pb);
	
	int deviceAlarmCtrlSave(DeviceAlarm deviceAlarm,Users user);
	
	PageBean queryUntreatedCountByConditionMap(DeviceAlarmConditionBean bean,Users user,PageBean pb);
	
	PageBean queryNewRecordByConditionMap(DeviceAlarmConditionBean bean,Users user,PageBean pb);
	
	DeviceAlarm selectById(Long alarmProcessId);

	/**
	 * 
	* @Title: deleteAlarmByImeiAndMac 
	* @Description: TODO(根据imei和mac删除报警记录) 
	* @param @param da
	* @param @return    入参
	* @return int    返回类型
	* @author （DZY） 
	* @throws
	* @date 2017年9月6日 下午4:05:56 
	* @version V1.0
	 */
	int deleteAlarmByImeiAndMac(DeviceAlarm da);

	/**
	 * 报警记录导出查询
	* 
	* @Title: DeviceAlarmService.java 
	* @Description: TODO 
	* @param @param deviceAlarmCon
	* @param @param user
	* @param @param pb
	* @param @return
	* @return PageBean
	* @author fl
	* @date 2018年2月6日 上午10:02:10
	* @version V1.0
	 */
	PageBean queryexport(DeviceAlarmConditionBean deviceAlarmCon, Users user, PageBean pb);

	/**
	 * fl 根据设施ID 查询报警记录
	 * @param request
	 * @param devId
	 * @param areaCode1 
	 * @param pb 
	 * @param user 
	 * @return
	 */
	PageBean queryDeviceAlarmByDevId(Long devId, String areaCode1, PageBean pb, Users user);

	/**
	 * 
	 * @Title: queryNotCompletedCount
	 * @Description: 查询未处理报警的数量
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月12日 下午5:10:41
	 * @version V1.0
	 */
	int queryNotCompletedCount(Users user);

	/**
	 * 保存到报警记录中
	* @Title: insertByImei 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param imei
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月28日 下午5:41:28 
	* @version V1.0
	 */
	int insertByImei(String imei);
	

	/**
	 * 插入远程开锁记录
	* @Title: insertMobileSwithLock 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @param time
	* @param @param imei
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年7月29日 下午8:35:03 
	* @version V1.0
	 */
	int insertMobileSwithLock(String userId,String time,String imei);

	/**
	 * 
	 * @Title: insertDeviceAlarm
	 * @Description: 处理干城上传报警信息
	 * @param @param ds
	 * @param @param alarmTypes
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月30日 下午3:22:45
	 * @version V1.0
	 * @param colTime 
	 */
	int updownDeviceAlarm(DeviceStatus ds, String alarmTypes, String time);

}
