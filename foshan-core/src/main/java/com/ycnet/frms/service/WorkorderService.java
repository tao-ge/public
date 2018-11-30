package com.ycnet.frms.service;

import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.NewBacklogBean;
import com.ycnet.frms.vo.WorkorderBean;

public interface WorkorderService {

	/**
	 * 
	* @Title: queryFlowOrderUser 
	* @Description: 根据当前用户的流程角色查询工单 
	* @param @param request
	* @param @param session
	* @param @param userId
	* @param @param orderNumber
	* @param @param ordeTitle
	* @param @param devName
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年3月26日 上午9:19:28 
	* @version V1.0
	 * @param state 
	 */
//	List<NewBacklogBean> queryFlowOrderUser(Users user, String orderNumber, String orderTitle, String devName,
//			PageBean pb, Integer state);
	
	public List<Map<String, Object>> queryFlowOrderUser(Users user, String orderNumber, String orderTitle,
			PageBean pb,Integer state);

	/**
	 * 
	* @Title: queryByWorkorderId 
	* @Description: 根据工单ID查询工单详情 
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
	WorkorderBean queryByWorkorderId(Long workorderId);


}
