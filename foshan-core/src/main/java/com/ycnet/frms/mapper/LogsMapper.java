package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Logs;

public interface LogsMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(Logs record);

    int insertSelective(Logs record);

    Logs selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(Logs record);

    int updateByPrimaryKey(Logs record);
    
    List<Logs> queryByConditionBeans(Map<String,Object> map);
    
    int queryCountByConditionMap(Map<String,Object> map);
}