package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.AccessConditionBean;
import com.ycnet.frms.vo.FacilityAccessBean;

public interface FacilityAccessService {
	FacilityAccess selectById(Long id);
	int save(FacilityAccess access);
	int delete(Long id);
	List<FacilityAccess> queryList(FacilityAccess fa,PageBean pb,Users user);
	int queryCountByList(FacilityAccess fa,Users user);
	List<FacilityAccessBean> queryListByCondition(AccessConditionBean cb,PageBean pb,Users user);
	int queryCountByCondition(AccessConditionBean cb,Users user);
	/**
	 * 
	* @Title: queryListByConditionExport 
	* @Description: 导出查询 
	* @param @param cb
	* @param @param pb
	* @param @param user
	* @param @return    
	* @return List<FacilityAccessBean>
	* @author liucanghai 
	* @throws
	* @date 2018年2月22日 上午10:01:32 
	* @version V1.0
	 */
	List<FacilityAccessBean> queryListByConditionExport(AccessConditionBean cb, PageBean pb, Users user);
	
	/**
	 * 
	 * @Title: queryAccessCountByMac
	 * @Description: 根据条件查询是否设施是否授权
	 * @param @param accessConditionBean
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月2日 上午10:30:44
	 * @version V1.0
	 */
	int queryAccessCountByMac(AccessConditionBean ac, Users user);
	
	/**
	 * 
	 * @Title: queryWhetherAccess
	 * @Description: 查询设施是否授权
	 * @param @param devId
	 * @param @return 
	 * @return FacilityAccess 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月24日 下午3:19:46
	 * @version V1.0
	 */
	FacilityAccess queryWhetherAccess(String devId);
	/**
	 * 根据imei,查询授权时间
	* @Title: queryFacilityAccessByImei 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param imei    入参
	* @return void    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月28日 下午6:06:57 
	* @version V1.0
	 */
	FacilityAccess queryFacilityAccessByImei(String imei);
}
