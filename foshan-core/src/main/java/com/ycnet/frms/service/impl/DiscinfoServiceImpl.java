package com.ycnet.frms.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.FrmsException;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.DiscinfoZF;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper;
import com.ycnet.frms.mapper.DiscinfoMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscGroupMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.service.CableSectionDecService;
import com.ycnet.frms.service.CableSectionService;
import com.ycnet.frms.service.DiscinfoService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.service.LinesService;
import com.ycnet.frms.service.ResourceDataLogService;
import com.ycnet.frms.vo.Disc;
import com.ycnet.frms.vo.DiscWX;
import com.ycnet.frms.vo.PortWX;
import com.ycnet.frms.vo.mobile.DiscinfoMobile;

@Service("discinfoService")
@Transactional
public class DiscinfoServiceImpl implements DiscinfoService {

	@Resource(name = "discinfoService")
	private DiscinfoService discinfoService;

	@Resource(name = "resourceDataLogService")
	private ResourceDataLogService resourceDataLogService;

	@Resource(name = "discinfoMapper")
	private DiscinfoMapper discinfoMapper;

	@Resource(name = "fiberdiscGroupMapper")
	private FiberdiscGroupMapper fiberdiscGroupMapper;

	@Resource(name = "facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;

	@Resource(name = "fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;

	@Resource(name = "cableSectionDecService")
	private CableSectionDecService cableSectionDecService;
	
	@Resource(name = "cableSectionMapper")
	private CableSectionMapper cableSectionMapper;
	
	@Resource(name="linesService")
	private LinesService linesService;
	
	@Resource(name="linesMapper")
	private LinesMapper linesMapper;

	@Resource(name="deviceDiscinfoEntityMapper")
	private DeviceDiscinfoEntityMapper  deviceDiscinfoEntityMapper;
	
	@Override
	public Discinfo selectById(Long discId) {
		return discinfoMapper.selectByPrimaryKey(discId);
	}

	@Override
	public int insert(Discinfo discinfo, int index, String model) {

		Facility f = facilityMapper.selectByPrimaryKey(discinfo.getDevId());
		if (f == null)
			throw new FrmsException("所属设施不存在。");
		FiberdiscGroup group = fiberdiscGroupMapper.selectByPrimaryKey(discinfo.getGroupId());
		if (group == null) {
			throw new FrmsException("所属分组不存在。");
		}
		discinfo.setSide(group.getSide());
		if(index<=group.getDiscNum()) {
			throw new FrmsException("熔纤盘序号需大于最新盘序号("+group.getDiscNum()+")");
		}
		String discCode = StringUtils.genPoint(f.getDevCode(), discinfo.getSide(), model, index);
		if (null != discinfoMapper.selectByDiscCode(discCode))
			throw new FrmsException("熔纤盘编码已存在。");
		discinfo.setDiscCode(discCode);
		discinfo.setDiscName(String.valueOf(index));
		int req = discinfoMapper.insertSelective(discinfo);
		//如果熔纤盘添加成功
		if(req>0) {
			group.setDiscNum(group.getDiscNum()+1);
			group.setLastModifyTime(new Date());
			group.setLastModifyUser(discinfo.getCreateUser());
			fiberdiscGroupMapper.updateByPrimaryKeySelective(group);
		}
		return req;
	};

	@Override
	public int chgDiscinfo(Discinfo discinfo) {
		int ret = 0;
		Discinfo info = new Discinfo();
		info.setDiscId(discinfo.getDiscId());
		info.setDiscName(discinfo.getDiscName());
		info.setRemark(discinfo.getRemark());
		ret = discinfoMapper.updateByPrimaryKeySelective(info);
		/*
		 * info = discinfoMapper.selectByPrimaryKey(discinfo.getDiscId());
		 * FiberdiscConditionBean param = new FiberdiscConditionBean();
		 * param.setDevId(info.getDevId()); param.setSide(info.getSide());
		 * List<Fiberdisc> list = fiberdiscMapper.queryBydev(param); for(Fiberdisc
		 * disc:list) { disc.setRemark(discinfo.getRemark());
		 * fiberdiscMapper.updateByPrimaryKeySelective(disc); }
		 */
		return ret;
	}

	@Override
	public int genDiscinfo(FiberdiscGroup group, Facility facility) {
		int ret = 0;

		String side = group.getSide();
		Long devId = facility.getDevId();
		String devCode = facility.getDevCode();
		Integer discNum = group.getDiscNum();
		Integer portNum = group.getPortNum();

		for (int index = 1; index <= discNum; index++) {
			Discinfo discinfo = new Discinfo();
			discinfo.setDevId(devId);
			discinfo.setGroupId(group.getGroupId());
			discinfo.setSide(side);
			discinfo.setDiscCode(StringUtils.genPoint(devCode, side, index));
			discinfo.setDiscName(String.valueOf(index));
			discinfo.setPortNum(portNum);
			discinfo.setCreateTime(new Date());
			discinfo.setCreateUser(group.getCreateUser());
			ret = discinfoMapper.insertSelective(discinfo);
		}
		return ret;
	}

	@Override
	public List<Discinfo> selectByGroup(Long groupId) {
		return discinfoMapper.selectByGroup(groupId);
	}

	/**
	 * 盘占用情况 -- 添加尾纤悬空状态
	 * 修改:付林
	 * --
	 */
	@Override
	public DiscWX selectDiscWX(Long devId, String side, Long discId) {
		Discinfo discinfo = discinfoMapper.selectByPrimaryKey(discId);
		//DiscWX d = fiberdiscMapper.selectFiberdiscWX(devId, side, discId);
		
		DiscWX d = fiberdiscMapper.selectFiberdiscByDiscCode(discinfo.getDiscCode());
		DeviceDiscinfoEntity deviceDiscinfo = null;
		String[] strArray = null;
		if (d == null) {
			return d;
		}else {
			deviceDiscinfo = deviceDiscinfoEntityMapper.selectByPrimaryKey(d.getDiscId());
			if(deviceDiscinfo!=null && deviceDiscinfo.getLastReportData()!=null && !"".equals(deviceDiscinfo.getLastReportData())) {
				strArray = deviceDiscinfo.getLastReportData().split("");
			}
		}
		if (d.getPorts() !=null && d.getPorts().size()>0) {
			List<PortWX> list = d.getPorts();
			for (int i = 0; i < list.size(); i++) {
				//处理硬件检测的端口占用情况
				if(strArray!=null && strArray.length>0) {
					if(i<strArray.length) {
						list.get(i).setBluetooethOccupy(strArray[i]);
						if(!strArray[i].equals(list.get(i).getOccupy())) {
							list.get(i).setHardOccupy("0");
						}
					}
				}
				//处理端口对端端口占用情况
				List<Lines> lines = list.get(i).getLines();
				if(lines!=null && lines.size()>0) {
					for (int j = 0; j < lines.size(); j++) {
						// 是否成端
						if (lines.get(j).getLineType().equals("01")) {
							// 判断A还是Z
							if ( lines.get(j).getAcode()!=null && lines.get(j).getAcode().contains(d.getDiscCode())) {
								// 查询Z段状态
								String code=lines.get(j).getZcode();
								if(code != null) {
									Fiberdisc fiberdisc=fiberdiscMapper.queryOccupy(code);
									list.get(i).setOccupy(fiberdisc.getIsOccup());// 占用
								}
							} else if (lines.get(j).getZcode()!=null && lines.get(j).getZcode().contains(d.getDiscCode())) {
								// 查询A段状态
								String code=lines.get(j).getAcode();
								if(code != null) {
									Fiberdisc fiberdisc=fiberdiscMapper.queryOccupy(code);
									list.get(i).setOccupy(fiberdisc.getIsOccup());// 占用
								}
							}
						}
					}
				}
			}
		}
		return d;
	}

	/**
	 * fl修改刷新熔纤盘
	 */
	@Override
	public Disc selectDisc(Long devId, String side, Long discId) {
		//Disc d2 = fiberdiscMapper.selectFiberdisc(devId, side, discId);
		Discinfo discinfo=discinfoMapper.selectByPrimaryKey(discId);
		Disc d = fiberdiscMapper.pushFiberdiscByDiscCode(discinfo.getDiscCode());
		System.out.println();
		return d;
	}

	@Override
	public Disc selectDiscBySectId(Long devId, String side, Long discId, Long sectId) {
		Disc d = fiberdiscMapper.selectFiberdiscBysectId(devId, side, discId, sectId);
		return d;
	}

	// 导出数据库 刘沧海 2017/10/13
	@Override
	public List<DiscinfoZF> queryList(Long orgId, String areaCode) {
		return discinfoMapper.queryList(orgId, areaCode);
	}

	@Override
	public int genDiscinfoZG(FiberdiscGroup group, Facility facility) {
		// TODO Auto-generated method stub
		int ret = 0;

		String side = group.getSide();
		Long devId = facility.getDevId();
		String devCode = facility.getDevCode();
		Integer discNum = group.getDiscNum();
		Integer portNum = group.getPortNum();

		for (int index = 1; index <= discNum; index++) {
			Discinfo discinfo = new Discinfo();
			discinfo.setDevId(devId);
			discinfo.setGroupId(group.getGroupId());
			discinfo.setSide(side);
			discinfo.setDiscCode(StringUtils.genPoint(devCode, side, index));
			discinfo.setDiscName(String.valueOf(index));
			discinfo.setPortNum(portNum);
			ret = discinfoMapper.insertSelective(discinfo);
			ret = discinfoMapper.insertSelectiveZG(discinfo);
		}
		return ret;
	}

	/**
	 * 
	 * @Title: discinfoDelete
	 * @Description: 删除熔纤盘
	 * @param @param discId
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月5日 上午9:09:38
	 * @version V1.0
	 */
	@Override
	public int discinfoDelete(Long discId, String discCode, Users user) {
		int req = 0;
		DeviceDiscinfoEntity ddf = deviceDiscinfoEntityMapper.selectByPrimaryKey(discId);
		if(ddf != null){
			throw new FrmsException("请先解除熔纤盘下的绑定的端子控制器!");
		}
		if(discId == null) {
			throw new FrmsException("熔纤盘ID不能为空");
		}
		if(discCode == null || "".equals(discCode)) {
			throw new FrmsException("熔纤盘编码不能为空");
		}
		Discinfo disc = discinfoMapper.selectByPrimaryKey(discId);
		
		//根据discCode查看当前盘是否存在成端或跳纤,如果存在,则不可以删除
		List<Lines> liensList = linesService.selectByDiscCode(discCode+"-");
		if(liensList != null && liensList.size()>0) {
			if(disc.getDiscName()!=null && !"".equals(disc.getDiscName())) {
				throw new FrmsException("熔纤盘【"+disc.getDiscName()+"】存在成端或跳纤,不能被删除");
			}else {
				throw new FrmsException("熔纤盘存在成端或跳纤,不能被删除");
			}
		}
		
		//修改熔纤盘分组的盘数-1
		FiberdiscGroup group = fiberdiscGroupMapper.selectByDiscId(discId);//根据盘ID查询分组
		if(group!=null) {
			if(group.getDiscNum()>0) {
				group.setDiscNum(group.getDiscNum()-1);
			}
			group.setLastModifyTime(new Date());
			group.setLastModifyUser(user.getUserId());
			fiberdiscGroupMapper.updateByPrimaryKeySelective(group);
		}
		
		//删除熔纤盘
		req = discinfoMapper.deleteByPrimaryKey(discId);
		//根据discCode删除端子数据
		req = fiberdiscService.deleteByDiscCode(discCode);
		return req;
	}

	/**
	 * 生成直熔盘信息
	 */
	@Override
	public int saveDiscinfo(FiberdiscGroup group, Facility facility, Users user) {
		int ret = 0;

		String side = group.getSide();
		Long devId = facility.getDevId();
		String devCode = facility.getDevCode();
		Integer discNum = group.getDiscNum();
		Integer portNum = group.getPortNum();

		for (int index = 1; index <= discNum; index++) {
			Discinfo discinfo = new Discinfo();
			discinfo.setDevId(devId);
			discinfo.setGroupId(group.getGroupId());
			discinfo.setSide(side);
			discinfo.setDiscCode(StringUtils.genPoint(devCode, side, index));
			discinfo.setDiscName(String.valueOf(index));
			discinfo.setPortNum(portNum);
			discinfo.setCreateTime(new Date());
			discinfo.setCreateUser(user.getUserId());
			ret = discinfoMapper.insertSelective(discinfo);
		}
		return ret;
	}

	/**
	 * 删除直熔盘
	 */
	@Override
	public int deleteLnvestments(Long discId, String discCode, Users user) {
		int req = 0;
		req = discinfoMapper.deleteByPrimaryKey(discId);
		req = discinfoMapper.deleteByDiscCode(discCode.replace("ZRIN", "ZROUT"));
		
		//根据discCode删除端子数据
		req = fiberdiscService.deleteByDiscCode(discCode);
		req = fiberdiscService.deleteByDiscCode(discCode.replace("ZRIN", "ZROUT"));
		//根据discCode删除跳纤
		req = linesService.deleteLinesByLineTypeAndCode(discCode+"-");
//		req = linesService.deleteLinesByLineTypeAndCode(discCode.replace("ZRIN", "ZROUT"));
		//根据discCode查成端
		List<Lines> liensListIn = linesService.queryByDiscCode(discCode+"-");
		if(liensListIn.size()>0) {
			for (Lines lines : liensListIn) {
				if(lines.getAcode()!=null && !"".equals(lines.getAcode())) {
					if(lines.getAcode().indexOf(discCode+"-")!=-1) {
						lines.setAcode(null);
						lines.setLastModifyTime(new Date());
						lines.setLastModifyUser(user.getUserId());
						req = linesMapper.updateByPrimaryKey(lines);
					}
				}
				if(lines.getZcode()!=null && !"".equals(lines.getZcode())) {
					if(lines.getZcode().indexOf(discCode+"-")!=-1) {
						lines.setZcode(null);
						lines.setLastModifyTime(new Date());
						lines.setLastModifyUser(user.getUserId());
						req = linesMapper.updateByPrimaryKey(lines);
					}
				}
				
			}
		}
		
		List<Lines> liensListOUT = linesService.queryByDiscCode(discCode.replace("ZRIN", "ZROUT")+"-");
		if(liensListOUT.size()>0) {
			for (Lines lines : liensListOUT) {
				if(lines.getAcode()!=null && !"".equals(lines.getAcode())) {
					if(lines.getAcode().indexOf(discCode.replace("ZRIN", "ZROUT")+"-")!=-1) {
						lines.setAcode(null);
						lines.setLastModifyTime(new Date());
						lines.setLastModifyUser(user.getUserId());
						req = linesMapper.updateByPrimaryKey(lines);
					}
				}
				if(lines.getZcode()!=null && !"".equals(lines.getZcode())) {
					if(lines.getZcode().indexOf(discCode.replace("ZRIN", "ZROUT")+"-")!=-1) {
						lines.setZcode(null);
						lines.setLastModifyTime(new Date());
						lines.setLastModifyUser(user.getUserId());
						req = linesMapper.updateByPrimaryKey(lines);
					}
				}
			}
		}

//		if(req>0) {
//			FiberdiscGroup group = fiberdiscGroupMapper.selectByDiscId(discId);//根据盘ID查询分组
//			if(group.getDiscNum()>0) {
//				group.setDiscNum(group.getDiscNum()-1);
//			}
//			group.setLastModifyTime(new Date());
//			group.setLastModifyUser(user.getUserId());
//			fiberdiscGroupMapper.updateByPrimaryKeySelective(group);
//		}
//		if(req>0) {
//			FiberdiscGroup group = fiberdiscGroupMapper.selectByDiscId(discId);//根据盘ID查询分组
//			if(group.getDiscNum()>0) {
//				group.setDiscNum(group.getDiscNum()-1);
//			}
//			group.setLastModifyTime(new Date());
//			group.setLastModifyUser(user.getUserId());
//			fiberdiscGroupMapper.updateByPrimaryKeySelective(group);
//		}
		return req;
	}

	/**
	 * 修改单个熔纤盘
	 */
	@Override
	public int update(Discinfo discinfo) {
		return discinfoMapper.updateByPrimaryKeySelective(discinfo);
	}
	
	public static void main(String[] args) {
		String a = " ";
		for(String b : a.split("")) {
			System.err.println(b);
		}
		
	}

	/**
	 * 查询机柜中该设施可选点
	* @Title: queryDiscinfoByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param otherDevId
	* @param @return    入参
	* @return List<DiscinfoMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月27日 下午4:13:53 
	* @version V1.0
	 */
	@Override
	public List<DiscinfoMobile> queryDiscinfoByConditions(Long devId, Long otherDevId) {
		//查询光缆段ID集合
		List<CableSection> clist=cableSectionMapper.queryByDevId(otherDevId);
		List<Long> slist = new ArrayList<Long>();
		List<DiscinfoMobile> list = new ArrayList<DiscinfoMobile>();
		if (clist!=null && clist.size()>0) {//查询端子情况,分组信息等
			for (CableSection CableSection : clist) {
				slist.add(CableSection.getSectId());
			}
			 list=fiberdiscMapper.queryByConditions(devId,slist);
		}
		return list;
	}

}
