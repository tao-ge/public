package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.DeviceLockEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.mobile.DeviceLockInfoMobile;

public interface DeviceLockEntityMapper {
    int deleteByPrimaryKey(Long lockId);

    int insert(DeviceLockEntity record);

    int insertSelective(DeviceLockEntity record);

    DeviceLockEntity selectByPrimaryKey(Long lockId);

    int updateByPrimaryKeySelective(DeviceLockEntity record);

    int updateByPrimaryKey(DeviceLockEntity record);

    /**
     * 
     * @Title: queryByLockCodeAndDevId
     * @Description: 根据lockCode和devId查询
     * @param @param map
     * @param @return 
     * @return int 入参
     * @return String    返回类型
     * @author DZY 
     * @throws
     * @date 2018年4月18日 下午7:27:13
     * @version V1.0
     */
	int queryByLockCodeAndDevId(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryByCodIdAndDevId
	 * @Description: 根据codId和devId查询中控器绑定的锁
	 * @param @param map
	 * @param @return 
	 * @return List<DeviceLockEntity> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月5日 上午10:13:31
	 * @version V1.0
	 */
	List<DeviceLockEntity> queryByCodIdAndDevId(Map<String, Object> map);

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
	List<DeviceLockInfoMobile> queryDeviceLockInfo(Map<String, Object> map);

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
	 * @param user 
	 */
	void updateByCondetions(String date, Users user);

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
	 * @param orgId 
	 */
	List<DeviceLockEntity> selectBycodId(Long codId, Long orgId);
}