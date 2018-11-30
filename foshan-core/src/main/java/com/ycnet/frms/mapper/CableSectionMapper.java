package com.ycnet.frms.mapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableSectionZF;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.vo.CableRouteCondition;
import com.ycnet.frms.vo.CableRouteLinks;
import com.ycnet.frms.vo.CableRouteNodes;
import com.ycnet.frms.vo.CableSectionBean;
import com.ycnet.frms.vo.CableSectionConditionBean;
import com.ycnet.frms.vo.CableSectionNotEndBean;
import com.ycnet.frms.vo.CableSectionResultVo;
import com.ycnet.frms.vo.CableSectionWellBean;
import com.ycnet.frms.vo.SectRoute;
import com.ycnet.frms.vo.SectRouteBean;
import com.ycnet.frms.vo.WellBean;
import com.ycnet.frms.vo.mobile.FacilityCabinetMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;

public interface CableSectionMapper {
    int deleteByPrimaryKey(Long sectId);

    int insert(CableSection record);

    int insertSelective(CableSection record);
    
    int insertSelectiveZG(CableSection record);

    CableSection selectByPrimaryKey(Long sectId);

    int updateByPrimaryKeySelective(CableSection record);
    
    int updateByPrimaryKeySelectiveZG(CableSection record);

    int updateByPrimaryKey(CableSection record);
    
    List<CableSection> queryByConditionBean(CableSectionConditionBean bean);
    
    List<CableRouteNodes> queryCableRouteNodes(CableRouteCondition con);
    
    List<CableRouteLinks> queryCableRouteLinks(CableRouteCondition con);
    
    List<CableRouteNodes> queryCableRouteNodesV1(CableRouteCondition con);
    
    List<CableRouteLinks> queryCableRouteLinksV1(CableRouteCondition con);
    
    List<CableSection> selectRelativeSection(Long devId);
    
    CableSection selectByCode(String sectCode);



	List<CableSection> querySectionList(CableSectionConditionBean bean);

	CableSectionConditionBean queryDev(Long sectId);

	List<CableSection> findFiberList(Long orgId, String areaCode1);
	/**
	 * 返回a/z端光纤段列表
	 * @param: orgId		机构id
	 * @param: areaCode1	聚汇区
	 * @throws
	 */
	List<Map<String, Object>> findFiberList2(Long orgId, String areaCode1);

	CableSectionResultVo selectCableSections(Long sectId);

	List<CableSectionBean> querySectionListBySectId(CableSectionConditionBean bean);


	List<CableSectionBean> queryByConditionBeanSect(CableSectionConditionBean bean);

	int deleteByDevId(Long devId);

	List<CableSection> queryByDevId(Long devId);




//	PageBean queryOne(Long orgId, PageBean pb);


	List<SectRouteBean> queryByConditionMap(Map<String, Object> map);
	
	int queryCountByConditionMap(Map<String, Object> map);

	int updateisentering(SectRouteBean record);

	List<Lines> selectisentering(Long id);

	int addisentering(SectRouteBean record);


	int opticalcable(SectRoute sectRoute);

	int insertopticalcables(SectRouteBean record);

	List<Lines> getAnyPortopticalcables(SectRouteBean record);

	//导出数据库  刘沧海 2017/10/13
	List<CableSectionZF> queryCableSectionList(Long orgId, String areaCode);

	
	/**
	 * 根据名称查询光缆段
	 * @author: YHT
	 * @date: 2017年10月19日 上午11:50:52 
	 * @Title: selectByName  
	 * @param @param secName
	 * @param @return     
	 * @return CableSection   
	 * @throws
	 */
	CableSection selectByName(String secName);
	
	/**
	 * 资管专用
	 * @author: YHT
	 * @date: 2017年10月24日 上午11:32:04 
	 * @Title: selectByNameZG  
	 * @param @param secName
	 * @param @return     
	 * @return CableSection   
	 * @throws
	 */
	CableSection selectByNameZG(String secName);
	
