package com.ycnet.frms.service;

import java.util.Date;
import java.util.List;

import com.ycnet.frms.bean.DeviceImage;

public interface DeviceImageService {

	/**
	 * 
	 * @Title: insert
	 * @Description: 添加保存图片信息
	 * @param @param deviceImage
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月27日 下午2:17:46
	 * @version V1.0
	 */
	int insert(DeviceImage deviceImage);

	/**
	 * 
	 * @Title: queryByImei
	 * @Description: 根据IMEI查询图片信息
	 * @param @param devImei
	 * @param @return 
	 * @return List<DeviceImage> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月28日 下午2:56:06
	 * @version V1.0
	 */
	List<DeviceImage> queryByImei(String devImei);

	/**
	 * 
	 * 
	 * @Title: queryByImeiAndColtime
	 * @Description: 根据imei,上报数据类型和上报数据时间查询一条图片信息
	 * @param @param devImei
	 * @param @param oprStyle
	 * @param @param alarmTime
	 * @param @return 
	 * @return DeviceImage 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月14日 上午1:20:29
	 * @version V1.0
	 */
	DeviceImage queryByImeiAndColtime(String devImei, String oprStyle, Date alarmTime);

}
  
    