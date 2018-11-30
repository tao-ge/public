package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarmEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.DeviceAlarmEntityMapper;
import com.ycnet.frms.vo.DeviceAlarmEntityBean;
import com.ycnet.frms.vo.mobile.AlarmEntityMobile;
import com.ycnet.frms.vo.mobile.FacilityAlarmListMobile;

@Repository("deviceAlarmEntityMapper")
public class DeviceAlarmEntityMapperImpl extends BaseSqlSupport implements DeviceAlarmEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long alarmId) {
		return this.delete("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.deleteByPrimaryKey",alarmId);
	}

	@Override
	public int insert(DeviceAlarmEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(DeviceAlarmEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.insertSelective",record);
	}

	@Override
	public DeviceAlarmEntity selectByPrimaryKey(Long alarmId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.selectByPrimaryKey",alarmId);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceAlarmEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(DeviceAlarmEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<AlarmEntityMobile> queryAlarmList(HashMap<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.queryAlarmList",map);
	}

	/**
	 * 接口查询告警信息列表
	* @Title: queryAlarmInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param pb
	* @param @return    入参
	* @return FacilityAlarmListMobile    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月8日 上午11:00:09 
	* @version V1.0
	 */
	@Override
	public FacilityAlarmListMobile queryAlarmInfo(Long devId,PageBean pb) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("pb", pb);
		return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.queryAlarmInfo",map);
	}

	/**
	 * 查询报警记录数据
	* @Title: queryDeviceAlarm 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return List<DeviceAlarmEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月15日 下午2:47:00 
	* @version V1.0
	 */
	@Override
	public List<DeviceAlarmEntityBean> queryDeviceAlarmList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.queryDeviceAlarmList",map);
	}

	/**
	 * 查询记录总数
	* @Title: queryDeviceAlarmCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月15日 下午2:47:52 
	* @version V1.0
	 */
	@Override
	public Integer queryDeviceAlarmCount(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.queryDeviceAlarmCount",map);
	}

	/**
	 * 查看报警详情
	* @Title: queryDeviceAlarmInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param alarmId
	* @param @return    入参
	* @return DeviceAlarmEntityBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月16日 上午9:23:23 
	* @version V1.0
	 */
	@Override
	public DeviceAlarmEntityBean queryDeviceAlarmInfo(Long alarmId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.queryDeviceAlarmInfo",alarmId);
	}

	/**
	 * 查询报警记录
	* @Title: queryDeviceAlarmList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return List<DeviceAlarmEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月20日 下午5:05:50 
	* @version V1.0
	 */
	@Override
	public List<DeviceAlarmEntityBean> queryDeviceAlarmListByConditions(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.queryDeviceAlarmListByConditions",map);
	}

	
	/**
	 * 查询报警记录总条数
	* @Title: queryDeviceAlarmCountsByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月21日 下午1:10:50 
	* @version V1.0
	 */
	@Override
	public Integer queryDeviceAlarmCountsByConditions(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.queryDeviceAlarmCountsByConditions",map);
	}

	/**
	 * 
	 * @Title: queryAlarmByDevId
	 * @Description: 查询设施下未处理报警记录
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月8日 上午9:38:54
	 * @version V1.0
	 */
	@Override
	public int queryAlarmByDevId(Long devId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.queryAlarmByDevId",devId);
	}

	/**
	 * 查询报警记录中是否有锁打开
	* @Title: queryByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param format
	* @param @param user
	* @param @return    入参
	* @return DeviceAlarmEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月8日 下午2:36:11 
	* @version V1.0
	 */
	@Override
	public DeviceAlarmEntity queryByConditions(Long devId, String format, Users user) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("date", format);
		map.put("devId", devId);
		map.put("orgId", user.getOrgId());
		return  this.selectOne("com.ycnet.frms.mapper.DeviceAlarmEntityMapper.queryByConditions",map);
	}
}
  
    