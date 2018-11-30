package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.CablesectionInvest;
import com.ycnet.frms.mapper.CablesectionInvestMapper;

@Repository("cablesectionInvestMapper")
public class CablesectionInvestMapperImpl extends BaseSqlSupport implements CablesectionInvestMapper{

	@Override
	public int deleteByPrimaryKey(Long sectInvestId) {
		return this.delete("com.ycnet.frms.mapper.CablesectionInvestMapper.deleteByPrimaryKey",sectInvestId);
	}

	@Override
	public int insert(CablesectionInvest record) {
		return this.insert("com.ycnet.frms.mapper.CablesectionInvestMapper.insert",record);
	}

	@Override
	public int insertSelective(CablesectionInvest record) {
		return this.insert("com.ycnet.frms.mapper.CablesectionInvestMapper.insertSelective",record);
	}

	@Override
	public CablesectionInvest selectByPrimaryKey(Long sectInvestId) {
		return this.selectOne("com.ycnet.frms.mapper.CablesectionInvestMapper.selectByPrimaryKey",sectInvestId);
	}

	@Override
	public int updateByPrimaryKeySelective(CablesectionInvest record) {
		return this.update("com.ycnet.frms.mapper.CablesectionInvestMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(CablesectionInvest record) {
		return this.update("com.ycnet.frms.mapper.CablesectionInvestMapper.updateByPrimaryKey",record);
	}
	
	/**
	 * 查询所属直熔盘列表
	* 
	* @Title: CablesectionInvestController.java 
	* @Description: TODO 
	* @param @param devId
	* @param @return
	* @return Object
	* @author fl
	* @date 2017年12月16日 上午11:17:57
	* @version V1.0
	 */
	@Override
	public List<CablesectionInvest> queryInvestByInversId(Long investId) {
		return this.selectList("com.ycnet.frms.mapper.CablesectionInvestMapper.queryInvestByInversId", investId);
	}
	
	/**
	 * 
	* @Description: 删除直熔盘光缆段
	 */
	@Override
	public int deleteByInvestId(Long investId) {
		return this.delete("com.ycnet.frms.mapper.CablesectionInvestMapper.deleteByInvestId",investId);
	}

	@Override
	public List<CablesectionInvest> selectByUpSectId(Long sectId) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.CablesectionInvestMapper.selectByUpSectId", sectId);
	}
	
}
  
    