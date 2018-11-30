package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarmEntity;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.DeviceLockEntity;
import com.ycnet.frms.bean.DeviceLockStatusEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.DeviceAlarmEntityMapper;
import com.ycnet.frms.mapper.DeviceLockEntityMapper;
import com.ycnet.frms.mapper.DeviceLockStatusEntityMapper;
import com.ycnet.frms.mapper.DeviceParamEntityMapper;
import com.ycnet.frms.service.DeviceAlarmEntityService;
import com.ycnet.frms.service.DeviceParamService;
import com.ycnet.frms.service.DeviceService;
import com.ycnet.frms.vo.DeviceAlarmEntityBean;

@Service("deviceAlarmEntityService")
public class DeviceAlarmEntityServiceImpl implements DeviceAlarmEntityService{
	
	@Resource(name="deviceAlarmEntityMapper")
	private DeviceAlarmEntityMapper deviceAlarmEntityMapper;
	
	@Resource(name="deviceLockStatusEntityMapper")
	private DeviceLockStatusEntityMapper deviceLockStatusEntityMapper;
	
	@Resource(name="deviceLockEntityMapper")
	private DeviceLockEntityMapper deviceLockEntityMapper;
	
	@Resource(name="deviceService")
	private DeviceService deviceService;

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
	@Override
	public PageBean queryDeviceAlarm(PageBean pb, DeviceAlarmEntityBean deviceAlarmEntity, Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("deviceAlarmEntity", deviceAlarmEntity);
		map.put("orgId", orgId);
		pb.setList(deviceAlarmEntityMapper.queryDeviceAlarmList(map));
		pb.setRows(deviceAlarmEntityMapper.queryDeviceAlarmCount(map));
		return pb;
	}

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
	@Override
	public DeviceAlarmEntityBean queryDeviceAlarmInfo(Long alarmId) {
		return deviceAlarmEntityMapper.queryDeviceAlarmInfo(alarmId);
	}

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
	@Override
	public PageBean queryDeviceAlarmListByConditions(Long devId, PageBean pb, Long orgId) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("pb", pb);
			map.put("orgId", orgId);
			pb.setList(deviceAlarmEntityMapper.queryDeviceAlarmListByConditions(map)); 
			pb.setRows(deviceAlarmEntityMapper.queryDeviceAlarmCountsByConditions(map));
		return pb;
	}

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
	@Override
	public int deviceAlarmPresentationSave(DeviceAlarmEntity deviceAlarm, Users user) {
		deviceAlarm.setUserId(user.getUserId());
		deviceAlarm.setUserName(user.getUserName());
		deviceAlarm.setDealTime(new Date());
		deviceAlarm.setDealSign("1");
		int num=deviceAlarmEntityMapper.updateByPrimaryKeySelective(deviceAlarm);
		
		DeviceAlarmEntity alarm = deviceAlarmEntityMapper.selectByPrimaryKey(deviceAlarm.getAlarmId());
		if(alarm != null && alarm.getDevId() != null) {
			//查询设施下的报警记录数量
			int req = deviceAlarmEntityMapper.queryAlarmByDevId(alarm.getDevId());
			if(req<=0) {
				//如果该设施下无报警记录,修改设施绑定的中控器状态
				List<DeviceEntity> codList = deviceService.selectListByDevId(alarm.getDevId());
				if(codList != null) {
					for (DeviceEntity device : codList) {
						//如果中控器状态为工作中,则修改中控器状态为正常
						if("02".equals(device.getWorkState())) {
							device.setWorkState("01");
							device.setLastModifyTime(new Date());
							device.setLastModifyUser(user.getUserId());
							deviceService.updateByPrimaryKeySelective(device);
						}
					}
				}
			}
		}
		
		return num;
	}
}
