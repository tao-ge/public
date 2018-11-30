package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Piping;
import com.ycnet.frms.bean.Piping;
import com.ycnet.frms.mapper.PipingMapper;
import com.ycnet.frms.vo.PipingBean;

@Repository("pipingMapper")
public class PipingMapperImpl extends  BaseSqlSupport implements PipingMapper {

	@Override
	public int insert(Piping record)
	{
		return this.insert("com.ycnet.frms.mapper.PipingMapper.insert",record);
	}

	@Override
	public Piping selectByPrimaryKey(Long id)
	{
		return this.selectOne("com.ycnet.frms.mapper.PipingMapper.selectByPrimaryKey",id);
	}

	@Override
	public int deleteByPrimaryKey(Long id)
	{
		return this.delete("com.ycnet.frms.mapper.PipingMapper.deleteByPrimaryKey",id);
	}

	/**
	 * 保存井设施
	 * 刘沧海
	 * 
	 */
	@Override
	public int insertSelective(Piping record)
	{
		return this.insert("com.ycnet.frms.mapper.PipingMapper.insertSelective",record);
	}
	//changePipingSectionByPipingId调用 zhouyu
	//recorePipingSectionByPipingId调用 zhouyu
	@Override
	public int updateByPrimaryKeySelective(Piping record)
	{
		return this.update("com.ycnet.frms.mapper.PipingMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(Piping record)
	{
		return this.update("com.ycnet.frms.mapper.PipingMapper.updateByPrimaryKey",record);
	}
	/**
	 * 
	* @Title: queryPipingByPipingSectId 
	* @Description: 根据设备id查询管孔信息列表
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月22日 上午11:10:30 
	* @version V1.0
	 */
	@Override
	public List<PipingBean> queryBypipingSectId(Long pipingSectId,Long orgId,Long wellId) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pipingSectId", pipingSectId);
		map.put("orgId", orgId);
		map.put("wellId", wellId);
		return this.selectList("com.ycnet.frms.mapper.PipingMapper.queryBypipingSectId",map);
	}

	/**
	 * 
	* @Title: queryByWellId 
	* @Description: 根据井ID查询管控信息 
	* @param @param wellId
	* @param @return    
	* @return List<Piping>
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 下午8:00:53 
	* @version V1.0
	 */
	@Override
	public List<Piping> queryByWellId(Long wellId) {
		return this.selectList("com.ycnet.frms.mapper.PipingMapper.queryByWellId", wellId);
	}
	/**
	 * 
	* @Title: queryPipingByPipingSectId 
	* @Description: 根据管道id查询管孔信息列表
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月17日 上午11:10:30 
	* @version V1.0
	 */
	@Override
	public Piping queryPipingbeanByPrimaryKey(Long pipingId) {
		return this.selectOne("com.ycnet.frms.mapper.PipingMapper.selectPipingbeanByPrimaryKey", pipingId);
	}
}
