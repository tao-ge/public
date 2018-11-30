package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.FrmsException;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.service.DiscinfoService;
import com.ycnet.frms.service.FacilityCoreService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscGroupService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.vo.Disc;
import com.ycnet.frms.vo.FiberdiscConditionBean;
import com.ycnet.frms.vo.FiberdiscGroupVo;

@Service("facilityCoreService")
@Transactional
public class FacilityCoreServiceImpl implements FacilityCoreService {

	@Override
	public int addGroup(FiberdiscGroup group) {
		int ret = 0;
		Facility facility = facilityService.selectById(group.getDevId());
		if(facility==null)
		{
			throw new FrmsException("所属设施不正确。");
		}
		//保存分组
		 ret = groupService.insert(group);
		//重新生成熔纤盘信息
		discinfoService.genDiscinfo(group, facility);
		//重新生成熔纤盘点数据
		facilityService.genFiberdisc(facility.getDevId(), group.getSide(), group.getDiscNum(),group.getPortNum(),null,group.getStartIndex());
		return ret;
	}
	
	@Override
	public int addGroup1(FiberdiscGroupVo group) {
		int ret = 0;
		Facility facility = facilityService.selectById(group.getDevId());
		if(facility==null)
		{
			throw new FrmsException("所属设施不正确。");
		}
		//保存分组
		 ret = groupService.insert(group);
		String side = group.getSide();
		Long devId = facility.getDevId();
		 Disc[] discs = group.getDisc();
		 if(discs!=null){
			 for(int i = 0;i <discs.length;i++){
				 Disc d = discs[i];
				 	Integer row = d.getRow();
				 	String model = d.getModel();
				 	model=model==null||"".equals(model.replaceAll(" ",""))?null:model.replaceAll(" ","").toUpperCase();
				 	row=row==null||row.intValue()<=0?1:row;
				 	Discinfo discinfo = new Discinfo();
					discinfo.setDevId(devId);
					discinfo.setGroupId(group.getGroupId());
					discinfo.setSide(side);
					discinfo.setDiscName(d.getDiscName());
					discinfo.setPortNum(d.getPortNum());
					discinfo.setRemark(d.getRemark()==null||"".equals(d.getRemark().trim())?d.getModel():d.getRemark());
					discinfoService.insert(discinfo, row,model);
					facilityService.genFiberdisc(facility.getDevId(), group.getSide(), 1,d.getPortNum(),row,group.getStartIndex(),model);
			 }
		 }
		return ret;
	}

	/**
	 * 
	 * @Title: addDiscInfo
	 * @Description: 添加/修改熔纤盘 (支持修改熔纤盘备注信息)
	 * @param @param discinfo
	 * @param @param index
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月31日 上午9:21:02
	 * @version V1.0
	 */
	@Override
	public int addDiscInfo(Discinfo discinfo,int index) {
		int ret = 0;
		//修改时只修改备注信息
		if(discinfo.getDiscId() != null && discinfo.getDiscId().longValue()!=-999L) {
			Discinfo dic = new Discinfo();
			dic.setDiscId(discinfo.getDiscId());
			dic.setRemark(discinfo.getRemark());
			dic.setLastModifyTime(new Date());
			dic.setLastModifyUser(discinfo.getLastModifyUser());
			ret =discinfoService.update(dic);
			return ret;
		}
		if(discinfo.getDevId() != null) {
			Facility fa = facilityService.selectById(discinfo.getDevId());
			if(fa == null) {
				throw new FrmsException("所属设施不正确。");
			}
		}
		ret =discinfoService.insert(discinfo, index,null);
		facilityService.genFiberdisc(discinfo.getDevId(), discinfo.getSide(), 1, discinfo.getPortNum(),index);
		FiberdiscConditionBean param = new FiberdiscConditionBean();
		param.setDevId(discinfo.getDevId());
		param.setSide(discinfo.getSide());
		List<Fiberdisc> list = fiberdiscService.selectFiberdisc(param);
		for(Fiberdisc disc:list)
		{
			disc.setRemark(discinfo.getRemark());
			fiberdiscMapper.updateByPrimaryKeySelective(disc);
		}
		
		return ret;
	}

	@Override
	public int saveFiberdisc(Fiberdisc fiberdisc) {
		return 0;
	}
	
	
	
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="fiberdiscGroupService")
	private FiberdiscGroupService groupService;
	
	@Resource(name="discinfoService")
	private DiscinfoService discinfoService;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;
	
	@Resource(name="fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;

	/**
	 * 添加分组（资管专用）
	 */
	@Override
	public int addGroupZG(FiberdiscGroup group) {
		// TODO Auto-generated method stub
		int ret = 0;
		Facility facility = facilityService.selectByIdZG(group.getDevId());
		if(facility==null)
		{
			throw new FrmsException("所属设施不正确。");
		}
		//保存分组
		ret = groupService.insert(group);
		ret = groupService.insertZG(group);
		//重新生成熔纤盘信息
		discinfoService.genDiscinfoZG(group, facility);
		//重新生成熔纤盘点数据
		facilityService.genFiberdiscZG(facility.getDevId(), group.getSide(), group.getDiscNum(),group.getPortNum(),null,group.getStartIndex());
		return ret;
	}
}
