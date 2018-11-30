package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Pages;
import com.ycnet.frms.mapper.PagesMapper;

@Repository("pagesMapper")
public class PagesMapperImpl extends  BaseSqlSupport 
		implements PagesMapper{
	
		@Override
		public int insert(Pages record)
		{
			return this.insert("com.ycnet.frms.mapper.PagesMapper.insert",record);
		}
	
		@Override
		public Pages selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.PagesMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.PagesMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Pages record)
		{
			return this.insert("com.ycnet.frms.mapper.PagesMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Pages record)
		{
			return this.update("com.ycnet.frms.mapper.PagesMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Pages record)
		{
			return this.update("com.ycnet.frms.mapper.PagesMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Pages> getPageList(Pages record) {
			
			return this.selectList("com.ycnet.frms.mapper.PagesMapper.getPageList",record);
		}
		@Override
		public int deleteByPrimaryKeySelective(Pages record)
		{
			return this.delete("com.ycnet.frms.mapper.PagesMapper.deleteByPrimaryKeySelective",record);
		}
		@Override
		public List<Pages> selectParentPagesByRoleId(Long roleId) {
			
			return this.selectList("com.ycnet.frms.mapper.PagesMapper.selectParentPagesByRoleId",roleId);
		}

		@Override
		public List<Pages> queryByPageName(String pageName) {
			return this.selectList("com.ycnet.frms.mapper.PagesMapper.queryByPageName", pageName);
		}
		
		@Override
		public List<Pages> queryByxjname(long pageId) {
			return this.selectList("com.ycnet.frms.mapper.PagesMapper.queryByxjname", pageId);
		}
		@Override
		public List<Pages> selectByPagesorder(Pages record) {
			
			return this.selectList("com.ycnet.frms.mapper.PagesMapper.selectByPagesorder",record);
		}
		/**
		 * 查询数目 zhouyu 12/28
		 */
		@Override
		public Integer queryCountByConditionMap(Map<String, Object> conditionMap) {
			return this.selectOne("com.ycnet.frms.mapper.PagesMapper.queryCountByConditionMap",conditionMap);
		}
		/**
		 * 查询列表zhouyu 12/28
		 */
		@Override
		public List<Pages> queryByConditionMap(Map<String, Object> conditionMap) {
			return this.selectList("com.ycnet.frms.mapper.PagesMapper.queryByConditionMap",conditionMap);
		}
		/**
		 * 
		* @Title: selectByroles 
		* @Description: 根据roleId查询角色的权限列表 
		* @param @param roleId
		* @param @return    入参
		* @return List<Pages>    返回类型
		* @author 周宇 
		* @throws
		* @date 2018年2月12日 上午10:07:01 
		* @version V1.0
		 */
		@Override
		public List<Pages> selectByroles(Long roleId,Long pageRank) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("roleId", roleId);
			map.put("pageRank",pageRank);
			return this.selectList("com.ycnet.frms.mapper.PagesMapper.selectByroles",map);
		}
		/**
		 * 
		* @Title: getPageListByRole 
		* @Description: 根据roleId查询角色的权限列表 
		* @param @param pages
		* @param @return    入参
		* @return List<Pages>    返回类型
		* @author 周宇 
		* @throws
		* @date 2018年2月12日 上午10:07:01 
		* @version V1.0
		 * @param pageRank 
		 */
		@Override
		public List<Pages> getPageListByRole(Pages pages, Long roleId) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("pages", pages);
			map.put("roleId",roleId);
			return this.selectList("com.ycnet.frms.mapper.PagesMapper.getPageListByRole",map);
		}
		/**
		 * 
		* @Title: getPageListByOrgId 
		* @Description: 根据组织机构查询权限 
		* @param @param pages
		* @param @param orgId
		* @param @return    入参
		* @return List<Pages>    返回类型
		* @author 周宇 
		* @throws
		* @date 2018年2月12日 下午3:48:54 
		* @version V1.0
		 */

		@Override
		public List<Pages> getPageListByOrgId(Pages pages, Long orgId) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("pages", pages);
			map.put("orgId",orgId);
			return this.selectList("com.ycnet.frms.mapper.PagesMapper.getPageListByOrgId",map);
		}
}