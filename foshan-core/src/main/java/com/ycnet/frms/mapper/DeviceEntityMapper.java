package com.ycnet.frms.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.vo.DeviceEntityFacilityBean;
import com.ycnet.frms.vo.DeviceEntityVo;
import com.ycnet.frms.vo.DeviceFactilityEntity;
import com.ycnet.frms.vo.mobile.DeviceEntityDtoBean;
import com.ycnet.frms.vo.mobile.DeviceEntityMobile;
import com.ycnet.frms.vo.mobile.DeviceEntityVoMobile;
import com.ycnet.frms.vo.mobile.DeviceMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;



public interface DeviceEntityMapper {
    int deleteByPrimaryKey(Long codId);

    int insert(DeviceEntity record);

    int insertSelective(DeviceEntity record);

    DeviceEntity selectByPrimaryKey(Long codId);

    int updateByPrimaryKeySelective(DeviceEntity record);

    int updateByPrimaryKey(DeviceEntity record);

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
	List<DeviceEntityDtoBean> queryDeviceListForOrgId(Long orgId, PageBean pb, String devName, String devCode, String codCode);


	/**
	 * 根据蓝牙地址查询中控器
	* @Title: queryDeviceCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codMac
	* @param @return    入参
	* @return DeviceEntityMobile    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 上午10:15:20 
	* @version V1.0
	 */
	DeviceEntityMobile queryDeviceCode(String codMac);

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
	 * @date 2018年4月13日 下午3:36:58
	 * @version V1.0
	 */
	int selectByCodMac(Map<String, Object> map);

	/**
	 * 
	* @Title: queryDeviceEntityList 
	* @Description: 查询中控器列表分页 
	* @param @param device
	* @param @param pb
	* @param @return    
	* @return List<DeviceEntityVo>
	* @author liucanghai 
	* @throws
	* @date 2018年4月15日 下午2:37:24 
	* @version V1.0
	 */
	List<DeviceEntityVo> queryDeviceEntityList(DeviceEntityVo device, PageBean pb);
	/**
	 * 查询设备数据
	* @Title: queryFacilityStateList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return List<DeviceEntityVo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月15日 下午4:45:15 
	* @version V1.0
	 */
	List<DeviceEntityVo> queryFacilityStateList(Map<String, Object> map);

	/**
	 * 
	* @Title: queryDeviceEntityCount 
	* @Description: 查询中控器列表分页 
	* @param @param device
	* @param @param pb
	* @param @return    
	* @return List<DeviceEntityVo>
	* @author liucanghai 
	* @throws
	* @date 2018年4月15日 下午2:37:24 
	* @version V1.0
	 */
	Integer queryDeviceEntityCount(DeviceEntityVo device, PageBean pb);



	/**
	 * 查询设备状态总条数
	* @Title: queryFacilityStateCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月15日 下午4:45:26 
	* @version V1.0
	 */
	Integer queryFacilityStateCount(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryByYearAndMonth
	 * @Description: 根据年/月查询，当月设备安装数量
	 * @param @param map
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 上午10:45:50
	 * @version V1.0
	 */
	int queryByYearAndMonth(Map<String, Object> map);

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
	 * @date 2018年4月17日 下午5:02:35
	 * @version V1.0
	 */
	DeviceEntity selectByCodCode(Map<String, Object> map);

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
	 * @date 2018年4月17日 下午5:05:26
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
	* @date 2018年4月20日 下午1:48:27 
	* @version V1.0
	 * @param deviceEntity 
	 */
	List<DeviceEntityFacilityBean> queryDeviceEntityListByOrgId(Long orgId, String devName, ArrayList<String> list2);

	
	/**
	 * 已经安装设备总数
	* @Title: queryByAreaCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午1:28:35 
	* @version V1.0
	 */
	int queryByAreaCode(Map<String, Object> map);

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
	 * @date 2018年5月8日 上午10:06:56
	 * @version V1.0
	 */
	List<DeviceEntity> selectListByDevId(Long devId);

	/**
	 * 未安装设备总数
	* @Title: queryByAreaCodeNo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param list2
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午1:36:35 
	* @version V1.0
	 */
	int queryByAreaCodeNo(Map<String, Object> map);
	
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
	* @date 2018年5月31日 下午2:18:19 
	* @version V1.0
	 */
	List<DeviceMobile> queryByDevId(Long devId);

	/**
	 * 查询机房下的的机柜数
	* @Title: queryCabinetsCounts 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<DeviceMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午1:29:16 
	* @version V1.0
	 */
	List<DeviceMobile> queryCabinetsCounts(Long devId);

	/**
	 * 查询机房下绑定的的中控器数
	* @Title: queryCabinetsAndBindCounts 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午1:37:57 
	* @version V1.0
	 */
	int queryCabinetsAndBindCounts(Long devId);

	/**
	 * 查询机房下机柜列表
	* @Title: queryCabinetsInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<FacilityCabinetsInfo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午2:14:41 
	* @version V1.0
	 */
	List<FacilityCabinetsInfo> queryCabinetsInfo(Long devId);
	
	/**
	 * 微信小程序根据编码和名称查询
	 * @Title: queryDeviceAsWxApp   
	 * @param: params
	 * @return: Map<String,Object>      
	 * @throws
	 */
	List<Map<String, Object>> queryDeviceAsWxApp(Map<String, Object> params);
	
	Long queryImDeviceCountByDevId(Long devId);
	/**
	 * 
	 * @Title: queryByDevIdAndCodState
	 * @Description: 查询设施下的已删除中控器
	 * @param @param map
	 * @param @return 
	 * @return DeviceEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月9日 下午2:04:32
	 * @version V1.0
	 */
	DeviceEntity queryByDevIdAndCodState(Map<String, Object> map);

	/**
	 * 
	 * @Title: selectDevId
	 * @Description: 根据devId查询
	 * @param @param devId
	 * @param @return 
	 * @return DeviceEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月18日 下午1:39:40
	 * @version V1.0
	 */
	List<DeviceEntity> selectDevId(Long devId);

	/**
	 * 机房查询
	* @Title: queryByDevAndCodId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codId
	* @param @param devId
	* @param @return    入参
	* @return DeviceEntityVoMobile    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月19日 上午8:59:04 
	* @version V1.0
	 */
	List<DeviceFactilityEntity> queryByPDevAndCodId(Long codId, Long devId);

	/**
	 * 光交查询
	* @Title: queryByDevAndCodId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codId
	* @param @param devId
	* @param @return    入参
	* @return DeviceEntityVoMobile    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月19日 上午9:04:49 
	* @version V1.0
	 */
	List<DeviceFactilityEntity> queryByDevAndCodId(Long codId, Long devId);

}