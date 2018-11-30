package com.ycnet.frms.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityForGroups;
import com.ycnet.frms.bean.FacilityImgs;
import com.ycnet.frms.bean.FacilityZF;
import com.ycnet.frms.vo.ExportJumper;
import com.ycnet.frms.vo.FacilityAll;
import com.ycnet.frms.vo.FacilityAsasideCableBean;
import com.ycnet.frms.vo.FacilityBean;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityFromMac;
import com.ycnet.frms.vo.FacilityVoBean;
import com.ycnet.frms.vo.LockKey;
import com.ycnet.frms.vo.RouteBean;
import com.ycnet.frms.vo.mobile.FacilityOdf;
import com.ycnet.frms.vo.mobile.ocii.IndexInBean;
import com.ycnet.frms.vo.mobile.ocii.IndexPoint;
import com.ycnet.frms.vo.mobile.ocii.OciiFacility;
import com.ycnet.frms.vo.mobile.ocii.OciiFacilityBean;


public interface FacilityMapper {
    int deleteByPrimaryKey(Long devId);

    int insert(Facility record);

    int insertSelective(Facility record);
    
    int insertSelectiveZG(Facility record);

    Facility selectByPrimaryKey(Long devId);

    int updateByPrimaryKeySelective(Facility record);

    int updateByPrimaryKey(Facility record);
    
    Facility selectByCode(String devCode);
    
    List<Facility> queryByConditionBean(FacilityConditionBean bean);
    
    List<Facility> selectAround(Map<String,Object> param);
    /**
     * 通过所属设施ID查询最大分光器编码
     * @param bean
     * @return
     */
    String queryObdMaxCode(FacilityConditionBean param);
    
    List<LockKey> queryKeyListByMap(Map<String,Object> map);
    
    /**
     * 
     * @author YHT
     * @time   2016年7月14日 下午2:04:17
     * @param map
     * @return
     */
    FacilityFromMac facilityFromdevMac(Map<String,Object> map);
    
    List<Facility> queryByConditionMap(Map<String,Object> map);
    
    int queryCountByConditionMap(Map<String,Object> map);
    
    
    /**
     * 智能锁设施授权查询
     * @param ids
     * @return
     */
    List<Facility> queryListByAccessIds(String[] ids);
    
    /**
     * 移动端 查询设施总数
     * @author YHT
     * @time   2016年8月7日 下午3:03:36
     * @param bean
     * @return
     */
    Integer queryCountByConditionBean(FacilityConditionBean bean);
    
    /**
     * 按照文本内容查询模糊查询设施列表
     * @param map
     * @return
     */
  //zhouyu 1/4修改返回值类型facility-facilityBean
    List<FacilityBean> queryListByContent(Map<String,Object> map);
    
    
    Integer countFacilityByPosition(Map<String,Object> map);
    
    List<Facility> selectFacilityByPosition(Map<String,Object> map);

	List<Facility> findFacilities(Long orgId);
	/**
	 * 查询未转化的点位
	 * @param orgId 
	 * @return
	 */
	int countFacilities(Long orgId);
	/**
	 * 查询所有没有转化过的点位信息
	 * @param pageSize 
	 * @param startNum 
	 * @return
	 */
	List<Facility> findNoChangeFacilityList(Map<String, Object> map);


	Facility selectByCodeXYId(Long devId);

	Facility queryByDevId(Long devId);

	Facility selectByCodeMap(String devCode);

	Facility selectByCode1(String devCode, Long orgId);

	FacilityImgs selectImgById(Long devId);

	List<Facility> selectArea(Long orgId);

	Facility queryFacilityBydevCode(Map<String, Object> map);

	List<Facility> queryByFacility(Long orgId, String areaCode1);

	List<Facility> queryByFacilityCablint(Long orgId, String areaCode1);

	//删除设备
	int update(Facility facility);
	
	List<Facility> selectByPdevId(Long devId);

	List<Facility> selectByPdevIdCablint(Long devId);

	List<Facility> querylllll();

	/**
	 * 
	* @Title: onSearchFacilities 
	* @Description: 按照模糊查询设施名称 
	* @param @param orgId
	* @param @param searchName
	* @param @param areaCode1
	* @param @return    
	* @return List<Facility>
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午6:59:36 
	* @version V1.0
	 */
	List<Facility> onSearchFacilities(Long orgId, String searchName,String areaCode1);

	List<FacilityAll> queryall();

	List<FacilityForGroups> queryAllForDev(FacilityForGroups bean);

