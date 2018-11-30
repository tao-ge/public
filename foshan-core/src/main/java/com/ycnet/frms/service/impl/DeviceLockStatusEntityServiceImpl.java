package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.mapper.DeviceLockStatusEntityMapper;
import com.ycnet.frms.service.DeviceLockStatusEntityService;
import com.ycnet.frms.vo.DeviceLockStatusEntityVo;
@Service("deviceLockStatusEntityService")
public class DeviceLockStatusEntityServiceImpl implements DeviceLockStatusEntityService {

	@Resource(name="deviceLockStatusEntityMapper")
	private DeviceLockStatusEntityMapper deviceLockStatusEntityMapper;
	
	/**
	 * 
	* @Title: queryByConditionBean 
	* @Description:监控数据 
	* @param @param devStaCon
	* @param @param pb
	* @param @return    
	* @return PageBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月16日 下午1:48:49 
	* @version V1.0
	 */
	@Override
	public PageBean queryByConditionBean(DeviceLockStatusEntityVo devStaCon, PageBean pb) {
		
		pb.setRows(deviceLockStatusEntityMapper.queryByDeviceStatusCount(devStaCon,pb));
		pb.setList(deviceLockStatusEntityMapper.queryByDeviceStatusList(devStaCon,pb));
		return pb;
	}

	/**
	 * 定时上报记录查询
	* @Title: queryDeviceLockStatustByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月21日 下午1:56:27 
	* @version V1.0
	 */
	@Override
	public PageBean queryDeviceLockStatustByConditions(Long devId, PageBean pb, Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("orgId", orgId);
		map.put("pb", pb);
		pb.setRows(deviceLockStatusEntityMapper. queryDeviceLockStatustCount(map));
		pb.setList(deviceLockStatusEntityMapper.queryDeviceLockStatustList(map));
		return pb;
	}

}
