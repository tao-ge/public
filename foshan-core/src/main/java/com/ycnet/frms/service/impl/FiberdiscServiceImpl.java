package com.ycnet.frms.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.FrmsException;
import com.ycnet.core.LineType;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableSectionDec;
import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscZF;
import com.ycnet.frms.bean.LineImage;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.PortImg;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.CableSectionDecMapper;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.DiscinfoMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.LineImageMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.PortImgMapper;
import com.ycnet.frms.mapper.RouteMapper;
import com.ycnet.frms.service.DeviceDiscinfoService;
import com.ycnet.frms.service.DeviceService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.service.LinesService;
import com.ycnet.frms.util.StringUtil;
import com.ycnet.frms.vo.CableSectionChart;
import com.ycnet.frms.vo.DeviceDiscinfoEntityBean;
import com.ycnet.frms.vo.DifferentPortsBean;
import com.ycnet.frms.vo.Disc;
import com.ycnet.frms.vo.Disc1;
import com.ycnet.frms.vo.DiscGroup;
import com.ycnet.frms.vo.FiberdiscBean;
import com.ycnet.frms.vo.FiberdiscBean;
import com.ycnet.frms.vo.FiberdiscConditionBean;
import com.ycnet.frms.vo.FiberdiscInfo;
import com.ycnet.frms.vo.FiberdiscOpdBean;
import com.ycnet.frms.vo.Group;
import com.ycnet.frms.vo.Group1;
import com.ycnet.frms.vo.LinesBean;
import com.ycnet.frms.vo.Port;
import com.ycnet.frms.vo.Port1;
import com.ycnet.frms.vo.PortJumper;
import com.ycnet.frms.vo.PortJumper.Jumper;

@Service("fiberdiscService")
@Transactional
public class FiberdiscServiceImpl implements FiberdiscService {

