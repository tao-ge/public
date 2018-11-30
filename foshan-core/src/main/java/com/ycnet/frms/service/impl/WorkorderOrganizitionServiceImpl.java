package com.ycnet.frms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.WorkorderOrganizition;
import com.ycnet.frms.mapper.WorkorderOrganizitionMapper;
import com.ycnet.frms.service.WorkorderOrganizitionService;

@Service("workorderOrganizitionService")
@Transactional
public class WorkorderOrganizitionServiceImpl implements WorkorderOrganizitionService{

	@Resource(name="workorderOrganizitionMapper")
	private WorkorderOrganizitionMapper workorderOrganizitionMapper;

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据ID查询调度项目的组织机构
	 * @param @param orgId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午2:27:57
	 * @version V1.0
	 */
	@Override
	public WorkorderOrganizition selectByPrimaryKey(Long orgId) {
		return workorderOrganizitionMapper.selectByPrimaryKey(orgId);
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加光调项目组织机构
	 * @param @param wOrgNew
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月9日 下午7:01:34
	 * @version V1.0
	 */
	@Override
	public int insertSelective(WorkorderOrganizition wOrgNew) {
		return workorderOrganizitionMapper.insertSelective(wOrgNew);
	}

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改光调组织机构
	 * @param @param wOrgNew
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月10日 下午5:29:51
	 * @version V1.0
	 */
	@Override
	public int updateByPrimaryKeySelective(WorkorderOrganizition wOrgNew) {
		return workorderOrganizitionMapper.updateByPrimaryKeySelective(wOrgNew);
	}
}
  
    