package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Basecode;
import com.ycnet.frms.bean.Organizition;

public interface OrganizitionService {

	Organizition selectById(Long orgId);
	
	List<Organizition> getOrganizitionList(Organizition record);
	
	PageBean queryByConditionMap(Organizition record,PageBean pb);
	
	int insert(Organizition record);
	
	Organizition selectByPrimaryKey(Long orgId);
	
	int updateByPrimaryKeySelective(Organizition record);

    int updateByPrimaryKey(Organizition record);
    
    int deleteByPrimaryKey(Long orgId);



	Organizition selectroleid(Long orgId);
	/**
	 * 
	* @Title: queryByConditionMapAndOrgId 
	* @Description: 组织机构根据orgid和orgName查询
	* @param @param organizition
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu
	* @throws
	* @date 2018年2月6日 上午10:20:49 
	* @version V1.0
	 */
	Object queryByConditionMapAndOrgId(Organizition organizition, PageBean pb, Long orgId);

	/**
	 * 
	* @Title: getorgPlatformList 
	* @Description: 得到当前组织机构下的所有平台类型 
	* @param @param orgId
	* @param @return    入参
	* @return List<String>    返回类型
	* @author 周宇
	* @throws
	* @date 2018年2月11日 下午1:35:36 
	* @version V1.0
	 */
	List<Basecode> getorgPlatformList(Long orgId);

	/**
	 * 查询机构名称
	* @Title: queryByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return Organizition    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月29日 上午11:00:33 
	* @version V1.0
	 */
	Organizition queryByOrgId(Long orgId);

	/**
	 * 提交参数到NB平台
	* @Title: httpsConection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param organizition
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月26日 下午2:54:39 
	* @version V1.0
	 * @throws Exception 
	 */
	int httpsConection(Organizition organizition) throws Exception;
    

}
