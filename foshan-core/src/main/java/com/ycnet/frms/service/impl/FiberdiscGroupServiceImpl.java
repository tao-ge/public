package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.FrmsException;
import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.FiberdiscGroupZF;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Twinfiber;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderDesign;
import com.ycnet.frms.bean.WorkorderLightline;
import com.ycnet.frms.bean.WorkorderRoutes;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper;
import com.ycnet.frms.mapper.DiscinfoMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscGroupMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.TwinfiberMapper;
import com.ycnet.frms.mapper.WorkorderDesignMapper;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper;
import com.ycnet.frms.mapper.WorkorderImplePlansImgMapper;
import com.ycnet.frms.mapper.WorkorderImplePlansMapper;
import com.ycnet.frms.mapper.WorkorderLightlineMapper;
import com.ycnet.frms.mapper.WorkorderRoutesMapper;
import com.ycnet.frms.service.DeviceService;
import com.ycnet.frms.service.DiscinfoService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscGroupService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.vo.FactilityFiberDiscGroupBean;
import com.ycnet.frms.vo.FiberdiscGroupBean;
import com.ycnet.frms.vo.GroupConditionBean;
import com.ycnet.frms.vo.WorkorderFiberdiscabindBean;
import com.ycnet.frms.vo.WorkorderFiberdiscabindImgBean;
import com.ycnet.frms.vo.WorkorderImplePlansImgBean;

@Service("fiberdiscGroupService")
@Transactional
public class FiberdiscGroupServiceImpl  implements FiberdiscGroupService{

	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper cableSectionMapper;
	
	@Resource(name="fiberdiscGroupMapper")
	private FiberdiscGroupMapper fiberdiscGroupMapper;
	
	@Resource(name="discinfoService")
	private DiscinfoService discinfoService;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;
	
	@Resource(name="fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;
	
	@Resource(name="discinfoMapper")
	private DiscinfoMapper discinfoMapper;
	
	@Resource(name="linesMapper")
	private LinesMapper linesMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="fiberdiscGroupService")
	private FiberdiscGroupService groupService;
	
	@Resource(name="twinfiberMapper")
	private TwinfiberMapper twinfiberMapper;
	
	@Resource(name="workorderFiberdiscabindAMapper")
	private WorkorderFiberdiscabindAMapper workorderFiberdiscabindAMapper;
	
	@Resource(name="workorderFiberdiscabindZMapper")
	private WorkorderFiberdiscabindZMapper workorderFiberdiscabindZMapper;
	
	@Resource(name="workorderImplePlansImgMapper")
	private WorkorderImplePlansImgMapper workorderImplePlansImgMapper;
	
	@Resource(name="workorderImplePlansMapper")
	private WorkorderImplePlansMapper workorderImplePlansMapper;
	
	@Resource(name="deviceService")
	private DeviceService deviceService;
	
	@Resource(name="workorderRoutesMapper")
	private WorkorderRoutesMapper workorderRoutesMapper;
	
	@Resource(name="workorderDesignMapper")
	private WorkorderDesignMapper workorderDesignMapper;
	
	@Resource(name="workorderLightlineMapper")
	private WorkorderLightlineMapper workorderLightlineMapper;
	
	@Resource(name="deviceDiscinfoEntityMapper")
	private DeviceDiscinfoEntityMapper deviceDiscinfoEntityMapper;
	
	@Override
	public FiberdiscGroup selectById(Long groupId) {
		return fiberdiscGroupMapper.selectByPrimaryKey(groupId);
	}
	
	@Override
	public int insert(FiberdiscGroup fiberdiscGroup) {
		//强制变成大写
		fiberdiscGroup.setSide(fiberdiscGroup.getSide().toUpperCase());
		Facility f = facilityService.selectById(fiberdiscGroup.getDevId());
		if(f==null)
			throw new FrmsException("获取设施出现异常。");
		GroupConditionBean bean = new GroupConditionBean();
		bean.setDevId(fiberdiscGroup.getDevId());
		if(fiberdiscGroup.getSide().toUpperCase().indexOf("FG")!=-1) {
			throw new FrmsException("分组编码"+fiberdiscGroup.getSide()+"中不能包含FG");
		}
		if(fiberdiscGroup.getSide().toUpperCase().indexOf("ZR")!=-1) {
			throw new FrmsException("分组编码"+fiberdiscGroup.getSide()+"中不能包含ZR");
		}
		bean.setSide(fiberdiscGroup.getSide());
		List<?> l = fiberdiscGroupMapper.queryGroupByDev(bean);
		if(l!=null&l.size()>0)
		{
			throw new FrmsException("分组编码已存在。");
		}
		
		return fiberdiscGroupMapper.insertSelective(fiberdiscGroup);
		
	}

	
	@Override
	public List<FiberdiscGroupBean> selectDiscinfoByGroup(Long devId,
			Long groupId) {
		return fiberdiscGroupMapper.selectDiscinfoByGroup(devId, groupId);
	}

