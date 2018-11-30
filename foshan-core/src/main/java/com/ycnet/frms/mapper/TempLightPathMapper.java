package com.ycnet.frms.mapper;

import java.util.HashMap;
import java.util.List;

import com.ycnet.frms.bean.TempLightPath;

public interface TempLightPathMapper {
	
    int deleteByPrimaryKey(Long orgId);

    int insert(TempLightPath record);

    int insertSelective(TempLightPath record);

    TempLightPath selectByPrimaryKey(Long pathId);

    int updateByPrimaryKeySelective(TempLightPath record);

    int updateByPrimaryKey(TempLightPath record);
    
    List<TempLightPath> selectByDevType(HashMap<String,Object> map);
    
    List<TempLightPath> selectMaxTime(String maxTime);
    
    int deleteByParam(String devType,Long orgId,String areaCode1);
    
    String selectMinTimeByParam(String devType,Long orgId,String areaCode1);
}