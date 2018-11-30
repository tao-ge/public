package com.ycnet.frms.mapper.pipeline;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ycnet.frms.bean.PipelineSection;
import com.ycnet.frms.bean.pipeline.Pipeline;
import com.ycnet.frms.bean.pipeline.PipelineExample;

public interface PipelineMapper {
    int countByExample(PipelineExample example);

    int deleteByExample(PipelineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pipeline record);

    int insertSelective(Pipeline record);

    List<Pipeline> selectByExample(PipelineExample example);

    Pipeline selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pipeline record, @Param("example") PipelineExample example);

    int updateByExample(@Param("record") Pipeline record, @Param("example") PipelineExample example);

    int updateByPrimaryKeySelective(Pipeline record);

    int updateByPrimaryKey(Pipeline record);
    
	/**
	 * 判断管道名称是否唯一
	 * @param pipelineName
	 * @return
	 */
	int findPipelineName(String pipelineName);

	/**
	 * 查找前后点位不为null的数据
	 * @return
	 */
	List<Pipeline> findAllNotNull();
	/**
	 * 
	* @Title: queryPipelineSectionByDevId 
	* @Description: 根据井Id查询管道段信息
	* @param @param devId
	* @param @return    入参
	* @return List<PipelineSection>    返回类型
	* @author 周宇 
	* @throws
	* @date 2017年11月30日 上午9:27:14 
	* @version V1.0
	 */
	List<PipelineSection> queryPipelineSectionByDevId(Long devId);

}