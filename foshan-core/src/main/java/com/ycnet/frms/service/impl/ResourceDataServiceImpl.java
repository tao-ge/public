package com.ycnet.frms.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.Basecode;
import com.ycnet.frms.bean.Cable;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableSectionZF;
import com.ycnet.frms.bean.CableZF;
import com.ycnet.frms.bean.CutData;
import com.ycnet.frms.bean.DiscinfoZF;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityImg;
import com.ycnet.frms.bean.FacilityZF;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.FiberdiscGroupZF;
import com.ycnet.frms.bean.FiberdiscZF;
import com.ycnet.frms.bean.LinesZF;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Project;
import com.ycnet.frms.bean.ResourceData;
import com.ycnet.frms.bean.Roles;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.RouteZF;
import com.ycnet.frms.bean.SiteCode;
import com.ycnet.frms.bean.Twinfiber;
import com.ycnet.frms.bean.UserRole;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WellPileline;
import com.ycnet.frms.mapper.ResourceDataMapper;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.service.CableSectionService;
import com.ycnet.frms.service.CableService;
import com.ycnet.frms.service.DiscinfoService;
import com.ycnet.frms.service.FacilityImgService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscGroupService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.service.LinesService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.ProjectService;
import com.ycnet.frms.service.ResourceDataService;
import com.ycnet.frms.service.RolesService;
import com.ycnet.frms.service.RouteService;
import com.ycnet.frms.service.SiteCodeService;
import com.ycnet.frms.service.TwinfiberService;
import com.ycnet.frms.service.UserRoleService;
import com.ycnet.frms.service.UsersService;
import com.ycnet.frms.service.WellPilelineService;

import net.sf.json.JSONArray;

@Service("resourceDataService")
public class ResourceDataServiceImpl implements ResourceDataService{

	@Resource(name="resourceDataMapper")
	private ResourceDataMapper resourceDataMapper;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;

	@Resource(name="cableSectionService")
	private CableSectionService cableSectionService;
	
	@Resource(name="discinfoService")
	private DiscinfoService discinfoService;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;
	
	@Resource(name="fiberdiscGroupService")
	private FiberdiscGroupService fiberdiscGroupService;
	
	@Resource(name="linesService")
	private LinesService linesService;
	
	@Resource(name="areasService")
	private AreasService areasService;
	
	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	
	@Resource(name="cableService")
	private CableService cableService;
	
	@Resource(name="facilityImgService")
	private FacilityImgService facilityImgService;
	
	@Resource(name="organizitionService")
	private OrganizitionService organizitionService;
	
	@Resource(name="projectService")
	private ProjectService projectService;
	
	@Resource(name="rolesService")
	private RolesService rolesService;
	
	@Resource(name="routeService")
	private RouteService routeService;
	
	@Resource(name="siteCodeService")
	private SiteCodeService siteCodeService;
	
	@Resource(name="twinfiberService")
	private TwinfiberService twinfiberService;
	
	@Resource(name="userRoleService")
	private UserRoleService userRoleService;
	
	@Resource(name="usersService")
	private UsersService usersService;
	
	@Resource(name="wellPilelineService")
	private WellPilelineService wellPilelineService;
	
