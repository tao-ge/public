package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycnet.core.FrmsException;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.DiscinfoEntity;
import com.ycnet.frms.bean.FacilityEntity;
import com.ycnet.frms.bean.FiberdiscEntity;
import com.ycnet.frms.bean.FiberdiscGroupEntity;
import com.ycnet.frms.mapper.DiscinfoEntityMapper;
import com.ycnet.frms.mapper.FacilityEntityMapper;
import com.ycnet.frms.mapper.FiberdiscEntityMapper;
import com.ycnet.frms.mapper.FiberdiscGroupEntityMapper;
import com.ycnet.frms.service.DiscinfoEntityService;
import com.ycnet.frms.service.FacilityEntityService;
import com.ycnet.frms.service.FiberdiscEntityService;


@Service("discinfoEntityService")
public class DiscinfoEntityServiceImpl implements DiscinfoEntityService{

	@Resource(name="discinfoEntityMapper")
	private DiscinfoEntityMapper discinfoEntityMapper;
	
	@Resource(name="discinfoEntityService")
	private DiscinfoEntityService discinfoEntityService;
	
	@Resource(name="facilityEntityMapper")
	private FacilityEntityMapper facilityEntityMapper;
	
	@Resource(name="fiberdiscGroupEntityMapper")
	private FiberdiscGroupEntityMapper fiberdiscGroupEntityMapper;
	
	@Resource(name="facilityEntityService")
	private FacilityEntityService facilityEntityService;
	
	@Resource(name="fiberdiscEntityService")
	private FiberdiscEntityService fiberdiscEntityService;
	
	@Resource(name="fiberdiscEntityMapper")
	private FiberdiscEntityMapper fiberdiscEntityMapper;

	/**
	 * 
	 * @Title: addDiscInfo
	 * @Description: 添加熔纤盘
	 * @param @param discinfo
	 * @param @param index
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午8:48:05
	 * @version V1.0
	 */
	@Override
	public int addDiscInfo(DiscinfoEntity discinfo, Integer index) {
		int ret = 0;
		if(discinfo.getDiscId() != null && !"".equals(discinfo.getDiscId().toString())) {
			ret =discinfoEntityService.updateByPrimaryKeySelective(discinfo);
			return ret;
		}
		ret =discinfoEntityService.insert(discinfo, index,null);
		facilityEntityService.genFiberdisc(discinfo.getDevId(), discinfo.getSide(), 1, discinfo.getPortNum(),index);
		FiberdiscEntity param = new FiberdiscEntity();
		param.setDevId(discinfo.getDevId());
		param.setSide(discinfo.getSide());
		List<FiberdiscEntity> list = fiberdiscEntityService.selectFiberdisc(param);
		for(FiberdiscEntity disc:list)
		{
			disc.setRemark(discinfo.getRemark());
			fiberdiscEntityMapper.updateByPrimaryKeySelective(disc);
		}
		
		return ret;
	}

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改熔纤盘
	 * @param @param discinfo
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午8:53:52
	 * @version V1.0
	 */
	@Override
	public int updateByPrimaryKeySelective(DiscinfoEntity discinfo) {
		return discinfoEntityMapper.updateByPrimaryKeySelective(discinfo);
	}

	/**
	 * 
	 * @Title: insert
	 * @Description: 添加熔纤盘
	 * @param @param discinfo
	 * @param @param index
	 * @param @param object
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午9:12:14
	 * @version V1.0
	 */
	@Override
	public int insert(DiscinfoEntity discinfo, Integer index, String model) {
		FacilityEntity f = facilityEntityMapper.selectByPrimaryKey(discinfo.getDevId());
		if (f == null)
			throw new FrmsException("所属设施不存在。");
		FiberdiscGroupEntity group = fiberdiscGroupEntityMapper.selectByPrimaryKey(discinfo.getGroupId());
		if (group == null) {
			throw new FrmsException("所属分组不存在。");
		}
		discinfo.setSide(group.getSide());
		String discCode = StringUtils.genPoint(f.getDevCode(), discinfo.getSide(), model, index);
		if (null != discinfoEntityMapper.selectByDiscCode(discCode))
			throw new FrmsException("熔纤盘编码已存在。");
		discinfo.setDiscCode(discCode);
		return discinfoEntityMapper.insertSelective(discinfo);
	}

	/**
	 * 
	 * @Title: genDiscinfo
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param group
	 * @param @param facility
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午11:38:41
	 * @version V1.0
	 */
	@Override
	public int genDiscinfo(FiberdiscGroupEntity group, FacilityEntity facility) {
		int ret = 0;

		String side = group.getSide();
		Long devId = facility.getDevId();
		String devCode = facility.getDevCode();
		Integer discNum = group.getDiscNum();
		Integer portNum = group.getPortNum();

		for (int index = 1; index <= discNum; index++) {
			DiscinfoEntity discinfo = new DiscinfoEntity();
			discinfo.setDevId(devId);
			discinfo.setGroupId(group.getGroupId());
			discinfo.setSide(side);
			discinfo.setDiscCode(StringUtils.genPoint(devCode, side, index));
			discinfo.setDiscName(String.valueOf(index));
			discinfo.setPortNum(portNum);
			discinfo.setCreateTime(new Date());
			discinfo.setCreateUser(group.getCreateUser());
			ret = discinfoEntityMapper.insertSelective(discinfo);
		}
		return ret;
	}
}
  
    