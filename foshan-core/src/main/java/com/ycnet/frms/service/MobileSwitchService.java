package com.ycnet.frms.service;

import java.util.Date;
import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Users;

public interface MobileSwitchService {
	int save(MobileSwitch ms);
	List<MobileSwitch> queryList(MobileSwitch ms,PageBean pb);
	PageBean queryByConditionBean(MobileSwitch ms, PageBean pb,Users user);
	PageBean queryNewRecordListByMap(MobileSwitch ms, Users user, PageBean pb);
	
	/**
	 * 
	 * @Title: queryById
	 * @Description: 根据ID查询开关锁记录
	 * @param @param switchId
	 * @param @return 
	 * @return MobileSwitch 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月28日 下午2:51:10
	 * @version V1.0
	 */
	MobileSwitch queryById(Long switchId);
	/**
	 * 导出开关锁记录
	* 
	* @Title: MobileSwitchService.java 
	* @Description: TODO 
	* @param @param ms
	* @param @param user
	* @param @return
	* @return List<MobileSwitch>
	* @author fl
	* @date 2018年2月6日 上午9:38:40
	* @version V1.0
	 */
	List<MobileSwitch> queryexport(MobileSwitch ms, Users user);
	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 手机开锁记录
	 * @param request
	 * @param devId
	 * @param pb 
	 * @param user 
	 * @return
	 */
	PageBean querySwitchRecordByDevId(Long devId, PageBean pb, Users user);
	/**
	 * 
	* @Title: queryMobileSwitchByUserId 
	* @Description: 当前用户的开关锁记录 
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年2月9日 下午2:31:42 
	* @version V1.0
	 * @param facility 
	 */
	List<MobileSwitch> queryMobileSwitchByUserId(Facility facility, Long userId);
	
	/**
	 * 
	 * @Title: queryRemoteUnlock
	 * @Description: 查询远程开锁
	 * @param @param user
	 * @param @param unlockTime
	 * @param @param devImei
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月26日 下午2:21:34
	 * @version V1.0
	 */
	int queryRemoteUnlock(String config,Users user, long unlockTime, String devImei);
}
