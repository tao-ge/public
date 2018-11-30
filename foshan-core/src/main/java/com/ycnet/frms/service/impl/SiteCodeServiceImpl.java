package com.ycnet.frms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.FacilityType;
import com.ycnet.frms.bean.SiteCode;
import com.ycnet.frms.mapper.SiteCodeMapper;
import com.ycnet.frms.service.SiteCodeService;

@Service("siteCodeService")
public class SiteCodeServiceImpl implements SiteCodeService {

	@Resource(name="siteCodeMapper")
	private SiteCodeMapper siteCodeMapper;
	
	@Override
	@Transactional
	public synchronized String getNewDevCode() {
		SiteCode siteCode = siteCodeMapper.selectSiteCode();
		if (siteCode==null)
		{
			siteCode = new SiteCode();
			siteCode.setDevCode(FacilityType.GJ.toString(1));
			siteCodeMapper.insertSelective(siteCode);
		}
		else
		{
			String devCode = siteCode.getDevCode();
			devCode = devCode==null||"".equals(devCode)?"0":devCode;
			devCode = devCode.replaceFirst(FacilityType.GJ.toString(), "");
			Integer  num = Integer.valueOf(devCode, 10);
			num ++;
			devCode = FacilityType.GJ.toString(num);
			siteCode.setDevCode(devCode);
			siteCodeMapper.updateByPrimaryKeySelective(siteCode);
		}
		return siteCode.getDevCode();
	}

	@Override
	@Transactional
	public synchronized String getNewSectCode() {
		SiteCode siteCode = siteCodeMapper.selectSiteCode();
		if (siteCode==null)
		{
			siteCode = new SiteCode();
			siteCode.setSectCode(FacilityType.GL.toString(1));
			siteCodeMapper.insertSelective(siteCode);
		}
		else
		{
			String sectCode = siteCode.getSectCode();
			sectCode = sectCode==null||"".equals(sectCode)?"0":sectCode;
			sectCode = sectCode.replaceFirst(FacilityType.GL.toString(), "");
			Integer  num = Integer.valueOf(sectCode, 10);
			num ++;
			sectCode = FacilityType.GL.toString(num);
			siteCode.setSectCode(sectCode);
			siteCodeMapper.updateByPrimaryKeySelective(siteCode);
		}
		return siteCode.getSectCode();
	}

	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<SiteCode> queryList() {
		return siteCodeMapper.queryList();
	}

}
