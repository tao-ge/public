package com.ycnet.frms.service.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.ycnet.core.APIHttpClient;
import com.ycnet.core.FrmsException;
import com.ycnet.core.HttpClientUtil;
import com.ycnet.core.HttpUtil;
import com.ycnet.core.HttpsUtil;
import com.ycnet.core.util.BeanUtils;
import com.ycnet.core.util.HexUtil;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceAlarm;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.DeviceStatus;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.AreasMapper;
import com.ycnet.frms.mapper.DeviceAlarmMapper;
import com.ycnet.frms.mapper.DeviceRegMapper;
import com.ycnet.frms.mapper.DeviceStatusMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.MobileSwitchMapper;
import com.ycnet.frms.mapper.UsersMapper;
import com.ycnet.frms.service.DeviceRegService;
import com.ycnet.frms.service.FacilityAccessService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.util.hzgcRequsetInfo;
import com.ycnet.frms.vo.AccessConditionBean;
import com.ycnet.frms.vo.AreasParent;
import com.ycnet.frms.vo.DeviceInfoAndAccess;
import com.ycnet.frms.vo.DeviceOfPlatform;
import com.ycnet.frms.vo.Devices;
import com.ycnet.frms.vo.PreventBean;
import com.ycnet.frms.vo.ResultOfPlatFormAddDevies;
import com.ycnet.frms.vo.mobile.DeviceRegVo;

import net.sf.json.JSONObject;