	//导出数据库  刘沧海 2017/10/13
	List<FacilityZF> queryList(Long orgId, String areaCode);

	//导出光交箱 刘沧海 2017/10/17
	List<FacilityAll> queryExportAll(Long orgId, String areaCode);

	//导出机房/机柜  刘沧海  2017/10/17
	List<FacilityAll> queryExportAllJifang(Long orgId, String areaCode);

	/**
	 * 根据名称查询设施
	 * @author: YHT
	 * @date: 2017年10月23日 上午11:49:33 
	 * @Title: selectByDevName  
	 * @param @param orgId
	 * @param @param devName
	 * @param @return     
	 * @return Facility   
	 * @throws
	 */
	Facility selectByDevName(Long orgId, String devName);
	
	/**
	 * 资管导入专用
	 * @author: YHT
	 * @date: 2017年10月24日 上午10:22:09 
	 * @Title: selectByPrimaryKey  
	 * @param @param devId
	 * @param @return     
	 * @return Facility   
	 * @throws
	 */
	Facility selectByPrimaryKeyZG(Long devId);
	
	/**
	 * 资管导入专用
	 * @author: YHT
	 * @date: 2017年10月24日 上午11:34:02 
	 * @Title: selectByDevName  
	 * @param @param orgId
	 * @param @param devName
	 * @param @return     
	 * @return Facility   
	 * @throws
	 */
	Facility selectByDevNameZG(Long orgId, String devName);
	/**
	 * 
	* @Title: queryByConditionBeanXiugai 
	* @Description: TODO(用于导入设施检测名字是否重复) 
	* @param @param fcb
	* @param @return    入参
	* @return List<Facility>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年10月25日 下午3:41:10 
	* @version V1.0
	 */
	List<Facility> queryByConditionBeanXiugai(FacilityConditionBean bean);

	/**
	 * 
	* @Title: queryJumperGJX 
	* @Description: TODO(查询本端对端跳纤光交箱) 
	* @param @return    入参
	* @return List<ExportJumper>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月1日 下午4:48:58 
	* @version V1.0
	 */
	List<ExportJumper> queryJumperGJX();

	/**
	 * 
	* @Title: queryJumperJifang 
	* @Description: TODO(查询本端对端跳纤机房) 
	* @param @return    入参
	* @return List<ExportJumper>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月1日 下午4:59:53 
	* @version V1.0
	 */
	List<ExportJumper> queryJumperJifang();

	/**
	 * 
	* @Title: queryJumperFenguangqi 
	* @Description: TODO(查询分光器的跳纤) 
	* @param @return    入参
	* @return List<ExportJumper>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 上午11:36:47 
	* @version V1.0
	 */
	List<ExportJumper> queryJumperFenguangqi();
	
	/**
	 * 验证井是否存在
	 * @param orgId
	 * @param deName
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	Facility isExitWell(Long orgId,String devName,String longitude,String latitude);

	/**
	 * 井的需求
	 * 根据条件,名称(首字母)和经纬度范围内查询
	* 
	* @Title: FacilityMapper.java 
	* @Description: TODO 
	* @param @param devName
	* @param @return
	* @return List<Facility>
	* @author fl
	* @date 2017年11月29日 下午2:04:48
	* @version V1.0
	 */
	List<Facility> querywells(HashMap<String, Object> map);
	
	/**
	 * 根据设施描述查询设施信息（资管导入数据 存储资管ID）
	* @Title: selectByDevDesc 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devDesc
	* @param @return    入参
	* @return Facility    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月1日 下午5:42:46 
	* @version V1.0
	 */
	Facility selectByDevDesc(String devDesc);

	/**
	 * 
	 * @Title: queryFacilityOdf
	 * @Description: 查询子设施列表
	 * @param @param devId
	 * @param @param queryType
	 * @param @return 
	 * @return List<Facility> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月5日 下午10:15:41
	 * @version V1.0
	 * @param pb 
	 */
	List<Facility> queryFacilityOdf(Long devId, Integer queryType, PageBean pb);

	/**
	 * 
	* @Title: queryByDevIdForOppo 
	* @Description: 根据设施ID查询不是本端的设施 
	* @param @param session
	* @param @param facility
	* @param @return    入参
	* @return Object    返回类型
	* @author 刘沧海 
	* @throws
	* @date 2017年12月10日 下午2:52:07 
	* @version V1.0
	 */
	List<Facility> queryByDevIdForOppo(Facility facility,PageBean pb);

