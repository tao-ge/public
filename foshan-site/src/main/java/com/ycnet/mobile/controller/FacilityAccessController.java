package com.ycnet.mobile.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.util.ResultData;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityAccess;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.FacilityAccessService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.UsersService;
import com.ycnet.mobile.util.Result;

@RestController
public class FacilityAccessController {
	@Resource(name="usersService")
	private UsersService usersService;
	@Resource(name="facilityAccessService")
	FacilityAccessService faService;
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	
	@RequestMapping("/m/applyForLockkey.htm")
	public Object applyForLockkey(HttpSession session,String devId)
	{
		Result r = Result.get();
		Users user = (Users) session.getAttribute("users"); 
		if(user==null){
			return r.putRContent("用户未登录或不存在！");
		}
		FacilityAccess fa = new FacilityAccess();
		fa.setDevIds(devId);
		fa.setUserId(user.getUserId());
		fa.setUserName(user.getUserName());
		fa.setValidateStatus("0");
		int i= faService.save(fa);
		r.putR(i).initSaveMessage();
		return r;
	}

	/**
	 * 获取钥匙列表
	 * @author YHT
	 * @time   2016年7月14日 上午9:28:43
	 * @param session
	 * @param fa
	 * @return
	 */
	@RequestMapping("/m/getActiveLockkeyList.htm")
	public Object getActiveLockkeyList(HttpSession session,Facility fa){
		Result r = Result.get();
		
		Users user = (Users) session.getAttribute("users"); 
		if(user==null){
			return r.putRContent("用户未登录或不存在！");
		}
		r.putDtList(facilityService.queryKeyList(user.getUserId(), fa)).initQueryMessage();
		return r;
	}
	
	/**
	 * 根据mac地址获取设施信息（废弃）
	 * @author YHT
	 * @time   2016年7月14日 下午1:07:59
	 * @param session
	 * @param fa
	 * @return
	 */
	@RequestMapping("/m/facilityFromdevMac21222.htm")
	public ResultData facilityFromdevMac(HttpSession session,Facility fa){
		Users users = (Users) session.getAttribute("users");
		ResultData result = new ResultData();
		try {
			
			if(users ==null){
				result.setR("-1");
				result.setR_content("用户未登录或不存在！");
		    	return result;
			}
			
			if(fa.getDevMac() == null){
				result.setR("1");
				result.setR_content("智能锁MAC不能为空！");
		    	return result;
			}
			
			result  = facilityService.facilityFromdevMac(session ,fa);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setR("0");
			result.setR_content("根据mac地址获取设施信息出现异常！");
	    	return result;
		}
		
		return result;
	}
	
}
