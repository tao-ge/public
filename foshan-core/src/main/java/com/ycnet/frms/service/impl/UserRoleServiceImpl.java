package com.ycnet.frms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.UserRole;
import com.ycnet.frms.mapper.UserRoleMapper;
import com.ycnet.frms.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Resource(name="userRoleMapper")
	private UserRoleMapper userRoleMapper;

	@Override
	public int deleteByPrimaryKey(Long userRoleId) {
		if(userRoleId != null){
			return userRoleMapper.deleteByPrimaryKey(userRoleId);
		}
		return 0;
	}
	
	@Override
	public int deleteByRoleId(Long roleId) {
		if(roleId != null){
			return userRoleMapper.deleteByRoleId(roleId);
		}
		return 0;
	}

	@Override
	public int insert(UserRole record) {
		if(record != null){
			return userRoleMapper.insert(record);
		}
		return 0;
	}

	@Override
	public UserRole selectByUserId(Long userId) {
		if(userId != null){
			return userRoleMapper.selectByUserId(userId);
		}
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(UserRole record) {
		if(record != null){
			return userRoleMapper.updateByPrimaryKeySelective(record);
		}
		return 0;
	}

	//导出数据库  刘沧海  2017/10/13
	@Override
	public List<UserRole> queryList() {
		return userRoleMapper.queryList();
	}
}
