package com.ycnet.frms.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.Constants;
import com.ycnet.core.Constants.FACILITTYPE;
import com.ycnet.core.FacilityType;
import com.ycnet.core.FrmsException;
import com.ycnet.core.LineType;
import com.ycnet.core.util.BeanUtils;
import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.LatLng;
import com.ycnet.core.util.ListUtils;
import com.ycnet.core.util.Map_Util;
import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.ResultData;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.AlarmPushlog;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityImg;
import com.ycnet.frms.bean.FacilityImgs;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.InspectImg;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Project;
import com.ycnet.frms.bean.Temps;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.DeviceAlarmMapper;
import com.ycnet.frms.mapper.DeviceRegMapper;
import com.ycnet.frms.mapper.DiscinfoMapper;
import com.ycnet.frms.mapper.FacilityImgMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscGroupMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.InspectImgMapper;
import com.ycnet.frms.mapper.OrganizitionMapper;
import com.ycnet.frms.mapper.TempsMapper;
import com.ycnet.frms.mapper.WellPilelineMapper;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.service.FacilityCoreService;
import com.ycnet.frms.service.LinesService;
import com.ycnet.frms.service.ProjectService;
import com.ycnet.frms.service.SiteCodeService;
import com.ycnet.frms.service.TempsService;
import com.ycnet.frms.vo.DeviceAlarmBean;
import com.ycnet.frms.vo.FacilityBean;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityFromMac;
import com.ycnet.frms.vo.FiberdiscConditionBean;
import com.ycnet.frms.vo.FiberdiscInfo;
import com.ycnet.frms.vo.JumperInfo;
import com.ycnet.frms.vo.LocateInfo;
import com.ycnet.frms.vo.LockKey;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("tempsService")
@Transactional
public class TempsServiceImpl implements TempsService  {
	
	@Resource(name="deviceAlarmMapper")
	private DeviceAlarmMapper deviceAlarmMapper;

	@Resource(name="tempsMapper")
	private TempsMapper tempsMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;
	
	@Resource(name="fiberdiscGroupMapper")
	private FiberdiscGroupMapper fiberdiscGroupMapper;
	
	@Resource(name="discinfoMapper")
	private DiscinfoMapper discinfoMapper;
	
	@Resource(name="areasService")
	private AreasService areasService;
	
	@Resource(name="siteCodeService")
	private SiteCodeService siteCodeService;
	
	@Resource(name="linesService")
	private LinesService linesService;
	
	@Resource(name="facilityCoreService")
	private FacilityCoreService fcService;
	
	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	
	@Resource(name="projectService")
	private ProjectService projectService;

	@Resource(name="deviceRegMapper")
	DeviceRegMapper deviceRegMapper;

	@Resource(name="inspectImgMapper")
	private InspectImgMapper inspectImgMapper;
	
	@Resource(name="facilityImgMapper")
	private FacilityImgMapper facilityImgMapper;
	
	@Resource
	private CableSectionMapper cableSectionMapper;
	
	@Resource(name="wellPilelineMapper")
	private WellPilelineMapper wellPilelineMapper;
	
	@Resource(name="organizitionMapper")
	private OrganizitionMapper organizitionMapper;
	
	@Override
	public Facility selectByCode(String devCode) {
		Facility fc=new Facility();
		Facility facility = facilityMapper.selectByCode(devCode);
		if(facility!=null){
			
		
			if(facility.getPdevId()!=null){
				fc=facilityMapper.selectByCodeXYId(facility.getPdevId());
				return fc;
			}else{
				fc.setDevId(facility.getDevId());
				fc.setDevCode(facility.getDevCode());
				fc.setDevName(facility.getDevName());
				fc.setLatitude(facility.getLatitude());
				fc.setLongitude(facility.getLongitude());
				fc.setAreaCode1(facility.getAreaCode1());
				fc.setAreaCode2(facility.getAreaCode2());
				fc.setBaiduX(facility.getBaiduX());
				fc.setBaiduY(facility.getBaiduY());
				fc.setCodeA(facility.getCodeA());
				fc.setCodeZ(facility.getCodeZ());
				fc.setCompleteDate(facility.getCompleteDate());
				fc.setDevAddr(facility.getDevAddr());
				fc.setDevDesc(facility.getDevDesc());
				fc.setDevImei(facility.getDevImei());
				fc.setDevMac(facility.getDevMac());
				fc.setDevModel(facility.getDevModel());
				fc.setDevType(facility.getDevType());
				fc.setFiberDiscNum(facility.getFiberDiscNum());
				fc.setFlag(facility.getFlag());
				fc.setImgType(facility.getImgType());
				fc.setIsTranslated(facility.getIsTranslated());
				fc.setOrgId(facility.getOrgId());
				fc.setRoom(facility.getRoom());
				fc.setSite(facility.getSite());
				fc.setSurveyResult(facility.getSurveyResult());
				fc.setProId(facility.getProId());
				fc.setPdevId(facility.getPdevId());
			}
		}
		return fc;
	}
	
	@Override
	public Facility selectByCode1(String devCode,Long orgId) {
		Facility fc=new Facility();
		Facility facility = facilityMapper.selectByCode1(devCode,orgId);
		if(facility!=null){
			
		
			if(facility.getPdevId()!=null){
				fc=facilityMapper.selectByCodeXYId(facility.getPdevId());
				return fc;
			}else{
				fc.setDevId(facility.getDevId());
				fc.setDevCode(facility.getDevCode());
				fc.setDevName(facility.getDevName());
				fc.setLatitude(facility.getLatitude());
				fc.setLongitude(facility.getLongitude());
				fc.setAreaCode1(facility.getAreaCode1());
				fc.setAreaCode2(facility.getAreaCode2());
				fc.setBaiduX(facility.getBaiduX());
				fc.setBaiduY(facility.getBaiduY());
				fc.setCodeA(facility.getCodeA());
				fc.setCodeZ(facility.getCodeZ());
				fc.setCompleteDate(facility.getCompleteDate());
				fc.setDevAddr(facility.getDevAddr());
				fc.setDevDesc(facility.getDevDesc());
				fc.setDevImei(facility.getDevImei());
				fc.setDevMac(facility.getDevMac());
				fc.setDevModel(facility.getDevModel());
				fc.setDevType(facility.getDevType());
				fc.setFiberDiscNum(facility.getFiberDiscNum());
				fc.setFlag(facility.getFlag());
				fc.setImgType(facility.getImgType());
				fc.setIsTranslated(facility.getIsTranslated());
				fc.setOrgId(facility.getOrgId());
				fc.setRoom(facility.getRoom());
				fc.setSite(facility.getSite());
				fc.setSurveyResult(facility.getSurveyResult());
				fc.setProId(facility.getProId());
				fc.setPdevId(facility.getPdevId());
			}
		}
		return fc;
	}
	
	@Override
	public Long save(Facility facility) {
		int ret = 0;
		if(facility.getDevId()!=null)
		{
			facility.setDevCode(null);
			if(facility.getDevName()!=null && !"".equals(facility.getDevName())){
				DeviceReg deviceReg = new DeviceReg();
				List<DeviceReg> list = deviceRegMapper.selectByDevId(facility.getDevId());
				String devRemark = null;
				for(int i=0;i<list.size();i++){
					deviceReg = list.get(i);
					if(deviceReg.getDevName()!=null && deviceReg.getDevName().indexOf("(")!=-1){
						devRemark = deviceReg.getDevName().substring(deviceReg.getDevName().indexOf("("));
						deviceReg.setDevName(facility.getDevName()+devRemark);
						deviceRegMapper.updateByPrimaryKeySelective(deviceReg);
					}
					
				}
			}
			
			
			
			ret =  facilityMapper.updateByPrimaryKeySelective(facility);
		}
		else
		{
			facility.setDevCode(siteCodeService.getNewDevCode());
			ret = facilityMapper.insertSelective(facility);
			//生成熔纤盘 新调整不需要在此自动生成熔纤盘
			//ret = genFiberdisc(facility.getDevId(), "A", facility.getFiberDiscNum());
			addConnecter(facility);
		}
		return facility.getDevId();
	}

