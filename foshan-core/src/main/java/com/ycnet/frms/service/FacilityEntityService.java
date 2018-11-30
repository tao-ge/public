package com.ycnet.frms.service;

import java.util.List;
import java.util.Map;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.FacilityEntity;
import com.ycnet.frms.bean.FacilityImgEntity;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityVoBeanXY;
import com.ycnet.frms.vo.mobile.FacilityListOpd;
import com.ycnet.frms.vo.mobile.FaciltyDeviceDtoBean;



public interface FacilityEntityService {

	/**
	 * 
	 * @Title: saveFacsility
	 * @Description: 保存设施(添加/修改)
	 * @param @param fa
	 * @param @param list
	 * @param @return 
	 * @return Long 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月12日 下午3:38:48
	 * @version V1.0
	 */
	Long saveFacsility(FacilityVoBeanXY fa, List<FacilityImgEntity> images);

	/**
	 * 设施查询
	* @Title: queryFacilityByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return List<FacilityEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月12日 下午3:52:48 
	* @version V1.0
	 */
	List<FaciltyDeviceDtoBean> queryFacilityByConditions(FacilityConditionBean facility, PageBean pb, Long orgId);

	/**
	 * 根据设施ID,查询设施信息
	* @Title: queryFacilityByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return FacilityEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月12日 下午4:30:50 
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
	 * @Title: genFiberdisc
	 * @Description: 依据给定的设施ID，盘面，和熔纤盘数量自动生成设施熔纤盘
	 * @param @param devId：		所属设施id
	 * @param @param side：		A,B 面
	 * @param @param fiberDiscNum	熔纤盘数量
	 * @param @param portNum		单个熔纤盘端子数
	 * @param @param fromIndex
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午9:27:42
	 * @version V1.0
	 */
	int genFiberdisc(Long devId,String side,Integer fiberDiscNum ,Integer portNum,Integer fromIndex);
	
	int genFiberdisc(Long devId,String side,Integer fiberDiscNum ,Integer portNum,Integer fromRowIndex,Integer fromColIndex);
	
	int genFiberdisc(Long devId,String side,Integer fiberDiscNum ,Integer portNum,Integer fromRowIndex,Integer fromColIndex,String model);

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据ID查询
	 * @param @param devId
	 * @param @return 
	 * @return FacilityEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午10:41:39
	 * @version V1.0
	 */
	FacilityEntity selectByPrimaryKey(Long devId);

	/**
	 * 
	 * @Title: selectById
	 * @Description: 根据ID查询
	 * @param @param devId
	 * @param @return 
	 * @return FacilityEntity 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午10:49:23
	 * @version V1.0
	 */
	FacilityEntity selectById(Long devId);

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
	* @date 2018年6月20日 下午4:35:28 
	* @version V1.0
	 */
	List<FacilityListOpd> queryopdFacilityDeviceByConditions(FacilityConditionBean facility, PageBean pb,
			Long orgId);

	/**
	 * 根据设施ID,查询设施详情
	* @Title: queryopdFacilityDeviceByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return FaciltyDeviceDtoBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月20日 下午4:50:46 
	* @version V1.0
	 */
	FaciltyDeviceDtoBean queryopdFacilityDeviceByDevId(Long devId);

}
  
    