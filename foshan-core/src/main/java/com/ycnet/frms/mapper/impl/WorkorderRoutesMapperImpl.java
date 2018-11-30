package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderRoutes;
import com.ycnet.frms.mapper.WorkorderRoutesMapper;
@Repository("workorderRoutesMapper")
public class WorkorderRoutesMapperImpl extends BaseSqlSupport implements WorkorderRoutesMapper {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.delete("com.ycnet.frms.mapper.WorkorderRoutesMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(WorkorderRoutes record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderRoutesMapper.insert",record);
	}

	@Override
	public int insertSelective(WorkorderRoutes record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderRoutesMapper.insertSelective",record);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderRoutes record) {
		return this.update("com.ycnet.frms.mapper.WorkorderRoutesMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderRoutes record) {
		return this.update("com.ycnet.frms.mapper.WorkorderRoutesMapper.updateByPrimaryKey",record);
	}
	
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
	@Override
	public List<WorkorderRoutes> queryRoutesAdevByDesignId(Long designId) {
		return this.selectList("com.ycnet.frms.mapper.WorkorderRoutesMapper.queryRoutesAdevByDesignId", designId);
	}

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
	@Override
	public WorkorderRoutes selectByPrimaryKey(Long designroutesId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderRoutesMapper.selectByPrimaryKey", designroutesId);
	}

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
	@Override
	public WorkorderRoutes selectDesignAndRouteSort(Long designplanId, int routesSort) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("designId", designplanId);
		map.put("routesSort", routesSort);
		return this.selectOne("com.ycnet.frms.mapper.WorkorderRoutesMapper.selectDesignAndRouteSort", map);
	}

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
	 */
	@Override
	public WorkorderRoutes queryRoutesAZ(int routesSort, Long designroutesId, Long devId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("routesSort", routesSort);
		map.put("designroutesId", designroutesId);
		return this.selectOne("com.ycnet.frms.mapper.WorkorderRoutesMapper.queryRoutesAZ", map);
	}
}
