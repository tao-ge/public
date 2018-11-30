package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.WorkorderUserRole;

public interface WorkorderUserRoleMapper {
    int deleteByPrimaryKey(Long userRoleId);

    int insert(WorkorderUserRole record);

    int insertSelective(WorkorderUserRole record);

    WorkorderUserRole selectByPrimaryKey(Long userRoleId);

    int updateByPrimaryKeySelective(WorkorderUserRole record);

    int updateByPrimaryKey(WorkorderUserRole record);
    
}