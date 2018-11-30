package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.ResourceDataLog;
import com.ycnet.frms.mapper.ResourceDataLogMapper;

@Repository("resourceDataLogMapper")
public class ResourceDataLogMapperImpl extends BaseSqlSupport implements ResourceDataLogMapper{

	@Override
	public int deleteByPrimaryKey(Long resLogId) {
		return this.delete("com.ycnet.frms.mapper.ResourceDataLogMapper.deleteByPrimaryKey",resLogId);
	}

	@Override
	public int insert(ResourceDataLog record) {
		return this.insert("com.ycnet.frms.mapper.ResourceDataLogMapper.insert",record);
	}

	@Override
	public int insertSelective(ResourceDataLog record) {
		return this.insert("com.ycnet.frms.mapper.ResourceDataLogMapper.insertSelective",record); 
	}

	@Override
	public ResourceDataLog selectByPrimaryKey(Long resLogId) {
		return this.selectOne("com.ycnet.frms.mapper.ResourceDataLogMapper.selectByPrimaryKey",resLogId);
	}

	@Override
	public int updateByPrimaryKeySelective(ResourceDataLog record) {
		return this.update("com.ycnet.frms.mapper.ResourceDataLogMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(ResourceDataLog record) {
		return this.update("com.ycnet.frms.mapper.ResourceDataLogMapper.updateByPrimaryKey",record);
	}

	/**
	 * 查询资管校准日志列表
	 */
	@Override
	public List<ResourceDataLog> queryByConditionBeans(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.ResourceDataLogMapper.queryByConditionBeans", map);
	}

	/**
	 * 查询资管校准日志列表数量
	 */
	@Override
	public int queryCountByConditionMap(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.ResourceDataLogMapper.queryCountByConditionMap", map);
	}


}
  
    