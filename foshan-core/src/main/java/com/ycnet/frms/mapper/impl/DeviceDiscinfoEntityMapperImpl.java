package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper;
import com.ycnet.frms.vo.DeviceDiscinfoEntityBean;
import com.ycnet.frms.vo.DifferentPortsBean;
import com.ycnet.frms.vo.FiberdiscGroupEntityVo;
import com.ycnet.frms.vo.FiberdiscGroupVo;
import com.ycnet.frms.vo.FiberdiscVo;
import com.ycnet.frms.vo.mobile.BindDiscNumMobile;
import com.ycnet.frms.vo.mobile.DeviceDiscinfosMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;
import com.ycnet.frms.vo.mobile.FiberdiscGroupOpdVo;

@Repository("deviceDiscinfoEntityMapper")
public class DeviceDiscinfoEntityMapperImpl extends BaseSqlSupport implements DeviceDiscinfoEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long discId) {
		return this.delete("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.deleteByPrimaryKey",discId);
	}

	@Override
	public int insert(DeviceDiscinfoEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(DeviceDiscinfoEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.insertSelective",record); 
	}

	@Override
	public DeviceDiscinfoEntity selectByPrimaryKey(Long discId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.selectByPrimaryKey",discId);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceDiscinfoEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(DeviceDiscinfoEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.updateByPrimaryKey",record);
	}

	/**
	 * 机房中机柜绑定端子控制器数,熔纤盘数
	 */
	@Override
	public List<DeviceDiscinfoEntity> queryForDevId(Long devId,Long codId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("codId", codId);
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryForDevId",map);
	}

	@Override
	public List<FiberdiscGroupOpdVo> queryDeviceDisinfoByDdevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryDeviceDisinfoByDdevId",devId);
	}

	
	@Override
	public int selectByCodIdAndDiscContrCode(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.selectByCodIdAndDiscContrCode",map);
	}

	/**
	 * 
	* @Title: queryDiscCode 
	* @Description: 根据盘编码查询端子编码 
	* @param @param discCode
	* @param @param devId
	* @param @return    
	* @return List<FiberdiscVo>
	* @author liucanghai 
	* @throws
	* @date 2018年4月18日 下午1:34:55 
	* @version V1.0
	 */
	@Override
	public List<FiberdiscVo> queryDiscCode(String discCode, Long devId) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("discCode", discCode);
		map.put("devId", devId);
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryDiscCode",map);
	}

	/**
	 * 设备上报日志记录查询
	* @Title: queryDeviceDisinfoList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return List<DeviceDiscinfoEntityBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月16日 下午4:50:57 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntityBean> queryDeviceDisinfoList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryDeviceDisinfoList",map);
	}

	/**
	 * 设备上报日志记录总条数
	* @Title: queryDeviceDisinfoCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月16日 下午4:51:16 
	* @version V1.0
	 */
	@Override
	public Integer queryDeviceDisinfoCount(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryDeviceDisinfoCount",map);
	}

	/**
	 * 查询所属设施名称
	* @Title: querydevNames 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codId
	* @param @return    入参
	* @return List<String>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月17日 下午2:57:01 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntityBean> querydevNames(Long codId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.querydevNames",codId);
	}

	/**
	 * 端口占用情况
	* @Title: queryDeviceDisinfoFiberdisc 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceDiscinfoEntityBean
	* @param @return    入参
	* @return List<DeviceDiscinfoEntityBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月18日 下午4:13:02 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntityBean> queryDeviceDisinfoFiberdisc(
			DeviceDiscinfoEntityBean deviceDiscinfoEntityBean) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryDeviceDisinfoFiberdisc",deviceDiscinfoEntityBean);
	}

	/**
	 *熔纤盘总数
	* @Title: queryByAreaCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午3:07:21 
	* @version V1.0
	 */
	@Override
	public int queryByAreaCode(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryByAreaCode",map);
	}
	
	/**
	 *已熔纤盘总数
	* @Title: queryByAreaCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午3:07:21 
	* @version V1.0
	 */
	@Override
	public int queryByAreaCodeY(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryByAreaCodeY",map);
	}

	/**
	 * 
	 * @Title: queryDifferentDats
	 * @Description: 差异数据统计查询列表
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月10日 上午11:05:34
	 * @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntityBean> queryDifferentDats(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryDifferentDats",map);
	}

	/**
	 * 
	 * @Title: queryDifferentDatsCount
	 * @Description: 差异数据统计查询数量
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月10日 上午11:06:48
	 * @version V1.0
	 */
	@Override
	public Integer queryDifferentDatsCount(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryDifferentDatsCount",map);
	}

	/**
	 * 查询上报的端子占用情况
	* @Title: queryReportOccupyByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return List<DifferentPortsBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月24日 下午3:48:56 
	* @version V1.0
	 */
	@Override
	public List<DifferentPortsBean> queryReportOccupyByOrgId(Long orgId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryReportOccupyByOrgId",orgId);
	}

	/**
	 * 根据区域编码查询上报仔占用
	* @Title: queryReportOccupyByArea 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param differentPortsBean
	* @param @return    入参
	* @return List<DifferentPortsBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月25日 上午10:32:42 
	* @version V1.0
	 */
	@Override
	public List<DifferentPortsBean> queryReportOccupyByArea(Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryReportOccupyByArea",map);
	}

	/**
	 * 根据orgId查询区域编码为空的数据
	* @Title: queryAreaCodeIsNull 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return DifferentPortsBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月28日 上午10:02:02 
	* @version V1.0
	 */
	@Override
	public DifferentPortsBean queryAreaCodeIsNull(Long orgId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryAreaCodeIsNull",orgId);
	}

	/**
	 * 根据中控器ID查询机柜列表
	* @Title: queryCabinetsInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<FacilityCabinetsInfo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午5:15:19 
	* @version V1.0
	 */
	@Override
	public List<FacilityCabinetsInfo> queryCabinetsInfo(Long codId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryCabinetsInfo",codId);
	}

	/**
	 * 
	 * @Title: queryByCodId
	 * @Description: 查询中控器下没有删除的端子控制器
	 * @param @param codId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月14日 上午9:54:28
	 * @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntity> queryByCodId(Long codId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryByCodId",codId);
	}

	/**
	 * 根据设施查询端子控制器
	* @Title: selectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<DeviceDiscinfoEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月22日 上午10:50:11 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntity> selectByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.selectByDevId",devId);
	}

	/**
	 * 
	 * @Title: updateByCodId
	 * @Description: 删除端子控制器
	 * @param @param codId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月9日 下午3:45:55
	 * @version V1.0
	 */
	@Override
	public int deleteByCodId(Long codId) {
		return this.update("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.deleteByCodId",codId);
	}

	/**
	 * 
	 * @Title: qeuryByGroupIdAndState
	 * @Description: 根据groupId查询删除的端子控制器
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月10日 下午3:32:17
	 * @version V1.0
	 */
	@Override
	public DeviceDiscinfoEntity qeuryByGroupIdAndState(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.qeuryByGroupIdAndState",map);
	}

	/**
	 * 
	 * @Title: selectByGroupId
	 * @Description: 根据groupId查询
	 * @param @param groupId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月11日 上午10:12:51
	 * @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntity> selectByGroupId(Long groupId) {
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.selectByGroupId",groupId);
	}

	/**
	 * 
	 * @Title: qeuryByDiscIdAndState
	 * @Description: 根据discId查询已删除的端子控制器
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月11日 上午11:21:09
	 * @version V1.0
	 */
	@Override
	public DeviceDiscinfoEntity qeuryByDiscIdAndState(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.qeuryByDiscIdAndState",map);
	}

	/**
	 * 查询未删除的检测板
	* @Title: selectByDeviceDiscinfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return DeviceDiscinfoEntity    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年7月30日 下午3:07:42 
	* @version V1.0
	 */
	@Override
	public DeviceDiscinfoEntity selectByDeviceDiscinfo(Long discId) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.selectByDeviceDiscinfo",discId);
	}
	/**
	 * 查询已绑定的设备列表
	* @Title: queryBindDiscNum 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codId
	* @param @param devId
	* @param @return    入参
	* @return List<BindDiscNumMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月1日 下午1:26:52 
	* @version V1.0
	 */
	@Override
	public List<BindDiscNumMobile> queryBindDiscNum(Long devId, Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("orgId", orgId);
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryBindDiscNum",map);
	}

	/**
	 * 查询检测板的详细信息
	* @Title: queryDeviceDiscinfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codId
	* @param @param devId
	* @param @param discContrId
	* @param @param orgId
	* @param @return    入参
	* @return List<DeviceDiscinfosMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月1日 下午2:30:20 
	* @version V1.0
	 */
	@Override
	public DeviceDiscinfosMobile queryDeviceDiscinfo(Long codId, Long devId, String discContrId, Long orgId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("codId", codId);
		map.put("devId", devId);
		map.put("orgId", orgId);
		map.put("discContrId", discContrId);
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryDeviceDiscinfo",map);
	}
	/**
	 * 查询已绑定检测板但未绑定熔纤盘的数据
	* @Title: queryByDiscIdAndStateAndContrCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param string
	* @param @param string2
	* @param @param discContrCode
	* @param @return    入参
	* @return DeviceDiscinfoEntity    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年9月1日 下午1:20:47 
	* @version V1.0
	 */
	@Override
	public DeviceDiscinfoEntity queryByDiscIdAndStateAndContrCode(Long discId, String discContrState, String discContrCode,Long codId) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("discId", discId);
		map.put("codId", codId);
		map.put("discContrState", discContrState);
		map.put("discContrCode", discContrCode);
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryByDiscIdAndStateAndContrCode",map);
	}

	/**
	 * 删除绑定在检测板但未绑定熔纤盘的数据
	* @Title: deleteByByDiscIdAndStateAndContrCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param disc
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年9月1日 下午1:37:12 
	* @version V1.0
	 */
	@Override
	public int deleteByByDiscIdAndStateAndContrCode(DeviceDiscinfoEntity disc) {
		// TODO Auto-generated method stub
		return this.delete("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.deleteByByDiscIdAndStateAndContrCode",disc);
	}

	/**
	 * 更新绑定在检测板但未绑定熔纤盘的数据
	* @Title: deleteByByDiscIdAndStateAndContrCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param disc
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年9月1日 下午1:37:12 
	* @version V1.0
	 */
	@Override
	public int updateByDiscIdAndStateAndContrCode(DeviceDiscinfoEntity disc) {
		// TODO Auto-generated method stub
		return this.update("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.updateByDiscIdAndStateAndContrCode",disc);  
	}
	

	/**
	 * 查询已绑定的检测板
	* @Title: selectByContrId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discContrId
	* @param @param orgId
	* @param @return    入参
	* @return List<DeviceDiscinfoEntity>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年9月1日 下午3:16:29 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntity> selectByContrId(String discContrId, Long orgId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("discContrId", discContrId);
		map.put("orgId", orgId);
		return this.selectList("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.selectByContrId",map);
	}

	@Override
	public DeviceDiscinfoEntity queryByDiscIdAndStates(Long discId, Integer discContrState) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("discId", discId);
		map.put("discContrState", discContrState);
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.queryByDiscIdAndStates",map);
	}
	/**
	 * 通过code和ID改变原code的值
	* @Title: updateByCodeAndId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param record
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年9月2日 下午2:09:34 
	* @version V1.0
	 */
	@Override
	public int updateByCodeAndId(DeviceDiscinfoEntity record) {
		// TODO Auto-generated method stub
		return this.update("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.updateByCodeAndId",record);  
	}

	/**
	 * 该熔纤盘是否绑定端子控制器
	* @Title: selectByDiscId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月5日 下午3:07:47 
	* @version V1.0
	 */
	@Override
	public int selectByDiscId(Long discId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper.selectByDiscId",discId);  
	}
}
  
    