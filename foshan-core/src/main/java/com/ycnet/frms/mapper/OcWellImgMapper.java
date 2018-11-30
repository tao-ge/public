package com.ycnet.frms.mapper;

import java.util.Map;

import com.ycnet.frms.bean.OcWellImg;

public interface OcWellImgMapper {
    int deleteByPrimaryKey(Long wellImgId);

    int insert(OcWellImg record);

    int insertSelective(OcWellImg record);

    OcWellImg selectByPrimaryKey(Long wellImgId);

    int updateByPrimaryKeySelective(OcWellImg record);

    int updateByPrimaryKey(OcWellImg record);

	/** 
	* @Title: deleteByDevIdAndUrl 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午2:37:17 
	* @version V1.0   
	*/
	int deleteByDevIdAndUrl(Map<String, Object> map);
}