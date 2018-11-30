package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.mapper.FacilityAccessMapper;
import com.ycnet.frms.vo.AccessConditionBean;
import com.ycnet.frms.vo.FacilityAccessBean;

@Repository("facilityAccessMapper")
public class FacilityAccessMapperImpl extends  BaseSqlSupport 
		implements FacilityAccessMapper{
	
		@Override
		public int insert(FacilityAccess record)
		{
			return this.insert("com.ycnet.frms.mapper.FacilityAccessMapper.insert",record);
		}
	
		@Override
		public FacilityAccess selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.FacilityAccessMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.FacilityAccessMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(FacilityAccess record)
		{
			return this.insert("com.ycnet.frms.mapper.FacilityAccessMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(FacilityAccess record)
		{
			return this.update("com.ycnet.frms.mapper.FacilityAccessMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(FacilityAccess record)
		{
			return this.update("com.ycnet.frms.mapper.FacilityAccessMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<FacilityAccess> queryListByMap(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.FacilityAccessMapper.queryListByMap",map);
		}

		@Override
		public int queryCountByMap(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityAccessMapper.queryCountByMap",map);
		}
		
		@Override
		public List<FacilityAccessBean> queryListByCondition(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.FacilityAccessMapper.queryListByCondition",map);
		}

		@Override
		public int queryCountByCondition(
				Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityAccessMapper.queryCountByCondition",map);
		}

		/**
		 * 
		 * @Title: queryAccessCountByMac
		 * @Description: 根据条件查询是否设施是否授权
		 * @param @param map
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年5月2日 上午10:49:36
		 * @version V1.0
		 */
		@Override
		public int queryAccessCountByMac(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityAccessMapper.queryAccessCountByMac",map);
		}

		/**
		 * 
		 * @Title: queryWhetherAccess
		 * @Description: 查询设施是否授权
		 * @param @param devId
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年7月24日 下午3:27:42
		 * @version V1.0
		 */
		@Override
		public FacilityAccess queryWhetherAccess(String devId) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityAccessMapper.queryWhetherAccess",devId);
		}

		/**
		 * 根据imei,查询授权时间
		* @Title: queryFacilityAccessByImei 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param imei
		* @param @return    入参
		* @return List<FacilityAccess>    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年7月28日 下午6:09:03 
		* @version V1.0
		 */
		@Override
		public FacilityAccess queryFacilityAccessByImei(String imei) {
			return this.selectOne("com.ycnet.frms.mapper.FacilityAccessMapper.queryFacilityAccessByImei",imei);
		}
}