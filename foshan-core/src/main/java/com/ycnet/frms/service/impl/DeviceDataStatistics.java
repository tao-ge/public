package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.service.DeviceDataStateService;
import com.ycnet.frms.service.DeviceDiscinfoService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.vo.DifferentPortsBean;

@Service
@Transactional
public class DeviceDataStatistics {
	
	@Resource(name="deviceDataStateService")
	private DeviceDataStateService deviceDataStateService;
	
	@Resource(name="fiberdiscService")
	private FiberdiscService fiberdiscService;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="deviceDiscinfoService")
	private DeviceDiscinfoService deviceDiscinfoService;
	
	@Scheduled(cron="0 0 2 * * ?")   //每天凌晨2点
//	@Scheduled(cron="0 03 16 ? * *")
	public void insertDevicedataStatistics() {
		try {
			Date date = new Date();
			List<Facility> fList = facilityService.queryFacilityByOrgId();
			if(fList != null && fList.size() > 0) {
				for (Facility fa : fList) {
					if(fa.getOrgId() != null) {
						//端子数据统计-区域,数据查询
						List<DifferentPortsBean> listByArea = handleDatasByAreas(fa.getOrgId());
						//端子数据统计-业务,数据数据查询
						List<DifferentPortsBean> listDev = handleDatasByDev(fa.getOrgId());
						if((listByArea != null && listByArea.size() > 0) || (listDev != null && listDev.size() > 0)) {
							deviceDataStateService.saveDeviceDataState(listByArea,listDev,fa.getOrgId(),date);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @Title: handleDatasByAreas
	 * @Description: 端子数据统计-区域,数据处理
	 * @param @param orgId 
	 * @return void 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午11:19:16
	 * @version V1.0
	 * @return 
	 */
	private List<DifferentPortsBean> handleDatasByAreas(Long orgId) {
		//根据orgId,查询端子占用
		List<DifferentPortsBean> list1=fiberdiscService.queryOccupyConditionByOrgId(orgId);
		//查询上报端子占用情况
		List<DifferentPortsBean> list2=deviceDiscinfoService.queryReportOccupyByOrgId(orgId);
		//未知,null的list集合
		List<DifferentPortsBean> ilist = new ArrayList<DifferentPortsBean>();
		//端口占用计算
		if (list1.size()>0 && list1!=null) {
			Iterator<DifferentPortsBean> itList = list1.iterator();
			while(itList.hasNext()){
				DifferentPortsBean differentPortsBean=itList.next();
				if (differentPortsBean.getIsNotOccup()+differentPortsBean.getIsOccup()!=0) {
					differentPortsBean.setOccupyBat(Double.valueOf(differentPortsBean.getIsOccup())/Double.valueOf((differentPortsBean.getIsNotOccup()+differentPortsBean.getIsOccup())));
				}
				//未知的,归为一组计算
				if (differentPortsBean.getAreaName()==null || "".equals(differentPortsBean.getAreaName())) {
					ilist.add(differentPortsBean);
					itList.remove();
				}
			}
		}
		//计算未知的端口占用
		if (ilist.size()>0 && ilist!=null) {
			DifferentPortsBean differentPortsBean1 = new DifferentPortsBean();
			differentPortsBean1.setAreaName("未知");
			int isNotOccup = 0;
			int isOccup = 0;
			for (int j = 0; j < ilist.size(); j++) {
				isOccup += ilist.get(j).getIsOccup();
				isNotOccup +=ilist.get(j).getIsNotOccup();
			}
			differentPortsBean1.setOrgId(orgId);
			differentPortsBean1.setIsOccup(isOccup);
			differentPortsBean1.setIsNotOccup(isNotOccup);
			differentPortsBean1.setOccupyBat(Double.valueOf(isOccup)/Double.valueOf((isNotOccup+isOccup)));
			list1.add(differentPortsBean1);
		}
		//端口差异数据计算
		for (int i = 0; i < list1.size(); i++) {
			DifferentPortsBean differentPortsBean1=list1.get(i);
			if (list2.size()>0 && list2 !=null) {
				for (int j = 0; j < list2.size(); j++) {
					DifferentPortsBean differentPortsBean2=list2.get(j);
					if (differentPortsBean2.getAreaCode1().equals(differentPortsBean1.getAreaCode1())) {
						if (differentPortsBean1.getAreaCode1()!=null && !"".equals(differentPortsBean1.getAreaCode1()) && differentPortsBean2.getAreaCode1()!=null && !"".equals(differentPortsBean2.getAreaCode1()) && differentPortsBean1.getAreaCode1().equals(differentPortsBean2.getAreaCode1())) {
							differentPortsBean1.setPortFreeNum(differentPortsBean2.getPortFreeNum());
							differentPortsBean1.setPortOccupyNum(differentPortsBean2.getPortOccupyNum());
							differentPortsBean1.setPortErrorNum(differentPortsBean2.getPortErrorNum());
							if (differentPortsBean2.getPortFreeNum()+differentPortsBean2.getPortOccupyNum()!=0) {
								differentPortsBean1.setErrorNumBat(Double.valueOf(differentPortsBean2.getPortErrorNum())/Double.valueOf(differentPortsBean2.getPortFreeNum()+differentPortsBean2.getPortOccupyNum()));
							}
						}
						if (differentPortsBean1.getAreaName().equals("未知")) {
							DifferentPortsBean nullBean = deviceDiscinfoService.queryAreaCodeIsNull(orgId);
							if (nullBean !=null) {
								differentPortsBean1.setPortFreeNum(nullBean.getPortFreeNum());
								differentPortsBean1.setPortOccupyNum(nullBean.getPortOccupyNum());
								differentPortsBean1.setPortErrorNum(nullBean.getPortErrorNum());
								if (nullBean.getPortFreeNum()+nullBean.getPortOccupyNum()!=0) {
									differentPortsBean1.setErrorNumBat(Double.valueOf(nullBean.getPortErrorNum())/Double.valueOf(nullBean.getPortFreeNum()+nullBean.getPortOccupyNum()));
								}
							}
						}
					}
				}
			}
		}
		return list1;
	}
	
	/**
	 * 
	 * @Title: handleDatasByDev
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param orgId 
	 * @return void 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 上午11:25:25
	 * @version V1.0
	 * @return 
	 */
	private List<DifferentPortsBean> handleDatasByDev(Long orgId) {
		//查询端子占用
		List<DifferentPortsBean> list1=fiberdiscService.queryOccupyConditionByArea(orgId);
		//查询上报端子占用情况
		List<DifferentPortsBean> list2=deviceDiscinfoService.queryReportOccupyByArea(orgId);
		if (list1.size()>0 && list1!=null) {
			for (int i = 0; i < list1.size(); i++) {
				DifferentPortsBean differentPortsBean1 = list1.get(i);
				if (differentPortsBean1.getIsNotOccup()+differentPortsBean1.getIsOccup()!=0) {
					differentPortsBean1.setOccupyBat(Double.valueOf(differentPortsBean1.getIsOccup())/Double.valueOf((differentPortsBean1.getIsNotOccup()+differentPortsBean1.getIsOccup())));
				}
				if (list2.size()>0 && list2 !=null) {
					if(i<list2.size()) {
						DifferentPortsBean differentPortsBean2=list2.get(i);
						if (differentPortsBean1.getAreaCode1()!=null && !"".equals(differentPortsBean1.getAreaCode1()) && differentPortsBean2.getAreaCode1()!=null && !"".equals(differentPortsBean2.getAreaCode1()) && differentPortsBean1.getAreaCode1().equals(differentPortsBean2.getAreaCode1())) {
							differentPortsBean1.setPortFreeNum(differentPortsBean2.getPortFreeNum());
							differentPortsBean1.setPortOccupyNum(differentPortsBean2.getPortOccupyNum());
							differentPortsBean1.setPortErrorNum(differentPortsBean2.getPortErrorNum());
							if (differentPortsBean2.getPortFreeNum()+differentPortsBean2.getPortOccupyNum()!=0) {
								differentPortsBean1.setErrorNumBat(Double.valueOf(differentPortsBean2.getPortErrorNum())/Double.valueOf(differentPortsBean2.getPortFreeNum()+differentPortsBean2.getPortOccupyNum()));
							}
						}
					}
				}
			}
		}
		return list1;
	}
}
  
    