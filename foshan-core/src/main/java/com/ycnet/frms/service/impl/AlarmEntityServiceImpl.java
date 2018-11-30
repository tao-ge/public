package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarmEntity;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.DeviceAlarmEntityMapper;
import com.ycnet.frms.mapper.DeviceLockEntityMapper;
import com.ycnet.frms.mapper.DeviceLockStatusEntityMapper;
import com.ycnet.frms.mapper.FacilityEntityMapper;
import com.ycnet.frms.service.AlarmEntityService;
import com.ycnet.frms.service.DeviceService;
import com.ycnet.frms.vo.mobile.AlarmEntityMobile;
import com.ycnet.frms.vo.mobile.FacilityAlarmListMobile;

@Service("alarmEntityService")
public class AlarmEntityServiceImpl implements AlarmEntityService {

	@Resource(name="deviceAlarmEntityMapper")
	private DeviceAlarmEntityMapper deviceAlarmEntityMapper;
	@Resource(name="facilityEntityMapper")
	private FacilityEntityMapper facilityEntityMapper;
	@Resource(name="deviceLockStatusEntityMapper")
	private DeviceLockStatusEntityMapper deviceLockStatusEntityMapper;
	
	@Resource(name="deviceLockEntityMapper")
	private DeviceLockEntityMapper deviceLockEntityMapper;
	
	@Resource(name="deviceService")
	private DeviceService deviceService;
	
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
	@Override
	public List<AlarmEntityMobile> queryAlarmList(PageBean pb, Long orgId,AlarmEntityMobile aem) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("orgId", orgId);
		map.put("aem", aem);
		List<AlarmEntityMobile> alarmList=deviceAlarmEntityMapper.queryAlarmList(map);
		return alarmList;
	}

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
	 */
	@Override
	public int modifAlarm(Users users,String alarmIds, String dealSituation) {
		DeviceAlarmEntity alarm=null;
		String[] alarms = alarmIds.split(",");
		int reg=0;
		if(alarms.length>0) {
			for (int i = 0; i < alarms.length; i++) {
				alarm= new DeviceAlarmEntity();
				long alarmId = Long.parseLong(alarms[i]);
				alarm.setAlarmId(alarmId);
				alarm.setDealSituation(dealSituation);
				alarm.setUserId(users.getUserId());
				alarm.setUserName(users.getUserName());
				alarm.setDealTime(new Date());
				alarm.setDealSign("1");
				reg=deviceAlarmEntityMapper.updateByPrimaryKeySelective(alarm);
				
				DeviceAlarmEntity alarmEntity = deviceAlarmEntityMapper.selectByPrimaryKey(alarmId);
				if(alarmEntity != null && alarmEntity.getDevId() != null) {
					//查询设施下的报警记录数量
					int req = deviceAlarmEntityMapper.queryAlarmByDevId(alarmEntity.getDevId());
					if(req<=0) {
						//如果该设施下无报警记录,修改设施绑定的中控器状态
						List<DeviceEntity> codList = deviceService.selectListByDevId(alarm.getDevId());
						if(codList != null) {
							for (DeviceEntity device : codList) {
								//如果中控器状态为工作中,则修改中控器状态为正常
								if("02".equals(device.getWorkState())) {
									device.setWorkState("01");
									device.setLastModifyTime(new Date());
									device.setLastModifyUser(users.getUserId());
									deviceService.updateByPrimaryKeySelective(device);
								}
							}
						}
					}
				}
			}
		}
		return reg;
	}

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
	 */
	@Override
	public FacilityAlarmListMobile queryAlarmInfo(Long devId, PageBean pb) {
		FacilityAlarmListMobile alarmList=deviceAlarmEntityMapper.queryAlarmInfo(devId,pb);
		return alarmList;
	}

}
