package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.WorkorderRoles;


public interface WorkorderRolesMapper {
	
    int deleteByPrimaryKey(Long roleId);

    int insert(WorkorderRoles record);

    int insertSelective(WorkorderRoles record);

    WorkorderRoles selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(WorkorderRoles record);

    int updateByPrimaryKey(WorkorderRoles record);
    
	/**
	 * 
	 * @Title: queryPaidanByOrgId
	 * @Description: 查询该组织机构下是否有施工派单角色
	 * @param @param map
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午4:20:14
	 * @version V1.0
	 */
	WorkorderRoles queryPaidanByOrgId(Map<String, Object> map);
}