package com.ycnet.frms.mapper.impl;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderOrganizition;
import com.ycnet.frms.mapper.WorkorderOrganizitionMapper;

@Repository("workorderOrganizitionMapper")
public class WorkorderOrganizitionMapperImpl extends BaseSqlSupport implements WorkorderOrganizitionMapper{

	@Override
	public int deleteByPrimaryKey(Long orgId) {
		return this.delete("com.ycnet.frms.mapper.WorkorderOrganizitionMapper.deleteByPrimaryKey",orgId);
	}

	@Override
	public int insert(WorkorderOrganizition record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderOrganizitionMapper.insert",record);
	}

	@Override
	public int insertSelective(WorkorderOrganizition record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderOrganizitionMapper.insertSelective",record);
	}

	@Override
	public WorkorderOrganizition selectByPrimaryKey(Long orgId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderOrganizitionMapper.selectByPrimaryKey",orgId);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderOrganizition record) {
		return this.update("com.ycnet.frms.mapper.WorkorderOrganizitionMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderOrganizition record) {
		return this.update("com.ycnet.frms.mapper.WorkorderOrganizitionMapper.updateByPrimaryKey",record);
	}

}
  
    