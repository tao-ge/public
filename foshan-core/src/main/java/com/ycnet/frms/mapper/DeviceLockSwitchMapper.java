package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceLockSwitch;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.DeviceLockSwitchVo;

public interface DeviceLockSwitchMapper {
    int deleteByPrimaryKey(Long switchId);

    int insert(DeviceLockSwitch record);

    int insertSelective(DeviceLockSwitch record);

    DeviceLockSwitch selectByPrimaryKey(Long switchId);

    int updateByPrimaryKeySelective(DeviceLockSwitch record);

    int updateByPrimaryKey(DeviceLockSwitch record);

    /**
	 * 
	* @Title: queryLockSwitchList 
	* @Description: 开关锁记录查询 
	* @param @param pb
	* @param @param switchVo
	* @param @return    
	* @return PageBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月17日 下午3:16:57 
	* @version V1.0
	 */
	List<DeviceLockSwitchVo> queryLockSwitchList(DeviceLockSwitchVo switchVo, PageBean pb);

	/**
	 * 
	* @Title: queryLockSwitchCount 
	* @Description: 开关锁记录查询 
	* @param @param pb
	* @param @param switchVo
	* @param @return    
	* @return PageBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月17日 下午3:16:57 
	* @version V1.0
	 */
	int queryLockSwitchCount(DeviceLockSwitchVo switchVo, PageBean pb);

	/**
	 * 开关锁记录查询数据
	* @Title: queryimDeviceLockSwitchList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return List<?>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月21日 下午3:06:43 
	* @version V1.0
	 */
	List<DeviceLockSwitchVo> queryimDeviceLockSwitchList(Map<String, Object> map);

	/**
	 * 开关锁记录查询总条数
	* @Title: queryimDeviceLockSwitchCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月21日 下午3:06:48 
	* @version V1.0
	 */
	Integer queryimDeviceLockSwitchCount(Map<String, Object> map);

	/**
	 * 
	 * @Title: deleteByLockId
	 * @Description: 根据lockId删除开关锁记录
	 * @param @param lockId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月5日 上午10:38:23
	 * @version V1.0
	 */
	int deleteByLockId(Map<String, Object> dsMap);

	/**
	 * 根据锁ID,查询是否有开锁成功记录
	* @Title: queryByLockId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param lockId
	* @param @return    入参
	* @return DeviceLockSwitch    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月7日 下午1:10:08 
	* @version V1.0
	 * @param date 
	 * @param user 
	 */
	DeviceLockSwitch queryByLockId(Long lockId, String date, Users user);

	/**
	 * 根据锁IDs,查询是否有开锁成功记录
	* @Title: queryByLockIds 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param lockId0
	* @param @param lockId1
	* @param @param format
	* @param @param user
	* @param @return    入参
	* @return DeviceLockSwitch    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月17日 上午10:56:25 
	* @version V1.0
	 */
	DeviceLockSwitch queryByLockIds(Long lockId0, Long lockId1, String date, Users user);
}