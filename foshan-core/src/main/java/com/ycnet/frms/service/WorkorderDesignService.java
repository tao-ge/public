package com.ycnet.frms.service;

import com.ycnet.frms.vo.WorkorderBean;
import com.ycnet.frms.vo.WorkorderFiberdiscabindVo;

public interface WorkorderDesignService {

	/**
	 * 
	* @Title: queryBydesignId 
	* @Description: 根据调度工单ID查询调度工单详细 
	* @param @param session
	* @param @param designId
	* @param @param userId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年3月26日 上午10:59:03 
	* @version V1.0
	 */
	WorkorderBean queryBydesignId(Long designId);

	/**
	 * 
	* @Title: queryFiberHandle 
	* @Description: 查询返回端子信息 
	* @param @param session
	* @param @param userId
	* @param @param designId
	* @param @param devId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月8日 上午9:55:37 
	* @version V1.0
	 */
	WorkorderFiberdiscabindVo queryFiberHandle(Long designroutesId, Long devId);

}