	@Override
	public FiberdiscGroupBean selectDiscinfoByGroup(Long groupId) {
		return fiberdiscGroupMapper.selectDiscinfoByGroup(groupId);
	}

	//删除熔纤盘分组
	@Override
	public int delete(Long groupId) {
		int r=0;
		FiberdiscGroup bean = fiberdiscGroupMapper.selectByPrimaryKey(groupId);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("side", bean.getSide());
		map.put("devId", bean.getDevId());
		fiberdiscMapper.deleteFiberDisc(map);
		
		discinfoMapper.deleteDiscInfo(groupId);
		
		Facility facility = facilityMapper.queryByDevId(bean.getDevId());
		String str="";
		str=facility.getDevCode()+"-"+bean.getSide();
		linesMapper.deleteLines(str);
		
		r=fiberdiscGroupMapper.deleteByPrimaryKey(groupId);
		return r;
	}
	//修改熔纤盘分组
	@Override
	public int update(Long groupId, String groupName) {
//		int r=0;
//		String devcode="";
//		
//		FiberdiscGroup bean = fiberdiscGroupMapper.selectByPrimaryKey(groupId);
//		
//		List<Discinfo> discinfo = discinfoMapper.selectByGroup(groupId);
//		if(discinfo!=null){
//			for (int i = 0; i < discinfo.size(); i++) {
//					String discCode = discinfo.get(i).getDiscCode();
//					String[] arr = discCode.split("-");
//						devcode=arr[0];
//						arr[1]=side;
//						discinfo.get(i).setDiscCode(arr[0]+"-"+arr[1]+"-"+arr[2]);
//						discinfo.get(i).setSide(side);
//						discinfoMapper.updateByPrimaryKeySelective(discinfo.get(i));
//					
////					}
//			}
//		}
//		
////		Facility facility = facilityMapper.queryByDevId(bean.getDevId());
////		if(facility!=null){
//			String str="";
//			str=devcode+"-"+bean.getSide();
//			//A端
//			if(linesMapper.queryLinesSideAcode(str)!=null){
//				List<Lines> linesA=linesMapper.queryLinesSideAcode(str);
//				if(linesA!=null ){
//					for (int i = 0; i < linesA.size(); i++) {
//						String acode = linesA.get(i).getAcode();
//						String[] arr=acode.split("-");
//							arr[1]=side;
//							linesA.get(i).setAcode(arr[0]+"-"+arr[1]+"-"+arr[2]+"-"+arr[3]);
//							linesMapper.updateByPrimaryKeySelective(linesA.get(i));
////						}
//						
//					}
//				}
//			}
//			//Z端
//			if(linesMapper.queryLinesSideZcode(str)!=null){
//				List<Lines> linesZ=linesMapper.queryLinesSideZcode(str);
//				if(linesZ!=null){
//					for (int i = 0; i < linesZ.size(); i++) {
//						String zcode = linesZ.get(i).getZcode();
//						String[] arr=zcode.split("-");
//							arr[1]=side;
//							linesZ.get(i).setZcode(arr[0]+"-"+arr[1]+"-"+arr[2]+"-"+arr[3]);
//							linesMapper.updateByPrimaryKeySelective(linesZ.get(i));
//					}
//				}
//			}
////		}
//		
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("side", bean.getSide());
//		map.put("devId", bean.getDevId());
//		List<Fiberdisc> fiberdisc=fiberdiscMapper.queryFiberDisc(map);
//		if(fiberdisc!=null){
//			for (int i = 0; i < fiberdisc.size(); i++) {
//				fiberdisc.get(i).setSide(side);
//				
//				String port01 = fiberdisc.get(i).getPort01();
//				String[] arr=port01.split("-");
//					arr[1]=side;
//					fiberdisc.get(i).setPort01(arr[0]+"-"+arr[1]+"-"+arr[2]+"-"+arr[3]);
//				
//				String disccode = fiberdisc.get(i).getDiscCode();
//				String[] arr1=disccode.split("-");
//					arr1[1]=side;
//					fiberdisc.get(i).setDiscCode(arr1[0]+"-"+arr1[1]+"-"+arr1[2]);
//					fiberdiscMapper.updateByPrimaryKeySelective(fiberdisc.get(i));
//			}
//		}
//		
//		r=fiberdiscGroupMapper.updateDiscGroup(groupId,side);
		int r=0;
		r=fiberdiscGroupMapper.updateDiscGroupName(groupId,groupName);
		return r;
	}
	@Override
	public int updateGroup(FiberdiscGroup discGroup) {
		return fiberdiscGroupMapper.updateByPrimaryKeySelective(discGroup);
	}

