package com.ycnet.frms.mapper.impl;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderUserRole;
import com.ycnet.frms.mapper.WorkorderUserRoleMapper;

@Repository("workorderUserRoleMapper")
public class WorkorderUserRoleMapperImpl extends BaseSqlSupport implements WorkorderUserRoleMapper{

	@Override
	public int deleteByPrimaryKey(Long userRoleId) {
		return this.delete("com.ycnet.frms.mapper.WorkorderUserRoleMapper.deleteByPrimaryKey",userRoleId);
	}

	@Override
	public int insert(WorkorderUserRole record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderUserRoleMapper.insert",record);
	}

	@Override
	public int insertSelective(WorkorderUserRole record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderUserRoleMapper.insertSelective",record);
	}

	@Override
	public WorkorderUserRole selectByPrimaryKey(Long userRoleId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderUserRoleMapper.selectByPrimaryKey",userRoleId);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderUserRole record) {
		return this.update("com.ycnet.frms.mapper.WorkorderUserRoleMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderUserRole record) {
		return this.update("com.ycnet.frms.mapper.WorkorderUserRoleMapper.updateByPrimaryKey",record);
	}

}
  
    