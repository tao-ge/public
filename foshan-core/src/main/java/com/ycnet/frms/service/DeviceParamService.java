package com.ycnet.frms.service;


import com.ycnet.frms.bean.DeviceParamEntity;
import com.ycnet.frms.vo.mobile.DeviceParamMobile;

public interface DeviceParamService {

	/**
	 * 根据 orgId,查询中控器参数
	* @Title: querydeviceParamByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return DeviceParamEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月13日 上午10:09:16 
	* @version V1.0
	 */
	DeviceParamMobile querydeviceParamByOrgId(Long orgId);

	/**
	 * 根据 orgId,查询中控器参数
	* @Title: queryDeviceParamInfoByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return DeviceParamEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月29日 上午10:55:32 
	* @version V1.0
	 */
	DeviceParamEntity queryDeviceParamInfoByOrgId(Long orgId);

	/**
	 * 设备参数修改保存
	* @Title: saveDeviceParam 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceParam
	* @param @param orgId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月29日 下午1:21:59 
	* @version V1.0
	 */
	int saveDeviceParam(DeviceParamEntity deviceParam, Long orgId);
	
}
