package com.ycnet.frms.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.FrmsException;
import com.ycnet.frms.bean.FacilityEntity;
import com.ycnet.frms.bean.FiberdiscGroupEntity;
import com.ycnet.frms.mapper.FiberdiscGroupEntityMapper;
import com.ycnet.frms.service.DiscinfoEntityService;
import com.ycnet.frms.service.FacilityEntityService;
import com.ycnet.frms.service.FiberdiscGroupEntityService;

@Service("fiberdiscGroupEntityService")
public class FiberdiscGroupEntityServiceImpl implements FiberdiscGroupEntityService{
	
	@Resource(name="fiberdiscGroupEntityMapper")
	private FiberdiscGroupEntityMapper fiberdiscGroupEntityMapper;
	
	@Resource(name="fiberdiscGroupEntityService")
	private FiberdiscGroupEntityService fiberdiscGroupEntityService;
	
	@Resource(name="facilityEntityService")
	private FacilityEntityService facilityEntityService;
	
	@Resource(name="discinfoEntityService")
	private DiscinfoEntityService discinfoEntityService;

	/**
	 * 
	 * @Title: addGroup
	 * @Description: 添加分组
	 * @param @param group
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午10:38:17
	 * @version V1.0
	 */
	@Override
	public int addGroup(FiberdiscGroupEntity group) {
		int ret = 0;
		FacilityEntity facility = facilityEntityService.selectById(group.getDevId());
		if(facility==null)
		{
			throw new FrmsException("所属设施不正确。");
		}
		//保存分组
		 ret = fiberdiscGroupEntityService.insert(group);
		//重新生成熔纤盘信息
		 discinfoEntityService.genDiscinfo(group, facility);
		//重新生成熔纤盘点数据
		 facilityEntityService.genFiberdisc(facility.getDevId(), group.getSide(), group.getDiscNum(),group.getPortNum(),null,group.getStartIndex());
		return ret;
	}

	/**
	 * 
	 * @Title: insert
	 * @Description: 保存分组
	 * @param @param group
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午10:46:37
	 * @version V1.0
	 */
	@Override
	public int insert(FiberdiscGroupEntity fiberdiscGroup) {
		//强制变成大写
		fiberdiscGroup.setSide(fiberdiscGroup.getSide().toUpperCase());
		FacilityEntity f = facilityEntityService.selectById(fiberdiscGroup.getDevId());
		if(f==null)
			throw new FrmsException("获取设施出现异常。");
		FiberdiscGroupEntity bean = new FiberdiscGroupEntity();
		bean.setDevId(fiberdiscGroup.getDevId());
		if(fiberdiscGroup.getSide().toUpperCase().indexOf("FG")!=-1) {
			throw new FrmsException("分组编码"+fiberdiscGroup.getSide()+"中不能包含FG");
		}
		if(fiberdiscGroup.getSide().toUpperCase().indexOf("ZR")!=-1) {
			throw new FrmsException("分组编码"+fiberdiscGroup.getSide()+"中不能包含ZR");
		}
		bean.setSide(fiberdiscGroup.getSide());
		List<?> l = fiberdiscGroupEntityMapper.queryGroupByDev(bean);
		if(l!=null&l.size()>0)
		{
			throw new FrmsException("分组编码已存在。");
		}
		
		return fiberdiscGroupEntityMapper.insertSelective(fiberdiscGroup);
	}
}
  
    