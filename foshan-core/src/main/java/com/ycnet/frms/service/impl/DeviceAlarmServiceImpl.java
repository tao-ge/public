package com.ycnet.frms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.Constants;
import com.ycnet.core.util.BeanUtils;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.DeviceImage;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.DeviceStatus;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.AreasMapper;
import com.ycnet.frms.mapper.DeviceAlarmMapper;
import com.ycnet.frms.mapper.DeviceImageMapper;
import com.ycnet.frms.mapper.DeviceRegMapper;
import com.ycnet.frms.mapper.DeviceStatusMapper;
import com.ycnet.frms.mapper.FacilityEntityMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.MobileSwitchMapper;
import com.ycnet.frms.mapper.OrganizitionMapper;
import com.ycnet.frms.mapper.UsersMapper;
import com.ycnet.frms.service.DeviceAlarmService;
import com.ycnet.frms.vo.AreasParent;
import com.ycnet.frms.vo.DeviceAlarmBean;
import com.ycnet.frms.vo.DeviceAlarmConditionBean;



@Service("deviceAlarmService")
public class DeviceAlarmServiceImpl implements DeviceAlarmService{

	@Resource(name="deviceAlarmMapper")
	private DeviceAlarmMapper deviceAlarmMapper;
	
	@Resource(name="deviceRegMapper")
	private DeviceRegMapper deviceRegMapper;
	
	@Resource(name="deviceImageMapper")
	private DeviceImageMapper deviceImageMapper;

	@Resource(name="areasMapper")
	private AreasMapper areasMapper;
	
