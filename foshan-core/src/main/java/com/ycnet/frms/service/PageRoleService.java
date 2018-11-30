package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.PageRole;

public interface PageRoleService {

	int insert(PageRole pageRole);
	
	List<Long> selectByRoleId(Long roleId);
	
	int deleteByRoleId(Long roleId);

	/**
	 * 
	 * @Title: deleteByPageIdAndOrgId
	 * @Description: 删除组织结构下，所有用户的某个功能
	 * @param @param orgId
	 * @param @param parseLong
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月21日 上午11:04:10
	 * @version V1.0
	 */
	int deleteByPageIdAndOrgId(Long orgId, Long pageId);
}
