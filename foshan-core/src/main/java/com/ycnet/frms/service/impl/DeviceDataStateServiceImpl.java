package com.ycnet.frms.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ycnet.frms.bean.DeviceDataState;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.mapper.DeviceDataStateMapper;
import com.ycnet.frms.service.DeviceDataStateService;
import com.ycnet.frms.vo.DifferentPortsBean;

@Service("deviceDataStateService")
public class DeviceDataStateServiceImpl implements DeviceDataStateService {

	@Resource(name="deviceDataStateMapper")
	private DeviceDataStateMapper deviceDataStateMapper;

	/**
	 * 
	 * @Title: saveDeviceDataState
	 * @Description: 添加保存数据
	 * @param @param listByArea
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月26日 下午1:39:10
	 * @version V1.0
	 */
	@Override
	@Transactional
	public int saveDeviceDataState(List<DifferentPortsBean> listByArea, List<DifferentPortsBean> listDev, Long orgId, Date date) {
		DeviceDataState dds = null;
		deviceDataStateMapper.deleteByDataType("01",orgId);
		if(listByArea != null) {
			//添加区域数据
			for (DifferentPortsBean dpb : listByArea) {
				dds = new DeviceDataState();
				dds.setDataType("01");
				dds.setCreateTime(date);
//			dds.setDevId(dpb.getDevId());
				double occupyBat = countHalfUp(dpb.getOccupyBat()*100);
				double errorNumBat = countHalfUp(dpb.getErrorNumBat()*100);
				String content = new String(dpb.getIsNotOccup()+","+dpb.getIsOccup()+","+occupyBat+"%,"+dpb.getPortFreeNum()+","+dpb.getPortOccupyNum()+","+dpb.getPortErrorNum()+","+errorNumBat+"%,");
				dds.setDataContent(content);
				dds.setAreaCode(dpb.getAreaCode1());
				dds.setAreaName(dpb.getAreaName());
				dds.setOrgId(orgId);
				deviceDataStateMapper.insertSelective(dds);
				dds = null;
			}
		}
		deviceDataStateMapper.deleteByDataType("02",orgId);
		List<DeviceDataState> list = new ArrayList<DeviceDataState>();
		if(listDev != null) {
			//添加设施数据
			for (DifferentPortsBean dpb : listDev) {
				dds = new DeviceDataState();
				dds.setDataType("02");
				dds.setCreateTime(date);
				dds.setDevId(dpb.getDevId());
				double occupyBat = countHalfUp(dpb.getOccupyBat()*100);
				double errorNumBat = countHalfUp(dpb.getErrorNumBat()*100);
				String content = new String(dpb.getIsNotOccup()+","+dpb.getIsOccup()+","+occupyBat+"%,"+dpb.getPortFreeNum()+","+dpb.getPortOccupyNum()+","+dpb.getPortErrorNum()+","+errorNumBat+"%,");
				dds.setDataContent(content);
				dds.setAreaCode(dpb.getAreaCode1());
				dds.setAreaName(dpb.getAreaName());
				dds.setOrgId(orgId);
				list.add(dds);
//				deviceDataStateMapper.insertSelective(dds);
				dds = null;
			}
		}
//		//添加区域数据
//		int saveAreaReq = deviceDataStateMapper.insertList(listByArea);
//		//添加设施数据
		deviceDataStateMapper.insertList(list);
		return 0;
	}

	private double countHalfUp(Double occupyBat) {
		BigDecimal   b   =   new   BigDecimal(occupyBat);    
		double   f   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		return f;
	}

	/**
	 * 端子差异数据统计地区
	* @Title: queryByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月26日 上午9:24:20 
	* @version V1.0
	 */
	@Override
	public PageBean queryByOrgId(Long orgId, PageBean pb) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("pb", pb);
		pb.setList(deviceDataStateMapper.queryListByConditions(map));
		//pb.setRows(deviceDataStateMapper.queryCounts(map));
		return pb;
	}

	/**
	 * 端子差异数据统计业务点
	* @Title: queryDifferentTotals 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param pb
	* @param @param differentPortsBean
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月26日 上午11:43:00 
	* @version V1.0
	 */
	@Override
	public PageBean queryDifferentTotals(Long orgId, PageBean pb, DifferentPortsBean differentPortsBean) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("pb", pb);
		if (differentPortsBean.getAreaCode2()!=null && !"".equals(differentPortsBean.getAreaCode2()) &&differentPortsBean.getAreaCode1()!=null && !"".equals(differentPortsBean.getAreaCode1())) {
			differentPortsBean.setAreaCode1(differentPortsBean.getAreaCode2());
		}
		if ((differentPortsBean.getAreaCode2()==null || "".equals(differentPortsBean.getAreaCode2())) &&differentPortsBean.getAreaCode1()!=null && !"".equals(differentPortsBean.getAreaCode1())) {
			if (!differentPortsBean.getAreaCode1().equals("0")) {
				map.put("code", differentPortsBean.getAreaCode1());
				differentPortsBean.setAreaCode1("");
			}else {
				map.put("code", "");
			}
		}
		map.put("differentPortsBean", differentPortsBean);
		pb.setList(deviceDataStateMapper.queryDifferentTotals(map));
		pb.setRows(deviceDataStateMapper.queryDifferentTotalsCounts(map));
		return pb;
	}

}
  
    