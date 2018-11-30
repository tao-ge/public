package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Investment;
import com.ycnet.frms.mapper.InvestmentMapper;

@Repository("investmentMapper")
public class InvestmentMapperImpl extends BaseSqlSupport implements InvestmentMapper{

	@Override
	public int deleteByPrimaryKey(Long investId) {
		return this.delete("com.ycnet.frms.mapper.InvestmentMapper.deleteByPrimaryKey",investId);
	}

	@Override
	public int insert(Investment record) {
		return this.insert("com.ycnet.frms.mapper.InvestmentMapper.insert",record);
	}

	@Override
	public int insertSelective(Investment record) {
		return this.insert("com.ycnet.frms.mapper.InvestmentMapper.insertSelective",record);
	}

	@Override
	public Investment selectByPrimaryKey(Long investId) {
		return this.selectOne("com.ycnet.frms.mapper.InvestmentMapper.selectByPrimaryKey",investId);
	}

	@Override
	public int updateByPrimaryKeySelective(Investment record) {
		return this.update("com.ycnet.frms.mapper.InvestmentMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(Investment record) {
		return this.update("com.ycnet.frms.mapper.InvestmentMapper.updateByPrimaryKey",record);
	}

	/**
	 *  查询直熔盘信息
	 */
	@Override
	public List<Investment> queryByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.InvestmentMapper.queryByDevId", devId);
	}

}
  
    