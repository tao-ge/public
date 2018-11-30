package com.ycnet.facility.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.core.FrmsException;
import com.ycnet.core.config.SessionName;
import com.ycnet.core.util.ExcelUtil;
import com.ycnet.core.util.ExcelUtil.ExcelExportData;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceImage;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.DeviceAlarmService;
import com.ycnet.frms.service.DeviceImageService;
import com.ycnet.frms.service.DeviceRegService;
import com.ycnet.frms.service.DeviceStatusService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.MobileSwitchService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.Request;
import com.ycnet.frms.vo.RequestHeader;
import com.ycnet.mobile.util.Result;

import net.sf.json.JSONObject;

@Controller
public class LocksController {
	@Resource(name="deviceRegService")
	DeviceRegService deviceRegService;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="mobileSwitchService")
	private MobileSwitchService mobileSwitchService;
	
	@Resource(name="deviceStatusService")
	private DeviceStatusService deviceStatusService;
	
	@Resource(name="deviceAlarmService")
	private DeviceAlarmService deviceAlarmService;
	
	@Resource(name="organizitionService")
	private OrganizitionService organizitionService;
	
	@Resource(name="deviceImageService")
	private DeviceImageService deviceImageService;
	
	private static final Logger LOG = LoggerFactory.getLogger(FacilitysController.class);
	
	/**
	 * 
	 * 
	 * @Title: switchRecordList
	 * @Description: 终端开锁记录列表，改名为记录报表查询
	 * @param @param request
	 * @param @param session
	 * @param @param ms
	 * @param @param pb
	 * @param @param model
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY (修改)
	 * @throws
	 * @date 2017年12月22日 下午5:04:52
	 * @version V1.0
	 */
	@RequestMapping("/switchRecordList.htm")
	public String switchRecordList(HttpServletRequest request ,HttpSession session ,MobileSwitch ms,PageBean pb,Model model)
	{
		Users user=(Users)session.getAttribute("platformUser");
		pb = mobileSwitchService.queryByConditionBean(ms,pb,user);
		model.addAttribute("navFlag",NavFlag.SwitchLockRecord);
		model.addAttribute("ms",ms);
		model.addAttribute("pb", pb);
		return "facility/mobileSwitchList";
	}
	
	
	/**
	 * 开关锁记录导出数据
	* 
	* @Title: DeviceAlarmController.java 
	* @Description: TODO 
	* @param @param request
	* @param @param pb
	* @param @param session
	* @param @param deviceAlarmCon
	* @param @return
	* @return Object
	* @author fl
	* @date 2018年2月5日 下午1:24:07
	* @version V1.0
	 */
	@RequestMapping("/exportmobileSwitch.htm")
	@ResponseBody
	public Object exportmobileSwitch(HttpServletRequest request,PageBean pb,HttpSession session,MobileSwitch ms) {

		Result r = Result.get();
		try {
		
		Users user=(Users)session.getAttribute("platformUser");	
//		pb = mobileSwitchService.queryByConditionBean(ms,pb,user);
		List<MobileSwitch> list=mobileSwitchService.queryexport(ms,user);
		List<String[]> columNames = new ArrayList<String[]>();
		columNames.add(new String[] { "设施编码", "设施名称", "操作类型", "用户名称","手机号码",
				"作业时间","作业时长" });
		
		List<String[]> fieldNames = new ArrayList<String[]>();
		fieldNames.add(new String[] { "devCode", "fdevName", "switchType", "userName","mobilePhone",
				"dateTime","workTime"});
		
		LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
		map.put("开关锁记录", list);

		ExcelExportData setInfo = new ExcelExportData();
		setInfo.setDataMap(map);
		setInfo.setFieldNames(fieldNames);
		setInfo.setTitles(new String[] { "开关锁记录" });
		setInfo.setColumnNames(columNames);

		String path = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/export/";
		Date date = new Date();
		String fileName1 = "开关锁记录" + date.getTime()+".xls";
		String pathAndFileName = path +fileName1;
		if (ExcelUtil.export2File(setInfo, pathAndFileName)) {				
			r.setR(1);
			Map<String,String> rMap=new HashMap<String,String>();
			rMap.put("filePath", "/export/"+fileName1);
			r.setDt(rMap);
			r.putRContent("导出成功！");
		}
		else
		{
			r.setR(0);
			
			r.putRContent("导出失败！");
		}
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return r;
	}
	
	/**
	 * 
	 * @Title: querySwitchImage
	 * @Description: 找到对应开锁记录的图片
	 * @param @param request
	 * @param @param session
	 * @param @param ms
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月28日 下午2:40:29
	 * @version V1.0
	 */
	@RequestMapping("/querySwitchImage.htm")
	@ResponseBody
	public Object querySwitchImage(HttpServletRequest request ,HttpSession session ,Long switchId) {
		Map<String,Object> map = new HashMap<String,Object>();
		MobileSwitch mc = mobileSwitchService.queryById(switchId);//查询当前的开关锁记录
		DeviceImage image = deviceImageService.queryByImeiAndColtime(mc.getDevImei(),"1",mc.getColTime());//1开锁数据
//		List<DeviceImage> images = deviceImageService.queryByImei(mc.getDevImei());//根据IMEI查询图片信息
//		long timeMin = 120;
//		long timedif = 0;
//		long time = 0;
//		int k = 0;
//		if(images.size()>0) {
//			//循环取得开锁记录时间和图片记录时间最接近的图片数据
//			for (int i = 0; i < images.size(); i++) {
//				if((timedif = Math.abs(images.get(i).getCreateTime().getTime() - mc.getRptTime().getTime())/1000)<120) {
//	//				timedif = Math.abs(images.get(i).getCreateTime().getTime()-mc.getRptTime().getTime());
//					if(timedif<=timeMin) {
//						timeMin=timedif;
//						k=i;
//					}
//				}
//			}
//			map.put("image", images.get(k));
//		}
		if(image!=null) {
			map.put("image", image);
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: showLockList
	 * @Description: 设备基本信息列表
	 * @param @param request
	 * @param @param session
	 * @param @param dr
	 * @param @param pb
	 * @param @param model
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY (修改)
	 * @throws
	 * @date 2017年12月22日 下午12:14:29
	 * @version V1.0
	 */
	@RequestMapping("/LockList.htm")
	public String showLockList(HttpServletRequest request ,HttpSession session ,DeviceReg dr,PageBean pb,Model model)
	{
		Users user=(Users)session.getAttribute(SessionName.platform);
		List<DeviceReg> list =  deviceRegService.queryList(dr,pb,user);
		pb.setRows(deviceRegService.queryCount(dr,user));
		model.addAttribute("LockList", list);
		model.addAttribute("pb", pb);
		model.addAttribute("dr", dr);
		model.addAttribute("SignList", signList);
		model.addAttribute("sign", dr.getValidateSign()==null?"":dr.getValidateSign());
		model.addAttribute("redeployingType", dr.getRedeployingType()==null?"":dr.getRedeployingType());
		return "facility/LockList";
	}
	
	@RequestMapping("/goLockAdd.htm")
	public String showLockAdd(HttpServletRequest request ,HttpSession session ,Model model)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		PageBean pb = new PageBean();
		model.addAttribute("pb",facilityService.queryByConditionBean(new FacilityConditionBean(),user,pb));
		model.addAttribute("SignList", signList);
		model.addAttribute("sign", "");
		return "facility/LockEdit";
	}
	
	@RequestMapping("/goLockEdit.htm")
	public String showLockEdit(HttpServletRequest request ,HttpSession session ,DeviceReg dr,Model model)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		PageBean pb = new PageBean();
		model.addAttribute("pb",facilityService.queryByConditionBean(new FacilityConditionBean(),user,pb));
		dr = deviceRegService.selectById(dr.getRegId());
		model.addAttribute("dr", dr);
		model.addAttribute("SignList", signList);
		model.addAttribute("sign", dr.getValidateSign()==null?"":dr.getValidateSign());
		return "facility/LockEdit";
	}

	@ResponseBody
	@RequestMapping("/saveLockReg.htm")
	public Object saveLockReg(HttpSession session,DeviceReg dr)
	{
		Result r = Result.get();
		Users user=(Users)session.getAttribute("platformUser");	
		if(user==null){
			return r.putRContent("用户未登录或不存在！");
		}
		if(dr==null)
			return r.putRContent("参数错误！");
		if(dr.getRegId()==null)
			dr.setRegTime(new Date());
		return r.putR(deviceRegService.save(dr)).initSaveMessage();
	}
	
	/**
	 * 
	* @Title: delLockReg 
	* @Description: 智能锁管理,锁删除操作:删除设备(tn_device_reg)同时,删除设备状态(tn_device_status)以及设备的报警记录(tn_device_alarm) 
	* @param @param session
	* @param @param id
	* @param @return    入参
	* @return Object    返回类型
	* @author DZY
	* @throws
	* @date 2017年9月6日 下午3:04:44 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/delLockReg.htm")
	@Transactional
	public Object delLockReg(HttpSession session,Long regId)//String idAry
	{
		Users user=(Users)session.getAttribute("platformUser");	
//		System.out.println("~~~~~~~~~~~~~~~~~~~"+regId);
		Result r = Result.get();
		int reqHW = 0;
		int rdd = 0;
		if(regId == null){
			r.setR(0);
			r.putRContent("设备ID不能为空！");
			return r;
		}
		DeviceReg dr = deviceRegService.selectById(regId);
		try {
			if(dr != null){
//			System.out.println("~~~~~~~~~~开始删除啦~~~~~~~~~");
				String devImei = dr.getDevImei();//设备imei
				String devMac = dr.getDevMac();//设备mac
				if(devImei != null && !"".equals(devImei) && devMac != null && !"".equals(devMac)){
					
					//远程删除华为平台设备
					reqHW = deviceRegService.remoteHWDeleteDevice(dr.getHwDeviceId(),dr.getDevPlatform());
					//删除转发服务器的设备,返回1(成功)或0(失败)
					rdd = remoteDeleteDevice(devImei,user);
					//删除平台上的设备
					int drNum = deviceRegService.delete(regId);
					
					/*DeviceStatus ds = new DeviceStatus();//设备状态记录
					ds.setDevImei(devImei);
					ds.setDevMac(devMac);*/
					
					/*int dsNum = deviceStatusService.deleteDevStatusByImeiAndMac(ds);//删除设备状态记录
*///				System.out.println("~~~~~~~~~~~~~~~删除设备状态记录"+dsNum+"条~~~~~~~~~~~~~~~");
					
					/*DeviceAlarm da = new DeviceAlarm();//设备报警记录
					da.setDevImei(devImei);
					da.setDevMac(devMac);
					int daNum = deviceAlarmService.deleteAlarmByImeiAndMac(da);*/
//				System.out.println("~~~~~~~~~~~~~~~删除设备报警记录"+daNum+"条~~~~~~~~~~~~~~~");
					
					if(rdd>0 && drNum>0)
					{
						r.setR(1);
						r.putRContent("删除成功");
					}else {
						r.setR(0);
						r.putRContent("删除失败");
					}
				}
			}else{
				r.setR(0);
				r.putRContent("设备ID错误");
				return r;
			}
		} catch (Exception e) {
			r.setR(0);
			r.putRContent("删除失败");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	* @Title: remoteDeleteDevice 
	* @Description: TODO(与转发服务器建立socket连接删除设备) 
	* @param @return    入参
	* @return Object    返回类型
	* @author （DZY） 
	* @throws
	* @date 2017年9月6日 下午5:50:05 
	* @version V1.0
	 * @param devImei 
	 * @param user 
	 */
	private int remoteDeleteDevice(String devImei, Users user){
//		System.out.println("~~~~~~~~准备删除啦~~~~~~~");
		BufferedReader in = null;
		BufferedOutputStream out = null;
		Socket socket = null;
		boolean bConnected = false;
		Organizition org = organizitionService.selectByPrimaryKey(user.getOrgId());
		try {
			socket = new Socket(org.getIp(),org.getPort().intValue());
			socket.setSoTimeout(1000*20);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			out = new BufferedOutputStream(socket.getOutputStream());
			//out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
			System.out.println("~~~~~~~~连接成功~~~~~~~~!");
			RequestHeader requestHeader = new RequestHeader();
			requestHeader.setDid(devImei);
			requestHeader.setMsgId("1");
			requestHeader.setMsgType("remoteDeleteDevice");
			requestHeader.setVersion("0.4");
			Request req = new Request();
			req.setHeader(requestHeader);
			JSONObject json = JSONObject.fromObject(req);//封装为json
//			outDate = "{\"header\":{\"version\":\"0.4\",\"msgType\":\"remoteDeleteDevice\",\"msgId\":\"1\",\"did\":\""+devImei+"\"}}";
			String outDate="";
			outDate = json.toString();
			out.write(outDate.getBytes());
			bConnected = true;
			
			out.write("\r\n".getBytes());
			out.flush();
			while(bConnected){
				String inStr = in.readLine();
				if(inStr != null ){
					if("ok".equals(inStr)){
						return 1;
					}else{
						return 0;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					socket.close();
					out.close();
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return 0;
	}
	
	
	/**
	 * 修改布防状态
	* @Title: updateRedeploying 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月24日 上午11:27:05 
	* @version V1.0
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/updateRedeploying.htm")
	public Object updateRedeploying(HttpServletRequest request,HttpSession session,String tid,Long regId,String pal,String imei) {
		Result r=Result.get();
		int num=0;
		try {
			Users user=(Users)session.getAttribute("platformUser");	
			num = deviceRegService.updateRedeploying(tid,regId,pal,imei,user);
			if (num>0) {
				r.setR_content("操作成功");
				r.setR(1);
			}else {
				r.setR_content("操作失败");
				r.setR(0);
			}
		} catch (FrmsException e1) {
			e1.printStackTrace();
			r.setR_content(e1.getMessage());
			r.setR(0);
		}catch (Exception e) {
			e.printStackTrace();
			r.setR_content("操做失败");
			r.setR(0);
		}
		return r;
	}
	
	
	public static class SignSelect{
		private String name;
		private String value;
		
		public SignSelect(String n,String v){
			super();
			this.name=n;
			this.value=v;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	
	public static List<SignSelect> signList = Arrays.asList(
		new SignSelect("未注册","0"),
		new SignSelect("已注册","1"),
		new SignSelect("已激活","2")
	);
	
	/**
	 * 记录报表查询导航栏枚举类
	* @desc: frms-site  
	* @author: DZY  
	* @createTime: 2018年2月5日 上午9:05:47  
	* @history:  
	* @version: v1.0
	 */
	public enum NavFlag{
		SwitchLockRecord,//开关锁记录查询
		ReportOnTime,//定时上报记录查询
		AlarmRecord//报警记录查询
	}
}
