package com.ycnet.frms.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.Constants;
import com.ycnet.core.FrmsException;
import com.ycnet.core.LineType;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableSectionDec;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Piping;
import com.ycnet.frms.bean.PipingCable;
import com.ycnet.frms.bean.PipingSectWell;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.bean.Project;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.TnExport;
import com.ycnet.frms.bean.Twinfiber;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.mapper.CableMapper;
import com.ycnet.frms.mapper.CableSectionDecMapper;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.ColorsMapper;
import com.ycnet.frms.mapper.DiscinfoMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscGroupMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.LinesTmpMapper;
import com.ycnet.frms.mapper.PipingCableMapper;
import com.ycnet.frms.mapper.PipingMapper;
import com.ycnet.frms.mapper.PipingSectWellMapper;
import com.ycnet.frms.mapper.PipingSectionMapper;
import com.ycnet.frms.mapper.ProjectMapper;
import com.ycnet.frms.mapper.RouteMapper;
import com.ycnet.frms.mapper.SpaceMapper;
import com.ycnet.frms.mapper.TempLightPathMapper;
import com.ycnet.frms.mapper.TnExportMapper;
import com.ycnet.frms.mapper.TwinfiberMapper;
import com.ycnet.frms.mapper.WellMapper;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.RouteBuildService;
import com.ycnet.frms.service.SiteCodeService;
import com.ycnet.frms.util.DataUtil;
import com.ycnet.frms.util.GoogleToGPSUtil;
import com.ycnet.frms.util.MapDistance;
import com.ycnet.frms.util.StringUtil;
import com.ycnet.frms.vo.AreaMapBean;
import com.ycnet.frms.vo.FacilityAll;
import com.ycnet.frms.vo.LinesTmp;
import com.ycnet.frms.vo.RouteTmp;

import net.sf.json.JSONArray;

