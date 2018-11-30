package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Tjumper;
import com.ycnet.frms.mapper.TjumperMapper;

@Repository("tjumperMapper")
public class TjumperMapperImpl extends BaseSqlSupport implements TjumperMapper{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.delete("com.ycnet.frms.mapper.TjumperMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(Tjumper record) {
		return this.insert("com.ycnet.frms.mapper.TjumperMapper.insert",record);
	}

	@Override
	public int insertSelective(Tjumper record) {
		return this.insert("com.ycnet.frms.mapper.TjumperMapper.insertSelective",record);
	}

	@Override
	public Tjumper selectByPrimaryKey(Long id) {
		return this.selectOne("com.ycnet.frms.mapper.TjumperMapper.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(Tjumper record) {
		return this.update("com.ycnet.frms.mapper.TjumperMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(Tjumper record) {
		return this.update("com.ycnet.frms.mapper.TjumperMapper.updateByPrimaryKey",record);
	}

	@Override
	public int deleteByUser(Long userId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		return this.delete("com.ycnet.frms.mapper.TjumperMapper.del",params);
	}

	@Override
	public int deleteByDev(String devCode) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("devCode", devCode);
		return this.delete("com.ycnet.frms.mapper.TjumperMapper.del",params);
	}

	@Override
	public List<Tjumper> findRecords(Long userId,String value) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("value", value);
		return this.selectList("com.ycnet.frms.mapper.TjumperMapper.findRecords",params);
	}
}
