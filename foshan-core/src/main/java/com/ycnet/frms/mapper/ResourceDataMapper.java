package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.ResourceData;

public interface ResourceDataMapper {
    int deleteByPrimaryKey(Long resId);

    int insert(ResourceData record);

    int insertSelective(ResourceData record);

    ResourceData selectByPrimaryKey(Long resId);

    int updateByPrimaryKeySelective(ResourceData record);

    int updateByPrimaryKey(ResourceData record);

    //查询资管数据管理数据
	List<ResourceData> queryResourceDataList(Map<String, Object> map);

	//查询资管数据管理数据数量
	Integer queryCountResourceDataList(Map<String, Object> map);

	//根据areaCode查询数据
	List<ResourceData> queryByAreaCode(String areaCode);
}