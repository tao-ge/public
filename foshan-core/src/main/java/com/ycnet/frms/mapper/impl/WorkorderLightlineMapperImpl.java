package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.WorkorderFiberdiscabindA;
import com.ycnet.frms.bean.WorkorderLightline;
import com.ycnet.frms.mapper.WorkorderLightlineMapper;

@Repository("workorderLightlineMapper")
public class WorkorderLightlineMapperImpl extends BaseSqlSupport implements WorkorderLightlineMapper {

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.delete("com.ycnet.frms.mapper.WorkorderLightlineMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(WorkorderLightline record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderLightlineMapper.insert",record);
	}

	@Override
	public int insertSelective(WorkorderLightline record) {
		return this.insert("com.ycnet.frms.mapper.WorkorderLightlineMapper.insertSelective",record);
	}

	@Override
	public WorkorderLightline selectByPrimaryKey(Long id) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderLightlineMapper.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(WorkorderLightline record) {
		return this.update("com.ycnet.frms.mapper.WorkorderLightlineMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(WorkorderLightline record) {
		return this.update("com.ycnet.frms.mapper.WorkorderLightlineMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<WorkorderLightline> queryLineByWorkId(Long workorderId) {
		return this.selectList("com.ycnet.frms.mapper.WorkorderLightlineMapper.queryLineByWorkId", workorderId);
	}

	/**
	 * 查询是否存在设备端口配置
	* @Title: queryDeviceExist 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param designroutesId
	* @param @return    入参
	* @return WorkorderLightline    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月6日 下午3:15:45 
	* @version V1.0
	 */
	@Override
	public WorkorderLightline queryDeviceExist(Long designroutesId) {
		return this.selectOne("com.ycnet.frms.mapper.WorkorderLightlineMapper.queryDeviceExist", designroutesId);
	}
}
