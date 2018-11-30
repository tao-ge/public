package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.MobileSwitchMapper;

@Repository("mobileSwitchMapper")
public class MobileSwitchMapperImpl extends  BaseSqlSupport 
		implements MobileSwitchMapper{
	
		@Override
		public int insert(MobileSwitch record)
		{
			return this.insert("com.ycnet.frms.mapper.MobileSwitchMapper.insert",record);
		}
	
		@Override
		public MobileSwitch selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.MobileSwitchMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.MobileSwitchMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(MobileSwitch record)
		{
			return this.insert("com.ycnet.frms.mapper.MobileSwitchMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(MobileSwitch record)
		{
			return this.update("com.ycnet.frms.mapper.MobileSwitchMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(MobileSwitch record)
		{
			return this.update("com.ycnet.frms.mapper.MobileSwitchMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<MobileSwitch> queryListByMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.MobileSwitchMapper.queryListByMap",map);
		}

		@Override
		public Integer queryCountByConditionMap(Map<String, Object> conditionMap) {
			return this.selectOne("com.ycnet.frms.mapper.MobileSwitchMapper.queryCountByConditionMap",conditionMap);
		}
		
		@Override
		public List<MobileSwitch> queryNewRecordListByMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.MobileSwitchMapper.queryNewRecordListByMap",map);
		}

		@Override
		public Integer queryNewRecordCountByConditionMap(Map<String, Object> conditionMap) {
			return this.selectOne("com.ycnet.frms.mapper.MobileSwitchMapper.queryNewRecordCountByConditionMap",conditionMap);
		}

		/**
		 * 实时监控页面excle表格
		 * fl 根据设施ID 手机开锁记录
		 * @param request
		 * @param devId
		 * @return
		 */
		@Override
		public List<MobileSwitch> querySwitchRecordByDevId(HashMap<String, Object> map) {
			return selectList("com.ycnet.frms.mapper.MobileSwitchMapper.querySwitchRecordByDevId",map);
		}
		
		/**
		 * 实时监控页面excle表格
		 * fl 根据设施ID 手机开锁记录总条数
		 * @param request
		 * @param devId
		 * @return
		 */
		@Override
		public Integer queryCountSwitchRecordByDevId(HashMap<String, Object> map) {
			return selectOne("com.ycnet.frms.mapper.MobileSwitchMapper.queryCountSwitchRecordByDevId",map);
		}
		
		/**
		 * 
		* @Title: queryMobileSwitchByUserId 
		* @Description: 当前用户的开关锁记录 
		* @param @param session
		* @param @return    
		* @return Object
		* @author liucanghai 
		* @throws
		* @date 2018年2月9日 下午2:31:42 
		* @version V1.0
		 */
		@Override
		public List<MobileSwitch> queryMobileSwitchByUserId(Facility facility,Long userId) {
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("facility", facility);
			map.put("userId", userId);
			return this.selectList("com.ycnet.frms.mapper.MobileSwitchMapper.queryMobileSwitchByUserId",map);
		}

		/**
		 * 
		 * @Title: queryRemoteUnlock
		 * @Description: 查询远程开锁
		 * @param @param map
		 * @param @return
		 * @return String    返回类型
		 * @author DZY 
		 * @throws
		 * @date 2018年7月26日 下午2:44:04
		 * @version V1.0
		 */
		@Override
		public MobileSwitch queryRemoteUnlock(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.MobileSwitchMapper.queryRemoteUnlock",map);
		}

		/**
		 * 查询最新一条成功的开锁记录
		* @Title: querySuccessOne 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param devImei
		* @param @return    入参
		* @return MobileSwitch    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年7月30日 下午4:10:03 
		* @version V1.0
		 */
		@Override
		public MobileSwitch querySuccessOne(String devImei) {
			return this.selectOne("com.ycnet.frms.mapper.MobileSwitchMapper.querySuccessOne",devImei);
		}

}