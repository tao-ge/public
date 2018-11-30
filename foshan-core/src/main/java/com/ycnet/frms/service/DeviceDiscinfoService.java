package com.ycnet.frms.service;


import java.util.List;

import com.ycnet.core.util.Page;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.DeviceDiscinfoEntityBean;
import com.ycnet.frms.vo.DifferentPortsBean;
import com.ycnet.frms.vo.FiberdiscGroupEntityVo;
import com.ycnet.frms.vo.FiberdiscGroupVo;
import com.ycnet.frms.vo.mobile.BindDiscNumMobile;
import com.ycnet.frms.vo.mobile.DeviceDiscinfoEntityMobile;
import com.ycnet.frms.vo.mobile.DeviceDiscinfosMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;
import com.ycnet.frms.vo.mobile.FiberdiscGroupOpdVo;

public interface DeviceDiscinfoService {

	/**
	 * 根据设施查询熔纤盘分组信息
	* @Title: queryDeviceDisinfoByDdevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<FiberdiscGroupVo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月13日 下午1:08:40 
	* @version V1.0
	 */
	List<FiberdiscGroupOpdVo> queryDeviceDisinfoByDdevId(Long devId);

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 端子控制器绑定熔纤盘
	 * @param @param dd
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午1:41:22
	 * @version V1.0
	 */
	int insertSelective(DeviceDiscinfoEntity dd);

		/**
	 * 
	 * @Title: insertSelective
	 * @Description: 端子控制器绑定熔纤盘
	 * @param @param dd
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午1:41:22
	 * @version V1.0
	 * @param bandType 
	 * @param user 
	 */
	int band(DeviceDiscinfoEntity dd, Users user, long bandType);

	/**
	 * 
	 * @Title: selectByCodIdAndDiscContrCode
	 * @Description: 查询同个中控器下，端子控制器编码是否已经绑定
	 * @param @param codId
	 * @param @param discContrCode
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午5:29:05
	 * @version V1.0
	 */
	int selectByCodIdAndDiscContrCode(Long codId, String discContrCode);

	/**
	 * 设备上报日志记录
	* @Title: queryDeviceDisinfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pb
	* @param @param deviceDiscinfoEntityBean
	* @param @param orgId
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月16日 下午4:48:11 
	* @version V1.0
	 */
	PageBean queryDeviceDisinfo(PageBean pb, DeviceDiscinfoEntityBean deviceDiscinfoEntityBean, Long orgId);

	/**
	 * 所属设施名称查询
	* @Title: querydevNames 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param codId
	* @param @return    入参
	* @return List<String>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月17日 下午2:56:00 
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
	* @date 2018年4月18日 下午4:10:17 
	* @version V1.0
	 */
	List<DeviceDiscinfoEntityBean> queryDeviceDisinfoFiberdisc(DeviceDiscinfoEntityBean deviceDiscinfoEntityBean);

	/**
	 * 端子占用情况查询
	* @Title: queryDeviceDisinfoByDisId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return DeviceDiscinfoEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月19日 下午4:23:03 
	* @version V1.0
	 */
	DeviceDiscinfoEntity queryDeviceDisinfoByDisId(Long discId);

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
	 * @param areaCode2 
	 * @param user 
	 */
	int queryByAreaCode(String areaCode1, String areaCode2, Users user);

	/**
	 * 已安装熔纤盘数量
	* @Title: queryByAreaCodeY 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午3:16:20 
	* @version V1.0
	 * @param areaCode2 
	 * @param user 
	 */
	int queryByAreaCodeY(String areaCode1, String areaCode2, Users user);

	/**
	 * 
	 * @Title: queryDifferentDats
	 * @Description: 差异数据统计列表查询
	 * @param @param pb
	 * @param @param ddeBean
	 * @param @param user
	 * @param @return 
	 * @return PageBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月10日 上午11:01:47
	 * @version V1.0
	 */
	PageBean queryDifferentDats(PageBean pb, DeviceDiscinfoEntityBean ddeBean, Users user);

	/**
	 * 查询端子差异数据统计
	* @Title: queryByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月24日 下午3:35:32 
	* @version V1.0
	 * @param pb 
	 */
	PageBean queryByOrgId(Long orgId, PageBean pb);

	/**
	 * 根据区域编码查询端子数据统计
	* @Title: queryDifferentTotalsByArea 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @param differentPortsBean
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月25日 上午10:24:27 
	* @version V1.0
	 */
	PageBean queryDifferentTotalsByArea(Long orgId, PageBean pb, DifferentPortsBean differentPortsBean);

	/**
	 * 根据盘ID.查询端子占用状态
	* @Title: queryDeviceDisinfoEntityByDisId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return DeviceDiscinfoEntityMobile    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 上午11:26:36 
	* @version V1.0
	 */
	List<DeviceDiscinfoEntityMobile> queryDeviceDisinfoEntityByDisId(Long discId);

	/**
	 * 根据中控器ID查询机柜列表
	* @Title: queryCabinetsInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<FacilityCabinetsInfo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午5:13:57 
	* @version V1.0
	 */
	List<FacilityCabinetsInfo> queryCabinetsInfo(Long codId);

	/**
	 * 刷新熔纤盘
	* @Title: queryopdDeviceDisinfoEntityByDisId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return List<DeviceDiscinfoEntityMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月22日 下午1:22:51 
	* @version V1.0
	 */
	List<DeviceDiscinfoEntityMobile> queryopdDeviceDisinfoEntityByDisId(Long discId);

	/**
	 * 
	 * @Title: queryReportOccupyByOrgId
	 * @Description: 查询上报端子占用情况
	 * @param @param orgId
	 * @param @return 
	 * @return List<DifferentPortsBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午10:47:23
	 * @version V1.0
	 */
	List<DifferentPortsBean> queryReportOccupyByOrgId(Long orgId);

	/**
	 * 
	 * @Title: queryAreaCodeIsNull
	 * @Description: 根据orgId查询区域编码为空的数据
	 * @param @param orgId
	 * @param @return 
	 * @return DifferentPortsBean 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午11:20:24
	 * @version V1.0
	 */
	DifferentPortsBean queryAreaCodeIsNull(Long orgId);

	/**
	 * 
	 * @Title: queryReportOccupyByArea
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param orgId
	 * @param @return 
	 * @return List<DifferentPortsBean> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 下午1:18:47
	 * @version V1.0
	 */
	List<DifferentPortsBean> queryReportOccupyByArea(Long orgId);

	/**
	 * 
	 * @Title: deleteDeviceDiscinfo
	 * @Description: 删除端子控制器
	 * @param @param discId
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月9日 下午3:59:24
	 * @version V1.0
	 * @param user 
	 */
	int deleteDeviceDiscinfo(Long discId, Users user);

	/**
	 * 
	 * @Title: qeuryByGroupIdAndState
	 * @Description: 根据groupId查询删除的端子控制器
	 * @param @param groupId
	 * @param @param string
	 * @param @return 
	 * @return DeviceDiscinfoEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月10日 下午3:28:52
	 * @version V1.0
	 */
	DeviceDiscinfoEntity qeuryByGroupIdAndState(Long groupId, String discContrState);

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
	DeviceDiscinfoEntity qeuryByDiscIdAndState(Long discId, String discContrState);

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
	 * @param orgId 
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
	 * 绑定检测板
	* @Title: bandContr 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param dd
	* @param @param user
	* @param @param bandType
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年9月1日 下午2:29:03 
	* @version V1.0
	 */
	int bandContr(DeviceDiscinfoEntity dd, Users user, Long bandType);

}
