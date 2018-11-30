package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.Subtube;

public interface SubtubeMapper {
    int deleteByPrimaryKey(Long subtubeId);

    int insert(Subtube record);

    int insertSelective(Subtube record);

    Subtube selectByPrimaryKey(Long subtubeId);

    int updateByPrimaryKeySelective(Subtube record);

    int updateByPrimaryKey(Subtube record);
}