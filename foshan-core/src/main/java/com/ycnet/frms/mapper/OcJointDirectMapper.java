package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.OcJointDirect;

public interface OcJointDirectMapper {
    int deleteByPrimaryKey(Long jointId);

    int insert(OcJointDirect record);

    int insertSelective(OcJointDirect record);

    OcJointDirect selectByPrimaryKey(Long jointId);

    int updateByPrimaryKeySelective(OcJointDirect record);

    int updateByPrimaryKey(OcJointDirect record);

	/** 
	 * 查询是否有直熔数据
	* @Title: queryByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<OcJointDirect>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月17日 上午9:12:06 
	* @version V1.0   
	*/
	List<OcJointDirect> queryByDevId(Long devId);

	/** 
	 * 查询所选纤芯是否存在直熔的数据
	* @Title: queryOcJointDirectByCondition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectIdA
	* @param @return    入参
	* @return List<OcJointDirect>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月17日 上午11:02:20 
	* @version V1.0   
	 * @param devId 
	 * @param jointId 
	 * @param integer2 
	 * @param integer 
	*/
	Integer queryOcJointDirectByCondition(Long sectId, Integer start, Integer end, Long devId, Long jointId);
}