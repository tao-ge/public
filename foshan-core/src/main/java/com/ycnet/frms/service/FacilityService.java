package com.ycnet.frms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.ResultData;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityForGroups;
import com.ycnet.frms.bean.FacilityImg;
import com.ycnet.frms.bean.FacilityImgs;
import com.ycnet.frms.bean.FacilityZF;
import com.ycnet.frms.bean.FiberdiscGroupZF;
import com.ycnet.frms.bean.OcJointDirect;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.CableSectionBean;
import com.ycnet.frms.vo.ExportJumper;
import com.ycnet.frms.vo.FacilityAll;
import com.ycnet.frms.vo.FacilityAsasideCableBean;
import com.ycnet.frms.vo.FacilityBean;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityRoom;
import com.ycnet.frms.vo.FacilityVoBean;
import com.ycnet.frms.vo.FacilityVoBeanXY;
import com.ycnet.frms.vo.FiberdiscGroupBean;
import com.ycnet.frms.vo.JumperInfo;
import com.ycnet.frms.vo.JumperInfos;
import com.ycnet.frms.vo.JumperTerraceInfo;
import com.ycnet.frms.vo.LocateInfo;
import com.ycnet.frms.vo.LockKey;
import com.ycnet.frms.vo.RouteBean;
import com.ycnet.frms.vo.WorkorderJumperInfo;
import com.ycnet.frms.vo.mobile.FacilityCabinetMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;
import com.ycnet.frms.vo.mobile.FacilityOdf;
import com.ycnet.frms.vo.mobile.FacilityVoOpd;
import com.ycnet.frms.vo.mobile.ocii.OciiFacility;
import com.ycnet.frms.vo.mobile.ocii.OciiFacilityBean;

public interface FacilityService {

	Facility selectByCode(String devCode);
	
	/**
	 * 
	 * @Title: save
	 * @Description: 2017年11月8日 下午对设施保存接口修改:
	 * 				添加参数Users,用于对资管校准日志的添加
	 * @param @param facility
	 * @param @param users
	 * @param @return 
	 * @return Long 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月8日 下午4:28:06
	 * @version V1.0
	 */
	Long save(Facility facility,Users users);
	
	Long save(Facility facility,List<FacilityImg> images);
	
	/**
	 * 添加分光器
	 * @param facility
	 * @return
	 */
	int addObd(Facility facility);
	
	Facility selectById(Long devId);
	
	int delete(Long devId);
	
	List<Facility> queryByConditionBean(FacilityConditionBean bean);
	
	
	List<Facility> selectAround(Double curLng, Double curLat,Double distance,String devType,String surveyResult,String areaCode1, Users user);
	
	List<Facility> selectAround1(Double curLng, Double curLat,Double distance,String devType,String surveyResult,String areaCode1, Users user, Integer isDel);
	//坐标转换
	List<FacilityBean> selectAroundConvert(Double curLng, Double curLat,Double distance,String devType,String surveyResult, String areaCode1, Users user);
	
	FacilityBean convert(Facility facility,Users user);
	
	List<FacilityBean> convert(List<Facility> list,Users user);
	
	/**
	 * 依据给定的设施ID，盘面，和熔纤盘数量自动生成设施熔纤盘
	 * @param devId ：所属设施id
	 * @param side    : A,B 面
	 * @param fiberDiscNum 熔纤盘数量
	 * @param portNum 单个熔纤盘端子数
	 * @return
	 */
	int genFiberdisc(Long devId,String side,Integer fiberDiscNum ,Integer portNum,Integer fromIndex);
	
	int genFiberdisc(Long devId,String side,Integer fiberDiscNum ,Integer portNum,Integer fromRowIndex,Integer fromColIndex);
	
	int genFiberdisc(Long devId,String side,Integer fiberDiscNum ,Integer portNum,Integer fromRowIndex,Integer fromColIndex,String model);
	/**
	 * 依据给定的设施ID，盘面，和制定的熔纤盘序号添加一个熔纤盘
	 * @param devId
	 * @param side
	 * @param discRowNo
	 * @return
	 */
	int addFiberdisc(Long devId,String side,Integer discRowNo);
	
