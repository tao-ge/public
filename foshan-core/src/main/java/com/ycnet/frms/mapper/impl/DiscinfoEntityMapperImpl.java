package com.ycnet.frms.mapper.impl;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.DiscinfoEntity;
import com.ycnet.frms.mapper.DiscinfoEntityMapper;

@Repository("discinfoEntityMapper")
public class DiscinfoEntityMapperImpl extends BaseSqlSupport implements DiscinfoEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long discId) {
		return this.delete("com.ycnet.frms.mapper.DiscinfoEntityMapper.deleteByPrimaryKey",discId);
	}

	@Override
	public int insert(DiscinfoEntity record) {
		return this.insert("com.ycnet.frms.mapper.DiscinfoEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(DiscinfoEntity record) {
		return this.insert("com.ycnet.frms.mapper.DiscinfoEntityMapper.insertSelective",record); 
	}

	@Override
	public DiscinfoEntity selectByPrimaryKey(Long discId) {
		return this.selectOne("com.ycnet.frms.mapper.DiscinfoEntityMapper.selectByPrimaryKey",discId);
	}

	@Override
	public int updateByPrimaryKeySelective(DiscinfoEntity record) {
		return this.update("com.ycnet.frms.mapper.DiscinfoEntityMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(DiscinfoEntity record) {
		return this.update("com.ycnet.frms.mapper.DiscinfoEntityMapper.updateByPrimaryKey",record);
	}

	@Override
	public DiscinfoEntity selectByDiscCode(String discCode) {
		return this.selectOne("com.ycnet.frms.mapper.DiscinfoEntityMapper.selectByDiscCode",discCode);
	}

}
  
    