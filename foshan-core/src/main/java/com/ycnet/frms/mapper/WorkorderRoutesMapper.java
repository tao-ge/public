package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.WorkorderRoutes;

public interface WorkorderRoutesMapper {

	int deleteByPrimaryKey(Long id);

    int insert(WorkorderRoutes record);

    int insertSelective(WorkorderRoutes record);

    int updateByPrimaryKeySelective(WorkorderRoutes record);

    int updateByPrimaryKey(WorkorderRoutes record);
    
	/**
	 * 
	* @Title: queryRoutesAdevByDesignId 
	* @Description: 根据调度工单ID查询所属下面设施 
	* @param @param designId
	* @param @return    
	* @return List<WorkorderRoutes>
	* @author liucanghai 
	* @throws
	* @date 2018年4月20日 下午2:41:43 
	* @version V1.0
	 */
	List<WorkorderRoutes> queryRoutesAdevByDesignId(Long designId);

	/**
	 * 
	* @Title: selectByPrimaryKey 
	* @Description: 查一条 
	* @param @param designroutesId
	* @param @return    
	* @return WorkorderRoutes
	* @author liucanghai 
	* @throws
	* @date 2018年4月23日 下午4:39:01 
	* @version V1.0
	 */
	WorkorderRoutes selectByPrimaryKey(Long designroutesId);

	/**
	 * 根据路由顺序,和 设计ID查询 上一条,Z设施ID
	* @Title: selectDesignAndRouteSort 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param designplanId
	* @param @param i
	* @param @return    入参
	* @return WorkorderRoutes    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月18日 上午10:09:31 
	* @version V1.0
	 */
	WorkorderRoutes selectDesignAndRouteSort(Long designplanId, int routesSort);

	/**
	 * 根据路由ID,和路由顺序查询AZ
	* @Title: queryRoutesAZ 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param i
	* @param @param designroutesId
	* @param @return    入参
	* @return WorkorderRoutes    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月27日 下午1:59:36 
	* @version V1.0
	 * @param devId 
	 */
	WorkorderRoutes queryRoutesAZ(int routesSort, Long designroutesId, Long devId);

}
