package com.ycnet.facility.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.core.config.SessionName;
import com.ycnet.core.util.PageBean;
import com.ycnet.facility.controller.FacilityDetailController.PageFlag;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroupZF;
import com.ycnet.frms.bean.LineImage;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.PortImg;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.LineImageMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.PortImgMapper;
import com.ycnet.frms.service.CableSectionService;
import com.ycnet.frms.service.DiscinfoService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscGroupService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.service.RouteService;
import com.ycnet.frms.vo.CableRoute;
import com.ycnet.frms.vo.CableRouteCondition;
import com.ycnet.frms.vo.CableSectionBean;
import com.ycnet.frms.vo.CableSectionChart;
import com.ycnet.frms.vo.Disc;
import com.ycnet.frms.vo.FacilityAsasideCableBean;
import com.ycnet.frms.vo.FiberdiscGroupBean;
import com.ycnet.frms.vo.Group;
import com.ycnet.frms.vo.JumperTerraceInfo;
import com.ycnet.frms.vo.LinesBean;
import com.ycnet.frms.vo.Port;
import com.ycnet.frms.vo.RouteChart;
import com.ycnet.mobile.util.Result;
/**
 * 设施相关图示
 * @author 海阔天空
 *
 */
@Controller
public class FacilityChartController {
	
	@Resource(name="fiberdiscGroupService")
	private FiberdiscGroupService fiberdiscGroupService;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;
	
	@Resource(name="cableSectionService")
	private CableSectionService cableSectionService;
	
	@Resource(name="routeService")
	private RouteService routeService;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="discinfoService")
	private DiscinfoService discinfoService;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	

	
	
	
	
	
	/**
	 * 设施关系示意图
	 * @return
	 */
	@RequestMapping("/facilityRelation.htm")
	public String facilityRelation(Model model,HttpSession session)
	{
		Users user=(Users)session.getAttribute(SessionName.platform);
		CableRouteCondition c=new CableRouteCondition();
		c.setOrgId(user.getOrgId());
		model.addAttribute("crLinks",cableSectionService.queryCableRouteLinksV1(c));
		model.addAttribute("crNodes",cableSectionService.queryCableRouteNodesV1(c));
		
		return "facilityChart/facilityRelation";
	}
	
	/**
	 * 设施关系示意图simple
	 * @return
	 */
	@RequestMapping("/facilityRelationSimple.htm")
	public String facilityRelationSimple(Model model,HttpSession session)
	{
		Users user=(Users)session.getAttribute(SessionName.platform);
		CableRouteCondition c=new CableRouteCondition();
		c.setOrgId(user.getOrgId());
		model.addAttribute("userName",user.getUserName());
		model.addAttribute("crLinks",cableSectionService.queryCableRouteLinksV1(c));
		model.addAttribute("crNodes",cableSectionService.queryCableRouteNodesV1(c));
		
		return "facilityChart/facilityRelationSimple";
	}
	
	/**
	 * 设施关系示意图
	 * 改为地图
	 * liucanghai
	 * @return
	 */
	@RequestMapping("/facilityRelationBydevId.htm")
	public String facilityRelationByOne(Model model,Long devId,HttpSession session,String devName)
	{
		Users user=(Users)session.getAttribute(SessionName.platform);
		Facility fa=facilityService.selectByPrimaryKey(devId);
		if(fa!=null) {
			if(fa.getBaiduX()!=null && fa.getBaiduY()!=null) {
				model.addAttribute("baiduX",fa.getBaiduX());
				model.addAttribute("baiduY",fa.getBaiduY());
			}else {
				model.addAttribute("baiduX","");
				model.addAttribute("baiduY","");
			}
		}
		List<Facility> list=facilityService.queryDevListForIds(devId);
		if(list!=null && list.size()>0) {
			model.addAttribute("faList",list);
		}
		
		List<CableSection> cableList=cableSectionService.queryForDevId(devId);
		if(cableList!=null && cableList.size()>0) {
			model.addAttribute("cableList",cableList);
		}
		
//		CableRoute cr=cableSectionService.queryCableRoute(devId);
//		model.addAttribute("userName",user.getUserName());
//		model.addAttribute("crLinks",cr.getLinks());
//		model.addAttribute("crNodes",cr.getNodes());
//		Facility f=facilityService.selectById(devId);
//		model.addAttribute("currNode", f.getDevName());
		
		//fl 添加导航页面用
		model.addAttribute("devId",devId);//必须，页面导航用
		model.addAttribute("devName",devName);//必须，页面导航用
		model.addAttribute("pageFlag",PageFlag.topography);//必须，页面导航用
		
		return "facilityChart/facilityCableSectionMap";
	}
	
	
	/**
	 * 熔纤盘示意图
	 * @return
	 */
	@RequestMapping("/fiberdiscChart.htm")
	public String fiberdiscChart(Model model,Long devId,String flag)
	{
		List<Facility> facilityList = facilityService.selectByDevId(devId);
		model.addAttribute("facilityList", facilityList);
		model.addAttribute("devId", devId);
		List<Group> groupList=fiberdiscService.queryDiscGroupDiscPorts(devId);
		if (flag==null || "".equals(flag)) {
			model.addAttribute("flag", "0");
		}else {
			model.addAttribute("flag", flag);
		}
		model.addAttribute("groupList", groupList);
		return "facilityChart/fiberdiscChartNew";
	}
	
