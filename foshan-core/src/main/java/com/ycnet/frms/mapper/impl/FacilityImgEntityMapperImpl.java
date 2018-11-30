package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.FacilityImgEntity;
import com.ycnet.frms.mapper.FacilityImgEntityMapper;

@Repository("facilityImgEntityMapper")
public class FacilityImgEntityMapperImpl extends BaseSqlSupport implements FacilityImgEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long devImgId) {
		return this.delete("com.ycnet.frms.mapper.FacilityImgEntityMapper.deleteByPrimaryKey",devImgId);
	}

	@Override
	public int insert(FacilityImgEntity record) {
		return this.insert("com.ycnet.frms.mapper.FacilityImgEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(FacilityImgEntity record) {
		return this.insert("com.ycnet.frms.mapper.FacilityImgEntityMapper.insertSelective",record); 
	}

	@Override
	public FacilityImgEntity selectByPrimaryKey(Long devImgId) {
		return this.selectOne("com.ycnet.frms.mapper.FacilityImgEntityMapper.selectByPrimaryKey",devImgId);
	}

	@Override
	public int updateByPrimaryKeySelective(FacilityImgEntity record) {
		return this.update("com.ycnet.frms.mapper.FacilityImgEntityMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(FacilityImgEntity record) {
		return this.update("com.ycnet.frms.mapper.FacilityImgEntityMapper.updateByPrimaryKey",record);
	}

	/**
     * 根据设施ID,查询设施图片
    * @Title: selectByDevId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param devId
    * @param @return    入参
    * @return List<FacilityImgEntity>    返回类型
    * @author FL（作者） 
    * @throws
    * @date 2018年6月14日 上午10:02:49 
    * @version V1.0
     */
	@Override
	public List<FacilityImgEntity> selectByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.FacilityImgEntityMapper.selectByDevId",devId);
	}

}
  
    