package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.util.BeanUtils;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Cable;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableZF;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Project;
import com.ycnet.frms.mapper.CableMapper;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.ProjectMapper;
import com.ycnet.frms.mapper.WellPilelineMapper;
import com.ycnet.frms.service.CableService;
import com.ycnet.frms.util.StringUtil;
import com.ycnet.frms.vo.CableBean;
import com.ycnet.frms.vo.CableConditionBean;
import com.ycnet.frms.vo.CableStat;
import com.ycnet.frms.vo.CablesBean;
import com.ycnet.frms.vo.LinesBean;

@Service("cableService")
@Transactional
public class CableServiceImpl implements CableService{

	@Resource(name="cableMapper")
	private CableMapper cableMapper;
	
	@Resource(name="projectMapper")
	private ProjectMapper projectMapper;
	
	@Resource(name="linesMapper")
	private LinesMapper linesMapper;
	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper cableSectionMapper;
	
	@Resource(name="fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="wellPilelineMapper")
	private WellPilelineMapper wellPilelineMapper;
	
	@Override
	public int save(Cable cable) {
		if(cable.getCableId() ==null)
		{
			return cableMapper.insertSelective(cable);
		}
		else
		{
			cable.setCableCode(null);
			return cableMapper.updateByPrimaryKeySelective(cable);
		}
	}

	@Override
	public Cable selectById(Long cableId) {
		
		return cableMapper.selectByPrimaryKey(cableId);
	}

	@Override
	public List<Cable> selectByParam(CableConditionBean searchBean) {
		return cableMapper.selectByParam(searchBean);
	}

	@Override
	public CableBean convert(Cable cable) {
		if(cable ==null)
			return null;
		CableBean bean = new CableBean();
		BeanUtils.copy(cable, bean);
		Project p = projectMapper.selectByPrimaryKey(cable.getProId());
		if(p!=null)
		{
			bean.setProName(p.getProName());
		}
		//判断是否成端
//		List<Lines> linesList = linesMapper.selectIsRBySectId(bean.getSectId());
//		String isTerminat = "0";
//		String sectNull = "0";
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
//		if("0".equals(sectNull)){
//			bean.setIsTerminat("0");
//		}
		return bean;
	}

	@Override
	public List<CableBean> convert(List<Cable> list) {
		if(list==null||list.size()==0)
			return null;
		List<CableBean> rs = new ArrayList<CableBean>();
		for(Cable cable:list)
		{
			rs.add(convert(cable));
		}
		return rs;
	}

	/**
	 * fl修改
	 */
	@Override
	public PageBean queryByCablesBean(CablesBean bean,PageBean pb,Long orgId){
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("cableCondition", bean);
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", orgId);
		//pb.setRows(cableMapper.queryCountByConditionMap(conditionMap));//;
		//pb.setList(this.convert(cableMapper.queryByConditionMap(conditionMap)));
		if (bean.getIsTerminat().equals("1") || bean.getIsTerminat().equals("2") || bean.getIsTerminat().equals("4")) {
			pb.setRows(cableMapper.queryCountByConditionsMap(conditionMap));
			pb.setList(this.convert(cableMapper.queryByConditionsMap(conditionMap)));//fl修改
		}
		if (bean.getIsTerminat().equals("0")) {
			pb.setRows(cableMapper.queryCountByConditionsMapZero(conditionMap));
			pb.setList(this.convert(cableMapper.queryByConditionsMapZero(conditionMap)));//fl修改
		}
		if (bean.getIsTerminat().equals("3")) {
			pb.setRows(cableMapper.queryCountByConditionsMapFour(conditionMap));
			pb.setList(this.convert(cableMapper.queryByConditionsMapFour(conditionMap)));//fl修改
		}
		//System.out.println(bean.getIsTerminat()+"++++++++++++++++++++++++++++++");
		return pb;
	}


//	/**
//	 * 多线程
//	* @ClassName: ThreadUseExtends 
//	* @Description: TODO
//	* @author fl
//	* @date 2018年1月17日 下午2:22:31 
//	*
//	 */
//	class ThreadUseExtends extends Thread
//	//通过继承Thread类,并实现它的抽象方法run()
//	//适当时候创建这一Thread子类的实例来实现多线程机制
//	//一个线程启动后（也即进入就绪状态）一旦获得CPU将自动调用它的run()方法
//	{
//	        
//	        ThreadUseExtends(int i){}//构造函数
//
//			public void run()
//	        {
//	                try
//	                {
//	                        sleep(10000);//挂起5秒
//	                }
//	                catch (InterruptedException e)
//	                {
//	                        return;
//	                }
//	        }
//	}

	@Override
	public int delete(Long sectId) {
		int r=0;
		r=cableSectionMapper.deleteByPrimaryKey(sectId);
		r=linesMapper.delete1("01",sectId);
		fiberdiscMapper.updateBySectId(sectId);
		return r; 
	}

	@Override
	public Cable queryCableEnd(Long sectId) {
		return cableMapper.queryCableEnd(sectId);
	}

	@Override
	public Cable queryCablesInfo(Long sectId) {
		Cable cable = cableMapper.queryCablesInfo(sectId);
		if(cable != null) {
			Lines lineA = linesMapper.selectBySectOrFib(sectId, 1L);
			Lines lineZ = linesMapper.selectBySectOrFib(sectId, cable.getFiberNum());
			if(lineA != null) {
				cable.setAcode(lineA.getAcode());
				cable.setZcode(lineA.getZcode());
			}
			if(lineZ != null) {
				cable.setAcodeE(lineZ.getAcode());
				cable.setZcodeE(lineZ.getZcode());
			}
		}
		return cable;
	}
	
	//导出纤芯占用详细(光交箱),刘沧海,2017-9-26
	@Override
	public List<Cable> queryPortInfo(Long orgId,String areaCode1) {
		return cableMapper.queryPortInfo(orgId,areaCode1);
	}

	@Override
	public List<CableStat> queryPortStat(Long orgId,String areaCode1) {
		List<CableStat> cablestat=cableMapper.queryPortStat(orgId,areaCode1);
//		for (int i = 0; i < cablestat.size(); i++) {
//			CableStat ca=cableMapper.queryPortStat1(orgId,cablestat.get(i).getSectId());
//				cablestat.get(i).setAseize(ca.getAseize()); //A端占用数量
//				
//				cablestat.get(i).setZseize(ca.getZseize()); //Z端占用数量
//		}
		return cablestat;
	}
	
	//光交箱端口占用详细
	@Override
	public List<Cable> queryCableInfo(Long orgId, String areaCode1) {
		return cableMapper.queryCableInfo(orgId,areaCode1);
	}
	
	//机柜端口占用详细,刘沧海,2017-9-26
	@Override
	public List<Cable> queryPortInfoCablin(Long orgId, String areaCode1) {
		return cableMapper.queryPortInfoCablin(orgId,areaCode1);
	}

	@Override
	public List<Cable> queryCableDevId(Long orgId, Long devId) {
		return cableMapper.queryCableDevId(orgId,devId);
	}

	@Override
	public List<Cable> queryCableInfoCablin(Long orgId, String areaCode1) {
		return cableMapper.queryCableInfoCablin(orgId,areaCode1);
	}

	@Override
	public List<Facility> queryBySectId(Long sectId) {
		CableSection cableSection = cableSectionMapper.selectByPrimaryKey(sectId);
		List<Facility> fList = new ArrayList<Facility>();
		if(cableSection != null) {
			if(cableSection.getDevIdA() != null) {
				Facility facility = facilityMapper.selectByPrimaryKey(cableSection.getDevIdA());
				if(facility != null) { 
//					facility.setIsShow(0);
					if(facility.getBaiduX()!=null && facility.getBaiduY()!=null) {
						fList.add(facility);
					}
				}
			}
			if(cableSection.getDevIdZ() != null) {
				Facility f = facilityMapper.selectByPrimaryKey(cableSection.getDevIdZ());
				if(f!=null) {
//					f.setIsShow(0);
					if(f.getBaiduX()!=null && f.getBaiduY()!=null) {
						fList.add(f);
					}
				}
			}
		}
		
		return fList;
	}

	/**
	 * 查询该id是否为局浅井ID
	 * @param devId
	 * @param sdList
	 * @return
	 */
//	private Long selectDevByWP(Long devId,List<SectDev> sdList){
//		Long fdevId=null;
//		List<WellPileline> wpList = wellPilelineMapper.selectByDevId(devId);
//		if(wpList==null || wpList.size()==0) {
//			return devId;
//		}else {
//			for(WellPileline wellPileline : wpList) {
//				for(SectDev sectDev : sdList) {
//					if(sectDev.getDevId().equals(wellPileline.getFdevId())) {
//						fdevId = selectDevByWP(wellPileline.getFdevId(),sdList);
//					}
//				}
//			}
//			if(fdevId == null) {
//				return devId;
//			}
//			return fdevId;
//		}
//		
//	}

	//导出数据库 刘沧海 2017/10/13
	@Override
	public List<CableZF> queryList(Long orgId) {
		return cableMapper.queryList(orgId);
	}

	/**
	 * web每第一次进入,只查未成端光缆
	* 
	* @Title: CableService.java 
	* @Description: TODO 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return
	* @return Object
	* @author fl
	* @date 2018年1月16日 下午2:11:13
	* @version V1.0
	 */
	@Override
	public PageBean queryCablesByBean(CablesBean bean,PageBean pb, Long orgId) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", orgId);
		conditionMap.put("cableCondition", bean);
		pb.setRows(cableMapper.queryCountCablesByBean(conditionMap));//;
		pb.setList(this.convert(cableMapper.queryCablesByBean(conditionMap)));		
		return pb;
	}
	/**
	 * 查询纠错光缆段
	 * zhouyu
	 */
	@Override
	public PageBean queryErrorRecCableListByCablesBean(CablesBean bean, PageBean pb, Long orgId) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		if(bean.getAreaCode1()!=null && bean.getAreaCode1()!="") {
			bean.setAreadevCode("");
		}
		pb.setPageSize(200);
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", orgId);
		conditionMap.put("cableCondition", bean);
		pb.setRows(cableMapper.queryErrorRecCableListCountByCablesBean(conditionMap));
		List<CablesBean> cableListByCablesBean = cableMapper.queryErrorRecCableListByCablesBean(conditionMap);
		
