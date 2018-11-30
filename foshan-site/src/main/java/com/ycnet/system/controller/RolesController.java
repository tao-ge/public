package com.ycnet.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.frms.bean.PageRole;
import com.ycnet.frms.bean.Pages;
import com.ycnet.frms.bean.Roles;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.PageRoleService;
import com.ycnet.frms.service.PagesService;
import com.ycnet.frms.service.RolesService;
import com.ycnet.frms.service.UserRoleService;

@Controller
public class RolesController {

	@Resource(name="rolesService")
	private RolesService rolesService;
	@Resource(name="pagesService")
	private PagesService pagesService;
	@Resource(name="pageRoleService")
	private PageRoleService pageRoleService;
	@Resource(name="userRoleService")
	private UserRoleService userRoleService;
	@Resource(name="organizitionService")
	private OrganizitionService organizitionService;
	
	private static final Logger LOG = LoggerFactory.getLogger(RolesController.class);
	
	@RequestMapping({"/rolesList.htm"})
	public String rolesList(HttpServletRequest request ,HttpSession session ,Model model)
	{
		queryRoleList(request,session,model);
		return "system/rolesList";
	}
	
	private void queryRoleList(HttpServletRequest request, HttpSession session,Model model){
		Map<String,String> rauMap = new HashMap<String,String>();
		Users user = (Users) session.getAttribute("platformUser");
		List<Roles> list = rolesService.queryRolesByOrgId(user.getOrgId());//根据orgid查询角色
		for (int i = 0; i < list.size(); i++) {
			String roleName = list.get(i).getRoleName();
			//List<Roles> rauList = rolesService.queryRolesAndUser(roleName);
			List<Roles> rauList = rolesService.queryRolesAndUser(roleName);
			if(rauList.size()>0){
				rauMap.put(roleName, "no");
				model.addAttribute("rauMap", rauMap);
			}else{
				rauMap.put(roleName, "yes");
				model.addAttribute("rauMap", rauMap);
			}
		}
		model.addAttribute("rolesList", list);
		
	}
	
	@RequestMapping("/rolesAdd.htm")
	public String rolesAdd(HttpServletRequest request ,HttpSession session ,Model model){
		Users user = (Users) session.getAttribute("platformUser");
		Long roleId = userRoleService.selectByUserId(user.getUserId()).getRoleId();
		String userCode = user.getUserCode();
		Pages pages = new Pages();
		pages.setPageRank(1l);
		List<Pages> fristList = pagesService.getPageList(pages);
		//管理员权限
		if(rolesService.selectByPrimaryKey(roleId).getOrgId()==0&&roleId!=40) {
			for(Pages page : fristList){
				pages.setPageRank(2l);
				pages.setParentPageId(page.getPageId());
				List<Pages> childPages = pagesService.getPageListByOrgId(pages,user.getOrgId());
				page.setChildPages(childPages);
			}
		//普通用户权限
		}else if(roleId!=40){
			for (Pages page : fristList) {
				pages.setPageRank(2l);
				pages.setParentPageId(page.getPageId());
				List<Pages> childPages = pagesService.getPageListByRole(pages,roleId);
				page.setChildPages(childPages);
			}
			//超级管理员权限
		}else {
			for(Pages page : fristList){
				pages.setPageRank(2l);
				pages.setParentPageId(page.getPageId());
				List<Pages> childPages = pagesService.getPageList(pages);
				page.setChildPages(childPages);
			}
		}
		
		model.addAttribute("fristPages", fristList);
		return "system/rolesAdd";
	}
	
	@ResponseBody
	@RequestMapping("/checkRoleName.htm")
	public Object checkRoleName(Model model,HttpSession session,String roleName){
		Users user = (Users) session.getAttribute("platformUser");
		List<Roles> roleList = rolesService.checkRoleName(roleName,user);
		if(roleList.size()>0){
			return "no";
		}
		return "yes";
	}
	
	
	@RequestMapping("/rolesInsert.htm")
	public String rolesInsert(HttpServletRequest request ,HttpSession session , Roles roles,Model model){
		
		String result = "";
		roles.setOrgId(((Users) session.getAttribute("platformUser")).getOrgId());
		int num =rolesService.insert(roles);
		// 获得角色ID
		long roleId = rolesService.getRoleId();
		
		PageRole pageRole = new PageRole();
		pageRole.setRoleId(roleId);
		// 功能角色关系表
		if(roles.getChosePages() != null){
			for(String pageId : roles.getChosePages()){
				pageRole.setPageId(Long.valueOf(pageId));
				pageRoleService.insert(pageRole);
			}
		}
		
		if(roles.getDirectFlg() != null && "1".equals(roles.getDirectFlg())){
			
			Pages pages = new Pages();
			pages.setPageRank(1l);
			List<Pages> fristList = pagesService.getPageList(pages);
			for(Pages page : fristList){
				pages.setPageRank(2l);
				pages.setParentPageId(page.getPageId());
				List<Pages> childPages = pagesService.getPageList(pages);
				page.setChildPages(childPages);
			}
			model.addAttribute("fristPages", fristList);
			
			result = "system/rolesAdd";
		} else {
//			List<Roles> list = rolesService.queryRolesList();
//			model.addAttribute("rolesList", list);
//			result = "system/rolesList";
			queryRoleList(request,session,model);
			return "system/rolesList";
		}
		
		return result;
	}
	
