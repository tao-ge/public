package com.ycnet.frms.service;

import java.util.HashMap;
import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.DeviceStatus;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.DeviceInfoAndAccess;
import com.ycnet.frms.vo.mobile.DeviceRegVo;

public interface DeviceRegService {
	DeviceReg selectById(Long regId);
	int save(DeviceReg dr);
	int delete(Long regId);
	List<DeviceReg> queryList(DeviceReg dr,PageBean pb,Users user);
	int queryCount(DeviceReg dr,Users user);
	/*
	 * 设备注册
	 */
	int regDevice(DeviceReg dr,Users users);
	
	DeviceInfoAndAccess selectByMac(String devMac,Users users);
	List queryAll();
	List<FacilityAccess> queryListByCondition(DeviceReg dr, PageBean pb, Users user);
	Integer queryCountByCondition(DeviceReg dr, Users user);
	DeviceReg checkDeviceReg(String devMac, Long orgId);
	
	List<DeviceReg> queryLockBydevCode(String devCode, long orgId);
	List<DeviceReg> queryFacilityLock(Long devId);
	
	/**
	 * 
	 * @Title: hwregDevice
	 * @Description: 设备注册到华为平台
	 * @param @param dr
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月19日 下午7:47:12
	 * @version V1.0
	 * @param platform 
	 */
	int hwregDevice(DeviceReg dr, Users user, String platform);
	
	/**
	 * 
	 * @Title: remoteDeleteDevice
	 * @Description: 远程删除转发服务器设备
	 * @param @param devImei
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月20日 下午9:18:47
	 * @version V1.0
	 */
	int remoteDeleteDevice(String devImei, Users user);
	
	/**
	 * 
	 * @Title: remoteHWDeleteDevice
	 * @Description: 远程删除华为平台设备
	 * @param @param hwDeviceId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月21日 上午11:06:46
	 * @version V1.0
	 * @param devPlatform 
	 */
	int remoteHWDeleteDevice(String hwDeviceId, String devPlatform);
	
	/**
	 * 
	 * @Title: addDevice
	 * @Description: 添加到转发平台
	 * @param @param dr
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月25日 下午8:04:53
	 * @version V1.0
	 */
	String addDevice(String dsStr);
	
	/**
	 * 
	 * @Title: queryNotRegDevice
	 * @Description: 查询未注册的设备
	 * @param @return 
	 * @return List<DeviceReg> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月25日 下午8:10:00
	 * @version V1.0
	 */
	List<DeviceReg> queryNotRegDevice();
	
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
	 * @date 2017年12月28日 上午9:20:57
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
	 * @date 2018年1月22日 下午4:44:34
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
	HashMap<String, Object> selectDeviceRegAreaCode1(Long orgId, String areaCode1, String devName);
	
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
	 * @date 2018年2月7日 下午2:11:44
	 * @version V1.0
	 * @param orgId 
	 */
	List<DeviceReg> queryByDevId(Long devId, Long orgId);
	
	/**
	 * 
	 * @Title: queryAccessByMac
	 * @Description: 根据mac查询设施是否授权
	 * @param @param devMac
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月2日 上午10:24:17
	 * @version V1.0
	 */
	int queryAccessByMac(String devMac, Users user);
	/**
	 * 修改布防状态
	* @Title: updateRedeploying 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param tid
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月24日 上午11:31:11 
	* @version V1.0
	 * @param imei 
	 * @param pal 
	 * @param user 
	 * @throws Exception 
	 */
	int updateRedeploying(String tid,Long regId, String pal, String imei, Users user) throws Exception;
	/**
	 * 查询锁的详细信息
	* @Title: queryLockInfoByRegId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param regId
	* @param @return    入参
	* @return DeviceReg    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月24日 下午2:04:52 
	* @version V1.0
	 * @param orgId 
	 */
	DeviceRegVo queryLockInfoByRegId(Long regId, Long orgId);
	
	/**
	 * 
	 * @Title: queryByCondition
	 * @Description: 根据条件查询
	 * @param @param pb
	 * @param @param dr
	 * @param @param user
	 * @param @return 
	 * @return PageBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月25日 下午3:13:09
	 * @version V1.0
	 */
	PageBean queryByCondition(PageBean pb, DeviceReg dr, Users user);
	/**
	 * 接口查询在线的有权限开的智能锁
	* @Title: queryByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pb
	* @param @param dr
	* @param @param user
	* @param @return    入参
	* @return List<DeviceReg>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月25日 下午5:43:50 
	* @version V1.0
	 */
	List<DeviceReg> queryByConditions(PageBean pb, DeviceReg dr, Users user);
	/**
	 * 查询imei
	* @Title: selectByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param palteFrom
	* @param @return    入参
	* @return List<DeviceReg>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月27日 下午4:46:30 
	* @version V1.0
	 */
	List<DeviceReg> selectByConditions(Long orgId, String palteFrom);
	
	/**
	 * 
	 * @Title: httpsConection
	 * @Description: 通信协议
	 * @param @param string
	 * @param @param devImei
	 * @param @param devPlatform
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月27日 下午2:34:55
	 * @version V1.0
	 * @param userId 
	 * @throws Exception 
	 */
	int httpsConection(String string, String devImei, String devPlatform, Long userId,Long expired_time ) throws Exception;
	/**
	 * 干城上传开关锁通知
	* @Title: insertAndManage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceStatus
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月30日 下午1:48:42 
	* @version V1.0
	 * @param userId 
	 * @param time 
	 * @param time 
	 * @param imei 
	 */
	int insertAndManage(DeviceStatus deviceStatus, Long userId, String time);
	
}
