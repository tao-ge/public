package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.mapper.OrganizitionMapper;

@Repository("organizitionMapper")
public class OrganizitionMapperImpl extends  BaseSqlSupport 
		implements OrganizitionMapper{
	
		@Override
		public int insert(Organizition record)
		{
			return this.insert("com.ycnet.frms.mapper.OrganizitionMapper.insert",record);
		}
	
		@Override
		public Organizition selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.OrganizitionMapper.selectByPrimaryKey",id);
		}
		//管理员的全部重新生成隐藏
		@Override
		public Organizition selectroleid(Long userId)
		{
			return this.selectOne("com.ycnet.frms.mapper.OrganizitionMapper.selectroleid",userId);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.OrganizitionMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Organizition record)
		{
			return this.insert("com.ycnet.frms.mapper.OrganizitionMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Organizition record)
		{
			return this.update("com.ycnet.frms.mapper.OrganizitionMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Organizition record)
		{
			return this.update("com.ycnet.frms.mapper.OrganizitionMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Organizition> getOrganizitionList(Organizition record) {
			
			return this.selectList("com.ycnet.frms.mapper.OrganizitionMapper.getOrganizitionList",record);
		}
		
		@Override
		public List<Organizition> queryByConditionMap(Map<String,Object> map) {
			
			return this.selectList("com.ycnet.frms.mapper.OrganizitionMapper.queryByConditionMap",map);
		}
		
		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {
			
			return this.selectOne("com.ycnet.frms.mapper.OrganizitionMapper.queryCountByConditionMap", map);
		}
		
		@Override
		public List<Organizition> OrgIdList() {
			
			return this.selectList("com.ycnet.frms.mapper.OrganizitionMapper.OrgIdList");
		}
		@Override
		public List<Organizition> queryByConditionMapAndOrgId(Map<String,Object> map) {
			
			return this.selectList("com.ycnet.frms.mapper.OrganizitionMapper.queryByConditionMapAndOrgId",map);
		}
		
		@Override
		public int queryCountByConditionMapAndOrgId(Map<String, Object> map) {
			
			return this.selectOne("com.ycnet.frms.mapper.OrganizitionMapper.queryCountByConditionMapAndOrgId", map);
		}
		/**
		 * 
		* @Title: getorgPlatformList 
		* @Description: 得到当前组织机构下的所有平台类型 
		* @param @param orgId
		* @param @return    入参
		* @return List<String>    返回类型
		* @author 周宇
		* @throws
		* @date 2018年2月11日 下午1:35:36 
		* @version V1.0
		 */
		@Override
		public Organizition getorgPlatformList(Long orgId) {
			return selectOne("com.ycnet.frms.mapper.OrganizitionMapper.selectByPrimaryKey",orgId);
		}
}