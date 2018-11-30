package com.ycnet.frms.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.base.Objects;
import com.ycnet.core.Constants;
import com.ycnet.core.DateState;
import com.ycnet.core.FacilityDevType;
import com.ycnet.core.FrmsException;
import com.ycnet.core.LineType;
import com.ycnet.core.util.StringUtils;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.CableSectionDec;
import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.LineImage;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.LinesZF;
import com.ycnet.frms.bean.PortImg;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderDesign;
import com.ycnet.frms.bean.WorkorderFiberdiscabindA;
import com.ycnet.frms.bean.WorkorderFiberdiscabindZ;
import com.ycnet.frms.bean.WorkorderImplePlans;
import com.ycnet.frms.bean.WorkorderImplePlansImg;
import com.ycnet.frms.bean.WorkorderLightline;
import com.ycnet.frms.bean.WorkorderRoutes;
import com.ycnet.frms.mapper.CableSectionDecMapper;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper;
import com.ycnet.frms.mapper.DiscinfoMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscGroupMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.LineImageMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.PortImgMapper;
import com.ycnet.frms.mapper.WorkorderDesignMapper;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper;
import com.ycnet.frms.mapper.WorkorderImplePlansImgMapper;
import com.ycnet.frms.mapper.WorkorderImplePlansMapper;
import com.ycnet.frms.mapper.WorkorderLightlineMapper;
import com.ycnet.frms.mapper.WorkorderRoutesMapper;
import com.ycnet.frms.service.CableSectionDecService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.LineImageService;
import com.ycnet.frms.service.LinesService;
import com.ycnet.frms.service.RouteBuildService;
import com.ycnet.frms.util.UploadUtil;
import com.ycnet.frms.vo.CableSectionConditionBean;
import com.ycnet.frms.vo.CableSectionDecBean;
import com.ycnet.frms.vo.CableSectionTwos;
import com.ycnet.frms.vo.ConnectTwoCableSectionBean;
import com.ycnet.frms.vo.FiberdiscConditionBean;
import com.ycnet.frms.vo.JumperInfo;
import com.ycnet.frms.vo.JumperInfos;
import com.ycnet.frms.vo.LinesBean;
import com.ycnet.frms.vo.LinesConditionBean;
import com.ycnet.frms.vo.WorkorderImplePlansImgBean;
import com.ycnet.frms.vo.WorkorderJumperInfo;

@Service("linesService")
@Transactional
public class LinesServiceImpl implements LinesService {

	@Resource(name="linesMapper")
	private LinesMapper linesMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	
	@Resource(name="cableSectionDecMapper")
	private CableSectionDecMapper cableSectionDecMapper;
	
