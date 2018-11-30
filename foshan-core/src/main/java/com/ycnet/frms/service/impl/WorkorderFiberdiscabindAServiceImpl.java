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

import com.ycnet.core.FrmsException;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.LineImage;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderDesign;
import com.ycnet.frms.bean.WorkorderFiberdiscabindA;
import com.ycnet.frms.bean.WorkorderFiberdiscabindZ;
import com.ycnet.frms.bean.WorkorderImplePlans;
import com.ycnet.frms.bean.WorkorderImplePlansImg;
import com.ycnet.frms.bean.WorkorderLightline;
import com.ycnet.frms.bean.WorkorderRoutes;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.WorkorderDesignMapper;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper;
import com.ycnet.frms.mapper.WorkorderImplePlansImgMapper;
import com.ycnet.frms.mapper.WorkorderImplePlansMapper;
import com.ycnet.frms.mapper.WorkorderLightlineMapper;
import com.ycnet.frms.mapper.WorkorderRoutesMapper;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.service.LinesService;
import com.ycnet.frms.service.RouteBuildService;
import com.ycnet.frms.service.WorkorderFiberdiscabindAService;
import com.ycnet.frms.util.UploadUtil;
import com.ycnet.frms.vo.JumperInfo;
import com.ycnet.frms.vo.LinesBean;
import com.ycnet.frms.vo.WorkorderFiberdiscabindImgBean;
import com.ycnet.frms.vo.WorkorderImplePlansImgBean;
import com.ycnet.frms.vo.WorkorderJumperInfo;

@Service("workorderFiberdiscabindAService")
public class WorkorderFiberdiscabindAServiceImpl implements WorkorderFiberdiscabindAService{

	@Resource(name="workorderFiberdiscabindAMapper")
	private WorkorderFiberdiscabindAMapper workorderFiberdiscabindAMapper;
	
	@Resource(name="workorderFiberdiscabindZMapper")
	private WorkorderFiberdiscabindZMapper workorderFiberdiscabindZMapper;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;
	
	@Resource(name="workorderDesignMapper")
	private WorkorderDesignMapper workorderDesignMapper;
	
	@Resource(name="workorderRoutesMapper")
	private WorkorderRoutesMapper workorderRoutesMapper;
	
	@Resource(name="linesService")
	private LinesService linesService;
	
	@Resource(name="workorderFiberdiscabindAService")
	private WorkorderFiberdiscabindAService workorderFiberdiscabindAService;
	
	@Resource(name="workorderImplePlansMapper")
	private WorkorderImplePlansMapper workorderImplePlansMapper;
	
	@Resource(name="workorderImplePlansImgMapper")
	private WorkorderImplePlansImgMapper workorderImplePlansImgMapper;
	
	@Resource(name="linesMapper")
	private LinesMapper linesMapper;
	
