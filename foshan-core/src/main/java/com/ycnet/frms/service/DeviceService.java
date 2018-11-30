package com.ycnet.frms.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.DeviceEntityVo;
import com.ycnet.frms.vo.DeviceLockStatusEntityVo;
import com.ycnet.frms.vo.mobile.DeviceEntityCountBean;
import com.ycnet.frms.vo.mobile.DeviceEntityDtoBean;
import com.ycnet.frms.vo.mobile.DeviceEntityMobile;
import com.ycnet.frms.vo.mobile.DeviceEntityVoMobile;
import com.ycnet.frms.vo.mobile.DeviceMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;
import com.ycnet.frms.vo.mobile.RackDetailsBean;

public interface DeviceService {

	/**
	 * 
	* @Title: queryDeviceListForOrgId 
	* @Description: 查询该组织机构下的中控器信息 
	* @param @param session
	* @param @param userId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月12日 下午2:48:15 
	* @version V1.0
	 * @param pb 
	 * @param codCode 
	 * @param devCode 
	 * @param devName 
	 */
	List<DeviceEntityDtoBean> queryDeviceListForOrgId(PageBean pb, Long orgId, String devName, String devCode, String codCode); 

	/**
	 * 
	* @Title: queryDeviceInfoRoom 
	* @Description: 查询中控器详情（机房） 
	* @param @param session
	* @param @param userId
	* @param @param codId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月12日 下午3:24:34 
	* @version V1.0
	 */
	DeviceEntityVoMobile queryDeviceInfoRoom(Long codId);

	/**
	 * 
	* @Title: verifyBluetoothBound 
	* @Description: 校验蓝牙是否绑定 
	* @param @param session
	* @param @param userId
	* @param @param macs
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月13日 上午10:00:19 
	* @version V1.0
	 */
	List<DeviceEntityMobile> verifyBluetoothBound(String macs);

	
	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 绑定中控器
	 * @param @param device
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午2:19:41
	 * @version V1.0
	 */
	int insertSelective(DeviceEntity device);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 绑定中控器
	 * @param @param device
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午2:19:41
	 * @version V1.0
	 * @param user 
	 * @param hardVersion 
	 */
	int band(DeviceEntity device, Users user, String hardVersion);

	/**
	 * 
	 * @Title: selectByCodMac
	 * @Description: 判断codMac是否已经绑定
	 * @param @param codMac
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午3:36:06
	 * @version V1.0
	 * @param codState 
	 */
	int selectByCodMac(String codMac, String codState);

	/**
	 * 
	* @Title: queryDeviceEntityList 
	* @Description: 中控器列表查询 
	* @param @param session
	* @param @param device
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年4月15日 下午2:20:05 
	* @version V1.0
	 * @param pb 
	 */
	PageBean queryDeviceEntityList(PageBean pb, DeviceEntityVo device);

	/**
	 * 
	* @Title: queryDeviceInfo 
	* @Description: 中控器详情 
	* @param @param codId
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年4月15日 下午4:43:50 
	* @version V1.0
	 */
	DeviceLockStatusEntityVo queryDeviceInfo(Long codId);

	/**
	 * 设备状态查询
	* @Title: queryFacilityState 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pb
	* @param @param deviceEntity
	* @param @param orgId
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月15日 下午4:37:34 
	* @version V1.0
	 * @param curStatus 
	 */
	PageBean queryFacilityState(PageBean pb, DeviceEntity deviceEntity, Long orgId, String curStatus);

	/**
	 * 
	 * @Title: queryByYearAndMonth
	 * @Description: 根据年/月查询，当月设备安装数量
	 * @param @param year
	 * @param @param month
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 上午10:43:36
	 * @version V1.0
	 * @param user 
	 * @param areaCode1 
	 * @param areaCode2 
	 */
	int queryByYearAndMonth(String year, String month, Users user, String areaCode1, String areaCode2);

	/**
	 * 
	 * @Title: selectByCodCode
	 * @Description: 判断codCode是否已经绑定
	 * @param @param codCode
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 下午5:01:24
	 * @version V1.0
	 * @param codState 
	 */
	DeviceEntity selectByCodCode(String codCode, String codState);

	/**
	 * 
	 * @Title: selectByImei
	 * @Description: 判断codImei是否已经绑定
	 * @param @param codImei
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 下午5:04:37
	 * @version V1.0
	 */
	int selectByImei(String codImei);

	/**
	 * 按位置浏览锁设施状态地图显示数据查询
	* @Title: queryDeviceEntityListByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return List<DeviceEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月20日 下午1:47:09 
	* @version V1.0
	 * @param deviceEntity 
	 */
	HashMap<String, Object> queryDeviceEntityListByOrgId(Long orgId,String devName,String areaCode1);
	/**
	 * 已经安装设备总数
	* @Title: queryByAreaCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午1:27:26 
	* @version V1.0
	 * @param areaCode2 
	 * @param user 
	 */
	int queryByAreaCode(String areaCode1, String areaCode2, Users user);

	/**
	 * 未安装设备总数
	* @Title: queryByAreaCodeNo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午1:35:42 
	* @version V1.0
	 * @param areaCode2 
	 * @param user 
	 */
	int queryByAreaCodeNo(String areaCode1, String areaCode2, Users user);
	/**
	 * 
	 * @Title: selectListByDevId
	 * @Description: 根据devId查询设施下的中控器
	 * @param @param devId
	 * @param @return 
	 * @return List<DeviceEntity> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月8日 上午10:05:48
	 * @version V1.0
	 */
	List<DeviceEntity> selectListByDevId(Long devId);

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改
	 * @param @param device
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月8日 上午10:36:48
	 * @version V1.0
	 */
	int updateByPrimaryKeySelective(DeviceEntity device);
	
	/**
	 * 根据设施ID查询机房中中控器列表
	 * @Title: findImDeviceByDevId   
	 * @param: devId
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	List<Map<String, Object>> findImDeviceByDevId(Long orgId, Long devId);

	/**
	 * 根据设施ID,查询中控器和盘分组信息
	* @Title: queryByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return DeviceMobile    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 下午2:12:29 
	* @version V1.0
	 */
	RackDetailsBean queryByDevId(Long devId);

	/**
	 * 根据设施ID,查询所属机柜和绑定机柜数
	* @Title: queryCabinetsAndBindCounts 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return DeviceEntityCountBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午1:25:20 
	* @version V1.0
	 */
	DeviceEntityCountBean queryCabinetsAndBindCounts(Long devId);

	/**
	 * 查询机房下机柜列表
	* @Title: queryCabinetsInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<FacilityCabinetsInfo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午2:13:32 
	* @version V1.0
	 */
	List<FacilityCabinetsInfo> queryCabinetsInfo(Long devId);

	/**
	 * 
	 * @Title: deleteDevice
	 * @Description: 删除中控器
	 * @param @param codId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月14日 上午9:46:43
	 * @version V1.0
	 */
	int deleteDevice(Long codId);
	
	/**
	 * 微信小程序根据编码和名称查询
	 * @param: devCode
	 * @param: devName
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	List<Map<String, Object>> queryDeviceAsWxApp(String devCode, String devName, int pageNo, int pageSize);

	/**
	 * 根据用户坐标查询符近的中控器
	 * @Title: getRangeDeviceByPoint   
	 * @param: curLng
	 * @param: curLat
	 * @param: distance	距离以千米为单位
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	List<Map<String, Object>> getRangeDeviceByPoint(double curLng, double curLat, int distance);
	
	Long queryImDeviceCountByDevId(Long devId);
}