	@Resource(name="fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;
	
	@Resource(name="linesMapper")
	private LinesMapper linesMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper cableSectionMapper;
	
	@Resource(name="discinfoMapper")
	private DiscinfoMapper discinfoMapper;

	@Resource(name="routeMapper")
	private RouteMapper routeMapper;
	@Resource(name="cableSectionDecMapper")
	private CableSectionDecMapper cableSectionDecMapper;
	@Resource(name="portImgMapper")
	private PortImgMapper portImgMapper;
	
	@Resource(name="lineImageMapper")
	private LineImageMapper lineImageMapper;
	
	@Resource(name="deviceDiscinfoService")
	private DeviceDiscinfoService deviceDiscinfoService;
	
	@Resource(name="deviceService")
	private DeviceService deviceService;
	
	@Override
	public int save(Fiberdisc fiberdisc) {
		if(fiberdisc.getFiberDiscId()!=null)
		{
			return fiberdiscMapper.updateByPrimaryKeySelective(fiberdisc);
		}
		else
		{
			return fiberdiscMapper.insertSelective(fiberdisc);
		}
		
	}

	@Override
	public Fiberdisc selectById(Long fiberDiscId) {
		return fiberdiscMapper.selectByPrimaryKey(fiberDiscId);
	}

	@Override
	public int delete(Long fiberDiscId) {
		return fiberdiscMapper.deleteByPrimaryKey(fiberDiscId);
	}

	@Override
	public List<Fiberdisc> selectByDevId(Long devId) {
		return fiberdiscMapper.selectByDevId(devId);
	}

	@Override
	public List<Fiberdisc> selectFiberdisc(FiberdiscConditionBean param) {
		
		return fiberdiscMapper.queryBydev(param);
	}

	@Override
	public List<FiberdiscInfo> queryDiscCodes(FiberdiscConditionBean param) {
		return fiberdiscMapper.queryDiscCodes(param);
	}

	@Override
	public List<Group> selectGroup(Long devId) {
		return fiberdiscMapper.selectGroup(devId);
	}
	@Override
	public List<Group> selectGroup1(Long devId) {
		List<Group> list = fiberdiscMapper.selectGroup1(devId);
		List<Facility> facilityList = facilityMapper.selectByPdevId(devId);
		//默认设施与中控器唯一绑定
		List<DeviceEntity> device = deviceService.selectListByDevId(devId);
		//Facility facility = facilityMapper.selectByPrimaryKey(devId);
		if(facilityList!=null && facilityList.size()>0) {
			for(Facility facility : facilityList) {
				if("30".equals(facility.getDevType()) || "31".equals(facility.getDevType()) || "32".equals(facility.getDevType())
						|| "32".equals(facility.getDevType())) {
					List<Group> listZDY = fiberdiscMapper.selectGroup1(facility.getDevId());
					for(Group group : listZDY) {
						group.setGroupName(facility.getDevName());
						group.setDevType(facility.getDevType());
					}
					list.addAll(listZDY);
				}else if( "06".equals(facility.getDevType())){
					List<Group> listFG = fiberdiscMapper.selectGroup1(facility.getDevId());
					listFG.get(0).setDevType(facility.getDevType());
					listFG.get(0).setGroupName(facility.getDevName());
					if(listFG.get(0).getDiscs()!=null && listFG.get(0).getDiscs().size()>0 && listFG.get(1).getDiscs()!=null && listFG.get(1).getDiscs().size()>0) {
						listFG.get(0).getDiscs().get(0).setDiscName(facility.getDevCode());
						listFG.get(1).getDiscs().get(0).setRow(2);
						listFG.get(0).getDiscs().addAll(listFG.get(1).getDiscs());
						if(listFG.get(1).getLines() != null) {
							listFG.get(0).getLines().addAll(listFG.get(1).getLines());
						}
					}
					listFG.remove(1);
					list.addAll(listFG);
				}
			}
		}
		if(device != null && device.size()>0) {
			if (!"".equals(device.get(0).getCodMac().trim())&& device.get(0).getCodMac() != null) {
				for (Group group : list) {
					group.setCodMac(device.get(0).getCodMac());
				}
			}
		}
		
		return list;
	}

	/**
	 * fl 添加备注
	 */
	@Override
	public PortJumper selectJumpers(Long devId, String port01) {
		PortJumper portJumper = new PortJumper();
		Fiberdisc disc = fiberdiscMapper.selectFiberdiscByCode(devId, port01);
		if(disc==null)
		{
			throw new FrmsException("获取端子信息错误。");
		}else {
				portJumper.setIsOccup(disc.getIsOccup());
				portJumper.setIsSheath(disc.getIsSheath());
				portJumper.setIsGlazed1(disc.getIsGlazed1());
				portJumper.setIsGlazed2(disc.getIsGlazed2());
	//			if(disc.getLightLen()!=null) {
	//				if(disc.getLightLen().longValue()%10==0) {
	//					portJumper.setLightLen(new BigDecimal(disc.getLightLen().longValue()));//光路长度
	//				}else {
	//					portJumper.setLightLen(disc.getLightLen().stripTrailingZeros());//光路长度
	//				}
	//			}
	//			if(disc.getLightWane()!=null) {
	//				if(disc.getLightLen().longValue()%10==0) {
	//					portJumper.setLightWane(new BigDecimal(disc.getLightWane().longValue()));//光路长度
	//				}else {
	//					portJumper.setLightWane(disc.getLightWane().stripTrailingZeros());//光衰
	//				}
	//				
	//			}
				
			if(disc.getLightLen()!=null) {
				portJumper.setLightLen(disc.getLightLen().stripTrailingZeros().toPlainString());//光路长度
			}else{
				portJumper.setLightLen("");//光路长度
			}
			if(disc.getLightWane()!=null) {
				portJumper.setLightWane(disc.getLightWane().stripTrailingZeros().toPlainString());//光衰
			}else {
				portJumper.setLightWane("");//光路长度
			}
				portJumper.setRemark(disc.getRemark());//备注
		}
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f!=null)
			portJumper.setDevName(f.getDevName());
		portJumper.setCode(port01);
		portJumper.setDevId(devId);
		
		CableSectionDec zgSectionDev=cableSectionDecMapper.queryZgSectionDev(port01);
		if(zgSectionDev!=null) {
			portJumper.setZgSectDec(zgSectionDev.getZgSectDec());
			portJumper.setZgRouteName(zgSectionDev.getZgRouteName());
			portJumper.setZgRouteText(zgSectionDev.getZgRouteText());
		}
		ArrayList<String> list=null;
		List<PortImg> imgList=portImgMapper.queryImgPaths(null,port01);
		if(imgList!=null) {
			list=new ArrayList<String>();
			for (int i = 0; i < imgList.size(); i++) {
				if(!"".equals(imgList.get(i).getImgUrl())) {
					list.add(imgList.get(i).getImgUrl());
				}
			}
			portJumper.setImgPaths(list);//查询图片路径
			list=null;
		}
		
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("lineType", "02");
		map.put("code", port01);
		List<LinesBean> lines = linesMapper.queryLineType(map);
		if(lines != null && lines.size()>0) {
			list=new ArrayList<String>();
			for (int i = 0; i < lines.size(); i++) {
				List<LineImage> limages=lineImageMapper.queryByLineId(lines.get(i).getLineId());
				if(limages != null && limages.size() > 0) {
					for (int j = 0; j < limages.size(); j++) {
						if(limages.get(j).getImgUrl() !=null && !"".equals(limages.get(j).getImgUrl())) {
							list.add(limages.get(j).getImgUrl());
						}
					}
				}
			}
			portJumper.setLineImgPaths(list);//跳纤图片路径
			list=null;
		}
		
		List<Lines> linesList = linesMapper.queryByPoint(port01);
		if(linesList!=null&&linesList.size()>2)
		{
			throw new FrmsException("端子跳纤数据错误，请联系管理员。");
		}
		List<Jumper> jumpers = new ArrayList<Jumper>();
		for(Lines line:linesList)
		{
			Long otherDevId;
			String otherCode;
			
			
			if("02".equals(line.getLineType())) {
				portJumper.setUnknownPointName(line.getUnknownPointName());
				portJumper.setSrvName(line.getSrvName());
			}
			
			otherDevId = (line.getAdevId() !=null && devId.longValue()==line.getAdevId().longValue() && port01.equals(line.getAcode()))?line.getZdevId():line.getAdevId();
			otherCode  = (line.getAdevId() !=null && devId.longValue()==line.getAdevId().longValue() && port01.equals(line.getAcode()))?line.getZcode():line.getAcode();
			Jumper jumper = null;
			if(!"".equals(otherCode)) {
				jumper = convert(line,otherDevId,otherCode);
			}
			
			if(jumper!=null) jumpers.add(jumper);
		}
		Collections.sort(jumpers);
		portJumper.setJumpers(jumpers);
		Route route =routeMapper.queryPort01(port01);
		if(route!=null) {
			//新增查询文本路由 刘沧海 2017/10/17
			portJumper.setRouteText(route.getRoutetext());
		}
		return portJumper;
	}
	
