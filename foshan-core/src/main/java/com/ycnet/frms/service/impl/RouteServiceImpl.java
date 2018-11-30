package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Case;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.util.BeanUtils;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.RouteNode;
import com.ycnet.frms.bean.RouteZF;
import com.ycnet.frms.bean.TaskRecord;
import com.ycnet.frms.bean.TempLightPath;
import com.ycnet.frms.bean.TnExport;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.LinesTmpMapper;
import com.ycnet.frms.mapper.RouteMapper;
import com.ycnet.frms.mapper.RouteNodeMapper;
import com.ycnet.frms.mapper.TempLightPathMapper;
import com.ycnet.frms.mapper.TnExportMapper;
import com.ycnet.frms.service.RouteService;
import com.ycnet.frms.vo.LinesTmp;
import com.ycnet.frms.vo.RoutChartLinkData;
import com.ycnet.frms.vo.RoutChartNodeData;
import com.ycnet.frms.vo.RouteBean;
import com.ycnet.frms.vo.RouteChart;
import com.ycnet.frms.vo.RouteTmp;
import com.ycnet.frms.vo.SectRoute;
import com.ycnet.frms.vo.SectRouteBean;
import com.ycnet.frms.vo.sectRoute.SectRouteResult;
import com.ycnet.frms.vo.sectRoute.RouteText;
import com.ycnet.frms.vo.sectRoute.SectRouteDisc;
import com.ycnet.frms.vo.sectRoute.SectRouteFiber;
import com.ycnet.frms.vo.sectRoute.SectRouteGroup;

