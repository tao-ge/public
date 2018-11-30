package com.ycnet.frms.mapper.impl;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.RouteNode;
import com.ycnet.frms.mapper.RouteNodeMapper;

@Repository("routeNodeMapper")
public class RouteNodeMapperImpl extends BaseSqlSupport implements RouteNodeMapper {

	@Override
	public int deleteByPrimaryKey(Long nodeId) {
		return this.delete("com.ycnet.frms.mapper.RouteNodeMapper.deleteByPrimaryKey",nodeId);
	}

	@Override
	public int insert(RouteNode record) {
		return this.insert("com.ycnet.frms.mapper.RouteNodeMapper.insert",record);
	}

	@Override
	public int insertSelective(RouteNode record) {
		return this.insert("com.ycnet.frms.mapper.RouteNodeMapper.insertSelective",record);
	}

	@Override
	public RouteNode selectByPrimaryKey(Long nodeId) {
		return this.selectOne("com.ycnet.frms.mapper.RouteNodeMapper.selectByPrimaryKey",nodeId);
	}

	@Override
	public int updateByPrimaryKeySelective(RouteNode record) {
		return this.update("com.ycnet.frms.mapper.RouteNodeMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(RouteNode record) {
		return this.update("com.ycnet.frms.mapper.RouteNodeMapper.updateByPrimaryKey",record);
	}

	@Override
	public int deleteByRoutePrimaryKey(Long routeId) {
		return this.delete("com.ycnet.frms.mapper.RouteNodeMapper.deleteByRoutePrimaryKey",routeId);
	}

}
