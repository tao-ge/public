package com.ycnet.facility.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.core.Constants;
import com.ycnet.core.util.ExcelUtil;
import com.ycnet.core.util.ExcelUtil.ExcelExportData;
import com.ycnet.core.util.PageBean;
import com.ycnet.facility.controller.LocksController.NavFlag;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.DeviceStatus;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.DeviceAlarmService;
import com.ycnet.frms.service.DeviceStatusService;
import com.ycnet.frms.service.MobileSwitchService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.vo.AreasParent;
import com.ycnet.frms.vo.DeviceAlarmBean;
import com.ycnet.frms.vo.DeviceStatusBean;
import com.ycnet.frms.vo.DeviceStatusConditionBean;
import com.ycnet.mobile.util.Result;

@Controller
public class DevStatusController {

	@Resource(name = "deviceStatusService")
	private DeviceStatusService deviceStatusService;

	@Resource(name = "organizitionService")
	private OrganizitionService organizitionService;

	@Resource(name = "areasService")
	private AreasService areasService;
	
	@Resource(name = "deviceAlarmService")
	private DeviceAlarmService deviceAlarmService;
	
	@Resource(name = "mobileSwitchService")
	private MobileSwitchService mobileSwitchService;

	private static final Logger LOG = LoggerFactory.getLogger(DevStatusController.class);

	// /**
	// * 查询设施监控数据
	// * @author YHT
	// * @time 2016年7月30日 上午10:57:13
	// * @param request
	// * @param session
	// * @param param
	// * @param model
	// * @return
	// */
	// @RequestMapping("/devStatusList.htm")
	// public String devStatusList(HttpSession session ,DeviceStatusConditionBean
	// devStaCon,PageBean pb ,Model model)
	// {
	// Users user=(Users)session.getAttribute("platformUser");
	// pb = deviceStatusService.queryByConditionBean(devStaCon, user, pb);
	// model.addAttribute("pb", pb);
	// model.addAttribute("oprStyleMap",Constants.oprStyleMap);
	// model.addAttribute("doorAndLockStatusMap",Constants.doorAndLockStatusMap);
	// model.addAttribute("isAlarmMap",Constants.isAlarmMap);
	// model.addAttribute("alarmTypeMap",Constants.alarmTypeMap);
	// model.addAttribute("devStaCon",devStaCon);
	// return "devStatus/devStatusList";
	// }
	
