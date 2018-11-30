package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.OcPiping;
import com.ycnet.frms.vo.mobile.ocii.OcPipingResult;

public interface OcPipingMapper {
    int deleteByPrimaryKey(Long pipingId);

    int insert(OcPiping record);

    int insertSelective(OcPiping record);

    OcPiping selectByPrimaryKey(Long pipingId);

    int updateByPrimaryKeySelective(OcPiping record);

    int updateByPrimaryKey(OcPiping record);

	/** 
	 * 查询光交箱下的管道段
	* @Title: queryOciiPipingSections 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param orgId
	* @param @return    入参
	* @return List<Map<String,Object>>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午4:42:02 
	* @version V1.0   
	*/
	List<Map<String, Object>> queryOciiPipingSections(Long devId, Long orgId);

	/** 
	 * 查询大孔信息
	* @Title: queryOcPipingById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingId
	* @param @return    入参
	* @return Map<String,Object>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月12日 下午2:22:57 
	* @version V1.0   
	*/
	Map<String, Object> queryOcPipingById(Long pipingId);
	
	
	/**
	 * 根据管道段ID删除管道段
	* @Title: queryOcPipingByPipingSectId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingId
	* @param @return    入参
	* @return List<OcPiping>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月19日 下午1:45:34 
	* @version V1.0
	 */
	List<OcPipingResult> queryOcPipingByPipingSectId(Long pipingSectId);
}