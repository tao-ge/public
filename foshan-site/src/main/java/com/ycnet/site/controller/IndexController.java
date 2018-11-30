package com.ycnet.site.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.core.GetHostIp;
import com.ycnet.core.MD5Utils;
import com.ycnet.core.util.IPUtil;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.MobileSwitch;
import com.ycnet.frms.bean.Pages;
import com.ycnet.frms.bean.UrlImg;
import com.ycnet.frms.bean.UserRole;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderUsers;
import com.ycnet.frms.mapper.UsersMapper;
import com.ycnet.frms.service.DeviceAlarmService;
import com.ycnet.frms.service.MobileSwitchService;
import com.ycnet.frms.service.PageRoleService;
import com.ycnet.frms.service.PagesService;
import com.ycnet.frms.service.UrlImgService;
import com.ycnet.frms.service.UserRoleService;
import com.ycnet.frms.service.UsersService;
import com.ycnet.frms.service.WorkorderUsersService;
import com.ycnet.frms.vo.DeviceAlarmConditionBean;
import com.ycnet.mobile.util.Result;

@Controller
public class IndexController {
	@Resource(name="usersService")
	private UsersService usersService;
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	@Resource(name="userRoleService")
	private UserRoleService userRoleService;
	@Resource(name="pageRoleService")
	private PageRoleService pageRoleService;
	@Resource(name="pagesService")
	private PagesService pagesService;

	@Resource(name="mobileSwitchService")
	private MobileSwitchService mobileSwitchService;
	
	@Resource(name="deviceAlarmService")
	private DeviceAlarmService deviceAlarmService;
	
	@Resource(name="urlImgService")
	private UrlImgService urlImgService;
	
	@Resource(name="workorderUsersService")
	private WorkorderUsersService workorderUsersService;
	
	private static  String ip40 = "120.77.183.40";
	
	@RequestMapping({"/main.htm"})
	public String main(HttpServletRequest request,HttpSession session,Model model)
	{
		String pmName = IPUtil.getRemoteHost40Name();
		model.addAttribute("pmName", pmName);
		return "main";
	}
	@Autowired
	public static Map<String,String> loginMap=null;
	@RequestMapping("/left.htm")
	public String left(HttpServletRequest request,HttpSession session ,Model model)
	{	
		// 获得登陆用户
		Users u = (Users) session.getAttribute("platformUser");
		// 根据用户ID获得用户角色关系表信息
		UserRole ur = userRoleService.selectByUserId(u.getUserId());
		// 根据取得的角色信息获得角色功能关系表信息
		
//		loginMap=new HashMap<>(); 
//		String sessionId=request.getSession().getId();
//		loginMap.put(u.getUserCode(), sessionId);
		
		List<Long> pidList = null;
		List<Pages> parentList = new ArrayList<Pages>();// 获得父节点
		if(ur != null){
			pidList = pageRoleService.selectByRoleId(ur.getRoleId());
			parentList = pagesService.selectParentPagesByRoleId(ur.getRoleId());
		}
	    // 获得系统功能信息
		// 获得子节点
		List<Pages> pagesList = new ArrayList<Pages>();
		if(pidList != null && pidList.size() > 0){
			for(Long pageId : pidList){
				Pages pages = pagesService.selectByPrimaryKey(pageId);
				pagesList.add(pages);
			}
		}
		
		for(Pages pPage : parentList){
			List<Pages> childList  = new ArrayList<Pages>();
			for(Pages child : pagesList){
				if(pPage.getPageId().equals(child.getParentPageId())){
					if("1".equals(child.getFlag())) {
						childList.add(child);
					}
				}
			}
			pPage.setChildPages(childList);
		}
		
		model.addAttribute("parentList", parentList);
		return "left";
	}
	
