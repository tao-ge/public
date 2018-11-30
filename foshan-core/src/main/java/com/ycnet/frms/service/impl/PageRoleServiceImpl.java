package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.PageRole;
import com.ycnet.frms.mapper.PageRoleMapper;
import com.ycnet.frms.service.PageRoleService;

@Service("pageRoleService")
public class PageRoleServiceImpl implements PageRoleService{

	@Resource(name="pageRoleMapper")
	private PageRoleMapper pageRoleMapper;
	@Override
	public int insert(PageRole record) {
		if(record != null){
			return pageRoleMapper.insert(record);
		}
		return 0;
	}
	@Override
	public List<Long> selectByRoleId(Long roleId) {
		if(roleId != null){
			return pageRoleMapper.selectByRoleId(roleId);
		}
		return null;
	}
	@Override
	public int deleteByRoleId(Long roleId) {
		if(roleId != null){
			return pageRoleMapper.deleteByRoleId(roleId);
		}
		return 0;
	}
	
	/**
	 * 
	 * @Title: deleteByPageIdAndOrgId
	 * @Description: 删除组织结构下，所有用户的某个功能
	 * @param @param orgId
	 * @param @param parseLong
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月21日 上午11:16:06
	 * @version V1.0
	 */
	@Override
	public int deleteByPageIdAndOrgId(Long orgId, Long pageId) {
		Map<String,Object> map = new HashMap<String,Object>();
		int req = 0;
		map.put("orgId", orgId);
		map.put("pageId", pageId);
		List<PageRole> prList = pageRoleMapper.selectByPageIdAndOrgId(map);
		for (PageRole pageRole : prList) {
			req = pageRoleMapper.deleteByPrimaryKey(pageRole.getPageRoleId());
		}
		return req;
	}

}
