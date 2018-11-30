package com.ycnet.system.controller;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.core.MD5Utils;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.ActIdUser;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Roles;
import com.ycnet.frms.bean.UserRole;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderOrganizition;
import com.ycnet.frms.bean.WorkorderRoles;
import com.ycnet.frms.bean.WorkorderUserRole;
import com.ycnet.frms.bean.WorkorderUsers;
import com.ycnet.frms.service.ActIdUserService;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.RolesService;
import com.ycnet.frms.service.UserRoleService;
import com.ycnet.frms.service.UsersService;
import com.ycnet.frms.service.WorkorderOrganizitionService;
import com.ycnet.frms.service.WorkorderRolesService;
import com.ycnet.frms.service.WorkorderUserRoleService;
import com.ycnet.frms.service.WorkorderUsersService;

@Controller
public class UsersContoller {
	
	@Resource(name="usersService")
	private UsersService usersService;
	
	@Resource(name="organizitionService")
	private OrganizitionService organizitionService;
	
	@Resource(name="rolesService")
	private RolesService rolesService;
	
	@Resource(name="userRoleService")
	private UserRoleService userRoleService;
	
	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	
	@Resource(name="workorderUsersService")
	private WorkorderUsersService workorderUsersService;
	
	@Resource(name="workorderOrganizitionService")
	private WorkorderOrganizitionService workorderOrganizitionService;
	
	@Resource(name="workorderRolesService")
	private WorkorderRolesService workorderRolesService;
	
	@Resource(name="workorderUserRoleService")
	private WorkorderUserRoleService workorderUserRoleService;
	
	@Resource(name="actIdUserService")
	private ActIdUserService actIdUserService;
	
	private static final Logger LOG = LoggerFactory.getLogger(UsersContoller.class);
	
	@RequestMapping("/userList.htm")
	public String userList(HttpServletRequest request ,HttpSession session , Users users,PageBean pb ,Model model){
		//用户登陆不能删除,把用户信息传到页面
		Users user = (Users) session.getAttribute("platformUser");
		users.setOrgId(user.getOrgId());
		model.addAttribute("user", user);
		model.addAttribute("userName",users.getUserName());//fl修改,查询框回显
		users.setOrgId(user.getOrgId());//设置orgId
		users.setUserId(user.getUserId());
		PageBean usersPb = usersService.queryUserList(users,pb);
		model.addAttribute("pb",usersPb);
		model.addAttribute("role",rolesService.selectByPrimaryKey(userRoleService.selectByUserId(user.getUserId()).getRoleId()));
		return "system/userList";
	}
	
	@RequestMapping("/userAdd.htm")
	public String userAdd(HttpServletRequest request ,HttpSession session , Users users,Model model){
		Users user = (Users) session.getAttribute("platformUser");
		// 获得机构列表
		List<Organizition> oList = organizitionService.getOrganizitionList(new Organizition());
		model.addAttribute("oList", oList);
		// 获得角色列表 2/11改添加组织机构查询
		//List<Roles> rList = rolesService.queryRoles();
		List<Roles> rList = rolesService.queryRolesByOrgId(user.getOrgId());
		model.addAttribute("rList", rList);
		model.addAttribute("user", user);
		model.addAttribute("orgDevPlatformList", organizitionService.getorgPlatformList(user.getOrgId()));
		//model.addAttribute("devPlatformList", basecodeService.getplatformList());
		model.addAttribute("role",rolesService.selectByPrimaryKey(userRoleService.selectByUserId(user.getUserId()).getRoleId()));
		return "system/userAdd";
	}
	
