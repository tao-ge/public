package com.ycnet.frms.service.impl;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.mapper.DeviceLockSwitchMapper;
import com.ycnet.frms.service.DeviceLockSwitchService;
import com.ycnet.frms.vo.DeviceLockSwitchVo;
@Service("deviceLockSwitchService")
public class DeviceLockSwitchServiceImpl implements DeviceLockSwitchService {

	@Resource(name="deviceLockSwitchMapper")
	private DeviceLockSwitchMapper deviceLockSwitchMapper;
	
	/**
	 * 
	* @Title: queryLockSwitchList 
	* @Description: 开关锁记录查询 
	* @param @param pb
	* @param @param switchVo
	* @param @return    
	* @return PageBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月17日 下午3:16:57 
	* @version V1.0
	 */
	@Override
	public PageBean queryLockSwitchList(PageBean pb, DeviceLockSwitchVo switchVo) {
		pb.setList(deviceLockSwitchMapper.queryLockSwitchList(switchVo,pb));
		pb.setRows(deviceLockSwitchMapper.queryLockSwitchCount(switchVo,pb));
		return pb;
	}

	/**
	 * 下方表格,开关锁记录查询
	* @Title: queryimDeviceLockSwitchByCondition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月21日 下午3:03:40 
	* @version V1.0
	 */
	@Override
	public PageBean queryimDeviceLockSwitchByCondition(Long devId, PageBean pb, Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("orgId", orgId);
		map.put("pb", pb);
		pb.setList(deviceLockSwitchMapper.queryimDeviceLockSwitchList(map));
		pb.setRows(deviceLockSwitchMapper.queryimDeviceLockSwitchCount(map));
		return pb;
	}

}
