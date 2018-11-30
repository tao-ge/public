package com.ycnet.frms.mapper.impl.pipeline;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.PipelineSection;
import com.ycnet.frms.bean.pipeline.Pipeline;
import com.ycnet.frms.bean.pipeline.PipelineExample;
import com.ycnet.frms.mapper.pipeline.PipelineMapper;
import com.ycnet.frms.mapper.point.PointMapper;

	@Repository("PipelineMapper")
	public class PipelineMapperImpl  extends  BaseSqlSupport implements PipelineMapper{

		@Override
		public int countByExample(PipelineExample example) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int deleteByExample(PipelineExample example) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int deleteByPrimaryKey(Integer id) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int insert(Pipeline record) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int insertSelective(Pipeline record) {
			return this.insert("com.ycnet.frms.mapper.pipeline.PipelineMapper.insertSelective",record);
		}

		@Override
		public List<Pipeline> selectByExample(PipelineExample example) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Pipeline selectByPrimaryKey(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int updateByExampleSelective(Pipeline record,
				PipelineExample example) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int updateByExample(Pipeline record, PipelineExample example) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int updateByPrimaryKeySelective(Pipeline record) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int updateByPrimaryKey(Pipeline record) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int findPipelineName(String pipelineName) {
			return this.selectOne("com.ycnet.frms.mapper.pipeline.PipelineMapper.findPipelineName",pipelineName);
		}

		@Override
		public List<Pipeline> findAllNotNull() {
			return this.selectList("com.ycnet.frms.mapper.pipeline.PipelineMapper.findAllNotNull");
		}
		//通过devid查询管道段信息 author：周宇
		@Override
		public List<PipelineSection> queryPipelineSectionByDevId(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.PipelineSectionMapper.queryPipelineByDevId",devId);
		}


	}
