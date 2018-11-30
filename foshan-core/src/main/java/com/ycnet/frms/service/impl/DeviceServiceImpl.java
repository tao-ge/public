package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.Constants.FACILITTYPE;
import com.ycnet.core.FrmsException;
import com.ycnet.core.util.HexUtil;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.DeviceLockEntity;
import com.ycnet.frms.bean.DeviceLockStatusEntity;
import com.ycnet.frms.bean.DiscinfoStatusEntity;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityEntity;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.FiberdiscGroupEntity;
import com.ycnet.frms.bean.HardwareUpgradEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.mapper.AreasMapper;
import com.ycnet.frms.mapper.DeviceDiscinfoEntityMapper;
import com.ycnet.frms.mapper.DeviceEntityMapper;
import com.ycnet.frms.mapper.DeviceLockEntityMapper;
import com.ycnet.frms.mapper.DeviceLockStatusEntityMapper;
import com.ycnet.frms.mapper.DeviceLockSwitchMapper;
import com.ycnet.frms.mapper.DiscinfoStatusEntityMapper;
import com.ycnet.frms.mapper.FacilityEntityMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.FiberdiscGroupEntityMapper;
import com.ycnet.frms.mapper.HardwareUpgradEntityMapper;
import com.ycnet.frms.mapper.UsersMapper;
import com.ycnet.frms.service.DeviceLockEntityService;
import com.ycnet.frms.service.DeviceService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.HardwareUpgradEntityService;
import com.ycnet.frms.vo.AreasParent;
import com.ycnet.frms.vo.DeviceDiscinfoEntityVo;
import com.ycnet.frms.vo.DeviceEntityFacilityBean;
import com.ycnet.frms.vo.DeviceEntityVo;
import com.ycnet.frms.vo.DeviceFactilityEntity;
import com.ycnet.frms.vo.DeviceLockStatusEntityVo;
import com.ycnet.frms.vo.DiscInfoEntityVo;
import com.ycnet.frms.vo.FiberdiscGroupEntityVo;
import com.ycnet.frms.vo.FiberdiscVo;
import com.ycnet.frms.vo.mobile.DeviceDiscinfoMobile;
import com.ycnet.frms.vo.mobile.DeviceEntityCountBean;
import com.ycnet.frms.vo.mobile.DeviceEntityDtoBean;
import com.ycnet.frms.vo.mobile.DeviceEntityMobile;
import com.ycnet.frms.vo.mobile.DeviceEntityVoMobile;
import com.ycnet.frms.vo.mobile.DeviceMobile;
import com.ycnet.frms.vo.mobile.DiscInfoEntityVoMobile;
import com.ycnet.frms.vo.mobile.DiscinfoGroupMobile;
import com.ycnet.frms.vo.mobile.FacilityCabinetsInfo;
import com.ycnet.frms.vo.mobile.FacilityEntityMobile;
import com.ycnet.frms.vo.mobile.RackDetailsBean;


