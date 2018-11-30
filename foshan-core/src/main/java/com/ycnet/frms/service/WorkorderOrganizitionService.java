package com.ycnet.frms.service;

import com.ycnet.frms.bean.WorkorderOrganizition;

public interface WorkorderOrganizitionService {

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据ID查询调度项目的组织机构
	 * @param @param orgId
	 * @param @return 
	 * @return WorkorderOrganizition 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午2:26:57
	 * @version V1.0
	 */
	WorkorderOrganizition selectByPrimaryKey(Long orgId);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加光调项目组织机构
	 * @param @param wOrgNew
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午7:01:11
	 * @version V1.0
	 */
	int insertSelective(WorkorderOrganizition wOrgNew);

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改光调组织机构
	 * @param @param wOrgNew
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月10日 下午5:29:19
	 * @version V1.0
	 */
	int updateByPrimaryKeySelective(WorkorderOrganizition wOrgNew);

}
  
    