	/**
	 * 
	* @Title: findFacilitiesAreaCode1 
	* @Description: 根据汇聚区查询 
	* @param @param orgId
	* @param @param areaCode1
	* @param @return    
	* @return List<Facility>
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午1:48:45 
	* @version V1.0
	 */
	List<Map<String, Object>> findFacilitiesAreaCode1(Long orgId, String areaCode1);
	
	/**
	 * 根据汇聚区和机构id查询
	 * @param orgId
	 * @param areaCode1
	 * @return
	 */
	List<Map<String, Object>> findFacilitiesACode1ByOrgId(Long orgId, String areaCode1);
	
	/**
	 * 
	* @Title: queryForFacilityIds 
	* @Description: 根据设施ID查询所属光缆段的设施ID集合 
	* @param @param devId
	* @param @param orgId
	* @param @return    
	* @return FacilityAsasideCableBean
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午8:40:34 
	* @version V1.0
	 */
	FacilityAsasideCableBean queryForFacilityIds(Long devId, Long orgId);

	/**
	 * 
	* @Title: queryInfacilityIds 
	* @Description: 根据设施ID集合（ids）查询设施 
	* @param @param ids
	* @param @return    
	* @return List<Facility>
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午8:52:50 
	* @version V1.0
	 */
	List<Facility> queryInfacilityIds(String ids);

	/**
	 * 
	* @Title: queryRepetitionDevName 
	* @Description: 保存设施时验证名称重复 
	* @param @param facility
	* @param @return    
	* @return List<Facility>
	* @author liucanghai 
	* @throws
	* @date 2017年12月21日 下午7:50:12 
	* @version V1.0
	 */
	Facility queryRepetitionDevName(Facility facility);
	/**
	 * 
	* @Title: countFacilitysByPosition 
	* @Description: 查询设施分页
	* @param @param conditionMap
	* @param @return    入参
	* @return Integer    返回类型
	* @author zy
	* @throws
	* @date 2018年1月8日 上午10:01:43 
	* @version V1.0
	 */
	Integer countFacilitysByPosition(Map<String, Object> conditionMap);
	/**
	 * 
	* @Title: countFacilitysByPosition 
	* @Description: 查询设施
	* @param @param conditionMap
	* @param @return    入参
	* @return Integer    返回类型
	* @author zy
	* @throws
	* @date 2018年1月8日 上午10:01:43 
	* @version V1.0
	 */
	List<Facility> selectFacilitysByPosition(Map<String, Object> conditionMap);

	/**
	 * 整合m/v1/facilityList.htm
	 * m/facilityByDevcode.htm 
	 * m/v1/getAround.htm
	* 
	* @Title: FacilityMapper.java 
	* @Description: TODO 
	* @param @param facility
	* @param @param pb
	* @param @param orgId
	* @param @return
	* @return List<FacilityConditionBean>
	* @author fl
	* @date 2018年1月19日 下午2:15:30
	* @version V1.0
	 */
	List<Facility> queryFacilityByConditions(FacilityConditionBean facility, PageBean pb, Long orgId);

	/**
	 * 设施列表查询
	* 
	* @Title: FacilityMapper.java 
	* @Description: TODO 
	* @param @param conditionMap
	* @param @return
	* @return List<Facility>
	* @author fl
	* @date 2018年1月24日 下午3:42:38
	* @version V1.0
	 */
	List<Facility> queryByConditionsMap(Map<String, Object> conditionMap);

	/**
	 * 设施列表查询总条数
	* 
	* @Title: FacilityMapper.java 
	* @Description: TODO 
	* @param @param conditionMap
	* @param @return
	* @return Integer
	* @author fl
	* @date 2018年1月24日 下午3:42:50
	* @version V1.0
	 */
	Integer queryCountByConditionsMap(Map<String, Object> conditionMap);

	/**
	 * 按照是否有经纬度查询
	* 
	* @Title: FacilityMapper.java 
	* @Description: TODO 
	* @param @param orgId
	* @param @param existLngLat
	* @param @param pb
	* @param @return
	* @return List<Facility>
	* @author fl
	* @date 2018年1月24日 下午5:27:20
	* @version V1.0
	 */
	List<FacilityBean> queryExportExisttLngLat(Long orgId, String existLngLat);

	/**
	 * 
	* @Title: queryByConditionBeanInspect 
	* @Description: 巡检任务管理查询设施 
	* @param @param bean
	* @param @return    
	* @return List<Facility>
	* @author liucanghai 
	* @throws
	* @date 2018年2月5日 下午4:33:54 
	* @version V1.0
	 */
	List<Facility> queryByConditionBeanInspect(FacilityConditionBean bean);

