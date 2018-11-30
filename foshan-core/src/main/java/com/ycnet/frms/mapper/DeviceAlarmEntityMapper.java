package com.ycnet.frms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarmEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.DeviceAlarmEntityBean;
import com.ycnet.frms.vo.mobile.AlarmEntityMobile;
import com.ycnet.frms.vo.mobile.FacilityAlarmListMobile;

public interface DeviceAlarmEntityMapper {
    int deleteByPrimaryKey(Long alarmId);

    int insert(DeviceAlarmEntity record);

    int insertSelective(DeviceAlarmEntity record);

    DeviceAlarmEntity selectByPrimaryKey(Long alarmId);

    int updateByPrimaryKeySelective(DeviceAlarmEntity record);

    int updateByPrimaryKey(DeviceAlarmEntity record);

    /**
     * 
    * @Title: queryAlarmList 
    * @Description: 告警信息列表 
    * @param @param map
    * @param @return    
    * @return List<AlarmEntityVo>
    * @author liucanghai 
    * @throws
    * @date 2018年4月12日 下午8:12:12 
    * @version V1.0
     */
	List<AlarmEntityMobile> queryAlarmList(HashMap<String, Object> map);

	/**
	 * 接口查询告警信息列表
	* @Title: queryAlarmInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param pb
	* @param @return    入参
	* @return FacilityAlarmListMobile    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月8日 上午11:00:09 
	* @version V1.0
	 */
	FacilityAlarmListMobile queryAlarmInfo(Long devId, PageBean pb);

	/**
	 * 查询报警记录数据
	* @Title: queryDeviceAlarm 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return List<DeviceAlarmEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月15日 下午2:47:00 
	* @version V1.0
	 */
	List<DeviceAlarmEntityBean> queryDeviceAlarmList(Map<String, Object> map);

	/**
	 * 查询记录总数
	* @Title: queryDeviceAlarmCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月15日 下午2:47:52 
	* @version V1.0
	 */
	Integer queryDeviceAlarmCount(Map<String, Object> map);

	/**
	 * 查看报警详情
	* @Title: queryDeviceAlarmInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param alarmId
	* @param @return    入参
	* @return DeviceAlarmEntityBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月16日 上午9:23:23 
	* @version V1.0
	 */
	DeviceAlarmEntityBean queryDeviceAlarmInfo(Long alarmId);

	/**
	 * 查询报警记录
	* @Title: queryDeviceAlarmListByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return List<DeviceAlarmEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月20日 下午5:08:02 
	* @version V1.0
	 */
	List<DeviceAlarmEntityBean> queryDeviceAlarmListByConditions(Map<String, Object> map);

	/**
	 * 查询报警记录总条数
	* @Title: queryDeviceAlarmCountsByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月21日 下午1:10:50 
	* @version V1.0
	 */
	Integer queryDeviceAlarmCountsByConditions(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryAlarmByDevId
	 * @Description: 查询设施下未处理报警记录
	 * @param @param devId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月8日 上午9:38:12
	 * @version V1.0
	 */
	int queryAlarmByDevId(Long devId);

	/**
	 * 查询报警记录中是否有锁打开
	* @Title: queryByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param format
	* @param @param user
	* @param @return    入参
	* @return DeviceAlarmEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月8日 下午2:36:11 
	* @version V1.0
	 * @param dev 
	 */
	DeviceAlarmEntity queryByConditions(Long devId, String format, Users user);
}