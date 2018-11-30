package com.ycnet.frms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceImage;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.DeviceImageMapper;
import com.ycnet.frms.mapper.MobileSwitchMapper;
import com.ycnet.frms.service.DeviceRegService;
import com.ycnet.frms.service.MobileSwitchService;

@Service("mobileSwitchService")
public class MobileSwitchServiceImpl implements MobileSwitchService {

	@Resource(name="mobileSwitchMapper")
	private MobileSwitchMapper mobileSwitchMapper;
	
	@Resource(name="deviceImageMapper")
	private DeviceImageMapper deviceImageMapper;
	
	@Resource(name="deviceRegService")
	DeviceRegService deviceRegService;
	
	@Override
	@Transactional
	public int save(MobileSwitch ms) {
		int ret = 0;
		if(ms.getSwitchId()!=null)
		{
			ret = mobileSwitchMapper.updateByPrimaryKeySelective(ms);
		}
		else
		{
			ret = mobileSwitchMapper.insertSelective(ms);
		}
		return ret;
	}


	@Override
	public List<MobileSwitch> queryList(MobileSwitch ms,PageBean pb) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ms", ms);
		map.put("pb", pb);
		return mobileSwitchMapper.queryListByMap(map);
	}


	@Override
	public PageBean queryByConditionBean(MobileSwitch ms, PageBean pb,Users user) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("ms", ms);		
		conditionMap.put("pb", pb);
		conditionMap.put("user", user);
		pb.setRows(mobileSwitchMapper.queryCountByConditionMap(conditionMap));
//		List<MobileSwitch> list = mobileSwitchMapper.queryListByMap(conditionMap);
		pb.setList(mobileSwitchMapper.queryListByMap(conditionMap));		
				
		return pb;
	}
	
	@Override
	public PageBean queryNewRecordListByMap(MobileSwitch ms, Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("ms", ms);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		pb.setRows(mobileSwitchMapper.queryNewRecordCountByConditionMap(conditionMap));
		pb.setList(mobileSwitchMapper.queryNewRecordListByMap(conditionMap));		
				
		return pb;
	}


	/**
	 * 
	 * @Title: queryById
	 * @Description: 根据ID查询开关锁记录
	 * @param @param switchId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月28日 下午2:51:43
	 * @version V1.0
	 */
	@Override
	public MobileSwitch queryById(Long switchId) {
		return mobileSwitchMapper.selectByPrimaryKey(switchId);
	}


	/**
	 * 导出开关锁记录
	* 
	* @Title: MobileSwitchService.java 
	* @Description: TODO 
	* @param @param ms
	* @param @param user
	* @param @return
	* @return List<MobileSwitch>
	* @author fl
	* @date 2018年2月6日 上午9:38:40
	* @version V1.0
	 */
	@Override
	public List<MobileSwitch> queryexport(MobileSwitch ms, Users user) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("ms", ms);
		conditionMap.put("user", user);
		List<MobileSwitch> list = mobileSwitchMapper.queryListByMap(conditionMap);
		if (list !=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getSwitchType().equals("0")) {
					list.get(i).setSwitchType("蓝牙开锁");
				}else if (list.get(i).getSwitchType().equals("1")) {
					list.get(i).setSwitchType("远程开锁");
				}else if (list.get(i).getSwitchType().equals("2")) {
					list.get(i).setSwitchType("扫码开锁");
				}else if (list.get(i).getSwitchType().equals("3")) {
					list.get(i).setSwitchType("小程序开锁");
				}else if (list.get(i).getSwitchType().equals("4")) {
					list.get(i).setSwitchType("机械开锁");
				}else {
					list.get(i).setSwitchType("未知开锁");
				}
			}
		}
		return list;
	}


	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 手机开锁记录
	 * @param request
	 * @param devId
	 * @return
	 */
	@Override
	public PageBean querySwitchRecordByDevId(Long devId,PageBean pb, Users user) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("pb", pb);
		map.put("user", user);
		pb.setRows(mobileSwitchMapper.queryCountSwitchRecordByDevId(map));
		pb.setList(mobileSwitchMapper.querySwitchRecordByDevId(map));
		return pb;
	}


	/**
	 * 
	* @Title: queryMobileSwitchByUserId 
	* @Description: 当前用户的开关锁记录 
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年2月9日 下午2:31:42 
	* @version V1.0
	 */
	@Override
	public List<MobileSwitch> queryMobileSwitchByUserId(Facility facility,Long userId) {
		List<MobileSwitch> mobileList = mobileSwitchMapper.queryMobileSwitchByUserId(facility,userId);
		if(mobileList!=null && mobileList.size()>0) {
			for(MobileSwitch mobile:mobileList) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("devImei", mobile.getDevImei());
				map.put("oprStyle", "1");
				map.put("alarmTime", mobile.getColTime());
				DeviceImage device=deviceImageMapper.queryByImeiAndColtime(map);//查询开锁图片
				if(device!=null) {
					if(!"".equals(device.getImageUrl())) {
						mobile.setImgUrl(device.getImageUrl());
					}
				}
			}
		}
		return mobileList;
	}

	/**
	 * 
	 * @Title: queryRemoteUnlock
	 * @Description: 查询远程开锁
	 * @param @param user
	 * @param @param unlockTime
	 * @param @param devImei
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月26日 下午2:22:07
	 * @version V1.0
	 */
	@Override
	public int queryRemoteUnlock(String config,Users user, long unlockTime, String devImei) {
		Boolean result = false;
		int req = 0;//返回结果	1.开锁成功	2.开锁超时	3.开锁失败
		try {
			int num = deviceRegService.httpsConection(config,devImei,user.getDevPlatform(),user.getUserId(),90l);
			if(num < 1) {
				return req = 3;//开锁失败
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return req = 3;//开锁失败
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user);
		map.put("devImei", devImei);
		map.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(unlockTime)));
		while(!result) {
			try {
				//如果开锁时间超过90秒,则返回超时
				if((System.currentTimeMillis() - unlockTime) > (90 * 1000)) {
					req = 2;//开锁超时
					result = true;
					break;
				}
				//查询远程开锁
				MobileSwitch ms = mobileSwitchMapper.queryRemoteUnlock(map);
				if(ms != null) {
					//开锁的时间戳
					long colTime = ms.getColTime().getTime();
					//计算时间差
					long value = colTime - unlockTime;
					//如果新的一条远程开锁记录在90秒之内,说明远程开锁成功
					if(value > 0 && value <= (90 * 1000)) {
						req = 1;//开锁成功
						result = true;
						break;
					}
				}
				Thread.currentThread();
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return req = 3;//开锁失败
			}
		}
		return req;
	}
}