		for (CablesBean cableBean : cableListByCablesBean) {
			List<Long> investmentsA = new ArrayList<Long>();
			List<Long> investmentsZ = new ArrayList<Long>();
			
			List<Long> inusedA = new ArrayList<Long>();
			List<Long> inusedZ = new ArrayList<Long>();
			List<Long> notInused = new ArrayList<Long>();
					Long sectId = cableBean.getSectId();
					List<LinesBean> lineslist = linesMapper.queryBySectId(sectId);
					for (LinesBean linesBean : lineslist) {
						Long fiberNo = linesBean.getFiberNo();
						String acode = linesBean.getAcode();
						String zcode = linesBean.getZcode();
						//未成端
						if(acode==null&&zcode==null) {
							notInused.add(fiberNo);
						//成端
							//az均不为空
						}else if(acode!=null&&zcode!=null) {
							if(acode.contains("ZRIN")||acode.contains("ZROUT")) {
								investmentsA.add(fiberNo);
								if(zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
									investmentsZ.add(fiberNo);
								}
							}else if(zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
								investmentsZ.add(fiberNo);
							}else {
								inusedA.add(fiberNo);
								inusedZ.add(fiberNo);
							}
						}
						//az一端为空
						else {
							try {
								if(acode.contains("ZRIN")||acode.contains("ZROUT")) {
									investmentsA.add(fiberNo);
								}else {
									inusedA.add(fiberNo);
								}
								
							} catch (Exception e) {
								if(zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
									investmentsZ.add(fiberNo);
								}else {
									inusedZ.add(fiberNo);
								}
							}
							
						}
							
					}
					cableBean.setInvestmentA(StringUtil.sort(investmentsA)!=null?StringUtil.sort(investmentsA):"");
					cableBean.setInvestmentZ(StringUtil.sort(investmentsZ)!=null?StringUtil.sort(investmentsZ):"");
					cableBean.setInusedZ(StringUtil.sort(inusedZ)!=null?StringUtil.sort(inusedZ):"");
					cableBean.setInusedA(StringUtil.sort(inusedA)!=null?StringUtil.sort(inusedA):"");
					cableBean.setNotInused(StringUtil.sort(notInused)!=null?StringUtil.sort(notInused):"");
					
		}
		pb.setList(cableListByCablesBean);		
		return pb;
	}

	/**
	 * 
	* @Title: queryAllCableSection 
	* @Description: 查询光缆段 (用于导出)
	* @param @param areaCode1
	* @param @return    
	* @return List<Cable>
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午4:47:01 
	* @version V1.0
	 */
	@Override
	public List<Cable> queryAllCableSection(CablesBean bean,PageBean pb, Long orgId) {
		pb=null;
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", orgId);
		conditionMap.put("cableCondition", bean);
		return cableMapper.queryCablesByBean(conditionMap);
	}

	/**
	 * 
	* @Title: moDuanSectionExport 
	* @Description: 导出末端光缆成端信息 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return    
	* @return List<Cable>
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午5:14:26 
	* @version V1.0
	 */
	@Override
	public List<Cable> moDuanSectionExport(CablesBean bean, PageBean pb, Long orgId) {
		pb=null;
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", orgId);
		conditionMap.put("cableCondition", bean);
		return cableMapper.queryByConditionsMapZero(conditionMap);
	}

	/**
	 * 
	* @Title: yiDuanSectionExport 
	* @Description: 导出一端成端光缆段信息 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return    
	* @return List<Cable>
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午5:20:23 
	* @version V1.0
	 */
	@Override
	public List<Cable> yiDuanSectionExport(CablesBean bean, PageBean pb, Long orgId) {
		pb=null;
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", orgId);
		conditionMap.put("cableCondition", bean);
		return cableMapper.queryByConditionsMap(conditionMap);
	}

	/**
	 * 
	* @Title: yiDuanSectionExport 
	* @Description: 导出已成端光缆段信息 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return    
	* @return List<Cable>
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午5:20:23 
	* @version V1.0
	 */
	@Override
	public List<Cable> yiChengDuanSectionExport(CablesBean bean, PageBean pb, Long orgId) {
		pb=null;
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", orgId);
		conditionMap.put("cableCondition", bean);
		return cableMapper.queryByConditionsMap(conditionMap);
	}

	/**
	 * 
	* @Title: chongFuSectionExport 
	* @Description: 导出重复光缆成端数据 
	* @param @param bean
	* @param @param pb
	* @param @param orgId
	* @param @return    
	* @return List<Cable>
	* @author liucanghai 
	* @throws
	* @date 2018年1月23日 下午5:24:59 
	* @version V1.0
	 */
	@Override
	public List<Cable> chongFuSectionExport(CablesBean bean, PageBean pb, Long orgId) {
		pb=null;
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", orgId);
		conditionMap.put("cableCondition", bean);
		return cableMapper.queryByConditionsMapFour(conditionMap);
	}

	@Override
	public List<CablesBean> exporterrorRecCable(CablesBean bean, PageBean pb, Long orgId) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		pb.setPageSize(2000);
		conditionMap.put("pb", pb);
		conditionMap.put("orgId", orgId);
		conditionMap.put("cableCondition", bean);
		List<CablesBean> cableListByCablesBean = cableMapper.queryErrorRecCableListByCablesBean(conditionMap);
		for (CablesBean cableBean : cableListByCablesBean) {
			List<Long> investmentsA = new ArrayList<Long>();
			List<Long> investmentsZ = new ArrayList<Long>();
			
			List<Long> inusedA = new ArrayList<Long>();
			List<Long> inusedZ = new ArrayList<Long>();
			List<Long> notInused = new ArrayList<Long>();
					Long sectId = cableBean.getSectId();
					List<LinesBean> lineslist = linesMapper.queryBySectId(sectId);
					for (LinesBean linesBean : lineslist) {
						Long fiberNo = linesBean.getFiberNo();
						String acode = linesBean.getAcode();
						String zcode = linesBean.getZcode();
						//未成端
						if(acode==null&&zcode==null) {
							notInused.add(fiberNo);
						//成端
							//az均不为空
						}else if(acode!=null&&zcode!=null) {
							if(acode.contains("ZRIN")||acode.contains("ZROUT")) {
								investmentsA.add(fiberNo);
								if(zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
									investmentsZ.add(fiberNo);
								}
							}else if(zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
								investmentsZ.add(fiberNo);
							}else {
								inusedA.add(fiberNo);
								inusedZ.add(fiberNo);
							}
						}
						//az一端为空
						else {
							try {
								if(acode.contains("ZRIN")||acode.contains("ZROUT")) {
									investmentsA.add(fiberNo);
								}else {
									inusedA.add(fiberNo);
								}
								
							} catch (Exception e) {
								if(zcode.contains("ZRIN")||zcode.contains("ZROUT")) {
									investmentsZ.add(fiberNo);
								}else {
									inusedZ.add(fiberNo);
								}
							}
							
						}
							
					}
					cableBean.setInvestmentA(StringUtil.sort(investmentsA)!=null?StringUtil.sort(investmentsA):"");
					cableBean.setInvestmentZ(StringUtil.sort(investmentsZ)!=null?StringUtil.sort(investmentsZ):"");
					cableBean.setInusedZ(StringUtil.sort(inusedZ)!=null?StringUtil.sort(inusedZ):"");
					cableBean.setInusedA(StringUtil.sort(inusedA)!=null?StringUtil.sort(inusedA):"");
					cableBean.setNotInused(StringUtil.sort(notInused)!=null?StringUtil.sort(notInused):"");
					
		}
		return cableListByCablesBean;	
	}

}
