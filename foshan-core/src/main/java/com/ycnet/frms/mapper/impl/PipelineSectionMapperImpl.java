package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.PipelineSection;
import com.ycnet.frms.mapper.PipelineSectionMapper;

@Repository("pipelineSectionMapper")
public class PipelineSectionMapperImpl extends  BaseSqlSupport 
		implements PipelineSectionMapper{
	
		@Override
		public int insert(PipelineSection record)
		{
			return this.insert("com.ycnet.frms.mapper.PipelineSectionMapper.insert",record);
		}
	
		@Override
		public PipelineSection selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.PipelineSectionMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.PipelineSectionMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(PipelineSection record)
		{
			return this.insert("com.ycnet.frms.mapper.PipelineSectionMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(PipelineSection record)
		{
			return this.update("com.ycnet.frms.mapper.PipelineSectionMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(PipelineSection record)
		{
			return this.update("com.ycnet.frms.mapper.PipelineSectionMapper.updateByPrimaryKey",record);
		}

		@Override
		public PipelineSection selectBy2DevId(Long fdevId, Long bdevId,Long orgId) {
			// TODO Auto-generated method stub
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("fdevId", fdevId);
			map.put("bdevId", bdevId);
			map.put("orgId", orgId);
			return this.selectOne("com.ycnet.frms.mapper.PipelineSectionMapper.selectBy2DevId",map);
		}

		@Override
		public List<PipelineSection> selectBySpaceId(Long devId, Long spaceId) {
			// TODO Auto-generated method stub
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("spaceId", spaceId);
			return this.selectList("com.ycnet.frms.mapper.PipelineSectionMapper.selectBySpaceId",map);
		}
	
}