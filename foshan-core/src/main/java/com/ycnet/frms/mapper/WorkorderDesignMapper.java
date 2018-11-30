package com.ycnet.frms.mapper;

import com.ycnet.frms.bean.WorkorderDesign;

public interface WorkorderDesignMapper {
	int deleteByPrimaryKey(Long designId);

    int insert(WorkorderDesign record);

    int insertSelective(WorkorderDesign record);

    WorkorderDesign selectByPrimaryKey(Long designId);

    int updateByPrimaryKeySelective(WorkorderDesign record);

    int updateByPrimaryKey(WorkorderDesign record);
}