	/**
	 * 设施分组信息 --刘沧海 -- 2017-9-26
	 */
	@Override
	public List<FiberdiscGroup> queryByDevId(Long devId) {
		return fiberdiscGroupMapper.queryByDevId(devId);
	}

	//导出数据库 刘沧海 2017/10/13
	@Override
	public List<FiberdiscGroupZF> queryList(Long orgId, String areaCode) {
		return fiberdiscGroupMapper.queryList(orgId,areaCode);
	}

	@Override
	public int insertZG(FiberdiscGroup fiberdiscGroup) {
		//强制变成大写
			fiberdiscGroup.setSide(fiberdiscGroup.getSide().toUpperCase());
			Facility f = facilityService.selectByIdZG(fiberdiscGroup.getDevId());
			if(f==null)
				throw new FrmsException("获取设施出现异常。");			
			return fiberdiscGroupMapper.insertSelectiveZG(fiberdiscGroup);
	}
	/**
	 * 
	* @Title: saveGroupDescByGroupId 
	* @Description: 根据熔纤盘groupId修改熔纤盘描述 
	* @param @param groupId
	* @param @param groupDesc
	* @param @param groupName
	* @param @return    入参
	* @return int    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月6日 下午4:12:15 
	* @version V1.0
	 */
	@Override
	public int saveGroupDescByGroupId(Long groupId, String groupDesc, String groupName,Users user) {
		return fiberdiscGroupMapper.saveGroupDescByGroupId(groupId,groupDesc,groupName,user);
	}

