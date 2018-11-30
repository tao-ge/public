package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.mapper.DiscinfoStatusEntityMapper;
import com.ycnet.frms.service.DiscinfoStatusService;
import com.ycnet.frms.vo.DeviceDiscinfoEntityBean;

@Service("discinfoStatusService")
public class DiscinfoStatusServiceImpl implements DiscinfoStatusService{
	

	@Resource(name="discinfoStatusEntityMapper")
	private DiscinfoStatusEntityMapper discinfoStatusEntityMapper;
	
	/**
	 * 查询历史上报记录
	* @Title: queryhistoryDetialByDiscId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param discId
	* @param @return    入参
	* @return List<DiscinfoStatusEntityBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月8日 下午1:45:00 
	* @version V1.0
	 */
	@Override
	public List<DeviceDiscinfoEntityBean> queryhistoryDetialByDiscId(Long discId) {
		List<DeviceDiscinfoEntityBean> list = discinfoStatusEntityMapper.queryhistoryDetialByDiscId(discId);
		for (int i = 0; i < list.size(); i++) {
			List<String> lastReportDataList = new ArrayList<String>();
			DeviceDiscinfoEntityBean deviceDiscinfoEntity=list.get(i);
			char[] lastReportDatas=null;
			if (deviceDiscinfoEntity!=null && deviceDiscinfoEntity.getLastReportData()!=null&&!"".equals( deviceDiscinfoEntity.getLastReportData())) {
				 lastReportDatas = deviceDiscinfoEntity.getLastReportData().toCharArray();
			}
			if (lastReportDatas !=null &&lastReportDatas.length>0) {
				deviceDiscinfoEntity.setLastReportDataSize(lastReportDatas.length/2);
				for (int j = 0; j < lastReportDatas.length; j++) {
					char s = lastReportDatas[j];
					lastReportDataList.add(String.valueOf(s));
				}
			}
			deviceDiscinfoEntity.setLastReportDataList(lastReportDataList);
		}
		return list;
	}

}
