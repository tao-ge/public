package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.FiberdiscGroupEntity;
import com.ycnet.frms.mapper.FiberdiscGroupEntityMapper;
import com.ycnet.frms.vo.mobile.DiscinfoGroupMobile;
@Repository("fiberdiscGroupEntityMapper")
public class FiberdiscGroupEntityMapperImpl extends BaseSqlSupport implements FiberdiscGroupEntityMapper {

	@Override
	public int deleteByPrimaryKey(Long groupId) {
		return this.delete("com.ycnet.frms.mapper.FiberdiscGroupEntityMapper.deleteByPrimaryKey",groupId);
	}

	@Override
	public int insert(FiberdiscGroupEntity record) {
		return this.insert("com.ycnet.frms.mapper.FiberdiscGroupEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(FiberdiscGroupEntity record) {
		return this.insert("com.ycnet.frms.mapper.FiberdiscGroupEntityMapper.insertSelective",record); 
	}

	@Override
	public FiberdiscGroupEntity selectByPrimaryKey(Long groupId) {
		return this.selectOne("com.ycnet.frms.mapper.FiberdiscGroupEntityMapper.selectByPrimaryKey",groupId);
	}

	@Override
	public int updateByPrimaryKeySelective(FiberdiscGroupEntity record) {
		return this.update("com.ycnet.frms.mapper.FiberdiscGroupEntityMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(FiberdiscGroupEntity record) {
		return this.update("com.ycnet.frms.mapper.FiberdiscGroupEntityMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<?> queryGroupByDev(FiberdiscGroupEntity bean) {
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupEntityMapper.queryGroupByDev",bean);
	}

	/**
	 * 
	* @Title: selectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return FiberdiscGroup    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 下午2:41:54 
	* @version V1.0
	 */
	@Override
	public List<DiscinfoGroupMobile> selectByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.FiberdiscGroupEntityMapper.selectByDevId",devId);
	}

}