@Service("routeBuildService")
public class RouteBuildServiceImpl implements RouteBuildService {

	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name ="tempLightPathMapper")
	private TempLightPathMapper tempLightPathMapper;
	
	@Resource(name="linesTmpMapper")
	private LinesTmpMapper linesTmpMapper;

	@Resource(name ="routeMapper")
	private RouteMapper routeMapper;
	
	@Resource(name="siteCodeService")
	private SiteCodeService siteCodeService;
	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper cableSectionMapper;

	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="cableSectionDecMapper")
	private CableSectionDecMapper cableSectionDecMapper;
	
	@Resource(name="projectMapper")
	private ProjectMapper projectMapper;
	
	@Resource(name="cableMapper")
	private CableMapper cableMapper;
	
	@Resource(name="wellMapper")
	private WellMapper wellMapper;
	
	@Resource(name="pipingSectWellMapper")
	private PipingSectWellMapper pipingSectWellMapper;
	
	@Resource(name="pipingSectionMapper")
	private PipingSectionMapper pipingSectionMapper;
	
	@Resource(name="pipingMapper")
	private PipingMapper pipingMapper;
	
	@Resource(name="pipingCableMapper")
	private PipingCableMapper pipingCableMapper;
	
	@Resource(name="spaceMapper")
	private SpaceMapper spaceMapper;
	
	@Resource(name="colorsMapper")
	private ColorsMapper colorsMapper;

	
	private String aotherName = "";
	private String zotherName = "";
	private String isSameDistrict = "1";//是否跨区
	
	@Override
	@Transactional
	public List<RouteTmp> exportDataTmp(Long orgId, String areaCode1, String devType) {
//		// TODO Auto-generated method stub
//		List<RouteTmp> listAll = new ArrayList<RouteTmp>(); 
//		RouteTmp rt = null;
//		
//		//处理分光器  
//		// -01-01  及一对多
//		List<LinesTmp> listFG = linesTmpMapper.selectFGBy01(orgId);
//			//往上递归出光路
//		
//		for(LinesTmp FGLines : listFG) {
//			rt = new RouteTmp();
//			
//			//上递归查询
//			LinesTmp ltFG = linesTmpMapper.selectTXByCode(FGLines.getAcode(),orgId,areaCode1);
//			if(ltFG == null) {
//				continue;
//			}else {
//				if("0".equals(ltFG.getIsSameDistrict())) {
//					isSameDistrict = "0";
//				}
//				rt.setRouteText(FGLines.getRouteNoteName());
//				rt.setRouteText(ltFG.getRouteNoteName() + "<==>" + rt.getRouteText());
//				
//				rt = SelectTopByTX(rt,ltFG,orgId,areaCode1);
//				
//			}
//			//下递归查询
//			RouteTmp rt1 = null;
//			List<LinesTmp> ltFGList = linesTmpMapper.selectFGListByCode(FGLines.getAcode(),orgId);
//			for(LinesTmp ltFGLines : ltFGList) {
//				rt1 = new RouteTmp();
//				LinesTmp ltTXLines = linesTmpMapper.selectTXByCode(ltFGLines.getZcode(),orgId,areaCode1);
//				if(ltTXLines == null) {
//					zotherName = ltFGLines.getZdevName()+"("+ltFGLines.getZcode()+")";
//					rt1.setZdevName(ltFGLines.getZdevName());
//					//rt1.setRouteText(rt.getRouteText()+"<==>"+zotherName);
//					rt1.setRouteText(rt.getRouteText()+"<==>"+ltFGLines.getRouteNoteName());
//				}else {
//					if("0".equals(ltTXLines.getIsSameDistrict())) {
//						isSameDistrict = "0";
//					}
//					//rt1.setRouteText(rt.getRouteText()+"<==>"+ ltFGLines.getZdevName()+"("+ltFGLines.getZcode()+")" + "<==>" +ltTXLines.getRouteNoteName());
//					rt1.setRouteText(rt.getRouteText()+"<==>"+ ltFGLines.getRouteNoteName() + "<==>" +ltTXLines.getRouteNoteName());
//					rt1 = SelectDownByTX(rt1,ltTXLines,orgId,areaCode1);
//				}
//				
//				rt1.setAdevName(rt.getAdevName());
//				rt1.setAotherName(aotherName);	
//				rt1.setZotherName(zotherName);
//				rt1.setRouteName(aotherName + "<==>" + zotherName);
//				rt1.setIsSameDistrict(isSameDistrict);
//				listAll.add(rt1);
//
//				
//				zotherName = "";
//				rt1 = null;
//			}
//			aotherName = "";
//			isSameDistrict = "1";
//			rt = null;
//			
//		}	
//		
//		//除去分光器生成光路
//		String adev = "";
//		String zdev = "";
//		while (true) {
//			LinesTmp zcLine = linesTmpMapper.selectZCLines(orgId);
//			if(zcLine == null) {
//				break;
//			}
//			if("0".equals(zcLine.getIsSameDistrict())) {
//				isSameDistrict = "0";
//			}
//			linesTmpMapper.deleteByCode(zcLine);
//			//往下递归出光路
//			rt = new RouteTmp();
//			
//			if(zcLine.getZcode() == null) {
//				if(linesTmpMapper.selectGXByCode(zcLine.getAcode(),orgId,areaCode1)==null){
//					linesTmpMapper.deleteByCode(zcLine);
//					continue;
//				}
//				
//				//上行
//				rt.setRouteText(zcLine.getAdevName()+"("+zcLine.getAcode()+")");
//				zotherName = zcLine.getAdevName()+"("+zcLine.getAcode()+")";
//				rt.setZdevName(zcLine.getAdevName());
//				rt = SelectTopByTXs(zcLine.getAcode(),rt,zcLine,orgId,areaCode1);
//			}else if(zcLine.getAcode() == null) {
//				if(linesTmpMapper.selectGXByCode(zcLine.getZcode(),orgId,areaCode1)==null){
//					linesTmpMapper.deleteByCode(zcLine);
//					continue;
//				}
//				
//				//下行
//				rt.setRouteText(rt.getRouteText()+"<==>"+zcLine.getZdevName()+"("+zcLine.getZcode()+")");
//				aotherName = zcLine.getZdevName()+"("+zcLine.getZcode()+")";
//				rt.setAdevId(zcLine.getZdevId());
//				rt.setAdevName(zcLine.getZdevName());
//				rt = SelectDownByTXs(zcLine.getZcode(),rt,zcLine,orgId,areaCode1);
//			}else {
//				adev = zcLine.getAdevName()+zcLine.getAcode();
//				zdev = zcLine.getZdevName()+zcLine.getZcode();
//				//过滤掉数据
////				if((adev.indexOf("PTN")!=-1&&zdev.indexOf("BBU")!=-1) || (adev.indexOf("BBU")!=-1&&zdev.indexOf("PTN")!=-1)) {
////				}else {
////					if(linesTmpMapper.selectGXByCode(zcLine.getAcode(),orgId)==null && linesTmpMapper.selectGXByCode(zcLine.getZcode(),orgId)==null){
////						linesTmpMapper.deleteByCode(zcLine);
////						continue;
////					}
////				}
//				
//				rt.setRouteText(zcLine.getAdevName()+"("+zcLine.getAcode()+")");
//				rt = SelectTopByTXs(zcLine.getAcode(),rt,zcLine,orgId,areaCode1);
//				
//				//下行
//				rt.setRouteText(rt.getRouteText()+"<==>"+zcLine.getZdevName()+"("+zcLine.getZcode()+")");
//				rt = SelectDownByTXs(zcLine.getZcode(),rt,zcLine,orgId,areaCode1);
//			}
//			rt.setAotherName(aotherName);	
//			rt.setZotherName(zotherName);
//			rt.setRouteName(aotherName + "<==>" + zotherName);
//			rt.setIsSameDistrict(isSameDistrict);
//			//if(!aotherName.equals(zotherName)) {
//			listAll.add(rt);
//			rt = null;
//			aotherName = "";
//			zotherName = "";
//			isSameDistrict = "1";
//			adev = "";
//			zdev = "";
//		}
//		Route route = null;
//		for(RouteTmp RouteTmp1 : listAll) {
//			route = new Route();
//			route.setCodes(RouteTmp1.getRouteText());
//			routeMapper.insertSelective(route);
//			route = null;
//		}
//		return listAll;
		return null;
	}
	/**
	 * 拼接区域编码集合
	 * @author: YHT
	 * @date: 2017年10月10日 上午10:55:30 
	 * @Title: getAreaCodes  
	 * @param @param AreaCodes
	 * @param @param aAreaCode
	 * @param @param zAreaCode
	 * @param @return     
	 * @return String   
	 * @throws
	 */
	private String getAreaCodes(String AreaCodes,String aAreaCode,String zAreaCode) {
		if(aAreaCode != null) {
			if("".equals(AreaCodes)) {
				AreaCodes = aAreaCode;
			}else if(AreaCodes.indexOf(aAreaCode)==-1){
				AreaCodes = AreaCodes + "," + aAreaCode;
			}
		}
		if(zAreaCode != null) {
			if("".equals(AreaCodes)) {
				AreaCodes = zAreaCode;
			}else if(AreaCodes.indexOf(zAreaCode)==-1){
				AreaCodes = AreaCodes + "," + zAreaCode;
			}
		}
		return AreaCodes;
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
	private RouteTmp SelectTopByTX(RouteTmp rt,LinesTmp lt,Long orgId,String areaCode) {
		LinesTmp ltGX = linesTmpMapper.selectGXByCode(lt.getRouteCode(),orgId,areaCode);
		
		if(ltGX == null) {
			aotherName = lt.getRouteNoteName();
			rt.setAdevName(aotherName.substring(0, aotherName.lastIndexOf("(")));
			rt.setAdevId(lt.getAdevId());
			rt.setAcode(lt.getRouteCode());
			return rt;
		}else {
			if("0".equals(ltGX.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),ltGX.getAareaCode(),ltGX.getZareaCode()));
			
			if(ltGX.getRouteCode() == null) {
				if(ltGX.getAdevId()==null || ltGX.getZdevId()==null) {
					aotherName = lt.getRouteNoteName();
					rt.setAdevName(aotherName.substring(0, aotherName.lastIndexOf("(")));
					rt.setAdevId(lt.getAdevId());
					rt.setAcode(lt.getRouteCode());
					return rt;
				}
				if(ltGX.getAcode()==null) {
					aotherName = ltGX.getAdevName();
					rt.setAdevName(aotherName);
					rt.setAdevId(ltGX.getAdevId());
					rt.setAcode(ltGX.getRouteCode());
					rt.setRouteText(ltGX.getAdevName() + "<==>" + rt.getRouteText());
				}else {
					aotherName = ltGX.getZdevName();
					rt.setAdevName(aotherName);
					rt.setAdevId(ltGX.getAdevId());
					rt.setAcode(ltGX.getRouteCode());
					rt.setRouteText(ltGX.getZdevName() + "<==>" + rt.getRouteText());
				}
				
			}else {
				rt.setRouteText(ltGX.getRouteNoteName() + "<==>" + rt.getRouteText());
				LinesTmp ltTX = linesTmpMapper.selectTXByCode(ltGX.getRouteCode(),orgId,areaCode);
				
				if(ltTX == null) {
					aotherName = ltGX.getRouteNoteName();
					rt.setAdevName(aotherName.substring(0, aotherName.lastIndexOf("(")));
					rt.setAdevId(ltGX.getAdevId());
					rt.setAcode(ltGX.getRouteCode());
					return rt;
				}else {
					if("0".equals(ltTX.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),ltTX.getAareaCode(),ltTX.getZareaCode()));
					linesTmpMapper.deleteByCode(ltTX);
					if(ltTX.getRouteCode() == null) {
						if(ltTX.getAdevId()==null || ltTX.getZdevId()==null) {
							aotherName = ltGX.getRouteNoteName();
							rt.setAdevName(aotherName.substring(0, aotherName.lastIndexOf("(")));
							rt.setAdevId(ltGX.getAdevId());
							rt.setAcode(ltGX.getRouteCode());
							return rt;
						}
						if(ltTX.getAcode()==null) {
							aotherName = ltTX.getAdevName();
							rt.setAdevName(aotherName);
							rt.setAdevId(ltTX.getAdevId());
							rt.setAcode(ltTX.getRouteCode());
							rt.setRouteText(ltTX.getAdevName() + "<==>" + rt.getRouteText());
						}else {
							aotherName = ltTX.getZdevName();
							rt.setAdevName(aotherName);
							rt.setAdevId(ltTX.getAdevId());
							rt.setAcode(ltTX.getRouteCode());
							rt.setRouteText(ltTX.getZdevName() + "<==>" + rt.getRouteText());
						}
						
					}else {
						rt.setRouteText(ltTX.getRouteNoteName() + "<==>" + rt.getRouteText());
						rt = SelectTopByTX(rt,ltTX,orgId,areaCode);
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
	private RouteTmp SelectDownByTX(RouteTmp rt,LinesTmp lt,Long orgId,String areaCode) {
		LinesTmp ltGX = linesTmpMapper.selectGXByCode(lt.getRouteCode(),orgId,areaCode);
		
		if(ltGX == null) {
			zotherName = lt.getRouteNoteName();
			rt.setZdevId(lt.getAdevId());
			rt.setZcode(lt.getRouteCode());
			rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
			return rt;
		}else {
			if("0".equals(ltGX.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),ltGX.getAareaCode(),ltGX.getZareaCode()));
			if(ltGX.getRouteCode() == null) {
				if(ltGX.getAdevId()==null || ltGX.getZdevId()==null) {
					zotherName = lt.getRouteNoteName();
					rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
					rt.setZdevId(lt.getAdevId());
					rt.setZcode(lt.getRouteCode());
					return rt;
				}
				if(ltGX.getAcode()==null) {
					zotherName = ltGX.getAdevName();
					rt.setZdevName(zotherName);
					rt.setZdevId(ltGX.getAdevId());
					rt.setZcode(ltGX.getRouteCode());
					rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getAdevName());
				}else {
					zotherName = ltGX.getZdevName();
					rt.setZdevName(zotherName);
					rt.setZdevId(ltGX.getAdevId());
					rt.setZcode(ltGX.getRouteCode());
					rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getZdevName());
				}
				
			}else {
				rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getRouteNoteName());
				LinesTmp ltTX = linesTmpMapper.selectTXByCode(ltGX.getRouteCode(),orgId,areaCode);
				
				if(ltTX == null) {
					zotherName = ltGX.getRouteNoteName();
					rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
					rt.setZdevId(ltGX.getAdevId());
					rt.setZcode(ltGX.getRouteCode());
					return rt;
				}else {
					if("0".equals(ltTX.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),ltTX.getAareaCode(),ltTX.getZareaCode()));
					linesTmpMapper.deleteByCode(ltTX);
					if(ltTX.getRouteCode() == null) {
						if(ltTX.getAdevId()==null || ltTX.getZdevId()==null) {
							zotherName = ltGX.getRouteNoteName();
							rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
							rt.setZdevId(ltGX.getAdevId());
							rt.setZcode(ltGX.getRouteCode());
							return rt;
						}
						if(ltTX.getAcode()==null) {
							zotherName = ltTX.getAdevName();
							rt.setAdevName(zotherName);
							rt.setZdevId(ltTX.getAdevId());
							rt.setZcode(ltTX.getRouteCode());
							rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getAdevName());
						}else {
							zotherName = ltTX.getZdevName();
							rt.setZdevName(zotherName);
							rt.setZdevId(ltTX.getAdevId());
							rt.setZcode(ltTX.getRouteCode());
							rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getZdevName());
						}
						
					}else {
						rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getRouteNoteName());
						rt = SelectDownByTX(rt,ltTX,orgId,areaCode);
					}
				}
			}
			
		}
		
		
		
		return rt;
	}
	
	/**
	 * 正常光路上行
	* @Title: SelectTopByTXs 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @param rt
	* @param @param lt
	* @param @param orgId
	* @param @param areaCode
	* @param @return    入参
	* @return RouteTmp    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年1月23日 下午1:40:04 
	* @version V1.0
	 */
	private RouteTmp SelectTopByTXs(String code,RouteTmp rt,LinesTmp lt,Long orgId,String areaCode) {
		LinesTmp ltGX = linesTmpMapper.selectGXByCode(code,orgId,areaCode);
		
		if(ltGX == null) {
			if(code.equals(lt.getAcode())) {
				aotherName = lt.getAdevName()+"("+code+")";
				rt.setAdevName(lt.getAdevName());
				rt.setAdevId(lt.getAdevId());
				rt.setAcode(lt.getAcode());
			}else {
				aotherName = lt.getZdevName()+"("+code+")";
				rt.setAdevName(lt.getZdevName());
				rt.setAdevId(lt.getAdevId());
				rt.setAcode(lt.getAcode());
			}
			return rt;
		}else {
			if("0".equals(ltGX.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),ltGX.getAareaCode(),ltGX.getZareaCode()));
			if(ltGX.getRouteCode() == null) {
				if(ltGX.getAdevId()==null || ltGX.getZdevId()==null) {
					if(code.equals(lt.getAcode())) {
						aotherName = lt.getAdevName()+"("+code+")";
						rt.setAdevName(lt.getAdevName());
						rt.setAdevId(lt.getAdevId());
						rt.setAcode(lt.getAcode());
					}else {
						aotherName = lt.getZdevName()+"("+code+")";
						rt.setAdevName(lt.getZdevName());
						rt.setAdevId(lt.getAdevId());
						rt.setAcode(lt.getAcode());
					}
					return rt;
				}
				if(ltGX.getAcode()==null) {
					aotherName = ltGX.getAdevName();
					rt.setAdevName(aotherName);
					rt.setAdevId(ltGX.getAdevId());
					rt.setAcode(ltGX.getRouteCode());
					rt.setRouteText(ltGX.getAdevName() + "<==>" + rt.getRouteText());
				}else {
					aotherName = ltGX.getZdevName();
					rt.setAdevName(aotherName);
					rt.setAdevId(ltGX.getAdevId());
					rt.setAcode(ltGX.getRouteCode());
					rt.setRouteText(ltGX.getZdevName() + "<==>" + rt.getRouteText());
				}
				
			}else {
				rt.setRouteText(ltGX.getRouteNoteName() + "<==>" + rt.getRouteText());
				LinesTmp ltTX = linesTmpMapper.selectTXByCode(ltGX.getRouteCode(),orgId,areaCode);
				
				if(ltTX == null) {
					aotherName = ltGX.getRouteNoteName();
					rt.setAdevName(aotherName.substring(0, aotherName.lastIndexOf("(")));
					rt.setAdevId(ltGX.getAdevId());
					rt.setAcode(ltGX.getRouteCode());
					return rt;
				}else {
					if("0".equals(ltTX.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),ltTX.getAareaCode(),ltTX.getZareaCode()));
					linesTmpMapper.deleteByCode(ltTX);
					if(ltTX.getRouteCode() == null) {
						if(ltTX.getAdevId()==null || ltTX.getZdevId()==null) {
							if(ltGX.getRouteCode().equals(ltGX.getAcode())) {
								aotherName = ltGX.getAdevName()+"("+code+")";
								rt.setAdevName(ltGX.getAdevName());
								rt.setAdevId(ltGX.getAdevId());
								rt.setAcode(ltGX.getRouteCode());
							}else {
								aotherName = ltGX.getZdevName()+"("+code+")";
								rt.setAdevName(ltGX.getZdevName());
								rt.setAdevId(ltGX.getAdevId());
								rt.setAcode(ltGX.getRouteCode());
							}
							return rt;
						}
						if(ltTX.getAcode()==null) {
							aotherName = ltTX.getAdevName();
							rt.setAdevName(aotherName);
							rt.setAdevId(ltTX.getAdevId());
							rt.setAcode(ltTX.getRouteCode());
							rt.setRouteText(ltTX.getAdevName() + "<==>" + rt.getRouteText());
						}else {
							aotherName = ltTX.getZdevName();
							rt.setAdevName(aotherName);
							rt.setAdevId(ltTX.getAdevId());
							rt.setAcode(ltTX.getRouteCode());
							rt.setRouteText(ltTX.getZdevName() + "<==>" + rt.getRouteText());
						}
						
					}else {
						rt.setRouteText(ltTX.getRouteNoteName() + "<==>" + rt.getRouteText());
						rt = SelectTopByTXs(ltTX.getRouteCode(),rt,ltTX,orgId,areaCode);
					}
					
				}
			}
		}
		return rt;
	}
	
	private RouteTmp SelectDownByTXs(String code,RouteTmp rt,LinesTmp lt,Long orgId,String areaCode) {
		LinesTmp ltGX = linesTmpMapper.selectGXByCode(code,orgId,areaCode);
		
		if(ltGX == null) {
			if(code.equals(lt.getAcode())) {
				zotherName = lt.getAdevName()+"("+code+")";
				rt.setZdevName(lt.getAdevName());
				rt.setZdevId(lt.getAdevId());
				rt.setZcode(lt.getZcode());
			}else {
				zotherName = lt.getZdevName()+"("+code+")";
				rt.setZdevName(lt.getZdevName());
				rt.setZdevId(lt.getAdevId());
				rt.setZcode(lt.getZcode());
			}
			return rt;
		}else {
			if("0".equals(ltGX.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),ltGX.getAareaCode(),ltGX.getZareaCode()));
			if(ltGX.getRouteCode() == null) {
				if(ltGX.getAdevId()==null || ltGX.getZdevId()==null) {
					if(code.equals(lt.getAcode())) {
						zotherName = lt.getAdevName()+"("+code+")";
						rt.setZdevName(lt.getAdevName());
						rt.setZdevId(lt.getAdevId());
						rt.setZcode(lt.getZcode());
					}else {
						zotherName = lt.getZdevName()+"("+code+")";
						rt.setZdevName(lt.getZdevName());
						rt.setZdevId(lt.getAdevId());
						rt.setZcode(lt.getZcode());
					}
					return rt;
				}
				if(ltGX.getAcode()==null) {
					zotherName = ltGX.getAdevName();
					rt.setZdevName(zotherName);
					rt.setZdevId(ltGX.getAdevId());
					rt.setZcode(ltGX.getRouteCode());
					rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getAdevName() );
				}else {
					zotherName = ltGX.getZdevName();
					rt.setZdevName(zotherName);
					rt.setZdevId(ltGX.getAdevId());
					rt.setZcode(ltGX.getRouteCode());
					rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getZdevName());
				}
				
			}else {
				rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getRouteNoteName());
				LinesTmp ltTX = linesTmpMapper.selectTXByCode(ltGX.getRouteCode(),orgId,areaCode);
				
				if(ltTX == null) {
					zotherName = ltGX.getRouteNoteName();
					rt.setZdevId(ltGX.getAdevId());
					rt.setZcode(ltGX.getRouteCode());
					rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
					return rt;
				}else {
					if("0".equals(ltTX.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),ltTX.getAareaCode(),ltTX.getZareaCode()));
					linesTmpMapper.deleteByCode(ltTX);
					if(ltTX.getRouteCode() == null) {
						if(ltTX.getAdevId()==null || ltTX.getZdevId()==null) {
							zotherName = ltGX.getRouteNoteName();
							rt.setZdevId(ltGX.getAdevId());
							rt.setZcode(ltGX.getRouteCode());
							rt.setZdevName(zotherName.substring(0, zotherName.lastIndexOf("(")));
							return rt;
						}
						if(ltTX.getAcode()==null) {
							zotherName = ltTX.getAdevName();
							rt.setZdevName(zotherName);
							rt.setZdevId(ltTX.getAdevId());
							rt.setZcode(ltTX.getRouteCode());
							rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getAdevName() );
						}else {
							zotherName = ltTX.getZdevName();
							rt.setZdevName(zotherName);
							rt.setZdevId(ltTX.getAdevId());
							rt.setZcode(ltTX.getRouteCode());
							rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getZdevName());
						}
						
					}else {
						rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getRouteNoteName());
						rt = SelectDownByTXs(ltTX.getRouteCode(),rt,ltTX,orgId,areaCode);
					}
				}
			}
		}
		return rt;
	}

	@Resource(name="exportMapper")
	private TnExportMapper tnExportMapper;
	
	
	@Override
	@Transactional
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
				//if(ponRoute.equals(pon.getD())) {//ID合
				if(ponRoute.equals(pon.getE())) {//ID合
					list.add(pon);
				}
				ponRoute="1";
			}else {
				pon = PONlist.get(i);
			}
			if("".equals(ponRoute)) {
				list = null;
				list = new ArrayList<TnExport>();
				//ponRoute = pon.getD();
				ponRoute = pon.getE();
				list.add(pon);
			}else {
				//if(ponRoute.equals(pon.getD())) {
				if(ponRoute.equals(pon.getE())) {
					list.add(pon);
				}else {
					route = new RouteTmp();
					if(list.size()<2) {
						route.setIsSameDistrict("PON一根跳纤");
					}else {
						route.setIsSameDistrict("PON");
					}
					route.setAreaCode1("全球通汇聚区");
						
					TnExport teCSD = null;
					for(TnExport te : list) {
						teCSD = te;
						//route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
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
					
					//导入传输段
					if(teCSD!= null && !"".equals(teCSD.getD())) {
						TnExport teCSD1 = tnExportMapper.selectByRouteId(teCSD.getD().substring(0,teCSD.getD().indexOf(".")));
						if(teCSD1 != null) {
							if(teCSD1.getK() != null) {
								route.setTransmissionSection(teCSD1.getK());
							}
							if(teCSD1.getL() != null) {
								route.setTransmissionSystem(teCSD1.getL());
							}
							if(teCSD1.getM() != null) {
								route.setRouteText(teCSD1.getM()+"<==>"+route.getRouteText());
							}
							if(teCSD1.getN() != null) {
								route.setRouteText(route.getRouteText()+"<==>"+teCSD1.getN());
							}
						}
						
					}
					teCSD = null;
					routeList.add(route);
					route = null;
					list = new ArrayList<TnExport>();
					ponRoute = pon.getE();
					//ponRoute = pon.getD();
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
					if(pdhRoute.equals(pdh.getE())) {
						list.add(pdh);
					}
					pdhRoute="1";
				}else {
					pdh = PDHlist.get(j);
				}
				if("".equals(pdhRoute)) {
					list = null;
					list = new ArrayList<TnExport>();
					pdhRoute = pdh.getE();
					list.add(pdh);
				}else {
					if(pdhRoute.equals(pdh.getE())) {
						list.add(pdh);
					}else {
						route = new RouteTmp();
						if(list.size()<2) {
							route.setIsSameDistrict("PDH一根跳纤");
						}else {
							route.setIsSameDistrict("PDH");
						}
						route.setAreaCode1("全球通汇聚区");
							
						TnExport teCSD = null;
						for(TnExport te : list) {
							teCSD = te;
							//route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
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
						//导入传输段
						if(teCSD!= null && !"".equals(teCSD.getD())) {
							TnExport teCSD1 = tnExportMapper.selectByRouteId(teCSD.getD().substring(0,teCSD.getD().indexOf(".")));
							if(teCSD1 != null) {
								if(teCSD1.getK() != null) {
									route.setTransmissionSection(teCSD1.getK());
								}
								if(teCSD1.getL() != null) {
									route.setTransmissionSystem(teCSD1.getL());
								}
								if(teCSD1.getM() != null) {
									route.setRouteText(teCSD1.getM()+"<==>"+route.getRouteText());
								}
								if(teCSD1.getN() != null) {
									route.setRouteText(route.getRouteText()+"<==>"+teCSD1.getN());
								}
							}
						}
						teCSD = null;
						routeList.add(route);
						route = null;
						list = new ArrayList<TnExport>();
						pdhRoute = pdh.getE();
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
				if(ptnRoute.equals(ptn.getE())) {
					list.add(ptn);
				}
				ptnRoute="1";
			}else {
				ptn = PTNlist.get(k);
			}
			if("".equals(ptnRoute)) {
				list = new ArrayList<TnExport>();
				ptnRoute = ptn.getE();
				list.add(ptn);
			}else {
				if(ptnRoute.equals(ptn.getE())) {
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
					route.setAreaCode1("全球通汇聚区");
					route2.setAreaCode1("全球通汇聚区");
					
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
//					TnExport cs = tnExportMapper.selectByRouteName(list1.get(0).getE());
//					if(cs != null) {
//						if(cs.getA() != null) {
//							route.setTransmissionSection(cs.getA());
//							route2.setTransmissionSection(cs.getA());
//						}
//						if(cs.getB() != null) {
//							route.setTransmissionSystem(cs.getB());
//							route2.setTransmissionSystem(cs.getB());
//						}
//						
//					}
//					
					TnExport teCSD = null;
					
					for(TnExport te1 : list1) {	
						teCSD = te1;
						//route.setRouteId(Long.parseLong(te1.getD().substring(0,te1.getD().indexOf("."))));
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
						//route2.setRouteId(Long.parseLong(te2.getD().substring(0,te2.getD().indexOf("."))));
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
//					if(cs != null) {
//						if(cs.getD() != null) {
//							route.setRouteText(cs.getD()+"<==>"+route.getRouteText());
//							route2.setRouteText(cs.getD()+"<==>"+route2.getRouteText());
//						}
//						if(cs.getF() != null) {
//							route.setRouteText(route.getRouteText()+"<==>"+cs.getF());
//							route2.setRouteText(route2.getRouteText()+"<==>"+cs.getF());
//						}
//					}
					
					//导入传输段
					if(teCSD!= null && !"".equals(teCSD.getD())) {
						TnExport teCSD1 = tnExportMapper.selectByRouteId(teCSD.getD().substring(0,teCSD.getD().indexOf(".")));
						if(teCSD1 != null) {
							if(teCSD1.getK() != null) {
								route.setTransmissionSection(teCSD1.getK());
								route2.setTransmissionSection(teCSD1.getK());
							}
							if(teCSD1.getL() != null) {
								route.setTransmissionSystem(teCSD1.getL());
								route2.setTransmissionSystem(teCSD1.getL());
							}
							if(teCSD1.getM() != null) {
								route.setRouteText(teCSD1.getM()+"<==>"+route.getRouteText());
								route2.setRouteText(teCSD1.getM()+"<==>"+route2.getRouteText());
							}
							if(teCSD1.getN() != null) {
								route.setRouteText(route.getRouteText()+"<==>"+teCSD1.getN());
								route2.setRouteText(route2.getRouteText()+"<==>"+teCSD1.getN());
							}
						}
					}
					
					routeList.add(route);
					routeList.add(route2);
					
					teCSD = null;
					
					route = null;
					route2 = null;
					list = null;
					list1 = null;
					list2 = null;
					list = new ArrayList<TnExport>();
					ptnRoute = ptn.getE();
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
					if(sdhRoute.equals(sdh.getE())) {
						list.add(sdh);
					}
					sdhRoute="1";
				}else {
					sdh = SDHlist.get(n);
				}
				if("".equals(sdhRoute)) {
					list = new ArrayList<TnExport>();
					sdhRoute = sdh.getE();
					list.add(sdh);
				}else {
					if(sdhRoute.equals(sdh.getE())) {
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
						route.setAreaCode1("全球通汇聚区");
						route2.setAreaCode1("全球通汇聚区");
							
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
						
//						TnExport cs = tnExportMapper.selectByRouteName(list1.get(0).getE());
//						if(cs != null) {
//							if(cs.getA() != null) {
//								route.setTransmissionSection(cs.getA());
//								route2.setTransmissionSection(cs.getA());
//							}
//							if(cs.getB() != null) {
//								route.setTransmissionSystem(cs.getB());
//								route2.setTransmissionSystem(cs.getB());
//							}
//							
//						}
						
						TnExport teCSD = null;
						
						for(TnExport te1 : list1) {	
							teCSD = te1;
							//route.setRouteId(Long.parseLong(te1.getD().substring(0,te1.getD().indexOf("."))));
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
							//route2.setRouteId(Long.parseLong(te2.getD().substring(0,te2.getD().indexOf("."))));
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
//						if(cs != null) {
//							if(cs.getD() != null) {
//								route.setRouteText(cs.getD()+"<==>"+route.getRouteText());
//								route2.setRouteText(cs.getD()+"<==>"+route2.getRouteText());
//							}
//							if(cs.getF() != null) {
//								route.setRouteText(route.getRouteText()+"<==>"+cs.getF());
//								route2.setRouteText(route2.getRouteText()+"<==>"+cs.getF());
//							}
//						}
						
						if(teCSD!= null && !"".equals(teCSD.getD())) {
							TnExport teCSD1 = tnExportMapper.selectByRouteId(teCSD.getD().substring(0,teCSD.getD().indexOf(".")));
							if(teCSD1 != null) {
								if(teCSD1.getK() != null) {
									route.setTransmissionSection(teCSD1.getK());
									route2.setTransmissionSection(teCSD1.getK());
								}
								if(teCSD1.getL() != null) {
									route.setTransmissionSystem(teCSD1.getL());
									route2.setTransmissionSystem(teCSD1.getL());
								}
								if(teCSD1.getM() != null) {
									route.setRouteText(teCSD1.getM()+"<==>"+route.getRouteText());
									route2.setRouteText(teCSD1.getM()+"<==>"+route2.getRouteText());
								}
								if(teCSD1.getN() != null) {
									route.setRouteText(route.getRouteText()+"<==>"+teCSD1.getN());
									route2.setRouteText(route2.getRouteText()+"<==>"+teCSD1.getN());
								}
							}
						}
						
						routeList.add(route);
						routeList.add(route2);
						
						teCSD = null;
						
						route = null;
						route2 = null;
						list = null;
						list1 = null;
						list2 = null;
						list = new ArrayList<TnExport>();
						sdhRoute = sdh.getE();
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
					//if(qtRoute.equals(qt.getD())) {
					if(qtRoute.equals(qt.getE())) {
						list.add(qt);
					}
					qtRoute="1";
				}else {
					qt = QTlist.get(m);
				}
				if("".equals(qtRoute)) {
					list = null;
					list = new ArrayList<TnExport>();
					qtRoute = qt.getE();
					//qtRoute = qt.getD();
					list.add(qt);
				}else {
					if(qtRoute.equals(qt.getE())) {
					//if(qtRoute.equals(qt.getD())) {
						list.add(qt);
					}else {
						route = new RouteTmp();
						if(list.size()<2) {
							route.setIsSameDistrict("其他一根跳纤");
						}else {
							route.setIsSameDistrict("其他");
						}
						route.setAreaCode1("全球通汇聚区");
						
						TnExport teCSD = null;
						
						for(TnExport te : list) {
							teCSD = te;
							//route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
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
						
						//导入传输段
						if(teCSD!= null && !"".equals(teCSD.getD())) {
							TnExport teCSD1 = tnExportMapper.selectByRouteId(teCSD.getD().substring(0,teCSD.getD().indexOf(".")));
							if(teCSD1 != null) {
								if(teCSD1.getK() != null) {
									route.setTransmissionSection(teCSD1.getK());
								}
								if(teCSD1.getL() != null) {
									route.setTransmissionSystem(teCSD1.getL());
								}
								if(teCSD.getM() != null) {
									route.setRouteText(teCSD1.getM()+"<==>"+route.getRouteText());
								}
								if(teCSD.getN() != null) {
									route.setRouteText(route.getRouteText()+"<==>"+teCSD1.getN());
								}
							}
						}
						teCSD = null;
						
						routeList.add(route);
						route = null;
						list = new ArrayList<TnExport>();
						qtRoute = qt.getE();
						//qtRoute = qt.getD();
						list.add(qt);
					}
				}
		
			}
		
		return routeList;
	}

//	//区域编码
//	private String areaCodeInfo = null;
//	//区域组织机构ID
//	private Long orgIdInfo = null;
	
	/**
	 * 重新生成光路
	 */
	@Override
	@Transactional
	public int routeBuild(Long orgId, String areaCode) {
		// TODO Auto-generated method stub
		int ret = 0;
		//判断是否已在生成光路
		if(linesTmpMapper.selectByOrgId(orgId) > 1) {
			throw new FrmsException("光路正在生成，请等待！");
		}
		
		ret = LineToLineTmp(orgId,areaCode);
		if(ret < 1) {
			throw new FrmsException("保存失败！");
		}else {
			ret = 0;
		}
		ret = routeMapper.deleteByOrgId(orgId,areaCode);
		
		List<RouteTmp> listAll = new ArrayList<RouteTmp>(); 
		RouteTmp rt = null;
		String devIds = "";
		
		//处理分光器  
		// -01-01一对多，  -01-02二对多
		List<LinesTmp> listFG = linesTmpMapper.selectFGBy01(orgId);
			//往上递归出光路
		
		for(LinesTmp FGLines : listFG) {
			rt = new RouteTmp();
			rt.setAreaCode1("");
			//上递归查询
			LinesTmp ltFG = linesTmpMapper.selectTXByCode(FGLines.getAcode(),orgId,areaCode);
			if(ltFG == null) {
				continue;
			}else {
				//判断是否跨区
				if("0".equals(ltFG.getIsSameDistrict())) {
					isSameDistrict = "0";
				}
				rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),FGLines.getAareaCode(),FGLines.getZareaCode()));
				rt.setRouteText(FGLines.getRouteNoteName());
				rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),FGLines.getAareaCode(),FGLines.getZareaCode()));
				rt.setRouteText(ltFG.getRouteNoteName() + "<==>" + rt.getRouteText());
				rt.setAcode(FGLines.getAcode());
				rt.setAdevId(ltFG.getAdevId());
				rt = SelectTopByTX(rt,ltFG,orgId,areaCode);
				
			}
			//下递归查询
			RouteTmp rt1 = null;
			List<LinesTmp> ltFGList = linesTmpMapper.selectFGListByCode(FGLines.getAcode(),orgId);
			for(LinesTmp ltFGLines : ltFGList) {
				rt1 = new RouteTmp();
				rt1.setAreaCode1(getAreaCodes(rt.getAreaCode1(),ltFGLines.getAareaCode(),ltFGLines.getZareaCode()));
				LinesTmp ltTXLines = linesTmpMapper.selectTXByCode(ltFGLines.getZcode(),orgId,areaCode);
				if(ltTXLines == null) {
					zotherName = ltFGLines.getZdevName()+"("+ltFGLines.getZcode()+")";
					rt1.setZdevName(ltFGLines.getZdevName());
					rt1.setZdevId(ltFGLines.getAdevId());
					rt1.setZcode(ltFGLines.getRouteCode());
					//rt1.setRouteText(rt.getRouteText()+"<==>"+zotherName);
					rt1.setRouteText(rt.getRouteText()+"<==>"+ltFGLines.getRouteNoteName());
				}else {
					if("0".equals(ltTXLines.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					rt1.setAreaCode1(getAreaCodes(rt1.getAreaCode1(),ltFGLines.getAareaCode(),ltFGLines.getZareaCode()));
					//rt1.setRouteText(rt.getRouteText()+"<==>"+ ltFGLines.getZdevName()+"("+ltFGLines.getZcode()+")" + "<==>" +ltTXLines.getRouteNoteName());
					rt1.setRouteText(rt.getRouteText()+"<==>"+ ltFGLines.getRouteNoteName() + "<==>" +ltTXLines.getRouteNoteName());
					rt1 = SelectDownByTX(rt1,ltTXLines,orgId,areaCode);
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
//		while (true) {
//			LinesTmp zcLine = linesTmpMapper.selectZCLines(orgId);
//			if(zcLine == null) {
//				break;
//			}
		List<LinesTmp> zcLineList = linesTmpMapper.selectZCLinesList(orgId);
		for(LinesTmp zcLine : zcLineList) {
			if("0".equals(zcLine.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			linesTmpMapper.deleteByCode(zcLine);
			//往下递归出光路
			rt = new RouteTmp();
			rt.setAreaCode1("");
			rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),zcLine.getAareaCode(),zcLine.getZareaCode()));
			if(zcLine.getZcode() == null) {
				if(linesTmpMapper.selectGXByCode(zcLine.getAcode(),orgId,areaCode)==null){
					linesTmpMapper.deleteByCode(zcLine);
					continue;
				}
				
				//上行
				rt.setRouteText(zcLine.getAdevName()+"("+zcLine.getAcode()+")");
				zotherName = zcLine.getAdevName()+"("+zcLine.getAcode()+")";
				rt.setZdevName(zcLine.getAdevName());
				rt.setZdevId(zcLine.getZdevId());
				rt.setZcode(null);
				rt = SelectTopByTXs(zcLine.getAcode(),rt,zcLine,orgId,areaCode);
			}else if(zcLine.getAcode() == null) {
				if(linesTmpMapper.selectGXByCode(zcLine.getZcode(),orgId,areaCode)==null){
					linesTmpMapper.deleteByCode(zcLine);
					continue;
				}
				
				//下行
				rt.setRouteText(rt.getRouteText()+"<==>"+zcLine.getZdevName()+"("+zcLine.getZcode()+")");
				aotherName = zcLine.getZdevName()+"("+zcLine.getZcode()+")";
				rt.setAdevId(zcLine.getAdevId());
				rt.setAcode(null);
				rt.setAdevName(zcLine.getZdevName());
				rt = SelectDownByTXs(zcLine.getZcode(),rt,zcLine,orgId,areaCode);
			}else {
				adev = zcLine.getAdevName()+zcLine.getAcode();
				zdev = zcLine.getZdevName()+zcLine.getZcode();
//				if((adev.indexOf("PTN")!=-1&&zdev.indexOf("BBU")!=-1) || (adev.indexOf("BBU")!=-1&&zdev.indexOf("PTN")!=-1)) {
//				}else {
//					if(linesTmpMapper.selectGXByCode(zcLine.getAcode(),orgId,areaCode)==null && linesTmpMapper.selectGXByCode(zcLine.getZcode(),orgId,areaCode)==null){
//						linesTmpMapper.deleteByCode(zcLine);
//						continue;
//					}
//				}
				
				rt.setRouteText(zcLine.getAdevName()+"("+zcLine.getAcode()+")");
				rt = SelectTopByTXs(zcLine.getAcode(),rt,zcLine,orgId,areaCode);
				
				//下行
				rt.setRouteText(rt.getRouteText()+"<==>"+zcLine.getZdevName()+"("+zcLine.getZcode()+")");
				rt = SelectDownByTXs(zcLine.getZcode(),rt,zcLine,orgId,areaCode);
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
		
		//return listAll;
		Route route = null;
		for(RouteTmp rtTmp : listAll) {
			route = new Route();
			route.setAotherName(rtTmp.getAotherName());
			route.setZotherName(rtTmp.getZotherName());
		//	route.setCodes(rtTmp.getRouteText());
			//将原有的codes换为routetext
			route.setRoutetext(rtTmp.getRouteText());
			route.setRouteName(rtTmp.getRouteName());
			route.setAcode(rtTmp.getAcode());
			route.setAdevId(rtTmp.getAdevId());
			route.setZcode(rtTmp.getZcode());
			route.setZdevId(rtTmp.getZdevId());
			route.setOrgId(orgId);
			route.setAreaCode1(rtTmp.getAreaCode1());
			route.setCreateTime(new Date()); 
			routeMapper.insertSelective(route);
			route = null;
		}
		ret = linesTmpMapper.deleteByLine();
	
			//删除tn_task_log表中数据
			 routeMapper.delectroutelog( orgId,  areaCode);
		
		return 1;
	}
	
	private Integer LineToLineTmp(Long orgId,String areaCode) {
		int ret = 0;
		//将要生成数据导入tn_lines1,和tn_line2
		//光纤
		int ret1 = linesTmpMapper.insertByOrgId01(orgId,areaCode);
		if(ret1 < 1) {
			return ret;
		}
		//tn_line2跳纤与分光器
		int ret2 = linesTmpMapper.insertByOrgId(orgId,areaCode);
		if(ret2 < 1) {
			return ret;
		}
		return ret2;
	}
	
	/**
	 * 东平汇聚光路跳纤信息0908  导出
	 * @author: YHT
	 * @date: 2017年9月8日 下午8:11:30 
	 * @Title: exportZZPT1  
	 * @param @return     
	 * @return List<RouteTmp>   
	 * @throws
	 */
	@Override
	@Transactional
	public List<RouteTmp> exportZZPT1() {
		// TODO Auto-generated method stub
		List<TnExport> list = null;
		List<RouteTmp> routeList =  new ArrayList<RouteTmp>();
		RouteTmp route = null;
		RouteTmp route2 = null;
		
		//List<TnExport> PONlist = tnExportMapper.selectByparam("PON");
		List<TnExport> PONlist = tnExportMapper.selectByparamByType("pon");
		//tnExportMapper.deleteByParam("PON");
		
		String ponRoute = "";
		for(int i=0;i<PONlist.size();i++) {
			TnExport pon = null;
			if(i+1 == PONlist.size()) {
				pon = PONlist.get(i);
				//if(ponRoute.equals(pon.getD())) {//ID合
				if(ponRoute.equals(pon.getE())) {//ID合
					list.add(pon);
				}
				ponRoute="1";
			}else {
				pon = PONlist.get(i);
			}
			if("".equals(ponRoute)) {
				list = null;
				list = new ArrayList<TnExport>();
				//ponRoute = pon.getD();
				ponRoute = pon.getE();
				list.add(pon);
			}else {
				//if(ponRoute.equals(pon.getD())) {
				if(ponRoute.equals(pon.getE())) {
					list.add(pon);
				}else {
					route = new RouteTmp();
					if(list.size()<2) {
						route.setIsSameDistrict("PON一根跳纤");
					}else {
						route.setIsSameDistrict("PON");
					}
					route.setAreaCode1("全球通汇聚区");
						
					TnExport teCSD = null;
					for(TnExport te : list) {
						teCSD = te;
						//route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
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
					if(teCSD!= null) {
						if(teCSD.getK() != null) {
							route.setTransmissionSection(teCSD.getK());
						}
						if(teCSD.getL() != null) {
							route.setTransmissionSystem(teCSD.getL());
						}
						if(teCSD.getM() != null) {
							route.setRouteText(teCSD.getM()+"<==>"+route.getRouteText());
						}
						if(teCSD.getN() != null) {
							route.setRouteText(route.getRouteText()+"<==>"+teCSD.getN());
						}
					}
					teCSD = null;
					routeList.add(route);
					route = null;
					list = new ArrayList<TnExport>();
					ponRoute = pon.getE();
					//ponRoute = pon.getD();
					list.add(pon);
				}
			}
		}
			//PDH
			List<TnExport> PDHlist = tnExportMapper.selectByparamByType("pdh");
			//tnExportMapper.deleteByParam("PDH");
			
			String pdhRoute = "";
			for(int j=0;j<PDHlist.size();j++) {
				TnExport pdh = null;
				if(j+1 == PDHlist.size()) {
					pdh = PDHlist.get(j);
					if(pdhRoute.equals(pdh.getE())) {
						//if(pdhRoute.equals(pdh.getD())) {
						list.add(pdh);
					}
					pdhRoute="1";
				}else {
					pdh = PDHlist.get(j);
				}
				if("".equals(pdhRoute)) {
					list = null;
					list = new ArrayList<TnExport>();
					pdhRoute = pdh.getE();
					list.add(pdh);
				}else {
					if(pdhRoute.equals(pdh.getE())) {
						list.add(pdh);
					}else {
						route = new RouteTmp();
						if(list.size()<2) {
							route.setIsSameDistrict("PDH一根跳纤");
						}else {
							route.setIsSameDistrict("PDH");
						}
						route.setAreaCode1("全球通汇聚区");
							
						TnExport teCSD = null;
						for(TnExport te : list) {
							teCSD = te;
							//route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
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
						if(teCSD!= null) {
							if(teCSD.getK() != null) {
								route.setTransmissionSection(teCSD.getK());
							}
							if(teCSD.getL() != null) {
								route.setTransmissionSystem(teCSD.getL());
							}
							if(teCSD.getM() != null) {
								route.setRouteText(teCSD.getM()+"<==>"+route.getRouteText());
							}
							if(teCSD.getN() != null) {
								route.setRouteText(route.getRouteText()+"<==>"+teCSD.getN());
							}
						}
						teCSD = null;
						routeList.add(route);
						route = null;
						list = new ArrayList<TnExport>();
						pdhRoute = pdh.getE();
						//pdhRoute = pdh.getD();
						list.add(pdh);
					}
				}
			}
		
			//ip
			List<TnExport> IPlist = tnExportMapper.selectByparamByType("ip");
			//tnExportMapper.deleteByParam("PON");
			
			String ipRoute = "";
			for(int i=0;i<IPlist.size();i++) {
				TnExport pon = null;
				if(i+1 == IPlist.size()) {
					pon = IPlist.get(i);
					//if(ipRoute.equals(pon.getD())) {//ID合
					if(ipRoute.equals(pon.getE())) {//ID合
						list.add(pon);
					}
					ipRoute="1";
				}else {
					pon = IPlist.get(i);
				}
				if("".equals(ipRoute)) {
					list = null;
					list = new ArrayList<TnExport>();
					//ipRoute = pon.getD();
					ipRoute = pon.getE();
					list.add(pon);
				}else {
					//if(ipRoute.equals(pon.getD())) {
					if(ipRoute.equals(pon.getE())) {
						list.add(pon);
					}else {
						route = new RouteTmp();
						if(list.size()<2) {
							route.setIsSameDistrict("IP一根跳纤");
						}else {
							route.setIsSameDistrict("IP");
						}
						route.setAreaCode1("全球通汇聚区");
							
						TnExport teCSD = null;
						for(TnExport te : list) {
							teCSD = te;
							//route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
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
						if(teCSD!= null) {
							if(teCSD.getK() != null) {
								route.setTransmissionSection(teCSD.getK());
							}
							if(teCSD.getL() != null) {
								route.setTransmissionSystem(teCSD.getL());
							}
							if(teCSD.getM() != null) {
								route.setRouteText(teCSD.getM()+"<==>"+route.getRouteText());
							}
							if(teCSD.getN() != null) {
								route.setRouteText(route.getRouteText()+"<==>"+teCSD.getN());
							}
						}
						teCSD = null;
						routeList.add(route);
						route = null;
						list = new ArrayList<TnExport>();
						ipRoute = pon.getE();
						//ponRoute = pon.getD();
						list.add(pon);
					}
				}
			}
				//nm
				List<TnExport> NMlist = tnExportMapper.selectByparamByType("nm");
				//tnExportMapper.deleteByParam("PDH");
				
				String nmRoute = "";
				for(int j=0;j<NMlist.size();j++) {
					TnExport pdh = null;
					if(j+1 == NMlist.size()) {
						pdh = NMlist.get(j);
						if(nmRoute.equals(pdh.getE())) {
							//if(nmRoute.equals(pdh.getD())) {
							list.add(pdh);
						}
						nmRoute="1";
					}else {
						pdh = NMlist.get(j);
					}
					if("".equals(nmRoute)) {
						list = null;
						list = new ArrayList<TnExport>();
						nmRoute = pdh.getE();
						list.add(pdh);
					}else {
						if(nmRoute.equals(pdh.getE())) {
							list.add(pdh);
						}else {
							route = new RouteTmp();
							if(list.size()<2) {
								route.setIsSameDistrict("NM一根跳纤");
							}else {
								route.setIsSameDistrict("NM");
							}
							route.setAreaCode1("全球通汇聚区");
								
							TnExport teCSD = null;
							for(TnExport te : list) {
								teCSD = te;
								//route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
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
							if(teCSD!= null) {
								if(teCSD.getK() != null) {
									route.setTransmissionSection(teCSD.getK());
								}
								if(teCSD.getL() != null) {
									route.setTransmissionSystem(teCSD.getL());
								}
								if(teCSD.getM() != null) {
									route.setRouteText(teCSD.getM()+"<==>"+route.getRouteText());
								}
								if(teCSD.getN() != null) {
									route.setRouteText(route.getRouteText()+"<==>"+teCSD.getN());
								}
							}
							teCSD = null;
							routeList.add(route);
							route = null;
							list = new ArrayList<TnExport>();
							//nmRoute = pdh.getD();
							nmRoute = pdh.getE();
							list.add(pdh);
						}
					}
				}
		
		
		//PTN
		List<TnExport> PTNlist = tnExportMapper.selectByparamByType("ptn");
		//tnExportMapper.deleteByParam("PTN");
		
		List<TnExport> list1 = null;
		List<TnExport> list2 = null;
		String ptnRoute = "";
		for(int k=0;k<PTNlist.size();k++) {
			TnExport ptn = null;
			if(k+1 == PTNlist.size()) {
				ptn = PTNlist.get(k);
				if(ptnRoute.equals(ptn.getE())) {
					//if(ptnRoute.equals(ptn.getD())) {
					list.add(ptn);
				}
				ptnRoute="1";
			}else {
				ptn = PTNlist.get(k);
			}
			if("".equals(ptnRoute)) {
				list = new ArrayList<TnExport>();
				ptnRoute = ptn.getE();
				//ptnRoute = ptn.getD();
				list.add(ptn);
			}else {
				if(ptnRoute.equals(ptn.getE())) {
					//if(ptnRoute.equals(ptn.getD())) {
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
					route.setAreaCode1("全球通汇聚区");
					route2.setAreaCode1("全球通汇聚区");
					
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
//					TnExport cs = tnExportMapper.selectByRouteName(list1.get(0).getE());
//					if(cs != null) {
//						if(cs.getA() != null) {
//							route.setTransmissionSection(cs.getA());
//							route2.setTransmissionSection(cs.getA());
//						}
//						if(cs.getB() != null) {
//							route.setTransmissionSystem(cs.getB());
//							route2.setTransmissionSystem(cs.getB());
//						}
//						
//					}
//					
					TnExport teCSD = null;
					
					for(TnExport te1 : list1) {	
						teCSD = te1;
						//route.setRouteId(Long.parseLong(te1.getD().substring(0,te1.getD().indexOf("."))));
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
						//route2.setRouteId(Long.parseLong(te2.getD().substring(0,te2.getD().indexOf("."))));
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
//					if(cs != null) {
//						if(cs.getD() != null) {
//							route.setRouteText(cs.getD()+"<==>"+route.getRouteText());
//							route2.setRouteText(cs.getD()+"<==>"+route2.getRouteText());
//						}
//						if(cs.getF() != null) {
//							route.setRouteText(route.getRouteText()+"<==>"+cs.getF());
//							route2.setRouteText(route2.getRouteText()+"<==>"+cs.getF());
//						}
//					}
					
					if(teCSD!= null) {
						if(teCSD.getK() != null) {
							route.setTransmissionSection(teCSD.getK());
							route2.setTransmissionSection(teCSD.getK());
						}
						if(teCSD.getL() != null) {
							route.setTransmissionSystem(teCSD.getL());
							route2.setTransmissionSystem(teCSD.getL());
						}
						if(teCSD.getM() != null) {
							route.setRouteText(teCSD.getM()+"<==>"+route.getRouteText());
							route2.setRouteText(teCSD.getM()+"<==>"+route2.getRouteText());
						}
						if(teCSD.getN() != null) {
							route.setRouteText(route.getRouteText()+"<==>"+teCSD.getN());
							route2.setRouteText(route2.getRouteText()+"<==>"+teCSD.getN());
						}
					}
					
					routeList.add(route);
					routeList.add(route2);
					
					teCSD = null;
					
					route = null;
					route2 = null;
					list = null;
					list1 = null;
					list2 = null;
					list = new ArrayList<TnExport>();
					//ptnRoute = ptn.getD();
					ptnRoute = ptn.getE();
					list.add(ptn);
				}
			}
		}

		
		//SDH
			List<TnExport> SDHlist = tnExportMapper.selectByparamByType("sdh");
			//tnExportMapper.deleteByParam("SDH");

			String sdhRoute = "";
			for(int n=0;n<SDHlist.size();n++) {
				TnExport sdh = null;
				if(n+1 == SDHlist.size()) {
					sdh = SDHlist.get(n);
					if(sdhRoute.equals(sdh.getE())) {
						//if(sdhRoute.equals(sdh.getD())) {
						list.add(sdh);
					}
					sdhRoute="1";
				}else {
					sdh = SDHlist.get(n);
				}
				if("".equals(sdhRoute)) {
					list = new ArrayList<TnExport>();
					sdhRoute = sdh.getE();
					//sdhRoute = sdh.getE();
					list.add(sdh);
				}else {
					if(sdhRoute.equals(sdh.getE())) {
						//if(sdhRoute.equals(sdh.getE())) {
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
						route.setAreaCode1("全球通汇聚区");
						route2.setAreaCode1("全球通汇聚区");
							
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
						
//						TnExport cs = tnExportMapper.selectByRouteName(list1.get(0).getE());
//						if(cs != null) {
//							if(cs.getA() != null) {
//								route.setTransmissionSection(cs.getA());
//								route2.setTransmissionSection(cs.getA());
//							}
//							if(cs.getB() != null) {
//								route.setTransmissionSystem(cs.getB());
//								route2.setTransmissionSystem(cs.getB());
//							}
//							
//						}
						
						TnExport teCSD = null;
						
						for(TnExport te1 : list1) {	
							teCSD = te1;
							//route.setRouteId(Long.parseLong(te1.getD().substring(0,te1.getD().indexOf("."))));
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
							//route2.setRouteId(Long.parseLong(te2.getD().substring(0,te2.getD().indexOf("."))));
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
//						if(cs != null) {
//							if(cs.getD() != null) {
//								route.setRouteText(cs.getD()+"<==>"+route.getRouteText());
//								route2.setRouteText(cs.getD()+"<==>"+route2.getRouteText());
//							}
//							if(cs.getF() != null) {
//								route.setRouteText(route.getRouteText()+"<==>"+cs.getF());
//								route2.setRouteText(route2.getRouteText()+"<==>"+cs.getF());
//							}
//						}
						
						if(teCSD!= null) {
							if(teCSD.getK() != null) {
								route.setTransmissionSection(teCSD.getK());
								route2.setTransmissionSection(teCSD.getK());
							}
							if(teCSD.getL() != null) {
								route.setTransmissionSystem(teCSD.getL());
								route2.setTransmissionSystem(teCSD.getL());
							}
							if(teCSD.getM() != null) {
								route.setRouteText(teCSD.getM()+"<==>"+route.getRouteText());
								route2.setRouteText(teCSD.getM()+"<==>"+route2.getRouteText());
							}
							if(teCSD.getN() != null) {
								route.setRouteText(route.getRouteText()+"<==>"+teCSD.getN());
								route2.setRouteText(route2.getRouteText()+"<==>"+teCSD.getN());
							}
						}
						
						routeList.add(route);
						routeList.add(route2);
						
						teCSD = null;
						
						route = null;
						route2 = null;
						list = null;
						list1 = null;
						list2 = null;
						list = new ArrayList<TnExport>();
						//sdhRoute = sdh.getD();
						sdhRoute = sdh.getE();
						list.add(sdh);
					}
				}
			}
			
			//wdm
			List<TnExport> WDMlist = tnExportMapper.selectByparamByType("wdm");
			//tnExportMapper.deleteByParam("SDH");

			String wdmRoute = "";
			for(int n=0;n<WDMlist.size();n++) {
				TnExport sdh = null;
				if(n+1 == WDMlist.size()) {
					sdh = WDMlist.get(n);
					if(wdmRoute.equals(sdh.getE())) {
						list.add(sdh);
					}
					wdmRoute="1";
				}else {
					sdh = WDMlist.get(n);
				}
				if("".equals(wdmRoute)) {
					list = new ArrayList<TnExport>();
					wdmRoute = sdh.getE();
					list.add(sdh);
				}else {
					if(wdmRoute.equals(sdh.getE())) {
						list.add(sdh);
					}else {
						route = new RouteTmp();
						route2 = new RouteTmp();
							
						if(list.size()<3) {
							route.setIsSameDistrict("WDM一根跳纤");
							route2.setIsSameDistrict("WDM一根跳纤");
						}else if(list.size()%2==1){
							route.setIsSameDistrict("WDM存在单根");
							route2.setIsSameDistrict("WDM存在单根");
						}else {
							route.setIsSameDistrict("WDM正常");
							route2.setIsSameDistrict("WDM正常");
						}
						route.setAreaCode1("全球通汇聚区");
						route2.setAreaCode1("全球通汇聚区");
							
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
						
//						TnExport cs = tnExportMapper.selectByRouteName(list1.get(0).getE());
//						if(cs != null) {
//							if(cs.getA() != null) {
//								route.setTransmissionSection(cs.getA());
//								route2.setTransmissionSection(cs.getA());
//							}
//							if(cs.getB() != null) {
//								route.setTransmissionSystem(cs.getB());
//								route2.setTransmissionSystem(cs.getB());
//							}
//							
//						}
						
						TnExport teCSD = null;
						
						for(TnExport te1 : list1) {	
							teCSD = te1;
							//route.setRouteId(Long.parseLong(te1.getD().substring(0,te1.getD().indexOf("."))));
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
							//route2.setRouteId(Long.parseLong(te2.getD().substring(0,te2.getD().indexOf("."))));
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
//						if(cs != null) {
//							if(cs.getD() != null) {
//								route.setRouteText(cs.getD()+"<==>"+route.getRouteText());
//								route2.setRouteText(cs.getD()+"<==>"+route2.getRouteText());
//							}
//							if(cs.getF() != null) {
//								route.setRouteText(route.getRouteText()+"<==>"+cs.getF());
//								route2.setRouteText(route2.getRouteText()+"<==>"+cs.getF());
//							}
//						}
						
						if(teCSD!= null) {
							if(teCSD.getK() != null) {
								route.setTransmissionSection(teCSD.getK());
								route2.setTransmissionSection(teCSD.getK());
							}
							if(teCSD.getL() != null) {
								route.setTransmissionSystem(teCSD.getL());
								route2.setTransmissionSystem(teCSD.getL());
							}
							if(teCSD.getM() != null) {
								route.setRouteText(teCSD.getM()+"<==>"+route.getRouteText());
								route2.setRouteText(teCSD.getM()+"<==>"+route2.getRouteText());
							}
							if(teCSD.getN() != null) {
								route.setRouteText(route.getRouteText()+"<==>"+teCSD.getN());
								route2.setRouteText(route2.getRouteText()+"<==>"+teCSD.getN());
							}
						}
						
						routeList.add(route);
						routeList.add(route2);
						
						teCSD = null;
						
						route = null;
						route2 = null;
						list = null;
						list1 = null;
						list2 = null;
						list = new ArrayList<TnExport>();
						wdmRoute = sdh.getE();
						//wdmRoute = sdh.getD();
						list.add(sdh);
					}
				}
			}
		
		//其它
			List<TnExport> QTlist = tnExportMapper.selectByparamByType("其它");
			
			String qtRoute = "";
			for(int m=0;m<QTlist.size();m++) {
				TnExport qt = null;
				if(m+1 == QTlist.size()) {
					qt = QTlist.get(m);
					//if(qtRoute.equals(qt.getD())) {
					if(qtRoute.equals(qt.getE())) {
						list.add(qt);
					}
					qtRoute="1";
				}else {
					qt = QTlist.get(m);
				}
				if("".equals(qtRoute)) {
					list = null;
					list = new ArrayList<TnExport>();
					qtRoute = qt.getE();
					//qtRoute = qt.getD();
					list.add(qt);
				}else {
					if(qtRoute.equals(qt.getE())) {
					//if(qtRoute.equals(qt.getD())) {
						list.add(qt);
					}else {
						route = new RouteTmp();
						if(list.size()<2) {
							route.setIsSameDistrict("其它一根跳纤");
						}else {
							route.setIsSameDistrict("其它");
						}
						route.setAreaCode1("全球通汇聚区");
						
						TnExport teCSD = null;
						
						for(TnExport te : list) {
							teCSD = te;
							//route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
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
						
						if(teCSD!= null) {
							if(teCSD.getK() != null) {
								route.setTransmissionSection(teCSD.getK());
							}
							if(teCSD.getL() != null) {
								route.setTransmissionSystem(teCSD.getL());
							}
							if(teCSD.getM() != null) {
								route.setRouteText(teCSD.getM()+"<==>"+route.getRouteText());
							}
							if(teCSD.getN() != null) {
								route.setRouteText(route.getRouteText()+"<==>"+teCSD.getN());
							}
						}
						teCSD = null;
						
						routeList.add(route);
						route = null;
						list = new ArrayList<TnExport>();
						qtRoute = qt.getE();
						//qtRoute = qt.getD();
						list.add(qt);
					}
				}
		
			}
			
			//空
			List<TnExport> Klist = tnExportMapper.selectByparamByType("");
			
			String kRoute = "";
			for(int m=0;m<Klist.size();m++) {
				TnExport qt = null;
				if(m+1 == Klist.size()) {
					qt = Klist.get(m);
					//if(kRoute.equals(qt.getD())) {
					if(kRoute.equals(qt.getE())) {
						list.add(qt);
					}
					kRoute="1";
				}else {
					qt = Klist.get(m);
				}
				if("".equals(kRoute)) {
					list = null;
					list = new ArrayList<TnExport>();
					kRoute = qt.getE();
					//kRoute = qt.getD();
					list.add(qt);
				}else {
					if(kRoute.equals(qt.getE())) {
					//if(kRoute.equals(qt.getD())) {
						list.add(qt);
					}else {
						route = new RouteTmp();
						if(list.size()<2) {
							route.setIsSameDistrict("空一根跳纤");
						}else {
							route.setIsSameDistrict("空");
						}
						route.setAreaCode1("全球通汇聚区");
						
						TnExport teCSD = null;
						
						for(TnExport te : list) {
							teCSD = te;
							//route.setRouteId(Long.parseLong(te.getD().substring(0,te.getD().indexOf("."))));
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
						
						if(teCSD!= null) {
							if(teCSD.getK() != null) {
								route.setTransmissionSection(teCSD.getK());
							}
							if(teCSD.getL() != null) {
								route.setTransmissionSystem(teCSD.getL());
							}
							if(teCSD.getM() != null) {
								route.setRouteText(teCSD.getM()+"<==>"+route.getRouteText());
							}
							if(teCSD.getN() != null) {
								route.setRouteText(route.getRouteText()+"<==>"+teCSD.getN());
							}
						}
						teCSD = null;
						
						routeList.add(route);
						route = null;
						list = new ArrayList<TnExport>();
						kRoute = qt.getE();
						//kRoute = qt.getD();
						list.add(qt);
					}
				}
		
			}
		
		return routeList;
	}


	@Override
	@Transactional
	public List<FacilityAll> labExport() {
		// TODO Auto-generated method stub
		List<FacilityAll> list = tnExportMapper.labExport();
		//List<FacilityAll> list=facilityMapper.queryall();
		for (int i = 0; i < list.size(); i++) {
			String str=list.get(i).getPort01();
			if(str!="") {
				Route route=routeMapper.queryCodes(str);
				if(route!=null) {
					list.get(i).setRouteText(route.getCodes());
				}
				route = null;	
			}
		}
		return list;
	}

	@Resource(name="fiberdiscGroupMapper")
	private FiberdiscGroupMapper fiberdiscGroupMapper;
	
	@Resource(name="twinfiberMapper")
	private TwinfiberMapper twinfiberMapper;

	@Resource(name="linesMapper")
	private LinesMapper linesMapper;
	
	@Resource(name="fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;
	
	@Resource(name="discinfoMapper")
	private DiscinfoMapper discinfoMapper;
	
	@Override
	@Transactional
	public int updateByGroupCode(String aside,String side,Long devId) {
		// TODO Auto-generated method stub
//		String aside = "A";
//		String side = "N";
//		Long devId=559L;
		List<FiberdiscGroup> list = tnExportMapper.FGlist(devId,aside);
		for(FiberdiscGroup group : list) {
				List<Discinfo> Dlist = discinfoMapper.selectByGroup(group.getGroupId());
				for(Discinfo disc : Dlist) {
					List<Fiberdisc> Flist = tnExportMapper.selectFiber(disc.getDiscCode());
					for(Fiberdisc fiberdisc : Flist) {
						List<Twinfiber> tfList = twinfiberMapper.selectByDevId(fiberdisc.getDevId());
						for(Twinfiber twinfiber : tfList) {
							twinfiber.setSide(side);
							twinfiber.setDiscCode(twinfiber.getDiscCode().substring(0, twinfiber.getDiscCode().indexOf("-")+1) + side + twinfiber.getDiscCode().substring(twinfiber.getDiscCode().lastIndexOf("-")));
							
							twinfiber.setPortCode1(twinfiber.getDiscCode().substring(0, twinfiber.getDiscCode().indexOf("-")+1) + side + twinfiber.getDiscCode().substring(twinfiber.getDiscCode().indexOf("-",twinfiber.getDiscCode().indexOf("-")+1)));
							twinfiber.setPortCode2(twinfiber.getDiscCode().substring(0, twinfiber.getDiscCode().indexOf("-")+1) + side + twinfiber.getDiscCode().substring(twinfiber.getDiscCode().indexOf("-",twinfiber.getDiscCode().indexOf("-")+1)));
							twinfiberMapper.updateByPrimaryKeySelective(twinfiber);
						}
						
						List<Lines> Llist = linesMapper.queryByPoint(fiberdisc.getPort01());
						for(Lines line : Llist) {
							
							if(fiberdisc.getPort01().equals(line.getAcode())) {
								line.setAcode(line.getAcode().substring(0, line.getAcode().indexOf("-")+1) + side + line.getAcode().substring(line.getAcode().indexOf("-",line.getAcode().indexOf("-")+1)));
							}else {
								line.setZcode(line.getZcode().substring(0, line.getZcode().indexOf("-")+1) + side + line.getZcode().substring(line.getZcode().indexOf("-",line.getZcode().indexOf("-")+1)));
							}
							linesMapper.updateByPrimaryKeySelective(line);
						}
						fiberdisc.setSide(side);
						fiberdisc.setDiscCode(fiberdisc.getDiscCode().substring(0, fiberdisc.getDiscCode().indexOf("-")+1) + side + fiberdisc.getDiscCode().substring(fiberdisc.getDiscCode().lastIndexOf("-")));
						System.out.println(fiberdisc.getDiscCode());
						fiberdisc.setPort01(fiberdisc.getPort01().substring(0, fiberdisc.getPort01().indexOf("-")+1) + side + fiberdisc.getPort01().substring(fiberdisc.getPort01().indexOf("-",fiberdisc.getPort01().indexOf("-")+1)));
						fiberdiscMapper.updateByPrimaryKeySelective(fiberdisc);
					}
					disc.setSide(side);
					disc.setDiscCode(disc.getDiscCode().substring(0, disc.getDiscCode().indexOf("-")+1) + side + disc.getDiscCode().substring(disc.getDiscCode().lastIndexOf("-")));
					discinfoMapper.updateByPrimaryKeySelective(disc);
					
				}
				group.setSide(side);
				fiberdiscGroupMapper.updateByPrimaryKeySelective(group);
		}
		return 0;
	}
	
	/**
	 * 按点生成光路
	 */
	@Override
	@Transactional
	public int getAnyPortFiberRouteText(String code, Long orgId) {
		// TODO Auto-generated method stub
		//查询是否存在跳纤 即是否可生成光路
		int ret = linesTmpMapper.selectExitTXbyCode(code,orgId);
		if(ret < 1) {
			throw new FrmsException("端子"+code+"不存在光路!");
		}
		return 0;
	}
	
	/**
	 * 单点生成光路
	 * @author: YHT
	 * @date: 2017年9月26日 下午1:42:06 
	 * @Title: singlePointGenOptPath  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	@Override
	@Transactional
	public int singlePointGenOptPath(String code, Long orgId) {
		return singlePointGenOptPathHandler(code, orgId);
	}
	
	@Override
	@Transactional
	@Async
	public void asyncSinglePointGenOptPath(String code, Long orgId) {
		singlePointGenOptPathHandler(code, orgId);
	}
	
	/**
	 * 单点生成光路
	 */
	private int singlePointGenOptPathHandler(String code, Long orgId) {
		// TODO Auto-generated method stub
		int ret = 0;
		//查询该端子所在光路的最顶点  0 0 非FG
		String topCode = selectTopCodeByCodeTX(code,orgId,0,0);
		String isFG = topCode.substring(topCode.indexOf(",")+1);
		if(",0".equals(topCode)) {
			//该端子未成端也未被占用
			throw new FrmsException("该端子未成端也未被占用不能生成光路！");
		}else if("0".equals(isFG)) {
			String retCode  =  selectTopCodeByCodeTX(topCode.substring(0,topCode.indexOf(",")),orgId,0,0);
			
			if(retCode.indexOf("-FG01-A-") != -1 || retCode.indexOf("-FG02-A-") != -1) {
				topCode = topCode.substring(0,topCode.indexOf(","));
			}else {
				topCode = retCode.substring(0,retCode.indexOf(","));
			}
		}else {
			topCode = topCode.substring(0,topCode.indexOf(","));
		}

		List<RouteTmp> listAll = new ArrayList<RouteTmp>(); 
		RouteTmp rt = new RouteTmp();
		rt.setAreaCode1("");
		//光路区域集合
		String areaCodes = "";
		LinesTmp ltGX = linesTmpMapper.selectGXByCode01(topCode,orgId);
		if(ltGX!=null && ltGX.getRouteCode()!=null) {
			rt.setAdevId(ltGX.getAdevId());
			rt.setAcode(ltGX.getRouteCode());
			if(ltGX.getOtherRouteNoteName() != null) {
				aotherName = ltGX.getOtherRouteNoteName();
				rt.setAotherName(ltGX.getOtherRouteNoteName());	
				rt.setRouteText(ltGX.getOtherRouteNoteName() +"<==>"+ltGX.getRouteNoteName());
			}else {
				aotherName = ltGX.getRouteNoteName();
				rt.setAotherName(ltGX.getRouteNoteName());	
				rt.setRouteText(ltGX.getRouteNoteName());
			}
			areaCodes = getAreaCodes(areaCodes,ltGX.getAareaCode(),ltGX.getZareaCode());
			rt.setAreaCode1(areaCodes);
			//rt = SelectRouteByGX_ONE(ltGX.getRouteCode(),rt,ltGX,orgId,areaCodes);
			topCode = ltGX.getRouteCode();
		}else {
			if(ltGX!=null && ltGX.getRouteCode()==null) {
				aotherName = ltGX.getRouteNoteName();
				rt.setAotherName(ltGX.getRouteNoteName());	
				rt.setAdevId(ltGX.getAdevId());
				rt.setAcode(ltGX.getRouteCode());
				rt.setRouteText(ltGX.getRouteNoteName() + "<==>" + ltGX.getOtherRouteNoteName());
				areaCodes = getAreaCodes(rt.getAreaCode1(),ltGX.getAareaCode(),ltGX.getZareaCode());
				rt.setAreaCode1(areaCodes);
			}
		}
		
		LinesTmp ltTX = linesTmpMapper.selectTXByCode01(topCode,orgId);
		if(ltTX == null) {
			//该端子无法生成光路   -- 不存在跳纤
			throw new FrmsException("该光路不存在跳纤不能生成光路！");
//			return -2;
		}else {
			if(ltTX.getRouteCode()==null) {
				if(ltGX == null) {
					throw new FrmsException("该端子尾纤悬空且未成端不能生成光路！");
				}else {
					//删除存在该端子的光路
					ret = routeMapper.deleteByCode(topCode);
					zotherName = ltTX.getOtherRouteNoteName();
					rt.setZdevId(ltTX.getAdevId());
					rt.setZcode(ltTX.getRouteCode());
					rt.setZotherName(ltTX.getOtherRouteNoteName());
				}
			}else {
				if(ltGX == null) {
					aotherName = ltTX.getOtherRouteNoteName();
					rt.setAotherName(ltTX.getOtherRouteNoteName());	
					rt.setZdevId(ltTX.getAdevId());
					rt.setZcode(ltTX.getRouteCode());
					rt.setRouteText(ltTX.getOtherRouteNoteName()+"<==>"+ltTX.getRouteNoteName());
					areaCodes = getAreaCodes(areaCodes,ltTX.getAareaCode(),ltTX.getZareaCode());
					rt.setAreaCode1(areaCodes);
				}else {
					areaCodes = getAreaCodes(areaCodes,ltTX.getAareaCode(),ltTX.getZareaCode());
					rt.setAreaCode1(areaCodes);
					rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getRouteNoteName() );
				}
				ret = routeMapper.deleteByCode(ltTX.getRouteCode());
				rt = SelectRouteByGX_ONE(ltTX.getRouteCode(),rt,ltTX,orgId,areaCodes);
			}
		}
		if(rt.getIsFG()==0 || rt.getAreaCode0().indexOf("-FG01-B-")!=-1) {
			rt.setRouteName(rt.getAotherName() + "<==>" + rt.getZotherName());
			listAll.add(rt);
			areaCodes = "";
		}else {
			List<LinesTmp> ltFGList = linesTmpMapper.selectFGByCode(rt.getAreaCode0().replace("B", "A"),orgId);
			RouteTmp routeTmp = null;
			for(LinesTmp linesTmp : ltFGList) {
				routeTmp = new RouteTmp();
				areaCodes = getAreaCodes(areaCodes,linesTmp.getAareaCode(),null);
				routeTmp.setAreaCode1(areaCodes);
				routeTmp.setAotherName(rt.getAotherName());
				routeTmp.setAdevId(linesTmp.getAdevId());
				routeTmp.setAcode(linesTmp.getAcode());
				routeTmp.setRouteText(rt.getRouteText()+"<==>"+linesTmp.getAdevName()+"("+linesTmp.getZcode()+")");
				LinesTmp tx = linesTmpMapper.selectTXByCode01(linesTmp.getZcode(),orgId);
				if(tx == null) {
					zotherName = linesTmp.getAdevName()+"("+linesTmp.getZcode()+")";
					routeTmp.setZotherName(linesTmp.getAdevName()+"("+linesTmp.getZcode()+")");
					routeTmp.setZdevId(linesTmp.getZdevId());
					routeTmp.setZcode(linesTmp.getZcode());
					routeTmp.setRouteName(routeTmp.getAotherName() + "<==>" + routeTmp.getZotherName());
					listAll.add(routeTmp);
					areaCodes = "";
					routeTmp = null;
				}else{
					areaCodes = getAreaCodes(areaCodes,tx.getZareaCode(),null);
					routeTmp.setAreaCode1(areaCodes);
					if("0".equals(ltTX.getIsSameDistrict())) {
						isSameDistrict = "0";
					}
					//linesTmpMapper.deleteByCode(ltTX);
					if(tx.getRouteCode() == null) {
						if(tx.getAdevId()==null || tx.getZdevId()==null) {
							zotherName = tx.getRouteNoteName();
							if(tx.getRouteNoteName()==null) {
								zotherName = tx.getOtherRouteNoteName();
								routeTmp.setZotherName(tx.getOtherRouteNoteName());
							}else {
								zotherName = tx.getRouteNoteName();
								routeTmp.setZotherName(tx.getRouteNoteName());
								routeTmp.setRouteText(routeTmp.getRouteText() + "<==>" + tx.getRouteNoteName() );
							}
							
							routeTmp.setZdevId(tx.getAdevId());
							routeTmp.setZcode(tx.getRouteCode());
							routeTmp.setRouteName(routeTmp.getAotherName() + "<==>" + routeTmp.getZotherName());
							listAll.add(routeTmp);
							areaCodes = "";
							routeTmp = null;
							continue;
						}
						if(tx.getAcode()==null) {
							zotherName = tx.getAdevName();
							routeTmp.setZdevId(tx.getAdevId());
							routeTmp.setZcode(null);
							routeTmp.setZotherName(tx.getAdevName());
							routeTmp.setRouteText(routeTmp.getRouteText() + "<==>" + tx.getAdevName() );
						}else {
							zotherName = tx.getZdevName();
							routeTmp.setZotherName(tx.getZdevName());
							routeTmp.setZdevId(tx.getAdevId());
							routeTmp.setZcode(null);
							routeTmp.setRouteText(routeTmp.getRouteText() + "<==>" + tx.getZdevName() );
						}
								
					}else {
						routeMapper.deleteByCode(tx.getRouteCode());
						routeTmp.setRouteText(routeTmp.getRouteText() + "<==>" + tx.getRouteNoteName());
						routeTmp = SelectRouteByGX_ONE(tx.getRouteCode(),routeTmp,tx,orgId,areaCodes);
					}
					routeTmp.setRouteName(routeTmp.getAotherName() + "<==>" + routeTmp.getZotherName());
					listAll.add(routeTmp);
					areaCodes = "";
					routeTmp = null;
				}
			}
		}
		Route route = null;
		for(RouteTmp rtTmp : listAll) {
			route = new Route();
			route.setAotherName(rtTmp.getAotherName());
			route.setZotherName(rtTmp.getZotherName());
			route.setAdevId(rtTmp.getAdevId());
			route.setAcode(rtTmp.getAcode());
			route.setZdevId(rtTmp.getZdevId());
			route.setZcode(rtTmp.getZcode());
			route.setCodes("");
			route.setRouteName(rtTmp.getRouteName());  
			route.setOrgId(orgId);
			route.setAreaCode1(rtTmp.getAreaCode1());
			route.setCreateTime(new Date());
			route.setRoutetext(rtTmp.getRouteText());
			ret = routeMapper.insertSelective(route);
			if(ret < 1) {
				break;
			}
			route = null;
		}
		return ret;
	}
	
	/**
	 * 向下生成光路  -- 单点生成专用
	 * @author: YHT
	 * @date: 2017年10月11日 下午1:16:30 
	 * @Title: SelectRouteByGX_ONE  
	 * @param @param code
	 * @param @param rt
	 * @param @param lt
	 * @param @param orgId
	 * @param @param areaCodes
	 * @param @return     
	 * @return RouteTmp   
	 * @throws
	 */
	private RouteTmp SelectRouteByGX_ONE(String code,RouteTmp rt,LinesTmp lt,Long orgId,String areaCodes) {
		//判断是否为分光器
		if(isFG(code)) {
			//分光器直接跳出
			rt.setIsFG(1);
			rt.setAreaCode0(code);
			zotherName = lt.getRouteNoteName();
			rt.setZotherName(lt.getRouteNoteName());
			rt.setZdevId(lt.getAdevId());
			rt.setZcode(lt.getRouteCode());
		}else {
			LinesTmp ltGX = linesTmpMapper.selectGXByCode01(code,orgId);
			if(ltGX == null) {
				zotherName = lt.getRouteNoteName();
				rt.setZotherName(lt.getRouteNoteName());
				rt.setZdevId(lt.getAdevId());
				rt.setZcode(lt.getRouteCode());
				routeMapper.deleteByCode(code);
				return rt;
			}else {
				if("0".equals(ltGX.getIsSameDistrict())) {
					isSameDistrict = "0";
				}
				areaCodes = getAreaCodes(areaCodes,ltGX.getAareaCode(),ltGX.getZareaCode());
				if(ltGX.getRouteCode() == null) {
					if(ltGX.getAdevId()==null || ltGX.getZdevId()==null) {
						if(ltGX.getRouteNoteName()==null) {
							zotherName = ltGX.getOtherRouteNoteName();
							rt.setZotherName(ltGX.getOtherRouteNoteName());
						}else {
							zotherName = ltGX.getRouteNoteName();
							rt.setZotherName(ltGX.getRouteNoteName());
							rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getRouteNoteName() );
						}
						
						rt.setZdevId(lt.getAdevId());
						rt.setZcode(lt.getRouteCode());
						rt.setAreaCode1(areaCodes);
						routeMapper.deleteByCode(code);
						return rt;
					}
					if(ltGX.getAcode()==null) {
						zotherName = ltGX.getAdevName();
						rt.setZotherName(ltGX.getAdevName());
						rt.setZdevId(ltGX.getAdevId());
						rt.setZcode(null);
						rt.setAreaCode1(areaCodes);
						rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getAdevName() );
					}else {
						zotherName = ltGX.getZdevName();
						rt.setZotherName(ltGX.getZdevName());
						rt.setZdevId(ltGX.getAdevId());
						rt.setZcode(null);
						rt.setAreaCode1(areaCodes);
						rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getZdevName() );
					}
					
				}else {
					rt.setAreaCode1(areaCodes);
					rt.setRouteText(rt.getRouteText() + "<==>" + ltGX.getRouteNoteName());
					LinesTmp ltTX = linesTmpMapper.selectTXByCode01(ltGX.getRouteCode(),orgId);
					
					if(ltTX == null) {
						zotherName = ltGX.getRouteNoteName();
						rt.setZotherName(ltGX.getRouteNoteName());
						rt.setZdevId(ltGX.getAdevId());
						rt.setZcode(ltGX.getRouteCode());
						routeMapper.deleteByCode(ltGX.getRouteCode());
						return rt;
					}else {
						if("0".equals(ltTX.getIsSameDistrict())) {
							isSameDistrict = "0";
						}
						areaCodes = getAreaCodes(areaCodes,ltTX.getAareaCode(),ltTX.getZareaCode());
						//linesTmpMapper.deleteByCode(ltTX);
						if(ltTX.getRouteCode() == null) {
							if(ltTX.getAdevId()==null || ltTX.getZdevId()==null) {
								if(ltTX.getRouteNoteName()==null) {
									zotherName = ltTX.getOtherRouteNoteName();
									rt.setZotherName(ltTX.getOtherRouteNoteName());
								}else {
									zotherName = ltTX.getRouteNoteName();
									rt.setZotherName(ltTX.getRouteNoteName());
									rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getRouteNoteName() );
								}
								rt.setZdevId(ltTX.getAdevId());
								rt.setZcode(ltTX.getRouteCode());
								rt.setAreaCode1(areaCodes);
								routeMapper.deleteByCode(ltTX.getRouteCode());
								return rt;
							}
							if(ltTX.getAcode()==null) {
								zotherName = ltTX.getAdevName();
								rt.setZotherName(ltTX.getAdevName());
								rt.setZdevId(ltTX.getAdevId());
								rt.setZcode(ltTX.getRouteCode());
								rt.setAreaCode1(areaCodes);
								rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getAdevName() );
								routeMapper.deleteByCode(ltTX.getZcode());
							}else {
								zotherName = ltTX.getZdevName();
								rt.setZotherName(ltTX.getZdevName());
								rt.setZdevId(ltTX.getAdevId());
								rt.setZcode(ltTX.getRouteCode());
								rt.setAreaCode1(areaCodes);
								rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getZdevName() );
								routeMapper.deleteByCode(ltTX.getAcode());
							}  
							
						}else {
							rt.setRouteText(rt.getRouteText() + "<==>" + ltTX.getRouteNoteName());
							rt.setAreaCode1(areaCodes);
							rt = SelectRouteByGX_ONE(ltTX.getRouteCode(),rt,ltTX,orgId,areaCodes);
						}
						
					}
				}
			}
		}
		
		return rt;
	}

	/**
	 * 查询该光路最顶点端子 - 跳纤
	 * @author: YHT
	 * @date: 2017年10月10日 下午1:47:30 
	 * @Title: selectTopCodeByCodeTX  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return String   
	 * @throws
	 */
	private String selectTopCodeByCodeTX(String code, Long orgId,int num,int isFG) {

		LinesTmp ltTX = linesTmpMapper.selectCodeByTX(code,orgId);
		if(ltTX==null || ltTX.getRouteCode()==null) {
			if(num == 0) {
				return selectTopCodeByCodeTX(selectTopCodeByCodeGX(code,orgId),orgId,num+1,isFG)+","+isFG;
			}else {
				return code+","+isFG;
			}
			
		}else {
			if(isFG(ltTX.getRouteCode())) {
				code = selectTopCodeByFG(ltTX.getRouteCode());
				code = selectTopCodeByCodeTX(code,orgId,num,1);
			}else {
				code = ltTX.getRouteCode();
				LinesTmp ltGX = linesTmpMapper.selectCodeByGX(code,orgId);
				if(ltGX!=null && ltGX.getRouteCode()!=null && !code.equals(ltGX.getRouteCode())) {
					code = selectTopCodeByCodeTX(ltGX.getRouteCode(),orgId,num+1,isFG);
				}else {
					return code+","+isFG;
				}
			}
		}
		return code+","+isFG;
	}
	
	/**
	 * 查询该光路最顶点端子 - 光纤
	 * @author: YHT
	 * @date: 2017年10月10日 下午1:47:30 
	 * @Title: selectTopCodeByCodeGX  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return String   
	 * @throws
	 */
	private String selectTopCodeByCodeGX(String code, Long orgId) {
		LinesTmp ltGX = linesTmpMapper.selectCodeByGX(code,orgId);
		if(ltGX!=null && ltGX.getRouteCode()!=null) {
			code = ltGX.getRouteCode();
		}else {
			code = null;
		}
		return code;
	}
	
	private String selectTopCodeByFG(String code) {
		if(code.indexOf("-B-") != -1) {
			return linesTmpMapper.selectCodeByFGB(code);
		}else if(code.indexOf("-00-00") != -1){
			return linesTmpMapper.selectCodeByFG00(code);
		}
		return code;
	}
	
	/**
	 * 是否为分光器上端子
	 * @author: YHT
	 * @date: 2017年9月26日 下午1:59:57 
	 * @Title: isFG  
	 * @param @param code
	 * @param @return     
	 * @return boolean   
	 * @throws
	 */
	private boolean isFG(String code) {
		boolean flag = false;
		if(code.indexOf("FG") != -1) {
			flag = true;
		}
		return flag;
	}


	/**
	 * 按设施生成光路
	 */
	@Override
	@Transactional
	public int rebulildDeviceRoute(Long devId, Long orgId) {
		// TODO Auto-generated method stub
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f == null) {
			throw new FrmsException("该设施不存在！");
		}
		List<Facility> facilityList = null;
		//处理机房类型
		if("20".equals(f.getDevType()) ) {
			facilityList = facilityMapper.selectByPdevId(devId);
		}else if("06".equals(f.getDevType())) {
			devId = f.getPdevId();
		}
		List<Fiberdisc> fList = null;
		if(facilityList!=null && facilityList.size()>0) {
			for(Facility facility : facilityList) {
				fList = fiberdiscMapper.selectByDevId(facility.getDevId());
				if(fList != null) {
					for(Fiberdisc fiberdisc : fList) {
						if(fiberdisc.getSectId()!=null) {
							singlePointGenOptPath(fiberdisc.getPort01(),orgId);
						}
					}
				}
				fList = null;
			}
		//处理非机房类型
		}else {
			fList = fiberdiscMapper.selectByDevId(devId);
			if(fList != null) {
				for(Fiberdisc fiberdisc : fList) {
					if(fiberdisc.getSectId()!=null) {
						singlePointGenOptPath(fiberdisc.getPort01(),orgId);
					}
				}
			}
		}
		
		return 1;
	}
	
	public List<TnExport> importZGErrorDatas_bk() {
		// TODO Auto-generated method stub
		String areaCode = "44130207";
		Long orgId = 12L;
		//创建接头
		List<TnExport> listJT = tnExportMapper.selectJT();
		Facility f = null;
		for(TnExport t : listJT) {
			if(facilityMapper.selectByDevNameZG(orgId, t.getO()+"-"+t.getM())==null ) {
				f = new Facility();
				f.setCreateTime(new Date());
				f.setAreaCode1(areaCode);
				f.setOrgId(orgId);
				f.setDevCode(siteCodeService.getNewDevCode());
				f.setDevType(Constants.FACILITTYPE.GLT.toString());
				f.setDevModel(t.getM().substring(0,t.getM().indexOf("."))); 
				f.setDevName(t.getO()+"-"+t.getM());
				//facilityService.save(f);
				facilityService.saveZG(f);
				f = null;
			}
		}
		
		//返回未导入数据
		List<TnExport> returnList = new ArrayList<TnExport>(); 
		
		List<TnExport> list = tnExportMapper.selectByparamByType(null);
		
		TnExport tnExport = null;
		
		//机房名称
		String jfName = null;
		Facility jfF = null;
		Long devId = null;
		//机柜名称
		String jgName = null;
		Facility jgF = null;
		//分组名称
		String groupName = null;
		String groupNameOld = null;
		FiberdiscGroup fjFG = null;
		//盘名称
		String discName = null;
		String discNameOld = null;
		Discinfo discinfo = null;
		//总盘数
		Integer discNum = 0;
		//每盘端子数 
		Integer portNum = 0;
		
		//端子名称
		String portName = null;
		String portNameNew = null;
		String portNameOld = null;
		Fiberdisc fiberdisc = null;
		//多ODF 端子无ODF  如    ODF01-02-03    A-01
		String discName1 = null;
		//光缆段名称
		String gldName = null;
		
		//
		for(TnExport export : list) {
			System.out.println(export.getD()+"==="+export.getF());
			//清楚重复数据
			List<TnExport> listT = tnExportMapper.selectByTnExport(export);
			if(listT != null && listT.size()>1) {
				for(TnExport te : listT) {
					if(export.getId()!=te.getId()) {
						te.setIsDel("1");
						tnExportMapper.updateByPrimaryKeySelective2(te);
						tnExportMapper.updateByPrimaryKeySelective(te);
					}
				}
			}
			//去除  微波设备  
			if("-1.0".equals(export.getK())) {
				returnList.add(export);
				continue;
			}
			if(export.getF()==null || "".equals(export.getF())) {
				returnList.add(export);
				continue;
			}
			if(export.getD().indexOf("机柜") != -1 && !export.getC().equals(jfName)) {
				jfName = export.getC();
				jfF = null;
				jfF = new Facility();
				jfF.setDevCode(siteCodeService.getNewDevCode());
				jfF.setAreaCode1(areaCode);
				jfF.setDevName(jfName);
				jfF.setDevType("20");
				jfF.setCreateTime(new Date());
				jfF.setOrgId(orgId);
				facilityMapper.insertSelective(jfF);
				devId = jfF.getDevId();
				facilityMapper.insertSelectiveZG(jfF);
			}
			if(export.getD().indexOf("机柜") == -1) {
				devId = null;
			}
			if(!export.getD().equals(jgName)) {
				jgName = export.getD();
				jgF = null;
				jgF = new Facility();
				jgF.setDevCode(siteCodeService.getNewDevCode());
				jgF.setAreaCode1(areaCode);
				jgF.setDevName(jgName);
				jgF.setCreateTime(new Date());
				jgF.setOrgId(orgId);
				jgF.setPdevId(devId);
				if(jgName.indexOf("机柜") != -1) {
					jgF.setDevType("05");
				}else if(jgName.indexOf("光交") != -1){
					jgF.setDevType("01");
				}else { 
					jgF.setDevType("03");
				}
				facilityMapper.insertSelective(jgF);
				facilityMapper.insertSelectiveZG(jgF);
			}
			if(export.getF().indexOf("/") != -1) {
				portName = export.getF().substring(export.getF().indexOf("/")+1);
			}else {  
				portName = export.getF();
			}
			if(portName.indexOf("===") != -1) {
				portName = portName.substring(portName.indexOf("===")+3);
			}
			portName = portName.replace("--", "-");
			//端子
			if(portName.indexOf("ODF")!=-1) {
				groupName = portName.substring(0,portName.indexOf("-"));
				if(groupName.length()==3) { 
					groupName = groupName + "01";
				}
			//设备名称
			}else if(export.getD().indexOf("ODF") != -1) {
				groupName = export.getD().substring(export.getD().indexOf("ODF"));
				//处理不带ODF 第几的数据
				if(groupName.length()==3) { 
					groupName = groupName + "01";
				}
				//处理多ODF  如 横沥汇聚-架位ACB3-ODF01-02-03   ODM01-E-003
				if(groupName.length() > 5) {
					if(portName.length()>6) {
						groupName = portName.substring(0,portName.indexOf("-"));
						//墨园基站-综合机柜02-ODF01/02/03  01-C-001
						if(groupName.length() < 4) {
							if(portName.indexOf("-")!=portName.lastIndexOf("-")) {
								groupName = "ODF" + groupName;
							}else {
								if(portName.indexOf("A") != -1) {
									groupName = "ODF01";
								}else if(portName.indexOf("B") != -1) {
									groupName = "ODF02";
								}else if(portName.indexOf("C") != -1) {
									groupName = "ODF03";
								}else if(portName.indexOf("D") != -1) {
									groupName = "ODF04";
								}else if(portName.indexOf("E") != -1) {
									groupName = "ODF05";
								}else if(portName.indexOf("F") != -1) {
									groupName = "ODF06";
								}else if(portName.indexOf("G") != -1) {
									groupName = "ODF07";
								}
							}
						}
					}else {
						groupName = "ODF01";
//						if(!portName.substring(portName.indexOf("-")).equalsIgnoreCase(discName1)) {
//						}
					}
				}
			//}else if(portName.length()<6) {
			}else{
				if(portName.indexOf("xn")!=-1) {
					groupName = "XN";
				}else {
					groupName = "A";
				}
			}
			//处理光缆段
			CableSection cs = cableSectionMapper.selectByNameZG(export.getL());
			Facility facility = null;
			if(cs==null) {
				cs = new CableSection();
				cs.setCreateTime(new Date());
				cs.setSectCode(siteCodeService.getNewSectCode());
				cs.setSecName(export.getL());
				cs.setFiberNum(Long.parseLong(export.getM().substring(0,export.getM().indexOf("."))));
				cs.setDevIdA(jgF.getDevId());
				cs.setDevCodeA(jgF.getDevCode());
				cs.setOrgId(jgF.getOrgId());
				if("光缆接头".equals(export.getN())) {
					facility = facilityMapper.selectByDevNameZG(orgId,export.getO()+"-"+export.getM());
					cs.setDevCodeZ(facility.getDevCode());
					cs.setDevIdZ(facility.getDevId());
				}else if("光缆接头".equals(export.getP())) {
					facility = facilityMapper.selectByDevNameZG(orgId,export.getQ()+"-"+export.getM());
					cs.setDevCodeZ(facility.getDevCode());
					cs.setDevIdZ(facility.getDevId());
				}
				cableSectionMapper.insertSelective(cs);
				cableSectionMapper.insertSelectiveZG(cs);
			}else {
				if("光缆接头".equals(export.getN())) {
					facility = facilityMapper.selectByDevNameZG(orgId,export.getO()+"-"+export.getM());
				}else if("光缆接头".equals(export.getP())) {
					facility = facilityMapper.selectByDevNameZG(orgId,export.getQ()+"-"+export.getM());
				}
				if(cs.getDevIdA()!=jgF.getDevId()) {
					cs.setDevIdZ(jgF.getDevId());
					cs.setDevCodeZ(jgF.getDevCode());
					cableSectionMapper.updateByPrimaryKeySelective(cs);
					cableSectionMapper.updateByPrimaryKeySelectiveZG(cs);
				}
			}
				
			
			//重新建立分组
			if(!groupName.equals(groupNameOld)) {
				if(groupNameOld != null) {
					fjFG.setDiscNum(discNum);
					fjFG.setPortNum(portNum);
					fiberdiscGroupMapper.updateByPrimaryKeySelective(fjFG);
					fiberdiscGroupMapper.updateByPrimaryKeySelectiveZG(fjFG);
				}
				fjFG = null;
				fjFG = new FiberdiscGroup();
				fjFG.setDevId(jgF.getDevId());
				fjFG.setSide(groupName);
				fjFG.setCreateTime(new Date());	
				
				fiberdiscGroupMapper.insertSelective(fjFG);
				fiberdiscGroupMapper.insertSelectiveZG(fjFG);
			}
			
			//获取盘名称
			if(portName.length()>6) {
				if(portName.indexOf("-") == portName.lastIndexOf("-")) {
					discName = "100";
				}else {
					discName = toDiscName(portName.substring(portName.indexOf("-")+1, portName.lastIndexOf("-")));
				}
			}else {
				//discName = toDiscName(portName.substring(0, portName.indexOf("-")));
				if("xn-".equals(portName)) {
					discName = "100";
				}else {
					discName = toDiscName(portName.substring(0, portName.indexOf("-")));
				}
			}
			
			if(!groupName.equals(groupNameOld) || !discName.equals(discNameOld)) {
				if(!groupName.equals(groupNameOld)) {
					discNum = 1;
				}else {
					++discNum;
				}
				if(discNameOld != null) {
					discinfo.setPortNum(portNum);
					discinfoMapper.updateByPrimaryKeySelective(discinfo);
					discinfoMapper.updateByPrimaryKeySelectiveZG(discinfo);
				}
				discinfo = null;
				discinfo = new Discinfo();
				discinfo.setCreateTime(new Date());
				discinfo.setDevId(jgF.getDevId());
				discinfo.setDiscCode(jgF.getDevCode()+"-"+groupName+"-"+discName);
				discinfo.setSide(groupName);
				discinfo.setGroupId(fjFG.getGroupId());
				discinfo.setDiscName(discName);
				discinfoMapper.insertSelective(discinfo);
				discinfoMapper.insertSelectiveZG(discinfo);
			}
			
			//获取端子名称 
			portNameNew = portName.substring(portName.lastIndexOf("-")+1);
			
			if(!portNameNew.equals(portNameOld)) {
				if(!discName.equals(discNameOld)) {
					portNum = 1;
				}else {
					++portNum;
				}
				fiberdisc = null;
				fiberdisc = new Fiberdisc();
				fiberdisc.setCreateTime(new Date());
				fiberdisc.setDevId(jgF.getDevId());
				fiberdisc.setDiscCode(discinfo.getDiscCode());
				fiberdisc.setSide(groupName);
				fiberdisc.setDiscRowNo(Long.parseLong(discName));
				fiberdisc.setDiscColNo(Long.parseLong(portNameNew));
				fiberdisc.setPort01(discinfo.getDiscCode()+"-"+portNameNew);
				fiberdisc.setSectId(cs.getSectId());
				fiberdisc.setFiberNum(Long.parseLong(export.getK().substring(0,export.getK().indexOf("."))));
				if(export.getG() != null) {
					fiberdisc.setRemark(export.getG()+"-"+export.getH());
				}
				fiberdiscMapper.insertSelective(fiberdisc);
				fiberdiscMapper.insertSelectiveZG(fiberdisc);
			}
			Lines line = linesMapper.selectBySectOrFib(cs.getSectId(),Long.parseLong(export.getK().substring(0,export.getK().indexOf("."))));
			
			if(line == null) {
				line = new Lines();
				line.setLineType(LineType.FIBER.toString());
				line.setAdevId(jgF.getDevId());
				line.setAcode(fiberdisc.getPort01());
				line.setSectId(cs.getSectId());
				line.setFiberNo(Long.parseLong(export.getK().substring(0,export.getK().indexOf("."))));
				line.setOrgId(jgF.getOrgId());
				line.setCreateTime(new Date());
				if(export.getG() != null && !"".equals(export.getG())) {
					line.setSrvName(export.getH());
					line.setCreateUser(Long.parseLong(export.getG().substring(0,export.getG().indexOf("."))));
				}
				
				if("光缆接头".equals(export.getN())) {
					line.setZcode(facility.getDevCode()+"-JTA-01-"+autoGenericCode(export.getK().substring(0,export.getK().indexOf("."))));
					line.setZdevId(facility.getDevId());
				}else if("光缆接头".equals(export.getP())) {
					line.setZcode(facility.getDevCode()+"-JTA-01-"+autoGenericCode(export.getK().substring(0,export.getK().indexOf("."))));
					line.setZdevId(facility.getDevId());
				}
				linesMapper.insertSelective(line);
				linesMapper.insertSelectiveZG(line);
			}else {
				line.setZdevId(jgF.getDevId());
				line.setZcode(fiberdisc.getPort01());
				linesMapper.updateByPrimaryKeySelective(line);
				linesMapper.updateByPrimaryKeySelectiveZG(line);
			}
			
			if(!"".equals(export.getG()) && !"".equals(export.getR())) {
				tnExport = null;
				tnExport = new TnExport();
				tnExport.setE(fiberdisc.getDevId()+"");
				tnExport.setJ(jgF.getDevCode()+"");
				if("光缆接头".equals(export.getN()) || "光缆接头".equals(export.getP())) {
					tnExport.setA(facility.getDevId()+"");
					tnExport.setB(facility.getDevName());
					tnExport.setC(facility.getDevCode()+"-JTB-01-"+autoGenericCode(export.getK().substring(0,export.getK().indexOf("."))));
				}
				tnExport.setD(export.getD());
				tnExport.setF(fiberdisc.getPort01());
				
				tnExport.setG(export.getG());
				tnExport.setH(export.getH());
				tnExport.setK(export.getK().substring(0,export.getK().indexOf(".")));
				tnExport.setL(export.getL());
				tnExport.setM(export.getM().substring(0,export.getM().indexOf(".")));
				tnExport.setN(export.getN());
				tnExport.setO(export.getO());
				tnExport.setP(export.getP());
				tnExport.setQ(export.getQ());
				tnExport.setR(export.getR().substring(0,export.getR().indexOf(".")));
				
				tnExportMapper.insertSelective01(tnExport);
			}
			
			discNameOld = discName;
			groupNameOld = groupName;
		}
  
		importTXDatas();
		return returnList;
	}
	
	private static String autoGenericCode(String code) {
		String result = Integer.parseInt(code)+"";
		if(result.length()==1) {
			result = "0" + result;
		}
		return result;
	}
	
	/**
	 * 验证是否为数字
	 * @author: YHT
	 * @date: 2017年10月23日 上午11:49:10 
	 * @Title: toDiscName  
	 * @param @param discName
	 * @param @return     
	 * @return String   
	 * @throws
	 */
	private static String toDiscName(String discName) {
		String reg = "^\\d{1,}$";
		Pattern pattern = Pattern.compile(reg);  
		Matcher matcher=pattern.matcher(discName);  
		if(matcher.matches()) {
			if(discName.length()>2) {
				discName = discName.substring(discName.length()-2);
			}
			return discName;
		}else {
			String name = "";
			if(discName.indexOf("A") != -1) {
				name = "01";
			}else if(discName.indexOf("B") != -1) {
				name = "02";
			}else if(discName.indexOf("C") != -1) {
				name = "03";
			}else if(discName.indexOf("D") != -1) {
				name = "04";
			}else if(discName.indexOf("E") != -1) {
				name = "05";
			}else if(discName.indexOf("F") != -1) {
				name = "06";
			}else if(discName.indexOf("G") != -1) {
				name = "07";
			}else if(discName.indexOf("H") != -1) {
				name = "08";
			}else if(discName.indexOf("I") != -1) {
				name = "09";
			}else if(discName.indexOf("J") != -1) {
				name = "10";
			}else if(discName.indexOf("K") != -1) {
				name = "11";
			}else if(discName.indexOf("L") != -1) {
				name = "12";
			}else if(discName.indexOf("M") != -1) {
				name = "13";
			}else if(discName.indexOf("N") != -1) {
				name = "14";
			}else if(discName.indexOf("O") != -1) {
				name = "15";
			}else if(discName.indexOf("P") != -1) {
				name = "16";
			}else if(discName.indexOf("Q") != -1) {
				name = "17";
			}else if(discName.indexOf("R") != -1) {
				name = "18";
			}else if(discName.indexOf("S") != -1) {
				name = "19";
			}else if(discName.indexOf("T") != -1) {
				name = "20";
			}else if(discName.indexOf("U") != -1) {
				name = "21";
			}else if(discName.indexOf("V") != -1) {
				name = "22";
			}else if(discName.indexOf("W") != -1) {
				name = "23";
			}else if(discName.indexOf("X") != -1) {
				name = "24";
			}else if(discName.indexOf("Y") != -1) {
				name = "25";
			}else if(discName.indexOf("Z") != -1) {
				name = "26";
			}else {
				name = "01";
			}
			return name;
		}
	}
	
	/**
	 * 导入资管跳纤数据
	 */
	@Override
	@Transactional
	public int importTXDatas() {
		// TODO Auto-generated method stub
		Long orgId = 12L;
		List<TnExport> list = tnExportMapper.selectTnExport01();
		//保存同一光路信息
		List<TnExport> listBF = null;
		Lines line = null;
		String routeId = "";
		//接头
		TnExport jtOne = null;
		TnExport jtTwo = null;
		
		//正常Z端
		TnExport teOne = null;
		TnExport teTwo = null;
		
		for(int k=0;k<list.size()+1;k++) {
			TnExport te = null;
			if(k==list.size()) {
				te = new TnExport();
				te.setG("0");
			}else {
				te = list.get(k);
			}
			if(!te.getG().equals(routeId)) {
				if(listBF != null) { 
					//是否连续
					if(listBF.size()>1 && isContinuity(listBF)) {
						//双芯同时走
						if(listBF.get(0).getL().equals(listBF.get(1).getL()) && !listBF.get(0).getK().equals(listBF.get(1).getK())) {
							if((Integer.parseInt(listBF.get(0).getR())%2==1 && listBF.size()>4) || (Integer.parseInt(listBF.get(0).getR())%2==0 && listBF.size()>2)) {
								for(int i=0;i<listBF.size();i++) {
									if(i%2==0) {
										if(jtOne != null) {
											//处理光缆段
											CableSection cs = cableSectionMapper.selectByNameZG(jtOne.getB()+"-"+listBF.get(i).getD());
											if(cs==null) {
												cs = new CableSection();
												cs.setCreateTime(new Date());
												cs.setSectCode(siteCodeService.getNewSectCode());
												cs.setSecName(jtOne.getB()+"-"+listBF.get(i).getD());
												if(Integer.parseInt(jtOne.getM())>Integer.parseInt(listBF.get(i).getM())) {
													cs.setFiberNum(Long.parseLong(jtOne.getM()));
												}else {
													cs.setFiberNum(Long.parseLong(listBF.get(i).getM()));
												}
												cs.setDevIdA(Long.parseLong(jtOne.getA()));
												cs.setDevCodeA(jtOne.getC().substring(0,jtOne.getC().indexOf("-")));
												cs.setOrgId(orgId);
												cs.setDevCodeZ(listBF.get(i).getJ());
												cs.setDevIdZ(Long.parseLong(listBF.get(i).getE()));
												cableSectionMapper.insertSelective(cs);
												cableSectionMapper.insertSelectiveZG(cs);
											}
											line = null;
											line = new Lines();
											if(Integer.parseInt(listBF.get(0).getR())%2==1) {
												line.setAcode(jtOne.getC());
												line.setAdevId(Long.parseLong(jtOne.getA()));
												line.setLineType(LineType.JUMPER.toString());
												if("光缆接头".equals(listBF.get(i).getN()) || "光缆接头".equals(listBF.get(i).getP())) {
													line.setZcode(listBF.get(i).getC());
													line.setZdevId(Long.parseLong(listBF.get(i).getA()));
												}else {
													line.setZcode(listBF.get(i).getF());
													line.setZdevId(Long.parseLong(listBF.get(i).getE()));
												}
											}else {
												line.setAcode(jtOne.getF());
												line.setAdevId(Long.parseLong(jtOne.getE()));
												line.setLineType(LineType.JUMPER.toString());
												line.setZcode(listBF.get(i).getF());
												line.setZdevId(Long.parseLong(listBF.get(i).getE()));
											}
											line.setCreateTime(new Date());
											line.setOrgId(orgId);
											line.setUnknownPointName(jtOne.getH());
											linesMapper.insertSelective(line);
											linesMapper.insertSelectiveZG(line);
											jtOne = null;
										}
												
										if(teOne != null) {
											line = null;
											line = new Lines();
											line.setAcode(teOne.getF());
											line.setAdevId(Long.parseLong(teOne.getE()));
											line.setLineType(LineType.JUMPER.toString());
											line.setCreateTime(new Date());
											line.setZcode(listBF.get(i).getF());
											line.setZdevId(Long.parseLong(listBF.get(i).getE()));
											line.setOrgId(orgId);
											line.setUnknownPointName(teOne.getH());
											linesMapper.insertSelective(line);
											linesMapper.insertSelectiveZG(line);
											teOne = null;
										}
										if("光缆接头".equals(listBF.get(i).getN()) || "光缆接头".equals(listBF.get(i).getP())) {
											jtOne = listBF.get(i);
										}else {
											if(Integer.parseInt(listBF.get(i).getR())>1 && Integer.parseInt(listBF.get(i).getR())%2==0) {
												teOne = listBF.get(i);
											}
										}
									}else {
										if(jtTwo != null) {
											//处理光缆段
											CableSection cs = cableSectionMapper.selectByNameZG(jtTwo.getB()+"-"+listBF.get(i).getD());
											if(cs==null) {
												cs = new CableSection();
												cs.setCreateTime(new Date());
												cs.setSectCode(siteCodeService.getNewSectCode());
												cs.setSecName(jtTwo.getB()+"-"+listBF.get(i).getD());
												if(Integer.parseInt(jtTwo.getM())>Integer.parseInt(listBF.get(i).getM())) {
													cs.setFiberNum(Long.parseLong(jtTwo.getM()));
												}else {
													cs.setFiberNum(Long.parseLong(listBF.get(i).getM()));
												}
												cs.setDevIdA(Long.parseLong(jtTwo.getA()));
												cs.setDevCodeA(jtTwo.getC().substring(0,jtTwo.getC().indexOf("-")));
												cs.setOrgId(orgId);
												cs.setDevCodeZ(listBF.get(i).getJ());
												cs.setDevIdZ(Long.parseLong(listBF.get(i).getE()));
												cableSectionMapper.insertSelective(cs);
												cableSectionMapper.insertSelectiveZG(cs);
											}
											line = null;
											line = new Lines();
											
											
											if(Integer.parseInt(listBF.get(0).getR())%2==1) {
												line.setAcode(jtTwo.getC());
												line.setAdevId(Long.parseLong(jtTwo.getA()));
												line.setLineType(LineType.JUMPER.toString());
												if("光缆接头".equals(listBF.get(i).getN()) || "光缆接头".equals(listBF.get(i).getP())) {
													line.setZcode(listBF.get(i).getC());
													line.setZdevId(Long.parseLong(listBF.get(i).getA()));
												}else {
													line.setZcode(listBF.get(i).getF());
													line.setZdevId(Long.parseLong(listBF.get(i).getE()));
												}
											}else {
												line.setAcode(jtTwo.getF());
												line.setAdevId(Long.parseLong(jtTwo.getE()));
												line.setLineType(LineType.JUMPER.toString());
												line.setZcode(listBF.get(i).getF());
												line.setZdevId(Long.parseLong(listBF.get(i).getE()));
											}
											
											line.setCreateTime(new Date());
											line.setOrgId(orgId);
											line.setUnknownPointName(jtTwo.getH());
											linesMapper.insertSelective(line);
											linesMapper.insertSelectiveZG(line);
											jtTwo = null;
										}
												
										if(teTwo != null) {
											line = null;
											line = new Lines();
											line.setAcode(teTwo.getF());
											line.setAdevId(Long.parseLong(teTwo.getE()));
											line.setLineType(LineType.JUMPER.toString());
											line.setCreateTime(new Date());
											line.setZcode(listBF.get(i).getF());
											line.setZdevId(Long.parseLong(listBF.get(i).getE()));
											line.setOrgId(orgId);
											line.setUnknownPointName(teTwo.getH());
											linesMapper.insertSelective(line);
											linesMapper.insertSelectiveZG(line);
											teTwo = null;
										}
										if("光缆接头".equals(listBF.get(i).getN()) || "光缆接头".equals(listBF.get(i).getP())) {
											jtTwo = listBF.get(i);
										}else {
											if(Integer.parseInt(listBF.get(i).getR())>1 && Integer.parseInt(listBF.get(i).getR())%2==0) {
												teTwo = listBF.get(i);
											}
										}
									}
								}
							}
						}else {
							for(int i=0;i<listBF.size();i++) {
								if(jtOne != null) {
									//处理光缆段
									CableSection cs = cableSectionMapper.selectByNameZG(jtOne.getB()+"-"+listBF.get(i).getD());
									if(cs==null) {
										cs = new CableSection(); 
										cs.setCreateTime(new Date());
										cs.setSectCode(siteCodeService.getNewSectCode());
										cs.setSecName(jtOne.getB()+"-"+listBF.get(i).getD());
										if(Integer.parseInt(jtOne.getM())>Integer.parseInt(listBF.get(i).getM())) {
											cs.setFiberNum(Long.parseLong(jtOne.getM()));
										}else {
											cs.setFiberNum(Long.parseLong(listBF.get(i).getM()));
										}
										cs.setDevIdA(Long.parseLong(jtOne.getA()));
										cs.setDevCodeA(jtOne.getC().substring(0,jtOne.getC().indexOf("-")));
										cs.setOrgId(orgId);
										cs.setDevCodeZ(listBF.get(i).getJ());
										cs.setDevIdZ(Long.parseLong(listBF.get(i).getE()));
										cableSectionMapper.insertSelective(cs);
										cableSectionMapper.insertSelectiveZG(cs);
									}
									line = null;
									line = new Lines();
									System.out.println(listBF.get(i).getC()+"======="+listBF.get(i).getL());
									if(Integer.parseInt(listBF.get(0).getR())%2==1) {
										line.setAcode(jtOne.getC());
										line.setAdevId(Long.parseLong(jtOne.getA()));
										line.setLineType(LineType.JUMPER.toString());
										if("光缆接头".equals(listBF.get(i).getN()) || "光缆接头".equals(listBF.get(i).getP())) {
											line.setZcode(listBF.get(i).getC());
											line.setZdevId(Long.parseLong(listBF.get(i).getA()));
										}else {
											line.setZcode(listBF.get(i).getF());
											line.setZdevId(Long.parseLong(listBF.get(i).getE()));
										}
										
									}else {
										line.setAcode(jtOne.getF());
										line.setAdevId(Long.parseLong(jtOne.getE()));
										line.setLineType(LineType.JUMPER.toString());
										line.setZcode(listBF.get(i).getF());
										line.setZdevId(Long.parseLong(listBF.get(i).getE()));
									}
									line.setCreateTime(new Date());
									line.setOrgId(orgId);
									line.setUnknownPointName(jtOne.getH());
									linesMapper.insertSelective(line);
									linesMapper.insertSelectiveZG(line);
									jtOne = null;
								}
										
								if(teOne != null) {
									line = null;
									line = new Lines();
									line.setAcode(teOne.getF());
									line.setAdevId(Long.parseLong(teOne.getE()));
									line.setLineType(LineType.JUMPER.toString());
									line.setCreateTime(new Date());
									line.setZcode(listBF.get(i).getF());
									line.setZdevId(Long.parseLong(listBF.get(i).getE()));
									line.setOrgId(orgId);
									line.setUnknownPointName(teOne.getH());
									linesMapper.insertSelective(line);
									linesMapper.insertSelectiveZG(line);
									teOne = null;
								}
								if("光缆接头".equals(listBF.get(i).getN()) || "光缆接头".equals(listBF.get(i).getP())) {
									jtOne = listBF.get(i);
								}else {
									if(Integer.parseInt(listBF.get(i).getR())>1 && Integer.parseInt(listBF.get(i).getR())%2==0) {
										teOne = listBF.get(i);
									}
								}
							}
						}
						jtOne = null;
						jtTwo = null;
						teOne = null;
						teTwo = null;
					}
					listBF = null;
				}
				listBF = new ArrayList<TnExport>();
				listBF.add(te);
				routeId = te.getG();
			} else {
				listBF.add(te);
			}
		}
		//删除TnExport01 中所有数据
		//tnExportMapper.deleteTnExport01();
		return 1;
	}
	
	/**
	 * 判断光路是否连续
	 * @author: YHT
	 * @date: 2017年10月23日 下午3:21:59 
	 * @Title: isContinuity  
	 * @param @param listBF
	 * @param @return     
	 * @return boolean   
	 * @throws
	 */
	private static boolean isContinuity(List<TnExport> listBF) {
		int before = 0;
		for(int j=0;j<listBF.size();j++) {
			int r = Integer.parseInt(listBF.get(j).getR());
			if(before == 0) {
				before = Integer.parseInt(listBF.get(j).getR());
			}
			if(r==before && r==(before+1)) {
				return false;
			}else {
				before = r;
			}
		}
		return true;
	}
	

	@Override
	@Transactional
	public List<TnExport> importFSDatas() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String areaCode = "440604";
				Long orgId = 16L;
				//创建接头
				List<TnExport> listJT = tnExportMapper.selectJT();
				Facility f = null;
				for(TnExport t : listJT) {
					if(facilityMapper.selectByDevNameZG(orgId, t.getO()+"-"+t.getM())==null ) {
						f = new Facility();
						f.setCreateTime(new Date());
						f.setAreaCode1(areaCode);
						f.setOrgId(orgId);
						f.setDevCode(siteCodeService.getNewDevCode());
						f.setDevType(Constants.FACILITTYPE.GLT.toString());
						f.setDevModel(t.getM()); 
						f.setDevName(t.getO()+"-"+t.getM());
						facilityService.save(f);
						facilityService.saveZG(f);
						f = null;
					}
				}
				
				//返回未导入数据
				List<TnExport> returnList = new ArrayList<TnExport>(); 
				
				List<TnExport> list = tnExportMapper.selectByparamByType(null);
				
				TnExport tnExport = null;
				
				//机房名称
				String jfName = null;
				Facility jfF = null;
				Long devId = null;
				//机柜名称
				String jgName = null;
				Facility jgF = null;
				//分组名称
				String groupName = null;
				String groupNameOld = null;
				FiberdiscGroup fjFG = null;
				//盘名称
				String discName = null;
				String discNameOld = null;
				Discinfo discinfo = null;
				//总盘数
				Integer discNum = 0;
				//每盘端子数 
				Integer portNum = 0;
				
				//端子名称
				String portName = null;
				String portNameNew = null;
				String portNameOld = null;
				Fiberdisc fiberdisc = null;
				//多ODF 端子无ODF  如    ODF01-02-03    A-01
				String discName1 = null;
				//光缆段名称
				String gldName = null;
				
				//
				for(TnExport export : list) {
					System.out.println(export.getD()+"==="+export.getF());
					//清楚重复数据
					List<TnExport> listT = tnExportMapper.selectByTnExport(export);
					if(listT != null && listT.size()>1) {
						for(TnExport te : listT) {
							if(export.getId()!=te.getId()) {
								te.setIsDel("1");
								tnExportMapper.updateByPrimaryKeySelective2(te);
								tnExportMapper.updateByPrimaryKeySelective(te);
							}
						}
					}
					//去除  微波设备  
					if("-1".equals(export.getK())) {
						returnList.add(export);
						continue;
					}
					if(export.getD().indexOf("GJ") == -1 && !export.getC().equals(jfName)) {
						jfName = export.getC();
						jfF = null;
						jfF = new Facility();
						jfF.setDevCode(siteCodeService.getNewDevCode());
						jfF.setAreaCode1(areaCode);
						jfF.setDevName(jfName);
						jfF.setDevType("20");
						jfF.setCreateTime(new Date());
						jfF.setOrgId(orgId);
						facilityMapper.insertSelective(jfF);
						devId = jfF.getDevId();
						facilityMapper.insertSelectiveZG(jfF);
					}
					if(export.getD().indexOf("GJ") != -1) {
						devId = null;
					}
					if(!export.getD().equals(jgName)) {
						jgName = export.getD();
						jgF = null;
						jgF = new Facility();
						jgF.setDevCode(siteCodeService.getNewDevCode());
						jgF.setAreaCode1(areaCode);
						jgF.setDevName(jgName);
						jgF.setCreateTime(new Date());
						jgF.setOrgId(orgId);
						jgF.setPdevId(devId);
						if(jgName.indexOf("光交")!=-1 || jgName.indexOf("GJ")!=-1){
							jgF.setDevType("01");
						}else { 
							jgF.setDevType("05");
						}
						facilityMapper.insertSelective(jgF);
						facilityMapper.insertSelectiveZG(jgF);
					}
					if(export.getF().indexOf("/") != -1) {
						portName = export.getF().substring(export.getF().indexOf("/")+1);
					}else {  
						portName = export.getF();
					}
					if(export.getF().indexOf("M0") != -1) {
						portName = export.getF().substring(export.getF().indexOf("M0"));
					}
					if(export.getF().substring(0, 3).indexOf("M0") != -1) {
						groupName = export.getF().substring(1,export.getF().indexOf("-"));
						//处理不带ODF 第几的数据
						if(groupName.length()==2) { 
							groupName = "ODF" + groupName;
						}else {
								groupName = "ODF01";
						}
					}else{
						if(portName.indexOf("xn")!=-1) {
							groupName = "XN";
						}else {
							groupName = "A";
						}
					}
					//处理光缆段
					CableSection cs = cableSectionMapper.selectByNameZG(export.getL());
					Facility facility = null;
					if(cs==null) {
						cs = new CableSection();
						cs.setCreateTime(new Date());
						cs.setSectCode(siteCodeService.getNewSectCode());
						cs.setSecName(export.getL());
						cs.setFiberNum(Long.parseLong(export.getM()));
						cs.setDevIdA(jgF.getDevId());
						cs.setDevCodeA(jgF.getDevCode());
						cs.setOrgId(jgF.getOrgId());
						if("光缆接头".equals(export.getN())) {
							facility = facilityMapper.selectByDevNameZG(orgId,export.getO()+"-"+export.getM());
							cs.setDevCodeZ(facility.getDevCode());
							cs.setDevIdZ(facility.getDevId());
						}else if("光缆接头".equals(export.getP())) {
							facility = facilityMapper.selectByDevNameZG(orgId,export.getQ()+"-"+export.getM());
							cs.setDevCodeZ(facility.getDevCode());
							cs.setDevIdZ(facility.getDevId());
						}
						cableSectionMapper.insertSelective(cs);
						cableSectionMapper.insertSelectiveZG(cs);
					}else {
						if("光缆接头".equals(export.getN())) {
							facility = facilityMapper.selectByDevNameZG(orgId,export.getO()+"-"+export.getM());
						}else if("光缆接头".equals(export.getP())) {
							facility = facilityMapper.selectByDevNameZG(orgId,export.getQ()+"-"+export.getM());
						}
						if(cs.getDevIdA()!=jgF.getDevId()) {
							cs.setDevIdZ(jgF.getDevId());
							cs.setDevCodeZ(jgF.getDevCode());
							cableSectionMapper.updateByPrimaryKeySelective(cs);
							cableSectionMapper.updateByPrimaryKeySelectiveZG(cs);
						}
					}
						
					
					//重新建立分组
					if(!groupName.equals(groupNameOld)) {
						if(groupNameOld != null) {
							fjFG.setDiscNum(discNum);
							fjFG.setPortNum(portNum);
							fiberdiscGroupMapper.updateByPrimaryKeySelective(fjFG);
							fiberdiscGroupMapper.updateByPrimaryKeySelectiveZG(fjFG);
						}
						fjFG = null;
						fjFG = new FiberdiscGroup();
						fjFG.setDevId(jgF.getDevId());
						fjFG.setSide(groupName);
						fjFG.setCreateTime(new Date());	
						
						fiberdiscGroupMapper.insertSelective(fjFG);
						fiberdiscGroupMapper.insertSelectiveZG(fjFG);
					}
					
					//获取盘名称
					if(portName.length()>6) {
						discName = toDiscName(portName.substring(portName.indexOf("-")+1, portName.lastIndexOf("-")));
					}else {
						//discName = toDiscName(portName.substring(0, portName.indexOf("-")));
						if("xn-".equals(portName)) {
							discName = "00";
						}else {
							discName = toDiscName(portName.substring(0, portName.indexOf("-")));
						}
					}
					
					if(!groupName.equals(groupNameOld) || !discName.equals(discNameOld)) {
						if(!groupName.equals(groupNameOld)) {
							discNum = 1;
						}else {
							++discNum;
						}
						if(discNameOld != null) {
							discinfo.setPortNum(portNum);
							discinfoMapper.updateByPrimaryKeySelective(discinfo);
							discinfoMapper.updateByPrimaryKeySelectiveZG(discinfo);
						}
						discinfo = null;
						discinfo = new Discinfo();
						discinfo.setCreateTime(new Date());
						discinfo.setDevId(jgF.getDevId());
						discinfo.setDiscCode(jgF.getDevCode()+"-"+groupName+"-"+discName);
						discinfo.setSide(groupName);
						discinfo.setGroupId(fjFG.getGroupId());
						discinfo.setDiscName(discName);
						discinfoMapper.insertSelective(discinfo);
						discinfoMapper.insertSelectiveZG(discinfo);
					}
					
					//获取端子名称 
					portNameNew = portName.substring(portName.lastIndexOf("-")+1);
					
					if(!portNameNew.equals(portNameOld)) {
						if(!discName.equals(discNameOld)) {
							portNum = 1;
						}else {
							++portNum;
						}
						fiberdisc = null;
						fiberdisc = new Fiberdisc();
						fiberdisc.setCreateTime(new Date());
						fiberdisc.setDevId(jgF.getDevId());
						fiberdisc.setDiscCode(discinfo.getDiscCode());
						fiberdisc.setSide(groupName);
						fiberdisc.setDiscRowNo(Long.parseLong(discName));
						fiberdisc.setDiscColNo(Long.parseLong(portNameNew));
						fiberdisc.setPort01(discinfo.getDiscCode()+"-"+portNameNew);
						fiberdisc.setSectId(cs.getSectId());
						if(export.getG() != null) {
							fiberdisc.setRemark(export.getG()+"-"+export.getH());
						}
						fiberdiscMapper.insertSelective(fiberdisc);
						fiberdiscMapper.insertSelectiveZG(fiberdisc);
					}
					Lines line = linesMapper.selectBySectOrFib(cs.getSectId(),Long.parseLong(export.getK()));
					
					if(line == null) {
						line = new Lines();
						line.setLineType(LineType.FIBER.toString());
						line.setAdevId(jgF.getDevId());
						line.setAcode(fiberdisc.getPort01());
						line.setSectId(cs.getSectId());
						line.setFiberNo(Long.parseLong(export.getK()));
						line.setOrgId(jgF.getOrgId());
						line.setCreateTime(new Date());
						if(export.getG() != null && !"".equals(export.getG())) {
							line.setSrvName(export.getH());
							line.setCreateUser(Long.parseLong(export.getG()));
						}
						
						if("光缆接头".equals(export.getN())) {
							line.setZcode(facility.getDevCode()+"-JTA-01-"+autoGenericCode(export.getK()));
							line.setZdevId(facility.getDevId());
						}else if("光缆接头".equals(export.getP())) {
							line.setZcode(facility.getDevCode()+"-JTA-01-"+autoGenericCode(export.getK()));
							line.setZdevId(facility.getDevId());
						}
						linesMapper.insertSelective(line);
						linesMapper.insertSelectiveZG(line);
					}else {
						line.setZdevId(jgF.getDevId());
						line.setZcode(fiberdisc.getPort01());
						linesMapper.updateByPrimaryKeySelectiveZG(line);
					}
					
					if(!"".equals(export.getG()) && !"".equals(export.getR())) {
						tnExport = null;
						tnExport = new TnExport();
						tnExport.setId(export.getId());
						tnExport.setE(fiberdisc.getDevId()+"");
						tnExport.setJ(jgF.getDevCode()+"");
						if("光缆接头".equals(export.getN()) || "光缆接头".equals(export.getP())) {
							tnExport.setA(facility.getDevId()+"");
							tnExport.setB(facility.getDevName());
							tnExport.setC(facility.getDevCode()+"-JTB-01-"+autoGenericCode(export.getK()));
						}
						tnExport.setD(export.getD());
						tnExport.setF(fiberdisc.getPort01());
						
						tnExport.setG(export.getG());
						tnExport.setH(export.getH());
						tnExport.setK(export.getK());
						tnExport.setL(export.getL());
						tnExport.setM(export.getM());
						tnExport.setN(export.getN());
						tnExport.setO(export.getO());
						tnExport.setP(export.getP());
						tnExport.setQ(export.getQ());
						tnExport.setR(export.getR());
						
						tnExportMapper.insertSelective01(tnExport);
					}
					
					discNameOld = discName;
					groupNameOld = groupName;
				}
		return returnList;
	}
	
	/**
	 * 导入佛山设施数据
	 */
	@Override
	public int importFSSSInfo(String devType) {
		// TODO Auto-generated method stub
		String areaCode = "440604";
		Long orgId = 16L;
		int ret = 0;
		//设施信息
		List<TnExport> listSS = null;
				//tnExportMapper.selectExport03();

		Facility f = null;
		CableSection cs = null;
		double[] gps = null;
		double[] bd = null;
		if("19".equals(devType)) {
			listSS = tnExportMapper.selectExport19();
			for(TnExport te : listSS) {
				f = new Facility();
				gps = new double[2];
				bd = new double[2];
				f.setDevType("19");
				f.setDevName(te.getB());
				f.setZgDevName(te.getB());
				f.setAreaCode1(DataUtil.AreaCodeByAreaName(te.getD()));
				f.setDevCode(siteCodeService.getNewDevCode());
				f.setCreateTime(new Date());
				Double longitude = DataUtil.CheckLongitude(te.getG());
				Double latitude = DataUtil.CheckLatitude(te.getH());
				if(longitude!=null && latitude !=null) {
					gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
					f.setLongitude(gps[1]+"");
					f.setLatitude(gps[0]+"");
					
					bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
					f.setBaiduX(new BigDecimal(bd[1]+""));
					f.setBaiduY(new BigDecimal(bd[0]+""));
				}
				f.setOrgId(orgId);
				f.setFlag("1");
				f.setSurveyResult("0");
				f.setDevState("0");
				f.setDevDesc(te.getA());
				ret = facilityMapper.insertSelective(f);
				gps = null;
				bd = null;
			}
		}else if("20".equals(devType)) {
			listSS = tnExportMapper.selectExport20();
			for(TnExport te : listSS) {
				f = new Facility();
				gps = new double[2];
				bd = new double[2];
				f.setDevType("20");
				f.setDevName(te.getC());
				f.setZgDevName(te.getC());
				f.setAreaCode1(DataUtil.AreaCodeByAreaName(te.getD()));
				f.setDevCode(siteCodeService.getNewDevCode());
				f.setCreateTime(new Date());
				Double longitude = DataUtil.CheckLongitude(te.getE());
				Double latitude = DataUtil.CheckLatitude(te.getF());
				if(longitude!=null && latitude !=null) {
					gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
					f.setLongitude(gps[1]+"");
					f.setLatitude(gps[0]+"");
					
					bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
					f.setBaiduX(new BigDecimal(bd[1]+""));
					f.setBaiduY(new BigDecimal(bd[0]+""));
				}
				f.setOrgId(orgId);
				f.setFlag("1");
				f.setSurveyResult("0");
				f.setDevState("0");
				f.setDevDesc(te.getA());
				if(te.getB()!=null && !"".equals(te.getB())) {
					Facility fZD = facilityMapper.selectByDevDesc(te.getB());
					if(fZD != null) {
						f.setPdevId(fZD.getDevId());
					}
				}
				
				ret = facilityMapper.insertSelective(f);
				gps = null;
				bd = null;
			}
		}else if("11".equals(devType)) {
			listSS = tnExportMapper.selectExport11();
			for(TnExport te : listSS) {
				f = new Facility();
				gps = new double[2];
				bd = new double[2];
				f.setDevType("11");
				f.setZgDevName(te.getD());
				f.setDevName(te.getD());
				f.setAreaCode1(DataUtil.AreaCodeByAreaName(te.getA()));
				f.setDevCode(siteCodeService.getNewDevCode());
				f.setDevKind(DataUtil.WellByCode(te.getE()));
				f.setCreateTime(new Date());

				Double longitude = DataUtil.CheckLongitude(te.getF());
				Double latitude = DataUtil.CheckLatitude(te.getG());
				if(longitude!=null && latitude !=null) {
					gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
					f.setLongitude(gps[1]+"");
					f.setLatitude(gps[0]+"");
					
					bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
					f.setBaiduX(new BigDecimal(bd[1]+""));
					f.setBaiduY(new BigDecimal(bd[0]+""));
				}
				f.setOrgId(orgId);
				f.setFlag("1");
				f.setSurveyResult("0");
				f.setDevState("0");
				f.setDevDesc(te.getB());
				
//				Facility fZD = facilityMapper.selectByDevDesc(te.getA());
//				if(fZD != null) {
//					f.setPdevId(fZD.getDevId());
//				}
				ret = facilityMapper.insertSelective(f);
				gps = null;
				bd = null;
			}
		}else if("01".equals(devType)) {
			listSS = tnExportMapper.selectExport01();
			for(TnExport te : listSS) {
				f = new Facility();
				gps = new double[2];
				bd = new double[2];
				f.setDevType("01");
				f.setZgDevName(te.getB());
				f.setDevName(te.getB());
				f.setAreaCode1(DataUtil.AreaCodeByAreaName(te.getO()));
				f.setDevCode(siteCodeService.getNewDevCode());
				f.setCreateTime(new Date());
				Double longitude = DataUtil.CheckLongitude(te.getC());
				Double latitude = DataUtil.CheckLatitude(te.getD());
				if(longitude!=null && latitude !=null) {
					gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
					f.setLongitude(gps[1]+"");
					f.setLatitude(gps[0]+"");
					
					bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
					f.setBaiduX(new BigDecimal(bd[1]+""));
					f.setBaiduY(new BigDecimal(bd[0]+""));
				}
				f.setOrgId(orgId);
				f.setFlag("1");
				f.setSurveyResult("0");
				f.setDevState("0");
				f.setDevDesc(te.getA()); 
				
				if(te.getI()!=null && !"".equals(te.getI())) {
					Facility fZD = facilityMapper.selectByDevDesc(te.getI());
					if(fZD != null) {
						f.setPdevId(fZD.getDevId());
					}
				}
				
				ret = facilityMapper.insertSelective(f);
				gps = null;
				bd = null;
			}
		//分纤点信息
		}else if("00".equals(devType)) {
			listSS = tnExportMapper.selectExport00();
			for(TnExport te : listSS) {
				if(te.getA()!=null && !"".equals(te.getA())) {
					Facility fJF = facilityMapper.selectByDevDesc(te.getA());
					if(fJF != null) {
						//tnExportMapper.deleteBy00(te.getId());
						continue;
					}
				}
				f = new Facility();
				gps = new double[2];
				bd = new double[2];
				f.setDevType(DataUtil.devTypeConvert(te.getJ()));
				f.setZgDevName(te.getB());
				f.setDevName(te.getB());
				f.setAreaCode1(DataUtil.AreaCodeByAreaName(te.getC()));
				f.setDevCode(siteCodeService.getNewDevCode());
				f.setCreateTime(new Date());
				Double longitude = DataUtil.CheckLongitude(te.getR());
				Double latitude = DataUtil.CheckLatitude(te.getS());
				if(longitude!=null && latitude !=null) {
					gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
					f.setLongitude(gps[1]+"");
					f.setLatitude(gps[0]+"");
					
					bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
					f.setBaiduX(new BigDecimal(bd[1]+""));
					f.setBaiduY(new BigDecimal(bd[0]+""));
				}
				f.setOrgId(orgId);
				f.setFlag("1");
				f.setSurveyResult("0");
				f.setDevDesc(te.getA()); 
				f.setDevModel(te.getM());
				f.setDevState("0");
				//处理工程信息
				if(te.getH()!=null && !"".equals(te.getH())) {
					Project project=projectMapper.selectByProCode(te.getH());
					if(project != null) {
						f.setProId(project.getProId());
					}
				}
				//处理机柜信息
				if(te.getJ()!=null && "ODF".equals(te.getJ())) {
					Facility fJF = facilityMapper.selectByDevDesc(te.getF());
					if(fJF != null) {
						f.setPdevId(fJF.getDevId());
					}else {
						continue;
					}
				}
				
//				else if(te.getD()!=null && !"".equals(te.getD())) {
//					Facility fZD = facilityMapper.selectByDevDesc(te.getD());
//					if(fZD != null) {
//						f.setPdevId(fZD.getDevId());
//					}
//				}
//				tnExportMapper.deleteBy00(te.getId());
				ret = facilityMapper.insertSelective(f);
				gps = null;
				bd = null;
			}
		}else if("gl".equals(devType)) {
			listSS = tnExportMapper.selectExportgl();
			for(TnExport te : listSS) {
				Facility fA = null;
				Facility fZ = null;
				cs = new CableSection();
				cs.setCreateTime(new Date());
				cs.setSectCode(siteCodeService.getNewSectCode());
				cs.setFiberNum(DataUtil.CheckFiberNum(te.getE()));
				cs.setZgFiberNum(DataUtil.CheckFiberNum(te.getE()));
				if(te.getD()!=null && !"".equals(te.getD())) {
					cs.setCableLen(new BigDecimal(te.getD())); 
				}
				cs.setOrgId(orgId);
				cs.setSurveyResult("0");
				cs.setZgSecName(te.getB());
				cs.setSecName(te.getB());
				cs.setSectState("0");
				cs.setIsEndfibercable("0");
				cs.setRemark(te.getA());
				
				//处理光缆信息
//				if(te.getF() != null) {
//					Cable cable=cableMapper.selectByCableCode(te.getF());
//					if(cable != null) {
//						cs.setCableId(cable.getCableId());
//					}
//				}
				
				if(te.getI()!=null && !"".equals(te.getI().trim())) {
					if(te.getI().indexOf(".")!=-1) {
						fA = facilityMapper.selectByDevDesc(te.getI().substring(0, te.getI().indexOf(".")));
					}else {
						fA = facilityMapper.selectByDevDesc(te.getI());
					}
					
					if(fA != null) {
						cs.setZgDevIdA(fA.getDevId());
					}else {
						cs = null;
						continue;
					}
				}else {
					cs = null;
					continue;
				}   
				
				if(te.getM()!=null && !"".equals(te.getM().trim())) {
					if(te.getM().indexOf(".")!=-1) {
						fZ = facilityMapper.selectByDevDesc(te.getM().substring(0, te.getM().indexOf(".")));
					}else {
						fZ = facilityMapper.selectByDevDesc(te.getM());
					}
					
					if(fZ != null) {
						cs.setZgDevIdZ(fZ.getDevId());
					}else {
						cs = null;
						continue;
					}
				}else {
					cs = null;
					continue;
				}
				tnExportMapper.deleteByGl(te.getId());
				
				ret = cableSectionMapper.insertSelective(cs);
				//添加光缆描述信息 和 纤芯成端信息
				if(cs.getZgFiberNum() > 0) {
					CableSectionDec csd = null;
					Lines line = null;
					for(long i=1;i<=cs.getZgFiberNum();i++) {
						csd = new CableSectionDec();
						csd.setCreateTime(new Date());
						csd.setFiberState("1");
						csd.setOrgId(orgId);
						csd.setFiberNo(i);
						csd.setSectId(cs.getSectId());
						cableSectionDecMapper.insertSelective(csd);
						csd = null;
						
						line = new Lines();
						line.setCreateTime(new Date());
						line.setAdevId(fA.getDevId());
						line.setZdevId(fZ.getDevId());
						line.setSectId(cs.getSectId());
						line.setFiberNo(i);
						line.setOrgId(orgId);
						line.setSurveyResult("0");
						line.setLineType("01");
						linesMapper.insertSelective(line);
						line = null; 
					}
				}
				cs = null;
			}
		//挂靠光路信息
		}else if("guanglu".equals(devType)) {
			listSS = tnExportMapper.selectExportguanglu();
//			List<TnExport> teList = null;
//			String routeId = "";
//			for(int i=0;i<listSS.size();i++) {
//				teList = new ArrayList<TnExport>();
//				if(!routeId.equals(te.getC())) {
//					//处理第一条数据
//					if("".equals(routeId)) {
//						routeId = te.getC();
//						teList.add(te);
//					//处理光路
//					}else {
//						
//					}
//				//处理最后一条数据
//				}else if(i==listSS.size()-1){
//					
//				//处理相同的光路
//				}else {
//					teList.add(te);
//				}
				
				
//			}
			for(TnExport te : listSS) {
				//光缆段名称
				String sectName = te.getH();
				//纤芯序号
				String fiberNo = te.getJ();
				if(sectName==null || fiberNo==null) {
					continue;
				}
				CableSectionDec csd = cableSectionDecMapper.queryBySectNameAndFiberNo(sectName,Long.parseLong(fiberNo));
				if(csd != null) {
					csd.setZgRouteId(te.getC());
					csd.setZgRouteName(te.getD());
					csd.setZgSectDec(te.getA());
					csd.setZgRouteText(te.getK());
					cableSectionDecMapper.updateByPrimaryKeySelective(csd);
				}
			}
		}else if("huizhou".equals(devType)){
			orgId = 12L;
			areaCode = "44130207";
			String createTime = "2018-03-30 00:00:00";
			listSS = tnExportMapper.selectExport();
			String secName="";
			int flag = 0;
			Facility fA = null;
			Facility fZ = null;
			
			Facility jfA = null;
			Facility jfZ = null;
			String jfADevName = "";
			String jfZDevName = "";
			
			cs = null;
			for(TnExport te : listSS) {
				if(!secName.equals(te.getA())) {
					cableSectionMapper.selectByZgSecNameAndTime(orgId,te.getA(),createTime);
					if(cableSectionMapper.selectByZgSecNameAndTime(orgId,te.getA(),createTime) != null) {
						continue;
					}
					secName = te.getA();
					flag = 1;
				}else {
					flag = 0;
				}
				if(flag == 1) {
					if("ODF".equals(te.getG()) && !"".equals(te.getC()) && !jfADevName.equals(te.getC())) {
						jfA = facilityMapper.selectByDevNameZG(orgId, te.getC());
						if(jfA == null) {
							jfA = new Facility();
							jfA.setDevType("20");
							jfA.setZgDevName(te.getC());
							jfA.setDevName(te.getC());
							jfA.setAreaCode1(te.getH());
							jfA.setDevCode(siteCodeService.getNewDevCode());
							jfA.setCreateTime(new Date());
							Double longitude = DataUtil.CheckLongitude(te.getD());
							Double latitude = DataUtil.CheckLatitude(te.getE());
							if(longitude!=null && latitude !=null) {
								gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
								jfA.setLongitude(gps[1]+"");
								jfA.setLatitude(gps[0]+"");
								
								bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
								jfA.setBaiduX(new BigDecimal(bd[1]+""));
								jfA.setBaiduY(new BigDecimal(bd[0]+""));
							}
							jfA.setOrgId(orgId);
							jfA.setFlag("1");
							jfA.setSurveyResult("0");
							jfA.setDevDesc("1"); 
							jfA.setDevState("0");
							facilityMapper.insertSelective(jfA);
						}
					}else if(!"ODF".equals(te.getG()) || "".equals(te.getC())) {
						jfADevName = "";
						jfA = null;
					}
					
					//A端设施
					fA = facilityMapper.selectByDevNameZG(orgId, te.getF());
					if(fA == null) {
						fA = new Facility();
						
						if(jfA != null) {
							fA.setPdevId(jfA.getDevId());
						}
						
						fA.setDevType(DataUtil.devTypeConvert(te.getG()));
						fA.setZgDevName(te.getF());
						fA.setDevName(te.getF());
						fA.setAreaCode1(te.getH());
						fA.setDevCode(siteCodeService.getNewDevCode());
						fA.setCreateTime(new Date());       
						Double longitude = DataUtil.CheckLongitude(te.getD());
						Double latitude = DataUtil.CheckLatitude(te.getE());
						if(longitude!=null && latitude !=null) {
							gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
							fA.setLongitude(gps[1]+"");
							fA.setLatitude(gps[0]+"");
							
							bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
							fA.setBaiduX(new BigDecimal(bd[1]+""));
							fA.setBaiduY(new BigDecimal(bd[0]+""));
						}
						fA.setOrgId(orgId);
						fA.setFlag("1");
						fA.setSurveyResult("0");
						fA.setDevDesc("1"); 
						fA.setDevState("0");
						facilityMapper.insertSelective(fA);
					}
					
					if("ODF".equals(te.getQ()) && !"".equals(te.getO()) && !jfZDevName.equals(te.getO())) {
						jfZ = facilityMapper.selectByDevNameZG(orgId, te.getO());
						if(jfZ == null) {
							jfZ = new Facility();
							
							jfZ.setDevType("20");
							jfZ.setZgDevName(te.getO());
							jfZ.setDevName(te.getO());
							jfZ.setAreaCode1(te.getH());
							jfZ.setDevCode(siteCodeService.getNewDevCode());
							jfZ.setCreateTime(new Date());
							Double longitude = DataUtil.CheckLongitude(te.getR());
							Double latitude = DataUtil.CheckLatitude(te.getS());
							if(longitude!=null && latitude !=null) {
								gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
								jfZ.setLongitude(gps[1]+"");
								jfZ.setLatitude(gps[0]+"");
								
								bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
								jfZ.setBaiduX(new BigDecimal(bd[1]+""));
								jfZ.setBaiduY(new BigDecimal(bd[0]+""));
							}
							jfZ.setOrgId(orgId);
							jfZ.setFlag("1");
							jfZ.setSurveyResult("0");
							jfZ.setDevDesc("1"); 
							jfZ.setDevState("0");
							facilityMapper.insertSelective(jfZ);
						}
					}else if(!"ODF".equals(te.getQ()) || "".equals(te.getO())) {
						jfZDevName = "";
						jfZ = null;
					}
					
					//Z端设施
					fZ = facilityMapper.selectByDevNameZG(orgId, te.getP());
					if(fZ == null) {
						fZ = new Facility();
						
						if(jfZ != null) {
							fZ.setPdevId(jfZ.getDevId());
						}
						
						fZ.setDevType(DataUtil.devTypeConvert(te.getQ()));
						fZ.setZgDevName(te.getP());
						fZ.setDevName(te.getP());
						fZ.setAreaCode1(te.getH());
						fZ.setDevCode(siteCodeService.getNewDevCode());
						fZ.setCreateTime(new Date());
						Double longitude = DataUtil.CheckLongitude(te.getR());
						Double latitude = DataUtil.CheckLatitude(te.getS());
						if(longitude!=null && latitude !=null) {
							gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
							fZ.setLongitude(gps[1]+"");
							fZ.setLatitude(gps[0]+"");
							
							bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
							fZ.setBaiduX(new BigDecimal(bd[1]+""));
							fZ.setBaiduY(new BigDecimal(bd[0]+""));
						}
						fZ.setOrgId(orgId);
						fZ.setFlag("1");
						fZ.setSurveyResult("0");
						fZ.setDevDesc("1"); 
						fZ.setDevState("0");
						facilityMapper.insertSelective(fZ);
					}
					cs = cableSectionMapper.selectByNameZG(secName);
					if(cs == null) {
						cs = new CableSection();
						cs.setCreateTime(new Date());
						cs.setSectCode(siteCodeService.getNewSectCode());
						cs.setFiberNum(DataUtil.CheckFiberNum(te.getB()));
						cs.setZgFiberNum(DataUtil.CheckFiberNum(te.getB()));
						cs.setOrgId(orgId);
						cs.setSurveyResult("0");
						cs.setZgSecName(te.getA());
						cs.setSecName(te.getA());
						cs.setSectState("0");
						cs.setIsEndfibercable("0");
						cs.setZgDevIdA(fA.getDevId());
						cs.setDevIdA(fA.getDevId());
						cs.setDevIdZ(fZ.getDevId());
						cs.setZgDevIdZ(fZ.getDevId());
						ret = cableSectionMapper.insertSelective(cs);
						//添加光缆描述信息 和 纤芯成端信息
						if(cs.getZgFiberNum() > 0) {
							CableSectionDec csd = null;
							Lines line = null;
							for(long i=1;i<=cs.getZgFiberNum();i++) {
								csd = new CableSectionDec();
								csd.setCreateTime(new Date());
								csd.setFiberState("1");
								csd.setOrgId(orgId);
								csd.setFiberNo(i);
								csd.setSectId(cs.getSectId());
								cableSectionDecMapper.insertSelective(csd);
								csd = null;
								
								line = new Lines();
								line.setCreateTime(new Date());
								line.setAdevId(fA.getDevId());
								line.setZdevId(fZ.getDevId());
								line.setSectId(cs.getSectId());
								line.setFiberNo(i);
								line.setOrgId(orgId);
								line.setSurveyResult("0");
								line.setLineType("01");
								linesMapper.insertSelective(line);
								line = null; 
							}
						}
					}
				}
					//处理光缆段
					
				if(te.getN()==null || "".equals(te.getN()) || Long.parseLong(te.getN())>cs.getZgFiberNum()) {
					continue;
				}
				CableSectionDec csd = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(cs.getSectId(),Long.parseLong(te.getN()));
				if(csd != null) {
					csd.setZgRouteId("");
					csd.setZgRouteName(te.getI());
					csd.setZgSectDec(te.getJ());
					csd.setZgRouteText(te.getT());
					cableSectionDecMapper.updateByPrimaryKeySelective(csd);
				}
			}
		}else if("04".equals(devType)) {
			listSS = tnExportMapper.selectExport04();
			for(TnExport te : listSS) {
				Facility facility = facilityMapper.selectByDevName(orgId, te.getC());
				if(facility != null) {
					continue;
				}
				f = new Facility();
				gps = new double[2];
				bd = new double[2];
				f.setDevType("04");
				f.setZgDevName(te.getC());
				f.setDevName(te.getC());
				f.setAreaCode1("440600");
				f.setDevCode(siteCodeService.getNewDevCode());
				f.setCreateTime(new Date());
				Double longitude = DataUtil.CheckLongitude(te.getH());
				Double latitude = DataUtil.CheckLatitude(te.getI());
				if(longitude!=null && latitude !=null) {
					gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
					f.setLongitude(gps[1]+"");
					f.setLatitude(gps[0]+"");
					
					bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
					f.setBaiduX(new BigDecimal(bd[1]+""));
					f.setBaiduY(new BigDecimal(bd[0]+""));
				}
				f.setOrgId(orgId);
				f.setFlag("1");
				f.setSurveyResult("0");
				f.setDevState("0");
				f.setDevDesc(te.getA()); 
				
				ret = facilityMapper.insertSelective(f);
				gps = null;
				bd = null;
			}
		//导入井数据
		}else if("well".equals(devType)) {
			listSS = tnExportMapper.selectExportWell();
			Well well = null;
			for(TnExport te : listSS) {
				well = new Well();
				well.setCreateTime(new Date());
				well.setIsFormerbureau("0");
				well.setWellKind(DataUtil.WellByCode(te.getE()));
				well.setIsBranch("0");
				well.setProrightType("04");
				well.setWellState("0");
				well.setOrgId(16L);
				well.setWellType("01");
				well.setWellName(te.getD()); 
				well.setZgWellName(te.getD()); 
				well.setWellNumber(te.getC());
				well.setZgWellNumber(te.getC());
				well.setRemark(te.getB().replace(".0", ""));
				gps = new double[2];
				bd = new double[2];
				Double longitude = DataUtil.CheckLongitude(te.getF());
				Double latitude = DataUtil.CheckLatitude(te.getG());
				if(longitude!=null && latitude !=null) {
					gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
					well.setLongitude(gps[1]+"");
					well.setLatitude(gps[0]+"");
					
					bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
					well.setBaiduX(bd[1]+"");
					well.setBaiduY(bd[0]+"");
				}
				//well.set 
				wellMapper.insertSelective(well);
				well = null;
			}
		//导入管道段信息
		}else if("piping".equals(devType)) {
			listSS = tnExportMapper.selectExportPiping();
			PipingSection ps = null;
			PipingSectWell pswA = null;
			Piping pA = null;
			PipingSectWell pswZ = null;
			Piping pZ = null;
			for(TnExport te : listSS) {
				ps = new PipingSection();
				ps.setPipingName(te.getA());
				ps.setPipingSectType("0");
				ps.setIsImport("1");
				ps.setPhyLen(te.getN());
				ps.setMapLen(te.getO());
				//ps.setRemark(remark);
				ps.setCreateTime(new Date());
				ps.setZgPipingName(te.getA());
				ps.setOrgId(16L);
				pipingSectionMapper.insertSelective(ps);
				//A插入管道段关联表信息
				pswA = new PipingSectWell();
				pswA.setPipingSectId(ps.getPipingSectId());
				pswA.setWellId(Long.parseLong(te.getD()));
				pipingSectWellMapper.insertSelective(pswA);
				pswA = null;
				//插入A端管道信息
				pA = new Piping();
				pA.setWellId(Long.parseLong(te.getD()));
				pA.setDiameter(0);
				pA.setSubtubeCount(Long.parseLong(te.getK()));
				pA.setUsedsubtubeCount(Integer.parseInt(te.getL()));
				pA.setUnusedsubtubeCount(Integer.parseInt(te.getM()));
				pA.setPipingSectId(ps.getPipingSectId());
				pA.setIsImport("1");
				pA.setPipingState("0");
				pA.setCreateTime(new Date());
				pA.setOrgId(orgId);
				pipingMapper.insertSelective(pA);
				pA = null;

				//Z插入管道段关联表信息
				pswZ = new PipingSectWell();
				pswZ.setPipingSectId(ps.getPipingSectId());
				pswZ.setWellId(Long.parseLong(te.getG()));
				pipingSectWellMapper.insertSelective(pswZ);
				pswZ = null;
				//插入Z端管道信息
				pZ = new Piping();
				pZ.setWellId(Long.parseLong(te.getG()));
				pZ.setDiameter(0);
				pZ.setSubtubeCount(Long.parseLong(te.getK()));
				pZ.setUsedsubtubeCount(Integer.parseInt(te.getL()));
				pZ.setUnusedsubtubeCount(Integer.parseInt(te.getM()));
				pZ.setPipingSectId(ps.getPipingSectId());
				pZ.setIsImport("1");
				pZ.setPipingState("0");
				pZ.setCreateTime(new Date());
				pZ.setOrgId(orgId);
				pipingMapper.insertSelective(pZ);
				pZ = null;
				
				ps = null;
			}
		//导入管道段与井的关联关系
		}else if("pipingSection".equals(devType)) {
			listSS = tnExportMapper.selectExportSection();
			PipingCable pc = null;
			for(TnExport te : listSS) {
				pc = new PipingCable();
				pc.setSectId(Long.parseLong(te.getE()));
				pc.setCheckfiberCount(Long.parseLong(te.getF()));
				pc.setWellId(Long.parseLong(te.getJ()));
				pc.setPipingId(Long.parseLong(te.getK()));
				pc.setPipingSectId(Long.parseLong(te.getC()));
				pc.setSectState("0");
				pc.setIsImport("1");
				//pc.setCheckfiberCount(checkfiberCount);
				pc.setCreateTime(new Date());
				pc.setOrgId(16L);
				pipingCableMapper.insertSelective(pc);
				pc = null;
			}
		//areaCode 区域编码匹配导入
		}else if("areaCode".equals(devType)) {
			listSS = tnExportMapper.selectExportAreaCode();
			Facility facility = null;
			if(listSS!=null && listSS.size()>0) {
				for(TnExport te : listSS) {
					facility = new Facility();
					facility.setDevId(te.getId());
					facility.setAreaCode1(te.getA());
					facilityMapper.updateByPrimaryKeySelective(facility);
					facility = null;
				}
			}
		}
		return ret;
	} 
	
	
//	private static boolean IsTrueRoute(List<TnExport> teList) {
//		boolean flag = true;
//		if(teList != null && teList.size()>2) {
//			String sectId = "";
//			boolean input = false;
//			int xuhao = 0;
//			for(TnExport te : teList) {
//				if(sectId.equals("")) {
//					sectId = te.getG();
//					++xuhao;
//				}else if(!sectId.equals(te.getG())){
//					input = true;
//				}else if(sectId.equals(te.getG())){
//					++xuhao;
//				}
//			}
//		}
//		
//		return flag;
//	}
	
	/**
	 * 导入佛山井数据
	 */
	@Override
	@Transactional
	public int importFSWellInfo() {
		// TODO Auto-generated method stub
		String areaCode = "440604";
		Long orgId = 16L;
		int ret = 0;
		//设施信息
		List<TnExport> listSS = tnExportMapper.selectExport03();
		for(TnExport te : listSS) {
			if(te.getC()!=null && te.getD()!=null) {
				Facility f = facilityMapper.isExitWell(16L, te.getB(),te.getC(),te.getD());
				if(f == null) {
					f = new Facility();
					f.setDevType("11");
					f.setDevName(te.getB());
					f.setAreaCode1(areaCode);
					f.setDevCode(siteCodeService.getNewDevCode());
					f.setCreateTime(new Date());
					f.setLongitude(te.getC());
					f.setLatitude(te.getD());
					f.setOrgId(orgId);
					f.setDevModel(te.getH()+"-"+te.getI()+"-"+te.getJ());
					f.setDevDesc(te.getA());
					ret = facilityMapper.insertSelective(f);
				}
			}
			
			if(te.getF()!=null && te.getG()!=null) {
				Facility f2 = facilityMapper.isExitWell(16L, te.getE(),te.getF(),te.getG());
				if(f2 == null) {
					f2 = new Facility();
					f2.setDevType("11");
					f2.setDevName(te.getE());
					f2.setAreaCode1(areaCode);
					f2.setDevCode(siteCodeService.getNewDevCode());
					f2.setCreateTime(new Date());
					f2.setLongitude(te.getF());
					f2.setLatitude(te.getG());
					f2.setOrgId(orgId);
					f2.setDevModel(te.getH()+"-"+te.getI()+"-"+te.getJ());
					f2.setDevDesc(te.getA());
					ret = facilityMapper.insertSelective(f2);
				}
			}
			
		}
		return ret;
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
/*		Pattern pattern = Pattern.compile("[0-9]"); */
		
		

		
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
		}else if(StringUtil.qurByChar('-',Adev)<3 || StringUtil.qurByChar('-',Zdev)<3) {
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

	@Override
	@Transactional
	public List<TnExport> importZGErrorDatas() {
		Long orgId = 12L;
		String areaCode = "";
		List<TnExport> list = tnExportMapper.selectByparamByType(null);
		
		CableSection upCS = new CableSection();
		
		
		if(list != null && list.size()>0) {
			for(TnExport te : list) {
				CableSection cs = null;
				if(!te.getA().trim().equals(upCS.getSecName() )) {
					Long jfdevIdA = null;
					Long jfdevIdZ = null;
					//处理A端机房
					Facility jfA = null;
					if(!"".equals(te.getF())) {
						jfA = facilityMapper.selectByDevName(orgId, te.getF());
						if(jfA == null) {
							jfA = new Facility();
							jfA.setDevCode(siteCodeService.getNewDevCode());
							jfA.setAreaCode1(areaCode);
							jfA.setDevName(te.getF());
							jfA.setDevType("20");
							jfA.setLongitude(te.getD());
							jfA.setLatitude(te.getE());
							jfA.setCreateTime(new Date());
							jfA.setOrgId(orgId);
							facilityMapper.insertSelective(jfA);
						}
						jfdevIdA = jfA.getDevId();
						
					}else {
						jfdevIdA = null;
					}
					
					//处理A端设施
					Facility fA = facilityMapper.selectByDevName(orgId, te.getC());
					if(jfA == null) {
						fA = new Facility();
						fA.setDevCode(siteCodeService.getNewDevCode());
						fA.setAreaCode1(areaCode);
						fA.setDevName(te.getC());
						fA.setDevType(DataUtil.devTypeConvert(te.getG()));
						fA.setLongitude(te.getD());
						fA.setLatitude(te.getE());
						fA.setCreateTime(new Date());
						fA.setOrgId(orgId);
						fA.setPdevId(jfdevIdA);
						facilityMapper.insertSelective(fA);
					}
					
					//处理Z端机房
					Facility jfZ = null;
					if(!"".equals(te.getO())) {
						jfZ = facilityMapper.selectByDevName(orgId, te.getO());
						if(jfZ == null) {
							jfZ = new Facility();
							jfZ.setDevCode(siteCodeService.getNewDevCode());
							jfZ.setAreaCode1(areaCode);
							jfZ.setDevName(te.getO());
							jfZ.setDevType("20");
							jfZ.setLongitude(te.getR());
							jfZ.setLatitude(te.getS());
							jfZ.setCreateTime(new Date());
							jfZ.setOrgId(orgId);
							facilityMapper.insertSelective(jfZ);
						}
						jfdevIdZ = jfZ.getDevId();
					}else {
						jfdevIdZ = null;
					}
					
					//处理Z端设施
					Facility fZ = facilityMapper.selectByDevName(12L, te.getP());
					if(fZ == null) {
						fZ = new Facility();
						fZ.setDevCode(siteCodeService.getNewDevCode());
						fZ.setAreaCode1(areaCode);
						fZ.setDevName(te.getP());
						fZ.setDevType(DataUtil.devTypeConvert(te.getQ()));
						fZ.setLongitude(te.getR());
						fZ.setLatitude(te.getS());
						fZ.setCreateTime(new Date());
						fZ.setOrgId(orgId);
						fZ.setPdevId(jfdevIdZ);
						facilityMapper.insertSelective(fZ);
					}
					
					//判断光缆段是否存在 获取
					cs = cableSectionMapper.selectByNameZG(te.getA().trim());
					if(cs == null) {
						cs = new CableSection();
						cs.setSectCode(siteCodeService.getNewSectCode());
						cs.setSecName(te.getA().trim());
						cs.setZgFiberNum(Long.parseLong(te.getB()));
						cs.setZgDevIdA(fA.getDevId());
						cs.setZgDevIdZ(fZ.getDevId());
						cs.setSurveyResult("0");
						cs.setIsEndfibercable("0");
						cs.setCreateTime(new Date());
						cs.setSectState("0");
						cableSectionMapper.insertSelective(cs);
						Lines line = null;
						CableSectionDec csd = null;
						for(int i=0;i<Long.parseLong(te.getB());i++) {
							line = new Lines();
							line.setLineType("01");
							line.setAdevId(fA.getDevId());
							line.setZdevId(fZ.getDevId());
							line.setSectId(cs.getSectId());
							line.setFiberNo(i+1L);
							line.setOrgId(orgId);
							line.setSurveyResult("0");
							line.setCreateTime(new Date());
							linesMapper.insertSelective(line);
							line = null;
							
							csd = new CableSectionDec();
							csd.setCreateTime(new Date());
							csd.setOrgId(orgId);
//							csd.setZgSectDec(te.getJ() + "-" + te.getK());
//							csd.setZgRouteName(zgRouteName);
							csd.setSectId(cs.getSectId());
							csd.setFiberNo(i+1L);
							csd.setFiberState("1");
							cableSectionDecMapper.insertSelective(csd);
						}
					}
					
				}else {
					cs = upCS;
				}
				
				
			}
		}
		return null;
	}
	

	/**
	 * 手动生成光路
	 */
	@Override
	public int handRebuildAlls(Long orgId, String areaCode) {
		// TODO Auto-generated method stub
		int ret = 0;
		//判断是否已在生成光路
//		if(linesTmpMapper.selectByOrgId(orgId) > 1) {
//			throw new FrmsException("光路正在生成，请等待！");
//		}
//		ret = LineToLineTmp(orgId,areaCode);
//		if(ret < 1) {
//			throw new FrmsException("保存失败！");
//		}else {
//			ret = 0;
//		}
//		ret = routeMapper.deleteByOrgId(orgId,areaCode);
		
		List<RouteTmp> listAll = new ArrayList<RouteTmp>(); 
		List<RouteTmp> listAll1 = new ArrayList<RouteTmp>(); 
		RouteTmp rt = null;
		
		//处理分光器  
		// -01-01一对多，  -01-02二对多
//		List<LinesTmp> listFG = linesTmpMapper.selectFGBy01(orgId);
//			//往上递归出光路
//		
//		for(LinesTmp FGLines : listFG) {
//			rt = new RouteTmp();
//			rt.setAreaCode1("");
//			//上递归查询
//			LinesTmp ltFG = linesTmpMapper.selectTXByCode(FGLines.getAcode(),orgId,areaCode);
//			if(ltFG == null) {
//				continue;
//			}else {
//				//判断是否跨区
//				if("0".equals(ltFG.getIsSameDistrict())) {
//					isSameDistrict = "0";
//				}
//				rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),FGLines.getAareaCode(),FGLines.getZareaCode()));
//				rt.setRouteText(FGLines.getRouteNoteName());
//				rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),FGLines.getAareaCode(),FGLines.getZareaCode()));
//				rt.setRouteText(ltFG.getRouteNoteName() + "<==>" + rt.getRouteText());
//				
//				rt = SelectTopByTX(rt,ltFG,orgId,areaCode);
//				
//			}
//			//下递归查询
//			RouteTmp rt1 = null;
//			List<LinesTmp> ltFGList = linesTmpMapper.selectFGListByCode(FGLines.getAcode(),orgId);
//			for(LinesTmp ltFGLines : ltFGList) {
//				rt1 = new RouteTmp();
//				rt1.setAreaCode1(getAreaCodes(rt.getAreaCode1(),ltFGLines.getAareaCode(),ltFGLines.getZareaCode()));
//				LinesTmp ltTXLines = linesTmpMapper.selectTXByCode(ltFGLines.getZcode(),orgId,areaCode);
//				if(ltTXLines == null) {
//					zotherName = ltFGLines.getZdevName()+"("+ltFGLines.getZcode()+")";
//					rt1.setZdevName(ltFGLines.getZdevName());
//					//rt1.setRouteText(rt.getRouteText()+"<==>"+zotherName);
//					rt1.setRouteText(rt.getRouteText()+"<==>"+ltFGLines.getRouteNoteName());
//				}else {
//					if("0".equals(ltTXLines.getIsSameDistrict())) {
//						isSameDistrict = "0";
//					}
//					rt1.setAreaCode1(getAreaCodes(rt1.getAreaCode1(),ltFGLines.getAareaCode(),ltFGLines.getZareaCode()));
//					//rt1.setRouteText(rt.getRouteText()+"<==>"+ ltFGLines.getZdevName()+"("+ltFGLines.getZcode()+")" + "<==>" +ltTXLines.getRouteNoteName());
//					rt1.setRouteText(rt.getRouteText()+"<==>"+ ltFGLines.getRouteNoteName() + "<==>" +ltTXLines.getRouteNoteName());
//					rt1 = SelectDownByTX(rt1,ltTXLines,orgId,areaCode);
//				}
//				
//				rt1.setAdevName(rt.getAdevName());
//				rt1.setAotherName(aotherName);	
//				rt1.setZotherName(zotherName);
//				rt1.setRouteName(aotherName + "<==>" + zotherName);
//				rt1.setIsSameDistrict(isSameDistrict);
//				listAll.add(rt1);
//
//				
//				zotherName = "";
//				rt1 = null;
//			}
//			aotherName = "";
//			isSameDistrict = "1";
//			rt = null;
//			
//		}	
//		
		Route route = null;
//		for(RouteTmp rtTmp : listAll) {
//			route = new Route();
//			route.setAotherName(rtTmp.getAotherName());
//			route.setZotherName(rtTmp.getZotherName());
//		//	route.setCodes(rtTmp.getRouteText());
//			//将原有的codes换为routetext
//			route.setRoutetext(rtTmp.getRouteText());
//			route.setRouteName(rtTmp.getRouteName());
//			route.setOrgId(orgId);
//			route.setAreaCode1(rtTmp.getAreaCode1());
//			route.setCreateTime(new Date()); 
//			routeMapper.insertSelective(route);
//			route = null;
//		}
		
		//除去分光器生成光路
		String adev = "";
		String zdev = "";
		//while (true) {
		List<LinesTmp> zcLineList = linesTmpMapper.selectZCLinesList(orgId);
		for(LinesTmp zcLine : zcLineList) {	
			if(zcLine.getAcode()!=null && zcLine.getZcode()!=null) {
				if(zcLine.getAcode().indexOf("ZRIN")!=-1 && zcLine.getZcode().indexOf("ZROUT")!=-1) {
					linesTmpMapper.deleteByCode(zcLine);
					continue;
				}else if(zcLine.getAcode().indexOf("JTA")!=-1 && zcLine.getZcode().indexOf("JTB")!=-1) {
					linesTmpMapper.deleteByCode(zcLine);
					continue;
				}
			}
			
			if("0".equals(zcLine.getIsSameDistrict())) {
				isSameDistrict = "0";
			}
			linesTmpMapper.deleteByCode(zcLine);
			//往下递归出光路
			rt = new RouteTmp();
			rt.setAreaCode1("");
			rt.setAreaCode1(getAreaCodes(rt.getAreaCode1(),zcLine.getAareaCode(),zcLine.getZareaCode()));
			if(zcLine.getZcode() == null) {
				if(linesTmpMapper.selectGXByCode(zcLine.getAcode(),orgId,areaCode)==null){
					linesTmpMapper.deleteByCode(zcLine);
					continue;
				}
				
				//上行
				rt.setRouteText(zcLine.getAdevName()+"("+zcLine.getAcode()+")");
				zotherName = zcLine.getAdevName()+"("+zcLine.getAcode()+")";
				rt.setZdevName(zcLine.getAdevName());
				rt = SelectTopByTXs(zcLine.getAcode(),rt,zcLine,orgId,areaCode);
			}else if(zcLine.getAcode() == null) {
				if(linesTmpMapper.selectGXByCode(zcLine.getZcode(),orgId,areaCode)==null){
					linesTmpMapper.deleteByCode(zcLine);
					continue;
				}
				
				//下行
				rt.setRouteText(rt.getRouteText()+"<==>"+zcLine.getZdevName()+"("+zcLine.getZcode()+")");
				aotherName = zcLine.getZdevName()+"("+zcLine.getZcode()+")";
				rt.setAdevId(zcLine.getAdevId());
				rt.setAdevName(zcLine.getZdevName());
				rt = SelectDownByTXs(zcLine.getZcode(),rt,zcLine,orgId,areaCode);
			}else {
				adev = zcLine.getAdevName()+zcLine.getAcode();
				zdev = zcLine.getZdevName()+zcLine.getZcode();
//				if((adev.indexOf("PTN")!=-1&&zdev.indexOf("BBU")!=-1) || (adev.indexOf("BBU")!=-1&&zdev.indexOf("PTN")!=-1)) {
//				}else {
//					if(linesTmpMapper.selectGXByCode(zcLine.getAcode(),orgId,areaCode)==null && linesTmpMapper.selectGXByCode(zcLine.getZcode(),orgId,areaCode)==null){
//						linesTmpMapper.deleteByCode(zcLine);
//						continue;
//					}
//				}
				
				rt.setRouteText(zcLine.getAdevName()+"("+zcLine.getAcode()+")");
				rt = SelectTopByTXs(zcLine.getAcode(),rt,zcLine,orgId,areaCode);
				
				//下行
				rt.setRouteText(rt.getRouteText()+"<==>"+zcLine.getZdevName()+"("+zcLine.getZcode()+")");
				rt = SelectDownByTXs(zcLine.getZcode(),rt,zcLine,orgId,areaCode);
			}
			rt.setAotherName(aotherName);	
			rt.setZotherName(zotherName);
			rt.setRouteName(aotherName + "<==>" + zotherName);
			rt.setIsSameDistrict(isSameDistrict);
			//if(!aotherName.equals(zotherName)) {
			//listAll1.add(rt);
			
			route = new Route();
			route.setAotherName(rt.getAotherName());
			route.setZotherName(rt.getZotherName());
			route.setRoutetext(rt.getRouteText());
			route.setRouteName(rt.getRouteName());
			route.setOrgId(orgId);
			route.setAreaCode1(rt.getAreaCode1());
			route.setCreateTime(new Date()); 
			routeMapper.insertSelective(route);
			route = null;
			
			
			
			rt = null;
			aotherName = "";
			zotherName = "";
			isSameDistrict = "1";
			adev = "";
			zdev = "";
		}
		
		//return listAll;
//		for(RouteTmp rtTmp : listAll1) {
//			route = new Route();
//			route.setAotherName(rtTmp.getAotherName());
//			route.setZotherName(rtTmp.getZotherName());
//		//	route.setCodes(rtTmp.getRouteText());
//			//将原有的codes换为routetext
//			route.setRoutetext(rtTmp.getRouteText());
//			route.setRouteName(rtTmp.getRouteName());
//			route.setOrgId(orgId);
//			route.setAreaCode1(rtTmp.getAreaCode1());
//			route.setCreateTime(new Date()); 
//			routeMapper.insertSelective(route);
//			route = null;
//		}
		//ret = linesTmpMapper.deleteByLine();
	
//			//删除tn_task_log表中数据
//			 routeMapper.delectroutelog( orgId,  areaCode);
		
		return 1;
	}
	
//	public static void main(String[] args) {
//		String a = "dawdawd/==M01-dw-ODF";
//		String b = "GJ2112-FG-B-ODF";
//		String c = "GJ2112-FG-A-ODF";
//		String d = "GJ2112-FG-A-ODF,0";
//		System.out.println(a.substring(0,a.indexOf("/")));
////		System.out.println(a.substring(a.indexOf("/")+1));
////		System.out.println(a.substring(a.indexOf("ODF")));
////		
////		System.out.println(a.substring(a.indexOf("-")+1, a.lastIndexOf("-")));
////		System.out.println(a.substring(0, a.indexOf("-")));
////		System.out.println(toDiscName("001")); 
////		
////		System.out.println(LineType.FIBER.toString());
////		
////		System.out.println(a.substring(a.lastIndexOf("-")+1));
//		System.out.println(b.replace("B", "A"));
//		System.out.println(c.replace("B", "A"));
//		System.out.println(d.substring(0, d.indexOf("")));
//		System.out.println(d.substring( d.indexOf(",")));
//		Pattern pattern = Pattern.compile("[0-9]"); 
//		System.out.println(pattern.matcher("1)".substring(0,1)).matches());
//		System.out.println("a)".substring("a)".length()-2,"a)".length()-1));
//		
//		System.out.println("a)".substring("a)".length()-2,"a)".length()-1));
//		
//		System.out.println(RouteBuildServiceImpl.LightPathType("惠州惠城区九龙机房综合机柜02(GJ7838-03-02-06)(NO:6)<==>惠州惠城区汝湖镇府机房综合机柜02(GJ7797-ODF04-01-06)(no:6)<==>\n" + 
//				"惠州惠城区汝湖镇府机房综合机柜01(GJ7796-ODF02-01-05)(NO:5)<==>惠州惠城区汝湖机房综合机柜01(GJ7834-ODF01-01-05)(no:5)<==>\n" + 
//				"惠州惠城区汝湖机房综合机柜01(GJ7834-ODF03-04-03)(NO:15)<==>惠州惠城区荣屋村机房综合机柜01(GJ6661-ODF01-02-03)<==>\n" + 
//				"惠州惠城区荣屋村机房综合机柜01(GJ6661-ODF01-03-06)<==>惠州惠城区先科大道机房综合机柜01(GJ7761-01-01-06)(NO:6)"));
//		
//		System.out.println("0.1".substring(0, "0.1".indexOf(".")));
//	}
	
	@Override
	public List<TnExport> routeOLT(Long orgId){
		List<TnExport> list = tnExportMapper.selectExport();
		List<TnExport> result = new ArrayList<TnExport>();
		TnExport resultTe = null;
		if(list!=null && list.size()>0) {
			for(TnExport te : list) {
				List<Route> routeList = routeMapper.queryRouteByCode(te.getF());
				if(routeList!=null && routeList.size()>0) {
					for(Route r : routeList) {
						resultTe = new TnExport();
						resultTe.setA(te.getA());
						resultTe.setB(te.getB());
						resultTe.setC(te.getC());
						resultTe.setD(te.getD());
						resultTe.setE(te.getE());
						resultTe.setF(te.getF());
						resultTe.setG(r.getRoutetext());
						result.add(resultTe);
						resultTe = null;
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 接收坐标所属地区信息【json】
	* @Title: insertStreet 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年8月4日 下午10:28:31 
	* @version V1.0
	 */
	@Override
	public int insertStreet(String code) {
		// TODO Auto-generated method stub
		TnExport te = null;
		//System.out.println(code);
		JSONArray jsonArray = JSONArray.fromObject(code);
        List<Map<String,Object>> mapListJson = (List)jsonArray;
        for (int i = 0; i < mapListJson.size(); i++) {
        	te = new TnExport();
            Map<String,Object> obj=mapListJson.get(i);
            for(Entry<String,Object> entry : obj.entrySet()){
                String strkey1 = entry.getKey();
                Object strval1 = entry.getValue();
                if("id".equals(strkey1)) {
                	te.setId(((Integer)strval1).longValue());
                }else if("value".equals(strkey1)) {
                	te.setH(strval1.toString());
                }
                System.out.println("KEY:"+strkey1+"  -->  Value:"+strval1+"\n");
            }
            tnExportMapper.updateByPrimaryKeySelective(te);
            te = null;
        }
		return 1;
	}
	
	public static void main(String[] args) {
		 String jsonArrayData="[{\"a1\":\"12\",\"b1\":\"112\",\"c1\":\"132\",\"d1\":\"134\"},{\"a2\":\"12\",\"b2\":\"112\",\"c2\":\"132\",\"d2\":\"134\"},{\"a3\":\"12\",\"b3\":\"112\",\"c3\":\"132\",\"d3\":\"134\"}]";
	        JSONArray jsonArray = JSONArray.fromObject(jsonArrayData);
	 
	        List<Map<String,Object>> mapListJson = (List)jsonArray;
	        for (int i = 0; i < mapListJson.size(); i++) {
	            Map<String,Object> obj=mapListJson.get(i);
	             
	            for(Entry<String,Object> entry : obj.entrySet()){
	                String strkey1 = entry.getKey();
	                Object strval1 = entry.getValue();
	                System.out.println("KEY:"+strkey1+"  -->  Value:"+strval1+"\n");
	            }
	        }
	}
	
	/**
	 * 查询所有区域集合
	* @Title: selectAreaList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return List<TnExport>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年8月4日 下午11:18:03 
	* @version V1.0
	 */
	@Override
	public List<TnExport> selectAreaList() {
		// TODO Auto-generated method stub
		return tnExportMapper.selectExport01();
	}
	@Override
	public List<TnExport> selectPointList() {
		// TODO Auto-generated method stub
		return tnExportMapper.selectExport();
	}
	@Override
	public int insertPoint(String code) {
		// TODO Auto-generated method stub
		TnExport te = null;
		//System.out.println(code);
		JSONArray jsonArray = JSONArray.fromObject(code);
        List<Map<String,Object>> mapListJson = (List)jsonArray;
        for (int i = 0; i < mapListJson.size(); i++) {
        	te = new TnExport();
            Map<String,Object> obj=mapListJson.get(i);
            for(Entry<String,Object> entry : obj.entrySet()){
                String strkey1 = entry.getKey();
                Object strval1 = entry.getValue();
                if("id".equals(strkey1)) {
                	te.setId(((Integer)strval1).longValue());
                }else if("value".equals(strkey1)) {
                	te.setK(strval1.toString());
                }
                System.out.println("KEY:"+strkey1+"  -->  Value:"+strval1+"\n");
            }
            tnExportMapper.updateByPrimaryKeySelective(te);
            te = null;
        }
		return 1;
	}
	@Override
	public int selectPoints(String code) {
		// TODO Auto-generated method stub
		
		if("1".equals(code)) {
			List<TnExport> teList1 = tnExportMapper.selectExport();
			for(TnExport te : teList1) {
				Double dis = MapDistance.getDistance_20180802(Double.parseDouble(te.getC()),Double.parseDouble(te.getE())
						,Double.parseDouble(te.getG()),Double.parseDouble(te.getI()));
				String[] areaArr = te.getK().split(",");
				if(areaArr[0].equals(areaArr[1])) {
					te.setL(selectAreaById(areaArr[0]));
					te.setM(dis+"");
				}else {
					te.setL(selectAreaById(areaArr[0]));
					te.setM(dis/2+"");
					te.setN(selectAreaById(areaArr[1]));
					te.setO(dis/2+"");
				}
				tnExportMapper.updateByPrimaryKeySelective(te);
				dis = null;
				areaArr = null;
			}
		}else {
			List<TnExport> teList2 = tnExportMapper.selectExport();
			for(TnExport te2 : teList2) {
				String[] aArr =te2.getB().replaceAll(",0", "").split(" ");
				String[] bArr =te2.getH().split(",");
//				if(aArr!=null && aArr.length>0) {
//					System.out.println("==========================="+aArr[aArr.length-1]);
//					aArr[aArr.length-1] = aArr[aArr.length-1].substring(0, aArr[aArr.length-1].lastIndexOf(","));
//					System.out.println("==========================="+aArr[aArr.length-1]);
//				}
				Double otherDis = 0.0;
				Double disAll = 0.0;
				int noSame = 0;
				for(int j=0;j<aArr.length-1;j++) {
					String[] zb1 = aArr[j].split(",");
					String[] zb2 = aArr[j+1].split(",");
					System.out.println("------------"+zb2[0]+"------------");
					System.out.println("------------"+zb2[1]+"------------");
					Double dis = MapDistance.getDistance_20180802(Double.parseDouble(zb1[1]),Double.parseDouble(zb1[0])
							,Double.parseDouble(zb2[1]),Double.parseDouble(zb2[0]));
					if(!"0".equals(bArr[j]) && noSame==0) {
						noSame = 1;
					}
					if("0".equals(bArr[j])) {
						otherDis = otherDis + dis;
					}else {
						disAll = disAll + dis;
					}
					if(!"0".equals(bArr[j])) {
						if(noSame==1) {
							te2.setK(selectAreaById(bArr[j]));
							te2.setL(disAll+"");
						}else if(noSame==2) {
							te2.setM(selectAreaById(bArr[j]));
							te2.setN(disAll+"");
						}else if(noSame==3) {
							te2.setO(selectAreaById(bArr[j]));
							te2.setP(disAll+"");
						}else if(noSame==4) {
							te2.setQ(selectAreaById(bArr[j]));
							te2.setR(disAll+"");
						}else if(noSame==5) {
							te2.setS(selectAreaById(bArr[j]));
							te2.setT(disAll+"");
						}
					}
					
					if(!"0".equals(bArr[j+1]) && !bArr[j].equals(bArr[j+1])) {
						++noSame;
						disAll = 0.0;
					}
				}
				//处理S重复
				if(te2.getT()!=null && te2.getS()!=null) {
					if(te2.getS().equals(te2.getQ())) {
						te2.setR((Double.parseDouble(te2.getT())+Double.parseDouble(te2.getR()))+"");
						te2.setS("");
						te2.setT("");
					}else if(te2.getS().equals(te2.getO())) {
						te2.setP((Double.parseDouble(te2.getT())+Double.parseDouble(te2.getP()))+"");
						te2.setS("");
						te2.setT("");
					}else if(te2.getS().equals(te2.getM())) {
						te2.setN((Double.parseDouble(te2.getT())+Double.parseDouble(te2.getN()))+"");
						te2.setS("");
						te2.setT("");
					}else if(te2.getS().equals(te2.getK())) {
						te2.setL((Double.parseDouble(te2.getT())+Double.parseDouble(te2.getL()))+"");
						te2.setS("");
						te2.setT("");
					}
				}
				
				//处理q重复
				if(te2.getQ()!=null && te2.getR()!=null) {
					if(te2.getQ().equals(te2.getO())) {
						te2.setP((Double.parseDouble(te2.getR())+Double.parseDouble(te2.getP()))+"");
						te2.setQ("");
						te2.setR("");
					}else if(te2.getQ().equals(te2.getM())) {
						te2.setN((Double.parseDouble(te2.getR())+Double.parseDouble(te2.getN()))+"");
						te2.setQ("");
						te2.setR("");
					}else if(te2.getQ().equals(te2.getK())) {
						te2.setL((Double.parseDouble(te2.getR())+Double.parseDouble(te2.getL()))+"");
						te2.setQ("");
						te2.setR("");
					}
				}
				
				//处理o重复
				if(te2.getO()!=null && te2.getP()!=null) {
					if(te2.getO().equals(te2.getM())) {
						te2.setN((Double.parseDouble(te2.getP())+Double.parseDouble(te2.getN()))+"");
						te2.setO("");
						te2.setP("");
					}else if(te2.getO().equals(te2.getK())) {
						te2.setL((Double.parseDouble(te2.getP())+Double.parseDouble(te2.getL()))+"");
						te2.setO("");
						te2.setP("");
					}
				}
				
				
				//处理M重复
				if(te2.getM()!=null && te2.getN()!=null) {
					if(te2.getM().equals(te2.getK())) {
						te2.setL((Double.parseDouble(te2.getN())+Double.parseDouble(te2.getL()))+"");
						te2.setM("");
						te2.setN("");
					}
				}
				
				te2.setI("其他区域");
				te2.setJ(otherDis+"");
				tnExportMapper.updateByPrimaryKeySelective(te2);
			}
		}
		return 1;
	}
	
	private static String selectAreaById(String id) {
		if("1".equals(id)) {
			return "惠城区三环内";
		}else if("2".equals(id)) {
			return "博罗县中心";
		}else if("3".equals(id)) {
			return "淡水-土湖区域 中心城区-惠阳修改";
		}else if("4".equals(id)) {
			return "火车站";
		}else if("5".equals(id)) {
			return "水口";
		}else if("6".equals(id)) {
			return "金山湖";
		}else if("7".equals(id)) {
			return "南线";
		}else if("8".equals(id)) {
			return "仲恺、陈江";
		}else if("9".equals(id)) {
			return "龙门县中心";
		}else if("10".equals(id)) {
			return "惠东县中心";
		}else if("11".equals(id)) {
			return "惠阳区中心";
		}else if("12".equals(id)) {
			return "大亚湾区中心 澳头";
		}else if("13".equals(id)) {
			return "土湖";
		}else if("14".equals(id)) {
			return "园州";
		}else if("15".equals(id)) {
			return "石湾";
		}else if("16".equals(id)) {
			return "九潭";
		}else if("17".equals(id)) {
			return "秋长";
		}else if("18".equals(id)) {
			return "西区";
		}else if("19".equals(id)) {
			return "马安1";
		}else if("20".equals(id)) {
			return "新圩-赵洞、约场、南坑区域 一般城区-惠阳修改";
		}else if("21".equals(id)) {
			return "三和街道 一般城区-惠阳修改";
		}else if("22".equals(id)) {
			return "秋长维布区域 一般城区-惠阳修改";
		}else if("23".equals(id)) {
			return "淡水-洋纳、南站新城 一般城区-惠阳修改";
		}else if("24".equals(id)) {
			return "马安1";
		}else if("25".equals(id)) {
			return "马安2";
		}else if("26".equals(id)) {
			return "三栋";
		}else if("27".equals(id)) {
			return "小金";
		}else if("28".equals(id)) {
			return "潼湖";
		}else if("29".equals(id)) {
			return "潼侨";
		}else if("30".equals(id)) {
			return "沥林";
		}else if("31".equals(id)) {
			return "永湖";
		}else if("32".equals(id)) {
			return "吉隆";
		}else if("33".equals(id)) {
			return "长宁";
		}else if("34".equals(id)) {
			return "黄埠";
		}else if("35".equals(id)) {
			return "新墟";
		}else if("36".equals(id)) {
			return "永汉";
		}else if("37".equals(id)) {
			return "南昆山";
		}else if("38".equals(id)) {
			return "平潭";
		}else if("39".equals(id)) {
			return "龙溪";
		}else if("40".equals(id)) {
			return "霞涌";
		}else if("41".equals(id)) {
			return "巽寮";
		}else if("42".equals(id)) {
			return "平海";
		}else if("43".equals(id)) {
			return "大岭";
		}else if("44".equals(id)) {
			return "石化区";
		}else if("45".equals(id)) {
			return "四角楼";
		}else if("46".equals(id)) {
			return "水口东江高新区";
		}else if("47".equals(id)) {
			return "晓联阿婆角";
		}else {
			return "其他区域";
		}
	}
	
	
	@Override
	public int hzboFacilityImport(String devType) {
			// TODO Auto-generated method stub
			Long orgId = 12L;
			int ret = 0;
			//设施信息
			List<TnExport> listSS = null;
					//tnExportMapper.selectExport03();
			//UPDATE tn_facility SET dev_desc=NULL WHERE dev_desc LIKE '%.0' AND area_code1 NOT LIKE '441322%'
//			SELECT * FROM tn_facility WHERE area_code1 LIKE '441322%'
//
//
//			SELECT a.*
//			FROM `tn_export_fac` a
//			LEFT JOIN tn_facility b ON b.zg_dev_name=a.a
//			WHERE b.dev_id IS NOT NULL

			
			Facility f = null;
			CableSection cs = null;
			double[] gps = null;
			double[] bd = null;
			//机房光剑
			if("20".equals(devType)) {
				listSS = tnExportMapper.selectExport20();
				for(TnExport te : listSS) {
					if(te.getB()!=null && !"".equals(te.getB())) {
						Facility fZD = facilityMapper.selectByDevDesc(te.getB());
						if(fZD != null) {
							continue;
						}
					}else {
						continue;
					}
					f = new Facility();
					gps = new double[2];
					bd = new double[2];
					f.setDevType(DataUtil.devTypeConvert(te.getC()));
					f.setDevName(te.getA());
					f.setZgDevName(te.getA());
					f.setAreaCode1(DataUtil.AreaCodeByAreaName_bl(te.getG()));
					f.setDevCode(siteCodeService.getNewDevCode());
					f.setCreateTime(new Date());
					Double longitude = DataUtil.CheckLongitude(te.getD());
					Double latitude = DataUtil.CheckLatitude(te.getE());
					if(longitude!=null && latitude !=null) {
						gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
						f.setLongitude(gps[1]+"");
						f.setLatitude(gps[0]+"");
						
						bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
						f.setBaiduX(new BigDecimal(bd[1]+""));
						f.setBaiduY(new BigDecimal(bd[0]+""));
					}
					f.setOrgId(orgId);
					f.setFlag("1");
					f.setSurveyResult("0");
					f.setDevState("0");
					f.setDevDesc(te.getB());
					if(te.getB()!=null && !"".equals(te.getB())) {
						Facility fZD = facilityMapper.selectByDevDesc(te.getB());
						if(fZD != null) {
							f.setPdevId(fZD.getDevId());
						}
					}
					
					ret = facilityMapper.insertSelective(f);
					gps = null;
					bd = null;
				}
			//分纤点
			}else if("11".equals(devType)) {
				listSS = tnExportMapper.selectExport11();
				for(TnExport te : listSS) {
					if(te.getB()!=null && !"".equals(te.getB())) {
						Facility fZD = facilityMapper.selectByDevDesc(te.getB());
						if(fZD != null) {
							continue;
						}
					}else {
						continue;
					}
					f = new Facility();
					Facility pfacility = new Facility();
					if(te.getD()!=null && !"".equals(te.getD())) {
						pfacility = facilityMapper.selectByDevDesc(te.getD());
						if(pfacility != null) {
							f.setPdevId(pfacility.getDevId());
						}
					}
					
					
					
					
					gps = new double[2];
					bd = new double[2];
					f.setDevType(DataUtil.devTypeConvert(te.getE()));
					f.setZgDevName(te.getA());
					f.setDevName(te.getA());
					if(pfacility == null) {
						f.setAreaCode1(DataUtil.AreaCodeByAreaName_bl(""));
					}else{
						f.setAreaCode1(DataUtil.AreaCodeByAreaName_bl(pfacility.getAreaCode1()));
					}
					pfacility = null;
					f.setDevCode(siteCodeService.getNewDevCode());;
					f.setCreateTime(new Date());

					Double longitude = DataUtil.CheckLongitude(te.getF());
					Double latitude = DataUtil.CheckLatitude(te.getG());
					if(longitude!=null && latitude !=null) {
						gps = GoogleToGPSUtil.gcj02_To_Bd09(latitude,longitude);
						f.setLongitude(gps[1]+"");
						f.setLatitude(gps[0]+"");
						
						bd = GoogleToGPSUtil.gcj02_To_Gps84(latitude,longitude);
						f.setBaiduX(new BigDecimal(bd[1]+""));
						f.setBaiduY(new BigDecimal(bd[0]+""));
					}
					f.setOrgId(orgId);
					f.setFlag("1");
					f.setSurveyResult("0");
					f.setDevState("0");
					f.setDevDesc(te.getB());
					
//					Facility fZD = facilityMapper.selectByDevDesc(te.getA());
//					if(fZD != null) {
//						f.setPdevId(fZD.getDevId());
//					}
					ret = facilityMapper.insertSelective(f);
					gps = null;
					bd = null;
				}
			}else if("gl".equals(devType)) {
				listSS = tnExportMapper.selectExportgl();
				for(TnExport te : listSS) {
					Facility fA = null;
					Facility fZ = null;
					cs = new CableSection();
					cs.setCreateTime(new Date());
					cs.setSectCode(siteCodeService.getNewSectCode());
					cs.setFiberNum(DataUtil.CheckFiberNum(te.getC()));
					cs.setZgFiberNum(DataUtil.CheckFiberNum(te.getC()));
//					if(te.getD()!=null && !"".equals(te.getD())) {
//						cs.setCableLen(new BigDecimal(te.getD())); 
//					}
					cs.setOrgId(orgId);
					cs.setSurveyResult("0");
					cs.setZgSecName(te.getB());
					cs.setSecName(te.getB());
					cs.setSectState("0");
					cs.setIsEndfibercable("0");
					cs.setRemark(te.getA());
					
					//处理光缆信息
//					if(te.getF() != null) {
//						Cable cable=cableMapper.selectByCableCode(te.getF());
//						if(cable != null) {
//							cs.setCableId(cable.getCableId());
//						}
//					}
					
					if(te.getF()!=null && !"".equals(te.getF().trim())) {
//						if(te.getF().indexOf(".")!=-1) {
//							fA = facilityMapper.selectByDevDesc(te.getI().substring(0, te.getI().indexOf(".")));
//						}else {
						fA = facilityMapper.selectByDevDesc(te.getF());
//						}
						
						if(fA != null) {
							cs.setZgDevIdA(fA.getDevId());
							cs.setDevIdA(fA.getDevId());
						}else {
							cs = null;
							continue;
						}
					}else {
						cs = null;
						continue;
					}   
					
					if(te.getI()!=null && !"".equals(te.getI().trim())) {
//						if(te.getM().indexOf(".")!=-1) {
//							fZ = facilityMapper.selectByDevDesc(te.getM().substring(0, te.getM().indexOf(".")));
//						}else {
							fZ = facilityMapper.selectByDevDesc(te.getI());
//						}
						
						if(fZ != null) {
							cs.setZgDevIdZ(fZ.getDevId());
							cs.setDevIdZ(fZ.getDevId());
						}else {
							cs = null;
							continue;
						}
					}else {
						cs = null;
						continue;
					}
					tnExportMapper.deleteByGl(te.getId());
					
					ret = cableSectionMapper.insertSelective(cs);
					//添加光缆描述信息 和 纤芯成端信息
					if(cs.getZgFiberNum() > 0) {
						CableSectionDec csd = null;
						Lines line = null;
						for(long i=1;i<=cs.getZgFiberNum();i++) {
							csd = new CableSectionDec();
							csd.setCreateTime(new Date());
							csd.setFiberState("1");
							csd.setOrgId(orgId);
							csd.setFiberNo(i);
							csd.setSectId(cs.getSectId());
							cableSectionDecMapper.insertSelective(csd);
							csd = null;
							
							line = new Lines();
							line.setCreateTime(new Date());
							line.setAdevId(fA.getDevId());
							line.setZdevId(fZ.getDevId());
							line.setSectId(cs.getSectId());
							line.setFiberNo(i);
							line.setOrgId(orgId);
							line.setSurveyResult("0");
							line.setLineType("01");
							linesMapper.insertSelective(line);
							line = null; 
						}
					}
					cs = null;
				}
			}
			
			return ret;
	}
}