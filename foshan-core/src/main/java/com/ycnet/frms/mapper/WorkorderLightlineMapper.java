package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.WorkorderLightline;

public interface WorkorderLightlineMapper {

	int deleteByPrimaryKey(Long id);

    int insert(WorkorderLightline record);

    int insertSelective(WorkorderLightline record);

    WorkorderLightline selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkorderLightline record);

    int updateByPrimaryKey(WorkorderLightline record);
    
	/**
	 * 
	* @Title: queryLineByWorkId 
	* @Description: 查询工单下面的光路
	* @param @param session
	* @param @param workerOrderId
	* @param @param userId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年3月26日 上午10:54:41 
	* @version V1.0
	 */
	List<WorkorderLightline> queryLineByWorkId(Long workorderId);

	/**
	 * 查询是否存在设备端口配置
	* @Title: queryDeviceExist 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param designroutesId
	* @param @return    入参
	* @return WorkorderLightline    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月6日 下午3:15:45 
	* @version V1.0
	 */
	WorkorderLightline queryDeviceExist(Long designroutesId);

}