	/**
	 * 光纤成端(单熔纤盘）
	 * @param devId  设施ID
	 * @param sectId  光缆段ID
	 * @param discCode 熔纤盘/插板编号
	 * @param fromFiberNum 起始纤芯
	 * @param endFiberNum	终止纤芯
	 * @param fromPortNum	起始端子序号
	 * @return
	 */
	int fiberLocate(Long devId,Long sectId,String discCode,Integer fromFiberNum,Integer endFiberNum,Integer fromPortNum);
	
	/**
	 * 光纤成端(多熔纤盘连续成端）
	 * @return
	 */
	int fiberLocate(Long devId,Long sectId,String startDiscCode,Integer fromFiberNum,Integer endFiberNum,Integer fromPortNum,String side,String direction);
	/**
	 * 
	* @Title: locateDisc 
	* @Description: TODO(成端) 
	* 修改:创建成端时天剑创建人和时间
	* @param @param info
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 下午4:14:39 
	* @version V1.0
	 */
	int fiberLocate(LocateInfo info);
	/**
	 * 解成端
	 * @param devId
	 * @param sectId
	 * @param fromFiberNum
	 * @param endFiberNum
	 * @return
	 */
	int unFiberLocate(Long devId,Long sectId,Integer fromFiberNum,Integer endFiberNum,Long userId);
	/**
	 * 
	* @Title: unLocateDisc 
	* @Description: TODO(修改:解成端时添加最后修改人和时间) 
	* @param @param devId
	* @param @param sectId
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 下午4:07:06 
	* @version V1.0
	 * @param aorz 
	 */
	int unFiberLocate(Long devId,Long sectId,Integer aorz, Long userId);
	/**
	 * 添加跳纤
	 * @param adevId
	 * @param acode
	 * @param zdevId
	 * @param zcode
	 * @param srvName
	 * @return
	 */
	int addJumper(Long adevId,String acode,Long zdevId,String zcode,String srvName,Long orgId);
	/**
	 * 
	* @Title: addJumper 
	* @Description: TODO(跳纤) 
	* 修改:跳纤时添加创建人和时间
	* @param @param session
	* @param @param jumperInfo
	* @param @return    入参
	* @return Object    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 下午4:16:57 
	* @version V1.0
	 * @param session 
	 */
	int saveJumper(JumperInfos jumperInfos,HttpServletRequest request,Users users);
	
	int saveJumper(JumperInfo jumperInfos,HttpServletRequest request,Users users);
	
    List<LockKey> queryKeyList(Long uid,Facility fa);
    
    /**
     * 根据mac地址获取设施信息
     * @author YHT
     * @time   2016年7月14日 下午1:35:41
     * @param session
     * @param fa
     * @return
     */
    ResultData facilityFromdevMac(HttpSession session,Facility fa);
    
    PageBean queryByConditionBean(FacilityConditionBean bean,Users user,PageBean pb);
    
    
    PageBean queryFacilityByPosition(FacilityConditionBean bean,PageBean pb);
    
	/**
	 * 根据地区code获取设施坐标并根据坐标获取半径内的所有设施
	 */
    PageBean queryDistanceFacilityByCondition(FacilityConditionBean bean,
			Users user, PageBean pb);

    /**
     * 查询设施详情
     * @param devId
     * @return
     */
    FacilityBean selectDetailById(Long devId);
    
    List<Facility> queryListByAccessIds(String ids);
    
    /**
     * 手机端 查询设施总数
     * @author YHT
     * @time   2016年8月7日 下午3:07:34
     * @param bean
     * @return
     */
    Integer queryCountByConditionBean(FacilityConditionBean bean);

	void exportFacilityTemp(HttpServletResponse response,HttpServletRequest request);

	String importFacilitiesByExcel(HttpServletRequest request,
			HttpServletResponse response,
			MultipartHttpServletRequest multiRequest);

	List<FacilityBean> queryListByContent(String content, Long orgId);
	
	/**
	 * 简单查询  不转换bean
	 * @param bean
	 * @param user
	 * @param pb
	 * @return
	 */
	PageBean simpleQueryByConditionBean(FacilityConditionBean bean,
			Users user, PageBean pb);
	
