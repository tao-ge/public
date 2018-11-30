package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.FacilityEntity;
import com.ycnet.frms.mapper.FacilityEntityMapper;
import com.ycnet.frms.vo.mobile.FacilityEntityMobile;
import com.ycnet.frms.vo.mobile.FacilityListOpd;
import com.ycnet.frms.vo.mobile.FaciltyDeviceDtoBean;

@Repository("facilityEntityMapper")
public class FacilityEntityMapperImpl extends BaseSqlSupport implements FacilityEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long devId) {
		return this.delete("com.ycnet.frms.mapper.FacilityEntityMapper.deleteByPrimaryKey",devId);
	}

	@Override
	public int insert(FacilityEntity record) {
		return this.insert("com.ycnet.frms.mapper.FacilityEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(FacilityEntity record) {
		return this.insert("com.ycnet.frms.mapper.FacilityEntityMapper.insertSelective",record); 
	}

	@Override
	public FacilityEntity selectByPrimaryKey(Long devId) {
		return this.selectOne("com.ycnet.frms.mapper.FacilityEntityMapper.selectByPrimaryKey",devId);
	}

	@Override
	public int updateByPrimaryKeySelective(FacilityEntity record) {
		return this.update("com.ycnet.frms.mapper.FacilityEntityMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(FacilityEntity record) {
		return this.update("com.ycnet.frms.mapper.FacilityEntityMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<FaciltyDeviceDtoBean> queryFacilityByConditions(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.FacilityEntityMapper.queryFacilityByConditions",map);
	}

	@Override
	public FacilityEntity queryRepetitionDevName(FacilityEntity facility) {
		return this.selectOne("com.ycnet.frms.mapper.FacilityEntityMapper.queryRepetitionDevName",facility);
	}

	@Override
	public FaciltyDeviceDtoBean queryFacilityByDevId(Long devId) {
		return this.selectOne("com.ycnet.frms.mapper.FacilityEntityMapper.queryFacilityByDevId",devId);
	}
	
	/**
	* 根据设施ID,查询设施信息
	* @Title: queryFacilityByDevId 
	* @param @param devId
	* @param @return    入参
	* @return FacilityEntity    返回类型
	* @throws
	* @version V1.0
	 */
	@Override
	public Map<String, Object> findFacilityByDevId(Long devId) {
		return this.selectOne("com.ycnet.frms.mapper.FacilityEntityMapper.findFacilityByDevId",devId);
	}

	@Override
	public List<FacilityEntityMobile> queryPdevIdList(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.FacilityEntityMapper.queryPdevIdList",devId);
	}

	/**
	 * 根据条件查询设施列表
	* @Title: queryopdFacilityDeviceByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return List<FaciltyDeviceDtoBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月20日 下午4:36:49 
	* @version V1.0
	 */
	@Override
	public List<FacilityListOpd> queryopdFacilityDeviceByConditions(Map<String,Object> map) {
		return this.selectList("com.ycnet.frms.mapper.FacilityEntityMapper.queryopdFacilityDeviceByConditions",map);
	}
}
  
    