@Service("deviceRegService")
public class DeviceRegServiceImpl implements DeviceRegService{
	@Resource(name="deviceRegMapper")
	DeviceRegMapper deviceRegMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="facilityAccessService")
	private FacilityAccessService facilityAccessService;

	@Resource(name="organizitionService")
	private OrganizitionService organizitionService;
	
	@Resource(name="deviceRegService")
	DeviceRegService deviceRegService;

	@Resource(name="areasMapper")
	private AreasMapper areasMapper;
	
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	
	@Resource(name="deviceAlarmMapper")
	private DeviceAlarmMapper deviceAlarmMapper;

	@Resource(name="deviceStatusMapper")
	private DeviceStatusMapper deviceStatusMapper;

	@Resource(name="mobileSwitchMapper")
	private MobileSwitchMapper mobileSwitchMapper;
	
	@Override
	public DeviceReg selectById(Long regId) {
		// TODO Auto-generated method stub
		return deviceRegMapper.selectByPrimaryKey(regId);
	}
	
	@Override
	public int save(DeviceReg dr) {
		int ret=0;
		if(dr.getDevId()!=null&&dr.getDevId()!=0)//获取设施信息  本功能取消，暂时放在这里
		{
			
			
		}
		if (dr.getRegId()==null){
			ret = deviceRegMapper.insertSelective(dr);
		}else			
			ret = deviceRegMapper.updateByPrimaryKeySelective(dr);
		return ret;
	}

	@Override
	public int delete(Long regId) {
		// TODO Auto-generated method stub
		return deviceRegMapper.deleteByPrimaryKey(regId);
	}
	
	/**
	 * 智能锁管理列表
	 */
	@Override
	public List<DeviceReg> queryList(DeviceReg dr, PageBean pb,Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dr", dr);
		map.put("pb", pb);
		map.put("user", user);
		return deviceRegMapper.queryListByMap(map);
	}
	
	//查询
	@Override
	public List queryAll() {
		return deviceRegMapper.queryAll();
	}
	
	
	/**
	 * 注册设备  通过mac和imei查询是否存在设备，如果存在则只更新设施字段；否则添加新的记录，注册状态为未注册。
	 */
	@Override
	@Transactional
	public int regDevice(DeviceReg dr,Users users) {
		int ret=0;
		int add=0;
		DeviceReg deviceReg=deviceRegMapper.selectByCondition(dr);
		if(deviceReg==null) {
			if(dr.getDevId()!=0)
			{
				Facility facility=facilityMapper.selectByPrimaryKey(dr.getDevId());
				if(facility!=null)
				{
					if(dr.getDevName()==null || "".equals(dr.getDevName())) {
						dr.setDevName(facility.getDevName());//如果设备名为空，默认为设施名称
					}else {
						dr.setDevName(dr.getDevName());//设备名称
					}
					dr.setDevCode(facility.getDevCode());
				}
			}
			
			dr.setOrgId(users.getOrgId());
			dr.setDevPlatform(users.getDevPlatform());
			dr.setRptTime(new Date());
			dr.setValidateSign("0");//初次默认未注册
			
			ret = deviceRegMapper.insertSelective(dr);
			add = regDeviceToForwardServer(dr,users);//注册到转发服务器
			if(ret>0 && add>0) {//如果注册到平台和注册平台都成功，则返回成功
				ret=1;
			}
		}
		
		return ret;
	}

	/**
	 * 
	 * @Title: regDeviceToForwardServer
	 * @Description: 注册到转发平台
	 * @param @param dr
	 * @param @param users
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月25日 下午8:09:40
	 * @version V1.0
	 */
	private int regDeviceToForwardServer(DeviceReg dr, Users users) {
		int add = 0;
			
		   try {
			   List<DeviceReg> drs=deviceRegService.queryNotRegDevice();
			   List<DeviceOfPlatform> dops=new ArrayList<DeviceOfPlatform>();
			   Organizition org = organizitionService.selectById(users.getOrgId());
			   
			   if(drs!=null&&drs.size()>0)
			   {
				   for(DeviceReg dr1:drs)
				   {
					   DeviceOfPlatform dop=new DeviceOfPlatform();
					   dop.setProductId(org.getPid());   //128:3EGJZ4	40:T3QWZI
					   dop.setSn(dr1.getDevImei());
					   dop.setDidkey("00abcdefghijklmn1234567891234567");
					   dops.add(dop);
					   System.out.print("");
				   }
				   
				   Devices ds=new Devices();
				   ds.setDevices(dops);
				   
				   ObjectMapper mapper = new ObjectMapper();
				   mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				   String dsStr;				
				   dsStr = mapper.writeValueAsString(ds);
				   System.out.println(dsStr);
				   //System.out.println(http.getToken());
				   String r=deviceRegService.addDevice(dsStr);  //发送给云平台    
				   
				   System.out.println(r);
				   //JsonParser
				   ResultOfPlatFormAddDevies rJson=mapper.readValue(r,ResultOfPlatFormAddDevies.class);
				   
				   if(rJson.getResult().equals("1200"))
				   {
					   for(ResultOfPlatFormAddDevies.DeviceResult d:rJson.getDevices())
					   {
						   DeviceReg deviceReg=new DeviceReg();
						   deviceReg.setDevImei(d.getSn());
						   deviceReg.setRegTime(new Date());
						   deviceReg.setRptTime(new Date());
						   deviceReg.setValidateSign("1");
						   deviceRegMapper.updateDeviceReg(deviceReg);
					   }
					   add = 1;
				   }				   
				   System.out.println(rJson.getResult());
			 
			   }
			     
		   } catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return add;
	}

	@Override
	public int queryCount(DeviceReg dr,Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dr", dr);
		map.put("user", user);
		return deviceRegMapper.queryCountByMap(map);
	}

	@Override
	public DeviceInfoAndAccess selectByMac(String devMac, Users users) {
	
		
		DeviceReg drInput=new DeviceReg();
		drInput.setDevMac(devMac);
		
		DeviceReg drOutPut=deviceRegMapper.selectByCondition(drInput);
		
		if(drOutPut!=null)
		{
			DeviceInfoAndAccess deviceInfoAndAccess=new DeviceInfoAndAccess();
			
//			BeanUtils.copy(drOutPut, deviceInfoAndAccess);
			deviceInfoAndAccess.setRegId(drOutPut.getRegId());;
			deviceInfoAndAccess.setDevId(drOutPut.getDevId());
			deviceInfoAndAccess.setDevCode(drOutPut.getDevCode());
			deviceInfoAndAccess.setDevName(drOutPut.getDevName());
			deviceInfoAndAccess.setDevImei(drOutPut.getDevImei());
			deviceInfoAndAccess.setDevMac(drOutPut.getDevMac());
			deviceInfoAndAccess.setRegTime(drOutPut.getRegTime());
			deviceInfoAndAccess.setRptTime(drOutPut.getRptTime());
			deviceInfoAndAccess.setValidateSign(drOutPut.getValidateSign());
			deviceInfoAndAccess.setOrgId(drOutPut.getOrgId());
			deviceInfoAndAccess.setHwDeviceId(drOutPut.getHwDeviceId());
			deviceInfoAndAccess.setfName(drOutPut.getfName());
			deviceInfoAndAccess.setDevPlatform(drOutPut.getDevPlatform());
			
			AccessConditionBean accessConditionBean=new AccessConditionBean();
			accessConditionBean.setDevId(deviceInfoAndAccess.getDevId());
			accessConditionBean.setUserId(users.getUserId());
			accessConditionBean.setValidateStatus("1");//2017-10-13整合AccessConditionBean和FacilityAccess,修改智能锁授权管理连带修改
			String dt=(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()).toString();
			accessConditionBean.setBdate(dt);
			
			//判断是否有权限，处理imei字段
			if(facilityAccessService.queryCountByCondition(accessConditionBean,users)>0)
			{
				deviceInfoAndAccess.setIsKey("是");
				
			}
			else
			{
				deviceInfoAndAccess.setIsKey("否");
				deviceInfoAndAccess.setDevImei("");
			}	
			
			return deviceInfoAndAccess;
			
		}
		else
		{
			return null;
		}
		
		
		
	}

	//高级查询
	@Override
	public List<FacilityAccess> queryListByCondition(DeviceReg dr, PageBean pb, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dr", dr);
		map.put("pb", pb);
		map.put("user", user);
		return deviceRegMapper.queryListByCondition(map);
	}

	@Override
	public Integer queryCountByCondition(DeviceReg dr, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dr", dr);
		map.put("user", user);
		return deviceRegMapper.queryCountByCondition(map);
	}

	@Override
	public DeviceReg checkDeviceReg(String devMac, Long orgId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devMac", devMac);
		map.put("orgId", orgId);
		return deviceRegMapper.checkDeviceReg(map);
	}

	@Override
	public List<DeviceReg> queryLockBydevCode(String devCode, long orgId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devCode", devCode);
		map.put("orgId", orgId);
		return deviceRegMapper.queryLockBydevCode(map);
	}

	@Override
	public List<DeviceReg> queryFacilityLock(Long devId) {
		return deviceRegMapper.queryFacilityLock(devId);
	}

	/**
	 * 设备注册到华为平台
	 */
	@Override
	public int hwregDevice(DeviceReg dr, Users user, String platform) {
		int num = 0;
		System.out.println("~~~~~~~HW~~~~~~~");
//		//与崔航对接华为平台,http请求
//		String post_url="http://116.62.37.111:9999/device";
//		APIHttpClient ac = new APIHttpClient(post_url);
//		JsonObject obj = new JsonObject();
//		obj.addProperty("imei", dr.getDevImei());
//		obj.addProperty("name", dr.getDevName());
//		obj.addProperty("platform", dr.getDevPlatform());
//		obj.addProperty("platform_from", "01");//干城方面有可能以后会对接其他平台，此参数用来区分移创平台与其他平台
//		String result = ac.post(obj.toString());
//		System.out.println("~~~~~~~"+result);
		
		//与崔航对接华为平台,https请求
		String result = "";
		try {
			String url="https://116.62.37.111:8888/device";
			HttpsUtil https = new HttpsUtil();
			https.initSSLConfigForTwoWay();
			JsonObject obj = new JsonObject();
			obj.addProperty("imei", dr.getDevImei());
			obj.addProperty("name", dr.getDevName());
			obj.addProperty("platform", dr.getDevPlatform());
			obj.addProperty("platform_from", "01");//干城方面有可能以后会对接其他平台，此参数用来区分移创平台与其他平台
			result = https.doPostJsonForString(url, obj.toString());
			System.out.println("~~~~~~~"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObj = JSONObject.fromObject(result);
		if(!jsonObj.containsKey("deviceId")) {
			num = 0;
		}else {
			String hwDeviceId = jsonObj.getString("deviceId");
			System.out.println("@@@@@@@@@@@"+hwDeviceId);
			
			DeviceReg device = new DeviceReg();
			device.setDevId(dr.getDevId());
			device.setDevName(dr.getDevName());
			device.setDevImei(dr.getDevImei());
			device.setDevMac(dr.getDevMac());
			//添加hwDeviceId
			DeviceReg deviceReg=deviceRegMapper.selectByCondition(device);
			deviceReg.setHwDeviceId(hwDeviceId);
			num = deviceRegMapper.updateByPrimaryKeySelective(deviceReg);
		}
		return num;
	}

	/**
	 * 
	 * @Title: addDevice
	 * @Description: 注册到转发服务器
	 * @param @param dr
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月25日 下午8:05:42
	 * @version V1.0
	 */
	@Override
	public String addDevice(String dsStr) {
		int num = 0;
		System.out.println("~~~~~~~Device~~~~~~~");
//		String post_url="http://localhost:8080/m2mGW-site/mobile/device/add.json";//本地测试
		String post_url="http://localhost/m2mGW/mobile/device/add.json";//128平台
		APIHttpClient ac = new APIHttpClient(post_url);
		
//		Organizition org = organizitionService.selectById(user.getOrgId());
//		DeviceManagerData dmd = new DeviceManagerData();
//		DeviceInfo info = new DeviceInfo();
//		info.setProductId(org.getPid());
//		info.setSn(dr.getDevImei());
//		DeviceInfo[] infos = new DeviceInfo[] {info};
//		dmd.setDevices(infos);
//		JSONObject json = JSONObject.fromObject(dmd);//封装为json
//		System.out.println(json.toString());
//		String result = ac.post(json.toString());
		String result = ac.post(dsStr);
		System.out.println("######result="+result);
//		JSONObject jsonResult = JSONObject.fromObject(result);
//		String resultr = jsonResult.getString("result");
//		System.out.println(resultr);
//		if("1200".equals(resultr)) {
//			num = 1;
//		}
		return result;
	}

	
	public static void main(String[] args) {
		HttpsUtil https = new HttpsUtil();
		String result = "";
		String url ="https://116.62.37.111:8888/api/devicecommand";
		try {
			https.initSSLConfigForTwoWay();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value="";
		String imei="123465798";
		String pal="04";
		String tid="0";
		String asic = HexUtil.encode(imei)+"10011002";
		String kzxx = tid=="0"?"04":"03";
		SimpleDateFormat spf = new SimpleDateFormat("yyMMddHHmmss");
		String time = spf.format(new Date());
		PreventBean preventBean = new PreventBean();
		preventBean.setImei(imei);
		preventBean.setPlatform(pal);
		preventBean.setPlatform_from("01");
		preventBean.setExpired_time("90000");
		preventBean.setValue(asic+kzxx+time);
		
		JSONObject json = JSONObject.fromObject(preventBean);//封装为json
		result=https.doPostJsonForString(url, json.toString());
		
	}

	/**
	 * 远程删除转发服务器设备
	 */
	@Override
	public int remoteDeleteDevice(String devImei, Users user) {
		BufferedReader in = null;
		BufferedOutputStream out = null;
		Socket socket = null;
		boolean bConnected = false;
		Organizition org = organizitionService.selectByPrimaryKey(user.getOrgId());
		try {
			socket = new Socket(org.getIp(),org.getPort().intValue());
			socket.setSoTimeout(1000*20);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			out = new BufferedOutputStream(socket.getOutputStream());
			//out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
			System.out.println("~~~~~~~~连接成功~~~~~~~~!");
			String outDate="";
			outDate = "{\"header\":{\"version\":\"0.4\",\"msgType\":\"remoteDeleteDevice\",\"msgId\":\"1\",\"did\":\""+devImei+"\"}}";
			out.write(outDate.getBytes());
			bConnected = true;
			
			out.write("\r\n".getBytes());
			out.flush();
			while(bConnected){
				String inStr = in.readLine();
				if(inStr != null ){
					if("ok".equals(inStr)){
						return 1;
					}else{
						return 0;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					socket.close();
					out.close();
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return 0;
	}

	/**
	 * 远程删除华为平台设备
	 */
	@Override
	public int remoteHWDeleteDevice(String hwDeviceId, String devPlatform) {
		int req = 0;
		//与崔航对接华为平台,https请求
		String uri = "";
//		if("0".equals(devPlatform)) {
			uri = "https://116.62.37.111:8888/device/"+hwDeviceId+"?platform="+devPlatform+"&platform_from=01";
//			uri = "http://116.62.37.111:9999/device/"+hwDeviceId+"?platform="+devPlatform+"&platform_from=01";
//		}else if("1".equals(devPlatform)) {
//			uri = "https://116.62.37.111:8888/devicetele/"+hwDeviceId;//电信
//		}
		String code = "";
		try {
			HttpsUtil https = new HttpsUtil();
			https.initSSLConfigForTwoWay();
			String result = https.doDeleteForString(uri);
			JSONObject json = JSONObject.fromObject(result);
			if(json.containsKey("response_code")) {
				code = json.getString("response_code");
				if("1200".equals(code)) {
					req=1;
					return req;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		//与崔航对接华为平台,http请求
//		String uri = "";
//		if("0".equals(devPlatform)) {
//			uri = "http://116.62.37.111:8888/device/"+hwDeviceId;//华为
//		}else if("1".equals(devPlatform)) {
//			uri = "http://116.62.37.111:8888/devicetele/"+hwDeviceId;//电信
//		}
//		HttpClientUtil hcu = new HttpClientUtil();
//		int result = hcu.doDelete(uri);
//		System.out.println("~~~~~~"+result);
//		if(result==204) {//204删除成功,400删除失败
//			req = 1;
//		}
		return req;
	}

	/**
	 * 
	 * @Title: queryNotRegDevice
	 * @Description: 查询未注册的设备
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月25日 下午8:10:30
	 * @version V1.0
	 */
	@Override
	public List<DeviceReg> queryNotRegDevice() {
		return deviceRegMapper.queryNotRegDevice();
	}

	/**
	 * 
	 * @Title: queryByImei
	 * @Description: 根据imei查询设备信息
	 * @param @param did
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月28日 上午9:21:21
	 * @version V1.0
	 */
	@Override
	public DeviceReg queryByImei(String did) {
		return deviceRegMapper.queryByImei(did);
	}

	/**
	 * 
	 * @Title: queryDevByMac
	 * @Description: 根据mac查询数据
	 * @param @param devMac
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月22日 下午4:45:14
	 * @version V1.0
	 */
	@Override
	public DeviceReg queryDevByMac(String devMac) {
		return deviceRegMapper.queryDevByMac(devMac);
	}

	/**
	 * 根据汇聚去查询设备表
	* @Title: selectMobileSwitchAreaCode1 
	* @Description: 根据汇聚去查询设备表
	* @param @param session
	* @param @param areaCode1
	* @param @return    
	* @return List<Facility>
	* @author fl 
	* @throws
	* @date 2018年2月8日 下午3:48:06 
	* @version V1.0
	 */
	@Override
	public HashMap<String, Object> selectDeviceRegAreaCode1(Long orgId, String areaCode1,String devName) {
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
		  List<DeviceReg> list1= deviceRegMapper.selectDeviceRegAreaCode1(orgId,list2,devName);
		  	String bj = "0";//报警 1 是
			String gz = "0";//工作 1 是
			if (list1 !=null && list1.size()>0) {
				for (int i = 0; i < list1.size(); i++) {
					if (list1.get(i).getDevStatus().equals("2")) {
						 bj = "1";
					}
					if (list1.get(i).getDevStatus().equals("1")) {
						 gz = "1";
					}
				}
			}
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("list", list1);
			map.put("isStatus", bj);
			map.put("isWorkStatus", gz);
		return map;
	}

	/**
	 * 
	 * @Title: queryByDevId
	 * @Description: 根据devId查询设备
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月7日 下午2:12:37
	 * @version V1.0
	 */
	@Override
	public List<DeviceReg> queryByDevId(Long devId, Long orgId) {
		return deviceRegMapper.queryByDevId(devId,orgId);
	}

	/**
	 * 
	 * @Title: queryAccessByMac
	 * @Description: 根据mac查询设施是否授权
	 * @param @param devMac
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月2日 上午10:24:45
	 * @version V1.0
	 */
	@Override
	public int queryAccessByMac(String devMac, Users user) {
		DeviceReg dev = deviceRegMapper.queryDevByMac(devMac);
		AccessConditionBean accessConditionBean=new AccessConditionBean();
		accessConditionBean.setDevId(dev.getDevId());
		accessConditionBean.setUserId(user.getUserId());
		accessConditionBean.setValidateStatus("1");//2017-10-13整合AccessConditionBean和FacilityAccess,修改智能锁授权管理连带修改
		String dt=(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()).toString();
		accessConditionBean.setBdate(dt);
		int req = facilityAccessService.queryAccessCountByMac(accessConditionBean,user);
		return req;
	}

	/**
	 * 修改布防状态
	* @Title: updateRedeploying 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param tid
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月24日 上午11:31:11 
	* @version V1.0
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public int updateRedeploying(String tid,Long regId,String pal, String imei, Users user) throws Exception {
		String controlInfo = "0".equals(tid)?"04":"03";
		//pal = "04";
		int num=0;
		int mum =httpsConection(controlInfo,imei,pal,user.getUserId(),90000l);
		/*修改表数据*/
		if (mum>0) {
			DeviceReg record = new DeviceReg();
			record.setRedeployingType(tid);
			record.setRegId(regId);
			num = deviceRegMapper.updateByPrimaryKeySelective(record);
		}else{
			new FrmsException("通讯失败!");
		}
		return num;
	}


	/**
	 * https通信
	* @Title: httpConection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param controlInfo
	* @param @param url2
	* @param @param imei    入参
	* @return void    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月27日 下午1:50:27 
	* @version V1.0
	 * @param pal 
	 * @throws Exception 
	 */
	public int httpsConection(String controlInfo, String imei, String pal, Long userId,Long expired_time) throws Exception {
		/*通信*/
		String url ="https://116.62.37.111:8888/api/devicecommand";
		HttpsUtil https = new HttpsUtil();
		String result = "";
		https.initSSLConfigForTwoWay();
		int mum=0;
		int req = 100;
		hzgcRequsetInfo hzgc = new hzgcRequsetInfo();
		hzgc.setImei(imei);
		hzgc.setUserId(userId.toString());
		hzgc.setExpired_time(expired_time);//app远程开锁90   其他配置信息90000
		hzgc.setValue(hzgc.setContrlValue(imei, controlInfo, req));
		
		JSONObject json = JSONObject.fromObject(hzgc);//封装为json
		System.out.println(json.toString());
		result=https.doPostJsonForString(url, json.toString());
		System.out.println(result.toString());
		JSONObject jsonObj = JSONObject.fromObject(result.toString());
		if(jsonObj.getString("status").equals("1")) {
			mum = 1;
		}
		return mum;
	}

	/**
	 * 查询锁的详细信息
	* @Title: queryLockInfoByRegId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param regId
	* @param @return    入参
	* @return DeviceReg    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月24日 下午2:04:52 
	* @version V1.0
	 */
	@Override
	public DeviceRegVo queryLockInfoByRegId(Long regId,Long orgId) {
		DeviceRegVo deviceRegVo=deviceRegMapper.queryLockInfoByRegId(regId, orgId);
		if (deviceRegVo!=null) {
			DeviceAlarm deviceAlarm=deviceAlarmMapper.queryByDevId(deviceRegVo.getDevId());
			if (deviceAlarm!=null && deviceAlarm.getAlarmContent()!=null) {
				deviceRegVo.setAlarmContent(deviceAlarm.getAlarmContent());
			}
		}
		return deviceRegVo;
	}

	/**
	 * 
	 * @Title: queryByCondition
	 * @Description: 根据条件查询
	 * @param @param pb
	 * @param @param dr
	 * @param @param user
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月25日 下午3:13:38
	 * @version V1.0
	 */
	@Override
	public PageBean queryByCondition(PageBean pb, DeviceReg dr, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("dr", dr);
		map.put("user", user);
		pb.setRows(deviceRegMapper.queryCountByConditionMap(map));
		pb.setList(deviceRegMapper.queryByConditionMap(map));
		return pb;
	}

	/**
	 * 接口查询在线的有权限开的智能锁
	* @Title: queryByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pb
	* @param @param dr
	* @param @param user
	* @param @return    入参
	* @return List<DeviceReg>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月25日 下午5:43:50 
	* @version V1.0
	 */
	@Override
	public List<DeviceReg> queryByConditions(PageBean pb, DeviceReg dr, Users user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("dr", dr);
		map.put("user", user);
		return deviceRegMapper.queryByConditionMap(map);
	}

	
	/**
	 * 查询imei
	* @Title: selectByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param string
	* @param @return    入参
	* @return List<DeviceReg>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月27日 下午4:46:30 
	* @version V1.0
	 */
	@Override
	public List<DeviceReg> selectByConditions(Long orgId, String palteFrom) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("palteFrom", palteFrom);
		return  deviceRegMapper.selectByConditions(map);
	}

	/**
	 * 干城上传开关锁通知
	* @Title: insertAndManage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param deviceStatus
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月30日 下午1:48:42 
	* @version V1.0
	 */
	@Override
	public int insertAndManage(DeviceStatus deviceStatus,Long userId,String time) {
		//1保存 deviceStatus 根据imei,改成最新的一条
		int num=0;
		DeviceReg deviceReg = deviceRegMapper.queryByImei(deviceStatus.getDevImei());	
		if (deviceReg!=null) {
			MobileSwitch mobileSwitch =new MobileSwitch();
			deviceStatus.setDevId(deviceReg.getDevId());
			deviceStatus.setDevCode(deviceReg.getDevCode());
			deviceStatus.setDevName(deviceReg.getDevName());
			try {
				deviceStatus.setColTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			deviceStatus.setRptTime(new Date());
			Organizition organizition = organizitionService.selectByPrimaryKey(deviceReg.getOrgId());
			deviceStatus.setLowTempThd(organizition.getLowTempThd());
			deviceStatus.setHighTempShd(organizition.getHighTempShd());
			deviceStatus.setHumidityShd(organizition.getHumidityShd());
			deviceStatus.setBatteryThd(organizition.getBatteryThd());
			deviceStatus.setTiltThd(organizition.getTilt());
			deviceStatus.setDevMac(deviceReg.getDevMac());
			deviceStatus.setAlarmSign("11111111");
			deviceStatus.setDealSign("2");
			deviceStatus.setCurStatus("1");
			deviceStatus.setOrgId(deviceStatus.getOrgId());
			if (userId!=null) {
				Users user = usersMapper.selectByPrimaryKey(userId);
				if (user!=null) {
					deviceStatus.setOrgId(user.getOrgId());
					mobileSwitch.setUserName(user.getUserName());
					mobileSwitch.setUserId(user.getUserId());
					mobileSwitch.setMobilePhone(user.getMobilePhone());
					mobileSwitch.setMobileImei(user.getMobileImei());
					mobileSwitch.setOrgId(user.getOrgId());
				}
			}
			//根据imei,改成最新的一条
			num = deviceStatusMapper.updateByImei(deviceStatus.getDevImei());
			num += deviceStatusMapper.insertSelective(deviceStatus);
			
			//2 根据oprStyle 1 14 保存开关锁记录//tn_mobile-switch//7 保存开锁记录 修改状态
			mobileSwitch.setDevId(deviceReg.getDevId());
			mobileSwitch.setDevCode(deviceReg.getDevCode());
			mobileSwitch.setDevName(deviceReg.getDevName());
			mobileSwitch.setColTime(deviceStatus.getColTime());
			mobileSwitch.setRptTime(new Date());
			mobileSwitch.setOprTypes("1");
			mobileSwitch.setResultStatus("1");
			mobileSwitch.setDevImei(deviceReg.getDevImei());
			mobileSwitch.setDevMac(deviceReg.getDevMac());
			if (deviceStatus.getOprStyle() != null && ("1".equals(deviceStatus.getOprStyle()) || "14".equals(deviceStatus.getOprStyle()))) {//开锁
				mobileSwitch.setSwitchType("1".equals(deviceStatus.getOprStyle())?"0":"4");
				mobileSwitch.setWorkTime("-");
				if (!"2".equals(deviceReg.getDevStatus())) {//不报警修改锁状态
					deviceReg.setDevStatus("1");//工作
				}
			}else if("7".equals(deviceStatus.getOprStyle())) {//关锁新增
				MobileSwitch ms = mobileSwitchMapper.querySuccessOne(deviceStatus.getDevImei());
				BigDecimal bTo = new BigDecimal(Double.toString(deviceStatus.getColTime().getTime()));
				BigDecimal bFrom = new BigDecimal(Double.toString(ms.getColTime().getTime()));
				BigDecimal sub = bTo.subtract(bFrom);//减
				BigDecimal min = new BigDecimal(String.valueOf((1000 * 60))); 
				BigDecimal div = sub.divide(min, 5, BigDecimal.ROUND_HALF_DOWN);//除
				String maxTime =  div.setScale(0, BigDecimal.ROUND_UP).toString();//向上取整
				mobileSwitch.setWorkTime(maxTime);
				mobileSwitch.setSwitchType("5");
				if (!"2".equals(deviceReg.getDevStatus())) {//不报警修改锁状态
					deviceReg.setDevStatus("0");//正常
				}
			}
			mobileSwitchMapper.insertSelective(mobileSwitch);
			deviceRegMapper.updateByPrimaryKeySelective(deviceReg);
		}
		
		
		
		return num;
	}
}
