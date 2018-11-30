package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.UserRole;
import com.ycnet.frms.mapper.UserRoleMapper;

@Repository("userRoleMapper")
public class UserRoleMapperImpl extends  BaseSqlSupport 
		implements UserRoleMapper{
	
		@Override
		public int insert(UserRole record)
		{
			return this.insert("com.ycnet.frms.mapper.UserRoleMapper.insert",record);
		}
	
		@Override
		public UserRole selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.UserRoleMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.UserRoleMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(UserRole record)
		{
			return this.insert("com.ycnet.frms.mapper.UserRoleMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(UserRole record)
		{
			return this.update("com.ycnet.frms.mapper.UserRoleMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(UserRole record)
		{
			return this.update("com.ycnet.frms.mapper.UserRoleMapper.updateByPrimaryKey",record);
		}

		@Override
		public int deleteByRoleId(Long roleId) {
			
			return this.delete("com.ycnet.frms.mapper.UserRoleMapper.deleteByRoleId", roleId);
		}

		@Override
		public UserRole selectByUserId(Long userId) {
			
			return this.selectOne("com.ycnet.frms.mapper.UserRoleMapper.selectByUserId",userId);
		}

		//导出数据库  刘沧海  2017/10/13
		@Override
		public List<UserRole> queryList() {
			return this.selectList("com.ycnet.frms.mapper.UserRoleMapper.queryList");
		}
}