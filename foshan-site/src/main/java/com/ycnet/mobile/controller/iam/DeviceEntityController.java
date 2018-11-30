package com.ycnet.mobile.controller.iam;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.FrmsException;
import com.ycnet.core.config.SessionName;
import com.ycnet.core.config.SocketDataConfig;
import com.ycnet.core.util.AppRequestEntity;
import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceEntity;
import com.ycnet.frms.bean.DeviceLockEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.DeviceDiscinfoService;
import com.ycnet.frms.service.DeviceLockEntityService;
import com.ycnet.frms.service.DeviceService;
import com.ycnet.frms.vo.mobile.BindDiscNumMobile;
import com.ycnet.frms.vo.mobile.DeviceDiscinfoEntityMobile;
import com.ycnet.frms.vo.mobile.DeviceDiscinfosMobile;
import com.ycnet.frms.vo.mobile.DeviceEntityDtoBean;
import com.ycnet.frms.vo.mobile.DeviceEntityMobile;
import com.ycnet.frms.vo.mobile.DeviceEntityVoMobile;
import com.ycnet.frms.vo.mobile.DeviceLockInfoMobile;

/**
 * 
* @ClassName: DeviceController 
* @Description: 中控器控制器
* @author liucanghai  
* @date 2018年4月12日 下午2:43:13 
* @version V1.0
 */
@RestController
@RequestMapping("/iam")
public class DeviceEntityController {
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceLockEntityService deviceLockEntityService;
	
	@Autowired
	private SocketDataConfig socketDataConfig;
	
