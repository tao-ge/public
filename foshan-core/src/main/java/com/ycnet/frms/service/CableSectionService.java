package com.ycnet.frms.service;

import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableSectionZF;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.vo.CableRoute;
import com.ycnet.frms.vo.CableRouteCondition;
import com.ycnet.frms.vo.CableRouteLinks;
import com.ycnet.frms.vo.CableRouteNodes;
import com.ycnet.frms.vo.CableSectionBean;
import com.ycnet.frms.vo.CableSectionConditionBean;
import com.ycnet.frms.vo.CableSectionNotEndBean;
import com.ycnet.frms.vo.CableSectionResultVo;
import com.ycnet.frms.vo.CableSectionWellBean;
import com.ycnet.frms.vo.WellBean;

public interface CableSectionService {
	
    Long save(CableSection cableSection);	
    
    CableSection selectById(Long sectId);
    
	List<CableSection> queryByConditionBean(CableSectionConditionBean bean);
	
	CableSectionBean convert(CableSection cableSection);
	
	List<CableSectionBean> convert(List<CableSection> list);
	
	List<CableSectionBean> queryBeanByConditionBean(CableSectionConditionBean bean);
	
	int delete(Long sectId);
	
    List<CableRouteNodes> queryCableRouteNodes(CableRouteCondition con);
    
    List<CableRouteLinks> queryCableRouteLinks(CableRouteCondition con);
    
    List<CableRouteNodes> queryCableRouteNodesV1(CableRouteCondition con);
    
    List<CableRouteLinks> queryCableRouteLinksV1(CableRouteCondition con);
	
	CableRoute queryCableRoute(Long devId);
	
	CableSection selectByCode(String sectCode);

	List<CableSection> querySectionList(CableSectionConditionBean bean);

	CableSectionResultVo queryDev(Long sectId);

	List<CableSection> findFiber(Long orgId, String areaCode1);
	
	/**
	 * 返回a/z端光纤段列表
	 * @param: orgId		机构id
	 * @param: areaCode1	聚汇区
	 * @throws
	 */
	List<Map<String, Object>> findFiber2(Long orgId, String areaCode1);

	/**
	 * 根据bean查询光缆段信息（带是否成端）
	 * @param bean
	 * @return
	 */
	List<CableSectionBean> querySectionListBySectId(CableSectionConditionBean bean);

	/**
	 * 根据bean查询光缆段信息（带是否成端）
	 * @param bean
	 * @return
	 */
	List<CableSectionBean> queryByConditionBeanSect(CableSectionConditionBean bean);

	//导出数据库  刘沧海 2017/10/13
	List<CableSectionZF> queryCableSectionList(Long orgId, String areaCode);
	/**
	 * 
	* @Title: queryByDevIdAZ 
	* @Description: TODO(根据 dev_id 查询光缆信息) 
	* @param @param devId
	* @param @param orgId
	* @param @return    入参
	* @return List<CableSectionBean>    返回类型
	* @author 周宇（作者） 
	* @throws
	* @date 2017年11月28日 上午9:13:37 
	* @version V1.0
	 */
	List<CableSectionBean> queryByDevId(Long devId, Long orgId) ;
	/**
	 * 
	* @Title: queryBysectState 
	* @Description: 按前台传入 sectState值查询tn_cable_section
	* @param @param orgId
	* @param @param sectState
	* @param @return    入参
	* @return List<CableSection>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年11月28日 上午9:15:38 
	* @version V1.0
	 */
	List<CableSectionBean> queryBysectState(Long orgId, String sectState,Long devid);

	/**
	 * 
	 * @Title: modifyCableSection
	 * @Description: 修改光缆段信息
	 * @param @param sectId
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年11月28日 下午1:38:28
	 * @version V1.0
	 */
	int modifyCableSection(CableSection ca, Users user);

	/**
	 * 根据传入的ID和之前的状态修改在表中的状态
	* 对资管导入光缆段和新增光缆段状态进行修改 
	* @Title: CableSectionController.java 
	* @Description: TODO 
	* @param @return
	* @return Result
	* @author fl
	 * @param user 
	* @date 2017年12月1日 下午1:42:19
	* @versi V1.0
	*/
	int upddateByIdAndState(Users user, CableSection cs);

	/**
	 * 
	 * @Title: recoveryCableSectionByState
	 * @Description: 根据光缆状态恢复光缆数据
	 * @param @param sectId
	 * @param @param sectState
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月3日 下午3:09:09
	 * @version V1.0
	 */
	int recoveryCableSectionByState(Long sectId, String sectState, Users user);

	/**
	 * 
	 * @Title: queryCableSectionByDevId
	 * @Description: 查询光缆段列表
	 * @param @param devId
	 * @param @param orgId
	 * @param @param queryType
	 * @param @return 
	 * @return List<CableSectionBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月5日 下午11:32:39
	 * @version V1.0
	 * @param userUsers 
	 * @param pb 
	 */
	List<CableSectionBean> queryCableSectionList(Users user, Long devId, CableSection cableSection, Integer queryType,Integer cableType, PageBean pb);

	/**
	 * 根据设施ID 查询光缆段信息
	* 
	* @Title: FacilityController.java 
	* @Description: TODO 
	* @param @return
	* @return Result
	* @author fl
	* @date 2017年12月5日 下午10:01:34
	* @version V1.0
	 */
	List<CableSection> queryById(String devId);


