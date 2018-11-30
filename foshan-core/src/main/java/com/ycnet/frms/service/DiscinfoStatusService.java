package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.vo.DeviceDiscinfoEntityBean;

public interface DiscinfoStatusService {

	/**
	 * 查询历史上报记录
	* @Title: queryhistoryDetialByDiscId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return List<DiscinfoStatusEntityBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月8日 下午1:45:00 
	* @version V1.0
	 */
	List<DeviceDiscinfoEntityBean> queryhistoryDetialByDiscId(Long discId);

}
