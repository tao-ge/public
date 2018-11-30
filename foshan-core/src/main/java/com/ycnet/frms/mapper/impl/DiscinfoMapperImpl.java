package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.DiscinfoZF;
import com.ycnet.frms.mapper.DiscinfoMapper;
import com.ycnet.frms.vo.mobile.DeviceDiscinfoEntityMobile;

@Repository("discinfoMapper")
public class DiscinfoMapperImpl extends BaseSqlSupport implements DiscinfoMapper {

	@Override
	public int deleteByPrimaryKey(Long discId) {
		return this.delete("com.ycnet.frms.mapper.DiscinfoMapper.deleteByPrimaryKey",discId);
	}

	@Override
	public int insert(Discinfo record) {
		return this.insert("com.ycnet.frms.mapper.DiscinfoMapper.insert",record);
	}

	@Override
	public int insertSelective(Discinfo record) {
		return this.insert("com.ycnet.frms.mapper.DiscinfoMapper.insertSelective",record);
	}

	@Override
	public Discinfo selectByPrimaryKey(Long discId) {
		return this.selectOne("com.ycnet.frms.mapper.DiscinfoMapper.selectByPrimaryKey",discId);
	}

	@Override
	public int updateByPrimaryKeySelective(Discinfo record) {
		return this.update("com.ycnet.frms.mapper.DiscinfoMapper.updateByPrimaryKeySelective",record);
	}
	
	@Override
	public int updateByPrimaryKeySelectiveZG(Discinfo record) {
		return this.update("com.ycnet.frms.mapper.DiscinfoMapper.updateByPrimaryKeySelectiveZG",record);
	}

	@Override
	public int updateByPrimaryKey(Discinfo record) {
		return this.update("com.ycnet.frms.mapper.DiscinfoMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<Discinfo> selectByGroup(Long groupId) {
		return this.selectList("com.ycnet.frms.mapper.DiscinfoMapper.selectByGroup",groupId);
	}

	@Override
	public Discinfo selectByDiscCode(String discCode) {
		return this.selectOne("com.ycnet.frms.mapper.DiscinfoMapper.selectByDiscCode",discCode);
	}

	@Override
	public int deleteDiscInfo(Long groupId) {
		return this.delete("com.ycnet.frms.mapper.DiscinfoMapper.deleteDiscInfo",groupId);
	}

	/**
	 * 用于删除设备
	 */
	@Override
	public List<Discinfo> selectByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.DiscinfoMapper.selectByDevId", devId);
	}
	/**
	 * 用于删除设备
	 */
	@Override
	public int deleteByDevId(Long devId) {
		return this.delete("com.ycnet.frms.mapper.DiscinfoMapper.deleteByDevId",devId);
	}

	//导出数据库 刘沧海 2017/10/13
	@Override
	public List<DiscinfoZF> queryList(Long orgId, String areaCode) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode", areaCode);
		return this.selectList("com.ycnet.frms.mapper.DiscinfoMapper.queryList",map);
	}

	@Override
	public int insertSelectiveZG(Discinfo record) {
		return this.insert("com.ycnet.frms.mapper.DiscinfoMapper.insertSelectiveZG",record);
	}

	/**
	 * 
	* @Title: deleteByDevCodeMohu 
	* @Description: 根据接头拼接编码删除盘数据  
	* @param @param devCode
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月20日 下午6:20:26 
	* @version V1.0
	 */
	@Override
	public int deleteByDevCodeMohu(String devCode) {
		return this.delete("com.ycnet.frms.mapper.DiscinfoMapper.deleteByDevCodeMohu",devCode);
	}

	/**
	 * 根据盘编码删除熔纤盘
	* @Title: deleteByDiscCode 
	* @Description: TODO 
	* @param @param replace
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月24日 上午12:18:46 
	* @version V1.0
	 */
	@Override
	public int deleteByDiscCode(String discCode) {
		return this.delete("com.ycnet.frms.mapper.DiscinfoMapper.deleteByDiscCode",discCode);
	}

	/**
	 * 
	* @Title: queryByDevIdForcode 
	* @Description: 根据设施ID和编码查询 
	* @param @param devId
	* @param @param codeOut
	* @param @return    
	* @return Discinfo
	* @author liucanghai 
	* @throws
	* @date 2017年12月26日 下午4:38:14 
	* @version V1.0
	 * @param codeIn 
	 */
	@Override
	public List<Discinfo> queryByDevIdForcode(Long devId,String codeIn, String codeOut) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("codeIn", codeIn);
		map.put("codeOut", codeOut);
		return this.selectList("com.ycnet.frms.mapper.DiscinfoMapper.queryByDevIdForcode", map);
	}

	/**
	 * 根据设施Id和分面查询熔纤盘
	 */
	@Override
	public List<Discinfo> queryByDevIdAndSide(Long devId, String side) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("side", side);
		return this.selectList("com.ycnet.frms.mapper.DiscinfoMapper.queryByDevIdAndSide", map);
	}

	/**
	 * 根据盘ID.查询端子占用状态
	* @Title: queryByDiscId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return List<DeviceDiscinfoEntityMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 上午11:29:42 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntityMobile> queryByDiscId(Long discId) {
		return this.selectList("com.ycnet.frms.mapper.DiscinfoMapper.queryByDiscId", discId);
	}

	
	
}
