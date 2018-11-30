package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.UserRole;

public interface UserRoleService {
	
	int deleteByPrimaryKey(Long userRoleId);

	int deleteByRoleId(Long roleId);
	
	int insert(UserRole record);
	
	UserRole selectByUserId(Long userId);
	
	int updateByPrimaryKeySelective(UserRole record);

	//导出数据库  刘沧海  2017/10/13
	List<UserRole> queryList();
}
