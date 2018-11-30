package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.DeviceStatus;
import com.ycnet.frms.mapper.DeviceStatusMapper;
import com.ycnet.frms.vo.DeviceStatusConditionBean;

@Repository("deviceStatusMapper")
public class DeviceStatusMapperImpl extends  BaseSqlSupport 
		implements DeviceStatusMapper{
	
		@Override
		public int insert(DeviceStatus record)
		{
			return this.insert("com.ycnet.frms.mapper.DeviceStatusMapper.insert",record);
		}
	
		@Override
		public DeviceStatus selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.DeviceStatusMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.DeviceStatusMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(DeviceStatus record)
		{
			return this.insert("com.ycnet.frms.mapper.DeviceStatusMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(DeviceStatus record)
		{
			return this.update("com.ycnet.frms.mapper.DeviceStatusMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(DeviceStatus record)
		{
			return this.update("com.ycnet.frms.mapper.DeviceStatusMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<DeviceStatus> queryByConditionBeans(DeviceStatusConditionBean bean) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.DeviceStatusMapper.queryByConditionBeans", bean);
		}
		
		@Override
		public List<DeviceStatus> queryByConditionBean(DeviceStatusConditionBean bean) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.DeviceStatusMapper.queryByConditionBean", bean);
		}

		/**
		 * 查询设施监控状态数据 分页
		 */
		@Override
		public List<DeviceStatus> queryByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.DeviceStatusMapper.queryByConditionMap", map);
		}
		
		/**
		 * 查询设施状态数据数量
		 */
		@Override
		public int queryCountByConditionMap(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.DeviceStatusMapper.queryCountByConditionMap", map);

		}

		/**
		 * 根据IMEI和MAC删除设备状态记录
		 */
		@Override
		public int deleteDevStatusByImeiAndMac(DeviceStatus ds) {
			// TODO Auto-generated method stub
			return this.delete("com.ycnet.frms.mapper.DeviceStatusMapper.deleteDevStatusByImeiAndMac",ds);
		}

		/**
		 * 定时上报记录总数
		* 
		* @Title: DeviceStatusService.java 
		* @Description: TODO 
		* @param @param devStaCon
		* @param @param user
		* @param @param pb
		* @param @return
		* @return PageBean
		* @author fl
		* @date 2018年2月5日 上午10:22:10
		* @version V1.0
		 */
		@Override
		public Integer queryCountdevTimeNewStatusList(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.DeviceStatusMapper.queryCountdevTimeNewStatusList",map);
		}

		/**
		 * 定时上报记录总记录
		* 
		* @Title: DeviceStatusService.java 
		* @Description: TODO 
		* @param @param devStaCon
		* @param @param user
		* @param @param pb
		* @param @return
		* @return PageBean
		* @author fl
		* @date 2018年2月5日 上午10:22:10
		* @version V1.0
		 */
		@Override
		public List<DeviceStatus> querydevTimeNewStatusList(Map<String, Object> map) {
			return selectList("com.ycnet.frms.mapper.DeviceStatusMapper.querydevTimeNewStatusList",map);
		}

		/**
		 * 实时监控页面excle表格
		 * fl 根据设施ID 定时上报记录
		 * @param devId
		 * @return
		 */
		@Override
		public List<DeviceStatus> querydevTimeNewStatuByDevId(Long devId,PageBean pb) {
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("pb", pb);
			return selectList("com.ycnet.frms.mapper.DeviceStatusMapper.querydevTimeNewStatuByDevId",map);
		}
		
		/**
		 * 实时监控页面excle表格
		 * fl 根据设施ID 定时上报记录总条数
		 * @param devId
		 * @return
		 */
		@Override
		public Integer queryCountdevTimeNewStatuByDevId(Long devId, PageBean pb) {
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("pb", pb);
			return selectOne("com.ycnet.frms.mapper.DeviceStatusMapper.queryCountdevTimeNewStatuByDevId",map);
		}

		/**
		 * 修改状态表中的所有当前状态改为历史状态
		* @Title: updateByImei 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param devImei
		* @param @return    入参
		* @return int    返回类型
		* @author FL（作者） 
		* @throws
		* @date 2018年7月30日 下午3:09:52 
		* @version V1.0
		 */
		@Override
		public int updateByImei(String devImei) {
			return update("com.ycnet.frms.mapper.DeviceStatusMapper.updateByImei", devImei);
		}

		

		


		

		
}