	/**
	 * 新建的熔纤盘列表展示,以及每个盘的纤芯
	* 
	* @Title: FiberdiscGroupService.java 
	* @Description: 
	* @param @param fiberdiscGroup
	* @param @return
	* @return List<FiberdiscGroup>
	* @author fl
	* @date 2017年12月17日 下午2:50:29
	* @version V1.0
	 */
	@Override
	public List<FiberdiscGroup> queryisInvest(Long devId) {
		List<FiberdiscGroup> queryisInvest=fiberdiscGroupMapper.queryisInvest(devId);//in
		if (queryisInvest !=null && queryisInvest.size()>0) {
			for (int i = 0; i < queryisInvest.size(); i++) {
				FiberdiscGroup fiberdiscGroup=queryisInvest.get(i);//分组集合
				//----------
//				if(fiberdiscGroup.getDiscinfoList()!=null && fiberdiscGroup.getDiscinfoList().size()>0) {
//					for (int j = 0; j < fiberdiscGroup.getDiscinfoList().size(); j++) {
//						Discinfo d = fiberdiscGroup.getDiscinfoList().get(j);
//						String discCode =d.getDiscCode();
//						List<Fiberdisc> fiberdiscList = fiberdiscMapper.queryFiberdiscByCodeId(discCode,devId);
//						if(fiberdiscList !=null && fiberdiscList .size()>1) {
//							List<Long> LongList = new ArrayList<Long>();
//							for (int k = 0; k < fiberdiscList.size(); k++) {
//								LongList.add(fiberdiscList.get(k).getFiberNum());
//							}
//							String[] strs=null;
//							if (LongList !=null && LongList.size()>0) {
//								 strs = StringUtil.sort(LongList).split(",");
//							}
//							String sb="";
//							String sb1="";
//							String sb2="";
//							for (int l = 0; l < strs.length; l++) {
//								if (strs[l].contains("-")) {//字符串长度大于1
//									sb1+=strs[l]+",";
//								}else {//小于1
//									sb2+=strs[l]+"-"+strs[l]+",";
//								}
//								sb=sb1+","+sb2;
//								d.setFiberNoA(sb.substring(1, sb.length()-1));
//								d.setSevNameA(cableSectionMapper.selectByPrimaryKey(fiberdiscList.get(j).getSectId()).getSecName());
//							}
//						}else if(fiberdiscList !=null && fiberdiscList .size()==1){
//							d.setFiberNoA(fiberdiscList.get(0).getFiberNum()+"-"+fiberdiscList.get(0).getFiberNum());
//							d.setSevNameA(cableSectionMapper.selectByPrimaryKey(fiberdiscList.get(0).getSectId()).getSecName());
//						}
//						List<Fiberdisc> fiberdiscListout=fiberdiscMapper.queryFiberdiscByCodeId(discCode.replace("ZRIN", "ZROUT"), devId);
//						if(fiberdiscListout !=null && fiberdiscListout.size()>1) {
//							List<Long> LongList = new ArrayList<Long>();
//							for (int k = 0; k < fiberdiscList.size(); k++) {
//								LongList.add(fiberdiscList.get(k).getFiberNum());
//							}
//							String[] strs = StringUtil.sort(LongList).split(",");
//							String sb="";
//							String sb1="";
//							String sb2="";  
//							for (int l = 0; l < strs.length; l++) {//8  9-10
//								if (strs[l].contains("-")) {//字符串长度大于1
//									sb1+=strs[l]+",";
//								}else {//小于1
//									sb2+=strs[l]+"-"+strs[l]+",";//8-8
//								}
//								sb=sb1+","+sb2;
//								d.setFiberNoB(sb.substring(1, sb.length()-1));
//								d.setSevNameZ(cableSectionMapper.selectByPrimaryKey(fiberdiscListout.get(j).getSectId()).getSecName());
//							}
//						}else if(fiberdiscListout !=null && fiberdiscListout.size()==1){
//							d.setFiberNoB(fiberdiscListout.get(0).getFiberNum()+"-"+fiberdiscListout.get(0).getFiberNum());
//							d.setSevNameZ(cableSectionMapper.selectByPrimaryKey(fiberdiscListout.get(0).getSectId()).getSecName());
//						}
				
				//---------
				if(fiberdiscGroup.getDiscinfoList()!=null && fiberdiscGroup.getDiscinfoList().size()>0) {
					//熔纤盘集合
					for (int j = 0; j < fiberdiscGroup.getDiscinfoList().size(); j++) {
						Discinfo d = fiberdiscGroup.getDiscinfoList().get(j);
						String discCode =d.getDiscCode();
						List<Fiberdisc> fiberdiscList = fiberdiscMapper.queryFiberdiscByCodeId(discCode,devId);
						if(fiberdiscList !=null && fiberdiscList .size()>1) {
							for (int k = 0; k < fiberdiscList.size(); k++) {
								d.setFiberNoA(fiberdiscList.get(0).getFiberNum()+"-"+fiberdiscList.get(fiberdiscList.size()-1).getFiberNum());
								d.setSevNameA(cableSectionMapper.selectByPrimaryKey(fiberdiscList.get(k).getSectId()).getSecName());
							}
						}else if(fiberdiscList !=null && fiberdiscList .size()==1){
							d.setFiberNoA(fiberdiscList.get(0).getFiberNum()+"-"+fiberdiscList.get(0).getFiberNum());
							d.setSevNameA(cableSectionMapper.selectByPrimaryKey(fiberdiscList.get(0).getSectId()).getSecName());
						}
//						discCode=discCode.replace("ZRIN", "ZROUT");
						List<Fiberdisc> fiberdiscListout=fiberdiscMapper.queryFiberdiscByCodeId(discCode.replace("ZRIN", "ZROUT"), devId);
						if(fiberdiscListout !=null && fiberdiscListout.size()>1) {
							for (int k = 0; k < fiberdiscListout.size(); k++) {
								d.setFiberNoB(fiberdiscListout.get(0).getFiberNum()+"-"+fiberdiscListout.get(fiberdiscListout.size()-1).getFiberNum());
								d.setSevNameZ(cableSectionMapper.selectByPrimaryKey(fiberdiscListout.get(k).getSectId()).getSecName());
							}
						}else if(fiberdiscListout !=null && fiberdiscListout.size()==1){
							d.setFiberNoB(fiberdiscListout.get(0).getFiberNum()+"-"+fiberdiscListout.get(0).getFiberNum());
							d.setSevNameZ(cableSectionMapper.selectByPrimaryKey(fiberdiscListout.get(0).getSectId()).getSecName());
						}
						
					}
				}
			}
		}
		return queryisInvest;
	}

