package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceLockStatusEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.DeviceLockStatusEntityVo;

public interface DeviceLockStatusEntityMapper {
    int deleteByPrimaryKey(Long lockStatusId);

    int insert(DeviceLockStatusEntity record);

    int insertSelective(DeviceLockStatusEntity record);

    DeviceLockStatusEntity selectByPrimaryKey(Long lockStatusId);

    int updateByPrimaryKeySelective(DeviceLockStatusEntity record);

    int updateByPrimaryKey(DeviceLockStatusEntity record);

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
	DeviceLockStatusEntity queryForDevId(Long devId);

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
	List<DeviceLockStatusEntityVo> queryByDeviceStatusList(DeviceLockStatusEntityVo devStaCon, PageBean pb);

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
	int queryByDeviceStatusCount(DeviceLockStatusEntityVo devStaCon, PageBean pb);

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
	Integer queryDeviceLockStatustCount(Map<String, Object> map);

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
	List<DeviceLockStatusEntityVo> queryDeviceLockStatustList(Map<String, Object> map);

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
	 * @param str 
	 */
	DeviceLockStatusEntity queryAlarmStatus(Long alarmId, char str);

	/**
	 * 
	 * @Title: deleteByLockId
	 * @Description: 删除锁状态记录
	 * @param @param dsMap
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月5日 上午11:13:07
	 * @version V1.0
	 */
	int deleteByLockId(Map<String, Object> dsMap);

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
	DeviceLockStatusEntity queryByConditions(Long devId, String format, Users user);
}