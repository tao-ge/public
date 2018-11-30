package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.OcPipingCable;

public interface OcPipingCableMapper {
    int deleteByPrimaryKey(Long subtubeRefSectId);

    int insert(OcPipingCable record);

    int insertSelective(OcPipingCable record);

    OcPipingCable selectByPrimaryKey(Long subtubeRefSectId);

    int updateByPrimaryKeySelective(OcPipingCable record);

    int updateByPrimaryKey(OcPipingCable record);

	/** 
	 * 查询光缆段与管道段大孔关系表
	* @Title: selectBySectId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return List<OcPipingCable>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午1:07:00 
	* @version V1.0   
	*/
	List<OcPipingCable> selectBySectId(Long sectId);
}