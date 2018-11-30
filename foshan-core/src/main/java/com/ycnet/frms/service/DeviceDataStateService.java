package com.ycnet.frms.service;

import java.util.Date;
import java.util.List;

import com.ycnet.frms.vo.DifferentPortsBean;

import com.ycnet.core.util.PageBean;

public interface DeviceDataStateService {

	/**
	 * 
	 * @Title: saveDeviceDataState
	 * @Description: 添加保存数据
	 * @param @param listByArea
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 下午1:38:43
	 * @version V1.0
	 * @param listDev 
	 * @param orgId 
	 * @param date 
	 */
	int saveDeviceDataState(List<DifferentPortsBean> listByArea, List<DifferentPortsBean> listDev, Long orgId, Date date);

	/**
	 * 端子差异数据统计地区
	* @Title: queryByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月26日 上午9:24:20 
	* @version V1.0
	 */
	PageBean queryByOrgId(Long orgId, PageBean pb);

	/**
	 * 端子差异数据统计业务点
	* @Title: queryDifferentTotals 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @param differentPortsBean
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月26日 上午11:43:00 
	* @version V1.0
	 */
	PageBean queryDifferentTotals(Long orgId, PageBean pb, DifferentPortsBean differentPortsBean);

}
  
    