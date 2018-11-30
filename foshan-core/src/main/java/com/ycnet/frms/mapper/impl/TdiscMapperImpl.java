package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Tdisc;
import com.ycnet.frms.mapper.TdiscMapper;

@Repository("tdiscMapper")
public class TdiscMapperImpl extends BaseSqlSupport implements TdiscMapper{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.delete("com.ycnet.frms.mapper.TdiscMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(Tdisc record) {
		return this.insert("com.ycnet.frms.mapper.TdiscMapper.insert",record);
	}

	@Override
	public int insertSelective(Tdisc record) {
		return this.insert("com.ycnet.frms.mapper.TdiscMapper.insertSelective",record);
	}

	@Override
	public Tdisc selectByPrimaryKey(Long id) {
		return this.selectOne("com.ycnet.frms.mapper.TdiscMapper.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(Tdisc record) {
		return this.update("com.ycnet.frms.mapper.TdiscMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(Tdisc record) {
		return this.update("com.ycnet.frms.mapper.TdiscMapper.updateByPrimaryKey",record);
	}

	@Override
	public int deleteByUser(Long userId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		return this.delete("com.ycnet.frms.mapper.TdiscMapper.del",params);
	}

	@Override
	public int deleteByDev(String devCode) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("devCode", devCode);
		return this.delete("com.ycnet.frms.mapper.TdiscMapper.del",params);
	}

	@Override
	public List<Tdisc> findRecords(Long userId,String value) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("value", value);
		return this.selectList("com.ycnet.frms.mapper.TdiscMapper.findRecords",params);
	}
	
	@Override
	public int genPortCode(Long userId,Long orgId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("orgId", orgId);
		int ret = this.update("com.ycnet.frms.mapper.TdiscMapper.genPortCode",userId);
		this.delete("com.ycnet.frms.mapper.TdiscMapper.deleteExistsFGPortCode",params);
		this.insert("com.ycnet.frms.mapper.TdiscMapper.genFGPortCode",params);
		return ret;
	}
}
