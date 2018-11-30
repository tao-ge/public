package com.ycnet.facility.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.CharEncoding;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.core.Constants;
import com.ycnet.core.FrmsException;
import com.ycnet.core.config.SessionName;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.Cable;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.ResourceDataLog;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.service.CableSectionService;
import com.ycnet.frms.service.CableService;
import com.ycnet.frms.service.DeviceLockStatusEntityService;
import com.ycnet.frms.service.DeviceRegService;
import com.ycnet.frms.service.DeviceStatusService;
import com.ycnet.frms.service.FacilityImgService;
import com.ycnet.frms.service.FacilityInspectService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscGroupService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.service.MobileSwitchService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.ResourceDataLogService;
import com.ycnet.frms.service.RouteService;
import com.ycnet.frms.service.UsersService;
import com.ycnet.frms.vo.CableSectionBean;
import com.ycnet.frms.vo.CableSectionConditionBean;
import com.ycnet.frms.vo.DeviceLockStatusEntityVo;
import com.ycnet.frms.vo.DeviceStatusConditionBean;
import com.ycnet.frms.vo.FacilityBean;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityImgConditionBean;
import com.ycnet.frms.vo.FacilityInspectConditionBean;
import com.ycnet.frms.vo.Group;
import com.ycnet.frms.vo.RouteBean;
import com.ycnet.mobile.util.Result;

@Controller
public class FacilityDetailController {

	@Resource(name="facilityService")
	private FacilityService facilityService;
	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	@Resource(name="areasService")
	private AreasService areasService;
	@Resource(name="cableSectionService")
	private CableSectionService cableSectionService;

	@Resource(name="deviceStatusService")
	private DeviceStatusService deviceStatusService;
	@Resource(name="facilityImgService")
	private FacilityImgService facilityImgService;
	@Resource(name="facilityInspectService")
	private FacilityInspectService inspectService;
	
	@Resource(name="organizitionService")
	private OrganizitionService  organizitionService;
	
	@Resource(name="usersService")
	private UsersService usersService;

	
	@Resource(name="mobileSwitchService")
	private MobileSwitchService mobileSwitchService;

	@Resource(name="cableService")
	private CableService cableService;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;

	@Resource(name="deviceRegService")
	DeviceRegService deviceRegService;
	
	@Resource(name = "routeService")
	private RouteService routeService;
	
	@Resource(name = "fiberdiscGroupService")
	private FiberdiscGroupService fiberdiscGroupService;
	
	@Resource(name="resourceDataLogService")
	private ResourceDataLogService resourceDataLogService;
	
	@Resource(name="deviceLockStatusEntityService")
	private DeviceLockStatusEntityService deviceLockStatusEntityService;
	
	private static final Logger LOG = LoggerFactory.getLogger(FacilitysController.class);
	
	
	
	/**
	 * 设施基本信息
	 * fl修改增加汇聚区名称
	 * @param request
	 * @param session
	 * @param devId
	 * @param model
	 * @return
	 */
	@RequestMapping("/facilityBaseInfo.htm")
	public String facilityBaseInfo(Long devId,Model model)
	{
		FacilityBean f=facilityService.selectDetailById(devId);
		String date = null;
		String code="";
		if(f!=null){
			if (f.getAreaCode()!=null && !"".equals(f.getAreaCode())) {
				code=f.getAreaCode();
			}
			Areas area = areasService.selectByCode(code);
			if (area !=null && area.getAreaName()!=null && !"".equals(area.getAreaName())) {
				f.setAreaName1(area.getAreaName());
			}
			if(f.getCompleteDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.format(f.getCompleteDate()); 
			}
		
			if(f.getLastModifyUser()!=null){
				Users users = usersService.selectByPrimaryKey(f.getLastModifyUser());
				if(users.getUserName()!=null){
					model.addAttribute("userName",users.getUserName());
				}
			}
			if(Constants.CheckTypeMap.containsKey(f.getCheckType()))
			{
				f.setCheckTypeName(Constants.CheckTypeMap.get(f.getCheckType()));
			}
			if(f.getCheckUserId() != null) {
				Users user = usersService.selectByPrimaryKey(f.getCheckUserId());
				if(user != null) {
					f.setCheckUserName(user.getUserName());
				}else {
					f.setCheckUserName("");
				}
				
			}
		}
		model.addAttribute("date",date);
		model.addAttribute("facility",f);		
		model.addAttribute("devId",devId);//必须，页面导航用
		model.addAttribute("devName",f.getDevName());//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.baseInfo);//必须，页面导航用
		return "facility/facilityBaseInfo";
	}
	
	@RequestMapping("/cableSectionList.htm")
	public String cableSectionList(String devName,Model model,CableSectionConditionBean csb)
	{
		List<CableSectionBean> csbs=cableSectionService.queryBeanByConditionBean(csb);
		FacilityBean f=facilityService.selectDetailById(csb.getDevId());
		model.addAttribute("csbs",csbs);
		
		model.addAttribute("csb", csb);
		model.addAttribute("devId",csb.getDevId());//必须，页面导航用
		model.addAttribute("devName",devName);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.cableSectionList);//必须，页面导航用
		
