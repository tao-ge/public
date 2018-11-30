package com.ycnet.frms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Users;

public interface MobileSwitchMapper {
    int deleteByPrimaryKey(Long switchId);

    int insert(MobileSwitch record);

    int insertSelective(MobileSwitch record);

    MobileSwitch selectByPrimaryKey(Long switchId);

    int updateByPrimaryKeySelective(MobileSwitch record);

    int updateByPrimaryKey(MobileSwitch record);
    
    List<MobileSwitch> queryListByMap(Map<String,Object> map);

	Integer queryCountByConditionMap(Map<String, Object> conditionMap);
	
	List<MobileSwitch> queryNewRecordListByMap(Map<String, Object> map);
	
	Integer queryNewRecordCountByConditionMap(Map<String, Object> conditionMap);

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
	 * @param facility 
	 */
	List<MobileSwitch> queryMobileSwitchByUserId(Facility facility, Long userId);

	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 手机开锁记录
	 * @param request
	 * @param devId
	 * @param pb 
	 * @return
	 */
	List<MobileSwitch> querySwitchRecordByDevId(HashMap<String, Object> map);

	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 手机开锁记录总条数
	 * @param request
	 * @param devId
	 * @param pb 
	 * @return
	 */
	Integer queryCountSwitchRecordByDevId(HashMap<String, Object> map);

	/**
	 * 
	 * @Title: queryRemoteUnlock
	 * @Description: 查询远程开锁
	 * @param @param map
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月26日 下午2:43:40
	 * @version V1.0
	 */
	MobileSwitch queryRemoteUnlock(Map<String, Object> map);

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
	MobileSwitch querySuccessOne(String devImei);

}