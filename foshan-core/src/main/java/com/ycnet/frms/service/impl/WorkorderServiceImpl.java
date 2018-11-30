package com.ycnet.frms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderLightline;
import com.ycnet.frms.mapper.WorkorderLightlineMapper;
import com.ycnet.frms.mapper.WorkorderMapper;
import com.ycnet.frms.service.WorkorderService;
import com.ycnet.frms.vo.NewBacklogBean;
import com.ycnet.frms.vo.WorkorderBean;

@Service("workorderService")
public class WorkorderServiceImpl implements WorkorderService{

	@Resource(name="workorderMapper")
	private WorkorderMapper workorderMapper;
	
	@Resource(name="workorderLightlineMapper")
	private WorkorderLightlineMapper workorderLightlineMapper;
	
	/**
	 * 
	* @Title: queryFlowOrderUser 
	* @Description: 根据当前用户的流程角色查询工单 
	* @param @param request
	* @param @param session
	* @param @param userId
	* @param @param orderNumber
	* @param @param ordeTitle
	* @param @param devName
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年3月26日 上午9:19:28 
	* @version V1.0
	 */
//	@Override
//	public List<NewBacklogBean> queryFlowOrderUser(Users user, String orderNumber, String orderTitle,
//			String devName,PageBean pb,Integer state) {
//		if(state==0) {
//			return workorderMapper.queryFlowOrderUser(orderNumber,orderTitle,devName,user.getOrgId(),pb);
//		}else {//查询我的经办
//			return workorderMapper.queryMobileHandling(orderNumber,orderTitle,devName,user.getOrgId(),pb);
//		}
//	}
	
	@Override
	public List<Map<String, Object>> queryFlowOrderUser(Users user, String orderNumber, String orderTitle,
			PageBean pb,Integer state) {
		if(state == 0) {
			return workorderMapper.queryFlowOrderUser(orderNumber,orderTitle,user.getUserCode(),pb);
		}else {
			return workorderMapper.queryMobileHandling(orderNumber,orderTitle,user.getUserCode(),pb);
		}
	}

	/**
	 * 
	* @Title: queryByWorkorderId 
	* @Description: 根据工单ID查询工单详情 
	* @param @param session
	* @param @param workerOrderId
	* @param @param userId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年3月26日 上午10:54:41 
	* @version V1.0
	 */
	@Override
	public WorkorderBean queryByWorkorderId(Long workorderId) {
		WorkorderBean wok = workorderMapper.queryByWorkorderId(workorderId);
		if(wok!=null) {
			List<WorkorderLightline> lineList=workorderLightlineMapper.queryLineByWorkId(workorderId);
			if(lineList!=null && lineList.size()>0) {
				for(WorkorderLightline line:lineList) {
					if("3".equals(line.getPlanstate())) {
						line.setRouState("1");
					}else {
						line.setRouState("0");
					}
//					if(line.getaDevId()!=null && line.getzDevId()!=null) {
//						Facility fa = facilityMapper.selectByPrimaryKey(line.getaDevId());
//						Facility fz = facilityMapper.selectByPrimaryKey(line.getzDevId());
//						int rega=linesMapper.selectJumperByCode(fa.getDevCode());
//						int regz=linesMapper.selectJumperByCode(fz.getDevCode());
//						if(rega>0 || regz>0) {
//							line.setRouState("1");//存在跳纤
//						}else {
//							line.setRouState("0");//未跳纤
//						}
//					}else if(line.getaDevId()!=null){
//						Facility fa = facilityMapper.selectByPrimaryKey(line.getaDevId());
//						int rega=linesMapper.selectJumperByCode(fa.getDevCode());
//						if(rega>0) {
//							line.setRouState("1");
//						}else {
//							line.setRouState("0");
//						}
//					}else if(line.getzDevId()!=null) {
//						Facility fz = facilityMapper.selectByPrimaryKey(line.getzDevId());
//						int regz=linesMapper.selectJumperByCode(fz.getDevCode());
//						if(regz>0) {
//							line.setRouState("1");
//						}else {
//							line.setRouState("0");
//						}
//					}
				}
				wok.setWoklineList(lineList);
			}
		}
		return wok;
	}

}
