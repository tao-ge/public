package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WellPileline;
import com.ycnet.frms.mapper.WellPilelineMapper;

@Repository("wellPilelineMapper")
public class WellPilelineMapperImpl extends  BaseSqlSupport 
		implements WellPilelineMapper{
	
		@Override
		public int insert(WellPileline record)
		{
			return this.insert("com.ycnet.frms.mapper.WellPilelineMapper.insert",record);
		}
	
		@Override
		public WellPileline selectByPrimaryKey(Long id)
		{
			return this.selectOne("com.ycnet.frms.mapper.WellPilelineMapper.selectByPrimaryKey",id);
		}
	
		@Override
		public int deleteByPrimaryKey(Long id)
		{
			return this.delete("com.ycnet.frms.mapper.WellPilelineMapper.deleteByPrimaryKey",id);
		}
	
		@Override
		public int insertSelective(WellPileline record)
		{
			return this.insert("com.ycnet.frms.mapper.WellPilelineMapper.insertSelective",record);
		}
	
		@Override
		public int updateByPrimaryKeySelective(WellPileline record)
		{
			return this.update("com.ycnet.frms.mapper.WellPilelineMapper.updateByPrimaryKeySelective",record);
		}
	
		@Override
		public int updateByPrimaryKey(WellPileline record)
		{
			return this.update("com.ycnet.frms.mapper.WellPilelineMapper.updateByPrimaryKey",record);
		}

		@Override
		public int selectCount(Long devId,Long sectId) {
			// TODO Auto-generated method stub
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("devId", devId);
			map.put("sectId", sectId);
			return this.selectOne("com.ycnet.frms.mapper.WellPilelineMapper.selectCount",map);
		}

		@Override
		public List<WellPileline> selectByDevId(Long devId) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.WellPilelineMapper.selectByDevId",devId);
		}
		
		@Override
		public int deleteByDevId(Long devId) {
			return this.delete("com.ycnet.frms.mapper.WellPilelineMapper.deleteByDevId",devId);
		}

		@Override
		public List<WellPileline> selectByFDevId(Long fdevId) {
			// TODO Auto-generated method stub
			return this.selectList("com.ycnet.frms.mapper.WellPilelineMapper.selectByFDevId",fdevId);
		}

		@Override
		public List<WellPileline> selectByDevIdOr(Long devId) {
			return this.selectList("com.ycnet.frms.mapper.WellPilelineMapper.selectByDevIdOr",devId);
		}

		@Override
		public int deleteByDevIdOr(Long devId) {
			return this.delete("com.ycnet.frms.mapper.WellPilelineMapper.deleteByDevIdOr", devId);
		}

		//导出数据库  刘沧海 2017/10/13
		@Override
		public List<WellPileline> queryList() {
			return this.selectList("com.ycnet.frms.mapper.WellPilelineMapper.queryList");
		}
}