package com.ycnet.facility.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.core.Constants;
import com.ycnet.core.util.ExcelUtil;
import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.ExcelUtil.ExcelExportData;
import com.ycnet.facility.controller.FacilityChartController.FiberdiscGroup;
import com.ycnet.facility.controller.FacilityChartController.LinkData;
import com.ycnet.facility.controller.FacilityChartController.PortData;
import com.ycnet.facility.controller.FacilityDetailController.PageFlag;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.Cable;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityImgs;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.service.CableSectionService;
import com.ycnet.frms.service.CableService;
import com.ycnet.frms.service.DiscinfoService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.service.LinesService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.RouteService;
import com.ycnet.frms.service.WellService;
import com.ycnet.frms.vo.CableFacilityBean;
import com.ycnet.frms.vo.CableSectionChart;
import com.ycnet.frms.vo.CableSectionResultVo;
import com.ycnet.frms.vo.CableStat;
import com.ycnet.frms.vo.CablesBean;
import com.ycnet.frms.vo.Disc;
import com.ycnet.frms.vo.FacilityAll;
import com.ycnet.frms.vo.Group;
import com.ycnet.frms.vo.LinesBean;
import com.ycnet.frms.vo.PipingSectionBean;
import com.ycnet.frms.vo.Port;
import com.ycnet.frms.vo.RouteBean;
import com.ycnet.frms.vo.RouteChart;
import com.ycnet.frms.vo.WellBean;
import com.ycnet.mobile.util.Result;

import net.sf.json.JSONArray;

@Controller
public class CablesController {

	@Resource(name="cableService") 
	private CableService cableService; 
	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource
	private OrganizitionService orgService;
	
	@Resource
	private AreasService areaService;
	
	@Resource(name="routeService")
	private RouteService routeService;
	
	@Resource(name="discinfoService")
	private DiscinfoService discinfoService;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;
	
	@Resource(name="cableSectionService")
	private CableSectionService cableSectionService;
	
	@Resource(name="linesService")
	private LinesService linesService;
	
	@Resource(name="wellService")
	private WellService wellService;
	
	private static final Logger LOG = LoggerFactory.getLogger(CablesController.class);
	
	/**
	 * 查询光缆列表
	 * @author YHT
	 * @time   2016年7月26日 下午8:14:28
	 * @param request
	 * @param session
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping("/cableList.htm")
	public String cableList(HttpServletRequest request ,HttpSession session ,CablesBean bean,PageBean pb,Model model,String areaCode1)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		String areaCode =null;
		Organizition o = orgService.selectById(user.getOrgId());
		if(o!=null)
			areaCode = o.getCode1();
		
		Areas area = new Areas();
		area.setParentAreaCode(areaCode);
		area.setAreaRank("3");
		
		if("".equals(bean.getAreaCode1())) bean.setAreaCode1(null);
		model.addAttribute("areaList",areaService.selectByAreaRank(area));
		//fl 新增,每第一次进入,只查未成端光缆
		if ("all".equals(bean.getIsTerminat()) || bean.getIsTerminat()==null ) {
			model.addAttribute("pb", cableService.queryCablesByBean(bean,pb,user.getOrgId()));
		}else {
			model.addAttribute("pb", cableService.queryByCablesBean(bean,pb,user.getOrgId()));
		}
		//model.addAttribute("pb", cableService.queryByCablesBean(bean,pb,user.getOrgId()));
		model.addAttribute("cableList", bean);
		model.addAttribute("areadevCode1",request.getParameter("areadevCode1"));
		model.addAttribute("areaCode2",request.getParameter("areaCode2"));
		model.addAttribute("CableTypeMap",Constants.CableTypeMap);
		model.addAttribute("SectSectionState",Constants.SectSectionState);//光缆状态
		return "cable/cableList";
	}
	/**
	 * 
	* @Title: errorRecCableList 
	* @Description: 查询纠错光缆列表 
	* @param @param request
	* @param @param session
	* @param @param bean
	* @param @param pb
	* @param @param model
	* @param @param areaCode1
	* @param @return    入参
	* @return String    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月23日 上午11:46:07 
	* @version V1.0
	 */
	@RequestMapping("/errorRecCableList.htm")
	public String errorRecCableList(HttpServletRequest request ,HttpSession session ,CablesBean bean,PageBean pb,Model model,String areaCode1)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		String areaCode =null;
		Organizition o = orgService.selectById(user.getOrgId());
		if(o!=null)
			areaCode = o.getCode1();
		
		Areas area = new Areas();
		area.setParentAreaCode(areaCode);
		area.setAreaRank("3");
		
