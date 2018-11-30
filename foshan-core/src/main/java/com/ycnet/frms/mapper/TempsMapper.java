package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Temps;

public interface TempsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Temps record);

    int insertSelective(Temps record);

    Temps selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Temps record);

    int updateByPrimaryKey(Temps record);
    
    List<Temps> selectByParam(Long id);
}