package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Pages;
import com.ycnet.frms.mapper.PagesMapper;
import com.ycnet.frms.service.PagesService;

@Service("pagesService")
public class PagesServiceImpl implements PagesService {
	
	@Resource(name="pagesMapper")
	private PagesMapper pagesMapper;

	@Override
	public List<Pages> getPageList(Pages record) {
		
		return pagesMapper.getPageList(record);
	}

	@Override
	public int insertSelective(Pages record) {
		if(record != null){
			return pagesMapper.insert(record);
		}
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(Pages record) {
		if(record != null){
			return pagesMapper.updateByPrimaryKeySelective(record);
		}
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Pages record) {
		if(record != null){
			return pagesMapper.updateByPrimaryKey(record);
		}
		return 0;
	}

	@Override
	public Pages selectByPrimaryKey(Long pageId) {
		if(pageId != null){
			return pagesMapper.selectByPrimaryKey(pageId);
		}
		return null;
	}
	@Override
	public int deleteByPrimaryKeySelective(Pages record) {
		if(record != null){
			return pagesMapper.deleteByPrimaryKeySelective(record);
		}
		return 0;
	}
	@Override
	public List<KVBean> selectByPagesRank(Pages record) {
		
		return codeList(pagesMapper.getPageList(record));
	}
	
	private List<KVBean> codeList(List<Pages> list)
	{
		List<KVBean> ls = new ArrayList<KVBean>();
		for(Pages pages:list)
		{
			if(pages==null||pages.getPageId()==null||"".equals(String.valueOf(pages.getPageId())))
				continue;
			KVBean bean = new KVBean(String.valueOf(pages.getPageId()),pages.getPageName());
			ls.add(bean);
		}
		return ls;
	}

	@Override
	public List<Pages> selectParentPagesByRoleId(Long roleId) {
		if(roleId != null){
			return pagesMapper.selectParentPagesByRoleId(roleId);
		}
		return null;
	}

	@Override
	public List<Pages> queryByPageName(String pageName) {
		return pagesMapper.queryByPageName(pageName);
	}
	@Override
	public List<Pages> queryByxjname(long pageId) {
		return pagesMapper.queryByxjname(pageId);
	}
	@Override
	public List<Pages> selectByPagesorder(Pages record) {
		
		return pagesMapper.selectByPagesorder(record);
	}
	/**
	 * zhouyu 12/28 分页
	 */
	@Override
	public PageBean queryByConditionMap(Pages pages, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("pages", pages);
		conditionMap.put("pb", pb);
		pb.setRows(pagesMapper.queryCountByConditionMap(conditionMap));
		pb.setList(pagesMapper.queryByConditionMap(conditionMap));
		
		return pb;
	}
	/**
	 * 
	* @Title: selectByroles 
	* @Description: 根据roleId查询角色的权限列表 
	* @param @param roleId
	* @param @return    入参
	* @return List<Pages>    返回类型
	* @author 周宇 
	* @throws
	* @date 2018年2月12日 上午10:07:01 
	* @version V1.0
	 */
	@Override
	public List<Pages> selectByroles(Long roleId,Long pageRank) {
		return pagesMapper.selectByroles(roleId,pageRank);
	}
	/**
	 * 
	* @Title: selectByroles 
	* @Description: 根据roleId查询角色的权限列表 
	* @param @param pages
	* @param @return    入参
	* @return List<Pages>    返回类型
	* @author 周宇 
	* @throws
	* @date 2018年2月12日 上午10:07:01 
	* @version V1.0
	 */
	@Override
	public List<Pages> getPageListByRole(Pages pages, Long userId) {
		return pagesMapper.getPageListByRole(pages,userId);
	}
	/**
	 * 
	* @Title: getPageListByOrgId 
	* @Description: 根据组织机构查询权限 
	* @param @param pages
	* @param @param orgId
	* @param @return    入参
	* @return List<Pages>    返回类型
	* @author 周宇 
	* @throws
	* @date 2018年2月12日 下午3:48:54 
	* @version V1.0
	 */
	@Override
	public List<Pages> getPageListByOrgId(Pages pages, Long orgId) {
		return pagesMapper.getPageListByOrgId(pages,orgId);
	}
}