	/**
	 * 添加直熔盘
	 */
	@Override
	public int saveLnvestments(FiberdiscGroup group, Users user) {
		int num = 0;
		Facility facility = facilityService.selectById(group.getDevId());
		if(facility==null)
		{
			throw new FrmsException("所属设施不正确。");
		}
		//IN
		FiberdiscGroup groupConditionIn = new FiberdiscGroup();
		groupConditionIn.setGroupDesc(group.getGroupDesc());
		groupConditionIn.setGroupName(group.getGroupName());
		groupConditionIn.setPortNum(group.getPortNum());
		groupConditionIn.setDiscNum(group.getDiscNum());
		groupConditionIn.setDevId(group.getDevId());
		//保存直熔盘分组IN
		num = groupService.saveLnvestmentsGroupIn(groupConditionIn,user);
		//生成直熔盘信息
		num = discinfoService.saveDiscinfo(groupConditionIn, facility,user);
		
		//OUT
		FiberdiscGroup groupConditionInOut = new FiberdiscGroup();
		groupConditionInOut.setGroupDesc(group.getGroupDesc());
		groupConditionInOut.setGroupName(group.getGroupName());
		groupConditionInOut.setPortNum(group.getPortNum());
		groupConditionInOut.setDiscNum(group.getDiscNum());
		groupConditionInOut.setDevId(group.getDevId());
		//保存直熔盘分组OUT
		num = groupService.saveLnvestmentsGroupOUT(groupConditionInOut,user);
		//生成直熔盘信息
		num = discinfoService.saveDiscinfo(groupConditionInOut, facility,user);
		
		//生成直熔盘端子数据并生成跳纤-直熔盘专用
		num = fiberdiscService.genFiberdisc(user,facility.getDevId(), groupConditionIn.getSide(), groupConditionIn.getDiscNum(),groupConditionIn.getPortNum(),null,groupConditionIn.getStartIndex(),null);
		
		//生成直熔盘端子数据并生成跳纤-直熔盘专用
		num = fiberdiscService.genFiberdiscOut(user,facility.getDevId(), groupConditionInOut.getSide(), groupConditionInOut.getDiscNum(),groupConditionInOut.getPortNum(),null,groupConditionInOut.getStartIndex(),null);
		return num;
	}

	/**
	 * 保存直熔盘分组
	 */
	@Override
	public int saveLnvestmentsGroupIn(FiberdiscGroup group, Users user) {
		int req = 0;
		String side = "";
		Facility f = facilityService.selectById(group.getDevId());
		if(f==null)
			throw new FrmsException("获取设施出现异常");
		
		String maxSide =  fiberdiscGroupMapper.selectByMaxSide("ZRIN",group.getDevId());
		if(maxSide == null || "".equals(maxSide)) {
			side = "ZRIN01";
		}else {
			side = "ZRIN" + String.format("%02d", Integer.parseInt(maxSide.substring(maxSide.indexOf("N")+1))+1);
		}
		group.setCreateTime(new Date());
		group.setCreateUser(user.getUserId());
		group.setCreateUserName(user.getUserName());
		group.setIsInvest("1");
		group.setSide(side);
		req = fiberdiscGroupMapper.insertSelective(group);
		return req;
	}

	@Override
	public int saveLnvestmentsGroupOUT(FiberdiscGroup group, Users user) {
		int req = 0;
		String side = "";
		Facility f = facilityService.selectById(group.getDevId());
		if(f==null)
			throw new FrmsException("获取设施出现异常");
		
		String maxSide =  fiberdiscGroupMapper.selectByMaxSide("ZROUT",group.getDevId());
		if(maxSide == null || "".equals(maxSide)) {
			side = "ZROUT01";
		}else {
			side = "ZROUT" + String.format("%02d", Integer.parseInt(maxSide.substring(maxSide.indexOf("T")+1))+1);
		}
		group.setCreateTime(new Date());
		group.setCreateUser(user.getUserId());
		group.setCreateUserName(user.getUserName());
		group.setIsInvest("1");
		group.setSide(side);
		req = fiberdiscGroupMapper.insertSelective(group);
		return req;
	}

