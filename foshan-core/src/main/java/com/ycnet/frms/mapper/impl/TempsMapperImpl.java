package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Temps;
import com.ycnet.frms.mapper.TempsMapper;


@Repository("tempsMapper")
public class TempsMapperImpl extends  BaseSqlSupport 
		implements TempsMapper{
	
		@Override
		public int insert(Temps record)
		{
			return this.insert("com.ycnet.frms.mapper.TempsMapper.insert",record);
		}
	
		@Override
		public Temps selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.TempsMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.TempsMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Temps record)
		{
			return this.insert("com.ycnet.frms.mapper.TempsMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Temps record)
		{
			return this.update("com.ycnet.frms.mapper.TempsMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Temps record)
		{
			return this.update("com.ycnet.frms.mapper.TempsMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Temps> selectByParam(Long id) {
			
			return this.selectList("com.ycnet.frms.mapper.TempsMapper.selectByParam",id);
		}

		
}