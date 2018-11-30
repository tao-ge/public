package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.PageRole;

public interface PageRoleMapper {
    int deleteByPrimaryKey(Long pageRoleId);

    int insert(PageRole record);

    int insertSelective(PageRole record);

    PageRole selectByPrimaryKey(Long pageRoleId);

    int updateByPrimaryKeySelective(PageRole record);

    int updateByPrimaryKey(PageRole record);
    
    List<Long> selectByRoleId(Long roleId);
    
    int deleteByRoleId(Long roleId);

    /**
     * 
     * @Title: selectByPageIdAndOrgId
     * @Description: 根据orgId和pageId查询角色功能关联表
     * @param @return 
     * @return PageRole 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年3月21日 上午11:19:17
     * @version V1.0
     * @param map 
     */
	List<PageRole> selectByPageIdAndOrgId(Map<String, Object> map);
}