package com.ycnet.frms.service.impl;

import java.util.List;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.Investment;
import com.ycnet.frms.mapper.CablesectionInvestMapper;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.InvestmentMapper;
import com.ycnet.frms.service.InvestmentService;

@Service("investmentService")
@Transactional
public class InvestmentServiceImpl implements InvestmentService{
	
	@Resource(name="investmentMapper")
	private InvestmentMapper investmentMapper;
	
	@Resource(name="cablesectionInvestMapper")
	private CablesectionInvestMapper cablesectionInvestMapper;

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
	public List<Investment> queryByDevId(Long devId) {
		List<Investment> investmentList=investmentMapper.queryByDevId(devId);
		if (investmentList!=null && investmentList.size()>0) {
			for (int i = 0; i < investmentList.size(); i++) {
				Investment investment=investmentList.get(i);
				investment.setCablesectionInvestList(cablesectionInvestMapper.queryInvestByInversId(investment.getInvestId()));
			}
		}
		return investmentList;
	}

	/**
	 * 保存直熔盘(修改和添加)
	 */
	@Override
	public int save(Investment investment, Users user) {
		int num = 0;
		if(investment.getInvestId() == null) {//添加
			investment.setCreateTime(new Date());
			investment.setCreateUser(user.getUserId());
			investment.setOrgId(user.getOrgId());
			num = investmentMapper.insertSelective(investment);
		}else {//修改
			investment.setLastModifyTime(new Date());
			investment.setLastModifyUser(user.getUserId());
			num= investmentMapper.updateByPrimaryKeySelective(investment);
		}
		return num;
	}

	/**
	 * 删除直熔盘
	 */
	@Override
	public int deleteInvestByInvestId(Long investId) {
		
		cablesectionInvestMapper.deleteByInvestId(investId);
		return investmentMapper.deleteByPrimaryKey(investId);
	}
}
  
    