	/**
	 * 根据devId获取设施信息
	 * @author  YHT 
	 * @date 创建时间：2017年2月27日 下午2:47:52 
	 * @version 1.0
	 * @param devId
	 * @return
	 */
	List<Facility> selectByDevIds(String workId);

	List<Facility> findFacilities(Long orgId);
	/**
	 * 转成百度经纬度
	 * @param orgId 
	 * @return
	 */

	String changeFacility(Long orgId);

	Facility selectByCodeMap(String devCode);

	Facility selectByCode1(Long devId, Long orgId);

	FacilityImgs selectImgById(Long devId);

	Long edit(FacilityVoBeanXY facility, List<FacilityImg> images);

	/**
	 * 井设施保存
	 * @param facilityImgs
	 * @return
	 */
	Long saveWell(FacilityImgs facilityImgs,HttpSession session,HttpServletRequest request);

	List<Facility> selectArea(Long orgId);

	Facility queryFacilityBydevCode(String devCode, Long orgId);
	
	
	FacilityImgs selectWellById(Long devId);
	
	Facility selectByCode2(String devCode);
	
	FacilityImgs selectWellByDevId(Long devId,Long sectId);

	List<Facility> queryByFacility(Long orgId, String areaCode1);

	List<Facility> queryByFacilityCablint(Long orgId, String areaCode1);

	int update(Long devId);

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
	List<Facility> onSearchFacilities(Long orgId, String searchName, String areaCode1);

	List<FacilityAll> queryall();

	//访问机柜,刘沧海,2017-9-28
	List<FacilityForGroups> queryAllForDev(FacilityForGroups bean);

	//导出数据库  刘沧海 2017/10/13
	List<FacilityZF> queryList(Long orgId, String areaCode);

	//导出光交箱 刘沧海 2017/10/17
	List<FacilityAll> queryExportAll(Long orgId, String areaCode);

	//导出机房/机柜  刘沧海  2017/10/17
	List<FacilityAll> queryExportAllJifang(Long orgId, String areaCode);
	
	/**
	 * 资管导入数据专用
	 * @author: YHT
	 * @date: 2017年10月24日 上午10:09:32 
	 * @Title: saveZG  
	 * @param @param facility
	 * @param @return     
	 * @return Long   
	 * @throws
	 */
	Long saveZG(Facility facility);
	
	/**
	 * 资管导入数据专用
	 * @author: YHT
	 * @date: 2017年10月24日 上午10:19:40 
	 * @Title: selectById  
	 * @param @param devId
	 * @param @return     
	 * @return Facility   
	 * @throws
	 */
	Facility selectByIdZG(Long devId);

	/**
	 * 资管导入数据专用
	 * @author: YHT
	 * @date: 2017年10月24日 上午10:38:22 
	 * @Title: genFiberdiscZG  
	 * @param @param devId
	 * @param @param side
	 * @param @param fiberDiscNum
	 * @param @param portNum
	 * @param @param fromRowIndex
	 * @param @param fromColIndex
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int genFiberdiscZG(Long devId,String side,Integer fiberDiscNum ,Integer portNum,Integer fromRowIndex,Integer fromColIndex);

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
	
	Long save(Facility facility);

	/**
	 * 井的需求
	 * 根据条件,名称(首字母)和经纬度范围内查询
	* 
	* @Title: FacilityService.java 
	* @Description: TODO 
	* @param @return
	* @return List<Facility>
	* @author fl
	* @date 2017年11月29日 下午1:43:36
	* @version V1.0
	 * @param map 
	 */
	List<Facility> querywells(String devName, Long distance, String curLng, String curLat);

	/**
	 * 
	* @Title: modifyFacilityState 
	* @Description: 对资管导入设施和新增设施状态进行修改
	* @param @param devId
	* @param @param devStateA
	* @param @param devStateZ
	* @param @param devName
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月1日 下午2:01:20 
	* @version V1.0
	 * @param userId 
	 */
	int updateFacilityState(Users users,Facility facility);

	/**
	 * 
	* @Title: verifyJumpper 
	* @Description: 校验是否有跳纤 
	* @param @param acode1
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月3日 下午5:56:18 
	* @version V1.0
	 */
	int verifyJumpper(String acode1, String zcode1, String acode2, String zcode2);