	/**
	 * @author: 周宇
	 * @date: 2017年11月27日 上午14:43:48
	 * @Title: 根据 dev_id 查询光缆信息  queryByDevIdAZ
	 * @param @param DevIdA
	 * @param @param DevIdZ
	 * @param @return     
	 * @return List<CableSectionBean>   
	 * @throws
	 */
	List<CableSectionBean> queryByDevId(Long devId,Long orgId);
	
	/**
	 * 
	* @Title: queryBysectState 
	* @Description: 按前台传入 sectState值查询tn_cable_section
	* @param @param orgId
	* @param @param sectState
	* @param @return    入参
	* @return List<CableSectionBean>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年11月28日 上午9:15:38 
	* @version V1.0
	 */
	List<CableSectionBean> queryBysectState(Long orgId, String sectState,Long devId);
	/**
	 * 
	* @Title: getIsTerminats 
	* @Description: 判断是否为完整光路
	* @param @param sectId
	* @param @return    入参
	* @return String    返回类型
	* @author 周宇 
	* @throws
	* @date 2017年11月30日 上午9:10:01 
	* @version V1.0
	 */
		int getIsTerminats(Long sectId);
	
	
	/**
	*根据传入的ID和之前的状态修改在表中的状态
		* 对资管导入光缆段和新增光缆段状态进行修改 * 
	* @Title: CableSectionMapper.java 
	* @Description: TODO 
	* @param @param sectId
	* @param @param secState
	* @param @return
	* @return int
	* @author fl
	* @date 2017年12月1日 下午1:54:43
	* @version V1.0
	 */
	int upddateByIdAndState(HashMap<String, Object> map);
	
	/**
	 * 
	* @Title: queryByDevIdOrgId 
	* @Description: 根据设施ID和机构ID查询 
	* @param @param devId
	* @param @param orgId
	* @param @return    
	* @return List<CableSection>
	* @author liucanghai 
	* @throws
	* @date 2017年12月1日 下午2:49:04 
	* @version V1.0
	 */
	List<CableSection> queryByDevIdOrgId(Long devId, Long orgId);
	
	/**
	 * 
	 * @Title: queryCableSectionByDevId
	 * @Description: 查询光缆段列表
	 * @param @param devId
	 * @param @param orgId
	 * @param @return 
	 * @return List<CableSectionBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月5日 下午11:35:30
	 * @version V1.0
	 * @param user 
	 * @param pb 
	 */
	List<CableSectionBean> queryCableSectionByDevId(Users user, Long devId, CableSection cableSection, PageBean pb);
	
	/**
	 * 
	 * @Title: queryZgCableSectionByDevId
	 * @Description: 查询光缆段列表-资管
	 * @param @param devId
	 * @param @param orgId
	 * @param @return 
	 * @return List<CableSectionBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月5日 下午11:37:43
	 * @version V1.0
	 * @param user 
	 * @param pb 
	 */
	List<CableSectionBean> queryZgCableSectionByDevId(Users user, Long devId, CableSection cableSection, PageBean pb);
	
	/**
	 * 根据设施ID 查询光缆段信息
	* 
	* @Title: CableSectionMapper.java 
	* @Description: TODO 
	* @param @param devId
	* @param @return
	* @return List<CableSection>
	* @author fl
	* @date 2017年12月5日 下午11:00:59
	* @version V1.0
	 */
	List<CableSection> queryById(String devId);

	List<CableSection> queryFiberNum();

	/**
	 * 
	* @Title: queryByZGDevIdOrgId 
	* @Description: 根据设施ID和机构ID查询资管数据 
	* @param @param devId
	* @param @param orgId
	* @param @return    
	* @return List<CableSection>
	* @author liucanghai 
	* @throws
	* @date 2017年12月1日 下午2:49:04 
	* @version V1.0
	 */
	List<CableSection> queryByZGDevIdOrgId(Long devId, Long orgId);

