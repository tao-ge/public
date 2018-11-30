package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.Constants;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.DeviceStatus;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.DeviceStatusMapper;
import com.ycnet.frms.service.DeviceStatusService;
import com.ycnet.frms.vo.DeviceStatusBean;
import com.ycnet.frms.vo.DeviceStatusConditionBean;



@Service("deviceStatusService")
public class DeviceStatusServiceImpl implements DeviceStatusService {

	@Resource(name="deviceStatusMapper")
	private DeviceStatusMapper deviceStatusMapper;


	
	@Override
	public PageBean queryByConditionBean(DeviceStatusConditionBean bean,
			Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("devStaCon", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		
		pb.setRows(deviceStatusMapper.queryCountByConditionMap(conditionMap));//;
		pb.setList(this.Convert(deviceStatusMapper.queryByConditionMap(conditionMap)));		
				
		return pb;
	}
	
	/**
	 * 获取设施的当前状态
	 */
	@Override
	public PageBean queryCurStatusByConditionBean(
			DeviceStatusConditionBean bean, Users user, PageBean pb) {
		
		bean.setCurStatus("1");
//		pb.setPageSize(2000);
		pb=this.queryByConditionBean(bean, user, pb);
				
		return pb;
	}
	
	/**
	 * 转换实体bean为业务bean
	 * @param deviceStatusList
	 * @return
	 */
	private List<DeviceStatusBean> Convert(List<DeviceStatus> deviceStatusList)
	{
		List<DeviceStatusBean> dsbList=new ArrayList<DeviceStatusBean>();
		for(DeviceStatus ds:deviceStatusList)
		{
			DeviceStatusBean dsb=new DeviceStatusBean();
//			BeanUtils.copy(ds, dsb);			
			//fl  根据devId，查询当前设施运行状态状态
			List<DeviceReg> deviceRegList=new List<DeviceReg>() {
				
				@Override
				public <T> T[] toArray(T[] a) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Object[] toArray() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public List<DeviceReg> subList(int fromIndex, int toIndex) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int size() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public DeviceReg set(int index, DeviceReg element) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public boolean retainAll(Collection<?> c) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean removeAll(Collection<?> c) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public DeviceReg remove(int index) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public boolean remove(Object o) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public ListIterator<DeviceReg> listIterator(int index) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public ListIterator<DeviceReg> listIterator() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int lastIndexOf(Object o) {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public Iterator<DeviceReg> iterator() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public boolean isEmpty() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public int indexOf(Object o) {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public DeviceReg get(int index) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public boolean containsAll(Collection<?> c) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean contains(Object o) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public void clear() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public boolean addAll(int index, Collection<? extends DeviceReg> c) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean addAll(Collection<? extends DeviceReg> c) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public void add(int index, DeviceReg element) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public boolean add(DeviceReg e) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			String devStatu="2";
			if (deviceRegList !=null && deviceRegList.size()>0) {
				 devStatu = deviceRegList.get(0).getDevStatus();
			}
			dsb.setDevStatusId(ds.getDevStatusId());
			dsb.setDevId(ds.getDevId());
			dsb.setDevCode(ds.getDevCode());
			dsb.setDevName(ds.getDevName());
			dsb.setColTime(ds.getColTime());
			dsb.setRptTime(ds.getRptTime());
			dsb.setOprStyle(ds.getOprStyle());
			dsb.setLockStatus(ds.getLockStatus());
			dsb.setDoorStatus(ds.getDoorStatus());
			dsb.setLowTempThd(ds.getLowTempThd());
			dsb.setHighTempShd(ds.getHighTempShd());
			dsb.setTemp(ds.getTemp());
			dsb.setHumidityShd(ds.getHumidityShd());
			dsb.setHumidity(ds.getHumidity());
			dsb.setBatteryThd(ds.getBatteryThd());
			dsb.setBattery(ds.getBattery());
			dsb.setTiltThd(ds.getTiltThd());
			dsb.setTilt(ds.getTilt());
			dsb.setSignals(ds.getSignals());
			dsb.setDevImei(ds.getDevImei());
			dsb.setDevMac(ds.getDevMac());
			dsb.setAlarmSign(ds.getAlarmSign());
			dsb.setDealSign(ds.getDealSign());
			dsb.setCurStatus(ds.getCurStatus());
			dsb.setOrgId(ds.getOrgId());
			dsb.setfName(ds.getfName());
			dsb.setFdevName(ds.getFdevName());
			dsb.setDateTime(ds.getDateTime());
			dsb.setAlarmContent(ds.getAlarmContent());
			dsb.setDevStatu(devStatu);
			try
			{
				dsb.setOpStyleName(Constants.oprStyleMap.get(dsb.getOprStyle()));			
				dsb.setDoorStatusName(Constants.doorAndLockStatusMap.get(dsb.getDoorStatus()));
				dsb.setLockStatusName(Constants.doorAndLockStatusMap.get(dsb.getLockStatus()));
				if(dsb.getAlarmSign().equals("11111111"))
				{
					dsb.setIsAlarmName("否");
				}
				else
				{
					dsb.setIsAlarmName("是");
				}			
			 
				String alarmDesign=dsb.getAlarmSign();
				
				dsb.setTempAlarm(alarmDesign.substring(0,1));
				dsb.setHumiAlarm(alarmDesign.substring(1,2));
				dsb.setBattAlarm(alarmDesign.substring(2,3));
				dsb.setTiltAlarm(alarmDesign.substring(3,4));
				dsb.setDoorOpenErr(alarmDesign.substring(4,5));
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
			//System.out.println(dsb.getDevStatu()+dsb.getIsAlarmName()+dsb.getLockStatus());
			dsbList.add(dsb);
		}
		
		return dsbList;
	}
	/**
	 * 根据IMEI和MAC删除设备状态记录
	 */
	@Override
	public int deleteDevStatusByImeiAndMac(DeviceStatus ds) {
		return deviceStatusMapper.deleteDevStatusByImeiAndMac(ds);
	}

	/**
	 * 定时上报记录查询
	* 
	* @Title: DeviceStatusService.java 
	* @Description: TODO 
	* @param @param devStaCon
	* @param @param user
	* @param @param pb
	* @param @return
	* @return PageBean
	* @author fl
	* @date 2018年2月5日 上午10:22:10
	* @version V1.0
	 */
	@Override
	public PageBean devTimeNewStatusList(DeviceStatusConditionBean devStaCon, Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("devStaCon", devStaCon);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		
		pb.setRows(deviceStatusMapper.queryCountdevTimeNewStatusList(conditionMap));
		pb.setList(this.Convert(deviceStatusMapper.querydevTimeNewStatusList(conditionMap)));
		return pb;
	}

	/**
	 * 导出定时上报记录
	* 
	* @Title: DeviceStatusService.java 
	* @Description: TODO 
	* @param @param devStaCon
	* @param @param user
	* @param @param pb
	* @param @return
	* @return PageBean
	* @author fl
	* @date 2018年2月6日 上午9:56:27
	* @version V1.0
	 */
	@Override
	public PageBean devTimExportNewStatus(DeviceStatusConditionBean devStaCon, Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("devStaCon", devStaCon);
		conditionMap.put("user", user);
		conditionMap.put("pb", null);
		
		pb.setRows(deviceStatusMapper.queryCountdevTimeNewStatusList(conditionMap));
		pb.setList(this.Convert(deviceStatusMapper.querydevTimeNewStatusList(conditionMap)));		
				
		return pb;
	}

	/**
	 * 实时监控页面excle表格
	 * fl 根据设施ID 定时上报记录
	 * @param devId
	 * @return
	 */
	@Override
	public PageBean querydevTimeNewStatuByDevId(Long devId,PageBean pb) {
		pb.setRows(deviceStatusMapper.queryCountdevTimeNewStatuByDevId(devId,pb));
		pb.setList(this.Convert(deviceStatusMapper.querydevTimeNewStatuByDevId(devId,pb)));	
		return pb;
	}
}
