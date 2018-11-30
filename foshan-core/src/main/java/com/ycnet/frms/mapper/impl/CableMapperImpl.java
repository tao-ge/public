package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Cable;
import com.ycnet.frms.bean.CableZF;
import com.ycnet.frms.mapper.CableMapper;
import com.ycnet.frms.vo.CableConditionBean;
import com.ycnet.frms.vo.CableSectionBean;
import com.ycnet.frms.vo.CableStat;
import com.ycnet.frms.vo.CablesBean;

@Repository("cableMapper")
public class CableMapperImpl extends  BaseSqlSupport 
		implements CableMapper{
	
		@Override
		public int insert(Cable record)
		{
			return this.insert("com.ycnet.frms.mapper.CableMapper.insert",record);
		}
	
		@Override
		public Cable selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.CableMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.CableMapper.deleteByPrimaryKey",id);
		}
		
		@Override
		public int insertSelective(Cable record)
		{
			return this.insert("com.ycnet.frms.mapper.CableMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(Cable record)
		{
			return this.update("com.ycnet.frms.mapper.CableMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(Cable record)
		{
			return this.update("com.ycnet.frms.mapper.CableMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<Cable> selectByParam(CableConditionBean searchBean) {
			
			return this.selectList("com.ycnet.frms.mapper.CableMapper.selectByParam",searchBean);
		}

		@Override
		public List<Cable> queryByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.CableMapper.queryByConditionMap",map);
		}
		
		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {			
			return this.selectOne("com.ycnet.frms.mapper.CableMapper.queryCountByConditionMap", map);
		}

		@Override
		public Cable queryCableEnd(Long sectId) {
			return this.selectOne("com.ycnet.frms.mapper.CableMapper.queryCableEnd",sectId);
		}

		@Override
		public Cable queryCablesInfo(Long sectId) {
			return this.selectOne("com.ycnet.frms.mapper.CableMapper.queryCablesInfo",sectId);
		}

		//导出纤芯占用详细(光交箱)
		@Override
		public List<Cable> queryPortInfo(Long orgId,String areaCode1) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.CableMapper.queryPortInfo", map);
		}

		@Override
		public List<CableStat> queryPortStat(Long orgId,String areaCode1) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.CableMapper.queryPortStat",map);
		}

		@Override
		public CableStat queryPortStat1(Long orgId,Long sectId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("sectId", sectId);
			
			return this.selectOne("com.ycnet.frms.mapper.CableMapper.queryPortStat1", map);
		}
		
		
		@Override
		public List<Cable> queryCableInfo(Long orgId, String areaCode1) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.CableMapper.queryCableInfo", map);
		}
		
		//机柜端口占用详情
		@Override
		public List<Cable> queryPortInfoCablin(Long orgId, String areaCode1) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.CableMapper.queryPortInfoCablin", map);
		}

		@Override
		public List<Cable> queryCableDevId(Long orgId, Long devId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("devId", devId);
			return this.selectList("com.ycnet.frms.mapper.CableMapper.queryCableDevId", map);
		}

		@Override
		public List<Cable> queryCableInfoCablin(Long orgId, String areaCode1) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode1", areaCode1);
			return this.selectList("com.ycnet.frms.mapper.CableMapper.queryCableInfoCablin", map);
		}
		
		@Override
		public Cable queryCablesInfos(Long sectId) {
			return this.selectOne("com.ycnet.frms.mapper.CableMapper.queryCablesInfos",sectId);
		}

		//导出数据库 刘沧海2017/10/13
		@Override
		public List<CableZF> queryList(Long orgId) {
			return this.selectList("com.ycnet.frms.mapper.CableMapper.queryList", orgId);
		}

		/**
		 * 根据编码查询光缆
		* @Title: selectByCableCode 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param code
		* @param @return    入参
		* @return Cable    返回类型
		* @author YHT（作者） 
		* @throws
		* @date 2017年12月4日 下午3:07:24 
		* @version V1.0
		 */
		@Override
		public Cable selectByCableCode(String code) {
			return this.selectOne("com.ycnet.frms.mapper.CableMapper.selectByCableCode",code);
		}

		/**
		 * web每第一次进入,只查未成端光缆
		* 
		* @Title: CableService.java 
		* @Description: TODO 
		* @param @param bean
		* @param @param pb
		* @param @param orgId
		* @param @return
		* @return Object
		* @author fl
		* @date 2018年1月16日 下午2:11:13
		* @version V1.0
		 */
		@Override
		public List<Cable> queryCablesByBean(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.CableMapper.queryCablesByBean",map);
		}

		/**
		 * web每第一次进入,只查未成端光缆 总数
		* 
		* @Title: CableMapper.java 
		* @Description: TODO 
		* @param @param conditionMap
		* @param @return
		* @return Integer
		* @author fl
		* @date 2018年1月16日 下午2:33:41
		* @version V1.0
		 */
		@Override
		public Integer queryCountCablesByBean(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.CableMapper.queryCountCablesByBean", map);
		}
		
		/**
		 * 修改web页面光缆列表查询,优化sql,查询总数据
		* 
		* @Title: CableMapper.java 
		* @Description: TODO 
		* @param @param conditionMap
		* @param @return
		* @return Cable
		* @author fl
		* @date 2018年1月16日 下午3:57:36
		* @version V1.0
		 */
		@Override
		public List<Cable> queryByConditionsMap(Map<String, Object> map) {
			return selectList("com.ycnet.frms.mapper.CableMapper.queryByConditionsMap", map);
		}

		/**
		 * 修改web页面光缆列表查询,优化sql,查询总数
		* 
		* @Title: CableMapper.java 
		* @Description: TODO 
		* @param @param conditionMap
		* @param @return
		* @return Cable
		* @author fl
		* @date 2018年1月16日 下午3:57:36
		* @version V1.0
		 */
		@Override
		public Integer queryCountByConditionsMap(Map<String, Object> map) {
			return selectOne("com.ycnet.frms.mapper.CableMapper.queryCountByConditionsMap", map);
		}

		/**
		 * 光缆状态为3的数据的总条数
		* 
		* @Title: CableMapper.java 
		* @Description: TODO 
		* @param @param conditionMap
		* @param @return
		* @return Cable
		* @author fl
		* @date 2018年1月22日 下午4:29:49
		* @version V1.0
		 */
		@Override
		public Integer queryCountByConditionsMapFour(Map<String, Object> map) {
			return selectOne("com.ycnet.frms.mapper.CableMapper.queryCountByConditionsMapFour", map);
		}

		/**
		 * 光缆状态为3的查询总数据
		* 
		* @Title: CableMapper.java 
		* @Description: TODO 
		* @param @param conditionMap
		* @param @return
		* @return Cable
		* @author fl
		* @date 2018年1月22日 下午4:29:49
		* @version V1.0
		 */
		@Override
		public List<Cable> queryByConditionsMapFour(Map<String, Object> map) {
			return selectList("com.ycnet.frms.mapper.CableMapper.queryByConditionsMapFour", map);
		}

		/**
		 * 光缆状态为0总条数
		* 
		* @Title: CableMapper.java 
		* @Description: TODO 
		* @param @param conditionMap
		* @param @return
		* @return Integer
		* @author fl
		* @date 2018年1月22日 下午5:33:56
		* @version V1.0
		 */
		@Override
		public Integer queryCountByConditionsMapZero(Map<String, Object> map) {
			return selectOne("com.ycnet.frms.mapper.CableMapper.queryCountByConditionsMapZero", map);
		}

		/**
		 * 光缆状态为0总数据
		* 
		* @Title: CableMapper.java 
		* @Description: TODO 
		* @param @param conditionMap
		* @param @return
		* @return Cable
		* @author fl
		* @date 2018年1月22日 下午5:34:11
		* @version V1.0
		 */
		@Override
		public List<Cable> queryByConditionsMapZero(Map<String, Object> map) {
			return selectList("com.ycnet.frms.mapper.CableMapper.queryByConditionsMapZero", map);
		}
		/**
		 * 查询纠错光缆段
		 * zhouyu
		 */
		@Override
		public List<CablesBean> queryErrorRecCableListByCablesBean(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.CableMapper.queryErrorRecCableListByCablesBean", map);
		}

		@Override
		public Integer queryErrorRecCableListCountByCablesBean(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.CableMapper.queryErrorRecCableListCountByCablesBean", map);
		}
		
}