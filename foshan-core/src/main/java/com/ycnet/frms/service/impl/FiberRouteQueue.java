package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.FrmsException;
import com.ycnet.core.util.FiberRoutesUtils;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.RouteNode;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.service.FiberRouteService;
import com.ycnet.frms.service.RouteService;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FiberRoute;
import com.ycnet.frms.vo.FiberRouteNode;

@Service
public class FiberRouteQueue   {

	@Resource
	private LinesMapper linesMapper;
	
	@Resource
	private FiberRouteService fiberRouteService;
	
	@Resource
	private RouteService routeService;
	
	@Resource 
	private FacilityMapper facilityMapper;
	
	private static Map<Long,Facility> ownerFacilitys = new HashMap<Long,Facility>();
	
	private  ConcurrentLinkedQueue<FiberRoute> q  =new  ConcurrentLinkedQueue<FiberRoute>();
	
	private int ok = 0;
	
	public void init(Long orgId){
		ownerFacilitys.clear();
		FacilityConditionBean param = new FacilityConditionBean();
		param.setOrgId(orgId);
		List<Facility> list =facilityMapper.queryByConditionBean(param);
		for(Facility f :list){
			ownerFacilitys.put(f.getDevId(), f);
		}
	}
	
	public void  selectFiber(Long orgId,String areaCode){
		List<String> portsList = linesMapper.selectAlonePort(orgId,areaCode,null,null);
		if(portsList==null||portsList.size()==0){
			throw new FrmsException("无法找到可以生成的光路。");
		}
		for(String port :portsList)
		{
			try {
				List<Lines> allLines =  linesMapper.selectRelationLines(port);
				FiberRoutesUtils routeUtils = new FiberRoutesUtils();
				FiberRoute fr = routeUtils.startRoute(port, allLines);
				q.add(fr);
				//q.offer(fr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		synchronized (this) {
			ok = 1;
		}
	}
	
	public void saveFiber(Long orgId){
		while(true){
			FiberRoute fiberRoute = q.poll();
			if (fiberRoute==null&&ok==1){
				break;
			}else if(fiberRoute==null&&ok==0){
				continue;
			}
			try
			{
			getFiberRouteText(orgId, fiberRoute);
			}catch(Exception e){
				
			}
		}
	}
	
	@Transactional
	public int getFiberRouteText(Long orgId, FiberRoute fiberRoute) {
		List<LinkedList<FiberRouteNode>> list = fiberRoute.getHalfRoutes();
		
		for(LinkedList<FiberRouteNode> nodeList:list)
		{
			StringBuffer  devIds =new StringBuffer(""),codes=new StringBuffer(""),fibers=new StringBuffer();
			Route route = new Route();
			route.setAcode(nodeList.getFirst().getCode());
			route.setAdevId(nodeList.getFirst().getDevId());
			//Facility first = facilityMapper.selectByPrimaryKey(nodeList.getFirst().getDevId());
			Facility first = ownerFacilitys.get(nodeList.getFirst().getDevId());
			if(first!=null)
				route.setAreaCode1(first.getAreaCode1());
			
			Long otherDevId = null;
			Facility f = null;
			
			Lines linea = linesMapper.selectAllLineWithOneNull(route.getAcode());
			if (linea!=null)
			{
				otherDevId = route.getAcode().equals(linea.getAcode())?linea.getZdevId():linea.getAdevId();
				if(otherDevId!=null &&(f = ownerFacilitys.get(otherDevId))!=null)
				{
					route.setAotherName(f.getDevName());
					
				}else
				{
					route.setAotherName(linea.getUnknownPointName());
				}
			}
			route.setZcode(nodeList.getLast().getCode());
			route.setZdevId(nodeList.getLast().getDevId());
			
			Lines linez = linesMapper.selectAllLineWithOneNull(route.getZcode());
			if (linez!=null){
				otherDevId = route.getZcode().equals(linez.getAcode())?linez.getZdevId():linez.getAdevId();
				if(otherDevId!=null &&(f = ownerFacilitys.get(otherDevId))!=null)
				{
					route.setZotherName(f.getDevName());
					
				}else
				{
					route.setZotherName(linea.getUnknownPointName());
				}
			}
				
			
			route.setRouteName(StringUtils.concatRouteName(route.getAcode(), route.getZcode()));
			route.setCreateTime(new Date());
			
			
			for(int i = 0;i<nodeList.size();i++)
			{
				FiberRouteNode node = nodeList.get(i);
				devIds.append(",").append(node.getDevId());
				codes.append(",").append(node.getCode());
				fibers.append(",").append(node.getFiberNo()==null?"":node.getFiberNo());
			}
			route.setOrgId(orgId);
			route.setDevIds(devIds.toString());
			route.setCodes(codes.toString());
			route.setFibers(fibers.toString());
			routeService.insert(route);
			for(int i = 0;i<nodeList.size();i++)
			{
				FiberRouteNode node = nodeList.get(i);
				RouteNode routeNode = new RouteNode();
				routeNode.setCode(node.getCode());
				routeNode.setDevId(node.getDevId());
				routeNode.setFiberNo( node.getFiberNo()==null?null:node.getFiberNo().intValue());
				routeNode.setNodeNo(i + 1);
				routeNode.setRouteId(route.getRouteId());
				routeNode.setSectId(node.getSectId());
				routeService.insert(routeNode);
			}
			
		}
		return 1;
	}
}
