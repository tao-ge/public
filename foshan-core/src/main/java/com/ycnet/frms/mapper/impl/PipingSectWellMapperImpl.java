package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.PipingSectWell;
import com.ycnet.frms.mapper.PipingSectWellMapper;

@Repository("pipingSectWellMapper")
public class PipingSectWellMapperImpl extends BaseSqlSupport implements PipingSectWellMapper{
	@Override
	public int insert(PipingSectWell record)
	{
		return this.insert("com.ycnet.frms.mapper.PipingSectWellMapper.insert",record);
	}

	@Override
	public PipingSectWell selectByPrimaryKey(Long id)
	{
		return this.selectOne("com.ycnet.frms.mapper.PipingSectWellMapper.selectByPrimaryKey",id);
	}

	@Override
	public int deleteByPrimaryKey(Long id)
	{
		return this.delete("com.ycnet.frms.mapper.PipingSectWellMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insertSelective(PipingSectWell record)
	{
		return this.insert("com.ycnet.frms.mapper.PipingSectWellMapper.insertSelective",record);
	}

	@Override
	public int updateByPrimaryKeySelective(PipingSectWell record)
	{
		return this.update("com.ycnet.frms.mapper.PipingSectWellMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(PipingSectWell record)
	{
		return this.update("com.ycnet.frms.mapper.PipingSectWellMapper.updateByPrimaryKey",record);
	}

	/**
     * 
    * @Title: queryByPipingSectId 
    * @Description: 根据管道段ID查询井ID 
    * @param @param pipingSectId
    * @param @return    
    * @return List<PipingSectWell>
    * @author liucanghai 
    * @throws
    * @date 2018年1月18日 上午10:50:07 
    * @version V1.0
     */
	@Override
	public List<PipingSectWell> queryByPipingSectId(Long pipingSectId) {
		return this.selectList("com.ycnet.frms.mapper.PipingSectWellMapper.queryByPipingSectId", pipingSectId);
	}

	/**
	 * 
	* @Title: querySpaceIdForPipingCableWell 
	* @Description: 根据井ID和管道段ID查询面ID 
	* @param @param wellId
	* @param @param pipingSectId
	* @param @return    
	* @return PipingSectWell
	* @author liucanghai 
	* @throws
	* @date 2018年1月22日 上午9:21:20 
	* @version V1.0
	 */
	@Override
	public PipingSectWell querySpaceIdForPipingCableWell(Long wellId, Long pipingSectId) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("wellId", wellId);
		map.put("pipingSectId", pipingSectId);
		return this.selectOne("com.ycnet.frms.mapper.PipingSectWellMapper.querySpaceIdForPipingCableWell", map);
	}

	/**
	 * 
	* @Title: queryByWellId 
	* @Description: 根据井ID查询关联管道段 
	* @param @param wellId
	* @param @return    
	* @return List<PipingSectWell>
	* @author liucanghai 
	* @throws
	* @date 2018年3月1日 上午11:29:58 
	* @version V1.0
	 */
	@Override
	public List<PipingSectWell> queryByWellId(Long wellId) {
		return this.selectList("com.ycnet.frms.mapper.PipingSectWellMapper.queryByWellId", wellId);
	}
}
