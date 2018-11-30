package com.ycnet.facility.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.Constants;
import com.ycnet.core.Constants.FACILITTYPE;
import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.Page;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Project;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.service.CableSectionService;
import com.ycnet.frms.service.DeviceRegService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.MobileSwitchService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.ProjectService;
import com.ycnet.frms.vo.ExportJumper;
import com.ycnet.frms.vo.FacilityAll;
import com.ycnet.frms.vo.FacilityAsasideCableBean;
import com.ycnet.frms.vo.FacilityBean;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.RouteBean;
import com.ycnet.mobile.util.Result;

@Controller
public class FacilitysController {

	@Resource(name="facilityService")
	private FacilityService facilityService;
	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	@Resource(name="areasService")
	private AreasService areasService;
	
	@Resource(name="organizitionService")
	private OrganizitionService  organizitionService;
	
	@Resource(name="cableSectionService")
	private CableSectionService cableSectionService;
	
	@Resource(name="mobileSwitchService")
	private MobileSwitchService mobileSwitchService;
	
	@Resource(name="deviceRegService")
	DeviceRegService deviceRegService;
	
	@Resource
	private AreasService areaService;
	
	@Resource
	private OrganizitionService orgService;
	
	private static final Logger LOG = LoggerFactory.getLogger(FacilitysController.class);
	/**
	 * 
	* @Title: exportFacilityTemp 
	* @Description: TODO(修改导入设施汇聚区BUG) 
	* @param @param response
	* @param @param request    入参
	* @return void    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年10月25日 下午3:29:47 
	* @version V1.0
	 */
    @RequestMapping("/exportFacilityTemp.htm")
    public void exportFacilityTemp(HttpServletResponse response,HttpServletRequest request) {
    	facilityService.exportFacilityTemp(response,request);
    }
	