	/**
	 * 
	* @Title: deleteLnvestmentsGroup 
	* @Description: 删除直熔盘分组 
	* @param @param groupId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月17日 下午7:54:09 
	* @version V1.0
	 */
	@Override
	public int deleteLnvestmentsGroup(Long groupId,Users user) {
		int reg=0;
		FiberdiscGroup fg = fiberdiscGroupMapper.selectByPrimaryKey(groupId);
		String sideout = fg.getSide().replaceAll("ZRIN", "ZROUT");
		//根据ZRIN和ZROUT查询分组
		List<FiberdiscGroup> groupList = fiberdiscGroupMapper.querySideByZO(fg.getDevId(), fg.getSide(), sideout);
		if(groupList != null && groupList.size()>0) {
			for (int i = 0; i < groupList.size(); i++) {
				List<Discinfo> discList=discinfoService.selectByGroup(groupList.get(i).getGroupId());
				if(discList!=null && discList.size()>0) {
					 for (int j = 0; j < discList.size(); j++) {
						 reg=discinfoService.deleteLnvestments(discList.get(j).getDiscId(), discList.get(j).getDiscCode(), user);
					 }
				}
				reg=fiberdiscGroupMapper.deleteByPrimaryKey(groupList.get(i).getGroupId());
			}
		}
		return reg;
	}
	
	public static void main(String[] args) {
		String a = "JIJN02";
		System.out.println(String.format("%02d", Integer.parseInt(a.substring(a.indexOf("N")+1))+1));
	}

	/**
	 * 
	* @Title: deleteFiberdiscGroup 
	* @Description: 删除熔纤盘分组 
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年1月4日 下午2:20:41 
	* @version V1.0
	 */
	@Override
	public int deleteFiberdiscGroup(Long groupId, Users user) {
		int reg=0;
		FiberdiscGroup fiberGroup = fiberdiscGroupMapper.selectByPrimaryKey(groupId);
		//判断分组下是否绑定端子控制器
		List<DeviceDiscinfoEntity> ddelist = deviceDiscinfoEntityMapper.selectByGroupId(groupId);
		if(ddelist != null && ddelist.size() > 0) {
			throw new FrmsException("请先解除分组下的绑定的端子控制器!");
		}
		if(fiberGroup!=null) {
			List<Discinfo> discList = discinfoService.selectByGroup(groupId);
			if(discList!=null && discList.size()>0) {
				for (int i = 0; i < discList.size(); i++) {//删除盘
					if(discList.get(i).getDiscId() == null || discList.get(i).getDiscCode() == null || "".equals(discList.get(i).getDiscCode())) {
						throw new FrmsException("数据异常，请联系管理员！");
					}
					reg=discinfoService.discinfoDelete(discList.get(i).getDiscId(), discList.get(i).getDiscCode(), user);
				}
			}
			reg=fiberdiscGroupMapper.deleteByPrimaryKey(groupId);//删除组
		}
		return reg;
	}

	/**
	 * 
	 * @Title: facilityGroupsSave
	 * @Description: 保存设施分组
	 * @param @param group
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月15日 下午5:08:51
	 * @version V1.0
	 */
	@Override
	public int facilityGroupsSave(FiberdiscGroup group, Users users) {
		group.setLastModifyTime(new Date());
		group.setLastModifyUser(users.getUserId());
		return fiberdiscGroupMapper.updateByPrimaryKeySelective(group);
	}