	/**
	 * 设施监控页面跳转
	 * @return
	 */
	@RequestMapping("/devStatusList.htm")
	public String devStatusList() {
		return "devStatus/devStatusListFirst";
	}
	
	
	/**
	 * 查询设施监控数据
	 * @author YHT
	 * @time 2016年7月30日 上午10:57:13
	 * @param request
	 * @param session
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping("/devStatusListEarth.htm")
	public String devStatusList(HttpSession session, String areaCode1, DeviceStatusConditionBean devStaCon, PageBean pb,
			Model model) {
		String areaName = null;
		if (areaCode1 == null || "".equals(areaCode1)) {
			Users user = (Users) session.getAttribute("platformUser");
			Organizition o = null;
			if (user != null)
				o = organizitionService.selectById(user.getOrgId());
			if (o != null)
				areaCode1 = o.getCode1();

			// 根据"市辖区"搜索地图位置,因目标不明确,地图会不显示
			if (o.getCode2() == null || "0".equals(o.getCode2())) {
				Areas area = areasService.selectByCode(areaCode1);
				areaName = area.getAreaName();// 惠州市
			} else {
				Areas area2 = areasService.selectByCode(o.getCode2());// 市辖区
				Areas area1 = areasService.selectByCode(o.getCode1());// 惠州市
				if ("市辖区".equals(area2.getAreaName())) {
					areaName = area1.getAreaName();
					model.addAttribute("centerName", areaName);
					areaName = area1.getAreaName() + area2.getAreaName();// 惠州市市辖区>>地图可以显示
				} else {
					areaName = area1.getAreaName();
					// 惠州市惠城区
					model.addAttribute("centerName", areaName);
				}
			}
			List<AreasParent> list = areasService.selectByParentCode(areaCode1);
			model.addAttribute("areaList", list);
		} else {
			Users user = (Users) session.getAttribute("platformUser");
			List<AreasParent> list = areasService.selectByParentCode(areaCode1);
			if (list !=null && list.size()>0) {
				model.addAttribute("areaList", list);
				for (int i = 0; i < list.size(); i++) {
					areaName+=list.get(i).getParentAreaName()+",";
					areaName=areaName.substring(0, areaName.length()-1);
				}
			}else {
				Areas area = areasService.selectByCode(areaCode1);
				areaName = area.getAreaName();// 惠州市
				model.addAttribute("centerName", areaName);
			}
		}
		model.addAttribute("areaName", areaName);
		model.addAttribute("areacode", areaCode1);
		return "devStatus/devStatusListEarth";
	}

	/**
	 * 页面标志枚举
	 * @author fl
	 *
	 */
	public enum PageFlag{
		switchRecord,//开锁记录
		devTimeNewStatus,//定时上报
		deviceAlarm//报警记录
	}
	
	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 查询报警记录
	 * @param request
	 * @param devId
	 * @return
	 */
	@RequestMapping("/queryDeviceAlarm.htm")
	public String queryDeviceAlarmByDevId(HttpServletRequest request,Long devId,String devName,String areaCode1,HttpSession session,Model model,PageBean pb){
		pb.setPageSize(15);
		Users user = (Users) session.getAttribute("platformUser");
		model.addAttribute("pb", deviceAlarmService.queryDeviceAlarmByDevId(devId,areaCode1,pb,user));
		model.addAttribute("devId", devId);
		model.addAttribute("devName", devName);
		model.addAttribute("flag", PageFlag.deviceAlarm);//导航用
		return "devStatus/devStatusListExcle";
	}
	
	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 定时上报记录
	 * @param request
	 * @param devId
	 * @return
	 */
	@RequestMapping("/querydevTimeNewStatu.htm")
	public String querydevTimeNewStatuByDevId(Long devId,String devName,Model model,PageBean pb){
			pb.setPageSize(20);
			model.addAttribute("pb", deviceStatusService.querydevTimeNewStatuByDevId(devId,pb));
			model.addAttribute("devId", devId);
			model.addAttribute("devName", devName);
			model.addAttribute("flag", PageFlag.devTimeNewStatus);//导航用
			return "devStatus/devTimeNewStatuExcle";
	}
		
	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 手机开锁记录
	 * @param request
	 * @param devId
	 * @return
	 */
	@RequestMapping("/querySwitchRecord.htm")
	public String querySwitchRecordByDevId(Long devId,String devName,String areaCode1,Model model,PageBean pb,HttpSession session){
		pb.setPageSize(15);
		Users user = (Users) session.getAttribute("platformUser");
		model.addAttribute("pb", mobileSwitchService.querySwitchRecordByDevId(devId,pb,user));
		model.addAttribute("devId", devId);
		model.addAttribute("devName", devName);
		model.addAttribute("flag", PageFlag.switchRecord);//导航用
		return "devStatus/devMobileSwitchExcle";
	}
	
	/**
	 * 
	 * @Title: devNewStatusList
	 * @Description: 设施最新状态列表，改名为设备当前状态
	 * @param @param
	 *            session
	 * @param @param
	 *            devStaCon
	 * @param @param
	 *            pb
	 * @param @param
	 *            model
	 * @param @return
	 * @return String 入参
	 * @return String 返回类型
	 * @author DZY (修改)
	 * @throws @date
	 *             2017年12月22日 下午2:11:35
	 * @version V1.0
	 */
	@RequestMapping("/devNewStatusList.htm")
	public String devNewStatusList(HttpSession session, DeviceStatusConditionBean devStaCon, PageBean pb, Model model) {
		Users user = (Users) session.getAttribute("platformUser");

		pb = deviceStatusService.queryCurStatusByConditionBean(devStaCon, user, pb);
		model.addAttribute("pb", pb);
		model.addAttribute("oprStyleMap", Constants.oprStyleMap);
		model.addAttribute("doorAndLockStatusMap", Constants.doorAndLockStatusMap);
		model.addAttribute("isAlarmMap", Constants.isAlarmMap);
		model.addAttribute("alarmTypeMap", Constants.alarmTypeMap);
		model.addAttribute("devStaCon", devStaCon);
		return "devStatus/devNewStatusList";
	}

