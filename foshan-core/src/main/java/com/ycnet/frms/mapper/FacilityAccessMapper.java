package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.vo.AccessConditionBean;
import com.ycnet.frms.vo.FacilityAccessBean;

public interface FacilityAccessMapper {
    int deleteByPrimaryKey(Long accessId);

    int insert(FacilityAccess record);

    int insertSelective(FacilityAccess record);

    FacilityAccess selectByPrimaryKey(Long accessId);

    int updateByPrimaryKeySelective(FacilityAccess record);

    int updateByPrimaryKey(FacilityAccess record);
    
    List<FacilityAccess> queryListByMap(Map<String,Object> map);
    int queryCountByMap(Map<String,Object> map);
	List<FacilityAccessBean> queryListByCondition(Map<String, Object> map);
	int queryCountByCondition(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryAccessCountByMac
	 * @Description: 根据条件查询是否设施是否授权
	 * @param @param map
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月2日 上午10:41:00
	 * @version V1.0
	 */
	int queryAccessCountByMac(Map<String, Object> map);

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
	 * @date 2018年7月24日 下午3:27:14
	 * @version V1.0
	 */
	FacilityAccess queryWhetherAccess(String devId);

	/**
	 * 根据imei,查询授权时间
	* @Title: queryFacilityAccessByImei 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param imei
	* @param @return    入参
	* @return List<FacilityAccess>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月28日 下午6:09:03 
	* @version V1.0
	 */
	FacilityAccess queryFacilityAccessByImei(String imei);
}