	/**
	 * 
	 * @Title: modifyGroupSide
	 * @Description: 修改熔纤盘分组编码
	 * @param @param fiberdiscGroup
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月23日 下午1:54:15
	 * @version V1.0
	 */
	@Override
	public int modifyGroupSide(FiberdiscGroup fiberdiscGroup, Users user) {
		int req = 0;
		String upSide = fiberdiscGroup.getSide();//修改的分组编码
		FiberdiscGroup g = groupService.selectById(fiberdiscGroup.getGroupId());
		String side = g.getSide();//原分组编码
		List<FiberdiscGroup> groupList = groupService.queryByDevIdAndSide(g.getDevId(),side);
		
		//查询修改双新绑定
		List<Twinfiber> tfList = twinfiberMapper.selectByDevIdAndSide(g.getDevId(),side);
		if(tfList!=null && tfList.size()>0) {
			for (Twinfiber twinfiber : tfList) {
				twinfiber.setSide(upSide);
				twinfiber.setDiscCode(twinfiber.getDiscCode().replaceFirst("-"+side+"-", "-"+upSide+"-"));
				twinfiber.setPortCode1(twinfiber.getPortCode1().replaceFirst("-"+side+"-", "-"+upSide+"-"));
				twinfiber.setPortCode2(twinfiber.getPortCode2().replaceFirst("-"+side+"-", "-"+upSide+"-"));
				req = twinfiberMapper.updateByPrimaryKeySelective(twinfiber);
			}
		}
		
		//查询修改设施分组
		if(groupList!=null && groupList.size()>0) {
			for (FiberdiscGroup group : groupList) {
				List<Discinfo> Dlist = discinfoMapper.selectByGroup(group.getGroupId());
				//查询修改熔纤盘
				if(Dlist!=null && Dlist.size()>0) {
					for (Discinfo discinfo : Dlist) {
						List<Fiberdisc> Flist = fiberdiscService.queryByDiscCode(discinfo.getDiscCode());
						//查询修改端子信息
						if(Flist!=null && Flist.size()>0) {
							for (Fiberdisc fiberdisc : Flist) {
								List<Lines> Llist = linesMapper.queryByPoint(fiberdisc.getPort01());
								//查询修改成端或跳纤信息
								if(Llist!=null && Llist.size()>0) {
									for(Lines line : Llist) {
										if(fiberdisc.getPort01().equals(line.getAcode())) {
											line.setAcode(line.getAcode().replaceFirst("-"+side+"-", "-"+upSide+"-"));
										}else {
											line.setZcode(line.getZcode().replaceFirst("-"+side+"-", "-"+upSide+"-"));
										}
										line.setLastModifyTime(new Date());
										line.setLastModifyUser(user.getUserId());
										req = linesMapper.updateByPrimaryKeySelective(line);
									}
								}
								fiberdisc.setSide(upSide);
								fiberdisc.setDiscCode(fiberdisc.getDiscCode().replaceFirst("-"+side+"-", "-"+upSide+"-"));
								fiberdisc.setPort01(fiberdisc.getPort01().replaceFirst("-"+side+"-", "-"+upSide+"-"));
								fiberdisc.setLastModifyTime(new Date());
								fiberdisc.setLastModifyUser(user.getUserId());
								req = fiberdiscMapper.updateByPrimaryKeySelective(fiberdisc);
							}
						}
						discinfo.setSide(upSide);
						discinfo.setDiscCode(discinfo.getDiscCode().replaceFirst("-"+side+"-", "-"+upSide+"-"));
						discinfo.setLastModifyTime(new Date());
						discinfo.setLastModifyUser(user.getUserId());
						req = discinfoMapper.updateByPrimaryKeySelective(discinfo);
					}
				}
				group.setSide(upSide);
				group.setLastModifyTime(new Date());
				group.setLastModifyUser(user.getUserId());
				req = fiberdiscGroupMapper.updateByPrimaryKeySelective(group);
			}
		}
		return req;
	}

	/**
	 * 
	 * @Title: queryByDevIdAndSide
	 * @Description: 根据devId和side查询分组数据
	 * @param @param devId
	 * @param @param side
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月23日 下午2:24:36
	 * @version V1.0
	 */
	@Override
	public List<FiberdiscGroup> queryByDevIdAndSide(Long devId, String side) {
		return fiberdiscGroupMapper.queryByDevIdAndSide(devId,side);
	}


