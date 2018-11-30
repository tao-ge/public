package com.ycnet.frms.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.Constants;
import com.ycnet.core.DateState;
import com.ycnet.core.FrmsException;
import com.ycnet.core.util.BeanUtils;
import com.ycnet.core.util.PageBean;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.Cable;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableSectionDec;
import com.ycnet.frms.bean.CableSectionZF;
import com.ycnet.frms.bean.CablesectionInvest;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.bean.TnExport;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.mapper.CableMapper;
import com.ycnet.frms.mapper.CableSectionDecMapper;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.CablesectionInvestMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.PipingSectionMapper;
import com.ycnet.frms.mapper.TnExportMapper;
import com.ycnet.frms.mapper.WellMapper;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.service.CableSectionService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.LinesService;
import com.ycnet.frms.service.SiteCodeService;
import com.ycnet.frms.util.StringUtil;
import com.ycnet.frms.vo.CableRoute;
import com.ycnet.frms.vo.CableRouteCondition;
import com.ycnet.frms.vo.CableRouteLinks;
import com.ycnet.frms.vo.CableRouteNodes;
import com.ycnet.frms.vo.CableSectionBean;
import com.ycnet.frms.vo.CableSectionConditionBean;
import com.ycnet.frms.vo.CableSectionNotEndBean;
import com.ycnet.frms.vo.CableSectionResultVo;
import com.ycnet.frms.vo.CableSectionWellBean;
import com.ycnet.frms.vo.LinesBean;
import com.ycnet.frms.vo.WellBean;

@Service("cableSectionService")
@Transactional
public class CableSectionServiceImpl implements CableSectionService{
	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper cableSectionMapper;
	
	@Resource(name="linesService")
	private LinesService linesService;
	
