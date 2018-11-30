package com.ycnet.frms.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.FacilityMapper;

@Component
public class UtilsService {

	@Resource(name = "facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper sectMapper;
	
	private static UtilsService utils;
	
	@PostConstruct
	public void initialize()
	{
		utils = this;
		utils.facilityMapper = this.facilityMapper;
		utils.sectMapper = this.sectMapper;
	}
	
	public static Facility getFacility(Long devId)
	{
		return utils.facilityMapper.selectByPrimaryKey(devId);
	}
	
	public static CableSection getSection(Long sectId)
	{
		return utils.sectMapper.selectByPrimaryKey(sectId);
	}
	
}
