package com.ycnet.frms.service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.vo.DeviceLockStatusEntityVo;

public interface DeviceLockStatusEntityService {

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
	PageBean queryByConditionBean(DeviceLockStatusEntityVo devStaCon, PageBean pb);

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
	PageBean queryDeviceLockStatustByConditions(Long devId, PageBean pb, Long orgId);

}