	@RequestMapping("/userInsert.htm")
	public String userInsert(HttpServletRequest request ,HttpSession session , Users users,PageBean pb ,Model model){
		Users user = (Users) session.getAttribute("platformUser");
		model.addAttribute("result", "");
		model.addAttribute("users", new Users());
		model.addAttribute("role",rolesService.selectByPrimaryKey(userRoleService.selectByUserId(user.getUserId()).getRoleId()));
		String result = "";
		
		if (users.getUserPwd() !=null && !"".equals(users.getUserPwd())) {
			users.setUserPwd(MD5Utils.md5Password(users.getUserPwd()));//fl修改
		}
		if(user.getOrgId()!=0) {
			users.setOrgId(user.getOrgId());
		}
		usersService.insert(users);
		//如果与光调项目同步
		if(users.getIsSynchOpss()!=null && "1".equals(users.getIsSynchOpss())) {
			String paidan = "add_paidan,";
			
			//查询流程用户是否存在
			ActIdUser actIdUser = actIdUserService.selectByPrimaryKey(users.getUserCode());
			if(actIdUser == null) {
				//添加流程用户
				ActIdUser actUser = new ActIdUser();
				actUser.setId(users.getUserCode());
				actUser.setFirst(users.getUserCode());
				actIdUserService.insertSelective(actUser);
			}
			
			//添加光调用户
			WorkorderUsers wUser = new WorkorderUsers();
			wUser.setUserCode(users.getUserCode());
			wUser.setUserName(users.getUserName());
			wUser.setUserPwd(users.getUserPwd());
			wUser.setSex(users.getSex());
			wUser.setMobilePhone(users.getMobilePhone());
			wUser.setContactPhone(users.getContactPhone());
			wUser.setOrgId(users.getOrgId());
			wUser.setRemark(users.getRemark());
			wUser.setFlag(users.getFlag());
			wUser.setMobileImei(users.getMobileImei());
			workorderUsersService.insertSelective(wUser);
			
			//初始化用户角色关联
			WorkorderUserRole wUserRole = new WorkorderUserRole();
			wUserRole.setUserId(wUser.getUserId());
			
			WorkorderOrganizition wOrg = workorderOrganizitionService.selectByPrimaryKey(users.getOrgId());
			Organizition org = organizitionService.selectByPrimaryKey(users.getOrgId());
			//光调项目是否存在该组织机构
			if(wOrg!=null) {//如果光调项目存在该组织机构
				WorkorderRoles wRole = workorderRolesService.queryPaidanByOrgId(users.getOrgId(),"add_paidan");
				if(wRole != null) {//如果该组织机构存在施工派单角色，直接让添加用户与该角色关联
					//设置用户角色关的联角色ID
					wUserRole.setRoleId(wRole.getRoleId());
				}else {//如果不存在，创建施工派单角色，然后创建用户与角色的关联关系
					//添加组织机构施工派单角色
					WorkorderRoles wRoleNew = new WorkorderRoles();
					wRoleNew.setRoleName("施工派单");
					wRoleNew.setRemark("施工派单");
					wRoleNew.setOrgId(users.getOrgId());
					wRoleNew.setActRoles(paidan);
					workorderRolesService.insertSelective(wRoleNew);
					//设置用户角色关的联角色ID
					wUserRole.setRoleId(wRoleNew.getRoleId());
				}
			}else if(wOrg == null){//如果光调项目不存在该组织机构，把该组织机构同步到光调项目
				//添加光调项目组织机构
				WorkorderOrganizition wOrgNew = new WorkorderOrganizition();
				wOrgNew.setOrgId(org.getOrgId());
				wOrgNew.setOrgName(org.getOrgName());
				wOrgNew.setOrgAddress(org.getOrgAddress());
				wOrgNew.setOrders(org.getOrders());
				wOrgNew.setRemark(org.getRemark());
				wOrgNew.setCode1(org.getCode1());
				wOrgNew.setCode2(org.getCode2());
				wOrgNew.setContactPhone(org.getContactPhone());
				wOrgNew.setActRoles(paidan);
				workorderOrganizitionService.insertSelective(wOrgNew);
				
				//添加组织机构施工派单角色
				WorkorderRoles wRoleNew = new WorkorderRoles();
				wRoleNew.setRoleName("施工派单");
				wRoleNew.setRemark("施工派单");
				wRoleNew.setOrgId(org.getOrgId());
				wRoleNew.setActRoles(paidan);
				workorderRolesService.insertSelective(wRoleNew);
				//设置用户角色关的联角色ID
				wUserRole.setRoleId(wRoleNew.getRoleId());
			}
			workorderUserRoleService.insertSelective(wUserRole);//添加用户角色关联表
		}
		
		// 插入用户角色关系表
		if(users.getRoleId() != null){
			UserRole ur = new UserRole();
			ur.setUserId(users.getUserId());
			ur.setRoleId(users.getRoleId());
			int urNum = userRoleService.insert(ur);
		}
		
		if(users.getDirectFlg() != null && "1".equals(users.getDirectFlg())){
			// 获得机构列表
			//List<Organizition> oList = organizitionService.getOrganizitionList(new Organizition());
			//model.addAttribute("oList", oList);
			// 获得角色列表
			//List<Roles> rList = rolesService.queryRolesList();
			List<Roles> rList = rolesService.queryRolesByOrgId(user.getOrgId());
			model.addAttribute("rList", rList);
			
			result = "system/userAdd";
		} else {
			Users user1 = new Users();
			user1.setOrgId(user.getOrgId());
			user1.setUserId(user.getUserId());
			model.addAttribute("pb",usersService.queryUserList(user1,pb));
			result = "system/userList";
		}
		model.addAttribute("user", user);
		return result;
	}
	
