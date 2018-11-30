package com.ycnet.frms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.AlarmPushlog;
import com.ycnet.frms.bean.DeviceAlarm;

public interface DeviceAlarmMapper {
    int deleteByPrimaryKey(Long alarmProcessId);

    int insert(DeviceAlarm record);

    int insertSelective(DeviceAlarm record);

    DeviceAlarm selectByPrimaryKey(Long alarmProcessId);

    int updateByPrimaryKeySelective(DeviceAlarm record);

    int updateByPrimaryKey(DeviceAlarm record);
    
    
    List<DeviceAlarm> queryByConditionMap(Map<String,Object> map);
    
    int queryCountByConditionMap(Map<String,Object> map);
    
    int queryUntreatedCountByConditionMap(Map<String,Object> map);
    
    List<DeviceAlarm> queryNewRecordByConditionMap(Map<String,Object> map);
    
    int queryNewRecordCountByConditionMap(Map<String, Object> map);
    
    List<DeviceAlarm> getAlarmList();
    
    int AlarmPushlogAdd(AlarmPushlog record);
    
    String getOrgIdByDevId(Long devId);
    
    AlarmPushlog getIsExitByLogTS(Long alarmProcessId);
    
    AlarmPushlog getIsExitByLogDX(Long alarmProcessId);
    
    int updateByPrimaryKeySelectives(AlarmPushlog record);

	int deleteAlarmByImeiAndMac(DeviceAlarm da);

	/**
	 * fl 根据设施ID 查询报警记录
	 * @param request
	 * @param devId
	 * @param pb 
	 * @return
	 */
	List<DeviceAlarm> queryDeviceAlarmByDevId(HashMap<String, Object> map);

	/**
	 * fl 根据设施ID 查询报警记录总条数
	 * @param request
	 * @param devId
	 * @param pb 
	 * @return
	 */
	Integer queryCountDeviceAlarmByDevId(HashMap<String, Object> map);

	/**
	 * 查询报警内容
	* @Title: queryByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return DeviceAlarm    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月24日 下午2:19:48 
	* @version V1.0
	 */
	DeviceAlarm queryByDevId(Long devId);
}