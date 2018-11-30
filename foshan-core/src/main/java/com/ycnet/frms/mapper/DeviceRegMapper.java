package com.ycnet.frms.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.vo.mobile.DeviceRegVo;

public interface DeviceRegMapper {
    int deleteByPrimaryKey(Long regId);

    int insert(DeviceReg record);

    int insertSelective(DeviceReg record);

    DeviceReg selectByPrimaryKey(Long regId);

    int updateByPrimaryKeySelective(DeviceReg record);

    int updateByPrimaryKey(DeviceReg record);
    

    List<DeviceReg> queryListByMap(Map<String,Object> map);
    int queryCountByMap(Map<String,Object> map);
    
    
    DeviceReg selectByCondition(DeviceReg record);
    
    /**
     * 根据设施Id查询对应锁
     * @author  YHT 
     * @date 创建时间：2017年1月10日 上午9:05:59 
     * @version 1.0
     * @param devId
     * @return
     */
    List<DeviceReg> selectByDevId(Long devId);

	List queryAll();

	List<FacilityAccess> queryListByCondition(Map<String, Object> map);

	Integer queryCountByCondition(Map<String, Object> map);

	DeviceReg checkDeviceReg(Map<String, Object> map);

	List<DeviceReg> queryLockBydevCode(Map<String, Object> map);

	List<DeviceReg> queryFacilityLock(Long devId);

	/**
	 * 
	 * @Title: queryNotRegDevice
	 * @Description: 查询未注册的设备
	 * @param @return 
	 * @return List<DeviceReg> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月25日 下午8:11:26
	 * @version V1.0
	 */
	List<DeviceReg> queryNotRegDevice();

	/**
	 * 
	 * @Title: updateDeviceReg
	 * @Description: 根据imei修改
	 * @param @param deviceReg
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月25日 下午11:45:48
	 * @version V1.0
	 */
	int updateDeviceReg(DeviceReg deviceReg);

	/**
	 * 
	 * @Title: queryByImei
	 * @Description: 根据imei查询设备信息
	 * @param @param did
	 * @param @return 
	 * @return DeviceReg 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月28日 上午9:22:07
	 * @version V1.0
	 */
	DeviceReg queryByImei(String did);

	/**
	 * 
	 * @Title: queryDevByMac
	 * @Description: 根据mac查询数据
	 * @param @param devMac
	 * @param @return 
	 * @return DeviceReg 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月22日 下午4:45:43
	 * @version V1.0
	 */
	DeviceReg queryDevByMac(String devMac);
	
	/**
	 * 根据汇聚去查询设备表
	* @Title: selectMobileSwitchAreaCode1 
	* @Description: 根据汇聚去查询设备表
	* @param @param session
	* @param @param areaCode1
	* @param @return    
	* @return List<Facility>
	* @author fl 
	* @throws
	* @date 2018年2月8日 下午3:48:06 
	* @version V1.0
	 * @param devName 
	 */
	List<DeviceReg> selectDeviceRegAreaCode1(Long orgId, ArrayList<String> list, String devName);

	/**
	 * 
	 * @Title: queryByDevId
	 * @Description: 根据devId查询设备
	 * @param @param devId
	 * @param @return 
	 * @return List<DeviceReg> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月7日 下午2:13:10
	 * @version V1.0
	 * @param orgId 
	 */
	List<DeviceReg> queryByDevId(Long devId, Long orgId);

	/**
	 * 查询锁的详细信息
	* @Title: queryLockInfoByRegId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param regId
	* @param @param orgId
	* @param @return    入参
	* @return DeviceRegVo    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月24日 下午2:08:18 
	* @version V1.0
	 */
	DeviceRegVo queryLockInfoByRegId(Long regId, Long orgId);

	/**
	 * 
	 * @Title: queryByConditionMap
	 * @Description: 根据条件查询列表
	 * @param @param map
	 * @param @return 
	 * @return List<DeviceReg> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月25日 下午3:16:07
	 * @version V1.0
	 */
	List<DeviceReg> queryByConditionMap(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryCountByConditionMap
	 * @Description: 根据条件查询列表数量
	 * @param @param map
	 * @param @return 
	 * @return Integer 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月25日 下午3:21:14
	 * @version V1.0
	 */
	Integer queryCountByConditionMap(Map<String, Object> map);

	/**
	 * 查询imei
	* @Title: selectByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param string
	* @param @return    入参
	* @return List<DeviceReg>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月27日 下午4:46:30 
	* @version V1.0
	 */
	List<DeviceReg> selectByConditions(Map<String, Object> map);

	/**
	 * 根据imei查询信息
	* @Title: selectByImei 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param imei
	* @param @return    入参
	* @return DeviceReg    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月28日 下午5:43:39 
	* @version V1.0
	 */
	DeviceReg selectByImei(String imei);
}