package com.ycnet.frms.service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.vo.DeviceLockSwitchVo;

public interface DeviceLockSwitchService {

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
	PageBean queryLockSwitchList(PageBean pb, DeviceLockSwitchVo switchVo);

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
	PageBean queryimDeviceLockSwitchByCondition(Long devId, PageBean pb, Long orgId);

}