	/**
	 * 定时上报记录
	 * 
	 * @Title: DevStatusController.java
	 * @Description: TODO
	 * @param @param
	 *            session
	 * @param @param
	 *            devStaCon
	 * @param @param
	 *            pb
	 * @param @param
	 *            model
	 * @param @return
	 * @return String
	 * @author fl
	 * @date 2018年2月5日 上午10:19:55
	 * @version V1.0
	 */
	@RequestMapping("/devTimeNewStatusList.htm")
	public String devTimeNewStatusList(HttpSession session, DeviceStatusConditionBean devStaCon, PageBean pb,
			Model model) {
		Users user = (Users) session.getAttribute("platformUser");

		pb = deviceStatusService.devTimeNewStatusList(devStaCon, user, pb);
		model.addAttribute("navFlag", NavFlag.ReportOnTime);
		model.addAttribute("pb", pb);
		model.addAttribute("oprStyleMap", Constants.oprStyleMap);
		model.addAttribute("doorAndLockStatusMap", Constants.doorAndLockStatusMap);
		model.addAttribute("isAlarmMap", Constants.isAlarmMap);
		model.addAttribute("devStaCon", devStaCon);
		return "devStatus/devTimeNewStatusList";
	}

	/**
	 * 定时上报记录导出
	 * 
	 * @Title: DevStatusController.java
	 * @Description: TODO
	 * @param @param
	 *            request
	 * @param @param
	 *            pb
	 * @param @param
	 *            session
	 * @param @param
	 *            deviceAlarmCon
	 * @param @return
	 * @return Object
	 * @author fl
	 * @date 2018年2月5日 下午1:40:20
	 * @version V1.0
	 */
	@RequestMapping("/exportdevTimeNewStatus.htm")
	@ResponseBody
	public Object exportdevAlarm(HttpServletRequest request, PageBean pb, HttpSession session,
			DeviceStatusConditionBean devStaCon) {
		Result r = Result.get();
		try {

			Users user = (Users) session.getAttribute("platformUser");
			pb = deviceStatusService.devTimExportNewStatus(devStaCon, user, pb);
			// List<DeviceStatus> list=deviceStatusService.devTimexportNewStatus(devStaCon,
			// user);
			List<String[]> columNames = new ArrayList<String[]>();
			columNames.add(new String[] { "设施编码", "设施名称", "数据类型", "锁状态", "门状态", "温度", "湿度", "倾斜", "电压", "信号", "自动上报时间",
					"是否报警","报警内容" });

			List<String[]> fieldNames = new ArrayList<String[]>();
			fieldNames.add(new String[] { "devCode", "fdevName", "opStyleName", "lockStatusName", "doorStatusName",
					"temp", "humidity", "tilt", "battery", "signals", "dateTime", "isAlarmName","alarmContent" });

			LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
			map.put("定时上报记录", pb.getList());

			ExcelExportData setInfo = new ExcelExportData();
			setInfo.setDataMap(map);
			setInfo.setFieldNames(fieldNames);
			setInfo.setTitles(new String[] { "定时上报记录" });
			setInfo.setColumnNames(columNames);

			String path = request.getSession().getServletContext().getRealPath("/") + "/export/";
			Date date = new Date();
			String fileName1 = "定时上报记录" + date.getTime() + ".xls";
			String pathAndFileName = path + fileName1;
			if (ExcelUtil.export2File(setInfo, pathAndFileName)) {
				r.setR(1);
				Map<String, String> rMap = new HashMap<String, String>();
				rMap.put("filePath", "/export/" + fileName1);
				r.setDt(rMap);
				r.putRContent("导出成功！");
			} else {
				r.setR(0);

				r.putRContent("导出失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return r;
	}

}