	@Resource(name="fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;
	
	@Resource(name = "routeBuildService")
	private RouteBuildService routeBuildService;
	
	@Resource(name = "workorderLightlineMapper")
	private WorkorderLightlineMapper workorderLightlineMapper;
	
	/**
	 * 
	 * @Title: saveJumper
	 * @Description: 跳纤,并添加工单信息
	 * @param @param jumperInfo
	 * @param @param request
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月5日 下午5:32:58
	 * @version V1.0
	 */
	@Override
	@Transactional
	public int saveJumper(JumperInfo jumperInfo, HttpServletRequest request, Users user) {
		int req = 0;
//		Long devId = jumperInfo.getDevId();
//		String code = jumperInfo.getCode();
//		Long zdevId = jumperInfo.getZdevId();
//		String zcode = jumperInfo.getZcode();
		Long designroutesId = jumperInfo.getDesignroutesId();
//		WorkorderFiberdiscabindA wfA = new WorkorderFiberdiscabindA();
//		WorkorderFiberdiscabindZ wfZ = new WorkorderFiberdiscabindZ();
//		if(devId != null && code != null && !"".equals(code.trim())) {
//			//查询端子
//			Fiberdisc fib = fiberdiscService.selectByPort(code);
//			//查询调度工单设计方案路由A设施端口
//			wfA = workorderFiberdiscabindAMapper.selectByPrimaryKey(designroutesId);
//			if(wfA != null) {
//				wfA.setImplAdevId(devId);
//				wfA.setImplFiberDiscId(fib.getFiberDiscId());
//				workorderFiberdiscabindAMapper.updateByPrimaryKeySelective(wfA);//修改
//			}else {
//				WorkorderFiberdiscabindA wf = new WorkorderFiberdiscabindA();
//				wf.setDesignroutesId(designroutesId);
//				wf.setImplAdevId(devId);
//				wf.setImplFiberDiscId(fib.getFiberDiscId());
//				workorderFiberdiscabindAMapper.insertSelective(wf);//添加
//			}
//		}
//		if(zdevId != null && zcode != null && !"".equals(zcode.trim())) {
//			//查询端子
//			Fiberdisc fib = fiberdiscService.selectByPort(zcode);
//			//查询调度工单设计方案路由Z设施端口
//			wfZ = workorderFiberdiscabindZMapper.selectByPrimaryKey(designroutesId);
//			if(wfZ != null) {
//				wfZ.setImplZdevId(zdevId);
//				wfZ.setImplFiberDiscId(fib.getFiberDiscId());
//				workorderFiberdiscabindZMapper.updateByPrimaryKeySelective(wfZ);
//			}else {
//				WorkorderFiberdiscabindZ wf = new WorkorderFiberdiscabindZ();
//				wf.setDesignroutesId(designroutesId);
//				wf.setImplZdevId(zdevId);
//				wf.setImplFiberDiscId(fib.getFiberDiscId());
//				workorderFiberdiscabindZMapper.insertSelective(wf);
//			}
//		}
		//跳纤操作
//		//判断修改调度工单设计方案(dd_workorder_design)的是否实施方案(isused)字段
//		if(wfA.getFiberDiscId() != null && wfA.getImplFiberDiscId() != null && wfZ.getFiberDiscId() != null && wfZ.getImplFiberDiscId() != null) {
//			//如果判断fiberDiscId与implFiberDiscId是否一致
//			if((wfA.getFiberDiscId() != wfA.getImplFiberDiscId()) || (wfZ.getFiberDiscId() != wfZ.getImplFiberDiscId())) {
//				WorkorderRoutes designRoutes = workorderRoutesMapper.selectByPrimaryKey(designroutesId);
//				if(designRoutes != null) {
//					//更改调度工单设计方案
//					WorkorderDesign design = workorderDesignMapper.selectByPrimaryKey(designRoutes.getDesignplanId());
//					if(design != null) {
//						design.setIsused("0");
//						workorderDesignMapper.updateByPrimaryKeySelective(design);
//					}
//					if("0".equals(jumperInfo.getSign())) {
//						//更改调度工单设计方案路由
//						designRoutes.setIsImple("1");
//						workorderRoutesMapper.updateByPrimaryKeySelective(designRoutes);
//					}
//				}
//			}
//		}
		if(workorderImplePlansMapper.queryByDesignroutesId(designroutesId) !=null) {
			throw new FrmsException("该设施已完成跳纤！");
		}
		
		jumperInfo.setOrgId(user.getOrgId());//跳纤lines表需要
		req = facilityService.saveJumper(jumperInfo,request,user);
		
		if(req > 0) {
			int num = 0;
			//修改路由状态为已完成
			WorkorderRoutes designRoutes = workorderRoutesMapper.selectByPrimaryKey(designroutesId);
			if(designRoutes != null) {
				designRoutes.setIsImple("1");//是否已完成实施 0 否 1 是
				num = workorderRoutesMapper.updateByPrimaryKeySelective(designRoutes);
			}
			int flag = 1;
			if(num > 0) {
				List<WorkorderRoutes> routeList = workorderRoutesMapper.queryRoutesAdevByDesignId(designRoutes.getDesignplanId());
				if(routeList != null && routeList.size()>0) {
					for (WorkorderRoutes workorderRoutes : routeList) {
						//如果有没有完成的路由
						if("0".equals(workorderRoutes.getIsImple())) {
							flag = 0;
						}
					}
				}
				//如果flag=1,说明整条路由都已完成,修改dd_workorder_lightline状态
				if(flag == 1) {
					WorkorderDesign design = workorderDesignMapper.selectByPrimaryKey(designRoutes.getDesignplanId());
					if(design != null) {
						WorkorderLightline lightLine = workorderLightlineMapper.selectByPrimaryKey(design.getLightlineId());
						if(lightLine != null) {
							lightLine.setPlanstate("3");//设计方案状态'0'未设计'1'草稿'2'设计成功 '3' 实施完成
							workorderLightlineMapper.updateByPrimaryKeySelective(lightLine);
						}
					}
				}
			}
		}
		saveImpplans(jumperInfo,user,request);//保存实施方案
		return req;
	}
	

	/**
	 * 
	 * @Title: updateJumper
	 * @Description: 解跳纤,重新跳纤,修改调度工单设计方案
	 * @param @param jumperInfo
	 * @param @param request
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月6日 上午10:51:03
	 * @version V1.0
	 */
	@Override
	@Transactional
	public int updateJumper(JumperInfo jumperInfo, HttpServletRequest request, Users user) {
		int req = 0;
		String code = jumperInfo.getCode();
		WorkorderImplePlans wip = workorderImplePlansMapper.queryByDesignroutesId(jumperInfo.getDesignroutesId());
		
		//LinesBean line = linesService.queryByAcodeAndZcode("02",code,zcode);
		Lines line = linesMapper.selectByPrimaryKey(wip.getLineId());
		
		if(line != null) {
			LinesBean linesBean = new LinesBean();
			linesBean.setLineId(line.getLineId());
			linesBean.setChangeType("1");//解跳纤
			linesBean.setAdevId(line.getAdevId());
			linesBean.setAcode(line.getAcode());
			linesBean.setZdevId(line.getZdevId());
			linesBean.setZcode(line.getZcode());
			req = linesService.changeJumpper(user,linesBean,request);
		}
//				
//			}
//		}else if(code!=null&& !"".equals(code)&&(zcode==null||"".equals(zcode))){
//			WorkorderImplePlans wpls = workorderImplePlansMapper.queryByDesignroutesId(jumperInfo.getDesignroutesId());
//			if (wpls!=null &&wpls.getLineId()!=null) {
//				LinesBean linesBean = new LinesBean();
//				linesBean.setLineId(wpls.getLineId());
//				linesBean.setChangeType("1");//解跳纤
//				linesBean.setAdevId(wpls.getAdevId());
//				linesBean.setAcode(fiberdiscService.selectById(wpls.getAfiberDiscId()).getPort01());
//				req = linesService.changeJumpper(user,linesBean,request);
		
		
		req = workorderFiberdiscabindAService.saveJumper(jumperInfo,request,user);
		if(req > 0) {
			if(code!=null && !code.trim().equals("")) {
				routeBuildService.asyncSinglePointGenOptPath(code, user.getOrgId());
			}
		}
		return req;
	}
	
	/**
	 * 
	 * @Title: saveImpplans
	 * @Description: 保存实施方案
	 * @param @param jumperInfo
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月7日 上午11:24:19
	 * @version V1.0
	 * @param request 
	 */
	private int saveImpplans(JumperInfo jumperInfo, Users user, HttpServletRequest request) {
		int reg = 0;
		WorkorderImplePlans plan = null;
		if("0".equals(jumperInfo.getSign())) {
			plan = new WorkorderImplePlans();
			handle(plan,jumperInfo,user);//处理数据
			plan.setCreateTime(new Date());
			plan.setCreateUser(user.getUserId());
			reg=workorderImplePlansMapper.insertSelective(plan);//添加
			/**保存图片到施工表中**/
			if(reg > 0) {
				if(request instanceof MultipartHttpServletRequest)
				{
					MultipartHttpServletRequest req1 = (MultipartHttpServletRequest)request;
					List<MultipartFile> listfile = req1.getFiles("images");
					WorkorderImplePlansImg li = null;
					for (int j = 0; j < listfile.size(); j++) {
						if (listfile!=null&&!listfile.isEmpty()) {
							li = new WorkorderImplePlansImg();
							String image = UploadUtil.uploadFile(listfile.get(j), req1, user.getOrgId(), "08");
							li.setImgUrl(image);
							li.setCreateTime(new Date());
							li.setCreateUser(user.getUserId());
							li.setOrgId(user.getOrgId());
							if (plan.getPlansId()!=null) {
								li.setPlansId(plan.getPlansId());
							}
							li.setImgType("01");
							workorderImplePlansImgMapper.insertSelective(li);
							li = null;
						}
					}
				}
			}
		}
		if("1".equals(jumperInfo.getSign())) {
			plan = workorderImplePlansMapper.queryByDesignroutesId(jumperInfo.getDesignroutesId());
			handle(plan,jumperInfo,user);//处理数据
			plan.setLastModifyTime(new Date());
			plan.setLastModifyUser(user.getUserId());
			reg = workorderImplePlansMapper.updateByPrimaryKeySelective(plan);//修改
			if (reg>0) {
				List<WorkorderImplePlansImgBean> list = workorderImplePlansImgMapper.queryByPlansId(plan.getPlansId());
				if (list!=null&&list.size()>0) {//如果存在数据
					for (int i = 0; i < list.size(); i++) {
						File file=new File(request.getSession().getServletContext().getRealPath("/")+list.get(i).getImgUrl());
						if(file.exists()&&file.isFile()) {
							file.delete();
						}
						workorderImplePlansImgMapper.deleteByPrimaryKey(list.get(i).getPlanImgId());
					}
				}
				if(request instanceof MultipartHttpServletRequest)
				{
					MultipartHttpServletRequest req1 = (MultipartHttpServletRequest)request;
					List<MultipartFile> listfile = req1.getFiles("images");
					WorkorderImplePlansImg li = null;
					for (int j = 0; j < listfile.size(); j++) {
						if (listfile!=null&&!listfile.isEmpty()) {
							li = new WorkorderImplePlansImg();
							String image = UploadUtil.uploadFile(listfile.get(j), req1, user.getOrgId(), "08");
							li.setImgUrl(image);
							li.setCreateTime(new Date());
							li.setCreateUser(user.getUserId());
							li.setOrgId(user.getOrgId());
							if (plan.getPlansId()!=null) {
								li.setPlansId(plan.getPlansId());
							}
							li.setImgType("01");
							workorderImplePlansImgMapper.insertSelective(li);
							li = null;
						}
					}
				}
			}
		}
		return reg;
	}

	/**
	 * 
	 * @Title: handle
	 * @Description: 处理数据
	 * @param @param plan
	 * @param @param jumperInfo
	 * @param @param user 
	 * @return void 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月7日 上午11:24:32
	 * @version V1.0
	 */
	private void handle(WorkorderImplePlans plan, JumperInfo jumperInfo, Users user) {
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
			plan.setAdevId(jumperInfo.getDevId());//A端设施ID
			plan.setZdevId(jumperInfo.getZdevId());//Z端设施ID
			plan.setDesignId(workRout.getDesignplanId());//调度工单ID
		}
		Fiberdisc fiberA = fiberdiscMapper.queryByPort(jumperInfo.getCode());
		Fiberdisc fiberZ=null;
		if (jumperInfo.getZcode()!=null && !"".equals(jumperInfo.getZcode())) {
			 fiberZ = fiberdiscMapper.queryByPort(jumperInfo.getZcode());
		}
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
						}else {
							plan.setIsOccupySame("1");//占用情况是否一致 0 不一致 1 一致
						}
					}
				}
			}
		}
		if (fiberA != null && fiberZ == null) {
			plan.setAfiberDiscId(fiberA.getFiberDiscId());//A端端子ID
			if(jumperInfo.getAdevData() != null) {
				char strA=jumperInfo.getAdevData().charAt(fiberA.getDiscColNo().intValue()-1);
				if(strA!=fiberA.getIsOccup().charAt(0)) {
					plan.setIsOccupySame("0");//占用情况是否一致 0 不一致 1 一致
				}else {
					plan.setIsOccupySame("1");//占用情况是否一致 0 不一致 1 一致
				}
			}
		}
		plan.setPortA(jumperInfo.getCode());//A端端子编码
		plan.setPortZ(jumperInfo.getZcode());//Z端端子编码
		//判断 实施方案是否一致 0 否 1 是
		WorkorderFiberdiscabindA workFiberA = workorderFiberdiscabindAMapper.queryByRoutsIds(jumperInfo.getDesignroutesId(), workRout.getAdevId());
		WorkorderFiberdiscabindZ workFiberZ=null;
		if (workRout.getZdevId()!=null) {
			 workFiberZ = workorderFiberdiscabindZMapper.queryByRoutsIds(jumperInfo.getDesignroutesId(), workRout.getZdevId());
		}
		if(workFiberA != null && workFiberA.getFiberDiscId() != null 
				&& workFiberZ != null && workFiberZ.getFiberDiscId() != null&& fiberA!=null&& fiberZ!=null
				&& fiberA.getFiberDiscId() != null && fiberZ.getFiberDiscId() !=null) {
			if(workFiberA.getFiberDiscId().longValue()==fiberA.getFiberDiscId().longValue() && workFiberZ.getFiberDiscId().longValue()==fiberZ.getFiberDiscId().longValue()) {
				plan.setIsImpleSame("1");//实施方案是否一致 0 否 1 是
			}else {
				plan.setIsImpleSame("0");
			}
		}
		if ( fiberA!=null&& fiberZ==null&&workFiberA != null && workFiberA.getFiberDiscId() != null && workFiberZ==null) {
			if(workFiberA.getFiberDiscId().longValue()==fiberA.getFiberDiscId().longValue()) {
				plan.setIsImpleSame("1");//实施方案是否一致 0 否 1 是
			}else {
				plan.setIsImpleSame("0");
			}
		}
		if (workFiberA == null&&workFiberZ==null ) {
			plan.setIsImpleSame("0");
		}
	}
}
  
    