	/**
	 * 根据工单查询
	* @Title: queryWorkorderFiberdiscabindAllByDesignroutesId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param designroutesId
	* @param @return    入参
	* @return FactilityFiberDiscGroupBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月6日 上午9:40:41 
	* @version V1.0
	 */
	@Override
	public FactilityFiberDiscGroupBean queryWorkorderFiberdiscabindAllByDesignroutesId(Long designroutesId,Long devId,String state,String startOrEnd) {
		FactilityFiberDiscGroupBean factilityFiberDiscGroupBean = new FactilityFiberDiscGroupBean();
		Facility facility = facilityMapper.selectByPrimaryKey(devId);
		if (facility !=null) {
			factilityFiberDiscGroupBean.setDevName(facility.getDevName());
			factilityFiberDiscGroupBean.setDevCode(facility.getDevCode());
		}
		if ("1".equals(state)) {
			List<WorkorderImplePlansImgBean> list=workorderImplePlansImgMapper.queryByDesignroutesId(designroutesId);
			List<String> imgList = new ArrayList<String>();
			if (list!=null && list.size()>0) {
				for (WorkorderImplePlansImgBean workorderImplePlansImgBean : list) {
					factilityFiberDiscGroupBean.setImpleBack(workorderImplePlansImgBean.getImpleBack());
					imgList.add(workorderImplePlansImgBean.getImgUrl());
				}
				factilityFiberDiscGroupBean.setImgUrls(imgList);
				imgList=null;
			}
		}
		WorkorderRoutes route = workorderRoutesMapper.selectByPrimaryKey(designroutesId);
		
		
		//2
		//始端
		WorkorderLightline workorderLightline=workorderLightlineMapper.queryDeviceExist(designroutesId);
		if("1".equals(startOrEnd)) {
			factilityFiberDiscGroupBean.setIsBend("1");
			//是否有设备配置
			if (workorderLightline==null || workorderLightline.getAfiberDiscId()!=null) {
				factilityFiberDiscGroupBean.setWorkorderFiberdiscabindBeanZ(null);
			}else {
				factilityFiberDiscGroupBean.setWorkorderFiberdiscabindBeanZ(workorderImplePlansMapper.queryImplAByFiberDiscId(workorderLightline.getAfiberDiscId()));
			}
			//1
			factilityFiberDiscGroupBean.setWorkorderFiberdiscabindBeanA(workorderFiberdiscabindAMapper.queryWorkorderFiberdiscabindAByDesignroutesId(designroutesId));
		//末端
		}else if("2".equals(startOrEnd)) {
			factilityFiberDiscGroupBean.setIsBend("1");
			if (workorderLightline==null || workorderLightline.getZfiberDiscId()!=null) {
				factilityFiberDiscGroupBean.setWorkorderFiberdiscabindBeanZ(null);
			}else {
				factilityFiberDiscGroupBean.setWorkorderFiberdiscabindBeanZ(workorderImplePlansMapper.queryImplAByFiberDiscId(workorderLightline.getAfiberDiscId()));
			}
			//1
			factilityFiberDiscGroupBean.setWorkorderFiberdiscabindBeanA(workorderFiberdiscabindAMapper.queryWorkorderFiberdiscabindAByDesignroutesId(designroutesId));
		//非始端末端
		}else {
			//1
			factilityFiberDiscGroupBean.setWorkorderFiberdiscabindBeanZ(workorderFiberdiscabindAMapper.queryWorkorderFiberdiscabindAByDesignroutesId(designroutesId));
			WorkorderRoutes route1 = workorderRoutesMapper.selectDesignAndRouteSort(route.getDesignplanId(), route.getRoutesSort()-1);
			factilityFiberDiscGroupBean.setWorkorderFiberdiscabindBeanA(workorderFiberdiscabindZMapper.queryWorkorderFiberdiscabindZByDesignroutesId(route1.getDesignroutesId()));
		}
		
		if("1".equals(state)) {
			//3
			WorkorderFiberdiscabindImgBean workorderFiberdiscabindImgBeanA = workorderImplePlansMapper.queryImplAByDesignroutesId(designroutesId);
			factilityFiberDiscGroupBean.setWorkorderFiberdiscabindImgBeanA(workorderFiberdiscabindImgBeanA);
			//4
			WorkorderFiberdiscabindImgBean workorderFiberdiscabindImgBeanZ = workorderImplePlansMapper.queryImplZByDesignroutesId(designroutesId);
			factilityFiberDiscGroupBean.setWorkorderFiberdiscabindImgBeanZ(workorderFiberdiscabindImgBeanZ);
		}
		
		return factilityFiberDiscGroupBean;

	}

	
}
