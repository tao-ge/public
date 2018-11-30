package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.InspectWork;
import com.ycnet.frms.vo.InspectWorkDev;

public interface InspectWorkMapper {
    int deleteByPrimaryKey(Long workId);

    int insert(InspectWork record);

    int insertSelective(InspectWork record);

    InspectWork selectByPrimaryKey(Long workId);

    int updateByPrimaryKeySelective(InspectWork record);

    //int updateByPrimaryKeyWithBLOBs(InspectWork record);

    int updateByPrimaryKey(InspectWork record);
    
    List<InspectWork> queryListByMap(Map<String,Object> map);
    
    List<InspectWork> queryListByMapByWeb(Map<String,Object> map);
    
    int queryCountByMap(Map<String,Object> map);
    
    int queryCountByMapByWeb(Map<String, Object> map);
    
	List<InspectWork> queryListByCondition(Map<String, Object> map);
	
	int queryCountByCondition(Map<String, Object> map);
	
	InspectWorkDev selectFaclityByDevId(Map<String, Object> map);
}