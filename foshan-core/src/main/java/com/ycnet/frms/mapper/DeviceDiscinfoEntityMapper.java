package com.ycnet.frms.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.vo.DeviceDiscinfoEntityBean;
import com.ycnet.frms.vo.DifferentPortsBean;
import com.ycnet.frms.vo.FiberdiscGroupEntityVo;
import com.ycnet.frms.vo.FiberdiscGroupVo;
import com.ycnet.frms.vo.FiberdiscVo;
import com.ycnet.frms.vo.mobile.BindDiscNumMobile;
import com.ycnet.frms.vo.mobile.DeviceDiscinfosMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;
import com.ycnet.frms.vo.mobile.FiberdiscGroupOpdVo;



public interface DeviceDiscinfoEntityMapper {
    int deleteByPrimaryKey(Long discId);

    int insert(DeviceDiscinfoEntity record);

    int insertSelective(DeviceDiscinfoEntity record);

    DeviceDiscinfoEntity selectByPrimaryKey(Long discId);

    int updateByPrimaryKeySelective(DeviceDiscinfoEntity record);

    int updateByPrimaryKey(DeviceDiscinfoEntity record);


    /**
     * 
    * @Title: queryForDevId 
    * @Description: 查询机柜绑定的端子控制器 
    * @param @param devId
    * @param @return    
    * @return List<DeviceDiscinfoEntity>
    * @author liucanghai 
    * @throws
    * @date 2018年4月12日 下午6:32:07 
    * @version V1.0
     * @param codId 
     */
	List<DeviceDiscinfoEntity> queryForDevId(Long devId, Long codId);

	/**
	 * 根据设施查询熔纤盘分组信息
	* @Title: queryDeviceDisinfoByDdevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<FiberdiscGroupVo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月13日 下午1:12:37 
	* @version V1.0
	 */
	List<FiberdiscGroupOpdVo> queryDeviceDisinfoByDdevId(Long devId);

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
	List<FiberdiscVo> queryDiscCode(String discCode, Long devId);

	/**
	 * 
	 * @Title: selectByCodIdAndDiscContrCode
	 * @Description: 查询同个中控器下，端子控制器编码是否已经绑定
	 * @param @param map
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午5:31:35
	 * @version V1.0
	 */
	int selectByCodIdAndDiscContrCode(Map<String, Object> map);
	
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
	List<DeviceDiscinfoEntityBean> queryDeviceDisinfoList(Map<String, Object> map);

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
	Integer queryDeviceDisinfoCount(Map<String, Object> map);

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
	List<DeviceDiscinfoEntityBean> querydevNames(Long codId);

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
	List<DeviceDiscinfoEntityBean> queryDeviceDisinfoFiberdisc(DeviceDiscinfoEntityBean deviceDiscinfoEntityBean);

	/**
	 * 熔纤盘总数
	* @Title: queryByAreaCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param list2
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午3:11:08 
	* @version V1.0
	 */
	int queryByAreaCode(Map<String, Object> map);

	/**
	 * 已安装熔纤盘数
	* @Title: queryByAreaCodeY 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param list2
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午3:17:17 
	* @version V1.0
	 */
	int queryByAreaCodeY(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryDifferentDats
	 * @Description: 差异数据统计查询列表
	 * @param @param map
	 * @param @return 
	 * @return List<DeviceDiscinfoEntityBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月10日 上午11:04:53
	 * @version V1.0
	 */
	List<DeviceDiscinfoEntityBean> queryDifferentDats(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryDifferentDatsCount
	 * @Description: 差异数据统计查询数量
	 * @param @param map
	 * @param @return 
	 * @return Integer 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月10日 上午11:06:21
	 * @version V1.0
	 */
	Integer queryDifferentDatsCount(Map<String, Object> map);

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
	List<DifferentPortsBean> queryReportOccupyByOrgId(Long orgId);

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
	List<DifferentPortsBean> queryReportOccupyByArea(Long orgId);

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
	DifferentPortsBean queryAreaCodeIsNull(Long orgId);

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
	List<FacilityCabinetsInfo> queryCabinetsInfo(Long codId);

	/**
	 * 
	 * @Title: queryByCodId
	 * @Description: 查询中控器下没有删除的端子控制器
	 * @param @param codId
	 * @param @return 
	 * @return List<DeviceDiscinfoEntity> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月14日 上午9:54:02
	 * @version V1.0
	 */
	List<DeviceDiscinfoEntity> queryByCodId(Long codId);

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
	List<DeviceDiscinfoEntity> selectByDevId(Long devId);

	/**
	 * 
	 * @Title: updateByCodId
	 * @Description: 删除端子控制器
	 * @param @param codId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月9日 下午3:44:28
	 * @version V1.0
	 */
	int deleteByCodId(Long codId);

	/**
	 * 
	 * @Title: qeuryByGroupIdAndState
	 * @Description: 根据groupId查询删除的端子控制器
	 * @param @param map
	 * @param @return 
	 * @return DeviceDiscinfoEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月10日 下午3:32:01
	 * @version V1.0
	 */
	DeviceDiscinfoEntity qeuryByGroupIdAndState(Map<String, Object> map);

	/**
	 * 
	 * @Title: selectByGroupId
	 * @Description: 根据groupId查询
	 * @param @param groupId
	 * @param @return 
	 * @return List<DeviceDiscinfoEntity> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月11日 上午10:12:17
	 * @version V1.0
	 */
	List<DeviceDiscinfoEntity> selectByGroupId(Long groupId);

	/**
	 * 
	 * @Title: qeuryByDiscIdAndState
	 * @Description: 根据discId查询已删除的端子控制器
	 * @param @param map
	 * @param @return 
	 * @return DeviceDiscinfoEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月11日 上午11:20:46
	 * @version V1.0
	 */
	DeviceDiscinfoEntity qeuryByDiscIdAndState(Map<String, Object> map);
	
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
	DeviceDiscinfoEntity selectByDeviceDiscinfo(Long discId);

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
	List<BindDiscNumMobile> queryBindDiscNum(Long devId, Long orgId);

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
	DeviceDiscinfosMobile queryDeviceDiscinfo(Long codId, Long devId, String discContrId, Long orgId);

	
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
	DeviceDiscinfoEntity queryByDiscIdAndStateAndContrCode(Long discId, String discContrState, String discContrCode,Long codId );

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
	int deleteByByDiscIdAndStateAndContrCode(DeviceDiscinfoEntity disc);
	
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
	int updateByDiscIdAndStateAndContrCode(DeviceDiscinfoEntity disc);
	

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
	List<DeviceDiscinfoEntity> selectByContrId(String discContrId, Long orgId);
	
	
	/**
	 * 
	 * @Title: qeuryByDiscIdAndState
	 * @Description: 根据discId查询已删除的端子控制器
	 * @param @param discId
	 * @param @param string
	 * @param @return 
	 * @return DeviceDiscinfoEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月11日 上午11:19:16
	 * @version V1.0
	 */
	DeviceDiscinfoEntity queryByDiscIdAndStates(Long discId, Integer discContrState);
	
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
	int updateByCodeAndId(DeviceDiscinfoEntity record);

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
	int selectByDiscId(Long discId);
}