	public void addConnecter(Facility facility)
	{
		int  count = 0;
		if(!Constants.FACILITTYPE.GLT.toString().equals(facility.getDevType())){
			return;
		}
		try
		{
			count = Integer.valueOf(facility.getDevModel());
			if(count <=0 )
			{
				throw new FrmsException("无效的线芯容量");
			}
		}
		catch(Exception e){
			throw new FrmsException("无效的线芯容量");
		}
		Long devId = facility.getDevId();
		
		FiberdiscGroup agroup = new FiberdiscGroup();
		FiberdiscGroup bgroup = new FiberdiscGroup();
		agroup.setDevId(devId);
		agroup.setSide("A");
		agroup.setDiscNum(1);
		agroup.setPortNum(count);
		fcService.addGroup(agroup);
		bgroup.setDevId(devId);
		bgroup.setSide("B");
		bgroup.setDiscNum(1);
		bgroup.setPortNum(count);
		fcService.addGroup(bgroup);
		linesService.addVirtual(facility.getDevId(),Constants.FACILITTYPE.GLT);
	}
	
	@Override
	public int addObd(Facility facility) {
		int ret = 0;
		//分光器代码
		String devType=Constants.FACILITTYPE.OBD.toString(); 
		Facility parent = facilityMapper.selectByPrimaryKey(facility.getPdevId());
		if(parent==null)
			throw new RuntimeException("所属设施不存在。");
		if(facility.getDevModel()==null)
			throw new RuntimeException("分光器型号不正确。");
		
		//生成分光器代码
		FacilityConditionBean param = new FacilityConditionBean();
		param.setPdevId(parent.getDevId());
		param.setDevCode(StringUtils.genPoint(parent.getDevCode(),FacilityType.FG.toString()));
		param.setDevType(devType);
		String devCode =facilityMapper.queryObdMaxCode(param);
		int num = 0;
		if(devCode!=null)
		{
			devCode = devCode.replaceAll(StringUtils.genPoint(parent.getDevCode(),FacilityType.FG.toString()), "");
			num = Integer.valueOf(devCode,10);
		}
		num ++;
		devCode = StringUtils.genPoint(parent.getDevCode(),FacilityType.FG.toString(num));
		
		facility.setDevCode(devCode);
		facility.setDevType(devType);
		ret = facilityMapper.insertSelective(facility);
		
		Constants.OBDTYPE type = Constants.OBDTYPE.getType(facility.getDevModel());
		if (type==Constants.OBDTYPE.NONE)
			throw new FrmsException("未知的分光器类型。");
		
		//生成分光器分组
		FiberdiscGroup in = new FiberdiscGroup();
		in.setDevId(facility.getDevId());
		in.setDiscNum(1);
		in.setSide("A");
		in.setPortNum(type.getLeftNum());
		fcService.addGroup(in);
		
		
		FiberdiscGroup out = new FiberdiscGroup();
		out.setDevId(facility.getDevId());
		out.setSide("B");
		out.setDiscNum(1);
		out.setPortNum(type.getRightNum());
		fcService.addGroup(out);
		//FiberdiscG
		//在fiberdisc中生存分光器上的点
		//Map<String,List<Fiberdisc>> map = genObdPoint(facility.getDevId(), facility.getDevModel());
		//生成分光器虚拟线
		linesService.addVirtual(facility.getDevId(),Constants.FACILITTYPE.OBD);
		return ret;
	}

	@Override
	public Facility selectById(Long devId) {
		return facilityMapper.selectByPrimaryKey(devId);
	}

	@Override
	public int delete(Long devId) {
		return facilityMapper.deleteByPrimaryKey(devId);
	}

	@Override
	public List<Facility> queryByConditionBean(FacilityConditionBean bean) {
		if(bean ==null) 
			bean = new FacilityConditionBean();
		return facilityMapper.queryByConditionBean(bean);
	}
	
