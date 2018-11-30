package com.ycnet.frms.service.impl;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.mapper.CablesectionInvestMapper;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CablesectionInvest;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.InvestmentMapper;
import com.ycnet.frms.service.CablesectionInvestService;

@Service("cablesectionInvestService")
@Transactional
public class CablesectionInvestServiceImpl implements CablesectionInvestService{

	@Resource(name="cablesectionInvestMapper")
	private CablesectionInvestMapper cablesectionInvestMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper cableSectionMapper;
	
	/**
	 * 光缆直熔信息保存(添加和修改)
	 */
	@Override
	public int save(CablesectionInvest cablesectionInvest, Users user) {
		int num = 0;
		if(cablesectionInvest.getSectInvestId() == null) {//添加
			cablesectionInvest.setCreateTime(new Date());
			cablesectionInvest.setCreateUser(user.getUserId());
			cablesectionInvest.setOrgId(user.getOrgId());
			num = cablesectionInvestMapper.insertSelective(cablesectionInvest);
		}else {//修改
			cablesectionInvest.setLastModifyTime(new Date());
			cablesectionInvest.setLastModifyUser(user.getUserId());
			num = cablesectionInvestMapper.updateByPrimaryKeySelective(cablesectionInvest);
		}
		return num;
	}
	

	@Override
	public List<CablesectionInvest> queryInvestByInversId(Long investId) {
		return cablesectionInvestMapper.queryInvestByInversId(investId);
	}



}
  
    