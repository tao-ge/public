package com.ycnet.frms.mapper.impl;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.DeviceParamEntity;
import com.ycnet.frms.mapper.DeviceParamEntityMapper;
import com.ycnet.frms.vo.mobile.DeviceParamMobile;

@Repository("deviceParamEntityMapper")
public class DeviceParamEntityMapperImpl extends BaseSqlSupport implements DeviceParamEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long paramId) {
		return this.delete("com.ycnet.frms.mapper.DeviceParamEntityMapper.deleteByPrimaryKey",paramId);
	}

	@Override
	public int insert(DeviceParamEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceParamEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(DeviceParamEntity record) {
		return this.insert("com.ycnet.frms.mapper.DeviceParamEntityMapper.insertSelective",record); 
	}

	@Override
	public DeviceParamEntity selectByPrimaryKey(Long paramId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceParamEntityMapper.selectByPrimaryKey",paramId);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceParamEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceParamEntityMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(DeviceParamEntity record) {
		return this.update("com.ycnet.frms.mapper.DeviceParamEntityMapper.updateByPrimaryKey",record);
	}

	@Override
	public DeviceParamMobile querydeviceParamByOrgId(Long orgId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceParamEntityMapper.querydeviceParamByOrgId",orgId);
	}

	/**
	 * 根据 orgId,查询中控器参数
	* @Title: queryDeviceParamInfoByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return DeviceParamEntity    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月29日 上午10:55:32 
	* @version V1.0
	 */
	@Override
	public DeviceParamEntity queryDeviceParamInfoByOrgId(Long orgId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceParamEntityMapper.queryDeviceParamInfoByOrgId",orgId);
	}

}
  
    