package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.ResourceData;
import com.ycnet.frms.mapper.ResourceDataMapper;

@Repository("resourceDataMapper")
public class ResourceDataMapperImpl extends BaseSqlSupport implements ResourceDataMapper {

	@Override
	public int deleteByPrimaryKey(Long resId) {
		return this.delete("com.ycnet.frms.mapper.ResourceDataMapper.deleteByPrimaryKey",resId);
	}

	@Override
	public int insert(ResourceData record) {
		return this.insert("com.ycnet.frms.mapper.ResourceDataMapper.insert",record);
	}

	@Override
	public int insertSelective(ResourceData record) {
		return this.insert("com.ycnet.frms.mapper.ResourceDataMapper.insertSelective",record);
	}

	@Override
	public ResourceData selectByPrimaryKey(Long resId) {
		return this.selectOne("com.ycnet.frms.mapper.ResourceDataMapper.selectByPrimaryKey",resId);
	}

	@Override
	public int updateByPrimaryKeySelective(ResourceData record) {
		return this.update("com.ycnet.frms.mapper.ResourceDataMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(ResourceData record) {
		return this.update("com.ycnet.frms.mapper.ResourceDataMapper.updateByPrimaryKey",record);
	}

	/**
	 * 查询资管数据管理数据
	 */
	@Override
	public List<ResourceData> queryResourceDataList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.ResourceDataMapper.queryResourceDataList",map);
	}

	/**
	 * 查询资管数据管理数据数量
	 */
	@Override
	public Integer queryCountResourceDataList(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.ResourceDataMapper.queryCountResourceDataList", map);
	}

	/**
	 * 根据areaCode查询数据
	 */
	@Override
	public List<ResourceData> queryByAreaCode(String areaCode) {
		return this.selectList("com.ycnet.frms.mapper.ResourceDataMapper.queryByAreaCode", areaCode);
	}

}