	@Resource(name="deviceStatusMapper")
	private DeviceStatusMapper deviceStatusMapper;
	
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="mobileSwitchMapper")
	private MobileSwitchMapper mobileSwitchMapper;
	
	@Resource(name="organizitionMapper")
	private OrganizitionMapper organizitionMapper;
	
	@Override
	public PageBean queryByConditionBean(DeviceAlarmConditionBean bean,
			Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("deviceAlarmCon", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		
		pb.setRows(deviceAlarmMapper.queryCountByConditionMap(conditionMap));//;
		pb.setList(Convert(deviceAlarmMapper.queryByConditionMap(conditionMap)));		
				
		return pb;
	}
	
	/**
	 * 转换报警列表未页面所需bean列表
	 * @param deviceAlarmList
	 * @return
	 */
	private List<DeviceAlarmBean> Convert(List<DeviceAlarm> deviceAlarmList)
	{
		List<DeviceAlarmBean> dabList=new ArrayList<DeviceAlarmBean>();
		for(DeviceAlarm da:deviceAlarmList)
		{
			DeviceAlarmBean dab=new DeviceAlarmBean();
//			BeanUtils.copy(da, dab);
			dab.setAlarmContent(da.getAlarmContent());
			dab.setAlarmId(da.getAlarmId());
			dab.setAlarmProcessId(da.getAlarmProcessId());
			dab.setAlarmTime(da.getAlarmTime());
			dab.setAlarmTypes(da.getAlarmTypes());
			dab.setDealSign(da.getDealSign());
			dab.setDealSituation(da.getDealSituation());
			dab.setDevCode(da.getDevCode());
			dab.setDevId(da.getDevId());
			dab.setDevImei(da.getDevImei());
			dab.setDevMac(da.getDevMac());
			dab.setDevName(da.getDevName());
			dab.setRptTime(da.getRptTime());
			dab.setUserId(da.getUserId());
			dab.setUserName(da.getUserName());
			dab.setFdevName(da.getFdevName());//所属设施名称
			dab.setDateTime(da.getDateTime());//格式化后的日期
			
			//设置温湿度等参数
			DeviceStatus ds = deviceStatusMapper.selectByPrimaryKey(da.getAlarmId());
			dab.setOpStyleName(Constants.oprStyleMap.get(ds.getOprStyle()));			
			dab.setDoorStatusName(Constants.doorAndLockStatusMap.get(ds.getDoorStatus()));
			dab.setLockStatusName(Constants.doorAndLockStatusMap.get(ds.getLockStatus()));
			dab.setTemp(ds.getTemp());
			dab.setHumidity(ds.getHumidity());
			dab.setTilt(ds.getTilt());
			dab.setBattery(ds.getBattery());
			dab.setSignals(ds.getSignals());
			if(ds.getAlarmSign().equals("11111111"))
			{
				dab.setIsAlarmName("否");
			}
			else
			{
				dab.setIsAlarmName("是");
			}			
			String alarmDesign=ds.getAlarmSign();
			dab.setTempAlarm(alarmDesign.substring(0,1));
			dab.setHumiAlarm(alarmDesign.substring(1,2));
			dab.setBattAlarm(alarmDesign.substring(2,3));
			dab.setTiltAlarm(alarmDesign.substring(3,4));
			dab.setDoorOpenErr(alarmDesign.substring(4,5));
			
			
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("devImei", da.getDevImei());
			map.put("oprStyle", "2");
			map.put("alarmTime", da.getAlarmTime());
			DeviceImage image = deviceImageMapper.queryByImeiAndColtime(map);
			if(image!=null) {
				if(!"".equals(image.getImageUrl())) {
					dab.setImgUrl(image.getImageUrl());
				}
			}
			
			if (da.getAlarmContent().indexOf("强行进入")!=-1) {
				dab.setIsCheck("1");
			}
			if(Constants.DealSignMap.containsKey(dab.getDealSign()))
			{
				dab.setDealSignName(Constants.DealSignMap.get(dab.getDealSign()));
			}
			dabList.add(dab);
		}
		
		return dabList;
	}

	/*
	 * 保存报警处理记录
	 * @see com.ycnet.frms.service.DeviceAlarmService#deviceAlarmCtrlSave(com.ycnet.frms.bean.DeviceAlarm, com.ycnet.frms.bean.Users)
	 */
	/**
	 * 
	 * @Title: deviceAlarmCtrlSave
	 * @Description: 报警查询记录页面，报警处理操作 
	 * @param @param deviceAlarm
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY (添加注释,添加处理报警时修改设备状态操作)
	 * @throws
	 * @date 2018年2月12日 上午10:15:50
	 * @version V1.0
	 */
	@Override
	public int deviceAlarmCtrlSave(DeviceAlarm deviceAlarm, Users user) {
		//查询报警记录
		DeviceAlarm da = deviceAlarmMapper.selectByPrimaryKey(deviceAlarm.getAlarmProcessId());
		//查询设备
		DeviceReg dr = deviceRegMapper.queryByImei(da.getDevImei());
		dr.setDevStatus("0");
		//把设备状态修改为正常
		deviceRegMapper.updateByPrimaryKeySelective(dr);
		
		deviceAlarm.setDealSign("1");
		deviceAlarm.setUserId(user.getUserId());
		deviceAlarm.setUserName(user.getUserName());
		int i=this.deviceAlarmMapper.updateByPrimaryKeySelective(deviceAlarm);
		
		return i;
	}
	
	@Override
	public PageBean queryUntreatedCountByConditionMap(DeviceAlarmConditionBean bean,
			Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("deviceAlarmCon", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		
		pb.setRows(deviceAlarmMapper.queryUntreatedCountByConditionMap(conditionMap));//;
				
		return pb;
	}
	
	@Override
	public PageBean queryNewRecordByConditionMap(DeviceAlarmConditionBean bean,
			Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("deviceAlarmCon", bean);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		
		pb.setRows(deviceAlarmMapper.queryNewRecordCountByConditionMap(conditionMap));//;
		pb.setList(Convert(deviceAlarmMapper.queryNewRecordByConditionMap(conditionMap)));	
		return pb;
	}

	@Override
	public DeviceAlarm selectById(Long alarmProcessId) {
		// TODO Auto-generated method stub
		return deviceAlarmMapper.selectByPrimaryKey(alarmProcessId);
	}

	/**
	 * 根据imei和mac删除报警记录
	 */
	@Override
	public int deleteAlarmByImeiAndMac(DeviceAlarm da) {
		return deviceAlarmMapper.deleteAlarmByImeiAndMac(da);
	}

	/**
	 * 报警记录导出查询
	* 
	* @Title: DeviceAlarmService.java 
	* @Description: TODO 
	* @param @param deviceAlarmCon
	* @param @param user
	* @param @param pb
	* @param @return
	* @return PageBean
	* @author fl
	* @date 2018年2月6日 上午10:02:10
	* @version V1.0
	 */
	@Override
	public PageBean queryexport(DeviceAlarmConditionBean deviceAlarmCon, Users user, PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("deviceAlarmCon", deviceAlarmCon);
		conditionMap.put("user", user);
		conditionMap.put("pb", null);
		
		pb.setRows(deviceAlarmMapper.queryCountByConditionMap(conditionMap));//;
		pb.setList(Convert(deviceAlarmMapper.queryByConditionMap(conditionMap)));	
		return pb;
	}

	/**
	 * fl 根据设施ID 查询报警记录
	 * @param request
	 * @param devId
	 * @return
	 */
	@Override
	public PageBean queryDeviceAlarmByDevId(Long devId, String areaCode1,PageBean pb, Users user) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		if(areaCode1!=null && !"".equals(areaCode1) && areaCode1.indexOf(",")!=-1){
			ArrayList<String> list = returnListByAreaCode1(areaCode1);//处理areaCode1返回list
			map.put("areaCode1", list);
		}
		map.put("pb", pb);
		map.put("devId", devId);
		map.put("user", user);
		pb.setRows(deviceAlarmMapper.queryCountDeviceAlarmByDevId(map));
		pb.setList(Convert(deviceAlarmMapper.queryDeviceAlarmByDevId(map)));
		return pb;
	}

	private ArrayList<String> returnListByAreaCode1(String areaCode1) {
		String[] areaCodes = areaCode1.split(",");
		  ArrayList<String> list2 = new ArrayList<String>();
		  for (int i = 0; i < areaCodes.length; i++) {
			  List<AreasParent> list = areasMapper.selectByParentChild(areaCodes[i]);//子类汇聚区
				AreasParent areasParent=areasMapper.selectByCode(areaCodes[i]);//本汇聚区
				if (list.size()>0 && list !=null) {//本类，子类汇聚区一起查询
					list.add(areasParent);
					int a=0;
					for (int j = 0; j < list.size(); j++) {
						if (list.get(j).getParentAreaCode().equals(areasParent.getParentAreaCode())) {//排除重复的情况
							a++;
						}
						if (a==2) {
							list.remove(list.size()-1);
						}
					}
					for (int j = 0; j < list.size(); j++) {//添加
						list2.add(list.get(j).getParentAreaCode());
					}
				}else {//没有子类。只查询本类汇聚区，添加
					list2.add(areaCodes[i]);
				}
		  }
		return list2;
	}

	/**
	 * 
	 * @Title: queryNotCompletedCount
	 * @Description: 查询未处理报警的数量
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月12日 下午5:11:22
	 * @version V1.0
	 */
	@Override
	public int queryNotCompletedCount(Users user) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user", user);
		return deviceAlarmMapper.queryUntreatedCountByConditionMap(map);
	}

	/**
	 * 保存到报警记录中
	* @Title: insertByImei 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param imei
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月28日 下午5:41:28 
	* @version V1.0
	 */
	@Override
	public int insertByImei(String imei) {
		DeviceReg deviceReg =deviceRegMapper.selectByImei(imei);
		DeviceAlarm deviceAlarm =new DeviceAlarm();
		int num=0;
		if (deviceReg!=null) {
			deviceAlarm.setDevCode(deviceReg.getDevCode());
			deviceAlarm.setDevName(deviceReg.getDevName());
			deviceAlarm.setDevImei(imei);
			deviceAlarm.setDevMac(deviceReg.getDevMac());
			deviceAlarm.setDealSign("0");
			deviceAlarm.setUserId(-9999l);
			deviceAlarm.setUserName("");
			deviceAlarm.setDevId(deviceReg.getDevId());
			deviceAlarm.setRptTime(new Date());
			deviceAlarm.setAlarmContent("备用钥匙开锁");
			deviceAlarm.setAlarmTime(new Date());
			deviceAlarm.setAlarmTypes("18");
			 num=deviceAlarmMapper.insertSelective(deviceAlarm);
		}
		return num;
	}

	/**
	 * 插入远程开锁记录
	* @Title: insertMobileSwithLock 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId
	* @param @param time
	* @param @param imei
	* @param @return    入参
	* @return int    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年7月29日 下午8:35:03 
	* @version V1.0
	 */
	@Override
	public int insertMobileSwithLock(String userId, String time, String imei) {
		// TODO Auto-generated method stub
		DeviceReg deviceReg =deviceRegMapper.selectByImei(imei);
		Users u = usersMapper.selectByPrimaryKey(Long.parseLong(userId));
		Facility f = facilityMapper.selectByPrimaryKey(deviceReg.getDevId());
		MobileSwitch ms = new MobileSwitch();
		ms.setDevId(deviceReg.getDevId());
		ms.setDevCode(f.getDevCode());
		ms.setDevName(f.getDevName());
		try {
			ms.setColTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ms.setDevImei(imei);
		ms.setDevMac(deviceReg.getDevMac());
		ms.setOrgId(u.getOrgId());
		ms.setUserId(u.getUserId());
		ms.setUserName(u.getUserName());
		ms.setMobilePhone(u.getMobilePhone());
		ms.setRptTime(new Date());
		ms.setSwitchType("1");
		ms.setOprTypes("1");
		ms.setResultStatus("1");
		return mobileSwitchMapper.insertSelective(ms);
	}

	/**
	 * 
	 * @Title: insertDeviceAlarm
	 * @Description: 处理干城上传报警信息
	 * @param @param ds
	 * @param @param alarmTypes
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月30日 下午3:23:29
	 * @version V1.0
	 */
	@Override
	public int updownDeviceAlarm(DeviceStatus ds, String alarmTypes, String time) {
		int req = 0;
		if(ds != null) {
			if(ds.getDevImei() != null && !"".equals(ds.getDevImei().trim())) {
				DeviceReg dr = deviceRegMapper.selectByImei(ds.getDevImei());
				Organizition org = organizitionMapper.selectByPrimaryKey(dr.getOrgId());
				ds.setDevId(dr.getDevId());
			    ds.setDevCode(dr.getDevCode());
			    ds.setDevName(dr.getDevName());
			    try {
			    	ds.setColTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
			    } catch (ParseException e) {
			    	e.printStackTrace();
			    }
				ds.setRptTime(new Date());
				ds.setOprStyle("5");//报警
				ds.setLowTempThd(org.getLowTempThd());
			    ds.setHighTempShd(org.getHighTempShd());
			    ds.setHumidityShd(org.getHumidityShd());
			    ds.setBatteryThd(org.getBatteryThd());
			    ds.setTiltThd(org.getTilt());
			    ds.setDevImei(dr.getDevImei());
			    ds.setDevMac(dr.getDevMac());
			    ds.setAlarmSign("11111111");
			    ds.setDealSign("0");
			    ds.setCurStatus("1");
			    ds.setOrgId(org.getOrgId());
			    
			    String alarmContent="";
			    if(alarmTypes != null && !"".equals(alarmTypes.trim())) {
			    	if(alarmTypes.length() == 1) {
			    		alarmTypes = "0"+alarmTypes;
	        		}
	        		if("01".equals(alarmTypes)) {
	        			alarmContent+="水浸报警;";
	        		}
	        		if("02".equals(alarmTypes)){
	        			alarmContent+="倾斜或震动报警;";
	        		}
	        		if("03".equals(alarmTypes)){
	        			alarmContent+="非法撬门报警;";
	        		}
	        		if("04".equals(alarmTypes)){
	        			alarmContent+="温度报警;";
	        		}
	        		if("05".equals(alarmTypes)){
	        			alarmContent+="门超时报警;";
	        		}
	        		if("06".equals(alarmTypes)){
	        			alarmContent+="电量报警;";
	        		}
	        		if("07".equals(alarmTypes)){
	        			alarmContent+="锁超时报警;";
	        		}
	        		if("08".equals(alarmTypes)){
	        			alarmContent+="非法撬锁报警;";
	        		}
	        		if("13".equals(alarmTypes)){
	        			alarmContent+="布防APP开锁;";
	        		}
	        		if("14".equals(alarmTypes)){
	        			alarmContent+="布防平台远程开锁;";
	        		}
	        		if("15".equals(alarmTypes)){
	        			alarmContent+="布防钥匙开锁;";
	        		}
	        		if("16".equals(alarmTypes)){
	        			alarmContent+="布防蓝牙开锁;";
	        		}
	        		if("17".equals(alarmTypes)){
	        			alarmContent+="通讯中断报警;";
	        		}
	        		if("18".equals(alarmTypes)){
	        			alarmContent+="备用钥匙报警;";
	        		}
			    }
			    ds.setAlarmContent(alarmContent);
			    //添加状态数据
			    int key = deviceStatusMapper.insertSelective(ds);
			    
			    DeviceAlarm da=new DeviceAlarm();
	        	da.setAlarmId((long)key);
	        	da.setDevId(dr.getDevId());
	        	da.setDevCode(dr.getDevCode());
	        	da.setDevName(dr.getDevName());
	        	da.setDevImei(dr.getDevImei());
	        	da.setDevMac(dr.getDevMac());
	        	da.setAlarmTime(ds.getColTime());
	        	da.setRptTime(new Date());
	        	da.setAlarmTypes(alarmTypes);//设置报警类型
	        	da.setAlarmContent(alarmContent);
	        	da.setDealSign("0");
	        	da.setDealSituation("");
	        	da.setUserId(0L);
	        	da.setUserName("");
	        	//添加报警记录
	        	req = deviceAlarmMapper.insertSelective(da);
	        	
	        	//修改当前设备状态
	        	dr.setDevStatus("2");
	        	deviceRegMapper.updateByPrimaryKeySelective(dr);
			}
		}
		return req;
	}

}
