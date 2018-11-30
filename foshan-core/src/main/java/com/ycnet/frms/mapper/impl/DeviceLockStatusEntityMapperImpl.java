package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceLockStatusEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.DeviceLockStatusEntityMapper;
import com.ycnet.frms.vo.DeviceLockStatusEntityVo;

@Repository("deviceLockStatusEntityMapper")
public class DeviceLockStatusEntityMapperImpl extends BaseSqlSupport implements DeviceLockStatusEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long lockStatusId) {
		return this.delete("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.deleteByPrimaryKey",lockStatusId);
	}

	@Override
	public int insert(DeviceLockStatusEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(DeviceLockStatusEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.insertSelective",record); 
	}

	@Override
	public DeviceLockStatusEntity selectByPrimaryKey(Long lockStatusId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.selectByPrimaryKey",lockStatusId);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceLockStatusEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.updateByPrimaryKeySelective",record); 
	}

	
	@Override
	public int updateByPrimaryKey(DeviceLockStatusEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.updateByPrimaryKey",record);
	}

	/**
     * 
    * @Title: queryForDevId 
    * @Description: 根据设施ID查询 
    * @param @param devId
    * @param @return    
    * @return DeviceLockStatusEntity
    * @author liucanghai 
    * @throws
    * @date 2018年4月15日 下午5:05:21 
    * @version V1.0
     */
	@Override
	public DeviceLockStatusEntity queryForDevId(Long devId) {
		return selectOne("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.queryForDevId", devId);
	}

	/**
	 * 
	* @Title: queryByDeviceStatusList 
	* @Description:监控数据 
	* @param @param devStaCon
	* @param @param pb
	* @param @return    
	* @return PageBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月16日 下午1:48:49 
	* @version V1.0
	 */
	@Override
	public List<DeviceLockStatusEntityVo> queryByDeviceStatusList(DeviceLockStatusEntityVo devStaCon, PageBean pb) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("devStaCon", devStaCon);
		map.put("pb", pb);
		return selectList("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.queryByDeviceStatusList", map);
	}

	/**
	 * 
	* @Title: queryByDeviceStatusCount 
	* @Description:监控数据 
	* @param @param devStaCon
	* @param @param pb
	* @param @return    
	* @return PageBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月16日 下午1:48:49 
	* @version V1.0
	 */
	@Override
	public int queryByDeviceStatusCount(DeviceLockStatusEntityVo devStaCon, PageBean pb) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("devStaCon", devStaCon);
		map.put("pb", pb);
		return selectOne("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.queryByDeviceStatusCount", map);
	}

	/**
	 * 定时上报记录总条数查询
	* @Title: queryDeviceLockStatustCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月21日 下午1:59:15 
	* @version V1.0
	 */
	@Override
	public Integer queryDeviceLockStatustCount(Map<String, Object> map) {
		return selectOne("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.queryDeviceLockStatustCount", map);
	}

	/**
	 * 定时上报记录数据查询
	* @Title: queryDeviceLockStatustList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return List<?>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月21日 下午2:00:25 
	* @version V1.0
	 */
	@Override
	public List<DeviceLockStatusEntityVo> queryDeviceLockStatustList(Map<String, Object> map) {
		return selectList("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.queryDeviceLockStatustList", map);
	}

	/**
	 * 
	* @Title: queryAlarmStatus 
	* @Description: 根据告警信息查询锁状态 
	* @param @param parseLong
	* @param @return    
	* @return DeviceLockStatusEntity
	* @author liucanghai 
	* @throws
	* @date 2018年4月27日 下午1:47:40 
	* @version V1.0
	 */
	@Override
	public DeviceLockStatusEntity queryAlarmStatus(Long alarmId,char str) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("alarmId", alarmId);
		map.put("str", str);
		return this.selectOne("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.queryAlarmStatus", map);
	}

	/**
	 * 
	 * @Title: deleteByLockId
	 * @Description: 删除锁状态记录
	 * @param @param dsMap
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月5日 上午11:13:41
	 * @version V1.0
	 */
	@Override
	public int deleteByLockId(Map<String, Object> dsMap) {
		return this.delete("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.deleteByLockId",dsMap);
	}

	/**
	 * 查询锁状态是否为打开
	* @Title: queryByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param format
	* @param @param user
	* @param @return    入参
	* @return DeviceLockStatusEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月8日 下午3:19:55 
	* @version V1.0
	 */
	@Override
	public DeviceLockStatusEntity queryByConditions(Long devId, String format, Users user) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("date", format);
		map.put("devId", devId);
		map.put("orgId", user.getOrgId());
		return this.selectOne("com.ycnet.frms.mapper.DeviceLockStatusEntityMapper.queryByConditions",map);
	}

}
  
    