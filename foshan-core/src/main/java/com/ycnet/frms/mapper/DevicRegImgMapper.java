package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.DevicRegImg;

public interface DevicRegImgMapper {
    int deleteByPrimaryKey(Long regImgId);

    int insert(DevicRegImg record);

    int insertSelective(DevicRegImg record);

    DevicRegImg selectByPrimaryKey(Long regImgId);

    int updateByPrimaryKeySelective(DevicRegImg record);

    int updateByPrimaryKey(DevicRegImg record);
}