	/**
	 * 
	* @Title: queryByNotisDevId 
	* @Description: 根据设施ID查询不属于此设施ID的光缆段 
	* @param @param CableSectionBean
	* @param @return    
	* @return CableSectionBean
	* @author liucanghai 
	* @throws
	* @date 2017年12月16日 上午11:17:52 
	* @version V1.0
	 */
	CableSectionBean queryByNotisDevId(CableSectionBean cableSectionBean);

	
	/**
	 * 
	* @Title: queryForInIds 
	* @Description: 根据光缆段ID集合条件查询光缆段 
	* @param @param cableSectionBean
	* @param @param ids
	* @param @param pb
	* @param @return    
	* @return List<CableSection>
	* @author liucanghai 
	* @throws
	* @date 2017年12月16日 下午2:20:40 
	* @version V1.0
	 */
	List<CableSection> queryForInIds(CableSectionBean cableSectionBean, String ids, PageBean pb);

	/**
	 * 根据设施ID
	* @Title: queryNoDeleteByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<CableSection>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月16日 下午10:54:05 
	* @version V1.0
	 */
	List<CableSection> queryNoDeleteByDevId(Long devId);
	
	/**
	 * 根据设施ID查询非045的cs
	* @Title: queryNODeleteCSByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<CableSection>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月16日 下午10:54:05 
	* @version V1.0
	 */
	List<CableSectionBean> queryNODeleteCSByDevId(long devId);


	/**
	 * 
	* @Title: queryAsasideTwoCD 
	* @Description: 根据ID查询光缆段两端都成端 
	* @param @param devId
	* @param @param orgId
	* @param @return    
	* @return CableSection
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午5:39:34 
	* @version V1.0
	 */
	List<CableSection> queryAsasideTwoCD(Long devId, Long orgId);

	/**
	 * 
	* @Title: queryAsasideOneCD 
	* @Description: 查询有一端成端的光缆段 
	* @param @param devId
	* @param @param orgId
	* @param @return    
	* @return List<CableSection>
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午10:39:29 
	* @version V1.0
	 */
	List<CableSection> queryAsasideOneCD(Long devId, Long orgId);

	/**
	 * 
	* @Title: queryAsasideZero 
	* @Description: 查询两端都没有成端的光缆段 
	* @param @param devId
	* @param @param orgId
	* @param @return    
	* @return List<CableSection>
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午11:14:19 
	* @version V1.0
	 */
	List<CableSection> queryAsasideZero(Long devId, Long orgId);
	/**
	 * 
	* @Title: queryCSBySecName 
	* @Description: 根据A端所属光缆名称和Z段所属光缆名称模糊查询光缆段
	* @param @param secNameA
	* @param @param secNameZ
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月21日 下午2:26:16 
	* @version V1.0
	 * @param pb 
	 */
	List<CableSectionBean> queryCSBySecName(String devNameA, String devNameZ, PageBean pb);

	/**
	 * 根据设施ID 查询机房关联光缆段和两端设施
	* @Title: queryCableSectionsByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param orgId
	* @param @return    入参
	* @return List<CableSection>    返回类型
	* @author fl
	* @throws
	* @date 2017年12月27日 上午10:05:11 
	* @version V1.0
	 */
	List<CableSectionBean> queryCableSectionsByDevId(Long devId, Long orgId);
	/**
	 * 
	* @Title: queryCableSectionAndAZDescBySectId 
	* @Description: 根据devId查询光缆段信息及两端信息
	* @param @param sectId
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author zy 
	* @throws
	* @date 2018年1月11日 下午2:48:29 
	* @version V1.0
	 */
	List<CableSectionResultVo> queryCableSectionAndAZDescBySectId(Long sectId, Long orgId);