	/**
	 * 
	 * @Title: recoveryFacilityByState
	 * @Description: 恢复设施数据
	 * @param @param devId
	 * @param @param devState
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月3日 下午5:31:04
	 * @version V1.0
	 */
	int recoveryFacilityByState(Long devId, String devState, Users user);

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
	 * @date 2017年12月5日 下午10:13:37
	 * @version V1.0
	 * @param pb 
	 */
	List<Facility> queryFacilityOdf(Long devId, Integer queryType, PageBean pb);

	/**
	 * 根据编码查询设施
	* 
	* @Title: FacilityService.java 
	* @Description: TODO 
	* @param @param devCode
	* @param @param orgId
	* @param @return
	* @return Facility
	* @author fl
	* @date 2017年12月7日 下午2:21:38
	* @version V1.0
	 */
	Facility selectfacilityByCode(String devCode, Long orgId);

	int updateForDecName(Long userId, String acode, String zcode);

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
	 * @param pb 
	 */
	List<Facility> queryByDevIdForOppo(Facility facility, PageBean pb);

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
	 * 根据汇聚区和机构id查询设施，返回geojson格式
	 * @param: orgId
	 * @param: areaCode1
	 * @return: Map<String,Object>      
	 * @throws
	 */
	Map<String, Object> findFacilitiesGeoPointByOrgId(Long orgId, String areaCode1);
	/**
	 * 
	* @Title: findbyDevId 
	* @Description: 根据设施ID查询光缆段成端状态 
	* @param @param devId
	* @param @return    
	* @return FacilityAsasideCableBean
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午5:29:04 
	* @version V1.0
	 * @param user 
	 */
	List<Facility> findbyDevId(Long devId, Users user);

	/**
	 * 
	* @Title: findCDState 
	* @Description: 根据设施ID查询所属光缆段 
	* @param @param session
	* @param @param devId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午9:27:24 
	* @version V1.0
	 */
	FacilityAsasideCableBean findCDState(Long devId, Long orgId);

	/**
	 * 
	* @Title: deleteOpticalSplitter 
	* @Description: 删除分光器 
	* @param @param devId
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年1月4日 下午3:05:16 
	* @version V1.0
	 */
	int deleteOpticalSplitter(Long devId, Users user);
	/**
	 * 
	* @Title: updateByDevIdAndDevName 
	* @Description: 修改设施名称
	* @param @param session
	* @param @param facility
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月4日 下午2:26:17 
	* @version V1.0
	 * @param users 
	 */
	int updateByDevIdAndDevName(Facility facility, Users users);
	/**
	 * 
	* @Title: findFacilityLists 
	* @Description: 根据地址信息返回设施，如果code不为null,当设施为机柜返回所属机房 
	* @param @param facilityCon
	* @param @param pageSize
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月9日 上午9:29:49 
	* @version V1.0
	 */
	PageBean queryFacilitysByPosition(FacilityConditionBean facilityCon, PageBean pb);

	/**
	 * 整合m/v1/facilityList.htm
	 * m/facilityByDevcode.htm 
	 * m/v1/getAround.htm
	 * 
	* @Title: FacilityService.java 
	* @Description: TODO 
	* @param @param facility
	* @param @param pb
	* @param @param orgId
	* @param @return
	* @return List<FacilityConditionBean>
	* @author fl
	* @date 2018年1月19日 下午2:12:19
	* @version V1.0
	 */
	List<Facility> queryFacilityByConditions(FacilityConditionBean facility, PageBean pb, Long orgId);

	/**
	 * 根据是否有经纬度导出设施数据
	* 
	* @Title: FacilityService.java 
	* @Description: TODO 
	* @param @param orgId
	* @param @param existLngLat
	* @param @return
	* @return List<FacilityAll>
	* @author fl
	* @date 2018年1月24日 下午4:48:43
	* @version V1.0
	 * @param pb 
	 */
	List<FacilityBean> queryExportExisttLngLat(Long orgId, String existLngLat);


	/**
	 * 
	* @Title: queryJumperTerrace 
	* @Description: 查询端子详情 
	* @param @param code
	* @param @return    
	* @return JumperTerraceInfo
	* @author liucanghai 
	* @throws
	* @date 2018年1月24日 下午2:24:46 
	* @version V1.0
	 */
	JumperTerraceInfo queryJumperTerrace(String code);

