package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.OcPipingSectWell;

public interface OcPipingSectWellMapper {
    int deleteByPrimaryKey(Long pipingWellId);

    int insert(OcPipingSectWell record);

    int insertSelective(OcPipingSectWell record);

    OcPipingSectWell selectByPrimaryKey(Long pipingWellId);

    int updateByPrimaryKeySelective(OcPipingSectWell record);

    int updateByPrimaryKey(OcPipingSectWell record);
}