//	/**
//	 * 熔纤盘示意图
//	 * @return
//	 */
//	@RequestMapping("/fiberdiscChart.htm")
//	public String fiberdiscChart(Model model,Long devId)
//	{
//		model.addAttribute("devId", devId);
//		List<Group> groups=fiberdiscService.selectGroupWithLines(devId);
//		
//			List<FiberdiscGroup> fiberdiscGroups=new ArrayList<FiberdiscGroup>();	
//			for(Group group:groups)
//			{
//				FiberdiscGroup fg=new FiberdiscGroup();	
//				fg.setGroupName(group.getSide());
//				fg.setGroupId(group.getGroupId());
//				fg.setGroupNameG(group.getGroupName());
////				fg.setGroupName("熔纤盘/"+group.getSide());
////				if (fg.getGroupName().contains("ZRIN")) {
////					fg.setGroupName("直熔盘/"+group.getSide());
////				}
////				if (fg.getGroupName().contains("ZROUT")) {
////					fg.setGroupName("直熔盘/"+fg.getGroupName());
////				}
//				Map<String,String> statusMap=new HashMap<String,String>();
//				statusMap.put("0", "未使用");
//				statusMap.put("1", "空闲");
//				statusMap.put("2", "占用");
//				fg.setStatusMap(statusMap);
//				
//				List<PortData> portDatas=new ArrayList<PortData>();
//				int i=0;//端口转为顺序号
//				int r=0;//行数
//				Map<String,String> portsMap=new HashMap<String,String>();//点与序号对应关系
//				int portSize=0;
//				int discSize = group.getDiscs().size();
//				for (int k = 0; k < group.getDiscs().size(); k++) {
//					Disc disc = discinfoService.selectDisc(group.getDevId(), group.getSide(), group.getDiscs().get(k).getDiscId());
//					portSize+=disc.getPorts().size();
//				}
//				int port = portSize/discSize;//12 获得每个盘的端子数
//				
//				
//				
//				for(Disc disc1:group.getDiscs())
//				{
//					r++;
//					Disc disc = discinfoService.selectDisc(group.getDevId(), group.getSide(), disc1.getDiscId());
//					PortData pd=new PortData();			
//					pd.setName("盘"+r);		
//					
//					int y=1+(disc1.getRow()-1)*40-1;				
//					int x=1;
//					pd.setX(x+"");
//					pd.setY(y+"");
//					pd.setV(x+""+y+"");
//					portDatas.add(pd);	
//					int l=0;
//					i=Integer.parseInt(disc.getDiscName())*port;//端子数*盘号=端子序号
//					for(Port pt:disc.getPorts())
//					{
//						l++;
//						if(l<port+1) {//判断不得大于端子数
//							i++;
//							PortData pd1=new PortData();			
//							pd1.setCategory(statusMap.get(pt.getStatus()));
//							pd1.setName(i-port+"");
//							portsMap.put(pt.getCode(), i-port+"");//记录对应关系					
//							int x1=1+(pt.getCol()+1-1)*40;
//							pd1.setX(x1+"");
//							pd1.setY(1+(r-1)*40-1+"");
//							pd1.setV(pt.getCode());
//							portDatas.add(pd1);
//						}
//					}
//					l=0;
//				}
//				fg.setPortDatas(portDatas);
//				int groupHeight=r*50+100;			
//				fg.setGroupHeight(groupHeight);
//				fg.setEchartsHeight(groupHeight+200);
//				
//				
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
//				
//				
//				fiberdiscGroups.add(fg);			
//			}
////			if (fiberdiscGroups!=null && fiberdiscGroups.size()>0) {
////				String groupName;
////				for (int j = 0; j < fiberdiscGroups.size(); j++) {
////					String name = fiberdiscGroups.get(j).getGroupName();
////					if (name.indexOf("ZRIN")>0) {
////						groupName="直熔盘/"+name;
////						fiberdiscGroups.get(j).setGroupName(groupName);
////					}
////					if (name.indexOf("ZROUT")>0) {
////						groupName="直熔盘/"+name;
////						fiberdiscGroups.get(j).setGroupName(groupName);
////					}
////					if (name.indexOf("GJ")>0) {
////						groupName="熔纤盘/"+name;
////						fiberdiscGroups.get(j).setGroupName(groupName);
////					}
////				}
////			}
//			model.addAttribute("fgs",fiberdiscGroups);
//			if(fiberdiscGroups!=null&&fiberdiscGroups.size()>0)
//			{
//				model.addAttribute("flag","1");
//			}
//			else
//			{
//				model.addAttribute("flag","0");
//			}
//			
//		
//		return "facilityChart/fiberdiscChart";
//	}
	
	public static void main(String[] args) {
		int ss=48;
		
		
		
	}
	
	/**
	 * 熔纤盘示意图测试版本
	 * @return
	 */
	@RequestMapping("/fiberdiscChartTest.htm")
	public String fiberdiscChartTest(Model model,Integer sl)
	{
		if(sl==0)
		{
			sl=24;
		}
		List<FiberdiscGroup> fiberdiscGroups=new ArrayList<FiberdiscGroup>();
		
		for(int k=1;k<10;k++)
		{
			FiberdiscGroup fg=new FiberdiscGroup();
			fg.setGroupName("M"+k);
			Map<String,String> statusMap=new HashMap<String,String>();
			statusMap.put("1", "未使用");
			statusMap.put("2", "空闲");
			statusMap.put("3", "占用");
			fg.setStatusMap(statusMap);
			List<PortData> portDatas=new ArrayList<PortData>();
			int iLen=sl;
			for(int i=1;i<=iLen;i++)
			{
				
				for(int j=1;j<=12;j++)
				{
					PortData pd=new PortData();			
					pd.setCategory("1");
					pd.setName(i+"-"+j);		
					
					int y=1+(i-1)*40;
					int x=1+(j-1)*40;
					pd.setX(x+"");
					pd.setY(y+"");
					
					portDatas.add(pd);
				}
			} 
			
			fg.setPortDatas(portDatas);
			int groupHeight=iLen*50+100;
			
			fg.setGroupHeight(groupHeight);
			fg.setEchartsHeight(groupHeight+200);
			fiberdiscGroups.add(fg);
			model.addAttribute("fgs",fiberdiscGroups);
		}
		
		return "facilityChart/fiberdiscChart";
	}
	
	
	
	/**
	 * 光路示意图  routeId 光路id
	 * @return
	 */
	@RequestMapping("/lightPathChart.htm")
	public String lightPathChart(Model model,Long routeId,PageBean pb)
	{
		
		RouteChart rc=this.routeService.queryRouteById(routeId);
		model.addAttribute("rt",rc);
		
		return "facilityChart/lightPathChart";
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

		private Long groupId;
		
		private String groupNameG;
		
		
		
		public String getGroupNameG() {
			return groupNameG;
		}

		public void setGroupNameG(String groupNameG) {
			this.groupNameG = groupNameG;
		}

		public Long getGroupId() {
			return groupId;
		}

		public void setGroupId(Long groupId) {
			this.groupId = groupId;
		}

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
	
	/**
	 * 
	* @Title: queryJumperTerrace 
	* @Description: 根据端口编号查询端口状态 
	* @param @param code
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年1月22日 下午4:38:53 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/queryJumperTerrace.htm")
	public Object queryJumperTerrace(String code,Model model) {
		Result r = Result.get();
		JumperTerraceInfo info=facilityService.queryJumperTerrace(code);
		if(info!=null) {
			r.putR(1);
			r.putDT(info);
		}
		return r;
	}
	/**
	 * 
	* @Title: Object 
	* @Description: 查询已成端光缆 
	* @param     
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年2月1日 上午10:31:55 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/queryFinCable.htm")
	public Object queryFinCable(Long groupId,HttpSession session)  {
//		Users user = (Users) session.getAttribute(SessionName.mobile);
		Result r = Result.get();
		List<CableSectionBean> cablesectionlist =facilityService.queryFinCable(groupId);
		if(cablesectionlist!=null && cablesectionlist.size()>0) {
			r.putR(1);
			r.putDtList(cablesectionlist);
		}
		return r;
	}
	
	/**
	 * 
	* @Title: Object 
	* @Description: 查询直熔光缆 
	* @param     
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年2月1日 上午10:31:55 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/queryZhiCable.htm")
	public Object queryZhiCable(Long groupId,HttpSession session)  {
//		Users user = (Users) session.getAttribute(SessionName.mobile);
		Result r = Result.get();
		List<CableSectionBean> cablesectionlist =facilityService.queryZhiCable(groupId);
		if(cablesectionlist!=null && cablesectionlist.size()>0) {
			r.putR(1);
			r.putDtList(cablesectionlist);
		}
		return r;
	}
	
}
