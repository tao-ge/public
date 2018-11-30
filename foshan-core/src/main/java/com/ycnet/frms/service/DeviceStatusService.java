package com.ycnet.frms.service;



import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceStatus;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.DeviceStatusBean;
import com.ycnet.frms.vo.DeviceStatusConditionBean;

public interface DeviceStatusService {

	/**
	 * 按条件查询数据，支持分页、高级查询
	 * @param bean
	 * @param user
	 * @param pb
	 * @return
	 */
	PageBean queryByConditionBean(DeviceStatusConditionBean bean,Users user,PageBean pb);
	
	/**
	 * 获取设施的当前状态
	 * @param bean
	 * @param user
	 * @param pb
	 * @return
	 */
	PageBean queryCurStatusByConditionBean(DeviceStatusConditionBean bean,Users user,PageBean pb);
	
	/**
	 * 
	* @Title: deleteDevStatusByImeiAndMac 
	* @Description: TODO(根据IMEI和MAC删除设备状态记录) 
	* @param @param ds
	* @param @return    入参
	* @return int    返回类型
	* @author （DZY） 
	* @throws
	* @date 2017年9月6日 下午4:05:13 
	* @version V1.0
	 */
	int deleteDevStatusByImeiAndMac(DeviceStatus ds);

	/**
	 * 定时上报记录
	* 
	* @Title: DeviceStatusService.java 
	* @Description: TODO 
	* @param @param devStaCon
	* @param @param user
	* @param @param pb
	* @param @return
	* @return PageBean
	* @author fl
	* @date 2018年2月5日 上午10:22:10
	* @version V1.0
	 */
	PageBean devTimeNewStatusList(DeviceStatusConditionBean devStaCon, Users user, PageBean pb);

	/**
	 * 导出定时上报记录
	* 
	* @Title: DeviceStatusService.java 
	* @Description: TODO 
	* @param @param devStaCon
	* @param @param user
	* @param @param pb
	* @param @return
	* @return PageBean
	* @author fl
	* @date 2018年2月6日 上午9:56:27
	* @version V1.0
	 */
	PageBean devTimExportNewStatus(DeviceStatusConditionBean devStaCon, Users user, PageBean pb);

	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 定时上报记录
	 * @param devId
	 * @return
	 */
	PageBean querydevTimeNewStatuByDevId(Long devId,PageBean pb);

}