	@RequestMapping("/top.htm")
	public String top(HttpServletRequest request, HttpSession session,Model model)
	{
		Users usr = (Users)session.getAttribute("platformUser");
		//最新报警信息
		PageBean dasCount=new PageBean();
		dasCount.setPageSize(3);
		DeviceAlarmConditionBean deviceAlarmCon = new DeviceAlarmConditionBean();		
		
		dasCount=deviceAlarmService.queryUntreatedCountByConditionMap(deviceAlarmCon,usr,dasCount);
		
		
		model.addAttribute("dasCount",dasCount);
		
		String ip = IPUtil.getV4IP();
		if (ip.equals(ip40)) {
			model.addAttribute("ipValue", 0);//40
		}else {
			model.addAttribute("ipValue", 1);//其他
		}
		String pmName = IPUtil.getRemoteHost40Name();
		model.addAttribute("pmName", pmName);
		return "top";
	}
	
	/**
	 * 
	* @Title: index 
	* @Description: TODO( 增加图标验证权限) 
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @return    入参
	* @return String    返回类型
	* @author （魏俊康） 
	* @throws
	* @date 2017年8月25日 上午10:22:52 
	* @version V1.0
	 */
	@RequestMapping("/index.htm")
	public String index(HttpServletRequest request, HttpSession session,Model model)
	{
		Users usr = (Users)session.getAttribute("platformUser");
		model.addAttribute("lastLoginTime", usr.getLastLoginTime());
		model.addAttribute("userName", usr.getUserName());
		
		UserRole ur = userRoleService.selectByUserId(usr.getUserId());
		List<Long> pidList = ur!=null?pageRoleService.selectByRoleId(ur.getRoleId()):null;
		String pidString = "";
		if (pidList!=null && pidList.size()>0) {
			for(int i=0;i<pidList.size();i++) {
				pidString += pidList.get(i)+",";
			}		
		}
		model.addAttribute("pidString",pidString);	
		//最新开锁记录
		PageBean msPb=new PageBean();
		msPb.setPageSize(3);
		MobileSwitch ms = new MobileSwitch();
		
		msPb =mobileSwitchService.queryNewRecordListByMap(ms,usr,msPb);
		
		//最新报警信息
		PageBean dasPb=new PageBean();
		dasPb.setPageSize(3);
		DeviceAlarmConditionBean deviceAlarmCon = new DeviceAlarmConditionBean();		
		
		dasPb=deviceAlarmService.queryNewRecordByConditionMap(deviceAlarmCon,usr,dasPb);
		
		
		model.addAttribute("dasPb",dasPb);
		model.addAttribute("msPb",msPb);
		
		String pmName = IPUtil.getRemoteHost40Name();
		model.addAttribute("pmName", pmName);
		
		return "index";
	}
	
	@RequestMapping("/footer.htm")
	public String footer(HttpServletRequest request, Model model)
	{
		String pmName = IPUtil.getRemoteHost40Name();
		model.addAttribute("pmName", pmName);
		return "footer";
	}
	
	@RequestMapping({"/changePWD.htm"})
	public String changePassword(HttpServletRequest request,HttpSession session,Model model)
	{
		String pmName = IPUtil.getRemoteHost40Name();
		model.addAttribute("pmName", pmName);
		return "changePWD";
	}
	/**
	 * 
	* @Title: downloadAPP 
	* @Description: APP下载
	* 刘沧海修改2018/3/22 
	* @param @param request
	* @param @param session
	* @param @param model
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年3月22日 上午11:35:41 
	* @version V1.0
	 */
	@RequestMapping({"/downloadAPP.htm"})
	public String downloadAPP(HttpServletRequest request,HttpSession session,Model model)
	{
		
		String ip = GetHostIp.getHostIp();//IP
		String pro = request.getSession().getServletContext().getContextPath();
		String proName = pro.substring(1, pro.length());//项目名称
		List<UrlImg> urlList=urlImgService.queryDownApkUrl(ip,proName);
		
		if(urlList!=null && urlList.size()==2) {
			for(UrlImg u:urlList) {
//				String http="http://";
				if("01".equals(u.getProjectType())) {//普查
					String imgName01="pc"+"_"+ip+"_"+proName;
					model.addAttribute("imgName01", imgName01);
					model.addAttribute("apkName01", u.getApkName());
				}else {//智能锁
					String imgName02="zns"+"_"+ip+"_"+proName;
					model.addAttribute("imgName02", imgName02);
					model.addAttribute("apkName02", u.getApkName());
				}
			}
		}else {
			model.addAttribute("imgName01", "");
			model.addAttribute("apkName01", "");
			model.addAttribute("imgName02", "");
			model.addAttribute("apkName02", "");
		}
		String pmName = IPUtil.getRemoteHost40Name();
		model.addAttribute("pmName", pmName);
		return "downloadAPP";
	}
	
