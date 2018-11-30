package com.ycnet.mobile.controller.iam;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.FrmsException;
import com.ycnet.core.config.SessionName;
import com.ycnet.core.util.AppRequestEntity;
import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.ResourceDataLog;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.DeviceDiscinfoService;
import com.ycnet.frms.service.DeviceService;
import com.ycnet.frms.service.DiscinfoService;
import com.ycnet.frms.service.FacilityCoreService;
import com.ycnet.frms.service.FacilityEntityService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscGroupService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.ResourceDataLogService;
import com.ycnet.frms.service.UsersService;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityVoBeanXY;
import com.ycnet.frms.vo.mobile.DeviceEntityCountBean;
import com.ycnet.frms.vo.mobile.DeviceMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;
import com.ycnet.frms.vo.mobile.FaciltyDeviceDtoBean;
import com.ycnet.frms.vo.mobile.RackDetailsBean;

import net.sf.json.JSONObject;


@RestController
@RequestMapping("/iam")
public class FacilityEntityController {
	
	@Resource(name = "facilityEntityService")
	private FacilityEntityService facilityEntityService;
	
	@Resource(name="usersService")
	private UsersService usersService;
	
	@Resource(name="organizitionService")
	private OrganizitionService organizitionService;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="resourceDataLogService")
	private ResourceDataLogService resourceDataLogService;
	
	@Resource(name="fiberdiscGroupService")
	private FiberdiscGroupService groupService;
	
	@Resource(name="discinfoService")
	private DiscinfoService discinfoService;
	
	@Resource(name="deviceService")
	private DeviceService deviceService;
	
	@Autowired
	private DeviceDiscinfoService deviceDiscinfoService;
	
	
	/**
	 * 设施查询
	* @Title: queryFacilityByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param session
	* @param @param facility
	* @param @param pb
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月12日 下午3:52:17 
	* @version V1.0
	 */
	@RequestMapping("/queryFacilityDeviceByConditions.htm")
	public Object queryFacilityByConditions(HttpSession session,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryFacilityDeviceByConditions");
		try {
			Integer pageNo = Integer.parseInt(entity.getDto().getOrDefault("pageNo", "0").toString());
			Integer pageSize = Integer.parseInt(entity.getDto().getOrDefault("pageSize", "0").toString());
			PageBean pb=new PageBean();
			pb.setPageNo(pageNo);
			if (pageSize.intValue()!=0) {
				pb.setPageSize(pageSize);
			}
			if (pb.getPageNo()==0) {//如果为0查询全部
				pb=null;
			}
			String devCode = entity.getDto().getOrDefault("devCode", "").toString();
			String devName = entity.getDto().getOrDefault("devName", "").toString();
			String surveyResult = entity.getDto().getOrDefault("surveyResult", "").toString();
			String areaCode1 = entity.getDto().getOrDefault("areaCode1", "").toString();
			String devType = entity.getDto().getOrDefault("devType", "").toString();
			String checkType = entity.getDto().getOrDefault("checkType", "").toString();
			Object curLat =entity.getDto().get("curLat");
			Object curLng =entity.getDto().get("curLng");
			Object distance =entity.getDto().get("distance");
			String devState = entity.getDto().getOrDefault("devState", "").toString();
			FacilityConditionBean facility=new FacilityConditionBean();
			facility.setDevCode(devCode);
			facility.setDevName(devName);
			facility.setSurveyResult(surveyResult);
			facility.setAreaCode1(areaCode1);
			facility.setDevType(devType);
			facility.setCheckType(checkType);
			if(null == curLat || "".equals(curLat.toString())) {
				facility.setCurLat(null);
			}else {
				facility.setCurLat(Double.valueOf(curLat.toString()));
			}
			if(null == curLng || "".equals(curLng.toString())) {
				facility.setCurLng(null);
			}else {
				facility.setCurLng(Double.valueOf(curLng.toString()));
			}
			if(null == distance || "".equals(distance.toString())) {
				facility.setDistance(null);
			}else {
				facility.setDistance(Double.valueOf(distance.toString()));
			}
			facility.setDevState(devState);
			Users users=(Users)session.getAttribute(SessionName.mobile);
			if (users !=null) {
				if (facility !=null) {
//					if (facility.getDistance()==null) {
//						facility.setDistance(1000.00);
//					}
					List<FaciltyDeviceDtoBean> facilityList=facilityEntityService.queryFacilityByConditions(facility,pb,users.getOrgId());
					if (facilityList !=null && facilityList.size()>0) {
//						System.out.println(facilityList.get(0).getDeviceEntityList().get(0).getCodName()+"=================");
						r.setCode("1");
						r.setMessage("查询成功!");
						r.setDto(facilityList);
					}else {
						r.setCode("0");
						r.setMessage("未查询到数据!");
					}
				}
			}else {
				r.setCode("0");
				r.setMessage("用户未登录!");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败!");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 根据设施ID,查询设施信息
	* @Title: queryFacilityByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月12日 下午4:27:33 
	* @version V1.0
	 */
	@RequestMapping("/queryFacilityDeviceByDevId.htm")
	public Object queryFacilityByDevId(HttpServletRequest request,@RequestBody AppRequestEntity entity) {
		Long devId = Long.parseLong(entity.getDto().getOrDefault("devId", -999l).toString());

		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryFacilityDeviceByDevId");
		try {
			Users user=(Users) request.getSession().getAttribute(SessionName.mobile);
			if (devId ==-999l) {
				r.setCode("0");
				r.setMessage("设施ID不能为空!");
				return r;
			}
			if (user !=null) {
				FaciltyDeviceDtoBean facilityEntity=facilityEntityService.queryFacilityByDevId(devId);
				//Map<String, Object> facilityEntity = facilityEntityService.findFacilityByDevId(devId);
				if (facilityEntity !=null) {
					r.setCode("1");
					r.setMessage("查询成功!");
					r.setDto(facilityEntity);
				}else {
					r.setCode("0");
					r.setMessage("未查询到数据!");
					r.setDto(null);
				}
			}else {
				r.setCode("0");
				r.setMessage("用户未登录!");
				r.setDto(null);
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败!");
			r.setDto(null);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * fl 修改,增加百度xy保存
	* @Title: saveFacility 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param session
	* @param @param facility
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月27日 下午4:42:58 
	* @version V1.0
	 */
	@RequestMapping("/saveFacility.htm")
	public Object saveFacility(HttpServletRequest request ,HttpSession session,@RequestBody AppRequestEntity entity)
	{
		JSONObject jsonObject = JSONObject.fromObject(entity.getDto());
		FacilityVoBeanXY facility = (FacilityVoBeanXY)JSONObject.toBean(jsonObject, FacilityVoBeanXY.class);
		Long i = 0L;
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("saveFacility");
		try {
			Users users=(Users)session.getAttribute("users");
			if(facility.getDevId()==null){
				if(facility.getAreaCode1()==null){
					Organizition o = organizitionService.selectById(users.getOrgId());
					if(o!=null)
						facility.setAreaCode1(o.getCode1());
				}
				if(facility.getOrgId()==null)
				{
					facility.setOrgId(users.getOrgId());
				}
				facility.setCreateTime(new Date()); 
				facility.setCreateUser(users.getUserId());
			}else{
				facility.setLastModifyTime(new Date());
				facility.setLastModifyUser(users.getUserId());
			}
			
			String imgType  = facility.getImgType();
			if(imgType ==null ||"".equals(imgType))
			{
				facility.setImgType("03");  //普查前
			}else{
				facility.setImgType("04"); //普查后
			}
			i=facilityService.saveFacilityImgs(facility,request);
			r.setCode(i.toString());
			if(i > 0) {
				r.setMessage("保存成功！");
			}else {
				r.setMessage("保存失败！");
			}
			//r.putR(i).initSaveMessage();
		} catch (FrmsException e1) {
			r.setCode("0");
			r.setMessage(e1.getMessage());
			r.setDto(null);
			e1.printStackTrace();
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败");
			r.setDto(null);
			e.printStackTrace();
		}
		return r;		
	}

	private static final Logger LOG = LoggerFactory.getLogger(AddInstrumentController.class);
	
	@Resource(name="facilityCoreService")
	private FacilityCoreService fcService;
	
	/**
	 * 
	* @Title: addDiscinfo 
	* @Description: 添加/修改熔纤盘 (支持修改熔纤盘备注信息)
	* @param @param session
	* @param @param entity
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年5月25日 上午9:48:10 
	* @version V1.0
	 */
	@RequestMapping("/addDiscinfoNew.htm")
	public Object addDiscinfo(HttpSession session,@RequestBody AppRequestEntity entity)
	{
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("addDiscinfoNew");
		try
		{
//			Integer index=Integer.parseInt(entity.getDto().getOrDefault("index", -999).toString());
			String discName = entity.getDto().getOrDefault("discName", "").toString();
			Integer index = Integer.valueOf(discName);
			Long discId=Long.parseLong(entity.getDto().getOrDefault("discId", -999L).toString());
			Long groupId=Long.parseLong(entity.getDto().getOrDefault("groupId", -999L).toString());
			String side = entity.getDto().getOrDefault("side", "").toString();
			Integer portNum=Integer.parseInt(entity.getDto().getOrDefault("portNum", -999).toString());
			Long devId=Long.parseLong(entity.getDto().getOrDefault("devId", -999L).toString());
			String remark = entity.getDto().getOrDefault("remark", "").toString();
			if(index != null && index != -999) {
				if(index<1) {
					r.setCode("0");
					r.setMessage("请填写正确熔纤盘序号 ！");
					return r;
				}
			}
			if(side != null && !"".equals(side.trim()) && side.length()>64) {
				r.setCode("0");
				r.setMessage("分组编码过长!");
				return r;
			}
			if(remark != null && !"".equals(remark.trim()) && remark.length()>200) {
				r.setCode("0");
				r.setMessage("熔纤盘备注信息过长!");
				return r;
			}
//			if(devId == null || devId.longValue()==-999L) {
//				r.setCode("0");
//				r.setMessage("设施ID不能为空！");
//				return r;
//			}
			if(portNum != null && portNum != -999 && portNum<1) {
				r.setCode("0");
				r.setMessage("请填写正确端子数量！");
				return r;
			}
//			if(groupId == null || groupId.longValue()==-999L) {
//				r.setCode("0");
//				r.setMessage("分组ID不能为空！");
//				return r;
//			}
			Discinfo discinfo =new Discinfo();
			discinfo.setDevId(devId);
			if(discId != null && discId != -999L) {
				discinfo.setDiscId(discId);
			}
			discinfo.setPortNum(portNum);
			discinfo.setRemark(remark);
			discinfo.setSide(side);
			discinfo.setGroupId(groupId);
			Users user = (Users) session.getAttribute("users"); 
			if(user==null){
				r.setCode("0");
				r.setMessage("用户未登录或不存在！");
				return r;
			}
			if(discinfo.getDiscId()==null || discinfo.getDiscId().longValue()==-999L){
				discinfo.setCreateTime(new Date());
				discinfo.setCreateUser(user.getUserId());
			}else{
				discinfo.setLastModifyUser(user.getUserId());
			}
			int i = fcService.addDiscInfo(discinfo, index);
			if(i>0) {
				r.setCode("1");
				r.setMessage("操作成功");
				return r;
			}else {
				r.setCode("0");
				r.setMessage("操作失败！");
				return r;
			}
		}
		catch(FrmsException e1)
		{
			r.setCode("0");
			r.setMessage(e1.getMessage());
			return r;
		}
		catch(Exception e)
		{
			r.setCode("0");
			r.setMessage("数据发生异常。");
			LOG.error("操作失败");
			e.printStackTrace();
		}
		return r;
	}
	/**
	 * 
	* @Title: addGroup 
	* @Description: 添加分组 
	* @param @param group
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年5月25日 上午9:48:21 
	* @version V1.0
	 */
	@RequestMapping("/addGroupNew.htm")
	public Object addGroup(HttpSession session,@RequestBody AppRequestEntity entity)
	{
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("addGroupNew");
		try
		{	
			String groupName = entity.getDto().getOrDefault("groupName", "").toString();
			String side = entity.getDto().getOrDefault("side", "").toString();
			Integer discNum=Integer.parseInt(entity.getDto().getOrDefault("discNum", -999).toString());
			Integer portNum=Integer.parseInt(entity.getDto().getOrDefault("portNum", -999).toString());
			String groupDesc = entity.getDto().getOrDefault("groupDesc", "").toString();
			Long devId=Long.parseLong(entity.getDto().getOrDefault("devId", -999L).toString());
			if(portNum<1) {
				if(portNum==-999) {
					r.setCode("0");
					r.setMessage("端子数量不能为空！");
					return r;
				}
				r.setCode("0");
				r.setMessage("请正确填写端子数量！");
				return r;
			}
			if(devId.longValue()==-999L) {
				r.setCode("0");
				r.setMessage("设施ID不能为空！");
				return r;
			}
			if(discNum<1) {
				if(discNum==-999) {
					r.setCode("0");
					r.setMessage("盘数量不能为空！");
					return r;
				}
				r.setCode("0");
				r.setMessage("请正确填写盘数量！");
				return r;
			}
			if(side == null || "".equals(side.trim())) {
				r.setCode("0");
				r.setMessage("分组编码不能为空！");
				return r;
			}
			if(side != null && !"".equals(side.trim()) && side.length()>64) {
				r.setCode("0");
				r.setMessage("分组编码过长!");
				return r;
			}
			if(groupName.length()>64) {
				r.setCode("0");
				r.setMessage("分组名称过长！");
				return r;
			}
			if(groupDesc.length()>200) {
				r.setCode("0");
				r.setMessage("分组描述过长！");
				return r;
			}
			FiberdiscGroup group=new FiberdiscGroup();
			group.setGroupName(groupName);
			group.setSide(side);
			group.setDiscNum(discNum);
			group.setPortNum(portNum);
			group.setDevId(devId);
			group.setGroupDesc(groupDesc);
			group.setCreateTime(new Date());
			
			String side1 = group.getSide();
			boolean matches = side1.matches("[0-9a-zA-Z]*");
			if(!matches) {
				r.setCode("0");
				r.setMessage("分组编码必须为数字或英文字母");
				return r;
			}
			Users user = (Users) session.getAttribute("users"); 
			if(user==null){
				r.setCode("0");
				r.setMessage("用户未登录或不存在！");
				return r;
			}
			group.setCreateUser(user.getUserId());
			int i = fcService.addGroup(group);
			if(i>0) {
				r.setCode("1");
				r.setMessage("操作成功！");
				return r;
			}else {
				r.setCode("0");
				r.setMessage("操作失败！");
				return r;
			}
		}
		catch(FrmsException e1)
		{
			r.setCode("0");
			r.setMessage(e1.getMessage());
			return r;
		}
		catch(Exception e)
		{
			r.setCode("0");
			r.setMessage("操作失败！");
			LOG.error("操作失败");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	 * @Title: updateGroupNew
	 * @Description: 修改分组
	 * @param @param session
	 * @param @param entity
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月30日 下午5:29:12
	 * @version V1.0
	 */
	@RequestMapping("/updateGroupNew.htm")
	public Object updateGroupNew(HttpSession session,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("updateGroupNew");
		try {
			Users user = (Users) session.getAttribute("users");
			String groupName = entity.getDto().getOrDefault("groupName", "").toString();
			String groupDesc = entity.getDto().getOrDefault("groupDesc", "").toString();
			Long groupId=Long.parseLong(entity.getDto().getOrDefault("groupId", -999L).toString());
			if(groupId == -999L) {
				r.setCode("0");
				r.setMessage("分组ID不能为空！");
				return r;
			}
			if(groupName.length()>64) {
				r.setCode("0");
				r.setMessage("分组名称过长！");
				return r;
			}
			if(groupDesc.length()>200) {
				r.setCode("0");
				r.setMessage("分组描述过长！");
				return r;
			}
			FiberdiscGroup group=new FiberdiscGroup();
			group.setGroupId(groupId);
			group.setGroupName(groupName);
			group.setGroupDesc(groupDesc);
			group.setLastModifyTime(new Date());
			group.setLastModifyUser(user.getUserId());
			int req = groupService.updateGroup(group);
			if(req>0) {
				r.setCode("1");
				r.setMessage("操作成功！");
			}else {
				r.setCode("0");
				r.setMessage("操作失败！");
			}
		} catch (NumberFormatException e) {
			r.setCode("0");
			r.setMessage("操作失败！");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 修改熔纤盘
	 */
	@RequestMapping(value = "/modifyDiscinfo.htm")
	public Object modifyDiscinfo(HttpSession session,@RequestBody AppRequestEntity entity) {
		Long discId = Long.valueOf(entity.getDto().getOrDefault("discId", "0").toString());
		String remark = entity.getDto().getOrDefault("remark", "").toString();
		
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("updateDiscinfo");
		r.setDto(null);
		
		Users user = (Users) session.getAttribute("users"); 
		if(user==null){
			r.setCode("0");
			r.setMessage("用户未登录或不存在！");
			return r;
		}
		
		Discinfo discinfo = new Discinfo();
		discinfo.setDiscId(discId);
		discinfo.setRemark(remark);
		discinfo.setLastModifyTime(new Date());
		discinfo.setLastModifyUser(user.getUserId());
		
		int num = discinfoService.update(discinfo);
		
		if(num > 0) {
			r.setCode("1");
			r.setMessage("修改成功！");
		}else {
			r.setCode("0");
			r.setMessage("修改失败！");
		}
		return r;
	}
	
	
	/**
	 * 根据设施ID查询机房中机柜列表
	 */
	@RequestMapping("/findFacilityByPdevId.htm")
	public Object findFacilityByPdevId(@RequestBody AppRequestEntity entity) {
		Long devId = Long.valueOf(entity.getDto().getOrDefault("devId", "0").toString());
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("findFacilityByPdevId");
		try {
			List<Map<String, Object>> facilitys = facilityService.findFacilityByPdevId(devId);
			if (facilitys !=null && facilitys.size()>0) {
				r.setCode("1");
				r.setMessage("查询成功！");
				r.setDto(facilitys);
			}else {
				r.setCode("0");
				r.setMessage("未查询到数据！");
			}
			return r;
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败！");
			r.setDto(null);
			e.printStackTrace();
			return r;
		}
	}
	
	/**
	 * 
	* @Title: modifyFacilityStateNew 
	* @Description: 对资管导入设施和新增设施状态进行修改
	* @param @param devId
	* @param @param devStateA
	* @param @param devStateZ
	* @param @param devName
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月1日 下午2:01:20 
	* @version V1.0
	 */
	@RequestMapping("/modifyFacilityStateNew.htm")
	public Object modifyFacilityState(HttpSession session,@RequestBody AppRequestEntity entity) {
		
		Long devId=Long.parseLong(entity.getDto().getOrDefault("devId", 0L).toString());
		String devStateA = entity.getDto().getOrDefault("devStateA", "").toString();
		String devStateZ = entity.getDto().getOrDefault("devStateZ", "").toString();
		String devName = entity.getDto().getOrDefault("devName", "").toString();
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("modifyFacilityStateNew");
		r.setDto(null);
		try {
			Users users=(Users)session.getAttribute("users");
			if(!"".equals(devStateZ) && devId.longValue()!=0L && !"".equals(devStateA) ) {
				Facility facility=new Facility();
				facility.setDevId(devId);
				facility.setDevStateA(devStateA);
				facility.setDevStateZ(devStateZ);
				facility.setDevName(devName);
				int reg=facilityService.updateFacilityState(users,facility);
				if(reg==1) {
					r.setCode("1");
					r.setMessage("修改成功!");
				}else if(reg==0) {
					r.setCode("0");
					r.setMessage("修改失败!");
				}
			}else {
				r.setCode("0");
				r.setMessage("所传参数不正确!");
			}
		} catch (FrmsException e1) {
			r.setCode("0");
			r.setMessage(e1.getMessage());
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败！");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	* @Title: deleteFiberdiscGroupNew 
	* @Description: 删除熔纤盘分组 
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年1月4日 下午2:20:41 
	* @version V1.0
	 */
	@RequestMapping("/deleteFiberdiscGroupNew.htm")
	public Object deleteFiberdiscGroup(HttpSession session,@RequestBody AppRequestEntity entity) {
		Long groupId=Long.parseLong(entity.getDto().getOrDefault("groupId", 0L).toString());
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("deleteFiberdiscGroupNew");
		r.setDto(null);
		Users user=(Users)session.getAttribute("users");
		ResourceDataLog rdl = new ResourceDataLog();
		try {
			if(groupId.longValue()==0L) {
				r.setCode("0");
				r.setMessage("熔纤盘分组ID不能为空！");
				return r;
			}
			FiberdiscGroup fg = groupService.selectById(groupId);
			rdl.setHisContent("分组编码:"+fg.getSide()+",分组名称:"+fg.getGroupName());//操作前的内容
			int reg=groupService.deleteFiberdiscGroup(groupId,user);
			if(reg>0) {
				rdl.setResLogType("02");//日志类型,02分组
				rdl.setHandleType("2");//操作类型,2删除
				rdl.setHandleId(groupId);//操作分组的ID
				rdl.setModifyUserId(user.getUserId());//操作人ID
				rdl.setModifyUserName(user.getUserName());//操作人名称
				rdl.setModifyTime(new Date());//操作时间
				resourceDataLogService.saveResourceDataLog(rdl);//添加资管校准日志数据
				r.setCode("1");
				r.setMessage("删除成功！");
			}else {
				r.setCode("0");
				r.setMessage("删除失败！");
			}
		} catch (FrmsException e1) {
			r.setCode("0");
			r.setMessage(e1.getMessage());
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败！");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	 * @Title: discinfoDeleteNew
	 * @Description: 删除熔纤盘
	 * @param @param discId
	 * @param @param session
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author liucanghai
	 * @throws
	 * @date 2018年1月5日 上午9:07:57
	 * @version V1.0
	 */
	@RequestMapping("/discinfoDeleteNew.htm")
	public Object discinfoDelete(HttpSession session,@RequestBody AppRequestEntity entity) {
		Long discId=Long.parseLong(entity.getDto().getOrDefault("discId", 0L).toString());
		String discCode = entity.getDto().getOrDefault("discCode", "").toString();
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("discinfoDeleteNew");
		r.setDto(null);
		ResourceDataLog rdl = new ResourceDataLog();
		Users user=(Users)session.getAttribute("users");
		if(discId.longValue() == 0L) {
			r.setCode("0");
			r.setMessage("熔纤盘ID不能为空");
			return r;
		}
		if("".equals(discCode)) {
			r.setCode("0");
			r.setMessage("熔纤盘编码不能为空");
			return r;
		}
		try {
			Discinfo dis = discinfoService.selectById(discId);
			rdl.setHisContent("熔纤盘编码:"+dis.getDiscCode());//操作前的内容
			int num = discinfoService.discinfoDelete(discId,discCode,user);
			if(num>0){
				rdl.setResLogType("03");//日志类型,03熔纤盘
				rdl.setHandleType("2");//操作类型,2删除
				rdl.setHandleId(discId);//操作分组的ID
				rdl.setModifyUserId(user.getUserId());//操作人ID
				rdl.setModifyUserName(user.getUserName());//操作人名称
				rdl.setModifyTime(new Date());//操作时间
				resourceDataLogService.saveResourceDataLog(rdl);//添加资管校准日志数据
				r.setCode("1");
				r.setMessage("删除成功");
			}else {
				r.setCode("0");
				r.setMessage("删除失败");
			}
		}catch (FrmsException e1) {
			r.setCode("0");
			r.setMessage(e1.getMessage());
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("删除失败");
			e.printStackTrace();
		} 
		return r;
	}
	
	/**
	 * 
	 * @Title: saveFacilityImages
	 * @Description: 保存图片
	 * @param @param request
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月1日 上午9:55:00
	 * @version V1.0
	 */
	@RequestMapping("/saveFacilityImages.htm")
	public Object saveFacilityImages(HttpServletRequest request,HttpSession session) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("saveFacilityImages");
		try {
			Users user=(Users)session.getAttribute("users");
			if(user==null){
				r.setCode("0");
				r.setMessage("用户未登录或不存在！");
				return r;
			}
			List<String> list = facilityService.saveFacilityImages(request,user);
			if(list != null && list.size()>0) {
				r.setCode(String.valueOf(list.size()));
				r.setMessage("保存成功");
				r.setDto(list);
				return r;
			}else {
				r.setCode("0");
				r.setMessage("保存失败");
				r.setDto(list);
				return r;
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("保存失败");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	 * @Title: deleteImage
	 * @Description: 删除图片
	 * @param @param request
	 * @param @param session
	 * @param @param entity
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月1日 上午10:17:58
	 * @version V1.0
	 */
	@RequestMapping("/deleteImage.htm")
	public Object deleteImage(HttpServletRequest request,@RequestBody AppRequestEntity entity,HttpSession session) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("deleteImage");
		try {
			List<String> imageUrlList = (List<String>) entity.getDto().getOrDefault("imageUrlList", null);
			Long devId=Long.parseLong(entity.getDto().getOrDefault("devId", -999L).toString());
			Users user=(Users)session.getAttribute("users");
			if(user==null){
				r.setCode("0");
				r.setMessage("用户未登录或不存在！");
				return r;
			}
			if(devId == null || devId == -999L || devId == 0L) {
				r.setCode("0");
				r.setMessage("设施ID不能为空");
				return r;
			}
			if(imageUrlList == null || imageUrlList.size() <= 0) {
				r.setCode("0");
				r.setMessage("删除图片路径不能为空");
				return r;
			}
			int s =facilityService.deleteImage(request,imageUrlList,devId);
			if (s>0) {
				r.setCode("1");
				r.setMessage("删除成功");
			}else {
				r.setCode("0");
				r.setMessage("删除失败");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("删除失败");
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 机柜详情,加盘信息
	* @Title: rackDetails 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 下午1:52:41 
	* @version V1.0
	 */
	@RequestMapping("/rackDetails.htm")
	public Object rackDetails(HttpSession session,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("rackDetails");
		try {
			Long devId = Long.valueOf(entity.getDto().getOrDefault("devId", -999l).toString());
			Users user=(Users)session.getAttribute(SessionName.mobile);
			if (null==user) {
				r.setCode("0");
				r.setMessage("用户未登录!");
				return r;
			}
			if (null==devId || devId==-999l) {
				r.setCode("0");
				r.setMessage("设施ID不能为空!");
				return r;
			}
			RackDetailsBean rackDetailsBean =deviceService.queryByDevId(devId);
			if (rackDetailsBean!=null) {
				r.setCode("1");
				r.setMessage("查询成功!");
				r.setDto(rackDetailsBean);
			}else {
				r.setCode("0");
				r.setMessage("未查询到数据!");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败!");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 根据机房查询所属机柜和绑定机柜数
	* @Title: queryCabinetsAndBindCounts 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午1:16:53 
	* @version V1.0
	 */
	@RequestMapping("/queryCabinetsAndBindCounts.htm")
	public Object queryCabinetsAndBindCounts(HttpServletRequest request,@RequestBody AppRequestEntity entity,HttpSession session) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryCabinetsAndBindCounts");
		
		try {
			Long devId = Long.parseLong(entity.getDto().getOrDefault("devId", -999l).toString());
			Users users = (Users) request.getSession().getAttribute(SessionName.mobile);
			if (users==null) {
				r.setCode("0");
				r.setMessage("用户未登录!");
				return r;
			}
			if (devId==-999l) {
				r.setCode("0");
				r.setMessage("设施ID不能为空!");
				return r;
			}
			DeviceEntityCountBean deviceEntityCountBean=deviceService.queryCabinetsAndBindCounts(devId);
			if (deviceEntityCountBean!=null) {
				r.setCode("1");
				r.setMessage("查询成功!");
				r.setDto(deviceEntityCountBean);
			}else {
				r.setCode("0");
				r.setMessage("未查询到数据!");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败!");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 根据中控器ID查询机柜列表
	* @Title: queryCabinetsInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param entity
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午2:07:27 
	* @version V1.0
	 */
	@RequestMapping("/queryCabinetsInfo.htm")
	public Object queryCabinetsInfo(HttpServletRequest request,@RequestBody AppRequestEntity entity,HttpSession session) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryCabinetsInfo");
		
		try {
			Long codId = Long.parseLong(entity.getDto().getOrDefault("codId", -999l).toString());
			Users users = (Users) request.getSession().getAttribute(SessionName.mobile);
			if (users==null) {
				r.setCode("0");
				r.setMessage("用户未登录!");
				return r;
			}
			if (codId==-999l) {
				r.setCode("0");
				r.setMessage("设施ID不能为空!");
				return r;
			}
			List<FacilityCabinetsInfo> facilityCabinetsInfoList=deviceDiscinfoService.queryCabinetsInfo(codId);
			if (facilityCabinetsInfoList!=null && facilityCabinetsInfoList.size()>0) {
				r.setCode("1");
				r.setMessage("查询成功!");
				r.setDto(facilityCabinetsInfoList);
			}else {
				r.setCode("0");
				r.setMessage("未查询到数据!");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败!");
			e.printStackTrace();
		}
		return r;
	}
}
  
    