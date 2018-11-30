package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.TempLightPath;
import com.ycnet.frms.mapper.TempLightPathMapper;

@Repository("tempLightPathMapper")
public class TempLightPathMapperImpl extends BaseSqlSupport implements TempLightPathMapper {

	@Override
	public int deleteByPrimaryKey(Long orgId) {
		return this.delete("com.ycnet.frms.mapper.TempLightPathMapper.deleteByPrimaryKey",orgId);
	}

	@Override
	public int insert(TempLightPath record) {
		return this.insert("com.ycnet.frms.mapper.TempLightPathMapper.insert",record);
	}

	@Override
	public int insertSelective(TempLightPath record) {
		return this.insert("com.ycnet.frms.mapper.TempLightPathMapper.insertSelective",record);
	}

	@Override
	public TempLightPath selectByPrimaryKey(Long pathId) {
		return this.selectOne("com.ycnet.frms.mapper.TempLightPathMapper.selectByPrimaryKey",pathId);
	}

	@Override
	public int updateByPrimaryKeySelective(TempLightPath record) {
		return this.update("com.ycnet.frms.mapper.TempLightPathMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(TempLightPath record) {
		return this.update("com.ycnet.frms.mapper.TempLightPathMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<TempLightPath> selectByDevType(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.TempLightPathMapper.selectByDevType",map);
	}

	@Override
	public List<TempLightPath> selectMaxTime(String maxTime) {
		// TODO Auto-generated method stub
		maxTime = this.selectOne("com.ycnet.frms.mapper.TempLightPathMapper.selectMaxTime");
		System.out.println("------------------------------------"+maxTime);
		if(maxTime == null) {
			return null;
		}else {
			return this.selectList("com.ycnet.frms.mapper.TempLightPathMapper.selectByMaxTime",maxTime);
		}
	}

	@Override
	public int deleteByParam(String devType,Long orgId,String areaCode1) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("devType", devType);
		map.put("orgId", orgId);
		map.put("areaCode1", areaCode1);
		return this.delete("com.ycnet.frms.mapper.TempLightPathMapper.deleteByParam",map);
	}

	@Override
	public String selectMinTimeByParam(String devType,Long orgId,String areaCode1) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("devType", devType);
		map.put("orgId", orgId);
		map.put("areaCode1", areaCode1);
		return this.selectOne("com.ycnet.frms.mapper.TempLightPathMapper.selectMinTimeByParam",map);
	}
}
