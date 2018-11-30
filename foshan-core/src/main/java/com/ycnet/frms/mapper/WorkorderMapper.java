package com.ycnet.frms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.NewBacklogBean;
import com.ycnet.frms.vo.WorkorderBean;

public interface WorkorderMapper {

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
	 */
	public List<Map<String, Object>> queryFlowOrderUser(String orderNumber, String orderTitle, 
			String usercode, PageBean pb);

	/**
	 * 
	* @Title: queryMobileHandling 
	* @Description: 查询我的经办 
	* @param @param map
	* @param @return    
	* @return List<NewBacklogBean>
	* @author liucanghai 
	* @throws
	* @date 2018年4月3日 下午3:06:17 
	* @version V1.0
	 */

	public List<Map<String, Object>> queryMobileHandling(String orderNumber, String ordeTitle, 
			String usercode, PageBean pb);

//	List<NewBacklogBean> queryMobileHandling(String orderNumber, String ordeTitle, String devName, Long orgId,
//			PageBean pb);
//
//	List<NewBacklogBean> queryMobileHandling(String orderNumber, String ordeTitle, String devName, Users user,
//			PageBean pb);

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

	/**
	 * 
	* @Title: queryBydesignId 
	* @Description: 根据工单ID查询调度工单 
	* @param @param designId
	* @param @return    
	* @return WorkorderBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月20日 下午2:41:20 
	* @version V1.0
	 */
	WorkorderBean queryBydesignId(Long designId);


}
