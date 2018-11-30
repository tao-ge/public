package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.InspectWork;
import com.ycnet.frms.mapper.InspectWorkMapper;
import com.ycnet.frms.vo.AccessConditionBean;
import com.ycnet.frms.vo.InspectWorkDev;

@Repository("inspectWorkMapper")
public class InspectWorkMapperImpl extends  BaseSqlSupport 
		implements InspectWorkMapper{
	
		@Override
		public int insert(InspectWork record)
		{
			return this.insert("com.ycnet.frms.mapper.InspectWorkMapper.insert",record);
		}
	
		@Override
		public InspectWork selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.InspectWorkMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.InspectWorkMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(InspectWork record)
		{
			return this.insert("com.ycnet.frms.mapper.InspectWorkMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(InspectWork record)
		{
			return this.update("com.ycnet.frms.mapper.InspectWorkMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(InspectWork record)
		{
			return this.update("com.ycnet.frms.mapper.InspectWorkMapper.updateByPrimaryKey",record);
		}

		@Override
		public List<InspectWork> queryListByMap(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.InspectWorkMapper.queryListByMap",map);
		}
		
		@Override
		public List<InspectWork> queryListByMapByWeb(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.InspectWorkMapper.queryListByMapByWeb",map);
		}

		@Override
		public int queryCountByMap(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.InspectWorkMapper.queryCountByMap",map);
		}
		
		@Override
		public int queryCountByMapByWeb(Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.InspectWorkMapper.queryCountByMapByWeb",map);
		}
		
		@Override
		public List<InspectWork> queryListByCondition(Map<String, Object> map) {
			return this.selectList("com.ycnet.frms.mapper.InspectWorkMapper.queryListByCondition",map);
		}

		@Override
		public int queryCountByCondition(
				Map<String, Object> map) {
			return this.selectOne("com.ycnet.frms.mapper.InspectWorkMapper.queryCountByCondition",map);
		}

		@Override
		public InspectWorkDev selectFaclityByDevId(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return this.selectOne("com.ycnet.frms.mapper.InspectWorkMapper.selectFaclityByDevId",map);
		}

//		@Override
//		public int updateByPrimaryKeyWithBLOBs(InspectWork record) {
//			// TODO Auto-generated method stub
//			return 0;
//		}
}