@Service("routeService")
@Transactional
public class RouteServiceImpl implements RouteService {

	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name ="tempLightPathMapper")
	private TempLightPathMapper tempLightPathMapper;
	
	@Resource(name="linesTmpMapper")
	private LinesTmpMapper linesTmpMapper;
	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper cableSectionMapper;
	
	@Override
	public int insert(Route route) {
		
		return routeMapper.insertSelective(route);
	}

	@Override
	public int insert(RouteNode routeNode) {
		return routeNodeMapper.insertSelective(routeNode);
	}

	@Override
	public int deleteById(Long routeId) {
		int ret = 0;
		ret = routeMapper.deleteByPrimaryKey(routeId);
		routeNodeMapper.deleteByRoutePrimaryKey(routeId);
		return ret;
	}

	@Override
	public List<Route> queryRouteByName(String routeName) {
		return routeMapper.queryRouteByName(routeName);
	}

	@Override
	public List<Route> queryRouteByDev(Long devId) {
		return routeMapper.queryRouteByDev(devId);
	}

	@Override
	public List<Route> queryRouteByCode(String code) {
		return routeMapper.queryRouteByCode(code);
	}

	@Override
	public List<Route> queryRouteByStartCode(String startCode) {
		return routeMapper.queryRouteByStartCode(startCode);
	}
	
	@Override
	public List<Route> queryRouteByEndpoint(String acode, String zcode) {
		return routeMapper.queryRouteByEndpoint(acode,zcode);
	}
	
	@Override
	public PageBean queryByConditionBean(RouteBean bean,
			Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		if(bean.getDevId()!=null && bean.getDevId().longValue()!=-1L) {
			Facility fa = facilityMapper.selectByPrimaryKey(bean.getDevId());
			bean.setDevName(fa.getDevName());//设施名称模糊查询文本路由
		}
		conditionMap.put("routeBean", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		
		pb.setRows(routeMapper.queryCountByConditionMap(conditionMap));//;
		pb.setList(this.Convert(routeMapper.queryByConditionMap(conditionMap)));		
				
		return pb;
	}
	
	/**
	 * 转换实体bean为业务bean
	 * @param routeList
	 * @return
	 */
	private List<RouteBean> Convert(List<Route> routeList)
	{
		List<RouteBean> rbList=new ArrayList<RouteBean>();
		for(Route r:routeList)
		{
			RouteBean rb=new RouteBean();
			BeanUtils.copy(r, rb);
			
    		Facility f = UtilsService.getFacility(r.getAdevId());
    		if(f!=null)
    			rb.setAdevName(f.getDevName());
    		else
    			rb.setAdevName("");
	    	
    		f = UtilsService.getFacility(r.getZdevId());
    		if(f!=null)
    			rb.setZdevName(f.getDevName());
    		else
    			rb.setZdevName("");
    		
    		
    		
			rbList.add(rb);
		}
		
		return rbList;
	}

	@Resource(name ="routeMapper")
	private RouteMapper routeMapper;
	
	@Resource(name ="routeNodeMapper")
	private RouteNodeMapper routeNodeMapper;

	@Override
	public RouteChart queryRouteById(Long routeId) {
		// TODO Auto-generated method stub

		Route r=routeMapper.selectByPrimaryKey(routeId);
		RouteChart rc=new RouteChart();
		
		BeanUtils.copy(r, rc);
		
		
		
		
    	
		
		
//		//设施光路线的信息
//		List<RoutChartLinkData> links=new ArrayList<RoutChartLinkData>();	
//		List<RoutChartNodeData> nodes=new ArrayList<RoutChartNodeData>();
//		int  flag=0;
//		RoutChartLinkData d=new RoutChartLinkData();
//		
//		long a1=rc.getNodes().size();
//		
//		for(RouteNode rn:rc.getNodes())
//		{
//			RoutChartNodeData rcn=new RoutChartNodeData();
//			BeanUtils.copy(rn, rcn);
//			rcn.setNodeName(this.fingFacilityName(rn.getCode()));
//			nodes.add(rcn);
//			
//			if(flag==0)
//			{
//				d.setSource(rn.getCode());
//				d.setSourceName(rcn.getNodeName());
//				flag=1;
//			}
//			else
//			{
//				d.setTarget(rn.getCode());
//				d.setTargetName(rcn.getNodeName());
//				links.add(d);
//				
//				d=new RoutChartLinkData();
//				d.setSource(rn.getCode());
//				d.setSourceName(rcn.getNodeName());
//			}
//			
//			
//		}		
//		
//		String firstNode=nodes.get(0).getNodeName();
//		String lastNode=nodes.get(nodes.size()-1).getNodeName();
//		if(rc.getAotherName()==null||"".equals(rc.getAotherName()))
//		{
//			Facility f = UtilsService.getFacility(r.getAdevId());
//			if(f!=null)
//				rc.setAdevName(f.getDevName());
//			else
//				rc.setAdevName("");
//		}
//		else
//		{
//			rc.setAdevName(rc.getAotherName());
//			RoutChartNodeData node=new RoutChartNodeData();
//			node.setNodeName(rc.getAotherName());
//			nodes.add(node);
//			RoutChartLinkData link=new RoutChartLinkData();
//			link.setSource("");
//			link.setSourceName(rc.getAotherName());
//			link.setTarget("");
//			link.setTargetName(firstNode);
//			links.add(link);
//		}
//		
//		if(rc.getZotherName()==null||"".equals(rc.getZotherName()))
//		{
//			Facility f =UtilsService.getFacility(r.getZdevId());
//			if(f!=null)
//				rc.setZdevName(f.getDevName());
//			else
//				rc.setZdevName("");
//		}
//		else
//		{
//			rc.setZdevName(rc.getZotherName());
//			RoutChartNodeData node=new RoutChartNodeData();
//			node.setNodeName(rc.getZotherName());
//			nodes.add(node);
//			
//			RoutChartLinkData link=new RoutChartLinkData();
//			link.setSource("");
//			link.setSourceName(rc.getZotherName());
//			link.setTarget("");
//			link.setTargetName(lastNode);
//			links.add(link);
//		}
		//光路示意图
		//将codes字段修改为routetext佟盛玮2017.10.17
		List<RoutChartLinkData> links=new ArrayList<RoutChartLinkData>();	
		List<RoutChartNodeData> nodes=new ArrayList<RoutChartNodeData>();
		if(r.getRoutetext()!=null) {
		String routetext=r.getRoutetext();
		String nodename[] =routetext.split("<==>");
	
		for(int i=0;i<nodename.length;i++) {
			RoutChartNodeData node=new RoutChartNodeData();
			RoutChartLinkData link=new RoutChartLinkData();
			node.setNodeName(nodename[i]);
			nodes.add(node);
			if(i!=nodename.length) {
			link.setSourceName(nodename[i]);
			links.add(link);
			
			}
			if(i+1<nodename.length) {
			link.setTargetName(nodename[i+1]);
			
			links.add(link);
			
			}
			
		}
		
		}
	
		
		rc.setChartlinks(links);
		rc.setChartNodes(nodes);
		
		return rc;
	}
	
	/**
	 * 根据设施熔纤盘点代码获取设施名称
	 * @param code
	 * @return
	 */
	private String fingFacilityName(String code)
	{
		String r=code;
		
		if(code.length()>=6)
		{
			Facility f=this.facilityMapper.selectByCode(code.substring(0, 6));
			if(f!=null)
			{
				r=f.getDevName();
				
				if(code.length()>7)
				{
					r+="("+code.substring(7)+")";
				}
			}
		 }
		
		return r;
	}

	@Override
	public PageBean queryAllByConditionMap(RouteBean bean,
			Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("routeBean", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		
		pb.setRows(routeMapper.queryCountByConditionMap(conditionMap));//;
		pb.setList(routeMapper.queryAllByConditionMap(conditionMap));		
				
		return pb;
	}
	
	@Override
	public PageBean querySimpleInfoByConditionBean(RouteBean bean,Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("routeBean", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		
		pb.setRows(routeMapper.querySimpleInfoCountByConditionMap(conditionMap));//;
		pb.setList(routeMapper.querySimpleInfoByConditionMap(conditionMap));		
				
		return pb;
	}
	//导出机柜光路
	@Override
	public List<RouteBean> queryExport(Long orgId,String areaCode1) {
//		Map<String,Object> conditionMap=new HashMap<String,Object>();
//		conditionMap.put("routeBean", routeBean);
//		conditionMap.put("user", user);
//		conditionMap.put("pb", pb);
//		
//		pb.setRows(routeMapper.queryCountExportMap(conditionMap));//;
//		pb.setList(routeMapper.queryAllExportMap(conditionMap));	
		return routeMapper.queryAllExportMap(orgId,areaCode1);
	}

	@Override
	public List<RouteBean> queryAllCablint(Long orgId,String areaCode1) {
//		Map<String,Object> conditionMap=new HashMap<String,Object>();
//		conditionMap.put("routeBean", routeBean);
//		conditionMap.put("user", user);
//		conditionMap.put("pb", pb);
//		
//		pb.setRows(routeMapper.queryCountCablintMap(conditionMap));//;
//		pb.setList(routeMapper.queryAllCablintMap(conditionMap));
		return routeMapper.queryAllCablint(orgId,areaCode1);
	}

	@Override
	public List<RouteBean> queryAllFacility(Long orgId, String devCode,Long devId,PageBean pb) {
		return routeMapper.queryAllFacility(orgId,devCode,devId,pb);
	}
	
	@Override
	public Integer queryCount(Long orgId, String devCode,Long devId) {
		return routeMapper.queryCount(orgId,devCode,devId);
	}

	@Override
	public List<RouteBean> queryFacility(Long orgId, String devCode, String devType) {
		
		return routeMapper.queryFacility(orgId,devCode,devType);
	}

	@Override
	public List<TempLightPath> queryLPExport(Long orgId, String areaCode1,String devType) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode1", areaCode1);
		map.put("devType", devType);
		return tempLightPathMapper.selectByDevType(map);
	}

	private String aotherName = "";
	private String zotherName = "";
	private String isSameDistrict = "1";//是否跨区
	@Override
	public List<RouteTmp> exportDataTmp(Long orgId, String areaCode1, String devType) {
		// TODO Auto-generated method stub
		List<RouteTmp> listAll = new ArrayList<RouteTmp>(); 
		RouteTmp rt = null;
		
		//处理分光器  
		// -01-01  及一对多
		List<LinesTmp> listFG = linesTmpMapper.selectFGBy01(null);
			//往上递归出光路
		
		for(LinesTmp FGLines : listFG) {
			rt = new RouteTmp();
			
			//上递归查询
			LinesTmp ltFG = linesTmpMapper.selectTXByCode(FGLines.getAcode(),null,null);
			if(ltFG == null) {
				continue;
			}else {
				if("0".equals(ltFG.getIsSameDistrict())) {
					isSameDistrict = "0";
				}
				rt.setRouteText(FGLines.getRouteNoteName());
				rt.setRouteText(ltFG.getRouteNoteName() + "<==>" + rt.getRouteText());
				
				rt = SelectTopByTX(rt,ltFG);
				
			}
			//下递归查询
			RouteTmp rt1 = null;
			List<LinesTmp> ltFGList = linesTmpMapper.selectFGListByCode(FGLines.getAcode(),null);
			for(LinesTmp ltFGLines : ltFGList) {
				rt1 = new RouteTmp();
				LinesTmp ltTXLines = linesTmpMapper.selectTXByCode(ltFGLines.getZcode(),null,null);
				if(ltTXLines == null) {
					zotherName = ltFGLines.getZdevName()+"("+ltFGLines.getZcode()+")";
					rt1.setZdevName(ltFGLines.getZdevName());
					//rt1.setRouteText(rt.getRouteText()+"<==>"+zotherName);
					rt1.setRouteText(rt.getRouteText()+"<==>"+ltFGLines.getRouteNoteName());
				}else {
					if("0".equals(ltTXLines.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					//rt1.setRouteText(rt.getRouteText()+"<==>"+ ltFGLines.getZdevName()+"("+ltFGLines.getZcode()+")" + "<==>" +ltTXLines.getRouteNoteName());
					rt1.setRouteText(rt.getRouteText()+"<==>"+ ltFGLines.getRouteNoteName() + "<==>" +ltTXLines.getRouteNoteName());
					rt1 = SelectDownByTX(rt1,ltTXLines);
				}
				
				rt1.setAdevName(rt.getAdevName());
				rt1.setAotherName(aotherName);	
				rt1.setZotherName(zotherName);
				rt1.setRouteName(aotherName + "<==>" + zotherName);
				rt1.setIsSameDistrict(isSameDistrict);
				listAll.add(rt1);

				
				zotherName = "";
				rt1 = null;
			}
			aotherName = "";
			isSameDistrict = "1";
			rt = null;
			
		}	
		
		//除去分光器生成光路
		String adev = "";
		String zdev = "";
		while (true) {
			LinesTmp zcLine = linesTmpMapper.selectZCLines(null);
			if(zcLine == null) {
				break;
			}
			if("0".equals(zcLine.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			linesTmpMapper.deleteByCode(zcLine);
			//往下递归出光路
			rt = new RouteTmp();
			
			if(zcLine.getZcode() == null) {
				if(linesTmpMapper.selectGXByCode(zcLine.getAcode(),null,null)==null){
					linesTmpMapper.deleteByCode(zcLine);
					continue;
				}
				
				//上行
				rt.setRouteText(zcLine.getAdevName()+"("+zcLine.getAcode()+")");
				zotherName = zcLine.getAdevName()+"("+zcLine.getAcode()+")";
				rt.setZdevName(zcLine.getAdevName());
				rt = SelectTopByTXs(zcLine.getAcode(),rt,zcLine);
			}else if(zcLine.getAcode() == null) {
				if(linesTmpMapper.selectGXByCode(zcLine.getZcode(),null,null)==null){
					linesTmpMapper.deleteByCode(zcLine);
					continue;
				}
				
				//下行
				rt.setRouteText(rt.getRouteText()+"<==>"+zcLine.getZdevName()+"("+zcLine.getZcode()+")");
				aotherName = zcLine.getZdevName()+"("+zcLine.getZcode()+")";
				rt.setAdevName(zcLine.getZdevName());
				rt = SelectDownByTXs(zcLine.getZcode(),rt,zcLine);
			}else {
				adev = zcLine.getAdevName()+zcLine.getAcode();
				zdev = zcLine.getZdevName()+zcLine.getZcode();
				if((adev.indexOf("PTN")!=-1&&zdev.indexOf("BBU")!=-1) || (adev.indexOf("BBU")!=-1&&zdev.indexOf("PTN")!=-1)) {
				}else {
					if(linesTmpMapper.selectGXByCode(zcLine.getAcode(),null,null)==null && linesTmpMapper.selectGXByCode(zcLine.getZcode(),null,null)==null){
						linesTmpMapper.deleteByCode(zcLine);
						continue;
					}
				}
				
				rt.setRouteText(zcLine.getAdevName()+"("+zcLine.getAcode()+")");
				rt = SelectTopByTXs(zcLine.getAcode(),rt,zcLine);
				
				//下行
				rt.setRouteText(rt.getRouteText()+"<==>"+zcLine.getZdevName()+"("+zcLine.getZcode()+")");
				rt = SelectDownByTXs(zcLine.getZcode(),rt,zcLine);
			}
			rt.setAotherName(aotherName);	
			rt.setZotherName(zotherName);
			rt.setRouteName(aotherName + "<==>" + zotherName);
			rt.setIsSameDistrict(isSameDistrict);
			//if(!aotherName.equals(zotherName)) {
			listAll.add(rt);
			rt = null;
			aotherName = "";
			zotherName = "";
			isSameDistrict = "1";
			adev = "";
			zdev = "";
		}
		
		return listAll;
	}
	
	
	/**
	 * 分光器上行
	 * @author: YHT
	 * @date: 2017年9月4日 上午10:24:29 
	 * @Title: SelectTopByTX  
	 * @Description: TODO
	 * @param @param rt
	 * @param @param lt
	 * @param @return     
	 * @return RouteTmp   
	 * @throws
	 */
	private RouteTmp SelectTopByTX(RouteTmp rt,LinesTmp lt) {
		LinesTmp ltGX = linesTmpMapper.selectGXByCode(lt.getRouteCode(),null,null);
		
		if(ltGX == null) {
			aotherName = lt.getRouteNoteName();
			rt.setAdevName(aotherName.substring(0, aotherName.lastIndexOf("(")));
			return rt;
		}else {
			if("0".equals(ltGX.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			
			if(ltGX.getRouteCode() == null) {
				if(ltGX.getAdevId()==null || ltGX.getZdevId()==null) {
					aotherName = lt.getRouteNoteName();
					rt.setAdevName(aotherName.substring(0, aotherName.lastIndexOf("(")));
					return rt;
				}
				if(ltGX.getAcode()==null) {
					aotherName = ltGX.getAdevName();
					rt.setAdevName(aotherName);
					rt.setRouteText(ltGX.getAdevName() + "<==>" + rt.getRouteText());
				}else {
					aotherName = ltGX.getZdevName();
					rt.setAdevName(aotherName);
					rt.setRouteText(ltGX.getZdevName() + "<==>" + rt.getRouteText());
				}
				
			}else {
				rt.setRouteText(ltGX.getRouteNoteName() + "<==>" + rt.getRouteText());
				LinesTmp ltTX = linesTmpMapper.selectTXByCode(ltGX.getRouteCode(),null,null);
				
				if(ltTX == null) {
					aotherName = ltGX.getRouteNoteName();
					rt.setAdevName(aotherName.substring(0, aotherName.lastIndexOf("(")));
					return rt;
				}else {
					if("0".equals(ltTX.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					
					linesTmpMapper.deleteByCode(ltTX);
					if(ltTX.getRouteCode() == null) {
						if(ltTX.getAdevId()==null || ltTX.getZdevId()==null) {
							aotherName = ltGX.getRouteNoteName();
							rt.setAdevName(aotherName.substring(0, aotherName.lastIndexOf("(")));
							return rt;
						}
						if(ltTX.getAcode()==null) {
							aotherName = ltTX.getAdevName();
							rt.setAdevName(aotherName);
							rt.setRouteText(ltTX.getAdevName() + "<==>" + rt.getRouteText());
						}else {
							aotherName = ltTX.getZdevName();
							rt.setAdevName(aotherName);
							rt.setRouteText(ltTX.getZdevName() + "<==>" + rt.getRouteText());
						}
						
					}else {
						rt.setRouteText(ltTX.getRouteNoteName() + "<==>" + rt.getRouteText());
						rt = SelectTopByTX(rt,ltTX);
					}
				}
			}
			
		}
		
		
		
		return rt;
	}
	
	/**
	 * 分光器下行
	 * @author: YHT
	 * @date: 2017年9月4日 上午10:23:57 
	 * @Title: SelectTopByTX  
	 * @Description: TODO
	 * @param @param rt
	 * @param @param lt
	 * @param @return     
	 * @return RouteTmp   
	 * @throws
	 */
	private RouteTmp SelectDownByTX(RouteTmp rt,LinesTmp lt) {
		LinesTmp ltGX = linesTmpMapper.selectGXByCode(lt.getRouteCode(),null,null);
		
		if(ltGX == null) {
			zotherName = lt.getRouteNoteName();
			rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
			return rt;
		}else {
			if("0".equals(ltGX.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			
			if(ltGX.getRouteCode() == null) {
				if(ltGX.getAdevId()==null || ltGX.getZdevId()==null) {
					zotherName = lt.getRouteNoteName();
					rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
					return rt;
				}
				if(ltGX.getAcode()==null) {
					zotherName = ltGX.getAdevName();
					rt.setZdevName(zotherName);
					rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getAdevName());
				}else {
					zotherName = ltGX.getZdevName();
					rt.setZdevName(zotherName);
					rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getZdevName());
				}
				
			}else {
				rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getRouteNoteName());
				LinesTmp ltTX = linesTmpMapper.selectTXByCode(ltGX.getRouteCode(),null,null);
				
				if(ltTX == null) {
					zotherName = ltGX.getRouteNoteName();
					rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
					return rt;
				}else {
					if("0".equals(ltTX.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					
					linesTmpMapper.deleteByCode(ltTX);
					if(ltTX.getRouteCode() == null) {
						if(ltTX.getAdevId()==null || ltTX.getZdevId()==null) {
							zotherName = ltGX.getRouteNoteName();
							rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
							return rt;
						}
						if(ltTX.getAcode()==null) {
							zotherName = ltTX.getAdevName();
							rt.setZdevName(zotherName);
							rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getAdevName());
						}else {
							zotherName = ltTX.getZdevName();
							rt.setZdevName(zotherName);
							rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getZdevName());
						}
						
					}else {
						rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getRouteNoteName());
						rt = SelectDownByTX(rt,ltTX);
					}
				}
			}
			
		}
		
		
		
		return rt;
	}
	
	private RouteTmp SelectTopByTXs(String code,RouteTmp rt,LinesTmp lt) {
		LinesTmp ltGX = linesTmpMapper.selectGXByCode(code,null,null);
		
		if(ltGX == null) {
			if(code.equals(lt.getAcode())) {
				aotherName = lt.getAdevName()+"("+code+")";
				rt.setAdevName(lt.getAdevName());
			}else {
				aotherName = lt.getZdevName()+"("+code+")";
				rt.setAdevName(lt.getZdevName());
			}
			return rt;
		}else {
			if("0".equals(ltGX.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			
			if(ltGX.getRouteCode() == null) {
				if(ltGX.getAdevId()==null || ltGX.getZdevId()==null) {
					if(code.equals(lt.getAcode())) {
						aotherName = lt.getAdevName()+"("+code+")";
						rt.setAdevName(lt.getAdevName());
					}else {
						aotherName = lt.getZdevName()+"("+code+")";
						rt.setAdevName(lt.getZdevName());
					}
					return rt;
				}
				if(ltGX.getAcode()==null) {
					aotherName = ltGX.getAdevName();
					rt.setAdevName(aotherName);
					rt.setRouteText(ltGX.getAdevName() + "<==>" + rt.getRouteText());
				}else {
					aotherName = ltGX.getZdevName();
					rt.setAdevName(aotherName);
					rt.setRouteText(ltGX.getZdevName() + "<==>" + rt.getRouteText());
				}
				
			}else {
				rt.setRouteText(ltGX.getRouteNoteName() + "<==>" + rt.getRouteText());
				LinesTmp ltTX = linesTmpMapper.selectTXByCode(ltGX.getRouteCode(),null,null);
				
				if(ltTX == null) {
					aotherName = ltGX.getRouteNoteName();
					rt.setAdevName(aotherName.substring(0, aotherName.lastIndexOf("(")));
					return rt;
				}else {
					if("0".equals(ltTX.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					
					linesTmpMapper.deleteByCode(ltTX);
					if(ltTX.getRouteCode() == null) {
						if(ltTX.getAdevId()==null || ltTX.getZdevId()==null) {
							if(ltGX.getRouteCode().equals(ltGX.getAcode())) {
								aotherName = ltGX.getAdevName()+"("+code+")";
								rt.setAdevName(ltGX.getAdevName());
							}else {
								aotherName = ltGX.getZdevName()+"("+code+")";
								rt.setAdevName(ltGX.getZdevName());
							}
							return rt;
						}
						if(ltTX.getAcode()==null) {
							aotherName = ltTX.getAdevName();
							rt.setAdevName(aotherName);
							rt.setRouteText(ltTX.getAdevName() + "<==>" + rt.getRouteText());
						}else {
							aotherName = ltTX.getZdevName();
							rt.setAdevName(aotherName);
							rt.setRouteText(ltTX.getZdevName() + "<==>" + rt.getRouteText());
						}
						
					}else {
						rt.setRouteText(ltTX.getRouteNoteName() + "<==>" + rt.getRouteText());
						rt = SelectTopByTXs(ltTX.getRouteCode(),rt,ltTX);
					}
					
				}
			}
		}
		return rt;
	}
	
	private RouteTmp SelectDownByTXs(String code,RouteTmp rt,LinesTmp lt) {
		LinesTmp ltGX = linesTmpMapper.selectGXByCode(code,null,null);
		
		if(ltGX == null) {
			if(code.equals(lt.getAcode())) {
				zotherName = lt.getAdevName()+"("+code+")";
				rt.setZdevName(lt.getAdevName());
			}else {
				zotherName = lt.getZdevName()+"("+code+")";
				rt.setZdevName(lt.getZdevName());
			}
			return rt;
		}else {
			if("0".equals(ltGX.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			
			if(ltGX.getRouteCode() == null) {
				if(ltGX.getAdevId()==null || ltGX.getZdevId()==null) {
					if(code.equals(lt.getAcode())) {
						zotherName = lt.getAdevName()+"("+code+")";
						rt.setZdevName(lt.getAdevName());
					}else {
						zotherName = lt.getZdevName()+"("+code+")";
						rt.setZdevName(lt.getZdevName());
					}
					return rt;
				}
				if(ltGX.getAcode()==null) {
					zotherName = ltGX.getAdevName();
					rt.setZdevName(zotherName);
					rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getAdevName() );
				}else {
					zotherName = ltGX.getZdevName();
					rt.setZdevName(zotherName);
					rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getZdevName());
				}
				
			}else {
				rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getRouteNoteName());
				LinesTmp ltTX = linesTmpMapper.selectTXByCode(ltGX.getRouteCode(),null,null);
				
				if(ltTX == null) {
					zotherName = ltGX.getRouteNoteName();
					rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
					return rt;
				}else {
					if("0".equals(ltTX.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					
					linesTmpMapper.deleteByCode(ltTX);
					if(ltTX.getRouteCode() == null) {
						if(ltTX.getAdevId()==null || ltTX.getZdevId()==null) {
							zotherName = ltGX.getRouteNoteName();
							rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
							return rt;
						}
						if(ltTX.getAcode()==null) {
							zotherName = ltTX.getAdevName();
							rt.setZdevName(zotherName);
							rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getAdevName() );
						}else {
							zotherName = ltTX.getZdevName();
							rt.setZdevName(zotherName);
							rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getZdevName());
						}
						
					}else {
						rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getRouteNoteName());
						rt = SelectDownByTXs(ltTX.getRouteCode(),rt,ltTX);
					}
				}
			}
		}
		return rt;
	}

	@Resource(name="exportMapper")
	private TnExportMapper tnExportMapper;
	
	
	@Override
	public List<RouteTmp> exportZZPT() {
		// TODO Auto-generated method stub
		List<TnExport> list = null;
		List<RouteTmp> routeList =  new ArrayList<RouteTmp>();
		RouteTmp route = null;
		RouteTmp route2 = null;
		
		List<TnExport> PONlist = tnExportMapper.selectByparam("PON");
		tnExportMapper.deleteByParam("PON");
		
		String ponRoute = "";
		for(int i=0;i<PONlist.size();i++) {
			TnExport pon = null;
			if(i+1 == PONlist.size()) {
				pon = PONlist.get(i);
				if(ponRoute.equals(pon.getD())) {
					list.add(pon);
				}
				ponRoute="1";
			}else {
				pon = PONlist.get(i);
			}
			if("".equals(ponRoute)) {
				list = null;
				list = new ArrayList<TnExport>();
				ponRoute = pon.getD();
				list.add(pon);
			}else {
				if(ponRoute.equals(pon.getD())) {
					list.add(pon);
				}else {
					route = new RouteTmp();
					if(list.size()<2) {
						route.setIsSameDistrict("PON一根跳纤");
					}else {
						route.setIsSameDistrict("PON");
					}
					route.setAreaCode1("东平汇聚区");
						
					for(TnExport te : list) {
						route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
						route.setRouteName(te.getE());
						route.setAreaCode0(te.getA());
						route.setAreaCode2(te.getC());
						if(route.getRouteText() == null) {
							if("".equals(te.getI())) {
								route.setRouteText(te.getF()+" "+te.getG()+" "+te.getH());
							}else {
								route.setRouteText(te.getF()+" "+te.getG()+" "+te.getH()+"(no:"+te.getI()+")");
							}
						}else {
							if("".equals(te.getI())) {
								route.setRouteText(route.getRouteText()+"<==>"+te.getF()+" "+te.getG()+" "+te.getH());
							}else {
								route.setRouteText(route.getRouteText()+"<==>"+te.getF()+" "+te.getG()+" "+te.getH()+"(no:"+te.getI()+")");
							}
						}
							
					}
					routeList.add(route);
					route = null;
					list = new ArrayList<TnExport>();
					ponRoute = pon.getD();
					list.add(pon);
				}
			}
		}
			//PDH
			List<TnExport> PDHlist = tnExportMapper.selectByparam("PDH");
			tnExportMapper.deleteByParam("PDH");
			
			String pdhRoute = "";
			for(int j=0;j<PDHlist.size();j++) {
				TnExport pdh = null;
				if(j+1 == PDHlist.size()) {
					pdh = PDHlist.get(j);
					if(pdhRoute.equals(pdh.getD())) {
						list.add(pdh);
					}
					pdhRoute="1";
				}else {
					pdh = PDHlist.get(j);
				}
				if("".equals(pdhRoute)) {
					list = null;
					list = new ArrayList<TnExport>();
					pdhRoute = pdh.getD();
					list.add(pdh);
				}else {
					if(pdhRoute.equals(pdh.getD())) {
						list.add(pdh);
					}else {
						route = new RouteTmp();
						if(list.size()<2) {
							route.setIsSameDistrict("PDH一根跳纤");
						}else {
							route.setIsSameDistrict("PDH");
						}
						route.setAreaCode1("东平汇聚区");
							
						for(TnExport te : list) {
							route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
							route.setRouteName(te.getE());
							route.setAreaCode0(te.getA());
							route.setAreaCode2(te.getC());
							if(route.getRouteText() == null) {
								if("".equals(te.getI())) {
									route.setRouteText(te.getF()+" "+te.getG()+" "+te.getH());
								}else {
									route.setRouteText(te.getF()+" "+te.getG()+" "+te.getH()+"(no:"+te.getI()+")");
								}
							}else {
								if("".equals(te.getI())) {
									route.setRouteText(route.getRouteText()+"<==>"+te.getF()+" "+te.getG()+" "+te.getH());
								}else {
									route.setRouteText(route.getRouteText()+"<==>"+te.getF()+" "+te.getG()+" "+te.getH()+"(no:"+te.getI()+")");
								}
							}
								
						}
						routeList.add(route);
						route = null;
						list = new ArrayList<TnExport>();
						pdhRoute = pdh.getD();
						list.add(pdh);
					}
				}
			}
		
		
		
		//PTN
		List<TnExport> PTNlist = tnExportMapper.selectByparam("PTN");
		tnExportMapper.deleteByParam("PTN");
		
		List<TnExport> list1 = null;
		List<TnExport> list2 = null;
		String ptnRoute = "";
		for(int k=0;k<PTNlist.size();k++) {
			TnExport ptn = null;
			if(k+1 == PTNlist.size()) {
				ptn = PTNlist.get(k);
				if(ptnRoute.equals(ptn.getD())) {
					list.add(ptn);
				}
				ptnRoute="1";
			}else {
				ptn = PTNlist.get(k);
			}
			if("".equals(ptnRoute)) {
				list = new ArrayList<TnExport>();
				ptnRoute = ptn.getD();
				list.add(ptn);
			}else {
				if(ptnRoute.equals(ptn.getD())) {
					list.add(ptn);
				}else {
					route = new RouteTmp();
					route2 = new RouteTmp();
					
					if(list.size()<3) {
						route.setIsSameDistrict("PTN一根跳纤");
						route2.setIsSameDistrict("PTN一根跳纤");
					}else if(list.size()%2==1){
						route.setIsSameDistrict("PTN存在单根");
						route2.setIsSameDistrict("PTN存在单根");
					}else {
						route.setIsSameDistrict("PTN正常");
						route2.setIsSameDistrict("PTN正常");
					}
					route.setAreaCode1("东平汇聚区");
					route2.setAreaCode1("东平汇聚区");
					
					list1 = new ArrayList<TnExport>();
					list2 = new ArrayList<TnExport>();
					int ptnNum = 1;
					for(TnExport te : list) {
						if(ptnNum%2==1) {
							list1.add(te);
						}else {
							list2.add(te);
						}
						++ptnNum;
					}	
					TnExport cs = tnExportMapper.selectByRouteName(list1.get(0).getE());
					if(cs != null) {
						if(cs.getA() != null) {
							route.setTransmissionSection(cs.getA());
							route2.setTransmissionSection(cs.getA());
						}
						if(cs.getB() != null) {
							route.setTransmissionSystem(cs.getB());
							route2.setTransmissionSystem(cs.getB());
						}
						
					}
					
					for(TnExport te1 : list1) {	
						route.setRouteId(Long.parseLong(te1.getD().substring(0,te1.getD().indexOf("."))));
						route.setRouteName(te1.getE());
						route.setAreaCode0(te1.getA());
						route.setAreaCode2(te1.getC());
						if(route.getRouteText() == null) {
							if("".equals(te1.getI())) {
								route.setRouteText(te1.getF()+" "+te1.getG()+" "+te1.getH());
							}else {
								route.setRouteText(te1.getF()+" "+te1.getG()+" "+te1.getH()+"(no:"+te1.getI()+")");
							}
						}else {
							if("".equals(te1.getI())) {
								route.setRouteText(route.getRouteText()+"<==>"+te1.getF()+" "+te1.getG()+" "+te1.getH());
							}else {
								route.setRouteText(route.getRouteText()+"<==>"+te1.getF()+" "+te1.getG()+" "+te1.getH()+"(no:"+te1.getI()+")");
							}
						}
								
					}
					for(TnExport te2 : list2) {	
						route2.setRouteId(Long.parseLong(te2.getD().substring(0,te2.getD().indexOf("."))));
						route2.setRouteName(te2.getE());
						route2.setAreaCode0(te2.getA());
						route2.setAreaCode2(te2.getC());
						if(route2.getRouteText() == null) {
							if("".equals(te2.getI())) {
								route2.setRouteText(te2.getF()+" "+te2.getG()+" "+te2.getH());
							}else {
								route2.setRouteText(te2.getF()+" "+te2.getG()+" "+te2.getH()+"(no:"+te2.getI()+")");
							}
						}else {
							if("".equals(te2.getI())) {
								route2.setRouteText(route2.getRouteText()+"<==>"+te2.getF()+" "+te2.getG()+" "+te2.getH());
							}else {
								route2.setRouteText(route2.getRouteText()+"<==>"+te2.getF()+" "+te2.getG()+" "+te2.getH()+"(no:"+te2.getI()+")");
							}
						}
								
					}
					if(cs != null) {
						if(cs.getD() != null) {
							route.setRouteText(cs.getD()+"<==>"+route.getRouteText());
							route2.setRouteText(cs.getD()+"<==>"+route2.getRouteText());
						}
						if(cs.getF() != null) {
							route.setRouteText(route.getRouteText()+"<==>"+cs.getF());
							route2.setRouteText(route2.getRouteText()+"<==>"+cs.getF());
						}
					}
					routeList.add(route);
					routeList.add(route2);
					route = null;
					route2 = null;
					list = null;
					list1 = null;
					list2 = null;
					list = new ArrayList<TnExport>();
					ptnRoute = ptn.getD();
					list.add(ptn);
				}
			}
		}

		
		//SDH
			List<TnExport> SDHlist = tnExportMapper.selectByparam("SDH");
			tnExportMapper.deleteByParam("SDH");

			String sdhRoute = "";
			for(int n=0;n<SDHlist.size();n++) {
				TnExport sdh = null;
				if(n+1 == SDHlist.size()) {
					sdh = SDHlist.get(n);
					if(sdhRoute.equals(sdh.getD())) {
						list.add(sdh);
					}
					sdhRoute="1";
				}else {
					sdh = SDHlist.get(n);
				}
				if("".equals(sdhRoute)) {
					list = new ArrayList<TnExport>();
					sdhRoute = sdh.getD();
					list.add(sdh);
				}else {
					if(sdhRoute.equals(sdh.getD())) {
						list.add(sdh);
					}else {
						route = new RouteTmp();
						route2 = new RouteTmp();
							
						if(list.size()<3) {
							route.setIsSameDistrict("SDH一根跳纤");
							route2.setIsSameDistrict("SDH一根跳纤");
						}else if(list.size()%2==1){
							route.setIsSameDistrict("SDH存在单根");
							route2.setIsSameDistrict("SDH存在单根");
						}else {
							route.setIsSameDistrict("SDH正常");
							route2.setIsSameDistrict("SDH正常");
						}
						route.setAreaCode1("东平汇聚区");
						route2.setAreaCode1("东平汇聚区");
							
						list1 = new ArrayList<TnExport>();
						list2 = new ArrayList<TnExport>();
						int sdhNum = 1;
						for(TnExport te : list) {
							if(sdhNum%2==1) {
								list1.add(te);
							}else {
								list2.add(te);
							}
							++sdhNum;
						}	
						
						TnExport cs = tnExportMapper.selectByRouteName(list1.get(0).getE());
						if(cs != null) {
							if(cs.getA() != null) {
								route.setTransmissionSection(cs.getA());
								route2.setTransmissionSection(cs.getA());
							}
							if(cs.getB() != null) {
								route.setTransmissionSystem(cs.getB());
								route2.setTransmissionSystem(cs.getB());
							}
							
						}
						
						for(TnExport te1 : list1) {	
							route.setRouteId(Long.parseLong(te1.getD().substring(0,te1.getD().indexOf("."))));
							route.setRouteName(te1.getE());
							route.setAreaCode0(te1.getA());
							route.setAreaCode2(te1.getC());
							if(route.getRouteText() == null) {
								if("".equals(te1.getI())) {
									route.setRouteText(te1.getF()+" "+te1.getG()+" "+te1.getH());
								}else {
									route.setRouteText(te1.getF()+" "+te1.getG()+" "+te1.getH()+"(no:"+te1.getI()+")");
								}
							}else {
								if("".equals(te1.getI())) {
									route.setRouteText(route.getRouteText()+"<==>"+te1.getF()+" "+te1.getG()+" "+te1.getH());
								}else {
									route.setRouteText(route.getRouteText()+"<==>"+te1.getF()+" "+te1.getG()+" "+te1.getH()+"(no:"+te1.getI()+")");
								}
							}
									
						}
						for(TnExport te2 : list2) {	
							route2.setRouteId(Long.parseLong(te2.getD().substring(0,te2.getD().indexOf("."))));
							route2.setRouteName(te2.getE());
							route2.setAreaCode0(te2.getA());
							route2.setAreaCode2(te2.getC());
							if(route2.getRouteText() == null) {
								if("".equals(te2.getI())) {
									route2.setRouteText(te2.getF()+" "+te2.getG()+" "+te2.getH());
								}else {
									route2.setRouteText(te2.getF()+" "+te2.getG()+" "+te2.getH()+"(no:"+te2.getI()+")");
								}
							}else {
								if("".equals(te2.getI())) {
									route2.setRouteText(route2.getRouteText()+"<==>"+te2.getF()+" "+te2.getG()+" "+te2.getH());
								}else {
									route2.setRouteText(route2.getRouteText()+"<==>"+te2.getF()+" "+te2.getG()+" "+te2.getH()+"(no:"+te2.getI()+")");
								}
							}
									
						}
						if(cs != null) {
							if(cs.getD() != null) {
								route.setRouteText(cs.getD()+"<==>"+route.getRouteText());
								route2.setRouteText(cs.getD()+"<==>"+route2.getRouteText());
							}
							if(cs.getF() != null) {
								route.setRouteText(route.getRouteText()+"<==>"+cs.getF());
								route2.setRouteText(route2.getRouteText()+"<==>"+cs.getF());
							}
						}
						
						
						routeList.add(route);
						routeList.add(route2);
						route = null;
						route2 = null;
						list = null;
						list1 = null;
						list2 = null;
						list = new ArrayList<TnExport>();
						sdhRoute = sdh.getD();
						list.add(sdh);
					}
				}
			}
		
		//其他
			List<TnExport> QTlist = tnExportMapper.selectByparams("");
			
			String qtRoute = "";
			for(int m=0;m<QTlist.size();m++) {
				TnExport qt = null;
				if(m+1 == QTlist.size()) {
					qt = QTlist.get(m);
					if(qtRoute.equals(qt.getD())) {
						list.add(qt);
					}
					qtRoute="1";
				}else {
					qt = QTlist.get(m);
				}
				if("".equals(qtRoute)) {
					list = null;
					list = new ArrayList<TnExport>();
					qtRoute = qt.getD();
					list.add(qt);
				}else {
					if(qtRoute.equals(qt.getD())) {
						list.add(qt);
					}else {
						route = new RouteTmp();
						if(list.size()<2) {
							route.setIsSameDistrict("其他一根跳纤");
						}else {
							route.setIsSameDistrict("其他");
						}
						route.setAreaCode1("东平汇聚区");
							
						for(TnExport te : list) {
							route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
							route.setRouteName(te.getE());
							route.setAreaCode0(te.getA());
							route.setAreaCode2(te.getC());
							if(route.getRouteText() == null) {
								if("".equals(te.getI())) {
									route.setRouteText(te.getF()+" "+te.getG()+" "+te.getH());
								}else {
									route.setRouteText(te.getF()+" "+te.getG()+" "+te.getH()+"(no:"+te.getI()+")");
								}
							}else {
								if("".equals(te.getI())) {
									route.setRouteText(route.getRouteText()+"<==>"+te.getF()+" "+te.getG()+" "+te.getH());
								}else {
									route.setRouteText(route.getRouteText()+"<==>"+te.getF()+" "+te.getG()+" "+te.getH()+"(no:"+te.getI()+")");
								}
							}
								
						}
						routeList.add(route);
						route = null;
						list = new ArrayList<TnExport>();
						qtRoute = qt.getD();
						list.add(qt);
					}
				}
		
			}
//		
//		List<TnExport> PTNlist = tnExportMapper.selectByparam("PTN");
//		
//		List<TnExport> SDHlist = tnExportMapper.selectByparam("SDH");
		
		return routeList;
	}

	/**
	 * 
	* @Title: queryExport1 
	* @Description: TODO(导出光交箱光路+文本路由) 
	* @param @param orgId
	* @param @param areaCode1
	* @param @return    入参
	* @return List<RouteBean>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月22日 上午11:08:00 
	* @version V1.0
	 */
	@Override
	public List<RouteBean> queryExport1(Long orgId, String areaCode1) {
		List<RouteBean>list=routeMapper.queryAllExportMap1(orgId,areaCode1);
		for (int i = 0; i < list.size(); i++) {
			Route route = routeMapper.queryPort01(list.get(i).getPort01());
			if(route!=null) {
				if(route.getRoutetext()!=null && !"".equals(route.getRoutetext())) {
					list.get(i).setRoutetext(route.getRoutetext());
					list.get(i).setLightType(LightPathType(route.getRoutetext()));//判断光路类型
				}
			}
		}
		return list;
	}

	/**
	 * 
	* @Title: queryAllCablint1 
	* @Description: TODO(导出机柜光路+机房+文本路由) 
	* @param @param orgId
	* @param @param areaCode1
	* @param @return    入参
	* @return List<RouteBean>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月22日 上午10:23:14 
	* @version V1.0
	 */
	@Override
	public List<RouteBean> queryAllCablint1(Long orgId, String areaCode1) {
		List<RouteBean> list=routeMapper.queryAllCablint1(orgId,areaCode1);
		for (int i = 0; i < list.size(); i++) {
			Route route = routeMapper.queryPort01(list.get(i).getPort01());
			if(route!=null) {
				if(route.getRoutetext()!=null && !"".equals(route.getRoutetext())) {
					list.get(i).setRoutetext(route.getRoutetext());
					list.get(i).setLightType(LightPathType(route.getRoutetext()));//判断光路类型
				}
			}
			
			Route r = routeMapper.queryPort01(list.get(i).getPort01());
			if(r!=null) {
				if(r.getRoutetext()!=null && !"".equals(r.getRoutetext())) {
					list.get(i).setRoutetext(r.getRoutetext());
				}
			}
		}
		return list;
	}
	
	/**
	 * 筛选光路类型工具类
	 * @param lightName
	 * @return
	 */
	public static String LightPathType(String lightName) {
		String lightType = "";
		String Adev = "";
		String Zdev = "";
		String Mdev = "";
		Pattern pattern = Pattern.compile("[0-9]"); 
		if(lightName == null) {
			return "";
		}else {
			Adev = lightName.substring(0,lightName.indexOf("<==>"));
			Zdev = lightName.substring(lightName.lastIndexOf("<==>"));
			Mdev = lightName.substring(lightName.indexOf("<==>"),lightName.lastIndexOf("<==>")+1);
		}
		//光路两端是设备
		if(Adev.indexOf("PTN")!=-1 && Zdev.indexOf("PTN")!=-1) {
			lightType = "PTN<==>PTN";
		}else if(Adev.indexOf("SDH")!=-1 && Zdev.indexOf("SDH")!=-1) {
			lightType =  "SDH<==>SDH";
		//OLT分光器/末端站点
		}else if(((Adev.indexOf("OLT")!=-1 || Adev.indexOf("PON")!=-1 || Adev.indexOf("5680T")!=-1 || Adev.indexOf("5683T")!=-1 || Adev.indexOf("ZX")!=-1) && Zdev.indexOf("FG")!=-1)
 					|| ((Zdev.indexOf("OLT")!=-1 || Zdev.indexOf("PON")!=-1 || Zdev.indexOf("5680T")!=-1 || Zdev.indexOf("5683T")!=-1 || Zdev.indexOf("ZX")!=-1) && Adev.indexOf("FG")!=-1)) {
			lightType =  "OLT<==>分光器";
		//BBUODF
		}else if((Adev.indexOf("BBU")!=-1 && Zdev.indexOf("ODF")!=-1) 
				|| (Zdev.indexOf("BBU")!=-1 && Adev.indexOf("ODF")!=-1) ) {
			lightType =  "BBU<==>ODF";
		}else if((Adev.indexOf("BBU")!=-1 && Zdev.indexOf("RRU")!=-1) 
				|| (Zdev.indexOf("BBU")!=-1 && Adev.indexOf("RRU")!=-1)) {
			lightType =  "BBU<==>RRU";
		}else if(((Adev.indexOf("OLT")!=-1 || Adev.indexOf("PON")!=-1 || Adev.indexOf("5680T")!=-1 || Adev.indexOf("5683T")!=-1 || Adev.indexOf("ZX")!=-1 || Adev.indexOf("PTN")!=-1 || Adev.indexOf("SDH")!=-1) && Zdev.indexOf("ODF")!=-1)
				|| ((Zdev.indexOf("OLT")!=-1 || Zdev.indexOf("PON")!=-1 || Zdev.indexOf("5680T")!=-1 || Zdev.indexOf("5683T")!=-1 || Zdev.indexOf("ZX")!=-1 || Zdev.indexOf("PTN")!=-1 || Zdev.indexOf("SDH")!=-1) && Adev.indexOf("ODF")!=-1)) {
			lightType =  "(PTN/SDH/OLT)<==>ODF";
		}else if(Adev.indexOf("ODF")!=-1 && Zdev.indexOf("ODF")!=-1) {
			lightType =  "ODF<==>ODF";
		}else if(lightName.indexOf("虚拟")!=-1) {
			lightType =  "包含虚拟信息";
		//光路两端任意一端没有明确的端口编号
		}else if(pattern.matcher(Adev.substring(Adev.length()-2,Adev.length()-1)).matches() || pattern.matcher(Zdev.substring(Zdev.length()-2,Zdev.length()-1)).matches()) {
			lightType =  "光路两端任意一端没有明确的端口编号";
		}else if((Adev.indexOf("ODF")!=-1 && Zdev.indexOf("FG")!=-1) 
				|| (Zdev.indexOf("ODF")!=-1 && Adev.indexOf("FG")!=-1)) {
			lightType =  "ODF<==>分光器";
		//中间是设备端口
		}else if(Mdev.indexOf("PTN")!=-1 || Mdev.indexOf("SDH")!=-1 || Mdev.indexOf("BBU")!=-1 || Mdev.indexOf("RRU")!=-1 || Mdev.indexOf("PON")!=-1 || Mdev.indexOf("OLT")!=-1 || Mdev.indexOf("5680T")!=-1 || Mdev.indexOf("5683T")!=-1 || Mdev.indexOf("ZX")!=-1) {
			lightType =  "中间是设备端口";
		}else if(((Adev.indexOf("#")!=-1 || Adev.indexOf("井")!=-1) && Zdev.indexOf("ODF")!=-1) 
				|| ((Zdev.indexOf("#")!=-1 || Zdev.indexOf("井")!=-1) && Adev.indexOf("ODF")!=-1) ) {
			lightType =  "一端是井，一端无设备";
		}else {
			lightType =  "其他";
		}
		return lightType;
	}
	/**
	 * zhouyu 18/1/4 改 添加光缆状态
	 */
	@Override
	public PageBean querySectRouteList(SectRouteBean bean, Users user, PageBean pb) {
		// TODO Auto-generated method stub
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("routeBean", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		List<SectRouteBean> list = cableSectionMapper.queryByConditionMap(conditionMap);
		if(list.size()>0) {
			for (SectRouteBean sectRouteBean : list) {
				String sectState = sectRouteBean.getSectState();
				switch (sectState) {
				case "0":
					sectRouteBean.setSectState("未核对");
					break;
				case "1":
					sectRouteBean.setSectState("正常");
					break;
				case "2":
					sectRouteBean.setSectState("新增");
					break;
				case "3":
					sectRouteBean.setSectState("修改");
					break;
				case "4":
					sectRouteBean.setSectState("资管删除");
					break;
				case "5":
					sectRouteBean.setSectState("新增删除");
					break;
				}
			}
		}
		pb.setList(list);	
		pb.setRows(cableSectionMapper.queryCountByConditionMap(conditionMap));//;
		return pb;
	}
	//修改表中的数据n_route中的is_entering从0->1 佟盛玮
	@Override
	public int updateisentering(SectRouteBean record) {
		return cableSectionMapper.updateisentering(record);
	}
	@Override
	public int opticalcable(SectRoute sectRoute) {
		return cableSectionMapper.opticalcable(sectRoute);
	}
	//tn_task_log表中插入数据  佟盛玮
	@Override
	public int addisentering(SectRouteBean record) {
		return cableSectionMapper.addisentering(record);
	}
	//tn_task_log表中插入数据  佟盛玮	@Override
	public int insertopticalcables(SectRouteBean record) {
		return cableSectionMapper.insertopticalcables(record);
	}
	//查看数据是否插入成功 佟盛玮
	@Override
	public int getAnyPortisentering(Long routeId) {
		int ret = 0;
		List<Lines> portsList=cableSectionMapper.selectisentering(routeId);
		ret=portsList.size();
		return ret;
	}
	//查看数据是否插入成功 佟盛玮
	@Override
	public int getAnyPortopticalcables(SectRouteBean record) {
		int ret = 0;
		List<Lines> portsList=cableSectionMapper.getAnyPortopticalcables(record);
		ret=portsList.size();
		return ret;
	}

	@Override
	public SectRouteResult selectByRouteA(SectRoute sectRoute) {
		// TODO Auto-generated method stub
		List<SectRoute> list = routeMapper.selectByRoute(sectRoute); 
		
		SectRouteResult result = new SectRouteResult();
		List<SectRouteGroup> srgList = new ArrayList<SectRouteGroup>();
		SectRouteGroup srg = null;
		List<RouteText> rtList = new ArrayList<RouteText>();
		RouteText rt = null;
		List<SectRouteDisc> srdList = null;
		List<SectRouteDisc> srdList_cr = null;
		SectRouteDisc srd = null;
		List<SectRouteFiber> srfList = null;
		SectRouteFiber srf = null;
		//实例化空盘
		SectRouteDisc srd1 = null;
		String side = null;//分组编码
		String disc = null;//盘编码
		String port = null;//端子编码
		Integer discNum = null;
		
		Route route = null;
		int i = 0;
		for(SectRoute sr : list) {
			++i;
			if(sr.getAcode() != null) {
				System.out.println(sr.getAcode());
				//插入分组
				String sideTmp = sr.getAcode().substring(sr.getAcode().indexOf("-")+1,sr.getAcode().lastIndexOf("-")-3);
				if(!sideTmp.equals(side)) {
					if(srg != null) {
						if(srdList != null && discNum!=null) {
							srd.setSrfList(srfList);
							srdList.add(srd);
							srd=null;
							srdList_cr = new ArrayList<SectRouteDisc>();
							//插入空盘
							for(int j=0;j<discNum;j++) {
								srd1 = new SectRouteDisc();
								srd1.setDiscNum(j+1+"");
								for(SectRouteDisc srd_cr : srdList) {
									if((srd_cr.getDiscNum()!=null && !"".equals(srd_cr.getDiscNum())) && (j+1)==Integer.parseInt(srd_cr.getDiscNum())) {
										srd1 = srd_cr;
									}
								}
								srdList_cr.add(srd1);
								srd1 = null;
							}
						}
						if(srdList_cr != null) {
							srg.setSrdList(srdList_cr);
							srdList_cr = null;
						}else {
							srg.setSrdList(srdList);
						}
						srdList = null;
						srgList.add(srg);
					}
					srg = null;
					srg = new SectRouteGroup();
					side = sideTmp;
					discNum = routeMapper.selectDiscNumBydev(sr.getAdevId(),side);
					srg.setDiscNum(discNum);
					srg.setSide(side);
					
					srdList = new ArrayList<SectRouteDisc>();
					disc = null;
					port = null;
				}
				//插入盘
				String discTmp = sr.getAcode().substring(sr.getAcode().lastIndexOf("-")-2,sr.getAcode().lastIndexOf("-"));
				if(!discTmp.equals(disc)) {
					if(srd != null) {
						srd.setSrfList(srfList);
						srfList = null;
						srdList.add(srd);
					}
					disc = discTmp;
					srd = null;
					srd = new SectRouteDisc();
					srd.setDiscNum(disc);
					srfList = new ArrayList<SectRouteFiber>();
					port = null;
				}
				//插入端子
				port = sr.getAcode().substring( sr.getAcode().lastIndexOf("-")+1);
				srf=null;
				srf = new SectRouteFiber();
				srf.setFiberPort(port+"("+sr.getFiberNo()+")");
				srf.setPort01(sr.getAcode());
				route=routeMapper.queryCodes(sr.getAcode());
				if(route != null) {
					
					srf.setIsflag(linesTmpMapper.selectByCodeA(sr.getAcode()));
					//插入光路
					rt = new RouteText();
					rt.setSideDisc(sr.getAcode().substring(sr.getAcode().indexOf("-")+1));
					rt.setRouteText(route.getCodes());
					rt.setRouteId(route.getRouteId());
					rt.setIsentering(route.getIsentering());
					//rt.setIs_entering(route.getIs_entering());
					//插入光路id
					rt.setOrgid(route.getOrgId());					
					rt.setAreacode1(route.getAreaCode1());
				
					rtList.add(rt);
					rt = null;
					srf.setRouteText(route.getCodes());
				}else {
					srf.setIsflag(0);
				}
				srfList.add(srf);
				route = null;
			}else {
				if(i == list.size() && srg!=null){
					System.out.println("-----------");
					srd.setSrfList(srfList);
					srdList.add(srd);
					if(srdList != null && discNum!=null) {
						srdList_cr = new ArrayList<SectRouteDisc>();
						//插入空盘
						for(int j=0;j<discNum;j++) {
							srd1 = new SectRouteDisc();
							srd1.setDiscNum(j+1+"");
							for(SectRouteDisc srd_cr : srdList) {
								if((srd_cr.getDiscNum()!=null && !"".equals(srd_cr.getDiscNum())) && (j+1)==Integer.parseInt(srd_cr.getDiscNum())) {
									srd1 = srd_cr;
								}
							}
							srdList_cr.add(srd1);
							srd1 = null;
						}
					}
					if(srdList_cr != null) {
						srg.setSrdList(srdList_cr);
						srdList_cr = null;
					}else {
						srg.setSrdList(srdList);
					}
					srgList.add(srg);
					
				}
				continue;
			}
			System.out.println(i+"----"+list.size());
			//最后插入
			if(i == list.size() && srg!=null){
				System.out.println("-----------");
				srd.setSrfList(srfList);
				srdList.add(srd);
				if(srdList != null && discNum!=null) {
					srdList_cr = new ArrayList<SectRouteDisc>();
					//插入空盘
					for(int j=0;j<discNum;j++) {
						srd1 = new SectRouteDisc();
						srd1.setDiscNum(j+1+"");
						for(SectRouteDisc srd_cr : srdList) {
							if((srd_cr.getDiscNum()!=null && !"".equals(srd_cr.getDiscNum())) && (j+1)==Integer.parseInt(srd_cr.getDiscNum())) {
								srd1 = srd_cr;
							}
						}
						srdList_cr.add(srd1);
						srd1 = null;
					}
				}
				if(srdList_cr != null) {
					srg.setSrdList(srdList_cr);
					srdList_cr = null;
				}else {
					srg.setSrdList(srdList);
				}
				srgList.add(srg);
			}
		}
		if(list != null && list.size()>0) {
			result.setAllFiberNo(list.size());
			result.setDevName(list.get(0).getAdevName());
			result.setSrgList(srgList);
			result.setRouteList(rtList);
		}
		return result;
	}
	
	@Override
	public SectRouteResult selectByRouteZ(SectRoute sectRoute) {
		// TODO Auto-generated method stub
		List<SectRoute> list = routeMapper.selectByRoute(sectRoute); 
		
		SectRouteResult result = new SectRouteResult();
		List<SectRouteGroup> srgList = new ArrayList<SectRouteGroup>();
		SectRouteGroup srg = null;
		List<RouteText> rtList = new ArrayList<RouteText>();
		RouteText rt = null;
		List<SectRouteDisc> srdList = null;
		List<SectRouteDisc> srdList_cr = null;
		SectRouteDisc srd = null;
		List<SectRouteFiber> srfList = null;
		SectRouteFiber srf = null;
		//实例化空盘
		SectRouteDisc srd1 = null;
		String side = null;//分组编码
		String disc = null;//盘编码
		String port = null;//端子编码
		Integer discNum = null;
		
		Route route = null;
		int i = 0;
		for(SectRoute sr : list) {
			++i;
			if(sr.getZcode() != null) {
				System.out.println(sr.getZcode());
				//插入分组
				String sideTmp = sr.getZcode().substring(sr.getZcode().indexOf("-")+1,sr.getZcode().lastIndexOf("-")-3);
				if(!sideTmp.equals(side)) {
					if(srg != null) {
						if(srdList != null && discNum!=null) {
							srd.setSrfList(srfList);
							srdList.add(srd);
							srd=null;
							srdList_cr = new ArrayList<SectRouteDisc>();
							//插入空盘
							for(int j=0;j<discNum;j++) {
								srd1 = new SectRouteDisc();
								srd1.setDiscNum(j+1+"");
								for(SectRouteDisc srd_cr : srdList) {
									if((srd_cr.getDiscNum()!=null && !"".equals(srd_cr.getDiscNum())) && (j+1)==Integer.parseInt(srd_cr.getDiscNum())) {
										srd1 = srd_cr;
									}
								}
								srdList_cr.add(srd1);
								srd1 = null;
							}
						}
						if(srdList_cr != null) {
							srg.setSrdList(srdList_cr);
							srdList_cr = null;
						}else {
							srg.setSrdList(srdList);
						}
						srdList = null;
						srgList.add(srg);
					}
					srg = null;
					srg = new SectRouteGroup();
					side = sideTmp;
					discNum = routeMapper.selectDiscNumBydev(sr.getZdevId(),side);
					srg.setDiscNum(discNum);
					srg.setSide(side);
					
					srdList = new ArrayList<SectRouteDisc>();
					disc = null;
					port = null;
				}
				//插入盘
				String discTmp = sr.getZcode().substring(sr.getZcode().lastIndexOf("-")-2,sr.getZcode().lastIndexOf("-"));
				if(!discTmp.equals(disc)) {
					if(srd != null) {
						srd.setSrfList(srfList);
						srfList = null;
						srdList.add(srd);
					}
					disc = discTmp;
					srd = null;
					srd = new SectRouteDisc();
					srd.setDiscNum(disc);
					srfList = new ArrayList<SectRouteFiber>();
					port = null;
				}
				//插入端子
				port = sr.getZcode().substring( sr.getZcode().lastIndexOf("-")+1);
				srf=null;
				srf = new SectRouteFiber();
				srf.setFiberPort(port+"("+sr.getFiberNo()+")");
				srf.setPort01(sr.getZcode());
				//查询全部光路中是否存在包含这个点的光路
				route=routeMapper.queryRouteByCode("",sr.getZcode());
				if(route != null) {
					srf.setIsflag(linesTmpMapper.selectByCodeZ(sr.getZcode()));
					//插入光路
					rt = new RouteText();
					rt.setSideDisc(sr.getZcode().substring(sr.getZcode().indexOf("-")+1));
					rt.setRouteText(route.getCodes());
					rt.setRouteId(route.getRouteId());
					rt.setIsentering(route.getIsentering());
					//插入光路id
					rt.setOrgid(route.getOrgId());					
					rt.setAreacode1(route.getAreaCode1());
					rtList.add(rt);
					rt = null;
					srf.setRouteText(route.getCodes());
				}else {
					srf.setIsflag(0);
				}
				srfList.add(srf);
				route = null;
			}else {
				if(i == list.size() && srg!=null){
					System.out.println("-----------");
					srd.setSrfList(srfList);
					srdList.add(srd);
					if(srdList != null && discNum!=null) {
						srdList_cr = new ArrayList<SectRouteDisc>();
						//插入空盘
						for(int j=0;j<discNum;j++) {
							srd1 = new SectRouteDisc();
							srd1.setDiscNum(j+1+"");
							for(SectRouteDisc srd_cr : srdList) {
								if((srd_cr.getDiscNum()!=null && !"".equals(srd_cr.getDiscNum())) && (j+1)==Integer.parseInt(srd_cr.getDiscNum())) {
									srd1 = srd_cr;
								}
							}
							srdList_cr.add(srd1);
							srd1 = null;
						}
					}
					if(srdList_cr != null) {
						srg.setSrdList(srdList_cr);
						srdList_cr = null;
					}else {
						srg.setSrdList(srdList);
					}
					srgList.add(srg);
					
				}
				continue;
			}
			System.out.println(i+"----"+list.size());
			//最后插入
			if(i == list.size() && srg!=null){
				System.out.println("-----------");
				srd.setSrfList(srfList);
				srdList.add(srd);
				if(srdList != null && discNum!=null) {
					srdList_cr = new ArrayList<SectRouteDisc>();
					//插入空盘
					for(int j=0;j<discNum;j++) {
						srd1 = new SectRouteDisc();
						srd1.setDiscNum(j+1+"");
						for(SectRouteDisc srd_cr : srdList) {
							if((srd_cr.getDiscNum()!=null && !"".equals(srd_cr.getDiscNum())) && (j+1)==Integer.parseInt(srd_cr.getDiscNum())) {
								srd1 = srd_cr;
							}
						}
						srdList_cr.add(srd1);
						srd1 = null;
					}
				}
				if(srdList_cr != null) {
					srg.setSrdList(srdList_cr);
					srdList_cr = null;
				}else {
					srg.setSrdList(srdList);
				}
				srgList.add(srg);
			}
		}
		if(list != null) {
			result.setAllFiberNo(list.size());
			result.setDevName(list.get(0).getZdevName());
			result.setSrgList(srgList);
			result.setRouteList(rtList);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
//		String a = "GJ3356-01-01-01";
//		System.out.println(a.substring(a.indexOf("-")+1,a.lastIndexOf("-")-3));
//		System.out.println(a.substring(a.lastIndexOf("-")-3));
//		System.out.println(a.substring(a.indexOf("-")+1).substring(0,a.indexOf("-")-1));
//		
//		System.out.println(a.substring(a.lastIndexOf("-")-2,a.lastIndexOf("-")));
//		
//		System.out.println(a.substring(a.lastIndexOf("-")+1));
		String b ="01";
		System.out.println(Integer.parseInt(b));
	}

	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<RouteZF> queryList(Long orgId,String areaCode) {
		return routeMapper.queryList(orgId,areaCode);
	}

	/**
	 * 
	* @Title: exportLightWane 
	* @Description: 导出光衰状态 
	* @param @param orgId
	* @param @param areaCode1
	* @param @return    
	* @return List<RouteBean>
	* @author liucanghai 
	* @throws
	* @date 2017年12月26日 上午9:16:42 
	* @version V1.0
	 */
	@Override
	public List<RouteBean> exportLightWaneGJX(Long orgId, String areaCode1) {
		return routeMapper.exportLightWaneGJX(orgId,areaCode1);
	}

	/**
	 * 
	* @Title: exportLightWaneCalint 
	* @Description: 导出光衰状态 
	* @param @param orgId
	* @param @param areaCode1
	* @param @return    
	* @return List<RouteBean>
	* @author liucanghai 
	* @throws
	* @date 2017年12月26日 上午9:16:42 
	* @version V1.0
	 */
	@Override
	public List<RouteBean> exportLightWaneCalint(Long orgId, String areaCode1) {
		return routeMapper.exportLightWaneCalint(orgId,areaCode1);
	}

	/**
	 * 
	* @Title: queryRouteTextForCode 
	* @Description: 根据端口编码查询光路文本路由 
	* @param @param code
	* @param @return    
	* @return Route
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午3:04:33 
	* @version V1.0
	 */
	@Override
	public Route queryRouteTextForCode(String code) {
		return routeMapper.queryRouteTextForCode(code);
	}

	
	@Override
	public List<RouteBean> testDT(Long orgId, String areaCode1) {
		return routeMapper.testDT(orgId,areaCode1);
	}
}
