package com.ycnet.frms.service.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Name;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.Constants;
import com.ycnet.core.Constants.FACILITTYPE;
import com.ycnet.core.DateState;
import com.ycnet.core.FacilityDevType;
import com.ycnet.core.FacilityType;
import com.ycnet.core.FrmsException;
import com.ycnet.core.util.BeanUtils;
import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.LatLng;
import com.ycnet.core.util.ListUtils;
import com.ycnet.core.util.Map_Util;
import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.ResultData;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableSectionDec;
import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityForGroups;
import com.ycnet.frms.bean.FacilityImg;
import com.ycnet.frms.bean.FacilityImgs;
import com.ycnet.frms.bean.FacilityZF;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.Imgs01;
import com.ycnet.frms.bean.InspectImg;
import com.ycnet.frms.bean.LineImage;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.OcCableSection;
import com.ycnet.frms.bean.OcJointDirect;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Project;
import com.ycnet.frms.bean.ResourceDataLog;
import com.ycnet.frms.bean.Route;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderDesign;
import com.ycnet.frms.bean.WorkorderFiberdiscabindA;
import com.ycnet.frms.bean.WorkorderFiberdiscabindZ;
import com.ycnet.frms.bean.WorkorderImplePlans;
import com.ycnet.frms.bean.WorkorderRoutes;
import com.ycnet.frms.mapper.AreasMapper;
import com.ycnet.frms.mapper.CableSectionDecMapper;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper;
import com.ycnet.frms.mapper.DeviceEntityMapper;
import com.ycnet.frms.mapper.DeviceRegMapper;
import com.ycnet.frms.mapper.DiscinfoMapper;
import com.ycnet.frms.mapper.FacilityImgMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscGroupMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.InspectImgMapper;
import com.ycnet.frms.mapper.LineImageMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.OcCableSectionMapper;
import com.ycnet.frms.mapper.OcJointDirectMapper;
import com.ycnet.frms.mapper.OrganizitionMapper;
import com.ycnet.frms.mapper.PipelineSectionMapper;
import com.ycnet.frms.mapper.PortImgMapper;
import com.ycnet.frms.mapper.PositionAreasMapper;
import com.ycnet.frms.mapper.RouteMapper;
import com.ycnet.frms.mapper.TwinfiberMapper;
import com.ycnet.frms.mapper.WellPilelineMapper;
import com.ycnet.frms.mapper.WorkorderDesignMapper;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper;
import com.ycnet.frms.mapper.WorkorderImplePlansMapper;
import com.ycnet.frms.mapper.WorkorderRoutesMapper;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.service.CableSectionDecService;
import com.ycnet.frms.service.CableSectionService;
import com.ycnet.frms.service.FacilityCoreService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.LinesService;
import com.ycnet.frms.service.ProjectService;
import com.ycnet.frms.service.ResourceDataLogService;
import com.ycnet.frms.service.SiteCodeService;
import com.ycnet.frms.util.ChineseCharToEn;
import com.ycnet.frms.util.StringUtil;
import com.ycnet.frms.util.UploadUtil;
import com.ycnet.frms.vo.CableSectionBean;
import com.ycnet.frms.vo.CableSectionWellBean;
import com.ycnet.frms.vo.ExportJumper;
import com.ycnet.frms.vo.FacilityAll;
import com.ycnet.frms.vo.FacilityAsasideCableBean;
import com.ycnet.frms.vo.FacilityBean;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityFromMac;
import com.ycnet.frms.vo.FacilityRoom;
import com.ycnet.frms.vo.FacilityVoBean;
import com.ycnet.frms.vo.FacilityVoBeanXY;
import com.ycnet.frms.vo.FiberdiscBean;
import com.ycnet.frms.vo.FiberdiscConditionBean;
import com.ycnet.frms.vo.FiberdiscInfo;
import com.ycnet.frms.vo.JumperInfo;
import com.ycnet.frms.vo.JumperInfos;
import com.ycnet.frms.vo.JumperTerraceInfo;
import com.ycnet.frms.vo.LinesBean;
import com.ycnet.frms.vo.LocateInfo;
import com.ycnet.frms.vo.LockKey;
import com.ycnet.frms.vo.WorkorderJumperInfo;
import com.ycnet.frms.vo.mobile.FacilityCabinetMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;
import com.ycnet.frms.vo.mobile.FacilityOdf;
import com.ycnet.frms.vo.mobile.FacilityVoOpd;
import com.ycnet.frms.vo.mobile.ocii.OciiFacility;
import com.ycnet.frms.vo.mobile.ocii.OciiFacilityBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("facilityService")
@Transactional
public class FacilityServiceImpl implements FacilityService  {

	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="cableSectionDecService")
	private CableSectionDecService cableSectionDecService;
	
	@Resource(name="fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;
	
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
	
	@Resource(name="resourceDataLogService")
	private ResourceDataLogService resourceDataLogService;

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
	
	@Resource(name="discinfoMapper")
	private DiscinfoMapper discinfoMapper;
	
	@Resource(name="fiberdiscGroupMapper")
	private FiberdiscGroupMapper fiberdiscGroupMapper;
	
	@Resource(name="linesMapper")
	private LinesMapper linesMapper;
	
	@Resource(name="twinfiberMapper")
	private TwinfiberMapper twinfiberMapper;
	
	@Resource(name="routeMapper")
	private RouteMapper routeMapper;
	
	@Resource(name="pipelineSectionMapper")
	private PipelineSectionMapper pipelineSectionMapper;

	@Resource(name="cableSectionService")
	private CableSectionService cableSectionService;
	@Resource(name="cableSectionDecMapper")
	private CableSectionDecMapper cableSectionDecMapper;
	
	@Resource(name="lineImageMapper")
	private LineImageMapper lineImageMapper;
	
	@Resource(name="portImgMapper")
	private PortImgMapper portImgMapper;
	
	@Resource(name="workorderRoutesMapper")
	private WorkorderRoutesMapper workorderRoutesMapper;
	
	@Resource(name="workorderImplePlansMapper")
	private WorkorderImplePlansMapper workorderImplePlansMapper;
	
	@Resource(name="workorderFiberdiscabindAMapper")
	private WorkorderFiberdiscabindAMapper workorderFiberdiscabindAMapper;
	
	@Resource(name="workorderFiberdiscabindZMapper")
	private WorkorderFiberdiscabindZMapper workorderFiberdiscabindZMapper;
	
	@Resource(name="deviceDiscinfoEntityMapper")
	private DeviceDiscinfoEntityMapper deviceDiscinfoEntityMapper;
	
	@Resource(name="workorderDesignMapper")
	private WorkorderDesignMapper workorderDesignMapper;
	
	@Resource(name="deviceEntityMapper")
	private DeviceEntityMapper deviceEntityMapper;
	
	@Resource(name="positionAreasMapper")
	private PositionAreasMapper positionAreasMapper;
	
	@Resource(name="areasMapper")
	private AreasMapper areasMapper;
	
	@Resource(name="ocJointDirectMapper")
	private OcJointDirectMapper ocJointDirectMapper;
	
	@Resource(name="ocCableSectionMapper")
	private OcCableSectionMapper ocCableSectionMapper;
	
	
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
	public Facility selectByCode1(Long devId,Long orgId) {
		Facility fc=new Facility();
		Facility facility = facilityMapper.selectByPrimaryKey(devId);
//		if(facility!=null){
//			
//		
//			if(facility.getPdevId()!=null){
//				fc=facilityMapper.selectByCodeXYId(facility.getPdevId());
//				return fc;
//			}else{
//				fc.setDevId(facility.getDevId());
//				fc.setDevCode(facility.getDevCode());
//				fc.setDevName(facility.getDevName());
//				fc.setLatitude(facility.getLatitude());
//				fc.setLongitude(facility.getLongitude());
//				fc.setAreaCode1(facility.getAreaCode1());
//				fc.setAreaCode2(facility.getAreaCode2());
//				fc.setBaiduX(facility.getBaiduX());
//				fc.setBaiduY(facility.getBaiduY());
//				fc.setCodeA(facility.getCodeA());
//				fc.setCodeZ(facility.getCodeZ());
//				fc.setCompleteDate(facility.getCompleteDate());
//				fc.setDevAddr(facility.getDevAddr());
//				fc.setDevDesc(facility.getDevDesc());
//				fc.setDevImei(facility.getDevImei());
//				fc.setDevMac(facility.getDevMac());
//				fc.setDevModel(facility.getDevModel());
//				fc.setDevType(facility.getDevType());
//				fc.setFiberDiscNum(facility.getFiberDiscNum());
//				fc.setFlag(facility.getFlag());
//				fc.setImgType(facility.getImgType());
//				fc.setIsTranslated(facility.getIsTranslated());
//				fc.setOrgId(facility.getOrgId());
//				fc.setRoom(facility.getRoom());
//				fc.setSite(facility.getSite());
//				fc.setSurveyResult(facility.getSurveyResult());
//				fc.setProId(facility.getProId());
//				fc.setPdevId(facility.getPdevId());
//			}
//		}
		return facility;
	}
	
	@Override
	public Long save(Facility facility,Users users) {
		ResourceDataLog rdl = new ResourceDataLog();
		int ret = 0;
		if((facility.getAreaCode1()==null||"".equals(facility.getAreaCode1()))&&facility.getPdevId()!=null){
			Facility parent = facilityMapper.selectByPrimaryKey(facility.getPdevId());
			if(parent!=null)
				facility.setAreaCode1(parent.getAreaCode1());
		}
		if(facility.getDevId()!=null)
		{
			Facility fa = facilityMapper.selectByPrimaryKey(facility.getDevId());//查询操作前内容,用于添加资管校准日志
			facility.setDevCode(null);//设置为null,修改时不会修改devCode
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
			//fl 添加百度x,y坐标转换
			if (facility!=null && facility.getLongitude()!=null && !"".equals(facility.getLongitude()) && facility.getLatitude()!=null && !"".equals(facility.getLatitude())) {
				LatLng latLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
				facility.setBaiduX(BigDecimal.valueOf(latLng.getLongitude()));
				facility.setBaiduY(BigDecimal.valueOf(latLng.getLatitude()));
			}
			//fl添加最后修改人.修改时间
			facility.setLastModifyUser(users.getUserId());
			facility.setLastModifyTime(new Date());
			ret =  facilityMapper.updateByPrimaryKeySelective(facility);
			if(ret>0) {
				Facility fxh = facilityMapper.selectByPrimaryKey(facility.getDevId());//查询操作后内容,因为devCode设置为null,所以需要重新查询
				rdl.setResLogType("01");//日志类型,01设施
				rdl.setHandleType("1");//操作类型,1修改
				rdl.setHandleId(fa.getDevId());//操作设施的ID
				rdl.setHisContent(fa.getDevName());//操作前的内容
				rdl.setNowContent(fxh.getDevName());//操作后的内容
				rdl.setModifyUserId(users.getUserId());//操作人ID
				rdl.setModifyUserName(users.getUserName());//操作人名称
				rdl.setModifyTime(new Date());//操作时间
				resourceDataLogService.saveResourceDataLog(rdl);//添加资管校准日志
			}
		}
		else
		{
			facility.setDevCode(siteCodeService.getNewDevCode());
			facility.setDevState("2");//2 新增,dzy修改
			//fl 添加百度x,y坐标转换
			if (facility!=null && facility.getLongitude()!=null && !"".equals(facility.getLongitude()) && facility.getLatitude()!=null && !"".equals(facility.getLatitude())) {
				LatLng latLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
				facility.setBaiduX(BigDecimal.valueOf(latLng.getLongitude()));
				facility.setBaiduY(BigDecimal.valueOf(latLng.getLatitude()));
			}
			//fl添加最后修改人.修改时间
			facility.setLastModifyUser(users.getUserId());
			facility.setLastModifyTime(new Date());
			ret = facilityMapper.insertSelective(facility);
			if(ret>0){
				rdl.setResLogType("01");//日志类型,01设施
				rdl.setHandleType("0");//操作类型,1新增
				rdl.setHandleId(facility.getDevId());//操作设施的ID
				rdl.setNowContent(facility.getDevName());//操作后的内容
				rdl.setModifyUserId(users.getUserId());//操作人ID
				rdl.setModifyUserName(users.getUserName());//操作人名称
				rdl.setModifyTime(new Date());//操作时间
				resourceDataLogService.saveResourceDataLog(rdl);//添加资管校准日志
			}
			//生成熔纤盘 新调整不需要在此自动生成熔纤盘
			//ret = genFiberdisc(facility.getDevId(), "A", facility.getFiberDiscNum());
			addConnecter(facility);
		}
		//锁定
		System.out.println("----------------------------------------"+facility.getCheckType());
		if(ret>0 && "1".equals(facility.getCheckType())) {
			ret = lockFacility(facility.getDevId(),facility.getCheckUserId(),"1");
		}
		//解锁
		if(ret>0 && "2".equals(facility.getCheckType())) {
			ret = lockFacility(facility.getDevId(),facility.getCheckUserId(),"2");
		}
		return facility.getDevId();
	}
	private int lockFacility(Long devId,Long userId,String checkType) {
		int ret = 0;
		List<Facility> facilityList = facilityMapper.selectByPdevId(devId);
		if(facilityList != null) {
			for(Facility f : facilityList) {
				f.setCheckType(checkType);
				f.setCheckUserId(userId);
				ret = facilityMapper.updateByPrimaryKeySelective(f);
				ret = lockFacility(f.getDevId(),userId,checkType);
			}
		}
		return ret;
	}

	public void addConnecter(Facility facility)
	{
		int  count = 0;
		if(!Constants.FACILITTYPE.GLT.toString().equals(facility.getDevType())){
			return;
		}
		try
		{
			if(facility.getDevModel()==null || "".equals(facility.getDevModel())) {
				throw new FrmsException("线芯容量不能为空");
			}
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
		agroup.setSide("JTA");
		agroup.setDiscNum(1);
		agroup.setPortNum(count);
		fcService.addGroup(agroup);
		bgroup.setDevId(devId);
		bgroup.setSide("JTB");
		bgroup.setDiscNum(1);
		bgroup.setPortNum(count);
		fcService.addGroup(bgroup);
		linesService.addVirtual(facility.getDevId(),Constants.FACILITTYPE.GLT);
	}
	
	@Override
	public int addObd(Facility facility) {
		int ret = 0;
		if(facility.getDevId()==null) {
			System.out.println("保存--------------------");
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
			facility.setDevState("2");//2 新增,dzy修改
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
		}else {
			System.out.println("修改--------------------");
			facilityMapper.updateByPrimaryKeySelective(facility);
		}
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
	public List<Facility> selectAround1(Double curLng, Double curLat,Double distance,String devType,String surveyResult, String areaCode1, Users user,Integer isDel) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("curLng",curLng);
		param.put("curLat", curLat);
		param.put("distance", distance);
		param.put("devType", devType);
		param.put("surveyResult", surveyResult);
		param.put("areaCode1", areaCode1);
		param.put("user", user);
		param.put("pdevIdIsNull", "1");
		param.put("isDel", isDel);
		return facilityMapper.selectAround(param);
	}
	@Override
	public FacilityBean convert(Facility facility,Users user) {
		if(facility==null)
			return null;
		FacilityBean bean = new FacilityBean();
		//百度XY ,小数点后九位 fl 添加,2018年3月29日
		if (facility.getBaiduX()!=null && facility.getBaiduY()!=null) {
//			int x = facility.getBaiduX().toString().indexOf(".");
//			int y = facility.getBaiduX().toString().indexOf(".");
//			String baiduX = facility.getBaiduX().toString().substring(0, x+10);
//			String baiduY = facility.getBaiduY().toString().substring(0, x+10);
			DecimalFormat format = new DecimalFormat("0.000000000");
			String baiduX = format.format(facility.getBaiduX());
			String baiduY = format.format(facility.getBaiduY());
			facility.setBaiduX(BigDecimal.valueOf(Double.valueOf(baiduX)));
			facility.setBaiduY(BigDecimal.valueOf(Double.valueOf(baiduY)));
			//百度转谷歌坐标
			LatLng guGeLatLng=Map_Util.bd09_To_Gcj02(Double.valueOf(String.valueOf(facility.getBaiduY())), Double.valueOf(String.valueOf(facility.getBaiduX())));
			String guGeX = format.format(guGeLatLng.getLongitude());
			String guGeY = format.format(guGeLatLng.getLatitude());
			bean.setGuGEY(BigDecimal.valueOf(Double.valueOf(guGeY)));
			bean.setGuGEX(BigDecimal.valueOf(Double.valueOf(guGeX)));
		}
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
		if(Constants.CheckTypeMap.containsKey(bean.getCheckType()))
		{
			bean.setCheckTypeName(Constants.CheckTypeMap.get(bean.getCheckType()));
		}
		if(user!=null && bean.getCheckUserId()!=null) {

			if("1".equals(bean.getCheckType()) && !user.getUserId().equals(bean.getCheckUserId())) {
				bean.setIsLocking("1");
			}
		}
		return bean;
	}

	@Override
	public List<FacilityBean> convert(List<Facility> list,Users user) {
		if(list==null||list.size()==0)
			return null;
		List<FacilityBean> rs = new ArrayList<FacilityBean>();
		for(Facility facility:list)
		{
			FacilityBean bean = convert(facility,user);
			rs.add(bean);
		}
		return rs;
	}

	@Override
	public int genFiberdisc(Long devId, String side, Integer fiberDiscNum,Integer portNum,Integer fromIndex)
	{
		return genFiberdisc( devId,  side,  fiberDiscNum, portNum, fromIndex,null);
	}

	@Override
	public int genFiberdisc(Long devId, String side, Integer fiberDiscNum,Integer portNum,Integer fromRowIndex,Integer fromColIndex){
		return genFiberdisc(devId,side,fiberDiscNum,portNum,fromRowIndex,fromColIndex,null);
	}
	
	@Override
	public int genFiberdiscZG(Long devId, String side, Integer fiberDiscNum,Integer portNum,Integer fromRowIndex,Integer fromColIndex){
		int ret = 0;
		String discModel = null;
		discModel=discModel==null||"".equals(discModel.replaceAll(" ",""))?null:discModel.replaceAll(" ","").toUpperCase();
		fromRowIndex = fromRowIndex==null?1:fromRowIndex;
		fromColIndex =fromColIndex==null?1:fromColIndex;
		if(fromColIndex.intValue()!=0&&fromColIndex.intValue()!=1)
		{
			throw new FrmsException("端口编码起始位置不正确！");
		}
		
		if(fiberDiscNum==null||(fiberDiscNum<=0))
			return ret;
		Facility f = facilityMapper.selectByPrimaryKeyZG(devId);
		if(f==null)
			return ret;
		String code =f.getDevCode();
		for(long row =fromRowIndex;row <=fiberDiscNum +(fromRowIndex -1);row ++)
		{
			for(long col = 1;col<=portNum;col++)
			{
				Fiberdisc disc = new Fiberdisc();
				disc.setDevId(devId);
				disc.setDiscRowNo(row);
				disc.setDiscColNo(col);
				disc.setSide(side);
				disc.setDiscCode(StringUtils.genPoint(code,side,discModel,row));
				disc.setPort01(StringUtils.genPoint(code,side,discModel,row,fromColIndex==1?col:(col - 1)));
				ret = fiberdiscMapper.insertSelective(disc);
				ret = fiberdiscMapper.insertSelectiveZG(disc);
			}
		}
		return ret;
	}
	
	@Override
	public int genFiberdisc(Long devId, String side, Integer fiberDiscNum,Integer portNum,Integer fromRowIndex,Integer fromColIndex,String  discModel){
		int ret = 0;
		discModel=discModel==null||"".equals(discModel.replaceAll(" ",""))?null:discModel.replaceAll(" ","").toUpperCase();
		fromRowIndex = fromRowIndex==null?1:fromRowIndex;
		fromColIndex =fromColIndex==null?1:fromColIndex;
		if(fromColIndex.intValue()!=0&&fromColIndex.intValue()!=1)
		{
			throw new FrmsException("端口编码起始位置不正确！");
		}
		
		if(fiberDiscNum==null||(fiberDiscNum<=0))
			return ret;
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f==null)
			return ret;
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
		for(long row =fromRowIndex;row <=fiberDiscNum +(fromRowIndex -1);row ++)
		{
			for(long col = 1;col<=portNum;col++)
			{
				Fiberdisc disc = new Fiberdisc();
				disc.setDevId(devId);
				disc.setDiscRowNo(row);
				disc.setDiscColNo(col);
				disc.setSide(side);
				disc.setDiscCode(StringUtils.genPoint(code,side,discModel,row));
				disc.setPort01(StringUtils.genPoint(code,side,discModel,row,fromColIndex==1?col:(col - 1)));
				disc.setCreateTime(new Date());
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
	 * 
	* @Title: locateDisc 
	* @Description: TODO(成端) 
	* 修改:创建成端时添加创建人和时间
	* @param @param info
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 下午4:14:39 
	* @version V1.0
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
		if(fromFiberNum>cs.getFiberNum() ) {
			throw new FrmsException("要成端纤芯数不能大于"+cs.getFiberNum()+"芯");
		}
		if(endFiberNum>cs.getFiberNum()) {
			throw new FrmsException("要成端纤芯数不能大于"+cs.getFiberNum()+"芯");
		}
		
		fromFiberNum = fromFiberNum==null?new Integer(1):fromFiberNum;
		endFiberNum   = endFiberNum ==null? new Integer(cs.getFiberNum().intValue()):endFiberNum;
		fromPort = fromPort==null?new Integer(1):fromPort;
		direction= direction==null||"".equals(direction.trim())?"1":direction;
		
		List<Fiberdisc> discList = fiberdiscMapper.queryByFiberNum(devId, sectId, fromFiberNum, endFiberNum);
		if(discList!=null&&discList.size()>0){
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
				line.setCreateTime(new Date());//添加创建人
				line.setCreateUser(info.getUserId());//添加时间
				
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
	/**
	 * 
	* @Title: unLocateDisc 
	* @Description: TODO(修改:解成端时添加最后修改人和时间) 
	* @param @param devId
	* @param @param sectId
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 下午4:07:06 
	* @version V1.0
	 */
	@Override
	public int unFiberLocate(Long devId, Long sectId,Integer aorz,Long userId) {
		if(devId==null||sectId==null)
			throw new FrmsException("不能获取对应的成端信息。");
//		int ret = linesService.unFiberlocate(devId, sectId,userId);
		int ret12=linesService.unbindAsaside(devId, sectId,aorz,userId);
		int ret1 =fiberdiscMapper.unlocate(devId, sectId,userId);
		return ret12+ret1;
	}

	@Override
	public int addJumper(Long adevId, String acode, Long zdevId, String zcode,
			String srvName,Long orgId) {
		return linesService.addJumper(adevId, acode, zdevId, zcode, srvName,orgId);
	}

	/**
	 * 
	* @Title: addJumper 
	* @Description: TODO(跳纤) 
	* 修改:跳纤时添加创建人和时间
	* @param @param session
	* @param @param jumperInfo
	* @param @return    入参
	* @return Object    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 下午4:16:57 
	* @version V1.0
	 */
	@Override
	public int saveJumper(JumperInfo jumperInfo,HttpServletRequest request,Users user) {
		int ret =0;
		String code;
//		String acode,zcode;
		String zcode;
		code = jumperInfo.getCode();
//		acode = jumperInfo.getAcode();
		zcode = jumperInfo.getZcode();
		if(zcode!=null && !"".equals(zcode)) {
			Fiberdisc fib = fiberdiscMapper.queryByPort(jumperInfo.getZcode());
			if(fib==null) {
				throw new FrmsException("目标跳纤点不存在");
			}else {
				jumperInfo.setZdevId(fib.getDevId());
			}
		}
		if(jumperInfo.getDevId()==null||code==null||"".equals(code.trim()))
		{
			throw new FrmsException("当前端子信息错误");
		}
//		if (acode!=null&&acode.equals(zcode))
//		{
//			throw new FrmsException("两条跳纤不能相同。");
//		}
		if (code!=null&&code.equals(zcode))
		{
			throw new FrmsException("两条跳纤不能相同。");
		}
		if(zcode!=null && !"".equals(zcode)) {//如果不是尾纤悬空
			ret +=updateForDecName(jumperInfo.getUserId(),code,zcode);
		}else {
			ret +=updateForDecName(jumperInfo.getUserId(),code,null);//是尾纤悬空或者跳到未知
		}
		ret +=linesService.addJumper(jumperInfo,request,user);
//		ret +=addJumper(jumperInfo.getAlinesId(),jumperInfo.getDevId(),code,jumperInfo.getAdevId(),acode,jumperInfo.getSrvName(),jumperInfo.getOrgId(),jumperInfo.getAunknownPointName(),jumperInfo.getUserId());
//		ret +=addJumper(jumperInfo.getZlinesId(),jumperInfo.getDevId(),code,jumperInfo.getZdevId(),zcode,jumperInfo.getSrvName(),jumperInfo.getOrgId(),jumperInfo.getZunknownPointName(),jumperInfo.getUserId());
//		if(ret>0) {
//			
//		}
		return ret;
	}
	
	/**
	 * 
	* @Title: updateForDecName 
	* @Description: 根据编码修改资管业务名称和端子占用状态 
	* @param @param userId
	* @param @param acode
	* @param @param zcode
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月3日 下午4:16:09 
	* @version V1.0
	 */
	@Override
	public int updateForDecName(Long userId,String acode,String zcode) {
		int reg=0;
		Fiberdisc fd=null;
		if(acode != null) {
			fd=new Fiberdisc();
			fd.setIsOccup("1");
			fd.setLastModifyTime(new Date());
			fd.setLastModifyUser(userId);
			fd.setPort01(acode);
			reg = fiberdiscMapper.updateByIsOccup(fd);
			fd=null;
		}
		
		if(zcode!=null) {
			fd=new Fiberdisc();
			fd.setIsOccup("1");
			fd.setLastModifyTime(new Date());
			fd.setLastModifyUser(userId);
			fd.setPort01(zcode);
			reg = fiberdiscMapper.updateByIsOccup(fd);
			fd=null;
		}
		
		
		Lines listAcode= linesMapper.queryByCode01(acode);
		if(listAcode!=null) {
			if(acode.equals(listAcode.getAcode())) {
				CableSectionDec caDec=cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(listAcode.getSectId(), listAcode.getFiberNo());
				if(caDec!=null) {
					caDec.setDevADec(listAcode.getSrvName());
					caDec.setModifyTime(new Date());
					caDec.setModifyUser(userId);
					reg = cableSectionDecMapper.updateByPrimaryKeySelective(caDec);
				}
			}else {
				CableSectionDec caDec=cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(listAcode.getSectId(), listAcode.getFiberNo());
				if(caDec!=null) {
					caDec.setDevZDec(listAcode.getSrvName());
					caDec.setModifyTime(new Date());
					caDec.setModifyUser(userId);
					reg = cableSectionDecMapper.updateByPrimaryKeySelective(caDec);
				}
			}
		}
		if(zcode!=null && !"".equals(zcode)) {
			Lines listZcode= linesMapper.queryByCode01(zcode);
			if(listZcode != null) {
				if(zcode.equals(listZcode.getAcode())) {
					CableSectionDec caDec=cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(listZcode.getSectId(), listZcode.getFiberNo());
					if(caDec!=null) {
						caDec.setDevADec(listZcode.getSrvName());
						caDec.setModifyTime(new Date());
						caDec.setModifyUser(userId);
						reg = cableSectionDecMapper.updateByPrimaryKeySelective(caDec);
					}
				}else {
					CableSectionDec caDec=cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(listZcode.getSectId(), listZcode.getFiberNo());
					if(caDec!=null) {
						caDec.setDevZDec(listZcode.getSrvName());
						caDec.setModifyTime(new Date());
						caDec.setModifyUser(userId);
						reg = cableSectionDecMapper.updateByPrimaryKeySelective(caDec);
					}
				}
			}
		}
		
		
		return reg;
	}
	
	@Override
	public int saveJumper(JumperInfos jumperInfos, HttpServletRequest request,Users user) {
		JumperInfo j1 = jumperInfos.getJ1();
		JumperInfo j2 = jumperInfos.getJ2();
		if(j1==null||j2==null){
			throw new FrmsException("参数错误！");
		}
		int ret = 0;
		ret +=saveJumper(j1,request,user);
		ret +=saveJumper(j2,request,user);
		return ret;
	}
	
	
//	public int  addJumper(Long linesId,Long curDevId,String curCode,Long otherDevId,String otherCode,String srvName,Long orgId,String unknownPointName,Long userId)
//	{
//		int ret = 0;
//		otherCode = (otherCode==null||"".equals(otherCode.trim()))?null:otherCode;
//		srvName = (srvName==null||"".equals(srvName.trim()))?null:srvName;
//		unknownPointName =(unknownPointName==null||"".equals(unknownPointName.trim()))?null:unknownPointName;
//		//之前已经存在连线
//		if(linesId !=null)
//		{
//			Lines line = linesService.selectById(linesId);
//			if (LineType.toType(line.getLineType()) == LineType.FIBER) //如果之前是纤芯成端，则什么都不操作
//				return ret;
//			//删除存在的线
//			
//			if(otherDevId==null&&otherCode==null&&unknownPointName==null)
//			{
//				int a = linesService.deleteById(linesId);
//				return a;
//				//return linesService.deleteById(line.getLineId());
//			}
//			else{
//				String acode = line.getAcode();
//				String zcode = line.getZcode();
//				line.setAcode(curCode.equals(acode)?curCode:otherCode);
//				line.setAdevId(curCode.equals(acode)?curDevId:otherDevId);
//				line.setZcode(curCode.equals(zcode)?curCode:otherCode);
//				line.setZdevId(curCode.equals(zcode)?curDevId:otherDevId);
//				line.setSrvName(srvName);
//				line.setUnknownPointName(unknownPointName);
//				line.setLastModifyTime(new Date());
//				line.setLastModifyUser(userId);
//				ret = linesService.updateAllColoumn(line);
//				if(otherDevId!=null&&otherCode!=null)
//				{
//					afterCheckPoint(otherDevId, otherCode);
//				}
//			}
//		}
//		else
//		{
//			
//			if((otherDevId!=null&&otherCode!=null)||unknownPointName!=null){
//				ret =linesService.addJumper(curDevId, curCode, otherDevId, otherCode, srvName, orgId, unknownPointName,userId);
//				afterCheckPoint(curDevId,curCode);
//				if(otherDevId!=null&&otherCode!=null)
//				{
//					afterCheckPoint(otherDevId,otherCode);
//				}
//			}
////			//另一端不为空才继续添加
////			if(otherDevId!=null&&otherCode!=null&&!"".endsWith(otherCode))
////			{
////				beforeCheckPoint(otherDevId,otherCode);
////				beforeCheckPoint(curDevId,curCode);
////				ret =addJumper(curDevId, curCode, otherDevId, otherCode, srvName,orgId);
////			}
//		}
//		return ret;
//	}
	
	
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
		String a=bean.getAreaCode();
		String b=bean.getAreaCode1();
//		pb.setRows(facilityMapper.queryCountByConditionMap(conditionMap));//;
//		pb.setList(this.convert(facilityMapper.queryByConditionMap(conditionMap),user));		
		pb.setRows(facilityMapper.queryCountByConditionsMap(conditionMap));//;
		pb.setList(this.convert(facilityMapper.queryByConditionsMap(conditionMap),user));		
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
				
				pb.setList(this.convert(fNewList,null));
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
		return this.convert(fNewList,null);
	}

	@Override
	public FacilityBean selectDetailById(Long devId) {
		return this.convert(facilityMapper.selectByPrimaryKey(devId),null);
	}

	public List<Facility> queryListByAccessIds(String ids) {
		return facilityMapper.queryListByAccessIds(ids.split(","));

	}
	public Integer queryCountByConditionBean(FacilityConditionBean bean) {
		return facilityMapper.queryCountByConditionBean(bean);

	}
/**
 * 导出'设施导入模版'Excel文件(.xls)
 * 修改:刘沧海  导入设施汇聚区BUG
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
			CellRangeAddressList regions = new CellRangeAddressList(1,1000,2,2);  
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
//			CellRangeAddressList regions2 = new CellRangeAddressList(1,1000,8,8);  
	         // DropDownList content  
	        HSSFSheet hidden = wb.createSheet("hidden");
	        Users user=(Users) request.getSession().getAttribute("platformUser");	
//	        List<Areas> areaList = areasService.selectByOrgId(user.getOrgId());
	        List<Areas> areaList = areasService.selectByOrgIdJQ(user.getOrgId());//精准查询子类汇聚区
//	        String[] areaArray = new String[areaList.size()];
	        String str=null;
	        for(int i=0;i<areaList.size();i++){
	        	str=areaList.get(i).getAreaName().trim()+"="+areaList.get(i).getAreaCode().trim();//F .substring(indexOf("areaCode1=")+10).trim()
	        	HSSFRow row = hidden.createRow(1000+i); 
	        	HSSFCell cell = row.createCell(0); 
	        	cell.setCellValue(str); 
	        }
	        Name namedCell = wb.createName(); 
	        namedCell.setNameName("hidden"); 
	        namedCell.setRefersToFormula("hidden!A1:A" + (areaList.size()+1000));//将下拉数据添加到新sheet 
	        DVConstraint constraint2 = DVConstraint.createFormulaListConstraint("hidden");  
	        CellRangeAddressList addressList = new CellRangeAddressList(1, 1000,0, 0); 
	        // bind DropDownList region and constraint to dataValidation  
	        HSSFDataValidation data_validation2 = new HSSFDataValidation(addressList,constraint2);  
	        // addValidation to sheet  
	        wb.setSheetHidden(1, true); //隐藏新sheet
	        sheet1.addValidationData(data_validation2);  
	        
	        HSSFRow headRow = sheet1.createRow(0);
	        headRow.createCell(0).setCellValue("所属片区  ");
	        headRow.createCell(1).setCellValue("设施名称（必填）");
	        headRow.createCell(2).setCellValue("设施类型");
	        headRow.createCell(3).setCellValue("设施型号");
	        headRow.createCell(4).setCellValue("竣工日期");
	        headRow.createCell(5).setCellValue("详情地址");
	        headRow.createCell(6).setCellValue("经度");
	        headRow.createCell(7).setCellValue("纬度");
	        headRow.createCell(8).setCellValue("附近特征描述");
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
//	                if(null == row || null == row.getCell(0) || "".equals(row.getCell(0).getStringCellValue().trim()) ){
//	                	continue;
//	                }
	                // 1.设置设施名称
	                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
	                String devName = row.getCell(1).getStringCellValue();
	                FacilityConditionBean fcb = new FacilityConditionBean();
	                fcb.setDevName(devName.trim());
//	                List<Facility> checkList = this.queryByConditionBean(fcb);
	                List<Facility> checkList = this.queryByConditionBeanXiugai(fcb);
	                if(checkList.size()>0){
	                	map.put("result", "第"+i+"行,设施名称已经存在. ");
	                	return map;
	                }
	                facility.setDevName(devName);

	                // 2.设置设施类型
	                if(row.getCell(1)!=null){
		                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
		                String devTypeStr = row.getCell(2).getStringCellValue();
		                if(devTypeStr.length()>1){
		                	String devType = devTypeStr.substring(0,2);
		                	facility.setDevType(devType);
		                }else {
		                	facility.setDevType("01");
		                }
	                }
	                //3.设施型号
	                if(row.getCell(3)!=null && "".equals(row.getCell(3).getStringCellValue())){
			                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
			                String devModel = row.getCell(2).getStringCellValue();
			                facility.setDevModel(devModel);
	                }
	                //4.竣工日期
//	                if(row.getCell(4)!=null || "".equals(row.getCell(4).getStringCellValue())){
////		                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
//		                String completeDate = row.getCell(4).getStringCellValue();
//		                SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
//		                
//	                	facility.setCompleteDate(sdf.parse(completeDate));
//	               
////		                	map.put("result", "第"+i+"行,竣工日期数据错误,请检查日期格式是否为 yyyy-MM-dd "+e.toString());
////		    	            return map;
//	                	facility.setCompleteDate(null);
//		                
//	                }else {
//	                	facility.setCompleteDate(null);
//	                }
	                //5.详情地址
	                if(row.getCell(5)!=null){
		                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
		                String devAddr = row.getCell(5).getStringCellValue();
		                facility.setDevAddr(devAddr);
	                }else {
	                	facility.setDevAddr(null);
	                }
	                //6.经度
	                if(row.getCell(6)!=null){
		                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
		                String longitude = row.getCell(6).getStringCellValue();
		                facility.setLongitude(longitude);
	                }else {
	                	facility.setLongitude(null);
	                }
	                //7.纬度
	                if(row.getCell(7)!=null){
		                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
		                String latitude = row.getCell(7).getStringCellValue();
		                facility.setLatitude(latitude);
	                }else {
	                	facility.setLatitude(null);
	                }
	                //8.附近特征描述
	                if(row.getCell(8)!=null){
		                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
		                String devDesc = row.getCell(8).getStringCellValue();
		                facility.setDevDesc(devDesc);
	                }else {
	                	facility.setDevDesc(null);
	                }
	                //9.所属片区
	                if(row.getCell(0)!=null){
		                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
		                String areaCode1Str = row.getCell(0).getStringCellValue();
		                String[] strs=areaCode1Str.split("=");
		                if(strs.length>0) {
		                	facility.setAreaCode1(strs[1]);
		                }
	                }else {
	                	facility.setAreaCode1(null);
	                }
	                //10.所属机房
	                if(row.getCell(9)!=null){
		                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
		                String room = row.getCell(9).getStringCellValue();
		                facility.setRoom(room);
	                }else {
	                	facility.setRoom(null);
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
	                }else {
	                	facility.setProId(null);
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
	/**
	 * 
	* @Title: queryByConditionBeanXiugai 
	* @Description: TODO(用于导入设施检测名字是否重复) 
	* @param @param fcb
	* @param @return    入参
	* @return List<Facility>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年10月25日 下午3:41:10 
	* @version V1.0
	 */
	private List<Facility> queryByConditionBeanXiugai(FacilityConditionBean bean) {
		if(bean ==null) 
			bean = new FacilityConditionBean();
		return facilityMapper.queryByConditionBeanXiugai(bean);
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
                	facility.setCreateTime(new Date());
                	facility.setCreateUser(user.getUserId());
                	facility.setOrgId(user.getOrgId());
                	FacilityConditionBean bean = new FacilityConditionBean();
                	bean.setDevName(facility.getDevName());
                	bean.setOrgId(facility.getOrgId());
                	temp = this.queryByConditionBean(bean);
                    if(null == temp || temp.size()==0){
                    	String temp2 = facility.getDevName();
                    	if(null != temp2 && temp2 !=""){
                    		facility.setDevState("2");//2 新增,dzy修改
                    		int i = facilityMapper.insertSelective(facility);
                    		if(i==1){
                    			insertCount+=1;
                    		}else {
                    			return "在第"+insertCount+"行导入失败";
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
	public List<FacilityBean> queryListByContent(String content,Long orgId) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("content",content);
		map.put("orgId", orgId);
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
		if((facility.getAreaCode1()==null||"".equals(facility.getAreaCode1()))&&facility.getPdevId()!=null){
			
			Facility parent = facilityMapper.selectByPrimaryKey(facility.getPdevId());
			if(parent!=null)
				facility.setAreaCode1(parent.getAreaCode1());
		}
		if(facility.getDevId()==null){
			Facility faDevNames=facilityMapper.queryRepetitionDevName(facility);
			if(faDevNames !=null ) {
				throw new FrmsException("设施名称【"+facility.getDevName()+"】已存在！");
			}
			
			if(Constants.FACILITTYPE.OBD.toString().equals(facility.getDevType()))
			{
				//fl 修改增加百度 X Y 坐标
				if (facility.getLatitude() !=null && !"".equals(facility.getLatitude()) && facility.getLongitude() !=null && !"".equals(facility.getLongitude())) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
				addObd(facility);
			}
			else
			{
				facility.setDevCode(siteCodeService.getNewDevCode());
				facility.setDevState("2");//2 新增,dzy修改
				//fl 修改增加百度 X Y 坐标
				if (facility.getLatitude() !=null && !"".equals(facility.getLatitude()) && facility.getLongitude() !=null && !"".equals(facility.getLongitude())) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
				
				facilityMapper.insertSelective(facility);
			}
			ret =facility.getDevId();
			for(FacilityImg img:images){
				img.setDevCode(facility.getDevCode());
				img.setDevId(facility.getDevId());
				facilityImgMapper.insertSelective(img);
			}
			addConnecter(facility);
		}
		else{
			//fl 修改增加百度 X Y 坐标
			if (facility.getLatitude() !=null && !"".equals(facility.getLatitude()) && facility.getLongitude() !=null && !"".equals(facility.getLongitude())) {
				LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
				facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
				facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
			}
			
			//验证设施名并修改光缆段名称
			validateDevName(facility);
			
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
	 * 
	 * @Title: validateDevName
	 * @Description: 验证修改设施名并修改光缆段名称
	 * @param @param facility 
	 * @return void 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月25日 上午11:06:58
	 * @version V1.0
	 */
	private void validateDevName(Facility facility) {
		if(facility==null || facility.getDevId() ==null ){
			return;
		}
		//fl 添加  2018年3月28日
		//---------
		if ("".equals(facility.getDevName())) {
			facility.setDevName(null);
		}
		//----------
		if(facility.getDevName()!=null &&!"".equals(facility.getDevName())) {
			Facility fa = facilityMapper.selectByPrimaryKey(facility.getDevId());
			if(fa!=null && !facility.getDevName().equals(fa.getDevName())) {
				if(facility.getDevType() != null && "05".equals(facility.getDevType())) {
					Facility fpn = facilityMapper.queryByPdevIdAndDevName(facility.getPdevId(),facility.getDevName());
					if(fpn != null) {
						throw new FrmsException("该名称在本机房已存在！");
					}
				}else {
					Facility faDevNames=facilityMapper.queryIamRepetitionDevName(facility);
					if(faDevNames !=null) {
						throw new FrmsException("设施名称【"+facility.getDevName()+"】已存在！");
					}
				}
				List<CableSection> cableList = cableSectionService.queryCaByDevId(facility.getOrgId(), facility.getDevId());
				for (CableSection cableSection : cableList) {
					cableSection.setSecName(cableSection.getSecName().replace(fa.getDevName(), facility.getDevName()));
					cableSection.setLastModifyTime(new Date());
					cableSection.setLastModifyUser(facility.getLastModifyUser());
					cableSectionMapper.updateByPrimaryKeySelective(cableSection);
				}
			}
		}
	}
	
	/**
	 * 同时保存设施基本信息，和照片信息
	 */
	@Override
	public Long edit(FacilityVoBeanXY fvb, List<FacilityImg> images) {
		Facility facility =  new Facility();
		facility.setLastModifyUser(fvb.getLastModifyUser());
		facility.setLastModifyTime(new Date());
		facility.setDevAddr(fvb.getDevAddr());
		facility.setAreaCode1(fvb.getAreaCode1());
		facility.setAreaCode2(fvb.getAreaCode2());
		facility.setCodeA(fvb.getCodeA());
		facility.setDevStateA(fvb.getDevStateA());
		facility.setCodeZ(fvb.getCodeZ());
		facility.setDevStateZ(fvb.getDevStateZ());
		facility.setCheckType(fvb.getCheckType());
		facility.setCheckUserId(fvb.getCheckUserId());
		facility.setCompleteDate(fvb.getCompleteDate());
		facility.setCreateTime(fvb.getCreateTime());
		facility.setCreateUser(fvb.getCreateUser());
		facility.setDevDesc(fvb.getDevDesc());
		facility.setDevId(fvb.getDevId());
		facility.setDevImei(fvb.getDevImei());
		facility.setDevKind(fvb.getDevKind());
		facility.setDevMac(fvb.getDevMac());
		facility.setDevMarking(fvb.getDevMarking());
		facility.setDevModel(fvb.getDevModel());
		facility.setDevName(fvb.getDevName());
		facility.setDevType(fvb.getDevType());
		facility.setFiberDiscNum(fvb.getFiberDiscNum());
		facility.setFlag(fvb.getFlag());
		facility.setIsbefwell(fvb.getIsbefwell());
		facility.setIsTranslated(fvb.getIsTranslated());
		facility.setZgDevName(fvb.getZgDevName());
		facility.setProId(fvb.getProId());
		facility.setPdevId(fvb.getPdevId());
		facility.setOrgId(fvb.getOrgId());
		facility.setImgType(fvb.getImgType());
		facility.setSite(fvb.getSite());
		facility.setSurveyResult(fvb.getSurveyResult());
		facility.setRoom(fvb.getRoom());
		facility.setDevCode(fvb.getDevCode());
		facility.setDevState(fvb.getDevState());
		facility.setLatitude(fvb.getLatitude());
		facility.setLongitude(fvb.getLongitude());
		if(!"".equals(fvb.getBaiduX()) && fvb.getBaiduX()!=null) {
			facility.setBaiduX(BigDecimal.valueOf(Double.parseDouble(fvb.getBaiduX())));
		}
		if(!"".equals(fvb.getBaiduY()) && fvb.getBaiduY()!=null) {
			facility.setBaiduY(BigDecimal.valueOf(Double.parseDouble(fvb.getBaiduY())));
		}
		
		
		Long ret = 0L;
		int reg=0;
		if(facility.getDevId()==null){
			if(Constants.FACILITTYPE.OBD.toString().equals(facility.getDevType()))
			{
				//fl 淇敼澧炲姞鐧惧害 X Y 鍧愭爣
				if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
				addObd(facility);
			}
			else
			{
				facility.setDevCode(siteCodeService.getNewDevCode());
				facility.setDevState("2");//2 鏂板,dzy淇敼
				//fl 淇敼澧炲姞鐧惧害 X Y 鍧愭爣
				if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
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
//			if(facility.getDevName()!=null &&!"".equals(facility.getDevName())) {//楠岃瘉鍚嶇О鏄惁閲嶅
//				Facility fa = facilityMapper.selectByPrimaryKey(facility.getDevId());
//				if(!facility.getDevName().equals(fa.getDevName())) {
//					facility.setOrgId(fa.getOrgId());
//					Facility faDevNames=facilityMapper.queryRepetitionDevName(facility);
//					if(faDevNames !=null) {
//						throw new FrmsException("璁炬柦鍚嶇О銆�"+facility.getDevName()+"銆戝凡瀛樺湪锛�");
//					}
//				}
//			}
			
			if(Constants.FACILITTYPE.GLT.toString().equals(facility.getDevType())) {
				Facility fa=facilityMapper.selectByPrimaryKey(facility.getDevId());
				if(!facility.getDevModel().equals(fa.getDevModel())) {
					String devCode = fa.getDevCode()+"-"+"JT";
					if("".equals(facility.getDevModel())) {
						throw new FrmsException("鎺ュご鍨嬪彿涓嶈兘涓虹┖锛�");
					}
					List<Lines> list01=linesMapper.queryDevCodeByCode(devCode);
					if(list01 !=null && list01.size()>0) {
						throw new FrmsException("姝ゆ帴澶村凡鎴愮锛屼笉鑳戒慨鏀规搷浣�");//濡傛灉鏈夋垚绔腑鏂�
					}
					if("0".equals(facility.getDevModel())) {
						reg=linesMapper.deleteLinesByLineTypeAndCode(devCode);//鍒犻櫎璺崇氦
						reg=fiberdiscMapper.deleteByDevCodeMohu(devCode);//鍒犻櫎绔瓙
						reg=discinfoMapper.deleteByDevCodeMohu(devCode);//鍒犻櫎鐩�
						reg=fiberdiscGroupMapper.deleteByDevId(facility.getDevId());//鍒犻櫎鍒嗙粍
					}else {
						reg=linesMapper.deleteLinesByLineTypeAndCode(devCode);//鍒犻櫎璺崇氦
						reg=fiberdiscMapper.deleteByDevCodeMohu(devCode);//鍒犻櫎绔瓙
						reg=discinfoMapper.deleteByDevCodeMohu(devCode);//鍒犻櫎鐩�
						reg=fiberdiscGroupMapper.deleteByDevId(facility.getDevId());//鍒犻櫎鍒嗙粍
						//fl 淇敼澧炲姞鐧惧害 X Y 鍧愭爣
						if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
							LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
							facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
							facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
						}
						addConnecter(facility);
					}
				}
			}
			//fl 淇敼澧炲姞鐧惧害 X Y 鍧愭爣
			if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
				LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
				facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
				facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
			}
			//楠岃瘉璁炬柦鍚嶅苟淇敼鍏夌紗娈靛悕绉�
			validateDevName(facility);
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
		pb.setList(this.convert(facilityMapper.selectFacilityByPosition(conditionMap),null));		
		return pb;
	}
	
	public List<Facility> findFacilities(Long orgId) {
		return facilityMapper.findFacilities(orgId);
	}
	
	public String changeFacility(Long orgId) {
		int total=facilityMapper.countFacilities(orgId);//没有转化过的点位 并且经纬度不是null的
		int pageSize=50;
		Map<String,Object> map=new HashMap<String,Object>();
		int totalPages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		for (int i = 1; i <= totalPages; i++) {
			map.put("pageSize", pageSize);  
			map.put("orgId", orgId);
			List<Facility> list = facilityMapper.findNoChangeFacilityList(map);
			boolean isSuccess=excHttpTranslate(list);
			if (!isSuccess) {
				break;
			}
		}
		//  执行完之后，  查询一下， 剩余未转化数量
		int surplus= facilityMapper.countFacilities(orgId);
		if (surplus==0) {
			return "true";
		}else {
			return total == 0 ? "true" :String.valueOf(surplus);
		}
		
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
		
		return facilityImgs;
	}


	@Override
	public Long saveWell(FacilityImgs facilityImgs,HttpSession session,HttpServletRequest request) {
		// TODO Auto-generated method stub
		int ret = 0;
		Facility fc = new Facility();
		Users users=(Users)session.getAttribute("users");
		if(facilityImgs != null){
			if(facilityImgs.getAreaCode1()==null){
				Organizition o = organizitionMapper.selectByPrimaryKey(users.getOrgId());
				if(o!=null)
					fc.setAreaCode1(o.getCode1());
			}
			fc.setOrgId(users.getOrgId());
			if(facilityImgs.getDevId()==null)
			{
				fc.setDevCode(siteCodeService.getNewDevCode());
				fc.setCreateUser(users.getUserId());
				fc.setCreateTime(new Date());
			}else
			{
				fc.setDevId(facilityImgs.getDevId());
				fc.setDevCode(facilityImgs.getDevCode());
				fc.setLastModifyUser(users.getUserId());
				fc.setLastModifyTime(new Date());
			}
			fc.setDevName(facilityImgs.getDevName());
			fc.setLatitude(facilityImgs.getLatitude());
			fc.setLongitude(facilityImgs.getLongitude());
			fc.setAreaCode1(facilityImgs.getAreaCode1());
			fc.setAreaCode2(facilityImgs.getAreaCode2());
			fc.setBaiduX(facilityImgs.getBaiduX());
			fc.setBaiduY(facilityImgs.getBaiduY());
			fc.setCodeA(facilityImgs.getCodeA());
			fc.setCodeZ(facilityImgs.getCodeZ());
			fc.setCompleteDate(facilityImgs.getCompleteDate());
			fc.setDevAddr(facilityImgs.getDevAddr());
			fc.setDevDesc(facilityImgs.getDevDesc());
			fc.setDevImei(facilityImgs.getDevImei());
			fc.setDevMac(facilityImgs.getDevMac());
			fc.setDevModel(facilityImgs.getDevModel());
			fc.setDevType(facilityImgs.getDevType());
			fc.setFiberDiscNum(facilityImgs.getFiberDiscNum());
			fc.setFlag(facilityImgs.getFlag());
			fc.setImgType(facilityImgs.getImgType());
			fc.setIsTranslated(facilityImgs.getIsTranslated());
			fc.setRoom(facilityImgs.getRoom());
			fc.setSite(facilityImgs.getSite());
			fc.setSurveyResult(facilityImgs.getSurveyResult());
			fc.setProId(facilityImgs.getProId());
			fc.setIsbefwell(facilityImgs.getIsbefwell());
			fc.setDevKind(facilityImgs.getDevKind());
			fc.setDevState(facilityImgs.getDevState());//3 修改,dzy修改
		}
		FacilityImg imgs = null;
		if(facilityImgs.getDevId()!=null)
		{
			
//			List<Space> spaceList = spaceMapper.selectListByPrimaryKey(facilityImgs.getDevId());
//			for(Space space : spaceList){
//				List<Piping> pipingList = pipingMapper.selectListByPrimaryKey(space.getSpaceId());
//				if(pipingList!=null && pipingList.size()>0) {
//					for(Piping piping : pipingList){
//						sectDevMapper.deleteByPipingId(piping.getPipingId());
//						valveMapper.deleteByDevId(piping.getPipingId());
//					}
//				}
//				pipingMapper.deleteByDevId(space.getSpaceId());
//			}
//			ret = spaceMapper.deleteByDevId(facilityImgs.getDevId());	
			//ret = wellPilelineMapper.deleteByDevId(facilityImgs.getDevId());
			//ret = facilityMapper.deleteByPrimaryKey(facilityImgs.getDevId());
			List<FacilityImg> fiList = facilityImgMapper.selectByDevId(facilityImgs.getDevId());
			if(fiList!=null && fiList.size()>0) {
				for(FacilityImg facilityImg : fiList){
//					if(facilityImgs.getImgs01() != null){
//						boolean flag = true;
//						for(Imgs01 imgs01 : facilityImgs.getImgs01()){
//							System.out.println(facilityImg.getImgUrl()+"===="+imgs01.getImgPath01());
//							if(facilityImg.getImgUrl().equals(imgs01.getImgPath01())){
//								flag = false ;
//							}
//						}
//						System.out.println(facilityImg.getImgUrl()+"--------"+flag);
//						if(facilityImg.getImgUrl()!=null && flag){
						if(facilityImg.getImgUrl()!=null){
							System.out.println(request.getSession().getServletContext()
									.getRealPath("/")+facilityImg.getImgUrl());
							File f = new File(request.getSession().getServletContext()
									.getRealPath("/")+facilityImg.getImgUrl());
							try {
								DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
								if (f.exists()) {
									dos.close();
								    System.out.println(f.getAbsoluteFile());
								    if (!f.delete()) {
								    	System.out.println("请关闭使用该文件的所有进程或者流！！");
								    } else {
								    	System.out.println(f.getName()+" 成功被删除！");
								    }
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
				}
			}

			
			ret = facilityImgMapper.deleteByDevId(facilityImgs.getDevId());
			
			ret = facilityMapper.updateByPrimaryKeySelective(fc);
			if(facilityImgs.getImgs01()!=null && facilityImgs.getImgs01().size()>0){
				for(Imgs01 img:facilityImgs.getImgs01()){
					imgs = new FacilityImg();
					imgs.setImgUrl(img.getImgPath01());
					imgs.setDevCode(fc.getDevCode());
					imgs.setDevId(fc.getDevId());
					imgs.setImgTypes("03");  //代表设施基本图片信息
					imgs.setPhotoTime(new Date());
					ret = facilityImgMapper.insertSelective(imgs);
					imgs = null;
				}
			}
		}else{
			fc.setDevState("2");//2 新增,dzy修改
			ret = facilityMapper.insertSelective(fc);
			if(facilityImgs.getImgs01()!=null && facilityImgs.getImgs01().size()>0){
				for(Imgs01 img:facilityImgs.getImgs01()){
					imgs = new FacilityImg();
					imgs.setImgUrl(img.getImgPath01());
					imgs.setDevCode(fc.getDevCode());
					imgs.setDevId(fc.getDevId());
					imgs.setImgTypes("03");  //代表设施基本图片信息
					imgs.setPhotoTime(new Date());
					ret = facilityImgMapper.insertSelective(imgs);
					imgs = null;
				}
			}
		}
	
//		if(ret > 0){
//			if(fc.getDevId() != null){
//				//井与井关联表
//				WellPileline wellPileline = new WellPileline();
//				if(facilityImgs.getFrontWellList()!=null && facilityImgs.getFrontWellList().size()>0){
//					for(Facility facility : facilityImgs.getFrontWellList()){
//
//						wellPileline.setFdevId(facility.getDevId());
//						wellPileline.setDevId(fc.getDevId());
//						ret = wellPilelineMapper.insertSelective(wellPileline);
//						if(ret<1){
//							throw new RuntimeException("保存失败");
//						}
//					}
//				}
//			}
			//井侧面
//			List<Space> spacelist = facilityImgs.getSpacelist();
//			if(spacelist!=null && spacelist.size()>0){
//				for(Space space : spacelist){
//					if(ret < 1){
//						throw new RuntimeException("保存失败");
//					}
//					space.setDevId(fc.getDevId());
//					//井侧面管道
//					List<Piping> pipinglist = space.getPipinglist();
//					if(pipinglist!=null && pipinglist.size()>0) {
//						space.setPipingCount(Long.parseLong(pipinglist.size()+""));
//					}else {
//						space.setPipingCount(0L);
//					}
//					ret = spaceMapper.insertSelective(space);
//					
//					//处理井面对应管道段信息
//					List<PipelineSection> pipelineSectionList = space.getPipelineSection();
//					if(pipelineSectionList!=null && pipelineSectionList.size()>0) {
//						for(PipelineSection pipelineSection : pipelineSectionList) {
//							PipelineSection p = pipelineSectionMapper.selectBy2DevId(pipelineSection.getFdevId(),fc.getDevId(),users.getOrgId());
//							Facility f = null;
//							if(p != null) {
//								if(p.getFdevId()!=null) {
//									f = facilityMapper.selectByPrimaryKey(p.getFdevId());
//								}
//								
//								pipelineSection.setPipesId(p.getPipesId());
//								if(fc.getDevId()==p.getFdevId()) {
//									pipelineSection.setFdevId(fc.getDevId());
//									pipelineSection.setFspaceId(space.getSpaceId());
//									pipelineSection.setBdevId(p.getFdevId());
//									pipelineSection.setBspaceId(p.getFspaceId());
//								}else {
//									pipelineSection.setBdevId(fc.getDevId());
//									pipelineSection.setBspaceId(space.getSpaceId());
//								}
//								pipelineSection.setMapLen(MapDistance.getDistance(Double.parseDouble(f.getLatitude()), Double.parseDouble(f.getLongitude()), Double.parseDouble(fc.getLatitude()), Double.parseDouble(fc.getLongitude()))+"m");
//								pipelineSection.setModifyTime(new Date());
//								pipelineSection.setModifyUser(users.getUserId());
//								pipelineSectionMapper.updateByPrimaryKeySelective(pipelineSection);
//							}else {
//								
//								if(pipelineSection.getFdevId()!=null) {
//									f = facilityMapper.selectByPrimaryKey(pipelineSection.getFdevId());
//								}
//								pipelineSection.setMapLen(MapDistance.getDistance(Double.parseDouble(f.getLatitude()), Double.parseDouble(f.getLongitude()), Double.parseDouble(fc.getLatitude()), Double.parseDouble(fc.getLongitude()))+"m");
//								pipelineSection.setOrgId(users.getOrgId());
//								pipelineSection.setBdevId(fc.getDevId());
//								pipelineSection.setBspaceId(space.getSpaceId());
//								pipelineSection.setCreateTime(new Date());
//								pipelineSection.setCreateUser(users.getUserId());
//								
//								pipelineSectionMapper.insertSelective(pipelineSection);
//							}
//						}
//					}
//					
//					//处理井侧面数据
//					if(pipinglist!=null && pipinglist.size()>0){
//						for(Piping piping : pipinglist){
//							if(ret < 1){
//								throw new RuntimeException("保存失败");
//							}
//							piping.setSpaceId(space.getSpaceId());
//							//井侧面管道的子管道
//							List<Valve> valvelist = piping.getValvelist();
//							if(valvelist!=null && valvelist.size()>0){
//								piping.setValveCount(Long.parseLong(valvelist.size()+""));
//							}else {
//								piping.setValveCount(0L);
//							}
//							ret = pipingMapper.insertSelective(piping);
//							//大孔绑定光缆
//							if(piping.getSectIds() != null) {
//								String[] sectIds = piping.getSectIds().split(",");
//								SectDev sectDev = null;
//								for(String sectId : sectIds) {
//									sectDev = new SectDev();
//									sectDev.setPipingId(piping.getPipingId());
//									sectDev.setSectId(Long.parseLong(sectId));
//									ret = sectDevMapper.insertSelective(sectDev);
//									if(ret < 1){
//										throw new RuntimeException("保存失败");
//									}
//									sectDev = null;
//								}
//							}
//							
//							if(valvelist!=null && valvelist.size()>0){
//								for(Valve valve : valvelist){
//									if(ret < 1){
//										throw new RuntimeException("保存失败");
//									}
//									valve.setPipingId(piping.getPipingId());
//									valve.setCreateTime(new Date());
//									valve.setValvePlace(space.getSpaceName()+"-"+piping.getPipingName()+"-"+valve.getValveName());
//									ret = valveMapper.insertSelective(valve);
//									if(valve.getSectIds() != null) {
//										String[] sectIds = valve.getSectIds().split(",");
//										SectDev sectDev = null;
//										for(String sectId : sectIds) {
//											sectDev = new SectDev();
//											sectDev.setPipingId(piping.getPipingId());
//											sectDev.setValveId(valve.getValveId());
//											sectDev.setSectId(Long.parseLong(sectId));
//											ret = sectDevMapper.insertSelective(sectDev);
//											if(ret < 1){
//												throw new RuntimeException("保存失败");
//											}
//											sectDev = null;
//										}
//									}
//								}
//							}
//							if(ret < 1){
//								throw new RuntimeException("保存失败");
//							}
//						}
//					}
//				}
//			}
//			
//			
//		}
		return fc.getDevId();
	}
	
	/**
	 * 查询井信息
	 */
	@Override
	public FacilityImgs selectWellById(Long devId) {
		FacilityImgs facilityImgs=facilityMapper.selectImgById(devId);
//		if(facilityImgs != null){
//			
//			List<Space> spaceList = spaceMapper.selectListByPrimaryKey(facilityImgs.getDevId());
//			if(spaceList != null && spaceList.size()>0){
//				for(Space space : spaceList){
//					//处理大孔
//					List<Piping> pipingList = pipingMapper.selectListByPrimaryKey(space.getSpaceId());
//					if(pipingList != null && pipingList.size()>0){
//						for(Piping piping : pipingList){
//							//处理关联光缆段
//							piping.setSectList(sectDevMapper.selectSect(piping.getPipingId(),null));
//							List<Valve> valveList = valveMapper.selectListByPrimaryKey(piping.getPipingId());
//							if(valveList != null){
//								for(int i=0;i<valveList.size();i++) {
//									valveList.get(i).setSectList(sectDevMapper.selectSect(null,valveList.get(i).getValveId()));;
//								}
//								piping.setValvelist(valveList);
//							}
//							
//						}
//						if(pipingList != null){
//							space.setPipinglist(pipingList);
//						}
//					}
//					//处理管道段
//					List<PipelineSection> pipeList = pipelineSectionMapper.selectBySpaceId(devId, space.getSpaceId());
//					if(pipeList != null) {
//						space.setPipelineSection(pipeList);
//					}
//				}
//			}
//			if(spaceList != null && spaceList.size()>0){
//				facilityImgs.setSpacelist(spaceList);
//			}
////			List<WellPileline> wpList = wellPilelineMapper.selectByDevId(devId);
////			List<Facility> facilityList = new ArrayList<>();
////			for(WellPileline wellPileline : wpList){
////				if(wellPileline.getFdevId() == null){
////					continue;
////				}
////				facilityMapper.selectByPrimaryKey(wellPileline.getFdevId());
////				facilityList.add(facilityMapper.selectByPrimaryKey(wellPileline.getFdevId()));
////			}
////			facilityImgs.setFrontWellList(facilityList);
//		}
		return facilityImgs;
	}
	/**
	 * 查询汇聚区
	 * @param orgId
	 * @return
	 */
	@Override
	public List<Facility> selectArea(Long orgId) {
		return facilityMapper.selectArea(orgId);
	}

	@Override
	public Facility queryFacilityBydevCode(String devCode, Long orgId) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("devCode", devCode);
		map.put("orgId", orgId);
		return facilityMapper.queryFacilityBydevCode(map);
	}
	

	@Override
	public FacilityImgs selectWellByDevId(Long devId,Long sectId) {
		FacilityImgs facilityImgs=facilityMapper.selectImgById(devId);
//		if(facilityImgs != null){
//			
//			List<Space> spaceList = spaceMapper.selectListByPrimaryKey(facilityImgs.getDevId());
//			for(Space space : spaceList){
//				List<Piping> pipingList = pipingMapper.selectListByPrimaryKey(space.getSpaceId());
//				for(Piping piping : pipingList){
//					List<Valve> valveList = valveMapper.selectListByPrimaryKey(piping.getPipingId());
//					if(valveList != null){
//						for(Valve valve : valveList) {
//							//判断该子管是否属于当前光缆段
//							if(sectId.equals(valve.getSectId())) {
//								valve.setPipingId(0L);
//							}else {
//								valve.setPipingId(1L);
//							}
//						}
//						piping.setValvelist(valveList);
//					}
//				}
//				if(pipingList != null){
//					space.setPipinglist(pipingList);
//				}
//			}
//			if(spaceList != null && spaceList.size()>0){
//				facilityImgs.setSpacelist(spaceList);
//			}
//		}
		return facilityImgs;
	}

	@Override
	public Facility selectByCode2(String devCode) {
		// TODO Auto-generated method stub
		return facilityMapper.selectByCode(devCode);
	}

	@Override
	public List<Facility> queryByFacility(Long orgId, String areaCode1) {
		return facilityMapper.queryByFacility(orgId,areaCode1);
	}

	@Override
	public List<Facility> queryByFacilityCablint(Long orgId, String areaCode1) {
		return facilityMapper.queryByFacilityCablint(orgId,areaCode1);
	}
	
	/**
	 * 删除设施
	 */
	@Override
	public int update(Long devId) {
		
		int reg=0;
		Facility facility=facilityMapper.selectByPrimaryKey(devId);
		
		if(facility.getDevType()!="11") {
		//删除子类设施
		if(facilityMapper.selectByPdevId(facility.getDevId())!=null) {   //查询到子类设施
			List<Facility> facilityPdevId =facilityMapper.selectByPdevId(facility.getDevId());
			for (int j = 0; j < facilityPdevId.size(); j++) {
			
				//删除三级子类
				if(facilityMapper.selectByPdevId(facilityPdevId.get(j).getDevId())!=null) {
					List<Facility> facilityPdevId2=facilityMapper.selectByPdevId(facilityPdevId.get(j).getDevId());
					for (int k = 0; k < facilityPdevId2.size(); k++) {
						List<Lines> linesList=linesMapper.selectByDevId(facilityPdevId2.get(k).getDevId());
						if(linesList!=null) {
							for (int i = 0; i < linesList.size(); i++) {
								cableSectionMapper.deleteByPrimaryKey(linesList.get(i).getSectId());
							}
							reg=linesMapper.deleteByDevId(facilityPdevId2.get(k).getDevId());
						}
						
						List<Discinfo> discList=discinfoMapper.selectByDevId(facilityPdevId2.get(k).getDevId());
						if(discList!=null) {
							reg=discinfoMapper.deleteByDevId(facilityPdevId2.get(k).getDevId());
						}
						
						List<Fiberdisc> fiberDiscList=fiberdiscMapper.selectByDevId(facilityPdevId2.get(k).getDevId());
						if(fiberDiscList!=null) {
							reg=fiberdiscMapper.deleteByDevId(facilityPdevId2.get(k).getDevId());
						}
						
						List<FiberdiscGroup> fiberDiscGroupList=fiberdiscGroupMapper.selectByDevId(facilityPdevId2.get(k).getDevId());
						if(fiberDiscGroupList!=null) {
							reg=fiberdiscGroupMapper.deleteByDevId(facilityPdevId2.get(k).getDevId());
						}
						
						if(twinfiberMapper.selectByDevId(facilityPdevId2.get(k).getDevId())!=null) {
							reg=twinfiberMapper.deleteByDevId(facilityPdevId2.get(k).getDevId());
						}
						
						reg=facilityMapper.deleteByPrimaryKey(facilityPdevId2.get(k).getDevId());
						
					}
					
					
					
				}
				
				
				List<Lines> linesList=linesMapper.selectByDevId(facilityPdevId.get(j).getDevId());
				if(linesList!=null) {
					for (int i = 0; i < linesList.size(); i++) {
						cableSectionMapper.deleteByPrimaryKey(linesList.get(i).getSectId());
					}
					reg=linesMapper.deleteByDevId(facilityPdevId.get(j).getDevId());
					System.out.println(linesMapper.deleteByDevId(facilityPdevId.get(j).getDevId())+"删除"+facilityPdevId.get(j).getDevId());
				}
				
				List<Discinfo> discList=discinfoMapper.selectByDevId(facilityPdevId.get(j).getDevId());
				if(discList!=null) {
					reg=discinfoMapper.deleteByDevId(facilityPdevId.get(j).getDevId());
				}
				
				List<Fiberdisc> fiberDiscList=fiberdiscMapper.selectByDevId(facilityPdevId.get(j).getDevId());
				if(fiberDiscList!=null) {
					reg=fiberdiscMapper.deleteByDevId(facilityPdevId.get(j).getDevId());
				}
				
				List<FiberdiscGroup> fiberDiscGroupList=fiberdiscGroupMapper.selectByDevId(facilityPdevId.get(j).getDevId());
				if(fiberDiscGroupList!=null) {
					reg=fiberdiscGroupMapper.deleteByDevId(facilityPdevId.get(j).getDevId());
				}
				
				if(twinfiberMapper.selectByDevId(facilityPdevId.get(j).getDevId())!=null) {
					reg=twinfiberMapper.deleteByDevId(facilityPdevId.get(j).getDevId());
				}
				
				reg=facilityMapper.deleteByPrimaryKey(facilityPdevId.get(j).getDevId());
				
			}
		}
		
		List<Lines> linesList=linesMapper.selectByDevId(devId);
		if(linesList!=null) {
			for (int i = 0; i < linesList.size(); i++) {
				cableSectionMapper.deleteByPrimaryKey(linesList.get(i).getSectId());
			}
			reg=linesMapper.deleteByDevId(devId);
			System.out.println(linesMapper.deleteByDevId(devId)+"删除"+devId);
		}
		
		List<Discinfo> discList=discinfoMapper.selectByDevId(devId);
		if(discList!=null) {
			reg=discinfoMapper.deleteByDevId(devId);
		}
		
		List<Fiberdisc> fiberDiscList=fiberdiscMapper.selectByDevId(devId);
		if(fiberDiscList!=null) {
			reg=fiberdiscMapper.deleteByDevId(devId);
		}
		
		List<FiberdiscGroup> fiberDiscGroupList=fiberdiscGroupMapper.selectByDevId(devId);
		if(fiberDiscGroupList!=null) {
			reg=fiberdiscGroupMapper.deleteByDevId(devId);
		}
		
		if(twinfiberMapper.selectByDevId(devId)!=null) {
			reg=twinfiberMapper.deleteByDevId(devId);
		}
		
		reg=facilityMapper.deleteByPrimaryKey(devId);
		}else {
//			if(sectDevMapper.selectByDevId(devId)!=null) {
//				reg=sectDevMapper.deleteByDevId(devId);
//			}
//			if(spaceMapper.selectListByPrimaryKey(devId)!=null) {
//				reg=spaceMapper.deleteByDevId(devId);
//				List<Space> spaceList=spaceMapper.selectListByPrimaryKey(devId);
//				for (int y = 0; y < spaceList.size(); y++) {
//					reg=pipingMapper.deleteByDevId(spaceList.get(y).getSpaceId());
//					List<Piping> pipingList=pipingMapper.selectListByPrimaryKey(spaceList.get(y).getSpaceId());
//					for (int j = 0; j < pipingList.size(); j++) {
//						reg=valveMapper.deleteByDevId(pipingList.get(j).getPipingId());
//					}
//				}
//				
//			}
			
			if(wellPilelineMapper.selectByDevIdOr(devId)!=null) {
				wellPilelineMapper.deleteByDevIdOr(devId);
			}
			
		}
		
		return reg;
	}

	@Override
	public List<Facility> selectByPdevIdCablint(Long devId) {
		return facilityMapper.selectByPdevIdCablint(devId);
	}

	@Override
	public List<Facility> querylllll() {
		return facilityMapper.querylllll();
	}

	/**
	 * 
	* @Title: onSearchFacilities 
	* @Description: 按照模糊查询设施名称 
	* @param @param orgId
	* @param @param searchName
	* @param @param areaCode1
	* @param @return    
	* @return List<Facility>
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午6:59:36 
	* @version V1.0
	 */
	@Override
	public List<Facility> onSearchFacilities(Long orgId, String searchName,String areaCode1) {
		return facilityMapper.onSearchFacilities(orgId,searchName,areaCode1);
	}

	@Override
	public List<FacilityAll> queryall() {
		List<FacilityAll> list=facilityMapper.queryall();
//		for (int i = 0; i < list.size(); i++) {
//			String str=list.get(i).getPort01();
//			if(str!="") {
//				if(routeMapper.queryCodes(str)!=null) {
//					Route route=routeMapper.queryCodes(str);
//					list.get(i).setRouteText(route.getCodes());
//				}
//				
//			}
//		}
		return list;
	}

	/**
	 * 访问机柜,刘沧海,2017-9-28
	 */
	@Override
	public List<FacilityForGroups> queryAllForDev(FacilityForGroups bean) {
		List<FacilityForGroups> facilityList=facilityMapper.queryAllForDev(bean);
//		if(facilityList!=null) {
//			for (int i = 0; i < facilityList.size(); i++) {
//				if(facilityList.get(i).getDevId()!=null ) {
//					List<Group> fiberList=fiberdiscMapper.selectGroup(facilityList.get(i).getDevId());
//					facilityList.get(i).setGroup(fiberList);
//				}
//			}
//		}
		
		return facilityList;
	}

	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<FacilityZF> queryList(Long orgId,String areaCode) {
		return facilityMapper.queryList(orgId,areaCode);
	}

	//导出光交箱 刘沧海 2017/10/17
	@Override
	public List<FacilityAll> queryExportAll(Long orgId, String areaCode) {
		return facilityMapper.queryExportAll(orgId,areaCode);
	}

	//导出机房/机柜  刘沧海  2017/10/17
	@Override
	public List<FacilityAll> queryExportAllJifang(Long orgId, String areaCode) {
		return facilityMapper.queryExportAllJifang(orgId,areaCode);
	}

	@Override
	public Long saveZG(Facility facility) {
		// TODO Auto-generated method stub
		int ret = 0;
		
		facility.setDevCode(siteCodeService.getNewDevCode());
		facility.setDevState("2");//2 新增,dzy修改
		ret = facilityMapper.insertSelective(facility);
		ret = facilityMapper.insertSelectiveZG(facility);
		//生成熔纤盘 新调整不需要在此自动生成熔纤盘
		//ret = genFiberdisc(facility.getDevId(), "A", facility.getFiberDiscNum());
		int  count = 0;
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
		agroup.setSide("JTA");
		agroup.setDiscNum(1);
		agroup.setPortNum(count);
		fcService.addGroupZG(agroup);
		bgroup.setDevId(devId);
		bgroup.setSide("JTB");
		bgroup.setDiscNum(1);
		bgroup.setPortNum(count);
		fcService.addGroupZG(bgroup);
		linesService.addVirtualZG(facility.getDevId(),Constants.FACILITTYPE.GLT);
		
		return facility.getDevId();
	}

	@Override
	public Facility selectByIdZG(Long devId) {
		// TODO Auto-generated method stub
		return facilityMapper.selectByPrimaryKeyZG(devId);
	}

	/**
	 * 
	* @Title: queryJumperGJX 
	* @Description: TODO(查询本端对端跳纤光交箱) 
	* @param @return    入参
	* @return List<ExportJumper>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月1日 下午4:48:58 
	* @version V1.0
	 */
	@Override
	public List<ExportJumper> queryJumperGJX() {
		return facilityMapper.queryJumperGJX();
	}

	/**
	 * 
	* @Title: queryJumperJifang 
	* @Description: TODO(查询本端对端跳纤机房) 
	* @param @return    入参
	* @return List<ExportJumper>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月1日 下午4:59:53 
	* @version V1.0
	 */
	@Override
	public List<ExportJumper> queryJumperJifang() {
		return facilityMapper.queryJumperJifang();
	}
	/**
	 * 
	* @Title: queryJumperFenguangqi 
	* @Description: TODO(查询分光器的跳纤) 
	* @param @return    入参
	* @return List<ExportJumper>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 上午11:36:47 
	* @version V1.0
	 */
	@Override
	public List<ExportJumper> queryJumperFenguangqi() {
		return facilityMapper.queryJumperFenguangqi();
	}
	
	@Override
	public Long save(Facility facility) {
		ResourceDataLog rdl = new ResourceDataLog();
		int ret = 0;
		if((facility.getAreaCode1()==null||"".equals(facility.getAreaCode1()))&&facility.getPdevId()!=null){
			Facility parent = facilityMapper.selectByPrimaryKey(facility.getPdevId());
			if(parent!=null)
				facility.setAreaCode1(parent.getAreaCode1());
		}
		if(facility.getDevId()!=null)
		{
			Facility fa = facilityMapper.selectByPrimaryKey(facility.getDevId());//查询操作前内容,用于添加资管校准日志
			facility.setDevCode(null);//设置为null,修改时不会修改devCode
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
			facility.setDevState("2");//2 新增,dzy修改
			ret = facilityMapper.insertSelective(facility);
			//生成熔纤盘 新调整不需要在此自动生成熔纤盘
			//ret = genFiberdisc(facility.getDevId(), "A", facility.getFiberDiscNum());
			addConnecter(facility);
		}
		//锁定
		System.out.println("----------------------------------------"+facility.getCheckType());
		if(ret>0 && "1".equals(facility.getCheckType())) {
			ret = lockFacility(facility.getDevId(),facility.getCheckUserId(),"1");
		}
		//解锁
		if(ret>0 && "2".equals(facility.getCheckType())) {
			ret = lockFacility(facility.getDevId(),facility.getCheckUserId(),"2");
		}
		return facility.getDevId();
	}
	
//	public static void main(String[] args) {
//		List<String> l = new ArrayList<String>();
//		l.add("1");
//		l.add("2");
//		l.add("3");
//		
//		List<String> s = l.subList(1, l.size());
//		System.out.println(s.size());
//		
//		System.out.println(MapDistance.getDistance(Double.parseDouble("23.1146225484"), Double.parseDouble("114.412529581"),
//				Double.parseDouble("114.411158369"), Double.parseDouble("23.1167381747")));
//	}

	/**
	 * 井的需求
	 * 根据条件,名称(首字母)和经纬度范围内查询
	 */
	@Override
	public List<Facility> querywells(String devName, Long distance, String curLng, String curLat) {
		
		//获取首字母  必须大于三个汉字,才获取首字母
				StringBuffer sb = new StringBuffer();  
				if(devName.length()>3) {
					ChineseCharToEn cte = new ChineseCharToEn(); 
					String spell =  cte.getAllFirstLetter(devName);
					//变成大写  
					for(int i=0;i<spell.length();i++){  
						char c = spell.charAt(i);  
						sb.append(Character.toUpperCase(c));   
					}
				}
				String Upper = sb.toString();
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("devName", devName);
				map.put("curLng", curLng);
				map.put("curLat",curLat);
				//map.put("spell", spell);小写
				map.put("Upper", Upper);
				map.put("distance", distance);
		List<Facility>list=facilityMapper.querywells(map);
		
		return list;
	}

	/**
	 * 
	* @Title: modifyFacilityState 
	* @Description: 对资管导入设施和新增设施状态进行修改
	* @param @param devId
	* @param @param devStateA
	* @param @param devStateZ
	* @param @param devName
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月1日 下午2:01:20 
	* @version V1.0
	 */
	@Override
	public int updateFacilityState(Users users,Facility facility) {
		int reg=0;
		CableSection cableSection=null;
		facility.setLastModifyUser(users.getUserId());
		facility.setLastModifyTime(new Date());
		facility.setDevState(facility.getDevStateZ());
		Facility fabyId=facilityMapper.selectByPrimaryKey(facility.getDevId());
		if(Constants.FACILITTYPE.GLT.toString().equals(fabyId.getDevType())) {
			if(!DateState.NEWDEL.toString().equals(facility.getDevState()) && !DateState.ZGDEL.toString().equals(facility.getDevState())) {
				facility.setDevType(fabyId.getDevType());
				addConnecter(facility);
			}else {
				Facility fa=new Facility();
				fa.setDevId(facility.getDevId());
				fa.setLastModifyTime(new Date());
				fa.setLastModifyUser(users.getUserId());
				fa.setDevModel("0");
				//验证设施名并修改光缆段名称
				validateDevName(fa);
				reg=facilityMapper.updateByPrimaryKeySelective(fa);
			}
		}
		
		//验证设施名并修改光缆段名称
		validateDevName(facility);
		reg=facilityMapper.updateByPrimaryKeySelective(facility);//修改设施
		
		if(DateState.ZGDEL.toString().equals(facility.getDevStateZ()) || DateState.NEWDEL.toString().equals(facility.getDevStateZ())) {//设施状态为删除继续
			Facility fa=null;
			
			if(FacilityDevType.ROOM.toString().equals(fabyId.getDevType())) {//判断是否为机房
				List<Facility> listByPdevid=facilityMapper.selectByPdevId(facility.getDevId());//查询所属该机房的机柜
				if(listByPdevid!=null && listByPdevid.size()>0) {
					for (int i = 0; i < listByPdevid.size(); i++) {
						List<Lines> listCount=linesMapper.queryDevCodeByCode(listByPdevid.get(i).getDevCode());
						if(listCount !=null && listCount.size()>0) {
							throw new FrmsException("【"+listByPdevid.get(i).getDevName()+"】已有成端,不能直接删除!");
						}
						
						fa=new Facility();
						fa.setDevId(listByPdevid.get(i).getDevId());
						fa.setDevState(facility.getDevStateZ());//修改状态
						fa.setLastModifyUser(users.getUserId());
						fa.setLastModifyTime(new Date());
						//验证设施名并修改光缆段名称
						validateDevName(fa);
						reg=facilityMapper.updateByPrimaryKeySelective(fa);
						if(DateState.ZGDEL.toString().equals(listByPdevid.get(i).getDevState())) {//4
							List<CableSection> cableSect=cableSectionMapper.queryByZGDevIdOrgId(listByPdevid.get(i).getDevId(),users.getOrgId());//根据资管机柜ID查询光缆段
							if(cableSect!=null && cableSect.size()>0) {
								for (CableSection cab:cableSect) {
									cableSection=new CableSection();
									cableSection.setSectId(cab.getSectId());
									cableSection.setSectState(facility.getDevStateZ());
									if("0".equals(cab.getSectState())) {
										cableSection.setSecName(cab.getZgSecName());
										cableSection.setFiberNum(cab.getZgFiberNum());
										cableSection.setDevIdA(cab.getZgDevIdA());
										cableSection.setDevIdZ(cab.getZgDevIdZ());
									}else {
										cableSection.setSecName(cab.getSecName());
										cableSection.setFiberNum(cab.getFiberNum());
										cableSection.setDevIdA(cab.getDevIdA());
										cableSection.setDevIdZ(cab.getDevIdZ());
									}
									cableSection.setVerifyNum(1);
									reg=cableSectionService.upddateByIdAndState(users,cableSection);
									cableSection=null;
								}
							}
						}else {
							List<CableSection> cableSect=cableSectionMapper.queryByDevIdOrgId(listByPdevid.get(i).getDevId(),users.getOrgId());//根据机柜ID查询光缆段
							if(cableSect!=null && cableSect.size()>0) {
								for (CableSection cab:cableSect) {
									cableSection=new CableSection();
									cableSection.setSectId(cab.getSectId());
									cableSection.setSectState(facility.getDevStateZ());
									cableSection.setVerifyNum(1);
									reg=cableSectionService.upddateByIdAndState(users,cableSection);
									cableSection=null;
								}
							}
						}
						
						fa=null;
						List<Facility> listByFG=facilityMapper.selectByPdevId(listByPdevid.get(i).getDevId());//查询所属该机柜的分光器
						if(listByFG!=null && listByFG.size()>0) {
							for (int j = 0; j < listByFG.size(); j++) {
								fa=new Facility();
								fa.setDevId(listByFG.get(i).getDevId());
								fa.setDevState(facility.getDevStateZ());//修改状态
								fa.setLastModifyUser(users.getUserId());
								fa.setLastModifyTime(new Date());
								//验证设施名并修改光缆段名称
								validateDevName(fa);
								reg=facilityMapper.updateByPrimaryKeySelective(fa);
								fa=null;
								if(DateState.ZGDEL.toString().equals(listByFG.get(i).getDevState())) {	//4
									List<CableSection> cableFGlist=cableSectionMapper.queryByZGDevIdOrgId(listByFG.get(i).getDevId(),users.getOrgId());
									if(cableFGlist!=null && cableFGlist.size()>0) {
										for (CableSection cab:cableFGlist) {
											cableSection=new CableSection();
											cableSection.setSectId(cab.getSectId());
											cableSection.setSectState(facility.getDevStateZ());
											if("0".equals(cab.getSectState())) {
												cableSection.setSecName(cab.getZgSecName());
												cableSection.setFiberNum(cab.getZgFiberNum());
												cableSection.setDevIdA(cab.getZgDevIdA());
												cableSection.setDevIdZ(cab.getZgDevIdZ());
											}else {
												cableSection.setSecName(cab.getSecName());
												cableSection.setFiberNum(cab.getFiberNum());
												cableSection.setDevIdA(cab.getDevIdA());
												cableSection.setDevIdZ(cab.getDevIdZ());
											}
											cableSection.setVerifyNum(1);
											reg=cableSectionService.upddateByIdAndState(users,cableSection);
											cableSection=null;
											
										}
									}
								}else {
									List<CableSection> cableFGlist=cableSectionMapper.queryByDevIdOrgId(listByFG.get(i).getDevId(),users.getOrgId());
									if(cableFGlist!=null && cableFGlist.size()>0) {
										for (CableSection cab:cableFGlist) {
											cableSection=new CableSection();
											cableSection.setSectId(cab.getSectId());
											cableSection.setSectState(facility.getDevStateZ());
											cableSection.setVerifyNum(1);
											reg=cableSectionService.upddateByIdAndState(users,cableSection);
											cableSection=null;
										}
									}
								}
							}
						}
					}
				}
			}else {
				if(DateState.ZGDEL.toString().equals(facility.getDevState())) {
					Facility facilityDevId=facilityMapper.selectByPrimaryKey(facility.getDevId());
					List<Lines> listCount=linesMapper.queryDevCodeByCode(facilityDevId.getDevCode());
					if(listCount !=null && listCount.size()>0) {
						throw new FrmsException("【"+facilityDevId.getDevName()+"】 已有成端,不能直接删除!");
					}
					
					List<CableSection> cableFGlist=cableSectionMapper.queryByZGDevIdOrgId(facility.getDevId(), users.getOrgId());
					if(cableFGlist!=null && cableFGlist.size()>0) {
						for (CableSection cab:cableFGlist) {
							cableSection=new CableSection();
							cableSection.setSectId(cab.getSectId());
							cableSection.setSectState(facility.getDevStateZ());
							if("0".equals(cab.getSectState())) {
								cableSection.setSecName(cab.getZgSecName());
								cableSection.setFiberNum(cab.getZgFiberNum());
								cableSection.setDevIdA(cab.getZgDevIdA());
								cableSection.setDevIdZ(cab.getZgDevIdZ());
							}else {
								cableSection.setSecName(cab.getSecName());
								cableSection.setFiberNum(cab.getFiberNum());
								cableSection.setDevIdA(cab.getDevIdA());
								cableSection.setDevIdZ(cab.getDevIdZ());
							}
							cableSection.setVerifyNum(1);
							reg=cableSectionService.upddateByIdAndState(users,cableSection);
							cableSection=null;
						}
					}
				}else {
					Facility facilityDevId=facilityMapper.selectByPrimaryKey(facility.getDevId());
					List<Lines> listCount=linesMapper.queryDevCodeByCode(facilityDevId.getDevCode());
					if(listCount !=null && listCount.size()>0) {
						throw new FrmsException("【"+facilityDevId.getDevName()+"】 已有成端,不能直接删除!");
					}
					List<CableSection> cable=cableSectionMapper.queryByDevIdOrgId(facility.getDevId(), users.getOrgId());
					if(cable!=null && cable.size()>0) {
						for (int i = 0; i < cable.size(); i++) {
							cableSection=new CableSection();
							cableSection.setSectId(cable.get(i).getSectId());
							cableSection.setSectState(facility.getDevStateZ());
							cableSection.setVerifyNum(1);
							reg=cableSectionService.upddateByIdAndState(users,cableSection);
							cableSection=null;
						}
					}
				}
				
				List<Facility> listByPdevid=facilityMapper.selectByPdevId(facility.getDevId());//根据设施ID查分光器
				if(listByPdevid!=null && listByPdevid.size()>0) {
					for (int i = 0; i < listByPdevid.size(); i++) {
						fa=new Facility();
						fa.setDevId(listByPdevid.get(i).getDevId());
						fa.setDevState(facility.getDevStateZ());//修改状态
						fa.setLastModifyUser(users.getUserId());
						fa.setLastModifyTime(new Date());
						//验证设施名并修改光缆段名称
						validateDevName(fa);
						reg=facilityMapper.updateByPrimaryKeySelective(fa);
						fa=null;
						if(DateState.ZGDEL.toString().equals(listByPdevid.get(i).getDevState())) {
							List<CableSection> cableFGlist=cableSectionMapper.queryByZGDevIdOrgId(listByPdevid.get(i).getDevId(),users.getOrgId());
							if(cableFGlist!=null && cableFGlist.size()>0) {
								for (CableSection cab:cableFGlist) {
									cableSection=new CableSection();
									cableSection.setSectId(cab.getSectId());
									cableSection.setSectState(facility.getDevStateZ());
									if("0".equals(cab.getSectState())) {
										cableSection.setSecName(cab.getZgSecName());
										cableSection.setFiberNum(cab.getZgFiberNum());
										cableSection.setDevIdA(cab.getZgDevIdA());
										cableSection.setDevIdZ(cab.getZgDevIdZ());
									}else {
										cableSection.setSecName(cab.getSecName());
										cableSection.setFiberNum(cab.getFiberNum());
										cableSection.setDevIdA(cab.getDevIdA());
										cableSection.setDevIdZ(cab.getDevIdZ());
									}
									cableSection.setVerifyNum(1);
									reg=cableSectionService.upddateByIdAndState(users,cableSection);
									cableSection=null;
								}
							}
						}else {
							List<CableSection> cableSect=cableSectionMapper.queryByDevIdOrgId(listByPdevid.get(i).getDevId(),users.getOrgId());
							if(cableSect!=null && cableSect.size()>0) {
								for (int j = 0; j < cableSect.size(); j++) {
									cableSection=new CableSection();
									cableSection.setSectId(cableSect.get(i).getSectId());
									cableSection.setSectState(facility.getDevStateZ());
									cableSection.setVerifyNum(1);
									reg=cableSectionService.upddateByIdAndState(users,cableSection);
									cableSection=null;
								}
							}
						}
					}
				}
			}
		}
		return reg;
	}

	/**
	 * 
	* @Title: verifyJumpper 
	* @Description: 校验是否有跳纤 
	* @param @param acode1
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月3日 下午5:56:18 
	* @version V1.0
	 */
	@Override
	public int verifyJumpper(String acode1, String zcode1, String acode2, String zcode2) {
		int num=0;
		if(!"".equals(acode1)) {
			int reg=linesMapper.isExistJumperByCode(acode1);
			if(reg>0) {
				throw new FrmsException("所选端子"+acode1+"已占用");
			}else {
				num=0;
			}
		}else if(!"".equals(zcode1)) {
			int reg=linesMapper.isExistJumperByCode(zcode1);
			if(reg>0) {
				throw new FrmsException("所选端子"+zcode1+"已占用");
			}else {
				num=0;
			}
		}else if(!"".equals(acode2)) {
			int reg=linesMapper.isExistJumperByCode(acode2);
			if(reg>0) {
				throw new FrmsException("所选端子"+acode2+"已占用");
			}else {
				num=0;
			}
		}else if(!"".equals(zcode2)) {
			int reg=linesMapper.isExistJumperByCode(zcode2);
			if(reg>0) {
				throw new FrmsException("所选端子"+zcode2+"已占用");
			}else {
				num=0;
			}
		}
		return num;
	}

	/**
	 * 恢复设施数据
	 */
	@Override
	public int recoveryFacilityByState(Long devId, String devState, Users user) {
		int num = 0;
		Facility fa = facilityMapper.selectByPrimaryKey(devId);
		fa.setLastModifyTime(new Date());
		fa.setLastModifyUser(user.getUserId());
		if("4".equals(fa.getDevState())) {//如果资管删除,改为修改
			fa.setDevState("3");
		}else if("5".equals(fa.getDevState())) {//如果新增删除,改为新增
			fa.setDevState("2");
		}
		num = facilityMapper.updateByPrimaryKeySelective(fa);
		List<Facility> faList = facilityMapper.selectByPdevId(devId);//判断是否有子设施
		Facility f = null;
		if(faList != null && faList.size()>0){
			for (int i = 0; i < faList.size(); i++) {
				f = new Facility();
				f = faList.get(i);
				f.setLastModifyTime(new Date());
				f.setLastModifyUser(user.getUserId());
				if(DateState.ZGDEL.toString().equals(f.getDevState())) {//如果资管删除,改为修改
					f.setDevState("3");
				}else if(DateState.NEWDEL.toString().equals(f.getDevState())) {//如果新增删除,改为新增
					f.setDevState("2");
				}
				num = facilityMapper.updateByPrimaryKeySelective(f);
				List<CableSectionBean> caList = cableSectionService.queryByDevId(devId, f.getOrgId());//根据devId查询光缆段
				for (CableSectionBean cableSection : caList) {
					num = cableSectionService.recoveryCableSectionByState(cableSection.getSectId(), cableSection.getSectState(), user);
				}
				f = null;
			}
		}
		return num;
	}

	/**
	 * 查询子设施列表
	 */
	@Override
	public List<Facility> queryFacilityOdf(Long devId, Integer queryType, PageBean pb) {
		List<Facility> fList = facilityMapper.queryFacilityOdf(devId,queryType,pb);
		return fList;
	}

	/**
	 * 根据编码查询设施
	 */
	@Override
	public Facility selectfacilityByCode(String devCode, Long orgId) {
		Facility facility = facilityMapper.selectByCode1(devCode,orgId);
		return facility;
	}

	/**
	 * 
	* @Title: queryByDevIdForOppo 
	* @Description: 根据设施ID查询不是本端的设施 
	* @param @param session
	* @param @param facility
	* @param @return    入参
	* @return Object    返回类型
	* @author 刘沧海 
	* @throws
	* @date 2017年12月10日 下午2:52:07 
	* @version V1.0
	 */
	@Override
	public List<Facility> queryByDevIdForOppo(Facility facility,PageBean pb) {
		return facilityMapper.queryByDevIdForOppo(facility,pb);
	}

	/**
	 * 
	* @Title: findFacilitiesAreaCode1 
	* @Description: 根据汇聚区查询 
	* @param @param orgId
	* @param @param areaCode1
	* @param @return    
	* @return List<Facility>
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午1:48:45 
	* @version V1.0
	 */
	@Override
	public List<Map<String, Object>> findFacilitiesAreaCode1(Long orgId, String areaCode1) {
		return facilityMapper.findFacilitiesAreaCode1(orgId,areaCode1);
	}

	/**
	 * 
	* @Title: findbyDevId 
	* @Description: 根据设施ID查询光缆段成端状态 
	* @param @param devId
	* @param @return    
	* @return FacilityAsasideCableBean
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午5:29:04 
	* @version V1.0
	 */
	@Override
	public List<Facility> findbyDevId(Long devId,Users user) {
//		FacilityAsasideCableBean bean=new FacilityAsasideCableBean();
//		List<CableSection> cableTwoCD=bean.getCableTwoCD();
		FacilityAsasideCableBean fa=facilityMapper.queryForFacilityIds(devId,user.getOrgId());
		if(fa != null) {
			String ids = fa.getIds();
			List<Facility> list=facilityMapper.queryInfacilityIds(ids);
			Iterator<Facility> it = list.iterator();
			while(it.hasNext()){
				Facility x = it.next();
				if(x.getBaiduX()==null || "".equals(x.getBaiduX().toString())||
						x.getBaiduY()==null || "".equals(x.getBaiduY().toString())) {
					it.remove();
				}
			}
			return list;
		}else {
			return null;
		}
//		List<CableSection> cableAsasideTwoCD=cableSectionMapper.queryAsasideTwoCD(devId,user.getOrgId());
//		if(cableAsasideTwoCD!=null && cableAsasideTwoCD.size()>0) {
//			cableTwoCD.addAll(cableAsasideTwoCD);
//		}
		
	}
	
	

	/**
	 * 
	* @Title: findCDState 
	* @Description: 根据设施ID查询所属光缆段 
	* @param @param session
	* @param @param devId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月19日 下午9:27:24 
	* @version V1.0
	 */
	@Override
	public FacilityAsasideCableBean findCDState(Long devId, Long orgId) {
		FacilityAsasideCableBean bean=new FacilityAsasideCableBean();
		List<CableSection> listTwo=cableSectionMapper.queryAsasideTwoCD(devId, orgId);//两端都成端
		if(listTwo!=null && listTwo.size()>0) {
			List<CableSection> list2=saveFacilityXY(listTwo);
			bean.setCableTwoCD(list2);
		}
		List<CableSection> listOne=cableSectionMapper.queryAsasideOneCD(devId, orgId);//一端成端
		if(listOne!=null && listOne.size()>0) {
			List<CableSection> list1=saveFacilityXY(listOne);
			bean.setCableOneCD(list1);
		}
		List<CableSection> listZero=cableSectionMapper.queryAsasideZero(devId, orgId);
		if(listZero!=null && listZero.size()>0) {
			List<CableSection> list0=saveFacilityXY(listZero);
			bean.setCableZeroCD(list0);
		}
		return bean;
	}
	/**
	 * 
	* @Title: saveFacilityXY 
	* @Description: 给光缆段添加设施坐标 
	* @param @param list
	* @param @return    
	* @return List<CableSection>
	* @author liucanghai 
	* @throws
	* @date 2017年12月20日 上午10:57:45 
	* @version V1.0
	 */
	public List<CableSection> saveFacilityXY(List<CableSection> list) {
		List<CableSection> caList=new ArrayList<CableSection>();
		Facility aFacility=new Facility();
		Facility zFacility=new Facility();
		for (CableSection cableSection : list) {
			aFacility=facilityMapper.selectByPrimaryKey(cableSection.getDevIdA());
			zFacility=facilityMapper.selectByPrimaryKey(cableSection.getDevIdZ());
			if (null !=aFacility   ) {
				if (null!=zFacility ) {
					if(zFacility.getBaiduX()!=null && !"".equals(zFacility.getBaiduX().toString())&&
							zFacility.getBaiduY()!=null && !"".equals(zFacility.getBaiduY().toString())) {
						if(aFacility.getBaiduX()!=null && !"".equals(aFacility.getBaiduX().toString())&&
								aFacility.getBaiduY()!=null && !"".equals(aFacility.getBaiduY().toString())) {
							cableSection.setAfacility(aFacility);
							cableSection.setZfacility(zFacility);
							caList.add(cableSection);
						}
					}
				}
			}else {
				continue;
			}
		}
		return caList;
	}
	/**
	 * 
	* @Title: updateByDevIdAndDevName 
	* @Description: 更新设施 
	* @param @param session
	* @param @param facility
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月4日 下午2:26:17 
	* @version V1.0
	 */
	@Override
	public int updateByDevIdAndDevName(Facility facility,Users users) {
		facility.setLastModifyUser(users.getOrgId());
		facility.setLastModifyTime(new Date());
		return facilityMapper.updateByPrimaryKeySelective(facility);
	}

	/**
	 * 
	* @Title: deleteOpticalSplitter 
	* @Description: 删除分光器 
	* @param @param devId
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年1月4日 下午3:05:16 
	* @version V1.0
	 */
	@Override
	public int deleteOpticalSplitter(Long devId, Users user) {
		int reg=0;
		
		Facility fa = facilityMapper.selectByPrimaryKey(devId);
		if(fa!=null && fa.getDevCode()!=null && !"".equals(fa.getDevCode())) {//如果可以查到设施并且devCode不为空
			String devCode = fa.getDevCode()+"-";
			List<Lines> lineList=linesMapper.queryForDiscCodeByOPS("01",devCode);
			if(lineList!=null && lineList.size()>0) {
				throw new FrmsException("请先解除成端再删除该分光器！");//判断如果有成端不能删除
			}
			List<Lines> lineListj=linesMapper.queryForDiscCodeByOPS("02",devCode);
			if(lineListj!=null && lineListj.size()>0) {
				throw new FrmsException("请先解除跳纤再删除该分光器！");//判断如果有跳纤不能删除
			}
			reg=linesMapper.deleteByDevId(devId);//删除lines
			reg=fiberdiscMapper.deleteByDevId(devId);//删除端子
			reg=discinfoMapper.deleteByDevId(devId);//删除盘
			reg=fiberdiscGroupMapper.deleteByDevId(devId);//删除分光器分组
			reg=facilityMapper.deleteByPrimaryKey(devId);//删除分光器
		}
		return reg;
	}
	/**
	 * 
	* @Title: findFacilityLists 
	* @Description: 根据地址信息返回设施，如果code不为null,当设施为机柜返回所属机房 
	* @param @param facilityCon
	* @param @param pageSize
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月9日 上午9:29:49 
	* @version V1.0
	 */
	@Override
	public PageBean queryFacilitysByPosition(FacilityConditionBean bean, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		String devCode = bean.getDevCode();
		if(devCode!=null && devCode!="") {
			conditionMap.put("devCode",devCode);
			Facility f = facilityMapper.queryFacilityBydevCode(conditionMap);
			List<Facility> arrayList = new ArrayList<Facility>();
			if(f!=null) {
				if(Objects.equals(f.getDevType(), "05")) {
					if(f.getPdevId()!=null) {
						Facility facility = facilityMapper.selectByPrimaryKey(f.getPdevId());
						if(facility!=null) {
							arrayList.add(facility);
						}
					}else {
						arrayList.add(f);
					}
				}else {
						arrayList.add(f);
				}
			}
			pb.setList(arrayList);
			pb.setRows(1);
		}else {
			conditionMap.put("fc", bean);
			conditionMap.put("pb", pb);
			pb.setRows(facilityMapper.countFacilitysByPosition(conditionMap));//;
			pb.setList(this.convert(facilityMapper.selectFacilitysByPosition(conditionMap),null));	
		}
		return pb;
	}

	/**
	 * 整合m/v1/facilityList.htm
	 * m/facilityByDevcode.htm 
	 * m/v1/getAround.htm
	 * 
	* @Title: FacilityService.java 
	* @Description: TODO 
	* @param @param facility
	* @param @param pb
	* @param @param orgId
	* @param @return
	* @return List<FacilityConditionBean>
	* @author fl
	* @date 2018年1月19日 下午2:12:19
	* @version V1.0
	 */
	@Override
	public List<Facility> queryFacilityByConditions(FacilityConditionBean facility, PageBean pb,Long orgId) {
		if (facility.getDevType()==null || "".equals(facility.getDevType())) {
			facility.setDevTypes(new String[0]);
		}else {
			String [] devTypes=facility.getDevType().split(",");
//			for (int i = 0; i < devTypes.length; i++) {
//				System.out.println(devTypes[i]);
//			}
			facility.setDevTypes(devTypes);
		}
		return facilityMapper.queryFacilityByConditions(facility,pb,orgId);
	}

	/**
	 * 
	* @Title: queryJumperTerrace 
	* @Description: 查询端子详情 
	* @param @param code
	* @param @return    
	* @return JumperTerraceInfo
	* @author liucanghai 
	* @throws
	* @date 2018年1月24日 下午2:24:46 
	* @version V1.0
	 */
	@Override
	public JumperTerraceInfo queryJumperTerrace(String code) {
		JumperTerraceInfo info=new JumperTerraceInfo();
		if(code!=null && !"".equals(code)) {
			info.setCode(code);//端口编码
//			String[] codes = code.split("-");
			Facility facility = new Facility();//查询设施
//			if(facility!=null) {
//				info.setDevName("编号：（"+facility.getDevCode()+"）"+"名称："+facility.getDevName());//设施名称
//			}
			Fiberdisc fiberdisc = fiberdiscMapper.selectByPort(code);
			if(fiberdisc!=null) {
				info.setIsOccup(fiberdisc.getIsOccup());//端口占用状态
			}
			Lines line01 = linesMapper.queryByCode01(code);//查询成端
			if(line01!=null) {
				CableSection casect = cableSectionMapper.selectByPrimaryKey(line01.getSectId());
				if(casect!=null) {
					info.setSecName("编号：（"+casect.getSectCode()+"）"+"名称："+casect.getSecName()+"：第"+line01.getFiberNo().toString()+"芯序");//光缆段名称
//					info.setFiberNum("纤芯数（"+casect.getFiberNum().toString()+"）芯的第（"+line01.getFiberNo().toString()+"）纤芯");
					CableSectionDec casectDec = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(casect.getSectId(), line01.getFiberNo());
					if(casectDec!=null) {
						info.setSrvName(casectDec.getZgSectDec());//资管业务描述
					}
				}
				
			}
			Lines line02 = linesMapper.queryLinsForAcode(code);//查询跳纤
			if(line02!=null) {
				if(code.equals(line02.getAcode())) {
					facility=getFacilityForCode(code);
					if(facility!=null) {
						info.setDevName(facility.getDevName()+"（"+line02.getZcode()+"）");
//						info.setFiberPlace("编号（"+facility.getDevCode()+"）"+"名称："+facility.getDevName()+"："+line02.getZcode());//跳纤另一端位置
					}
				}else {
					info.setFiberPlace(line02.getAcode());
					facility=getFacilityForCode(code);
					if(facility!=null) {
						info.setDevName(facility.getDevName()+"（"+line02.getAcode()+"）");
					}
				}
				info.setSrvName(line02.getSrvName());//业务描述
				List<LineImage> lineImg = lineImageMapper.queryByLineId(line02.getLineId());
				if(lineImg!=null && lineImg.size()>0) {
					info.setLineImgUrl(lineImg);
				}
			}
			Route route=routeMapper.queryRouteTextForCode(code);
			if(route!=null) {
				info.setRouteText(route.getRoutetext());//文本路由
				info.setRouteName(route.getRouteName());//光路名称
			}
		}
		return info;
	}

	/**
	 * 
	* @Title: getFacilityForCode 
	* @Description: 根据 端口编码查设施
	* @param @param code
	* @param @return    
	* @return Facility
	* @author liucanghai 
	* @throws
	* @date 2018年1月24日 下午2:02:26 
	* @version V1.0
	 */
	public Facility getFacilityForCode(String code) {
		if(code!=null && !"".equals(code)) {
			String[] codes = code.split("-");
			Facility facility = facilityMapper.selectByCode(codes[0]);
			return facility;
		}else {
			return null;
		}
	}

	/**
	 * 根据是否有经纬度导出设施数据
	* 
	* @Title: FacilityService.java 
	* @Description: TODO 
	* @param @param orgId
	* @param @param existLngLat
	* @param @return
	* @return List<FacilityAll>
	* @author fl
	* @date 2018年1月24日 下午4:48:43
	* @version V1.0
	 * @param pb 
	 */
	@Override
	public List<FacilityBean> queryExportExisttLngLat(Long orgId,String existLngLat) {
		List<FacilityBean> list = facilityMapper.queryExportExisttLngLat(orgId,existLngLat);
//		if (list !=null && list.size()>0) {
//			for (int i = 0; i < list.size(); i++) {
//				FacilityBean fb = list.get(i);
//				if (fb !=null && fb.getPdevId() !=null) {
//					List<Facility> facilityList = facilityMapper.selectByPdevId(fb.getPdevId());
//					if (facilityList !=null && facilityList.size()>0) {
//						for (int j = 0; j < facilityList.size(); j++) {
//							Facility facility = facilityList.get(j);
//							fb.setPdevName(facility.getDevName());
//						}
//					}
//				}
//				list.add(fb);
//			}
//		}
		return list;
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
	@Override
	public List<CableSectionBean> queryFinCable(Long groupId) {
		
		List<CableSectionBean> cableList=new ArrayList<CableSectionBean>();
		FiberdiscGroup group = fiberdiscGroupMapper.selectByPrimaryKey(groupId);
		Facility facility = facilityMapper.selectByPrimaryKey(group.getDevId());
		
		String discCode = facility.getDevCode()+"-"+group.getSide()+"-";
		
		List<Fiberdisc> fiberdiscList=fiberdiscMapper.queryByPort01(discCode);
		if(fiberdiscList!=null && fiberdiscList.size()>0) {
			for (int i = 0; i < fiberdiscList.size(); i++) {
				CableSectionBean cable = cableSectionMapper.queryBySectIdBean(fiberdiscList.get(i).getSectId());
				cableList.add(cable);
			}
		}
		isInvestment(cableList);
		return cableList;
	}
	public void isInvestment(List<CableSectionBean> cablesectionlist){
		for (CableSectionBean cableSectionBean : cablesectionlist) {

			List<Long> chengduan=new ArrayList<Long>();
			List<Long> zhirong=new ArrayList<Long>();
			List<Long> weichengduan=new ArrayList<Long>();
					Long sectId = cableSectionBean.getSectId();
					List<LinesBean> lineslist = linesMapper.queryBySectId(sectId);
					for (LinesBean linesBean : lineslist) {
						Long fiberNo = linesBean.getFiberNo();
						String acode = linesBean.getAcode();
						String zcode = linesBean.getZcode();
						//未成端
						if(acode==null&&zcode==null) {
							weichengduan.add(fiberNo);
						//成端
							//az均不为空
						}else if(acode!=null&&zcode!=null) {
							if(acode.contains("ZRIN")||acode.contains("ZROUT")||zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
								zhirong.add(fiberNo);
							}else {
								chengduan.add(fiberNo);
							}
						}
						//az一端为空
						else {
							try {
								if(acode.contains("ZRIN")||acode.contains("ZROUT")) {
									zhirong.add(fiberNo);
								}else {
									chengduan.add(fiberNo);
								}
								
							} catch (Exception e) {
								if(zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
									zhirong.add(fiberNo);
								}else {
									chengduan.add(fiberNo);
								}
							}
							
						}
							
					}
					cableSectionBean.setInvestment(StringUtil.sort(zhirong)!=null?StringUtil.sort(zhirong):"");
					cableSectionBean.setInused(StringUtil.sort(chengduan)!=null?StringUtil.sort(chengduan):"");
					cableSectionBean.setNotInused(StringUtil.sort(weichengduan)!=null?StringUtil.sort(weichengduan):"");
					
		}
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
	@Override
	public List<CableSectionBean> queryZhiCable(Long groupId) {
		List<CableSectionBean> cableList=new ArrayList<CableSectionBean>();
		CableSectionBean cableZhi=null;
		FiberdiscGroup group = fiberdiscGroupMapper.selectByPrimaryKey(groupId);
		Facility facility = facilityMapper.selectByPrimaryKey(group.getDevId());
//		|| group.getSide().contains("ZROUT")
		if(group.getSide().contains("ZRIN") || group.getSide().contains("ZROUT")) {
			String AdiscCode="";
			String ZdiscCode="";
			if(group.getSide().contains("ZRIN")) {
				AdiscCode = facility.getDevCode()+"-"+group.getSide()+"-";
				ZdiscCode = facility.getDevCode()+"-"+group.getSide().replaceAll("ZRIN", "ZROUT")+"-";
			}else {
				ZdiscCode = facility.getDevCode()+"-"+group.getSide()+"-";
				AdiscCode = facility.getDevCode()+"-"+group.getSide().replaceAll("ZROUT", "ZRIN")+"-";
			}	
			List<FiberdiscBean> fiberdiscList=fiberdiscMapper.queryByZhiPort01(AdiscCode,ZdiscCode);
			if(fiberdiscList!=null && fiberdiscList.size()>0) {
				for (int i = 0; i < fiberdiscList.size(); i++) {
					CableSectionBean acable = cableSectionMapper.queryBySectIdBean(fiberdiscList.get(i).getaSectId());
					CableSectionBean zcable = cableSectionMapper.queryBySectIdBean(fiberdiscList.get(i).getzSectId());
					cableZhi=new CableSectionBean();
					cableZhi.setaSectId(acable.getSectId());//直熔A端光缆
					cableZhi.setaSecName(acable.getSecName());
					cableZhi.setaSectCode(acable.getSectCode());
					cableZhi.setzSecName(zcable.getSecName());//直熔Z端光缆
					cableZhi.setzSectCode(zcable.getSectCode());
					cableZhi.setzSectId(zcable.getSectId());
					cableList.add(cableZhi);
				}
			}
			zIsInvestment(cableList);
			cableZhi=null;
		}
		return cableList;
	}
	public void zIsInvestment(List<CableSectionBean> cablesectionlist){
		for (CableSectionBean cableSectionBean : cablesectionlist) {
			List<Long> chengduan=new ArrayList<Long>();
			List<Long> weichengduan=new ArrayList<Long>();
			List<Long> zhirong=new ArrayList<Long>();
			List<Long> Zzhirong=new ArrayList<Long>();
			
					Long aSectId = cableSectionBean.getaSectId();
					Long zSectId = cableSectionBean.getzSectId();
					List<LinesBean> alineslist = linesMapper.queryBySectId(aSectId);
					for (LinesBean linesBean : alineslist) {
						Long fiberNo = linesBean.getFiberNo();
						String acode = linesBean.getAcode();
						String zcode = linesBean.getZcode();
						//未成端
						if(acode==null&&zcode==null) {
							weichengduan.add(fiberNo);
						//成端
							//az均不为空
						}else if(acode!=null&&zcode!=null) {
							if(acode.contains("ZRIN")||acode.contains("ZROUT")||zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
								if(acode.contains("ZRIN") || zcode.contains("ZRIN")) {
									zhirong.add(fiberNo);
								}
								if(acode.contains("ZROUT") || zcode.contains("ZROUT")) {
									Zzhirong.add(fiberNo);
								}else {
									chengduan.add(fiberNo);
								}
							}
						}
						//az一端为空
						else {
							try {
								if(acode.contains("ZRIN")||acode.contains("ZROUT")) {
									if(acode.contains("ZRIN")) {
										zhirong.add(fiberNo);
									}if(acode.contains("ZROUT")){
										Zzhirong.add(fiberNo);
									}else {
										chengduan.add(fiberNo);
									}
								}
								
							} catch (Exception e) {
								if(zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
									if(zcode.contains("ZRIN")) {
										zhirong.add(fiberNo);
									}if(zcode.contains("ZROUT")) {
										Zzhirong.add(fiberNo);
									}else {
										chengduan.add(fiberNo);
									}
								}
							}
							
						}
							
					}
					List<LinesBean> zlineslist = linesMapper.queryBySectId(zSectId);
					for (LinesBean linesBean : zlineslist) {
						Long fiberNo = linesBean.getFiberNo();
						String acode = linesBean.getAcode();
						String zcode = linesBean.getZcode();
						//未成端
						if(acode==null&&zcode==null) {
							weichengduan.add(fiberNo);
						//成端
							//az均不为空
						}else if(acode!=null&&zcode!=null) {
							if(acode.contains("ZRIN")||acode.contains("ZROUT")||zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
								if(acode.contains("ZRIN") || zcode.contains("ZRIN")) {
									zhirong.add(fiberNo);
								}
								if(acode.contains("ZROUT") || zcode.contains("ZROUT")) {
									Zzhirong.add(fiberNo);
								}else {
									chengduan.add(fiberNo);
								}
							}
						}
						//az一端为空
						else {
							try {
								if(acode.contains("ZRIN")||acode.contains("ZROUT")) {
									if(acode.contains("ZRIN")) {
										zhirong.add(fiberNo);
									}if(acode.contains("ZROUT")) {
										Zzhirong.add(fiberNo);
									}else {
										chengduan.add(fiberNo);
									}
								}
								
							} catch (Exception e) {
								if(zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
									if(zcode.contains("ZRIN")) {
										zhirong.add(fiberNo);
									}if(zcode.contains("ZROUT")) {
										Zzhirong.add(fiberNo);
									}else {
										chengduan.add(fiberNo);
									}
								}
							}
							
						}
							
					}
					cableSectionBean.setInvestment(StringUtil.sort(zhirong)!=null?StringUtil.sort(zhirong):"");
					cableSectionBean.setzInvestment(StringUtil.sort(Zzhirong)!=null?StringUtil.sort(Zzhirong):"");
					
		}
	}

	/**
	 * 
	* @Title: queryByConditionBeanInspect 
	* @Description: 巡检任务管理查询设施 
	* @param @param bean
	* @param @return    
	* @return List<Facility>
	* @author liucanghai 
	* @throws
	* @date 2018年2月5日 下午4:33:54 
	* @version V1.0
	 */
	@Override
	public List<Facility> queryByConditionBeanInspect(FacilityConditionBean bean) {
		if(bean ==null) 
			bean = new FacilityConditionBean();
		return facilityMapper.queryByConditionBeanInspect(bean);
	}


	/**
	 * 根据用户,查询有问题的设施
	* @Title: queryProblematicalFactilityByUser 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @return    入参
	* @return List<FacilityVoBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午4:37:24 
	* @version V1.0
	 */
	@Override
	public List<FacilityVoBean> queryProblematicalFactilityByUser(Long orgId, PageBean pb,int isCheckAll,Long userId,FacilityVoBean bean) {
		return facilityMapper.queryProblematicalFactilityByUser(orgId,pb,isCheckAll,userId,bean);
	}

	/**
	 * 
	 * @Title: queryByOrgId
	 * @Description: 查询组织机构下是否有设施
	 * @param @param orgId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月17日 下午4:28:58
	 * @version V1.0
	 */
	@Override
	public int queryByOrgId(Long orgId) {
		return facilityMapper.queryByOrgId(orgId);
	}

	/**
	 * 保存设施
	* @Title: save2 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param list
	* @param @return    入参
	* @return Long    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月27日 下午4:36:55 
	* @version V1.0
	 */
	@Override
	public Long save2(FacilityVoBeanXY fvb, List<FacilityImg> images) {
		Facility facility =  new Facility();
		facility.setLastModifyUser(fvb.getLastModifyUser());
		facility.setLastModifyTime(fvb.getLastModifyTime());
		facility.setDevAddr(fvb.getDevAddr());
		facility.setAreaCode1(fvb.getAreaCode1());
		facility.setAreaCode2(fvb.getAreaCode2());
		facility.setCodeA(fvb.getCodeA());
		facility.setDevStateA(fvb.getDevStateA());
		facility.setCodeZ(fvb.getCodeZ());
		facility.setDevStateZ(fvb.getDevStateZ());
		facility.setCheckType(fvb.getCheckType());
		facility.setCheckUserId(fvb.getCheckUserId());
		facility.setCompleteDate(fvb.getCompleteDate());
		facility.setCreateTime(fvb.getCreateTime());
		facility.setCreateUser(fvb.getCreateUser());
		facility.setDevDesc(fvb.getDevDesc());
		facility.setDevId(fvb.getDevId());
		facility.setDevImei(fvb.getDevImei());
		facility.setDevKind(fvb.getDevKind());
		facility.setDevMac(fvb.getDevMac());
		facility.setDevMarking(fvb.getDevMarking());
		facility.setDevModel(fvb.getDevModel());
		facility.setDevName(fvb.getDevName());
		facility.setDevType(fvb.getDevType());
		facility.setFiberDiscNum(fvb.getFiberDiscNum());
		facility.setFlag(fvb.getFlag());
		facility.setIsbefwell(fvb.getIsbefwell());
		facility.setIsTranslated(fvb.getIsTranslated());
		facility.setZgDevName(fvb.getZgDevName());
		facility.setProId(fvb.getProId());
		facility.setPdevId(fvb.getPdevId());
		facility.setOrgId(fvb.getOrgId());
		facility.setImgType(fvb.getImgType());
		facility.setSite(fvb.getSite());
		facility.setSurveyResult(fvb.getSurveyResult());
		facility.setRoom(fvb.getRoom());
		facility.setDevCode(fvb.getDevCode());
		facility.setDevState(fvb.getDevState());
		facility.setLatitude(fvb.getLatitude());
		facility.setLongitude(fvb.getLongitude());
		if(!"".equals(fvb.getBaiduX()) && fvb.getBaiduX()!=null) {
			facility.setBaiduX(BigDecimal.valueOf(Double.parseDouble(fvb.getBaiduX())));
		}
		if(!"".equals(fvb.getBaiduY()) && fvb.getBaiduY()!=null) {
			facility.setBaiduY(BigDecimal.valueOf(Double.parseDouble(fvb.getBaiduY())));
		}
		
		
		
		Long ret = 0L;
		if((facility.getAreaCode1()==null||"".equals(facility.getAreaCode1()))&&facility.getPdevId()!=null){
			
			Facility parent = facilityMapper.selectByPrimaryKey(facility.getPdevId());
			if(parent!=null)
				facility.setAreaCode1(parent.getAreaCode1());
		}
		if(facility.getDevId()==null){
			Facility faDevNames=facilityMapper.queryRepetitionDevName(facility);
			if(faDevNames !=null ) {
				throw new FrmsException("设施名称【"+facility.getDevName()+"】已存在！");
			}
			
			if(Constants.FACILITTYPE.OBD.toString().equals(facility.getDevType()))
			{
				if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
				addObd(facility);
			}
			else
			{
				facility.setDevCode(siteCodeService.getNewDevCode());
				facility.setDevState("2");//2 新增,dzy修改
				//fl 修改增加百度 X Y 坐标
				if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
				
				facilityMapper.insertSelective(facility);
			}
			ret =facility.getDevId();
			for(FacilityImg img:images){
				img.setDevCode(facility.getDevCode());
				img.setDevId(facility.getDevId());
				facilityImgMapper.insertSelective(img);
			}
			addConnecter(facility);
		}
		else{
			//fl 修改增加百度 X Y 坐标
			if (facility.getLatitude() !=null && !"".equals(facility.getLatitude()) && facility.getLongitude() !=null && !"".equals(facility.getLongitude())) {
				LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
				facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
				facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
			}
			
			//验证设施名并修改光缆段名称
			validateDevName(facility);
			
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
	 * 根据汇聚区和机构id查询
	 * @param orgId
	 * @param areaCode1
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findFacilitiesACode1ByOrgId(Long orgId, String areaCode1) {
		return facilityMapper.findFacilitiesACode1ByOrgId(orgId, areaCode1);
	}
	
	/**
	 * 根据汇聚区和机构id查询设施，返回geojson格式
	 * @param: orgId
	 * @param: areaCode1
	 * @return: Map<String,Object>      
	 * @throws
	 */
	@Override
	public Map<String, Object> findFacilitiesGeoPointByOrgId(Long orgId, String areaCode1) {
		Map<String, Object> featuresMap = new HashMap<String, Object>();
		featuresMap.put("type", "FeatureCollection");
		
		Map<String, Object> crsMap = new HashMap<String, Object>();
		crsMap.put("type", "name");
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("name", "EPSG:4326");
		crsMap.put("properties", properties);
		featuresMap.put("crs", crsMap);
		
		List<Map<String, Object>> facilitys = facilityMapper.findFacilitiesACode1ByOrgId(orgId, areaCode1);
		if(facilitys != null && facilitys.size() > 0) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> facility : facilitys) {
				
				Map<String, Object> featureMap = new HashMap<String, Object>();
				featureMap.put("type", "Feature");
				
				Map<String, Object> geometryMap = new HashMap<String, Object>();
				geometryMap.put("type", "Point");
				BigDecimal[] coordinate = new BigDecimal[2];
				coordinate[0] = (BigDecimal)facility.get("baiduX");
				coordinate[1] = (BigDecimal)facility.get("baiduY");
				geometryMap.put("coordinates", coordinate);
				
				featureMap.put("geometry", geometryMap);
				list.add(featureMap);
			}
			featuresMap.put("features", list);
		}
		
		return featuresMap;
	}

	/**
	 * 
	* @Title: queryForFacilityIds 
	* @Description: 查询设施下的光缆段的另一端设施ID集合 
	* @param @param devId
	* @param @return    
	* @return FacilityAsasideCableBean
	* @author liucanghai 
	* @throws
	* @date 2018年4月16日 下午4:10:42 
	* @version V1.0
	 */
	@Override
	public List<Facility> queryDevListForIds(Long devId) {
		List<Facility> list =new ArrayList<Facility>();
		FacilityAsasideCableBean faBean = facilityMapper.queryForFacilityIds(devId, null);
		if(faBean!=null && faBean.getIds()!=null && !"".equals(faBean.getIds())) {
			if(faBean.getIds()!=null && !"".equals(faBean.getIds())) {
				String str = faBean.getIds()+","+devId.toString();
				list = facilityMapper.queryInfacilityIds(str);
			}
		}else {
			Facility fa = facilityMapper.selectByPrimaryKey(devId);
			list.add(fa);
		}
		if(list!=null && list.size()>0) {
			for(Facility fa :list) {
				if(FacilityDevType.ONU.toString().equals(fa.getDevType())) {
					fa.setDevTypeName("光交箱");
				}else if(FacilityDevType.OTB.toString().equals(fa.getDevType())) {
					fa.setDevTypeName("光终端盒");
				}else if(FacilityDevType.FDB.toString().equals(fa.getDevType())) {
					fa.setDevTypeName("光分纤箱");
				}else if(FacilityDevType.OFS.toString().equals(fa.getDevType())) {
					fa.setDevTypeName("光缆接头");
				}else if(FacilityDevType.ODF.toString().equals(fa.getDevType())) {
					fa.setDevTypeName("机柜");
				}else if(FacilityDevType.SFP.toString().equals(fa.getDevType())) {
					fa.setDevTypeName("分光器");
				}else if(FacilityDevType.ROOM.toString().equals(fa.getDevType())) {
					fa.setDevTypeName("机房");
				}
			}
		}
		return list;
	}

	@Override
	public Facility selectByPrimaryKey(Long devId) {
		return facilityMapper.selectByPrimaryKey(devId);
	}

	/**
	 * 根据设施ID,查询所属设施
	* @Title: selectByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<Facility>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月19日 下午2:25:40 
	* @version V1.0
	 */
	@Override
	public List<Facility> selectByDevId(Long devId) {
		List<Facility> list=new ArrayList<Facility>();
		Facility facility=facilityMapper.selectByPrimaryKey(devId);//本设施
		if (facility!=null && facility.getDevType()!=null && !"".equals(facility.getDevType())&&facility.getDevType().equals("20")) {
			List<Facility> list1 =facilityMapper.selectByDevId(devId);//所属设施 (为机房的时候)
			if (list1.size()>0 && list1!=null) {
				list.addAll(list1);
			}
		}
		 list.add(facility);
		 return list;
	}
	
	/**
	 * <p>Title: findFacilitysToBdMap</p>   
	 * 按位置浏览-高级查询-根据条件返回设备信息   
	 * @param areaCode
	 * @param areaCode1
	 * @param devType
	 * @param devName
	 * @param devCode
	 * @param orgId
	 * @return   
	 */
	public List<Map<String, Object>> findFacilitysToBdMap(String areaCode, String areaCode1, String devType,
			String devName, String devCode, Long orgId){
		Map<String, Object> params = new HashMap<String, Object>();
		if(org.apache.commons.lang3.StringUtils.isNotBlank(areaCode1)) {
			params.put("areaCode1", areaCode1);
		}else {
			if(org.apache.commons.lang3.StringUtils.isNotBlank(areaCode)) {
				params.put("areaCode", areaCode);
			}
		}
		if(org.apache.commons.lang3.StringUtils.isNotBlank(devType)) {
			params.put("devType", devType);
		}
		if(org.apache.commons.lang3.StringUtils.isNotBlank(devCode)) {
			params.put("devCode", "%"+devCode+"%");
		}
		if(org.apache.commons.lang3.StringUtils.isNotBlank(devName)) {
			params.put("devName", "%"+devName+"%");
		}
		if(orgId != null && orgId != 0) {
			params.put("orgId", orgId);
		}
		
		return facilityMapper.findFacilitysToBdMap(params);
	}

	/**
	 * 
	* @Title: workorderSaveJumper 
	* @Description: 光路调度跳纤 
	* @param @param jumperInfo
	* @param @param request
	* @param @param user
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年4月24日 上午9:23:05 
	* @version V1.0
	 */
	@Override
	public int workorderSaveJumper(WorkorderJumperInfo jumperInfo, HttpServletRequest request, Users user) {
		int ret =0;
		String code;
		String acode,zcode;
		code = jumperInfo.getCode();
		acode = jumperInfo.getAcode();
		zcode = jumperInfo.getZcode();
		//fl 修改,添加实施方案存储
		WorkorderRoutes workorderRoutes=workorderRoutesMapper.selectByPrimaryKey(jumperInfo.getDesignroutesId());
		if (workorderRoutes !=null) {
			WorkorderFiberdiscabindA workorderFiberdiscabindA=workorderFiberdiscabindAMapper.queryByRoutsIds(workorderRoutes.getDesignroutesId(), workorderRoutes.getAdevId());
			WorkorderFiberdiscabindZ workorderFiberdiscabindZ=workorderFiberdiscabindZMapper.queryByRoutsIds(workorderRoutes.getDesignroutesId(), workorderRoutes.getAdevId());
			WorkorderDesign record = new WorkorderDesign();
			if (workorderFiberdiscabindZ !=null && workorderFiberdiscabindA !=null) {
				Fiberdisc fiberdiscA=fiberdiscMapper.selectByPrimaryKey(workorderFiberdiscabindA.getFiberDiscId());
				Fiberdisc fiberdiscZ=fiberdiscMapper.selectByPrimaryKey(workorderFiberdiscabindA.getFiberDiscId());
				if (fiberdiscA !=null && fiberdiscZ !=null) {
					if ((code.equals(fiberdiscA.getDiscCode())&& zcode.equals(fiberdiscZ.getDiscCode()))||(code.equals(fiberdiscZ.getDiscCode())&& zcode.equals(fiberdiscA.getDiscCode()))) {
						record.setIsused("1");
						record.setDesignId(workorderRoutes.getDesignplanId());
						workorderDesignMapper.updateByPrimaryKeySelective(record);
					}else {
						record.setIsused("0");
						record.setDesignId(workorderRoutes.getDesignplanId());
						workorderDesignMapper.updateByPrimaryKeySelective(record);
					}
				}
			}else if(workorderFiberdiscabindZ ==null && workorderFiberdiscabindA !=null){
				record.setIsused("1");
				record.setDesignId(workorderRoutes.getDesignplanId());
				workorderDesignMapper.updateByPrimaryKeySelective(record);
			}else {
				record.setIsused("0");
				record.setDesignId(workorderRoutes.getDesignplanId());
				workorderDesignMapper.updateByPrimaryKeySelective(record);
			}
		}
		
		if(code!=null && !"".equals(code)) {
			Fiberdisc fib = fiberdiscMapper.queryByPort(code);
			if(fib==null) {
				throw new FrmsException("A端目标跳纤点不存在");
			}else {
				jumperInfo.setDevId(fib.getDevId());
			}
		}else {
			throw new FrmsException("请传入A端跳纤点！");
		}
		
		if(zcode!=null && !"".equals(zcode)) {
			Fiberdisc fib = fiberdiscMapper.queryByPort(jumperInfo.getZcode());
			if(fib==null) {
				throw new FrmsException("Z端目标跳纤点不存在");
			}else {
				jumperInfo.setZdevId(fib.getDevId());
			}
		}else {
			throw new FrmsException("请传入Z端跳纤点！");
		}
		
		if(jumperInfo.getDevId()==null||code==null||"".equals(code.trim()))
		{
			throw new FrmsException("当前端子信息错误");
		}
		if (acode!=null&&acode.equals(zcode))
		{
			throw new FrmsException("两条跳纤不能相同。");
		}
		if(zcode!=null && !"".equals(zcode)) {//如果不是尾纤悬空
			ret +=updateForDecName(jumperInfo.getUserId(),code,zcode);
		}else {
			ret +=updateForDecName(jumperInfo.getUserId(),code,null);//是尾纤悬空或者跳到未知
		}
		ret +=linesService.workorderAddJumper(jumperInfo,request,user);
//		ret +=addJumper(jumperInfo.getAlinesId(),jumperInfo.getDevId(),code,jumperInfo.getAdevId(),acode,jumperInfo.getSrvName(),jumperInfo.getOrgId(),jumperInfo.getAunknownPointName(),jumperInfo.getUserId());
//		ret +=addJumper(jumperInfo.getZlinesId(),jumperInfo.getDevId(),code,jumperInfo.getZdevId(),zcode,jumperInfo.getSrvName(),jumperInfo.getOrgId(),jumperInfo.getZunknownPointName(),jumperInfo.getUserId());
//		if(ret>0) {
//			Facility fa = facilityMapper.selectByPrimaryKey(jumperInfo.getDevId());
//			if(FacilityDevType.ODF.toString().equals(fa.getDevType())) {//如果是机柜
//				String[] str = jumperInfo.getCode().split("-");
//				String strs=str[0]+"-"+str[1]+"-"+str[2];
//				Discinfo discInfo = discinfoMapper.selectByDiscCode(strs);
//				DeviceDiscinfoEntity deinfo = deviceDiscinfoEntityMapper.selectByPrimaryKey(discInfo.getDiscId());
//				jumperInfo.setAdevData(deinfo.getLastReportData());
//			}
//			Facility fz = facilityMapper.selectByPrimaryKey(jumperInfo.getZdevId());
//			if(FacilityDevType.ODF.toString().equals(fz.getDevType())) {//如果是机柜
//				String[] str = jumperInfo.getCode().split("-");
//				String strs=str[0]+"-"+str[1]+"-"+str[2];
//				Discinfo discInfo = discinfoMapper.selectByDiscCode(strs);
//				DeviceDiscinfoEntity deinfo = deviceDiscinfoEntityMapper.selectByPrimaryKey(discInfo.getDiscId());
//				jumperInfo.setZdevData(deinfo.getLastReportData());
//			}
//			ret+=insertImpplans(jumperInfo,user);
//		}
		workorderRoutes.setIsImple("1");
		workorderRoutesMapper.updateByPrimaryKeySelective(workorderRoutes);
		WorkorderImplePlans plan = new WorkorderImplePlans();
		handles(plan,jumperInfo,user);//处理数据
		plan.setCreateTime(new Date());
		plan.setCreateUser(user.getUserId());
		
		workorderImplePlansMapper.insertSelective(plan);//添加
		
		return ret;
	}
	
	private void handles(WorkorderImplePlans plan, WorkorderJumperInfo jumperInfo, Users user) {
		plan.setOrgId(user.getOrgId());
		plan.setImpleBack(jumperInfo.getImpleBack());//实施反馈信息
		plan.setAdevData(jumperInfo.getAdevData());//A端设备检测数据
		plan.setZdevData(jumperInfo.getZdevData());//Z端设备检测数据
		LinesBean line = linesService.queryByAcodeAndZcode("02",jumperInfo.getCode(),jumperInfo.getZcode());
		if(line != null) {
			plan.setLineId(line.getLineId());//跳纤ID
		}
		plan.setDesignroutesId(jumperInfo.getDesignroutesId());//调度工单光路ID
		WorkorderRoutes workRout = workorderRoutesMapper.selectByPrimaryKey(jumperInfo.getDesignroutesId());
		if(workRout != null) {
			plan.setAdevId(workRout.getAdevId());//A端设施ID
			plan.setZdevId(workRout.getZdevId());//Z端设施ID
			plan.setDesignId(workRout.getDesignplanId());//调度工单ID
		}
		Fiberdisc fiberA = fiberdiscMapper.queryByPort(jumperInfo.getCode());
		Fiberdisc fiberZ = fiberdiscMapper.queryByPort(jumperInfo.getZcode());
		if(fiberA != null && fiberZ != null) {
			plan.setAfiberDiscId(fiberA.getFiberDiscId());//A端端子ID
			plan.setZfiberDiscId(fiberZ.getFiberDiscId());//Z端端子ID
			//判断 占用情况是否一致 0 不一致 1 一致
			if(jumperInfo.getAdevData() != null || jumperInfo.getZdevData() != null) {
				int flag = 1;
				if(jumperInfo.getAdevData() != null) {
					char strA=jumperInfo.getAdevData().charAt(fiberA.getDiscColNo().intValue()-1);
					if(strA!=fiberA.getIsOccup().charAt(0)) {
						flag = 0;
						plan.setIsOccupySame("0");//占用情况是否一致 0 不一致 1 一致
					}
				}
				if(jumperInfo.getZdevData() != null) {
					char strZ=jumperInfo.getZdevData().charAt(fiberZ.getDiscColNo().intValue()-1);
					if(flag == 1) {
						if(strZ!=fiberZ.getIsOccup().charAt(0)) {
							plan.setIsOccupySame("0");//占用情况是否一致 0 不一致 1 一致
						}
					}
				}
			}
		}
		plan.setPortA(jumperInfo.getCode());//A端端子编码
		plan.setPortZ(jumperInfo.getZcode());//Z端端子编码
		//判断 实施方案是否一致 0 否 1 是
		WorkorderFiberdiscabindA workFiberA = workorderFiberdiscabindAMapper.queryByRoutsIds(jumperInfo.getDesignroutesId(), workRout.getAdevId());
		WorkorderFiberdiscabindZ workFiberZ = workorderFiberdiscabindZMapper.queryByRoutsIds(jumperInfo.getDesignroutesId(), workRout.getZdevId());
		if(workFiberA != null && workFiberA.getFiberDiscId() != null 
				&& workFiberZ != null && workFiberZ.getFiberDiscId() != null
				&& fiberA.getFiberDiscId() != null && fiberZ.getFiberDiscId() !=null) {
			if(workFiberA.getFiberDiscId().longValue()==fiberA.getFiberDiscId().longValue() && workFiberZ.getFiberDiscId().longValue()==fiberZ.getFiberDiscId().longValue()) {
				plan.setIsImpleSame("1");//实施方案是否一致 0 否 1 是
			}else {
				plan.setIsImpleSame("0");
			}
		}
	}
	/**
	 * 
	* @Title: insertImpplans 
	* @Description: 插入实时施工方案 
	* @param @param jumperInfo
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年4月24日 上午10:29:33 
	* @version V1.0
	 */
	public int insertImpplans(WorkorderJumperInfo jumperInfo,Users user) {
		int reg=0;
		if(jumperInfo.getAdevData()==null || "".equals(jumperInfo.getAdevData())) {
			throw new FrmsException("A端设备检测数据不能为空！");
		}
		if(jumperInfo.getZdevData()==null || "".equals(jumperInfo.getZdevData())) {
			throw new FrmsException("Z端设备检测数据不能为空！");
		}
		WorkorderImplePlans plan=new WorkorderImplePlans();
//		if(jumperInfo.getPlansId()==null) {//添加
		plan.setCreateTime(new Date());
		plan.setCreateUser(user.getUserId());
		plan.setOrgId(user.getOrgId());
		plan.setImpleBack(jumperInfo.getImpleBack());//实施反馈信息
		plan.setAdevData(jumperInfo.getAdevData());//A端设备检测数据
		plan.setZdevData(jumperInfo.getZdevData());//Z端设备检测数据
		Lines line = linesMapper.queryLineType02(jumperInfo.getCode());
		plan.setLineId(line.getLineId());//跳纤ID
		plan.setDesignroutesId(jumperInfo.getDesignroutesId());//调度工单光路ID
		WorkorderRoutes workRout = workorderRoutesMapper.selectByPrimaryKey(jumperInfo.getDesignroutesId());
		plan.setAdevId(workRout.getAdevId());//A端设施ID
		plan.setZdevId(workRout.getZdevId());//Z端设施ID
		plan.setDesignId(workRout.getDesignplanId());//调度工单ID
		Fiberdisc fiberA = fiberdiscMapper.queryByPort(jumperInfo.getCode());
		Fiberdisc fiberZ = fiberdiscMapper.queryByPort(jumperInfo.getZcode());
		plan.setAfiberDiscId(fiberA.getFiberDiscId());//A端端子ID
		plan.setZfiberDiscId(fiberZ.getFiberDiscId());//Z端端子ID
		plan.setPortA(jumperInfo.getCode());//A端端子编码
		plan.setPortZ(jumperInfo.getZcode());//Z端端子编码
		//判断 实施方案是否一致 0 否 1 是
		WorkorderFiberdiscabindA workFiberA = workorderFiberdiscabindAMapper.queryByRoutsIds(jumperInfo.getDesignroutesId(), workRout.getAdevId());
		WorkorderFiberdiscabindZ workFiberZ = workorderFiberdiscabindZMapper.queryByRoutsIds(jumperInfo.getDesignroutesId(), workRout.getZdevId());
		if(workFiberA.getFiberDiscId().longValue()==fiberA.getFiberDiscId().longValue() && 
				workFiberZ.getFiberDiscId().longValue()==fiberZ.getFiberDiscId().longValue()) {
			plan.setIsImpleSame("1");//实施方案是否一致 0 否 1 是
		}else {
			plan.setIsImpleSame("0");
		}
		//判断 占用情况是否一致 0 不一致 1 一致
		char strA=jumperInfo.getAdevData().charAt(fiberA.getDiscColNo().intValue()-1);
		char strZ=jumperInfo.getZdevData().charAt(fiberZ.getDiscColNo().intValue()-1);
		if(strA==fiberA.getIsOccup().charAt(0) && strZ==fiberZ.getIsOccup().charAt(0)) {
			plan.setIsOccupySame("1");//占用情况是否一致 0 不一致 1 一致
		}else {
			plan.setIsOccupySame("0");
		}
		reg=workorderImplePlansMapper.insertSelective(plan);//插入
//		}
//		else {
//			jumperInfo.getPlan().setLastModifyTime(new Date());
//			jumperInfo.getPlan().setLastModifyUser(user.getUserId());
//			//判断 实施方案是否一致 0 否 1 是
//			
//			WorkorderFiberdiscabindA workFiberA = workorderFiberdiscabindAMapper.queryByRoutsIds(jumperInfo.getPlan().getDesignroutesId(), jumperInfo.getPlan().getAdevId());
//			WorkorderFiberdiscabindZ workFiberZ = workorderFiberdiscabindZMapper.queryByRoutsIds(jumperInfo.getPlan().getDesignroutesId(), jumperInfo.getPlan().getZdevId());
//			fiberdiscMapper.queryByPort(jumperInfo.getPlan().getPortA());
//			if(workFiberA.getFiberDiscId().longValue()==fiberA.getFiberDiscId().longValue() && 
//					workFiberZ.getFiberDiscId().longValue()==fiberZ.getFiberDiscId().longValue()) {
//				plan.setIsImpleSame("1");//实施方案是否一致 0 否 1 是
//			}else {
//				plan.setIsImpleSame("0");
//			}
//			//判断 占用情况是否一致 0 不一致 1 一致
//			char strA=jumperInfo.getPlan().getAdevData().charAt(fiberA.getDiscColNo().intValue()-1);
//			char strZ=jumperInfo.getPlan().getZdevData().charAt(fiberZ.getDiscColNo().intValue()-1);
//			if(strA==fiberA.getIsOccup().charAt(0) && strZ==fiberZ.getIsOccup().charAt(0)) {
//				plan.setIsOccupySame("1");//占用情况是否一致 0 不一致 1 一致
//			}else {
//				plan.setIsOccupySame("0");
//			}
//			
//			reg=workorderImplePlansMapper.updateByPrimaryKeySelective(jumperInfo.getPlan());
//		}
		return reg;
	}

	/**
	 * 
	* @Title: queryDevRoomByODF 
	* @Description: 根据机柜ID查询所属机房下所有机柜信息 
	* @param @param session
	* @param @param userId
	* @param @param devId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月26日 上午10:37:25 
	* @version V1.0
	 */
	@Override
	public FacilityRoom queryDevRoomByODF(Long devId) {
		FacilityRoom room=new FacilityRoom();
		List<Facility> faList=null;
		Facility fa = facilityMapper.selectByPrimaryKey(devId);
		if(fa.getPdevId()!=null) {
			Facility faRoom = facilityMapper.selectByPrimaryKey(fa.getPdevId());//机房
			room.setDevIdRoom(fa.getPdevId());
			room.setDevCodeRoom(faRoom.getDevCode());//机房
			room.setDevNameRoom(faRoom.getDevName());
			room.setDevTypeRoom(faRoom.getDevType());
			faList=facilityMapper.selectByPdevId(fa.getPdevId());//机柜
			room.setFaList(faList);
		}else {
			faList=new  ArrayList<Facility>();
			faList.add(fa);
			room.setFaList(faList);
		}
		return room;
	}
	
	public static void main(String[] args) {
		String ss="GJ3574-A-01-04";
		String[] sss = ss.split("-");
		String str=sss[0]+"-"+sss[1]+"-"+sss[2];
		System.out.println(str);
	}

	/**
	 * 
	 * @Title: queryListByContentAndBindCod
	 * @Description: 查询已绑定中控器的设施，并且只有光交箱，光终端盒，光分纤箱，机房
	 * @param @param content
	 * @param @param orgId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY (FL修改20180628)
	 * @throws
	 * @date 2018年4月27日 下午1:59:44
	 * @version V1.0
	 */
	@Override
	public List<FacilityBean> queryListByContentAndBindCod(String content, String areaCode1,String areaCode2, Long orgId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("content",content);
		if (areaCode1!=null && !"".equals(areaCode1) && areaCode2!=null && !"".equals(areaCode2)) {
			areaCode1= areaCode2;
		}
		if (areaCode1!=null && !"".equals(areaCode1) && (areaCode2==null || "".equals(areaCode2))) {
			String code = areaCode1;
			map.put("code",code);
			areaCode1="";
		}
		map.put("areaCode1",areaCode1);
		map.put("orgId", orgId);
		return facilityMapper.queryListByContentAndBindCod(map);
	}
	
	/**
	 * 根据设施ID查询机房中机柜列表
	 */
	@Override
	public List<Map<String, Object>> findFacilityByPdevId(Long devId) {
		return facilityMapper.findFacilityByPdevId(devId);
	}

	/**
	 * 
	 * 
	 * @Title: saveFacilityImages
	 * @Description: 保存图片
	 * @param @param request
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月1日 下午1:28:04
	 * @version V1.0
	 */
	@Override
	public List<String> saveFacilityImages(HttpServletRequest request, Users user) {
		List<String> list = new ArrayList<String>();
		if(request instanceof MultipartHttpServletRequest)
		{
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			List<MultipartFile> files = req.getFiles("pic");
			if (files!=null&&files.size()>0) {
				for(int index = 0;index<files.size();index++)
				{
					 String image = UploadUtil.uploadFile(files.get(index), req,user.getOrgId(), "01");//"01"添加设施图片
					 list.add(image);
				}
			 }
		}
		return list;
	}

	/**
	 * 删除图片文件和数据
	* @Function: FacilityService.java
	* @return：返回结果描述
	* @throws：异常描述
	* @version: v1.0.0
	* @author: FL
	* @date: 2018年10月9日 下午3:22:38
	 */
	@Override
	public int deleteImage(HttpServletRequest request,List<String> imageUrlList, Long devId) {
		int req = 0;
		Map<String,Object> map = null;
		if(imageUrlList != null && imageUrlList.size()>0) {
			for (String imageUrl : imageUrlList) {
				if(imageUrl != null && !"".equals(imageUrl.trim())) {
					map = new HashMap<String,Object>();
					map.put("devId", devId);
					map.put("imgUrl", imageUrl);
					req = facilityImgMapper.deleteByDevIdAndUrl(map);//根据devId和imgUrl删除端子图片信息
					req+= UploadUtil.deleteImage(imageUrl, request,"01");//删除文件
				}
			}
		}
		return req;
	}

	/**
	 * 装维保存设施(非光交箱)图片接口
	* @Title: saveFacilityImgs 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param picList
	* @param @return    入参
	* @return Long    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月1日 上午10:18:12 
	* @version V1.0
	 */
	@Override
	public Long saveFacilityImgs(FacilityVoBeanXY fvb,HttpServletRequest request) {

		Facility facility =  new Facility();
		facility.setLastModifyUser(fvb.getLastModifyUser());
		facility.setLastModifyTime(fvb.getLastModifyTime());
		facility.setDevAddr(fvb.getDevAddr());
		facility.setAreaCode1(fvb.getAreaCode1());
		facility.setAreaCode2(fvb.getAreaCode2());
		facility.setCodeA(fvb.getCodeA());
		facility.setDevStateA(fvb.getDevStateA());
		facility.setCodeZ(fvb.getCodeZ());
		facility.setDevStateZ(fvb.getDevStateZ());
		facility.setCheckType(fvb.getCheckType());
		facility.setCheckUserId(fvb.getCheckUserId());
		facility.setCompleteDate(fvb.getCompleteDate());
		facility.setCreateTime(fvb.getCreateTime());
		facility.setCreateUser(fvb.getCreateUser());
		facility.setDevDesc(fvb.getDevDesc());
		facility.setDevId(fvb.getDevId());
		facility.setDevImei(fvb.getDevImei());
		facility.setDevKind(fvb.getDevKind());
		facility.setDevMac(fvb.getDevMac());
		facility.setDevMarking(fvb.getDevMarking());
		facility.setDevModel(fvb.getDevModel());
		facility.setDevName(fvb.getDevName());
		facility.setDevType(fvb.getDevType());
		facility.setFiberDiscNum(fvb.getFiberDiscNum());
		facility.setFlag(fvb.getFlag());
		facility.setIsbefwell(fvb.getIsbefwell());
		facility.setIsTranslated(fvb.getIsTranslated());
		facility.setZgDevName(fvb.getZgDevName());
		facility.setProId(fvb.getProId());
		facility.setPdevId(fvb.getPdevId());
		facility.setOrgId(fvb.getOrgId());
		facility.setImgType(fvb.getImgType());
		facility.setSite(fvb.getSite());
		facility.setSurveyResult(fvb.getSurveyResult());
		facility.setRoom(fvb.getRoom());
		facility.setDevCode(fvb.getDevCode());
		facility.setDevState(fvb.getDevState());
		facility.setLatitude(fvb.getLatitude());
		facility.setLongitude(fvb.getLongitude());
		if(!"".equals(fvb.getBaiduX()) && fvb.getBaiduX()!=null) {
			facility.setBaiduX(BigDecimal.valueOf(Double.parseDouble(fvb.getBaiduX())));
		}
		if(!"".equals(fvb.getBaiduY()) && fvb.getBaiduY()!=null) {
			facility.setBaiduY(BigDecimal.valueOf(Double.parseDouble(fvb.getBaiduY())));
		}
		
		
		
		Long ret = 0L;
		if((facility.getAreaCode1()==null||"".equals(facility.getAreaCode1()))&&facility.getPdevId()!=null){
			
			Facility parent = facilityMapper.selectByPrimaryKey(facility.getPdevId());
			if(parent!=null)
				facility.setAreaCode1(parent.getAreaCode1());
		}
		if(facility.getDevId()==null){
			if(facility.getDevType() != null && "05".equals(facility.getDevType())) {
				Facility fpn = facilityMapper.queryByPdevIdAndDevName(facility.getPdevId(),facility.getDevName());
				if(fpn != null) {
					throw new FrmsException("该名称在本机房已存在！");
				}
			}else {
				Facility faDevNames=facilityMapper.queryIamRepetitionDevName(facility);
				//查询名称是否重复,过滤:同组织机构下不为新增删除且名称相同,则提示信息
				if(faDevNames !=null ) {
					throw new FrmsException("设施名称【"+facility.getDevName()+"】已存在！");
				}
			}
			
			if(Constants.FACILITTYPE.OBD.toString().equals(facility.getDevType()))
			{
				if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
				addObd(facility);
			}
			else
			{
				facility.setDevCode(siteCodeService.getNewDevCode());
				facility.setDevState("2");//2 新增,dzy修改
				//fl 修改增加百度 X Y 坐标
				if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
				
				facilityMapper.insertSelective(facility);
			}
			ret =facility.getDevId();
			FacilityImg img = new FacilityImg();
			if ( fvb.getPicList()!=null && fvb.getPicList().size()>0) {
				for (String url : fvb.getPicList()) {
					img.setDevCode(facility.getDevCode());
					img.setDevId(facility.getDevId());
					img.setImgUrl(url);
					img.setFlag("1");
					img.setPhotoTime(new Date());
					img.setImgTypes(fvb.getImgType());
					facilityImgMapper.insertSelective(img);
				}
			}
			addConnecter(facility);
		}
		else{
			/*验证devState*/
			
			Facility f = facilityMapper.selectByPrimaryKey(facility.getDevId());
			if (f !=null) {
				if((fvb.getDevState() == null || "".equals(fvb.getDevState()))) {
					fvb.setDevState(f.getDevState());
				}else {
					if (!fvb.getDevState().equals(f.getDevState())) {
//						if (f.getZgDevName()!=null && !"".equals(f.getZgDevName())) {
//							if (fvb.getDevState().equals("1")) {
//								if (!f.getZgDevName().equals(fvb.getDevName())) {
//									throw new FrmsException("必须与资管名称相同!");
//								}
//							}
//							if (fvb.getDevState().equals("3")) {
//								if (f.getZgDevName().equals(fvb.getDevName())) {
//									throw new FrmsException("必须与资管名称不同!");
//								}
//							}
//						}
						if (fvb.getDevState().equals("4") || fvb.getDevState().equals("5")) {
							List<FiberdiscGroup> list1 = fiberdiscGroupMapper.selectByDevId(fvb.getDevId());//分组
							List<DeviceEntity> list2 = deviceEntityMapper.selectListByDevId(fvb.getDevId());//中控器
							List<DeviceDiscinfoEntity>list3=deviceDiscinfoEntityMapper.selectByDevId(fvb.getDevId());//端子控制器
							if (list1!=null && list1.size()>0) {
								throw new FrmsException("请先删除该设施下分组!");
							}
							if (list2!=null && list2.size()>0) {
								throw new FrmsException("请先解除该设施绑定的中控器!");
							}
							if (list3!=null && list3.size()>0) {
								throw new FrmsException("请先解除该设施绑定的端子控制器!");
							}
							List<CableSectionWellBean> list = cableSectionMapper.queryopdCableSectionByDevId(fvb.getDevId());
							if (list!=null && list.size()>0) {
								for (int i = 0; i < list.size(); i++) {
									cableSectionMapper.deleteByPrimaryKey(list.get(i).getSectId());
								}
							}
						}
					}
				}
				
			}
			
			//fl 修改增加百度 X Y 坐标
			if (facility.getLatitude() !=null && !"".equals(facility.getLatitude()) && facility.getLongitude() !=null && !"".equals(facility.getLongitude())) {
				LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
				facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
				facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
			}
			
			//验证设施名并修改光缆段名称
			validateDevName(facility);
			
			facilityMapper.updateByPrimaryKeySelective(facility);
			List<FacilityImg> list = facilityImgMapper.selectByDevId(fvb.getDevId());//数据库路径集合
			List<String> imgList=null;
			if ( fvb.getPicList()!=null) {
				 imgList = fvb.getPicList();//传入路径
			}
			List<String> imgUrlList = new ArrayList<String>();
			
			if (imgList!=null && imgList.size()>0) {
				int t=0;
				for (String url : imgList) {
					if (list!=null && list.size()>0) {
						for (int i = 0; i < list.size(); i++) {
							String sjkimgUrl=list.get(i).getImgUrl();//数据路径
							if (url.equals(sjkimgUrl)) {
								++t;
							}
						}
						if (t==0) {
							imgUrlList.add(url);//去添加
						}
						t=0;
					}else {
						imgUrlList.add(url);
					}
				}
			}
			if (imgUrlList!=null && imgUrlList.size()>0) {
				for (String url : imgUrlList) {
					FacilityImg img = new FacilityImg();
					img.setDevCode(facility.getDevCode());
					img.setDevId(facility.getDevId());
					img.setImgUrl(url);
					img.setFlag("1");
					img.setPhotoTime(new Date());
					img.setImgTypes(fvb.getImgType());
					facilityImgMapper.insertSelective(img);
				}
			}
			ret = facility.getDevId();
		}
		return ret;
	
	}

	/**
	 * 根据设施ID,查询设施信息
	* @Title: queryFacilityByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return FacilityVoBeanXY    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 上午9:13:15 
	* @version V1.0
	 */
	@Override
	public FacilityVoOpd queryFacilityByDevId(Long devId) {
		FacilityVoOpd facilityVoOpd = new FacilityVoOpd();
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if (f.getLastModifyTime() !=null) {
			facilityVoOpd.setTime(f.getLastModifyTime());
		}else {
			facilityVoOpd.setTime(f.getCreateTime());
		}
		facilityVoOpd.setDevId(f.getDevId());
		facilityVoOpd.setDevName(f.getDevName());
		facilityVoOpd.setDevType(f.getDevType());
		facilityVoOpd.setDevCode(f.getDevCode());
		facilityVoOpd.setDevState(f.getDevState());
		facilityVoOpd.setLatitude(f.getLatitude());
		facilityVoOpd.setLongitude(f.getLongitude());
		facilityVoOpd.setDevAddr(f.getDevAddr());
		Areas area = areasMapper.selectByPrimaryKey(f.getAreaCode());
		if (area!=null && area.getAreaName()!=null && !"".equals(area.getAreaName())) {
			facilityVoOpd.setAreaName(area.getAreaName());
		}
		if(f.getBaiduX()!=null) {
			facilityVoOpd.setBaiduX(f.getBaiduX()+"");
		}
		if( f.getBaiduY()!=null) {
			facilityVoOpd.setBaiduY(f.getBaiduY()+"");
		}
		return facilityVoOpd;
	}

	/**
	 * 查询机柜列表
	* @Title: queryopdFacilityOdf 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param pb
	* @param @return    入参
	* @return List<Facility>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 上午10:42:22 
	* @version V1.0
	 */
	@Override
	public List<FacilityOdf> queryopdFacilityOdf(Long devId, PageBean pb) {
		return facilityMapper.queryopdFacilityOdf( devId, pb);
	}

	/**
	 * 根据设施ID状态,删除设施
	* @Title: facilityopdDelete 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param devState
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 上午11:08:15 
	* @version V1.0
	 */
	@Override
	public int facilityopdDelete(Long devId, String devState) {
		int num=0;
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if (f==null) {
			throw new FrmsException("未查询到该设施!");
		}else {
			if (devState.equals("4")) {
				f.setDevState("4");
				num=facilityMapper.updateByPrimaryKeySelective(f);
			}
			if (devState.equals("5")) {
				f.setDevState("5");
				num=facilityMapper.updateByPrimaryKeySelective(f);
			}
			List<FiberdiscGroup> list1 = fiberdiscGroupMapper.selectByDevId(devId);//分组
			List<DeviceEntity> list2 = deviceEntityMapper.selectListByDevId(devId);//中控器
			List<DeviceDiscinfoEntity>list3=deviceDiscinfoEntityMapper.selectByDevId(devId);//端子控制器
			if (list1!=null && list1.size()>0) {
				throw new FrmsException("请先删除该设施下分组!");
			}
			if (list2!=null && list2.size()>0) {
				throw new FrmsException("请先解除该设施绑定的中控器!");
			}
			if (list3!=null && list3.size()>0) {
				throw new FrmsException("请先解除该设施绑定的端子控制器!");
			}
			List<CableSectionWellBean> list = cableSectionMapper.queryopdCableSectionByDevId(devId);
			if (list!=null && list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					cableSectionMapper.deleteByPrimaryKey(list.get(i).getSectId());
				}
			}
		}
		return num;
	}

	/**
	 * 设施修改保存
	* @Title: saveopdFacilityImgs 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param request
	* @param @return    入参
	* @return Long    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 下午1:20:32 
	* @version V1.0
	 */
	@Override
	public Long saveopdFacilityImgs(FacilityVoBeanXY fvb, HttpServletRequest request) {


		Facility facility =  new Facility();
		facility.setLastModifyUser(fvb.getLastModifyUser());
		facility.setLastModifyTime(fvb.getLastModifyTime());
		facility.setDevAddr(fvb.getDevAddr());
		facility.setAreaCode1(fvb.getAreaCode1());
		facility.setAreaCode2(fvb.getAreaCode2());
		facility.setCodeA(fvb.getCodeA());
		facility.setDevStateA(fvb.getDevStateA());
		facility.setCodeZ(fvb.getCodeZ());
		facility.setDevStateZ(fvb.getDevStateZ());
		facility.setCheckType(fvb.getCheckType());
		facility.setCheckUserId(fvb.getCheckUserId());
		facility.setCompleteDate(fvb.getCompleteDate());
		facility.setCreateTime(fvb.getCreateTime());
		facility.setCreateUser(fvb.getCreateUser());
		facility.setDevDesc(fvb.getDevDesc());
		facility.setDevId(fvb.getDevId());
		facility.setDevImei(fvb.getDevImei());
		facility.setDevKind(fvb.getDevKind());
		facility.setDevMac(fvb.getDevMac());
		facility.setDevMarking(fvb.getDevMarking());
		facility.setDevModel(fvb.getDevModel());
		facility.setDevName(fvb.getDevName());
		facility.setDevType(fvb.getDevType());
		facility.setFiberDiscNum(fvb.getFiberDiscNum());
		facility.setFlag(fvb.getFlag());
		facility.setIsbefwell(fvb.getIsbefwell());
		facility.setIsTranslated(fvb.getIsTranslated());
		facility.setZgDevName(fvb.getZgDevName());
		facility.setProId(fvb.getProId());
		facility.setPdevId(fvb.getPdevId());
		facility.setOrgId(fvb.getOrgId());
		facility.setImgType(fvb.getImgType());
		facility.setSite(fvb.getSite());
		facility.setSurveyResult(fvb.getSurveyResult());
		facility.setRoom(fvb.getRoom());
		facility.setDevCode(fvb.getDevCode());
		facility.setDevState(fvb.getDevState());
		facility.setLatitude(fvb.getLatitude());
		facility.setLongitude(fvb.getLongitude());
		if(!"".equals(fvb.getBaiduX()) && fvb.getBaiduX()!=null) {
			facility.setBaiduX(BigDecimal.valueOf(Double.parseDouble(fvb.getBaiduX())));
		}
		if(!"".equals(fvb.getBaiduY()) && fvb.getBaiduY()!=null) {
			facility.setBaiduY(BigDecimal.valueOf(Double.parseDouble(fvb.getBaiduY())));
		}
		Long ret = 0L;
		if((facility.getAreaCode1()==null||"".equals(facility.getAreaCode1()))&&facility.getPdevId()!=null){
			
			Facility parent = facilityMapper.selectByPrimaryKey(facility.getPdevId());
			if(parent!=null)
				facility.setAreaCode1(parent.getAreaCode1());
		}
		if(facility.getDevId()==null){
			Facility faDevNames=facilityMapper.queryRepetitionDevName(facility);
			if(faDevNames !=null ) {
				throw new FrmsException("设施名称【"+facility.getDevName()+"】已存在！");
			}
			
			if(Constants.FACILITTYPE.OBD.toString().equals(facility.getDevType()))
			{
				if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
				addObd(facility);
			}
			else
			{
				facility.setDevCode(siteCodeService.getNewDevCode());
				facility.setDevState("2");//2 新增,dzy修改
				//fl 修改增加百度 X Y 坐标
				if ((facility.getLatitude() !=null && !"".equals(facility.getLatitude())) && ("".equals(fvb.getBaiduX()) || fvb.getBaiduX()==null)) {
					LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
					facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
					facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
				}
				
				facilityMapper.insertSelective(facility);
			}
			ret =facility.getDevId();
			FacilityImg img = new FacilityImg();
			if ( fvb.getPicList()!=null && fvb.getPicList().size()>0) {
				for (String url : fvb.getPicList()) {
					img.setDevCode(facility.getDevCode());
					img.setDevId(facility.getDevId());
					img.setImgUrl(url);
					img.setFlag("1");
					img.setPhotoTime(new Date());
					img.setImgTypes(fvb.getImgType());
					facilityImgMapper.insertSelective(img);
				}
			}
			addConnecter(facility);
		}
		else{
			/*验证devState*/
			Facility f = facilityMapper.selectByPrimaryKey(facility.getDevId());
			if (f !=null) {
				if((fvb.getDevState() == null || "".equals(fvb.getDevState()))) {
					fvb.setDevState(f.getDevState());
				}else {
					if (!fvb.getDevState().equals(f.getDevState())) {
//						if (f.getZgDevName()!=null && !"".equals(f.getZgDevName())) {
//							if (fvb.getDevState().equals("1")) {
//								if (!f.getZgDevName().equals(fvb.getDevName())) {
//									throw new FrmsException("必须与资管名称相同!");
//								}
//							}
//							if (fvb.getDevState().equals("3")) {
//								if (f.getZgDevName().equals(fvb.getDevName())) {
//									throw new FrmsException("必须与资管名称不同!");
//								}
//							}
//						}
						if (fvb.getDevState().equals("4") || fvb.getDevState().equals("5")) {
							List<FiberdiscGroup> list1 = fiberdiscGroupMapper.selectByDevId(fvb.getDevId());//分组
							List<DeviceEntity> list2 = deviceEntityMapper.selectListByDevId(fvb.getDevId());//中控器
							List<DeviceDiscinfoEntity>list3=deviceDiscinfoEntityMapper.selectByDevId(fvb.getDevId());//端子控制器
							if (list1!=null && list1.size()>0) {
								throw new FrmsException("请先删除该设施下分组!");
							}
							if (list2!=null && list2.size()>0) {
								throw new FrmsException("请先解除该设施绑定的中控器!");
							}
							if (list3!=null && list3.size()>0) {
								throw new FrmsException("请先解除该设施绑定的端子控制器!");
							}
							List<CableSectionWellBean> list = cableSectionMapper.queryopdCableSectionByDevId(fvb.getDevId());
							if (list!=null && list.size()>0) {
								for (int i = 0; i < list.size(); i++) {
									cableSectionMapper.deleteByPrimaryKey(list.get(i).getSectId());
								}
							}
						}
					}
				}
			}
			
			/**/
			//fl 修改增加百度 X Y 坐标
			if (facility.getLatitude() !=null && !"".equals(facility.getLatitude()) && facility.getLongitude() !=null && !"".equals(facility.getLongitude())) {
				LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
				facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
				facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
			}
			
			//验证设施名并修改光缆段名称
			validateDevName(facility);
			
			facilityMapper.updateByPrimaryKeySelective(facility);
			List<String> imgList=null;
			if ( fvb.getPicList()!=null) {
				 imgList = fvb.getPicList();//传入路径
			}
			List<FacilityImg> list = facilityImgMapper.selectByDevId(fvb.getDevId());//数据库路径集合
			
			List<String> imgUrlList = new ArrayList<String>();
			if (imgList!=null && imgList.size()>0) {
				int t=0;
				for (String url : imgList) {
					if (list!=null && list.size()>0) {
						for (int i = 0; i < list.size(); i++) {
							String sjkimgUrl=list.get(i).getImgUrl();//数据路径
							if (url.equals(sjkimgUrl)) {
								++t;
							}
						}
						if (t==0) {
							imgUrlList.add(url);//去添加
						}
						t=0;
					}else {
						imgUrlList.add(url);
					}
				}
			}
			if (imgUrlList!=null && imgUrlList.size()>0) {
				for (String url : imgUrlList) {
					FacilityImg img = new FacilityImg();
					img.setDevCode(facility.getDevCode());
					img.setDevId(facility.getDevId());
					img.setImgUrl(url);
					img.setFlag("1");
					img.setPhotoTime(new Date());
					img.setImgTypes(fvb.getImgType());
					facilityImgMapper.insertSelective(img);
				}
			}
			ret = facility.getDevId();
		}
		return ret;
	}

	/**
	 * 
	 * @Title: verifyGroupType
	 * @Description: 验证跳纤两端的所属分组类型
	 * @param @param code
	 * @param @param zcode
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月25日 上午11:11:50
	 * @version V1.0
	 */
	@Override
	public void verifyGroupType(String code, String zcode) {
		String groupTypeA = "";
		String groupTypeZ = "";
		if(code != null && !"".equals(code.trim())) {
			FiberdiscGroup groupA = fiberdiscGroupMapper.queryGroupByPort01(code);
			if(groupA != null && groupA.getGroupType() != null && !"".equals(groupA.getGroupType().trim())) {
				groupTypeA = groupA.getGroupType();
			}
		}
		if(zcode != null && !"".equals(zcode.trim())) {
			FiberdiscGroup groupZ = fiberdiscGroupMapper.queryGroupByPort01(zcode);
			if(groupZ != null && groupZ.getGroupType() != null && !"".equals(groupZ.getGroupType().trim())) {
				groupTypeZ = groupZ.getGroupType();
			}
		}
		if(!"".equals(groupTypeA) && !"".equals(groupTypeZ) && groupTypeA.equals(groupTypeZ)) {
			if("01".equals(groupTypeA)) {
				throw new FrmsException("两端必须有一端为设备分组");
			}
			if("02".equals(groupTypeA)) {
				throw new FrmsException("两端只能有一端为设备分组");
			}
		}
	}

	/**
	 * 
	 * @Title: queryFacilityByOrgId
	 * @Description: 根据orgId分组查询
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午10:23:45
	 * @version V1.0
	 */
	@Override
	public List<Facility> queryFacilityByOrgId() {
		return facilityMapper.queryFacilityByOrgId();
	}

	/**
	 * 查询可修改机柜
	* @Title: queryCabinetsByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param routesSort
	* @param @param designroutesId
	* @param @return    入参
	* @return List<FacilityCabinetsInfo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月27日 下午1:55:57 
	* @version V1.0
	 * @throws Exception 
	 */
	@Override
	public List<FacilityCabinetMobile> queryCabinetsByConditions(int routesSort, Long designroutesId, Users users, Long devId){
		List<FacilityCabinetMobile> list=new ArrayList<FacilityCabinetMobile>();
		if (routesSort>1) {//最后一条查询上一条的AZ
			routesSort=routesSort-1;
		}
		WorkorderRoutes workorderRoute=workorderRoutesMapper.queryRoutesAZ(routesSort,designroutesId,devId);//查询对端ID
		Facility facility=null;
		if (workorderRoute!=null) {//查询pdevId
			 facility=facilityMapper.selectByPrimaryKey(devId);
			 //查询可修改机柜
			 if (facility!=null && facility.getPdevId()!=null ) {//机柜
				 list=cableSectionMapper.queryByConditions(users.getOrgId(),facility.getPdevId(),devId,workorderRoute.getZdevId());
			 }else {//光交箱
				 FacilityCabinetMobile facilityCabinetsInfo = new FacilityCabinetMobile();
				 Facility fa = facilityMapper.selectByPrimaryKey(devId);
				 if (fa!=null) {
					 facilityCabinetsInfo.setDevCode(fa.getDevCode());
					 facilityCabinetsInfo.setDevId(devId);
					 facilityCabinetsInfo.setDevName(fa.getDevName());
					 facilityCabinetsInfo.setOtherDevId(workorderRoute.getZdevId());
					 list.add(facilityCabinetsInfo);
				 }
			 }
		}else {
			throw new  FrmsException("未查询到对端ID");
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.FacilityService#queryJointByWellId(java.lang.Long)
	 */
	/** 
	 * 根据井Id下的所有接头
	* @Title: queryJointByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return List<Facility>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月10日 下午4:18:57 
	* @version V1.0   
	*/
	@Override
	public List<Map<String, Object>> queryJointByWellId(Long wellId) {
		return facilityMapper.queryJointByWellId(wellId);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.FacilityService#queryOciiFacilityList(com.ycnet.frms.vo.FacilityConditionBean, java.lang.Long)
	 */
	/** 
	 * 查询设施列表
	* @Title: queryOciiFacilityList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param orgId
	* @param @return    入参
	* @return List<OciiFacility>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午1:36:19 
	* @version V1.0   
	*/
	@Override
	public List<OciiFacility> queryOciiFacilityList(FacilityConditionBean facility, PageBean pb, Long orgId) {
		return facilityMapper.queryOciiFacilityList(facility,pb,orgId);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.FacilityService#queryOciiFacilityByDevId(java.lang.Long)
	 */
	/** 
	 * 根据设施ID查询设施(机房,光交)
	* @Title: queryOciiFacilityByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return OciiFacilityBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午2:17:28 
	* @version V1.0   
	*/
	@Override
	public OciiFacilityBean queryOciiFacilityByDevId(Long devId) {
		return facilityMapper.queryOciiFacilityByDevId(devId);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.FacilityService#queryOciiFacilityByPdevId(java.lang.Long)
	 */
	/** 
	 * 查询机房下的机柜列表
	* @Title: queryOciiFacilityByPdevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<Map<String,Object>>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 下午2:48:45 
	* @version V1.0   
	*/
	@Override
	public List<Map<String, Object>> queryOciiFacilityByPdevId(Long devId) {
		return facilityMapper.queryOciiFacilityByPdevId(devId);
	}

	/** 
	 * 删除光缆接头
	* @Title: delOciiJointByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param orgId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月16日 下午4:28:59 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.FacilityService#delOciiJointByDevId(java.lang.Long, java.lang.Long)
	 */
	@Override
	public int delOciiJointByDevId(Long devId, Long orgId, String devState) {
		int reg=0;
		List<OcJointDirect> list =ocJointDirectMapper.queryByDevId(devId);
		if (list!=null && list.size()>0) {
			throw new FrmsException("("+devId+")该设施下有直熔光缆不能删除!");
		}else {
			Facility facility = new Facility();
			facility.setDevId(devId);
			if (devState.equals("5")) {
				facility.setDevState(devState);
			}else if(devState.equals("4")){
				facility.setDevState(devState);
			}
			reg=facilityMapper.updateByPrimaryKeySelective(facility);
		}
		return reg;
	}

	/** 
	 * 删除接头中光缆直熔【解直熔】
	* @Title: delOciiJointDirectByJointId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param jointId
	* @param @param orgId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月17日 上午9:38:42 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.FacilityService#delOciiJointDirectByDevId(java.lang.Long, java.lang.Long)
	 */
	@Override
	public int delOciiJointDirectByJointId(Long jointId, Long orgId) {
		return ocJointDirectMapper.deleteByPrimaryKey(jointId);
	}

	/** 
	 * 添加/修改直熔
	* @Title: saveOciiJointDirect 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ocJointDirect
	* @param @param orgId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月17日 上午10:18:57 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.FacilityService#saveOciiJointDirect(com.ycnet.frms.bean.OcJointDirect, java.lang.Long)
	 */
	@Override
	public int saveOciiJointDirect(OcJointDirect ocJointDirect, Users user) {
		int reg=0;
		/**校验**/
		if (facilityMapper.selectByPrimaryKey(ocJointDirect.getDevId())==null) {
			throw new FrmsException("该设施不存在!");
		}
		if (ocJointDirect.getSectIdA()==ocJointDirect.getSectIdZ()) {
			throw new FrmsException("不能选择同一根光缆!");
		}
		if (ocJointDirect.getFiberStartA()<=0 || ocJointDirect.getFiberStartZ()<=0) {
			throw new FrmsException("A/Z起始端纤芯数必须大于0!");
		}
		if(ocJointDirect.getFiberStartA()>ocJointDirect.getFiberEndA() || ocJointDirect.getFiberStartZ()>ocJointDirect.getFiberEndZ()) {
			throw new FrmsException("起始纤芯不能大于结束纤芯!");
		}
		if((ocJointDirect.getFiberEndZ()-ocJointDirect.getFiberStartZ())!=(ocJointDirect.getFiberEndA()-ocJointDirect.getFiberStartA())) {
			throw new FrmsException("两端纤芯数不一致!");
		}
		OcCableSection ocCableSectionA = ocCableSectionMapper.selectByPrimaryKey(ocJointDirect.getSectIdA());
		OcCableSection ocCableSectionZ = ocCableSectionMapper.selectByPrimaryKey(ocJointDirect.getSectIdZ());
		if (ocCableSectionA==null || ocCableSectionZ==null) {
			throw new FrmsException("光缆A/Z不存在!");
		}
		if (ocCableSectionA.getFiberNum() < ocJointDirect.getFiberStartA()
				|| ocCableSectionA.getFiberNum() < ocJointDirect.getFiberEndA()
				|| ocCableSectionZ.getFiberNum() < ocJointDirect.getFiberStartZ()
				|| ocCableSectionZ.getFiberNum() < ocJointDirect.getFiberEndZ()) {
			throw new FrmsException("所输芯数不得大于光缆段芯数!");
		}
		/**筛选校验的条件**/
		//查询所选纤芯是否存在直熔的数据
		Integer sumA = ocJointDirectMapper.queryOcJointDirectByCondition(ocJointDirect.getSectIdA(),ocJointDirect.getFiberStartA(),ocJointDirect.getFiberEndA(),ocJointDirect.getDevId(),ocJointDirect.getJointId());
		Integer sumZ = ocJointDirectMapper.queryOcJointDirectByCondition(ocJointDirect.getSectIdA(),ocJointDirect.getFiberStartA(),ocJointDirect.getFiberEndA(),ocJointDirect.getDevId(),ocJointDirect.getJointId());
		if (sumA>0) {
			throw new FrmsException("A端所选纤芯中("+ocJointDirect.getFiberStartA()+"一一一"+ocJointDirect.getFiberEndA()+")已有直熔纤芯!");
		}
		if (sumZ>0) {
			throw new FrmsException("Z端所选纤芯中("+ocJointDirect.getFiberStartZ()+"一一一"+ocJointDirect.getFiberEndZ()+")已有直熔纤芯!");
		}
		/**保存数据**/
		if (ocJointDirect.getJointId() != null) {
			ocJointDirect.setLastModifyTime(new Date());
			ocJointDirect.setLastModifyUser(user.getUserId());
			reg = ocJointDirectMapper.updateByPrimaryKeySelective(ocJointDirect);
		} else {
			ocJointDirect.setCreateTime(new Date());
			ocJointDirect.setCreateUser(user.getUserId());
			ocJointDirect.setOrgId(user.getOrgId());
			reg = ocJointDirectMapper.insertSelective(ocJointDirect);
		}
		return reg;
	}

	
	/** 
	 * 修改/保存接头
	* @Title: saveOciiJoint 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param facility
	* @param @param user
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月17日 下午3:38:47 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.service.FacilityService#saveOciiJoint(com.ycnet.frms.bean.Facility, com.ycnet.frms.bean.Users)
	 */
	@Override
	public int saveOciiJoint(Facility facility, Users user) {
		int reg=0;
		if (facility!=null && facility.getLongitude()!=null && !"".equals(facility.getLongitude()) && facility.getLatitude()!=null && !"".equals(facility.getLatitude())) {
			LatLng LatLng = Map_Util.Gps84_To_bd09(Double.valueOf(facility.getLatitude()), Double.valueOf(facility.getLongitude()));
			facility.setBaiduX(BigDecimal.valueOf(LatLng.getLongitude()));
			facility.setBaiduY(BigDecimal.valueOf(LatLng.getLatitude()));
		}
		facility.setDevCode(siteCodeService.getNewDevCode());
		if (facility.getDevId() == null) {// 保存(新建)
			facility.setCreateTime(new Date());
			facility.setCreateUser(user.getUserId());
			facility.setOrgId(user.getOrgId());
			facility.setDevModel("0");
			facility.setDevType("04");
			facility.setDevState("2");
			reg = facilityMapper.insertSelective(facility);
		} else {// 修改
			facility.setLastModifyTime(new Date());
			facility.setLastModifyUser(user.getUserId());
			reg = facilityMapper.updateByPrimaryKeySelective(facility);
		}
		return reg;
	}
}
