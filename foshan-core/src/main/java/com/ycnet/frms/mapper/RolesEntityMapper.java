package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.RolesEntity;

public interface RolesEntityMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(RolesEntity record);

    int insertSelective(RolesEntity record);

    RolesEntity selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(RolesEntity record);

    int updateByPrimaryKey(RolesEntity record);
}