package com.ycnet.mobile.controller.iam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.log.FrmsLog;
import com.ycnet.core.util.AppRequestEntity;
import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.DeviceService;
import com.ycnet.frms.service.DiscinfoService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscGroupService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.ResourceDataLogService;
import com.ycnet.frms.service.UsersService;


@RestController
@RequestMapping("/iam")
public class AddInstrumentController {
	
	@Resource(name="usersService")
	private UsersService usersService;
	
	@RequestMapping(value = "/appLogin.htm")
	@FrmsLog("用户登录")
	public Object appLogin(HttpSession session ,@RequestBody AppRequestEntity entity)
	{
		String userCode = entity.getDto().getOrDefault("userCode", "").toString();
		String userPwd = entity.getDto().getOrDefault("userPwd", "").toString();
		String mobileImei = entity.getImei();
		Users user = usersService.getLoginUsers(userCode, userPwd, mobileImei);
		
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("appLogin");
		if(user ==null)
		{	
			r.setCode("0");
			r.setMessage("用户名或密码错误！");
			r.setDto(null);
		}
		else
		{
			session.setAttribute("users", user);
			r.setCode("1");
			r.setMessage("登录成功！");
			r.setDto(user);
		}
		return r;
	}
	/**
	 * 修改密码
	 */
	@RequestMapping("/updateUserPwd.htm")
	public Object updateUserPwd(HttpSession session, @RequestBody AppRequestEntity entity) {
		Users user = (Users) session.getAttribute("users");
		String newUserPwd = entity.getDto().getOrDefault("newUserPwd", "").toString();
		String oldUserPwd = entity.getDto().getOrDefault("oldUserPwd", "").toString();
		
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("updateUserPwd");
		r.setDto(null);
		
		if(user==null){
			r.setCode("-1");
			r.setMessage("用户未登录或不存在！");
			return r;
		}
		if("".equals(newUserPwd)){
			r.setCode("2");
			r.setMessage("修改密码不能为空！");
	    	return r;
		}
		if("".equals(oldUserPwd)){
			r.setCode("3");
			r.setMessage("原密码不能为空！");
	    	return r;
		}
		
		r = usersService.userUpdateForuserPwd(session ,newUserPwd,oldUserPwd,user);
		r.setTranCode("updateUserPwd");
		return r;
	}
	
	
}
