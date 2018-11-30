package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.SiteCode;
import com.ycnet.frms.mapper.SiteCodeMapper;

@Repository("siteCodeMapper")
public class SiteCodeMapperImpl extends  BaseSqlSupport 
		implements SiteCodeMapper{
	
		@Override
		public int insert(SiteCode record)
		{
			return this.insert("com.ycnet.frms.mapper.SiteCodeMapper.insert",record);
		}
	
		@Override
		public SiteCode selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.SiteCodeMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.SiteCodeMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(SiteCode record)
		{
			return this.insert("com.ycnet.frms.mapper.SiteCodeMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(SiteCode record)
		{
			return this.update("com.ycnet.frms.mapper.SiteCodeMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(SiteCode record)
		{
			return this.update("com.ycnet.frms.mapper.SiteCodeMapper.updateByPrimaryKey",record);
		}

		@Override
		public SiteCode selectSiteCode() {
			return this.selectOne("com.ycnet.frms.mapper.SiteCodeMapper.selectSiteCode");
		}

		//导出数据库  刘沧海 2017/10/13
		@Override
		public List<SiteCode> queryList() {
			return this.selectList("com.ycnet.frms.mapper.SiteCodeMapper.queryList");
		}
		
		
}