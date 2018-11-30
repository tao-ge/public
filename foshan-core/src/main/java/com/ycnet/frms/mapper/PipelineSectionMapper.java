package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.PipelineSection;

public interface PipelineSectionMapper {
    int deleteByPrimaryKey(Long pipesId);

    int insert(PipelineSection record);

    int insertSelective(PipelineSection record);

    PipelineSection selectByPrimaryKey(Long pipesId);

    int updateByPrimaryKeySelective(PipelineSection record);

    int updateByPrimaryKey(PipelineSection record);
    
    /**
     * 根据两端信息查询管道
     * @param fdevId
     * @param bdevId
     * @return
     */
    PipelineSection selectBy2DevId(Long fdevId,Long bdevId,Long orgId);
    
    /**
     * 根据面ID查询管道段信息
     * @param devId
     * @param spaceId
     * @return
     */
    List<PipelineSection> selectBySpaceId(Long devId,Long spaceId);
}