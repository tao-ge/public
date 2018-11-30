package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.DeviceImage;
import com.ycnet.frms.mapper.DeviceImageMapper;

@Repository("deviceImageMapper")
public class DeviceImageMapperImpl extends BaseSqlSupport implements DeviceImageMapper{

	@Override
	public int deleteByPrimaryKey(Long imageId) {
		return this.delete("com.ycnet.frms.mapper.DeviceImageMapper.deleteByPrimaryKey",imageId);
	}

	@Override
	public int insert(DeviceImage record) {
		return this.insert("com.ycnet.frms.mapper.DeviceImageMapper.insert",record);
	}

	@Override
	public int insertSelective(DeviceImage record) {
		return this.insert("com.ycnet.frms.mapper.DeviceImageMapper.insertSelective",record);
	}

	@Override
	public DeviceImage selectByPrimaryKey(Long imageId) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceImageMapper.selectByPrimaryKey",imageId);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceImage record) {
		return this.update("com.ycnet.frms.mapper.DeviceImageMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(DeviceImage record) {
		return this.update("com.ycnet.frms.mapper.DeviceImageMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	 * @Title: queryByImei
	 * @Description: 根据IMEI查询图片信息
	 * @param @param devImei
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月28日 下午2:57:21
	 * @version V1.0
	 */
	@Override
	public List<DeviceImage> queryByImei(String devImei) {
		return this.selectList("com.ycnet.frms.mapper.DeviceImageMapper.queryByImei", devImei);
	}

	/**
	 * 
	 * @Title: queryByImeiAndColtime
	 * @Description: 根据imei,上报数据类型和上报数据时间查询一条图片信息
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月14日 上午1:24:31
	 * @version V1.0
	 */
	@Override
	public DeviceImage queryByImeiAndColtime(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.DeviceImageMapper.queryByImeiAndColtime",map);
	}

}
  
    