package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.WorkorderFiberdiscabindA;
import com.ycnet.frms.bean.WorkorderFiberdiscabindZ;
import com.ycnet.frms.bean.WorkorderImplePlans;
import com.ycnet.frms.bean.WorkorderRoutes;
import com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper;
import com.ycnet.frms.mapper.DeviceEntityMapper;
import com.ycnet.frms.mapper.DiscinfoMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.mapper.WorkorderDesignMapper;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindAMapper;
import com.ycnet.frms.mapper.WorkorderFiberdiscabindZMapper;
import com.ycnet.frms.mapper.WorkorderImplePlansMapper;
import com.ycnet.frms.mapper.WorkorderMapper;
import com.ycnet.frms.mapper.WorkorderRoutesMapper;
import com.ycnet.frms.service.WorkorderDesignService;
import com.ycnet.frms.vo.Group1;
import com.ycnet.frms.vo.WorkorderBean;
import com.ycnet.frms.vo.WorkorderFiberdiscabindVo;
import com.ycnet.frms.vo.WorkorderFiberdiscabindVoA;
import com.ycnet.frms.vo.WorkorderFiberdiscabindVoZ;

@Service("workorderDesignService")
public class WorkorderDesignServiceImpl implements WorkorderDesignService {

	@Resource(name = "workorderDesignMapper")
	private WorkorderDesignMapper workorderDesignMapper;

	@Resource(name = "workorderFiberdiscabindAMapper")
	private WorkorderFiberdiscabindAMapper workorderFiberdiscabindAMapper;

	@Resource(name = "workorderFiberdiscabindZMapper")
	private WorkorderFiberdiscabindZMapper workorderFiberdiscabindZMapper;

	@Resource(name = "workorderMapper")
	private WorkorderMapper workorderMapper;

	@Resource(name = "facilityMapper")
	private FacilityMapper facilityMapper;