	@Resource(name="linesService")
	private LinesService linesService;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="discinfoMapper")
	private DiscinfoMapper discinfoMapper;
	
	@Resource(name="fiberdiscGroupMapper")
	private FiberdiscGroupMapper fiberdiscGroupMapper;
	
	@Resource(name = "lineImageMapper")
	private LineImageMapper lineImageMapper;

	@Resource(name = "fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;
	
	@Resource(name="lineImageService")
	private LineImageService lineImageService; 
	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper cableSectionMapper;
	
	@Resource(name="workorderImplePlansMapper")
	private WorkorderImplePlansMapper workorderImplePlansMapper;
	
	@Resource(name="workorderImplePlansImgMapper")
	private WorkorderImplePlansImgMapper workorderImplePlansImgMapper;
	
	@Resource(name="workorderFiberdiscabindAMapper")
	private WorkorderFiberdiscabindAMapper workorderFiberdiscabindAMapper;
	
	@Resource(name="workorderFiberdiscabindZMapper")
	private WorkorderFiberdiscabindZMapper workorderFiberdiscabindZMapper;
	
	@Resource(name="deviceDiscinfoEntityMapper")
	private DeviceDiscinfoEntityMapper deviceDiscinfoEntityMapper;
	
	@Resource(name="workorderRoutesMapper")
	private WorkorderRoutesMapper workorderRoutesMapper;
	
	@Resource(name = "routeBuildService")
	private RouteBuildService routeBuildService;
	
	@Resource(name="workorderDesignMapper")
	private WorkorderDesignMapper workorderDesignMapper;
	
	@Resource(name="workorderLightlineMapper")
	private WorkorderLightlineMapper workorderLightlineMapper;
	
	@Override
	public int deleteById(Long lineId) {
		return linesMapper.deleteByPrimaryKey(lineId);
	}

	
	@Override
	public int deleteAllFibers(Long sectId) {
		return linesMapper.delete1(LineType.FIBER.toString(),sectId);
	}
	@Override
	public Lines selectById(Long lineId) {
		return linesMapper.selectByPrimaryKey(lineId);
	}

	
	
	@Override
	public List<Lines> queryFiber(LinesConditionBean param) {
		if (param==null)
			param = new LinesConditionBean();
		param.setLineType(LineType.FIBER.toString());
		return linesMapper.queryFiber(param);
	}
	
	@Override
	public List<Lines> queryFiberByFiberNo(Long sectId,Integer fromFiberNum,Integer endFiberNum)
	{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lineType", LineType.FIBER.toString());
		param.put("sectId", sectId);
		param.put("fromFiberNo", fromFiberNum);
		param.put("endFiberNo", endFiberNum);
		return linesMapper.queryFiberByFiberNo(param);
	}

	@Override
	public List<Lines> selectBySect(Long sectId,Long devId,String srvName) {
		LinesConditionBean param = new LinesConditionBean(LineType.FIBER.toString(), sectId, devId,srvName);
		return linesMapper.queryFiber(param);
	}
	
	@Override
	public int addVirtual(Long devId,Constants.FACILITTYPE devType) {
		int ret = 0;
		Facility f = facilityMapper.selectByPrimaryKey(devId);
		if(f==null)
			throw new FrmsException("未知设施。");
		
		FiberdiscConditionBean param = new FiberdiscConditionBean();

		if(Constants.FACILITTYPE.OBD.equals(devType))
		{
			param.setDevId(devId);
			param.setDiscRowNo(1L);
			param.setSide("A");
			List<Fiberdisc> inList = fiberdiscMapper.queryBydev(param);
			param.setSide("B");
			List<Fiberdisc> outList=fiberdiscMapper.queryBydev(param);
			//OBD内部虚拟点 如：GJ0001-FG01-00-00
			String virtualCode =StringUtils.genPoint(f.getDevCode(),0,0);
			//OBD内部左侧虚拟线
			for(Fiberdisc in:inList)
			{
				Lines line = new Lines();
				line.setLineType(LineType.VIRTUAL.toString());
				line.setAcode(in.getPort01());
				line.setAdevId(devId);
				line.setZcode(virtualCode);
				line.setZdevId(devId);
				line.setOrgId(f.getOrgId());
				ret +=linesMapper.insertSelective(line);
			}
			//OBD内部右侧虚拟线
			for(Fiberdisc out:outList)
			{
				Lines line = new Lines();
				line.setLineType(LineType.VIRTUAL.toString());
				line.setAcode(virtualCode);
				line.setAdevId(devId);
				line.setZcode(out.getPort01());
				line.setZdevId(devId);
				line.setOrgId(f.getOrgId());
				ret +=linesMapper.insertSelective(line);
			}
		}else if(Constants.FACILITTYPE.GLT.equals(devType))
		{
			param.setDevId(devId);
			param.setDiscRowNo(1L);
			param.setSide("JTA");
			List<Fiberdisc> inList = fiberdiscMapper.queryBydev(param);
			param.setSide("JTB");
			List<Fiberdisc> outList=fiberdiscMapper.queryBydev(param);
			if(inList==null||inList.size()==0||outList==null||outList.size()==0||inList.size()!=outList.size())
				throw new FrmsException("接头端子数据不正确。");
			for(Fiberdisc in: inList){
				String inPort=in.getPort01();
				String temp = inPort.replace("-JTA-", "-JTB-");
				String outPort =null;
				for(Fiberdisc out:outList){
					if(temp.equals(out.getPort01()))
					{
						outPort=temp;
						break;
					}
				}
				if(outPort==null)
				{
					throw new FrmsException("接头端子错误。");
				}
				
				Lines line = new Lines();
				//更改接头存
				line.setLineType(LineType.JUMPER.toString());
				line.setAcode(inPort);
				line.setAdevId(devId);
				line.setZcode(outPort);
				line.setZdevId(devId);
				line.setOrgId(f.getOrgId());
				ret +=linesMapper.insertSelective(line);
			}
		}
		return ret;
	}

	@Override
	public int addVirtualZG(Long devId,Constants.FACILITTYPE devType) {
		int ret = 0;
		Facility f = facilityMapper.selectByPrimaryKeyZG(devId);
		if(f==null)
			throw new FrmsException("未知设施。");
		
		FiberdiscConditionBean param = new FiberdiscConditionBean();
		param.setDevId(devId);
		param.setDiscRowNo(1L);
		param.setSide("JTA");
		List<Fiberdisc> inList = fiberdiscMapper.queryBydevZG(param);
		param.setSide("JTB");
		List<Fiberdisc> outList=fiberdiscMapper.queryBydevZG(param);
		if(Constants.FACILITTYPE.GLT==devType)
		{
			if(inList==null||inList.size()==0||outList==null||outList.size()==0||inList.size()!=outList.size())
				throw new FrmsException("接头端子数据不正确。");
			for(Fiberdisc in: inList){
				String inPort=in.getPort01();
				String temp = inPort.replace("-JTA-", "-JTB-");
				String outPort =null;
				for(Fiberdisc out:outList){
					if(temp.equals(out.getPort01()))
					{
						outPort=temp;
						break;
					}
				}
				if(outPort==null)
				{
					throw new FrmsException("接头端子错误。");
				}
				
				Lines line = new Lines();
				//更改接头存
				line.setLineType(LineType.JUMPER.toString());
				line.setAcode(inPort);
				line.setAdevId(devId);
				line.setZcode(outPort);
				line.setZdevId(devId);
				line.setOrgId(f.getOrgId());
				ret +=linesMapper.insertSelective(line);
				ret +=linesMapper.insertSelectiveZG(line);
			}
		}
		return ret;
	}
	
	@Override
	public int addFiber(Long sectId, Long count,Long devIdA,Long devIdZ,Long orgId) {
		/**
		 * 查询该光缆段所有的光纤并删除，重新生成
		 */
		int ret = 0;
		List<Lines> lines = selectBySect(sectId,null,null);
		for(Lines l :lines)
		{
			if(l.getAcode()!=null||l.getZcode()!=null)
			{
				throw new FrmsException("尾纤已成端，不能修改。");
			}
			deleteById(l.getLineId());
		}
		
		//获取光缆段A、Z两端设施
		Facility devA = facilityMapper.selectByPrimaryKey(devIdA);
		Facility devZ = facilityMapper.selectByPrimaryKey(devIdZ);
		if(devA==null||devZ==null)
		{
			throw new FrmsException("光缆段A/Z端设施不存在。");
		}
		long num = 0;
		//数量不正确返回0
		if (count==null||((num = count.longValue())<=0L))
			return 0;
		for(long index = 1L ;index<= num;index++)
		{
			Lines l = new Lines();
			l.setLineType(LineType.FIBER.toString());
			l.setSectId(sectId);
			l.setAdevId(devIdA);
			l.setZdevId(devIdZ);
			l.setFiberNo(index);
			l.setOrgId(orgId);
			ret += linesMapper.insertSelective(l);
		}
		return ret;
	}

	@Override
	public int updateFiber(Long sectId, Long oldDevIdA, Long oldDevIdZ,
			Long newDevIdA, Long newDevIdZ) {
		String lineType=LineType.FIBER.toString();
		LinesConditionBean param = new LinesConditionBean();
		param.setLineType(lineType);
		param.setSectId(sectId);
		List<Lines> linesList = linesMapper.queryFiber(param);
		oldDevIdA=oldDevIdA==null?0L:oldDevIdA;
		oldDevIdZ=oldDevIdZ== null?0L:oldDevIdZ;
		Long oldDevId = null, newDevId = null;
		if((oldDevIdA.longValue()==newDevIdA.longValue() && oldDevIdZ.longValue()==newDevIdZ.longValue())||
		   (oldDevIdA.longValue()==newDevIdZ.longValue() && oldDevIdZ.longValue() ==newDevIdA.longValue()	))
		{
			return 1; //ok;
		}
		else if(oldDevIdA.longValue()==newDevIdA.longValue()  && oldDevIdZ.longValue()!=newDevIdZ.longValue())
		{
			oldDevId = oldDevIdZ;
			newDevId = newDevIdZ;
		}else if(oldDevIdA.longValue()!=newDevIdA.longValue() && oldDevIdZ.longValue() ==newDevIdZ.longValue())
		{
			oldDevId = oldDevIdA;
			newDevId = newDevIdA;
		}
		else if(oldDevIdA.longValue()==newDevIdZ.longValue() && oldDevIdZ.longValue()!= newDevIdA.longValue()){
			oldDevId = oldDevIdZ;
			newDevId = newDevIdA;
		}else if(oldDevIdA.longValue() !=newDevIdZ.longValue() && oldDevIdZ.longValue()==newDevIdA.longValue())
		{
			oldDevId = oldDevIdA;
			newDevId = newDevIdZ;
		}
		else{
			throw new FrmsException("不允许同时修改A/Z两端！");
		}
		for(int i = 0 ;i<linesList.size();i++)
		{
			 Lines l = linesList.get(i);
			 Long a = l.getAdevId();
			 Long z = l.getZdevId();
			 if(a!=null && a.longValue()==oldDevId.longValue())
			 {
				 if(l.getAcode()!=null)
					 throw new FrmsException("A/Z已有成端信息，不允许修改！");
				 l.setAdevId(newDevId);
				 linesList.set(i, l);
			 }else if(z!=null && z.longValue()==oldDevId.longValue())
			 {
				 if(l.getZcode()!=null)
					 throw new FrmsException("A/Z已有成端信息，不允许修改！");
				 l.setZdevId(newDevId);
				 linesList.set(i, l);
			 }else{
				 throw new FrmsException("修改纤芯信息错误！");
			 }
		}
		int num = update(linesList);
		if(num != linesList.size()){
			throw new FrmsException("修改纤芯信息数据错误！");
		}
		return num;
	}
	
	@Override
	public int addJumper(Long adevId, String acode, Long zdevId, String zcode,
			String srvName,Long orgId) {
		if(acode==null||"".equals(acode.trim())||zcode==null||"".equals(zcode.trim()))
		{
			throw new FrmsException("跳纤数据不完整！");
		}
		String lineType=LineType.JUMPER.toString();
		LinesConditionBean param = new LinesConditionBean();
		param.setAcode(acode);
		param.setAdevId(adevId);
		param.setZcode(zcode);
		param.setZdevId(zdevId);
		param.setLineType(lineType);
		Lines line = linesMapper.selectJumper(param);
		if(line !=null)
		{
			line.setSrvName(srvName);
			line.setOrgId(orgId);
			return linesMapper.updateByPrimaryKeySelective(line);
		}
		else
		{
			line = new Lines();
			line.setLineType(lineType);
			line.setAcode(acode);
			line.setAdevId(adevId);
			line.setZcode(zcode);
			line.setZdevId(zdevId);
			line.setSrvName(srvName);
			line.setOrgId(orgId);
			return linesMapper.insertSelective(line);
		}
	}
	
	/**
	 * 
	* @Title: addJumper 
	* @Description: TODO(跳纤添加到line) 
	* 修改:跳纤时添加创建人和时间
	* 修改:跳纤时判断端子是否存在跳纤
	* @param @param adevId
	* @param @param acode
	* @param @param zdevId
	* @param @param zcode
	* @param @param srvName
	* @param @param orgId
	* @param @param unknownPointName
	* @param @param userId
	* @param @return    入参
	* @return int    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 下午4:18:46 
	* @version V1.0
	 */
	@Override
	public int addJumper(Long adevId, String acode, Long zdevId, String zcode,
			String srvName,Long orgId,String unknownPointName,Long userId) {
		int ret=0;
		if((acode==null||"".equals(acode.trim()))&&(zcode==null||"".equals(zcode.trim())))
		{
			throw new FrmsException("跳纤数据不完整！");
		}
		String lineType=LineType.JUMPER.toString();
		LinesConditionBean param = new LinesConditionBean();
		param.setAcode(acode);
		param.setAdevId(adevId);
		param.setZcode(zcode);
		param.setZdevId(zdevId);
		param.setLineType(lineType);
		Lines line = linesMapper.selectJumper(param);
		if(line !=null)
		{
			line.setSrvName(srvName);
			line.setOrgId(orgId);
			line.setUnknownPointName(unknownPointName);
			return linesMapper.updateByPrimaryKeySelective(line);
		}
		else
		{
			ret = isExistJumper(acode);
			if(ret > 0) {
				throw new FrmsException("所选端子"+acode+"已跳纤");
			}
			ret = isExistJumper(zcode);
			if(ret > 0) {
				throw new FrmsException("所选端子"+zcode+"已跳纤");
			}
			line = new Lines();
			line.setLineType(lineType);
			line.setAcode(acode);
			line.setAdevId(adevId);
			line.setZcode(zcode);
			line.setZdevId(zdevId);
			line.setSrvName(srvName);
			line.setUnknownPointName(unknownPointName);
			line.setOrgId(orgId);
			line.setCreateTime(new Date());
			line.setCreateUser(userId);
			return linesMapper.insertSelective(line);
		}
	}
	
	@Override
	public int update(Lines lines) {
		return linesMapper.updateByPrimaryKeySelective(lines);
	}

	@Override
	public int updateAllColoumn(Lines lines) {
		return linesMapper.updateByPrimaryKey(lines);
	}
	
	@Override
	public int update(List<Lines> list) {
		int ret = 0;
		for(Lines line:list)
		{
			if(line.getLineId() == null) {
				ret += linesMapper.insertSelective(line);
			}else {
				ret +=linesMapper.updateByPrimaryKeySelective(line);
			}
		}
		return ret;
	}

	@Override
	public int unFiberlocate(Long devId, Long sectId, Integer fromFiberNo,
			Integer endFiberNo) {
		return linesMapper.unFiberlocate(LineType.FIBER.toString(),devId,sectId,fromFiberNo,endFiberNo);
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
	public int unFiberlocate(Long devId, Long sectId,Long userId) {
		return linesMapper.unFiberlocate(LineType.FIBER.toString(),devId,sectId,userId);
	}

	@Override
	public List<Lines> queryByPoint(String code) {
		return linesMapper.queryByPoint(code);
	}
	
	@Override
	public Lines selectLinesByTwoPoint(String acode, String zcode) {
		if(acode ==null||zcode==null||"".equals(acode.trim())||"".equals(zcode.trim()))
			throw new FrmsException("端子不能为空。");
		return linesMapper.selectLinesByTwoPoint(acode, zcode);
	}


	@Override
	public List<LinesBean> selectLineBySect(Long sectId) {
		// TODO Auto-generated method stub
		return linesMapper.selectLineBySect(sectId);
	}

	
	@Override
	public int saveTwinsJumper(JumperInfos jumperInfos, HttpServletRequest request, Users user) {
		JumperInfo j1 = jumperInfos.getJ1();
		JumperInfo j2 = jumperInfos.getJ2();
		
		if(j1==null||j2==null){
			throw new FrmsException("参数错误！");
		}
		if(j1.getDevId()==null||j1.getCode()==null||"".equals(j1.getCode().trim())) {
			throw new FrmsException("当前端子信息错误");
		}
		if(j2.getDevId()==null||j2.getCode()==null||"".equals(j2.getCode().trim())) {
			throw new FrmsException("当前端子信息错误");
		}
//		IF(JUMPERINFOS.GETJ1().GETDEVID().LONGVALUE()!=JUMPERINFOS.GETJ1().GETZDEVID().LONGVALUE()||
//			JUMPERINFOS.GETJ1().GETDEVID().LONGVALUE()!=JUMPERINFOS.GETJ2().GETDEVID().LONGVALUE()||
//			JUMPERINFOS.GETJ1().GETZDEVID().LONGVALUE()!=JUMPERINFOS.GETJ2().GETZDEVID()) {
//			THROW NEW FRMSEXCEPTION("所跳纤端子不在同一设备！");
//		}
		
		if (j1!=null&&j1.getCode().equals(j1.getZcode())) {
			throw new FrmsException("两条跳纤不能相同。");
		}
		int ret = 0;
		ret=facilityService.updateForDecName(j1.getUserId(), j1.getCode(), j1.getZcode());
		ret=facilityService.updateForDecName(j2.getUserId(), j2.getCode(), j2.getZcode());
		ret +=addJumper(j1,request,user);
		ret +=addJumper(j2,request,user);
		if(ret>=2) {
			Facility f1=facilityMapper.selectByPrimaryKey(j1.getZdevId());
			j2.setAdevType(f1.getDevType());
			Facility f2=facilityMapper.selectByPrimaryKey(j2.getZdevId());
			j2.setZdevType(f2.getDevType());
		}
		if(ret<2) {
			throw new FrmsException("绑定跳纤失败！");
		}
		
		return ret;
	}
	
	/**
	 * 单芯跳纤添加
	 * @author: YHT
	 * @date: 2017年9月6日 下午3:53:27 
	 * @Title: addJumper  
	 * @param @param jumperInfo
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	@Override
	public int addJumper(JumperInfo jumperInfo,HttpServletRequest request,Users user) {
		int ret = 0;
		//判断是否存在跳纤
		if(jumperInfo.getCode()!=null && !"".equals(jumperInfo.getCode())) {
			ret = isExistJumper(jumperInfo.getCode());
			if(ret > 0) {
				throw new FrmsException("所选端子"+jumperInfo.getCode()+"已占用");
			}
		}
		if(jumperInfo.getZcode()!=null && !"".equals(jumperInfo.getZcode())) {
			ret = isExistJumper(jumperInfo.getZcode());
			if(ret > 0) {
				throw new FrmsException("所选端子"+jumperInfo.getZcode()+"已占用");
			}
		}
		
		//修改纤芯详情描述本端
		if(jumperInfo.getCode()!=null && !"".equals(jumperInfo.getCode())) {
			List<LinesBean> linecd = linesService.queryLineType("01", jumperInfo.getCode());//查询成端信息,正确数据返回一条
			if(linecd.size()>0) {
				CableSectionDec csda = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(linecd.get(0).getSectId(), linecd.get(0).getFiberNo());
				csda.setModifyTime(new Date());
				csda.setModifyUser(jumperInfo.getUserId());
				if(jumperInfo.getCode().equals(linecd.get(0).getAcode())) {
					csda.setDevADec(jumperInfo.getSrvName());
				}else {
					csda.setDevZDec(jumperInfo.getSrvName());
				}
				ret = cableSectionDecMapper.updateByPrimaryKeySelective(csda);
			}
		}
		
		//修改纤芯详情描述对端
		if(jumperInfo.getZcode()!=null && !"".equals(jumperInfo.getZcode())) {
			List<LinesBean> linecd = linesService.queryLineType("01", jumperInfo.getZcode());//查询成端信息,正确数据返回一条
			if(linecd.size()>0) {
				CableSectionDec csda = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(linecd.get(0).getSectId(), linecd.get(0).getFiberNo());
				csda.setModifyTime(new Date());
				csda.setModifyUser(jumperInfo.getUserId());
				if(jumperInfo.getZcode().equals(linecd.get(0).getAcode())) {
					csda.setDevADec(jumperInfo.getSrvName());
				}else{
					csda.setDevZDec(jumperInfo.getSrvName());
				}
				ret = cableSectionDecMapper.updateByPrimaryKeySelective(csda);
			}
		}
		
		Lines line = new Lines();
		line.setSrvName(jumperInfo.getSrvName());
		line.setAcode(jumperInfo.getCode());
		line.setAdevId(jumperInfo.getDevId());
//		if(!"".equals(jumperInfo.getZcode())) {
//			line.setZcode(jumperInfo.getZcode());
//		}
		if(jumperInfo.getZcode()!=null && !"".equals(jumperInfo.getZcode())) {
			line.setZcode(jumperInfo.getZcode());
		}
		if(jumperInfo.getZdevId()!=null) {
			line.setZdevId(jumperInfo.getZdevId());
		}
		line.setLineType(LineType.JUMPER.toString());
		line.setUnknownPointName(jumperInfo.getUnknownPointName());//尾纤悬空
		line.setSurveyResult("1");
		line.setOrgId(jumperInfo.getOrgId());
		line.setCreateTime(new Date());
		line.setCreateUser(jumperInfo.getUserId());
		ret = linesMapper.insertSelective(line);
		//添加图片描述 周宇17/12/23
		if(ret>0) {
			List<LineImage> list = new ArrayList<LineImage>();
			if(request instanceof MultipartHttpServletRequest)
			{
				MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
				List<MultipartFile> listfile = req.getFiles("images");
				for (int j = 0; j < listfile.size(); j++) {
					if (listfile!=null&&!listfile.isEmpty()) {
						LineImage li = new LineImage();
								
						String image = UploadUtil.uploadFile(listfile.get(j), req,user.getOrgId(), "02");//"02"添加端子图片
						li.setImgUrl(image);
						li.setCreateTime(new Date());
						li.setCreateUser(user.getUserId());
						list.add(li);
					}
				}
			}
			String code = jumperInfo.getCode();
			String zcode = jumperInfo.getZcode();
			if(Objects.equal(code, "")) {
				code=null;
			}
			if(Objects.equal(zcode, "")) {
				zcode=null;
			}
			Lines line2 = linesMapper.selectLinesByTwoPointZY(code,zcode);
			ret += linesService.insertLineImg(list,line2.getLineId());
		}
		return ret;
	}
	
	/**
	 * 判断是否存在跳纤
	 * @author: YHT
	 * @date: 2017年9月6日 下午3:45:53 
	 * @Title: isExistJumper  
	 * @param @param code
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	public int isExistJumper(String code) {
		return linesMapper.isExistJumperByCode(code);
	}

	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<LinesZF> queryList(Long orgId,String areaCode) {
		return linesMapper.queryList(orgId,areaCode);
	}

	/**
	 * 判断是否存在跳纤
	 * @author: lch
	 * @date: 2017/11/27 
	 * @Title: insertCableSectionDev  
	 * @param @param code
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	@Override
	public int insertCableSectionDev(Long userId) {
		CableSectionDec cableSectionDec=null;
		Fiberdisc fd=null; 
		List<Lines> list01=linesMapper.queryLinsFor01();
		int reg = 0;
		for (int i = 0; i < list01.size(); i++) {
			cableSectionDec=new CableSectionDec();
			cableSectionDec.setFiberState("2");//纤芯状态
			cableSectionDec.setSectId(list01.get(i).getSectId());
			cableSectionDec.setFiberNo(list01.get(i).getFiberNo());
			cableSectionDec.setModifyTime(new Date());
			cableSectionDec.setModifyUser(userId);
			cableSectionDec.setOrgId(list01.get(i).getOrgId());//机构ID
			if(list01.get(i).getFiberNo()!=null) {
				cableSectionDec.setFiberNo(list01.get(i).getFiberNo());//纤芯序号
			}else {
				cableSectionDec=null;
				continue;
			}
			if(!"".equals(list01.get(i).getAcode()) ) {
				Lines listAcode=linesMapper.queryLinsForAcode(list01.get(i).getAcode());
				if(listAcode!=null) {
					fd=new Fiberdisc();
					fd.setIsOccup("1");
					fd.setPort01(listAcode.getAcode());
					fd.setLastModifyTime(new Date());
					fd.setLastModifyUser(userId);
					fiberdiscMapper.updateByIsOccup(fd);
					if(listAcode.getSrvName()!=null) {
						cableSectionDec.setDevADec(listAcode.getSrvName());//A端业务描述
					}
					fd=null;
				}
			}
			if(!"".equals(list01.get(i).getZcode()) ) {
				Lines listZcode=linesMapper.queryLinsForAcode(list01.get(i).getZcode());
				if(listZcode!=null) {
					fd=new Fiberdisc();
					fd.setIsOccup("1");
					fd.setPort01(listZcode.getZcode());
					fd.setLastModifyTime(new Date());
					fd.setLastModifyUser(userId);
					fiberdiscMapper.updateByIsOccup(fd);
					if(listZcode.getSrvName()!=null) {
						cableSectionDec.setDevZDec(listZcode.getSrvName());//Z端业务描述
					}
					fd=null;
				}
			}
			reg=cableSectionDecMapper.insertSelective(cableSectionDec);
			
			cableSectionDec=null;
			
		}
		return reg;
	}

	/**
	 * 
	* @Title: queryCodeByZg 
	* @Description: TODO(根据端子查询资管详情) 
	* @param @param code
	* @param @return    入参
	* @return CableSectionDec    返回类型
	* @author liucanghai（作者） 
	* @throws
	* @date 2017年11月28日 上午9:20:42 
	* @version V1.0
	 */
	@Override
	public CableSectionDecBean queryCodeByZg(String code) {
		return cableSectionDecMapper.queryZgSectionDevZG(code);
	}

	/**
	 * 根据SectId和FiberNo查询一条
	 */
	@Override
	public Lines queryBySectIdAndFiberNo(Long sectId, Long fiberNo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sectId", sectId);
		map.put("fiberNo", fiberNo);
		return linesMapper.queryBySectIdAndFiberNo(map);
	}

	/**
	 * 更改跳纤接口,包括(解跳纤,更改跳纤)
	 */
	@Override
	public int changeJumpper(Users user, LinesBean linesBean, HttpServletRequest request) {
		int num = 0;
		if("1".equals(linesBean.getChangeType())) {//解跳纤
			num = linesMapper.deleteByPrimaryKey(linesBean.getLineId());//删除跳纤
			if(num<1) {
				return num;
			}
			if(linesBean.getAcode()!=null && !"".equals(linesBean.getAcode())) {//如果A端存在
				num = modifyPort(user,linesBean.getAcode(),"update");//修改端子状态
			}
			
			if(linesBean.getZcode()!=null && !"".equals(linesBean.getZcode())) {//如果Z端存在
				num = modifyPort(user,linesBean.getZcode(),"update");//修改端子状态
			}
		}else if("2".equals(linesBean.getChangeType())){//更改跳纤	[linesBean.getAcode()]本端	[linesBean.getZcode()]对端
			int saveImage = 0;
			if(linesBean.getZcode() != null && !"".equals(linesBean.getZcode())) {
				Fiberdisc fib = fiberdiscMapper.queryByPort(linesBean.getZcode());
				if(fib==null) {
					throw new FrmsException("目标跳纤点不存在");
				}else {
					linesBean.setZdevId(fib.getDevId());
				}
				List<LinesBean> linejr = linesService.queryLineType("02",linesBean.getZcode());//查询接入端信息,判断是否存在跳纤
				if(linejr != null && linejr.size()>0) {//如果存在
					//判断接入端是否为尾纤悬空或者未知设施
					if((linesBean.getZcode().equals(linejr.get(0).getAcode()) && (linejr.get(0).getZcode()==null || "".equals(linejr.get(0).getZcode()))) || (linesBean.getZcode().equals(linejr.get(0).getZcode()) && (linejr.get(0).getAcode()==null || "".equals(linejr.get(0).getAcode())))) {
						throw new FrmsException("接入端不能为尾纤悬空或者未知设施");
					}
					//如果不做更改，不提示目标跳纤点已存在
					if((!linesBean.getZcode().equals(linejr.get(0).getZcode()) && !linesBean.getAcode().equals(linejr.get(0).getAcode())) || (!linesBean.getZcode().equals(linejr.get(0).getAcode()) && !linesBean.getAcode().equals(linejr.get(0).getZcode()))) {
						if(linesBean.getSrvName()!=null && !linesBean.getSrvName().equals(linejr.get(0).getSrvName())) {
							Lines line = new Lines();
							line.setLineId(linejr.get(0).getLineId());
							line.setSrvName(linesBean.getSrvName());
							line.setLastModifyTime(new Date());
							line.setLastModifyUser(user.getUserId());
							linesMapper.updateByPrimaryKeySelective(line);
							//保存图片
							saveImage = linesService.saveImages(linesBean.getLineId(),request,user);
							//修改纤芯详情描述A
							List<LinesBean> linecd = linesService.queryLineType("01", linesBean.getAcode());//查询成端信息,正确数据返回一条
							if(linecd.size()>0) {
								CableSectionDec csda = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(linecd.get(0).getSectId(), linecd.get(0).getFiberNo());
								csda.setModifyTime(new Date());
								csda.setModifyUser(user.getUserId());
								if(linesBean.getAcode().equals(linecd.get(0).getAcode())) {
									csda.setDevADec(linesBean.getSrvName());
								}else {
									csda.setDevZDec(linesBean.getSrvName());
								}
								num = cableSectionDecMapper.updateByPrimaryKeySelective(csda);
							}
							
							//修改纤芯详情描述Z
							if(linesBean.getZcode()!=null && !"".equals(linesBean.getZcode())) {
								List<LinesBean> linecdz = linesService.queryLineType("01", linesBean.getZcode());//查询成端信息,正确数据返回一条
								if(linecdz.size()>0) {
									CableSectionDec csdz = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(linecdz.get(0).getSectId(), linecdz.get(0).getFiberNo());
									csdz.setModifyTime(new Date());
									csdz.setModifyUser(user.getUserId());
									if(linesBean.getZcode().equals(linecdz.get(0).getAcode())) {
										csdz.setDevADec(linesBean.getSrvName());
									}else{
										csdz.setDevZDec(linesBean.getSrvName());
									}
									num = cableSectionDecMapper.updateByPrimaryKeySelective(csdz);
								}
							}
							
							num = 1;
							return num;
						}
					}else {
						num = 4;
						return num;
					}
				}
			}
			
			//保存图片
			saveImage = linesService.saveImages(linesBean.getLineId(),request,user);
			
			Lines line = linesMapper.selectByPrimaryKey(linesBean.getLineId());//根据ID查询原跳纤信息
//			String lineType = "01";//查成端
			
			if(linesBean.getAcode().equals(line.getAcode())) {//如果本端等于acode
				String zcode = line.getZcode();//对端,原跳纤端
				if(linesBean.getZcode()==null || "".equals(linesBean.getZcode())) {
					line.setZcode(null);//尾纤悬空
					line.setZdevId(null);
					line.setUnknownPointName(linesBean.getUnknownPointName());
				}else {
					line.setZcode(linesBean.getZcode());//更改跳纤
				}
				if(linesBean.getZcode()!=null && !"".equals(linesBean.getZcode())) {
					Fiberdisc fiberdisc = fiberdiscMapper.queryByPort(linesBean.getZcode());//根据code查端子
					line.setZdevId(fiberdisc.getDevId());//更改设施
				}
				line.setLastModifyTime(new Date());
				line.setLastModifyUser(user.getUserId());
				line.setSrvName(linesBean.getSrvName());//业务描述
				line.setSurveyResult("1");
				num = linesMapper.updateByPrimaryKey(line);
				if(num<1) {
					return num;
				}
				
				//修改纤芯详情描述A
				List<LinesBean> linecd = linesService.queryLineType("01", linesBean.getAcode());//查询成端信息,正确数据返回一条
				if(linecd.size()>0) {
					CableSectionDec csda = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(linecd.get(0).getSectId(), linecd.get(0).getFiberNo());
					csda.setModifyTime(new Date());
					csda.setModifyUser(user.getUserId());
					if(linesBean.getAcode().equals(linecd.get(0).getAcode())) {
						csda.setDevADec(linesBean.getSrvName());
					}else {
						csda.setDevZDec(linesBean.getSrvName());
					}
					num = cableSectionDecMapper.updateByPrimaryKeySelective(csda);
				}
				
				//修改纤芯详情描述Z
				if(linesBean.getZcode()!=null && !"".equals(linesBean.getZcode())) {
					List<LinesBean> linecdz = linesService.queryLineType("01", linesBean.getZcode());//查询成端信息,正确数据返回一条
					if(linecdz.size()>0) {
						CableSectionDec csdz = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(linecdz.get(0).getSectId(), linecdz.get(0).getFiberNo());
						csdz.setModifyTime(new Date());
						csdz.setModifyUser(user.getUserId());
						if(linesBean.getZcode().equals(linecdz.get(0).getAcode())) {
							csdz.setDevADec(linesBean.getSrvName());
						}else{
							csdz.setDevZDec(linesBean.getSrvName());
						}
						num = cableSectionDecMapper.updateByPrimaryKeySelective(csdz);
					}
				}
				
				if(zcode!=null && !"".equals(zcode)) {//如果对端不为空
					num = modifyPort(user,zcode,"update");//修改端子状态
				}
				
				if(linesBean.getZcode()!=null && !"".equals(linesBean.getZcode())){//如果接入端存在
					num = modifyPort(user,linesBean.getZcode(),"insert");//修改端子状态
				}
			}else if(linesBean.getAcode().equals(line.getZcode())) {//如果本端等于zcode
				String acode = line.getAcode();//对端,原跳纤端
				if(linesBean.getZcode()==null || "".equals(linesBean.getZcode())) {
					line.setAcode(null);//尾纤悬空
					line.setAdevId(null);
					line.setUnknownPointName(linesBean.getUnknownPointName());
				}else {
					line.setAcode(linesBean.getZcode());//更改跳纤
				}
				if(linesBean.getZcode()!=null && !"".equals(linesBean.getZcode())) {
					Fiberdisc fiberdisc = fiberdiscMapper.queryByPort(linesBean.getZcode());//根据code查端子
					line.setAdevId(fiberdisc.getDevId());//更改设施
				}
//				Fiberdisc fiberdisc = fiberdiscMapper.queryByPort(linesBean.getZcode());//根据code查端子
//				line.setZdevId(fiberdisc.getDevId());//更改设施
				line.setLastModifyTime(new Date());
				line.setLastModifyUser(user.getUserId());
				line.setSrvName(linesBean.getSrvName());//业务描述
				num = linesMapper.updateByPrimaryKey(line);
				if(num<1) {
					return num;
				}
				
				//修改纤芯详情描述A
				List<LinesBean> linecd = linesService.queryLineType("01", linesBean.getZcode());//查询成端信息,正确数据返回一条
				if(linecd.size()>0) {
					CableSectionDec csda = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(linecd.get(0).getSectId(), linecd.get(0).getFiberNo());
					csda.setModifyTime(new Date());
					csda.setModifyUser(user.getUserId());
					if(linesBean.getZcode().equals(linecd.get(0).getAcode())) {
						csda.setDevADec(linesBean.getSrvName());
					}
					if(linesBean.getZcode().equals(linecd.get(0).getZcode())) {
						csda.setDevZDec(linesBean.getSrvName());
					}
					num = cableSectionDecMapper.updateByPrimaryKeySelective(csda);
				}
				
				//修改纤芯详情描述Z
				if(linesBean.getAcode()!=null && !"".equals(linesBean.getAcode())) {
					List<LinesBean> linecdz = linesService.queryLineType("01", linesBean.getAcode());//查询成端信息,正确数据返回一条
					if(linecdz.size()>0) {
						CableSectionDec csdz = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(linecdz.get(0).getSectId(), linecdz.get(0).getFiberNo());
						csdz.setModifyTime(new Date());
						csdz.setModifyUser(user.getUserId());
						if(linesBean.getAcode().equals(linecdz.get(0).getAcode())) {
							csdz.setDevADec(linesBean.getSrvName());
						}
						if(linesBean.getAcode().equals(linecdz.get(0).getZcode())) {
							csdz.setDevZDec(linesBean.getSrvName());
						}
						num = cableSectionDecMapper.updateByPrimaryKeySelective(csdz);
					}
				}
				
				if(acode!=null && !"".equals(acode)) {//如果对端不为空
					num = modifyPort(user,acode,"update");//修改端子状态
				}
				
				if(linesBean.getZcode()!=null && !"".equals(linesBean.getZcode())){//如果接入端存在
					num = modifyPort(user,linesBean.getZcode(),"insert");//修改端子状态
				}
			}
			if(saveImage>0 && num>0) {
				num = 1;
				return num;
			}
		}
		return num;
	}
	
	/**
	 * 根据code查询是否成端
	 */
	@Override
	public List<LinesBean> queryLineType(String lineType,String code) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lineType", lineType);
		map.put("code", code);
		return linesMapper.queryLineType(map);
	}
	
	/**
	 * 
	 * @Title: modifyPort
	 * @Description: 修改端子状态
	 * @param @param user
	 * @param @param code
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月1日 下午2:54:07
	 * @version V1.0
	 * @param falg 
	 */
	private int modifyPort(Users user, String code, String flag) {
		Fiberdisc fiberdisc = fiberdiscMapper.queryByPort(code);//根据code查端子
		int num = 0;
		if(fiberdisc!=null) {
			fiberdisc.setLastModifyTime(new Date());
			fiberdisc.setLastModifyUser(user.getUserId());
			if("update".equals(flag)) {
				fiberdisc.setIsOccup("0");//未占用
			}else if("insert".equals(flag)){
				fiberdisc.setIsOccup("1");//已占用
			}
			num = fiberdiscMapper.updateByPrimaryKeySelective(fiberdisc);
		}
		return num;//修改端子状态;
	}

	/**
	 * 根据sectId查询数据
	 */
	@Override
	public List<LinesBean> queryBySectId(Long sectId) {
		return linesMapper.queryBySectId(sectId);
	}
	
	/**
	 * 根据修改光缆信息。修改纤芯信息
	* @Title: updateLine 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param cableSection
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月3日 上午11:54:34 
	* @version V1.0
	 */
	@Override
	public int updateLine(CableSection cableSection,CableSection old) {
		// TODO Auto-generated method stub
		String lineType=LineType.FIBER.toString();
		LinesConditionBean param = new LinesConditionBean();
		param.setLineType(lineType);
		param.setSectId(cableSection.getSectId());
		List<Lines> newLineList = new ArrayList<Lines>();
		Lines newLine = null;
		List<Lines> linesList = linesMapper.queryFiber(param);
		for(int i = 0 ;i<linesList.size();i++)
		{
			 Lines line = linesList.get(i);
			 String acode = line.getAcode();
			 String zcode = line.getZcode();
			 if(cableSection.getFiberNum().longValue() != old.getFiberNum().longValue()) {
				 if(acode!=null) {
					 throw new FrmsException("光缆A端已成端,请解成端后再修改！");
				 }
				 if(zcode!=null) {
					 throw new FrmsException("光缆Z端已成端,请解成端后再修改！");
				 }
			 }
			 if(DateState.NEWLY.toString().equals(cableSection.getSectState())) {
				 if(cableSection.getDevIdA().longValue()!=old.getDevIdA().longValue()) {
					 if(acode!=null) {
						 throw new FrmsException("光缆A端已成端,请解成端后再修改！");
					 }
				 } 
				 if(cableSection.getDevIdZ().longValue()!=old.getDevIdZ().longValue()) {
					 if(zcode!=null) {
						 throw new FrmsException("光缆A端已成端,请解成端后再修改！");
					 }
				 }
				 line.setAdevId(cableSection.getDevIdA());
				 line.setZdevId(cableSection.getDevIdZ());
				 line.setLastModifyTime(new Date());
				 line.setLastModifyUser(cableSection.getLastModifyUser());
			 }else if(DateState.NOCHECK.toString().equals(old.getSectState())){
				 if(cableSection.getDevIdA().longValue()!=cableSection.getZgDevIdA().longValue()) {
					 if(acode!=null) {
						 throw new FrmsException("光缆Z端已成端,请解成端后再修改！");
					 }
				 } 
				 if(cableSection.getDevIdZ().longValue()!=cableSection.getZgDevIdZ().longValue()) {
					 if(zcode!=null) {
						 throw new FrmsException("光缆Z端已成端,请解成端再后修改！");
					 }
				 }
				 line.setAdevId(cableSection.getDevIdA());
				 line.setZdevId(cableSection.getDevIdZ());
				 line.setLastModifyTime(new Date());
				 line.setLastModifyUser(cableSection.getLastModifyUser());
			 }else {
				 if(cableSection.getDevIdA().longValue()!=old.getDevIdA().longValue()) {
					 if(acode!=null) {
						 throw new FrmsException("光缆Z端已成端,请解成端后再修改！");
					 }
				 } 
				 if(cableSection.getDevIdZ().longValue()!=old.getDevIdZ().longValue()) {
					 if(zcode!=null) {
						 throw new FrmsException("光缆Z端已成端,请解成端再后修改！");
					 }
				 }
				 line.setAdevId(cableSection.getDevIdA());
				 line.setZdevId(cableSection.getDevIdZ());
				 line.setLastModifyTime(new Date());
				 line.setLastModifyUser(cableSection.getLastModifyUser());
			 }
		}
		Long oldFiberNum = null;
		if(DateState.NOCHECK.toString().equals(old.getSectState())) {
			oldFiberNum = old.getZgFiberNum();
		}else {
			oldFiberNum = old.getFiberNum();
		}
		if(cableSection.getFiberNum() > oldFiberNum) {
			newLineList.addAll(linesList);
			for(long j=oldFiberNum+1;j<cableSection.getFiberNum()+1;j++) {
				newLine = new Lines();
				newLine.setLineType(LineType.FIBER.toString());
				newLine.setAdevId(cableSection.getDevIdA());
				newLine.setZdevId(cableSection.getDevIdZ());
				newLine.setCreateTime(new Date());
				newLine.setCreateUser(cableSection.getLastModifyUser());
				newLine.setSurveyResult("0");
				newLine.setOrgId(cableSection.getOrgId());
				newLine.setSectId(cableSection.getSectId());
				newLine.setFiberNo(j);
				newLineList.add(newLine);
				newLine = null;
			}
		}else if(cableSection.getFiberNum() < oldFiberNum){
			if(old.getSectId() != null) {
				linesMapper.deleteBySectIdAndFiberNum(old.getSectId(),cableSection.getFiberNum());
			}
			newLineList = linesList.subList(0, Integer.parseInt(cableSection.getFiberNum()+""));
		}else {
			newLineList = linesList;
		}
		int num = update(newLineList);
		if(num != newLineList.size()){
			throw new FrmsException("修改纤芯信息数据错误！");
		}
		return num;
	}
	

	@Override
	public List<Lines> queryFiberNumFor() {
		return linesMapper.queryFiberNumFor();
	}

	public static void main(String[] args) {
		List<CableSectionTwos> list=new ArrayList<CableSectionTwos>();
		
		CableSectionTwos two=new CableSectionTwos();
		two.setFiberNoStartA(1L);
		two.setFiberNoEndA(8L);
		two.setFiberNoStartZ(6L);
		two.setFiberNoEndZ(12L);
		list.add(two);
		
		CableSectionTwos two1=new CableSectionTwos();
		two1.setFiberNoStartA(4L);
		two1.setFiberNoEndA(11L);
		two1.setFiberNoStartZ(9L);
		two1.setFiberNoEndZ(18L);
		list.add(two1);
		Long s=0L;
		for (int i = 0; i < list.size(); i++) {
			Long b=(list.get(i).getFiberNoEndZ()-list.get(i).getFiberNoStartZ()+1)+(list.get(i).getFiberNoEndA()-list.get(i).getFiberNoStartA()+1);
			System.out.println(b+"b");
			s+=b;
		}
		System.out.println(s);
	}
	
	/**
	 * 
	* @Title: saveConnectTwoCableSection 
	* @Description: 保存直熔光缆段 
	* @param @param bean
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月17日 下午2:46:47 
	* @version V1.0
	 */
	@Override
	public int saveConnectTwoCableSection(Users user,ConnectTwoCableSectionBean bean) {
//		int reg=0;
//		Long num=0L;
//		Long sectIdA=0L;
//		Long sectIdZ=0L;
//		Discinfo discinfo = discinfoMapper.selectByPrimaryKey(bean.getDiscId());
//		if(bean.getRemark()!=null && !"".equals(bean.getRemark())) {
//			discinfo.setRemark(bean.getRemark());
//			discinfo.setLastModifyTime(new Date());
//			discinfo.setLastModifyUser(user.getUserId());
//			reg=discinfoMapper.updateByPrimaryKeySelective(discinfo);	//修改盘备注
//		}
//		if(bean.getCaSectwoList()==null || bean.getCaSectwoList().size()<1) {
//			throw new FrmsException("请选择光缆段！");
//		}
//		for (int i = 0; i < bean.getCaSectwoList().size(); i++) {
//			if(bean.getCaSectwoList().get(i).getSectIdA()==null || bean.getCaSectwoList().get(i).getSectIdZ()==null) {
//				throw new FrmsException("光缆段不能为空！");
//			}
//			
//			if(bean.getCaSectwoList().get(i).getSectIdA().longValue()==bean.getCaSectwoList().get(i).getSectIdZ().longValue()) {
//				throw new FrmsException("要直熔的两端不能为同一条光缆段！");
//			}
//			
//			if(
//				bean.getCaSectwoList().get(i).getFiberNoEndA()==null ||
//				bean.getCaSectwoList().get(i).getFiberNoEndZ()==null ||
//				bean.getCaSectwoList().get(i).getFiberNoStartA()==null ||
//				bean.getCaSectwoList().get(i).getFiberNoStartZ()==null) {
//				throw new FrmsException("纤芯起始序号不能为空！");
//			}
//			
//			if(bean.getCaSectwoList().get(i).getFiberNoStartA()>bean.getCaSectwoList().get(i).getFiberNoEndA()||
//					bean.getCaSectwoList().get(i).getFiberNoStartZ()>bean.getCaSectwoList().get(i).getFiberNoEndZ()) {
//				throw new FrmsException("起始数不能从大到小！");
//			}
//			
//			if((bean.getCaSectwoList().get(i).getFiberNoEndA()-bean.getCaSectwoList().get(i).getFiberNoStartA())!=
//					(bean.getCaSectwoList().get(i).getFiberNoEndZ()-bean.getCaSectwoList().get(i).getFiberNoStartZ())) {
//				throw new FrmsException("两个光缆段的纤芯数量不一致！");
//			}
//			
//			if(i==0) {
//				sectIdA=bean.getCaSectwoList().get(i).getSectIdA();
//				sectIdZ=bean.getCaSectwoList().get(i).getSectIdZ();
//			}else {
//				if(sectIdA.longValue() != bean.getCaSectwoList().get(i).getSectIdA().longValue()) {
//					throw new FrmsException("直熔盘A端光缆必须一致！");
//				}
//				if(sectIdZ.longValue() != bean.getCaSectwoList().get(i).getSectIdZ().longValue()) {
//					throw new FrmsException("直熔Z端光缆必须一致！");
//				}
//			}
//			
//			num+=(bean.getCaSectwoList().get(i).getFiberNoEndZ()-bean.getCaSectwoList().get(i).getFiberNoStartZ()+1)+
//					(bean.getCaSectwoList().get(i).getFiberNoEndA()-bean.getCaSectwoList().get(i).getFiberNoStartA()+1);
//					if(discinfo.getPortNum()<num) {
//						throw new FrmsException("直熔芯数不能大于建分组时所设芯数！");
//					}
//		}
//		
//		for (int i = 0; i < bean.getCaSectwoList().size(); i++) {
//			Long fiberNo=0L;	//端口列序号,disc_col_no，必从1开始
//			
//			for (long j = bean.getCaSectwoList().get(i).getFiberNoStartA(); j < bean.getCaSectwoList().get(i).getFiberNoEndA()+1; j++) {//A光缆段
//				++fiberNo;
//				Lines linesA=linesMapper.queryFutureTwoCableSection(bean.getCaSectwoList().get(i).getSectIdA(),j);
//				if(linesA.getAdevId()!=null) {
//					if(bean.getDevId().longValue()==linesA.getAdevId().longValue()) {
//						if(linesA.getAcode() != null && !"".equals(linesA.getAcode())) {
//							throw new FrmsException("已成端，不能进行直熔！");
//						}
//						String fiberNoStr = String.format("%02d", fiberNo);				//是1位自动在前面补0
//						String port01 = bean.getDiscCode()+"-"+fiberNoStr;
//						Fiberdisc fiberDiscIN=fiberdiscMapper.selectByPort(port01);    //ZRIN
//						if(fiberDiscIN!=null) {
//							fiberDiscIN.setSectId(bean.getCaSectwoList().get(i).getSectIdA());
//							fiberDiscIN.setFiberNum(j);
//							fiberDiscIN.setLastModifyTime(new Date());
//							fiberDiscIN.setLastModifyUser(user.getUserId());
//							reg=fiberdiscMapper.updateByPrimaryKeySelective(fiberDiscIN);	//修改端子表sect_id,fiber_num
//							
//							linesA.setAcode(port01);	//插入acode
//							linesA.setLastModifyTime(new Date());
//							linesA.setLastModifyUser(user.getUserId());
//							reg=linesMapper.updateByPrimaryKeySelective(linesA);	//修改lines表 acode
//						}
//					} 
//				}
//				if(linesA.getZdevId()!=null) {
//					if (bean.getDevId().longValue()==linesA.getZdevId().longValue()) {
//						if(linesA.getZcode() != null && !"".equals(linesA.getZcode())) {
//							throw new FrmsException("已成端，不能进行直熔！");
//						}
//						String fiberNoStr = String.format("%02d", fiberNo);				//是1位自动在前面补0
//						String port01 = bean.getDiscCode()+"-"+fiberNoStr;
//						Fiberdisc fiberDiscIN=fiberdiscMapper.selectByPort(port01);    //ZRIN
//						if(fiberDiscIN!=null) {
//							fiberDiscIN.setSectId(bean.getCaSectwoList().get(i).getSectIdA());
//							fiberDiscIN.setFiberNum(j);
//							fiberDiscIN.setLastModifyTime(new Date());
//							fiberDiscIN.setLastModifyUser(user.getUserId());
//							reg=fiberdiscMapper.updateByPrimaryKeySelective(fiberDiscIN);	//修改端子表sect_id,fiber_num
//							
//							linesA.setZcode(port01);	//插入zcode
//							linesA.setLastModifyTime(new Date());
//							linesA.setLastModifyUser(user.getUserId());
//							reg=linesMapper.updateByPrimaryKeySelective(linesA);	//修改lines表 zcode
//						}
//					}
//				}
//			}
//			
//			fiberNo=0L;
//			
//			for (long j = bean.getCaSectwoList().get(i).getFiberNoStartZ(); j < bean.getCaSectwoList().get(i).getFiberNoEndZ()+1; j++) {
//				++fiberNo;
//				Lines linesB=linesMapper.queryFutureTwoCableSection(bean.getCaSectwoList().get(i).getSectIdZ(), j);
//				if(linesB.getAdevId()!=null) {
//					if(bean.getDevId().longValue()==linesB.getAdevId().longValue()) {
//						if(linesB.getAcode() != null && !"".equals(linesB.getAcode())) {
//							throw new FrmsException("已成端，不能进行直熔！");
//						}
//						String discCodeOut=bean.getDiscCode().replaceAll("ZRIN", "ZROUT");	//IN替换OUT
//						String fiberNoStr = String.format("%02d", fiberNo);				//是1位自动在前面补0
//						String port01 = discCodeOut+"-"+fiberNoStr;
//						Fiberdisc fiberDiscOUT=fiberdiscMapper.selectByPort(port01);    //ZRIN
//						if(fiberDiscOUT!=null) {
//							fiberDiscOUT.setSectId(bean.getCaSectwoList().get(i).getSectIdZ());
//							fiberDiscOUT.setFiberNum(j);
//							fiberDiscOUT.setLastModifyTime(new Date());
//							fiberDiscOUT.setLastModifyUser(user.getUserId());
//							reg=fiberdiscMapper.updateByPrimaryKeySelective(fiberDiscOUT);	//修改端子表sect_id,fiber_num
//							
//							linesB.setAcode(port01);	//插入acode
//							linesB.setLastModifyTime(new Date());
//							linesB.setLastModifyUser(user.getUserId());
//							reg=linesMapper.updateByPrimaryKeySelective(linesB);	//修改lines表 acode
//						}
//					} 
//				}
//				if(linesB.getZdevId()!=null) {
//					if(bean.getDevId().longValue()==linesB.getZdevId().longValue()) {
//						if(linesB.getZcode() != null && !"".equals(linesB.getZcode())) {
//							throw new FrmsException("已成端，不能进行直熔！");
//						}
//						String discCodeOut=bean.getDiscCode().replaceAll("ZRIN", "ZROUT");	//IN替换OUT
//						String fiberNoStr = String.format("%02d", fiberNo);				//是1位自动在前面补0
//						String port01 = discCodeOut+"-"+fiberNoStr;
//						Fiberdisc fiberDiscOUT=fiberdiscMapper.selectByPort(port01);    //ZRIN
//						if(fiberDiscOUT!=null) {
//							fiberDiscOUT.setSectId(bean.getCaSectwoList().get(i).getSectIdZ());
//							fiberDiscOUT.setFiberNum(j);
//							fiberDiscOUT.setLastModifyTime(new Date());
//							fiberDiscOUT.setLastModifyUser(user.getUserId());
//							reg=fiberdiscMapper.updateByPrimaryKeySelective(fiberDiscOUT);		//修改端子表sect_id,fiber_num
//						
//							linesB.setZcode(port01);	//插入acode
//							linesB.setLastModifyTime(new Date());
//							linesB.setLastModifyUser(user.getUserId());
//							reg=linesMapper.updateByPrimaryKeySelective(linesB);	//修改lines表 zcode
//						}
//					}
//				}
//			}
//			fiberNo=0L;
//		}
		int reg=0;
		Long fiberNo=0L;	//绔彛鍒楀簭鍙�,disc_col_no锛屽繀浠�1寮�濮�
		Discinfo discinfo = discinfoMapper.selectByPrimaryKey(bean.getDiscId());
		if(bean.getFiberNoStartA()>bean.getFiberNoEndA() || bean.getFiberNoStartZ()>bean.getFiberNoEndZ()) {
			throw new FrmsException("起始纤芯不能从大到小！");
		}
		if((bean.getFiberNoEndA()-bean.getFiberNoStartA())!=(bean.getFiberNoEndZ()-bean.getFiberNoStartZ())) {
			throw new FrmsException("两端纤芯数不一致！");
		}
		if(discinfo.getPortNum()<(bean.getFiberNoEndZ()-bean.getFiberNoStartZ()+1)) {
			throw new FrmsException("直熔芯数不能大于建分组时所设芯数！");
		}
		CableSection casA = cableSectionMapper.selectByPrimaryKey(bean.getSectIdA());
		CableSection casZ = cableSectionMapper.selectByPrimaryKey(bean.getSectIdZ());
		if(casA.getFiberNum()<bean.getFiberNoEndA() || casA.getFiberNum()<bean.getFiberNoStartA()||
				casZ.getFiberNum()<bean.getFiberNoStartZ()||casZ.getFiberNum()<bean.getFiberNoEndZ()) {
			throw new FrmsException("所输芯数不得大于光缆段芯数！");
		}
		
		if(bean.getRemark()!=null && !"".equals(bean.getRemark())) {
			String codeOut = bean.getDiscCode().replaceAll("ZRIN", "ZROUT");
			List<Discinfo> discList=discinfoMapper.queryByDevIdForcode(bean.getDevId(),bean.getDiscCode(),codeOut);
			if(discList!=null && discList.size()>0) {
				for (int i = 0; i < discList.size(); i++) {
					discList.get(i).setRemark(bean.getRemark());
					discList.get(i).setLastModifyTime(new Date());
					discList.get(i).setLastModifyUser(user.getUserId());
					reg=discinfoMapper.updateByPrimaryKeySelective(discList.get(i));	//添加盘业务描述
				}
			}
		}
		List<Lines> lineListA = linesMapper.queryBySectIdLineType(bean.getSectIdA(),"01");
		List<Lines> lineListZ = linesMapper.queryBySectIdLineType(bean.getSectIdZ(),"01");
		for (long i = bean.getFiberNoStartA(); i < bean.getFiberNoEndA()+1; i++) {//A鍏夌紗娈�
			++fiberNo;
			//Lines linesA=linesMapper.queryFutureTwoCableSection(bean.getSectIdA(),i);
			Lines linesA = new Lines();
			for(Lines line : lineListA) {
				if(i==line.getFiberNo()) {
					linesA = line;
				}
			}
			if(linesA.getAdevId()!=null) {
				if(bean.getDevId().longValue()==linesA.getAdevId().longValue()) {
					if(linesA.getAcode() != null && !"".equals(linesA.getAcode())) {
						throw new FrmsException("A端已成端，请先解成端在直熔！");
					}
					String fiberNoStr = String.format("%02d", fiberNo);				//鏄�1浣嶈嚜鍔ㄥ湪鍓嶉潰琛�0
					String port01 = bean.getDiscCode()+"-"+fiberNoStr;
					Fiberdisc fiberDiscIN=fiberdiscMapper.selectByPort(port01);    //ZRIN
					if(fiberDiscIN!=null) {
						fiberDiscIN.setSectId(bean.getSectIdA());
						fiberDiscIN.setFiberNum(i);
						fiberDiscIN.setLastModifyTime(new Date());
						fiberDiscIN.setLastModifyUser(user.getUserId());
						reg=fiberdiscMapper.updateByPrimaryKeySelective(fiberDiscIN);	//淇敼绔瓙琛╯ect_id,fiber_num
						
						linesA.setAcode(port01);	//鎻掑叆acode
						linesA.setLastModifyTime(new Date());
						linesA.setLastModifyUser(user.getUserId());
						reg=linesMapper.updateByPrimaryKeySelective(linesA);	//淇敼lines琛� acode
					}
				} 
			}
			if(linesA.getZdevId()!=null) {
				if (bean.getDevId().longValue()==linesA.getZdevId().longValue()) {
					if(linesA.getZcode() != null && !"".equals(linesA.getZcode())) {
						throw new FrmsException("Z端已成端，请先解成端在直熔！");
					}
					String fiberNoStr = String.format("%02d", fiberNo);				//鏄�1浣嶈嚜鍔ㄥ湪鍓嶉潰琛�0
					String port01 = bean.getDiscCode()+"-"+fiberNoStr;
					Fiberdisc fiberDiscIN=fiberdiscMapper.selectByPort(port01);    //ZRIN
					if(fiberDiscIN!=null) {
						fiberDiscIN.setSectId(bean.getSectIdA());
						fiberDiscIN.setFiberNum(i);
						fiberDiscIN.setLastModifyTime(new Date());
						fiberDiscIN.setLastModifyUser(user.getUserId());
						reg=fiberdiscMapper.updateByPrimaryKeySelective(fiberDiscIN);	//淇敼绔瓙琛╯ect_id,fiber_num
						
						linesA.setZcode(port01);	//鎻掑叆zcode
						linesA.setLastModifyTime(new Date());
						linesA.setLastModifyUser(user.getUserId());
						reg=linesMapper.updateByPrimaryKeySelective(linesA);	//淇敼lines琛� zcode
					}
				}
			}
		}
		fiberNo=0L;
		
		for (long i = bean.getFiberNoStartZ(); i < bean.getFiberNoEndZ()+1; i++) {
			++fiberNo;
			//Lines linesB=linesMapper.queryFutureTwoCableSection(bean.getSectIdZ(), i);
			Lines linesB = new Lines();
			for(Lines line : lineListZ) {
				if(i==line.getFiberNo()) {
					linesB=line;
				}
			}
			if(linesB.getAdevId()!=null) {
				if(bean.getDevId().longValue()==linesB.getAdevId().longValue()) {
					if(linesB.getAcode() != null && !"".equals(linesB.getAcode())) {
						throw new FrmsException("A端已成端，请先解成端在直熔！");
					}
					String discCodeOut=bean.getDiscCode().replaceAll("ZRIN", "ZROUT");	//IN鏇挎崲OUT
					String fiberNoStr = String.format("%02d", fiberNo);				//鏄�1浣嶈嚜鍔ㄥ湪鍓嶉潰琛�0
					String port01 = discCodeOut+"-"+fiberNoStr;
					Fiberdisc fiberDiscOUT=fiberdiscMapper.selectByPort(port01);    //ZRIN
					if(fiberDiscOUT!=null) {
						fiberDiscOUT.setSectId(bean.getSectIdZ());
						fiberDiscOUT.setFiberNum(i);
						fiberDiscOUT.setLastModifyTime(new Date());
						fiberDiscOUT.setLastModifyUser(user.getUserId());
						reg=fiberdiscMapper.updateByPrimaryKeySelective(fiberDiscOUT);	//淇敼绔瓙琛╯ect_id,fiber_num
						
						linesB.setAcode(port01);	//鎻掑叆acode
						linesB.setLastModifyTime(new Date());
						linesB.setLastModifyUser(user.getUserId());
						reg=linesMapper.updateByPrimaryKeySelective(linesB);	//淇敼lines琛� acode
					}
				} 
			}
			if(linesB.getZdevId()!=null) {
				if(bean.getDevId().longValue()==linesB.getZdevId().longValue()) {
					if(linesB.getZcode() != null && !"".equals(linesB.getZcode())) {
						throw new FrmsException("Z端已成端，请先解成端在直熔！");
					}
					String discCodeOut=bean.getDiscCode().replaceAll("ZRIN", "ZROUT");	//IN鏇挎崲OUT
					String fiberNoStr = String.format("%02d", fiberNo);				//鏄�1浣嶈嚜鍔ㄥ湪鍓嶉潰琛�0
					String port01 = discCodeOut+"-"+fiberNoStr;
					Fiberdisc fiberDiscOUT=fiberdiscMapper.selectByPort(port01);    //ZRIN
					if(fiberDiscOUT!=null) {
						fiberDiscOUT.setSectId(bean.getSectIdZ());
						fiberDiscOUT.setFiberNum(i);
						fiberDiscOUT.setLastModifyTime(new Date());
						fiberDiscOUT.setLastModifyUser(user.getUserId());
						reg=fiberdiscMapper.updateByPrimaryKeySelective(fiberDiscOUT);		//淇敼绔瓙琛╯ect_id,fiber_num
					
						linesB.setZcode(port01);	//鎻掑叆acode
						linesB.setLastModifyTime(new Date());
						linesB.setLastModifyUser(user.getUserId());
						reg=linesMapper.updateByPrimaryKeySelective(linesB);	//淇敼lines琛� zcode
					}
				}
			}
		}
		
		return reg;
	}

	/**
	 * 根据discCode删除跳纤
	 */
	@Override
	public int deleteLinesByLineTypeAndCode(String discCode) {
		return linesMapper.deleteLinesByLineTypeAndCode(discCode);
	}

	/**
	 * 根据discCode查成端
	 */
	@Override
	public List<Lines> queryByDiscCode(String discCode) {
		return linesMapper.queryByDiscCode(discCode);
	}
	
	
	
	/**
	 * 保存跳纤图片
	* 
	* @Description: TODO 
	* @param @param 
	*            linesBean
	* @param @param 
	*            images
	* @param @return
	* @return int
	* @author zy
	* @date 2017年12月3日 下午3:23:57
	* @version V1.0
	 */
	@Override
	public int insertLineImg(List<LineImage> list,Long lineId) {
		int num = 0;
			for (int i = 0; i < list.size(); i++) {
				LineImage li=list.get(i);
				li.setLineId(lineId);
				num = lineImageMapper.insertSelective(li);
			}
		return num;
	}

	/**
	 * 
	 * @Title: saveImages
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param lineId
	 * @param @param request
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月23日 下午9:38:43
	 * @version V1.0
	 */
	@Override
	public int saveImages(Long lineId, HttpServletRequest request, Users user) {
		//保存图片
		//根据lineId查询图片数据
		int saveImage = 0;
		List<LineImage> lineImageList = lineImageService.queryByLineId(lineId);
		if(lineImageList.size()>0) {//如果存在数据
			for (int i = 0; i < lineImageList.size(); i++) {
				File file=new File(request.getSession().getServletContext().getRealPath("/")+lineImageList.get(i).getImgUrl());
				if(file.exists()&&file.isFile())
					file.delete();
			}
			//根据lineId删除图片
			saveImage = lineImageService.deleteImageByLineId(lineId);
		}
		if(request instanceof MultipartHttpServletRequest)
		{
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			List<MultipartFile> listfile = req.getFiles("images");
			for (int j = 0; j < listfile.size(); j++) {
				if (listfile!=null&&!listfile.isEmpty()) {
					LineImage lineImage = new LineImage();
					String image = UploadUtil.uploadFile(listfile.get(j), req,user.getOrgId(), "02");//"02"添加端子图片
					lineImage.setImgUrl(image);
					lineImage.setLineId(lineId);
					lineImage.setCreateTime(new Date());
					lineImage.setCreateUser(user.getUserId());
					saveImage = lineImageService.insertSelective(lineImage);
				}
			}
		}
		return saveImage;
	}

	/**
	 * 
	 * @Title: selectByDiscCode
	 * @Description: 根据discCode查看当前盘是否存在成端或跳纤,如果存在,则不可以删除 
	 * @param @param string
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月5日 上午9:53:08
	 * @version V1.0
	 */
	@Override
	public List<Lines> selectByDiscCode(String discCode) {
		return linesMapper.selectByDiscCode(discCode);
	}


	/**
	 * 
	* @Title: unbindAsaside 
	* @Description: 解成端处理lines表 
	* @param @param devId
	* @param @param sectId
	* @param @param aorz
	* @param @param userId
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年1月12日 上午10:58:12 
	* @version V1.0
	 */
	@Override
	public int unbindAsaside(Long devId, Long sectId,Integer aorz, Long userId) {
		return linesMapper.unbindAsaside(LineType.FIBER.toString(),devId,sectId,aorz,userId);
	}


	/**
	 * 
	* @Title: workorderAddJumper 
	* @Description: 光路调度跳纤 
	* @param @param jumperInfo
	* @param @param request
	* @param @param user
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年4月24日 上午9:48:29 
	* @version V1.0
	 */
	@Override
	public int workorderAddJumper(WorkorderJumperInfo jumperInfo, HttpServletRequest request, Users user) {
		int ret = 0;
		//判断是否存在跳纤
		if(jumperInfo.getCode()!=null && !"".equals(jumperInfo.getCode())) {
			ret = isExistJumper(jumperInfo.getCode());
			if(ret > 0) {
				throw new FrmsException("所选端子"+jumperInfo.getCode()+"已占用");
			}
		}
		if(jumperInfo.getZcode()!=null && !"".equals(jumperInfo.getZcode())) {
			ret = isExistJumper(jumperInfo.getZcode());
			if(ret > 0) {
				throw new FrmsException("所选端子"+jumperInfo.getZcode()+"已占用");
			}
		}
		
		//修改纤芯详情描述本端
		if(jumperInfo.getCode()!=null && !"".equals(jumperInfo.getCode())) {
			List<LinesBean> linecd = linesService.queryLineType("01", jumperInfo.getCode());//查询成端信息,正确数据返回一条
			if(linecd.size()>0) {
				CableSectionDec csda = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(linecd.get(0).getSectId(), linecd.get(0).getFiberNo());
				csda.setModifyTime(new Date());
				csda.setModifyUser(jumperInfo.getUserId());
				if(jumperInfo.getCode().equals(linecd.get(0).getAcode())) {
					csda.setDevADec(jumperInfo.getSrvName());
				}else {
					csda.setDevZDec(jumperInfo.getSrvName());
				}
				ret = cableSectionDecMapper.updateByPrimaryKeySelective(csda);
			}
		}
		
		//修改纤芯详情描述对端
		if(jumperInfo.getZcode()!=null && !"".equals(jumperInfo.getZcode())) {
			List<LinesBean> linecd = linesService.queryLineType("01", jumperInfo.getZcode());//查询成端信息,正确数据返回一条
			if(linecd.size()>0) {
				CableSectionDec csda = cableSectionDecMapper.querySectionDecBySectIdAndFiberNo(linecd.get(0).getSectId(), linecd.get(0).getFiberNo());
				csda.setModifyTime(new Date());
				csda.setModifyUser(jumperInfo.getUserId());
				if(jumperInfo.getZcode().equals(linecd.get(0).getAcode())) {
					csda.setDevADec(jumperInfo.getSrvName());
				}else{
					csda.setDevZDec(jumperInfo.getSrvName());
				}
				ret = cableSectionDecMapper.updateByPrimaryKeySelective(csda);
			}
		}
		
		Lines line = new Lines();
		line.setSrvName(jumperInfo.getSrvName());
		line.setAcode(jumperInfo.getCode());
		line.setAdevId(jumperInfo.getDevId());
//		if(!"".equals(jumperInfo.getZcode())) {
//			line.setZcode(jumperInfo.getZcode());
//		}
		if(jumperInfo.getZcode()!=null && !"".equals(jumperInfo.getZcode())) {
			line.setZcode(jumperInfo.getZcode());
		}
		if(jumperInfo.getZdevId()!=null) {
			line.setZdevId(jumperInfo.getZdevId());
		}
		line.setLineType(LineType.JUMPER.toString());
		line.setUnknownPointName(jumperInfo.getUnknownPointName());//尾纤悬空
		line.setSurveyResult("1");
		line.setOrgId(jumperInfo.getOrgId());
		line.setCreateTime(new Date());
		line.setCreateUser(jumperInfo.getUserId());
		ret = linesMapper.insertSelective(line);
		
		Long id=0L;
		if(ret>0) {
			Facility fa = facilityMapper.selectByPrimaryKey(jumperInfo.getDevId());
			if(FacilityDevType.ODF.toString().equals(fa.getDevType())) {//如果是机柜
				String[] str = jumperInfo.getCode().split("-");
				String strs=str[0]+"-"+str[1]+"-"+str[2];
				Discinfo discInfo = discinfoMapper.selectByDiscCode(strs);
				DeviceDiscinfoEntity deinfo = deviceDiscinfoEntityMapper.selectByPrimaryKey(discInfo.getDiscId());
				jumperInfo.setAdevData(deinfo.getLastReportData());
			}
			Facility fz = facilityMapper.selectByPrimaryKey(jumperInfo.getZdevId());
			if(FacilityDevType.ODF.toString().equals(fz.getDevType())) {//如果是机柜
				String[] str = jumperInfo.getCode().split("-");
				String strs=str[0]+"-"+str[1]+"-"+str[2];
				Discinfo discInfo = discinfoMapper.selectByDiscCode(strs);
				DeviceDiscinfoEntity deinfo = deviceDiscinfoEntityMapper.selectByPrimaryKey(discInfo.getDiscId());
				jumperInfo.setZdevData(deinfo.getLastReportData());
			}
			//id=insertImpplans(jumperInfo,user);
		}
		//添加图片描述 周宇17/12/23
		if(ret>0) {
			List<WorkorderImplePlansImg> list = new ArrayList<WorkorderImplePlansImg>();
			if(request instanceof MultipartHttpServletRequest)
			{
				MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
				List<MultipartFile> listfile = req.getFiles("images");
				for (int j = 0; j < listfile.size(); j++) {
					if (listfile!=null&&!listfile.isEmpty()) {
						WorkorderImplePlansImg li = new WorkorderImplePlansImg();
								
						String image = UploadUtil.uploadFile(listfile.get(j), req,user.getOrgId(), "08");//"02"添加端子图片
						li.setImgUrl(image);
						li.setCreateTime(new Date());
						li.setCreateUser(user.getUserId());
						li.setOrgId(user.getOrgId());
						list.add(li);
					}
				}
			}
//			String code = jumperInfo.getCode();
//			String zcode = jumperInfo.getZcode();
//			if(Objects.equal(code, "")) {
//				code=null;
//			}
//			if(Objects.equal(zcode, "")) {
//				zcode=null;
//			}
//			Lines line2 = linesMapper.selectLinesByTwoPointZY(code,zcode);
//			ret += linesService.insertLineImg(list,line2.getLineId());
			//ret+=insertImplePlansImg(list,id);
		}
		if(ret > 0) {
			if(jumperInfo.getCode()!=null || "".equals(jumperInfo.getCode())) {
				routeBuildService.asyncSinglePointGenOptPath(jumperInfo.getCode(), jumperInfo.getOrgId());
			}else if(jumperInfo.getZcode()!=null || "".equals(jumperInfo.getZcode())) {
				routeBuildService.asyncSinglePointGenOptPath(jumperInfo.getZcode(), jumperInfo.getOrgId());
			}
		}
		return ret;
	}

	public int insertImplePlansImg(List<WorkorderImplePlansImg> list,Long plansId) {
		int num = 0;
		for (int i = 0; i < list.size(); i++) {
			WorkorderImplePlansImg li=list.get(i);
			li.setPlansId(plansId);
			num = workorderImplePlansImgMapper.insertSelective(li);
		}
		return num;
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
	public Long insertImpplans(WorkorderJumperInfo jumperInfo,Users user) {
		Long ret=0L;
		WorkorderImplePlans plan=new WorkorderImplePlans();
		int countA=workorderImplePlansMapper.queryByFiberDataCount(jumperInfo.getOrgId(),jumperInfo.getCode());
		int countZ=workorderImplePlansMapper.queryByFiberDataCount(jumperInfo.getOrgId(),jumperInfo.getZcode());
		if(countA>0 ) {
			if(jumperInfo.getAdevData()==null || "".equals(jumperInfo.getAdevData())) {
				throw new FrmsException("A端设备检测数据不能为空！");
			}else {
				plan.setAdevData(jumperInfo.getAdevData());//A端设备检测数据
			}
		}else {
			plan.setAdevData(jumperInfo.getAdevData());//A端设备检测数据
		}
		if(countZ>0) {
			if(jumperInfo.getZdevData()==null || "".equals(jumperInfo.getZdevData())) {
				throw new FrmsException("Z端设备检测数据不能为空！");
			}else {
				plan.setZdevData(jumperInfo.getZdevData());//Z端设备检测数据
			}
		}else {
			plan.setZdevData(jumperInfo.getZdevData());//Z端设备检测数据
		}
		
		
		
		
		
		
//		if(jumperInfo.getPlansId()==null) {//添加
		plan.setCreateTime(new Date());
		plan.setCreateUser(user.getUserId());
		plan.setOrgId(user.getOrgId());
		plan.setImpleBack(jumperInfo.getImpleBack());//实施反馈信息
		
		Lines line = linesMapper.queryLineType02(jumperInfo.getCode());
		plan.setLineId(line.getLineId());//跳纤ID
		plan.setDesignroutesId(jumperInfo.getDesignroutesId());//调度工单光路ID
		
		Fiberdisc fiberA = fiberdiscMapper.queryByPort(jumperInfo.getCode());
		Fiberdisc fiberZ = fiberdiscMapper.queryByPort(jumperInfo.getZcode());
		plan.setAfiberDiscId(fiberA.getFiberDiscId());//A端端子ID
		plan.setZfiberDiscId(fiberZ.getFiberDiscId());//Z端端子ID
		plan.setPortA(jumperInfo.getCode());//A端端子编码
		plan.setPortZ(jumperInfo.getZcode());//Z端端子编码
		
		WorkorderRoutes workRout = workorderRoutesMapper.selectByPrimaryKey(jumperInfo.getDesignroutesId());
		if(workRout!=null) {
			plan.setAdevId(workRout.getAdevId());//A端设施ID
			plan.setZdevId(workRout.getZdevId());//Z端设施ID
			plan.setDesignId(workRout.getDesignplanId());//调度工单ID
			
			//判断 实施方案是否一致 0 否 1 是
			WorkorderFiberdiscabindA workFiberA = workorderFiberdiscabindAMapper.queryByRoutsIds(jumperInfo.getDesignroutesId(), workRout.getAdevId());
			WorkorderFiberdiscabindZ workFiberZ = workorderFiberdiscabindZMapper.queryByRoutsIds(jumperInfo.getDesignroutesId(), workRout.getZdevId());
			if(workFiberA!=null && workFiberZ!=null) {
				if(workFiberA.getFiberDiscId().longValue()==fiberA.getFiberDiscId().longValue() && 
						workFiberZ.getFiberDiscId().longValue()==fiberZ.getFiberDiscId().longValue()) {
					plan.setIsImpleSame("1");//实施方案是否一致 0 否 1 是
				}else {
					plan.setIsImpleSame("0");
				}
			}else {
				plan.setIsImpleSame("0");
			}
		}
		
		
		
		if(jumperInfo.getAdevData()!=null && !"".equals(jumperInfo.getAdevData())
				&& jumperInfo.getZdevData()!=null && !"".equals(jumperInfo.getZdevData())) {
			//判断 占用情况是否一致 0 不一致 1 一致
			char strA=jumperInfo.getAdevData().charAt(fiberA.getDiscColNo().intValue()-1);
			char strZ=jumperInfo.getZdevData().charAt(fiberZ.getDiscColNo().intValue()-1);
			if(strA==fiberA.getIsOccup().charAt(0) && strZ==fiberZ.getIsOccup().charAt(0)) {
				plan.setIsOccupySame("1");//占用情况是否一致 0 不一致 1 一致
			}else {
				plan.setIsOccupySame("0");
			}
			int reg=workorderImplePlansMapper.insertSelective(plan);//插入
			if(reg>0) {
				ret=plan.getPlansId();
			}
		}
		
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
		return ret;
	}
	
	/**
	 * 
	* @Title: workorderChangeJumpper 
	* @Description: 光路调度解跳纤 
	* @param @param user
	* @param @param linesBean
	* @param @param request
	* @param @return    
	* @return int
	* @author liucanghai YHT
	* @throws
	* @date 2018年4月24日 下午1:38:58  2018年9月29日10:48:58
	* @version V1.0
	 */
	@Override
	public int workorderChangeJumpper(Users user, LinesBean linesBean, HttpServletRequest request,Long designroutesId) {
		int num = 0;
		if("1".equals(linesBean.getChangeType())) {//解跳纤
			WorkorderImplePlans plan=workorderImplePlansMapper.queryByDesignroutesId(designroutesId);
			Lines line = linesMapper.selectByPrimaryKey(plan.getLineId());
			if(line != null) {
				num = linesMapper.deleteByPrimaryKey(plan.getLineId());//删除跳纤
				if(line.getAcode()!=null && !"".equals(line.getAcode())) {//如果A端存在
					num = modifyPort(user,line.getAcode(),"update");//修改端子状态
				}
				
				if(line.getZcode()!=null && !"".equals(line.getZcode())) {//如果Z端存在
					num = modifyPort(user,line.getZcode(),"update");//修改端子状态
				}
			}
			//删除图片数据及文件
			num=workorderImplePlansImgMapper.deleteImplePlansImg(plan.getPlansId());//删除图片
			List<WorkorderImplePlansImgBean> wipbList = workorderImplePlansImgMapper.queryByPlansId(plan.getPlansId());
			if(wipbList!=null && wipbList.size()>0) {
				for(WorkorderImplePlansImgBean wipb : wipbList) {
					File file = new File(request.getSession().getServletContext().getRealPath("/")+wipb.getImgUrl());
					if(file.exists() && file.isFile()) {
						file.delete();
					}
				}
			}
			num=workorderImplePlansMapper.deleteByDesignroutesId(designroutesId);//删除实施施工方案
			WorkorderDesign  workorderDesign = workorderDesignMapper.selectByPrimaryKey(plan.getDesignId());
			if(workorderDesign != null) {
				//更改`dd_workorder_routes`表的is_imple为0
				WorkorderRoutes wr = new WorkorderRoutes();
				wr.setDesignroutesId(designroutesId);
				wr.setIsImple("0");
				num=workorderRoutesMapper.updateByPrimaryKeySelective(wr);
					
				//更改dd_workorder_lightline的planstate为设计完成
				WorkorderLightline wl = new WorkorderLightline();
				wl.setLightlineId(workorderDesign.getLightlineId());
				wl.setLastModifyTime(new Date());
				wl.setLastModifyUser(user.getUserId());
				wl.setPlanstate("2");
				num=workorderLightlineMapper.updateByPrimaryKeySelective(wl);
					
			}
		}
		return 1;
	}

	/**
	 * 
	 * @Title: queryByAcodeAndZcode
	 * @Description: 根据acode和zcode查询跳纤
	 * @param @param code
	 * @param @param zcode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月6日 上午11:46:17
	 * @version V1.0
	 */
	@Override
	public LinesBean queryByAcodeAndZcode(String lineType, String code, String zcode) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("acode", code);
		map.put("zcode", zcode);
		map.put("lineType", lineType);
		return linesMapper.queryByAcodeAndZcode(map);
	}

	/**
	 * 
	 * @Title: saveJumpperImagesOpd
	 * @Description: 保存跳纤端子图片
	 * @param @param request
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月21日 下午4:32:17
	 * @version V1.0
	 */
	@Override
	public List<String> saveJumpperImagesOpd(HttpServletRequest request, Users user) {
		List<String> list = new ArrayList<String>();
		if(request instanceof MultipartHttpServletRequest)
		{
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			List<MultipartFile> files = req.getFiles("pic");
			if (files!=null&&files.size()>0) {
				for(int index = 0;index<files.size();index++)
				{
					 String image = UploadUtil.uploadFile(files.get(index), req,user.getOrgId(), "02");//"01"添加设施图片
					 list.add(image);
				}
			 }
		}
		return list;
	}

	/**
	 * 
	 * @Title: selectLinesByTwoPointZY
	 * @Description: 查询跳纤
	 * @param @param code
	 * @param @param zcode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月22日 下午2:40:57
	 * @version V1.0
	 */
	@Override
	public Lines selectLinesByTwoPointZY(String code, String zcode) {
		return linesMapper.selectLinesByTwoPointZY(code,zcode);
	}

	/**
	 * 
	 * @Title: deleteImage
	 * @Description: 删除端子图片
	 * @param @param request
	 * @param @param imageUrlList
	 * @param @param lineId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月17日 下午3:48:29
	 * @version V1.0
	 */
	@Override
	public int deleteImage(HttpServletRequest request, List<String> imageUrlList, Long lineId) {
		int req = 0;
		Map<String,Object> map = null;
		if(imageUrlList != null && imageUrlList.size()>0) {
			for (String imageUrl : imageUrlList) {
				if(imageUrl != null && !"".equals(imageUrl.trim())) {
					map = new HashMap<String,Object>();
					map.put("lineId", lineId);
					map.put("imgUrl", imageUrl);
					req = lineImageMapper.deleteByLineIdAndUrl(map);//根据lineId和imgUrl删除端子图片信息
					File file = new File(request.getSession().getServletContext().getRealPath("/")+imageUrl);
					if(file.exists() && file.isFile()) {
						file.delete();
					}
				}
			}
		}
		return req;
	}
}


