package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderRoles;
import com.ycnet.frms.mapper.WorkorderRolesMapper;

@Repository("workorderRolesMapper")
public class WorkorderRolesMapperImpl extends BaseSqlSupport implements WorkorderRolesMapper{

	@Override
	public int deleteByPrimaryKey(Long roleId) {
		return this.delete("com.ycnet.frms.mapper.WorkorderRolesMapper.deleteByPrimaryKey",roleId);
	}

	@Override
	public int insert(WorkorderRoles record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderRolesMapper.insert",record);
	}

	@Override
	public int insertSelective(WorkorderRoles record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderRolesMapper.insertSelective",record);
	}

	@Override
	public WorkorderRoles selectByPrimaryKey(Long roleId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderRolesMapper.selectByPrimaryKey",roleId);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderRoles record) {
		return this.update("com.ycnet.frms.mapper.WorkorderRolesMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderRoles record) {
		return this.update("com.ycnet.frms.mapper.WorkorderRolesMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	 * @Title: queryPaidanByOrgId
	 * @Description: 查询该组织机构下是否有施工派单角色
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午4:20:41
	 * @version V1.0
	 */
	@Override
	public WorkorderRoles queryPaidanByOrgId(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderRolesMapper.queryPaidanByOrgId", map);
	}

}
  
    