package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.CableSectionDec;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.mapper.CableSectionDecMapper;
import com.ycnet.frms.vo.CableSectionConditionBean;
import com.ycnet.frms.vo.CableSectionDecBean;

@Repository("cableSectionDecMapper")
public class CableSectionDecMapperImpl extends BaseSqlSupport implements CableSectionDecMapper{

	@Override
	public int deleteByPrimaryKey(Long sectDecId) {
		return this.delete("com.ycnet.frms.mapper.CableSectionDecMapper.deleteByPrimaryKey",sectDecId);
	}

	@Override
	public int insert(CableSectionDec record) {
		return this.insert("com.ycnet.frms.mapper.CableSectionDecMapper.insert",record);
	}

	@Override
	public int insertSelective(CableSectionDec record) {
		return this.insert("com.ycnet.frms.mapper.CableSectionDecMapper.insertSelective",record);
	}

	@Override
	public CableSectionDec selectByPrimaryKey(Long sectDecId) {
		return this.selectOne("com.ycnet.frms.mapper.CableSectionDecMapper.selectByPrimaryKey",sectDecId);
	}

	@Override
	public int updateByPrimaryKeySelective(CableSectionDec record) {
		return this.update("com.ycnet.frms.mapper.CableSectionDecMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(CableSectionDec record) {
		return this.update("com.ycnet.frms.mapper.CableSectionDecMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<CableSectionDec> queryCabledetailsList(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.CableSectionDecMapper.queryCabledetailsList",map);
	}

	/**
	 * 
	* @Title: queryZgSectionDev 
	* @Description: TODO(根据端口编码查询资管光缆段) 
	* @param @param port01
	* @param @return    入参
	* @return CableSectionDec    返回类型
	* @author liucanghai（作者） 
	* @throws
	* @date 2017年11月27日 下午3:29:58 
	* @version V1.0
	 */
	@Override
	public CableSectionDec queryZgSectionDev(String port01) {
		return this.selectOne("com.ycnet.frms.mapper.CableSectionDecMapper.queryZgSectionDev",port01);
	}

	@Override
	public Integer selectCountAll(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.CableSectionDecMapper.selectCountAll",map);
	}

	@Override
	public CableSectionDec querySectionDecBySectIdAndFiberNo(Long sectId, Long fiberNo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sectId", sectId);
		map.put("fiberNo", fiberNo);
		return this.selectOne("com.ycnet.frms.mapper.CableSectionDecMapper.querySectionDecBySectIdAndFiberNo",map);
	}

	@Override
	public CableSectionDec queryIsOccup(HashMap<String, Long> map) {
		return this.selectOne("com.ycnet.frms.mapper.CableSectionDecMapper.queryIsOccup",map);
	}

	/**
	 * 根据SectId和FiberNo查询一条
	 */
	@Override
	public Lines queryBySectIdAndFiberNo(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.queryBySectIdAndFiberNo",map);
	}

	/**
	 * 根据port01查询资管详情
	 * 刘沧海
	 * 2017/11/29
	 */
	@Override
	public CableSectionDecBean queryZgSectionDevZG(String code) {
		return this.selectOne("com.ycnet.frms.mapper.CableSectionDecMapper.queryZgSectionDevZG",code);
	}

	/**
	 * 修改纤芯状态
	 */
	@Override
	public int modifyBySectIdAndState(Long sectId, String fiberState,String fiberState1) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sectId", sectId);
		map.put("fiberState", fiberState);
		map.put("fiberState1", fiberState1);
		return this.update("com.ycnet.frms.mapper.CableSectionDecMapper.modifyBySectIdAndState",map);
	}

	@Override
	public CableSectionDec queryBySectNameAndFiberNo(String sectName, Long fiberNo) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("secName", sectName);
		map.put("fiberNo", fiberNo);
		return this.selectOne("com.ycnet.frms.mapper.CableSectionDecMapper.queryBySectNameAndFiberNo",map);
	}

	/**
	 * 根据光缆ID和光缆芯数查询大于光缆芯数的所有光缆描述
	 */
	@Override
	public List<CableSectionDec> selectBySectIdAndGreatFiberNum(Long sectId, Long oldFiberNum,Long newFiberNum) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sectId", sectId);
		map.put("oldFiberNum", oldFiberNum);
		map.put("newFiberNum", newFiberNum);
		return this.selectList("com.ycnet.frms.mapper.CableSectionDecMapper.selectBySectIdAndGreatFiberNum",map);
	}

	@Override
	public int deleteBySectIdAndFiberNo(Long sectId, Long fiberNo) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sectId", sectId);
		map.put("fiberNo", fiberNo);
		return this.delete("com.ycnet.frms.mapper.CableSectionDecMapper.deleteBySectIdAndFiberNo",map);
	}

	@Override
	public List<CableSectionDec> selectBySectId(Long sectId) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.CableSectionDecMapper.selectBySectId",sectId);
	}

}