	@Resource(name = "fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;

	@Resource(name = "workorderRoutesMapper")
	private WorkorderRoutesMapper workorderRoutesMapper;

	@Resource(name = "linesMapper")
	private LinesMapper linesMapper;
	
	@Resource(name = "discinfoMapper")
	private DiscinfoMapper discinfoMapper;
	
	@Resource(name = "deviceDiscinfoEntityMapper")
	private DeviceDiscinfoEntityMapper deviceDiscinfoEntityMapper;
	
	@Resource(name = "deviceEntityMapper")
	private DeviceEntityMapper deviceEntityMapper;
	
	@Resource(name="workorderImplePlansMapper")
	private WorkorderImplePlansMapper workorderImplePlansMapper;

	/**
	 * 
	 * @Title: queryBydesignId
	 * @Description: 根据调度工单ID查询调度工单详细
	 * @param @param
	 *            session
	 * @param @param
	 *            designId
	 * @param @param
	 *            userId
	 * @param @return
	 * @return Object
	 * @author liucanghai
	 * @throws @date
	 *             2018年3月26日 上午10:59:03
	 * @version V1.0
	 */
	@Override
	public WorkorderBean queryBydesignId(Long designId) {
		WorkorderBean work = workorderMapper.queryBydesignId(designId);
		if (work != null) {
			List<WorkorderRoutes> rouList = workorderRoutesMapper.queryRoutesAdevByDesignId(designId);
			if (rouList != null && rouList.size() > 0) {
				for (int i = 0; i < rouList.size(); i++) {
					Facility fa = facilityMapper.selectByPrimaryKey(rouList.get(i).getAdevId());
					rouList.get(i).setAdevName(fa.getDevName());
					if (rouList.get(i).getZdevId() != 0) {
						Facility fz = facilityMapper.selectByPrimaryKey(rouList.get(i).getZdevId());
						rouList.get(i).setZdevName(fz.getDevName());
					}
					WorkorderImplePlans plan = workorderImplePlansMapper.queryByDesignroutesId(rouList.get(i).getDesignroutesId());
					if(plan != null) {
						rouList.get(i).setRouState("1");//是否完成标识 0 未完成 1 已完成
					}else {
						rouList.get(i).setRouState("0");//是否完成标识 0 未完成 1 已完成
					}
//					WorkorderFiberdiscabindA fiberA = workorderFiberdiscabindAMapper
//							.queryByRoutsIds(rouList.get(i).getDesignroutesId(), rouList.get(i).getAdevId());
//					WorkorderFiberdiscabindZ fiberZ = workorderFiberdiscabindZMapper
//							.queryByRoutsIds(rouList.get(i).getDesignroutesId(), rouList.get(i).getZdevId());
//					if (fiberA != null && fiberZ != null) {
//						Fiberdisc fiA = fiberdiscMapper.selectByPrimaryKey(fiberA.getFiberDiscId());
//						Fiberdisc fiZ = fiberdiscMapper.selectByPrimaryKey(fiberZ.getFiberDiscId());
//						if ("1".equals(fiA.getIsOccup()) && "1".equals(fiZ.getIsOccup())) {
//							rouList.get(i).setRouState("1");// 完成
//						} else {
//							rouList.get(i).setRouState("0");// 未完成
//						}
//					} else {
//						rouList.get(i).setRouState("0");// 未完成
//					}
				}
				work.setWokRouteList(rouList);
			}
		}
		return work;
	}

	/**
	 * 
	 * @Title: queryFiberHandle
	 * @Description: 查询返回端子信息
	 * @param @param
	 *            session
	 * @param @param
	 *            userId
	 * @param @param
	 *            designId
	 * @param @param
	 *            devId
	 * @param @return
	 * @return Object
	 * @author liucanghai
	 * @throws @date
	 *             2018年4月8日 上午9:55:37
	 * @version V1.0
	 */
	@Override
	public WorkorderFiberdiscabindVo queryFiberHandle(Long designroutesId, Long devId) {
		WorkorderFiberdiscabindVo vo = new WorkorderFiberdiscabindVo();
		Facility facility = facilityMapper.selectByPrimaryKey(devId);
		vo.setDevId(devId);
		vo.setDevCode(facility.getDevCode());
		vo.setDevName(facility.getDevName());
		vo.setDevType(facility.getDevType());
		vo.setDevState(facility.getDevState());
		WorkorderFiberdiscabindA fiberA = null;
		WorkorderFiberdiscabindZ fiberZ = null;
		WorkorderRoutes routes = workorderRoutesMapper.selectByPrimaryKey(designroutesId);
		fiberA = workorderFiberdiscabindAMapper.queryByRoutsIds(designroutesId, devId);
		Discinfo disc = null;
		DeviceDiscinfoEntity ddf = null;
		DeviceEntity device = null;
		if (routes.getRoutesSort() == 1) { // 第一个
			vo.setIsBend("1");
			if (fiberA != null) {
				if (fiberA.getFiberDiscId() != null) {
					WorkorderFiberdiscabindVoA voA = new WorkorderFiberdiscabindVoA();
					WorkorderFiberdiscabindVoZ voZ = new WorkorderFiberdiscabindVoZ();
					Fiberdisc fiberdiscA = fiberdiscMapper.selectByPrimaryKey(fiberA.getFiberDiscId());
					if (fiberdiscA != null) {
						disc = new Discinfo();
						ddf = new DeviceDiscinfoEntity();
						//查盘
						if(fiberdiscA.getDiscCode() != null && !"".equals(fiberdiscA.getDiscCode())) {
							disc = discinfoMapper.selectByDiscCode(fiberdiscA.getDiscCode());
						}
						//查端子控制器
						if(disc != null && disc.getDiscId() != null) {
							ddf = deviceDiscinfoEntityMapper.selectByPrimaryKey(disc.getDiscId());
							if(ddf != null && ddf.getCodId() != null) {
								device = deviceEntityMapper.selectByPrimaryKey(ddf.getCodId());
							}
						}
						//设置fiberA端子控制器编码
						if(ddf!=null && ddf.getDiscContrCode() != null && !"".equals(ddf.getDiscContrCode())) {
							voA.setDiscContrCode(ddf.getDiscContrCode());
						}
						//设置fiberA中控器MAC
						if(device != null && device.getCodMac() != null && !"".equals(device.getCodMac())) {
							voA.setCodMac(device.getCodMac());
						}
						voA.setAport01(fiberdiscA.getPort01());
						voA.setAdiscCode(fiberdiscA.getDiscCode());
						voA.setAfiberNum(fiberdiscA.getFiberNum());
						voA.setAfiberDiscId(fiberA.getFiberDiscId());
						if (fiberdiscA.getDevId() != null) {
							voA.setAdevId(fiberdiscA.getDevId());// adevId
							Facility fa = facilityMapper.selectByPrimaryKey(fiberdiscA.getDevId());
							if (fa != null) {
								voA.setAdevCode(fa.getDevCode());
								voA.setAdevName(fa.getDevName());
								voA.setAdevType(fa.getDevType());
							}
						}
						Lines line = linesMapper.queryLineType02(fiberdiscA.getPort01());
						if (line != null) {
							voA.setLineId(line.getLineId());// 原跳纤ID
							voA.setSrvName(line.getSrvName());
						}
					}
					Group1 group = fiberdiscMapper.queryByGroupDesc(fiberA.getFiberDiscId());
					if (group != null) {
						voA.setDiscColNoA(group.getDiscColNo());
						voA.setDiscRowNoA(group.getDiscRowNo());
						voA.setGroupDescA(group.getGroupDesc());
						voA.setGroupNameA(group.getGroupName());
						voA.setPortNumA(group.getPortNum());
						voA.setSideA(group.getSide());
					}
					if (voA != null) {
						vo.setFiberA(voA);
						// AZ相同
						//设置fiberZ端子控制器编码
						if(voA.getDiscContrCode() != null && !"".equals(voA.getDiscContrCode())) {
							voZ.setDiscContrCode(voA.getDiscContrCode());
						}
						//设置fiberA中控器MAC
						if(voA.getCodMac() != null && !"".equals(voA.getCodMac())) {
							voZ.setCodMac(voA.getCodMac());
						}
						voZ.setZport01(voA.getAport01());
						voZ.setZdiscCode(voA.getAdiscCode());
						voZ.setZfiberNum(voA.getAfiberNum());
						voZ.setZfiberDiscId(voA.getAfiberDiscId());
						voZ.setZdevId(voA.getAdevId());
						voZ.setZdevCode(voA.getAdevCode());
						voZ.setZdevName(voA.getAdevName());
						voZ.setZdevType(voA.getAdevType());
						voZ.setLineId(voA.getLineId());
						voZ.setSrvName(voA.getSrvName());
						voZ.setDiscColNoZ(voA.getDiscColNoA());
						voZ.setDiscRowNoZ(voA.getDiscRowNoA());
						voZ.setGroupDescZ(voA.getGroupDescA());
						voZ.setGroupNameZ(voA.getGroupNameA());
						voZ.setPortNumZ(voA.getPortNumA());
						voZ.setSideZ(voA.getSideA());
						vo.setFiberZ(voZ);
					}
				}
			}

		} else if (routes.getZdevId() == 0 || routes.getZdevId() == null) {// 最后一个
			vo.setIsBend("1");
			WorkorderRoutes routeA = workorderRoutesMapper.selectDesignAndRouteSort(routes.getDesignplanId(),
					routes.getRoutesSort() - 1);
			fiberZ = workorderFiberdiscabindZMapper.queryByRoutsIds(routeA.getDesignroutesId(), routeA.getZdevId());
			if (fiberZ != null) {
				if (fiberZ.getFiberDiscId() != null) {
					WorkorderFiberdiscabindVoA voA = new WorkorderFiberdiscabindVoA();
					WorkorderFiberdiscabindVoZ voZ = new WorkorderFiberdiscabindVoZ();
					Fiberdisc fiberdiscZ = fiberdiscMapper.selectByPrimaryKey(fiberZ.getFiberDiscId());
					if (fiberdiscZ != null) {
						disc = new Discinfo();
						ddf = new DeviceDiscinfoEntity();
						//查盘
						if(fiberdiscZ.getDiscCode() != null && !"".equals(fiberdiscZ.getDiscCode())) {
							disc = discinfoMapper.selectByDiscCode(fiberdiscZ.getDiscCode());
						}
						//查端子控制器
						if(disc != null && disc.getDiscId() != null) {
							ddf = deviceDiscinfoEntityMapper.selectByPrimaryKey(disc.getDiscId());
							if(ddf != null && ddf.getCodId() != null) {
								device = deviceEntityMapper.selectByPrimaryKey(ddf.getCodId());
							}
						}
						//设置fiberA端子控制器编码
						if(ddf!=null && ddf.getDiscContrCode() != null && !"".equals(ddf.getDiscContrCode())) {
							voA.setDiscContrCode(ddf.getDiscContrCode());
						}
						//设置fiberA中控器MAC
						if(device != null && device.getCodMac() != null && !"".equals(device.getCodMac())) {
							voA.setCodMac(device.getCodMac());
						}
						voA.setAdiscCode(fiberdiscZ.getDiscCode());
						voA.setAport01(fiberdiscZ.getPort01());
						voA.setAfiberDiscId(fiberdiscZ.getFiberDiscId());
						voA.setAfiberNum(fiberdiscZ.getFiberNum());
						if (fiberdiscZ.getDevId() != null) {
							voA.setAdevId(fiberdiscZ.getDevId());
							Facility fa = facilityMapper.selectByPrimaryKey(fiberdiscZ.getDevId());
							if (fa != null) {
								voA.setAdevCode(fa.getDevCode());
								voA.setAdevName(fa.getDevName());
								voA.setAdevType(fa.getDevType());
							}
						}
						Lines line = linesMapper.queryLineType02(fiberdiscZ.getPort01());
						if (line != null) {
							voA.setLineId(line.getLineId());
							voA.setSrvName(line.getSrvName());
						}
					}
					Group1 group = fiberdiscMapper.queryByGroupDesc(fiberZ.getFiberDiscId());
					if (group != null) {
						voA.setDiscColNoA(group.getDiscColNo());
						voA.setDiscRowNoA(group.getDiscRowNo());
						voA.setGroupDescA(group.getGroupDesc());
						voA.setGroupNameA(group.getGroupName());
						voA.setPortNumA(group.getPortNum());
						voA.setSideA(group.getSide());
					}
					if (voA != null) {
						vo.setFiberA(voA);
						//设置fiberZ端子控制器编码
						if(voA.getDiscContrCode() != null && !"".equals(voA.getDiscContrCode())) {
							voZ.setDiscContrCode(voA.getDiscContrCode());
						}
						//设置fiberA中控器MAC
						if(voA.getCodMac() != null && !"".equals(voA.getCodMac())) {
							voZ.setCodMac(voA.getCodMac());
						}
						voZ.setZport01(voA.getAport01());
						voZ.setZdiscCode(voA.getAdiscCode());
						voZ.setZfiberNum(voA.getAfiberNum());
						voZ.setZfiberDiscId(voA.getAfiberDiscId());
						voZ.setZdevId(voA.getAdevId());
						voZ.setZdevCode(voA.getAdevCode());
						voZ.setZdevName(voA.getAdevName());
						voZ.setZdevType(voA.getAdevType());
						voZ.setLineId(voA.getLineId());
						voZ.setSrvName(voA.getSrvName());
						voZ.setDiscColNoZ(voA.getDiscColNoA());
						voZ.setDiscRowNoZ(voA.getDiscRowNoA());
						voZ.setGroupDescZ(voA.getGroupDescA());
						voZ.setGroupNameZ(voA.getGroupNameA());
						voZ.setPortNumZ(voA.getPortNumA());
						voZ.setSideZ(voA.getSideA());
						vo.setFiberZ(voZ);
					}
				}

			}
		} else {// 中间的
				// Z端信息
			if (fiberA != null) {
				if (fiberA.getFiberDiscId() != null) {
					WorkorderFiberdiscabindVoZ voZ = new WorkorderFiberdiscabindVoZ();
					Fiberdisc fiberdiscZ = fiberdiscMapper.selectByPrimaryKey(fiberA.getFiberDiscId());
					if (fiberdiscZ != null) {
						disc = new Discinfo();
						ddf = new DeviceDiscinfoEntity();
						//查盘
						if(fiberdiscZ.getDiscCode() != null && !"".equals(fiberdiscZ.getDiscCode())) {
							disc = discinfoMapper.selectByDiscCode(fiberdiscZ.getDiscCode());
						}
						//查端子控制器
						if(disc != null && disc.getDiscId() != null) {
							ddf = deviceDiscinfoEntityMapper.selectByPrimaryKey(disc.getDiscId());
							if(ddf != null && ddf.getCodId() != null) {
								device = deviceEntityMapper.selectByPrimaryKey(ddf.getCodId());
							}
						}
						//设置fiberA端子控制器编码
						if(ddf!=null && ddf.getDiscContrCode() != null && !"".equals(ddf.getDiscContrCode())) {
							voZ.setDiscContrCode(ddf.getDiscContrCode());
						}
						//设置fiberA中控器MAC
						if(device != null && device.getCodMac() != null && !"".equals(device.getCodMac())) {
							voZ.setCodMac(device.getCodMac());
						}
						voZ.setZport01(fiberdiscZ.getPort01());
						voZ.setZdiscCode(fiberdiscZ.getDiscCode());
						voZ.setZfiberNum(fiberdiscZ.getFiberNum());
						voZ.setZfiberDiscId(fiberA.getFiberDiscId());
						if (fiberdiscZ.getDevId() != null) {
							voZ.setZdevId(fiberdiscZ.getDevId());
							Facility fz = facilityMapper.selectByPrimaryKey(fiberdiscZ.getDevId());
							if (fz != null) {
								voZ.setZdevCode(fz.getDevCode());
								voZ.setZdevName(fz.getDevName());
								voZ.setZdevType(fz.getDevType());
							}
						}
						Lines line = linesMapper.queryLineType02(fiberdiscZ.getPort01());
						if (line != null) {
							voZ.setLineId(line.getLineId());
							voZ.setSrvName(line.getSrvName());
						}
					}
					Group1 group = fiberdiscMapper.queryByGroupDesc(fiberA.getFiberDiscId());
					if (group != null) {
						voZ.setDiscColNoZ(group.getDiscColNo());
						voZ.setDiscRowNoZ(group.getDiscRowNo());
						voZ.setGroupDescZ(group.getGroupDesc());
						voZ.setGroupNameZ(group.getGroupName());
						voZ.setPortNumZ(group.getPortNum());
						voZ.setSideZ(group.getSide());
					}
					if (voZ != null) {
						vo.setFiberZ(voZ);
					}
				}
			}
			// A端信息
			WorkorderRoutes routeA = workorderRoutesMapper.selectDesignAndRouteSort(routes.getDesignplanId(),
					routes.getRoutesSort() - 1);
			fiberZ = workorderFiberdiscabindZMapper.queryByRoutsIds(routeA.getDesignroutesId(), routeA.getZdevId());
			if (fiberZ != null) {
				if (fiberZ.getFiberDiscId() != null) {
					WorkorderFiberdiscabindVoA voA = new WorkorderFiberdiscabindVoA();
					Fiberdisc fiberdiscZ = fiberdiscMapper.selectByPrimaryKey(fiberZ.getFiberDiscId());
					if (fiberdiscZ != null) {
						disc = new Discinfo();
						ddf = new DeviceDiscinfoEntity();
						//查盘
						if(fiberdiscZ.getDiscCode() != null && !"".equals(fiberdiscZ.getDiscCode())) {
							disc = discinfoMapper.selectByDiscCode(fiberdiscZ.getDiscCode());
							if(ddf != null && ddf.getCodId() != null) {
								device = deviceEntityMapper.selectByPrimaryKey(ddf.getCodId());
							}
						}
						//查端子控制器
						if(disc != null && disc.getDiscId() != null) {
							ddf = deviceDiscinfoEntityMapper.selectByPrimaryKey(disc.getDiscId());
							if(ddf != null && ddf.getCodId() != null) {
								device = deviceEntityMapper.selectByPrimaryKey(ddf.getCodId());
							}
						}
						//设置fiberA端子控制器编码
						if(ddf!=null && ddf.getDiscContrCode() != null && !"".equals(ddf.getDiscContrCode())) {
							voA.setDiscContrCode(ddf.getDiscContrCode());
						}
						//设置fiberA中控器MAC
						if(device != null && device.getCodMac() != null && !"".equals(device.getCodMac())) {
							voA.setCodMac(device.getCodMac());
						}
						voA.setAdiscCode(fiberdiscZ.getDiscCode());
						voA.setAport01(fiberdiscZ.getPort01());
						voA.setAfiberDiscId(fiberdiscZ.getFiberDiscId());
						voA.setAfiberNum(fiberdiscZ.getFiberNum());
						if (fiberdiscZ.getDevId() != null) {
							voA.setAdevId(fiberdiscZ.getDevId());
							Facility fa = facilityMapper.selectByPrimaryKey(fiberdiscZ.getDevId());
							if (fa != null) {
								voA.setAdevCode(fa.getDevCode());
								voA.setAdevName(fa.getDevName());
								voA.setAdevType(fa.getDevType());
							}
						}
						Lines line = linesMapper.queryLineType02(fiberdiscZ.getPort01());
						if (line != null) {
							voA.setLineId(line.getLineId());
							voA.setSrvName(line.getSrvName());
						}
					}
					Group1 group = fiberdiscMapper.queryByGroupDesc(fiberZ.getFiberDiscId());
					if (group != null) {
						voA.setDiscColNoA(group.getDiscColNo());
						voA.setDiscRowNoA(group.getDiscRowNo());
						voA.setGroupDescA(group.getGroupDesc());
						voA.setGroupNameA(group.getGroupName());
						voA.setPortNumA(group.getPortNum());
						voA.setSideA(group.getSide());
					}
					if (voA != null) {
						vo.setFiberA(voA);
					}
				}

			}

		}

		if (fiberA == null && fiberZ == null) {
			fiberA = null;
			fiberZ = null;
		}
		return vo;
	}

}
