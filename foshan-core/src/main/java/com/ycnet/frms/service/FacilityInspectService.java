package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.ResultData;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityInspect;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.FacilityInspectConditionBean;

public interface FacilityInspectService {
	
	
	/**
	 * 上报巡检信息
	 * @param facilityInspect
	 * @return
	 */
	ResultData save(FacilityInspect facilityInspect);
	
	/**
	 * 我的巡检记录
	 * @author YHT
	 * @time   2016年7月7日 下午5:54:28
	 * @param bean
	 * @return
	 */
	ResultData queryByConditionBean(FacilityInspectConditionBean bean,String page,String pagesize);
	
	/**
	 * 查询巡检详细信息
	 * @author YHT
	 * @time   2016年7月8日 上午9:52:13
	 * @param inspectedId
	 * @return
	 */
	ResultData selectById(String inspectedId);
	
	FacilityInspect selectByIds(Long inspectedId);
	
	/**
	 * web端查询巡检日志
	 * @author YHT
	 * @time   2016年7月28日 下午7:43:08
	 * @param bean
	 * @return
	 */
	PageBean queryByConditionBeans(FacilityInspectConditionBean bean,PageBean pb,Users user);

	/**
	 * 
	* @Title: queryFacilityInspectByDevId 
	* @Description: 根据设施ID查询巡检历史任务 
	* @param @param devId
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年2月9日 下午2:07:35 
	* @version V1.0
	 */
	List<FacilityInspect> queryFacilityInspectByDevId(Facility facility);
	
}