	@RequestMapping("/rolesUp.htm")
	public String rolesUp(HttpServletRequest request ,HttpSession session , Roles roles,Model model){
		Users user = (Users) session.getAttribute("platformUser");
		String exchangedRoleId = request.getParameter("roleId");
		Long roleId = userRoleService.selectByUserId(user.getUserId()).getRoleId();
		Roles result = rolesService.selectByPrimaryKey(roles.getRoleId());
		model.addAttribute("roles", result);
		
		// 获得关联功能列表
		Pages pages = new Pages();
		pages.setPageRank(1l);
		List<Pages> fristList = pagesService.getPageList(pages);
		if(rolesService.selectByPrimaryKey(roleId).getOrgId()==0&&roleId!=40) {
			for(Pages page : fristList){
				pages.setPageRank(2l);
				pages.setParentPageId(page.getPageId());
				List<Pages> childPages = pagesService.getPageListByOrgId(pages,user.getOrgId());
				List<Pages> rolechildPages = pagesService.getPageListByRole(pages,new Long(exchangedRoleId));
				for (Pages pages2 : childPages) {
					if(rolechildPages.contains(pages2)) {
						pages2.setChooseFlag("true");
					}
				}
				page.setChildPages(childPages);
			}
			
		}else if(roleId!=40){
			for (Pages page : fristList) {
				pages.setPageRank(2l);
				pages.setParentPageId(page.getPageId());
				List<Pages> childPages = pagesService.getPageListByRole(pages,roleId);
				List<Pages> rolechildPages = pagesService.getPageListByRole(pages,new Long(exchangedRoleId));
				for (Pages pages2 : childPages) {
					if(rolechildPages.contains(pages2)) {
						pages2.setChooseFlag("true");
					}
				}
				page.setChildPages(childPages);
			}
		}else {
			for(Pages page : fristList){
				pages.setPageRank(2l);
				pages.setParentPageId(page.getPageId());
				List<Pages> rolechildPages = pagesService.getPageListByRole(pages,new Long(exchangedRoleId));
				List<Pages> childPages = pagesService.getPageList(pages);
				for (Pages pages2 : childPages) {
					if(rolechildPages.contains(pages2)) {
						pages2.setChooseFlag("true");
					}
				}
				page.setChildPages(childPages);
				
			}
		}
		
		model.addAttribute("fristPages", fristList);
		
		return "system/rolesUpdate";
	}
	
	@RequestMapping("/rolesUpdate.htm")
	public String rolesUpdate(HttpServletRequest request ,HttpSession session , Roles roles,Model model){
		
		int num = rolesService.updateByPrimaryKeySelective(roles);
		
		// 删除功能角色关系表数据
		int deleteNum = pageRoleService.deleteByRoleId(roles.getRoleId());
		
		PageRole pageRole = new PageRole();
		pageRole.setRoleId(roles.getRoleId());
		// 功能角色关系表
		if(roles.getChosePages() != null){
			for(String pageId : roles.getChosePages()){
				pageRole.setPageId(Long.valueOf(pageId));
				int insertNum = pageRoleService.insert(pageRole);
			}
		}
		
//		List<Roles> list = rolesService.queryRolesList();
//		model.addAttribute("rolesList", list);
		queryRoleList(request,session,model);
		return "system/rolesList";
	}
	
	@RequestMapping("/rolesDelete.htm")
	public String rolesDelete(HttpServletRequest request ,HttpSession session , Roles roles,Model model){
		// 删除角色表数据
		int num = rolesService.deleteByPrimaryKey(roles.getRoleId());
		// 删除功能角色关系表数据
		int prNum = pageRoleService.deleteByRoleId(roles.getRoleId());
		// 删除用户角色关系表数据
		int urNum = userRoleService.deleteByRoleId(roles.getRoleId());
		
//		List<Roles> list = rolesService.queryRolesList();
//		model.addAttribute("rolesList", list);
		queryRoleList(request,session,model);
		return "system/rolesList";
	}
}
