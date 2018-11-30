package com.ycnet.site.controller;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.background.SingleColorBackgroundFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.AbstractCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.core.log.FrmsLog;
import com.ycnet.core.util.IPUtil;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.UsersService;
import com.ycnet.mobile.util.Result;

@Controller
public class LoginController {
	
	@Resource(name="usersService")
	private UsersService usersService;

	private static  String ip40 = "120.77.183.40";
	
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping({"/login.htm"})
	public String main(HttpServletRequest request,HttpSession session,Model model)
	{
		String ip = IPUtil.getV4IP();
		if (ip.equals(ip40)) {
			model.addAttribute("ipValue", 0);//40
		}else {
			model.addAttribute("ipValue", 1);//其他
		}
		return "login";
	}
	
	
	@RequestMapping("/userLogin.htm")
	@FrmsLog("用户登录")
	public Object userLogin(HttpServletRequest request ,HttpSession session ,Users users ,String validCode,Model model)
	{
		
		String ip = IPUtil.getV4IP();
		if (ip.equals(ip40)) {
			model.addAttribute("ipValue", 0);//40
		}else {
			model.addAttribute("ipValue", 1);//其他
		}
		String result = "";
		Users user = (Users) session.getAttribute("platformUser");//切换账号
		if (user==null) {
			if (users.getUserCode()!=null && users.getUserPwd() !=null && !"".equals(users.getUserCode())&& !"".equals(users.getUserPwd())) {
				
				String patchca = (String)session.getAttribute("PATCHCA");
				if(patchca!=null&&!patchca.equals(validCode)){
					request.setAttribute("result", "验证码不正确！");
					result = "login";
					return result;
				}
				Users u = usersService.getLoginUsers(users.getUserCode(), users.getUserPwd(), users.getMobileImei());
				//session.setAttribute("platformUser", u);
				if(u ==null)
				{
					request.setAttribute("result", "用户名或密码错误！");
					result = "login";
				}
				else
				{
					session.setAttribute("platformUser", u);
					String pmName = IPUtil.getRemoteHost40Name();
					model.addAttribute("pmName", pmName);
					result = "main";
				}
			}else {
				result = "login";
			}
		}else {
			session.setAttribute("platformUser", user);
			String pmName = IPUtil.getRemoteHost40Name();
			model.addAttribute("pmName", pmName);
			result = "main";
		}
		return result;
	}
	

	@RequestMapping("/userLogout.htm")
	public Object userLogout(HttpSession session,Model model,HttpServletRequest request)
	{
		String ip = IPUtil.getV4IP();
		if (ip.equals(ip40)) {
			model.addAttribute("ipValue", 0);//40
		}else {
			model.addAttribute("ipValue", 1);//其他
		}
		//session.removeAttribute("user");
		session.removeAttribute("users");
		session.removeAttribute("platformUser");
		session.invalidate();
		return "login";
	}
	
	@RequestMapping("/patchca.htm")
	public void patchca(HttpServletResponse response,HttpSession session) throws IOException 
	{
		CaptchaService cs = new CaptchaService();
		response.setContentType("image/png");
		response.setHeader("cache", "no-cache");
		OutputStream os = response.getOutputStream();
		String patchca = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
		session.setAttribute("PATCHCA", patchca);
		os.flush();
		os.close();
		cs = null;
	}

	private class CaptchaService extends AbstractCaptchaService{
		public CaptchaService(){
			String[] fontOption = { "Verdana", "Tahoma" };
			this.wordFactory = new WordFactory();
			this.fontFactory = new RandomFontFactory(31,fontOption);
			this.textRenderer = new BestFitTextRenderer();
			this.backgroundFactory = new SingleColorBackgroundFactory();
			this.colorFactory = new SingleColorFactory(new Color(25,60,170));
			this.filterFactory = new CurvesRippleFilterFactory(colorFactory);
			this.width = 90;
			this.height=30;
			
		}
	}
	private class WordFactory extends RandomWordFactory{
		public WordFactory(){
			this.characters="1234567890";
			this.maxLength=4;
			this.minLength=4;
		}
	}
	
	/**
	 * 联系我们
	* @Title: conectionUs 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param session
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月3日 上午11:20:40 
	* @version V1.0
	 */
	@RequestMapping("/conectionUs.htm")
	public String conectionUs(HttpSession session,HttpServletRequest request,Model model) {
		String ip = IPUtil.getV4IP();
		if (ip.equals(ip40)) {
			model.addAttribute("ipValue", 0);//40
		}else {
			model.addAttribute("ipValue", 1);//其他
		}
		return "conectionUs";
	}
	
	/**
	 * 关于我们
	* @Title: aboutUs 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param session
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月3日 上午11:45:27 
	* @version V1.0
	 */
	@RequestMapping("/aboutUs.htm")
	public String aboutUs(HttpSession session,HttpServletRequest request,Model model) {
		String ip = IPUtil.getV4IP();
		if (ip.equals(ip40)) {
			model.addAttribute("ipValue", 0);//40
		}else {
			model.addAttribute("ipValue", 1);//其他
		}
		return "aboutUs";
	}
	
	/**
	 * 切换账号
	* @Title: switchAccountLogin 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param session
	* @param @param users
	* @param @param validCode
	* @param @param model
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月4日 下午3:23:12 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/switchAccountLogin.htm")
	public Object switchAccountLogin(HttpServletRequest request ,HttpSession session ,Users users,Model model)
	{
		String ip = IPUtil.getV4IP();
		if (ip.equals(ip40)) {
			model.addAttribute("ipValue", 0);//40
		}else {
			model.addAttribute("ipValue", 1);//其他
		}
		Result r = Result.get();
		Map map = new HashMap();
		map.put("u", users);
		if (users.getUserCode()!=null && users.getUserPwd() !=null && !"".equals(users.getUserCode())&& !"".equals(users.getUserPwd())) {
			
			Users u = usersService.getLoginUsers(users.getUserCode(), users.getUserPwd(), users.getMobileImei());
			if(u ==null){
				r.setR(0);
				r.setR_content("用户名或者密码不正确!");
			}else{
				session.setAttribute("platformUser", u);
				r.setR(1);
				r.setR_content("切换成功!");
			}
			map.put("r", r);
		}
		return r;
	}
}