@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

	@Resource(name="deviceEntityMapper") 
	private DeviceEntityMapper deviceEntityMapper;
	
	@Resource(name="facilityEntityMapper")  
	private FacilityEntityMapper facilityEntityMapper;
	
	@Resource(name="deviceDiscinfoEntityMapper")  
	private DeviceDiscinfoEntityMapper deviceDiscinfoEntityMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@Resource(name="deviceService")
	private DeviceService deviceService;
	
	@Resource(name="deviceLockStatusEntityMapper")
	private DeviceLockStatusEntityMapper deviceLockStatusEntityMapper;
	
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	
	@Resource(name="fiberdiscGroupEntityMapper")
	private FiberdiscGroupEntityMapper fiberdiscGroupEntityMapper;
	
	@Resource(name="hardwareUpgradEntityMapper")
	private HardwareUpgradEntityMapper hardwareUpgradEntityMapper;
	
	@Resource(name="areasMapper")
	private AreasMapper areasMapper;
	
	@Resource(name="hardwareUpgradEntityService")
	private HardwareUpgradEntityService hardwareUpgradEntityService;
	
	@Resource(name="deviceLockEntityService")
	private DeviceLockEntityService deviceLockEntityService;
	
	@Resource(name="deviceLockEntityMapper")
	private DeviceLockEntityMapper deviceLockEntityMapper;
	
	@Resource(name="discinfoStatusEntityMapper")
	private DiscinfoStatusEntityMapper discinfoStatusEntityMapper;
	
	@Resource(name="deviceLockSwitchMapper")
	private DeviceLockSwitchMapper deviceLockSwitchMapper;
	
	/**
	 * 
	* @Title: queryDeviceListForOrgId 
	* @Description: 查询该组织机构下的中控器信息 
	* @param @param session
	* @param @param userId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月12日 下午2:48:15 
	* @version V1.0
	 */
	@Override
	public List<DeviceEntityDtoBean> queryDeviceListForOrgId(PageBean pb,Long orgId, String devName, String devCode, String codCode) {
		List<DeviceEntityDtoBean> deviceList = deviceEntityMapper.queryDeviceListForOrgId(orgId,pb,devName,devCode,codCode);
		return deviceList;
	}

	/**
	 * 
	* @Title: queryDeviceInfoRoom 
	* @Description: 查询中控器详情（机房） 
	* @param @param session
	* @param @param userId
	* @param @param codId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月12日 下午3:24:34 
	* @version V1.0
	 */
	@Override
	public DeviceEntityVoMobile queryDeviceInfoRoom(Long codId) {
		DeviceEntity vo=deviceEntityMapper.selectByPrimaryKey(codId);
		DeviceEntityVoMobile deviceEntityVoMobile = new DeviceEntityVoMobile();
		List<DeviceFactilityEntity> deviceFactilityList = null;
		if(vo!=null) {
			Facility fa = facilityMapper.selectByPrimaryKey(vo.getDevId());
			if (fa!=null && "20".equals(fa.getDevType())) {//机房
				deviceFactilityList = deviceEntityMapper.queryByPDevAndCodId(codId,vo.getDevId());
			}
			if (fa!=null && "01".equals(fa.getDevType())) {//光交
				deviceFactilityList =deviceEntityMapper.queryByDevAndCodId(codId,vo.getDevId());
			}
			deviceEntityVoMobile.setCodCode(vo.getCodCode());
			deviceEntityVoMobile.setCodId(vo.getCodId());
			deviceEntityVoMobile.setCodMac(vo.getCodMac());
			deviceEntityVoMobile.setCodName(vo.getCodName());
			deviceEntityVoMobile.setDevId(fa.getDevId());
			deviceEntityVoMobile.setDevName(fa.getDevName());
			deviceEntityVoMobile.setDevAddr(fa.getDevAddr());
			deviceEntityVoMobile.setBaiduX(fa.getBaiduX());
			deviceEntityVoMobile.setBaiduY(fa.getBaiduY());
			deviceEntityVoMobile.setDevCode(fa.getDevCode());
			deviceEntityVoMobile.setDevType(fa.getDevType());
			if (deviceFactilityList!=null && deviceFactilityList.size()>0) {
				deviceEntityVoMobile.setDeviceFactilityList(deviceFactilityList);
			}
			
		}else {
			deviceEntityVoMobile=null;
		}
		return deviceEntityVoMobile;
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 绑定中控器
	 * @param @param device
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午2:20:13
	 * @version V1.0
	 */
	@Override
	public int insertSelective(DeviceEntity device) {
		return deviceEntityMapper.insertSelective(device);
	}

	/**
	 * 
	* @Title: verifyBluetoothBound 
	* @Description: 校验蓝牙是否绑定 
	* @param @param session
	* @param @param userId
	* @param @param macs
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年4月13日 上午10:00:19 
	* @version V1.0
	 */
	@Override
	public List<DeviceEntityMobile> verifyBluetoothBound(String macs) {
		List<DeviceEntityMobile> list=new ArrayList<DeviceEntityMobile>();
		String[] macsList = macs.split(",");
		if(macsList.length>0) {
			for (int i = 0; i < macsList.length; i++) {
				DeviceEntityMobile device=deviceEntityMapper.queryDeviceCode(macsList[i]);
				if(device!=null) {
					device.setExist("1");
					if (device.getUser()!=null) {
						Users user = usersMapper.selectByPrimaryKey(device.getUser());
						device.setUserName(user.getUserName());
					}
					if(device.getDevId()!=null) {
						Facility fa = facilityMapper.selectByPrimaryKey(device.getDevId());
						if(fa!=null) {
							device.setDevCode(fa.getDevCode());
							device.setDevType(fa.getDevType());
						}
					}
					list.add(device);
				}else {
					DeviceEntityMobile device1 = new DeviceEntityMobile();
					device1.setExist("0");
					device1.setCodMac(macsList[i]);
					list.add(device1);
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 绑定中控器
	 * @param @param device
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午2:20:13
	 * @version V1.0
	 */
	@Override
	@Transactional
	public int band(DeviceEntity device, Users user, String hardVersion) {
		//中控器编码转换大写
		String codCode = device.getCodCode().toUpperCase();
		device.setCodCode(codCode);
		String hardType="";
		Facility fa = facilityService.selectById(device.getDevId());
		if(fa ==null || fa.getOrgId()!=user.getOrgId()) {
			throw new FrmsException("所属设施不存在");
		}else {
			device.setCodName(fa.getDevName()+"（中控器）");
			if ("01".equals(fa.getDevType())) {
				hardType="01";
			}
			if ("20".equals(fa.getDevType())) {
				hardType="02";
			}
		}
		//查询中控器是否已绑定,过滤掉已删除的中控器
		DeviceEntity deviceEntity = deviceService.selectByCodCode(device.getCodCode(),"2");
		if(deviceEntity !=null) {
			Facility fa1 = facilityService.selectById(deviceEntity.getDevId());
			throw new FrmsException("该中控器已与"+fa1.getDevName()+"设施绑定");
		}
		//查询Mac是否已绑定,过滤掉已删除的中控器
		if(device.getCodId()==null) {
			int mac = deviceService.selectByCodMac(device.getCodMac(),"2");
			if(mac>0) {
				throw new FrmsException("蓝牙MAC已绑定");
			}
		}
		//绑定时,如果设施为光交箱 不能绑定一个以上中控器
		if(device.getCodId()==null && "01".equals(fa.getDevType().trim())) {
			List<DeviceEntity> deList = deviceEntityMapper.selectDevId(device.getDevId());
			if(deList != null && deList.size()>1) {
				throw new FrmsException("该光交箱已绑定中控器");
			}
		}
		//中控器版本绑定
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("hardVersion", hardVersion);
		map1.put("orgId", user.getOrgId());
		map1.put("hardType", hardType);
		HardwareUpgradEntity hard = hardwareUpgradEntityMapper.queryByHardVersionAndOrgId(map1);
		if(hard == null) {
			throw new FrmsException("硬件版本错误");
		}
		device.setHardId(hard.getHardId());
		device.setCodState("0");
		device.setOrgId(user.getOrgId());
		device.setWorkState("01");
		//查询设施下的已删除中控器
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devId", device.getDevId());
		map.put("codState", "2");
		DeviceEntity delDevice = deviceEntityMapper.queryByDevIdAndCodState(map);
		if(device.getCodId()==null  && delDevice != null) {
			delDevice.setCodCode(device.getCodCode());
			delDevice.setCodName(device.getCodName());
			delDevice.setCodMac(device.getCodMac());
			delDevice.setCodState("0");
			delDevice.setLastModifyTime(new Date());
			delDevice.setLastModifyUser(user.getUserId());
			delDevice.setHardId(device.getHardId());
			delDevice.setLastHardTime(device.getLastHardTime());
			device.setWorkState("01");
			//查询锁
			List<DeviceLockEntity> dlList = deviceLockEntityService.queryByCodIdAndDevId(device.getDevId(),delDevice.getCodId());
			//如果该中控器下面没有绑定锁
			if(dlList == null || dlList.size() <= 0) {
				//绑定锁
				delDevice.setLocks(device.getLocks());
				bandDeviceLock(fa,delDevice,user);
			}
			return deviceEntityMapper.updateByPrimaryKeySelective(delDevice);
		}else if(device.getCodId()==null && delDevice == null) {//绑定中控器
			device.setCreateTime(new Date());
			device.setCreateUser(user.getUserId());
			//绑定中控器
			int num = deviceEntityMapper.insertSelective(device);
			//绑定锁
			bandDeviceLock(fa,device,user);
			return num;
		}else if(device.getCodId()!=null){//重新绑定中控器
			device.setLastModifyTime(new Date());
			device.setLastModifyUser(user.getUserId());
			return deviceEntityMapper.updateByPrimaryKeySelective(device);
		}else {
			return 0;
		}
	}

	/**
	 * 
	 * @Title: bandDeviceLock
	 * @Description: 绑定锁
	 * @param @param fa
	 * @param @param device
	 * @param @param user 
	 * @return void 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月9日 下午2:37:40
	 * @version V1.0
	 */
	private void bandDeviceLock(Facility fa, DeviceEntity device, Users user) {
		String defaultString = "00000000000000";
		if(fa .getDevType() != null && //!"20".equals(fa .getDevType()) && 
				device.getLocks()!=null && !"".equals(device.getLocks())) {
			String[] lockStrs = device.getLocks().split(",");
			for (int i = 0; i < lockStrs.length; i++) {
				String lock = lockStrs[i];
				DeviceLockEntity deviceLock = new DeviceLockEntity();
				int req = deviceLockEntityService.queryByLockCodeAndDevId(lock,device.getDevId(),device.getCodId());
				if(req>0) {
					throw new FrmsException("锁编码("+lock+")已绑定");
				}else {
					deviceLock.setLockCode(lock+defaultString);
				}
				deviceLock.setLockName(lock);
				deviceLock.setCodId(device.getCodId());
				deviceLock.setDevId(device.getDevId());
				deviceLock.setRegTime(new Date());
				deviceLock.setRegUser(user.getUserId());
				deviceLock.setValidateSign("0");
				deviceLock.setLockWorkStatus("0");
				deviceLock.setOrgId(user.getOrgId());
				deviceLockEntityService.insertSelective(deviceLock);
			}
		}
	}

	/**
	 * 
	 * @Title: selectByCodMac
	 * @Description: 判断codMac是否已经绑定
	 * @param @param codMac
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午3:36:33
	 * @version V1.0
	 */
	@Override
	public int selectByCodMac(String codMac, String codState) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("codMac", codMac);
		map.put("codState", codState);
		return deviceEntityMapper.selectByCodMac(map);
	}

	/**
	 * 
	* @Title: queryDeviceEntityList 
	* @Description: 中控器列表查询 
	* @param @param session
	* @param @param device
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年4月15日 下午2:20:05 
	* @version V1.0
	 */
	@Override
	public PageBean queryDeviceEntityList(PageBean pb,DeviceEntityVo device) {
		pb.setList(deviceEntityMapper.queryDeviceEntityList(device,pb));
		pb.setRows(deviceEntityMapper.queryDeviceEntityCount(device,pb));
		return pb;
	}

	/**
	 * 
	* @Title: queryDeviceInfo 
	* @Description: 中控器详情 
	* @param @param codId
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年4月15日 下午4:43:50 
	* @version V1.0
	 */
	@Override
	public DeviceLockStatusEntityVo queryDeviceInfo(Long codId) {
		DeviceLockStatusEntityVo vo=new DeviceLockStatusEntityVo();
		DeviceEntity dev = deviceEntityMapper.selectByPrimaryKey(codId);
		if(dev!=null) {
			vo.setCodId(dev.getCodId());
			vo.setCodImei(dev.getCodImei());
			vo.setCodName(dev.getCodName());
			vo.setCodMac(dev.getCodMac());
			vo.setCreateUser(dev.getCreateUser());
			vo.setHardState(dev.getHardState());
			Users userCre = usersMapper.selectByPrimaryKey(dev.getCreateUser());
			if(userCre!=null) {
				vo.setCreateUserName(userCre.getUserName());
			}
			vo.setCreateTime(dev.getCreateTime());
			vo.setLastModifyTime(dev.getLastModifyTime());
			vo.setLastModifyUser(dev.getLastModifyUser());
			Users userLast = usersMapper.selectByPrimaryKey(dev.getLastModifyUser());
			if(userLast!=null) {
				vo.setLastModifyUserName(userLast.getUserName());
			}
			if(dev.getDevId()!=null) {
				Facility fa = facilityMapper.selectByPrimaryKey(dev.getDevId());
				if(fa!=null) {
					vo.setDevCode(fa.getDevCode());
					vo.setDevAddr(fa.getDevAddr());
					vo.setDevName(fa.getDevName());
					vo.setDevType(fa.getDevType());
					Areas area = areasMapper.selectByPrimaryKey(fa.getAreaCode2());
					if(area!=null) {
						vo.setAreaCode1(area.getAreaName());
					}
				}
				DeviceLockStatusEntity lock = deviceLockStatusEntityMapper.queryForDevId(dev.getDevId());
				if(lock!=null) {
					vo.setBattery(lock.getBattery());//电量
					vo.setDoorOpen01(lock.getDoorOpen01());//门01状态
					vo.setLockOpen01(lock.getLockOpen01());//锁01状态
					vo.setDoorOpen02(lock.getDoorOpen02());//门02状态
					vo.setLockOpen02(lock.getLockOpen02());//锁02状态
					vo.setSignals(lock.getSignals());//信号
					vo.setTemp(lock.getTemp());//温度
					vo.setTilt(lock.getTilt());//倾斜
					vo.setRptTime(lock.getRptTime());//上报时间
				}
			}
			if(dev.getHardId()!=null) {
				HardwareUpgradEntity hard = hardwareUpgradEntityMapper.selectByPrimaryKey(dev.getHardId());
				if(hard!=null) {
					if(hard.getHardVersion()!=null) {
						vo.setHardVersion(hard.getHardVersion());//版本号
					}
				}
			}
		}
		return vo;
	}

	/**
	 * 设备状态查询
	* @Title: queryFacilityState 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pb
	* @param @param deviceEntity
	* @param @param orgId
	* @param @return    入参
	* @return PageBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月15日 下午4:37:34 
	* @version V1.0
	 */
	@Override
	public PageBean queryFacilityState(PageBean pb, DeviceEntity deviceEntity, Long orgId,String curStatus) {
		Map<String,Object> map=new HashMap<String,Object>();
		//进入页面初始化
		if(curStatus==null) {
			curStatus="1";
		}
		//查询历史状态时，不传状态参数即：查询所有数据，包括当前状态和历史状态
		if ("0".equals(curStatus)) {
			curStatus=null;
		}
		map.put("pb", pb);
		map.put("curStatus", curStatus);
		map.put("orgId", orgId);
		map.put("deviceEntity", deviceEntity);
		pb.setList(deviceEntityMapper.queryFacilityStateList(map));
		pb.setRows(deviceEntityMapper.queryFacilityStateCount(map));
		return pb;
	}

	/**
	 * 
	 * @Title: queryByYearAndMonth
	 * @Description: 根据年/月查询，当月设备安装数量
	 * @param @param year
	 * @param @param month
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 上午10:44:26
	 * @version V1.0
	 */
	@Override
	public int queryByYearAndMonth(String year, String month, Users user,String areaCode1,String areaCode2) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("user", user);
		if (areaCode2==null||"".equals(areaCode2)) {
			map.put("areaCode1", areaCode1);
		}else {
			map.put("areaCode1", areaCode2);
		}
		return deviceEntityMapper.queryByYearAndMonth(map);
	}

	/**
	 * 
	 * @Title: selectByCodCode
	 * @Description: 判断codCode是否已经绑定
	 * @param @param codCode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 下午5:02:04
	 * @version V1.0
	 */
	@Override
	public DeviceEntity selectByCodCode(String codCode, String codState) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("codCode", codCode);
		map.put("codState", codState);
		return deviceEntityMapper.selectByCodCode(map);
	}

	/**
	 * 
	 * @Title: selectByImei
	 * @Description: 判断codImei是否已经绑定
	 * @param @param codImei
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月17日 下午5:05:01
	 * @version V1.0
	 */
	@Override
	public int selectByImei(String codImei) {
		return deviceEntityMapper.selectByImei(codImei);
	}

	/**
	 * 按位置浏览锁设施状态地图显示数据查询
	* @Title: queryDeviceEntityListByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return List<DeviceEntity>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月20日 下午1:47:09 
	* @version V1.0
	 */
	@Override
	public HashMap<String, Object> queryDeviceEntityListByOrgId(Long orgId,String devName,String areaCode1) {
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
		  List<DeviceEntityFacilityBean> list1= deviceEntityMapper.queryDeviceEntityListByOrgId(orgId,devName,list2);
		  	String bj = "0";//报警 1 是
			String gz = "0";//工作 1 是
			if (list1 !=null && list1.size()>0) {
				for (int i = 0; i < list1.size(); i++) {
					if (list1.get(i).getWorkState().equals("03")) {
						 bj = "1";
					}
					if (list1.get(i).getWorkState().equals("02")) {
						 gz = "1";
					}
				}
			}
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("list", list1);
			map.put("bj", bj);
			map.put("gz", gz);
			return map;
	}

	
	/**
	 * 已经安装设备总数
	* @Title: queryByAreaCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午1:27:26 
	* @version V1.0
	 */
	@Override
	public int queryByAreaCode(String areaCode1,String areaCode2,Users user) {
			Map<String, Object> map = new HashMap<String,Object>();
			if (areaCode2==null || "".equals(areaCode2)) {
				map.put("areaCode1", areaCode1);
			}else {
				map.put("areaCode1", areaCode2);
			}
			map.put("orgId", user.getOrgId());
		return deviceEntityMapper.queryByAreaCode(map);
	}

	/**
	 * 未安装设备总数
	* @Title: queryByAreaCodeNo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param areaCode1
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午1:35:42 
	* @version V1.0
	 */
	@Override
	public int queryByAreaCodeNo(String areaCode1,String areaCode2,Users user) {
			Map<String, Object> map = new HashMap<String,Object>();
			if (areaCode2==null || "".equals(areaCode2)) {
				map.put("areaCode1", areaCode1);
			}else {
				map.put("areaCode1", areaCode2);
			}
			map.put("orgId", user.getOrgId());
		return deviceEntityMapper.queryByAreaCodeNo(map);
	}


	/**
	 * 
	 * @Title: selectListByDevId
	 * @Description: 根据devId查询设施下的中控器
	 * @param @param devId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月8日 上午10:06:25
	 * @version V1.0
	 */
	@Override
	public List<DeviceEntity> selectListByDevId(Long devId) {
		return deviceEntityMapper.selectListByDevId(devId);
	}

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改
	 * @param @param device
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年5月8日 上午10:37:08
	 * @version V1.0
	 */
	@Override
	public int updateByPrimaryKeySelective(DeviceEntity device) {
		return deviceEntityMapper.updateByPrimaryKeySelective(device);
	}

	/**
	 * 根据设施ID查询机房中中控器列表
	 * @Title: findImDeviceByDevId   
	 * @param: devId
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	@Override
	public List<Map<String, Object>> findImDeviceByDevId(Long orgId, Long devId) {
		return deviceEntityMapper.findImDeviceByDevId(orgId, devId);
	}

	/**
	 * 根据设施ID,查询中控器和盘分组信息
	* @Title: queryByDevId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return DeviceMobile    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 下午2:12:29 
	* @version V1.0
	 */
	@Override
	public RackDetailsBean queryByDevId(Long devId) {
		List<DeviceMobile> deviceMobileList = deviceEntityMapper.queryByDevId(devId);
		List<DiscinfoGroupMobile> discinfoGroupMobileList=fiberdiscGroupEntityMapper.selectByDevId(devId);
		RackDetailsBean rackDetailsBean = new RackDetailsBean();
		if (deviceMobileList!=null && deviceMobileList.size()>0) {
			rackDetailsBean.setDeviceMobileList(deviceMobileList);
		}
		if (discinfoGroupMobileList !=null && discinfoGroupMobileList.size()>0) {
			rackDetailsBean.setDiscinfoGroupMobileList(discinfoGroupMobileList);
		}
		return rackDetailsBean;
	}

	/**
	 * 根据设施ID,查询所属机柜和绑定机柜数
	* @Title: queryCabinetsAndBindCounts 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return DeviceEntityCountBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午1:25:20 
	* @version V1.0
	 */
	@Override
	public DeviceEntityCountBean queryCabinetsAndBindCounts(Long devId) {
		DeviceEntityCountBean deviceEntityCountBean = new DeviceEntityCountBean();
		List<FacilityEntityMobile> list =facilityEntityMapper.queryPdevIdList(devId);
		if (list!=null && list.size()>0) {
			deviceEntityCountBean.setCabinetCounts(list.size());
		}else {
			deviceEntityCountBean.setCabinetCounts(0);
		}
		deviceEntityCountBean.setCabinetBindCounts(deviceEntityMapper.queryCabinetsAndBindCounts(devId));
		return deviceEntityCountBean;
	}

	/**
	 * 查询机房下机柜列表
	* @Title: queryCabinetsInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @return    入参
	* @return List<FacilityCabinetsInfo>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月12日 下午2:13:32 
	* @version V1.0
	* 
	*/
	@Override
	public List<FacilityCabinetsInfo> queryCabinetsInfo(Long devId) {
		return deviceEntityMapper.queryCabinetsInfo(devId);
	}

	/**
	 * 
	 * @Title: deleteDevice
	 * @Description: 删除中控器
	 * @param @param codId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月14日 上午9:47:13
	 * @version V1.0
	 */
	@Override
	@Transactional
	public int deleteDevice(Long codId) {
		int req = 0;
		DeviceEntity device = deviceEntityMapper.selectByPrimaryKey(codId);
		if(device == null) {
			throw new FrmsException("查无此中控器,无法删除");
		}
		device.setCodState("2");//已删除
//		//删除端子控制器
//		req = deviceDiscinfoEntityMapper.deleteByCodId(codId);
		//查询中控器下没有删除的端子控制器
		List<DeviceDiscinfoEntity> ddfList = deviceDiscinfoEntityMapper.queryByCodId(codId);
		if(ddfList != null && ddfList.size() > 0) {
			throw new FrmsException("中控器下已绑定检测板,请先解绑检测板");
		}
		//删除中控器
		req = deviceEntityMapper.updateByPrimaryKeySelective(device);
		/*
		//根据codId和devId查询中控器绑定的锁
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("codId", codId);
		map.put("devId", device.getDevId());
		List<DeviceLockEntity> dlList = deviceLockEntityMapper.queryByCodIdAndDevId(map);
		if(dlList != null && dlList.size() > 0) {
			for (int i = 0; i < dlList.size(); i++) {
				Map<String,Object> dsMap = new HashMap<String,Object>();
				dsMap.put("lockId", dlList.get(i).getLockId());
				dsMap.put("devId", device.getDevId());
				//删除开关锁记录
				deviceLockSwitchMapper.deleteByLockId(dsMap);
				//删除锁状态记录
				deviceLockStatusEntityMapper.deleteByLockId(dsMap);
				//删除锁
				deviceLockEntityMapper.deleteByPrimaryKey(dlList.get(i).getLockId());
			}
		}
		//查询中控器下绑定端子控制器
		List<DeviceDiscinfoEntity> ddfList = deviceDiscinfoEntityMapper.queryByCodId(codId);
		if(ddfList != null && ddfList.size() > 0) {
			for (DeviceDiscinfoEntity deviceDiscinfoEntity : ddfList) {
				//删除中控器所绑定端子控制器的上报记录
				discinfoStatusEntityMapper.deleteByCodIdAndDiscId(codId,deviceDiscinfoEntity.getDiscId());
				//删除中控器所绑定的端子控制器
				deviceDiscinfoEntityMapper.deleteByPrimaryKey(deviceDiscinfoEntity.getDiscId());
			}
		}
		//删除中控器
		req = deviceEntityMapper.deleteByPrimaryKey(codId);
		*/
		return req;
	}
	
	/**
	 * 微信小程序根据编码和名称查询
	 * @param devCode
	 * @param devName
	 * @return   
	 */
	@Override
	public List<Map<String, Object>> queryDeviceAsWxApp(String devCode, String devName, int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(devCode)) {
			map.put("devCode", "%"+devCode+"%");
		}
		if(StringUtils.isNotBlank(devName)) {
			map.put("devName", "%"+devName+"%");
		}
		map.put("startRowNum", (pageNo-1)*pageSize);
		map.put("endRowNum", pageSize);
		return deviceEntityMapper.queryDeviceAsWxApp(map);
	}
	
	/**
	 * 根据用户坐标查询符近的中控器
	 * @Title: getRangeDeviceByPoint   
	 * @param: curLng
	 * @param: curLat
	 * @param: distance	距离以千米为单位 
	 * @return: List<Map<String,Object>>      
	 * @throws
	 */
	@Override
	public List<Map<String, Object>> getRangeDeviceByPoint(double curLng, double curLat, int distance) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("curLng", curLng);
		map.put("curLat", curLat);
		map.put("distance", distance);
		return deviceEntityMapper.queryDeviceAsWxApp(map);
	}
	
	@Override
	public Long queryImDeviceCountByDevId(Long devId) {
		return deviceEntityMapper.queryImDeviceCountByDevId(devId);
	}
}
