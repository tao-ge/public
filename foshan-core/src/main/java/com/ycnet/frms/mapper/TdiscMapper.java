package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Tdisc;

public interface TdiscMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tdisc record);

    int insertSelective(Tdisc record);

    Tdisc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tdisc record);

    int updateByPrimaryKey(Tdisc record);
    
    int deleteByUser(Long userId);
    
    int deleteByDev(String devCode);
    
    List<Tdisc> findRecords(Long userId,String value);
    
    int genPortCode(Long userId,Long orgId);
}