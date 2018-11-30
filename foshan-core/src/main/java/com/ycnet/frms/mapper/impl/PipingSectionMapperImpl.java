package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.PipingSectionMapper;
import com.ycnet.frms.vo.PipingSectionBean;

@Repository("pipingSectionMapper")
public class PipingSectionMapperImpl extends BaseSqlSupport implements PipingSectionMapper {

	@Override
	public int insert(PipingSection record)
	{
		return this.insert("com.ycnet.frms.mapper.PipingSectionMapper.insert",record);
	}

	@Override
	public PipingSection selectByPrimaryKey(Long id)
	{
		return this.selectOne("com.ycnet.frms.mapper.PipingSectionMapper.selectByPrimaryKey",id);
	}

	@Override
	public int deleteByPrimaryKey(Long id)
	{
		return this.delete("com.ycnet.frms.mapper.PipingSectionMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insertSelective(PipingSection record)
	{
		return this.insert("com.ycnet.frms.mapper.PipingSectionMapper.insertSelective",record);
	}

	@Override
	public int updateByPrimaryKeySelective(PipingSection record)
	{
		return this.update("com.ycnet.frms.mapper.PipingSectionMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(PipingSection record)
	{
		return this.update("com.ycnet.frms.mapper.PipingSectionMapper.updateByPrimaryKey",record);
	}

	/**
	 * 查询管道段信息
	 */
	@Override
	public List<PipingSection> queryByWellId(Long wellId) {
		return this.selectList("com.ycnet.frms.mapper.PipingSectionMapper.queryByWellId", wellId);
	}

	/**
	 * 查询管道段,分面
	 */
	@Override
	public List<PipingSectionBean> queryAllByWellId(Long wellId) {
		return this.selectList("com.ycnet.frms.mapper.PipingSectionMapper.queryAllByWellId", wellId);
	}
	

	/**
	 * 查询管道段总数 fl 
	 */
	@Override
	public Integer queryCountPipingSections(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.PipingSectionMapper.queryCountPipingSections", map);
	}

	/**
	 * 查询管道段信息 fl 
	 */
	@Override
	public List<PipingSection> queryPipingSections(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.PipingSectionMapper.queryPipingSections", map);
	}

	@Override
	public PipingSectionBean queryByPrimaryKey(Long pipingSectId) {
		return this.selectOne("com.ycnet.frms.mapper.PipingSectionMapper.queryByPrimaryKey", pipingSectId);
	}
}
