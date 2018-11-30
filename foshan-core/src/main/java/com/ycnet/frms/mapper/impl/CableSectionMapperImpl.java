package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableSectionZF;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.CableSectionMapper;
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
import com.ycnet.frms.vo.mobile.FacilityCabinetMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;

@Repository("cableSectionMapper")
public class CableSectionMapperImpl extends  BaseSqlSupport 
		implements CableSectionMapper{
	
		@Override
		public int insert(CableSection record)
		{
			return this.insert("com.ycnet.frms.mapper.CableSectionMapper.insert",record);
		}
	
		@Override
		public CableSection selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.CableSectionMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(CableSection record)
		{
			return this.insert("com.ycnet.frms.mapper.CableSectionMapper.insertSelective",record);
		}
		
		@Override
		public int insertSelectiveZG(CableSection record)
		{
			return this.insert("com.ycnet.frms.mapper.CableSectionMapper.insertSelectiveZG",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(CableSection record)
		{
			return this.update("com.ycnet.frms.mapper.CableSectionMapper.updateByPrimaryKeySelective",record);
		}
		
		@Override
		public int updateByPrimaryKeySelectiveZG(CableSection record)
		{
			return this.update("com.ycnet.frms.mapper.CableSectionMapper.updateByPrimaryKeySelectiveZG",record);
		}
	
		@Override
		public int updateByPrimaryKey(CableSection record)
		{
			return this.update("com.ycnet.frms.mapper.CableSectionMapper.updateByPrimaryKey",record);
		}
		@Override
		public List<CableSection> queryByConditionBean(CableSectionConditionBean bean)
		{
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryByConditionBean", bean);
		}

		/**
		 * 查询光缆路由中设施节点
		 */
		@Override
		public List<CableRouteNodes> queryCableRouteNodes(
				CableRouteCondition con) {
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryCableRouteNodes", con);
		}
		
		@Override
		public List<CableRouteNodes> queryCableRouteNodesV1(
				CableRouteCondition con) {
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryCableRouteNodesV1", con);
		}
		/**
		 * 查询光缆路由中设施关系
		 */
		@Override
		public List<CableRouteLinks> queryCableRouteLinks(
				CableRouteCondition con) {
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryCableRouteLinks", con);
		}
		
		@Override
		public List<CableRouteLinks> queryCableRouteLinksV1(
				CableRouteCondition con) {
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryCableRouteLinksV1", con);
		}

		@Override
		public List<CableSection> selectRelativeSection(Long devId) {
			String code = this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.findSection",devId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.selectRelativeSection",code);
		}

		@Override
		public CableSection selectByCode(String sectCode) {
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.selectByCode" ,sectCode);
		}


		@Override
		public List<CableSection> querySectionList(CableSectionConditionBean bean) {
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.querySectionList",bean);
		}

		@Override
		public CableSectionConditionBean queryDev(Long sectId) {
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.queryDev",sectId);
		}

		//A端Z端详情
		@Override
		public CableSectionResultVo selectCableSections(Long sectId) {
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.selectCableSections",sectId);
		}

		
		@Override
		public List<CableSection> findFiberList(Long orgId,String areaCode1) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.findFiberList",map);
		}
		
		

		@Override
		public List<CableSectionBean> querySectionListBySectId(CableSectionConditionBean bean) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.querySectionListBySectId",bean);
		}

		@Override
		public List<CableSectionBean> queryByConditionBeanSect(CableSectionConditionBean bean) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryByConditionBeanSect", bean);
		}

		@Override
		public int deleteByDevId(Long devId) {
			return this.delete("com.ycnet.frms.mapper.CableSectionMapper.deleteByDevId", devId);
		}

		@Override
		public List<CableSection> queryByDevId(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryByDevId", devId);
		}
		
		/**
		 * 查询光缆-光路数据 分页
		 */
		@Override
		public List<SectRouteBean> queryByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryByConditionMap", map);
		}
		
		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.queryCountByConditionMap", map);
		}
		
		//修改表中的数据n_route中的is_entering从0->1 佟盛玮
		@Override
		public int updateisentering(SectRouteBean record) {
			// TODO Auto-generated method stub
			return this.update("com.ycnet.frms.mapper.CableSectionMapper.updateisentering",record);
		}
		@Override
		public int opticalcable(SectRoute sectRoute) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.RouteMapper.opticalcable",sectRoute);
		}
		//tn_task_log表中插入数据  佟盛玮
		@Override
		public int addisentering(SectRouteBean record) {
			// TODO Auto-generated method stub
		
			return this.update("com.ycnet.frms.mapper.CableSectionMapper.addisentering",record);
		}
		//tn_task_log表中插入数据  佟盛玮
		@Override
		public int insertopticalcables(SectRouteBean record) {
			// TODO Auto-generated method stub
		
			return this.update("com.ycnet.frms.mapper.CableSectionMapper.addopticalcables",record);
		}
		@Override
		//查看数据是否插入成功 佟盛玮
		public List<Lines> selectisentering(Long id) {
			
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.selectisentering",id);
		}
		//查看数据是否插入成功 佟盛玮
		@Override
		public List<Lines> getAnyPortopticalcables(SectRouteBean record) {
			
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.selectopticalcables",record);
		}

		//导出数据库  刘沧海 2017/10/13
		@Override
		public List<CableSectionZF> queryCableSectionList(Long orgId,String areaCode) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode", areaCode);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryCableSectionList",map);
		}

		@Override
		public CableSection selectByName(String secName) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.selectByName",secName);
		}
		
		@Override
		public CableSection selectByNameZG(String secName) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.selectByNameZG",secName);
		}
		
		
		/**
		 * @author: 周宇
		 * @date: 2017年11月27日 上午14:43:48
		 * @Title:  按前台传入 sectState值查询  "queryBysectState"
		 * @param @param DevId
		 * @param @return     
		 * @return List<CableSectionBean>   
		 * @throws
		 */
		@Override
		public List<CableSectionBean> queryBysectState(Long orgId,String sectState,Long devId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("sectState", sectState);
			map.put("devId", devId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryBysectState", map);
		}
		/**
		 * @author: 周宇
		 * @date: 2017年11月27日 上午14:43:48
		 * @Title: 根据 dev_id 查询光缆信息  queryByDevId
		 * @param @param DevId
		 * @param @return     
		 * @return List<CableSectionBean>  
		 * @throws
		 */
		@Override
		public List<CableSectionBean> queryByDevId(Long devId, Long orgId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("devId", devId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryByDevIdzy", map);
		}

		/**
		 * @author: 周宇
		 * @date: 2017年11月27日 上午14:43:48
		 * @Title: 获得isTEminat
		 * @param @param sectId
		 * @param @return     
		 * @return String   
		 * @throws
		 */
		@Override
		public int getIsTerminats(Long sectId) {
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.getIsTerminats", sectId);
		}

		

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
		@Override
		public int upddateByIdAndState(HashMap<String, Object> map) {
			return this.update("com.ycnet.frms.mapper.CableSectionMapper.upddateByIdAndState", map);
		}
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
		@Override
		public List<CableSection> queryByDevIdOrgId(Long devId, Long orgId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("devId", devId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryByDevIdOrgId", map);
		}

		/**
		 * 查询光缆段列表
		 */
		@Override
		public List<CableSectionBean> queryCableSectionByDevId(Users user,Long devId, CableSection cableSection,PageBean pb) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("cableSection", cableSection);
			map.put("pb", pb);
			map.put("user", user);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryCableSectionByDevId", map);
		}

		/**
		 * 查询光缆段列表-资管
		 */
		@Override
		public List<CableSectionBean> queryZgCableSectionByDevId(Users user,Long devId, CableSection cableSection,PageBean pb) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("user", user);
			map.put("devId", devId);
			map.put("pb", pb);
			map.put("cableSection", cableSection);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryZgCableSectionByDevId", map);
		}

		/**
		 * 根据设施ID 查询光缆段信息
		 * fl
		 * 
		 */
		@Override
		public List<CableSection> queryById(String devId) {
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryById", devId);
		}

		@Override
		public List<CableSection> queryFiberNum() {
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryFiberNum");
		}

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
		@Override
		public List<CableSection> queryByZGDevIdOrgId(Long devId, Long orgId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("devId", devId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryByZGDevIdOrgId", map);
		}

		/**
		 * 
		* @Title: queryByNotisDevId 
		* @Description: 根据设施ID查询不属于此设施ID的光缆段 
		* @param CableSectionBean
		* @param @return    
		* @return CableSectionBean
		* @author liucanghai 
		* @throws
		* @date 2017年12月16日 上午11:17:52 
		* @version V1.0
		 */
		@Override
		public CableSectionBean queryByNotisDevId(CableSectionBean cableSectionBean) {
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.queryByNotisDevId", cableSectionBean);
		}

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
		@Override
		public List<CableSection> queryForInIds(CableSectionBean cableSectionBean, String ids,PageBean pb) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("cableSectionBean", cableSectionBean);
			map.put("ids", ids);
			map.put("pb", pb);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryForInIds", map);
		}

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
		@Override
		public List<CableSection> queryNoDeleteByDevId(Long devId) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryNoDeleteByDevId", devId);
		}
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
		@Override
		public List<CableSectionBean> queryNODeleteCSByDevId(long devId) {
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryNODeleteCSByDevId", devId);
		}

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
		@Override
		public List<CableSection> queryAsasideTwoCD(Long devId, Long orgId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("orgId", orgId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryAsasideTwoCD", map);
		}

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
		@Override
		public List<CableSection> queryAsasideOneCD(Long devId, Long orgId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("orgId", orgId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryAsasideOneCD", map);
		}

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
		@Override
		public List<CableSection> queryAsasideZero(Long devId, Long orgId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("orgId", orgId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryAsasideZero", map);
		}
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
		 */
		@Override
		public List<CableSectionBean> queryCSBySecName(String devNameA, String devNameZ,PageBean pb) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("DevNameA", devNameA);
			map.put("DevNameZ", devNameZ);
			map.put("pb", pb);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryCSBySecName", map);
		}

		/**
		 * 根据设施ID 查询机房关联光缆段和两端设施
		 * fl
		 */
		@Override
		public List<CableSectionBean> queryCableSectionsByDevId(Long devId, Long orgId) {
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("orgId", orgId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryCableSectionsByDevId", map);
		}
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
		@Override
		public List<CableSectionResultVo> queryCableSectionAndAZDescBySectId(Long sectId, Long orgId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("sectId", sectId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryCableSectionAndAZDescBySectId",map);
		}

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
		@Override
		public List<CableSection> queryexistCableSectionByDevIds(Long devIdA, Long devIdZ) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("devIdA", devIdA);
			map.put("devIdZ", devIdZ);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryexistCableSectionByDevIds",map);
		}

		/**
		 * 
		 * @Title: queryCaByDevId
		 * @Description: 根据devId和orgId查询
		 * @param @param orgId
		 * @param @param devId
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年1月25日 上午11:26:53
		 * @version V1.0
		 */
		@Override
		public List<CableSection> queryCaByDevId(Long orgId, Long devId) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("devId", devId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryCaByDevId",map);
		}

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
		@Override
		public CableSectionBean queryBySectIdBean(Long sectId) {
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.queryBySectIdBean", sectId);
		}

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
		 */
		@Override
		public List<CableSectionNotEndBean> queryNotEndCableSections(Long userId,PageBean pb,Long orgId,int isCheckAll,CableSectionNotEndBean bean) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("userId", userId);
			map.put("pb", pb);
			map.put("orgId", orgId);
			map.put("isCheckAll", isCheckAll);
			map.put("bean", bean);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryNotEndCableSections", map);
		}

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
		 */
		@Override
		public List<CableSectionNotEndBean> querydiscordCableSection(Long userId, PageBean pb,Long orgId,int isCheckAll,CableSectionNotEndBean bean) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("userId", userId);
			map.put("pb", pb);
			map.put("orgId", orgId);
			map.put("isCheckAll", isCheckAll);
			map.put("bean", bean);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.querydiscordCableSection", map);
		}

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
		 */
		@Override
		public List<CableSectionNotEndBean> queryRepetitionCableSection(Long userId,Long orgId, PageBean pb,int isCheckAll,String secName) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("pb", pb);
			map.put("userId", userId);
			map.put("isCheckAll", isCheckAll);
			map.put("secName", secName);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryRepetitionCableSection", map);
		}

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
		@Override
		public List<CableSectionNotEndBean> queryRepetitionCableSectionBydevIds(Long orgId, PageBean pb, Long devIdA,
				Long devIdZ) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("pb", pb);
			map.put("devIdA", devIdA);
			map.put("devIdZ", devIdZ);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryRepetitionCableSectionBydevIds", map);
		}

		@Override
		public CableSection selectByZgSecNameAndTime(Long orgId, String zgSecName, String createTime) {
			// TODO Auto-generated method stub
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("zgSecName", zgSecName);
			map.put("createTime", createTime);
			return this.selectOne("com.ycnet.frms.mapper.CableSectionMapper.selectByZgSecNameAndTime", map);
		}

		/**
		 * 返回a/z端光纤段列表
		 * @param: orgId		机构id
		 * @param: areaCode1	聚汇区
		 * @throws
		 */
		@Override
		public List<Map<String, Object>> findFiberList2(Long orgId, String areaCode1) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.findFiberList2",map);
		}

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
		@Override
		public List<CableSectionWellBean> queryopdCableSectionByDevId(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryopdCableSectionByDevId",devId);
		}

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
		 */
		@Override
		public List<FacilityCabinetMobile> queryByConditions(Long orgId, Long pdevId,Long devId, Long zdevId) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("pdevId", pdevId);
			map.put("devId", devId);
			map.put("zdevId", zdevId);
			return this.selectList("com.ycnet.frms.mapper.CableSectionMapper.queryByConditions",map);
		}
}