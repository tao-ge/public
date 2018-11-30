package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.DeviceLockEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.DeviceLockEntityMapper;
import com.ycnet.frms.vo.mobile.DeviceLockInfoMobile;

@Repository("deviceLockEntityMapper")
public class DeviceLockEntityMapperImpl extends BaseSqlSupport implements DeviceLockEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long lockId) {
		return this.delete("com.ycnet.frms.mapper.DeviceLockEntityMapper.deleteByPrimaryKey",lockId);
	}

	@Override
	public int insert(DeviceLockEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceLockEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(DeviceLockEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceLockEntityMapper.insertSelective",record); 
	}

	@Override
	public DeviceLockEntity selectByPrimaryKey(Long lockId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceLockEntityMapper.selectByPrimaryKey",lockId);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceLockEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceLockEntityMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(DeviceLockEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceLockEntityMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	 * @Title: queryByLockCodeAndDevId
	 * @Description: 根据lockCode和devId查询
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月18日 下午7:27:40
	 * @version V1.0
	 */
	@Override
	public int queryByLockCodeAndDevId(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceLockEntityMapper.queryByLockCodeAndDevId",map);
	}

	/**
	 * 
	 * @Title: queryByCodIdAndDevId
	 * @Description: 根据codId和devId查询中控器绑定的锁
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月5日 上午10:13:50
	 * @version V1.0
	 */
	@Override
	public List<DeviceLockEntity> queryByCodIdAndDevId(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceLockEntityMapper.queryByCodIdAndDevId",map);
	}

	/**
	 * 查询锁,中控器,设施信息
	* @Title: queryDeviceLockInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return List<DeviceLockInfoMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月5日 上午9:50:12 
	* @version V1.0
	 */
	@Override
	public List<DeviceLockInfoMobile> queryDeviceLockInfo(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceLockEntityMapper.queryDeviceLockInfo",map);
	}

	/**
	 * 修改不应该在线的锁状态
	* @Title: updateByCondetions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param date    入参
	* @return void    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月7日 上午11:55:51 
	* @version V1.0
	 */
	@Override
	public void updateByCondetions(String date, Users user) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("date", date);
		map.put("user", user);
		this.update("com.ycnet.frms.mapper.DeviceLockEntityMapper.updateByCondetions",map);
	}

	
	/**
	 * 根据中控器ID,查询在线锁
	* @Title: selectBycodId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codId
	* @param @return    入参
	* @return List<DeviceLockEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月16日 下午5:35:33 
	* @version V1.0
	 */
	@Override
	public List<DeviceLockEntity> selectBycodId(Long codId,Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("codId", codId);
		return this.selectList("com.ycnet.frms.mapper.DeviceLockEntityMapper.selectBycodId", map);
	}
}
  
    