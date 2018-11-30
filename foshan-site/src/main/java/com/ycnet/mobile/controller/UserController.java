package com.ycnet.mobile.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.log.FrmsLog;
import com.ycnet.core.util.ResultData;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.UsersService;
import com.ycnet.mobile.util.Result;

@RestController
public class UserController {

	@Resource(name="usersService")
	private UsersService usersService;
	
	@RequestMapping("/m/uLogin.htm")
	@FrmsLog("用户登录")
	public Object uLogin(HttpSession session ,Users users)
	{
		Users u = usersService.getLoginUsers(users.getUserCode(), users.getUserPwd(), users.getMobileImei());
		
		Result r = Result.get().putDT(u);
		if(u ==null)
		{
			r.putRContent("用户名或密码错误！");
		}
		else
		{
			session.setAttribute("users", u);
		}
		
		return r;
	}
	
	/**
	 * 修改账户信息
	 * @author YHT
	 * @time   2016年7月14日 上午10:42:48
	 * @param session
	 * @param users
	 * @return
	 */
	@RequestMapping("/m/userUpdate.htm")
	public ResultData userUpdate(HttpSession session ,Users u)
	{
		Users users = (Users) session.getAttribute("users");
		ResultData result = new ResultData();
		try {
			System.out.println(u.getSex());
			if(users ==null){
				result.setR("-1");
				result.setR_content("用户未登录或不存在！");
		    	return result;
			}
			result  = usersService.userUpdate(session ,u);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setR("0");
			result.setR_content("修改密码及账户信息出现异常！");
	    	return result; 
		}
		
		return result;
	}
	
	/**
	 * 修改密码
	 * @author YHT
	 * @time   2016年7月14日 上午10:42:48
	 * @param session
	 * @param users
	 * @return
	 */
	@RequestMapping("/m/userUpdateForuserPwd.htm")
	public ResultData userUpdateForuserPwd(HttpSession session ,Users u,String oldUserPwd)
	{
		Users users = (Users) session.getAttribute("users");
		ResultData result = new ResultData();
		try {
			
			if(users ==null){
				result.setR("-1");
				result.setR_content("用户未登录或不存在！");
		    	return result;
			}
			if(u.getUserPwd() == null){
				result.setR("1");
				result.setR_content("修改密码不能为空！");
		    	return result;
			}
			if(oldUserPwd == null){
				result.setR("3");
				result.setR_content("原密码不能为空！");
		    	return result;
			}
			result  = usersService.userUpdateForuserPwd(session ,u,oldUserPwd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setR("0");
			result.setR_content("修改密码出现异常！");
	    	return result;
		}
		
		return result;
	}
	
	/**
	 * 查询个人信息
	 * @author YHT
	 * @time   2016年7月15日 上午11:28:54
	 * @param session
	 * @return
	 */
	@RequestMapping("/m/userInfo.htm")
	public ResultData userInfo(HttpSession session)
	{
		Users users = (Users) session.getAttribute("users");
		ResultData result = new ResultData();
		try {
			if(users ==null){
				result.setR("-1");
				result.setR_content("用户未登录或不存在！");
		    	return result;
			}
			
			result  = usersService.userInfo(session);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setR("0");
			result.setR_content("查询个人信息出现异常！");
	    	return result;
		}
		
		return result;
	}
	
	/**
	 * 修改密码为MD5加密
	* 
	* @Title: LoginController.java 
	* @Description: TODO 
	* @param @return
	* @return String
	* @author fl
	* @date 2018年1月8日 下午1:01:04
	* @version V1.0
	 */
	@RequestMapping("/m/updateMD5Password.htm")
	public Object updateMD5Password(HttpSession session) {
		Users users = (Users) session.getAttribute("users");
		ResultData r = new ResultData();
		int num =usersService.updateMD5Password();
		try {
			if (num>0) {
				r.setR("1");
				r.setR_content("修改成功");
			}else {
				r.setR("0");
				r.setR_content("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setR("0");
			r.setR_content("操作失败");
		}
		return r;
	}
	
}
