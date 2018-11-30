package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscZF;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.vo.CableSectionChart;
import com.ycnet.frms.vo.DifferentPortsBean;
import com.ycnet.frms.vo.Disc;
import com.ycnet.frms.vo.DiscGroup;
import com.ycnet.frms.vo.DiscWX;
import com.ycnet.frms.vo.FiberdiscBean;
import com.ycnet.frms.vo.FiberdiscConditionBean;
import com.ycnet.frms.vo.FiberdiscInfo;
import com.ycnet.frms.vo.Group;
import com.ycnet.frms.vo.Group1;
import com.ycnet.frms.vo.mobile.DiscinfoMobile;

@Repository("fiberdiscMapper")
public class FiberdiscMapperImpl extends  BaseSqlSupport 
		implements FiberdiscMapper{
	
		@Override
		public int insert(Fiberdisc record)
		{
			return this.insert("com.ycnet.frms.mapper.FiberdiscMapper.insert",record);
		}
	
		@Override
		public Fiberdisc selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.FiberdiscMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(Fiberdisc record)
		{
			return this.insert("com.ycnet.frms.mapper.FiberdiscMapper.insertSelective",record);
		}
		
		@Override
		public int insertSelectiveZG(Fiberdisc record)
		{
			return this.insert("com.ycnet.frms.mapper.FiberdiscMapper.insertSelectiveZG",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Fiberdisc record)
		{
			return this.update("com.ycnet.frms.mapper.FiberdiscMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Fiberdisc record)
		{
			return this.update("com.ycnet.frms.mapper.FiberdiscMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Fiberdisc> selectByDevId(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.selectByDevId", devId);
		}

		@Override
		public List<Fiberdisc> queryBydev(FiberdiscConditionBean param) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryBydev",param);
		}
		
		@Override
		public List<Fiberdisc> queryBydevZG(FiberdiscConditionBean param) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryBydevZG",param);
		}

		@Override
		public List<FiberdiscInfo> queryDiscCodes(FiberdiscConditionBean param) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryDiscCodes",param);
		}

		@Override
		public int unlocate(Long devId,Long sectId,Integer fromFiberNum,Integer endFiberNum,Long userId) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("devId", devId);
			param.put("sectId", sectId);
			param.put("fromFiberNum", fromFiberNum);
			param.put("endFiberNum", endFiberNum);
			param.put("userId", userId);
			return this.update("com.ycnet.frms.mapper.FiberdiscMapper.unlocate",param);
		}

		@Override
		public int unlocate(Long devId,Long sectId,Long userId) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("devId", devId);
			param.put("sectId", sectId);
			param.put("userId",userId);
			return this.update("com.ycnet.frms.mapper.FiberdiscMapper.unalllocate",param);
		}
		@Override
		public List<Fiberdisc> queryByFiberNum(Long devId, long sectId,
				Integer fromFiberNum, Integer endFiberNum) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("devId", devId);
			param.put("sectId", sectId);
			param.put("fromFiberNum", fromFiberNum);
			param.put("endFiberNum", endFiberNum);
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryByFiberNum",param);
		}

		@Override
		public List<Group> selectGroup(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.selectGroup",devId);
		}
		
		@Override
		public List<Group> selectGroup1(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.selectGroup1",devId);
		}
		
		@Override
		public Disc selectFiberdisc(Long devId,String side,Long discId)
		{
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("devId",devId);
			param.put("side",side);
			param.put("discId", discId);
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.selectFiberdisc",param);
			
		}
		
		@Override
		public DiscWX selectFiberdiscWX(Long devId,String side,Long discId)
		{
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("devId",devId);
			param.put("side",side);
			param.put("discId", discId);
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.selectFiberdiscWX",param);
			
		}
		
		@Override
		public Fiberdisc selectFiberdiscByCode(Long devId, String port01) {
			FiberdiscConditionBean param = new FiberdiscConditionBean();
			param.setDevId(devId);
			param.setPort01(port01);
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryBydev",param);
		}

		@Override
		public Fiberdisc selectByPort(String port01) {
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.selectByPort",port01);
		}

		@Override
		public List<Fiberdisc> checkOccupyFiberdisc(Long devId,String side,String direction) {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("devId", devId);
			params.put("side", side);
			params.put("direction", direction);
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.checkOccupyFiberdisc",params);
		}

		/**
		 * 
		* @Title: fiberdiscGroup 
		* @Description: 根据光缆段ID和设施ID查询熔纤盘详细信息 
		* @param @param sectId
		* @param @param devId
		* @param @return    
		* @return Object
		* @author liucanghai 
		* @throws
		* @date 2018年5月2日 上午11:53:56 
		* @version V1.0
		 */
		@Override
		public List<DiscGroup> queryDiscGroup(Long sectId, Long devId) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("sectId", sectId);
			param.put("devId", devId);
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryDiscGroup", param);
		}

		@Override
		public int updateBySectId(Long sectId) {
			return this.update("com.ycnet.frms.mapper.FiberdiscMapper.updateBySectId", sectId);
		}

		@Override
		public int deleteFiberDisc(Map<String, Object> map) {
			return this.delete("com.ycnet.frms.mapper.FiberdiscMapper.deleteFiberDisc",map);
		}

		@Override
		public List<Fiberdisc> queryFiberDisc(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryFiberDisc", map);
		}

		@Override
		public List<?> queryDiscSection(Long discId) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryDiscSection",discId);
		}

		@Override
		public Disc selectFiberdiscBysectId(Long devId, String side, Long discId, Long sectId) {
			// TODO Auto-generated method stub
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("devId",devId);
			param.put("side",side);
			param.put("discId", discId);
			param.put("sectId", sectId);
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.selectFiberdiscBysectId",param);
		}

		/**
		 * 用于删除设备
		 */
		@Override
		public int deleteByDevId(Long devId) {
			return this.delete("com.ycnet.frms.mapper.FiberdiscMapper.deleteByDevId", devId);
		}

		//导出数据库 刘沧海 2017/10/13
		@Override
		public List<FiberdiscZF> queryList(Long orgId, String areaCode) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("orgId",orgId);
			map.put("areaCode",areaCode);
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryList",map);
		}

		@Override
		public Fiberdisc queryOccupy(String code) {
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryOccupy",code);
		}
		/**
		 * 
		* @Title: updateIsOccurByDevid 
		* @Description: 更新fiberdisc中的isoccur 
		* @param @param adevId
		* @param @return    入参
		* @return int    返回类型
		* @author zhouyu 
		* @throws
		* @date 2017年12月1日 下午4:16:35 
		* @version V1.0
		 * @param isOccur 
		 */
		@Override
		public int updateIsOccurByDevid(Long devId,String isOccur) {
			Fiberdisc fiberdisc = new Fiberdisc();
			fiberdisc.setIsOccup(isOccur);
			fiberdisc.setDevId(devId);
			return this.update("com.ycnet.frms.mapper.FiberdiscMapper.updateIsOccurByDevId",fiberdisc);
		}

		/**
		 * 根据code查端子
		 */
		@Override
		public Fiberdisc queryByPort(String code) {
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryByPort",code);
		}

		
		/**
		 * 根据端子编码查询
		 */
		@Override
		public Fiberdisc queryId(String port01) {
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryId",port01);
		}

		@Override
		public int updateByIsOccup(Fiberdisc record) {
			return this.update("com.ycnet.frms.mapper.FiberdiscMapper.updateByIsOccup",record);
		}

		//根据熔纤盘编码,和设施ID,查询端子状态
		@Override
		public List<Fiberdisc> queryFiberdiscByCodeId(String discCode,Long devId) {
			HashMap<String, Object> map = new HashMap<String,Object>();
			if (discCode !=null && !"".equals(discCode)) {
				map.put("discCode", discCode);
			}
			if (discCode !=null) {
				map.put("devId", devId);
			}
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryFiberdiscByCodeId", map);
		}
		/**
		 * 
		* @Title: queryFiberdiscForCode 
		* @Description: 根据盘编码和序号查询 
		* @param @param discCode
		* @param @param i
		* @param @return    
		* @return Fiberdisc
		* @author liucanghai 
		* @throws
		* @date 2017年12月17日 下午3:28:37 
		* @version V1.0
		 */
		@Override
		public Fiberdisc queryFiberdiscForCode(String discCode, long i) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("discCode", discCode);
			map.put("discColNo", i);
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryFiberdiscForCode", map);
		}

		/**
		 * 根据discCode删除端子数据
		 */
		@Override
		public int deleteByDiscCode(String discCode) {
			return this.delete("com.ycnet.frms.mapper.FiberdiscMapper.deleteByDiscCode",discCode);
		}

		/**
		 * 
		* @Title: deleteByDevCodeMohu 
		* @Description: 根据接头拼接编码删除端子数据 
		* @param @param devCode
		* @param @return    
		* @return int
		* @author liucanghai 
		* @throws
		* @date 2017年12月20日 下午6:16:26 
		* @version V1.0
		 */
		@Override
		public int deleteByDevCodeMohu(String devCode) {
			return this.delete("com.ycnet.frms.mapper.FiberdiscMapper.deleteByDevCodeMohu",devCode);
		}

		/**
		 * 查询表中的光路、光衰数据
		 */
		@Override
		public List<Fiberdisc> queryByTowDates() {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryByTowDates");
		}

		/**
		 * 查询端子表数据
		 */
		@Override
		public List<Fiberdisc> queryByThings(Long devId, String side) {
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("side", side);
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryByThings", map);
		}
		
	    /**
	     * 刷新熔纤盘（新）
	    * @Title: selectFiberdiscByDiscCode 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param discCode
	    * @param @return    入参
	    * @return DiscWX    返回类型
	    * @author YHT（作者） 
	    * @throws
	    * @date 2017年12月24日 下午1:55:34 
	    * @version V1.0
	     */
		@Override
		public DiscWX selectFiberdiscByDiscCode(String discCode)
		{
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("discCode",discCode);
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.selectFiberdiscByDiscCode",param);
			
		}

		/**
		 * 清空端子数据 fl
		 */
		@Override
		public int updateByDiscCode(String discCode) {
			return this.update("com.ycnet.frms.mapper.FiberdiscMapper.updateByDiscCode", discCode);
		}

		/**
		 * 查询端子数据
		* 
		* @Title: RouteController.java 
		* @Description: TODO 
		* @param @param port
		* @param @param routeText
		* @param @return
		* @return String
		* @author fl
		* @date 2018年1月11日 下午2:06:41
		* @version V1.0
		 */
		@Override
		public FiberdiscBean queryfiberdiscByPort(String port) {
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryfiberdiscByPort", port);
		}

		/**
		 * 根据设施Id查盘分组
		* 
		* @Title: FiberdiscMapper.java 
		* @Description: TODO 
		* @param @param devId
		* @param @return
		* @return List<Group>
		* @author fl
		* @date 2018年1月16日 上午9:17:53
		* @version V1.0
		 */
		@Override
		public List<Group> selectGroupXG(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.selectGroupXG",devId);
		}

		/**
		 * 刷新熔纤盘
		* 
		* @Title: FiberdiscMapper.java 
		* @Description: TODO 
		* @param @param discCode
		* @param @return
		* @return Disc
		* @author fl
		* @date 2018年1月16日 上午9:37:29
		* @version V1.0
		 */
		@Override
		public Disc pushFiberdiscByDiscCode(String discCode) {
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.pushFiberdiscByDiscCode", discCode);
		}

		/**
		 * 
		 * @Title: queryByDiscCode
		 * @Description: 根据discCode查询端子数据
		 * @param @param discCode
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年1月23日 下午2:59:35
		 * @version V1.0
		 */
		@Override
		public List<Fiberdisc> queryByDiscCode(String discCode) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryByDiscCode",discCode);
		}

		/**
		 * 
		* @Title: queryByPort01 
		* @Description: 模糊查询端子编码 
		* @param @param discCode
		* @param @return    
		* @return List<Fiberdisc>
		* @author liucanghai 
		* @throws
		* @date 2018年2月1日 下午2:13:10 
		* @version V1.0
		 */
		@Override
		public List<Fiberdisc> queryByPort01(String discCode) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryByPort01",discCode);
		}


		/**
		 * 
		* @Title: queryByZhiPort01 
		* @Description: 查询直熔 
		* @param @param adiscCode
		* @param @param zdiscCode
		* @param @return    
		* @return List<Fiberdisc>
		* @author liucanghai 
		* @throws
		* @date 2018年2月2日 上午11:34:49 
		* @version V1.0
		 */
		@Override
		public List<FiberdiscBean> queryByZhiPort01(String adiscCode, String zdiscCode) {
			HashMap<String ,Object> map=new HashMap<String ,Object>();
			map.put("adiscCode", adiscCode);
			map.put("zdiscCode", zdiscCode);
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryByZhiPort01",map);
		}

		/**
		 * 
		* @Title: cableSectionChart 
		* @Description: 光缆段成端详情 
		* @param @param model
		* @param @param devId
		* @param @param sectId
		* @param @return    
		* @return String
		* @author liucanghai 
		* @throws
		* @date 2018年4月18日 下午3:20:21 
		* @version V1.0
		 */
		@Override
		public CableSectionChart cableSectionChart(Long devId, Long sectId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("sectId", sectId);
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.cableSectionChart", map);
		}

		/**
		 * 查询端子情况
		* @Title: queryDiscGroupDiscPorts 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param devId
		* @param @return    入参
		* @return CableSectionChart    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年4月19日 下午2:44:23 
		* @version V1.0
		 */
		@Override
		public List<Group> queryDiscGroupDiscPorts(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryDiscGroupDiscPorts", devId);
		}

		/**
		 * 
		* @Title: queryByGroupDesc 
		* @Description: 根据端子ID查询端口信息 
		* @param @param fiberDiscId
		* @param @return    
		* @return Group1
		* @author liucanghai 
		* @throws
		* @date 2018年4月23日 上午9:38:42 
		* @version V1.0
		 */
		@Override
		public Group1 queryByGroupDesc(Long fiberDiscId) {
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryByGroupDesc", fiberDiscId);
		}

		/**
		 * 
		* @Title: queryFiberdiscGroupWithSection 
		* @Description: 根据光缆段ID和设施ID查询熔纤盘详细信息  
		* @param @return    
		* @return Object
		* @author liucanghai 
		* @throws
		* @date 2018年5月3日 下午4:53:07 
		* @version V1.0
		 */
		@Override
		public List<DiscGroup> queryFiberdiscGroupWithSection(Long sectId, Long devId) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("sectId", sectId);
			param.put("devId", devId);
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryFiberdiscGroupWithSection", param);
		}

		/**
		 * 
		 * @Title: queryFiberdiscOccupCount
		 * @Description: 根据devId查询设施下端子占用情况
		 * @param @param devId
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年5月10日 下午1:54:15
		 * @version V1.0
		 */
		@Override
		public FiberdiscBean queryFiberdiscOccupCount(Long devId) {
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryFiberdiscOccupCount", devId);
		}

		/**
		 * 根据机构ID,查询端子占用情况
		* @Title: queryOccupyConditionByOrgId 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param orgId
		* @param @return    入参
		* @return List<DifferentPortsBean>    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年5月24日 下午3:44:04 
		* @version V1.0
		 */
		@Override
		public List<DifferentPortsBean> queryOccupyConditionByOrgId(Long orgId) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("orgId", orgId);
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryOccupyConditionByOrgId", map);
		}

		/**
		 * 根据机构ID,查询端子占用情况总条数
		* @Title: queryOccupyCountsByOrgId 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @return    入参
		* @return Integer    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年5月25日 上午9:36:24 
		* @version V1.0
		 */
		@Override
		public Integer queryOccupyCountsByOrgId(Long orgId, PageBean pb) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("pb", pb);
			map.put("orgId", orgId);
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryOccupyCountsByOrgId", map);
		}
		
		/**
		 * 根据区域编码查询端子数据统计
		* @Title: queryOccupyConditionByArea 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param orgId
		* @param @param pb
		* @param @return    入参
		* @return List<DifferentPortsBean>    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年5月25日 上午10:26:44 
		* @version V1.0
		 * @param differentPortsBean 
		 */
		@Override
		public List<DifferentPortsBean> queryOccupyConditionByArea(Long orgId) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("orgId", orgId);
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryOccupyConditionByArea", map);
		}

		/**
		 * 根据区域编码查询端子数据统计总条数
		* @Title: queryOccupyCountsByArea 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param orgId
		* @param @param pb
		* @param @param differentPortsBean
		* @param @return    入参
		* @return Integer    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年5月25日 上午11:00:05 
		* @version V1.0
		 */
		@Override
		public Integer queryOccupyCountsByArea(Long orgId, PageBean pb, DifferentPortsBean differentPortsBean) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("pb", pb);
			map.put("orgId", orgId);
			map.put("differentPortsBean", differentPortsBean);
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryOccupyCountsByArea", map);
		}

		/**
		 * 端子差异情况
		* @Title: queryPortOccupyCodition 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param devId
		* @param @return    入参
		* @return List<Group>    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年5月25日 下午4:19:38 
		* @version V1.0
		 */
		@Override
		public List<Group> queryPortOccupyCodition(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryPortOccupyCodition", devId);
		}

		/**
		 * 查询本端端子设施名称
		* @Title: queryDevNameByDiscId 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param discId
		* @param @return    入参
		* @return String    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年6月22日 下午3:24:22 
		* @version V1.0
		 */
		@Override
		public String queryDevNameByDiscId(Long fiberDiscId) {
			return this.selectOne("com.ycnet.frms.mapper.FiberdiscMapper.queryDevNameByDiscId", fiberDiscId);
		}

		/**
		 * 查询端子情况,分组信息等
		* @Title: queryByConditions 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param devId
		* @param @param clist
		* @param @return    入参
		* @return List<DiscinfoMobile>    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年8月27日 下午4:19:19 
		* @version V1.0
		 */
		@Override
		public List<DiscinfoMobile> queryByConditions(Long devId, List<Long> clist) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("clist", clist);
			return this.selectList("com.ycnet.frms.mapper.FiberdiscMapper.queryByConditions", map);
		}
		

}