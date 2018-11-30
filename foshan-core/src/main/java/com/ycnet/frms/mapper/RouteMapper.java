package com.ycnet.frms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.RouteZF;
import com.ycnet.frms.bean.TempLightPath;
import com.ycnet.frms.vo.LightPath;
import com.ycnet.frms.vo.RouteBean;
import com.ycnet.frms.vo.RouteTmp;
import com.ycnet.frms.vo.SectRoute;

public interface RouteMapper {
    int deleteByPrimaryKey(Long routeId);

    int insert(Route record);

    int insertSelective(Route record);

    Route selectByPrimaryKey(Long routeId);

    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);
    
    List<Route> queryRouteByName(String routeName);
    
    List<Route> queryRouteByDev(Long devId);
    
    List<Route> queryRouteByCode(String code);
    
    List<Route> queryRouteByStartCode(String startCode);
    
    List<Route> queryRouteByEndpoint(String acode,String zcode);
    
    List<Route> queryByConditionMap(Map<String, Object> map);
    
    int queryCountByConditionMap(Map<String, Object> map);
    
    /**
     * 查询所有符合条件的记录 
     * @param map
     * @return
     */
    List<RouteBean> queryAllByConditionMap(Map<String, Object> map);    
    List<RouteBean> querySimpleInfoByConditionMap(Map<String, Object> map);
    int querySimpleInfoCountByConditionMap(Map<String, Object> map);

	int queryCountExportMap(Map<String, Object> map);

//	List<RouteBean> queryAllExportMap(Map<String, Object> map);

	List<RouteBean> queryAllExportMap(Long orgId, String areaCode1);

	int queryCountCablintMap(Map<String, Object> map);

//	List<RouteBean> queryAllCablintMap(Map<String, Object> map);

	List<RouteBean> queryAllCablint(Long orgId, String areaCode1);

	List<RouteBean> queryAllFacility(Long orgId, String devCode, Long devId,PageBean pb);
	
	int queryCount(Long orgId, String devCode, Long devId);

	List<RouteBean> queryFacility(Long orgId, String devCode, String devType);

	List<TempLightPath> queryAllLightPathInfo(Long orgId);
    
	List<RouteTmp> exportDataTmp(HashMap<String,Object> map);

	Route queryCodes(String str);

	List<RouteBean> queryAllExportMap1(Long orgId, String areaCode1);

	List<RouteBean> queryAllCablint1(Long orgId, String areaCode1);
	
	List<SectRoute> selectByRoute(SectRoute sectRoute);
	
	int deleteByOrgId(Long orgId,String code);
	
	Integer selectDiscNumBydev(Long devId,String side);
	
	/**
	 * 根据地区编码查询光路
	 * @author: YHT
	 * @date: 2017年9月27日 下午3:20:53 
	 * @Title: queryCodes  
	 * @param @param areaCode
	 * @param @param str
	 * @param @return     
	 * @return Route   
	 * @throws
	 */
	Route queryRouteByCode(String areaCode,String code);


	//导出数据库  刘沧海 2017/10/13
	List<RouteZF> queryList(Long orgId, String areaCode);

	//新增查询光路文本路由  刘沧海  2017/10/17
	Route queryPort01(String port01);

	/**
	 * 删除含有该端子的光路
	 * @author: YHT
	 * @date: 2017年10月11日 下午4:51:21 
	 * @Title: deleteByCode  
	 * @param @param code
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int deleteByCode(String code);

	int delectroutelog(Long orgId, String areaCode);

	/**
	 * 根据acode和zcode 查询route-text
	* 
	* @Title: RouteMapper.java 
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @return String
	* @author fl
	* @date 2017年11月27日 下午7:06:46
	* @version V1.0
	 */
	Route queryRoutext(Map<String, Object> map);

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
	List<RouteBean> exportLightWaneGJX(Long orgId, String areaCode1);

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
	List<RouteBean> exportLightWaneCalint(Long orgId, String areaCode1);

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
	Route queryRouteTextForCode(String code);

	List<RouteBean> testDT(Long orgId, String areaCode1);

}
