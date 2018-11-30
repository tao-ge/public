package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.WorkorderRoles;
import com.ycnet.frms.mapper.WorkorderRolesMapper;
import com.ycnet.frms.service.WorkorderRolesService;

@Service("workorderRolesService")
@Transactional
public class WorkorderRolesServiceImpl implements WorkorderRolesService{
	
	@Resource(name="workorderRolesMapper")
	private WorkorderRolesMapper workorderRolesMapper;

	/**
	 * 
	 * @Title: queryPaidanByOrgId
	 * @Description: 查询该组织机构下是否有施工派单角色
	 * @param @param orgId
	 * @param @param string
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午4:17:20
	 * @version V1.0
	 */
	@Override
	public WorkorderRoles queryPaidanByOrgId(Long orgId, String actRoles) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("actRoles", actRoles);
		return workorderRolesMapper.queryPaidanByOrgId(map);
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加光调项目角色
	 * @param @param wRole
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午6:22:34
	 * @version V1.0
	 */
	@Override
	public int insertSelective(WorkorderRoles wRole) {
		return workorderRolesMapper.insertSelective(wRole);
	}

	
}
