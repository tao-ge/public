package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Roles;
import com.ycnet.frms.mapper.RolesMapper;

@Repository("rolesMapper")
public class RolesMapperImpl extends  BaseSqlSupport 
		implements RolesMapper{
	
		@Override
		public int insert(Roles record)
		{
			return this.insert("com.ycnet.frms.mapper.RolesMapper.insert",record);
		}
	
		@Override
		public Roles selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.RolesMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.RolesMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Roles record)
		{
			return this.insert("com.ycnet.frms.mapper.RolesMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Roles record)
		{
			return this.update("com.ycnet.frms.mapper.RolesMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Roles record)
		{
			return this.update("com.ycnet.frms.mapper.RolesMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Roles> queryRolesList() {
			System.out.println(this.count("com.ycnet.frms.mapper.RolesMapper.queryRolesList",null));
			return this.selectList("com.ycnet.frms.mapper.RolesMapper.queryRolesList");
		}

		@Override
		public long getRoleId() {
			return this.selectOne("com.ycnet.frms.mapper.RolesMapper.getRoleId");
		}

		@Override
		public List<Roles> queryRoles() {
			return this.selectList("com.ycnet.frms.mapper.RolesMapper.queryRoles");
		}

		@Override
		public List<Roles> checkRoleName(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.RolesMapper.checkRoleName",map);
		}

		@Override
		public List<Roles> queryRolesAndUser(String roleName) {
			return this.selectList("com.ycnet.frms.mapper.RolesMapper.queryRolesAndUser",roleName);
		}

		//导出数据库  刘沧海 2017/10/13
		@Override
		public List<Roles> queryList() {
			return this.selectList("com.ycnet.frms.mapper.RolesMapper.queryList");
		}
		/**
		 * 
		* @Title: queryRolesByOrgId 
		* @Description: 根据组织机构查询角色列表
		* @param @param orgId
		* @param @return    入参
		* @return List<Roles>    返回类型
		* @author周宇 
		* @throws
		* @date 2018年2月11日 上午10:56:11 
		* @version V1.0
		 */
		@Override
		public List<Roles> queryRolesByOrgId(Long orgId) {
			return this.selectList("com.ycnet.frms.mapper.RolesMapper.queryRolesByOrgId",orgId);
		}

		/**
		 * 
		 * @Title: queryIsAdmin
		 * @Description: 查询该用户是否为管理员角色
		 * @param @param userId
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年3月17日 下午12:34:55
		 * @version V1.0
		 */
		@Override
		public int queryIsAdmin(Long userId) {
			return this.selectOne("com.ycnet.frms.mapper.RolesMapper.queryIsAdmin",userId);
		}
}