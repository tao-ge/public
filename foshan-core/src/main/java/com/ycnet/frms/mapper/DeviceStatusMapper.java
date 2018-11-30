package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.DeviceStatus;
import com.ycnet.frms.vo.DeviceStatusConditionBean;

public interface DeviceStatusMapper {
    int deleteByPrimaryKey(Long devStatusId);

    int insert(DeviceStatus record);

    int insertSelective(DeviceStatus record);

    DeviceStatus selectByPrimaryKey(Long devStatusId);

    int updateByPrimaryKeySelective(DeviceStatus record);

    int updateByPrimaryKey(DeviceStatus record);
    
    List<DeviceStatus> queryByConditionBeans(DeviceStatusConditionBean bean);
    
    List<DeviceStatus> queryByConditionBean(DeviceStatusConditionBean bean);
    
    
    List<DeviceStatus> queryByConditionMap(Map<String,Object> map);
    
    int queryCountByConditionMap(Map<String,Object> map);

	int deleteDevStatusByImeiAndMac(DeviceStatus ds);

	Integer queryCountdevTimeNewStatusList(Map<String, Object> map);

	List<DeviceStatus> querydevTimeNewStatusList(Map<String, Object> map);

	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 定时上报记录
	 * @param devId
	 * @param pb 
	 * @return
	 */
	List<DeviceStatus> querydevTimeNewStatuByDevId(Long devId, PageBean pb);

	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 定时上报记录总条数
	 * @param devId
	 * @param pb 
	 * @return
	 */
	Integer queryCountdevTimeNewStatuByDevId(Long devId, PageBean pb);

	/**
	 * 修改状态表中的所有当前状态改为历史状态
	* @Title: updateByImei 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devImei
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月30日 下午3:09:52 
	* @version V1.0
	 */
	int updateByImei(String devImei);
}