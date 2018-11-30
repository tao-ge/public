package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Space;
import com.ycnet.frms.mapper.SpaceMapper;

@Repository("spaceMapper")
public class SpaceMapperImpl extends  BaseSqlSupport implements SpaceMapper {

	@Override
	public int insert(Space record)
	{
		return this.insert("com.ycnet.frms.mapper.SpaceMapper.insert",record);
	}

	@Override
	public Space selectByPrimaryKey(Long id)
	{
		return this.selectOne("com.ycnet.frms.mapper.SpaceMapper.selectByPrimaryKey",id);
	}

	@Override
	public int deleteByPrimaryKey(Long id)
	{
		return this.delete("com.ycnet.frms.mapper.SpaceMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insertSelective(Space record)
	{
		return this.insert("com.ycnet.frms.mapper.SpaceMapper.insertSelective",record);
	}
	
	@Override
	public int updateByPrimaryKeySelective(Space record)
	{
		return this.update("com.ycnet.frms.mapper.SpaceMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(Space record)
	{
		return this.update("com.ycnet.frms.mapper.SpaceMapper.updateByPrimaryKey",record);
	}

	/**
	 * 根据井ID查询面
	 */
	@Override
	public List<Space> queryByWellId(Long wellId) {
		return this.selectList("com.ycnet.frms.mapper.SpaceMapper.queryByWellId", wellId);
	}

}
