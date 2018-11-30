package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Organizition;

public interface OrganizitionMapper {
    int deleteByPrimaryKey(Long orgId);

    int insert(Organizition record);

    int insertSelective(Organizition record);

    Organizition selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(Organizition record);

    int updateByPrimaryKey(Organizition record);
    
    List<Organizition> getOrganizitionList(Organizition record);
    
    List<Organizition> queryByConditionMap(Map<String,Object> map);
    
    int queryCountByConditionMap(Map<String,Object> map);
    
    List<Organizition> OrgIdList();

	Organizition selectroleid(Long userId);
	/**
	 * 
	* @Title: queryCountByConditionMapAndOrgId 
	* @Description: 查询组织机构数目
	* @param @param conditionMap
	* @param @return    入参
	* @return int    返回类型
	* @author zhouyu
	* @throws
	* @date 2018年2月6日 上午11:21:21 
	* @version V1.0
	 */
	int queryCountByConditionMapAndOrgId(Map<String, Object> conditionMap);
	/**
	 * 
	* @Title: queryCountByConditionMapAndOrgId 
	* @Description: 查询组织机构
	* @param @param conditionMap
	* @param @return    入参
	* @return Integer    返回类型
	* @author zhouyu
	* @throws
	* @date 2018年2月6日 上午11:21:21 
	* @version V1.0
	 */
	List<Organizition> queryByConditionMapAndOrgId(Map<String, Object> conditionMap);

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
	Organizition getorgPlatformList(Long orgId);
}