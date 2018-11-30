package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.FiberdiscEntity;
import com.ycnet.frms.mapper.FiberdiscEntityMapper;
@Repository("fiberdiscEntityMapper")
public class FiberdiscEntityMapperImpl extends BaseSqlSupport implements FiberdiscEntityMapper {

	@Override
	public int deleteByPrimaryKey(Long fiberDiscId) {
		return this.delete("com.ycnet.frms.mapper.FiberdiscEntityMapper.deleteByPrimaryKey",fiberDiscId);
	}

	@Override
	public int insert(FiberdiscEntity record) {
		return this.insert("com.ycnet.frms.mapper.FiberdiscEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(FiberdiscEntity record) {
		return this.insert("com.ycnet.frms.mapper.FiberdiscEntityMapper.insertSelective",record); 
	}

	@Override
	public FiberdiscEntity selectByPrimaryKey(Long fiberDiscId) {
		return this.selectOne("com.ycnet.frms.mapper.FiberdiscEntityMapper.selectByPrimaryKey",fiberDiscId);
	}

	@Override
	public int updateByPrimaryKeySelective(FiberdiscEntity record) {
		return this.update("com.ycnet.frms.mapper.FiberdiscEntityMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(FiberdiscEntity record) {
		return this.update("com.ycnet.frms.mapper.FiberdiscEntityMapper.updateByPrimaryKeyWithBLOBs",record);
	}

	@Override
	public int updateByPrimaryKey(FiberdiscEntity record) {
		return this.update("com.ycnet.frms.mapper.FiberdiscEntityMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<FiberdiscEntity> queryBydev(FiberdiscEntity param) {
		return this.selectList("com.ycnet.frms.mapper.FiberdiscEntityMapper.queryBydev", param);
	}

}