	public static void main(String[] args) {
//		HttpServletRequest request = ServletActionContext.getRequest();  
//		String ss = request.getSession().getServletContext().getRealPath("/");
		String ss="D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\frms-site\\";
		String[] sss = ss.split("\"");
		System.out.println(sss.length);
//		String s1=sss[sss.length+1];
//		System.out.println(s1);
	}
	@RequestMapping("/m/qqqqrequest.htm")
	public  String xiangmu(HttpServletRequest request) {
//		D:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\frms-site\
		 String ss = request.getSession().getServletContext().getContextPath();
		System.out.println(ss);
		String sss = ss.substring(1, ss.length()-1);
		System.out.println(sss);
		return ss;
	}
	
	
	
	
	/**
	 * 
	 * @Title: submitPWD
	 * @Description: 修改密码保存
	 * @param @param session
	 * @param @param oriPassword
	 * @param @param newPassword
	 * @param @return
	 * @param @throws IOException 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY修改 
	 * @throws
	 * @date 2018年7月3日 下午2:23:22
	 * @version V1.0
	 */
	@RequestMapping({"submitPWD.htm"})
	@ResponseBody
	public Object submitPWD(HttpSession session, String oriPassword, String newPassword) throws IOException
	{
		Result r = Result.get();
		Users usr = (Users)session.getAttribute("platformUser");
		if(usr == null) {
			r.putR(0);
			r.putRContent("用户未登录或登录超时");
			return r;
		}
		Users u = usersService.getLoginUsers(usr.getUserCode(), oriPassword, null);
		if(u ==null)
		{
			r.putR(0);
			r.putRContent("原密码密码错误！");
			return r;
		}
		u.setLastLoginTime(new Date());
		u.setUserPwd(MD5Utils.md5Password(newPassword));
		int req = usersMapper.updateByPrimaryKeySelective(u);
		if(req > 0) {
			//如果用户为同步光调项目用户
			if(usr.getIsSynchOpss()!=null && "1".equals(usr.getIsSynchOpss())) {
				//修改光调用户
				WorkorderUsers wUser = workorderUsersService.queryByUserCode(usr.getUserCode());
				wUser.setUserPwd(u.getUserPwd());
				workorderUsersService.updateByPrimaryKeySelective(wUser);
			}
			r.putR(1);
			r.putRContent("修改成功");
		}else {
			r.putR(0);
			r.putRContent("修改失败");
		}
		return r;
	}
	
	/**
	 * 验证原密码是否正确
	* 
	* @Title: IndexController.java 
	* @Description: TODO 
	* @param @param qrPassword
	* @param @param session
	* @param @return
	* @return Object
	* @author fl
	* @date 2018年1月8日 下午3:22:52
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("userPassword.htm")
	public Object yanzhengPassword(String qrPassword,HttpSession session) {
		Users user = (Users)session.getAttribute("platformUser");
		Users uer=usersService.queryqrPassword(user.getUserId());
		if (uer !=null && !"".equals(uer.getUserPwd())) {
			if (MD5Utils.md5Password(qrPassword).equals(uer.getUserPwd())) {
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * 切换账号
	* @Title: switchAccount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月4日 上午11:47:31 
	* @version V1.0
	 */
	@RequestMapping("switchAccount.htm")
	public String switchAccount (HttpServletRequest request,HttpSession session) {
		Users user = (Users) session.getAttribute("platformUser");
		System.out.println(user.getUserCode());
		return "switchAccount";
	}
}