	@RequestMapping("/userUp.htm")
	public String userUp(HttpServletRequest request ,HttpSession session , Users users,Model model){
		Users result = usersService.selectByPrimaryKey(users.getUserId());
		//查询该用户是否为管理员角色
		int isAdmin = rolesService.queryIsAdmin(users.getUserId());
		model.addAttribute("isAdmin",isAdmin);
		//model.addAttribute("devPlatformList", basecodeService.getplatformList());//平台列表
		
		// 获得机构列表
		//List<Organizition> oList = organizitionService.getOrganizitionList(new Organizition());
		//model.addAttribute("oList", oList);
		// 获得角色列表
//		List<Roles> rList = rolesService.queryRolesList();
//		model.addAttribute("rList", rList);
//		List<Roles> rList=rolesService.queryRoles();
		List<Organizition> oList = organizitionService.getOrganizitionList(new Organizition());
		model.addAttribute("oList", oList);
		Users user1 = (Users) session.getAttribute("platformUser");
		if(Objects.equals(null, result.getOrgId())) {
			model.addAttribute("orgName","");
		}else {
			model.addAttribute("orgName",organizitionService.selectById(result.getOrgId()).getOrgName());
		}
		
		model.addAttribute("orgDevPlatformList", organizitionService.getorgPlatformList(user1.getOrgId()));//根据orgid查平台列表
		List<Roles> rList = rolesService.queryRolesByOrgId(user1.getOrgId());
		model.addAttribute("rList", rList);
		// 获得用户角色信息
		UserRole ur = userRoleService.selectByUserId(users.getUserId());
		
		Roles role = rolesService.selectByPrimaryKey(ur.getRoleId());
		model.addAttribute("role", role);
		if(ur != null){
			result.setRoleId(ur.getRoleId());
		}
		
		model.addAttribute("users", result);
		return "system/userUpdate";
	}
	