	@RequestMapping("/importFacilities.htm")
	public void importFacilities(HttpServletRequest request,HttpServletResponse response,MultipartHttpServletRequest multiRequest){
		response.setContentType("text/html;charset=UTF-8");
		String result = "0";
		try {
		    result = this.facilityService.importFacilitiesByExcel(request, response, multiRequest);
		    PrintWriter out = response.getWriter();
		    out.append("<script>alert('"+result+"');</script>");
		    out.flush();
		} catch (Exception e) {
		    e.printStackTrace();
		    
		}
	}
	
	
	/**
	 * 查询设施列表,添加导出功能  刘沧海 2017/10/17
	 * @param session
	 * @param facilityCon 查询条件
	 * @param pb 分页信息
	 * @param model 
	 *  zhouyu 1/15修改
	 * @return
	 */
	@RequestMapping("/facilityList.htm")
	public String facilityList(HttpSession session ,@ModelAttribute FacilityConditionBean facilityCon,PageBean pb ,Model model)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		String areaCode =null;
		Organizition o = orgService.selectById(user.getOrgId());
		Areas area = new Areas();
		if(o!=null) {
			area.setParentAreaCode(o.getCode1());
		}
//		if (facilityCon!=null&&facilityCon.getAreaCode1()!=""&&facilityCon.getAreaCode1()!=null) {
//			facilityCon.setAreaCode1(facilityCon.getAreaCode1().substring(0, facilityCon.getAreaCode1().length()-1));
//		}
		if(facilityCon!=null&&facilityCon.getAreaCode()!=""&&facilityCon.getAreaCode()!=null) {
			areaCode=facilityCon.getAreaCode();
		}else {
			areaCode = o.getCode1();
		}
			
//		area.setParentAreaCode(areaCode);
//		if(facilityCon!=null&&facilityCon.getAreaCode1()!=""&&facilityCon.getAreaCode1()!=null) {
//			area.setAreaCode(facilityCon.getAreaCode1());
//		}
//		area.setAreaRank("3");
		
		 
		String a=facilityCon.getAreaCode();
		facilityCon.setSurveyResult("".equals(facilityCon.getSurveyResult())?null:facilityCon.getSurveyResult());
		model.addAttribute("pb",facilityService.queryByConditionBean(facilityCon,user,pb));
		model.addAttribute("deviceTypeList",basecodeService.getDEVTypeList());		
		model.addAttribute("DevTypeall",Constants.DevTypeall);		
		model.addAttribute("areasServiceList",areasService.selectByOrgId(user.getOrgId()));
		model.addAttribute("surveyResultList",Constants.SurveyResultMap);	
		model.addAttribute("CheckTypeMap",Constants.CheckTypeMap);
		model.addAttribute("facilityCon",facilityCon); 
		model.addAttribute("areaList",areaService.selectByAreaRank(area));
		if (facilityCon !=null && facilityCon.getAreaCode1()!=null && !"".equals(facilityCon.getAreaCode1())) {
			model.addAttribute("areaCode2",facilityCon.getAreaCode1());
		}
		model.addAttribute("devStateList", Constants.FacilityState);///fl修改
		model.addAttribute("existLngLat", Constants.existLngLat);///fl修改
		return "facility/facilityList";
	}

	/**
	 * 加载设施添加页面
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/facilityLoadAdd.htm")
	public String facilityLoadAdd(HttpServletRequest request ,HttpSession session ,Model model)
	{	
		Users user = (Users) session.getAttribute("platformUser");
		String areaCode =null;
		Organizition o = orgService.selectById(user.getOrgId());
		if(o!=null)
			areaCode = o.getCode1();
		
		Areas area = new Areas();
		area.setParentAreaCode(areaCode);
		area.setAreaRank("3");
	
		model.addAttribute("deviceTypeList",basecodeService.getDEVTypeList());
		model.addAttribute("areasServiceList",areasService.selectByOrgId(user.getOrgId()));
		model.addAttribute("areasList",areasService.selectByAreaRank(area));
		model.addAttribute("surveyResultList",Constants.SurveyResultList);
		model.addAttribute("flagMap",Constants.FlagMap);
		model.addAttribute("CheckTypeMap",Constants.CheckTypeMap);
		model.addAttribute("devStateList", Constants.FacilityState);///fl修改
		return "facility/facilityEdit";
	}
	
	/**
	 * 加载设施修改页面
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/facilityLoadUpdate.htm")
	public String facilityLoadUpdate(Long devId ,HttpSession session ,Model model)
	{
		Facility f=facilityService.selectById(devId);
		Users user=(Users)session.getAttribute("platformUser");	
		String date = null;
		if(f != null){
			if(f.getCompleteDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.format(f.getCompleteDate()); 
			}
		}
		/**
		 * zhouyu1/16改
		 */
		String areaCode =null;
		Organizition o = orgService.selectById(user.getOrgId());
		if(o!=null)
			areaCode = o.getCode1();
		
		Areas area = new Areas();
		area.setParentAreaCode(areaCode);
		area.setAreaRank("3");
		model.addAttribute("areasList",areasService.selectByAreaRank(area));
		
		model.addAttribute("deviceTypeList",basecodeService.getDEVTypeList());
		model.addAttribute("areasServiceList",areasService.selectByOrgId(user.getOrgId()));
		model.addAttribute("surveyResultList",Constants.SurveyResultList);
		model.addAttribute("flagMap",Constants.FlagMap);
		model.addAttribute("CheckTypeMap",Constants.CheckTypeMap);
		model.addAttribute("devStateList", Constants.FacilityState);///fl修改
		model.addAttribute("facility",f);
		model.addAttribute("date",date);
		if(f.getProId()!=null) {
			model.addAttribute("proId", f.getProId());
			Project pro=projectService.selectById(f.getProId());
			//zhouyu 1/29改 防止空指针
			if(pro!=null) {
				model.addAttribute("proName",pro.getProName());
			}
			
		}
		return "facility/facilityEdit";
	}
	
	/**
	 * 保存设施信息
	 * @param param 设施基本信息
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/facilitySave.htm")
	public Object facilitySave(Facility param,Model model,HttpSession session)
	{		
		Result r = Result.get();
		try {
			Users users=(Users)session.getAttribute("platformUser");
			param.setOrgId(users.getOrgId());
			if(param.getAreaCode1()==null|| "".equals(param.getAreaCode1())){
				Organizition o = organizitionService.selectById(users.getOrgId());
				if(o!=null)
					param.setAreaCode1(o.getCode1());
			}
			if(!"0".equals(param.getCheckType())) {
				param.setCheckUserId(users.getUserId());
			}
			Long status = facilityService.save(param,users);
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
	 * 删除设施信息
	 * @param devId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/facilityDelete.htm")
	public Object facilityDelete(Long devId)
	{
		Result r = Result.get();
		try {
			if(devId==null){
				r.setR(0);
				r.putRContent("设施ID不能为空！");
				
				return r;
			}
			
			int status = facilityService.delete(devId);
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

	
	@ResponseBody 
	@RequestMapping("/queryListByContent.htm")
	public Object queryListByContent(HttpSession session,String content){
		Result r = Result.get();
		Users user=(Users)session.getAttribute("platformUser");	
		//zhouyu 1/4修改返回值类型facility-facilityBean
		List<FacilityBean> list = facilityService.queryListByContent(content,user.getOrgId());
		List<KVBean> type = basecodeService.getDEVTypeList();
		if(list!=null && list.size()>0) {
			for (FacilityBean fb : list) {
				for (KVBean kvBean : type) {
					if(kvBean.getName().equals(fb.getDevType())) {
						fb.setDevTypeName(kvBean.getValue());
					}
				}
			}
		}
		return r.putDtList(list).initQueryMessage();
	}
	
	@RequestMapping("/facilityMap.htm")
	public String facilityMap()
	{
		return "facility/facilityMap";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/facilityBdMap.htm")
	public String facilityBaiduMap()
	{
		return "redirect:/showSitesBdMap.htm";
	}
	
	@RequestMapping("/facilityMapTreeList.htm")
	public String facilityMapTreeList(HttpSession session ,Model model)
	{		
		Users user=(Users)session.getAttribute("platformUser");	
//		model.addAttribute("areasServiceList",areasService.selectByOrgId(user.getOrgId()));
		model.addAttribute("areasServiceList",areasService.selectByOrgIdParent(user.getOrgId()));
		return "facility/facilityMapTreeList";
		
	}
	
	@RequestMapping("/showSitesMap.htm")
	public String showSitesMap(HttpSession session, String areaCode1,Model model)
	{
		String areaName = null;
		if(areaCode1==null||"".equals(areaCode1))
		{
			Users user=(Users)session.getAttribute("platformUser");	
			Organizition o = null;
			if(user!=null)
				o = organizitionService.selectById(user.getOrgId());
			if(o!=null)
				areaCode1 = o.getCode1();
			
			//根据"市辖区"搜索地图位置,因目标不明确,地图会不显示
			if(o.getCode2()==null || "0".equals(o.getCode2())){
				Areas area = areasService.selectByCode(areaCode1);
				areaName = area.getAreaName();//惠州市
			}else{
				Areas area2 = areasService.selectByCode(o.getCode2());//市辖区
				Areas area1 = areasService.selectByCode(o.getCode1());//惠州市
				if("市辖区".equals(area2.getAreaName())){
					areaName = area1.getAreaName()+area2.getAreaName();//惠州市市辖区>>地图可以显示
				}else{
					areaName = area1.getAreaName()+area2.getAreaName();;//惠州市惠城区
				}
			}
		}else{
			Areas areas = areasService.selectByCode(areaCode1);//二级信息
			String areaNames = areas.getAreaName();//二级的地区名
			String areaCodep = areas.getParentAreaCode();//上级area_code
			Areas areap = areasService.selectByCode(areaCodep);//上级信息
			String areaNamep = areap.getAreaName();//上级地区名
			areaName = areaNamep;
		}
		model.addAttribute("areaName",areaName);
		model.addAttribute("areacode",areaCode1);
		return "facility/showSitesMap3";
		
	}
	
	/**
	 * 按位置浏览
	 * @param session
	 * @param areaCode1
	 * @param model
	 * @return
	 */
	@RequestMapping("/showSitesBdMap.htm")
	public String showSitesBdMap(HttpSession session, String areaCode1,Model model)
	{
		String areaName = null;
		Areas pArea = new Areas();
		if(areaCode1==null||"".equals(areaCode1))
		{
			Users user=(Users)session.getAttribute("platformUser");	
			Organizition o = null;
			if(user!=null)
				o = organizitionService.selectById(user.getOrgId());
			if(o!=null) {
				areaCode1 = o.getCode1();
				pArea.setParentAreaCode(o.getCode1());				
			}
			
			//根据"市辖区"搜索地图位置,因目标不明确,地图会不显示
			if(o.getCode2()==null || "0".equals(o.getCode2())){
				Areas area = areasService.selectByCode(areaCode1);
				areaName = area.getAreaName();//惠州市
			}else{
				Areas area2 = areasService.selectByCode(o.getCode2());//市辖区
				Areas area1 = areasService.selectByCode(o.getCode1());//惠州市
				if("市辖区".equals(area2.getAreaName())){
					areaName = area1.getAreaName()+area2.getAreaName();//惠州市市辖区>>地图可以显示
				}else{
					areaName = area1.getAreaName()+area2.getAreaName();;//惠州市惠城区
				}
			}
		}else{
			Areas areas = areasService.selectByCode(areaCode1);//二级信息
			String areaNames = areas.getAreaName();//二级的地区名
			String areaCodep = areas.getParentAreaCode();//上级area_code
			Areas areap = areasService.selectByCode(areaCodep);//上级信息
			String areaNamep = areap.getAreaName();//上级地区名
			areaName = areaNamep;
			pArea.setParentAreaCode(areaCodep);
		}
		model.addAttribute("areaName",areaName);
		model.addAttribute("areacode",areaCode1);
		model.addAttribute("areaList",areaService.selectByAreaRank(pArea));
		model.addAttribute("deviceTypeList",basecodeService.getDEVTypeList());
		return "facility/showSitesMap6";
		
	}
	