	/**
	 * 根据用户,查询有问题的设施
	* @Title: queryProblematicalFactilityByUser 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @return    入参
	* @return List<FacilityVoBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午4:38:36 
	* @version V1.0
	 * @param userId 
	 * @param isCheckAll 
	 * @param bean 
	 */
	List<FacilityVoBean> queryProblematicalFactilityByUser(Long orgId, PageBean pb, int isCheckAll, Long userId, FacilityVoBean bean);

	/**
	 * 
	 * @Title: queryByOrgId
	 * @Description: 查询组织机构下是否有设施
	 * @param @param orgId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月17日 下午4:29:32
	 * @version V1.0
	 */
	int queryByOrgId(Long orgId);
	
	/**
	 * 按位置浏览-高级查询-根据条件返回设备信息
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findFacilitysToBdMap(Map<String, Object> params);

	/**
	 * 根据设施ID,查询所属设施
	* @Title: selectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<Facility>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月19日 下午2:26:54 
	* @version V1.0
	 */
	List<Facility> selectByDevId(Long devId);

	/**
	 * 
	 * @Title: queryListByContentAndBindCod
	 * @Description: 查询已绑定中控器的设施，并且只有光交箱，光终端盒，光分纤箱，机房
	 * @param @param map
	 * @param @return 
	 * @return List<FacilityBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月27日 下午2:01:14
	 * @version V1.0
	 */
	List<FacilityBean> queryListByContentAndBindCod(Map<String, Object> map);
	
	/**
	 * 根据设施ID查询机房中机柜列表
	 */
	List<Map<String, Object>> findFacilityByPdevId(Long devId);

	/**
	 * 根据设施ID查询机房中机柜列表
	* @Title: queryopdFacilityOdf 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param pb
	* @param @return    入参
	* @return List<Facility>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 上午10:48:40 
	* @version V1.0
	 */
	List<FacilityOdf> queryopdFacilityOdf(Long devId, PageBean pb);

	/**
	 * 
	 * @Title: queryFacilityByOrgId
	 * @Description: 根据orgId分组查询
	 * @param @return 
	 * @return List<Facility> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午10:24:21
	 * @version V1.0
	 */
	List<Facility> queryFacilityByOrgId();

	/**
	 * 
	 * @Title: queryIamRepetitionDevName
	 * @Description: 根据名称查询
	 * @param @param facility
	 * @param @return 
	 * @return Facility 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月11日 下午2:44:36
	 * @version V1.0
	 */
	Facility queryIamRepetitionDevName(Facility facility);

	/**
	 * 
	 * @Title: queryByPdevIdAndDevName
	 * @Description: 根据机房下的机柜名称查询
	 * @param @param pdevId
	 * @param @param devName
	 * @param @return 
	 * @return Facility 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月11日 下午4:50:31
	 * @version V1.0
	 */
	Facility queryByPdevIdAndDevName(Long pdevId, String devName);

	/** 
	 * 根据井Id下的所有接头
	* @Title: queryJointByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return List<Facility>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午4:20:53 
	* @version V1.0   
	*/
	List<Map<String, Object>> queryJointByWellId(Long wellId);

	/** 
	 * 查询设施列表
	* @Title: queryOciiFacilityList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param orgId
	* @param @return    入参
	* @return List<OciiFacility>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午1:38:30 
	* @version V1.0   
	 * @param pb 
	*/
	List<OciiFacility> queryOciiFacilityList(FacilityConditionBean facility, PageBean pb, Long orgId);

	/** 
	 * 根据设施ID查询设施(机房,光交)
	* @Title: queryOciiFacilityByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return OciiFacilityBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午2:18:57 
	* @version V1.0   
	*/
	OciiFacilityBean queryOciiFacilityByDevId(Long devId);

	/** 
	 * 查询机房下的机柜列表
	* @Title: queryOciiFacilityByPdevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<Map<String,Object>>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午2:49:42 
	* @version V1.0   
	*/
	List<Map<String, Object>> queryOciiFacilityByPdevId(Long devId);

	/**
	 * 通过坐标查询首页设施信息
	* @Title: OcIndexFacByType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param iib
	* @param @return    入参
	* @return List<IndexPoint>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月16日 下午2:29:14 
	* @version V1.0
	 */
	List<IndexPoint> OcIndexFacByType(IndexInBean iib);

}
