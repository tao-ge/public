package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.DeviceLockEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.mobile.DeviceLockInfoMobile;

public interface DeviceLockEntityService {

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 添加
	 * @param @param deviceLock
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月18日 下午6:41:31
	 * @version V1.0
	 */
	int insertSelective(DeviceLockEntity deviceLock);

	/**
	 * 
	 * @Title: queryByLockCodeAndDevId
	 * @Description: 根据lockCode和devId查询
	 * @param @param lock
	 * @param @param devId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月18日 下午7:25:33
	 * @version V1.0
	 * @param codId 
	 */
	int queryByLockCodeAndDevId(String lock, Long devId, Long codId);

	/**
	 * 查询锁,中控器,设施信息
	* @Title: queryDeviceLockInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param devCode
	* @param @param devName
	* @param @return    入参
	* @return List<DeviceLockInfoMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月5日 上午9:48:56 
	* @version V1.0
	 */
	List<DeviceLockInfoMobile> queryDeviceLockInfo(Long orgId, String devCode, String devName);

	/**
	 * 
	 * @Title: queryByCodIdAndDevId
	 * @Description: 根据devId和codId查询锁
	 * @param @param devId
	 * @param @param codId
	 * @param @return 
	 * @return List<DeviceLockEntity> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月9日 下午2:52:56
	 * @version V1.0
	 */
	List<DeviceLockEntity> queryByCodIdAndDevId(Long devId, Long codId);

	/**
	 * 手机远程开锁,点击开锁
	* @Title: unlocking 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceReg
	* @param @param user
	* @param @return    入参
	* @return DeviceLockSwitch    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月7日 上午11:25:01 
	* @version V1.0
	 * @param port 
	 * @param ip 
	 */
	String unlocking(DeviceLockEntity deviceLock, Users user, String ip, String port);

}
  
    