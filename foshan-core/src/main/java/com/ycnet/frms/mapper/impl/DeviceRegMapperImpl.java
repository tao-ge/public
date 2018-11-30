package com.ycnet.frms.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.mapper.DeviceRegMapper;
import com.ycnet.frms.vo.mobile.DeviceRegVo;

@Repository("deviceRegMapper")
public class DeviceRegMapperImpl extends  BaseSqlSupport 
		implements DeviceRegMapper{
	
		@Override
		public int insert(DeviceReg record)
		{
			return this.insert("com.ycnet.frms.mapper.DeviceRegMapper.insert",record);
		}
	
		@Override
		public DeviceReg selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.DeviceRegMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.DeviceRegMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(DeviceReg record)
		{
			return this.insert("com.ycnet.frms.mapper.DeviceRegMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(DeviceReg record)
		{
			return this.update("com.ycnet.frms.mapper.DeviceRegMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(DeviceReg record)
		{
			return this.update("com.ycnet.frms.mapper.DeviceRegMapper.updateByPrimaryKey",record);
		}

		/**
		 * 智能锁管理列表
		 */
		@Override
		public List<DeviceReg> queryListByMap(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.DeviceRegMapper.queryListByMap",map);
		}
		
		//查所有
		@Override
		public List queryAll() {
			return this.selectList("com.ycnet.frms.mapper.DeviceRegMapper.queryAll");
		}
		
		
		@Override
		public int queryCountByMap(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.DeviceRegMapper.queryCountByMap",map);
		}
		
		@Override
		public DeviceReg selectByCondition(DeviceReg record) {
			return this.selectOne("com.ycnet.frms.mapper.DeviceRegMapper.selectByCondition",record);
		}

		@Override
		public List<DeviceReg> selectByDevId(Long devId) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.DeviceRegMapper.selectByDevId",devId);
		}
		
		//高级查询
		@Override
		public List<FacilityAccess> queryListByCondition(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.DeviceRegMapper.queryListByCondition",map);
		}

		@Override
		public Integer queryCountByCondition(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.DeviceRegMapper.queryCountByCondition",map);
		}

		@Override
		public DeviceReg checkDeviceReg(Map<String, Object> map) {
			return selectOne("com.ycnet.frms.mapper.DeviceRegMapper.checkDeviceReg",map);
		}

		@Override
		public List<DeviceReg> queryLockBydevCode(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.DeviceRegMapper.queryLockBydevCode",map);
		}

		@Override
		public List<DeviceReg> queryFacilityLock(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.DeviceRegMapper.queryFacilityLock",devId);
		}

		/**
		 * 
		 * @Title: queryNotRegDevice
		 * @Description: TODO(这里用一句话描述这个方法的作用) 
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2017年12月25日 下午8:11:42
		 * @version V1.0
		 */
		@Override
		public List<DeviceReg> queryNotRegDevice() {
			return this.selectList("com.ycnet.frms.mapper.DeviceRegMapper.queryNotRegDevice");
		}

		/**
		 * 
		 * @Title: updateDeviceReg
		 * @Description: 根据imei修改
		 * @param @param deviceReg
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2017年12月25日 下午11:46:21
		 * @version V1.0
		 */
		@Override
		public int updateDeviceReg(DeviceReg deviceReg) {
			return this.update("com.ycnet.frms.mapper.DeviceRegMapper.updateDeviceReg",deviceReg);
		}

		/**
		 * 
		 * @Title: queryByImei
		 * @Description: 根据imei查询设备信息
		 * @param @param did
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2017年12月28日 上午9:22:53
		 * @version V1.0
		 */
		@Override
		public DeviceReg queryByImei(String did) {
			return this.selectOne("com.ycnet.frms.mapper.DeviceRegMapper.queryByImei",did);
		}

		/**
		 * 
		 * @Title: queryDevByMac
		 * @Description: 根据mac查询数据
		 * @param @param devMac
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年1月22日 下午4:46:02
		 * @version V1.0
		 */
		@Override
		public DeviceReg queryDevByMac(String devMac) {
			return this.selectOne("com.ycnet.frms.mapper.DeviceRegMapper.queryDevByMac",devMac);
		}

		/**
		 * 根据汇聚去查询设备表
		* @Title: selectMobileSwitchAreaCode1 
		* @Description: 根据汇聚去查询设备表
		* @param @param session
		* @param @param areaCode1
		* @param @return    
		* @return List<Facility>
		* @author fl 
		* @throws
		* @date 2018年2月8日 下午3:48:06 
		* @version V1.0
		 */
		@Override
		public List<DeviceReg> selectDeviceRegAreaCode1(Long orgId,ArrayList<String> list,String devName) {
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("orgId", orgId);
			map.put("areaCode", list);
			map.put("devName", devName);
			return selectList("com.ycnet.frms.mapper.DeviceRegMapper.selectDeviceRegAreaCode1",map);
		}

		/**
		 * 
		 * @Title: queryByDevId
		 * @Description: 根据devId查询设备
		 * @param @param devId
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年2月7日 下午2:13:27
		 * @version V1.0
		 */
		@Override
		public List<DeviceReg> queryByDevId(Long devId, Long orgId) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("orgId", orgId);
			return this.selectList("com.ycnet.frms.mapper.DeviceRegMapper.queryByDevId",map);
		}

		/**
		 * 查询锁的详细信息
		* @Title: queryLockInfoByRegId 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param regId
		* @param @param orgId
		* @param @return    入参
		* @return DeviceRegVo    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年7月24日 下午2:08:18 
		* @version V1.0
		 */
		@Override
		public DeviceRegVo queryLockInfoByRegId(Long regId, Long orgId) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("regId", regId);
			map.put("orgId", orgId);
			return this.selectOne("com.ycnet.frms.mapper.DeviceRegMapper.queryLockInfoByRegId",map);
		}

		/**
		 * 
		 * @Title: queryByConditionMap
		 * @Description: 根据条件查询列表
		 * @param @param map
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年7月25日 下午3:17:02
		 * @version V1.0
		 */
		@Override
		public List<DeviceReg> queryByConditionMap(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.DeviceRegMapper.queryByConditionMap",map);
		}

		/**
		 * 
		 * @Title: queryCountByConditionMap
		 * @Description: 根据条件查询列表数量
		 * @param @param map
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年7月25日 下午3:21:42
		 * @version V1.0
		 */
		@Override
		public Integer queryCountByConditionMap(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.DeviceRegMapper.queryCountByConditionMap",map);
		}

		/**
		 * 查询imei
		* @Title: selectByConditions 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param orgId
		* @param @param string
		* @param @return    入参
		* @return List<DeviceReg>    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年7月27日 下午4:46:30 
		* @version V1.0
		 */
		@Override
		public List<DeviceReg> selectByConditions(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.DeviceRegMapper.selectByConditions",map);
		}

		/**
		 * 根据imei查询信息
		* @Title: selectByImei 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param imei
		* @param @return    入参
		* @return DeviceReg    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年7月28日 下午5:43:39 
		* @version V1.0
		 */
		@Override
		public DeviceReg selectByImei(String imei) {
			return this.selectOne("com.ycnet.frms.mapper.DeviceRegMapper.selectByImei",imei);
		}
}