package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.FacilityEntity;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityEntityVoBean;
import com.ycnet.frms.vo.mobile.FacilityEntityMobile;
import com.ycnet.frms.vo.mobile.FacilityListOpd;
import com.ycnet.frms.vo.mobile.FaciltyDeviceDtoBean;


public interface FacilityEntityMapper {
    int deleteByPrimaryKey(Long devId);

    int insert(FacilityEntity record);

    int insertSelective(FacilityEntity record);

    FacilityEntity selectByPrimaryKey(Long devId);

    int updateByPrimaryKeySelective(FacilityEntity record);

    int updateByPrimaryKey(FacilityEntity record);

    /**
     * 设施查询
    * @Title: queryFacilityByConditions 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param map
    * @param @return    入参
    * @return List<FacilityEntity>    返回类型
    * @author FL（作者） 
    * @throws
    * @date 2018年4月12日 下午3:55:42 
    * @version V1.0
     */
	List<FaciltyDeviceDtoBean> queryFacilityByConditions(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryRepetitionDevName
	 * @Description: 验证设施名称是否存在
	 * @param @param facility
	 * @param @return 
	 * @return FacilityEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月12日 下午5:18:29
	 * @version V1.0
	 */
	FacilityEntity queryRepetitionDevName(FacilityEntity facility);

	/**
	 * 根据设施ID,查询设施信息
	* @Title: queryFacilityByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return FacilityEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月12日 下午4:32:17 
	* @version V1.0
	 */
	FaciltyDeviceDtoBean queryFacilityByDevId(Long devId);
	
	/**
	* 根据设施ID,查询设施信息
	* @Title: queryFacilityByDevId 
	* @param @param devId
	* @param @return    入参
	* @return FacilityEntity    返回类型
	* @throws
	* @version V1.0
	 */
	Map<String, Object> findFacilityByDevId(Long devId);

	/**
	 * 
	* @Title: queryPdevIdList 
	* @Description: 根据机柜ID查询机房下的ID 
	* @param @param devId
	* @param @return    
	* @return List<FacilityEntity>
	* @author liucanghai 
	* @throws
	* @date 2018年4月12日 下午5:43:24 
	* @version V1.0
	 */
	List<FacilityEntityMobile> queryPdevIdList(Long devId);

	/**
	 * 根据条件查询设施列表
	* @Title: queryopdFacilityDeviceByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return List<FaciltyDeviceDtoBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月20日 下午4:36:49 
	* @version V1.0
	 */
	List<FacilityListOpd> queryopdFacilityDeviceByConditions(Map<String,Object> map);
}