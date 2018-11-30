package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.DeviceImage;
import com.ycnet.frms.mapper.DeviceImageMapper;
import com.ycnet.frms.service.DeviceImageService;

@Service("deviceImageService")
public class DeviceImageServiceImpl implements DeviceImageService{

	@Resource(name="deviceImageMapper")
	private DeviceImageMapper deviceImageMapper;
	
	@Override
	public int insert(DeviceImage deviceImage) {
		return deviceImageMapper.insertSelective(deviceImage);
	}

	/**
	 * 
	 * @Title: queryByImei
	 * @Description: 根据IMEI查询图片信息
	 * @param @param devImei
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月28日 下午2:56:38
	 * @version V1.0
	 */
	@Override
	public List<DeviceImage> queryByImei(String devImei) {
		return deviceImageMapper.queryByImei(devImei);
	}

	/**
	 * 
	 * @Title: queryByImeiAndColtime
	 * @Description: 根据imei,上报数据类型和上报数据时间查询一条图片信息
	 * @param @param devImei
	 * @param @param oprStyle
	 * @param @param alarmTime
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月14日 上午1:22:21
	 * @version V1.0
	 */
	@Override
	public DeviceImage queryByImeiAndColtime(String devImei, String oprStyle, Date alarmTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devImei", devImei);
		map.put("oprStyle", oprStyle);
		map.put("alarmTime", alarmTime);
		return deviceImageMapper.queryByImeiAndColtime(map);
	}
	

}
  
    