		if("".equals(bean.getAreaCode1())) bean.setAreaCode1(null);
		model.addAttribute("areaList",areaService.selectByAreaRank(area));
		model.addAttribute("pb", cableService.queryErrorRecCableListByCablesBean(bean,pb,user.getOrgId()));
		model.addAttribute("cableList", bean);
		model.addAttribute("areadevCode1",request.getParameter("areadevCode1"));
		model.addAttribute("areaCode2",request.getParameter("areaCode2"));
		model.addAttribute("CableTypeMap",Constants.CableTypeMap);
		return "cable/errorRecCableList";
	}
	
	/**
	 * 
	 * @Title: exporterrorRecCable
	 * @Description: 导出纠错光缆段
	 * @param @param request
	 * @param @param response
	 * @param @param session
	 * @param @param areaCode1
	 * @param @param bean
	 * @param @param pb
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月25日 上午9:22:31
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/exporterrorRecCable.htm")
	public Object exporterrorRecCable(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1,CablesBean bean,PageBean pb) {

		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("纠错光缆段统计"); 
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
		
		cell.setCellValue("光缆编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 1);
		
		cell.setCellValue("光缆名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 2);
		
		cell.setCellValue("A端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 3);
		
		cell.setCellValue("A端设施名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("Z端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("Z端设施名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
		cell.setCellValue("纤芯数");
		cell.setCellStyle(style);
		cell = row.createCell((int) 7);
		
		cell.setCellValue("光缆状态");
		cell.setCellStyle(style);
		cell = row.createCell((int) 8);
		
		cell.setCellValue("A端成端");
		cell.setCellStyle(style);
		cell = row.createCell((int) 9);
		
		cell.setCellValue("Z端成端");
		cell.setCellStyle(style);
		cell = row.createCell((int) 10);
		
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(areaCode1+"区号");
		if(areaCode1!=null && !"".equals(areaCode1)) {
			bean.setAreaCode1(areaCode1);
		}
		List<CablesBean> list1 = cableService.exporterrorRecCable(bean,pb, user.getOrgId());
		if(list1!=null){
			for (int i = 0; i < list1.size(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				if(list1.get(i).getSecCode()!=null){
					cell.setCellValue(list1.get(i).getSecCode());
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}
				
				if(list1.get(i).getSecName()!=null){
					cell.setCellValue(list1.get(i).getSecName());
					cell.setCellStyle(style);
					cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
				}
				
				if(list1.get(i).getDevCodeA()!=null){
					cell.setCellValue(list1.get(i).getDevCodeA());
					cell.setCellStyle(style);
					cell = row.createCell(3);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(3);
				}
				
				if(list1.get(i).getAdevName()!=null){
					cell.setCellValue(list1.get(i).getAdevName());
					cell.setCellStyle(style);
					cell = row.createCell(4);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(4);
				}
				
				if(list1.get(i).getDevCodeZ()!=null){
					cell.setCellValue(list1.get(i).getDevCodeZ());
					cell.setCellStyle(style);
					cell = row.createCell(5);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(5);
				}
					
				if(list1.get(i).getZdevName()!=null) {
					cell.setCellValue(list1.get(i).getZdevName());
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}
				
				if(list1.get(i).getFiberNum()!=null) {
					cell.setCellValue(list1.get(i).getFiberNum().toString());
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}
				
				if(list1.get(i).getDevState()!=null) {
					if("0".equals(list1.get(i).getDevState())) {
						cell.setCellValue("未核对");
					}if("1".equals(list1.get(i).getDevState())) {
						cell.setCellValue("正常");
					}if("2".equals(list1.get(i).getDevState())) {
						cell.setCellValue("新增");
					}if("3".equals(list1.get(i).getDevState())) {
						cell.setCellValue("修改");
					}if("4".equals(list1.get(i).getDevState())) {
						cell.setCellValue("资管删除");
					}if("5".equals(list1.get(i).getDevState())) {
						cell.setCellValue("新增删除");
					}
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}
				
				if(list1.get(i).getInusedA()!=null) {
					if(list1.get(i).getInvestmentA()!=null && !"".equals(list1.get(i).getInvestmentA())){
						cell.setCellValue("成端："+list1.get(i).getInusedA()+",直熔："+list1.get(i).getInvestmentA());
					}else {
						cell.setCellValue("成端："+list1.get(i).getInusedA());
					}
					
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}else {
					if(list1.get(i).getInvestmentA()!=null && !"".equals(list1.get(i).getInvestmentA())){
						cell.setCellValue("直熔："+list1.get(i).getInvestmentA());
					}else {
						cell.setCellValue("");
					}
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}
					
				if(list1.get(i).getInusedZ()!=null) {
					if(list1.get(i).getInvestmentZ()!=null && !"".equals(list1.get(i).getInvestmentZ())){
						cell.setCellValue("成端："+list1.get(i).getInusedZ()+",直熔："+list1.get(i).getInvestmentZ());
					}else {
						cell.setCellValue("成端："+list1.get(i).getInusedZ());
					}
					
					cell.setCellStyle(style);
					cell = row.createCell(10);
				}else {
					if(list1.get(i).getInvestmentZ()!=null && !"".equals(list1.get(i).getInvestmentZ())){
						cell.setCellValue("直熔："+list1.get(i).getInvestmentZ());
					}else {
						cell.setCellValue("");
					}
					cell.setCellStyle(style);
					cell = row.createCell(10);
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
		String fileName = "纠错光缆段数据" + date.getTime() + ".xls";
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
	
	/**
	 * 光缆段列表详情
	 */
	@RequestMapping("/cableInfo.htm")
	public String cableInfo(HttpServletRequest request ,HttpSession session ,Long sectId,Model model){
		Cable cb=cableService.queryCablesInfo(sectId);
//		Cable cbE=cableService.queryCableEnd(sectId);
		model.addAttribute("cb",cb);
//		model.addAttribute("cbE",cbE);
		model.addAttribute("sectId",sectId);
		model.addAttribute("pageFlag",Page1Flag.baseInfo);
		return "cable/cableBaseInfo";
	}
	
	/**
	 * 在地图上显示光缆段详细信息
	 * 刘沧海，2017/1/3
	 * @param session
	 * @param sectId
	 * @param model
	 * @return
	 */
	@RequestMapping("/cableInfoMap.htm") 
	public String cableInfoMap(HttpSession session, Long sectId,Model model){
		Users user=(Users)session.getAttribute("platformUser");
		List<WellBean> wellList=cableSectionService.queryCableSectionWell(sectId,user.getOrgId());//查询光缆段两边井
		if(wellList!=null && wellList.size()>0) {
			model.addAttribute("wellList",wellList);
		}
//		if(wellList != null && wellList.size()>0) {
//			for (int i = 0; i < wellList.size(); i++) {
//				if("1".equals(wellList.get(i).getIsFormerbureau())) {
//					if(wellList.get(i).getDevId()!=null) {
//						Facility facility = facilityService.selectById(wellList.get(i).getDevId());
//						wellList.get(i).setFa(facility);
//					}
//				}
//			}
//			model.addAttribute("wellList",wellList);
//		}
		Cable cb=cableService.queryCablesInfo(sectId);
		List<Facility> fList = cableService.queryBySectId(sectId);
		List<WellBean> zbList = getZBList(wellList);
		BigDecimal lng=null;
		BigDecimal lag=null;
		for(Facility facility : fList) {
			if(facility!=null)
			if(!"".equals(facility.getBaiduX().toString()) && facility.getBaiduX()!=null && facility.getBaiduY()!=null && !"".equals(facility.getBaiduY().toString())) {
				lng = facility.getBaiduX();
				lag = facility.getBaiduY();
				break;
			}
		}
		if(fList.size()==2) {
			Facility f1 = fList.get(0);
			if(f1.getBaiduX()!=null ) {
				model.addAttribute("baiduX1",f1.getBaiduX());
				model.addAttribute("baiduY1",f1.getBaiduY());
			}
			Facility f2 = fList.get(1);
			if(f2.getBaiduX()!=null) {
				model.addAttribute("baiduX2",f2.getBaiduX());
				model.addAttribute("baiduY2",f2.getBaiduY());
			}
		}
		model.addAttribute("lng",lng);
		model.addAttribute("lag",lag);
		model.addAttribute("cb",cb);
		model.addAttribute("fList", fList);
		model.addAttribute("zbList", zbList);
		model.addAttribute("sectId", sectId);
		model.addAttribute("pageFlag",Page1Flag.cableMap);
		return "cable/cableMaps";

	}
	private List<WellBean> getZBList(List<WellBean> fList){
		List<WellBean> wList = new ArrayList<WellBean>();
		WellBean w = null;
		for(WellBean well : fList) {
			//过滤不关联的设施
//			if(facility.getIsShow()==0) {
//				continue;
//			}
			if(w == null) {
				w = new WellBean();
			}
			if(well != null) {
				if(well.getBaiduX()!=null && !"".equals(well.getBaiduX()) && well.getBaiduY()!=null && !"".equals(well.getBaiduY())) {
					if(w.getBaiduX()==null || "".equals(w.getBaiduX())) {
						w.setBaiduX(well.getBaiduX());
						w.setBaiduY(well.getBaiduY());   
					}else {
						w.setCodeBaiduX(well.getBaiduX());
						w.setCodeBaiduY(well.getBaiduY());
						wList.add(w);
						w=null;
						w = new WellBean();
						w.setBaiduX(well.getBaiduX());
						w.setBaiduY(well.getBaiduY());
					}
				}else {
					continue;
				}
			}else {
				continue;
			}
//			if(f.getAreaCode1()!=null) {
//				zbList.add(f);
//				f=null;
//			}
		}
		return wList;
	}
	/**
	 * 
	* @Title: pipingSectionList 
	* @Description: 根据井ID查询管道段信息 
	* @param @param wellId
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年1月3日 下午3:30:32 
	* @version V1.0
	 */
	@RequestMapping("/pipingSectionList.htm")
	public String pipingSectionList(Long wellId,Model model) {
		Well well=wellService.selectByPrimaryKey(wellId);
		model.addAttribute("wellName", well.getWellName());
		List<PipingSectionBean> pipingSeclist=wellService.querypipingSectionList(wellId);
		model.addAttribute("pipingSeclist", pipingSeclist);
		return "cable/wellInfoList";
	}
	
	/**
	 * gps点位转化成百度标准点位
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changeFacilityCable.htm")
	public String changeFacilityCable(HttpSession session){
		Users user=(Users)session.getAttribute("platformUser");
		String message="false";
		message=facilityService.changeFacility(user.getOrgId());
		return message;
		
	}
	
	//光缆段两点位置ajax
	@ResponseBody
	@RequestMapping("/findCables.htm")
	public Map<String,Object> findCables(Long sectId){
		Map<String,Object> map=new HashMap<String,Object>();
		Cable cb=cableService.queryCablesInfo(sectId);
		if(cb.getDevCodeA()!=null){
			System.out.println(cb.getDevCodeA()+"***");
			Facility facilityA = facilityService.selectByCode(cb.getDevCodeA());
			if(facilityA!=null){
				System.out.println(facilityA.getDevCode()+"***");
				System.out.println(facilityA.getLongitude()+"位置A"+facilityA.getLatitude()+"456");
				if(facilityA.getBaiduX()!=null && facilityA.getBaiduY()!=null){
					map.put("baiduXA", facilityA.getBaiduX()); 
					map.put("baiduYA", facilityA.getBaiduY()); 
				}
				map.put("facilityA", facilityA);
				System.out.println(facilityA+"facilityA");
			}
		}
		if(cb.getDevCodeZ()!=null){
			System.out.println(cb.getDevCodeZ()+"***");
		Facility facilityZ = facilityService.selectByCode(cb.getDevCodeZ());
			if(facilityZ!=null){
				System.out.println(facilityZ.getDevCode()+"789");
				if(facilityZ.getBaiduX()!=null && facilityZ.getBaiduY()!=null){
					map.put("baiduXZ", facilityZ.getBaiduX()); 
					map.put("baiduYZ", facilityZ.getBaiduY()); 
				}
				map.put("facilityZ", facilityZ);
				System.out.println(facilityZ.getLongitude()+"位置Z"+facilityZ.getLatitude()+"789");
				System.out.println(facilityZ+"facilityZ");
			} 
		}
		map.put("cb", cb);
		
		return map;
	}
	//线AJAX
	@ResponseBody
	@RequestMapping("/findFiberCable.htm")
	public Map<String,Object> findFiber(Long sectId){
		Map<String,Object> map=new HashMap<String,Object>();
		Cable cb=cableService.queryCablesInfo(sectId);
		if(cb.getDevCodeA()!=null){
			Facility facilityA = facilityService.selectByCode(cb.getDevCodeA());
			if(facilityA!=null){
				System.out.println(facilityA.getLongitude()+"线A"+facilityA.getLatitude()+"456");
				if(facilityA.getBaiduX()!=null && facilityA.getBaiduY()!=null){
					map.put("baiduXA", facilityA.getBaiduX()); 
					map.put("baiduYA", facilityA.getBaiduY()); 
				}
				map.put("facilityA", facilityA);
			}
		}
		if(cb.getDevCodeZ()!=null){
			Facility facilityZ = facilityService.selectByCode(cb.getDevCodeZ());
			if(facilityZ!=null){
				if(facilityZ.getBaiduX()!=null && facilityZ.getBaiduY()!=null){
					map.put("baiduXZ", facilityZ.getBaiduX()); 
					map.put("baiduYZ", facilityZ.getBaiduY()); 
				}
				map.put("facilityZ", facilityZ);
				System.out.println(facilityZ.getLongitude()+"线Z"+facilityZ.getLatitude()+"789");
			}
		}
		map.put("cb", cb);
		return map;
	}
	/**
	 * 导出光交箱光缆段列表详情
	 * @param request
	 * @param response
	 * @param session
	 * @param areaCode1
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cableExport.htm")
	public Result exportData(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1){
		Result r = Result.get();
		try {
		// 声明一个工作薄
			HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
			HSSFSheet sheet = wb.createSheet("光交箱光缆段成端"); 
		//给单子名称一个长度
		sheet.setDefaultColumnWidth((int)15);
		// 生成一个样式 
		CellStyle style = wb.createCellStyle();
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
		
		//光交箱光缆段
		cell.setCellValue("A端设施名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 1);
		
		cell.setCellValue("A端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 2);
		
		cell.setCellValue("光缆段名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 3);
		
		cell.setCellValue("光缆段编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("纤芯数量");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("A端成端开始位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
		cell.setCellValue("Z端设施名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 7);
		
		cell.setCellValue("Z端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 8);
		
		cell.setCellValue("Z端成端开始位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 9);
		//机房
//		cell.setCellValue("机房编码");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 1);
//		
//		cell.setCellValue("机房名称");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 2);
//		
//		cell.setCellValue("机柜编码");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 3);
//		
//		cell.setCellValue("机柜名称");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 4);
//		
//		cell.setCellValue("光缆段编码");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 5);
//		
//		cell.setCellValue("光缆段名称");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 6);
//		
//		cell.setCellValue("纤芯数量");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 7);
//		
//		cell.setCellValue("纤芯序号");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 8);
//		
//		cell.setCellValue("端口编码");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 9);
//		
//		cell.setCellValue("Z端跳纤位置");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 10);
//		
//		cell.setCellValue("Z端端口状态");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 11);
//		
//		cell.setCellValue("双芯绑定");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 12);
//		
//		cell.setCellValue("光路文本路由");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 13);
		
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(user.getOrgId()+"用户ID");
		System.out.println(areaCode1+"区号");
		List<Cable> list1 = cableService.queryCableInfo(user.getOrgId(),areaCode1);
//		List<FacilityAll> list1=facilityService.queryall();
		if(list1!=null){
			for (int i = 0; i < list1.size(); i++) {
	
				row = sheet.createRow(i + 1);
				//光交箱光缆段
				cell = row.createCell(0);
				if(list1.get(i).getAdevName()!=null){
				cell.setCellValue((list1.get(i)).getAdevName());
				cell.setCellStyle(style);
				cell = row.createCell(1);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}
				
				if(list1.get(i).getACodeDev()!=null){
					cell.setCellValue((list1.get(i)).getACodeDev());
					cell.setCellStyle(style);
					cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
				}
				
				if(list1.get(i).getSecName()!=null){
					cell.setCellValue(( list1.get(i)).getSecName());
					cell.setCellStyle(style);
					cell = row.createCell(3);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(3);
				}
				
				if(list1.get(i).getSecCode()!=null){
					cell.setCellValue((list1.get(i)).getSecCode());
					cell.setCellStyle(style);
					cell = row.createCell(4);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(4);
				}
				
				if(list1.get(i).getFiberNum()!=null){
					cell.setCellValue((list1.get(i)).getFiberNum());
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}
				
				if(list1.get(i).getCdAcodeSta()!=null){
					cell.setCellValue((list1.get(i)).getCdAcodeSta());
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}
				
				if(list1.get(i).getZdevName()!=null){
					cell.setCellValue((list1.get(i)).getZdevName());
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}
				
				if(list1.get(i).getZCodeDev()!=null){
					cell.setCellValue((list1.get(i)).getZCodeDev());
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}
				
				if(list1.get(i).getCdZcodeSta()!=null){
					cell.setCellValue((list1.get(i)).getCdZcodeSta());
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}
				//机房
//				cell = row.createCell(0);
//				if(list1.get(i).getDevCode()!=null){
//				cell.setCellValue((list1.get(i)).getDevCode());
//				cell.setCellStyle(style);
//				cell = row.createCell(1);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(1);
//				}
//				
//				if(list1.get(i).getDevName()!=null){
//					cell.setCellValue((list1.get(i)).getDevName());
//					cell.setCellStyle(style);
//					cell = row.createCell(2);
//					}else{
//						cell.setCellValue("");
//						cell.setCellStyle(style);
//						cell = row.createCell(2);
//				}
//				
//				if(list1.get(i).getDevCode1()!=null){
//					cell.setCellValue(( list1.get(i)).getDevCode1());
//					cell.setCellStyle(style);
//					cell = row.createCell(3);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(3);
//				}
//				
//				if(list1.get(i).getDevName1()!=null){
//					cell.setCellValue((list1.get(i)).getDevName1());
//					cell.setCellStyle(style);
//					cell = row.createCell(4);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(4);
//				}
//				
//				if(list1.get(i).getSecCode()!=null){
//					cell.setCellValue((list1.get(i)).getSecCode());
//					cell.setCellStyle(style);
//					cell = row.createCell(5);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(5);
//				}
//				
//				if(list1.get(i).getSecName()!=null){
//					cell.setCellValue((list1.get(i)).getSecName());
//					cell.setCellStyle(style);
//					cell = row.createCell(6);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(6);
//				}
//				
//				if(list1.get(i).getShuLiang()!=null){
//					cell.setCellValue((list1.get(i)).getShuLiang());
//					cell.setCellStyle(style);
//					cell = row.createCell(7);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(7);
//				}
//				
//				if(list1.get(i).getXuHao()!=null){
//					cell.setCellValue((list1.get(i)).getXuHao());
//					cell.setCellStyle(style);
//					cell = row.createCell(8);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(8);
//				}
//				
//				if(list1.get(i).getPort01()!=null){
//					cell.setCellValue((list1.get(i)).getPort01());
//					cell.setCellStyle(style);
//					cell = row.createCell(9);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(9);
//				}
//				
//				if(list1.get(i).getMoPlace()!=null){
//					cell.setCellValue((list1.get(i)).getMoPlace());
//					cell.setCellStyle(style);
//					cell = row.createCell(10);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(10);
//				}
//				
//				if(list1.get(i).getMoStat()!=null){
//					cell.setCellValue((list1.get(i)).getMoStat());
//					cell.setCellStyle(style);
//					cell = row.createCell(11);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(11);
//				}
//				
//				if(list1.get(i).getBangDingId()!=null){
//					cell.setCellValue((list1.get(i)).getBangDingId());
//					cell.setCellStyle(style);
//					cell = row.createCell(12);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(12);
//				}
//				
//				if(list1.get(i).getRouteText()!=null){
//					cell.setCellValue((list1.get(i)).getRouteText());
//					cell.setCellStyle(style);
//					cell = row.createCell(13);
//				}else{
//					cell.setCellValue("");
//					cell.setCellStyle(style);
//					cell = row.createCell(13);
//				}
				
			}
			
			
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
//			sheet.autoSizeColumn((int)10,true);
//			sheet.autoSizeColumn((int)11,true);
//			sheet.autoSizeColumn((int)12,true);
//			sheet.autoSizeColumn((int)13,true);
			
			if(list1.size()>0){
				int rowCount1=sheet.getLastRowNum();//最后一行
				HSSFCell obj1=null;  
				
				//合并A端设施名称
				String tempDevA = sheet.getRow(1).getCell(1).getStringCellValue();
				int firstrowDevA=1;
				for (int j = 2; j < rowCount1; j++) {
					obj1=sheet.getRow(j).getCell(1);
					if(!tempDevA.equals(obj1.getStringCellValue()))
					{
						if(firstrowDevA==j-1){
							firstrowDevA=j;
							tempDevA=sheet.getRow(j).getCell(1).getStringCellValue();
						}else{
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j -1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j -1, 1, 1));
						firstrowDevA = j;
						tempDevA=sheet.getRow(j).getCell(1).getStringCellValue();
						}
					}
					if(j+1==rowCount1){
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j+1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j+1, 1, 1));
					}
				}
				//合并光缆段
				String tempSec = sheet.getRow(1).getCell(3).getStringCellValue();
				HSSFCell obj12=null;
				int firstrowSec=1;
				for (int j = 2; j < rowCount1; j++) {
					obj12=sheet.getRow(j).getCell(3);
					if(!tempSec.equals(obj12.getStringCellValue()))
					{
						if(firstrowSec==j-1){
							firstrowSec=j;
							tempSec=sheet.getRow(j).getCell(3).getStringCellValue();
						}else{
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j -1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j -1, 3, 3));
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j -1, 4, 4));
						firstrowSec = j;
						tempSec=sheet.getRow(j).getCell(3).getStringCellValue();
						}
					}
					if(j+1==rowCount1){
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j+1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j+1, 3, 3));
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j+1, 4, 4));
					}
				}
				//合并Z端设施
				String tempDevZ = sheet.getRow(1).getCell(7).getStringCellValue();
				int firstrowDevZ=1;
				HSSFCell obj13=null;
				for (int j = 2; j < rowCount1; j++) {
					obj13=sheet.getRow(j).getCell(7);
					if(!tempDevZ.equals(obj13.getStringCellValue()))
					{
						if(firstrowDevZ==j-1){
							firstrowDevZ=j;
							tempDevZ=sheet.getRow(j).getCell(7).getStringCellValue();
						}else{
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j -1, 6, 6));
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j -1, 7, 7));
						firstrowDevZ = j;
						tempDevZ=sheet.getRow(j).getCell(7).getStringCellValue();
						}
					}
					if(j+1==rowCount1){
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j+1, 6, 6));
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j+1, 7, 7));
					}
//				String tempDevA = sheet.getRow(1).getCell(0).getStringCellValue();
//				int firstrowDevA=1;
//				for (int j = 2; j < rowCount1; j++) {
//					obj1=sheet.getRow(j).getCell(0);
//					if(!tempDevA.equals(obj1.getStringCellValue()))
//					{
//						if(firstrowDevA==j-1){
//							firstrowDevA=j;
//							tempDevA=sheet.getRow(j).getCell(0).getStringCellValue();
//						}else{
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j -1, 0, 0));
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j -1, 1, 1));
//						firstrowDevA = j;
//						tempDevA=sheet.getRow(j).getCell(0).getStringCellValue();
//						}
//					}
//					if(j+1==rowCount1){
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j+1, 0, 0));
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j+1, 1, 1));
//					}
//				}
//				//合并光缆段
//				String tempSec = sheet.getRow(1).getCell(2).getStringCellValue();
//				HSSFCell obj12=null;
//				int firstrowSec=1;
//				for (int j = 2; j < rowCount1; j++) {
//					obj12=sheet.getRow(j).getCell(2);
//					if(!tempSec.equals(obj12.getStringCellValue()))
//					{
//						if(firstrowSec==j-1){
//							firstrowSec=j;
//							tempSec=sheet.getRow(j).getCell(2).getStringCellValue();
//						}else{
//						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j -1, 2, 2));
//						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j -1, 3, 3));
//						firstrowSec = j;
//						tempSec=sheet.getRow(j).getCell(2).getStringCellValue();
//						}
//					}
//					if(j+1==rowCount1){
//						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j+1, 2, 2));
//						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j+1, 3, 3));
//					}
//				}
//				//合并Z端设施
//				String tempDevZ = sheet.getRow(1).getCell(4).getStringCellValue();
//				int firstrowDevZ=1;
//				HSSFCell obj13=null;
//				for (int j = 2; j < rowCount1; j++) {
//					obj13=sheet.getRow(j).getCell(4);
//					if(!tempDevZ.equals(obj13.getStringCellValue()))
//					{
//						if(firstrowDevZ==j-1){
//							firstrowDevZ=j;
//							tempDevZ=sheet.getRow(j).getCell(4).getStringCellValue();
//						}else{
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j -1, 4, 4));
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j -1, 5, 5));
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j -1, 6, 6));
//						firstrowDevZ = j;
//						tempDevZ=sheet.getRow(j).getCell(4).getStringCellValue();
//						}
//					}
//					if(j+1==rowCount1){
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j+1, 4, 4));
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j+1, 5, 5));
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j+1, 6, 6));
//					}
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
		String fileName = "光交箱光缆段成端数据" + date.getTime() + ".xls";
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
	/**
	 * 导出机柜光缆段
	 * @param request
	 * @param response
	 * @param session
	 * @param areaCode1
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cableExportCablin.htm")
	public Result cableExportCablin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1){
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("机柜光缆段成端"); 
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
		
		cell.setCellValue("A端设施名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 1);
		
		cell.setCellValue("A端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 2);
		
		cell.setCellValue("光缆段名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 3);
		
		cell.setCellValue("光缆段编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("纤芯数量");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("A端成端开始位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
		cell.setCellValue("Z端设施名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 7);
		
		cell.setCellValue("Z端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 8);
		
		cell.setCellValue("Z端成端开始位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 9);
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(user.getOrgId()+"用户ID");
		System.out.println(areaCode1+"区号");
		List<Cable> list1 = cableService.queryCableInfoCablin(user.getOrgId(),areaCode1);
		if(list1!=null){
			for (int i = 0; i < list1.size(); i++) {
	
				row = sheet.createRow(i + 1);
	
				cell = row.createCell(0);
				if(list1.get(i).getAdevName()!=null){
				cell.setCellValue((list1.get(i)).getAdevName());
				cell.setCellStyle(style);
				cell = row.createCell(1);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}
				
				if(list1.get(i).getACodeDev()!=null){
					cell.setCellValue((list1.get(i)).getACodeDev());
					cell.setCellStyle(style);
					cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
				}
				
				if(list1.get(i).getSecName()!=null){
					cell.setCellValue(( list1.get(i)).getSecName());
					cell.setCellStyle(style);
					cell = row.createCell(3);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(3);
				}
				
				if(list1.get(i).getSecCode()!=null){
					cell.setCellValue((list1.get(i)).getSecCode());
					cell.setCellStyle(style);
					cell = row.createCell(4);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(4);
				}
				
				if(list1.get(i).getFiberNum()!=null){
					cell.setCellValue((list1.get(i)).getFiberNum());
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}
				
				if(list1.get(i).getCdAcodeSta()!=null){
					cell.setCellValue((list1.get(i)).getCdAcodeSta());
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}
				
				if(list1.get(i).getZdevName()!=null){
					cell.setCellValue((list1.get(i)).getZdevName());
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}
				
				if(list1.get(i).getZCodeDev()!=null){
					cell.setCellValue((list1.get(i)).getZCodeDev());
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}
				
				if(list1.get(i).getCdZcodeSta()!=null){
					cell.setCellValue((list1.get(i)).getCdZcodeSta());
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}
				
				
				
			}
			
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
			
			if(list1.size()>0){ 
				int rowCount1=sheet.getLastRowNum();//最后一行
				HSSFCell obj1=null;  
				
				//合并A端设施名称
				String tempDevA = sheet.getRow(1).getCell(1).getStringCellValue();
				int firstrowDevA=1;
				for (int j = 2; j < rowCount1; j++) {
					obj1=sheet.getRow(j).getCell(1);
					if(!tempDevA.equals(obj1.getStringCellValue()))
					{
						if(firstrowDevA==j-1){
							firstrowDevA=j;
							tempDevA=sheet.getRow(j).getCell(1).getStringCellValue();
						}else{
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j -1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j -1, 1, 1));
						firstrowDevA = j;
						tempDevA=sheet.getRow(j).getCell(1).getStringCellValue();
						}
					}
					if(j+1==rowCount1){
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j+1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j+1, 1, 1));
					}
				}
				//合并光缆段
				String tempSec = sheet.getRow(1).getCell(3).getStringCellValue();
				HSSFCell obj12=null;
				int firstrowSec=1;
				for (int j = 2; j < rowCount1; j++) {
					obj12=sheet.getRow(j).getCell(3);
					if(!tempSec.equals(obj12.getStringCellValue()))
					{
						if(firstrowSec==j-1){
							firstrowSec=j;
							tempSec=sheet.getRow(j).getCell(3).getStringCellValue();
						}else{
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j -1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j -1, 3, 3));
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j -1, 4, 4));
						firstrowSec = j;
						tempSec=sheet.getRow(j).getCell(3).getStringCellValue();
						}
					}
					if(j+1==rowCount1){
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j+1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j+1, 3, 3));
						sheet.addMergedRegion(new CellRangeAddress(firstrowSec, j+1, 4, 4));
					}
				}
				//合并Z端设施
				String tempDevZ = sheet.getRow(1).getCell(7).getStringCellValue();
				int firstrowDevZ=1;
				HSSFCell obj13=null;
				for (int j = 2; j < rowCount1; j++) {
					obj13=sheet.getRow(j).getCell(7);
					if(!tempDevZ.equals(obj13.getStringCellValue()))
					{
						if(firstrowDevZ==j-1){
							firstrowDevZ=j;
							tempDevZ=sheet.getRow(j).getCell(7).getStringCellValue();
						}else{
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j -1, 6, 6));
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j -1, 7, 7));
						firstrowDevZ = j;
						tempDevZ=sheet.getRow(j).getCell(7).getStringCellValue();
						}
					}
					if(j+1==rowCount1){
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j+1, 6, 6));
						sheet.addMergedRegion(new CellRangeAddress(firstrowDevZ, j+1, 7, 7));
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
		String fileName = "机柜光缆段成端数据" + date.getTime() + ".xls";
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
	/**
	 * 
	* @Title: exportPortInfoCablin 
	* @Description: TODO(导出纤芯占用详细机柜) 
	* @param @param request
	* @param @param response
	* @param @param session
	* @param @param areaCode1
	* @param @return    入参
	* @return Result    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年9月27日 下午4:02:23 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/exportPortInfoCablin.htm")
	public Result exportPortInfoCablin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1) {
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("端口占用"); 
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
        String[] strs=null;
        int count=0;
        List<Route> newlist=new ArrayList<Route>();
        
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
		
		cell.setCellValue("光缆长度");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("纤芯序号");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("A端设备名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
		cell.setCellValue("A端设备编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 7);
		
		cell.setCellValue("A端端口状态");
		cell.setCellStyle(style);
		cell = row.createCell((int) 8);
		
		cell.setCellValue("A端跳纤开始位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 9);
		
		cell.setCellValue("Z端设备名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 10);
		
		cell.setCellValue("Z端设备编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 11);
		
		cell.setCellValue("Z端端口状态");
		cell.setCellStyle(style);
		cell = row.createCell((int) 12);
		
		cell.setCellValue("Z端跳纤开始位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 13);
		
		
		
		
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(user.getOrgId()+"用户ID");
		System.out.println(areaCode1+"区号");
		List<Cable> list1 = cableService.queryPortInfoCablin(user.getOrgId(),areaCode1);
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
					cell.setCellValue((list1.get(i)).getFiberNum());
					cell.setCellStyle(style);
					cell = row.createCell(3);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(3);
				}
				
				if(list1.get(i).getCableLen()!=null){
					cell.setCellValue((list1.get(i)).getCableLen().toString());
					cell.setCellStyle(style);
					cell = row.createCell(4);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(4);
				}
				
				if(list1.get(i).getFiberNo()!=null){
					cell.setCellValue(( list1.get(i)).getFiberNo());
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}
				
				if(list1.get(i).getAdevName()!=null){
					cell.setCellValue((list1.get(i)).getAdevName());
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}
				
				if(list1.get(i).getDevCodeA()!=null){
					cell.setCellValue((list1.get(i)).getDevCodeA());
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}
				
				if(list1.get(i).getAstat()!=null){
					cell.setCellValue((list1.get(i)).getAstat());
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}
				
				if(list1.get(i).getTxAcodeSta()!=null){
					cell.setCellValue((list1.get(i)).getTxAcodeSta());
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}
				
				if(list1.get(i).getZdevName()!=null){
					cell.setCellValue((list1.get(i)).getZdevName());
					cell.setCellStyle(style);
					cell = row.createCell(10);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(10);
				}
				
				if(list1.get(i).getDevCodeZ()!=null){
					cell.setCellValue((list1.get(i)).getDevCodeZ());
					cell.setCellStyle(style);
					cell = row.createCell(11);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(11);
				}
				
				if(list1.get(i).getZstat()!=null){
					cell.setCellValue((list1.get(i)).getZstat());
					cell.setCellStyle(style);
					cell = row.createCell(12);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(12);
				}
				
				if(list1.get(i).getTxZcodeSta()!=null){
					cell.setCellValue((list1.get(i)).getTxZcodeSta());
					cell.setCellStyle(style);
					cell = row.createCell(13);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(13);
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
				sheet.autoSizeColumn((int)13,true);
				
				
				int rowCount1=sheet.getLastRowNum();//最后一行
				HSSFCell obj1=null;  
				HSSFCell obj12=null;  
				//合并光缆段名称
				String temp = sheet.getRow(1).getCell(1).getStringCellValue();
				int firstrow=1;
				for (int j = 2; j < rowCount1; j++) {
					obj1=sheet.getRow(j).getCell(1);
					if(!temp.equals(obj1.getStringCellValue()))
					{
						if(firstrow==j-1){
							firstrow=j;
							temp=sheet.getRow(j).getCell(1).getStringCellValue();
						}else{
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j -1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j -1, 1, 1));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j -1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j -1, 3, 3));
						firstrow = j;
						temp=sheet.getRow(j).getCell(1).getStringCellValue();
						}
					}
					if(j+1==rowCount1){
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j+1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j+1, 1, 1));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j+1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j+1, 3, 3));
					}
				}
				//清空第12列
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
		String fileName = "机柜端口占用详细数据" + date.getTime() + ".xls";
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
	
	/**
	 * 
	* @Title: exportPortInfo 
	* @Description: TODO(导出纤芯占用详细(光交箱)) 
	* @param @param request
	* @param @param response
	* @param @param session
	* @param @param areaCode1
	* @param @return    入参
	* @return Result    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年9月27日 下午4:05:43 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/exportPortInfo.htm")
	public Result exportPortInfo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1) {
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("端口占用"); 
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
        String[] strs=null;
        int count=0;
        List<Route> newlist=new ArrayList<Route>();
        
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
		
		cell.setCellValue("光缆长度");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("纤芯序号");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("A端设备名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
		cell.setCellValue("A端设备编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 7);
		
		cell.setCellValue("A端端口状态");
		cell.setCellStyle(style);
		cell = row.createCell((int) 8);
		
		cell.setCellValue("A端跳纤开始位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 9);
		
		cell.setCellValue("Z端设备名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 10);
		
		cell.setCellValue("Z端设备编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 11);
		
		cell.setCellValue("Z端端口状态");
		cell.setCellStyle(style);
		cell = row.createCell((int) 12);
		
		cell.setCellValue("Z端跳纤开始位置");
		cell.setCellStyle(style);
		cell = row.createCell((int) 13);
		
		
		
		
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(user.getOrgId()+"用户ID");
		System.out.println(areaCode1+"区号");
		List<Cable> list1 = cableService.queryPortInfo(user.getOrgId(),areaCode1);
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
					cell.setCellValue((list1.get(i)).getFiberNum());
					cell.setCellStyle(style);
					cell = row.createCell(3);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(3);
				}
				
				if(list1.get(i).getCableLen()!=null){
					cell.setCellValue((list1.get(i)).getCableLen().toString());
					cell.setCellStyle(style);
					cell = row.createCell(4);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(4);
				}
				
				if(list1.get(i).getFiberNo()!=null){
					cell.setCellValue(( list1.get(i)).getFiberNo());
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}
				
				if(list1.get(i).getAdevName()!=null){
					cell.setCellValue((list1.get(i)).getAdevName());
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}
				
				if(list1.get(i).getDevCodeA()!=null){
					cell.setCellValue((list1.get(i)).getDevCodeA());
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}
				
				if(list1.get(i).getAstat()!=null){
					cell.setCellValue((list1.get(i)).getAstat());
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}
				
				if(list1.get(i).getTxAcodeSta()!=null){
					cell.setCellValue((list1.get(i)).getTxAcodeSta());
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}
				
				if(list1.get(i).getZdevName()!=null){
					cell.setCellValue((list1.get(i)).getZdevName());
					cell.setCellStyle(style);
					cell = row.createCell(10);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(10);
				}
				
				if(list1.get(i).getDevCodeZ()!=null){
					cell.setCellValue((list1.get(i)).getDevCodeZ());
					cell.setCellStyle(style);
					cell = row.createCell(11);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(11);
				}
				
				if(list1.get(i).getZstat()!=null){
					cell.setCellValue((list1.get(i)).getZstat());
					cell.setCellStyle(style);
					cell = row.createCell(12);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(12);
				}
				
				if(list1.get(i).getTxZcodeSta()!=null){
					cell.setCellValue((list1.get(i)).getTxZcodeSta());
					cell.setCellStyle(style);
					cell = row.createCell(13);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(13);
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
				sheet.autoSizeColumn((int)13,true);
				
				
				int rowCount1=sheet.getLastRowNum();//最后一行
				HSSFCell obj1=null;  
				HSSFCell obj12=null;  
				//合并光缆段名称
				String temp = sheet.getRow(1).getCell(1).getStringCellValue();
				int firstrow=1;
				for (int j = 2; j < rowCount1; j++) {
					obj1=sheet.getRow(j).getCell(1);
					if(!temp.equals(obj1.getStringCellValue()))
					{
						if(firstrow==j-1){
							firstrow=j;
							temp=sheet.getRow(j).getCell(1).getStringCellValue();
						}else{
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j -1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j -1, 1, 1));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j -1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j -1, 3, 3));
						firstrow = j;
						temp=sheet.getRow(j).getCell(1).getStringCellValue();
						}
					}
					if(j+1==rowCount1){
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j+1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j+1, 1, 1));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j+1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(firstrow, j+1, 3, 3));
					}
				}
				//清空第12列
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
		String fileName = "光交箱端口占用详细数据" + date.getTime() + ".xls";
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
	
	/**
	 * 导出纤芯占用统计
	 * @param request
	 * @param response
	 * @param session
	 * @param areaCode1
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/exportPortStat.htm")
	public Result exportPortStat(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1) {
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("端口占用统计"); 
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
		
		cell.setCellValue("光缆编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 1);
		
		cell.setCellValue("光缆名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 2);
		
		cell.setCellValue("占用芯数");
		cell.setCellStyle(style);
		cell = row.createCell((int) 3);
		
		cell.setCellValue("光缆芯数");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("占用百分比%");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(user.getOrgId()+"用户ID");
		System.out.println(areaCode1+"区号");
		List<CableStat> list1 = cableService.queryPortStat(user.getOrgId(),areaCode1);
		if(list1!=null){
			for (int i = 0; i < list1.size(); i++) {
				row = sheet.createRow(i + 1);
	
				cell = row.createCell(0);
				if(list1.get(i).getSectCode()!=null){
				cell.setCellValue((list1.get(i)).getSectCode());
				cell.setCellStyle(style);
				cell = row.createCell(1);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}
				
				if(list1.get(i).getSecName()!=null){
					cell.setCellValue((list1.get(i)).getSecName());
					cell.setCellStyle(style);
					cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
				}
				
				
				DecimalFormat df=new DecimalFormat("0.00");
					cell.setCellValue((list1.get(i)).getAseize());
					cell.setCellStyle(style);
					cell = row.createCell(3);
				
				if(list1.get(i).getFiberNum()!=null){
					cell.setCellValue((list1.get(i)).getFiberNum());
					cell.setCellStyle(style);
					cell = row.createCell(4);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(4);
				}
					
					if(list1.get(i).getFiberNum()!=null) {
						cell.setCellValue(df.format((list1.get(i)).getAseize()*1.0/(list1.get(i)).getFiberNum()*100)+"%");
						cell.setCellStyle(style);
						cell = row.createCell(5);
					}else {
						cell.setCellValue(0.00+"%");   
						cell.setCellStyle(style);
						cell = row.createCell(5);
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
		String fileName = "端口占用统计数据" + date.getTime() + ".xls";
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
	
	@RequestMapping("/cableAdds.htm")
	public String cableAdds(HttpServletRequest request ,HttpSession session ,Model model)
	{
		model.addAttribute("deviceTypeList",basecodeService.getDEVTypeList());
		return "cable/cableEdit";
	}
	
	@RequestMapping("/cableUpdates.htm")
	public String cableUpdates(Long cableId,HttpServletRequest request ,HttpSession session ,Model model)
	{
		Cable cable = cableService.selectById(cableId);
		String date = null;
		if(cable != null){
			if(cable.getCompleteDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.format(cable.getCompleteDate()); 
			}
		}
		model.addAttribute("deviceTypeList",basecodeService.getDEVTypeList());
		model.addAttribute("cable", cableService.convert(cable));
		model.addAttribute("date",date);
		return "cable/cableEdit";
	}
	
	/**
	 * 添加光缆信息
	 * @author YHT
	 * @time   2016年7月26日 下午8:15:23
	 * @param request
	 * @param session
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping("/cableAdd.htm")
	public Object cableAdd(HttpServletRequest request ,HttpSession session ,Cable param,Model model)
	{
		Result r = Result.get();
		try {
			Users user=(Users)session.getAttribute("platformUser");	
			param.setOrgId(user.getOrgId());
			int status = cableService.save(param);
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
	
	/**
	 * 查询光缆详细信息
	 * @author YHT
	 * @time   2016年7月26日 下午8:15:41
	 * @param request
	 * @param session
	 * @param cableId
	 * @param model
	 * @return
	 */