	@Override
	public List<Facility> selectAround(Double curLng, Double curLat,Double distance,String devType,String surveyResult, String areaCode1, Users user) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("curLng",curLng);
		param.put("curLat", curLat);
		param.put("distance", distance);
		param.put("devType", devType);
		param.put("surveyResult", surveyResult);
		param.put("areaCode1", areaCode1);
		param.put("user", user);
		return facilityMapper.selectAround(param);
	}
	
	@Override
	public List<Facility> selectAround1(Double curLng, Double curLat,Double distance,String devType,String surveyResult, String areaCode1, Users user) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("curLng",curLng);
		param.put("curLat", curLat);
		param.put("distance", distance);
		param.put("devType", devType);
		param.put("surveyResult", surveyResult);
		param.put("areaCode1", areaCode1);
		param.put("user", user);
		param.put("pdevIdIsNull", "1");
		return facilityMapper.selectAround(param);
	}
	@Override
	public FacilityBean convert(Facility facility) {
		if(facility==null)
			return null;
		FacilityBean bean = new FacilityBean();
		BeanUtils.copy(facility, bean);
		
		try
		{
		Areas a1 = areasService.selectByCode(bean.getAreaCode1());
		if(a1!=null)
		{
			bean.setAreaName1(a1.getAreaName());
		}
		Areas a2 = areasService.selectByCode(bean.getAreaCode2());
		if(a2!=null)
		{
			bean.setAreaName1(a2.getAreaName());
		}
		
		if(facility.getPdevId()!=null)
		{
			Facility pFacility=this.selectById(facility.getPdevId());
			bean.setPdevName(pFacility.getDevName());
		}
		else
		{
			bean.setPdevName("无");
		}
		
		Map<String,String> mapDevType=basecodeService.getDEVTypeMap();
		if(mapDevType.containsKey(bean.getDevType()))
		{
			      bean.setDevTypeName(mapDevType.get(bean.getDevType()));
		}
		
		
		if(FACILITTYPE.OBD.toString().equals(bean.getDevType()))
		{
			Map<String,String> obdTypeMap=basecodeService.getOBDTypeMap();
			if(obdTypeMap.containsKey(bean.getDevModel()))
			{
				bean.setDevModel(obdTypeMap.get(bean.getDevModel()));
			}
		}
		
		Map<String,String> surveyResultMap=Constants.SurveyResultMap;
		if(surveyResultMap.containsKey(bean.getSurveyResult()))
		{
			bean.setSurveyResultName(surveyResultMap.get(bean.getSurveyResult()));
		}
		
		if(Constants.FlagMap.containsKey(bean.getFlag()))
		{
			bean.setFlagName(Constants.FlagMap.get(bean.getFlag()));
		}
		
		Project project=projectService.selectById(bean.getProId());
		if(project!=null)
		{
			bean.setProIdName(project.getProName());
		}
		}
		catch(Exception ex)
		{
			
		}
		
		
		return bean;
	}

	@Override
	public List<FacilityBean> convert(List<Facility> list) {
		if(list==null||list.size()==0)
			return null;
		List<FacilityBean> rs = new ArrayList<FacilityBean>();
		for(Facility facility:list)
		{
			FacilityBean bean = convert(facility);
			rs.add(bean);
		}
		return rs;
	}

	@Override
	public int genFiberdisc(Long devId, String side, Integer fiberDiscNum,Integer portNum,Integer fromIndex)
	{
		int ret =0;
		if(fiberDiscNum==null||(fiberDiscNum<=0))
			return ret;
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f==null)
			return 0;
		String code =f.getDevCode();
		/*FiberdiscConditionBean param = new FiberdiscConditionBean();
		param.setDevId(devId);
		param.setSide(side);
		List<Fiberdisc> list = fiberdiscMapper.queryBydev(param);
		for(Fiberdisc disc :list)
		{
			List<?> tempList = linesService.queryByPoint(disc.getPort01());
			if(tempList!=null&&tempList.size()>0)
			{
				throw new FrmsException("端子已经成端或被占用。");
			}
			fiberdiscMapper.deleteByPrimaryKey(disc.getFiberDiscId());
		}*/
		for(long row =( fromIndex==null?1:fromIndex);row <=fiberDiscNum +(fromIndex==null?0:fromIndex -1);row ++)
		{
			for(long col = 1;col<=portNum;col++)
			{
				Fiberdisc disc = new Fiberdisc();
				disc.setDevId(devId);
				disc.setDiscRowNo(row);
				disc.setDiscColNo(col);
				disc.setSide(side);
				disc.setDiscCode(StringUtils.genPoint(code,side,row));
				disc.setPort01(StringUtils.genPoint(code,side,row,col));
				ret = fiberdiscMapper.insertSelective(disc);
			}
		}
		return ret;
	}

	@Override
	public int addFiberdisc(Long devId, String side, Integer discRowNo) {
		int ret = 1;
		FiberdiscConditionBean param = new FiberdiscConditionBean();
		param.setDevId(devId);
		param.setSide(side);
		param.setDiscRowNo(Long.valueOf(discRowNo));
		List<Fiberdisc> list = fiberdiscMapper.queryBydev(param);
		if(list!=null&&list.size()>0)
		{
			throw new FrmsException("熔纤盘已经存在");
		}
		if(discRowNo==null||discRowNo<1)
		{
			throw new FrmsException("无效的熔纤盘序号");
		}
		param.setDiscRowNo(Long.valueOf(discRowNo -1));
		list = fiberdiscMapper.queryBydev(param);
		if((discRowNo!=1)&&(list==null|| list.size()==0))
		{
			throw new FrmsException("熔纤盘序号必须连续。");
		}
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f==null)
			return 0;
		String code =f.getDevCode();
		for(long col = 1;col<=12;col++)
		{
			Fiberdisc disc = new Fiberdisc();
			disc.setDevId(devId);
			disc.setDiscRowNo(Long.valueOf(discRowNo));
			disc.setDiscColNo(col);
			disc.setSide(side);
			disc.setPort01(StringUtils.genPoint(code,side,discRowNo,col));
			ret *= fiberdiscMapper.insertSelective(disc);
		}
		return ret;
	}
	
	
	@Override
	public int fiberLocate(Long devId, Long sectId, String discCode,
			Integer fromFiberNum, Integer endFiberNum, Integer fromPort) {
		
		//纤芯数
		int fiberCount = endFiberNum - fromFiberNum + 1;
		FiberdiscConditionBean param = new FiberdiscConditionBean();
		param.setDevId(devId);
		param.setDiscCode(discCode);
		List<FiberdiscInfo> list =fiberdiscMapper.queryDiscCodes(param);
		if(list.size()!=1)
		{
			//目前只允许单盘成端
			throw new FrmsException("获取熔纤盘/插板错误！");
		}
		
		List<Fiberdisc> fiberdiscList = fiberdiscMapper.queryByFiberNum(devId, sectId, fromFiberNum, endFiberNum);
		if (fiberdiscList.size()>0)
		{
			for(Fiberdisc disc:fiberdiscList)
			{
				throw new FrmsException("纤芯号("+disc.getFiberNum()+")已成端。");
			}
		}	
		List<Lines> fiberLines =  linesService.queryFiberByFiberNo(sectId, fromFiberNum, endFiberNum);
		if(fiberLines.size() != fiberCount)
		{
			//-----导数------------------
			throw new FrmsException("不存在指定的纤芯。");
			//return 0;
		}
		for(Lines lines :fiberLines)
		{
			if(lines.getAcode()!=null && lines.getZcode()!=null)
			{
				throw new FrmsException("纤芯("+lines.getFiberNo()+")已经占用！");
			}
		}
		FiberdiscInfo fiberdiscInfo = list.get(0);
		List<Fiberdisc> allFiberDisc = fiberdiscInfo.getAll();
		int allnum = allFiberDisc.size();
		if(fiberCount > allFiberDisc.size())
		{
			throw new FrmsException("纤芯范围不能超过" + allnum+"!");
		}
		
		List<Fiberdisc> unused = fiberdiscInfo.getUnused();
		Collections.sort(unused);
		
		//获取连续未使用的熔纤盘
		unused = getSeriesCount(unused,fromPort);
		if(fiberCount > unused.size())
		{
			//-----导数------------------
			throw new FrmsException("纤芯范围不能超过连续的空闲端口!");
		}
		//获取实际需要使用的熔纤盘
		unused =unused.subList(0, fiberCount);
		//对需要成端的光纤按纤芯序号排序
		Collections.sort(fiberLines);
		
		
		List<Fiberdisc> discList = locateDisc(unused,sectId,fromFiberNum);
		
		List<Lines> linesList =locateFiber(discList,fiberLines);
		
		linesService.update(linesList);
		int ret = 0;
		for(Fiberdisc disc :discList)
		{
			ret +=fiberdiscMapper.updateByPrimaryKeySelective(disc);
		}
		return ret;
	}
	
	/**
	 * 多熔纤盘连续成段
	 */
	@Override
	public int fiberLocate(Long devId, Long sectId, String startDiscCode,
			Integer fromFiberNum, Integer endFiberNum, Integer fromPort,String side,String direction) {
		
		LocateInfo info = new LocateInfo();
		info.setDevId(devId);
		info.setSectId(sectId);
		info.setDiscCode(startDiscCode);
		info.setFromFiberNum(fromFiberNum);
		info.setEndFiberNum(endFiberNum);
		info.setFromPortNum(fromPort);
		info.setSide(side);
		info.setDirection(direction);
		return fiberLocate(info);
	}
	/**
	 * 多熔纤盘连续成段
	 */
	@Override
	public int fiberLocate(LocateInfo info) {
		Long devId = info.getDevId();
		Long sectId = info.getSectId();
		String startDiscCode = info.getDiscCode();
		Integer fromFiberNum = info.getFromFiberNum();
		Integer endFiberNum = info.getEndFiberNum();
		Integer fromPort = info.getFromPortNum();
		String side = info.getSide();
		String direction = info.getDirection();
		CableSection cs = cableSectionMapper.selectByPrimaryKey(sectId);
		if(cs==null||cs.getFiberNum()==null||cs.getFiberNum().intValue()==0){
			throw new FrmsException("无效的光缆段(或光缆段纤芯数)不正确");
		}
		
		fromFiberNum = fromFiberNum==null?new Integer(1):fromFiberNum;
		endFiberNum   = endFiberNum ==null? new Integer(cs.getFiberNum().intValue()):endFiberNum;
		fromPort = fromPort==null?new Integer(1):fromPort;
		direction= direction==null||"".equals(direction.trim())?"1":direction;
		
		List<Fiberdisc> discList = fiberdiscMapper.queryByFiberNum(devId, sectId, fromFiberNum, endFiberNum);
		if(discList!=null&&discList.size()>1){
			throw new FrmsException("该光缆段在此设施上已有成端。");
		}
		
		discList = fiberdiscMapper.checkOccupyFiberdisc(devId, side, direction);
		List<Fiberdisc> locateList = null;
		for(int i = 0;i<discList.size();i++){
			Fiberdisc fiberdisc = discList.get(i);
			if(startDiscCode.equals(fiberdisc.getDiscCode())&&fromPort==fiberdisc.getDiscColNo().intValue())
			{
				if(discList.size() - i < endFiberNum - fromFiberNum + 1){
					throw new FrmsException("没有足够的连续空闲熔纤盘。");
				}
				locateList = discList.subList(i, i+ endFiberNum - fromFiberNum +1);
				break;
			}
		}
		
		if(locateList==null||locateList.size()!=endFiberNum - fromFiberNum + 1)
		{
			throw new FrmsException("熔纤盘端子数量与线芯数不匹配。");
		}
		
		for(Fiberdisc fiberdisc:locateList){
			if(fiberdisc.getSectId()!=null||fiberdisc.getFiberNum()!=null){
				throw new FrmsException("熔纤盘端子（"+fiberdisc.getPort01()+"）已被占用。" );
			}
		}
		
		List<Lines> allFibers = linesService.queryFiberByFiberNo(sectId,fromFiberNum,endFiberNum);
		if(allFibers==null||allFibers.size()!=endFiberNum - fromFiberNum + 1){
			throw new FrmsException("光缆段实际纤芯数与所填纤芯数不一致。");
		}
		
		for(Lines line :allFibers){
			if(line.getAcode()!=null&&line.getZcode()!=null){
				throw new FrmsException("纤芯号("+line.getFiberNo()+")已成端！");
			}
		}
		
		List<Lines> locateLines = new ArrayList<Lines>(0);
		for(int index = fromFiberNum,start=0;index<=endFiberNum;index ++,start++){
			Fiberdisc fd = locateList.get(start);
			fd.setSectId(sectId);
			fd.setFiberNum((long)index);
			String port = fd.getPort01();
			if(fd.getCreateTime()==null)
			{
				fd.setCreateTime(new Date());
				fd.setCreateUser(info.getUserId());
			}else{
				fd.setLastModifyTime(new Date());
				fd.setLastModifyUser(info.getUserId());
			}
			locateList.set(start,fd);
			
			for(Lines line:allFibers){
				if(line.getFiberNo().intValue()==index){
					allFibers.remove(line);
					if(line.getAdevId()!=null&&line.getAdevId().longValue()==devId.longValue()){
						line.setAcode(port);
					}
					else if(line.getZdevId()!=null&&line.getZdevId().longValue()==devId.longValue()){
						line.setZcode(port);
					}
					else{
						throw  new FrmsException("光缆段纤芯号("+index+")位置信息有错误！");
					}
					locateLines.add(line);
					break;
				}
			}
			
		}
		if(locateList.size() !=locateLines.size()){
			throw new FrmsException("熔纤盘与纤芯不匹配！");
		}
		linesService.update(locateLines);
		int ret = 0;
		for(Fiberdisc disc :locateList){
			ret +=fiberdiscMapper.updateByPrimaryKeySelective(disc);
		}
		return ret;
	}
	
	
	@Override
	public List<LockKey> queryKeyList(Long uid, Facility fa) {
		if(uid==null)
			return null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", uid);
		map.put("dev", fa);
		return facilityMapper.queryKeyListByMap(map);
	}
	
	@Override
	public int unFiberLocate(Long devId, Long sectId, Integer fromFiberNum,
			Integer endFiberNum,Long userId) {
		if(devId==null||sectId==null||fromFiberNum==null||endFiberNum==null)
			throw new FrmsException("不能获取对应的成端信息。");
		int ret = linesService.unFiberlocate(devId, sectId, fromFiberNum, endFiberNum);
		int ret1 =fiberdiscMapper.unlocate(devId, sectId, fromFiberNum, endFiberNum,userId);
		if(ret != ret1)
		{
			throw new FrmsException("解成端发生错误，数据不匹配");
		}
		return ret;
	}
	
	@Override
	public int unFiberLocate(Long devId, Long sectId,Long userId) {
		if(devId==null||sectId==null)
			throw new FrmsException("不能获取对应的成端信息。");
		int ret = linesService.unFiberlocate(devId, sectId,userId);
		int ret1 =fiberdiscMapper.unlocate(devId, sectId,userId);
		return ret+ret1;
	}

	@Override
	public int addJumper(Long adevId, String acode, Long zdevId, String zcode,
			String srvName,Long orgId) {
		return linesService.addJumper(adevId, acode, zdevId, zcode, srvName,orgId);
	}

	@Override
	public int saveJumper(JumperInfo jumperInfo) {
		int ret =0;
		String code;
		String acode,zcode;
		code = jumperInfo.getCode();
		acode = jumperInfo.getAcode();
		zcode = jumperInfo.getZcode();
		if(jumperInfo.getDevId()==null||code==null||"".equals(code.trim()))
		{
			throw new FrmsException("当前端子信息错误");
		}
		if (acode!=null&&acode.equals(zcode))
		{
			throw new FrmsException("两条跳纤不能相同。");
		}
		
		ret +=addJumper(jumperInfo.getAlinesId(),jumperInfo.getDevId(),code,jumperInfo.getAdevId(),acode,jumperInfo.getSrvName(),jumperInfo.getOrgId(),jumperInfo.getAunknownPointName(),jumperInfo.getUserId());
		ret +=addJumper(jumperInfo.getZlinesId(),jumperInfo.getDevId(),code,jumperInfo.getZdevId(),zcode,jumperInfo.getSrvName(),jumperInfo.getOrgId(),jumperInfo.getZunknownPointName(),jumperInfo.getUserId());
		
		return ret;
	}
	
	public int  addJumper(Long linesId,Long curDevId,String curCode,Long otherDevId,String otherCode,String srvName,Long orgId,String unknownPointName,Long userId)
	{
		int ret = 0;
		otherCode = (otherCode==null||"".equals(otherCode.trim()))?null:otherCode;
		srvName = (srvName==null||"".equals(srvName.trim()))?null:srvName;
		unknownPointName =(unknownPointName==null||"".equals(unknownPointName.trim()))?null:unknownPointName;
		//之前已经存在连线
		if(linesId !=null)
		{
			Lines line = linesService.selectById(linesId);
			if (LineType.toType(line.getLineType()) == LineType.FIBER) //如果之前是纤芯成端，则什么都不操作
				return ret;
			//删除存在的线
			
			if(otherDevId==null&&otherCode==null&&unknownPointName==null)
			{
				int a = linesService.deleteById(linesId);
				return a;
				//return linesService.deleteById(line.getLineId());
			}
			else{
				String acode = line.getAcode();
				String zcode = line.getZcode();
				line.setAcode(curCode.equals(acode)?curCode:otherCode);
				line.setAdevId(curCode.equals(acode)?curDevId:otherDevId);
				line.setZcode(curCode.equals(zcode)?curCode:otherCode);
				line.setZdevId(curCode.equals(zcode)?curDevId:otherDevId);
				line.setSrvName(srvName);
				line.setUnknownPointName(unknownPointName);
				line.setLastModifyTime(new Date());
				line.setLastModifyUser(userId);
				ret = linesService.updateAllColoumn(line);
				if(otherDevId!=null&&otherCode!=null)
				{
					afterCheckPoint(otherDevId, otherCode);
				}
			}
		}
		else
		{
			
			if((otherDevId!=null&&otherCode!=null)||unknownPointName!=null){
				ret =linesService.addJumper(curDevId, curCode, otherDevId, otherCode, srvName, orgId, unknownPointName,userId);
				afterCheckPoint(curDevId,curCode);
				if(otherDevId!=null&&otherCode!=null)
				{
					afterCheckPoint(otherDevId,otherCode);
				}
			}
//			//另一端不为空才继续添加
//			if(otherDevId!=null&&otherCode!=null&&!"".endsWith(otherCode))
//			{
//				beforeCheckPoint(otherDevId,otherCode);
//				beforeCheckPoint(curDevId,curCode);
//				ret =addJumper(curDevId, curCode, otherDevId, otherCode, srvName,orgId);
//			}
		}
		return ret;
	}
	
	
	public void beforeCheckPoint(Long devId,String code)
	{
		FiberdiscConditionBean param = new FiberdiscConditionBean();
		param.setDevId(devId);
		param.setPort01(code==null?"":code);
		List<?> list = fiberdiscMapper.queryBydev(param);
		if(list==null||list.size()!=1)
		{
			throw new FrmsException("端子信息错误。");
		}
		List<?> list1 = linesService.queryByPoint(code);
		if(list1!=null&&list1.size()>1)
		{
			throw new FrmsException("不能继续在"+code+"端子上连接跳纤！");
		}
	}
	public void afterCheckPoint(Long devId,String code)
	{
		FiberdiscConditionBean param = new FiberdiscConditionBean();
		param.setDevId(devId);
		param.setPort01(code==null?"":code);
		List<?> list = fiberdiscMapper.queryBydev(param);
		if(list==null||list.size()!=1)
		{
			throw new FrmsException("端子信息错误。");
		}
		List<?> list1 = linesService.queryByPoint(code);
		if(list1!=null&&list1.size()>2)
		{
			throw new FrmsException("不能继续在"+code+"端子上连接跳纤！");
		}
	}
	/**
	 * 获取连续未使用的熔纤盘端口
	 * @param list
	 * @param from
	 * @return
	 */
	public List<Fiberdisc> getSeriesCount(List<Fiberdisc> list,int from)
	{
		List<Fiberdisc> retList = new ArrayList<Fiberdisc>();
		Fiberdisc disc = ListUtils.get(list, from);
		if(disc==null)
		{
			return retList;
		}
		retList.add(disc);
		int index = list.indexOf(disc);
		while(index <list.size())
		{
			Fiberdisc d = ListUtils.get(list, ++from);
			if(d==null||(index +1 !=list.indexOf(d)))
			{
				return retList;
			}
			else
			{
				retList.add(d);
				index ++;
			}
		}
		
		return retList;
	}
	
	/**
	 * 按照给定纤芯范围，和起始端口，批量设置成端信息
	 * @param list
	 * @param sectId
	 * @param fromFiberNum
	 * @param endFiberNum
	 * @param fromPort
	 * @return
	 */
	public List<Fiberdisc> locateDisc(List<Fiberdisc> list ,Long sectId,Integer fromFiberNum)
	{
		List<Fiberdisc> locateList = new ArrayList<Fiberdisc>();
		for(int index = 0;index<list.size();index++)
		{
			Fiberdisc disc = list.get(index);
			disc.setSectId(sectId);
			disc.setFiberNum((long)(fromFiberNum+index));
			locateList.add(disc);
		}
		return locateList;
	}
	public List<Lines> locateFiber(List<Fiberdisc> discList,List<Lines> lineList )
	{
		List<Lines> retList = new ArrayList<Lines>();
		if(discList.size()!=lineList.size())
		{
			//出现这种问题，前边业务逻辑有错。
			throw new FrmsException("异常错误！");
		}
		for(int index = 0;index<discList.size();index++)
		{
			Lines lines = lineList.get(index);
			if(lines.getAdevId().longValue()==discList.get(index).getDevId().longValue())
			{
				//A端设施和熔纤盘设施一致
				if(lines.getAcode()!=null)
				{
					throw new FrmsException(lines.getAcode() +"已成端，不能重复操作。");
				}
				lines.setAcode(discList.get(index).getPort01());
			}
			else if(lines.getZdevId().longValue()==discList.get(index).getDevId().longValue())
			{
				if(lines.getZcode()!=null)
				{
					throw new FrmsException(lines.getZcode() +"已成端，不能重复操作。");
				}
				lines.setZcode(discList.get(index).getPort01());
			}
			else
			{
				//理论上不应该出现这样的问题。
			}
			retList.add(lines);
		}
		return retList;
	}

	@Override
	public ResultData facilityFromdevMac(HttpSession session, Facility fa) {
		// TODO Auto-generated method stub
		ResultData result = new ResultData();
		List<FacilityFromMac> list = new ArrayList<FacilityFromMac>();
		Users users = (Users)session.getAttribute("users");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId",users.getUserId().toString());
		map.put("devMac", fa.getDevMac());
		
		FacilityFromMac facilityFromMac = facilityMapper.facilityFromdevMac(map);
		
		if(facilityFromMac != null){
			list.add(facilityFromMac);
			result.setR("1");
			result.setDt(list);
			result.setR_content("根据mac地址获取设施信息成功！");
			return result;
		}
		result.setR("0");
		result.setR_content("根据mac地址获取设施信息失败！");
		
		return result;
	}

	/**
	 * 获取设施列表(分页)
	 */
	@Override
	public PageBean queryByConditionBean(FacilityConditionBean bean,
			Users user, PageBean pb) {
		
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("facilityCondition", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		pb.setRows(facilityMapper.queryCountByConditionMap(conditionMap));//;
		pb.setList(this.convert(facilityMapper.queryByConditionMap(conditionMap)));		
				
		return pb;
	}
	
	/**
	 * 根据地区code获取设施坐标并根据坐标获取半径内的所有设施
	 */
	@Override
	public PageBean queryDistanceFacilityByCondition(FacilityConditionBean bean,
			Users user, PageBean pb) {
		
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("facilityCondition", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		List<Facility> fOldList =facilityMapper.queryByConditionMap(conditionMap);
		
		try{
			if(fOldList!=null && fOldList.size()>0){
				Facility fb = (Facility)fOldList.get(0);
				Double curLng = Double.valueOf(fb.getLongitude());
				Double curLat = Double.valueOf(fb.getLatitude());
				pb.setPageSize(1);
				List<Facility> fNewList = selectAround(curLng,curLat,500.00,null,null,bean.getAreaCode1(),user);
				//去除相同的，将一开始查的坐标放在开始位置
				fNewList.remove(fb);
				fNewList.add(0, fb);
				
				for (Facility facility : fNewList) {					
					LatLng latLng=Map_Util.Gps84_To_bd09(Double.parseDouble(facility.getLatitude()), Double.parseDouble(facility.getLongitude()));
					facility.setLongitude(latLng.getLongitude()+"");
					facility.setLatitude(latLng.getLatitude()+"");										
				}
				
				pb.setList(this.convert(fNewList));
			}
		}catch(Exception e){System.out.println(e.getMessage());}
				
		return pb;
	}
	
	@Override
	public List<FacilityBean> selectAroundConvert(Double curLng, Double curLat,Double distance,String devType,String surveyResult, String areaCode1, Users user) {
		List<Facility> fNewList = selectAround(curLng,curLat,500.00,null,null,areaCode1,user);
		for (Facility facility : fNewList) {
			LatLng latLng=Map_Util.Gps84_To_bd09(Double.parseDouble(facility.getLatitude()), Double.parseDouble(facility.getLongitude()));
			facility.setLongitude(latLng.getLongitude()+"");
			facility.setLatitude(latLng.getLatitude()+"");			
		}
		return this.convert(fNewList);
	}

	@Override
	public FacilityBean selectDetailById(Long devId) {
		return this.convert(facilityMapper.selectByPrimaryKey(devId));
	}

	public List<Facility> queryListByAccessIds(String ids) {
		return facilityMapper.queryListByAccessIds(ids.split(","));

	}
	public Integer queryCountByConditionBean(FacilityConditionBean bean) {
		return facilityMapper.queryCountByConditionBean(bean);

	}
/**
 * 导出'设施导入模版'Excel文件(.xls)
 */
	@Override
	public void exportFacilityTemp(HttpServletResponse response,HttpServletRequest request) {
		 HSSFWorkbook wb = new HSSFWorkbook();
	        CellStyle style = wb.createCellStyle();
	        style.setAlignment(CellStyle.ALIGN_CENTER);
	        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
	        HSSFSheet sheet1 = wb.createSheet("facilitiesImportTemplate");
	        sheet1.createFreezePane(255, 1);
	        //set cells data format to text
	        for(int i = 0;i<20;i++){
	        	sheet1.setDefaultColumnStyle(i, style);
	        }
	        //set the dropDownList data of devType 设施类型
	        @SuppressWarnings("deprecation")
			CellRangeAddressList regions = new CellRangeAddressList(1,1000,1,1);  
	        // DropDownList content  
	        List<KVBean> devTypeList = basecodeService.getDEVTypeList();
	        String[] devTypeArray = new String[devTypeList.size()];
	        for(int i=0;i<devTypeList.size();i++){
	        	devTypeArray[i]=devTypeList.get(i).getName()+devTypeList.get(i).getValue();//F .substring(0,2)
	        }
	        DVConstraint constraint = DVConstraint.createExplicitListConstraint(devTypeArray);  
	        // bind DropDownList region and constraint to dataValidation  
	        HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);  
	        // addValidation to sheet  
	        sheet1.addValidationData(data_validation);  
	        
	        //set the dropDownList data of areaCode1 所属片区
	        @SuppressWarnings("deprecation")
			CellRangeAddressList regions2 = new CellRangeAddressList(1,1000,8,8);  
	        // DropDownList content  
	        Users user=(Users) request.getSession().getAttribute("platformUser");	
	        List<Areas> areaList = areasService.selectByOrgId(user.getOrgId());
	        String[] areaArray = new String[areaList.size()];
	        for(int i=0;i<areaList.size();i++){
	        	areaArray[i]=areaList.get(i).getAreaName()+"  areaCode1="+areaList.get(i).getAreaCode();//F .substring(indexOf("areaCode1=")+10).trim()
	        }
	        DVConstraint constraint2 = DVConstraint.createExplicitListConstraint(areaArray);  
	        // bind DropDownList region and constraint to dataValidation  
	        HSSFDataValidation data_validation2 = new HSSFDataValidation(regions2,constraint2);  
	        // addValidation to sheet  
	        sheet1.addValidationData(data_validation2);  
	        
	        HSSFRow headRow = sheet1.createRow(0);
	        headRow.createCell(0).setCellValue("设施名称（必填）");
	        headRow.createCell(1).setCellValue("设施类型");
	        headRow.createCell(2).setCellValue("设施型号");
	        headRow.createCell(3).setCellValue("竣工日期");
	        headRow.createCell(4).setCellValue("详情地址");
	        headRow.createCell(5).setCellValue("经度");
	        headRow.createCell(6).setCellValue("纬度");
	        headRow.createCell(7).setCellValue("附近特征描述");
	        headRow.createCell(8).setCellValue("所属片区");
	        headRow.createCell(9).setCellValue("所属机房");
	        headRow.createCell(10).setCellValue("所属工程");
	        // 设置下载时客户端Excel的名称
	        response.setContentType("application/vnd.ms-excel");
	        try {
				response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("设施导入模板.xls","utf-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        OutputStream ouputStream;
	        try {
	            ouputStream = response.getOutputStream();
	            wb.write(ouputStream);
	            ouputStream.flush();
	            ouputStream.close();
	        } catch (IOException e) {
	            System.err.println("导出设施模板"+e);
	        } finally {
	            wb = null;
	            ouputStream = null;
	            style = null;
	        }
	}

/**
 * 接收前台传入的Excel文件,执行导入数据
 */
	@Override
	public String importFacilitiesByExcel(HttpServletRequest request,HttpServletResponse response,MultipartHttpServletRequest multiRequest) {
		try {
            MultipartFile file = multiRequest.getFile("importExcel");
            if (file.isEmpty()) {
                return "文件为空";
            }else if(!".xls".equals(file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."),file.getOriginalFilename().length()))){
                return "扩展名不正确";
            }
            return execImport(this.getFacilities(file.getInputStream()), request);
        } catch (IOException e) {
            System.err.println("导入设施失败:"+e.getLocalizedMessage());
            return "失败";
        } 
	}
	/*
	 * 将Excel中的设施数据存入List<Facility>,方便之后插入数据库
	 */
	private Map<String, Object> getFacilities(InputStream is) {
		 Map<String, Object> map = new HashMap<String,Object>();
	        
	        List<Facility> facilityList = new ArrayList<Facility>();
	        Facility facility = null;
	        POIFSFileSystem fs = null;
	        HSSFWorkbook wb = null;
	        HSSFSheet sheet = null;
	        HSSFRow row = null;
	        int rows = 0;
	        try {
	            try {
	                fs = new POIFSFileSystem(is);
	                wb = new HSSFWorkbook(fs);
	            } catch (IOException e) {
	                return null;
	            }
	            sheet = wb.getSheetAt(0);
	            rows = sheet.getPhysicalNumberOfRows();
	            Pattern numberPattern = Pattern.compile("[0-9]*");
	            Pattern decimalPattern = Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?");
	            for (int i = 1; i < rows; i++) {
	            	facility = new Facility();
	                row = sheet.getRow(i);
	                // 排除空cell问题
	                if(null == row || null == row.getCell(0) || "".equals(row.getCell(0).getStringCellValue().trim()) ){
	                	continue;
	                }
	                // 1.设置设施名称
	                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
	                String devName = row.getCell(0).getStringCellValue();
	                FacilityConditionBean fcb = new FacilityConditionBean();
	                fcb.setDevName(devName.trim());
	                List<Facility> checkList = this.queryByConditionBean(fcb);
	                if(checkList.size()>0){
	                	map.put("result", "第"+i+"行,设施名称已经存在. ");
	                	return map;
	                }
	                facility.setDevName(devName);

	                // 2.设置设施类型
	                if(row.getCell(1)!=null){
		                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
		                String devTypeStr = row.getCell(1).getStringCellValue();
		                if(devTypeStr.length()>1){
		                	String devType = devTypeStr.substring(0,2);
		                	facility.setDevType(devType);
		                }
	                }
	                //3.设施型号
	                if(row.getCell(2)!=null){
			                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
			                String devModel = row.getCell(2).getStringCellValue();
			                facility.setDevModel(devModel);
	                }
	                //4.竣工日期
	                if(row.getCell(3)!=null){
		                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		                String completeDate = row.getCell(3).getStringCellValue();
		                SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		                try{
		                	facility.setCompleteDate(sdf.parse(completeDate));
		                }catch(Exception e)
		                {
		                	map.put("result", "第"+i+"行,竣工日期数据错误,请检查日期格式是否为 yyyy-MM-dd "+e.toString());
		    	            return map;
		                }
	                }
	                //5.详情地址
	                if(row.getCell(4)!=null){
		                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
		                String devAddr = row.getCell(4).getStringCellValue();
		                facility.setDevAddr(devAddr);
	                }
	                //6.经度
	                if(row.getCell(5)!=null){
		                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
		                String longitude = row.getCell(5).getStringCellValue();
		                facility.setLongitude(longitude);
	                }
	                //7.纬度
	                if(row.getCell(6)!=null){
		                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
		                String latitude = row.getCell(6).getStringCellValue();
		                facility.setLatitude(latitude);
	                }
	                //8.附近特征描述
	                if(row.getCell(7)!=null){
		                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
		                String devDesc = row.getCell(7).getStringCellValue();
		                facility.setDevDesc(devDesc);
	                }
	                //9.所属片区
	                if(row.getCell(8)!=null){
		                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
		                String areaCode1Str = row.getCell(8).getStringCellValue();
		                if(areaCode1Str.indexOf("areaCode1=")!=-1){
		                	String areaCode1= areaCode1Str.substring(areaCode1Str.indexOf("areaCode1=")+10).trim();
		                	facility.setAreaCode1(areaCode1);
		                }
	                }
	                //10.所属机房
	                if(row.getCell(9)!=null){
		                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
		                String room = row.getCell(9).getStringCellValue();
		                facility.setRoom(room);
	                }
	                //11.所属工程
	                if(row.getCell(10)!=null){
		                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
		                String proId = row.getCell(10).getStringCellValue();
		                try{
		                	if(proId!=""){
		                		Project p = new Project();
		                		p.setProName(proId);
		                		PageBean pageBean = projectService.queryByProjectBean(p, new PageBean(),null);//ly20160909 可能有bug 未测试
		                		if(pageBean.getList().size()==1){
		                			Project project = (Project)(pageBean.getList().get(0));
		                			facility.setProId(project.getProId());
		                		}else if(pageBean.getList().size()>1){
		                			map.put("result", "第"+i+"行,无法确定工程名称所对应的id,因为工程列表存在多条相同名称的工程.请在<工程管理>删除问题数据 ");
				    	            return map;
		                		}
		                		else{
		                			facility.setProId(Long.parseLong(proId));
		                		}
		                	}
		                }catch(Exception e){
		                	map.put("result", "第"+i+"行,所属工程数据错误,输入非数字类型或工程名称不存在. "+e.toString());
		    	            return map;
		                }
	                }
	                
	                
	                facilityList.add(facility);
	            }
	            map.put("facilityList", facilityList);
	            return map;
	        } catch (Exception e) {
	            map.put("result", e.getMessage());
	            return map;
	        } finally {
	        	facilityList = null;
	        	facility = null;
	            fs = null;
	            wb = null;
	            sheet = null;
	            row = null;
	        }
	}
/*
 * 将List<Facility>中数据写入数据库
 */
	private String execImport(Map<String, Object> map, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
		List<Facility> list = (List<Facility>) map.get("facilityList");
        if(list==null){
            return map.get("result").toString();
        }
        
        try {
            if(null != list && list.size()>0){ 
            	int insertCount=0;
            	List<Facility> temp = null;
                for(Facility facility:list) {
                	facility.setDevCode(siteCodeService.getNewDevCode());
                	facility.setSurveyResult("0");
                	facility.setFlag("1");
                	Users user = (Users)request.getSession().getAttribute("platformUser");
                	facility.setOrgId(user.getOrgId());
                	FacilityConditionBean bean = new FacilityConditionBean();
                	bean.setDevName(facility.getDevName());
                	temp = this.queryByConditionBean(bean);
                    if(null == temp || temp.size()==0){
                    	String temp2 = facility.getDevName();
                    	if(null != temp2 && temp2 !=""){
                    		int i = facilityMapper.insertSelective(facility);
                    		if(i==1){
                    			insertCount+=1;
                    		}
                    	}
                    }else{
                    	//facilityMapper.updateByPrimaryKeySelective(facility);
                    }
                }
                return "导入完成,共"+insertCount+"条";
            }else{
                return "失败";
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "导入完成";
    }
	//zhouyu 1/4修改返回值类型facility-facilityBean
	@Override
	public List<FacilityBean> queryListByContent(String content) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("content",content);
		return facilityMapper.queryListByContent(map);
	}
	
	/**
	 * 获取设施列表(分页)
	 */
	@Override
	public PageBean simpleQueryByConditionBean(FacilityConditionBean bean,
			Users user, PageBean pb) {
		
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("facilityCondition", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		pb.setRows(facilityMapper.queryCountByConditionMap(conditionMap));//;
		pb.setList(facilityMapper.queryByConditionMap(conditionMap));		
				
		return pb;
	}
	
	@Override
	public List<Facility> selectByDevIds(String workId) {
		List<Facility> fList = new ArrayList<Facility>();
		List<InspectImg> IList = inspectImgMapper.selectByWorkIds(Long.parseLong(workId));
		Facility f = null;
		for(InspectImg i : IList){
			f = facilityMapper.selectByPrimaryKey(i.getDevId());
			fList.add(f);
		}
		return fList;
	}

	/**
	 * 同时保存设施基本信息，和照片信息
	 */
	@Override
	public Long save(Facility facility, List<FacilityImg> images) {
		Long ret = 0L;
		if(facility.getDevId()==null){
			if(Constants.FACILITTYPE.OBD.toString().equals(facility.getDevType()))
			{
				addObd(facility);
			}
			else
			{
				facility.setDevCode(siteCodeService.getNewDevCode());
				facilityMapper.insertSelective(facility);
			}
			ret =facility.getDevId();
			for(FacilityImg img:images){
				img.setDevCode(facility.getDevCode());
				img.setDevId(facility.getDevId());
				facilityImgMapper.insertSelective(img);
			}
		}
		else{
			facilityMapper.updateByPrimaryKeySelective(facility);
			Facility f = facilityMapper.selectByPrimaryKey(facility.getDevId());
			for(FacilityImg img:images){
				img.setDevCode(f.getDevCode());
				img.setDevId(f.getDevId());
				facilityImgMapper.insertSelective(img);
			}
			ret = facility.getDevId();
		}
		return ret;
	}
	
	/**
	 * 同时保存设施基本信息，和照片信息
	 */
	@Override
	public Long edit(Facility facility, List<FacilityImg> images) {
		Long ret = 0L;
		if(facility.getDevId()==null){
			System.out.println("进来了吗");
			if(Constants.FACILITTYPE.OBD.toString().equals(facility.getDevType()))
			{
				addObd(facility);
			}
			else
			{
				facility.setDevCode(siteCodeService.getNewDevCode());
				facilityMapper.insertSelective(facility);
			}
			ret =facility.getDevId();
			for(FacilityImg img:images){
				img.setDevCode(facility.getDevCode());
				img.setDevId(facility.getDevId());
				facilityImgMapper.insertSelective(img);
			}
			
//			List<FacilityImg> facilityImg=facilityImgMapper.selectByDevId(facility.getDevId());
//			for (int i = 0; i < facilityImg.size(); i++) {
//				for (int j = 0; j < paths.length; j++) {
//					if(facilityImg.get(i).getImgUrl().equals(paths[j])){
//						facilityImg.get(i).setImgUrl(null);
//					}
//				}
//			}
			
		}
		else{
			facilityMapper.updateByPrimaryKeySelective(facility);
			Facility f = facilityMapper.selectByPrimaryKey(facility.getDevId());
			for(FacilityImg img:images){
				img.setDevCode(f.getDevCode());
				img.setDevId(f.getDevId());
				facilityImgMapper.insertSelective(img);
			}
			ret = facility.getDevId();
		}
		
		
		return ret;
	}


	@Override
	public PageBean queryFacilityByPosition(FacilityConditionBean bean,
			PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("fc", bean);
		conditionMap.put("pb", pb);
		pb.setRows(facilityMapper.countFacilityByPosition(conditionMap));//;
		pb.setList(this.convert(facilityMapper.selectFacilityByPosition(conditionMap)));		
		return pb;
	}
	
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("1");
		l.add("2");
		l.add("3");
		
		List<String> s = l.subList(1, l.size());
		System.out.println(s.size());
	}
	
	public List<Facility> findFacilities(Long orgId) {
		return facilityMapper.findFacilities(orgId);
	}

	
	private boolean excHttpTranslate(List<Facility> fList) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		String post_url="http://api.map.baidu.com/geoconv/v1/";
		try {
			HttpPost post = new HttpPost(post_url);
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			String coords="";
			for(int j=0;j<fList.size();j++){
				String x=fList.get(j).getLongitude().toString();
				String y=fList.get(j).getLatitude().toString();
				String coord=x+","+y+";";
				coords+=coord;
			}
			
			coords=coords.substring(0,coords.length()-1);
			list.add(new BasicNameValuePair("coords", coords));
			list.add(new BasicNameValuePair("from", "1"));
			list.add(new BasicNameValuePair("to", "5"));
			list.add(new BasicNameValuePair("ak", "0a3ma3VXzWL0k8a0TgZ3AjllNDmtgFX5"));
			
			
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefEntity);
			CloseableHttpResponse httpResponse = httpClient.execute(post);
			HttpEntity entity = httpResponse.getEntity();
			if (null != entity) {
				result= EntityUtils.toString(entity,"utf-8");
				System.out.println(result);
			}
			JSONObject json=JSONObject.fromObject(result);
			JSONArray jsonArray = json.getJSONArray("result");
			Object jsonStatus = json.get("status");
			System.err.println(jsonStatus);
			if (Integer.parseInt(jsonStatus.toString())==0) {
				Facility updateBean = null;
				JSONObject item = null;
				for (int i = 0; i < fList.size(); i++) {
					item = jsonArray.getJSONObject(i);
					updateBean = new Facility();
					updateBean.setDevId(fList.get(i).getDevId());
					updateBean.setBaiduX(BigDecimal.valueOf(item.optDouble("x")));
					updateBean.setBaiduY(BigDecimal.valueOf(item.optDouble("y")));
					updateBean.setIsTranslated("1");
					facilityMapper.updateByPrimaryKeySelective(updateBean);
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public Facility selectByCodeMap(String devCode) {
		return facilityMapper.selectByCodeMap(devCode);
	}
	/*
	 * 查询添加普查前和普查后图片
	 * 
	 */
	@Override
	public FacilityImgs selectImgById(Long devId) {
		FacilityImgs facilityImgs=facilityMapper.selectImgById(devId);
//		if(facilityImgs.getImgs01()!=null){
//			List<Imgs01> list = facilityImgs.getImgs01();
//			for (int i = 0; i < list.size(); i++) {
//				String str01 = "http://101.200.217.182:8080/frms"+
//			list.get(i).getImgPath01();//拼接图片地址-普查前
//				list.get(i).setImgPath01(str01);
//			}
//		}
//		if(facilityImgs.getImgs02()!=null){
//			List<Imgs02> list = facilityImgs.getImgs02();
//			for (int i = 0; i < list.size(); i++) {
//				String str02 = "http://101.200.217.182:8080/frms"+
//			list.get(i).getImgPath02();//拼接图片地址=普查后
//				list.get(i).setImgPath02(str02);
//			}
//		}
		if(facilityImgs != null){
//			List<Space> spaceList = spaceMapper.selectListByPrimaryKey(facilityImgs.getDevId());
//			for(Space space : spaceList){
//				List<Piping> pipingList = pipingMapper.selectListByPrimaryKey(space.getSpaceId());
//				for(Piping piping : pipingList){
//					List<Valve> valveList = valveMapper.selectListByPrimaryKey(piping.getPipingId());
//					if(valveList != null){
//						piping.setValvelist(valveList);
//					}
//				}
//				if(pipingList != null){
//					space.setPipinglist(pipingList);
//				}
//			}
//			if(spaceList != null){
//				facilityImgs.setSpacelist(spaceList);
//			}
		}
		return facilityImgs;
	}


	

	@Override
	public int saveOLT(String vender) {
		// TODO Auto-generated method stub
		List<Temps> tempList = tempsMapper.selectByParam(1L);
		List<Fiberdisc> fibList = null;
		List<Discinfo> discinfoList = null;
		Facility facility = null;
		FiberdiscGroup fiberdiscGroup = null;
		Discinfo discinfo = null;
		Fiberdisc fiberdisc = null;
		//保存返回值
		int ret = 0;
		
		//上一个设施名称
		String devName = "";
		
		//熔纤盘ID
		int fiberDiscId = -1; 
		//端口ID
		int portId = -1; 
		
		//ONUID
		int ONUId = -1; 

		//ONU备注名称
		String ONUName = ""; 
		
		for(int i=0;i<tempList.size();i++){
			String a1 = tempList.get(i).getA1();
			String b1 = tempList.get(i).getB2();
			String c1 = tempList.get(i).getC3();
			String d1 = tempList.get(i).getD4();
			String e1 = tempList.get(i).getE5();
			
			if(a1!=null && "".equals(a1)){
				continue;
			}
			
			
			facility = new Facility();
			fiberdiscGroup = new FiberdiscGroup();
			if(!devName.equals(a1)){
				if(fibList!=null || i==tempList.size()){
					for(Discinfo discinfo1 : discinfoList){
						for(Fiberdisc fiberdisc1 : discinfo1.getFibList()){
							if(ret < 1){
								throw new RuntimeException("端子保存失败");
							}
							ret = fiberdiscMapper.insertSelective(fiberdisc1);
						}
					}
					discinfoList = null;
					fibList = null;
				}
				//创建设备
				facility.setDevCode(siteCodeService.getNewDevCode());
				facility.setDevName(a1);
				facility.setAreaCode1("441300");
				if("HW".equals(vender)){
					facility.setDevModel("OLT-HW-MA5608T");
				}else if("ZX".equals(vender)){
					facility.setDevModel("OLT-ZX-C300");
				}
				
				facility.setDevType("19");
				facility.setFlag("1");
				facility.setSurveyResult("0");
				facility.setFiberDiscNum(20);
				ret = facilityMapper.insertSelective(facility);
				if(ret < 1){
					throw new RuntimeException("设施保存失败");
				}
				//创建熔纤盘分组
				fiberdiscGroup.setDevId(facility.getDevId());
				
				fiberdiscGroup.setDiscNum(20);
				fiberdiscGroup.setPortNum(16);
				if("HW".equals(vender)){
					fiberdiscGroup.setSide("OLTHW5680T");
					fiberdiscGroup.setGroupName("HWOLT01");
				}else if("ZX".equals(vender)){
					fiberdiscGroup.setSide("OLTZXC300");
					fiberdiscGroup.setGroupName("ZXOLT");
				}
				
				fiberdiscGroup.setCreateTime(new Date());
				ret = fiberdiscGroupMapper.insertSelective(fiberdiscGroup);
				
				if(ret < 1){
					throw new RuntimeException("熔纤盘保存失败");
				}
				//先提条件 ： 槽、熔纤盘从1开始  端子从0开始
				discinfoList = new ArrayList<Discinfo>();
				for(int j=0;j<20;j++){
					discinfo = new Discinfo();
					
					discinfo.setCreateTime(new Date());
					discinfo.setGroupId(fiberdiscGroup.getGroupId());
					discinfo.setSide(fiberdiscGroup.getSide());
					discinfo.setDevId(facility.getDevId());
					discinfo.setDiscCode(facility.getDevCode()+"-"+fiberdiscGroup.getSide()+"-"+String.format("%02d", j+1));
					discinfo.setDiscName((j+1)+"");
					discinfo.setPortNum(20);
					
					ret = discinfoMapper.insertSelective(discinfo);
					fibList = new ArrayList<Fiberdisc>();
					for(int k=0;k<16;k++){
						fiberdisc = new Fiberdisc();
						if(ret < 1){
							throw new RuntimeException("端子保存失败");
						}
						fiberdisc.setCreateTime(new Date());
						fiberdisc.setDevId(facility.getDevId());
						fiberdisc.setSide(fiberdiscGroup.getSide());
						fiberdisc.setDiscRowNo((long)(j+1));
						fiberdisc.setDiscCode(discinfo.getDiscCode());
						fiberdisc.setDiscColNo((long)(k+1));
						fiberdisc.setPort01(discinfo.getDiscCode()+"-"+String.format("%02d", k+1));
						fibList.add(fiberdisc);
						//ret = fiberdiscMapper.insertSelective(fiberdisc);
						
						fiberdisc = null;
					}
					discinfo.setFibList(fibList);
					discinfoList.add(discinfo);
					discinfo = null;
				}
				
			}
				
			//处理已占用ONU数
			if(d1!=null && !"".equals(d1)){
				//判断ONUID是否已经存在
				if((int)Integer.parseInt(d1)!=ONUId && devName.equals(a1) && (int)Integer.parseInt(b1)==fiberDiscId && (int)Integer.parseInt(c1)==portId){
					ONUName=ONUName+","+(Integer.parseInt(d1)+1)+"-"+e1;
				}else{
					ONUName=(Integer.parseInt(d1)+1)+"-"+e1;
				}
				if(!"".equals(ONUName)){
					for(int n=0;n<discinfoList.size();n++){
						if(discinfoList.get(n).getDiscName().equals(b1)){
							for(int p=0;p<discinfoList.get(n).getFibList().size();p++){
								if(discinfoList.get(n).getFibList().get(p).getDiscColNo()==(Integer.parseInt(c1)+1)){
									discinfoList.get(n).getFibList().get(p).setDetailsPort(ONUName);
								}
							}
						}
						
						
					}
					
				}	
				
					
			}else{
				continue;
			}
			
			devName = a1;
			fiberDiscId = Integer.parseInt(b1);
			portId = Integer.parseInt(c1);
			ONUId = Integer.parseInt(d1);
 
			//判断是否为list最后一项
			if(tempList.size()!=(i+1)){
				//判断下一个
				if(!a1.equals(tempList.get(i+1).getA1())){
					facility = null;
					fiberdiscGroup = null;
				}
			}else{
				for(Discinfo discinfo1 : discinfoList){
					for(Fiberdisc fiberdisc1 : discinfo1.getFibList()){
						if(ret < 1){
							throw new RuntimeException("端子保存失败");
						}
						ret = fiberdiscMapper.insertSelective(fiberdisc1);
					}
				}
			}
			a1 = null;
			b1 = null;
			c1 = null;
			d1 = null;
		}
		
		
		return 1;
	}

	@Override
	public int buildAlarmLog() {
		// TODO Auto-generated method stub
		List<DeviceAlarmBean> alarmList = Convert(deviceAlarmMapper.getAlarmList());
		String orgId = "";
		for(DeviceAlarmBean alarm : alarmList){
			orgId = deviceAlarmMapper.getOrgIdByDevId(alarm.getDevId());
			AlarmPushlog log= deviceAlarmMapper.getIsExitByLogTS(alarm.getAlarmProcessId());
			if(log == null){ 
				AlarmPushlog logs = new AlarmPushlog();
				logs.setAlarmProcessId(alarm.getAlarmProcessId());
				logs.setDevId(alarm.getDevId());
				logs.setPushTime(new Date());
				logs.setPushSign("1");
				logs.setOrgId(Long.parseLong(orgId));
				deviceAlarmMapper.AlarmPushlogAdd(logs);
			}else{
				log.setPushTime(new Date());
				deviceAlarmMapper.updateByPrimaryKeySelectives(log);
			}
		}
		
		return 1;
	}

	private List<DeviceAlarmBean> Convert(List<DeviceAlarm> deviceAlarmList)
	{
		List<DeviceAlarmBean> dabList=new ArrayList<DeviceAlarmBean>();
		for(DeviceAlarm da:deviceAlarmList)
		{
			DeviceAlarmBean dab=new DeviceAlarmBean();
			BeanUtils.copy(da, dab);
			if(Constants.DealSignMap.containsKey(dab.getDealSign()))
			{
				dab.setDealSignName(Constants.DealSignMap.get(dab.getDealSign()));
			}
			dabList.add(dab);
		}
		
		return dabList;
	}

	@Override
	public String changeFacility() {
		// TODO Auto-generated method stub
		return null;
	}


}
