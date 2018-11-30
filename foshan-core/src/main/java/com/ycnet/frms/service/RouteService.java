package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.RouteNode;
import com.ycnet.frms.bean.RouteZF;
import com.ycnet.frms.bean.TempLightPath;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.RouteBean;
import com.ycnet.frms.vo.RouteChart;
import com.ycnet.frms.vo.RouteTmp;
import com.ycnet.frms.vo.SectRouteBean;
import com.ycnet.frms.vo.sectRoute.SectRouteResult;
import com.ycnet.frms.vo.SectRoute;

public interface RouteService {

	int insert(Route route);
	
	int insert(RouteNode routeNode);
	
	int deleteById(Long routeId);
	
	List<Route> queryRouteByName(String routeName);
	
	List<Route> queryRouteByDev(Long devId);
	
	List<Route> queryRouteByCode(String code);
	
	List<Route> queryRouteByStartCode(String startCode);

	List<Route> queryRouteByEndpoint(String acode,String zcode);
	/**
	 * 根据光路id获取光路信息
	 * @param routeId
	 * @return
	 */
	RouteChart queryRouteById(Long routeId);

	PageBean queryByConditionBean(RouteBean bean,
			Users user, PageBean pb);
	
	/**
	 * 查询光路数据 不转bean
	 * @param bean
	 * @param user
	 * @param pb
	 * @return
	 */
	PageBean queryAllByConditionMap(RouteBean bean,Users user, PageBean pb);
	
	PageBean querySimpleInfoByConditionBean(RouteBean bean,Users user, PageBean pb);

//	PageBean queryExport(RouteBean routeBean, Users user, PageBean pb);

	List<RouteBean> queryExport(Long orgId, String areaCode1);

//	PageBean queryAllCablint(RouteBean routeBean, Users user, PageBean pb);

	List<RouteBean> queryAllCablint(Long orgId, String areaCode1);

	List<RouteBean> queryAllFacility(Long orgId, String devCode, Long devId,PageBean pb);
	
	Integer queryCount(Long orgId, String devCode, Long devId);
	
	List<RouteBean> queryFacility(Long orgId, String devCode, String devType);
	
	/**
	 * 根据临时表数据导出光路
	 * @param orgId
	 * @param areaCode1
	 * @return
	 */
	List<TempLightPath> queryLPExport(Long orgId, String areaCode1,String devType);
	
	List<RouteTmp> exportDataTmp(Long orgId, String areaCode1,String devType);
	
	List<RouteTmp> exportZZPT();

	/**
	 * 
	* @Title: queryExport1 
	* @Description: TODO(导出光交箱光路+文本路由) 
	* @param @param orgId
	* @param @param areaCode1
	* @param @return    入参
	* @return List<RouteBean>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月22日 上午11:08:00 
	* @version V1.0
	 */
	List<RouteBean> queryExport1(Long orgId, String areaCode1);

	/**
	 * 
	* @Title: queryAllCablint1 
	* @Description: TODO(导出机柜光路+机房+文本路由) 
	* @param @param orgId
	* @param @param areaCode1
	* @param @return    入参
	* @return List<RouteBean>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月22日 上午10:23:14 
	* @version V1.0
	 */
	List<RouteBean> queryAllCablint1(Long orgId, String areaCode1);
	
	PageBean querySectRouteList(SectRouteBean record,Users user,PageBean pb); 
	
	SectRouteResult selectByRouteA(SectRoute sectRoute);
	
	SectRouteResult selectByRouteZ(SectRoute sectRoute);

	int updateisentering(SectRouteBean record);

	int getAnyPortisentering(Long Id);

	int addisentering(SectRouteBean record);

	

	int opticalcable(SectRoute sectRoute);

	int insertopticalcables(SectRouteBean record);

	int getAnyPortopticalcables(SectRouteBean record);

	//导出数据库  刘沧海 2017/10/13
	List<RouteZF> queryList(Long orgId, String areaCode);

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
