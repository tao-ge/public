package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.AlarmPushlog;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.mapper.DeviceAlarmMapper;

@Repository("deviceAlarmMapper")
public class DeviceAlarmMapperImpl extends  BaseSqlSupport 
		implements DeviceAlarmMapper{
	
		@Override
		public int insert(DeviceAlarm record)
		{
			return this.insert("com.ycnet.frms.mapper.DeviceAlarmMapper.insert",record);
		}
	
		@Override
		public DeviceAlarm selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.DeviceAlarmMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(DeviceAlarm record)
		{
			return this.insert("com.ycnet.frms.mapper.DeviceAlarmMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(DeviceAlarm record)
		{
			return this.update("com.ycnet.frms.mapper.DeviceAlarmMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(DeviceAlarm record)
		{
			return this.update("com.ycnet.frms.mapper.DeviceAlarmMapper.updateByPrimaryKey",record);
		}

		/**
		 * 按条件查询数据，支持分页
		 */
		@Override
		public List<DeviceAlarm> queryByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.DeviceAlarmMapper.queryByConditionMap",map);
		}

		/**
		 * 按条件查询返回结果数量
		 */
		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmMapper.queryCountByConditionMap",map);
		}
		
		/**
		 * 按条件查询未处理报警记录总数
		 */
		@Override
		public int queryUntreatedCountByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmMapper.queryUntreatedCountByConditionMap",map);
		}
		
		/**
		 * 关联tn_device_status表按条件查询数据，支持分页
		 */
		@Override
		public List<DeviceAlarm> queryNewRecordByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.DeviceAlarmMapper.queryNewRecordByConditionMap",map);
		}
		
		/**
		 * 关联tn_device_status表按条件查询返回结果数量
		 */
		@Override
		public int queryNewRecordCountByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmMapper.queryNewRecordCountByConditionMap",map);
		}
		
		@Override
		public List<DeviceAlarm> getAlarmList() {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.DeviceAlarmMapper.getAlarmList");
		}
		
		/**
		 * 添加推送日志
		 */
		@Override
		public int AlarmPushlogAdd(AlarmPushlog record)
		{
			return this.insert("com.ycnet.frms.mapper.DeviceAlarmMapper.AlarmPushlogAdd",record);
		}
		
		/**
		 * 通过设备Id查询机构ID
		 */
		@Override
		public String getOrgIdByDevId(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmMapper.getOrgIdByDevId",id);
		}
		
		/**
		 * 查询推送日志是否存在
		 */
		@Override
		public AlarmPushlog getIsExitByLogTS(Long alarmProcessId)
		{
			return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmMapper.getIsExitByLogTS",alarmProcessId);
		}
		
		/**
		 * 短信日志查询
		 * @author  YHT 
		 * @date 创建时间：2017年1月24日 上午8:53:12 
		 * @version 1.0
		 * @param alarmProcessId
		 * @return
		 */
		@Override
		public AlarmPushlog getIsExitByLogDX(Long alarmProcessId)
		{
			return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmMapper.getIsExitByLogTS",alarmProcessId);
		}
		
		@Override
		public int updateByPrimaryKeySelectives(AlarmPushlog record)
		{
			return this.update("com.ycnet.frms.mapper.DeviceAlarmMapper.updateByPrimaryKeySelectives",record);
		}

		/**
		 * 根据imei和mac删除报警记录
		 */
		@Override
		public int deleteAlarmByImeiAndMac(DeviceAlarm da) {
			return this.delete("com.ycnet.frms.mapper.DeviceAlarmMapper.deleteAlarmByImeiAndMac",da);
		}

		/**
		 * fl 根据设施ID 查询报警记录
		 * @param request
		 * @param devId
		 * @return
		 */
		@Override
		public List<DeviceAlarm> queryDeviceAlarmByDevId(HashMap<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.DeviceAlarmMapper.queryDeviceAlarmByDevId",map);
		}
		
		/**
		 * fl 根据设施ID 查询报警记录总条数
		 * @param request
		 * @param devId
		 * @return
		 */
		@Override
		public Integer queryCountDeviceAlarmByDevId(HashMap<String, Object> map) {
			return selectOne("com.ycnet.frms.mapper.DeviceAlarmMapper.queryCountDeviceAlarmByDevId",map);
		}

		/**
		 * 查询报警内容
		* @Title: queryByDevId 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param devId
		* @param @return    入参
		* @return DeviceAlarm    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年7月24日 下午2:19:48 
		* @version V1.0
		 */
		@Override
		public DeviceAlarm queryByDevId(Long devId) {
			return selectOne("com.ycnet.frms.mapper.DeviceAlarmMapper.queryByDevId",devId);
		}
}