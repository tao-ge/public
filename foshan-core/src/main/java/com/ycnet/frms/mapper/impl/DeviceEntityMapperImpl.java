package com.ycnet.frms.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.mapper.DeviceEntityMapper;
import com.ycnet.frms.vo.DeviceEntityFacilityBean;
import com.ycnet.frms.vo.DeviceEntityVo;
import com.ycnet.frms.vo.DeviceFactilityEntity;
import com.ycnet.frms.vo.mobile.DeviceEntityDtoBean;
import com.ycnet.frms.vo.mobile.DeviceEntityMobile;
import com.ycnet.frms.vo.mobile.DeviceMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;

@Repository("deviceEntityMapper")
public class DeviceEntityMapperImpl extends BaseSqlSupport implements DeviceEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long codId) {
		return this.delete("com.ycnet.frms.mapper.DeviceEntityMapper.deleteByPrimaryKey",codId);
	}

	@Override
	public int insert(DeviceEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(DeviceEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceEntityMapper.insertSelective",record); 
	}

	@Override
	public DeviceEntity selectByPrimaryKey(Long codId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.selectByPrimaryKey",codId);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceEntityMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(DeviceEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceEntityMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<DeviceEntityDtoBean> queryDeviceListForOrgId(Long orgId, PageBean pb, String devName, String devCode, String codCode) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("orgId", orgId);
		map.put("devName", devName);
		map.put("devCode", devCode);
		map.put("codCode", codCode);
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.queryDeviceListForOrgId",map);
	}

	/**
	 * 根据MAC查询中控器
	 */
	@Override
	public DeviceEntityMobile queryDeviceCode(String codMac) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.queryDeviceCode",codMac);
	}

	/**
	 * 
	 * @Title: selectByCodMac
	 * @Description: 判断codMac是否已经绑定
	 * @param @param codMac
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 下午5:03:08
	 * @version V1.0
	 */
	@Override
	public int selectByCodMac(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.selectByCodMac",map);
	}

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
	@Override
	public List<DeviceEntityVo> queryDeviceEntityList(DeviceEntityVo device, PageBean pb) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("device", device);
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.queryDeviceEntityList",map);
	}

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
	@Override
	public Integer queryDeviceEntityCount(DeviceEntityVo device, PageBean pb) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("device", device);
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.queryDeviceEntityCount",map);
	}

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
	@Override
	public List<DeviceEntityVo> queryFacilityStateList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.queryFacilityStateList",map);
	}

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
	@Override
	public Integer queryFacilityStateCount(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.queryFacilityStateCount",map);
	}

	/**
	 * 
	 * @Title: queryByYearAndMonth
	 * @Description: 根据年/月查询，当月设备安装数量
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 上午10:46:20
	 * @version V1.0
	 */
	@Override
	public int queryByYearAndMonth(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.queryByYearAndMonth",map);
	}

	/**
	 * 
	 * @Title: selectByCodCode
	 * @Description: 判断codCode是否已经绑定
	 * @param @param codCode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 下午5:03:29
	 * @version V1.0
	 */
	@Override
	public DeviceEntity selectByCodCode(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.selectByCodCode",map);
	}

	/**
	 * 
	 * @Title: selectByImei
	 * @Description: 判断codImei是否已经绑定
	 * @param @param codImei
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 下午5:05:41
	 * @version V1.0
	 */
	@Override
	public int selectByImei(String codImei) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.selectByImei",codImei);
	}


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
	 */
	@Override
	public List<DeviceEntityFacilityBean> queryDeviceEntityListByOrgId(Long orgId, String devName,
			ArrayList<String> list2) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("devName", devName);
		map.put("areaCodes", list2);
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.queryDeviceEntityListByOrgId",map);
	}

	/**
	 * 已安装设备总数
	* @Title: queryByAreaCodeNo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午1:35:42 
	* @version V1.0
	 */
	@Override
	public int queryByAreaCode(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.queryByAreaCode",map);
	}

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
	 */
	@Override
	public int queryByAreaCodeNo(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.queryByAreaCodeNo",map);
	}

	/**
	 * 
	 * @Title: selectListByDevId
	 * @Description: 根据devId查询设施下的中控器
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月8日 上午10:07:32
	 * @version V1.0
	 */
	@Override
	public List<DeviceEntity> selectListByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.selectListByDevId",devId);
	}

	/**
	 * 根据设施ID查询机房中中控器列表
	 * @Title: findImDeviceByDevId   
	 * @param: devId
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	@Override
	public List<Map<String, Object>> findImDeviceByDevId(Long orgId, Long devId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("devId", devId);
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.findImDeviceByDevId",map);
	}

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
	@Override
	public List<DeviceMobile> queryByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.queryByDevId",devId);
	}

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
	@Override
	public List<DeviceMobile> queryCabinetsCounts(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.queryCabinetsCounts",devId);
	}

	/**
	 * 查询机房下绑定的的机柜数
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
	@Override
	public int queryCabinetsAndBindCounts(Long devId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.queryCabinetsAndBindCounts",devId);
	}

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
	@Override
	public List<FacilityCabinetsInfo> queryCabinetsInfo(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.queryCabinetsInfo",devId);
	}

	/**
	 * 微信小程序根据编码和名称查询
	 * @param params
	 * @return list
	 */
	@Override
	public List<Map<String, Object>> queryDeviceAsWxApp(Map<String, Object> params) {
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.queryDeviceAsWxApp", params);
	}
	
	@Override
	public Long queryImDeviceCountByDevId(Long devId) {
		return  this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.queryImDeviceCountByDevId",devId);
	}

	/**
	 * 
	 * @Title: queryByDevIdAndCodState
	 * @Description: 查询设施下的已删除中控器
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月9日 下午2:06:03
	 * @version V1.0
	 */
	@Override
	public DeviceEntity queryByDevIdAndCodState(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceEntityMapper.queryByDevIdAndCodState",map);
	}

	/**
	 * 
	 * @Title: selectDevId
	 * @Description: 根据devId查询
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月18日 下午1:40:58
	 * @version V1.0
	 */
	@Override
	public List<DeviceEntity> selectDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.selectDevId",devId);
	}

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
	@Override
	public List<DeviceFactilityEntity> queryByPDevAndCodId(Long codId, Long devId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("codId", codId);
		map.put("devId", devId);
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.queryByPDevAndCodId",map);
	}

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
	@Override
	public List<DeviceFactilityEntity> queryByDevAndCodId(Long codId, Long devId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("codId", codId);
		map.put("devId", devId);
		return this.selectList("com.ycnet.frms.mapper.DeviceEntityMapper.queryByDevAndCodId",map);
	}
}
  
    