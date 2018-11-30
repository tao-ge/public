package com.ycnet.frms.service;

import com.ycnet.frms.bean.WorkorderRoles;

public interface WorkorderRolesService {

	/**
	 * 
	 * @Title: queryPaidanByOrgId
	 * @Description: 查询该组织机构下是否有施工派单角色
	 * @param @param orgId
	 * @param @param string
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午4:15:58
	 * @version V1.0
	 */
	WorkorderRoles queryPaidanByOrgId(Long orgId, String actRoles);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加光调项目角色
	 * @param @param wRole
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午6:21:55
	 * @version V1.0
	 */
	int insertSelective(WorkorderRoles wRole);
	
}
