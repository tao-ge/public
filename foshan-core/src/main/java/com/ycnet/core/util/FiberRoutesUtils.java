package com.ycnet.core.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.ycnet.core.LineType;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.service.impl.LineUtilsService;
import com.ycnet.frms.vo.FiberRoute;
import com.ycnet.frms.vo.FiberRouteNode;

public class FiberRoutesUtils {

	private  FiberRoute fiberRoutes = new FiberRoute();
	
	private List<Lines> allLines = new CopyOnWriteArrayList<Lines>();
	
	
	public FiberRoute  startRoute(String code,List<Lines> all)
	{
		if(all!=null&&all.size()==1)
		{
			Lines l = all.get(0);
			if(LineType.FIBER.toString().equals(l.getLineType()))
			{
				return null; //仅两侧成端
			}
			if(l.getAcode()==null||l.getZcode()==null)
			{
				return null;
			}
		}
		allLines.addAll(all);
		
		fiberRoutes.setAcode(code);
		LinkedList<FiberRouteNode> linkedList = new LinkedList<FiberRouteNode>();
		Lines first = firstLine(code);
		
		if(first==null)
			return null;
		FiberRouteNode node = getCurNode(code, first);
		//node.setLineType(first.getLineType());
		//node.setSectId(first.getSectId());
		//node.setFiberNo(first.getFiberNo());
		linkedList.add(node);
		
		ArrayList<LinkedList<FiberRouteNode>> halfRoutes = new ArrayList<LinkedList<FiberRouteNode>>();
		halfRoutes.add(linkedList);
		fiberRoutes.setHalfRoutes(halfRoutes);
		
		FiberRouteNode otherNode = getOtherNode(code, first);
		if(otherNode==null)
		{
			return fiberRoutes;
		}
		
		
		
		return loopFiber(fiberRoutes);
		
	}
	
	
	
	private FiberRouteNode getNode(String code ,Long devId,Long sectId,Long fiberNo)
	{
		if(code==null||devId == null)
			return null;
		FiberRouteNode node = new FiberRouteNode();
		node.setCode(code);
		node.setDevId(devId);
		node.setSectId(sectId);
		node.setFiberNo(fiberNo);
		return node;
	}
	
	private Lines nextLines(String code)
	{
		if(code==null)
			return null;
		for (Lines line:allLines)
		{
			if(code.equals(line.getAcode())||code.equals(line.getZcode()))
			{
				return line;
			}
		}
		return null;
	}
	
	private String getOtherPort(String code ,Lines line)
	{
		if(code ==null||line==null)
			return null;
		if(code.equals(line.getAcode()))
			return line.getZcode();
		if(code.equals(line.getZcode()))
			return line.getAcode();
		return null;
	}
	
	private FiberRouteNode getOtherNode(String code,Lines line)
	{
		if(code ==null||line==null)
			return null;
		if(code.equals(line.getAcode()))
			return getNode(line.getZcode(),line.getZdevId(),line.getLineId(),line.getFiberNo());
		if(code.equals(line.getZcode()))
			return getNode(line.getAcode(),line.getAdevId(),line.getLineId(),line.getFiberNo());
		return null;
	}
	
	private FiberRouteNode getCurNode(String code,Lines line)
	{
		if(code ==null||line==null)
			return null;
		if(code.equals(line.getAcode()))
			return getNode(line.getAcode(),line.getAdevId(),line.getSectId(),line.getFiberNo());
		if(code.equals(line.getZcode()))
			return getNode(line.getZcode(),line.getZdevId(),line.getSectId(),line.getFiberNo());
		return null;
	}
	
	
	private FiberRoute loopFiber(FiberRoute route)
	{
		if(route==null)
			return null;
		FiberRoute temp = new FiberRoute();
		temp.setAcode(route.getAcode());
		ArrayList<LinkedList<FiberRouteNode>>halfRoutes= route.getHalfRoutes();
		temp.setHalfRoutes(halfRoutes);
		for(int i = 0 ;i <halfRoutes.size();i++)
		{
			LinkedList<FiberRouteNode> linkedRoute = halfRoutes.get(i);
			FiberRouteNode node = linkedRoute.getLast();
			if(node==null)
			{
				break;
			}
			String curCode = node.getCode();
			Lines line =null;
			while((line= nextLines(curCode))!=null)
			{
				String otherPort = getOtherPort(curCode, line);
				if(otherPort==null)
					break;
				if(otherPort.endsWith("-00-00")) //分光器
				{
					deleteOBDLine(otherPort);
					if(otherPort.equals(line.getAcode())) //从下往上
					{
						List<Lines> inLines = LineUtilsService.getInOBDLines(otherPort);
						for(Lines l :inLines)
						{
							FiberRouteNode nodeTemp = getOtherNode(otherPort, l);
							nodeTemp.setLineType(LineType.JUMPER.toString());
							LinkedList<FiberRouteNode> cloneList = (LinkedList<FiberRouteNode>)linkedRoute.clone();
							cloneList.addLast(nodeTemp);
							halfRoutes.add(cloneList);
						}
					
						halfRoutes.remove(i);
						loopFiber(temp);
					}
					else if(otherPort.equals(line.getZcode())) //从上往下
					{
						List<Lines> outLines = LineUtilsService.getOutOBDLines(otherPort);
						for(Lines l :outLines)
						{
							FiberRouteNode nodeTemp = getOtherNode(otherPort, l);
							nodeTemp.setLineType(LineType.JUMPER.toString());
							LinkedList<FiberRouteNode> cloneList = (LinkedList<FiberRouteNode>)linkedRoute.clone();
							cloneList.addLast(nodeTemp);
							allLines.remove(l);
							halfRoutes.add(cloneList);
						}
						halfRoutes.remove(i);
						loopFiber(temp);
					}
				}
				else
				{
					FiberRouteNode nodeTemp = getCurNode(otherPort, line);
					nodeTemp.setLineType(line.getLineType());
					nodeTemp.setFiberNo(line.getFiberNo());
					nodeTemp.setSectId(line.getSectId());
					linkedRoute.addLast(nodeTemp);
					allLines.remove(line);
					halfRoutes.set(i, linkedRoute);
					curCode = otherPort;
					line = nextLines(otherPort);
				}
			}
			
		}
		
		return temp;
	}
	
	private void deleteOBDLine(String ObdVirturalPort)
	{
		for(Lines l:allLines)
		{
			if(ObdVirturalPort.equals(l.getAcode())||ObdVirturalPort.equals(l.getZcode()))
				allLines.remove(l);
		}
	}
	
	private Lines firstLine(String code)
	{
		Lines temp = null;
		if( code ==null) return null;
		for(Lines line:allLines)
		{
			if((code.equals(line.getAcode()) && line.getZcode()==null)||(code.equals(line.getZcode())&&line.getAcode()==null))
			{
				temp = line;
				allLines.remove(line);
				continue;
			}
			if(code.equals(line.getAcode())||code.equals(line.getZcode()))
			{
				return line;
			}
		}
		return temp;
	}
}
