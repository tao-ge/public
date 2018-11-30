package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Project;
import com.ycnet.frms.mapper.ProjectMapper;
import com.ycnet.frms.vo.ProjectConditionBean;

@Repository("projectMapper")
public class ProjectMapperImpl extends  BaseSqlSupport 
		implements ProjectMapper{
	
		@Override
		public int insert(Project record)
		{
			return this.insert("com.ycnet.frms.mapper.ProjectMapper.insert",record);
		}
	
		@Override
		public Project selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.ProjectMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.ProjectMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Project record)
		{
			return this.insert("com.ycnet.frms.mapper.ProjectMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Project record)
		{
			return this.update("com.ycnet.frms.mapper.ProjectMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Project record)
		{
			return this.update("com.ycnet.frms.mapper.ProjectMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Project> selectByParam(ProjectConditionBean bean) {
			
			return this.selectList("com.ycnet.frms.mapper.ProjectMapper.selectByParam",bean);
		}

		@Override
		public int queryCountByProjectBean(Map<String, Object>  map) {
			return this.selectOne("com.ycnet.frms.mapper.ProjectMapper.queryCountByProjectBean",map);
		}

		@Override
		public List<Project> queryByProjectBean(Map<String, Object>  map) {
			return this.selectList("com.ycnet.frms.mapper.ProjectMapper.queryByProjectBean",map);
		}

		//导出数据库 刘沧海 2017/10/13
		@Override
		public List<Project> queryList(Long orgId) {
			return this.selectList("com.ycnet.frms.mapper.ProjectMapper.queryList",orgId);
		}
		
		@Override
		public Project selectByProCode(String proCode)
		{
			return this.selectOne("com.ycnet.frms.mapper.ProjectMapper.selectByProCode",proCode);
		}
}