//	@RequestMapping("/showSitesOLMap.htm")
//	public String showSitesOLMap(HttpSession session, String areaCode1,Model model) throws Exception
//	{
//		String areaName = null;
//		Areas pArea = new Areas();
//		if(areaCode1==null||"".equals(areaCode1))
//		{
//			Users user=(Users)session.getAttribute("platformUser");	
//			Organizition o = null;
//			if(user!=null)
//				o = organizitionService.selectById(user.getOrgId());
//			if(o!=null) {
//				areaCode1 = o.getCode1();
//				pArea.setParentAreaCode(o.getCode1());				
//			}
//			
//			//根据"市辖区"搜索地图位置,因目标不明确,地图会不显示
//			if(o.getCode2()==null || "0".equals(o.getCode2())){
//				Areas area = areasService.selectByCode(areaCode1);
//				areaName = area.getAreaName();//惠州市
//			}else{
//				Areas area2 = areasService.selectByCode(o.getCode2());//市辖区
//				Areas area1 = areasService.selectByCode(o.getCode1());//惠州市
//				if("市辖区".equals(area2.getAreaName())){
//					areaName = area1.getAreaName()+area2.getAreaName();//惠州市市辖区>>地图可以显示
//				}else{
//					areaName = area1.getAreaName()+area2.getAreaName();;//惠州市惠城区
//				}
//			}
//		}else{
//			Areas areas = areasService.selectByCode(areaCode1);//二级信息
//			String areaNames = areas.getAreaName();//二级的地区名
//			String areaCodep = areas.getParentAreaCode();//上级area_code
//			Areas areap = areasService.selectByCode(areaCodep);//上级信息
//			String areaNamep = areap.getAreaName();//上级地区名
//			areaName = areaNamep;
//			pArea.setParentAreaCode(areaCodep);
//		}
//		//model.addAttribute("areaName",areaName);
//		model.addAttribute("areaLocation",QQMapWsApi.getLocationByPCD(areaName));
//		model.addAttribute("areacode",areaCode1);
//		return "facility/showSitesMap5";
//	}
	
	/**
	 * 按位置浏览-高级查询-根据条件返回设备信息
	 * @param session
	 * @param areaCode
	 * @param areaCode1
	 * @param devType
	 * @param devName
	 * @param devCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "facility/findFacilitysToBdMap.htm")
	public Object findFacilitysToBdMap(HttpSession session, String areaCode, String areaCode1, String devType,
					String devName, String devCode){
		System.out.println(areaCode + "," + areaCode1 + "," + devType + "," + devName + "," + devCode);
		Users user=(Users)session.getAttribute("platformUser");	
		List<Map<String, Object>> list = facilityService.findFacilitysToBdMap(areaCode, areaCode1, devType, devName, devCode, user.getOrgId());
		return list;
	}
	
	/**
	 * 
	* @Title: onSearch 
	* @Description: TODO(按位置浏览搜索栏,模糊搜索设施名称) 
	* @param @param session
	* @param @param searchName
	* @param @return    入参
	* @return Object    返回类型
	* @author （DZY） 
	* 修改：刘沧海，按照汇聚区模糊查询
	* @throws
	* @date 2017年8月23日 下午4:28:25 
	* @version V1.0
	 */
	@RequestMapping("/onSearch.htm")
	@ResponseBody
	public Object onSearch(HttpSession session,String searchName,String areaCode1){
		List<Facility> list =new ArrayList<Facility>();
		if(searchName.trim() != null && !"".equals(searchName.trim())){
			Users user=(Users)session.getAttribute("platformUser");
			list =facilityService.onSearchFacilities(user.getOrgId(),searchName,areaCode1);
		}
		return list;
	}
	/**
	 * 
	* @Title: findByDevId 
	* @Description: 根据设施ID查询光缆段 
	* @param @param devId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午5:13:17 
	* @version V1.0
	 */
	@RequestMapping("/findbyDevId.htm")
	@ResponseBody
	public Object findByDevId(HttpSession session,Long devId) {
		Users user=(Users)session.getAttribute("platformUser");
		List<Facility> facilityBean=facilityService.findbyDevId(devId,user);
		if(facilityBean !=null && facilityBean.size()>0) {
			return facilityBean;
		}else {
			return 0;
		}
	}
	/**
	 * 
	* @Title: findCDState 
	* @Description: 根据设施ID查询所属光缆段 
	* @param @param session
	* @param @param devId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午9:27:24 
	* @version V1.0
	 */
	@RequestMapping("/findCDState.htm")
	@ResponseBody
	public Object findCDState(HttpSession session,Long devId) {
		Users user=(Users)session.getAttribute("platformUser");
		FacilityAsasideCableBean bean=facilityService.findCDState(devId,user.getOrgId());
		return bean;
	}
	
	/**
	 * 获取设施下拉列表
	 * @return
	 */ 
	@ResponseBody 
	@RequestMapping("/json/facility/list")
	public Object facilityTinSelect(HttpSession session,FacilityConditionBean facilityCon)
	{
		
		
		 Users user=(Users)session.getAttribute("platformUser");	
		 PageBean pb=new PageBean();
		 pb.setPageNo(1);
		 pb.setPageSize(4000);
		 pb=facilityService.simpleQueryByConditionBean(facilityCon,user,pb);
		 @SuppressWarnings("unchecked")
		List<Facility> facilityList=(List<Facility>) pb.getList();
		
		List<TinSelect> tinSelects=new ArrayList<TinSelect>();
		for(Facility f:facilityList)
		{
			TinSelect t=new TinSelect(f.getDevId(),f.getDevName());
			tinSelects.add(t);
		}
		
		return tinSelects;
	}
	
	@Resource(name="projectService")
	private ProjectService projectService;
	
	@ResponseBody 
	@RequestMapping("/json/project/List.htm")
	public Object findProjectList(HttpServletRequest request,HttpSession session,PageBean pb,Long proId,Model model)
	{
		Users user=(Users)session.getAttribute("platformUser");
		pb.setPageNo(1);
		pb.setPageSize(6000);
		@SuppressWarnings("unchecked")
		List<Project> list = (List<Project>)projectService.queryByProjectBean(null,pb,user).getList();
		List<TinSelect> tinSelects=new ArrayList<TinSelect>();
		for(Project f:list)
		{
			TinSelect t=new TinSelect(f.getProId(),f.getProName());
			tinSelects.add(t);
		}
		return tinSelects;
	}
	
	/**
	 * 可查询下拉列表json实体类
	 * @author 海阔天空
	 *
	 */
	public static class TinSelect
	{
		private Long val;
		private String text;
		
		
		public TinSelect(Long val, String text) {
			super();
			this.val = val;
			this.text = text;
		}
		public Long getVal() {
			return val;
		}
		public void setVal(Long val) {
			this.val = val;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
	}

	/**
	 * 获取点位信息集合
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findFacilities.htm")
	public List<Facility> findFacilities(HttpSession session){
		List<Facility> list =new ArrayList<Facility>();
		Users user=(Users)session.getAttribute("platformUser");
		list =facilityService.findFacilities(user.getOrgId());
		return list;
	}
	/**
	* 
	* @Title: findFacilitiesAreaCode1 
	* @Description: 根据汇聚区查询 
	* @param @param session
	* @param @param areaCode1
	* @param @return    
	* @return List<Facility>
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午1:48:06 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/findFacilitiesAreaCode1.htm")
	public Object findFacilitiesAreaCode1(HttpSession session,String areaCode1){
		Users user=(Users)session.getAttribute("platformUser");
		return facilityService.findFacilitiesAreaCode1(user.getOrgId(),areaCode1);
	}
	
	/**
	 * 每个区的一个设备及其位置信息
	 * @param session
	 * @param areaCode1
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findFacilitiesACode1ByOrgId.htm")
	public Object findFacilitiesACode1ByOrgId(HttpSession session,String areaCode1){
		Users user=(Users)session.getAttribute("platformUser");
		List<Map<String, Object>> list = facilityService.findFacilitiesACode1ByOrgId(user.getOrgId(),areaCode1);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/findFacilitiesGeoPointByOrgId.htm")
	public Object findFacilitiesGeoPointByOrgId(HttpSession session,String areaCode1) {
		Users user=(Users)session.getAttribute("platformUser");
		Map<String, Object> map = facilityService.findFacilitiesGeoPointByOrgId(user.getOrgId(), areaCode1);
		return map;
	}
	
	/**
	 * 根据汇聚去查询设备表
	* @Title: selectMobileSwitchAreaCode1 
	* @Description: 根据汇聚去查询设备表
	* @param @param session
	* @param @param areaCode1
	* @param @return    
	* @return List<Facility>
	* @author fl 
	* @throws
	* @date 2018年2月8日 下午3:48:06 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/selectDeviceRegAreaCode1.htm")
	public HashMap<String, Object> selectDeviceRegAreaCode1(HttpSession session,String areaCode1,String devName){
		Users user=(Users)session.getAttribute("platformUser");
		HashMap<String, Object>map =deviceRegService.selectDeviceRegAreaCode1(user.getOrgId(),areaCode1,devName);
		return map;
	}
	
	
	
	/**
	 * gps点位转化成百度标准点位
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changeFacility.htm")
	public String changeFacility(HttpSession session){
		Users user=(Users)session.getAttribute("platformUser");
		String message="false";
		message=facilityService.changeFacility(user.getOrgId());
		return message;
		
	}
	
	/**
	 * a/z端光纤段
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findFiber.htm")
	public List<CableSection> findFiber(HttpSession session,String areaCode1){
		Users user=(Users)session.getAttribute("platformUser");
		List<CableSection> list =cableSectionService.findFiber(user.getOrgId(),areaCode1);
		return list;
	}

	/**
	 * a/z端光纤段
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findFiber2.htm")
	public Object findFibe2(HttpSession session,String areaCode1){
		Users user=(Users)session.getAttribute("platformUser");
		List<Map<String, Object>> list =cableSectionService.findFiber2(user.getOrgId(),areaCode1);
		return list;
	}

	
	/**
	 * 
	* @Title: tn_device_reg 
	* @Description: TODO(点击设备状态弹出该设施锁的状态) 
	* @param @param devId
	* @param @param model
	* @param @return    入参
	* @return String    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年8月24日 下午4:51:58 
	* @version V1.0
	 */
	@RequestMapping("/facilityLock.htm")
	public String lockList(Long devId, Model model) {
		model.addAttribute("LockList", deviceRegService.queryFacilityLock(devId));
		return "facility/facilityLock";
	}
	
	/**
	 * 根据是否存在经纬度导出数据
	* 
	* @Title: FacilitysController.java 
	* @Description: TODO 
	* @param @param session
	* @param @param existLngLat
	* @param @return
	* @return Result
	* @author fl
	* @date 2018年1月24日 下午4:50:47
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/exprotIstLngLat.htm")
	public Result exprotIstLngLat(HttpSession session,String existLngLat,PageBean pb,HttpServletRequest request) {
		Result r = Result.get();
		try {
			// 声明一个工作薄
			HSSFWorkbook wb = new HSSFWorkbook();
			
			// 生成一个样式 
			HSSFCellStyle style = wb.createCellStyle();
			// 设置四边的边框 
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			
			
			// 设置水平对齐的样式为居中对齐; 
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 设置垂直对齐的样式为居中对齐;
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 样式字体居中
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
	        HSSFRow row = null;//创建一行  
	        HSSFCell cell = null;//每个单元格  
	        // 声明一个单子并命名 
			HSSFSheet sheet = wb.createSheet("按照有无经纬度导出设施表"); 
			sheet.setColumnWidth(0, 20*256);      
			// 创建第一行（也可以称为表头）
			row = sheet.createRow(0);
			// 给单子名称一个长度
			sheet.setDefaultColumnWidth((int) 15);
//	        writeTitleContent(sheet,style);//写入标题
			 //标题  
	        row = sheet.createRow(0);          
	        //第一行写入标题行  
	        cell = row.createCell((int) 0); //
	        cell.setCellStyle( style); 
	        cell.setCellValue("序号");  
			
	        cell.setCellValue("设施编号");
	        cell.setCellStyle(style);
	        cell = row.createCell((int) 1);
	        
	        cell.setCellValue("设施名称");
	        cell.setCellStyle(style);
	        cell = row.createCell((int) 2);
			
//	        cell.setCellValue("设施类型");
//	        cell.setCellStyle(style);
//	        cell = row.createCell((int) 3);
	        
	        cell.setCellValue("设施类型");
	        cell.setCellStyle(style);
	        cell = row.createCell((int) 3);
			
	        cell.setCellValue("所属机房");
	        cell.setCellStyle(style);
	        cell = row.createCell((int) 4);
	        
	        cell.setCellValue("所属设施");
	        cell.setCellStyle(style);
	        cell = row.createCell((int) 5);
	        
	        cell.setCellValue("核查状态");
	        cell.setCellStyle(style);
	        cell = row.createCell((int) 6);
	        
	        cell.setCellValue("设施状态");
	        cell.setCellStyle(style);
	        cell = row.createCell((int) 7);
	        
			// 向单元格里填充数据
			Users user = (Users) session.getAttribute("platformUser");
			//数据
			List<FacilityBean> list1=facilityService.queryExportExisttLngLat(user.getOrgId(),existLngLat);
			//导出设施数据
			if(list1!=null && list1.size()>0) {
				for (int i = 0; i < list1.size(); i++) {
					row = sheet.createRow(i+1);
					cell = row.createCell(0);
					cell.setCellStyle(style);
					cell.setCellValue(1);//序号  
//						//导出设施数据
					if(list1.get(i).getDevCode()!=null){
						cell.setCellValue((list1.get(i)).getDevCode());
						cell.setCellStyle(style);
						cell = row.createCell(1);
						}else{
							cell.setCellValue("");
							cell.setCellStyle(style);
							cell = row.createCell(1);
						}
					
					if(list1.get(i).getDevName()!=null){
						cell.setCellValue((list1.get(i)).getDevName());
						cell.setCellStyle(style);
						cell = row.createCell(2);
						}else{
							cell.setCellValue("");
							cell.setCellStyle(style);
							cell = row.createCell(2);
						}
					
						
					cell.setCellValue(list1.get(i).getDevType());
					cell.setCellStyle(style);
					cell = row.createCell(3);
					
//					if(list1.get(i).getDevModel()!=null){
//							cell.setCellValue((list1.get(i)).getDevModel());
//						cell.setCellStyle(style);
//						cell = row.createCell(3);
//						}else{
//							cell.setCellValue("");
//							cell.setCellStyle(style);
//							cell = row.createCell(3);
//					}
					
					if(list1.get(i).getRoom()!=null){
						cell.setCellValue((list1.get(i)).getRoom());
						cell.setCellStyle(style);
						cell = row.createCell(4);
						}else{
							cell.setCellValue("");
							cell.setCellStyle(style);
							cell = row.createCell(4);
					}
					
					if(list1.get(i).getPdevName()!=null){
						cell.setCellValue((list1.get(i)).getPdevName());
						cell.setCellStyle(style);
						cell = row.createCell(5);
						}else{
							cell.setCellValue("");
							cell.setCellStyle(style);
							cell = row.createCell(5);
					}
					
					if(list1.get(i).getCheckType()!=null){
						if (list1.get(i).getCheckType().equals("0")) {
							cell.setCellValue("已巡检");
						}
						if (list1.get(i).getCheckType().equals("1")) {
							cell.setCellValue("已锁定");
						}
						if (list1.get(i).getCheckType().equals("2")) {
							cell.setCellValue("未巡检");
						}
						cell.setCellStyle(style);
						cell = row.createCell(6);
						}else{
							cell.setCellValue("");
							cell.setCellStyle(style);
							cell = row.createCell(6);
					}

					if(list1.get(i).getDevState()!=null){
						if (list1.get(i).getDevState().equals("0")) {
							cell.setCellValue("未核对");
						}
						if (list1.get(i).getDevState().equals("1")) {
							cell.setCellValue("与现场一致");
						}
						if (list1.get(i).getDevState().equals("2")) {
							cell.setCellValue("新增");
						}
						if (list1.get(i).getDevState().equals("3")) {
							cell.setCellValue("修改");
						}
						if (list1.get(i).getDevState().equals("4")) {
							cell.setCellValue("未找到");
						}
						if (list1.get(i).getDevState().equals("5")) {
							cell.setCellValue("新增删除");
						}
						cell.setCellStyle(style);
						cell = row.createCell(7);
						}else{
							cell.setCellValue("");
							cell.setCellStyle(style);
							cell = row.createCell(7);
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
					sheet.autoSizeColumn((int)7,true);
				}
			}
			
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
				r.putRContent("导出异常！");
		}
		return r;
	}
	
	/**
	 * 
	* @Title: exportAll 
	* @Description: TODO(导出设施,机房和光交箱) 
	* @param @param request
	* @param @param response
	* @param @param session
	* @param @param areaCode
	* @param @return    入参
	* @return Result    返回类型
	* @author （刘沧海） fl修改 (空指针问题01.31)
	* @throws
	* @date 2017年11月1日 下午5:17:04 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/exportAll.htm")
	public Result exportAll(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode) {
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		
		// 生成一个样式 
		HSSFCellStyle style = wb.createCellStyle();
		// 设置四边的边框 
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		
		// 设置水平对齐的样式为居中对齐; 
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
        HSSFRow row = null;//创建一行  
        HSSFCell cell = null;//每个单元格  

        // 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("光交箱"); 
		sheet.setColumnWidth(0, 20*256);      
		// 创建第一行（也可以称为表头）
		row = sheet.createRow(0);
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((int) 15);
//        writeTitleContent(sheet,style);//写入标题
		 //标题  
        row = sheet.createRow(0);          
        //第一行写入标题行  
        cell = row.createCell((int) 0); //
        cell.setCellStyle( style); 
        cell.setCellValue("序号");  
		
        cell.setCellValue("设备编号");
        cell.setCellStyle(style);
        cell = row.createCell((int) 1);
        
        cell.setCellValue("设备名称");
        cell.setCellStyle(style);
        cell = row.createCell((int) 2);
        
        cell.setCellValue("经度");
        cell.setCellStyle(style);
        cell = row.createCell((int) 3);
        
        cell.setCellValue("纬度");
        cell.setCellStyle(style);
        cell = row.createCell((int) 4);
		
//        cell.setCellValue("光缆段编号");
//        cell.setCellStyle(style);
//        cell = row.createCell((int) 3);
//        
//        cell.setCellValue("光缆段名称");
//        cell.setCellStyle(style);
//        cell = row.createCell((int) 4);
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		//导出光交箱
		List<FacilityAll> list1=facilityService.queryExportAll(user.getOrgId(),areaCode);
		if(list1!=null && list1.size()>0) {
			for (int i = 0; i < list1.size(); i++) {
				row = sheet.createRow(i+1);
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(1);//序号  
//					//导出光交箱光路
				if(list1.get(i).getDevCode()!=null){
					cell.setCellValue((list1.get(i)).getDevCode());
					cell.setCellStyle(style);
					cell = row.createCell(1);
					}else{
						cell.setCellValue("");
						cell.setCellStyle(style);
						cell = row.createCell(1);
					}
				
				if(list1.get(i).getDevName()!=null){
					cell.setCellValue((list1.get(i)).getDevName());
					cell.setCellStyle(style);
					cell = row.createCell(2);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(2);
				}
				
				if(list1.get(i).getLongitude()!=null){
					cell.setCellValue((list1.get(i)).getLongitude());
					cell.setCellStyle(style);
					cell = row.createCell(3);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(3);
				}
				
				if(list1.get(i).getLatitude()!=null){
					cell.setCellValue((list1.get(i)).getLatitude());
					cell.setCellStyle(style);
					cell = row.createCell(4);
				}else{
					cell.setCellValue("");
					cell.setCellStyle(style);
					cell = row.createCell(4);
				}
				
//				if(list1.get(i).getSecCode()!=null){
//					cell.setCellValue((list1.get(i)).getSecCode());
//					cell.setCellStyle(style);
//					cell = row.createCell(3);
//					}else{
//						cell.setCellValue("");
//						cell.setCellStyle(style);
//						cell = row.createCell(3);
//				}
//				
//				if(list1.get(i).getSecName()!=null){
//					cell.setCellValue((list1.get(i)).getSecName());
//					cell.setCellStyle(style);
//					cell = row.createCell(4);
//					}else{
//						cell.setCellValue("");
//						cell.setCellStyle(style);
//						cell = row.createCell(4);
//				}
		
				//自适应列宽
				sheet.autoSizeColumn((int)0,true);
				sheet.autoSizeColumn((int)1,true);
				sheet.autoSizeColumn((int)2,true);
				sheet.autoSizeColumn((int)3,true);
				sheet.autoSizeColumn((int)4,true);
				
			}
		}
		
		//导出机房机柜
		List<FacilityAll> list2=facilityService.queryExportAllJifang(user.getOrgId(),areaCode);	
		if(list2!=null && list2.size()>0) {
			HSSFSheet sheetJifang = wb.createSheet("机房"); 
			sheetJifang.setColumnWidth(0, 20*256);      
			// 创建第一行（也可以称为表头）
			HSSFRow rowJifang = sheetJifang.createRow(0);
			// 给单子名称一个长度
			sheet.setDefaultColumnWidth((int) 15);
			
			rowJifang = sheetJifang.createRow(0);          
	        //第一行写入标题行  
	        HSSFCell cellJifang = rowJifang.createCell((int) 0); //
	        cellJifang.setCellStyle( style); 
	        cellJifang.setCellValue("序号");  
			
	        cellJifang.setCellValue("设备编号");
	        cellJifang.setCellStyle(style);
	        cellJifang = rowJifang.createCell((int) 1);
	        
	        cellJifang.setCellValue("设备名称");
	        cellJifang.setCellStyle(style);
	        cellJifang = rowJifang.createCell((int) 2);
			
	        cellJifang.setCellValue("机柜编号");
	        cellJifang.setCellStyle(style);
	        cellJifang = rowJifang.createCell((int) 3);
	        
	        cellJifang.setCellValue("机柜名称");
	        cellJifang.setCellStyle(style);
	        cellJifang = rowJifang.createCell((int) 4);
	        
	        cellJifang.setCellValue("经度");
	        cellJifang.setCellStyle(style);
	        cellJifang = rowJifang.createCell((int) 4);
	        
	        cellJifang.setCellValue("纬度");
	        cellJifang.setCellStyle(style);
	        cellJifang = rowJifang.createCell((int) 4);
	        
//	        cellJifang.setCellValue("光缆段编号");
//	        cellJifang.setCellStyle(style);
//	        cellJifang = rowJifang.createCell((int) 5);
//	        
//	        cellJifang.setCellValue("光缆段名称");
//	        cellJifang.setCellStyle(style);
//	        cellJifang = rowJifang.createCell((int) 6);
			
			for (int i = 0; i < list2.size(); i++) {
				rowJifang = sheetJifang.createRow(i+1);
				cellJifang = rowJifang.createCell(0);
				cellJifang.setCellStyle(style);
				cellJifang.setCellValue(1);//序号  
	//			//导出光交箱光路
	//			
				if(list2.get(i).getDevCode()!=null){
					cellJifang.setCellValue((list2.get(i)).getDevCode());
					cellJifang.setCellStyle(style);
					cellJifang = rowJifang.createCell(1);
					}else{
						cellJifang.setCellValue("");
						cellJifang.setCellStyle(style);
						cellJifang = rowJifang.createCell(1);
					}
				
				if(list2.get(i).getDevName()!=null){
					cellJifang.setCellValue((list2.get(i)).getDevName());
					cellJifang.setCellStyle(style);
					cellJifang = rowJifang.createCell(2);
					}else{
						cellJifang.setCellValue("");
						cellJifang.setCellStyle(style);
						cellJifang = rowJifang.createCell(2);
					}
				
				if(list2.get(i).getDevCode1()!=null){
					cellJifang.setCellValue((list2.get(i)).getDevCode1());
					cellJifang.setCellStyle(style);
					cellJifang = rowJifang.createCell(3);
					}else{
						cellJifang.setCellValue("");
						cellJifang.setCellStyle(style);
						cellJifang = rowJifang.createCell(3);
					}
				
				if(list2.get(i).getDevName1()!=null){
					cellJifang.setCellValue((list2.get(i)).getDevName1());
					cellJifang.setCellStyle(style);
					cellJifang = rowJifang.createCell(4);
					}else{
						cellJifang.setCellValue("");
						cellJifang.setCellStyle(style);
						cellJifang = rowJifang.createCell(4);
					}
				
				if(list2.get(i).getLongitude()!=null){
					cellJifang.setCellValue((list2.get(i)).getLongitude());
					cellJifang.setCellStyle(style);
					cellJifang = rowJifang.createCell(5);
					}else{
						cellJifang.setCellValue("");
						cellJifang.setCellStyle(style);
						cellJifang = rowJifang.createCell(5);
					}
				
				if(list2.get(i).getLatitude()!=null){
					cellJifang.setCellValue((list2.get(i)).getLatitude());
					cellJifang.setCellStyle(style);
					cellJifang = rowJifang.createCell(6);
					}else{
						cellJifang.setCellValue("");
						cellJifang.setCellStyle(style);
						cellJifang = rowJifang.createCell(6);
					}
				
//				if(list2.get(i).getSecCode()!=null){
//					cellJifang.setCellValue((list2.get(i)).getSecCode());
//					cellJifang.setCellStyle(style);
//					cellJifang = rowJifang.createCell(5);
//					}else{
//						cellJifang.setCellValue("");
//						cellJifang.setCellStyle(style);
//						cellJifang = rowJifang.createCell(5);
//				}
//				
//				if(list2.get(i).getSecName()!=null){
//					cellJifang.setCellValue((list2.get(i)).getSecName());
//					cellJifang.setCellStyle(style);
//					cellJifang = rowJifang.createCell(6);
//					}else{
//						cellJifang.setCellValue("");
//						cellJifang.setCellStyle(style);
//						cellJifang = rowJifang.createCell(6);
//				}
				
				sheetJifang.autoSizeColumn((int)0,true);
				sheetJifang.autoSizeColumn((int)1,true);
				sheetJifang.autoSizeColumn((int)2,true);
				sheetJifang.autoSizeColumn((int)3,true);
				sheetJifang.autoSizeColumn((int)4,true);
				sheetJifang.autoSizeColumn((int)5,true);
				sheetJifang.autoSizeColumn((int)6,true);
			}
		}
		if(list1.size()==0 && list2.size()==0 ) {
			r.setR(0);
			Map<String,String> rMap=new HashMap<String,String>();
			rMap.put("filePath", "1");
			r.setDt(rMap);
			r.putRContent("无数据！");
		}
		int m=wb.getNumberOfSheets();//获得sheet页总数
		System.out.println(m+"sheet总数");
		for (int m1 = 0; m1 < m; m1++) {
			sheet=wb.getSheetAt(m1);//得到第几个sheet
		
			//合并设备名称
			int rowCount1=sheet.getLastRowNum();//最后一行
			HSSFCell obj1=null;  
			String tempDevA = sheet.getRow(1).getCell(0).getStringCellValue();
			int firstrowDevA=0;//定义要合并的个数
			for (int j = 2; j <= rowCount1; j++) {
				obj1=sheet.getRow(j).getCell(0);
				if(tempDevA.equals(obj1.getStringCellValue()))
				{
					firstrowDevA++;
					if(j==rowCount1) {//合并最后一次
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowDevA, j, 0, 0));//合并单元格
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowDevA, j, 1, 1));
					}
				}else {
					if(firstrowDevA!=0) {//判断没有要合并的跳过
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowDevA-1, j-1, 0, 0));
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowDevA-1, j-1, 1, 1));
						firstrowDevA=0;
					}
					tempDevA=sheet.getRow(j).getCell(0).getStringCellValue();//合并后代替下一个值
				}
			}
			
			//合并光缆段
			String tempSec = sheet.getRow(1).getCell(2).getStringCellValue();
			HSSFCell obj12=null;
			int firstrowSec=0;
			for (int j = 2; j <= rowCount1; j++) {
				obj12=sheet.getRow(j).getCell(2);
				if(tempSec.equals(obj12.getStringCellValue()))
				{
					firstrowSec++;
					if(j==rowCount1) {
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowSec, j, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowSec, j, 3, 3));
					}
				}else {
					if(firstrowSec!=0) {
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowSec-1, j-1, 2, 2));
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowSec-1, j-1, 3, 3));
						firstrowSec=0;
					}
					tempSec=sheet.getRow(j).getCell(2).getStringCellValue();
				}
			}
		}	
			
			
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
			r.putRContent("导出异常！");
		    }
		return r;
	}
	
	
	
	/**
	 * 
	* @Title: exportAll 
	* @Description: TODO(导出跳纤备用) 
	* @param @param request
	* @param @param response
	* @param @param session
	* @param @param areaCode
	* @param @return    入参
	* @return Result    返回类型
	* @author （作者） 
	* @throws
	* @date 2017年11月1日 下午2:50:58 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/exportJumper.htm")
	public Result exportAllBY(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			String areaCode) {
		Result r = Result.get();
		try {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		
		// 生成一个样式 
		HSSFCellStyle style = wb.createCellStyle();
		// 设置四边的边框 
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		
		// 设置水平对齐的样式为居中对齐; 
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
        HSSFRow row = null;//创建一行  
        HSSFCell cell = null;//每个单元格  

        // 声明一个单子并命名 
		HSSFSheet sheet = wb.createSheet("光交箱"); //第一个sheet
		sheet.setColumnWidth(0, 20*256);      
		// 创建第一行（也可以称为表头）
		row = sheet.createRow(0);
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((int) 15);
//        writeTitleContent(sheet,style);//写入标题
		 //标题  
        row = sheet.createRow(0);          
        //第一行写入标题行  
        cell = row.createCell((int) 0); //
        cell.setCellStyle( style); 
        cell.setCellValue("序号");  
		
        cell.setCellValue("本端设施名称");
        cell.setCellStyle(style);
        cell = row.createCell((int) 1);
        
        cell.setCellValue("本端跳纤端口");
        cell.setCellStyle(style);
        cell = row.createCell((int) 2);
		
        cell.setCellValue("对端跳纤端口");
        cell.setCellStyle(style);
        cell = row.createCell((int) 3);
        
       
		
		// 向单元格里填充数据
		Users user = (Users) session.getAttribute("platformUser");
		//导出光交箱
		List<ExportJumper> list1=facilityService.queryJumperGJX();
		
				for (int i = 0; i < list1.size(); i++) {
					row = sheet.createRow(i+1);
					cell = row.createCell(0);
					cell.setCellStyle(style);
					cell.setCellValue(1);//序号  
//					//导出光交箱光路
					if(list1.get(i).getDevName()!=null){
						cell.setCellValue((list1.get(i)).getDevName());
						cell.setCellStyle(style);
						cell = row.createCell(1);
						}else{
							cell.setCellValue("");
							cell.setCellStyle(style);
							cell = row.createCell(1);
						}
					
					if(list1.get(i).getBenDuan()!=null){
						cell.setCellValue((list1.get(i)).getBenDuan());
						cell.setCellStyle(style);
						cell = row.createCell(2);
						}else{
							cell.setCellValue("");
							cell.setCellStyle(style);
							cell = row.createCell(2);
						}
					
					if(list1.get(i).getDuiDuan()!=null){
						cell.setCellValue((list1.get(i)).getDuiDuan());
						cell.setCellStyle(style);
						cell = row.createCell(3);
						}else{
							cell.setCellValue("");
							cell.setCellStyle(style);
							cell = row.createCell(3);
					}
					
					
			
				//自适应列宽
				sheet.autoSizeColumn((int)0,true);
				sheet.autoSizeColumn((int)1,true);
				sheet.autoSizeColumn((int)2,true);
					
//				
		}
		list1=null;
		HSSFSheet sheetJifang = wb.createSheet("机房"); //第二个sheet
		sheetJifang.setColumnWidth(0, 20*256);      
		// 创建第一行（也可以称为表头）
		HSSFRow rowJifang = sheetJifang.createRow(0);
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((int) 15);
		
		rowJifang = sheetJifang.createRow(0);          
        //第一行写入标题行  
        HSSFCell cellJifang = rowJifang.createCell((int) 0); //
        cellJifang.setCellStyle( style); 
        cellJifang.setCellValue("序号");  
		
        cellJifang.setCellValue("本端机房名称");
        cellJifang.setCellStyle(style);
        cellJifang = rowJifang.createCell((int) 1);
        
        cellJifang.setCellValue("本端机柜名称");
        cellJifang.setCellStyle(style);
        cellJifang = rowJifang.createCell((int) 2);
		
        cellJifang.setCellValue("本端跳纤端口");
        cellJifang.setCellStyle(style);
        cellJifang = rowJifang.createCell((int) 3);
        
        cellJifang.setCellValue("对端跳纤端口");
        cellJifang.setCellStyle(style);
        cellJifang = rowJifang.createCell((int) 4);
		//导出机房机柜
		List<ExportJumper> list2=facilityService.queryJumperJifang();	
		for (int i = 0; i < list2.size(); i++) {
			rowJifang = sheetJifang.createRow(i+1);
			cellJifang = rowJifang.createCell(0);
			cellJifang.setCellStyle(style);
			cellJifang.setCellValue(1);//序号  
//			//导出光交箱光路
//			
			if(list2.get(i).getDevName1()!=null){
				cellJifang.setCellValue((list2.get(i)).getDevName1());
				cellJifang.setCellStyle(style);
				cellJifang = rowJifang.createCell(1);
				}else{
					cellJifang.setCellValue("");
					cellJifang.setCellStyle(style);
					cellJifang = rowJifang.createCell(1);
				}
			
			if(list2.get(i).getDevName()!=null){
				cellJifang.setCellValue((list2.get(i)).getDevName());
				cellJifang.setCellStyle(style);
				cellJifang = rowJifang.createCell(2);
				}else{
					cellJifang.setCellValue("");
					cellJifang.setCellStyle(style);
					cellJifang = rowJifang.createCell(2);
				}
			
			if(list2.get(i).getBenDuan()!=null){
				cellJifang.setCellValue((list2.get(i)).getBenDuan());
				cellJifang.setCellStyle(style);
				cellJifang = rowJifang.createCell(3);
				}else{
					cellJifang.setCellValue("");
					cellJifang.setCellStyle(style);
					cellJifang = rowJifang.createCell(3);
				}
			
			if(list2.get(i).getDuiDuan()!=null){
				cellJifang.setCellValue((list2.get(i)).getDuiDuan());
				cellJifang.setCellStyle(style);
				cellJifang = rowJifang.createCell(4);
				}else{
					cellJifang.setCellValue("");
					cellJifang.setCellStyle(style);
					cellJifang = rowJifang.createCell(4);
				}
			
			
			sheetJifang.autoSizeColumn((int)0,true);
			sheetJifang.autoSizeColumn((int)1,true);
			sheetJifang.autoSizeColumn((int)2,true);
			sheetJifang.autoSizeColumn((int)3,true);
			
		}
		list2=null;
		HSSFSheet sheetFenguangqi = wb.createSheet("分光器"); //第三个sheet
		sheetFenguangqi.setColumnWidth(0, 20*256);      
		// 创建第一行（也可以称为表头）
		HSSFRow rowFenguangqi = sheetFenguangqi.createRow(0);
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((int) 15);
		
        //第一行写入标题行  
        HSSFCell cellFenguangqi = rowFenguangqi.createCell((int) 0); //
        cellFenguangqi.setCellStyle( style); 
        cellFenguangqi.setCellValue("序号");  
		
        cellFenguangqi.setCellValue("本端设施名称");
        cellFenguangqi.setCellStyle(style);
        cellFenguangqi = rowFenguangqi.createCell((int) 1);
        
        cellFenguangqi.setCellValue("本端分光器名称");
        cellFenguangqi.setCellStyle(style);
        cellFenguangqi = rowFenguangqi.createCell((int) 2);
		
        cellFenguangqi.setCellValue("本端跳纤端口");
        cellFenguangqi.setCellStyle(style);
        cellFenguangqi = rowFenguangqi.createCell((int) 3);
        
        cellFenguangqi.setCellValue("对端跳纤端口");
        cellFenguangqi.setCellStyle(style);
        cellFenguangqi = rowFenguangqi.createCell((int) 4);
		//导出机房机柜
		List<ExportJumper> list3=facilityService.queryJumperFenguangqi();	
		for (int i = 0; i < list3.size(); i++) {
			rowFenguangqi = sheetFenguangqi.createRow(i+1);
			cellFenguangqi = rowFenguangqi.createCell(0);
			cellFenguangqi.setCellStyle(style);
			cellFenguangqi.setCellValue(1);//序号  
//			//导出光交箱光路
//			
			if(list3.get(i).getDevName1()!=null){
				cellFenguangqi.setCellValue((list3.get(i)).getDevName1());
				cellFenguangqi.setCellStyle(style);
				cellFenguangqi = rowFenguangqi.createCell(1);
				}else{
					cellFenguangqi.setCellValue("");
					cellFenguangqi.setCellStyle(style);
					cellFenguangqi = rowFenguangqi.createCell(1);
				}
			
			if(list3.get(i).getDevName()!=null){
				cellFenguangqi.setCellValue((list3.get(i)).getDevName());
				cellFenguangqi.setCellStyle(style);
				cellFenguangqi = rowFenguangqi.createCell(2);
				}else{
					cellFenguangqi.setCellValue("");
					cellFenguangqi.setCellStyle(style);
					cellFenguangqi = rowFenguangqi.createCell(2);
				}
			
			if(list3.get(i).getBenDuan()!=null){
				cellFenguangqi.setCellValue((list3.get(i)).getBenDuan());
				cellFenguangqi.setCellStyle(style);
				cellFenguangqi = rowFenguangqi.createCell(3);
				}else{
					cellFenguangqi.setCellValue("");
					cellFenguangqi.setCellStyle(style);
					cellFenguangqi = rowFenguangqi.createCell(3);
				}
			
			if(list3.get(i).getDuiDuan()!=null){
				cellFenguangqi.setCellValue((list3.get(i)).getDuiDuan());
				cellFenguangqi.setCellStyle(style);
				cellFenguangqi = rowFenguangqi.createCell(4);
				}else{
					cellFenguangqi.setCellValue("");
					cellFenguangqi.setCellStyle(style);
					cellFenguangqi = rowFenguangqi.createCell(4);
				}
			
			
			sheetJifang.autoSizeColumn((int)0,true);
			sheetJifang.autoSizeColumn((int)1,true);
			sheetJifang.autoSizeColumn((int)2,true);
			sheetJifang.autoSizeColumn((int)3,true);
			
		}
		
		
		
		int m=wb.getNumberOfSheets();//获得sheet页总数
		System.out.println(m+"sheet总数");
		for (int m1 = 0; m1 < m; m1++) {
			sheet=wb.getSheetAt(m1);//得到第几个sheet
		
			//合并设备名称
			int rowCount1=sheet.getLastRowNum();//最后一行
			HSSFCell obj1=null;  
			String tempDevA = sheet.getRow(1).getCell(0).getStringCellValue();
			int firstrowDevA=0;//定义要合并的个数
			for (int j = 2; j <= rowCount1; j++) {
				obj1=sheet.getRow(j).getCell(0);
				if(tempDevA.equals(obj1.getStringCellValue()))
				{
					firstrowDevA++;
					if(j==rowCount1) {//合并最后一次
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowDevA, j, 0, 0));//合并单元格
					}
				}else {
					if(firstrowDevA!=0) {//判断没有要合并的跳过
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowDevA-1, j-1, 0, 0));
						firstrowDevA=0;
					}
					tempDevA=sheet.getRow(j).getCell(0).getStringCellValue();//合并后代替下一个值
				}
			}
			
			//合并光缆段
			String tempSec = sheet.getRow(1).getCell(1).getStringCellValue();
			HSSFCell obj12=null;
			int firstrowSec=0;
			for (int j = 2; j <= rowCount1; j++) {
				obj12=sheet.getRow(j).getCell(1);
				if(tempSec.equals(obj12.getStringCellValue()))
				{
					firstrowSec++;
					if(j==rowCount1) {
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowSec, j, 1, 1));
					}
				}else {
					if(firstrowSec!=0) {
						sheet.addMergedRegion(new CellRangeAddress(j-firstrowSec-1, j-1, 1, 1));
						firstrowSec=0;
					}
					tempSec=sheet.getRow(j).getCell(1).getStringCellValue();
				}
			}
		}	
			
			
		String path = request.getSession().getServletContext().getRealPath("/")
				+"/export/";
		Date date = new Date();
		String fileName = "光交箱光路数据" + date.getTime() + ".xls";
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
}