	/**
	 * 查询资管数据管理数据
	 */
	@Override
	public PageBean queryResourceDataList(ResourceData rd, Users user, PageBean pb) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ResourceData", rd);
		map.put("user", user);
		map.put("pb", pb);
		List<ResourceData> list = resourceDataMapper.queryResourceDataList(map);
		pb.setRows(resourceDataMapper.queryCountResourceDataList(map));//;
		pb.setList(list);
		return pb;
	}

	/**
	 * 重新生成修改保存
	 */
	@Override
	public int reGeneratorSave(ResourceData rd) {
		return resourceDataMapper.updateByPrimaryKeySelective(rd);
	}

	@Override
	public ResourceData queryById(Long resId) {
		return resourceDataMapper.selectByPrimaryKey(resId);
	}

	/**
	 * 生成.txt文件
	 * 作者:刘沧海
	 * 修改:DZY
	 */
	@Override
	public String reGeneratorSave(HttpSession session,HttpServletRequest request ,String areaCode) {
		Users users = (Users) session.getAttribute("platformUser");
		String fileName="";
		String list=null;
		int num;//切分的数量
		List<CutData> cutDataList = new ArrayList<CutData>();//记录表信息的list
		JSONArray listJson = new JSONArray();
		String baseUrl = "http://192.168.0.21:8080/frms-site/data/"+areaCode+"/";
		
		//1.tn_facility
		List<FacilityZF> facilityList=facilityService.queryList(users.getOrgId(),areaCode);
		List<FacilityZF> facilityList1;
		CutData facilityCutData = new CutData();
		List<String> facilityNameList = new ArrayList<String>();
		num = 1+facilityList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		facilityCutData.setCutName("tn_facility.txt");
		facilityCutData.setCutNum(num);
		if(facilityList!=null && facilityList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					facilityList1 = facilityList.subList(i*10000, facilityList.size());//切分最后的
				}else{
					facilityList1 = facilityList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_facility_"+(i+1)+".txt";
				facilityNameList.add(fileName);
				listJson = JSONArray.fromObject(facilityList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_facility.txt";//.txt文件名
			facilityNameList.add(fileName);
			listJson = JSONArray.fromObject(facilityList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		facilityCutData.setBaseUrl(baseUrl);//设置路径
		facilityCutData.setFileNameList(facilityNameList);
		cutDataList.add(facilityCutData);//生成CutData.txt文件的list,记录切分的信息
		facilityList=null;
		listJson=null;
		list=null;
		
		//2.tn_cable_section
		List<CableSectionZF> cableSectionList=cableSectionService.queryCableSectionList(users.getOrgId(),areaCode);
		List<CableSectionZF> cableSectionList1;
		CutData cableSectionCutData = new CutData();
		List<String> cableSectionNameList = new ArrayList<String>();
		num = 1+cableSectionList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		cableSectionCutData.setCutName("tn_cable_section.txt");
		cableSectionCutData.setCutNum(num);
		if(cableSectionList!=null && cableSectionList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					cableSectionList1 = cableSectionList.subList(i*10000, cableSectionList.size());//切分最后的
				}else{
					cableSectionList1 = cableSectionList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_cable_section_"+(i+1)+".txt";
				cableSectionNameList.add(fileName);
				listJson = JSONArray.fromObject(cableSectionList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_cable_section.txt";//.txt文件名
			cableSectionNameList.add(fileName);
			listJson = JSONArray.fromObject(cableSectionList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		cableSectionCutData.setBaseUrl(baseUrl);//设置路径
		cableSectionCutData.setFileNameList(cableSectionNameList);
		cutDataList.add(cableSectionCutData);//生成CutData.txt文件的list,记录切分的信息
		cableSectionList=null;
		listJson=null;
		list=null;

		//3.tn_discinfo
		List<DiscinfoZF> discinfoList=discinfoService.queryList(users.getOrgId(),areaCode);
		List<DiscinfoZF> discinfoList1;
		CutData discinfoCutData = new CutData();
		List<String> discinfoNameList = new ArrayList<String>();
		num = 1+discinfoList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		discinfoCutData.setCutName("tn_discinfo.txt");
		discinfoCutData.setCutNum(num);
		if(discinfoList!=null && discinfoList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					discinfoList1 = discinfoList.subList(i*10000, discinfoList.size());//切分最后的
				}else{
					discinfoList1 = discinfoList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_discinfo_"+(i+1)+".txt";
				discinfoNameList.add(fileName);
				listJson = JSONArray.fromObject(discinfoList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_discinfo.txt";//.txt文件名
			discinfoNameList.add(fileName);
			listJson = JSONArray.fromObject(discinfoList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		discinfoCutData.setBaseUrl(baseUrl);//设置路径
		discinfoCutData.setFileNameList(discinfoNameList);
		cutDataList.add(discinfoCutData);//生成CutData.txt文件的list,记录切分的信息
		discinfoList=null;
		listJson=null;
		list=null;
		
		//4.tn_fiberdisc
		List<FiberdiscZF> fiberdiscList=fiberdiscService.queryList(users.getOrgId(),areaCode);
		List<FiberdiscZF> fiberdiscList1;
		CutData fiberdiscCutData = new CutData();
		List<String> fiberdiscNameList = new ArrayList<String>();
		num = 1+fiberdiscList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		fiberdiscCutData.setCutName("tn_fiberdisc.txt");
		fiberdiscCutData.setCutNum(num);
		if(fiberdiscList!=null && fiberdiscList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					fiberdiscList1 = fiberdiscList.subList(i*10000, fiberdiscList.size());//切分最后的
				}else{
					fiberdiscList1 = fiberdiscList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_fiberdisc_"+(i+1)+".txt";
				fiberdiscNameList.add(fileName);
				listJson = JSONArray.fromObject(fiberdiscList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_fiberdisc.txt";//.txt文件名
			fiberdiscNameList.add(fileName);
			listJson = JSONArray.fromObject(fiberdiscList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		fiberdiscCutData.setBaseUrl(baseUrl);//设置路径
		fiberdiscCutData.setFileNameList(fiberdiscNameList);
		cutDataList.add(fiberdiscCutData);//生成CutData.txt文件的list,记录切分的信息
		fiberdiscList=null;
		listJson=null;
		list=null;
		
		//5.tn_fiberdisc_group
		List<FiberdiscGroupZF> fiberdiscGroupList=fiberdiscGroupService.queryList(users.getOrgId(),areaCode);
		List<FiberdiscGroupZF> fiberdiscGroupList1;
		CutData fiberdiscGroupCutData = new CutData();
		List<String> fiberdiscGroupNameList = new ArrayList<String>();
		num = 1+fiberdiscGroupList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		fiberdiscGroupCutData.setCutName("tn_fiberdisc_group");
		fiberdiscGroupCutData.setCutNum(num);
		if(fiberdiscGroupList!=null && fiberdiscGroupList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					fiberdiscGroupList1 = fiberdiscGroupList.subList(i*10000, fiberdiscGroupList.size());//切分最后的
				}else{
					fiberdiscGroupList1 = fiberdiscGroupList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_fiberdisc_group_"+(i+1)+".txt";
				fiberdiscGroupNameList.add(fileName);
				listJson = JSONArray.fromObject(fiberdiscGroupList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_fiberdisc_group.txt";//.txt文件名
			fiberdiscGroupNameList.add(fileName);
			listJson = JSONArray.fromObject(fiberdiscGroupList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		fiberdiscGroupCutData.setBaseUrl(baseUrl);//设置路径
		fiberdiscGroupCutData.setFileNameList(fiberdiscGroupNameList);
		cutDataList.add(fiberdiscGroupCutData);//生成CutData.txt文件的list,记录切分的信息
		fiberdiscGroupList=null;
		listJson=null;
		list=null;
		
		//6.tn_lines
		List<LinesZF> linesList=linesService.queryList(users.getOrgId(),areaCode);
		List<LinesZF> linesList1;
		CutData linesCutData = new CutData();
		List<String> linesNameList = new ArrayList<String>();
		num = 1+linesList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		linesCutData.setCutName("tn_lines.txt");
		linesCutData.setCutNum(num);
		if(linesList!=null && linesList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					linesList1 = linesList.subList(i*10000, linesList.size());//切分最后的
				}else{
					linesList1 = linesList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_lines_"+(i+1)+".txt";
				linesNameList.add(fileName);
				listJson = JSONArray.fromObject(linesList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_lines.txt";//.txt文件名
			linesNameList.add(fileName);
			listJson = JSONArray.fromObject(linesList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		linesCutData.setBaseUrl(baseUrl);//设置路径
		linesCutData.setFileNameList(linesNameList);
		cutDataList.add(linesCutData);//生成CutData.txt文件的list,记录切分的信息
		linesList=null;
		listJson=null;
		list=null;
		
		
		
		//7.tn_areas
		List<Areas> areasList=areasService.queryList();
		List<Areas> areasList1;
		CutData areasCutData = new CutData();
		List<String> areasNameList = new ArrayList<String>();
		num = 1+areasList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		areasCutData.setCutName("tn_areas.txt");
		areasCutData.setCutNum(num);
		if(areasList!=null && areasList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					areasList1 = areasList.subList(i*10000, areasList.size());//切分最后的
				}else{
					areasList1 = areasList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_areas_"+(i+1)+".txt";
				areasNameList.add(fileName);
				listJson = JSONArray.fromObject(areasList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_areas.txt";//.txt文件名
			areasNameList.add(fileName);
			listJson = JSONArray.fromObject(areasList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		areasCutData.setBaseUrl(baseUrl);//设置路径
		areasCutData.setFileNameList(areasNameList);
		cutDataList.add(areasCutData);//生成CutData.txt文件的list,记录切分的信息
		areasList=null;
		listJson=null;
		list=null;
		
		//8.tn_basecode
		List<Basecode> baseList=basecodeService.queryList();
		List<Basecode> baseList1;
		CutData baseCutData = new CutData();
		List<String> baseNameList = new ArrayList<String>();
		num = 1+baseList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		baseCutData.setCutName("tn_basecode.txt");
		baseCutData.setCutNum(num);
		if(baseList!=null && baseList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					baseList1 = baseList.subList(i*10000, baseList.size());//切分最后的
				}else{
					baseList1 = baseList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_basecode_"+(i+1)+".txt";
				baseNameList.add(fileName);
				listJson = JSONArray.fromObject(baseList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_basecode.txt";//.txt文件名
			baseNameList.add(fileName);
			listJson = JSONArray.fromObject(baseList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		baseCutData.setBaseUrl(baseUrl);//设置路径
		baseCutData.setFileNameList(baseNameList);
		cutDataList.add(baseCutData);//生成CutData.txt文件的list,记录切分的信息
		baseList=null;
		listJson=null;
		list=null;
		
		//9.tn_cable
		List<CableZF> cableList =cableService.queryList(users.getOrgId());
		List<CableZF> cableList1;
		CutData cableCutData = new CutData();
		List<String> cableNameList = new ArrayList<String>();
		num = 1+cableList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		cableCutData.setCutName("tn_cable.txt");
		cableCutData.setCutNum(num);
		if(cableList!=null && cableList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					cableList1 = cableList.subList(i*10000, cableList.size());//切分最后的
				}else{
					cableList1 = cableList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_cable_"+(i+1)+".txt";
				cableNameList.add(fileName);
				listJson = JSONArray.fromObject(cableList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_cable.txt";//.txt文件名
			cableNameList.add(fileName);
			listJson = JSONArray.fromObject(cableList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		cableCutData.setBaseUrl(baseUrl);//设置路径
		cableCutData.setFileNameList(cableNameList);
		cutDataList.add(cableCutData);//生成CutData.txt文件的list,记录切分的信息
		cableList=null;
		listJson=null;
		list=null;
		
		//10.tn_facility_img
		List<FacilityImg> facilityImgList=facilityImgService.queryList();
		List<FacilityImg> facilityImgList1;
		CutData facilityImgCutData = new CutData();
		List<String> facilityImgNameList = new ArrayList<String>();
		num = 1+facilityImgList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		facilityImgCutData.setCutName("tn_facility_img.txt");
		facilityImgCutData.setCutNum(num);
		if(facilityImgList!=null && facilityImgList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					facilityImgList1 = facilityImgList.subList(i*10000, facilityImgList.size());//切分最后的
				}else{
					facilityImgList1 = facilityImgList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_facility_img_"+(i+1)+".txt";
				facilityImgNameList.add(fileName);
				listJson = JSONArray.fromObject(facilityImgList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_facility_img.txt";//.txt文件名
			facilityImgNameList.add(fileName);
			listJson = JSONArray.fromObject(facilityImgList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		facilityImgCutData.setBaseUrl(baseUrl);//设置路径
		facilityImgCutData.setFileNameList(facilityImgNameList);
		cutDataList.add(facilityImgCutData);//生成CutData.txt文件的list,记录切分的信息
		facilityImgList=null;
		listJson=null;
		list=null;
		
		
		//11.tn_organizition
		Organizition organizitionList=organizitionService.selectById(users.getOrgId());
		CutData organizitionCutData = new CutData();
		List<String>organizitionNameList = new ArrayList<String>();
		organizitionCutData.setCutName("tn_organizition.txt");
		organizitionCutData.setCutNum(1);
		fileName="tn_organizition.txt";//.txt文件名
		organizitionNameList.add(fileName);
		listJson = JSONArray.fromObject(organizitionList);//把数据转成json
		list=listJson.toString();
		uploadFile(list,request,fileName,areaCode);//生成文件
		organizitionCutData.setBaseUrl(baseUrl);//设置路径
		organizitionCutData.setFileNameList(organizitionNameList);
		cutDataList.add(organizitionCutData);//生成CutData.txt文件的list,记录切分的信息
		organizitionList=null;
		listJson=null;
		list=null;
		
		//12.tn_piping
//		List<Piping> pipingList=pipingService.queryList();
//		List<Piping> pipingList1;
//		CutData pipingCutData = new CutData();
//		List<String> pipingNameList = new ArrayList<String>();
//		num = 1+pipingList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
//		pipingCutData.setCutName("tn_piping.txt");
//		pipingCutData.setCutNum(num);
//		if(pipingList!=null && pipingList.size()>10000){
//			for(int i=0;i<num;i++){
//				if(i == num-1){
//					pipingList1 = pipingList.subList(i*10000, pipingList.size());//切分最后的
//				}else{
//					pipingList1 = pipingList.subList(i*10000, (i+1)*10000);//每次切分10000条
//				}
//				fileName="tn_piping_"+(i+1)+".txt";
//				pipingNameList.add(fileName);
//				listJson = JSONArray.fromObject(pipingList1);
//				list=listJson.toString();
//				uploadFile(list,request,fileName,areaCode);//生成文件
//			}
//		}else{
//			fileName="tn_piping.txt";//.txt文件名
//			pipingNameList.add(fileName);
//			listJson = JSONArray.fromObject(pipingList);//把数据转成json
//			list=listJson.toString();
//			uploadFile(list,request,fileName,areaCode);//生成文件
//		}
//		pipingCutData.setBaseUrl(baseUrl);//设置路径
//		pipingCutData.setFileNameList(pipingNameList);
//		cutDataList.add(pipingCutData);//生成CutData.txt文件的list,记录切分的信息
//		pipingList=null;
//		listJson=null;
//		list=null;
		
		//13.tn_project
		List<Project> projectList=projectService.queryList(users.getOrgId());
		List<Project> projectList1;
		CutData projectCutData = new CutData();
		List<String> projectNameList = new ArrayList<String>();
		num = 1+projectList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		projectCutData.setCutName("tn_project.txt");
		projectCutData.setCutNum(num);
		if(projectList!=null && projectList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					projectList1 = projectList.subList(i*10000, projectList.size());//切分最后的
				}else{
					projectList1 = projectList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_project_"+(i+1)+".txt";
				projectNameList.add(fileName);
				listJson = JSONArray.fromObject(projectList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_project.txt";//.txt文件名
			projectNameList.add(fileName);
			listJson = JSONArray.fromObject(projectList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		projectCutData.setBaseUrl(baseUrl);//设置路径
		projectCutData.setFileNameList(projectNameList);
		cutDataList.add(projectCutData);//生成CutData.txt文件的list,记录切分的信息
		projectList=null;
		listJson=null;
		list=null;
		
		//14.tn_roles
		List<Roles> rolesList=rolesService.queryList();
		List<Roles> rolesList1;
		CutData rolesCutData = new CutData();
		List<String> rolesNameList = new ArrayList<String>();
		num = 1+rolesList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		rolesCutData.setCutName("tn_roles.txt");
		rolesCutData.setCutNum(num);
		if(rolesList!=null && rolesList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					rolesList1 = rolesList.subList(i*10000, rolesList.size());//切分最后的
				}else{
					rolesList1 = rolesList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_roles_"+(i+1)+".txt";
				rolesNameList.add(fileName);
				listJson = JSONArray.fromObject(rolesList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_roles.txt";//.txt文件名
			rolesNameList.add(fileName);
			listJson = JSONArray.fromObject(rolesList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		rolesCutData.setBaseUrl(baseUrl);//设置路径
		rolesCutData.setFileNameList(rolesNameList);
		cutDataList.add(rolesCutData);//生成CutData.txt文件的list,记录切分的信息
		rolesList=null;
		listJson=null;
		list=null;
		
		
		//15.tn_route
		List<RouteZF> routeList=routeService.queryList(users.getOrgId(),areaCode);
		List<RouteZF> routeList1;
		CutData routeCutData = new CutData();
		List<String> routeNameList = new ArrayList<String>();
		num = 1+routeList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		routeCutData.setCutName("tn_route.txt");
		routeCutData.setCutNum(num);
		if(routeList!=null && routeList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					routeList1 = routeList.subList(i*10000, routeList.size());//切分最后的
				}else{
					routeList1 = routeList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_route_"+(i+1)+".txt";
				routeNameList.add(fileName);
				listJson = JSONArray.fromObject(routeList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_route.txt";//.txt文件名
			routeNameList.add(fileName);
			listJson = JSONArray.fromObject(routeList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		routeCutData.setBaseUrl(baseUrl);//设置路径
		routeCutData.setFileNameList(routeNameList);
		cutDataList.add(routeCutData);//生成CutData.txt文件的list,记录切分的信息
		routeList=null;
		listJson=null;
		list=null;
		
//		//16.tn_sect_dev
//		List<SectDev> sectDevList=sectDevService.queryList();
//		List<SectDev> sectDevList1;
//		CutData sectDevCutData = new CutData();
//		List<String> sectDevNameList = new ArrayList<String>();
//		num = 1+sectDevList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
//		sectDevCutData.setCutName("tn_sect_dev.txt");
//		sectDevCutData.setCutNum(num);
//		if(sectDevList!=null && sectDevList.size()>10000){
//			for(int i=0;i<num;i++){
//				if(i == num-1){
//					sectDevList1 = sectDevList.subList(i*10000, sectDevList.size());//切分最后的
//				}else{
//					sectDevList1 = sectDevList.subList(i*10000, (i+1)*10000);//每次切分10000条
//				}
//				fileName="tn_sect_dev_"+(i+1)+".txt";
//				sectDevNameList.add(fileName);
//				listJson = JSONArray.fromObject(sectDevList1);
//				list=listJson.toString();
//				uploadFile(list,request,fileName,areaCode);//生成文件
//			}
//		}else{
//			fileName="tn_sect_dev.txt";//.txt文件名
//			sectDevNameList.add(fileName);
//			listJson = JSONArray.fromObject(sectDevList);//把数据转成json
//			list=listJson.toString();
//			uploadFile(list,request,fileName,areaCode);//生成文件
//		}
//		sectDevCutData.setBaseUrl(baseUrl);//设置路径
//		sectDevCutData.setFileNameList(sectDevNameList);
//		cutDataList.add(sectDevCutData);//生成CutData.txt文件的list,记录切分的信息
//		sectDevList=null;
//		listJson=null;
//		list=null;
		
		//17.tn_site_code
		List<SiteCode> siteCodeList=siteCodeService.queryList();
		List<SiteCode> siteCodeList1;
		CutData siteCodeCutData = new CutData();
		List<String> siteCodeNameList = new ArrayList<String>();
		num = 1+siteCodeList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		siteCodeCutData.setCutName("tn_site_code.txt");
		siteCodeCutData.setCutNum(num);
		if(siteCodeList!=null && siteCodeList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					siteCodeList1 = siteCodeList.subList(i*10000, siteCodeList.size());//切分最后的
				}else{
					siteCodeList1 = siteCodeList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				siteCodeList1 = siteCodeList.subList(i*10000, (i+1)*10000);
				fileName="tn_site_code_"+(i+1)+".txt";
				siteCodeNameList.add(fileName);
				listJson = JSONArray.fromObject(siteCodeList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_site_code.txt";//.txt文件名
			siteCodeNameList.add(fileName);
			listJson = JSONArray.fromObject(siteCodeList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		siteCodeCutData.setBaseUrl(baseUrl);//设置路径
		siteCodeCutData.setFileNameList(siteCodeNameList);
		cutDataList.add(siteCodeCutData);//生成CutData.txt文件的list,记录切分的信息
		siteCodeList=null;
		listJson=null;
		list=null;
		
//		//18.tn_space
//		List<Space> spaceList=spaceService.queryList();
//		List<Space> spaceList1;
//		CutData spaceCutData = new CutData();
//		List<String> spaceNameList = new ArrayList<String>();
//		num = 1+spaceList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
//		spaceCutData.setCutName("tn_space.txt");
//		spaceCutData.setCutNum(num);
//		if(spaceList!=null && spaceList.size()>10000){
//			for(int i=0;i<num;i++){
//				if(i == num-1){
//					spaceList1 = spaceList.subList(i*10000, spaceList.size());//切分最后的
//				}else{
//					spaceList1 = spaceList.subList(i*10000, (i+1)*10000);//每次切分10000条
//				}
//				fileName="tn_space_"+(i+1)+".txt";
//				spaceNameList.add(fileName);
//				listJson = JSONArray.fromObject(spaceList1);
//				list=listJson.toString();
//				uploadFile(list,request,fileName,areaCode);//生成文件
//			}
//		}else{
//			fileName="tn_space.txt";//.txt文件名
//			spaceNameList.add(fileName);
//			listJson = JSONArray.fromObject(spaceList);//把数据转成json
//			list=listJson.toString();
//			uploadFile(list,request,fileName,areaCode);//生成文件
//		}
//		spaceCutData.setBaseUrl(baseUrl);//设置路径
//		spaceCutData.setFileNameList(spaceNameList);
//		cutDataList.add(spaceCutData);//生成CutData.txt文件的list,记录切分的信息
//		spaceList=null;
//		listJson=null;
//		list=null;
		
		//19.tn_twinfiber
		List<Twinfiber> twinfiberList=twinfiberService.queryList(users.getOrgId(),areaCode);
		List<Twinfiber> twinfiberList1;
		CutData twinfiberCutData = new CutData();
		List<String> twinfiberNameList = new ArrayList<String>();
		num = 1+twinfiberList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		twinfiberCutData.setCutName("tn_twinfiber.txt");
		twinfiberCutData.setCutNum(num);
		if(twinfiberList!=null && twinfiberList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					twinfiberList1 = twinfiberList.subList(i*10000, twinfiberList.size());//切分最后的
				}else{
					twinfiberList1 = twinfiberList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_twinfiber_"+(i+1)+".txt";
				twinfiberNameList.add(fileName);
				listJson = JSONArray.fromObject(twinfiberList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_twinfiber.txt";//.txt文件名
			twinfiberNameList.add(fileName);
			listJson = JSONArray.fromObject(twinfiberList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		twinfiberCutData.setBaseUrl(baseUrl);//设置路径
		twinfiberCutData.setFileNameList(twinfiberNameList);
		cutDataList.add(twinfiberCutData);//生成CutData.txt文件的list,记录切分的信息
		twinfiberList=null;
		listJson=null;
		list=null;
		
		//20.tn_user_role
		List<UserRole> userRoleList=userRoleService.queryList();
		List<UserRole> userRoleList1;
		CutData userRoleCutData = new CutData();
		List<String> userRoleNameList = new ArrayList<String>();
		num = 1+userRoleList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		userRoleCutData.setCutName("tn_user_role.txt");
		userRoleCutData.setCutNum(num);
		if(userRoleList!=null && userRoleList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					userRoleList1 = userRoleList.subList(i*10000, userRoleList.size());//切分最后的
				}else{
					userRoleList1 = userRoleList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_user_role_"+(i+1)+".txt";
				userRoleNameList.add(fileName);
				listJson = JSONArray.fromObject(userRoleList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_user_role.txt";//.txt文件名
			userRoleNameList.add(fileName);
			listJson = JSONArray.fromObject(userRoleList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		userRoleCutData.setBaseUrl(baseUrl);//设置路径
		userRoleCutData.setFileNameList(userRoleNameList);
		cutDataList.add(userRoleCutData);//生成CutData.txt文件的list,记录切分的信息
		userRoleList=null;
		listJson=null;
		list=null;
		
		//21.tn_users
		List<Users> usersList=usersService.queryList(users.getOrgId());
		List<Users> usersList1;
		CutData usersCutData = new CutData();
		List<String> usersNameList = new ArrayList<String>();
		num = 1+usersList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		usersCutData.setCutName("tn_users.txt");
		usersCutData.setCutNum(num);
		if(usersList!=null && usersList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					usersList1 = usersList.subList(i*10000, usersList.size());//切分最后的
				}else{
					usersList1 = usersList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_users_"+(i+1)+".txt";
				usersNameList.add(fileName);
				listJson = JSONArray.fromObject(usersList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_users.txt";//.txt文件名
			usersNameList.add(fileName);
			listJson = JSONArray.fromObject(usersList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		usersCutData.setBaseUrl(baseUrl);//设置路径
		usersCutData.setFileNameList(usersNameList);
		cutDataList.add(usersCutData);//生成CutData.txt文件的list,记录切分的信息
		usersList=null;
		listJson=null;
		list=null;
		
		//22.tn_valve
//		List<Valve> valveList=valveService.queryList();
//		List<Valve> valveList1;
//		CutData valveCutData = new CutData();
//		List<String> valveNameList = new ArrayList<String>();
//		num = 1+valveList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
//		valveCutData.setCutName("tn_valve.txt");
//		valveCutData.setCutNum(num);
//		if(valveList!=null && valveList.size()>10000){
//			for(int i=0;i<num;i++){
//				if(i == num-1){
//					valveList1 = valveList.subList(i*10000, valveList.size());//切分最后的
//				}else{
//					valveList1 = valveList.subList(i*10000, (i+1)*10000);//每次切分10000条
//				}
//				fileName="tn_valve_"+(i+1)+".txt";
//				valveNameList.add(fileName);
//				listJson = JSONArray.fromObject(valveList1);
//				list=listJson.toString();
//				uploadFile(list,request,fileName,areaCode);//生成文件
//			}
//		}else{
//			fileName="tn_valve.txt";//.txt文件名
//			valveNameList.add(fileName);
//			listJson = JSONArray.fromObject(valveList);//把数据转成json
//			list=listJson.toString();
//			uploadFile(list,request,fileName,areaCode);//生成文件
//		}
//		valveCutData.setBaseUrl(baseUrl);//设置路径
//		valveCutData.setFileNameList(valveNameList);
//		cutDataList.add(valveCutData);//生成CutData.txt文件的list,记录切分的信息
//		valveList=null;
//		listJson=null;
//		list=null;
		
		//23.tn_well_pileline
		List<WellPileline> wellPilelineList=wellPilelineService.queryList();
		List<WellPileline> wellPilelineList1;
		CutData wellPilelineCutData = new CutData();
		List<String> wellPilelineNameList = new ArrayList<String>();
		num = 1+wellPilelineList.size()/10000;//切分的数量,目的是把文件且分为3M一下大小的文件,所以统一查询10000条,在3M以下;+1,解决数据不满1W不生成的问题
		wellPilelineCutData.setCutName("tn_well_pileline.txt");
		wellPilelineCutData.setCutNum(num);
		if(wellPilelineList!=null && wellPilelineList.size()>10000){
			for(int i=0;i<num;i++){
				if(i == num-1){
					wellPilelineList1 = wellPilelineList.subList(i*10000, wellPilelineList.size());//切分最后的
				}else{
					wellPilelineList1 = wellPilelineList.subList(i*10000, (i+1)*10000);//每次切分10000条
				}
				fileName="tn_well_pileline_"+(i+1)+".txt";
				wellPilelineNameList.add(fileName);
				listJson = JSONArray.fromObject(wellPilelineList1);
				list=listJson.toString();
				uploadFile(list,request,fileName,areaCode);//生成文件
			}
		}else{
			fileName="tn_well_pileline.txt";//.txt文件名
			wellPilelineNameList.add(fileName);
			listJson = JSONArray.fromObject(wellPilelineList);//把数据转成json
			list=listJson.toString();
			uploadFile(list,request,fileName,areaCode);//生成文件
		}
		wellPilelineCutData.setBaseUrl(baseUrl);//设置路径
		wellPilelineCutData.setFileNameList(wellPilelineNameList);
		cutDataList.add(wellPilelineCutData);//生成CutData.txt文件的list,记录切分的信息
		wellPilelineList=null;
		listJson=null;
		list=null;
		
		//记录以上表的相关信息:表名,下载路径,切分数量,切分后每张表的名
		fileName="CutData.txt";
		JSONArray cutDataJson = JSONArray.fromObject(cutDataList);
		list=cutDataJson.toString();
		return uploadFile(list,request,fileName,areaCode);
	}
	
	/**
	 * 导出数据库工具 刘沧海 2017/10/13
	 * 修改:DZY
	 */
	private static String prexImagUrl ="/apk/data";
	public static String uploadFile(String list,HttpServletRequest request,String fileName,String areaCode)
	{
		String str="1";
		String path = request.getSession().getServletContext().getRealPath("/") + prexImagUrl+"/"+areaCode;//保存路径
//		String FileURL = prexImagUrl +"/" +fileName;
		File parent = new File(path);
		if(parent.exists()) {//如果文件存在删除原文件,重新生成
			parent.delete();
		}
		parent.mkdirs();//生成文件夹
		File dataBaseFile = new File(parent , fileName );//生成文件
		OutputStream out = null;
		BufferedWriter rd = null;
		try {
			 out =new FileOutputStream(dataBaseFile);
			 rd   =   new BufferedWriter(new OutputStreamWriter(out,"utf-8"));
			 rd.write(list);
			return str;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (rd!=null)
			{
				try {
					rd.close();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "0";
	}

	/**
	 * 生成数据的添加保存
	 */
	@Override
	public int saveGenerData(ResourceData rdi) {
		return resourceDataMapper.insertSelective(rdi);
	}

	/**
	 * 根据areaCode查询数据
	 */
	@Override
	public List<ResourceData> queryByAreaCode(String areaCode) {
		return resourceDataMapper.queryByAreaCode(areaCode);
	}

	/**
	 * 生成数据的修改保存
	 */
	@Override
	public int updateGenerData(ResourceData rdi) {
		return resourceDataMapper.updateByPrimaryKeySelective(rdi);
	}

	/**
	 * 导入资管数据文件
	 */
	@Override
	public int importResourceDatasFile(HttpSession session, HttpServletRequest request, String areaCode, MultipartFile importFile) {
		String path = request.getSession().getServletContext().getRealPath("/") + prexImagUrl+"/"+areaCode;//保存路径
		try {
			String fileName = importFile.getOriginalFilename();
			int dot = fileName.lastIndexOf('.');
			String db = fileName.substring(dot+1);//获取文件扩展名
//			System.out.println("~~~~~~~~~~~~~"+db);
			InputStream in = importFile.getInputStream();
			int size=in.available();
			if(size>209715200)
				return 4;//文件大于200M
			System.out.println("!~~~~~~~"+size);
			if("db".equals(db)) {
				if(!importFile.isEmpty()) {
					File parent = new File(path);
					if(parent.exists()) {//如果文件存在删除原文件,重新生成
						parent.delete();
					}
					parent.mkdirs();//生成文件夹
					File dataBaseFile = new File(parent , fileName);//生成文件
					importFile.transferTo(dataBaseFile);
					return 1;//导入成功
				}
				return 2;//文件为空
			}else {
				return 3;//文件类型错误
			}
		} catch (IllegalStateException e) {
			//TODOAuto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			//TODOAuto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
