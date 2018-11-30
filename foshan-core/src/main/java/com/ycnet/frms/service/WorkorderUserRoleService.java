package com.ycnet.frms.service;

import com.ycnet.frms.bean.WorkorderUserRole;

public interface WorkorderUserRoleService {

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加用户角色关联表
	 * @param @param wUserRole
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午6:09:28
	 * @version V1.0
	 */
	int insertSelective(WorkorderUserRole wUserRole);
	
}
