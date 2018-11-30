package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.RouteZF;
import com.ycnet.frms.bean.TempLightPath;
import com.ycnet.frms.mapper.RouteMapper;
import com.ycnet.frms.vo.LightPath;
import com.ycnet.frms.vo.RouteBean;
import com.ycnet.frms.vo.RouteTmp;
import com.ycnet.frms.vo.SectRoute;

@Repository("routeMapper")
public class RouteMapperImpl extends BaseSqlSupport implements RouteMapper {

	@Override
	public int deleteByPrimaryKey(Long routeId) {
		return this.delete("com.ycnet.frms.mapper.RouteMapper.deleteByPrimaryKey",routeId);
	}

	@Override
	public int insert(Route record) {
		return this.insert("com.ycnet.frms.mapper.RouteMapper.insert",record);
	}

	@Override
	public int insertSelective(Route record) {
		return this.insert("com.ycnet.frms.mapper.RouteMapper.insertSelective",record);
	}

	@Override
	public Route selectByPrimaryKey(Long routeId) {
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.selectByPrimaryKey",routeId);
	}

	@Override
	public int updateByPrimaryKeySelective(Route record) {
		return this.update("com.ycnet.frms.mapper.RouteMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(Route record) {
		return this.update("com.ycnet.frms.mapper.RouteMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<Route> queryRouteByName(String routeName) {
		if(routeName==null||"".equals(routeName.trim()))
			return null;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("routeName", routeName);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryRoute",param);
	}

	@Override
	public List<Route> queryRouteByDev(Long devId) {
		if(devId ==null)
			return null;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("devId", devId);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryRoute",param);
	}

	@Override
	public List<Route> queryRouteByCode(String code) {
		if(code==null||"".equals(code.trim()))
			return null;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("code", code);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryRouteByCode",param);
	}

	@Override
	public List<Route> queryRouteByStartCode(String startCode) {
		if(startCode==null||"".equals(startCode.trim()))
			return null;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("startCode", startCode);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryRoute",param);
	}
	
	@Override
	public List<Route> queryRouteByEndpoint(String acode, String zcode) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("acode", acode);
		params.put("zcode", zcode);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryRouteByEndpoint",params);
	}
	/**
	 * 查询光路数据 分页
	 */
	@Override
	public List<Route> queryByConditionMap(Map<String, Object> map) {
	
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryByConditionMap", map);
	}

	/**
	 * 查询光路数据数量
	 */
	@Override
	public int queryCountByConditionMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.queryCountByConditionMap", map);

	}