	/**
	 * 根据两个devId查询是否存在光缆段
	* 
	* @Title: CableSectionMapper.java 
	* @Description: TODO 
	* @param @param devIdA
	* @param @param devIdZ
	* @param @return
	* @return CableSection
	* @author fl
	* @date 2018年1月15日 下午12:48:41
	* @version V1.0
	 */
	List<CableSection> queryexistCableSectionByDevIds(Long devIdA, Long devIdZ);

	/**
	 * 
	 * @Title: queryCaByDevId
	 * @Description: 根据devId和orgId查询
	 * @param @param orgId
	 * @param @param devId
	 * @param @return 
	 * @return List<CableSection> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月25日 上午11:26:36
	 * @version V1.0
	 */
	List<CableSection> queryCaByDevId(Long orgId, Long devId);

	/**
	 * 
	* @Title: queryBySectIdBean 
	* @Description: 根据ID查询光缆段 
	* @param @param sectId
	* @param @return    
	* @return CableSection
	* @author liucanghai 
	* @throws
	* @date 2018年2月1日 下午2:45:38 
	* @version V1.0
	 */
	CableSectionBean queryBySectIdBean(Long sectId);

	/**
	 * 根据用户ID 查询未成端光缆
	* @Title: queryNotEndCableSections 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @return    入参
	* @return List<CableSectionNotEndBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午1:23:05 
	* @version V1.0
	 * @param pb 
	 * @param orgId 
	 * @param isCheckAll 
	 * @param bean 
	 */
	List<CableSectionNotEndBean> queryNotEndCableSections(Long userId, PageBean pb, Long orgId, int isCheckAll, CableSectionNotEndBean bean);

	/**
	 * 根据用户ID,查询成端不一致光缆段
	* @Title: querydiscordCableSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @param pb
	* @param @return    入参
	* @return List<CableSectionNotEndBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午2:09:29 
	* @version V1.0
	 * @param orgId 
	 * @param isCheckAll 
	 * @param bean 
	 */
	List<CableSectionNotEndBean> querydiscordCableSection(Long userId, PageBean pb, Long orgId, int isCheckAll, CableSectionNotEndBean bean);

	/**
	 * 根据机构ID,查询重复光缆段
	* @Title: queryRepetitionCableSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @return    入参
	* @return List<CableSectionNotEndBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午2:39:32 
	* @version V1.0
	 * @param isCheckAll 
	 * @param orgId 
	 * @param secName 
	 */
	List<CableSectionNotEndBean> queryRepetitionCableSection(Long userId, Long orgId, PageBean pb, int isCheckAll, String secName);

	/**
	 * 根据设施ID,查询重复光缆段
	* @Title: queryRepetitionCableSectionBydevIds 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @param devIdA
	* @param @param devIdZ
	* @param @return    入参
	* @return List<CableSectionNotEndBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午3:37:20 
	* @version V1.0
	 */
	List<CableSectionNotEndBean> queryRepetitionCableSectionBydevIds(Long orgId, PageBean pb, Long devIdA, Long devIdZ);

	/**
	 * 根据
	* @Title: selectByZgSecNameAndTime 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param a
	* @param @param createTime
	* @param @return    入参
	* @return CableSection    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年3月31日 上午9:55:05 
	* @version V1.0
	 */
	CableSection selectByZgSecNameAndTime(Long orgId, String zgSecName, String createTime);

	/**
	 * 查询所属光缆段及所属井列表
	* @Title: queryopdCableSectionByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<CableSectionWellBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 上午10:26:06 
	* @version V1.0
	 */
	List<CableSectionWellBean> queryopdCableSectionByDevId(Long devId);

	/**
	 * 查询可修改的机柜
	* @Title: queryByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param zfacility
	* @param @param afacility
	* @param @param adevId
	* @param @param zdevId
	* @param @return    入参
	* @return List<FacilityCabinetsInfo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月27日 下午2:15:27 
	* @version V1.0
	 * @param zdevId 
	 */
	List<FacilityCabinetMobile> queryByConditions(Long orgId, Long pdevId,Long devId, Long zdevId);


}