//	@RequestMapping("/cableInfo.htm")
//	public String cableInfo(HttpServletRequest request ,HttpSession session ,Long cableId,Model model)
//	{
//		Cable f=cableService.selectById(cableId);
//		String date = null;
//		if(f != null){
//			if(f.getCompleteDate() != null){
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				date = sdf.format(f.getCompleteDate()); 
//			}
//		}
//		
//		model.addAttribute("cable",cableService.convert(f));		
//		model.addAttribute("cableId",cableId);//必须，页面导航用
//		model.addAttribute("cableName",f.getCableName());//必须，页面导航用
//		model.addAttribute("pageFlag",PageFlag.baseInfo);//必须，页面导航用
//		model.addAttribute("date",date);
//		return "cable/cableBaseInfo";
//	}
	
	/**
	 * 删除光缆信息
	 * @author YHT
	 * @time   2016年7月26日 下午8:16:03
	 * @param request
	 * @param session
	 * @param cableId
	 * @param model
	 * @return
	 */
	@RequestMapping("/cableDelete.htm")
	@ResponseBody 
	public Object cableDelete(Long sectId)
	{
		Result r = Result.get();
		try {
			if(sectId==null){
				r.setR(0);
				r.putRContent("光缆段ID不能为空！");
				
				return r;
			}
			 
			int status = cableService.delete(sectId);
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
	
	@RequestMapping("/cablesChart.htm")
	public String cablesChart(Model model,String devCode,Long sectId)
	{
		
//		List<LinesBean> linesList=linesService.selectLineBySect(sectId);
//		
//			List<CableGroup> cableGroups=new ArrayList<CableGroup>();	
//
//			CableGroup fg=new CableGroup();			
//			fg.setGroupName(secName);
//				
//			Map<String,String> statusMap=new HashMap<String,String>();
//			statusMap.put("0", "未使用");
//			statusMap.put("1", "未成端");
//			statusMap.put("2", "已成端");
//			//statusMap.put("3", "成端错误");
//			fg.setStatusMap(statusMap);
//				
//			List<PortData> portDatas=new ArrayList<PortData>();
//			int i=0;//端口转为顺序号
//			int r=0;//行数
//			Map<String,String> portsMap=new HashMap<String,String>();//点与序号对应关系
//
//
//			for(LinesBean lines : linesList)
//			{
//				++i;
//				PortData pd1=new PortData();			
//				pd1.setCategory(statusMap.get(lines.getIsTerminat()));
//				pd1.setName(lines.getFiberNo()+"");
//				portsMap.put(lines.getAcode()+"", i+"");//记录对应关系					
//				//int x1=(int)(1+lines.getFiberNo()*40);
//				pd1.setX(r%10+1+"");
//				pd1.setY(r/10+1+"");
//				pd1.setV(lines.getAcode()+","+lines.getZcode());
//				portDatas.add(pd1);
//				++r; 
//			}
//
//				
//			fg.setPortDatas(portDatas);
//			int groupHeight=r*10+50;			
//			fg.setGroupHeight(groupHeight);
//			fg.setEchartsHeight(groupHeight+200);
//				
//			List<LinkData> links=new ArrayList<LinkData>();
//			for(LinesBean lines : linesList)
//			{
//				LinkData link=new LinkData();
////				link.setSource(portsMap.get(lines.getAcode()));
////				link.setTarget(portsMap.get(lines.getZcode()));
////				
//				link.setSource(portsMap.get(lines.getAcode()));
//				link.setTarget(portsMap.get(lines.getZcode()));
//				links.add(link);				
//			}
//				
//			fg.setLinkDatas(links);
//				
//				
//			cableGroups.add(fg);			
//					
//			model.addAttribute("fgs",cableGroups);
//			if(cableGroups!=null&&cableGroups.size()>0)
//			{
//				model.addAttribute("flag","1");
//			}
//			else
//			{
//				model.addAttribute("flag","0");
//			}
//			
			Facility cs = facilityService.selectByCode2(devCode);

			List<FiberdiscGroup> fiberdiscGroups=new ArrayList<FiberdiscGroup>();	
			List<Group> groups = new ArrayList<Group>();
			
			if(cs != null) {
				groups=fiberdiscService.selectSectGroupByDevId(cs.getDevId());
			}
			
			
			if(groups != null) {
				for(Group group:groups)
				{
					FiberdiscGroup fg=new FiberdiscGroup();			
					fg.setGroupName(group.getSide());
					
					Map<String,String> statusMap=new HashMap<String,String>();
					statusMap.put("0", " ");
					statusMap.put("1", "已成端");
					statusMap.put("2", "占用");
					fg.setStatusMap(statusMap);
					
					List<PortData> portDatas=new ArrayList<PortData>();
					int i=0;//端口转为顺序号
					int r=0;//行数
					Map<String,String> portsMap=new HashMap<String,String>();//点与序号对应关系
				
				
					for(Disc disc1:group.getDiscs())
					{
						Disc disc = discinfoService.selectDiscBySectId(group.getDevId(), group.getSide(), disc1.getDiscId(),sectId);
						PortData pd=new PortData();			
						//pd.setCategory("1");
						pd.setName("盘"+disc.getDiscName());		
						
						int y=1+(disc1.getRow()-1)*40;				
						int x=1;
						pd.setX(x+"");
						pd.setY(y+"");
						pd.setV(x+""+y+"");
						portDatas.add(pd);			
					    r++;
						for(Port pt:disc.getPorts())
						{
							i++;
							PortData pd1=new PortData();	
							
							if("1".equals(pt.getStatus())&&"1".equals(pt.getIsCd())) {
								pd1.setCategory(statusMap.get("2"));
							}else {
								pd1.setCategory(statusMap.get(pt.getStatus()));
							}
							pd1.setName(i+"");
							portsMap.put(pt.getCode(), i+"");//记录对应关系					
							int x1=1+(pt.getCol()+1-1)*40;
							pd1.setX(x1+"");
							pd1.setY(y+"");
							pd1.setV(pt.getCode());
							portDatas.add(pd1);
						}
						
					}
					
					fg.setPortDatas(portDatas);
					int groupHeight=r*50+100;			
					fg.setGroupHeight(groupHeight);
					fg.setEchartsHeight(groupHeight+200);
					
	//				List<LinkData> links=new ArrayList<LinkData>();
	//				for(Lines line:group.getLines())
	//				{
	//					LinkData link=new LinkData();
	//					link.setSource(portsMap.get(line.getAcode()));
	//					link.setTarget(portsMap.get(line.getZcode()));
	//					links.add(link);				
	//				}
	//				
	//				fg.setLinkDatas(links);
					
					
					fiberdiscGroups.add(fg);			
				}		
			}
			model.addAttribute("fgs",fiberdiscGroups);
			if(fiberdiscGroups!=null&&fiberdiscGroups.size()>0)
			{
				model.addAttribute("flag","1");
			}
			else
			{
				model.addAttribute("flag","0");
			}
	
		return "cable/cableChart";
	}

	/**
	 * 导出全部光缆段信息
	 * @param request
	 * @param response
	 * @param session
	 * @param areaCode1
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/allSectionExport.htm")
	public Result allSectionExport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1,CablesBean bean,PageBean pb) {
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("端口占用统计"); 
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
		
		cell.setCellValue("光缆编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 1);
		
		cell.setCellValue("光缆名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 2);
		
		cell.setCellValue("A端设施");
		cell.setCellStyle(style);
		cell = row.createCell((int) 3);
		
		cell.setCellValue("Z端设施");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("纤芯数");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("光缆状态");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
//		cell.setCellValue("成端情况");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 7);
		
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(areaCode1+"区号");
		if(areaCode1!=null && !"".equals(areaCode1)) {
			bean.setAreaCode1(areaCode1);
		}
		List<Cable> list1 = cableService.queryAllCableSection(bean,pb, user.getOrgId());
		if(list1!=null){
			for (int i = 0; i < list1.size(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				if(list1.get(i).getSecCode()!=null){
					cell.setCellValue(list1.get(i).getSecCode());
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}
				
				if(list1.get(i).getSecName()!=null){
					cell.setCellValue(list1.get(i).getSecName());
					cell.setCellStyle(style);
					cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
				}
				
				if(list1.get(i).getAdevName()!=null){
					cell.setCellValue(list1.get(i).getAdevName());
					cell.setCellStyle(style);
					cell = row.createCell(3);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(3);
				}
					
				if(list1.get(i).getZdevName()!=null) {
					cell.setCellValue(list1.get(i).getZdevName());
					cell.setCellStyle(style);
					cell = row.createCell(4);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(4);
				}
				
				if(list1.get(i).getFiberNum()!=null) {
					cell.setCellValue(list1.get(i).getFiberNum().toString());
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}
				
				if(list1.get(i).getSectState()!=null) {
					if("0".equals(list1.get(i).getSectState())) {
						cell.setCellValue("未核对");
					}if("1".equals(list1.get(i).getSectState())) {
						cell.setCellValue("正常");
					}if("2".equals(list1.get(i).getSectState())) {
						cell.setCellValue("新增");
					}if("3".equals(list1.get(i).getSectState())) {
						cell.setCellValue("修改");
					}if("4".equals(list1.get(i).getSectState())) {
						cell.setCellValue("资管删除");
					}if("5".equals(list1.get(i).getSectState())) {
						cell.setCellValue("新增删除");
					}
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(6);
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
		String fileName = "光缆段数据" + date.getTime() + ".xls";
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
	
	/**
	 * 导出末端光缆段信息
	 * @param request
	 * @param response
	 * @param session
	 * @param areaCode1
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/moDuanSectionExport.htm")
	public Result moDuanSectionExport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1,CablesBean bean,PageBean pb) {
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("端口占用统计"); 
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
		
		cell.setCellValue("光缆编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 1);
		
		cell.setCellValue("光缆名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 2);
		
		cell.setCellValue("A端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 3);
		
		cell.setCellValue("A端设施");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("Z端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("Z端设施");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
		cell.setCellValue("纤芯数");
		cell.setCellStyle(style);
		cell = row.createCell((int) 7);
		
		cell.setCellValue("光缆状态");
		cell.setCellStyle(style);
		cell = row.createCell((int) 8);
		
		cell.setCellValue("成端情况");
		cell.setCellStyle(style);
		cell = row.createCell((int) 9);
		
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(areaCode1+"区号");
		if(areaCode1!=null && !"".equals(areaCode1)) {
			bean.setAreaCode1(areaCode1);
		}
		bean.setIsTerminat("0");//末端光缆
		List<Cable> list1 = cableService.moDuanSectionExport(bean,pb, user.getOrgId());
		if(list1!=null){
			for (int i = 0; i < list1.size(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				if(list1.get(i).getSecCode()!=null){
					cell.setCellValue(list1.get(i).getSecCode());
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}
				
				if(list1.get(i).getSecName()!=null){
					cell.setCellValue(list1.get(i).getSecName());
					cell.setCellStyle(style);
					cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
				}
				
				if(list1.get(i).getDevCodeA()!=null){
					cell.setCellValue(list1.get(i).getDevCodeA());
					cell.setCellStyle(style);
					cell = row.createCell(3);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(3);
				}
				
				if(list1.get(i).getAdevName()!=null){
					cell.setCellValue(list1.get(i).getAdevName());
					cell.setCellStyle(style);
					cell = row.createCell(4);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(4);
				}
					
				if(list1.get(i).getDevCodeZ()!=null) {
					cell.setCellValue(list1.get(i).getDevCodeZ());
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}
				
				if(list1.get(i).getZdevName()!=null) {
					cell.setCellValue(list1.get(i).getZdevName());
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}
				
				if(list1.get(i).getFiberNum()!=null) {
					cell.setCellValue(list1.get(i).getFiberNum().toString());
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}
				
				if(list1.get(i).getSectState()!=null) {
					if("0".equals(list1.get(i).getSectState())) {
						cell.setCellValue("未核对");
					}if("1".equals(list1.get(i).getSectState())) {
						cell.setCellValue("正常");
					}if("2".equals(list1.get(i).getSectState())) {
						cell.setCellValue("新增");
					}if("3".equals(list1.get(i).getSectState())) {
						cell.setCellValue("修改");
					}if("4".equals(list1.get(i).getSectState())) {
						cell.setCellValue("资管删除");
					}if("5".equals(list1.get(i).getSectState())) {
						cell.setCellValue("新增删除");
					}
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}
					
				if(list1.get(i).getIsTerminat1()!=null) {
					cell.setCellValue(list1.get(i).getIsTerminat1());
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(9);
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
		String fileName = "末端光缆段数据" + date.getTime() + ".xls";
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
	
	/**
	 * 导出一端成端光缆段信息
	 * @param request
	 * @param response
	 * @param session
	 * @param areaCode1
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yiDuanSectionExport.htm")
	public Result yiDuanSectionExport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1,CablesBean bean,PageBean pb,String localState) {
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("端口占用统计"); 
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
		
		cell.setCellValue("光缆编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 1);
		
		cell.setCellValue("光缆名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 2);
		
		cell.setCellValue("A端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 3);
		
		cell.setCellValue("A端设施");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("Z端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("Z端设施");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
		cell.setCellValue("纤芯数");
		cell.setCellStyle(style);
		cell = row.createCell((int) 7);
		
		cell.setCellValue("光缆状态");
		cell.setCellStyle(style);
		cell = row.createCell((int) 8);
		
		cell.setCellValue("成端情况");
		cell.setCellStyle(style);
		cell = row.createCell((int) 9);
		
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(areaCode1+"区号");
		if(areaCode1!=null && !"".equals(areaCode1)) {
			bean.setAreaCode1(areaCode1);
		}
		bean.setIsTerminat(localState);//末端光缆
		List<Cable> list1 = cableService.yiDuanSectionExport(bean,pb, user.getOrgId());
		if(list1!=null){
			for (int i = 0; i < list1.size(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				if(list1.get(i).getSecCode()!=null){
					cell.setCellValue(list1.get(i).getSecCode());
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}
				
				if(list1.get(i).getSecName()!=null){
					cell.setCellValue(list1.get(i).getSecName());
					cell.setCellStyle(style);
					cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
				}
				
				if(list1.get(i).getDevCodeA()!=null){
					cell.setCellValue(list1.get(i).getDevCodeA());
					cell.setCellStyle(style);
					cell = row.createCell(3);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(3);
				}
				
				if(list1.get(i).getAdevName()!=null){
					cell.setCellValue(list1.get(i).getAdevName());
					cell.setCellStyle(style);
					cell = row.createCell(4);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(4);
				}
					
				if(list1.get(i).getDevCodeZ()!=null) {
					cell.setCellValue(list1.get(i).getDevCodeZ());
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}
				
				if(list1.get(i).getZdevName()!=null) {
					cell.setCellValue(list1.get(i).getZdevName());
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}
				
				if(list1.get(i).getFiberNum()!=null) {
					cell.setCellValue(list1.get(i).getFiberNum().toString());
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}
				
				if(list1.get(i).getSectState()!=null) {
					if("0".equals(list1.get(i).getSectState())) {
						cell.setCellValue("未核对");
					}if("1".equals(list1.get(i).getSectState())) {
						cell.setCellValue("正常");
					}if("2".equals(list1.get(i).getSectState())) {
						cell.setCellValue("新增");
					}if("3".equals(list1.get(i).getSectState())) {
						cell.setCellValue("修改");
					}if("4".equals(list1.get(i).getSectState())) {
						cell.setCellValue("资管删除");
					}if("5".equals(list1.get(i).getSectState())) {
						cell.setCellValue("新增删除");
					}
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}
					
				if(list1.get(i).getIsTerminat1()!=null) {
					cell.setCellValue(list1.get(i).getIsTerminat1());
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(9);
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
		String mingzi="";
		if("1".equals(localState)) {
			mingzi="一端成端光缆段数据";
		}if("2".equals(localState)) {
			mingzi="已成端光缆段数据";
		}if("4".equals(localState)) {
			mingzi="两端均未成端光缆段数据";
		}
		String fileName = mingzi + date.getTime() + ".xls";
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
	
	/**
	 * 导出已成端光缆段信息
	 * @param request
	 * @param response
	 * @param session
	 * @param areaCode1
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/yiChengDuanSectionExport.htm")
	public Result yiChengDuanSectionExport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1,CablesBean bean,PageBean pb) {
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("端口占用统计"); 
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
		
		cell.setCellValue("光缆编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 1);
		
		cell.setCellValue("光缆名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 2);
		
		cell.setCellValue("A端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 3);
		
		cell.setCellValue("A端设施");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("Z端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("Z端设施");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
		cell.setCellValue("纤芯数");
		cell.setCellStyle(style);
		cell = row.createCell((int) 7);
		
		cell.setCellValue("光缆状态");
		cell.setCellStyle(style);
		cell = row.createCell((int) 8);
		
		cell.setCellValue("成端情况");
		cell.setCellStyle(style);
		cell = row.createCell((int) 9);
		
		
		
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(areaCode1+"区号");
		if(areaCode1!=null && !"".equals(areaCode1)) {
			bean.setAreaCode1(areaCode1);
		}
		bean.setIsTerminat("2");//末端光缆
		List<Cable> list1 = cableService.yiChengDuanSectionExport(bean,pb, user.getOrgId());
		if(list1!=null){
			for (int i = 0; i < list1.size(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				if(list1.get(i).getSecCode()!=null){
					cell.setCellValue(list1.get(i).getSecCode());
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}
				
				if(list1.get(i).getSecName()!=null){
					cell.setCellValue(list1.get(i).getSecName());
					cell.setCellStyle(style);
					cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
				}
				
				if(list1.get(i).getDevCodeA()!=null){
					cell.setCellValue(list1.get(i).getDevCodeA());
					cell.setCellStyle(style);
					cell = row.createCell(3);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(3);
				}
				
				if(list1.get(i).getAdevName()!=null){
					cell.setCellValue(list1.get(i).getAdevName());
					cell.setCellStyle(style);
					cell = row.createCell(4);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(4);
				}
					
				if(list1.get(i).getDevCodeZ()!=null) {
					cell.setCellValue(list1.get(i).getDevCodeZ());
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(5);
				}
				
				if(list1.get(i).getZdevName()!=null) {
					cell.setCellValue(list1.get(i).getZdevName());
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}
				
				if(list1.get(i).getFiberNum()!=null) {
					cell.setCellValue(list1.get(i).getFiberNum().toString());
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}
				
				if(list1.get(i).getSectState()!=null) {
					if("0".equals(list1.get(i).getSectState())) {
						cell.setCellValue("未核对");
					}if("1".equals(list1.get(i).getSectState())) {
						cell.setCellValue("正常");
					}if("2".equals(list1.get(i).getSectState())) {
						cell.setCellValue("新增");
					}if("3".equals(list1.get(i).getSectState())) {
						cell.setCellValue("修改");
					}if("4".equals(list1.get(i).getSectState())) {
						cell.setCellValue("资管删除");
					}if("5".equals(list1.get(i).getSectState())) {
						cell.setCellValue("新增删除");
					}
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}
					
				if(list1.get(i).getIsTerminat1()!=null) {
					cell.setCellValue(list1.get(i).getIsTerminat1());
					cell.setCellStyle(style);
					cell = row.createCell(9);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(9);
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
		String fileName = "两端成端光缆段数据" + date.getTime() + ".xls";
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
	
	/**
	 * 导出重复光缆段信息
	 * @param request
	 * @param response
	 * @param session
	 * @param areaCode1
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/chongFuSectionExport.htm")
	public Result chongFuSectionExport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode1,CablesBean bean,PageBean pb) {
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("端口占用统计"); 
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
		
		cell.setCellValue("光缆编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 1);
		
		cell.setCellValue("光缆名称");
		cell.setCellStyle(style);
		cell = row.createCell((int) 2);
		
		cell.setCellValue("A端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 3);
		
		cell.setCellValue("A端设施");
		cell.setCellStyle(style);
		cell = row.createCell((int) 4);
		
		cell.setCellValue("Z端设施编码");
		cell.setCellStyle(style);
		cell = row.createCell((int) 5);
		
		cell.setCellValue("Z端设施");
		cell.setCellStyle(style);
		cell = row.createCell((int) 6);
		
		cell.setCellValue("纤芯数");
		cell.setCellStyle(style);
		cell = row.createCell((int) 7);
		
		cell.setCellValue("光缆状态");
		cell.setCellStyle(style);
		cell = row.createCell((int) 8);
		
		
//		cell.setCellValue("标记");
//		cell.setCellStyle(style);
//		cell = row.createCell((int) 9);
		
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(areaCode1+"区号");
		if(areaCode1!=null && !"".equals(areaCode1)) {
			bean.setAreaCode1(areaCode1);
		}
		bean.setIsTerminat("3");//末端光缆
		List<Cable> list1 = cableService.chongFuSectionExport(bean,pb, user.getOrgId());
		if(list1!=null){
			for (int i = 0; i < list1.size(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				if(list1.get(i).getSecCode()!=null){
					cell.setCellValue(list1.get(i).getSecCode());
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(1);
				}
				
				if(list1.get(i).getSecName()!=null){
					cell.setCellValue(list1.get(i).getSecName());
					cell.setCellStyle(style);
					cell = row.createCell(2);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(2);
				}
				
				if(list1.get(i).getDevCodeA()!=null){
					cell.setCellValue(list1.get(i).getDevCodeA());
					cell.setCellStyle(style);
					cell = row.createCell(3);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(3);
				}
				
				if(list1.get(i).getAdevName()!=null){
					cell.setCellValue(list1.get(i).getAdevName());
					cell.setCellStyle(style);
					cell = row.createCell(4);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(4);
				}
				
				if(list1.get(i).getDevCodeZ()!=null){
					cell.setCellValue(list1.get(i).getDevCodeZ());
					cell.setCellStyle(style);
					cell = row.createCell(5);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(5);
				}
					
				if(list1.get(i).getZdevName()!=null) {
					cell.setCellValue(list1.get(i).getZdevName());
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(6);
				}
				
				if(list1.get(i).getFiberNum()!=null) {
					cell.setCellValue(list1.get(i).getFiberNum().toString());
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(7);
				}
				
				if(list1.get(i).getSectState()!=null) {
					if("0".equals(list1.get(i).getSectState())) {
						cell.setCellValue("未核对");
					}if("1".equals(list1.get(i).getSectState())) {
						cell.setCellValue("正常");
					}if("2".equals(list1.get(i).getSectState())) {
						cell.setCellValue("新增");
					}if("3".equals(list1.get(i).getSectState())) {
						cell.setCellValue("修改");
					}if("4".equals(list1.get(i).getSectState())) {
						cell.setCellValue("资管删除");
					}if("5".equals(list1.get(i).getSectState())) {
						cell.setCellValue("新增删除");
					}
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}else {
					cell.setCellValue("");   
					cell.setCellStyle(style);
					cell = row.createCell(8);
				}
					
				
//				cell.setCellValue("重复");
//				cell.setCellStyle(style);
//				cell = row.createCell(7);
//				
//				if(list1.get(i).getEqualityIds()!=null) {
//					cell.setCellValue(list1.get(i).getEqualityIds().toString());
//					cell.setCellStyle(style);
//					cell = row.createCell(8);
//				}else {
//					
//					cell = row.createCell(8);
//					cell.setCellValue("");   
//					cell.setCellStyle(style);
//				}	
					
					
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
				
//				int rowCount1=sheet.getLastRowNum();//最后一行
//				String tempDevA = sheet.getRow(1).getCell(7).getStringCellValue();
//				int firstrowDevA=1;
//				HSSFCell obj1=null;
//				for (int j = 2; j < rowCount1; j++) {
//					obj1=sheet.getRow(j).getCell(7);
//					if(!tempDevA.equals(obj1.getStringCellValue()))
//					{
//						if(firstrowDevA==j-1){
//							firstrowDevA=j;
//							tempDevA=sheet.getRow(j).getCell(7).getStringCellValue();
//						}else{
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j -1, 7, 7));
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j -1, 6, 6));
//						firstrowDevA = j;
//						tempDevA=sheet.getRow(j).getCell(7).getStringCellValue();
//						}
//					}
//					if(j+1==rowCount1){
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j+1, 7, 7));
//						sheet.addMergedRegion(new CellRangeAddress(firstrowDevA, j+1, 6, 6));
//					}
//					
//					cell = row.getCell(7);
//					cell.setCellStyle(style);
//					cell.setCellValue("");   
//				}
				
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
		String fileName = "重复光缆段数据" + date.getTime() + ".xls";
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

	/**
	 * 熔纤盘分组
	 * @author 海阔天空
	 *
	 */
	public class FiberdiscGroup
	{
		private String groupName;//分组名称
		
		private Map<String,String> statusMap;//状态map
	
		private int groupWidth;//分组宽度
		
		private int groupHeight;//分组高度
		
		private int echartsHeight;//画布高度
		
		private List<PortData> portDatas;//端口数据
		
		private List<PortData> panDatas;//熔纤盘标识数据
		
		private List<LinkData> linkDatas;//关系数据	

		public List<PortData> getPortDatas() {
			return portDatas;
		}
	
		public void setPortDatas(List<PortData> portDatas) {
			this.portDatas = portDatas;
		}
	
		public List<LinkData> getLinkDatas() {
			return linkDatas;
		}
	
		public void setLinkDatas(List<LinkData> linkDatas) {
			this.linkDatas = linkDatas;
		}
	
		public String getGroupName() {
			return groupName;
		}
	
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
	
		public Map<String, String> getStatusMap() {
			return statusMap;
		}
	
		public void setStatusMap(Map<String, String> statusMap) {
			this.statusMap = statusMap;
		}
	
		public int getGroupWidth() {
			return groupWidth;
		}
	
		public void setGroupWidth(int groupWidth) {
			this.groupWidth = groupWidth;
		}
	
		public int getGroupHeight() {
			return groupHeight;
		}
	
		public void setGroupHeight(int groupHeight) {
			this.groupHeight = groupHeight;
		}
	
		public int getEchartsHeight() {
			return echartsHeight;
		}
	
		public void setEchartsHeight(int echartsHeight) {
			this.echartsHeight = echartsHeight;
		}
	
		public List<PortData> getPanDatas() {
			return panDatas;
		}
	
		public void setPanDatas(List<PortData> panDatas) {
			this.panDatas = panDatas;
		}
		
		
		
		
		
	}
	
	/**
	 * 端口数据
	 * @author 海阔天空
	 *
	 */
	public class PortData
	{
		private String category;//分类
		private String name;//名称
		private String x;//x坐标
		private String y;//y坐标
		private String v;//取值
		
		public String getV() {
			return v;
		}
		public void setV(String v) {
			this.v = v;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getX() {
			return x;
		}
		public void setX(String x) {
			this.x = x;
		}
		public String getY() {
			return y;
		}
		public void setY(String y) {
			this.y = y;
		}	
		
		
		
	}
	
	/**
	 * 线之间关系数据
	 * @author 海阔天空
	 *
	 */
	public class LinkData
	{
		private String source;
		private String target;
		
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getTarget() {
			return target;
		}
		public void setTarget(String target) {
			this.target = target;
		}
		
	}
		
	
	
	public enum Page1Flag{
		baseInfo,//基本信息
		cableMap //光缆段地理位置
	}
	
	@RequestMapping("/wellInfoList.htm") 
	public String wellInfoList(HttpSession session, Long devId,Long sectId,Model model){
		FacilityImgs wellList=facilityService.selectWellByDevId(devId,sectId);
		

		model.addAttribute("wellList", wellList);
		return "cable/wellInfoList";

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
	@RequestMapping("/cableSectionChart.htm")
	public String cableSectionChart(Model model,String devCode,Long sectId,Integer state) {
		Facility f = facilityService.selectByCode2(devCode);
		CableSectionChart chart=null;
		if(f!=null) {
			chart=fiberdiscService.cableSectionChart(f.getDevId(),sectId,state);
		}
		model.addAttribute("chart", chart);
		return "cable/cableSectionChart";
	}
}
