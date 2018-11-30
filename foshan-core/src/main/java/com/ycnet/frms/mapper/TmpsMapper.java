package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Tmps;

public interface TmpsMapper {
    int deleteByPrimaryKey(Long tmpId);

    int insert(Tmps record);

    int insertSelective(Tmps record);

    Tmps selectByPrimaryKey(Long tmpId);

    int updateByPrimaryKeySelective(Tmps record);

    int updateByPrimaryKey(Tmps record);
    
    List<Tmps> queryByConditionBeans(Map<String,Object> map);
    
    int queryCountByConditionMap(Map<String,Object> map);
}