package com.ycnet.device.controller;

import java.text.SimpleDateFormat;
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

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.core.Constants;
import com.ycnet.core.util.ExcelUtil;
import com.ycnet.core.util.PageBean;
import com.ycnet.facility.controller.LocksController.NavFlag;
import com.ycnet.facility.controller.LocksController.SignSelect;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.DeviceImage;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.DeviceAlarmService;
import com.ycnet.frms.service.DeviceImageService;
import com.ycnet.frms.vo.DeviceAlarmBean;
import com.ycnet.frms.vo.DeviceAlarmConditionBean;
import com.ycnet.mobile.util.Result;
import com.ycnet.core.util.ExcelUtil.ExcelExportData;

@Controller
public class DeviceAlarmController {
	
	@Resource(name="deviceAlarmService")
	private DeviceAlarmService deviceAlarmService;
	
	@Resource(name="deviceImageService")
	private DeviceImageService deviceImageService;
	
	/**
	 * 
	 * @Title: deviceAlarmList
	 * @Description: 报警管理列表
	 * @param @param session
	 * @param @param deviceAlarmCon
	 * @param @param pb
	 * @param @param model
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY (仅注释)
	 * @throws
	 * @date 2017年12月29日 下午1:35:37
	 * @version V1.0
	 */
	@RequestMapping("/deviceAlarmList.htm")
	public String deviceAlarmList(HttpSession session ,DeviceAlarmConditionBean deviceAlarmCon,PageBean pb ,Model model)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		pb=deviceAlarmService.queryByConditionBean(deviceAlarmCon,user,pb);
		model.addAttribute("pb",pb);
		model.addAttribute("navFlag",NavFlag.AlarmRecord);
		//model.addAttribute("deviceTypeList",basecodeService.getDEVTypeList());
		//model.addAttribute("areasServiceList",areasService.selectByOrgId(user.getOrgId()));
		model.addAttribute("dealSignList",Constants.DealSignList);		
		model.addAttribute("deviceAlarmCon",deviceAlarmCon);
		model.addAttribute("alarmTypesList", alarmTypesList);
		
		return "deviceAlarm/deviceAlarmList";
		
	}
	
	/**
	 * 
	 * @Title: queryAlarmImage
	 * @Description: 找到对应开锁记录的图片
	 * @param @param request
	 * @param @param session
	 * @param @param alarmProcessId
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月29日 下午1:38:36
	 * @version V1.0
	 */
	@RequestMapping("/queryAlarmImage.htm")
	@ResponseBody
	public Object queryAlarmImage(HttpServletRequest request ,HttpSession session ,Long alarmProcessId) {
		Map<String,Object> map = new HashMap<String,Object>();
		DeviceAlarm da = deviceAlarmService.selectById(alarmProcessId);//查询当前的开关锁记录
		DeviceImage image = deviceImageService.queryByImeiAndColtime(da.getDevImei(),"2",da.getAlarmTime());//2报警数据
//		List<DeviceImage> images = deviceImageService.queryByImei(da.getDevImei());//根据IMEI查询图片信息
//		long timeMin = 120;
//		long timedif = 0;
//		long time = 0;
//		int k = 0;
//		if(images.size()>0) {
//			//循环取得开锁记录时间和图片记录时间最接近的图片数据
//			for (int i = 0; i < images.size(); i++) {
//				if((timedif = Math.abs(images.get(i).getCreateTime().getTime() - da.getRptTime().getTime())/1000)<120) {
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
	 * @Title: deviceAlarmCtrlSave
	 * @Description: 报警查询记录页面，报警处理操作
	 * @param @param deviceAlarm
	 * @param @param session
	 * @param @param model
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY (添加注释,添加处理报警时修改设备状态操作)
	 * @throws
	 * @date 2018年2月11日 下午5:12:27
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/deviceAlarmCtrlSave.htm")
	public Object deviceAlarmCtrlSave(DeviceAlarm deviceAlarm,HttpSession session ,Model model)
	{		
		Result r = Result.get();
		try {
			Users user=(Users)session.getAttribute("platformUser");	
			int status = deviceAlarmService.deviceAlarmCtrlSave(deviceAlarm,user);
			if(status>0)
			{
				r.setR(1);
				r.putRContent("处理成功");
			}
			else
			{
				r.setR(0);
				r.putRContent("处理失败");
			}
			
		} catch (Exception e) {
			r.setR(0);
			r.putRContent("处理失败");
			e.printStackTrace();
		}
		
		return r;		
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 报警记录查询导出数据
	* 
	* @Title: DeviceAlarmController.java 
	* @Description: TODO 
	* @param @return
	* @return String
	* @author fl
	* @date 2018年2月5日 上午11:40:53
	* @version V1.0
	 */
	@RequestMapping("/exportdevAlarm.htm")
	@ResponseBody
	public Object exportdevAlarm(HttpServletRequest request,PageBean pb,HttpSession session,DeviceAlarmConditionBean deviceAlarmCon) {
			Result r = Result.get();
			try {
			
			Users user=(Users)session.getAttribute("platformUser");	
			pb=deviceAlarmService.queryexport(deviceAlarmCon,user,pb);
			List<String[]> columNames = new ArrayList<String[]>();
			columNames.add(new String[] { "设施编码", "设施名称", "报警时间", "报警内容","是否处理", "处理情况",
					"处理人" });
			
			List<String[]> fieldNames = new ArrayList<String[]>();
			fieldNames.add(new String[] { "devCode", "fdevName", "dateTime", "alarmContent","dealSignName", "dealSituation",
					"userName"});
			
			LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
			map.put("报警记录", pb.getList());

			ExcelExportData setInfo = new ExcelExportData();
			setInfo.setDataMap(map);
			setInfo.setFieldNames(fieldNames);
			setInfo.setTitles(new String[] { "报警记录" });
			setInfo.setColumnNames(columNames);

			String path = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/export/";
			Date date = new Date();
			String fileName1 = "报警记录" + date.getTime()+".xls";
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
	
	public static List<SignSelect> alarmTypesList = Arrays.asList(
			new SignSelect("01","阈值告警"),
			new SignSelect("02","水浸告警"),
			new SignSelect("03","震动告警"),
			new SignSelect("04","门锁异常告警"),
			new SignSelect("05","门超时告警"),
			new SignSelect("06","锁超时告警"),
			new SignSelect("07","电量告警"),
			new SignSelect("8","非法撬锁"),
			new SignSelect("13","布防APP开锁"),
			new SignSelect("14","布防平台远程开锁"),
			new SignSelect("15","布防钥匙开锁"),
			new SignSelect("16","布防蓝牙开锁"),
			new SignSelect("17","通讯终端告警")
	);
}