	@Resource(name="siteCodeService")
	private SiteCodeService siteCodeService;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="cableMapper")
	private CableMapper cableMapper;
	
	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="linesMapper")
	private LinesMapper linesMapper;
	
	@Resource(name="cableSectionDecMapper")
	private CableSectionDecMapper cableSectionDecMapper;
	
	@Resource(name="cableSectionService")
	private CableSectionService cableSectionService;
	
	@Resource(name="exportMapper")
	private TnExportMapper tnExportMapper;
	
	@Resource(name="cablesectionInvestMapper")
	private CablesectionInvestMapper cablesectionInvestMapper;
	
	@Resource(name="wellMapper")
	private WellMapper wellMapper;
	
	@Resource(name="pipingSectionMapper")
	private PipingSectionMapper pipingSectionMapper;
	

	
	
	public List<CableSection> queryByConditionBean(CableSectionConditionBean bean)
	{
		
		return cableSectionMapper.queryByConditionBean(bean);
	}
	
	public CableSection selectById(Long sectId )
	{
		return cableSectionMapper.selectByPrimaryKey(sectId);
	}
	
	public int delete(Long sectId) {
		linesService.deleteAllFibers(sectId);
		return cableSectionMapper.deleteByPrimaryKey(sectId);
	}
	
	public Long save(CableSection cableSection)
	{
		long ret = 0;
		if(cableSection.getSectId()!=null)
		{
			/**
			 * 编码不让修改
			 */
			cableSection.setSectCode(null);
			CableSection old = cableSectionMapper.selectByPrimaryKey(cableSection.getSectId());
			if(cableSection.getDevIdA()==null|| cableSection.getDevIdZ()==null)
			{
				throw new FrmsException("光缆段A/Z端设施不能为空");
			}
			
			if(cableSection.getDevIdA().longValue()== cableSection.getDevIdZ().longValue())
			{
				throw new FrmsException("光缆段A/Z端设施不能相同");
			}
			if(DateState.NEWLY.toString().equals(cableSection.getSectState())) {
				if(cableSection.getDevIdA().longValue()!=old.getDevIdA().longValue())
				{
					Facility f = facilityMapper.selectByPrimaryKey(cableSection.getDevIdA());
					if(f==null)
						throw new FrmsException("光缆段A端设施id不正确");
					cableSection.setDevCodeA(f.getDevCode());
				}
				
				if(cableSection.getDevIdZ().longValue()!=old.getDevIdZ().longValue())
				{
					Facility f = facilityMapper.selectByPrimaryKey(cableSection.getDevIdZ());
					if(f==null)
						throw new FrmsException("光缆段Z端设施id不正确");
					cableSection.setDevCodeZ(f.getDevCode());
				}
			}
			//linesService.updateFiber(cableSection.getSectId(),old.getDevIdA(),old.getDevIdZ(),cableSection.getDevIdA(),cableSection.getDevIdZ());
			linesService.updateLine(cableSection,old);
			cableSectionMapper.updateByPrimaryKeySelective(cableSection);
			//修改纤芯
		}
		else
		{
			cableSection.setSectCode(siteCodeService.getNewSectCode());
			Facility f = facilityMapper.selectByPrimaryKey(cableSection.getDevIdA());
			if(f==null)
				throw new FrmsException("光缆段A端设施id不正确");
			cableSection.setDevCodeA(f.getDevCode());
			
			f = facilityMapper.selectByPrimaryKey(cableSection.getDevIdZ());
			if(f==null)
				throw new FrmsException("光缆段Z端设施id不正确");
			cableSection.setDevCodeZ(f.getDevCode());
			cableSection.setSectState("2");//2 添加,dzy修改
			
			cableSectionMapper.insertSelective(cableSection);
			//生成纤芯
			linesService.addFiber(cableSection.getSectId(), cableSection.getFiberNum(),cableSection.getDevIdA(),cableSection.getDevIdZ(),cableSection.getOrgId());
		}
		ret = cableSection.getSectId();
		return ret;
	}

	@Override
	public CableSectionBean convert(CableSection cableSection) {
		if (cableSection == null)
			return null;
		CableSectionBean bean = new CableSectionBean();
		BeanUtils.copy(cableSection, bean);
		Facility fA = facilityService.selectById(bean.getDevIdA());
		Facility fZ = facilityService.selectById(bean.getDevIdZ());
		Cable cable = new Cable();
		if(bean.getCableId() != null) {
			cable = cableMapper.selectByPrimaryKey(bean.getCableId());
		}
		if(fA!=null)
			bean.setDevAName(fA.getDevName());
		if(fZ!=null)
			bean.setDevZName(fZ.getDevName());
		if(cable!=null)
			bean.setCableName(cable.getCableName());
		
		Map<String,String> surveyResultMap=Constants.SurveyResultMap;
		if(surveyResultMap.containsKey(bean.getSurveyResult()))
		{
			bean.setSurveyResultName(surveyResultMap.get(bean.getSurveyResult()));
		}
//		List<Lines> linesList = linesMapper.selectIsRBySectId(cableSection.getSectId());
		String isTerminat = "0";
		String sectNull = "0";
//		for(Lines lines : linesList){
//			//判断光缆两端是否都为空
//			if(lines.getAcode()!=null || lines.getZcode()!=null){
//				sectNull = "1";
//			}
//			if(lines.getAcode()==null && lines.getZcode()==null){
//				continue;
//			}else if(lines.getAcode()!=null && lines.getZcode()!=null){
//				String aFiberNum =lines.getAcode().substring(lines.getAcode().lastIndexOf("-")+1);
//				String zFiberNum =lines.getZcode().substring(lines.getZcode().lastIndexOf("-")+1);
//				if(!aFiberNum.equals(zFiberNum)){
//					isTerminat ="0";
//					break;
//				}		
//				int ret = linesMapper.selectTerminatByPort(lines.getAcode(),lines.getZcode());
//				if(ret == 2){
//					isTerminat = "1";
//				}
//			}else{
//				isTerminat = "2";
//				break;
//			}
//		}
//		bean.setIsTerminat(isTerminat);
		if("0".equals(sectNull)){
			bean.setIsTerminat("0");
		}
		return bean;
	}

	@Override
	public List<CableSectionBean> convert(List<CableSection> list) {
		if(list==null||list.size()==0)
			return null;
		List<CableSectionBean> rs = new ArrayList<CableSectionBean>();
		for(CableSection cableSection:list)
		{
			CableSectionBean bean = convert(cableSection);
			rs.add(bean);
		}
		return rs;
	}

	@Override
	public List<CableSectionBean> queryBeanByConditionBean(
			CableSectionConditionBean bean) {
		// TODO Auto-generated method stub
		return this.convert(cableSectionMapper.queryByConditionBean(bean));
	}

	@Override
	public List<CableRouteNodes> queryCableRouteNodes(CableRouteCondition con) {
		return cableSectionMapper.queryCableRouteNodes(con);
	}

	@Override
	public List<CableRouteNodes> queryCableRouteNodesV1(CableRouteCondition con) {
		return cableSectionMapper.queryCableRouteNodesV1(con);
	}
	
	@Override
	public List<CableRouteLinks> queryCableRouteLinks(CableRouteCondition con) {
		return cableSectionMapper.queryCableRouteLinks(con);
	}
	
	@Override
	public List<CableRouteLinks> queryCableRouteLinksV1(CableRouteCondition con) {
		return cableSectionMapper.queryCableRouteLinksV1(con);
	}

	@Override
	public CableRoute queryCableRoute(Long devId) {
		List<CableSection> sections = cableSectionMapper.selectRelativeSection(devId);
		Map<Long,Facility> map = new HashMap<Long,Facility>();
		
		List<CableRouteLinks> links = new ArrayList<CableRouteLinks>();
		List<CableRouteNodes> nodes = new ArrayList<CableRouteNodes>();
		
		for(CableSection section:sections)
		{
			CableRouteLinks link = new CableRouteLinks();
			link.setCableName(section.getSecName());
		
			Long adevId = section.getDevIdA();
			Long zdevId = section.getDevIdZ();
			Facility a = map.get(adevId);
			Facility z = map.get(zdevId);
			if(a==null)
			{
				a = facilityService.selectById(adevId);
				map.put(adevId, a);
			}
			if(z==null)
			{
				z = facilityService.selectById(zdevId);
				map.put(zdevId, z);
			}
			if(a!=null)
				link.setDevAName(a.getDevName());
			if(z!=null)
				link.setDevZName(z.getDevName());
			links.add(link);
		}
		
		for(Long id: map.keySet())
		{
			CableRouteNodes node = new CableRouteNodes();
			Facility f;
			if(id!=null && ((f=map.get(id))!=null))
			{
				node.setDevCode(f.getDevCode());
				node.setDevId(String.valueOf(id));
				node.setDevName(f.getDevName());
				Map<String,String> mapDevType=basecodeService.getDEVTypeMap();
				if(mapDevType.containsKey(f.getDevType()))
				{
					node.setDevType(mapDevType.get(f.getDevType()));
					      
				}
				
				nodes.add(node);
			}
		}
		CableRoute route = new CableRoute();
		route.setLinks(links);
		route.setNodes(nodes);
		return route;
	}
	
	public CableSection selectByCode(String sectCode) {
		return cableSectionMapper.selectByCode(sectCode);
	}
	
	//获取光缆列表,分页
	@Override
	public List<CableSection> querySectionList(CableSectionConditionBean bean) {
		return cableSectionMapper.querySectionList(bean);
	}
	//A端Z端详情
	@Override
	public CableSectionResultVo queryDev(Long sectId) {
		return cableSectionMapper.selectCableSections(sectId);
	}
	
	
	@Override
	public List<CableSection> findFiber(Long orgId,String areaCode1) {
		List<CableSection> list =cableSectionMapper.findFiberList(orgId,areaCode1);
		List<CableSection> caList=new ArrayList<CableSection>();
		Facility aFacility=new Facility();
		Facility zFacility=new Facility();
		for (CableSection cableSection : list) {
			aFacility=facilityMapper.selectByPrimaryKey(cableSection.getDevIdA());
			zFacility=facilityMapper.selectByPrimaryKey(cableSection.getDevIdZ());
			if (null !=aFacility   ) {
				if (null!=zFacility ) {
					cableSection.setAfacility(aFacility);
					cableSection.setZfacility(zFacility);
					caList.add(cableSection);
					
				}else {
					continue;
				}
			}else {
				continue;
			}
			
		}
		return caList;
	}
	


	/**
	 * 
	* @Title: querySectionListBySectId 
	* @Description: 查询光缆段分页 
	* @param @param session
	* @param @param pageBean
	* @param @param cableSection
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月3日 上午11:13:48 
	* @version V1.0
	 */
	@Override
	public List<CableSectionBean> querySectionListBySectId(CableSectionConditionBean bean) {
		List<CableSectionBean> list = cableSectionMapper.querySectionListBySectId(bean);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDevIdA()!=null) {
				List<CableSectionBean> listDecA=cableSectionService.queryByDevId(list.get(i).getDevIdA(), bean.getOrgId());
				if(listDecA!=null) {
					for(CableSectionBean cable:listDecA) {
						list.get(i).setZgDevAName(cable.getZgDevAName());
						list.get(i).setZgDevZName(cable.getZgDevZName());
						list.get(i).setZgDevIdA(cable.getZgDevIdA());
						list.get(i).setZgDevIdZ(cable.getZgDevIdZ());
						list.get(i).setZgFiberNum(cable.getZgFiberNum());
						list.get(i).setZgSecName(cable.getZgSecName());
						list.get(i).setIsTerminat(cable.getIsTerminat());
					}
				}
			}
			if(list.get(i).getDevIdZ()!=null) {
				List<CableSectionBean> listDecZ=cableSectionService.queryByDevId(list.get(i).getDevIdZ(), bean.getOrgId());
				if(listDecZ!=null) {
					for(CableSectionBean cable:listDecZ) {
						list.get(i).setZgDevAName(cable.getZgDevAName());
						list.get(i).setZgDevZName(cable.getZgDevZName());
						list.get(i).setZgDevIdA(cable.getZgDevIdA());
						list.get(i).setZgDevIdZ(cable.getZgDevIdZ());
						list.get(i).setZgFiberNum(cable.getZgFiberNum());
						list.get(i).setZgSecName(cable.getZgSecName());
						list.get(i).setIsTerminat(cable.getIsTerminat());
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 添加光缆状态
	 * @author: YHT
	 * @date: 2017年11月10日 下午3:04:57 
	 * @Title: addStatus  
	 * @param @param list
	 * @param @return     
	 * @return List<CableSectionBean>   
	 * @throws
	 */
	public List<CableSectionBean> addStatus(List<CableSectionBean> list){
		if(list==null || list.size()<1) {
			return null;
		}
		int aNum = 0;
		int zNum = 0;
		for(CableSectionBean cs : list) {
			if("1".equals(cs.getIsTerminat())) {
				List<Lines> lineList = linesMapper.selectBySectId(cs.getSectId());
				for(Lines lines : lineList) {
					if(lines.getAcode() != null) {
						aNum = linesMapper.isExistJumperByCode(lines.getAcode());
						zNum = linesMapper.isExistJumperByCode(lines.getZcode());
						if((aNum==0 && zNum!=0) || (aNum!=0 && zNum==0)) {
							cs.setIsTerminat("0");
							break;
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<CableSectionBean> queryByConditionBeanSect(CableSectionConditionBean bean) {
		//查询机房，先注释
//		List<CableSection> list=null;
//		list=cableSectionMapper.queryByConditionBean(bean);
//		Facility facility = null;
//		if(bean.getDevId()!=null) {
//			facility = facilityMapper.selectByPrimaryKey(bean.getDevId());
//			if(facility.getPdevId()!=null) {
//				bean.setDevId(facility.getPdevId());
//				if(list!=null && list.size()>0) {
//					list.addAll(cableSectionMapper.queryByConditionBean(bean));
//				}else {
//					list = cableSectionMapper.queryByConditionBean(bean);
//				}
//			}
//		}
		
		return cableSectionMapper.queryByConditionBeanSect(bean);
	}

	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<CableSectionZF> queryCableSectionList(Long orgId,String areaCode) {
		return cableSectionMapper.queryCableSectionList(orgId,areaCode);
	}
	/**
	 * 
	* @Title: queryByDevId
	* @Description: TODO(根据 dev_id 查询光缆信息) 
	* @param @param devId
	* @param @param orgId
	* @param @return    入参
	* @return List<CableSectionBean>    返回类型
	* @author 周宇（作者） 
	* @throws
	* @date 2017年11月28日 上午9:13:37 
	* @version V1.0
	 */
	@Override
	public List<CableSectionBean> queryByDevId(Long devId,Long orgId) {
		// TODO Auto-generated method stub
		List<CableSectionBean> list =  cableSectionMapper.queryByDevId(devId, orgId);
		if(list != null && list.size()>0) {
			for(CableSectionBean cs : list) {
				Long sectId = cs.getSectId();
				String isTerminat="";
				//判断是否为完整光缆
				int isT = cableSectionMapper.getIsTerminats(sectId);
				if(isT>0){
					 isTerminat="1";
				}else {
					 isTerminat="0";
				}
				cs.setIsTerminat(isTerminat);
			}
		}
		return list;
	}
	
	/**
	 * 
	* @Title: queryBysectState 
	* @Description: 按前台传入 sectState值查询tn_cable_section
	* @param @param orgId
	* @param @param sectState
	* @param @return    入参
	* @return List<CableSectionBean>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年11月28日 上午9:15:38 
	* @version V1.0
	 */
	@Override
	public List<CableSectionBean> queryBysectState( Long orgId,String sectState,Long devId) {
		return cableSectionMapper.queryBysectState(orgId,sectState,devId);
	}

		
	/**
	* 修改光缆段信息
	*/
	@Override
	public int modifyCableSection(CableSection ca, Users user) {
			int num = 0;
			CableSection old = null;
			if(ca.getSectId() != null) {
				old = cableSectionMapper.selectByPrimaryKey(ca.getSectId());
			}		
					
			CableSectionDec caDesc = null;
			ca.setOrgId(user.getOrgId());
			if(ca.getSectId()==null){
				ca.setCreateUser(user.getUserId());
				ca.setCreateTime(new Date());
			}else{
				ca.setLastModifyTime(new Date());
				ca.setLastModifyUser(user.getUserId());
			}
			//一致
			if(DateState.NORMAL.toString().equals(ca.getSectState())) {
				num = cableSectionMapper.updateByPrimaryKeySelective(ca);
			//处理新增纤芯修改
			}else if(DateState.NEWLY.toString().equals(ca.getSectState())) {
				Long sectId = cableSectionService.save(ca);
				Long oldFiber = 0L;
				if(old != null) {
					oldFiber = old.getFiberNum();
				}
				//zgFiberNum 资管原来纤芯  
				//FiberNum   改变后纤芯
				if(ca.getFiberNum().longValue()>oldFiber) {
					//参数  小大
					for(long i =oldFiber.intValue();i<ca.getFiberNum();i++){
						//处理光缆描述
						caDesc = new CableSectionDec();
						caDesc.setFiberNo(i+1);
						caDesc.setFiberState("2");
						caDesc.setSectId(sectId);
						caDesc.setCreateUser(user.getUserId());
						caDesc.setCreateTime(new Date());
						caDesc.setOrgId(user.getOrgId());
						num = cableSectionDecMapper.insertSelective(caDesc);
						caDesc = null;
					}
				}else if(ca.getFiberNum().longValue()<oldFiber){
					for(long i =ca.getFiberNum()+1;i<oldFiber+1;i++){
						if(sectId == null) {
							continue;
						}
						num = cableSectionDecMapper.deleteBySectIdAndFiberNo(sectId, i);
					}
				}
				
			//处理修改
			}else if(DateState.MODIF.toString().equals(ca.getSectState())) {
				num = cableSectionService.save(ca).intValue();
				//处理未进行修改的新增资管光缆
				if(DateState.NOCHECK.toString().equals(old.getSectState())) {
					if(ca.getFiberNum().longValue()>ca.getZgFiberNum().longValue()) {
						for(long i =ca.getZgFiberNum();i<ca.getFiberNum();i++){
							caDesc = new CableSectionDec();
							caDesc.setFiberNo(i+1);
							caDesc.setFiberState("2");
							caDesc.setSectId(ca.getSectId());
							caDesc.setCreateUser(user.getUserId());
							caDesc.setCreateTime(new Date());
							caDesc.setOrgId(user.getOrgId());
							num = cableSectionDecMapper.insertSelective(caDesc);
							caDesc = null;
						}
					}else if(ca.getFiberNum().longValue()<ca.getZgFiberNum().longValue()){
						List<CableSectionDec> decList = cableSectionDecMapper.selectBySectIdAndGreatFiberNum(ca.getSectId(),ca.getFiberNum(),ca.getZgFiberNum()+1);
						if(decList!=null && decList.size()>0) {
							for(CableSectionDec csd : decList){
								if(csd != null) {
									if("2".equals(csd.getFiberState())) {
										csd.setFiberState("3");
									}else {
										csd.setFiberState("0");
									}
									csd.setModifyTime(new Date());
									csd.setModifyUser(user.getUserId());
									num = cableSectionDecMapper.updateByPrimaryKeySelective(csd);
								}
							}
						}
						
					}
				//处理除了上面所有情况的 资管修改
				}else {
					//1 新 2 旧 3资
					//1>3=2
					if(old.getFiberNum().longValue()==old.getZgFiberNum().longValue() && old.getFiberNum().longValue()<ca.getFiberNum().longValue()) {
						for(long i =old.getFiberNum();i<(ca.getFiberNum()+1);i++){
							caDesc = new CableSectionDec();
							caDesc.setFiberNo((i+1)) ;
							caDesc.setFiberState("2");
							caDesc.setSectId(ca.getSectId());
							caDesc.setCreateUser(user.getUserId());
							caDesc.setCreateTime(new Date());
							caDesc.setOrgId(user.getOrgId());
							num = cableSectionDecMapper.insertSelective(caDesc);
							caDesc = null;
						}
					//1<3=2
					}else if(old.getFiberNum().longValue()==old.getZgFiberNum().longValue() && old.getFiberNum().longValue()>ca.getFiberNum().longValue()) {
						//查询出被修改的资管数据
						List<CableSectionDec> decList = cableSectionDecMapper.selectBySectIdAndGreatFiberNum(old.getSectId(),ca.getFiberNum(),old.getFiberNum()+1);
						if(decList!=null && decList.size()>0) {
							for(CableSectionDec csd : decList){
								if("2".equals(csd.getFiberState()) || "3".equals(csd.getFiberState())) {
									csd.setFiberState("3");
								}else {
									csd.setFiberState("0");
								}
								csd.setModifyUser(user.getUserId());
								csd.setModifyTime(new Date());
								num = cableSectionDecMapper.updateByPrimaryKeySelective(csd);
							}
						}
					//1=3>2
					}else if(ca.getFiberNum().longValue()==old.getZgFiberNum().longValue() && old.getFiberNum().longValue()<ca.getFiberNum().longValue()) {
						//查询出被修改的资管数据
						List<CableSectionDec> decList = cableSectionDecMapper.selectBySectIdAndGreatFiberNum(old.getSectId(),old.getFiberNum(),ca.getFiberNum()+1);
						if(decList!=null && decList.size()>0) {
							for(CableSectionDec csd : decList){
								if("2".equals(csd.getFiberState()) || "3".equals(csd.getFiberState())) {
									csd.setFiberState("2");
								}else {
									csd.setFiberState("1");
								}
								csd.setModifyUser(user.getUserId());
								csd.setModifyTime(new Date());
								num = cableSectionDecMapper.updateByPrimaryKeySelective(csd);
							}
						}
					//1=3<2
					}else if(ca.getFiberNum().longValue()==old.getZgFiberNum().longValue() && old.getFiberNum().longValue()>ca.getFiberNum().longValue()) {
						//查询出被修改的资管数据
						List<CableSectionDec> decList = cableSectionDecMapper.selectBySectIdAndGreatFiberNum(old.getSectId(),ca.getFiberNum(),old.getFiberNum()+1);
						if(decList!=null && decList.size()>0) {
							for(CableSectionDec csd : decList){
								num = cableSectionDecMapper.deleteBySectIdAndFiberNo(csd.getSectId(), csd.getFiberNo());
							}
						}
					// 1>2>3
					}else if(ca.getFiberNum().longValue()>old.getFiberNum().longValue() && old.getFiberNum().longValue()>old.getZgFiberNum().longValue()) {
						for(long i =old.getFiberNum();i<(ca.getFiberNum()+1);i++){
							caDesc = new CableSectionDec();
							caDesc.setFiberNo((i+1)) ;
							caDesc.setFiberState("2");
							caDesc.setSectId(ca.getSectId());
							caDesc.setCreateUser(user.getUserId());
							caDesc.setCreateTime(new Date());
							caDesc.setOrgId(user.getOrgId());
							num = cableSectionDecMapper.insertSelective(caDesc);
							caDesc = null;
						}
					// 1>3>2
					}else if(ca.getFiberNum().longValue()>old.getZgFiberNum().longValue() && old.getZgFiberNum()>old.getFiberNum().longValue()) {
						//查询出被修改的资管数据
						List<CableSectionDec> decList = cableSectionDecMapper.selectBySectIdAndGreatFiberNum(old.getSectId(),old.getFiberNum(),old.getZgFiberNum()+1);
						if(decList!=null && decList.size()>0) {
							for(CableSectionDec csd : decList){
								if("2".equals(csd.getFiberState()) || "3".equals(csd.getFiberState())) {
									csd.setFiberState("2");
								}else {
									csd.setFiberState("1");
								}
								csd.setModifyUser(user.getUserId());
								csd.setModifyTime(new Date());
								num = cableSectionDecMapper.updateByPrimaryKeySelective(csd);
							}
						}
						//处理非资管导入数据
						for(long i =old.getZgFiberNum();i<(ca.getFiberNum()+1);i++){
							caDesc = new CableSectionDec();
							caDesc.setFiberNo(i+1) ;
							caDesc.setFiberState("2");
							caDesc.setSectId(ca.getSectId());
							caDesc.setCreateUser(user.getUserId());
							caDesc.setCreateTime(new Date());
							caDesc.setOrgId(user.getOrgId());
							num = cableSectionDecMapper.insertSelective(caDesc);
							caDesc = null;
						}
					//3>1>2
					}else if(ca.getFiberNum().longValue()<old.getZgFiberNum().longValue() && ca.getFiberNum().longValue()>old.getFiberNum().longValue()){
						//查询出被修改的资管数据
						List<CableSectionDec> decList = cableSectionDecMapper.selectBySectIdAndGreatFiberNum(old.getSectId(),old.getFiberNum(),ca.getFiberNum()+1);
						if(decList!=null && decList.size()>0) {
							for(CableSectionDec csd : decList){
								if("2".equals(csd.getFiberState()) || "3".equals(csd.getFiberState())) {
									csd.setFiberState("2");
								}else {
									csd.setFiberState("1");
								}
								csd.setModifyUser(user.getUserId());
								csd.setModifyTime(new Date());
								num = cableSectionDecMapper.updateByPrimaryKeySelective(csd);
							}
						}
					//3>2>1
					}else if(old.getFiberNum().longValue()<old.getZgFiberNum().longValue() && ca.getFiberNum().longValue()<old.getFiberNum().longValue()){
						//查询出被修改的资管数据
						List<CableSectionDec> decList = cableSectionDecMapper.selectBySectIdAndGreatFiberNum(old.getSectId(),ca.getFiberNum(),old.getFiberNum()+1);
						if(decList!=null && decList.size()>0) {
							for(CableSectionDec csd : decList){
								if("2".equals(csd.getFiberState()) || "3".equals(csd.getFiberState())) {
									csd.setFiberState("3");
								}else {
									csd.setFiberState("0");
								}
								csd.setModifyUser(user.getUserId());
								csd.setModifyTime(new Date());
								num = cableSectionDecMapper.updateByPrimaryKeySelective(csd);
							}
						}
					//2>3>1
					}else if(old.getFiberNum().longValue()>old.getZgFiberNum().longValue() && ca.getFiberNum().longValue()<old.getZgFiberNum().longValue()){
						//查询出被修改的资管数据
						List<CableSectionDec> decList = cableSectionDecMapper.selectBySectIdAndGreatFiberNum(old.getSectId(),ca.getFiberNum(),old.getFiberNum()+1);
						if(decList!=null && decList.size()>0) {
							for(CableSectionDec csd : decList){
								if(old.getZgFiberNum() < csd.getFiberNo()) {
									num = cableSectionDecMapper.deleteBySectIdAndFiberNo(csd.getSectId(), csd.getFiberNo());
								}else {
									if("2".equals(csd.getFiberState()) || "3".equals(csd.getFiberState())) {
										csd.setFiberState("3");
									}else {
										csd.setFiberState("0");
									}
									csd.setModifyUser(user.getUserId());
									csd.setModifyTime(new Date());
									num = cableSectionDecMapper.updateByPrimaryKeySelective(csd);
								}
								
							}
						}
					//2>1>3
					}else if(old.getFiberNum().longValue()>ca.getFiberNum().longValue() && ca.getFiberNum().longValue()>old.getZgFiberNum().longValue()){
						//查询出被修改的资管数据
						List<CableSectionDec> decList = cableSectionDecMapper.selectBySectIdAndGreatFiberNum(old.getSectId(),ca.getFiberNum(),old.getFiberNum()+1);
						if(decList!=null && decList.size()>0) {
							for(CableSectionDec csd : decList){
								num = cableSectionDecMapper.deleteBySectIdAndFiberNo(csd.getSectId(), csd.getFiberNo());
							}
						}
					}
				}
				
			//处理资管删除
			}else if(DateState.ZGDEL.toString().equals(ca.getSectState())) {
				num = cableSectionMapper.updateByPrimaryKeySelective(ca);
				for(int i=0 ; i<ca.getFiberNum();i++) {
					caDesc = new CableSectionDec();
					//根据SectId和FiberNo查询一条
					caDesc = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(ca.getSectId(),(long)(i+1));
					if("2".equals(caDesc.getFiberState())) {
						caDesc.setFiberState("3");
					}else {
						caDesc.setFiberState("0");
					}
					caDesc.setModifyTime(new Date());
					caDesc.setModifyUser(user.getUserId());
					num = cableSectionDecMapper.updateByPrimaryKeySelective(caDesc);
					caDesc = null;
				}
			//处理新增删除
			}else if(DateState.NEWDEL.toString().equals(ca.getSectState())) {
				num = cableSectionMapper.updateByPrimaryKeySelective(ca);
				for(int i=0 ; i<ca.getFiberNum();i++) {
					caDesc = new CableSectionDec();
					//根据SectId和FiberNo查询一条
					caDesc = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(ca.getSectId(),(long)(i+1));
					if("2".equals(caDesc.getFiberState())) {
						caDesc.setFiberState("3");
					}else {
						caDesc.setFiberState("0");
					}
					caDesc.setModifyTime(new Date());
					caDesc.setModifyUser(user.getUserId());
					num = cableSectionDecMapper.updateByPrimaryKeySelective(caDesc);
					caDesc = null;
				}
			}
//			if(ca.getSectId() != null) {
//				CableSection caleSection = new CableSection();
//				if("0".equals(old.getSectState())) {
//					if(old.getZgDevIdA().longValue()!=ca.getZgDevIdA().longValue()) {
//						caleSection.setSectId(old.getSectId());
//						Facility faNew = facilityMapper.selectByPrimaryKey(ca.getZgDevIdA());
//						Facility faOld = facilityMapper.selectByPrimaryKey(old.getZgDevIdA());
//						caleSection.setSecName(ca.getSecName().replaceFirst(faOld.getDevName(), faNew.getDevName()));
//						cableSectionMapper.updateByPrimaryKeySelective(caleSection);
//					}
//					if(old.getZgDevIdZ().longValue()!=ca.getZgDevIdZ().longValue()) {
//						caleSection.setSectId(old.getSectId());
//						Facility faNew = facilityMapper.selectByPrimaryKey(ca.getZgDevIdZ());
//						Facility faOld = facilityMapper.selectByPrimaryKey(old.getZgDevIdZ());
//						caleSection.setSecName(ca.getSecName().replace(faOld.getDevName(), faNew.getDevName()));
//						cableSectionMapper.updateByPrimaryKeySelective(caleSection);
//					}
//				}else {
//					if(old.getDevIdA().longValue()!=ca.getDevIdA().longValue()) {
//						caleSection.setSectId(old.getSectId());
//						Facility faNew = facilityMapper.selectByPrimaryKey(ca.getDevIdA());
//						Facility faOld = facilityMapper.selectByPrimaryKey(old.getDevIdA());
//						caleSection.setSecName(ca.getSecName().replaceFirst(faOld.getDevName(), faNew.getDevName()));
//						cableSectionMapper.updateByPrimaryKeySelective(caleSection);
//					}
//					if(old.getDevIdZ().longValue()!=ca.getDevIdZ().longValue()) {
//						caleSection.setSectId(old.getSectId());
//						Facility faNew = facilityMapper.selectByPrimaryKey(ca.getDevIdZ());
//						Facility faOld = facilityMapper.selectByPrimaryKey(old.getDevIdZ());
//						caleSection.setSecName(ca.getSecName().replace(faOld.getDevName(), faNew.getDevName()));
//						cableSectionMapper.updateByPrimaryKeySelective(caleSection);
//					}
//				}
//			}
			return 1;
		}


	/**
	 * 根据传入的ID和之前的状态修改在表中的状态
	* 对资管导入光缆段和新增光缆段状态进行修改 
	* @Title: CableSectionController.java 
	* @Description: TODO 
	* @param @return
	* @return Result
	* @author fl
	* @date 2017年12月1日 下午1:53:13
	* @versi V1.0
	*/ 
	@Override
	public int upddateByIdAndState(Users user,CableSection cs) {
		cs.setLastModifyTime(new Date());
		cs.setLastModifyUser(user.getUserId());
		if(cs.getVerifyNum()!=1) {	//校验是否为已删除设施的光缆段
			String lineType="01";
			List<Lines> cableSection = linesMapper.queryForSectId(cs.getSectId(),lineType);
			if(cableSection!=null && cableSection.size()>0) {
				throw new FrmsException("光缆纤芯已占用，不可以直接删除！");
			}
		}
		return cableSectionMapper.updateByPrimaryKeySelective(cs);
	}

	/**
	 * 根据光缆状态恢复光缆数据
	 */
	@Override
	public int recoveryCableSectionByState(Long sectId, String sectState, Users user) {
		int num = 0;
		CableSectionDec caDesc = null;
		CableSection cableSection = cableSectionMapper.selectByPrimaryKey(sectId);
		if(sectState!=null) {
			if(DateState.ZGDEL.toString().equals(sectState)) {
				cableSection.setSectState("3");
			}else if(DateState.NEWDEL.toString().equals(sectState)) {
				cableSection.setSectState("2");
			}
			cableSection.setLastModifyTime(new Date());
			cableSection.setLastModifyUser(user.getUserId());
			num = cableSectionMapper.updateByPrimaryKeySelective(cableSection);
		}
		//↓2017年12月4日16:20:10   DZY
//		num = cableSectionDecMapper.modifyBySectIdAndState(sectId,"0","1");//修改纤芯状态
//		num = cableSectionDecMapper.modifyBySectIdAndState(sectId,"3","2");//修改纤芯状态
		//↑
//		List<LinesBean> linesList = linesService.queryBySectId(sectId);//根据sectId查询数据
//		for (LinesBean linesBean : linesList) {
//			caDesc = new CableSectionDec();
//			Long fiberNo = linesBean.getFiberNo();
//			caDesc = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(sectId,fiberNo);//根据SectId和FiberNo查询一条
//			caDesc.setModifyTime(new Date());
//			caDesc.setModifyUser(user.getUserId());
//			if("0".equals(caDesc.getFiberState())) {
//				caDesc.setFiberState("1");
//			}else if("3".equals(caDesc.getFiberState())) {
//				caDesc.setFiberState("2");
//			}
//			num = cableSectionDecMapper.updateByPrimaryKeySelective(caDesc);
//			caDesc = null;
//		}
		return num;
	}

	/**
	 * 查询光缆段列表
	 */
	@Override
	public List<CableSectionBean> queryCableSectionList(Users user,Long devId, CableSection cableSection, Integer queryType,Integer cableType,PageBean pb) {
		List<CableSectionBean> list = null;
		if(devId != null) {
			pb.setPageSize(500);
		}
		if(queryType==0) {
			list =  cableSectionMapper.queryZgCableSectionByDevId(user,devId, cableSection,pb);
		}else {
			list =  cableSectionMapper.queryCableSectionByDevId(user,devId, cableSection,pb);
		}
		Facility facility = null;
		if(devId != null && queryType==1) {
			facility = facilityMapper.selectByPrimaryKey(devId);
			if(facility.getPdevId()!=null) {
				if(list!=null && list.size()>0) {
					list.addAll(cableSectionMapper.queryCableSectionByDevId(user,facility.getPdevId(), cableSection,pb));
				}else {
					list = cableSectionMapper.queryCableSectionByDevId(user,facility.getPdevId(), cableSection,pb);
				}
			}
		}
		
		if(list != null && list.size()>0) {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
			//for(int i=0;i<list.size();i++) {
				CableSectionBean cs = (CableSectionBean) iter.next();
				//判断对端是否为机房
				if(devId!=null && queryType!=0 && (Constants.FACILITTYPE.ROOM.toString().equals(cs.getDevTypeA()) || Constants.FACILITTYPE.ROOM.toString().equals(cs.getDevTypeZ()))) {
					cs.setIsJF("1");
				}
				//判断光缆段状态  0 删除 1成端 2 未成端
				if(devId!=null && queryType!=0 && cableType!=null) {
					if(cableType==0) {
						if(cs.getSectState()!=null && (!cs.getSectState().equals("4") && !cs.getSectState().equals("5"))) {
							iter.remove();
						}
					}else {
						if("0".equals(cableType+"")) {
							if(cs.getSectState()!=null && (!"4".equals(cs.getSectState()) && !"5".equals(cs.getSectState()))) {
								iter.remove();
							}
						}else {
							if(cs.getSectState()!=null && ("4".equals(cs.getSectState()) || "5".equals(cs.getSectState()))) {
								iter.remove();
							}else {
								List<Lines> lineList = linesMapper.selectBySectId(cs.getSectId());
								for(Lines line : lineList) {
									if(devId.longValue() == line.getAdevId().longValue()) {
										if(line.getAcode()!=null && !"".equals(line.getAcode()) && (line.getAcode().indexOf("ZRIN")==-1 && line.getAcode().indexOf("ZROUT")==-1)) {
											cs.setIsCD("1");
											break;
										}
									}
									if(devId.longValue() == line.getZdevId().longValue()) {
										if(line.getZcode()!=null && !"".equals(line.getZcode()) && (line.getZcode().indexOf("ZRIN")==-1 && line.getZcode().indexOf("ZROUT")==-1)) {
											cs.setIsCD("1");
											break;
										}
									}
								}
								if("1".equals(cs.getIsCD())) {
									if(cableType == 2) {
										iter.remove();
									}
								}else { 
									if(cableType == 1) {
										iter.remove();
									}
								}
							}
						}
					}
					
				}
//				Long sectId = cs.getSectId();
//				String isTerminat="";
//				//判断是否为完整光缆
//				int isT = cableSectionMapper.getIsTerminats(sectId);
//				if(isT>0){
//					 isTerminat="1";
//				}else {
//					 isTerminat="0";
//				}
//				cs.setIsTerminat(isTerminat);
			}
		}
		return list;
	}

	/**
	 * 根据设施ID 查询光缆段信息
	* 
	* @Title: FacilityController.java 
	* @Description: TODO 
	* @param @return
	* @return Result
	* @author fl
	* @date 2017年12月5日 下午10:01:34
	* @version V1.0
	 */
	@Override
	public List<CableSection> queryById(String devId) {
		return cableSectionMapper.queryById(devId);
	}

	@Override
	public int insertByCable() {
//		List<Lines> linesList=linesService.queryFiberNumFor();
//		for (int i = 0; i < linesList.size(); i++) {
//			
//		}
		int reg=0;
		List<CableSection> cableList=cableSectionMapper.queryFiberNum();
		for (int i = 0; i < cableList.size(); i++) {
			List<LinesBean> linesList=linesService.queryBySectId(cableList.get(i).getSectId());
			if(linesList.size()>0) {
				cableList.get(i).setZgFiberNum((long)linesList.size());
			}else {
				cableList.get(i).setZgFiberNum(0L);
			}
			reg=cableSectionMapper.updateByPrimaryKeySelective(cableList.get(i));
		}
		return reg;
	}

	/**
	 * 
	* @Title: splitRouteText 
	* @Description: TODO(拆分光路) 
	* @param @return    入参
	* @return Object    返回类型
	* @author 刘沧海（作者） 
	* @throws
	* @date 2017年12月12日 下午3:25:38 
	* @version V1.0
	 */
	@Override
	public int splitRouteText() {
		TnExport export=null;
		int reg=0;
		List<TnExport> tnList=tnExportMapper.queryRouteText();
		for (int i = 0; i < tnList.size(); i++) {
			String routeText=tnList.get(i).getE().toString();
			String rouLeft = splitString(routeText,1);		//分解出“，”左侧文本路由
			export=new  TnExport();
			export.setA(tnList.get(i).getA());
			export.setB(tnList.get(i).getB());
			export.setC(tnList.get(i).getC());
			export.setD(tnList.get(i).getD());
			export.setE(rouLeft);
//			export.setF(tnList.get(i).getF());
//			export.setG(tnList.get(i).getG());
//			export.setH(tnList.get(i).getH());
//			export.setI(rouLeft);
			reg=tnExportMapper.insertSelective01(export);
			export=null;
			String rouRight = splitString(routeText,2);		//分解出“，”右侧文本路由
			export=new  TnExport();
			export.setA(tnList.get(i).getA());
			export.setB(tnList.get(i).getB());
			export.setC(tnList.get(i).getC());
			export.setD(tnList.get(i).getD());
			export.setE(rouRight);
//			export.setF(tnList.get(i).getG());
//			export.setG(tnList.get(i).getF());
//			export.setH(tnList.get(i).getH());
//			export.setI(rouRight);
			reg=tnExportMapper.insertSelective01(export);
			export=null;
		}
		
		
		return reg;
	}
	
	/**
	 * 
	* @Title: splitString 
	* @Description: TODO(光路文本路由拆分) 
	* @param @param str
	* @param @param reg
	* @param @return    入参
	* @return String    返回类型
	* @author lch（作者） 
	* @throws
	* @date 2017年12月13日 下午5:58:56 
	* @version V1.0
	 */
	public String splitString(String str,int reg) {
		
		String s=str.toString();
//		if(s.indexOf("、")!=-1 || s.indexOf("，")!=-1) {
//        	so=s.replaceAll("、", ",").replaceAll("，", ",");
//        }else {
//        	so=s;
//        }
		s=s.replaceAll("、", ",").replaceAll("\\(13架）", "\\(13架)").replaceAll("\\*", "！");
        List<String> result = new ArrayList<String>();
        int m = 0, n = 0, count = 0;
        for(int i = 0; i < s.length(); i++){
        	if(s.charAt(i) == '('){
                if(count == 0) m = i;
                count ++;
            }

            if(s.charAt(i) == ')'){
                count--;
                if (count == 0) {
                    n = i;
                    result.add(s.substring(m+1,n).trim());
                }
            }
        }
        // 检验括号是否配对
        if(count==0) {
    	   for (int i = 0; i < result.size(); i++) {
    		   if(result.get(i).indexOf(",")!=-1) {
    			   String[] strs = result.get(i).split(",");
    			   String s1 = strs[0];
    			   String s2 = strs[1];
    			   
    			   if(reg==1) {
    				   s=s.replaceAll(result.get(i), s1);	//截取“，”左侧
    			   }else {
    				   s=s.replaceAll(result.get(i), s2);	//截取“，”右侧
    			   }
    		   }if(result.get(i).indexOf("，")!=-1 &&  result.get(i).indexOf("（")==-1){
    			   String[] strs = result.get(i).split("，");
    			   String s1 = strs[0];
    			   String s2 = strs[1];
    			   
    			   if(reg==1) {
    				   s=s.replaceAll(result.get(i), s1);	//截取“，”左侧
    			   }else {
    				   s=s.replaceAll(result.get(i), s2);	//截取“，”右侧
    			   }
    		   }
    	   }
        }
		return s.replaceAll("！", "\\*");
	}
	
//	public static void main(String[] args) {
//		
//		int reg=1;
////		String so="";{综合楼8楼机房803列103架-综合楼7楼W702列-BA17架(FX104,FX105)}综合楼8楼机房803列103架(省电信144芯横（下）/01-I-008,省电信144芯横（下）/01-I-009)\n
//		String so="三新七村-ODF机柜01-ODF01/02(ODF01-01-002，ODF01-01-003){金山汽车城光交箱-三新七村-ODF机柜01-ODF01/02(FX002，FX003)}金山汽车城光交箱(A-18-002，A-18-003)《跳纤》金山汽车城光交箱(A-05-003，A-05-004){金山汽车城光交箱-挂于虾尾岭35号外墙金鸡村基站光分配箱(FX003，FX004)}挂于虾尾岭35号外墙金鸡村基站光分配箱(01-003，01-004)";
////		if(so.indexOf("、")!=-1 || so.indexOf("*")!=-1) {
////        	so=so.replaceAll("、", ",").replaceAll("*", "!");
////        	System.out.println(so);
////        }
//		so=so.replaceAll("、", ",").replaceAll("\\(13架）", "\\(13架)").replaceAll("\\*", "！");
//		
//		List<String> result = new ArrayList<String>();
//        int m = 0, n = 0, count = 0;
//        for(int i = 0; i < so.length(); i++){
//        	if(so.charAt(i) == '('){
//                if(count == 0) m = i;
//                count ++;
//            }
//
//            if(so.charAt(i) == ')'){
//                count--;
//                if (count == 0) {
//                    n = i;
//                    result.add(so.substring(m+1,n).trim());
//                }
//            }
//        }
//        // 检验括号是否配对
//        if(count==0) {
//    	   String sg="";
//    	   for (int i = 0; i < result.size(); i++) {
//    		   if(result.get(i).indexOf(",")!=-1) {
//    			   
//    			   String[] strs = result.get(i).split(",");
//    			   String s1 = strs[0];
//    			   String s2 = strs[1];
////    			   
////    			   if(reg==1) {
////    				   sg=so.replaceAll(result.get(i), s1);	//截取“，”左侧
////    			   }else {
////    				   sg=so.replaceAll(result.get(i), s2);	//截取“，”右侧
////    			   }
//    			   so=so.replaceAll(result.get(i), s1);	//截取“，”左侧
////    			   so=sg;
//    			   
////    			   System.out.println(s1);
//    		   } if(result.get(i).indexOf("，")!=-1 &&  result.get(i).indexOf("（")==-1){
//    			   String[] strs = result.get(i).split("，");
//    			   String s1 = strs[0];
//    			   String s2 = strs[1];
//    			   so=so.replaceAll(result.get(i), s1);
//    		   }
//    	   }
//        }
//        
//        System.out.println(so.replaceAll("！", "\\*"));
////        String s="三新七村-ODF机柜01-ODF01/02(ODF01-01-002，ODF01-01-003){金山汽车城光交箱-三新七村-ODF机柜01-ODF01/02(FX002，FX003)}金山汽车城光交箱(A-18-002，A-18-003)《跳纤》金山汽车城光交箱(A-05-003，A-05-004){金山汽车城光交箱-挂于虾尾岭35号外墙金鸡村基站光分配箱(FX003，FX004)}挂于虾尾岭35号外墙金鸡村基站光分配箱(01-003，01-004)";
//
//	}

	/**
	 * 
	* @Title: insertCableSection 
	* @Description: TODO(插入光缆段) 
	* @param @param session
	* @param @param list
	* @param @return    入参
	* @return Object    返回类型
	* @author 刘沧海（作者） 
	* @throws
	* @date 2017年12月13日 下午8:10:00 
	* @version V1.0
	 */
	@Override
	public int updateExtractCableSection(List<CableSection> list, Users user) {
		
		int reg=0;
		Cable cab=new Cable();
		CableSection cable=list.get(0);
		if(cable.getSectCode()==null || "".equals(cable.getSectCode())) {
			throw new FrmsException("主光缆编码不能为空！");
		}
		CableSection cs = cableSectionMapper.selectByPrimaryKey(cable.getSectId());
		Long fiberNumSum = 0L;
		if(list.size()>1) {
			for(CableSection c : list) {
				fiberNumSum = fiberNumSum + c.getFiberNum();
			}
			if(cs.getFiberNum()!=fiberNumSum) {
				throw new FrmsException("光缆挂靠前后芯数不相等！");
			}
		}
		
		cab.setCableName(cable.getSecName());
		cab.setCableSectNum((long)list.size()); 
		cab.setOrgId(user.getOrgId());
		cab.setCableCode(cable.getSectCode());
		reg=cableMapper.insertSelective(cab);		//插入第一条到光缆

		
		cable.setCableId(cab.getCableId());			//获取光缆ID
		reg=cableSectionService.modifyCableSection(cable,user);//调用修改接口
		if(list.size()>1) {
			Long fiberNo = cable.getFiberNum();
			for (int i = 1; i < list.size(); i++) {
				list.get(i).setCableId(cab.getCableId());
				list.get(i).setCreateTime(new Date()); 
				list.get(i).setCreateUser(user.getUserId());
				list.get(i).setSectCode(siteCodeService.getNewSectCode());
				CableSection sect = list.get(i);
				reg=cableSectionMapper.insertSelective(sect);

			}
		}
		return reg;
	}
	
	/**
	 * 
	* @Title: updateAttachedCableSection 
	* @Description: TODO(挂靠光缆段) 
	* @param @param session
	* @param @param list
	* @param @return    入参
	* @return Object    返回类型
	* @author 尹海涛（作者） 
	* @throws
	* @date 2017年12月14日 下午12:10:33 
	* @version V1.0
	 */
	public int updateAttachedCableSection(List<CableSection> list, Users user) {
		
		Cable cab = null; 
		int reg=0;
		
		String port = "A";
		CableSection cable=list.get(0);
		//搜索光缆对应纤芯
		List<Lines> lineList = linesMapper.selectBySectId(cable.getSectId());
		CableSection cs = cableSectionMapper.selectByPrimaryKey(cable.getSectId());
		//搜索光缆业务描述
		List<CableSectionDec> decList = cableSectionDecMapper.selectBySectIdAndGreatFiberNum(cable.getSectId(), cable.getFiberNum(), cs.getFiberNum());
		if(cable.getSectCode()==null || "".equals(cable.getSectCode()) || cable.getSectId()==null) {
			throw new FrmsException("主光缆ID和编码不能为空！");
		}
		//判断AZ端
		if(cable.getDevIdZ().longValue()==list.get(1).getDevIdZ().longValue()) {
			port = "Z";
		}
		for(Lines line : lineList) {
			if("A".equals(port) && (line.getZcode()!=null && !"".equals(line.getZcode()))) {
				throw new FrmsException("主光缆对端已成端,请解成端后再进行该操作！");
			}
			if("Z".equals(port) && (line.getAcode()!=null && !"".equals(line.getAcode()))) {
				throw new FrmsException("主光缆对端已成端,请解成端后再进行该操作！");
			}
		}
		
		Long fiberNumSum = 0L;
		if(list.size()>1) {
			for(CableSection c : list) {
				fiberNumSum = fiberNumSum + c.getFiberNum();
			}
			if(cs.getFiberNum()!=fiberNumSum) {
				throw new FrmsException("光缆挂靠前后芯数不相等！");
			}
		}
		cab = cableMapper.selectByCableCode(cable.getSectCode());
		if(cab == null) {
			cab = new Cable();
			cab.setCableName(cable.getSecName());
			cab.setCableSectNum((long)list.size()); 
			cab.setOrgId(user.getOrgId());
			cab.setCableCode(cable.getSectCode());
			reg=cableMapper.insertSelective(cab);	
		}
		//插入第一条到光缆
		
		cable.setCableId(cab.getCableId());			//获取光缆ID
//		reg=cableSectionService.modifyCableSection(cable,user);//调用修改接口
		cableSectionMapper.updateByPrimaryKeySelective(cable);
		if(list.size()>1) {
			Long fiberNo = cable.getFiberNum();
			for (int i = 1; i < list.size(); i++) {
				list.get(i).setCableId(cab.getCableId());
				list.get(i).setCreateTime(new Date());
				list.get(i).setCreateUser(user.getUserId());
				list.get(i).setSectCode(siteCodeService.getNewSectCode());
				CableSection sect = list.get(i);
				reg=cableSectionMapper.insertSelective(sect);
				CableSectionDec caDesc = null;
				for(int j=1;i<lineList.size()+1;i++) {
					if(j>fiberNo) {
						if("A".equals(port)) {
							lineList.get(j).setZdevId(sect.getDevIdZ());
							lineList.get(j).setSectId(sect.getSectId());
							lineList.get(j).setFiberNo(j-fiberNo);
						}
						
						if("Z".equals(port)) {
							lineList.get(j).setAdevId(sect.getDevIdA());
							lineList.get(j).setSectId(sect.getSectId());
							lineList.get(j).setFiberNo(j-fiberNo);
						}
						linesMapper.updateByPrimaryKeySelective(lineList.get(j));
						
						caDesc = new CableSectionDec();
						caDesc.setFiberNo(j-fiberNo) ;
						caDesc.setFiberState("2");
						caDesc.setSectId(sect.getSectId());
						caDesc.setCreateUser(user.getUserId());
						caDesc.setCreateTime(new Date());
						caDesc.setOrgId(user.getOrgId());
						cableSectionDecMapper.insertSelective(caDesc);
						caDesc = null;
					}
				}
				fiberNo = fiberNo + sect.getFiberNum();
			}
		}
		if(decList!=null && decList.size()>0) {
			for(CableSectionDec csd : decList) {
				if("2".equals(csd.getFiberState())) {
					csd.setFiberState("3");
				}else {
					csd.setFiberState("0");
				}
				csd.setModifyTime(new Date());
				csd.setModifyUser(user.getUserId());
				cableSectionDecMapper.updateByPrimaryKeySelective(csd);
			}
				
		}
		
		return reg;
	}

	/**
	 * 
	* @Title: queryDirectmelCableSection 
	* @Description: 获取可能经过此设施并进行直熔的光缆  
	* @param @param session
	* @param @param CableSectionBean
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月16日 上午11:05:36 
	* @version V1.0
	 */
	@Override
	public List<CableSection> queryDirectmelCableSection(CableSectionBean cableSectionBean,PageBean pb) {
		
		CableSectionBean listIs=cableSectionMapper.queryByNotisDevId(cableSectionBean);
		if(listIs!=null ) {
			List<CableSection> cableSecList=cableSectionMapper.queryForInIds(cableSectionBean,listIs.getIds(),pb);
			return cableSecList;
		}else {
			return null;
		}
	}
	/**
	 * 
	* @Title: queryCableSectionAboutInvestment 
	* @Description: 查询光缆段列表，返回直融信息 
	* @param @param devId
	* @param @param orgId
	* @param @return    入参
	* @return Object    返回类型
	* @author 周宇 
	* @throws
	* @date 2017年12月17日 下午2:24:23 
	* @version V1.0
	 */
	@Override
	public List<CableSectionBean> queryCableSectionAboutInvestment(long devId, long orgId) {
		List<CableSectionBean> cablesectionlist = cableSectionMapper.queryNODeleteCSByDevId(devId);
		isInvestment(cablesectionlist);
		return cablesectionlist;
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
	 * 根据设施ID查询所属光缆段
	 */
	@Override
	public List<CableSection> querySectByDevId(Long orgId, Long devId) {
		// TODO Auto-generated method stub
		List<CableSection> list = cableSectionMapper.queryNoDeleteByDevId(devId);
		if(list!=null && list.size()>0) {
			for(CableSection cs : list) {
				List<CablesectionInvest> ciList = cablesectionInvestMapper.selectByUpSectId(cs.getSectId());
				String fiberInfo = "";
				for(CablesectionInvest ci : ciList) {
					if("".equals(fiberInfo)) {
						fiberInfo = ci.getFiberNoStart() + "-" + ci.getFiberNoEnd();
					}
					fiberInfo = fiberInfo + "," +ci.getFiberNoStart() + "-" + ci.getFiberNoEnd();
				}
				cs.setFiberInfo(fiberInfo);
			}
		}
		return list;
	}

	@Override
	public int pdhcopy() {
		int reg=0;
		List<TnExport> list=tnExportMapper.pdhcopy();
		TnExport tn=null;
		for (int i = 0; i < list.size(); i++) {
			
			tn=new TnExport();
			tn.setA(list.get(i).getA());
			tn.setB(list.get(i).getB());
			tn.setC(list.get(i).getC());
			reg=tnExportMapper.insertSelective01(tn);
			tn=null;
		}
		return reg;
	}
	//根据A端所属光缆名称和Z段所属光缆名称模糊查询光缆段
	@Override
	public List<CableSectionBean> queryCSBySecName(String devNameA, String devNameZ,PageBean pb) {
		if(devNameA==null) {
			devNameA="";
		}
		if(devNameZ==null) {
			devNameZ="";
		}
		return cableSectionMapper.queryCSBySecName(devNameA,devNameZ,pb);
	}

	/**
	 * 根据设施ID 查询机房关联光缆段和两端设施
	 * fl
	 */
	@Override
	public List<CableSectionBean> queryCableSectionsByDevId(Long devId, Long orgId) {
		 List<CableSectionBean> cableSectionList =cableSectionMapper.queryCableSectionsByDevId(devId,orgId);
		 if(cableSectionList!=null && cableSectionList.size()>0) {
			 for(CableSectionBean cableJifang:cableSectionList) {
				 cableJifang.setIsJF("1");//机房
			 }
		 }
		 List<Facility> facilityList = facilityMapper.selectByPdevId(devId);
		 if(facilityList!=null && facilityList.size()>0) {
			 for (int i = 0; i < facilityList.size(); i++) {
				 List<CableSectionBean> cabineList = cableSectionMapper.queryCableSectionsByDevId(facilityList.get(i).getDevId(),orgId);
				 if(cabineList!=null && cabineList.size()>0) {
					 cableSectionList.addAll(cabineList);//机柜
				 }
			}
		 }
			 
		 return cableSectionList;
	}

	/**
	 * 
	* @Title: queryCableSectionWell 
	* @Description: 根据光缆段查询光联井信息 
	* @param @param sectId
	* @param @return    
	* @return List<Well>
	* @author liucanghai 
	* @throws
	* @date 2018年1月3日 下午1:50:24 
	* @version V1.0
	 */
	@Override
	public List<WellBean> queryCableSectionWell(Long sectId,Long orgId) {
		List<WellBean> wellList=wellMapper.queryBySectId(sectId,orgId);//查询井信息
//		if(wellList!=null && wellList.size()>0) {
//			for (int i = 0; i < wellList.size(); i++) {
//				List<PipingSection> pipingList = pipingSectionMapper.queryByWellId(wellList.get(i).getWellId());
//				if(pipingList!=null && pipingList.size()>0) {
//					wellList.get(i).setPipingSectionList(pipingList);//井下的管道段
//				}
//			}
//		}
		return wellList;
	}
	/**
	 * 
	* @Title: queryCableSectionAndAZDescBySectId 
	* @Description: 根据devId查询光缆段信息及两端信息
	* @param @param sectId
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author zy 
	* @throws
	* @date 2018年1月11日 下午2:48:29 
	* @version V1.0
	 */
	@Override
	public List<CableSectionResultVo> queryCableSectionAndAZDescBySectId(Long sectId, Long orgId) {
		List<CableSectionResultVo> csbLsit = cableSectionMapper.queryCableSectionAndAZDescBySectId(sectId,orgId);
		if(csbLsit != null && csbLsit.get(0) != null) {
			if("ODF".equals(csbLsit.get(0).getAdevType())) {
				Facility af = facilityService.selectById(csbLsit.get(0).getAdevId());
				csbLsit.get(0).setPdevIdA(af.getPdevId());
			}
			if("ODF".equals(csbLsit.get(0).getZdevType())) {
				Facility zf = facilityService.selectById(csbLsit.get(0).getZdevId());
				csbLsit.get(0).setPdevIdZ(zf.getPdevId());
			}
			ArrayList<CableSectionBean> arrayList = new ArrayList<CableSectionBean>();
			CableSectionBean csb = new CableSectionBean();
			csb.setSectId(csbLsit.get(0).getSectId());
			arrayList.add(csb);
			isInvestment(arrayList);
			csbLsit.get(0).setInvestment(arrayList.get(0).getInvestment());
			csbLsit.get(0).setInused(arrayList.get(0).getInused());
			csbLsit.get(0).setNotInused(arrayList.get(0).getNotInused());
		}
		return csbLsit;
	}

	/**
	 * 
	* @Title: insertionCableSe 
	* @Description: 导入光缆段数据专用方法 
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年1月11日 上午10:13:49 
	* @version V1.0
	 */
	@Override
	public int insertionCableSe() {
		int reg=0;
		List<TnExport> tnList=tnExportMapper.queryRouteText();
		for (int i = 0; i < tnList.size(); i++) {
			if(!"".equals(tnList.get(i).getD())) {
				double ss = Double.valueOf(tnList.get(i).getD());
				double cableLen = ss*0.001;
				BigDecimal b = new BigDecimal(cableLen);
				b=b.setScale(4, BigDecimal.ROUND_HALF_UP);  //四舍五入
				CableSection cs = cableSectionMapper.selectByPrimaryKey(Long.valueOf(tnList.get(i).getA()));
				if(cs !=null) {
					cs.setCableLen(b);
					reg=cableSectionMapper.updateByPrimaryKeySelective(cs);
				}
			}
		}
		return reg;
	}

	public static void main(String[] args) {
		String s="125.6787888";
		BigDecimal b = new BigDecimal(s); 
//		b=b.setScale(2, BigDecimal.ROUND_DOWN); //小数位 直接舍去
		b=b.setScale(4, BigDecimal.ROUND_HALF_UP);  //四舍五入
		System.out.println(b);
	}

	/**
	 * 根据两个devId查询是否存在光缆段
	 */
	@Override
	public List<CableSection> queryexistCableSectionByDevIds(Long devIdA, Long devIdZ) {
		return cableSectionMapper.queryexistCableSectionByDevIds(devIdA,devIdZ);
	}

	/**
	 * 
	 * @Title: queryCaByDevId
	 * @Description: 根据devId和orgId查询
	 * @param @param orgId
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月25日 上午11:25:45
	 * @version V1.0
	 */
	@Override
	public List<CableSection> queryCaByDevId(Long orgId, Long devId) {
		return cableSectionMapper.queryCaByDevId(orgId,devId);
	}

	/**
	 * 根据用户ID 查询未成端光缆
	* @Title: queryNotEndCableSections 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @return    入参
	* @return List<CableSectionNotEndBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午1:21:27 
	* @version V1.0
	 */
	@Override
	public List<CableSectionNotEndBean> queryNotEndCableSections(Long userId,PageBean pb,Long orgId,int isCheckAll,CableSectionNotEndBean bean) {
		return cableSectionMapper.queryNotEndCableSections(userId,pb,orgId,isCheckAll,bean);
	}

	/**
	 * 根据用户ID,查询成端不一致光缆段
	* @Title: querydiscordCableSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @param pb
	* @param @return    入参
	* @return List<CableSectionNotEndBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午2:08:05 
	* @version V1.0
	 */
	@Override
	public List<CableSectionNotEndBean> querydiscordCableSection(Long userId, PageBean pb,Long orgId,int isCheckAll,CableSectionNotEndBean bean) {
		return cableSectionMapper.querydiscordCableSection(userId,pb,orgId,isCheckAll,bean);
	}

	/**
	 * 根据机构ID,查询重复光缆段
	* @Title: queryRepetitionCableSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @return    入参
	* @return List<CableSectionNotEndBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午2:39:32 
	* @version V1.0
	 */
	@Override
	public List<CableSectionNotEndBean> queryRepetitionCableSection(Long userId,Long orgId, PageBean pb,int isCheckAll,String secName) {
		return cableSectionMapper.queryRepetitionCableSection(userId,orgId,pb,isCheckAll,secName);
	}

	/**
	 * 根据设施ID,查询重复光缆段
	* @Title: queryRepetitionCableSection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @param devIdA
	* @param @param devIdZ
	* @param @return    入参
	* @return List<CableSectionNotEndBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年3月15日 下午3:31:22 
	* @version V1.0
	 */
	@Override
	public List<CableSectionNotEndBean> queryRepetitionCableSectionBydevIds(Long orgId, PageBean pb, Long devIdA,
			Long devIdZ) {
		return cableSectionMapper.queryRepetitionCableSectionBydevIds(orgId,pb,devIdA,devIdZ);
	}

	/**
	 * 返回a/z端光纤段列表
	 * @param: orgId		机构id
	 * @param: areaCode1	聚汇区
	 * @throws
	 */
	@Override
	public List<Map<String, Object>> findFiber2(Long orgId, String areaCode1) {
		return cableSectionMapper.findFiberList2(orgId, areaCode1);
	}

	/**
	 * 
	* @Title: queryForDevId 
	* @Description: 根据设施ID查询光缆段集合 
	* @param @param devId
	* @param @return    
	* @return List<CableSection>
	* @author liucanghai 
	* @throws
	* @date 2018年4月16日 下午4:48:27 
	* @version V1.0
	 */
	@Override
	public List<CableSection> queryForDevId(Long devId) {
		List<CableSection> list = cableSectionMapper.queryByDevId(devId);
		if(list!=null) {
			for(CableSection cable:list) {
				if(cable.getDevIdA()!=null && cable.getDevIdZ()!=null) {
					Facility fa = facilityMapper.selectByPrimaryKey(cable.getDevIdA());
					Facility fz = facilityMapper.selectByPrimaryKey(cable.getDevIdZ());
					if(fa.getBaiduX()!=null && fa.getBaiduY()!=null && fz.getBaiduX()!=null && fz.getBaiduY()!=null) {
						cable.setAfacility(fa);
						cable.setZfacility(fz);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 查询所属光缆段及所属井列表
	* @Title: queryopdCableSectionByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<CableSectionWellBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月21日 上午10:23:24 
	* @version V1.0
	 */
	@Override
	public List<CableSectionWellBean> queryopdCableSectionByDevId(Long devId) {
		return cableSectionMapper.queryopdCableSectionByDevId(devId);
	}
}
