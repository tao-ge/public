package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.Constants;
import com.ycnet.core.FrmsException;
import com.ycnet.core.LineType;
import com.ycnet.core.util.FiberRoutesUtils;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.RouteNode;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.service.FiberRouteService;
import com.ycnet.frms.service.RouteService;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FiberRoute;
import com.ycnet.frms.vo.FiberRouteNode;
import com.ycnet.frms.vo.FiberdiscConditionBean;

@Service("fiberRouteService")
public class FiberRouteServiceImpl implements FiberRouteService{

	@Resource(name="linesMapper")
	private LinesMapper linesMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;
	
	@Resource(name="routeService")
	private RouteService routeService;
	
	private static final Logger LOG = LoggerFactory
			.getLogger(FiberRouteServiceImpl.class);
	
	private FiberRoute getFiberRoute(String code) {
		
		List<Lines> allLines =  linesMapper.selectRelationLines(code);
		FiberRoutesUtils routeUtils = new FiberRoutesUtils();
		return routeUtils.startRoute(code, allLines);
	}
	
	@Override
	public int getAnyPortFiberRouteText(String code,Long orgId) {
		int ret = 0;
		List<Lines> allLines =  linesMapper.selectRelationLines(code);
		StringBuffer sb = new StringBuffer("");
		for(Lines l:allLines){
			sb.append(l.getLineId()).append(",");
		}
		String lineIds = sb.toString();
		List<String> portsList = linesMapper.selectAlonePort(orgId,null,lineIds,null);
		for(String startCode:portsList){
			ret +=getFiberRouteText(startCode,orgId);
		}
		return ret;
	}

	@Override
	public int getDeviceFiberRouteText(Long devId) {
		int ret = 0;
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f==null)
			throw new FrmsException("无效的设施。");
		if(Constants.FACILITTYPE.ROOM.toString().equals(f.getDevType())){
			FacilityConditionBean param = new FacilityConditionBean();
			param.setPdevId(devId);
			List<Facility> list =  facilityMapper.queryByConditionBean(param);
			for(Facility fa:list){
				ret +=getAllPortRoute(fa.getDevId(),fa.getOrgId());
			}
		}else{
			ret = getAllPortRoute(devId, f.getOrgId());
		}
		return ret;
		/*Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f==null)
			throw new FrmsException("无效的设施。");
		orgId = f.getOrgId();
		int ret = 0;
		List<String> portsList = linesMapper.selectAlonePort(orgId,null,null,devId);
		if(portsList==null||portsList.size()==0){
			throw new FrmsException("无法找到可以生成的光路。");
		}
		for(String port :portsList)
		{
			try {
				ret +=getFiberRouteText(port,orgId);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("（"+port+"）生成光路错误："+ e.getMessage());
			}
			
		}
		return ret;*/
	}
	@Override
	public int getAllFiberRouteText(Long orgId,String areaCode) {
		int ret = 0;
		List<String> portsList = linesMapper.selectAlonePort(orgId,areaCode,null,null);
		if(portsList==null||portsList.size()==0){
			throw new FrmsException("无法找到可以生成的光路。");
		}
		for(String port :portsList)
		{
			try {
				ret +=getFiberRouteText(port,orgId);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("（"+port+"）生成光路错误："+ e.getMessage());
			}
			
		}
		return ret;
	}

	public int getAllPortRoute(Long devId,Long orgId) {
		FiberdiscConditionBean param = new FiberdiscConditionBean();
		param.setDevId(devId);
		int ret = 0;
		List<Fiberdisc> list = fiberdiscMapper.queryBydev(param);
		for(Fiberdisc d:list){
			ret +=getAnyPortFiberRouteText(d.getPort01(),orgId);
		}
		return ret;
	}
	
	
	@Override
	@Transactional
	public int getFiberRouteText(String code,Long orgId) {
		int ret = 0;
		FiberRoute fiberRoute=getFiberRoute(code);
		if(fiberRoute==null)
			return 0;
		String startCode = fiberRoute.getAcode();
		
		List<Route> routeList = routeService.queryRouteByEndpoint(startCode,null);
		for(Route route :routeList)
		{
			routeService.deleteById(route.getRouteId());
		}
		List<LinkedList<FiberRouteNode>> list = fiberRoute.getHalfRoutes();
		
		for(LinkedList<FiberRouteNode> nodeList:list)
		{
			String endCode = nodeList.getLast().getCode();
			List<Route> l = routeService.queryRouteByEndpoint(endCode,startCode);
			if(l!=null&l.size()>0)
			{
				continue;
			}
			StringBuffer  devIds =new StringBuffer(""),codes=new StringBuffer(""),fibers=new StringBuffer();
			Route route = new Route();
			route.setAcode(nodeList.getFirst().getCode());
			route.setAdevId(nodeList.getFirst().getDevId());
			Facility first = facilityMapper.selectByPrimaryKey(nodeList.getFirst().getDevId());
			if(first!=null)
				route.setAreaCode1(first.getAreaCode1());
			
			Long otherDevId = null;
			Facility f = null;
			
			Lines linea = linesMapper.selectAllLineWithOneNull(route.getAcode());
			if (linea!=null)
			{
				otherDevId = route.getAcode().equals(linea.getAcode())?linea.getZdevId():linea.getAdevId();
				if(otherDevId!=null &&(f = facilityMapper.selectByPrimaryKey(otherDevId))!=null)
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
				if(otherDevId!=null &&(f = facilityMapper.selectByPrimaryKey(otherDevId))!=null)
				{
					route.setZotherName(f.getDevName());
					
				}else
				{
					route.setZotherName(linez.getUnknownPointName());
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
			ret +=routeService.insert(route);
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
		return ret;
	}
	
}
