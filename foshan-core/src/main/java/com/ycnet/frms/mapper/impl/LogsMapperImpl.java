package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Logs;
import com.ycnet.frms.mapper.LogsMapper;
import com.ycnet.frms.vo.LogsConditionBean;

@Repository("logsMapper")
public class LogsMapperImpl extends  BaseSqlSupport 
		implements LogsMapper{
	
		@Override
		public int insert(Logs record)
		{
			return this.insert("com.ycnet.frms.mapper.LogsMapper.insert",record);
		}
	
		@Override
		public Logs selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.LogsMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.LogsMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Logs record)
		{
			return this.insert("com.ycnet.frms.mapper.LogsMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Logs record)
		{
			return this.update("com.ycnet.frms.mapper.LogsMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Logs record)
		{
			return this.update("com.ycnet.frms.mapper.LogsMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Logs> queryByConditionBeans(Map<String,Object> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.LogsMapper.queryByConditionBeans", map);
		}

		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.LogsMapper.queryCountByConditionMap", map);
		}
}