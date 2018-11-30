package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.TransfLogEntity;
import com.ycnet.frms.mapper.TransfLogEntityMapper;
import com.ycnet.frms.vo.FacilityAll;

@Repository("transfLogEntityMapper")
public class TransfLogEntityMapperImpl extends  BaseSqlSupport implements TransfLogEntityMapper{

	@Override
	public int deleteByPrimaryKey(Long orgId) {
		return this.delete("com.ycnet.frms.mapper.TransfLogEntityMapper.deleteByPrimaryKey",orgId);
	}

	@Override
	public int insert(TransfLogEntity record) {
		return this.insert("com.ycnet.frms.mapper.TransfLogEntityMapper.insert",record);
	}

	@Override
	public int insertSelective(TransfLogEntity record) {
		return this.insert("com.ycnet.frms.mapper.TransfLogEntityMapper.insertSelective",record);
	}

	@Override
	public TransfLogEntity selectByPrimaryKey(Long orgId) {
		return this.selectOne("com.ycnet.frms.mapper.TransfLogEntityMapper.selectByPrimaryKey",orgId);
	}

	@Override
	public int updateByPrimaryKeySelective(TransfLogEntity record) {
		return this.update("com.ycnet.frms.mapper.TransfLogEntityMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(TransfLogEntity record) {
		return this.update("com.ycnet.frms.mapper.TransfLogEntityMapper.updateByPrimaryKey",record);
	}

	/**
     * 设备上报日志查询
    * @Title: queryAll 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param map
    * @param @return    入参
    * @return List<TransfLogEntity>    返回类型
    * @author FL（作者） 
    * @throws
    * @date 2018年5月24日 上午11:09:31 
    * @version V1.0
     */
	@Override
	public List<TransfLogEntity> queryAll(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.TransfLogEntityMapper.queryAll",map);
	}


	/**
	 * 设备上报日志记录总条数
	* @Title: queryAllCount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return Integer    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月24日 上午11:09:44 
	* @version V1.0
	 */
	@Override
	public Integer queryAllCount(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.TransfLogEntityMapper.queryAllCount",map);
	}
	
	
	
}
