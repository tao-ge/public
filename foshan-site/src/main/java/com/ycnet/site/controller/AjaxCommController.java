package com.ycnet.site.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.util.LatLng;
import com.ycnet.core.util.Map_Util;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.vo.FacilityConditionBean;

@RestController
@RequestMapping("/ajax")
public class AjaxCommController {

	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	
	@Resource(name="areasService")
	private AreasService areasService;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@RequestMapping("/devType.htm")
	public Object getDevType()
	{
		return basecodeService.getDEVTypeMap();
	}
	
	@RequestMapping("/classcode.htm")
	public Object getClasscode()
	{
		return basecodeService.getClasscode();
	}
	
	@RequestMapping("/getProv.htm")
	public Object getProv(Areas record)
	{
		record.setAreaRank("1");
		return areasService.selectByAreaRank(record);
	}
	
	@RequestMapping("/getCity.htm")
	public Object getCity(Areas record)
	{
		record.setAreaRank("2");
		return areasService.selectByAreaRank(record);
	}
	
	@RequestMapping("/getAreas.htm")
	public Object getAreas(Areas record)
	{


		return areasService.selectByAreaRank(record);
	}
		
	@RequestMapping("/showSitesMap.htm")
	public Object getSites(HttpSession session,FacilityConditionBean facilityCon)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		facilityCon.setIsNullPoint(1);//判断坐标是否为空		
		PageBean pb = new PageBean();
		pb.setPageSize(1);
		pb = facilityService.queryDistanceFacilityByCondition(facilityCon,user,pb);
				
		return pb;
	}
	
	@RequestMapping("/showDragendMap.htm")
	public Object getSitestest(HttpSession session,String areaCode1,Double curLng,Double curLat)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		
		LatLng latLng = Map_Util.bd09_To_Gps84(curLat,curLng);
		
		PageBean pb = new PageBean();
		pb.setPageSize(20000);
		pb.setList(facilityService.selectAroundConvert(latLng.getLongitude(),latLng.getLatitude(),500.00,null,null,areaCode1,user));
		return pb;
	}
}
