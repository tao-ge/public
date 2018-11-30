package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Tmps;
import com.ycnet.frms.mapper.TmpsMapper;
import com.ycnet.frms.vo.TmpsConditionBean;

@Repository("tmpsMapper")
public class TmpsMapperImpl extends  BaseSqlSupport 
		implements TmpsMapper{
	
		@Override
		public int insert(Tmps record)
		{
			return this.insert("com.ycnet.frms.mapper.TmpsMapper.insert",record);
		}
	
		@Override
		public Tmps selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.TmpsMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.TmpsMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Tmps record)
		{
			return this.insert("com.ycnet.frms.mapper.TmpsMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Tmps record)
		{
			return this.update("com.ycnet.frms.mapper.TmpsMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Tmps record)
		{
			return this.update("com.ycnet.frms.mapper.TmpsMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Tmps> queryByConditionBeans(Map<String,Object> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.TmpsMapper.queryByConditionBeans", map);
		}
		
		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.TmpsMapper.queryCountByConditionMap", map);
		}
}