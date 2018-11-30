package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.TnExport;
import com.ycnet.frms.vo.FacilityAll;
import com.ycnet.frms.vo.RouteTmp;

public interface RouteBuildService {

	
	List<RouteTmp> exportDataTmp(Long orgId, String areaCode1,String devType);
	
	List<RouteTmp> exportZZPT();
	
	List<RouteTmp> exportZZPT1();
	
	/**
	 * 根据区域重新生成光路
	 * @author: YHT
	 * @date: 2017年9月7日 上午10:08:25 
	 * @Title: routeBuild  
	 * @param @param areaCode
	 * @param @param orgId
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int routeBuild(Long orgId,String areaCode);
	

	int handRebuildAlls(Long orgId,String areaCode);
	
	
	List<FacilityAll> labExport();
	
	int updateByGroupCode(String aside,String side,Long devId);
	
	/**
	 * 按端子生成光路
	 * @author: YHT
	 * @date: 2017年9月26日 上午11:46:29 
	 * @Title: getAnyPortFiberRouteText  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int getAnyPortFiberRouteText(String code,Long orgId);
	
	/**
	 * 导入资管数据
	 * @author: YHT
	 * @date: 2017年10月20日 上午11:19:51 
	 * @Title: importZGErrorDatas  
	 * @param @return     
	 * @return List<TnExport>   
	 * @throws
	 */
	List<TnExport> importZGErrorDatas();
	
	/**
	 * 单点生成
	 * @author: YHT
	 * @date: 2017年10月17日 上午10:37:32 
	 * @Title: singlePointGenOptPath  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int singlePointGenOptPath(String code, Long orgId);
	
	/**
	 * 单点生成-异步
	 */
	void asyncSinglePointGenOptPath(String code, Long orgId);
	
	
	
	/**
	 * 全部生成光路
	 * @author: YHT
	 * @date: 2017年10月17日 上午10:44:50 
	 * @Title: rebulildDeviceRoute  
	 * @param @param devId
	 * @param @param orgId
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int rebulildDeviceRoute(Long devId, Long orgId);
	
	int importTXDatas();
	
	/**
	 * 导入佛山数据
	 * @author: YHT
	 * @date: 2017年11月6日 下午3:32:53 
	 * @Title: importFSDatas  
	 * @param @return     
	 * @return List<TnExport>   
	 * @throws
	 */
	List<TnExport> importFSDatas();
	
	/**
	 * 导入佛山设施信息
	 * @author: YHT
	 * @date: 2017年11月8日 下午3:45:54 
	 * @Title: importFSSSInfo  
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int importFSSSInfo(String devType);
	
	/**
	 * 导入佛山井数据
	 * @return
	 */
	int importFSWellInfo();

	/**
	 * 导出OLT
	* @Title: routeOLT 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return List<TnExport>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年8月4日 下午10:27:01 
	* @version V1.0
	 */
	List<TnExport> routeOLT(Long orgId);
	
	/**
	 * 接收坐标所属地区信息【json】
	* @Title: insertStreet 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年8月4日 下午10:28:31 
	* @version V1.0
	 */
	int insertStreet(String code);

	/**
	 * 查询所有区域集合
	* @Title: selectAreaList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return List<TnExport>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年8月4日 下午11:18:03 
	* @version V1.0
	 */
	List<TnExport> selectAreaList();

	/**
	 * 查询点集合
	* @Title: selectPointList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return List<TnExport>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年8月4日 下午11:20:19 
	* @version V1.0
	 */
	List<TnExport> selectPointList();

	int insertPoint(String code);

	int selectPoints(String code);

	/**
	 * 惠州博罗数据导入
	* @Title: hzboFacilityImport 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devType
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年9月7日 下午3:00:52 
	* @version V1.0
	 */
	int hzboFacilityImport(String devType);
}
