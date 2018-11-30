package com.ycnet.frms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.WorkorderUserRole;
import com.ycnet.frms.mapper.WorkorderUserRoleMapper;
import com.ycnet.frms.service.WorkorderUserRoleService;

@Service("workorderUserRoleService")
@Transactional
public class WorkorderUserRoleServiceImpl implements WorkorderUserRoleService {

	@Resource(name="workorderUserRoleMapper")
	private WorkorderUserRoleMapper  workorderUserRoleMapper;

	/**
	 * 
	 * 
	 * @Title: insertSelective
	 * @Description: 添加用户角色关联表
	 * @param @param wUserRole
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午6:09:55
	 * @version V1.0
	 */
	@Override
	public int insertSelective(WorkorderUserRole wUserRole) {
		return workorderUserRoleMapper.insertSelective(wUserRole);
	}

}
