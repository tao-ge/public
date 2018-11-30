package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.CableSectionDec;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.mapper.CableSectionDecMapper;
import com.ycnet.frms.mapper.RouteMapper;
import com.ycnet.frms.service.CableSectionDecService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.service.LinesService;
@Service("cableSectionDecService")
@Transactional
public class CableSectionDecServiceImpl implements CableSectionDecService{

	@Resource(name="cableSectionDecMapper")
	private CableSectionDecMapper cableSectionDecMapper;
	
	@Resource(name="routeMapper")
	private RouteMapper routeMapper;
	
	@Resource(name="linesService")
	private LinesService linesService;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;

	/**
	* @Title: 查询光缆断详细信息
	* @Description: TODO 
	* @param @return
	* @return String
	* @author fl
	* @date 2017年11月27日 下午1:40:50
	* @version V1.0
	 */
	@Override
	public List<CableSectionDec> queryCabledetails(Long sectId, Long fiberNo, String sectDec, PageBean pageBean) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", sectId);
		map.put("pageBean", pageBean);
		map.put("fiberNo", fiberNo);
//		if(sectDec !=null && !sectDec.equals("")) {
//			map.put("sectDec", sectDec);
//		}
		map.put("sectDec", sectDec);
		List<CableSectionDec> cableSectionDecList=cableSectionDecMapper.queryCabledetailsList(map);
		for (int i = 0; i < cableSectionDecList.size(); i++) {
			 CableSectionDec cableSectionDec = cableSectionDecList.get(i);
			String acode = cableSectionDec.getAcode();
			String zcode = cableSectionDec.getZcode();
			if((acode==null||"".equals(acode) && (zcode==null||"".equals(zcode)))) {
				cableSectionDec.setRouteText(null);
				continue;
			}else if(acode!=null && !"".equals(acode)){
				map.put("acode", acode);
			}else {
				map.put("zcode", zcode);
			}
			Route route=routeMapper.queryRoutext(map);
			if (route !=null) {
				cableSectionDec.setRouteText(route.getRoutetext());
			}else {
				cableSectionDec.setRouteText("");
			}
			
		}
//		pb.setRows(cableSectionDecMapper.selectCountAll(map));
//		pb.setList(cableSectionDecList);
		
		return cableSectionDecList;
	}

	/**
	 * 查询成端对应端状态
	* 
	* @Title: CableSectionDecService.java 
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @return String
	* @author fl
	* @date 2017年11月28日 下午12:35:02
	* @version V1.0
	 */
	@Override
	public CableSectionDec queryIsOccup(HashMap<String, Long> map) {
		return cableSectionDecMapper.queryIsOccup(map);
	}


	
	
	

	
	
	
	
	
	


}