		return "facility/cableSectionList";
	}
	/**
	 * 导出光缆段列表
	 * @param request
	 * @param response
	 * @param session
	 * @param devId
	 * 修改:刘沧海,合并单元格重新编写
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cableSectionExport.htm")
	public Result exportData(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			Long devId){
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("端口成端"); 
		//给单子名称一个长度
		sheet.setDefaultColumnWidth((int)15);
		// 生成一个样式 
		HSSFCellStyle style = wb.createCellStyle();
		// 设置四边的边框 
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		sheet.setColumnWidth(0, 20*256);      
		
		// 设置水平对齐的样式为居中对齐; 
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		// 创建第一行（也可以称为表头）
		HSSFRow row = sheet.createRow(0);
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((int) 15);
        // 样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 给表头第一行一次创建单元格
        
        
		HSSFCell cell = row.createCell((int) 0); //
		
		cell.setCellValue("光缆段名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 1);
		
		cell.setCellValue("光缆段编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 2);
		
		cell.setCellValue("纤芯数量");
		cell.setCellStyle(style);
		cell = row.createCell((int) 3);
		
		cell.setCellValue("纤芯序号");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("A端设施名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("A端设施编号");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
		cell.setCellValue("A端成端开始位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 7);
		
		cell.setCellValue("A端成端结束位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 8);
		
		cell.setCellValue("Z端设施名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 9);
		
		cell.setCellValue("Z端设施编号");
		cell.setCellStyle(style);
		cell = row.createCell((int) 10);
		
		cell.setCellValue("Z端成端开始位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 11);
		
		cell.setCellValue("Z端成端结束位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 12);
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(user.getOrgId()+"用户ID");
		System.out.println(devId+"ID");
		List<Cable> list1 = cableService.queryCableDevId(user.getOrgId(),devId);
		if(list1!=null){
		System.out.println(list1.size()+"数量");
		for (int i = 0; i < list1.size(); i++) {

			row = sheet.createRow(i + 1);

			cell = row.createCell(0);
			if(list1.get(i).getSecName()!=null){
			cell.setCellValue((list1.get(i)).getSecName());
			cell.setCellStyle(style);
			cell = row.createCell(1);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(1);
			}
			
			if(list1.get(i).getSecCode()!=null){
				cell.setCellValue((list1.get(i)).getSecCode());
				cell.setCellStyle(style);
				cell = row.createCell(2);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(2);
			}
			
			if(list1.get(i).getFiberNum()!=null){
				cell.setCellValue(( list1.get(i)).getFiberNum());
				cell.setCellStyle(style);
				cell = row.createCell(3);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(3);
			}
			
			if(list1.get(i).getFiberNo()!=null){
				cell.setCellValue((list1.get(i)).getFiberNo());
				cell.setCellStyle(style);
				cell = row.createCell(4);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(4);
			}
			
			if(list1.get(i).getAdevName()!=null){
				cell.setCellValue((list1.get(i)).getAdevName());
				cell.setCellStyle(style);
				cell = row.createCell(5);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(5);
			}
			
			if(list1.get(i).getACodeDev()!=null){
				cell.setCellValue((list1.get(i)).getACodeDev());
				cell.setCellStyle(style);
				cell = row.createCell(6);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(6);
			}
			
			if(list1.get(i).getCdAcodeSta()!=null){
				cell.setCellValue((list1.get(i)).getCdAcodeSta());
				cell.setCellStyle(style);
				cell = row.createCell(7);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(7);
			}
			
			if(list1.get(i).getCdAcodeEnd()!=null){
				cell.setCellValue((list1.get(i)).getCdAcodeEnd());
				cell.setCellStyle(style);
				cell = row.createCell(8);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(8);
			}
			
			
			if(list1.get(i).getZdevName()!=null){
				cell.setCellValue((list1.get(i)).getZdevName());
				cell.setCellStyle(style);
				cell = row.createCell(9);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(9);
			}
			
			if(list1.get(i).getZCodeDev()!=null){
				cell.setCellValue((list1.get(i)).getZCodeDev());
				cell.setCellStyle(style);
				cell = row.createCell(10);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(10);
			}
			
			if(list1.get(i).getCdZcodeSta()!=null){
				cell.setCellValue((list1.get(i)).getCdZcodeSta());
				cell.setCellStyle(style);
				cell = row.createCell(11);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(11);
			}
			
			if(list1.get(i).getCdZcodeEnd()!=null){
				cell.setCellValue((list1.get(i)).getCdZcodeEnd());
				cell.setCellStyle(style);
				cell = row.createCell(12);
			}else{
				cell.setCellValue("");
				cell.setCellStyle(style);
				cell = row.createCell(12);
			}
			
		}
		if(list1.size()>0){
			//自适应列宽
			sheet.autoSizeColumn((int)0,true);
			sheet.autoSizeColumn((int)1,true);
			sheet.autoSizeColumn((int)2,true);
			sheet.autoSizeColumn((int)3,true);
			sheet.autoSizeColumn((int)4,true);
			sheet.autoSizeColumn((int)5,true);
			sheet.autoSizeColumn((int)6,true);
			sheet.autoSizeColumn((int)7,true);
			sheet.autoSizeColumn((int)8,true);
			sheet.autoSizeColumn((int)9,true);
			sheet.autoSizeColumn((int)10,true);
			sheet.autoSizeColumn((int)11,true);
			sheet.autoSizeColumn((int)12,true);
			
			
			int rowCount1=sheet.getLastRowNum();//最后一行
			HSSFCell obj1=null;  
			HSSFCell obj12=null;  
			//合并光缆段名称
			String temp = sheet.getRow(1).getCell(1).getStringCellValue();
			int firstrow=0;
			for (int j = 2; j <= rowCount1; j++) {
				obj1=sheet.getRow(j).getCell(1);
				if(temp.equals(obj1.getStringCellValue()))
				{
					firstrow++;
					if(j==rowCount1) {
						sheet.addMergedRegion(new CellRangeAddress(j-firstrow, j, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(j-firstrow, j, 1, 1));
						sheet.addMergedRegion(new CellRangeAddress(j-firstrow, j, 2, 2));
					}
				}else {
					if(firstrow!=0) {
						sheet.addMergedRegion(new CellRangeAddress(j-firstrow-1, j-1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(j-firstrow-1, j-1, 1, 1));
						sheet.addMergedRegion(new CellRangeAddress(j-firstrow-1, j-1, 2, 2));
						firstrow=0;
					}
					temp=sheet.getRow(j).getCell(0).getStringCellValue();
				}
			}
		}else{
			r.setR(1);
			Map<String,String> rMap=new HashMap<String,String>();
			rMap.put("filePath", "1");
			r.setDt(rMap);
			r.putRContent("无数据！");
			return r;
		}
	}
		String path = request.getSession().getServletContext().getRealPath("/")
				+"/export/";
		Date date = new Date();
		String fileName = "光缆段详细数据" + date.getTime() + ".xls";
		String pathAndFileName = path +fileName;
		
		System.out.println(pathAndFileName);
		File file = new File(pathAndFileName);
		
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (file.exists()) {
        	file.delete();
        }
		FileOutputStream out =new FileOutputStream(file);
		wb.write(out); 
		out.close();
		
		
		r.setR(1);
		Map<String,String> rMap=new HashMap<String,String>();
		rMap.put("filePath", "/export/"+fileName);
		r.setDt(rMap);
		r.putRContent("导出成功！");
		System.out.println("导出成功！");
		} catch (Exception e) {
			e.printStackTrace();
			r.putRContent("导出异常！");
		    }
		return r;
	}
	
	@RequestMapping("/sonFacilityList.htm")
	public String sonFacilityList(Long devId,String devName,Model model,String devCode,String devType,String name)
	{
		FacilityConditionBean facilityCon=new FacilityConditionBean();
		facilityCon.setPdevId(devId);
		facilityCon.setPdevName(devName);
		facilityCon.setDevName(name);
		facilityCon.setDevCode(devCode);
		facilityCon.setDevType(devType);
		PageBean pb=new PageBean();
		pb.setPageSize(2000);
		model.addAttribute("pb",facilityService.queryByConditionBean(facilityCon,null,pb));
		model.addAttribute("deviceTypeList",basecodeService.getDEVTypeList());//设施类型
		model.addAttribute("name",name);
		model.addAttribute("devCode", devCode);
		model.addAttribute("devType", devType);
		model.addAttribute("devId",devId);//必须，页面导航用
		model.addAttribute("devName",devName);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.sonfacility);//必须，页面导航用
		
		return "facility/sonFacilityList";
		
	}

	
	@RequestMapping("/devDetailStatusList.htm")
	public String devDetailStatusList(Long devId,String devName,Model model,DeviceLockStatusEntityVo devStaCon,PageBean pb)
	{
		if(devStaCon == null){
			devStaCon = new DeviceLockStatusEntityVo();
		}
		devStaCon.setDevId(devId);
		//pb.setPageSize(1);
		model.addAttribute("pb", deviceLockStatusEntityService.queryByConditionBean(devStaCon,pb));
//		model.addAttribute("oprStyleMap",Constants.oprStyleMap);
		model.addAttribute("oprStyleMap",Constants.oprStyleMap1);
		model.addAttribute("doorAndLockStatusMap",Constants.doorAndLockStatusMap);
		model.addAttribute("isAlarmMap",Constants.isAlarmMap);
		model.addAttribute("alarmTypeMap",Constants.alarmTypeMap);
		model.addAttribute("devStaCon",devStaCon);
		
		model.addAttribute("devId",devId);//必须，页面导航用
		model.addAttribute("devName",devName);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.devStatus);//必须，页面导航用
		return "facility/devDetailStatusList";
	}
	
	/**
	 * 
	 * @Title: devDetailStatusList
	 * @Description: 干城设施详情监控数据
	 * @param @param devId
	 * @param @param devName
	 * @param @param model
	 * @param @param devStaCon
	 * @param @param pb
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月14日 下午4:43:03
	 * @version V1.0
	 */
	@RequestMapping({"/devDetailStatusList1.htm"})
	  public String devDetailStatusList(Long devId, String devName, Model model, DeviceStatusConditionBean devStaCon, PageBean pb)
	  {
	    if (devStaCon == null) {
	      devStaCon = new DeviceStatusConditionBean();
	    }
	    devStaCon.setDevId(devId);

	    model.addAttribute("pb", this.deviceStatusService.queryByConditionBean(devStaCon, null, pb));
	    model.addAttribute("oprStyleMap", Constants.oprStyleMap);
	    model.addAttribute("doorAndLockStatusMap", Constants.doorAndLockStatusMap);
	    model.addAttribute("isAlarmMap", Constants.isAlarmMap);
	    model.addAttribute("alarmTypeMap", Constants.alarmTypeMap);
	    model.addAttribute("devStaCon", devStaCon);

	    model.addAttribute("devId", devId);
	    model.addAttribute("devName", devName);
	    model.addAttribute("pageFlag", PageFlag.devStatus);
	    return "facility/devDetailStatusList";
	  }
	
	@RequestMapping("/facilityImgList.htm")
	public String facilityImgList(Long devId,String devName,Model model,FacilityImgConditionBean facilityImgCon,PageBean pb)
	{
		facilityImgCon.setDevId(devId);
//		PageBean pb=new PageBean();
		pb.setPageSize(6);
		model.addAttribute("pb",facilityImgService.queryByConditionBean(facilityImgCon,null,pb));
		model.addAttribute("facilityImgCon",facilityImgCon);
		
		model.addAttribute("devId",devId);//必须，页面导航用
		model.addAttribute("devName",devName);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.facilityImg);//必须，页面导航用
		
		return "facility/facilityImgList";
		
	}
	
	@RequestMapping("/facilityInspectList.htm")
	public String facilityInspectList(Long devId,String devName,Model model,FacilityInspectConditionBean bean,PageBean pb,HttpSession session)
	{
		Users user=(Users)session.getAttribute(SessionName.platform);
		bean.setDevId(devId);
//		PageBean pb=new PageBean();
		pb.setPageSize(10);
		model.addAttribute("pb",inspectService.queryByConditionBeans(bean,pb,user));
		model.addAttribute("inspectList",bean);
		
		model.addAttribute("devId",devId);//必须，页面导航用
		model.addAttribute("devName",devName);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.facilityInspect);//必须，页面导航用
		
		return "facility/facilityInspectList";
		
	}

	@RequestMapping("/facilitySwitchList.htm")
	public String facilitySwitchList(Model model,PageBean pb,MobileSwitch ms,HttpSession session)
	{
		Users user=(Users)session.getAttribute(SessionName.platform);
		pb =mobileSwitchService.queryByConditionBean(ms, pb,user);
		model.addAttribute("ms",ms);
		model.addAttribute("pb",pb);		
		model.addAttribute("devId",ms.getDevId());//必须，页面导航用
		model.addAttribute("devName",ms.getDevName());//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.mobileSwitch);//必须，页面导航用
		return "facility/facilitySwitchRecord";
	}

	/**
	 * 
	* @Title: facilityGroups 
	* @Description: TODO(把终端开关锁改变为设施分组信息) 
	* @param @param devId
	* @param @return    入参
	* @return String    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年9月26日 下午4:05:17 
	* @version V1.0
	 */
	@RequestMapping("/facilityGroups.htm")
	public String facilityGroups(Long devId,Model model) {
		List<FiberdiscGroup> fiberDiscGroup=fiberdiscGroupService.queryByDevId(devId);
		Facility fa = facilityService.selectById(devId);
		model.addAttribute("fiberGroup", fiberDiscGroup);
		model.addAttribute("devId",fa.getDevId());//必须，页面导航用
		model.addAttribute("devName",fa.getDevName());//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.facilityGroup);//必须，页面导航用
		return "facility/facilityGroups";
	}
	
	/**
	 * 
	 * @Title: modifyfacilityGroups
	 * @Description: 设施分组修改跳转页
	 * @param @param groupId
	 * @param @param model
	 * @param @return 
	 * @return String 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月15日 下午4:30:59
	 * @version V1.0
	 */
	@RequestMapping("/modifyfacilityGroups.htm")
	public String modifyfacilityGroups(Long groupId,Model model) {
		FiberdiscGroup group = fiberdiscGroupService.selectById(groupId);
		model.addAttribute("group", group);
		return "facility/facilityGroupsEdit";
	}
	
	/**
	 * 
	 * @Title: facilityGroupsSave
	 * @Description: 设施分组保存
	 * @param @param group
	 * @param @param session
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月15日 下午5:01:18
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/facilityGroupsSave.htm")
	public Object facilityGroupsSave(FiberdiscGroup group,HttpSession session) {
		Result r = Result.get();
		try {
			Users users=(Users)session.getAttribute("platformUser");
			int req = fiberdiscGroupService.facilityGroupsSave(group,users);
			if(req>0) {
				r.putR(1);
				r.putRContent("保存成功!");
			}else {
				r.putR(0);
				r.putRContent("保存失败!");
			}
		} catch (Exception e) {
			r.putR(0);
			r.putRContent("保存失败!");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	 * @Title: deleteFacilityGroups
	 * @Description: 删除设施分组
	 * @param @param group
	 * @param @param session
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月16日 下午3:21:43
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/deleteFacilityGroups.htm")
	public Object deleteFacilityGroups(FiberdiscGroup group,HttpSession session) {
		Result r = Result.get();
		Users user=(Users)session.getAttribute("platformUser");
		ResourceDataLog rdl = new ResourceDataLog();
		Long groupId = group.getGroupId();
		try {
			if(groupId==null) {
				r.putR(0);
				r.putRContent("熔纤盘分组ID不能为空！");
				return r;
			}
			FiberdiscGroup fg = fiberdiscGroupService.selectById(groupId);
			rdl.setHisContent("分组编码:"+fg.getSide()+",分组名称:"+fg.getGroupName());//操作前的内容
			int reg=fiberdiscGroupService.deleteFiberdiscGroup(groupId,user);
			if(reg>0) {
				rdl.setResLogType("02");//日志类型,02分组
				rdl.setHandleType("2");//操作类型,2删除
				rdl.setHandleId(groupId);//操作分组的ID
				rdl.setModifyUserId(user.getUserId());//操作人ID
				rdl.setModifyUserName(user.getUserName());//操作人名称
				rdl.setModifyTime(new Date());//操作时间
				resourceDataLogService.saveResourceDataLog(rdl);//添加资管校准日志数据
				r.putR(1);
				r.putRContent("删除成功！");
			}else {
				r.putR(0);
				r.putRContent("删除失败！");
			}
		} catch (FrmsException e1) {
			r.putR(0);
			r.putRContent(e1.getMessage());
		} catch (Exception e) {
			r.putR(0);
			r.putRContent("删除失败！");
			e.printStackTrace();
		}
		return r;
	}
	
	
	@RequestMapping("/cableSectionLoadAdd.htm")
	public Object cableSectionloadAdd(Long devId,Model model)
	{
		
		
		model.addAttribute("surveyResultList",Constants.SurveyResultList);
		model.addAttribute("devId",devId);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.cableSectionList);//必须，页面导航用
		
		return "facility/cableSectionAdd";
	}
	
	@RequestMapping("/cableSectionLoadUpdate.htm")
	public Object cableSectionLoadUpdate(Long devId,Long sectId,Model model)
	{

		CableSection cableSection = cableSectionService.selectById(sectId);
		CableSectionBean cableSectionBean = null;
		if(cableSection != null){
			cableSectionBean = cableSectionService.convert(cableSection);
		}
		model.addAttribute("surveyResultList",Constants.SurveyResultList);
		model.addAttribute("sect",cableSectionBean);
		model.addAttribute("devId",devId);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.cableSectionList);//必须，页面导航用
		
		return "facility/cableSectionUpdate";
	}
	
	@RequestMapping("/sectSave.htm")
	public Object cableSectionEdit(HttpServletRequest request ,HttpSession session ,Long devId,Model model,CableSection bean)
	{
		Result r = Result.get();
		try {
			
			long status = cableSectionService.save(bean);
			if(status>0)
			{
				r.setR(1);
				r.putRContent("保存成功！");
			}
			else
			{
				r.putRContent("保存失败！");
			}
			
		} catch (Exception e) {
			r.putRContent(e.getMessage());
		}
		
		return r;
	}
	
	@ResponseBody 
	@RequestMapping("/sectDelete.htm")
	public Object sectDelete(Long sectId)
	{
		Result r = Result.get();
		try {
			if(sectId==null){
				r.setR(0);
				r.putRContent("光缆段ID不能为空！");
				
				return r;
			}
			
			int status = cableSectionService.delete(sectId);
			if(status>0)
			{
				r.setR(1);
				r.putRContent("删除成功！");
			}
			
		} catch (Exception e) {
			r.putRContent(e.getMessage());
		}
		
		return r;
		
	}
	
	@RequestMapping("/sonSave.htm")
	public Object sonFacilityEdit(HttpServletRequest request ,HttpSession session,Model model,Facility bean)
	{
		Result r = Result.get();
		try {
		Users users=(Users)session.getAttribute("platformUser");
		bean.setOrgId(users.getOrgId());
		if(bean.getAreaCode1()==null){
			Organizition o = organizitionService.selectById(users.getOrgId());
			if(o!=null)
				bean.setAreaCode1(o.getCode1());
		}
		if(!"0".equals(bean.getCheckType())) {
			bean.setCheckUserId(users.getUserId());
		}
		System.out.println("进来哦了");
			int status = facilityService.addObd(bean);
			if(status>0)
			{
				r.setR(1);
				r.putRContent("保存成功！");
			}
			else
			{
				r.putRContent("保存失败！");
			}
			
		} catch (Exception e) {
			r.putRContent(e.getMessage());
		}
		
		return r;
	}
	
	@ResponseBody 
	@RequestMapping("/sonDelete.htm")
	public Object sonFacilityDelete(Long devId)
	{
		Result r = Result.get();
		try {
			if(devId==null){
				r.setR(0);
				r.putRContent("设备ID不能为空！");
				
				return r;
			}
			
			int status = facilityService.update(devId);
			if(status>0)
			{
				r.setR(1);
				r.putRContent("删除成功！");
			}
			
		} catch (Exception e) {
			r.putRContent(e.getMessage());
		}
		
		return r;
		
	}
	
	@RequestMapping("/sonFacilityLoadAdd.htm")
	public Object sonFacilityloadAdd(Long devId,Model model)
	{
		
		
		model.addAttribute("surveyResultList",Constants.SurveyResultList);
		model.addAttribute("obdTypeList",basecodeService.getOBDTYPEList());
		model.addAttribute("devId",devId);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.sonfacility);//必须，页面导航用 
		
		return "facility/sonFacilityEdit";
	}
	
	@RequestMapping("/sonFacilityLoadUpdate.htm")
	public Object sonFacilityLoadUpdate(Long devId,Long pdevId,Model model)
	{

		FacilityBean facilityBean = facilityService.selectDetailById(devId);
		model.addAttribute("surveyResultList",Constants.SurveyResultList);
		model.addAttribute("obdTypeList",basecodeService.getOBDTYPEList());
		model.addAttribute("son",facilityBean);
		model.addAttribute("devId",pdevId);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.sonfacility);//必须，页面导航用
		
		return "facility/sonFacilityEdit";
	}
	
	@RequestMapping("/sonFacilityBaseInfo.htm")
	public Object sonFacilityBaseInfo(Long devId,Long pdevId,Model model)
	{

		FacilityBean facilityBean = facilityService.selectDetailById(devId);
		model.addAttribute("surveyResultList",Constants.SurveyResultList);
		model.addAttribute("obdTypeList",basecodeService.getOBDTYPEList());
		model.addAttribute("son",facilityBean);
		model.addAttribute("devId",pdevId);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.sonfacility);//必须，页面导航用
		
		return "facility/sonFacilityBaseInfo";
	}
	 //熔纤盘
	@RequestMapping("/fiberDiscList.htm")
	public String fiberDiscList(Long devId,String devName,Model model)
	{
//		CableSectionConditionBean csb=new CableSectionConditionBean();
//		csb.setDevId(devId);		
//		List<CableSectionBean> csbs=cableSectionService.queryBeanByConditionBean(csb);
//		FacilityBean f=facilityService.selectDetailById(devId);
//		model.addAttribute("csbs",csbs);
		
		List<Group> list = fiberdiscService.selectGroup(devId);
		model.addAttribute("fiberdiscGroup",list);
		model.addAttribute("devId",devId);//必须，页面导航用
		model.addAttribute("devName",devName);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.fiberdiscInfo);//必须，页面导航用
		
		return "facility/fiberDiscList";
	}
	
	
	  @RequestMapping({"/facilityWebSwitchList.htm"})
	  public String facilityWebSwitchList(Model model, PageBean pb, DeviceReg dr, HttpSession session)
	  {
	    Users user = (Users)session.getAttribute(SessionName.platform);
	    List list = this.deviceRegService.queryList(dr, pb, user);
	    pb.setRows(Integer.valueOf(this.deviceRegService.queryCount(dr, user)));
	    model.addAttribute("LockList", list);
	    model.addAttribute("pb", pb);
	    model.addAttribute("SignList", signList);
	    model.addAttribute("sign", (dr.getValidateSign() == null) ? "" : dr.getValidateSign());
	    model.addAttribute("ms", dr);
	    model.addAttribute("devId", dr.getDevId());
	    model.addAttribute("devName", dr.getDevName());
	    model.addAttribute("pageFlag", PageFlag.derive);
	    return "facility/facilityWebSwitchRecord";
	  }
	  
	  @RequestMapping({"/deriveONU.htm"})
	  public String deriveONU(HttpSession session,HttpServletRequest request,Long devId,Long returnNum,PageBean pb,Model model){
		  List<RouteBean> list1 = new ArrayList<RouteBean>();
		  Users user = (Users) session.getAttribute("platformUser");
		  FacilityBean f=facilityService.selectDetailById(devId);
		  String fileName = "";
		  if(f!=null && returnNum!=null){
//			  pb.setPageSize(50);
//			  pb.setRows(Integer.valueOf(routeService.queryCount(user.getOrgId(),f.getDevCode(),devId)));
			  list1 = routeService.queryAllFacility(user.getOrgId(),f.getDevCode(),devId,pb);
			  
			  
			  try {
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet("导出设施数据"); 
				sheet.setDefaultColumnWidth((int)15);
				HSSFCellStyle style = wb.createCellStyle();
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				sheet.setColumnWidth(0, 20*256);      
				
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				HSSFRow row = sheet.createRow(0);
				sheet.setDefaultColumnWidth((int) 15);
		        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        
				HSSFCell cell = row.createCell((int) 0); //
				
				cell.setCellValue("序号");
				cell.setCellStyle(style);
				cell = row.createCell((int) 1);
				
				cell.setCellValue("设备名称");
				cell.setCellStyle(style);
				cell = row.createCell((int) 2);
				
				cell.setCellValue("光缆段名称");
				cell.setCellStyle(style);
				cell = row.createCell((int) 3);
				
				cell.setCellValue("纤芯数量");
				cell.setCellStyle(style);
				cell = row.createCell((int) 4);
				
				cell.setCellValue("纤芯序号");
				cell.setCellStyle(style);
				cell = row.createCell((int) 5);
				
				cell.setCellValue("端口编码");
				cell.setCellStyle(style);
				cell = row.createCell((int) 6);
				
				cell.setCellValue("双芯绑定");
				cell.setCellStyle(style);
				cell = row.createCell((int) 7);
				
				cell.setCellValue("光路文本路由");
				cell.setCellStyle(style);
				cell = row.createCell((int) 8);
				
				cell.setCellValue("Z端跳纤位置");
				cell.setCellStyle(style);
				cell = row.createCell((int) 9);
				
				cell.setCellValue("Z端端口状态");
				cell.setCellStyle(style);
				cell = row.createCell((int) 10);
				
				// 向单元格里填充数据

				for (int i=0; i<list1.size(); i++) {
		
					row = sheet.createRow(i + 1);
	
					cell = row.createCell(0);
					if(list1.get(i).getDevName()!=null){
						cell.setCellValue(i+1);
						cell.setCellStyle(style);
						cell = row.createCell(1);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(1);
					}
					
					cell = row.createCell(1);
					if(list1.get(i).getDevName()!=null){
						cell.setCellValue((list1.get(i)).getDevName());
						cell.setCellStyle(style);
						cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
					}
						
					if(list1.get(i).getSecName()!=null){
						cell.setCellValue((list1.get(i)).getSecName());
						cell.setCellStyle(style);
						cell = row.createCell(3);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(3);
					}
						
					if(list1.get(i).getFnum1()!=null){
						cell.setCellValue((list1.get(i)).getFnum1());
						cell.setCellStyle(style);
						cell = row.createCell(4);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(4);
					}
						
					if(list1.get(i).getFnum2()!=null){
						cell.setCellValue((list1.get(i)).getFnum2());
						cell.setCellStyle(style);
						cell = row.createCell(5);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(5);
					}
						
					if(list1.get(i).getPort01()!=null){
						cell.setCellValue((list1.get(i)).getPort01()+"");
						cell.setCellStyle(style);
						cell = row.createCell(6);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(6);
					}
						
					if(list1.get(i).getId()!=null){
						cell.setCellValue((list1.get(i)).getId()+"");
						cell.setCellStyle(style);
						cell = row.createCell(7);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(7);
					}
					
					if(list1.get(i).getRouteText()!=null){
						cell.setCellValue((list1.get(i)).getRouteText());
						cell.setCellStyle(style);
						cell = row.createCell(8);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(8);
					}
						
					if(list1.get(i).getTxPort()!=null){
						cell.setCellValue((list1.get(i)).getTxPort());
						cell.setCellStyle(style);
						cell = row.createCell(9);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(9);
					}
						
					if(list1.get(i).getZcodeType()!=null){
						cell.setCellValue((list1.get(i)).getZcodeType());
						cell.setCellStyle(style);
						cell = row.createCell(10);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(10);
					}
						
					//设施ID
					if(list1.get(i).getDevId()!=null){
						cell.setCellValue((list1.get(i)).getDevId()+"");
						cell = row.createCell(11);
					}else{
						cell.setCellValue("");
						cell = row.createCell(11);
					}
					//光缆段ID
					if(list1.get(i).getSectId()!=null){
						cell.setCellValue((list1.get(i)).getSectId()+"");
						cell = row.createCell(12);
					}else{
						cell.setCellValue("");
						cell = row.createCell(12);
					}
				}
				if(list1.size() > 0){
					//自适应列宽
					sheet.autoSizeColumn((int)0,true);
					sheet.autoSizeColumn((int)1,true);
					sheet.autoSizeColumn((int)2,true);
					sheet.autoSizeColumn((int)3,true);
					sheet.autoSizeColumn((int)4,true);
					sheet.autoSizeColumn((int)5,true);
					sheet.autoSizeColumn((int)6,true);
					sheet.autoSizeColumn((int)7,true);
					sheet.autoSizeColumn((int)8,true);
					sheet.autoSizeColumn((int)9,true);
					sheet.autoSizeColumn((int)10,true);
						
					int rowCount1=sheet.getLastRowNum();//最后一行
					HSSFCell obj11=null;  
					HSSFCell obj12=null;  
	
					//第二列
					String temp=sheet.getRow(1).getCell(10).getStringCellValue();
					int firstrow = 1;
					for (int j = 2; j < rowCount1; j++) {
						obj11=sheet.getRow(j).getCell(10);
						if(!temp.equals(obj11.getStringCellValue()))
						{
							sheet.addMergedRegion(new CellRangeAddress(firstrow, j -1, 1, 1));//合并
							firstrow = j;
							temp=sheet.getRow(j).getCell(10).getStringCellValue();
						}
						if(j+1==rowCount1){
							sheet.addMergedRegion(new CellRangeAddress(firstrow, j+1, 1, 1));//合并最后相同内容
						}
					}
					//第三列
					String temp2=sheet.getRow(1).getCell(11).getStringCellValue();
					int firstrow2 = 1;
					for (int j = 2; j < rowCount1; j++) {
						obj12=sheet.getRow(j).getCell(11);
						if(!temp2.equals(obj12.getStringCellValue()))
						{
							if(firstrow2==j-1){
								firstrow2=j;
								temp2=sheet.getRow(j).getCell(2).getStringCellValue();
							}else{
								
								if(temp2!=null && !"".endsWith(temp2)){
									sheet.addMergedRegion(new CellRangeAddress(firstrow2, j -1, 2, 2));
									sheet.addMergedRegion(new CellRangeAddress(firstrow2, j -1, 3, 3));
								}
							
								firstrow2 = j;
								temp2=sheet.getRow(j).getCell(11).getStringCellValue();
							}
						}
						if(j+1==rowCount1){
							if(temp2!=null && !"".endsWith(temp2)){
								sheet.addMergedRegion(new CellRangeAddress(firstrow2, j+1, 2, 2));
								sheet.addMergedRegion(new CellRangeAddress(firstrow2, j+1, 3, 3));
							}
							
						}
					}
						
						
					//第七列
					String temp3=sheet.getRow(1).getCell(6).getStringCellValue();
					int firstrow3 = 1;
					for (int j = 2; j < rowCount1; j++) {
						obj11=sheet.getRow(j).getCell(6);
						if(!temp3.equals(obj11.getStringCellValue()))
						{
							sheet.addMergedRegion(new CellRangeAddress(firstrow3, j -1, 6, 6));//合并
							firstrow3 = j;
							temp=sheet.getRow(j).getCell(6).getStringCellValue();
						}
						if(j+1==rowCount1){
							sheet.addMergedRegion(new CellRangeAddress(firstrow3, j+1, 6, 6));//合并最后相同内容
						}
					}
					//清空第11列
					for(int j=1; j < rowCount1+1; j++){
						obj12=sheet.getRow(j).getCell(10);
						obj12.setCellValue("");
						obj12.removeCellComment();
							
					}
					//清空第12列
					for(int j=1; j < rowCount1; j++){
						obj12=sheet.getRow(j).getCell(11);
						obj12.setCellValue("");
						obj12.removeCellComment();
					}
					String path = request.getSession().getServletContext().getRealPath("/")
								+"/export/";
					Date date = new Date();
					fileName = "设施数据" + date.getTime() + ".xls";
					String pathAndFileName = path +fileName;
						
					System.out.println(pathAndFileName);
					File file = new File(pathAndFileName);
					
				    File parent = file.getParentFile();
				    if (parent != null && !parent.exists()) {
				        parent.mkdirs();
				    }
				    if (file.exists()) {
				        file.delete();
				    }
					FileOutputStream out =new FileOutputStream(file);
					wb.write(out); 
					out.close();
					model.addAttribute("filePath", request.getContextPath()+"/export/"+fileName);
				}else{
					returnNum = null;
					model.addAttribute("filePath", fileName);
				}
				
				
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("导出异常！");
				}
		  }else {
			  returnNum = null;
			  model.addAttribute("filePath", fileName);
		  }
		  System.out.println(request.getContextPath()+"/export/"+fileName);
		  
		  model.addAttribute("returnNum", returnNum);
		  model.addAttribute("pb", pb);
		  model.addAttribute("csbs",list1);
		  model.addAttribute("devId", devId);
		  model.addAttribute("pageFlag", PageFlag.derive);
		  return "facility/facilityDerive";
	  }
	  

	  /**
	   * 导出设施数据
	   * @param request
	   * @param response
	   * @param session
	   * @param devId
	   * @return
	   */
	  @ResponseBody
	  @RequestMapping("/facilityONU.htm")
	  public Result facilityONU(HttpServletRequest request,HttpServletResponse response, HttpSession session,	Long devId) {
			Result r = Result.get();
			try {
			// 声明一个工作薄
			HSSFWorkbook wb = new HSSFWorkbook();
			// 声明一个单子并命名 
			HSSFSheet sheet = wb.createSheet("导出设施数据"); 
			//给单子名称一个长度
			sheet.setDefaultColumnWidth((int)15);
			// 生成一个样式 
			HSSFCellStyle style = wb.createCellStyle();
			// 设置四边的边框 
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			sheet.setColumnWidth(0, 20*256);      
			
			// 设置水平对齐的样式为居中对齐; 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 设置垂直对齐的样式为居中对齐;
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//			// 创建第一行（也可以称为表头）
			HSSFRow row = sheet.createRow(0);
			// 给单子名称一个长度
			sheet.setDefaultColumnWidth((int) 15);
	        // 样式字体居中
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 给表头第一行一次创建单元格
	        
			HSSFCell cell = row.createCell((int) 0); //
			
			cell.setCellValue("序号");
			cell.setCellStyle(style);
			cell = row.createCell((int) 1);
			
			cell.setCellValue("设备名称");
			cell.setCellStyle(style);
			cell = row.createCell((int) 2);
			
			cell.setCellValue("光缆段名称");
			cell.setCellStyle(style);
			cell = row.createCell((int) 3);
			
			cell.setCellValue("纤芯数量");
			cell.setCellStyle(style);
			cell = row.createCell((int) 4);
			
			cell.setCellValue("纤芯序号");
			cell.setCellStyle(style);
			cell = row.createCell((int) 5);
			
			cell.setCellValue("端口编码");
			cell.setCellStyle(style);
			cell = row.createCell((int) 6);
			
			cell.setCellValue("双芯绑定");
			cell.setCellStyle(style);
			cell = row.createCell((int) 7);
			
			cell.setCellValue("光路文本路由");
			cell.setCellStyle(style);
			cell = row.createCell((int) 8);
			
			cell.setCellValue("Z端跳纤位置");
			cell.setCellStyle(style);
			cell = row.createCell((int) 9);
			
			cell.setCellValue("Z端端口状态");
			cell.setCellStyle(style);
			cell = row.createCell((int) 10);
			
			// 向单元格里填充数据
			Users user = (Users) session.getAttribute("platformUser");
			FacilityBean f=facilityService.selectDetailById(devId);
			if(f!=null){
				PageBean pb = new PageBean();
				pb.setPageSize(5000);
				List<RouteBean> list1 = routeService.queryAllFacility(user.getOrgId(),f.getDevCode(),devId,pb);
				
				for (int i=0; i<list1.size(); i++) {
	
					row = sheet.createRow(i + 1);

					cell = row.createCell(0);
					if(list1.get(i).getDevName()!=null){
						cell.setCellValue(i+1);
						cell.setCellStyle(style);
						cell = row.createCell(1);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(1);
					}
					
					cell = row.createCell(1);
					if(list1.get(i).getDevName()!=null){
						cell.setCellValue((list1.get(i)).getDevName());
						cell.setCellStyle(style);
						cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
					}
					
					if(list1.get(i).getSecName()!=null){
						cell.setCellValue((list1.get(i)).getSecName());
						cell.setCellStyle(style);
						cell = row.createCell(3);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(3);
					}
					
					if(list1.get(i).getFnum1()!=null){
						cell.setCellValue((list1.get(i)).getFnum1());
						cell.setCellStyle(style);
						cell = row.createCell(4);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(4);
					}
					
					if(list1.get(i).getFnum2()!=null){
						cell.setCellValue((list1.get(i)).getFnum2());
						cell.setCellStyle(style);
						cell = row.createCell(5);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(5);
					}
					
					if(list1.get(i).getPort01()!=null){
						cell.setCellValue((list1.get(i)).getPort01()+"");
						cell.setCellStyle(style);
						cell = row.createCell(6);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(6);
					}
					
					if(list1.get(i).getId()!=null){
						cell.setCellValue((list1.get(i)).getId()+"");
						cell.setCellStyle(style);
						cell = row.createCell(7);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(7);
					}
					
					if(list1.get(i).getRouteText()!=null){
						cell.setCellValue((list1.get(i)).getRouteText());
						cell.setCellStyle(style);
						cell = row.createCell(8);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(8);
					}
					
					if(list1.get(i).getTxPort()!=null){
						cell.setCellValue((list1.get(i)).getTxPort());
						cell.setCellStyle(style);
						cell = row.createCell(9);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(9);
					}
					
					if(list1.get(i).getZcodeType()!=null){
						cell.setCellValue((list1.get(i)).getZcodeType());
						cell.setCellStyle(style);
						cell = row.createCell(10);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(10);
					}
					
					//设施ID
					if(list1.get(i).getDevId()!=null){
						cell.setCellValue((list1.get(i)).getDevId()+"");
						cell = row.createCell(11);
					}else{
						cell.setCellValue("");
						cell = row.createCell(11);
					}
					//光缆段ID
					if(list1.get(i).getSectId()!=null){
						cell.setCellValue((list1.get(i)).getSectId()+"");
						cell = row.createCell(12);
					}else{
						cell.setCellValue("");
						cell = row.createCell(12);
					}
				}
				if(list1.size() > 0){
					//自适应列宽
					sheet.autoSizeColumn((int)0,true);
					sheet.autoSizeColumn((int)1,true);
					sheet.autoSizeColumn((int)2,true);
					sheet.autoSizeColumn((int)3,true);
					sheet.autoSizeColumn((int)4,true);
					sheet.autoSizeColumn((int)5,true);
					sheet.autoSizeColumn((int)6,true);
					sheet.autoSizeColumn((int)7,true);
					sheet.autoSizeColumn((int)8,true);
					sheet.autoSizeColumn((int)9,true);
					sheet.autoSizeColumn((int)10,true);
					
					int rowCount1=sheet.getLastRowNum();//最后一行
					HSSFCell obj11=null;  
					HSSFCell obj12=null;  

					//第二列
					String temp=sheet.getRow(1).getCell(10).getStringCellValue();
					int firstrow = 1;
					for (int j = 2; j < rowCount1; j++) {
						obj11=sheet.getRow(j).getCell(10);
						if(!temp.equals(obj11.getStringCellValue()))
						{
							sheet.addMergedRegion(new CellRangeAddress(firstrow, j -1, 1, 1));//合并
							firstrow = j;
							temp=sheet.getRow(j).getCell(10).getStringCellValue();
						}
						if(j+1==rowCount1){
							sheet.addMergedRegion(new CellRangeAddress(firstrow, j+1, 1, 1));//合并最后相同内容
						}
					}
					//第三列
					String temp2=sheet.getRow(1).getCell(11).getStringCellValue();
					int firstrow2 = 1;
					for (int j = 2; j < rowCount1; j++) {
						obj12=sheet.getRow(j).getCell(11);
						if(!temp2.equals(obj12.getStringCellValue()))
						{
							if(firstrow2==j-1){
								firstrow2=j;
								temp2=sheet.getRow(j).getCell(2).getStringCellValue();
							}else{
							
								if(temp2!=null && !"".endsWith(temp2)){
									sheet.addMergedRegion(new CellRangeAddress(firstrow2, j -1, 2, 2));
									sheet.addMergedRegion(new CellRangeAddress(firstrow2, j -1, 3, 3));
								}
						
								firstrow2 = j;
								temp2=sheet.getRow(j).getCell(11).getStringCellValue();
							}
						}
						if(j+1==rowCount1){
							if(temp2!=null && !"".endsWith(temp2)){
								sheet.addMergedRegion(new CellRangeAddress(firstrow2, j+1, 2, 2));
								sheet.addMergedRegion(new CellRangeAddress(firstrow2, j+1, 3, 3));
							}
						
						}
					}
					
					
					//第七列
					String temp3=sheet.getRow(1).getCell(6).getStringCellValue();
					//int firstrow3 = 1;
					for (int j = 2; j < rowCount1; j++) {
						obj11=sheet.getRow(j).getCell(6);
						System.out.println(temp3+"==="+obj11.getStringCellValue());
						if("".equals(obj11.getStringCellValue())) {
							continue;
						}
						if(temp3.equals(obj11.getStringCellValue()))
						{
							sheet.addMergedRegion(new CellRangeAddress(j-1, j, 6, 6));//合并
							
							temp=sheet.getRow(j).getCell(6).getStringCellValue();
						}else {
							if(!"".equals(obj11.getStringCellValue())) {
								temp3 = obj11.getStringCellValue();
							}
							
						}
						if(j+1==rowCount1){
							if(temp3.equals(obj11.getStringCellValue())) {
								sheet.addMergedRegion(new CellRangeAddress(j, j+1, 6, 6));//合并最后相同内容
							}
							
						}
					}
					
//					//第七列
//					String temp3=sheet.getRow(1).getCell(6).getStringCellValue();
//					int firstrow3 = 1;
//					for (int j = 2; j < rowCount1; j++) {
//						obj11=sheet.getRow(j).getCell(6);
////						if(temp3==null || "".equals(temp3)){
////							continue;
////						}
//						System.out.println(temp3 + "========="+obj11.getStringCellValue());
//						if(!temp3.equals(obj11.getStringCellValue()))
//						{
//							if(temp3!=null && !"".endsWith(temp3)){
//								sheet.addMergedRegion(new CellRangeAddress(firstrow3, j -1, 6, 6));//合并
//							}
//							
//							firstrow3 = j;
//							temp3=sheet.getRow(j).getCell(6).getStringCellValue();
//						}
//						if(j+1==rowCount1){
//							if(temp3!=null && !"".endsWith(temp3)){
//							sheet.addMergedRegion(new CellRangeAddress(firstrow3, j+1, 6, 6));//合并最后相同内容
//							}
//						}
//					}
					
					//清空第11列
					for(int j=1; j < rowCount1+1; j++){
						obj12=sheet.getRow(j).getCell(10);
						obj12.setCellValue("");
						obj12.removeCellComment();
						
					}
					//清空第12列
					for(int j=1; j < rowCount1; j++){
						obj12=sheet.getRow(j).getCell(11);
						obj12.setCellValue("");
						obj12.removeCellComment();
					}
				}else{
					r.setR(1);
					Map<String,String> rMap=new HashMap<String,String>();
					rMap.put("filePath", "1");
					r.setDt(rMap);
					r.putRContent("无数据！");
					return r;
				}
			
			
		}
			
//			Date date = new Date();
//			String fileName = "导出数据" + date.getTime() + ".xls";
//			FileOutputStream out =new FileOutputStream("D:/"+fileName);
//			wb.write(out); 
//			out.close();
//			
//			String filePath="D:/"+fileName;
//			Map<String,String> rMap=new HashMap<String,String>();
//			downLoad(request,response,filePath,fileName);
			
			
			String path = request.getSession().getServletContext().getRealPath("/")
					+"/export/";
			Date date = new Date();
			String fileName = "设施数据" + date.getTime() + ".xls";
			String pathAndFileName = path +fileName;
			
			System.out.println(pathAndFileName);
			File file = new File(pathAndFileName);
			
	        File parent = file.getParentFile();
	        if (parent != null && !parent.exists()) {
	            parent.mkdirs();
	        }
	        if (file.exists()) {
	        	file.delete();
	        }
			FileOutputStream out =new FileOutputStream(file);
			wb.write(out); 
			out.close();
			
			
			r.setR(1);
			Map<String,String> rMap=new HashMap<String,String>();
			rMap.put("filePath", "/export/"+fileName);
			r.setDt(rMap);
			r.putRContent("导出成功！");
			System.out.println("导出成功！");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("导出异常！");
			    }
			return r;
		}
	     
	    /**
	     * 下载
	     * @param input 输入流
	     * @param fileName 输出的文件名
	     * @param fileSize 文件大小
	     */
	    public static void downLoad2(HttpServletRequest request,HttpServletResponse response,InputStream input, String fileName, long fileSize) {
	        try {
//	            HttpServletResponse response = HttpServletResponse;// 线程池中取出response
	        	System.out.println(fileName);
	            String agent = request.getHeader("USER-AGENT").toLowerCase();
	            String name = new String(fileName.getBytes(agent.indexOf("msie") != -1 ? "GBK" : CharEncoding.UTF_8), CharEncoding.ISO_8859_1);
	            response.setHeader("Content-disposition", "attachment;filename=" + name);
	            response.setContentType("application/octet-stream");
//	            response.setContentType("application/x-download"); 
	            response.setHeader("Content-Length", "" + fileSize);
	            System.out.println(fileSize);
	            OutputStream output = response.getOutputStream();
	            byte[] buffer = new byte[1024];int i = 0;
	            while ((i = input.read(buffer)) != -1) {
	                output.write(buffer, 0, i);
	            }
	            input.close();output.flush();output.close();
	        } catch (Exception e) {
	        	LOG.error("下载", e);
	        }
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
	 * 页面标志枚举
	 * @author 海阔天空
	 * fl 添加拓扑图
	 */
	public enum PageFlag{
		baseInfo,//设施基本信息
		cableSectionList,//接入光缆段
		sonfacility,//子设施信息
		fiberdiscInfo,//熔纤盘信息
		topography,//拓扑图
		devStatus,//监控数据
		mobileSwitch,//终端开锁记录
		facilityGroup,//设施分组信息
		derive,//导出
		facilityImg,//图片信息
		facilityInspect//巡检记录
	}
}
