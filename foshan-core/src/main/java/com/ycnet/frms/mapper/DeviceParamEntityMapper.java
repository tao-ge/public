package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.DeviceParamEntity;
import com.ycnet.frms.vo.mobile.DeviceParamMobile;

public interface DeviceParamEntityMapper {
    int deleteByPrimaryKey(Long paramId);

    int insert(DeviceParamEntity record);

    int insertSelective(DeviceParamEntity record);

    DeviceParamEntity selectByPrimaryKey(Long paramId);

    int updateByPrimaryKeySelective(DeviceParamEntity record);

    int updateByPrimaryKey(DeviceParamEntity record);

    /**
     * 根据 orgId,查询中控器参数
    * @Title: querydeviceParamByOrgId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param orgId
    * @param @return    入参
    * @return DeviceParamEntity    返回类型
    * @author FL（作者） 
    * @throws
    * @date 2018年4月13日 上午10:11:46 
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
}