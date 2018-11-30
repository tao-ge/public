package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.mapper.WellMapper;
import com.ycnet.frms.vo.WellBean;

@Repository("wellMapper")
public class WellMapperImpl extends  BaseSqlSupport implements WellMapper {

	@Override
	public int insert(Well record)
	{
		return this.insert("com.ycnet.frms.mapper.WellMapper.insert",record);
	}

	@Override
	public Well selectByPrimaryKey(Long id)
	{
		return this.selectOne("com.ycnet.frms.mapper.WellMapper.selectByPrimaryKey",id);
	}

	@Override
	public int deleteByPrimaryKey(Long id)
	{
		return this.delete("com.ycnet.frms.mapper.WellMapper.deleteByPrimaryKey",id);
	}

	/**
	 * 保存井设施
	 * 刘沧海
	 * 
	 */
	@Override
	public int insertSelective(Well record)
	{
		return this.insert("com.ycnet.frms.mapper.WellMapper.insertSelective",record);
	}
	
	@Override
	public int updateByPrimaryKeySelective(Well record)
	{
		return this.update("com.ycnet.frms.mapper.WellMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(Well record)
	{
		return this.update("com.ycnet.frms.mapper.WellMapper.updateByPrimaryKey",record);
	}

	/**
	 * 井信息（关联每个井对应的管道段）
	 */
	@Override
	public List<WellBean> queryWells(WellBean well,String Upper,PageBean pb, Long distance,Long orgId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wellName", well.getWellName());
		map.put("distance", distance);
		map.put("curLng", well.getLongitude());
		map.put("upper", Upper);
		map.put("curLat", well.getLatitude());
		map.put("wellState",well.getWellState());
		map.put("isState",well.getIsState());
		map.put("wellNumber", well.getWellNumber());
		map.put("wellKind", well.getWellKind());
		map.put("pb", pb);
		map.put("orgId",orgId);
		return this.selectList("com.ycnet.frms.mapper.WellMapper.queryWells", map);
	}

	/**
	 * 查询井信息,关联光缆段
	 */
	@Override
	public WellBean queryWellByWellId(Long wellId,Long orgId) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("wellId", wellId);
		map.put("orgId", orgId);
		return this.selectOne("com.ycnet.frms.mapper.WellMapper.queryWellByWellId", map);
	}

	@Override
	public Well selectByPrimaryKeyAndOrgId(Long wellId, Long orgId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wellId", wellId);
		map.put("orgId", orgId);
		return this.selectOne("com.ycnet.frms.mapper.WellMapper.selectByPrimaryKeyAndOrgId",map);
	}
	/**
	 * 
	* @Title: queryWells 
	* @Description: 查询井列表
	* @param @param well
	* @param @param orgId
	* @param @param pb
	* @param @return    入参
	* @return List<WellBean>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月2日 下午4:29:59 
	* @version V1.0
	 */
	@Override
	public List<WellBean> queryWells(WellBean well, PageBean pb) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("well", well);
		map.put("pb",pb);
		return this.selectList("com.ycnet.frms.mapper.WellMapper.queryWellByWell",map);
	}

	/**
	 * 
	* @Title: queryBySectId 
	* @Description: 根据光缆段ID查询井信息 
	* @param @param sectId
	* @param @return    
	* @return List<WellBean>
	* @author liucanghai 
	* @throws
	* @date 2018年1月3日 下午2:25:07 
	* @version V1.0
	 */
	@Override
	public List<WellBean> queryBySectId(Long sectId,Long orgId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sectId", sectId);
		map.put("orgId", orgId);
		return this.selectList("com.ycnet.frms.mapper.WellMapper.queryBySectId", map);
	}

	/**
	 * 查询所有井 fl
	 */
	@Override
	public List<Well> queryWell() {
		return this.selectList("com.ycnet.frms.mapper.WellMapper.queryWell");
	}

	@Override
	public Integer queryWellCount(WellBean well, PageBean pb) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("well", well);
		map.put("pb",pb);
		return this.selectOne("com.ycnet.frms.mapper.WellMapper.queryWellCount",map);
	}

	/**
	 * 
	* @Title: updateWellBean 
	* @Description: 修改井设施 
	* @param @param well
	* @param @return    
	* @return long
	* @author liucanghai 
	* @throws
	* @date 2018年2月7日 上午11:27:02 
	* @version V1.0
	 */
	@Override
	public int updateWellBean(Well well) {
		return this.update("com.ycnet.frms.mapper.WellMapper.updateWellBean",well);
	}

	/**
	 * 
	* @Title: queryByWellId 
	* @Description: 根据井ID查询井 
	* @param @param wellId
	* @param @return    
	* @return WellBean
	* @author liucanghai 
	* @throws
	* @date 2018年3月1日 下午1:22:56 
	* @version V1.0
	 */
	@Override
	public WellBean queryByWellId(Long wellId) {
		return this.selectOne("com.ycnet.frms.mapper.WellMapper.queryByWellId", wellId);
	}

	/**
	 * 
	* @Title: queryWellExport 
	* @Description: 条件查询井设施 
	* @param @param well
	* @param @return    
	* @return List<WellBean>
	* @author liucanghai 
	* @throws
	* @date 2018年3月28日 下午3:39:28 
	* @version V1.0
	 */
	@Override
	public List<WellBean> queryWellExport(WellBean well) {
		return this.selectList("com.ycnet.frms.mapper.WellMapper.queryWellExport",well);
	}

	/**
	 * 
	* @Title: queryWellAll 
	* @Description: 查询组织机构下的井设施 
	* @param @param orgId
	* @param @return    
	* @return List<Well>
	* @author liucanghai 
	* @throws
	* @date 2018年3月28日 下午5:36:30 
	* @version V1.0
	 */
	@Override
	public List<Well> queryWellAll(Long orgId) {
		return this.selectList("com.ycnet.frms.mapper.WellMapper.queryWellAll",orgId);
	}

}
