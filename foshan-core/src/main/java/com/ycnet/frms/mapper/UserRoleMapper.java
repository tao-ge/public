package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long userRoleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long userRoleId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    int deleteByRoleId(Long roleId);
    
    UserRole selectByUserId(Long userId);

    //导出数据库  刘沧海  2017/10/13
	List<UserRole> queryList();
}