	private Jumper convert(Lines lines,Long devId,String code)
	{
		Jumper jumper = new Jumper();
		jumper.setLineId(lines.getLineId());
		jumper.setType(lines.getLineType());
		
		jumper.setCode(code);
		jumper.setDevId(devId);
		
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f!=null)
			jumper.setDevName(f.getDevName());
		
		if(LineType.toType(lines.getLineType())==LineType.FIBER)
		{
			CableSection sect = cableSectionMapper.selectByPrimaryKey(lines.getSectId());
			if(sect!=null)
				jumper.setSectName(sect.getSecName());
			jumper.setSectId(lines.getSectId());
			jumper.setFiberNo(lines.getFiberNo().intValue());
		}
		else if(LineType.toType(lines.getLineType())==LineType.VIRTUAL)
		{
			return null;
		}
		else
		{
			if(devId!=null && code !=null)
			{
				Fiberdisc fiberdisc = fiberdiscMapper.selectFiberdiscByCode(devId, code);
				if(fiberdisc ==null)
					throw new FrmsException("端子数据错误。");
				jumper.setRow(fiberdisc.getDiscRowNo().intValue());
				jumper.setCol(fiberdisc.getDiscColNo().intValue());
				jumper.setSide(fiberdisc.getSide());
			}else
			{
				jumper.setUnknownPointName(lines.getUnknownPointName());
			}
		}
		return jumper;
	}
	
	/**
	 * fl 修改根据devId 查设施 加非空验证
	 */
	@Override
	public List<Group> selectGroupWithLines(Long devId) {
		//List<Group> groupList = selectGroup(devId);
		List<Group> groupList = null;
		Facility facility =null;
		if (devId !=null) {
			 groupList = selectGroupXG(devId);//fl修改
			facility=facilityMapper.selectByPrimaryKey(devId);//fl查询devCode
		}
		if (groupList !=null && groupList.size()>0) {
			for(int i= 0 ;i<groupList.size();i++)
			{
				Group group = groupList.get(i);
				String side = group.getSide();
				
//				List<Lines> lines = linesMapper.selectJumperByGroup(devId,side);
				String code ="";
				if (facility !=null && facility.getDevCode() !=null && !"".equals(facility.getDevCode())) {
					code = facility.getDevCode()+"-"+side+"-";
				}
				List<Lines> lines = linesMapper.selectJumperByCode(code);//fl修改
				group.setLines(lines);
				
				groupList.set(i, group);
			}
		}
		return groupList;
	}

	

	


	/**
	 * 根据设施Id查盘分组
	* 
	* @Title: FiberdiscServiceImpl.java 
	* @Description: TODO 
	* @param @param devId
	* @param @return
	* @return List<Group>
	* @author fl
	* @date 2018年1月16日 上午9:17:26
	* @version V1.0
	 */
	private List<Group> selectGroupXG(Long devId) {
		return fiberdiscMapper.selectGroupXG(devId);
	}

	@Override
	public Fiberdisc selectByPort(String port01) {
		return fiberdiscMapper.selectByPort(port01);
	}

	/**
	 * 
	* @Title: fiberdiscGroup 
	* @Description: 根据光缆段ID和设施ID查询熔纤盘详细信息 
	* @param @param sectId
	* @param @param devId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年5月2日 上午11:53:56 
	* @version V1.0
	 */
	@Override  
	public List<DiscGroup> queryDiscGroup(Long sectId, Long devId) {
		List<DiscGroup> fd = fiberdiscMapper.queryDiscGroup(sectId,devId);
		return fd;
	}

	@Override
	public List<?> queryDiscSection(Long discId) {
		return fiberdiscMapper.queryDiscSection(discId);
	}
	
	@Override
	public List<Group> selectSectGroupByDevId(Long devId) {
	
		return fiberdiscMapper.selectGroup(devId);
	}

	//导出数据库 刘沧海 2017/10/13
	@Override
	public List<FiberdiscZF> queryList(Long orgId, String areaCode) {
		return fiberdiscMapper.queryList(orgId,areaCode);
	}

	@Override
	public int updateByPrimarykey(long devId) {
		Fiberdisc f=new Fiberdisc();
		f.setDevId(devId);
		return fiberdiscMapper.updateByPrimaryKeySelective(f);
	}
	/**
	 * 
	* @Title: updateIsOccurByDevid 
	* @Description: 更新fiberdisc中的isoccur 
	* @param @param adevId
	* @param @return    入参
	* @return int    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月1日 下午4:16:35 
	* @version V1.0
	 */
	@Override
	public int updateIsOccurByDevid(Long adevId,String isOccur) {
		return fiberdiscMapper.updateIsOccurByDevid(adevId,isOccur);
	}

	/**
	 * 保存端子信息
	* 
	* @Title: FiberdiscService.java 
	* @Description: TODO 
	* @param @param port01
	* @param @param isSheath
	* @param @param isGlazed1
	* @param @param isGlazed2
	* @param @return
	* @return int
	* @author fl
	* @date 2017年12月1日 下午3:15:40
	* @version V1.0
	 */
	@Override
	public int insertFiberdisc(Fiberdisc fiberdisc) {
		//根据port01查询主键fiber_disc_id
		Fiberdisc fiberDisc = fiberdiscMapper.queryId(fiberdisc.getPort01());
		fiberdisc.setFiberDiscId(fiberDisc.getFiberDiscId());
		//空的处理
		if(fiberdisc.getRemark()==null) {
			fiberdisc.setRemark("");
		}
		return fiberdiscMapper.updateByPrimaryKeySelective(fiberdisc);
	}

	/**
	 * 生成直熔盘端子数据-直熔盘专用
	 */
	@Override
	public int genFiberdisc(Users user, Long devId, String side, Integer fiberDiscNum,Integer portNum,Integer fromRowIndex,Integer fromColIndex,String  discModel) {
		int ret = 0;
		discModel=discModel==null||"".equals(discModel.replaceAll(" ",""))?null:discModel.replaceAll(" ","").toUpperCase();
		fromRowIndex = fromRowIndex==null?1:fromRowIndex;
		fromColIndex =fromColIndex==null?1:fromColIndex;
		if(fromColIndex.intValue()!=0&&fromColIndex.intValue()!=1)
		{
			throw new FrmsException("端口编码起始位置不正确！");
		}
		
		if(fiberDiscNum==null||(fiberDiscNum<=0))
			return ret;
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f==null)
			return ret;
		String code =f.getDevCode();
		Lines lines = null;
		for(long row =fromRowIndex;row <=fiberDiscNum +(fromRowIndex -1);row ++)
		{
			for(long col = 1;col<=portNum;col++)
			{
				Fiberdisc disc = new Fiberdisc();
				disc.setDevId(devId);
				disc.setDiscRowNo(row);
				disc.setDiscColNo(col);
				disc.setSide(side);
				disc.setIsOccup("1");
				disc.setDiscCode(StringUtils.genPoint(code,side,discModel,row));
				disc.setPort01(StringUtils.genPoint(code,side,discModel,row,fromColIndex==1?col:(col - 1)));
				disc.setCreateTime(new Date());
				disc.setCreateUser(user.getUserId());
				ret = fiberdiscMapper.insertSelective(disc);
				
				//生成跳纤
				lines = new Lines();
				lines.setLineType("02");
				lines.setAdevId(devId);
				lines.setAcode(disc.getPort01());
				lines.setZdevId(devId);
				lines.setZcode(disc.getPort01().replace("ZRIN", "ZROUT"));
				lines.setOrgId(user.getOrgId());
				lines.setCreateTime(new Date());
				lines.setCreateUser(user.getUserId());
				ret = linesMapper.insertSelective(lines);
			}
		}
		return ret;
	}

	/**
	 * 生成直熔盘端子数据-直熔盘专用
	 */
	@Override
	public int genFiberdiscOut(Users user, Long devId, String side, Integer fiberDiscNum, Integer portNum,
			Integer fromRowIndex, Integer fromColIndex, String discModel) {
		int ret = 0;
		discModel=discModel==null||"".equals(discModel.replaceAll(" ",""))?null:discModel.replaceAll(" ","").toUpperCase();
		fromRowIndex = fromRowIndex==null?1:fromRowIndex;
		fromColIndex =fromColIndex==null?1:fromColIndex;
		if(fromColIndex.intValue()!=0&&fromColIndex.intValue()!=1)
		{
			throw new FrmsException("端口编码起始位置不正确！");
		}
		
		if(fiberDiscNum==null||(fiberDiscNum<=0))
			return ret;
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f==null)
			return ret;
		String code =f.getDevCode();
		Lines lines = null;
		for(long row =fromRowIndex;row <=fiberDiscNum +(fromRowIndex -1);row ++)
		{
			for(long col = 1;col<=portNum;col++)
			{
				Fiberdisc disc = new Fiberdisc();
				disc.setDevId(devId);
				disc.setDiscRowNo(row);
				disc.setDiscColNo(col);
				disc.setSide(side);
				disc.setIsOccup("1");
				disc.setDiscCode(StringUtils.genPoint(code,side,discModel,row));
				disc.setPort01(StringUtils.genPoint(code,side,discModel,row,fromColIndex==1?col:(col - 1)));
				disc.setCreateTime(new Date());
				disc.setCreateUser(user.getUserId());
				ret = fiberdiscMapper.insertSelective(disc);
			}
		}
		return ret;
	}

	/**
	 * 根据discCode删除端子数据
	 */
	@Override
	public int deleteByDiscCode(String discCode) {
		return fiberdiscMapper.deleteByDiscCode(discCode);
	}

	/**
	 * 更改端子表中的光路、光衰数据，只保留数字
	 * fl
	 */
	@Override
	public int updateTowDates() {
		int num=0;
		List<Fiberdisc> fiberdiscList=fiberdiscMapper.queryByTowDates();
		for (int i = 0; i < fiberdiscList.size(); i++) {
			Fiberdisc fiberdisc=fiberdiscList.get(i);
			/**
			 * zhouyu 1/4 lightlen,lightwane改类型 
			 */
			
			/*if(fiberdisc.getLightLen()!=null) {
				if(fiberdisc.getLightLen().indexOf("k")!=-1) {
					fiberdisc.setLightLen(fiberdisc.getLightLen().substring(0, fiberdisc.getLightLen().indexOf("k")));
				}
			}
			if(fiberdisc.getLightWane()!=null) {
				if(fiberdisc.getLightWane().indexOf("d")!=-1) {
					fiberdisc.setLightWane(fiberdisc.getLightWane().substring(0, fiberdisc.getLightWane().indexOf("d")));
				}
			}*/
//			char[] shu = fiberdisc.getLightLen().toCharArray();
//			for (int j = 0; j < shu.length; j++) {
//				String LightLen = "";
//				if (String.valueOf(shu[i]).matches("^[0-9]+(.[0-9]+)?$")) {
//					LightLen +=String.valueOf(shu[i]);
//					fiberdisc.setLightLen(LightLen);
//				}
//			}
//			char[] shu1 = fiberdisc.getLightWane().toCharArray();
//			for (int j = 0; j < shu1.length; j++) {
//				String LightWane = "";
//				if (String.valueOf(shu1[i]).matches("^[0-9]+(.[0-9]+)?$")) {
//					LightWane +=String.valueOf(shu1[i]);
//					fiberdisc.setLightWane(LightWane);
//				}
//			}
			num=fiberdiscMapper.updateByPrimaryKeySelective(fiberdisc);
		}
		
		return num;
	}
	
	@Override
	public int updateThreeDates(Long devId,String side,String newCode) {
		int num=0;
		List<Fiberdisc> fiberdiscList=fiberdiscMapper.queryByThings(devId,side);
		List<Discinfo> discinfoList=discinfoMapper.queryByDevIdAndSide(devId,side);
		if (fiberdiscList!=null && fiberdiscList.size()>0) {
			for (int i = 0; i < fiberdiscList.size(); i++) {
				Fiberdisc fiberdisc=fiberdiscList.get(i);
				fiberdisc.setPort01(fiberdisc.getPort01().replace(fiberdisc.getSide(), newCode));
				fiberdisc.setDiscCode(fiberdisc.getDiscCode().replace(fiberdisc.getSide(), newCode));
				fiberdisc.setSide(newCode);
				num=fiberdiscMapper.updateByPrimaryKeySelective(fiberdisc);
			}
		}
		if (discinfoList!=null && discinfoList.size()>0) {
			for (int i = 0; i < discinfoList.size(); i++) {
				Discinfo discinfo=discinfoList.get(i);
				discinfo.setDiscCode(discinfo.getDiscCode().replace(discinfo.getSide(), newCode));
				discinfo.setSide(newCode);
				num=discinfoMapper.updateByPrimaryKeySelective(discinfo);
			}
		}
		return num;
	}

	/**
	 * 
	 *  只删除直熔的纤芯
	* @Title: FiberdiscService.java 
	* @Description: TODO 
	* @param @param discCode
	* @param @param users
	* @param @return
	* @return int
	* @author fl
	* @date 2017年12月29日 上午10:04:37
	* @version V1.0
	 */
	 
	@Override
	public int deleteZRFiberdiscs(String discCode, Users user) {
		int num=0;
		List<Lines> linesList=linesMapper.queryByDiscCode(discCode);//in
		List<Lines> linesListout=linesMapper.queryByDiscCode(discCode.replace("ZRIN", "ZROUT"));//out
		if (linesList !=null && linesList.size()>0) {
			for (int i = 0; i < linesList.size(); i++) {
				Lines lines = linesList.get(i);
				if (lines.getAcode()!=null && !"".equals(lines.getAcode())) {//A端
					if (lines.getAcode().contains("ZRIN")) {
						lines.setAcode(null);
						lines.setLastModifyTime(new Date());
						lines.setLastModifyUser(user.getUserId());
						num += linesMapper.updateByPrimaryKey(lines);
					}
				}
				if(lines.getZcode()!=null && !"".equals(lines.getZcode())){//Z端
					if (lines.getZcode().contains("ZRIN")) {
						lines.setZcode(null);
						lines.setLastModifyTime(new Date());
						lines.setLastModifyUser(user.getUserId());
						num += linesMapper.updateByPrimaryKey(lines);
					}
				}
			}
		}
		if (linesListout!=null && linesListout.size()>0) {
			for (int i = 0; i < linesListout.size(); i++) {
				Lines lines = linesListout.get(i);
				if (lines.getAcode() !=null && !"".equals(lines.getAcode())) {
					if (lines.getAcode().contains("ZROUT")) {
						lines.setAcode(null);
						lines.setLastModifyTime(new Date());
						lines.setLastModifyUser(user.getUserId());
						num += linesMapper.updateByPrimaryKey(lines);
					}
				}
				if (lines.getZcode()!=null && !"".equals(lines.getZcode())) {
					if (lines.getZcode().contains("ZROUT")) {
						lines.setZcode(null);
						lines.setLastModifyTime(new Date());
						lines.setLastModifyUser(user.getUserId());
						num += linesMapper.updateByPrimaryKey(lines);
					}
				}
			}
		}
		//删除端子表中的数据
		num += fiberdiscMapper.updateByDiscCode(discCode);
		num += fiberdiscMapper.updateByDiscCode(discCode.replace("ZRIN", "ZROUT"));
		return num;
	}
	
	
	public static void main(String[] args) {
//		if(disc.getLightLen()!=null) {
//			if(disc.getLightLen().longValue()%10==0) {
//				portJumper.setLightLen(new BigDecimal(disc.getLightLen().longValue()));//光路长度
//			}else {
//				portJumper.setLightLen(disc.getLightLen().stripTrailingZeros());//光路长度
//			}
//		}
//		if(disc.getLightWane()!=null) {
//			if(disc.getLightLen().longValue()%10==0) {
//				portJumper.setLightWane(new BigDecimal(disc.getLightWane().longValue()));//光路长度
//			}else {
//				portJumper.setLightWane(disc.getLightWane().stripTrailingZeros());//光衰
//			}
//			
//		}
		System.out.println(new BigDecimal("110.0").stripTrailingZeros().toPlainString());
	}

	/**
	 * 查询端子数据
	* 
	* @Title: RouteController.java 
	* @Description: TODO 
	* @param @param port
	* @param @param routeText
	* @param @return
	* @return String
	* @author fl
	* @date 2018年1月11日 下午2:06:41
	* @version V1.0
	 */
	@Override
	public FiberdiscBean queryfiberdiscByPort(String port) {
		return fiberdiscMapper.queryfiberdiscByPort(port);
	}

	/**
	 * 
	 * @Title: queryByDiscCode
	 * @Description: 根据discCode查询端子数据
	 * @param @param discCode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月23日 下午2:58:41
	 * @version V1.0
	 */
	@Override
	public List<Fiberdisc> queryByDiscCode(String discCode) {
		return fiberdiscMapper.queryByDiscCode(discCode);
	}

	/**
	 * 
	* @Title: cableSectionChart 
	* @Description: 光缆段成端详情 
	* @param @param model
	* @param @param devId
	* @param @param sectId
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年4月18日 下午3:20:21 
	* @version V1.0
	 */
	@Override
	public CableSectionChart cableSectionChart(Long devId, Long sectId,Integer state) {
		CableSectionChart chart=null;
		chart=fiberdiscMapper.cableSectionChart(devId,sectId);
		if(chart!=null) {
			if(chart.getGroupList()!=null && chart.getGroupList().size()>0) {
				for(Group g:chart.getGroupList()) {
					if(g.getDiscs()!=null && g.getDiscs().size()>0) {
						List<Long> chengduan=null;
						for(Disc d :g.getDiscs()) {
							chengduan=new ArrayList<Long>();
							if(d.getPorts()!=null && d.getPorts().size()>0) {
								for(Port p: d.getPorts()) {
									if(p.getFiberNum()!=null) {
										chengduan.add(p.getFiberNum());
									}
								}
								d.setDiscFiberNum(StringUtil.sort(chengduan)!=null?StringUtil.sort(chengduan):"");
							}else {
								d.setDiscFiberNum("");
							}
						}
					}
				}
				chart.setNotoEnd(queryNotoEnd(devId,sectId,state));
			}
		}else {
			chart=new CableSectionChart();
			chart.setNotoEnd(queryNotoEnd(devId,sectId,state));
		}
		return chart;
	}
	/**
	 * 
	* @Title: queryNotoEnd 
	* @Description: 处理查询未成端的纤芯数 
	* @param @param devId
	* @param @param sectId
	* @param @param state
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年4月19日 下午5:02:19 
	* @version V1.0
	 */
	public String queryNotoEnd(Long devId,Long sectId,Integer state) {
		String str="";
		if(state==1) {
			List<Lines> lineList=linesMapper.queryNotoEndA(devId,sectId);
			if(lineList!=null && lineList.size()>0) {
				List<Long> weichengduan=new ArrayList<Long>();
				for(Lines line:lineList) {
					weichengduan.add(line.getFiberNo());
				}
				if(weichengduan!=null && weichengduan.size()>0) {//未成端
					str=StringUtil.sort(weichengduan)!=null?StringUtil.sort(weichengduan):"";
				}
				weichengduan=null;
			}
		}else {
			List<Lines> lineList=linesMapper.queryNotoEndZ(devId,sectId);
			if(lineList!=null && lineList.size()>0) {
				List<Long> weichengduan=new ArrayList<Long>();
				for(Lines line:lineList) {
					weichengduan.add(line.getFiberNo());
				}
				if(weichengduan!=null && weichengduan.size()>0) {//未成端
					str=StringUtil.sort(weichengduan)!=null?StringUtil.sort(weichengduan):"";
				}
				weichengduan=null;
			}
		}
		return str;
	}

	/**
	 * 查询端子情况
	* @Title: queryDiscGroupDiscPorts 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return CableSectionChart    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月19日 下午2:43:21 
	* @version V1.0
	 */
	@Override
	public List<Group> queryDiscGroupDiscPorts(Long devId) {
		List<Group> list=fiberdiscMapper.queryDiscGroupDiscPorts(devId);
		if (list.size()>0 && list!=null) {
			for (int i = 0; i < list.size(); i++) {
				Group group = list.get(i);
				List<Disc> discList = group.getDiscs();
				if (discList.size()>0 &&discList!=null) {
					for (int j = 0; j < discList.size(); j++) {
						Disc disc = discList.get(j);
						List<Port> portList = disc.getPorts();//设施端子状态
						//查询硬件中的端子
						DeviceDiscinfoEntity deviceDiscinfoEntity=deviceDiscinfoService.queryDeviceDisinfoByDisId(disc.getDiscId());
						List<String> lastReportDataList = new ArrayList<String>();//硬件端子状态
						if ( deviceDiscinfoEntity !=null && deviceDiscinfoEntity.getLastReportData() !=null &&!"".equals( deviceDiscinfoEntity.getLastReportData())) {
							char[] lastReportDatas = deviceDiscinfoEntity.getLastReportData().toCharArray();
							for (int s = 0; s < lastReportDatas.length; s++) {
								char c = lastReportDatas[s];
								lastReportDataList.add(String.valueOf(c));
							}
							//-----------------------------------------比较
							if (lastReportDataList.size()>0 &&lastReportDataList!=null && portList.size()>0 && portList!=null) {
								Integer portNum = 0;
								if(lastReportDataList.size()>portList.size()) {
									portNum = portList.size();
								}else {
									portNum = lastReportDataList.size();
								}
								for (int k = 0; k < portNum; k++) {
									Port port = portList.get(k);
									String occup = lastReportDataList.get(k);
									if (!port.getIsOccup().equals(occup)) {
										//设备是0
										if (occup.equals("0")) {
											port.setOccup("3");
										}
										if (occup.equals("1")) {
											port.setOccup("4");
										}
									}else {
										port.setOccup(occup);
									}
								}
								//补差值
								if (lastReportDataList.size()<portList.size()) {
									for (int k = lastReportDataList.size(); k < portList.size(); k++) {
										portList.get(k).setOccup("2");
									}
								}
							}
						}else {
							for (int k = 0; k < portList.size(); k++) {
								Port port = portList.get(k);
								port.setOccup(port.getIsOccup());
							}
						}
					}
				}
			}
		}
		 return list;
	}

	/**
	 * 
	* @Title: queryFiberdiscGroupWithSection 
	* @Description: 根据光缆段ID和设施ID查询熔纤盘详细信息  
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年5月3日 下午4:53:07 
	* @version V1.0
	 */
	@Override
	public List<DiscGroup> queryFiberdiscGroupWithSection(Long sectId, Long devId) {
		return fiberdiscMapper.queryFiberdiscGroupWithSection(sectId,devId);
	}

	/**
	 * 
	 * @Title: queryFiberdiscOccupCount
	 * @Description: 根据devId查询设施下端子占用情况
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月10日 下午1:52:54
	 * @version V1.0
	 */
	@Override
	public FiberdiscBean queryFiberdiscOccupCount(Long devId) {
		return fiberdiscMapper.queryFiberdiscOccupCount(devId);
	}

	/**
	 * 端子差异情况
	* @Title: queryPortOccupyCodition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<Group>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月25日 下午4:19:38 
	* @version V1.0
	 */
	@Override
	public List<Group> queryPortOccupyCodition(Long devId) {
		List<Group> list=fiberdiscMapper.queryPortOccupyCodition(devId);
		if (list.size()>0 && list!=null) {
			for (int i = 0; i < list.size(); i++) {
				Group group = list.get(i);
				List<Disc> discList = group.getDiscs();
				if (discList.size()>0 &&discList!=null) {
					for (int j = 0; j < discList.size(); j++) {
						Disc disc = discList.get(j);
						List<Port> portList = disc.getPorts();//设施端子状态
						//查询硬件中的端子
						DeviceDiscinfoEntity deviceDiscinfoEntity=deviceDiscinfoService.queryDeviceDisinfoByDisId(disc.getDiscId());
						List<String> lastReportDataList = new ArrayList<String>();//硬件端子状态
						if ( deviceDiscinfoEntity !=null && deviceDiscinfoEntity.getLastReportData() !=null &&!"".equals( deviceDiscinfoEntity.getLastReportData())) {
							char[] lastReportDatas = deviceDiscinfoEntity.getLastReportData().toCharArray();
							for (int s = 0; s < lastReportDatas.length; s++) {
								char c = lastReportDatas[s];
								lastReportDataList.add(String.valueOf(c));
							}
							//-----------------------------------------比较
							if (lastReportDataList.size()>0 &&lastReportDataList!=null && portList.size()>0 && portList!=null) {
								Integer portNum = 0;
								if(lastReportDataList.size()>portList.size()) {
									portNum = portList.size();
								}else {
									portNum = lastReportDataList.size();
								}
								for (int k = 0; k < portNum; k++) {
									Port port = portList.get(k);
									String occup = lastReportDataList.get(k);
									if (!port.getIsOccup().equals(occup)) {
										//设备是0
										if (occup.equals("0")) {
											port.setOccup("3");
										}
										if (occup.equals("1")) {
											port.setOccup("4");
										}
									}else {
										port.setOccup(occup);
									}
								}
								//补差值
								if (lastReportDataList.size()<portList.size()) {
									for (int k = lastReportDataList.size(); k < portList.size(); k++) {
										portList.get(k).setOccup("2");
									}
								}
							}
						}else {
							for (int k = 0; k < portList.size(); k++) {
								Port port = portList.get(k);
								port.setOccup(port.getIsOccup());
							}
						}
					}
				}
			}
		}
		 return list;
	}

	/**
	 * 查询端子详情
	* @Title: queryfiberdisc 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @param port01
	* @param @return    入参
	* @return FiberdiscOpdBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月22日 下午3:10:53 
	* @version V1.0
	 */
	@Override
	public FiberdiscOpdBean queryfiberdisc(Long fiberDiscId, String port01) {
		FiberdiscOpdBean fiberdiscOpdBean= new FiberdiscOpdBean();
		fiberdiscOpdBean.setPort01(port01);
		String devName =fiberdiscMapper.queryDevNameByDiscId(fiberDiscId);//本端设施名称
		if (devName !=null && !"".equals(devName)) {
			fiberdiscOpdBean.setAdevName(devName);
		}
		Lines lines = linesMapper.queryOppositeByPort01(port01);//对端port01 设施ID 描述 // 用acode,adev_id接
		if (lines !=null && lines.getAdevId()!=null && lines.getAcode()!=null && !"".equals(lines.getAcode())) {
			fiberdiscOpdBean.setCode(lines.getAcode());
			if (lines.getSrvName()!=null && !"".equals(lines.getSrvName())) {
				fiberdiscOpdBean.setSrvName(lines.getSrvName());
			}
			Facility f = facilityMapper.selectByPrimaryKey(lines.getAdevId());//对端设施名称
			if (f!=null && f.getDevName()!=null && !"".equals(f.getDevName())) {
				fiberdiscOpdBean.setZdevName(f.getDevName());
			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("zcode", port01);
			map.put("acode", lines.getAcode());
			Route route =routeMapper.queryRoutext(map);//文本路由
			if (route!=null && route.getRoutetext()!=null && !"".equals(route.getRoutetext())) {
				fiberdiscOpdBean.setRouteText(route.getRoutetext());;
			}
		}
		List<String> urlList = new ArrayList<String>();
		List<PortImg> list = portImgMapper.queryImgPaths(fiberdiscMapper.selectByPrimaryKey(fiberDiscId).getDevId(), port01);
		if (list!=null && list.size()>0) {
			for (PortImg portImg : list) {
				if (portImg!=null && portImg.getImgUrl()!=null && !"".equals(portImg.getImgUrl())) {
					urlList.add(portImg.getImgUrl());
				}
			}
		}
		if (urlList!=null && urlList.size()>0) {
			fiberdiscOpdBean.setImgsUrls(urlList);
		}
		
		return fiberdiscOpdBean;
	}

	/**
	 * 
	 * @Title: queryOccupyConditionByOrgId
	 * @Description: 根据orgId,查询端子占用
	 * @param @param orgId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午10:44:08
	 * @version V1.0
	 */
	@Override
	public List<DifferentPortsBean> queryOccupyConditionByOrgId(Long orgId) {
		return fiberdiscMapper.queryOccupyConditionByOrgId(orgId);
	}

	/**
	 * 
	 * @Title: queryOccupyConditionByArea
	 * @Description: 根据orgId,查询端子占用
	 * @param @param orgId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午11:57:39
	 * @version V1.0
	 */
	@Override
	public List<DifferentPortsBean> queryOccupyConditionByArea(Long orgId) {
		return fiberdiscMapper.queryOccupyConditionByArea(orgId);
	}
}