	@RequestMapping("/userUpdate.htm")
	public String userUpdate(HttpServletRequest request ,HttpSession session , Users users ,PageBean pb ,Model model){
		Users user1 = (Users) session.getAttribute("platformUser");
		int num = usersService.updateByPrimaryKeySelective(users);

		//如果用户为同步光调项目用户
		if(users.getIsSynchOpss()!=null && "1".equals(users.getIsSynchOpss())) {
			//如果无效,等于用户,同时删除流程用户
			if("0".equals(users.getFlag())) {
				//删除光调流程用户
				actIdUserService.deleteByPrimaryKey(users.getUserCode());
			}else {//如果有效,查看书否存在;不存在则添加
				//查询流程用户是否存在
				ActIdUser actIdUser = actIdUserService.selectByPrimaryKey(users.getUserCode());
				if(actIdUser == null) {
					//添加流程用户
					ActIdUser actUser = new ActIdUser();
					actUser.setId(users.getUserCode());
					actUser.setFirst(users.getUserCode());
					actIdUserService.insertSelective(actUser);
				}
			}
			
			
			//修改光调用户
			WorkorderUsers wUser = workorderUsersService.queryByUserCode(users.getUserCode());
			wUser.setUserName(users.getUserName());
			wUser.setSex(users.getSex());
			wUser.setMobilePhone(users.getMobilePhone());
			wUser.setContactPhone(users.getContactPhone());
			wUser.setRemark(users.getRemark());
			wUser.setFlag(users.getFlag());
			wUser.setMobileImei(users.getMobileImei());
			workorderUsersService.updateByPrimaryKeySelective(wUser);
		}
		
		model.addAttribute("role",rolesService.selectByPrimaryKey(userRoleService.selectByUserId(user1.getUserId()).getRoleId()));
		// 用户角色关系处理
		UserRole ur = userRoleService.selectByUserId(users.getUserId());
		if(ur != null){
			if(users.getRoleId() != null){
				ur.setRoleId(users.getRoleId());
				userRoleService.updateByPrimaryKeySelective(ur);
			} 
//			else {
//				userRoleService.deleteByPrimaryKey(ur.getUserRoleId());
//			}
		} else {
			if(users.getRoleId() != null){
				UserRole param = new UserRole();
				param.setRoleId(users.getRoleId());
				param.setUserId(users.getUserId());
				userRoleService.insert(param);
			}
		}
		
		Users user = new Users();
		user.setOrgId(((Users) session.getAttribute("platformUser")).getOrgId());
		user.setUserId(((Users) session.getAttribute("platformUser")).getUserId());
		model.addAttribute("pb",usersService.queryUserList(user,pb));
		model.addAttribute("user", (Users) session.getAttribute("platformUser"));
		return "system/userList";
	}
	
	@RequestMapping("/userDelete.htm")
	public String userDelete(HttpServletRequest request ,HttpSession session , Users users,PageBean pb ,Model model){
		Users user = (Users) session.getAttribute("platformUser");
		Users uById = usersService.selectByPrimaryKey(users.getUserId());
		if(users != null){
			users.setFlag("0");
		}
		int num = usersService.updateByPrimaryKeySelective(users);

		if(uById != null && uById.getIsSynchOpss() != null && "1".equals(uById.getIsSynchOpss())) {
			//删除光调流程用户
			actIdUserService.deleteByPrimaryKey(uById.getUserCode());
			
			//修改光调项目用户为无效
			WorkorderUsers wUser = workorderUsersService.queryByUserCode(uById.getUserCode());
			WorkorderUsers workOrderUser = new WorkorderUsers();
			workOrderUser.setUserId(wUser.getUserId());
			workOrderUser.setFlag("0");
			workorderUsersService.updateByPrimaryKeySelective(workOrderUser);
		}
		
		users.setOrgId(user.getOrgId());
		users.setUserId(user.getUserId());
		model.addAttribute("pb",usersService.queryUserList(users,pb));
		model.addAttribute("user", (Users) session.getAttribute("platformUser"));
		model.addAttribute("role",rolesService.selectByPrimaryKey(userRoleService.selectByUserId(user.getUserId()).getRoleId()));
		return "system/userList";
	}
	
	/**
	 * 
	* @Title: userNameVerify 
	* @Description: TODO(添加用户，登录名重复验证) 
	* @param @param session
	* @param @param userCode
	* @param @param model
	* @param @return    入参
	* @return Object    0 重复  1可用
	* @author （魏俊康） 
	* @throws
	* @date 2017年8月25日 上午10:19:27 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/userNameVerify.htm")
	public Object userNameVerify(HttpSession session , String userCode,Model model){
		
		int uList = usersService.selectByUserCode(userCode);

		return uList;
	}
	
}
