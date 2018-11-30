package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.WorkorderMapper;
import com.ycnet.frms.vo.NewBacklogBean;
import com.ycnet.frms.vo.WorkorderBean;
@Repository("workorderMapper")
public class WorkorderMapperImpl extends BaseSqlSupport implements WorkorderMapper {

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
	@Override
	public List<Map<String, Object>> queryFlowOrderUser(String orderNumber, String orderTitle, 
			String usercode, PageBean pb) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		if(StringUtils.isNotBlank(orderNumber)) {
			map.put("orderNumber", orderNumber);
		}
		if(StringUtils.isNotBlank(orderTitle)) {
			map.put("orderTitle", orderTitle);
		}
		if(StringUtils.isNotBlank(usercode)) {
			map.put("usercode", usercode);
		}
		map.put("pb", pb);
		return this.selectList("com.ycnet.frms.mapper.WorkorderMapper.queryFlowOrderUser", map);
	}

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
	@Override
	public List<Map<String, Object>> queryMobileHandling(String orderNumber, String ordeTitle, 
			String usercode, PageBean pb) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("orderNumber", orderNumber);
		map.put("ordeTitle", ordeTitle);
		map.put("usercode", usercode);
		map.put("pb", pb);
		return this.selectList("com.ycnet.frms.mapper.WorkorderMapper.queryMobileHandling", map);
	}

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
	@Override
	public WorkorderBean queryByWorkorderId(Long workorderId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderMapper.queryByWorkorderId", workorderId);
	}

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
	@Override
	public WorkorderBean queryBydesignId(Long designId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderMapper.queryBydesignId", designId);
	}

}