	@Autowired
	private DeviceDiscinfoService deviceDiscinfoService;
	
	
	
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
	@RequestMapping(value="/queryDeviceListForOrgId.htm")
	public Object queryDeviceListForOrgId(HttpSession session,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryDeviceListForOrgId");
		try {
			Integer pageNo = Integer.parseInt(entity.getDto().getOrDefault("pageNo", "0").toString());
			Integer pageSize = Integer.parseInt(entity.getDto().getOrDefault("pageSize", "0").toString());
			String devName = entity.getDto().getOrDefault("devName", "").toString();
			String devCode = entity.getDto().getOrDefault("devCode", "").toString();
			String codCode = entity.getDto().getOrDefault("codCode", "").toString();
			PageBean pb = new PageBean();
			pb.setPageNo(pageNo);
			if (pageSize.intValue()!=0) {
				pb.setPageSize(pageSize);
			}
			if(pb.getPageNo()==0) {
				pb=null;
			}
			Users users=(Users)session.getAttribute(SessionName.mobile);
			if(users!=null) {
				List<DeviceEntityDtoBean> deviceList=deviceService.queryDeviceListForOrgId(pb,users.getOrgId(),devName,devCode,codCode);
				if(deviceList!=null && deviceList.size()>0) {
					r.setCode("1");
					r.setMessage("查询成功！");
					r.setDto(deviceList);
				}else {
					r.setCode("0");
					r.setMessage("未查询到数据！");
				}
			}else {
				r.setCode("0");
				r.setMessage("请重新登录！");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败！");
			e.printStackTrace();
		}
		return r;
	}
	/**
	 * 
	* @Title: queryDeviceInfoRoom 
	* @Description: 查询中控器详情（机房和非机房） 
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
	@RequestMapping(value="/queryDeviceInfoRoom.htm")
	public Object queryDeviceInfoRoom(HttpSession session,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryDeviceInfoRoom");
		try {
			Long codId = Long.parseLong(entity.getDto().getOrDefault("codId", -999l).toString());
			if(codId==null || codId==-999L) {
				r.setCode("0");
				r.setMessage("中控器ID不能为空！");
				return r;
			}
			Users users=(Users)session.getAttribute(SessionName.mobile);
			if(users!=null) {
				DeviceEntityVoMobile device=deviceService.queryDeviceInfoRoom(codId);
				if(device!=null) {
					r.setCode("1");
					r.setMessage("查询成功！");
					r.setDto(device);
				}else {
					r.setCode("0");
					r.setMessage("未查询到数据！");
				}
			}else {
				r.setCode("0");
				r.setMessage("请重新登录！");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败！");
			e.printStackTrace();
		}
		return r;
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
	@RequestMapping("/verifyBluetoothBound.htm")
	public Object verifyBluetoothBound(HttpSession session,@RequestBody AppRequestEntity entity) {
		String macs =entity.getDto().getOrDefault("macs", "").toString();
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("verifyBluetoothBound");
		try {
			if(macs==null || "".equals(macs) ) {
				r.setCode("0");
				r.setMessage("蓝牙mac集合不能为空！");
				return r;
			}
			Users users=(Users)session.getAttribute(SessionName.mobile);
			if(users!=null) {
				List<DeviceEntityMobile> list=deviceService.verifyBluetoothBound(macs);
				if(list!=null && list.size()>0) {
					r.setCode("1");
					r.setMessage("查询成功！");
					r.setDto(list);
				}else {
					r.setCode("0");
					r.setMessage("未查询到数据！");
				}
			}else {
				r.setCode("0");
				r.setMessage("请重新登录！");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败！");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	 * @Title: bandDevice
	 * @Description: 绑定/重新绑定中控器
	 * @param @param session
	 * @param @param device
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午2:19:47
	 * @version V1.0
	 */
	@RequestMapping("/bandDevice.htm")
	public Object bandDevice(HttpSession session,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("bandDevice");
		try {
			Long codId =Long.parseLong(entity.getDto().getOrDefault("codId", -999L).toString());
			String codMac = entity.getDto().getOrDefault("codMac", "").toString();
			String codCode = entity.getDto().getOrDefault("codCode", "").toString();
			String hardVersion = entity.getDto().getOrDefault("hardVersion", "").toString();
			Long devId =Long.parseLong(entity.getDto().getOrDefault("devId", -999L).toString());
//			Long hardId =Long.parseLong(entity.getDto().getOrDefault("hardId", -999L).toString());
			String locks = entity.getDto().getOrDefault("locks", "").toString();
			Users user=(Users)session.getAttribute(SessionName.mobile);
			if(user==null) {
				r.setCode("0");
				r.setMessage("用户请登录");
				return r;
			}
			if(codMac==null || "".equals(codMac.trim())) {
				r.setCode("0");
				r.setMessage("蓝牙MAC不能为空");
				return r;
			}
			if(hardVersion==null || "".equals(hardVersion.trim())) {
				r.setCode("0");
				r.setMessage("中控器版本不能为空");
				return r;
			}else {
				hardVersion=Double.valueOf(hardVersion)/100+"";
			}
			if(codCode==null || "".equals(codCode.trim())) {
				r.setCode("0");
				r.setMessage("中控器ID不能为空");
				return r;
			}
			if(devId==null ||devId.longValue()==-999L) {
				r.setCode("0");
				r.setMessage("所属设施ID不能为空");
				return r;
			}
			if(codMac != null && !"".equals(codMac.trim()) && codMac.length()>32) {
				r.setCode("0");
				r.setMessage("蓝牙MAC过长");
				return r;
			}
			DeviceEntity device=new DeviceEntity();
			if(codId != null && codId != 0 && codId != -999L) {
				device.setCodId(codId);
			}
			device.setCodMac(codMac);
			device.setDevId(devId);
			device.setLocks(locks);
			device.setCodCode(codCode);
			int req = deviceService.band(device,user,hardVersion);
			if(req>0) {
				r.setCode("1");
				r.setMessage("绑定成功");
			}else {
				r.setCode("0");
				r.setMessage("绑定失败");
			}
		}catch (FrmsException e1) {
			r.setCode("0");
			r.setMessage(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("绑定失败");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	 * @Title: deleteDevice
	 * @Description: 删除中控器
	 * @param @param request
	 * @param @param entity
	 * @param @param session
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月14日 上午9:42:47
	 * @version V1.0
	 */
	@RequestMapping("/deleteDevice.htm")
	public Object deleteDevice(HttpServletRequest request,@RequestBody AppRequestEntity entity,HttpSession session) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("deleteDevice");
		try {
			Long codId = Long.parseLong(entity.getDto().getOrDefault("codId", -999l).toString());
			Users users = (Users) request.getSession().getAttribute(SessionName.mobile);
			if (users==null) {
				r.setCode("0");
				r.setMessage("用户未登录!");
				return r;
			}
			if (codId == null || codId == -999l) {
				r.setCode("0");
				r.setMessage("中控器ID不能为空!");
				return r;
			}
			int req = deviceService.deleteDevice(codId);
			if(req > 0) {
				r.setCode("1");
				r.setMessage("删除成功!");
			}else {
				r.setCode("0");
				r.setMessage("删除失败!");
			}
		} catch (FrmsException e1) {
			r.setCode("0");
			r.setMessage(e1.getMessage());
			e1.printStackTrace();
		} catch (NumberFormatException e) {
			r.setCode("0");
			r.setMessage("删除失败!");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 根据设施ID查询机房中中控器列表
	 */
	@RequestMapping("/findImDeviceByDevId.htm")
	public Object findImDeviceByDevId(HttpSession session, @RequestBody AppRequestEntity entity) {
		Long devId = Long.valueOf(entity.getDto().getOrDefault("devId", "0").toString());
		Users user = (Users) session.getAttribute("users");
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("findFacilityByPdevId");
		try {
			if(null == user) {
				r.setCode("0");
				r.setMessage("用户未登录或不存在！");
				r.setDto(null);
				return r;
			}
			List<Map<String, Object>> list = deviceService.findImDeviceByDevId(user.getOrgId(), devId);
			if (list!=null && list.size()>0) {
				r.setCode("1");
				r.setMessage("查询成功！");
				r.setDto(list);
			}else {
				r.setCode("0");
				r.setMessage("未查询到数据!！");
			}
			return r;
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败！");
			r.setDto(null);
			e.printStackTrace();
			return r;
		}
	}
	
	/**
	 * 查询锁,中控器,设施信息
	* @Title: queryDeviceLockInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param session
	* @param @param entity
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月5日 上午9:40:00 
	* @version V1.0
	 */
	@RequestMapping("/queryDeviceLockInfo.htm")
	public Object queryDeviceLockInfo(HttpSession session, @RequestBody AppRequestEntity entity) {
		String devName = entity.getDto().getOrDefault("devName", "").toString();
		String devCode = entity.getDto().getOrDefault("devCode", "").toString();
		Users user = (Users) session.getAttribute(SessionName.mobile);
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryDeviceLockInfo");
		try {
			if(null == user) {
				r.setCode("0");
				r.setMessage("用户未登录或不存在！");
				r.setDto(null);
				return r;
			}
			List<DeviceLockInfoMobile> list =deviceLockEntityService.queryDeviceLockInfo(user.getOrgId(),devCode,devName);
			if (list!=null && list.size()>0) {
				r.setCode("1");
				r.setMessage("查询成功！");
				r.setDto(list);
			}else {
				r.setCode("0");
				r.setMessage("未查询到数据！");
				r.setDto(null);
			}
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode("0");
			r.setDto(null);
			r.setMessage("查询失败!！");
		}
		return r;
	}
	
	/**
	 * 手机远程开锁,点击开锁
	* @Title: queryMobileSwitchPreFive 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月7日 上午9:39:02 
	* @version V1.0
	 */
	@RequestMapping("/unlocking.htm")
	public Object unlocking(HttpSession session,@RequestBody AppRequestEntity entity) {
//		String lockCode = entity.getDto().getOrDefault("lockCode", "").toString();
		Long userId = Long.valueOf(entity.getDto().getOrDefault("userId", -9999l).toString());
		Long codId = Long.valueOf(entity.getDto().getOrDefault("codId", -9999l).toString());
		DeviceLockEntity deviceLock = new DeviceLockEntity();
//		deviceLock.setLockCode(lockCode);
		deviceLock.setCodId(codId);
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("unlocking");
		try {
			Users user = (Users) session.getAttribute(SessionName.mobile); 
			if(user==null){
				r.setMessage("用户未登录!");
				r.setCode("0");
				return r;
			}
			if (codId!=null && codId==-9999l) {
				r.setMessage("中控器ID,不能为空!");
				r.setCode("0");
				return r;
			}
			if(user.getUserId().intValue()!=userId.intValue()){
				r.setMessage("用户信息不正确!");
				r.setCode("0");
				return r;
			}
			String result = deviceLockEntityService.unlocking(deviceLock,user,socketDataConfig.getUdpIp(),socketDataConfig.getUdpPort());
			//System.out.println(result+"============================================");
			if (result.contains("开锁成功")) {
				r.setCode("1");
				r.setMessage("开锁成功!");
			}else if(result.contains("锁未激活")){
				r.setCode("0");
				r.setMessage("锁未激活!");
			}else if(result.contains("开锁失败")){
				r.setCode("0");
				r.setMessage("开锁失败!");
			}else {
				r.setCode("0");
				r.setMessage("开锁失败!");
			}	
			r.setMessage(result);
		}catch (Exception e) {
			e.printStackTrace();
			r.setCode("0");
			r.setMessage("数据发生异常");
		}
		return r;
	}
	
	/**
	 * 查询已绑定的设备列表
	* @Title: queryBindDiscNum 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param entity
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月1日 下午1:25:12 
	* @version V1.0
	 */
	@RequestMapping("/queryBindDiscNum.htm")
	public Object queryBindDiscNum(HttpServletRequest request,@RequestBody AppRequestEntity entity) {
//		Long codId = Long.parseLong(entity.getDto().getOrDefault("codId", -999L).toString());
		Long devId = Long.parseLong(entity.getDto().getOrDefault("devId", -999L).toString());
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryBindDiscNum");
		try {
			Users user=(Users) request.getSession().getAttribute(SessionName.mobile);
			if (null==user) {
				r.setCode("0");
				r.setMessage("用户未登录!");
				return r;
			}
			if (null==devId || devId==-999l) {
				r.setCode("0");
				r.setMessage("设施ID不能为空!");
				return r;
			}
			List<BindDiscNumMobile> bindDiscNumMobileList=deviceDiscinfoService.queryBindDiscNum(devId,user.getOrgId());
			if (bindDiscNumMobileList !=null && bindDiscNumMobileList.size()>0) {
				r.setCode("1");
				r.setMessage("查询成功");
				r.setDto(bindDiscNumMobileList);
			}else {
				r.setCode("0");
				r.setMessage("未查询到数据");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 查询检测板的详细信息
	* @Title: queryDeviceDiscinfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param entity
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年9月1日 下午2:25:05 
	* @version V1.0
	 */
	@RequestMapping("/queryDeviceDiscinfo.htm")
	public Object queryDeviceDiscinfo(HttpServletRequest request,@RequestBody AppRequestEntity entity) {
		Long codId = Long.parseLong(entity.getDto().getOrDefault("codId", -999L).toString());
		Long devId = Long.parseLong(entity.getDto().getOrDefault("devId", -999L).toString());
		String discContrId = entity.getDto().getOrDefault("discContrId", "").toString();
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryDeviceDiscinfo");
		try {
			Users user=(Users) request.getSession().getAttribute(SessionName.mobile);
			if (null==user) {
				r.setCode("0");
				r.setMessage("用户未登录!");
				return r;
			}
			if (null==devId || devId==-999l) {
				r.setCode("0");
				r.setMessage("设施ID不能为空!");
				return r;
			}
			if (null==codId || codId==-999l) {
				r.setCode("0");
				r.setMessage("中控器ID不能为空!");
				return r;
			}
			if (null==discContrId || "".equals(discContrId)) {
				r.setCode("0");
				r.setMessage("检测板ID不能为空!");
				return r;
			}
			DeviceDiscinfosMobile deviceDiscinfosMobile=deviceDiscinfoService.queryDeviceDiscinfo(codId,devId,discContrId,user.getOrgId());
			if (deviceDiscinfosMobile !=null) {
				r.setCode("1");
				r.setMessage("查询成功");
				r.setDto(deviceDiscinfosMobile);
			}else {
				r.setCode("0");
				r.setMessage("未查询到数据");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败");
			e.printStackTrace();
		}
		return r;
	}
}