	/**
	 * 
	* @Title: Object 
	* @Description: 查询已成端光缆 
	* @param     
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年2月1日 上午10:31:55 
	* @version V1.0
	 */
	List<CableSectionBean> queryFinCable(Long groupId);

	/**
	 * 
	* @Title: Object 
	* @Description: 查询直熔光缆 
	* @param     
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年2月1日 上午10:31:55 
	* @version V1.0
	 */
	List<CableSectionBean> queryZhiCable(Long groupId);

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
	* @date 2018年3月15日 下午4:37:24 
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
	 * @date 2018年3月17日 下午4:28:21
	 * @version V1.0
	 */
	int queryByOrgId(Long orgId);


	/**
	 * 保存设施
	* @Title: save2 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param list
	* @param @return    入参
	* @return Long    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月27日 下午4:36:55 
	* @version V1.0
	 */
	Long save2(FacilityVoBeanXY facility, List<FacilityImg> list);
	
	/**
	 * 按位置浏览-高级查询-根据条件返回设备信息
	 * @param areaCode
	 * @param areaCode1
	 * @param devType
	 * @param devName
	 * @param devCode
	 * @param orgId
	 * @return
	 */
	public List<Map<String, Object>> findFacilitysToBdMap(String areaCode, String areaCode1, String devType,
					String devName, String devCode, Long orgId);

//	/**
//	 * 根据汇聚去查询设施
//	* @Title: selectFacilitiesAreaCode1 
//	* @Description: 根据汇聚区查询 
//	* @param @param session
//	* @param @param areaCode1
//	* @param @return    
//	* @return List<Facility>
//	* @author fl 
//	* @throws
//	* @date 2018年2月8日 下午1:48:06 
//	* @version V1.0
//	 */
//	List<Facility> selectFacilitiesAreaCode1(Long orgId, String areaCode1);
	/**
	 * 
	* @Title: queryDevListForIds 
	* @Description: 查询设施下的光缆段的另一端设施ID集合 
	* @param @param devId
	* @param @return    
	* @return FacilityAsasideCableBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月16日 下午4:10:42 
	* @version V1.0
	 */
	List<Facility> queryDevListForIds(Long devId);
	Facility selectByPrimaryKey(Long devId);
	public List<Facility> selectByDevId(Long devId);

	/**
	 * 
	* @Title: workorderSaveJumper 
	* @Description: 光路调度跳纤 
	* @param @param jumperInfo
	* @param @param request
	* @param @param user
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年4月24日 上午9:23:05 
	* @version V1.0
	 */
	int workorderSaveJumper(WorkorderJumperInfo jumperInfo, HttpServletRequest request, Users user);

	/**
	 * 
	* @Title: queryDevRoomByODF 
	* @Description: 根据机柜ID查询所属机房下所有机柜信息 
	* @param @param session
	* @param @param userId
	* @param @param devId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月26日 上午10:37:25 
	* @version V1.0
	 */
	FacilityRoom queryDevRoomByODF(Long devId);

	/**
	 * 
	 * @Title: queryListByContentAndBindCod
	 * @Description: 查询已绑定中控器的设施，并且只有光交箱，光终端盒，光分纤箱，机房
	 * @param @param content
	 * @param @param orgId
	 * @param @return 
	 * @return List<FacilityBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月27日 下午1:50:19
	 * @version V1.0
	 * @param areaCode1 
	 * @param areaCode2 
	 */
	List<FacilityBean> queryListByContentAndBindCod(String content, String areaCode1, String areaCode2, Long orgId);

	/**
	 * 根据设施ID查询机房中机柜列表
	 */
	List<Map<String, Object>> findFacilityByPdevId(Long devId);

	/**
	 * 
	 * @Title: saveFacilityImages
	 * @Description: 保存图片
	 * @param @param request
	 * @param @return 
	 * @return List<String> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月1日 下午1:27:34
	 * @version V1.0
	 * @param user 
	 */
	List<String> saveFacilityImages(HttpServletRequest request, Users user);

