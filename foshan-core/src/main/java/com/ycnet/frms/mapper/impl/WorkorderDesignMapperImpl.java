package com.ycnet.frms.mapper.impl;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderDesign;
import com.ycnet.frms.mapper.WorkorderDesignMapper;
@Repository("workorderDesignMapper")
public class WorkorderDesignMapperImpl extends BaseSqlSupport implements WorkorderDesignMapper {

	@Override
	public int insert(WorkorderDesign record)
	{
		return this.insert("com.ycnet.frms.mapper.WorkorderDesignMapper.insert",record);
	}

	@Override
	public WorkorderDesign selectByPrimaryKey(Long id)
	{
		return this.selectOne("com.ycnet.frms.mapper.WorkorderDesignMapper.selectByPrimaryKey",id);
	}

	@Override
	public int deleteByPrimaryKey(Long id)
	{
		return this.delete("com.ycnet.frms.mapper.WorkorderDesignMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insertSelective(WorkorderDesign record)
	{
		return this.insert("com.ycnet.frms.mapper.WorkorderDesignMapper.insertSelective",record);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderDesign record)
	{
		return this.update("com.ycnet.frms.mapper.WorkorderDesignMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderDesign record)
	{
		return this.update("com.ycnet.frms.mapper.WorkorderDesignMapper.updateByPrimaryKey",record);
	}

}
