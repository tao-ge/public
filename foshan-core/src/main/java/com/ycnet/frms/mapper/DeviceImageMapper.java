package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.DeviceImage;

public interface DeviceImageMapper {
    int deleteByPrimaryKey(Long imageId);

    int insert(DeviceImage record);

    int insertSelective(DeviceImage record);

    DeviceImage selectByPrimaryKey(Long imageId);

    int updateByPrimaryKeySelective(DeviceImage record);

    int updateByPrimaryKey(DeviceImage record);

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
     * @date 2017年12月28日 下午2:57:08
     * @version V1.0
     */
	List<DeviceImage> queryByImei(String devImei);

	/**
	 * 
	 * @Title: queryByImeiAndColtime
	 * @Description: 根据imei,上报数据类型和上报数据时间查询一条图片信息
	 * @param @param map
	 * @param @return 
	 * @return DeviceImage 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月14日 上午1:23:56
	 * @version V1.0
	 */
	DeviceImage queryByImeiAndColtime(Map<String, Object> map);
}