package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Tjumper;

public interface TjumperMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tjumper record);

    int insertSelective(Tjumper record);

    Tjumper selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tjumper record);

    int updateByPrimaryKey(Tjumper record);
    
    int deleteByUser(Long userId);
    
    int deleteByDev(String devCode);
    
    List<Tjumper> findRecords(Long userId,String value);
}