package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityForGroups;
import com.ycnet.frms.bean.FacilityImgs;
import com.ycnet.frms.bean.FacilityZF;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.vo.ExportJumper;
import com.ycnet.frms.vo.FacilityAll;
import com.ycnet.frms.vo.FacilityAsasideCableBean;
import com.ycnet.frms.vo.FacilityBean;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityFromMac;
import com.ycnet.frms.vo.FacilityVoBean;
import com.ycnet.frms.vo.LockKey;
import com.ycnet.frms.vo.mobile.FacilityOdf;
import com.ycnet.frms.vo.mobile.ocii.IndexInBean;
import com.ycnet.frms.vo.mobile.ocii.IndexPoint;
import com.ycnet.frms.vo.mobile.ocii.OciiFacility;
import com.ycnet.frms.vo.mobile.ocii.OciiFacilityBean;

@Repository("facilityMapper")
public class FacilityMapperImpl extends  BaseSqlSupport 
		implements FacilityMapper{
	
		@Override
		public int insert(Facility record)
		{
			return this.insert("com.ycnet.frms.mapper.FacilityMapper.insert",record);
		}
	
		@Override
		public Facility selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.FacilityMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Facility record)
		{
			return this.insert("com.ycnet.frms.mapper.FacilityMapper.insertSelective",record);
		}
		
		@Override
		public int insertSelectiveZG(Facility record)
		{
			return this.insert("com.ycnet.frms.mapper.FacilityMapper.insertSelectiveZG",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Facility record)
		{
			return this.update("com.ycnet.frms.mapper.FacilityMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Facility record)
		{
			return this.update("com.ycnet.frms.mapper.FacilityMapper.updateByPrimaryKey",record);
		}

		@Override
		public Facility selectByCode(String devCode) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.selectByCode",devCode);
		}

		@Override
		public List<Facility> queryByConditionBean(FacilityConditionBean bean) {
			//System.out.println(this.count("com.ycnet.frms.mapper.FacilityMapper.queryByConditionBean", bean));
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryByConditionBean", bean);
		}
		
		/**
		 * 按位置浏览-高级查询-根据条件返回设备信息
		 */
		public List<Map<String, Object>> findFacilitysToBdMap(Map<String, Object> params){
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.findFacilitysToBdMap", params);
		}

		@Override
		public List<Facility> selectAround(Map<String, Object> param) {
			
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.selectAround",param);
		}

		@Override
		public String queryObdMaxCode(FacilityConditionBean param) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryObdMaxCode",param);
		}
		
		@Override
		public List<LockKey> queryKeyListByMap(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryKeyListByMap",map);
		}
		
		@Override
		public FacilityFromMac facilityFromdevMac(Map<String,Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.facilityFromdevMac",map);
		}

		/*
		 * 查询数据列表按map zy1/16改
		 * @see com.ycnet.frms.mapper.FacilityMapper#queryByConditionMap(java.util.Map)
		 */
		@Override
		public List<Facility> queryByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			//System.out.println(this.count("com.ycnet.frms.mapper.FacilityMapper.queryByConditionMap", map));
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryByConditionMapZY",map);
		}

		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {			
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryCountByConditionMap", map);
		}

		@Override
		public List<Facility> queryListByAccessIds(String[] ids) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryListByAccessIds",ids);
		}

		@Override
		public Integer queryCountByConditionBean(FacilityConditionBean bean) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryCountByConditionBean", bean);
		}
		//zhouyu 1/4修改返回值类型facility-facilityBean
		@Override
		public List<FacilityBean> queryListByContent(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryListByContent",map);
		}

		@Override
		public Integer countFacilityByPosition(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.countFacilityByPosition", map);
		}

		@Override
		public List<Facility> selectFacilityByPosition(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.selectFacilityByPosition",map);
		}
		
		@Override
		public List<Facility> findFacilities(Long orgId) {
			
			 return this.selectList("com.ycnet.frms.mapper.FacilityMapper.findFacilities",orgId);
		}

		@Override
		public int countFacilities(Long orgId) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.countFacilities",orgId);
		}

		@Override
		public List<Facility> findNoChangeFacilityList(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.findNoChangeFacilityList", map);
		}


		@Override
		public Facility selectByCodeXYId(Long devId) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.selectByCodeXYId", devId);
		}

		@Override
		public Facility queryByDevId(Long devId) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryByDevId", devId);
		}

		@Override
		public Facility selectByCodeMap(String devCode) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.selectByCodeMap", devCode);
		}

		@Override
		public Facility selectByCode1(String devCode, Long orgId) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("devCode", devCode);
			map.put("orgId", orgId);
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.selectByCode1", map);
		}

		@Override
		public FacilityImgs selectImgById(Long devId) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.selectImgById",devId);
		}

		@Override
		public List<Facility> selectArea(Long orgId) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.selectArea", orgId);
		}

		@Override
		public Facility queryFacilityBydevCode(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryFacilityBydevCode",map);
		}

		@Override
		public List<Facility> selectByPdevId(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.selectByPdevId",devId);
		}

		@Override
		public List<Facility> queryByFacility(Long orgId, String areaCode1) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryByFacility", map);
		}

		@Override
		public List<Facility> queryByFacilityCablint(Long orgId, String areaCode1) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryByFacilityCablint",map);
		}

		/**
		 * 用于删除设备
		 */
		@Override
		public int update(Facility facility) {
			return this.update("com.ycnet.frms.mapper.FacilityMapper.updateByPrimaryKeySelective",facility);
		}

		@Override
		public List<Facility> selectByPdevIdCablint(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.selectByPdevIdCablint", devId);
		}

		@Override
		public List<Facility> querylllll() {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.querylllll");
		}

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
		@Override
		public List<Facility> onSearchFacilities(Long orgId, String searchName,String areaCode1) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("searchName", searchName);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.onSearchFacilities",map);
		}

		@Override
		public List<FacilityAll> queryall() {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryall");
		}

		//访问机柜,刘沧海,2017-9-28
		@Override
		public List<FacilityForGroups> queryAllForDev(FacilityForGroups bean) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryAllForDev", bean);
		}

		//导出数据库  刘沧海 2017/10/13
		@Override
		public List<FacilityZF> queryList(Long orgId,String areaCode) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode", areaCode);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryList", map);
		}

		//导出光交箱 刘沧海 2017/10/17
		@Override
		public List<FacilityAll> queryExportAll(Long orgId, String areaCode) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode", areaCode);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryExportAll",map);
		}

		//导出机房/机柜  刘沧海  2017/10/17
		@Override
		public List<FacilityAll> queryExportAllJifang(Long orgId, String areaCode) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode", areaCode);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryExportAllJifang",map);
		}

		@Override
		public Facility selectByDevName(Long orgId, String devName) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("devName", devName);
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.selectByDevName",map);
		}

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
		@Override
		public List<Facility> queryByConditionBeanXiugai(FacilityConditionBean bean) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryByConditionBeanXiugai", bean);
		}

		@Override
		public Facility selectByPrimaryKeyZG(Long id) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.selectByPrimaryKeyZG",id);
		}

		@Override
		public Facility selectByDevNameZG(Long orgId, String devName) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("devName", devName);
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.selectByDevNameZG",map);
		}

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
		@Override
		public List<ExportJumper> queryJumperGJX() {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryJumperGJX");
		}

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
		@Override
		public List<ExportJumper> queryJumperJifang() {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryJumperJifang");
		}

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
		@Override
		public List<ExportJumper> queryJumperFenguangqi() {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryJumperFenguangqi");
		}

		@Override
		public Facility isExitWell(Long orgId, String devName, String longitude, String latitude) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("devName", devName);
			map.put("longitude", longitude);
			map.put("latitude", latitude);
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.isExitWell",map);
		}

		/**
		 * 井的需求
		 * 根据条件,名称(首字母)和经纬度范围内查询
		 */
		@Override
		public List<Facility> querywells(HashMap<String, Object> map) {
			
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.querywells",map);
		}

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
		@Override
		public Facility selectByDevDesc(String devDesc) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.selectByDevDesc",devDesc);
		}

		/**
		 * 查询子设施列表
		 */
		@Override
		public List<Facility> queryFacilityOdf(Long devId, Integer queryType, PageBean pb) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("pb", pb);
			map.put("queryType", queryType);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryFacilityOdf",map);
		}

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
		@Override
		public List<Facility> queryByDevIdForOppo(Facility facility, PageBean pb) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("facility", facility);
			map.put("pb", pb);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryByDevIdForOppo",map);
		}

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
		@Override
		public List<Map<String, Object>> findFacilitiesAreaCode1(Long orgId, String areaCode1) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.findFacilitiesAreaCode1",map);
		}
		
		/**
		 * 根据汇聚区和机构id查询
		 * @param orgId
		 * @param areaCode1
		 * @return
		 */
		@Override
		public List<Map<String, Object>> findFacilitiesACode1ByOrgId(Long orgId, String areaCode1) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.findFacilitiesACode1ByOrgId",map);
		}

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
		@Override
		public FacilityAsasideCableBean queryForFacilityIds(Long devId, Long orgId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("orgId", orgId);
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryForFacilityIds", map);
		}

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
		@Override
		public List<Facility> queryInfacilityIds(String ids) {
//			_parameter
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryInfacilityIds", ids);
		}

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
		@Override
		public Facility queryRepetitionDevName(Facility facility) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryRepetitionDevName", facility);
		}

		@Override
		public Integer countFacilitysByPosition(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.countFacilitysByPosition", map);
		}

		@Override
		public List<Facility> selectFacilitysByPosition(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.selectFacilitysByPosition",map);
		}

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
		@Override
		public List<Facility> queryFacilityByConditions(FacilityConditionBean facility, PageBean pb,Long orgId) {
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("facility", facility);
			map.put("pb", pb);
			map.put("orgId", orgId);
			return selectList("com.ycnet.frms.mapper.FacilityMapper.queryFacilityByConditions",map);
		}

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
		@Override
		public List<Facility> queryByConditionsMap(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryByConditionsMap", map);
		}

		/**
		 * 设施列表查询总条数
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
		@Override
		public Integer queryCountByConditionsMap(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryCountByConditionsMap", map);
		}

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
		@Override
		public List<FacilityBean> queryExportExisttLngLat(Long orgId, String existLngLat) {
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("existLngLat", existLngLat);
			map.put("orgId", orgId);
			return selectList("com.ycnet.frms.mapper.FacilityMapper.queryExportExisttLngLat", map);
		}

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
		@Override
		public List<Facility> queryByConditionBeanInspect(FacilityConditionBean bean) {
			return selectList("com.ycnet.frms.mapper.FacilityMapper.queryByConditionBeanInspect", bean);
		}

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
		 */
		@Override
		public List<FacilityVoBean> queryProblematicalFactilityByUser(Long orgId, PageBean pb,int isCheckAll,Long userId,FacilityVoBean bean) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("pb", pb);
			map.put("orgId", orgId);
			map.put("isCheckAll", isCheckAll);
			map.put("userId", userId);
			map.put("bean", bean);
			return selectList("com.ycnet.frms.mapper.FacilityMapper.queryProblematicalFactilityByUser", map);
		}

		/**
		 * 
		 * @Title: queryByOrgId
		 * @Description: 查询组织机构下是否有设施
		 * @param @param orgId
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年3月17日 下午4:29:51
		 * @version V1.0
		 */
		@Override
		public int queryByOrgId(Long orgId) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryByOrgId", orgId);
		}

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
		@Override
		public List<Facility> selectByDevId(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.selectByDevId", devId);
		}

		/**
		 * 
		 * @Title: queryListByContentAndBindCod
		 * @Description: 查询已绑定中控器的设施，并且只有光交箱，光终端盒，光分纤箱，机房
		 * @param @param map
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年4月27日 下午2:01:39
		 * @version V1.0
		 */
		@Override
		public List<FacilityBean> queryListByContentAndBindCod(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryListByContentAndBindCod",map);
		}
		
		/**
		 * 根据设施ID查询机房中机柜列表
		 */
		@Override
		public List<Map<String, Object>> findFacilityByPdevId(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.findFacilityByPdevId",devId);
		}
