package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.PageRole;
import com.ycnet.frms.bean.Pages;

public interface PagesService {

	List<Pages> getPageList(Pages record);
	
	int insertSelective(Pages record);
	
	int updateByPrimaryKeySelective(Pages record);

    int updateByPrimaryKey(Pages record);
    
    Pages selectByPrimaryKey(Long pageId);
    
    List<KVBean> selectByPagesRank(Pages record);
    
    List<Pages> selectParentPagesByRoleId(Long roleId);

	List<Pages> queryByPageName(String pageName);
	/**
	 * 查看下级是否使用上级功能管理
	 * @author tsw
	 * @since 2017年10月27日8:45:52
	 *
	 */
	List<Pages> queryByxjname(long pageId);
	/**
	 * 删除
	 * @author tsw
	 * @since 2017年10月27日8:45:52
	 *
	 */
	int deleteByPrimaryKeySelective(Pages record);
	/**
	 * 添加页面功能管理
	 * @author tsw
	 * @since 2017年10月30日8:45:52
	 *
	 */
	List<Pages> selectByPagesorder(Pages record);
	/**
	 * 
	* @Title: queryByConditionMap 
	* @Description: 分页 
	* @param @param pages
	* @param @param pb
	* @param @return    入参
	* @return PageBean    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月28日 下午5:09:28 
	* @version V1.0
	 */
	PageBean queryByConditionMap(Pages pages, PageBean pb);
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
	 * @param pageRank 
	 */
	List<Pages> selectByroles(Long roleId, Long pageRank);
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
	 * @param pageRank 
	 */
	List<Pages> getPageListByRole(Pages pages, Long roleId);
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
	List<Pages> getPageListByOrgId(Pages pages, Long orgId);
}