	int insertByCable();

	/**
	 * 
	* @Title: splitRouteText 
	* @Description: TODO(拆分光路) 
	* @param @return    入参
	* @return Object    返回类型
	* @author 刘沧海（作者） 
	* @throws
	* @date 2017年12月12日 下午3:25:38 
	* @version V1.0
	 */
	int splitRouteText();

	/**
	 * 
	* @Title: insertCableSection 
	* @Description: TODO(插入光缆段) 
	* @param @param session
	* @param @param list
	* @param @return    入参
	* @return Object    返回类型
	* @author 刘沧海（作者） 
	* @throws
	* @date 2017年12月13日 下午8:10:00 
	* @version V1.0
	 */
	int updateExtractCableSection(List<CableSection> list, Users user);

	/**
	 * 
	* @Title: updateAttachedCableSection 
	* @Description: TODO(挂靠光缆段) 
	* @param @param session
	* @param @param list
	* @param @return    入参
	* @return Object    返回类型
	* @author 尹海涛（作者） 
	* @throws
	* @date 2017年12月14日 下午12:10:33 
	* @version V1.0
	 */
	int updateAttachedCableSection(List<CableSection> list, Users user);

	/**
	 * 
	* @Title: queryDirectmelCableSection 
	* @Description: 获取可能经过此设施并进行直熔的光缆  
	* @param @param session
	* @param @param CableSectionBean
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月16日 上午11:05:36 
	* @version V1.0
	 * @param pb 
	 */
	List<CableSection> queryDirectmelCableSection(CableSectionBean cableSectionBean, PageBean pb);
	
	/**
	 * 
	* @Title: queryCableSectionAboutInvestment 
	* @Description: 查询光缆段列表，返回直融信息 
	* @param @param devId
	* @param @param orgId
	* @param @return    入参
	* @return Object    返回类型
	* @author 周宇 
	* @throws
	* @date 2017年12月17日 下午2:24:23 
	* @version V1.0
	 */
	List<CableSectionBean> queryCableSectionAboutInvestment(long devId, long orgId);

	/**
	 * 根据设施ID查询光缆段
	* @Title: querySectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param devId
	* @param @return    入参
	* @return List<CableSectionBean>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月16日 下午10:14:05 
	* @version V1.0
	 */
	List<CableSection> querySectByDevId(Long orgId, Long devId);

	int pdhcopy();
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
	List<CableSectionBean> queryCSBySecName(String devNameA,String devNameZ, PageBean pb);

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
	* @date 2017年12月27日 上午9:58:12 
	* @version V1.0
	 */
	List<CableSectionBean> queryCableSectionsByDevId(Long devId, Long orgId);

	/**
	 * 
	* @Title: queryCableSectionWell 
	* @Description: 根据光缆段查询光联井信息 
	* @param @param sectId
	* @param @return    
	* @return List<Well>
	* @author liucanghai 
	* @throws
	* @date 2018年1月3日 下午1:50:24 
	* @version V1.0
	 * @param orgId 
	 */
	List<WellBean> queryCableSectionWell(Long sectId, Long orgId);
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
	 * 
	* @Title: insertionCableSe 
	* @Description: 插入光缆段长度 
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年1月11日 下午5:56:33 
	* @version V1.0
	 */
	int insertionCableSe();

	/**
	 * 根据两个devId查询是否存在光缆段
	* 
	* @Title: CableSectionService.java 
	* @Description: TODO 
	* @param @param devIdA
	* @param @param devIdZ
	* @param @return
	* @return CableSection
	* @author fl
	* @date 2018年1月15日 下午12:45:42
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
	 * @date 2018年1月25日 上午11:23:44
	 * @version V1.0
	 */
	List<CableSection> queryCaByDevId(Long orgId, Long devId);

	/**
	 * 根据用户ID 查询未成端光缆
	* @Title: queryNotEndCableSections 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @return    入参
	* @return List<CableSectionNotEndBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午1:21:27 
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
	* @date 2018年3月15日 下午2:08:05 
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
	 * @param long1 
	 * @param isCheckAll 
	 * @param secName 
	 */
	List<CableSectionNotEndBean> queryRepetitionCableSection(Long userId, Long orgId, PageBean pb, int isCheckAll, String secName);

	/**
	 * 根据设施ID,查询重复光缆段
	* @Title: queryRepetitionCableSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @param devIdA
	* @param @param devIdZ
	* @param @return    入参
	* @return List<CableSectionNotEndBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午3:31:22 
	* @version V1.0
	 */
	List<CableSectionNotEndBean> queryRepetitionCableSectionBydevIds(Long orgId, PageBean pb, Long devIdA, Long devIdZ);

	/**
	 * 
	* @Title: queryForDevId 
	* @Description: 根据设施ID查询光缆段集合 
	* @param @param devId
	* @param @return    
	* @return List<CableSection>
	* @author liucanghai 
	* @throws
	* @date 2018年4月16日 下午4:48:27 
	* @version V1.0
	 */
	List<CableSection> queryForDevId(Long devId);

	/**
	 * 查询所属光缆段及所属井列表
	* @Title: queryopdCableSectionByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<CableSectionWellBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 上午10:23:24 
	* @version V1.0
	 */
	List<CableSectionWellBean> queryopdCableSectionByDevId(Long devId);
}