//		/**
//		 * 根据汇聚去查询设施
//		* @Title: selectFacilitiesAreaCode1 
//		* @Description: 根据汇聚区查询 
//		* @param @param session
//		* @param @param areaCode1
//		* @param @return    
//		* @return List<Facility>
//		* @author fl 
//		* @throws
//		* @date 2018年2月8日 下午1:48:06 
//		* @version V1.0
//		 */
//		@Override
//		public List<Facility> selectFacilitiesAreaCode1(Long orgId, ArrayList<String> list) {
//			HashMap<String, Object> map = new HashMap<String,Object>();
//			map.put("areaCode", list);
//			map.put("orgId", orgId);
//			return selectList("com.ycnet.frms.mapper.FacilityMapper.selectFacilitiesAreaCode1", map);
//		}

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
		@Override
		public List<FacilityOdf> queryopdFacilityOdf(Long devId, PageBean pb) {
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("pb", pb);
			map.put("devId", devId);
			return selectList("com.ycnet.frms.mapper.FacilityMapper.queryopdFacilityOdf", map);
		}

		/**
		 * 
		 * @Title: queryFacilityByOrgId
		 * @Description: 根据orgId分组查询
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年6月26日 上午10:24:50
		 * @version V1.0
		 */
		@Override
		public List<Facility> queryFacilityByOrgId() {
			return selectList("com.ycnet.frms.mapper.FacilityMapper.queryFacilityByOrgId");
		}

		/**
		 * 
		 * @Title: queryIamRepetitionDevName
		 * @Description: 根据名称查询
		 * @param @param facility
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年7月11日 下午2:45:08
		 * @version V1.0
		 */
		@Override
		public Facility queryIamRepetitionDevName(Facility facility) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryIamRepetitionDevName",facility);
		}

		/**
		 * 
		 * @Title: queryByPdevIdAndDevName
		 * @Description: 根据机房下的机柜名称查询
		 * @param @param pdevId
		 * @param @param devName
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年7月11日 下午4:52:09
		 * @version V1.0
		 */
		@Override
		public Facility queryByPdevIdAndDevName(Long pdevId, String devName) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pdevId", pdevId);
			map.put("devName", devName);
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryByPdevIdAndDevName",map);
		}

		/* (non-Javadoc)
		 * @see com.ycnet.frms.mapper.FacilityMapper#queryJointByWellId(java.lang.Long)
		 */
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
		@Override
		public List<Map<String, Object>> queryJointByWellId(Long wellId) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryJointByWellId",wellId);
		}

		/* (non-Javadoc)
		 * @see com.ycnet.frms.mapper.FacilityMapper#queryOciiFacilityList(com.ycnet.frms.vo.FacilityConditionBean, java.lang.Long)
		 */
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
		*/
		@Override
		public List<OciiFacility> queryOciiFacilityList(FacilityConditionBean facility, PageBean pb, Long orgId) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("facility", facility);
			map.put("orgId", orgId);
			map.put("pb", pb);
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryOciiFacilityList",map);
		}

		/* (non-Javadoc)
		 * @see com.ycnet.frms.mapper.FacilityMapper#queryOciiFacilityByDevId(java.lang.Long)
		 */
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
		@Override
		public OciiFacilityBean queryOciiFacilityByDevId(Long devId) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityMapper.queryOciiFacilityByDevId",devId);
		}

		/* (non-Javadoc)
		 * @see com.ycnet.frms.mapper.FacilityMapper#queryOciiFacilityByPdevId(java.lang.Long)
		 */
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
		@Override
		public List<Map<String, Object>> queryOciiFacilityByPdevId(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.queryOciiFacilityByPdevId",devId);
		}

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
		@Override
		public List<IndexPoint> OcIndexFacByType(IndexInBean iib) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.FacilityMapper.OcIndexFacByType",iib);
		}
}