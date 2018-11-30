package com.ycnet.frms.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ycnet.core.LineType;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.service.RouteService;
import com.ycnet.frms.vo.LinesConditionBean;

@Component
public class LineUtilsService {

	@Resource(name="linesMapper")
	private LinesMapper linesMapper;
	
	private static LineUtilsService lineUtils;
	
	@PostConstruct
	public void initialize()
	{
		lineUtils = this;
		lineUtils.linesMapper = this.linesMapper;
	}
	
	public static List<Lines> getInOBDLines(String obdVirtualPort)
	{
		LinesConditionBean param = new LinesConditionBean();
		param.setLineType(LineType.VIRTUAL.toString());
		param.setZcode(obdVirtualPort);
		return lineUtils.linesMapper.queryVirtual(param);
	}
	
	public static List<Lines> getOutOBDLines(String obdVirtualPort)
	{
		LinesConditionBean param = new LinesConditionBean();
		param.setLineType(LineType.VIRTUAL.toString());
		param.setAcode(obdVirtualPort);
		return lineUtils.linesMapper.queryVirtual(param);
	}
}
