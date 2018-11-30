package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.PageRole;
import com.ycnet.frms.mapper.PageRoleMapper;

@Repository("pageRoleMapper")
public class PageRoleMapperImpl extends  BaseSqlSupport 
		implements PageRoleMapper{
	
		@Override
		public int insert(PageRole record)
		{
			return this.insert("com.ycnet.frms.mapper.PageRoleMapper.insert",record);
		}
	
		@Override
		public PageRole selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.PageRoleMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.PageRoleMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(PageRole record)
		{
			return this.insert("com.ycnet.frms.mapper.PageRoleMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(PageRole record)
		{
			return this.update("com.ycnet.frms.mapper.PageRoleMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(PageRole record)
		{
			return this.update("com.ycnet.frms.mapper.PageRoleMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Long> selectByRoleId(Long roleId) {
			
			return this.selectList("com.ycnet.frms.mapper.PageRoleMapper.selectByRoleId", roleId);
		}

		@Override
		public int deleteByRoleId(Long roleId) {
			
			return this.delete("com.ycnet.frms.mapper.PageRoleMapper.deleteByRoleId", roleId);
		}

		/**
		 * 
		 * @Title: selectByPageIdAndOrgId
		 * @Description: 根据orgId和pageId查询角色功能关联表
		 * @param @param map
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年3月21日 上午11:20:26
		 * @version V1.0
		 */
		@Override
		public List<PageRole> selectByPageIdAndOrgId(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.PageRoleMapper.selectByPageIdAndOrgId", map);
		}
}