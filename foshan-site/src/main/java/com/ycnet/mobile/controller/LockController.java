package com.ycnet.mobile.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.config.SessionName;
import com.ycnet.core.config.SocketDataConfig;
import com.ycnet.core.util.AppRequestEntity;
import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.DeviceLockEntity;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.DeviceLockEntityService;
import com.ycnet.frms.service.MobileSwitchService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.mobile.util.Result;

@RestController
public class LockController {
	@Resource(name="mobileSwitchService")
	MobileSwitchService msService;
	@Resource(name="organizitionService")
	OrganizitionService organizitionService;
	
	@Autowired
	private DeviceLockEntityService deviceLockEntityService;
	
	@Autowired
	private SocketDataConfig socketDataConfig;
	
	/**
	 * 
	 * @Title: reportLockSwitch
	 * @Description: 手机端开锁后上报蓝牙开锁数据
	 * @param @param session
	 * @param @param ms
	 * @param @param opTime
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月8日 上午9:07:40
	 * @version V1.0
	 */
	@RequestMapping("/m/reportLockSwitch.htm")
	public Object reportLockSwitch(HttpSession session,MobileSwitch ms,String opTime)
	{
		Result r = Result.get();
		Users user = (Users) session.getAttribute("users"); 
		if(user==null){
			return r.putRContent("用户未登录或不存在！");
		}
		ms.setUserId(user.getUserId());
		ms.setUserName(user.getUserName());
		ms.setMobileImei(user.getMobileImei());
		ms.setMobilePhone(user.getMobilePhone());
		ms.setRptTime(new Date());
		ms.setOrgId(user.getOrgId());
		ms.setSwitchType("0");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			ms.setColTime(format.parse(opTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int i=msService.save(ms);
		r.putR(i).initSaveMessage();
		return r;
	}

	@RequestMapping("/m/getLockRecordList.htm")
	public Object getLockRecordList(HttpSession session,MobileSwitch ms,PageBean pb)
	{
		Result r = Result.get();
		Users user = (Users) session.getAttribute("users"); 
		if(user==null){
			return r.putRContent("用户未登录或不存在！");
		}
		if(ms==null)
			return r.putRContent("参数错误！");
		ms.setUserId(user.getUserId());
		return r.putDtList(msService.queryList(ms, pb)).initQueryMessage();
	}
	

	@RequestMapping("/m/getMyOrgInfo.htm")
	public Object getMyOrgInfo(HttpSession session){
		Result r = Result.get();
		Users user = (Users) session.getAttribute("users"); 
		if(user==null){
			return r.putRContent("用户未登录或不存在！");
		}
		Organizition oz =  organizitionService.selectById(user.getOrgId());
		return r.putDT(oz);
	}
	/**
	 * 
	* @Title: queryMobileSwitchByUserId 
	* @Description: 当前用户的开关锁记录 
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年2月9日 下午2:31:42 
	* @version V1.0
	 */
	@RequestMapping("/m/queryMobileSwitchByUserId.htm")
	public Object queryMobileSwitchByUserId(Facility facility,HttpSession session) {
		Result r = Result.get();
		try {
			Users user = (Users) session.getAttribute("users"); 
			if(user==null){
				return r.putRContent("用户未登录或不存在！");
			}
			List<MobileSwitch> msLits=msService.queryMobileSwitchByUserId(facility,user.getUserId());
			if(msLits!=null && msLits.size()>0) {
				r.setR(1);
				r.setDtList(msLits);
				r.setR_content("查询成功！");
			}else {
				r.setR(0);
				r.setR_content("未查询到数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setR(0);
			r.setR_content("查询数据错误，请联系管理员！");
		}
		return r;
	}
	
	
	/**
	 *  手机远程开锁,点击开锁
	* @Title: unlocking 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param session
	* @param @param deviceLock
	* @param @param userId
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月8日 下午4:04:32 
	* @version V1.0
	 */
	@RequestMapping("/m/unlocking.htm")
	public Object unlocking(HttpSession session,DeviceLockEntity deviceLock,Long userId) {
		Result r = Result.get();
		try {
			Users user = (Users) session.getAttribute(SessionName.mobile); 
			if(user==null){
				r.setR_content("用户未登录!");
				r.setR(0);
				return r;
			}
			if (user.getUserId().intValue()!=userId.intValue()) {
				r.setR_content("用户信息不正确!");
				r.setR(0);
				return r;
			}
			String result = deviceLockEntityService.unlocking(deviceLock,user,socketDataConfig.getUdpIp(),socketDataConfig.getUdpPort());
			r.setR_content(result);
			if (result.contains("成功")) {
				r.setR(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
			r.setR_content("数据发生异常!");
			r.setR(0);
		}
		return r;
	}
}