	/**
	 * 删除图片文件和数据
	* @Function: FacilityService.java
	* @return：返回结果描述
	* @throws：异常描述
	* @version: v1.0.0
	* @author: FL
	* @date: 2018年10月9日 下午3:22:38
	 */
	int deleteImage(HttpServletRequest request, List<String> imageUrlList, Long devId);

	/**
	 * 装维保存设施(非光交箱)图片接口
	* @Title: saveFacilityImgs 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param picList
	* @param @return    入参
	* @return Long    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月1日 上午10:18:12 
	* @version V1.0
	 * @param request 
	 */
	Long saveFacilityImgs(FacilityVoBeanXY facility, HttpServletRequest request);

	/**
	 * 根据设施ID,查询设施信息
	* @Title: queryFacilityByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return FacilityVoBeanXY    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 上午9:13:15 
	* @version V1.0
	 */
	FacilityVoOpd queryFacilityByDevId(Long devId);

	/**
	 * 查询机柜列表
	* @Title: queryopdFacilityOdf 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param pb
	* @param @return    入参
	* @return List<Facility>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 上午10:42:22 
	* @version V1.0
	 */
	List<FacilityOdf> queryopdFacilityOdf(Long devId, PageBean pb);

	/**
	 * 根据设施ID状态,删除设施
	* @Title: facilityopdDelete 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param devState
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 上午11:08:15 
	* @version V1.0
	 */
	int facilityopdDelete(Long devId, String devState);

	/**
	 * 设施修改保存
	* @Title: saveopdFacilityImgs 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param request
	* @param @return    入参
	* @return Long    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 下午1:20:32 
	* @version V1.0
	 */
	Long saveopdFacilityImgs(FacilityVoBeanXY facility, HttpServletRequest request);

	/**
	 * 
	 * @Title: verifyGroupType
	 * @Description: 验证跳纤两端的所属分组类型
	 * @param @param code
	 * @param @param zcode 
	 * @return void 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月25日 上午11:09:53
	 * @version V1.0
	 */
	void verifyGroupType(String code, String zcode);

	/**
	 * 
	 * @Title: queryFacilityByOrgId
	 * @Description: 根据orgId分组查询
	 * @param @return 
	 * @return List<Facility> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午10:23:14
	 * @version V1.0
	 */
	List<Facility> queryFacilityByOrgId();

	/**
	 * 查询可修改机柜
	* @Title: queryCabinetsByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param routesSort
	* @param @param designroutesId
	* @param @return    入参
	* @return List<FacilityCabinetsInfo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月27日 下午1:55:57 
	* @version V1.0
	 * @param users 
	 * @param devId 
	 */
	List<FacilityCabinetMobile> queryCabinetsByConditions(int routesSort, Long designroutesId, Users users, Long devId);

	/** 
	 * 根据井Id下的所有接头
	* @Title: queryJointByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return List<Facility>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午4:18:57 
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
	* @date 2018年10月11日 下午1:36:19 
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
	* @date 2018年10月11日 下午2:17:28 
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
	* @date 2018年10月11日 下午2:48:45 
	* @version V1.0   
	*/
	List<Map<String, Object>> queryOciiFacilityByPdevId(Long devId);

	/** 
	 * 删除光缆接头
	* @Title: delOciiJointByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param orgId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月16日 下午4:28:59 
	* @version V1.0   
	 * @param devState 
	*/
	int delOciiJointByDevId(Long devId, Long orgId, String devState);

	/** 
	 * 删除接头中光缆直熔【解直熔】
	* @Title: delOciiJointDirectByJointId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param jointId
	* @param @param orgId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月17日 上午9:38:42 
	* @version V1.0   
	*/
	int delOciiJointDirectByJointId(Long jointId, Long orgId);

	/** 
	 * 添加修改直熔
	* @Title: saveOciiJointDirect 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ocJointDirect
	* @param @param orgId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月17日 上午10:18:57 
	* @version V1.0   
	*/
	int saveOciiJointDirect(OcJointDirect ocJointDirect, Users user);

	/** 
	 * 修改/保存接头
	* @Title: saveOciiJoint 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月17日 下午3:38:47 
	* @version V1.0   
	*/
	int saveOciiJoint(Facility facility, Users user);

}