	@Override
	public List<RouteBean> queryAllByConditionMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryAllByConditionMap", map);
	}

	@Override
	public List<RouteBean> querySimpleInfoByConditionMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.querySimpleInfoByConditionMap", map);
	}

	@Override
	public int querySimpleInfoCountByConditionMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.querySimpleInfoCountByConditionMap", map);
	}

	@Override
	public int queryCountExportMap(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.queryCountExportMap", map);
	}

	@Override
	public List<RouteBean> queryAllExportMap(Long orgId,String areaCode1) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode1", areaCode1);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryAllExportMap", map);
	}

	@Override
	public int queryCountCablintMap(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.queryCountCablintMap", map);
	}

	@Override
	public List<RouteBean> queryAllCablint(Long orgId,String areaCode1) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode1", areaCode1);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryAllCablintMap", map);
	}

	@Override
	public List<RouteBean> queryAllFacility(Long orgId, String devCode,Long devId,PageBean pb) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("devCode", devCode);
		map.put("devId", devId);
		map.put("pb", pb);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryAllFacility",map);
	}
	
	@Override
	public int queryCount(Long orgId, String devCode,Long devId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("devCode", devCode);
		map.put("devId", devId);
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.queryCount",map);
	}

	@Override
	public List<RouteBean> queryFacility(Long orgId, String devCode, String devType) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("devCode", devCode);
		map.put("devType", devType);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryFacility",map);
	}

	@Override
	public List<TempLightPath> queryAllLightPathInfo(Long orgId) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryAllLightPathInfo",orgId);
	}

	@Override
	public List<RouteTmp> exportDataTmp(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.exportDataTmp",map);
	}

	@Override
	public Route queryCodes(String str) {
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.queryCodes", str);
	}

	@Override
	public List<RouteBean> queryAllExportMap1(Long orgId, String areaCode1) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode1", areaCode1);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryAllExportMap1",map);
	}

	@Override
	public List<RouteBean> queryAllCablint1(Long orgId, String areaCode1) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode1", areaCode1);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryAllCablint1",map);
	}

	@Override
	public List<SectRoute> selectByRoute(SectRoute sectRoute) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.selectByRoute",sectRoute);
	}

	@Override
	public int deleteByOrgId(Long orgId,String code) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgId", orgId);
		params.put("code", code);
		return this.delete("com.ycnet.frms.mapper.RouteMapper.deleteByOrgId",params);
	}
	//删除tn_task_log表中数据
	@Override
	public int delectroutelog(Long orgId, String areaCode) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgId", orgId);
		params.put("areaCode", areaCode);
		return this.delete("com.ycnet.frms.mapper.RouteMapper.delectroutelog",params);
	}
	@Override
	public Integer selectDiscNumBydev(Long devId, String side) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("devId", devId);
		params.put("side", side);
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.selectDiscNumBydev",params);
	}

	@Override
	public Route queryRouteByCode(String areaCode, String code) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("areaCode", areaCode);
		params.put("code", code);
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.queryRouteByCode",params);
	}


	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<RouteZF> queryList(Long orgId,String areaCode) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode", areaCode);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.queryList", map);
	}

	//新增查询光路文本路由  刘沧海  2017/10/17
	@Override
	public Route queryPort01(String port01) {
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.queryPort01",port01);
	}


	@Override
	public int deleteByCode(String code) {
		// TODO Auto-generated method stub
		if("".equals(code) || code==null) {
			return 1;
		}
		return this.delete("com.ycnet.frms.mapper.RouteMapper.deleteByCode",code);
	}

	@Override
	public Route queryRoutext(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.queryRoutext",map);
	}

	/**
	 * 
	* @Title: exportLightWane 
	* @Description: 导出光衰状态 
	* @param @param orgId
	* @param @param areaCode1
	* @param @return    
	* @return List<RouteBean>
	* @author liucanghai 
	* @throws
	* @date 2017年12月26日 上午9:16:42 
	* @version V1.0
	 */
	@Override
	public List<RouteBean> exportLightWaneGJX(Long orgId, String areaCode1) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode1", areaCode1);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.exportLightWaneGJX", map);
	}

	/**
	 * 
	* @Title: exportLightWaneCalint 
	* @Description: 导出光衰状态 
	* @param @param orgId
	* @param @param areaCode1
	* @param @return    
	* @return List<RouteBean>
	* @author liucanghai 
	* @throws
	* @date 2017年12月26日 上午9:16:42 
	* @version V1.0
	 */
	@Override
	public List<RouteBean> exportLightWaneCalint(Long orgId, String areaCode1) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode1", areaCode1);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.exportLightWaneCalint", map);
	}

	/**
	 * 
	* @Title: queryRouteTextForCode 
	* @Description: 根据端口编码查询光路文本路由 
	* @param @param code
	* @param @return    
	* @return Route
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午3:04:33 
	* @version V1.0
	 */
	@Override
	public Route queryRouteTextForCode(String code) {
		return this.selectOne("com.ycnet.frms.mapper.RouteMapper.queryRouteTextForCode", code);
	}

	@Override
	public List<RouteBean> testDT(Long orgId, String areaCode1) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode1", areaCode1);
		return this.selectList("com.ycnet.frms.mapper.RouteMapper.testDT", map);
	}


}
