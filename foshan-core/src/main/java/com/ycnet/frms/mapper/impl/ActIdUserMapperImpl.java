package com.ycnet.frms.mapper.impl;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.ActIdUser;
import com.ycnet.frms.mapper.ActIdUserMapper;

@Repository("actIdUserMapper")
public class ActIdUserMapperImpl extends BaseSqlSupport implements ActIdUserMapper{

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.delete("com.ycnet.frms.mapper.ActIdUserMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(ActIdUser record) {
		return this.insert("com.ycnet.frms.mapper.ActIdUserMapper.insert",record);
	}

	@Override
	public int insertSelective(ActIdUser record) {
		return this.insert("com.ycnet.frms.mapper.ActIdUserMapper.insertSelective",record); 
	}

	@Override
	public ActIdUser selectByPrimaryKey(String id) {
		return this.selectOne("com.ycnet.frms.mapper.ActIdUserMapper.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(ActIdUser record) {
		return this.update("com.ycnet.frms.mapper.ActIdUserMapper.updateByPrimaryKeySelective",record); 
	}

	@Override
	public int updateByPrimaryKey(ActIdUser record) {
		return this.update("com.ycnet.frms.mapper.ActIdUserMapper.updateByPrimaryKey",record);
	}

}
  
    