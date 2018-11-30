package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.WorkorderOrganizition;

public interface WorkorderOrganizitionMapper {
	
    int deleteByPrimaryKey(Long orgId);

    int insert(WorkorderOrganizition record);

    int insertSelective(WorkorderOrganizition record);

    WorkorderOrganizition selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(WorkorderOrganizition record);

    int updateByPrimaryKey(WorkorderOrganizition record);
}