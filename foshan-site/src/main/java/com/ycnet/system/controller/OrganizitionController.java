package com.ycnet.system.controller;

import java.util.Arrays;
import java.util.Date;
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

import com.ycnet.core.FrmsException;
import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Pages;
import com.ycnet.frms.bean.Roles;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.WorkorderOrganizition;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.service.PageRoleService;
import com.ycnet.frms.service.PagesService;
import com.ycnet.frms.service.RolesService;
import com.ycnet.frms.service.UsersService;
import com.ycnet.frms.service.WorkorderOrganizitionService;
import com.ycnet.mobile.util.Result;

@Controller
public class OrganizitionController {

	@Resource(name="organizitionService")
	private OrganizitionService organizitionService;
	
	@Resource(name="areasService")
	private AreasService areasService;
	
	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	
	@Resource(name="rolesService")
	private RolesService rolesService;
	
	@Resource(name="usersService")
	private UsersService usersService;
	
	@Resource(name="pagesService")
	private PagesService pagesService;
	
	@Resource(name="pageRoleService")
	private PageRoleService pageRoleService;
	
	@Resource(name="workorderOrganizitionService")
	private WorkorderOrganizitionService workorderOrganizitionService;
	
	private static final Logger LOG = LoggerFactory.getLogger(OrganizitionController.class);
	
	@RequestMapping({"/organizitionList.htm"})
	public String getOrganizitionList(HttpServletRequest request ,HttpSession session ,Organizition organizition,PageBean pb ,Model model){
		Users user=(Users)session.getAttribute("platformUser");	
		/*model.addAttribute("pb",organizitionService.queryByConditionMap(organizition,pb));*/
		model.addAttribute("pb",organizitionService.queryByConditionMapAndOrgId(organizition,pb,user.getOrgId()));
		model.addAttribute("orgName", organizition.getOrgName());//fl修改  查询回显
		/*model.addAttribute("basecode",basecode);
		if(basecode.getClassCode()!=null)
		model.addAttribute("code", basecode.getClassCode());*/
		model.addAttribute("orgId",user.getOrgId());//fl修改  查询回显
		return "system/organizitionList";
	}
	
	@RequestMapping({"/organizitionAdd.htm"})
	public String organizitionAdd(HttpServletRequest request ,HttpSession session ,Organizition organizition,Model model){
		Users user = (Users) session.getAttribute("platformUser");
		model.addAttribute("devPlatformList", basecodeService.getplatformList());//fl添加
		model.addAttribute("orgId",user.getOrgId());
		//添加角色列表
		List<Roles> rList=rolesService.queryRoles();
		model.addAttribute("rList", rList);
		String userCode = user.getUserCode();
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
		Areas record= new Areas();
		record.setAreaRank("1");
		model.addAttribute("provinceList",areasService.selectByAreaRank(record));
		return "system/organizitionAdd";
	}
	
	@RequestMapping({"/organizitionInsert.htm"})
	public String organizitionInsert(HttpServletRequest request ,HttpSession session ,Organizition organizition,PageBean pb ,Model model){
		
		String result = "";
		model.addAttribute("orgId",((Users)session.getAttribute("platformUser")).getOrgId());
		organizition.setCreateData(new Date());
		
		int num = organizitionService.insert(organizition);

		if(organizition.getIsSynchOpss()!=null && "1".equals(organizition.getIsSynchOpss())) {
			WorkorderOrganizition wOrgNew = new WorkorderOrganizition();
			wOrgNew.setOrgId(organizition.getOrgId());
			wOrgNew.setOrgName(organizition.getOrgName());
			wOrgNew.setOrgAddress(organizition.getOrgAddress());
			wOrgNew.setOrders(organizition.getOrders());
			wOrgNew.setRemark(organizition.getRemark());
			wOrgNew.setCode1(organizition.getCode1());
			wOrgNew.setCode2(organizition.getCode2());
			wOrgNew.setContactPhone(organizition.getContactPhone());
			wOrgNew.setActRoles("add_paidan,");
			workorderOrganizitionService.insertSelective(wOrgNew);
		}
		
//		if(organizition.getDirectFlg() != null && "1".equals(organizition.getDirectFlg())){
//			result = "system/organizitionAdd";
//		} else {
//			model.addAttribute("pb",organizitionService.queryByConditionMap(new Organizition(),pb));
//			result = "system/organizitionList";
//		}
		
		return "system/organizitionAdd";
	}
	
	@RequestMapping({"/organizitionUp.htm"})
	public String organizitionUp(HttpServletRequest request ,HttpSession session ,Organizition organizition,Model model){
		//查询该用户是否为管理员角色
		int isAdmin = rolesService.queryIsAdmin(((Users)session.getAttribute("platformUser")).getUserId());
		model.addAttribute("isAdmin",isAdmin);
		model.addAttribute("user",(Users)session.getAttribute("platformUser"));
		
		model.addAttribute("orgId",((Users)session.getAttribute("platformUser")).getOrgId());
		Organizition result = organizitionService.selectByPrimaryKey(organizition.getOrgId());
		String changedOrgId = request.getParameter("orgId");
		if(result != null){
			Areas code = areasService.selectByCode(result.getCode1());
			if(code != null ){
				//Areas city = areasService.selectByCode(code.getParentAreaCode());
				//if(city != null){
					//result.setCity(city.getAreaCode());
					result.setProv(code.getParentAreaCode());
					result.setCity(result.getCode1());
					
				//}
			}
		}
		model.addAttribute("organizition", result);
		// 获得所属片区List
		Areas param = new Areas();
		param.setAreaRank("1");
		List<KVBean> provs = areasService.selectByAreaRank(param);
		model.addAttribute("provs", provs);
		param.setAreaRank("2");
		param.setParentAreaCode(result.getProv());
		List<KVBean> citys = areasService.selectByAreaRank(param);
		model.addAttribute("citys", citys);
		param.setAreaRank("3");
		param.setParentAreaCode(result.getCity());
		List<KVBean> list = areasService.selectByAreaRank(param);
		model.addAttribute("areas", list);
		//model.addAttribute("devPlatformList", Constants.devPlatform);//fl添加
		model.addAttribute("orgId",((Users)session.getAttribute("platformUser")).getOrgId());
		model.addAttribute("devPlatformList", basecodeService.getplatformList());
		//添加角色列表
		List<Roles> rList=rolesService.queryRolesByOrgId(organizition.getOrgId());
		model.addAttribute("rList", rList);
		//获得功能列表
		Pages pages = new Pages();
		pages.setPageRank(1l);
		pages.setFlag("1");
		List<Pages> fristList = pagesService.getPageList(pages);
		for(Pages page : fristList){
			pages.setPageRank(2l);
			pages.setFlag("1");
			pages.setParentPageId(page.getPageId());
			List<Pages> childPages = pagesService.getPageList(pages);
			List<Pages> changeOrgChildPages = pagesService.getPageListByOrgId(pages,new Long(changedOrgId));
			for (Pages pages2 : childPages) {
				if(changeOrgChildPages.contains(pages2)) {
					pages2.setChooseFlag("true");
				}
			}
			page.setChildPages(childPages);
		}
		model.addAttribute("fristPages", fristList);
		return "system/organizitionUpdate";
	}
	
	@RequestMapping({"/organizitionUpdate.htm"})
	public String organizitionUpdate(HttpServletRequest request ,HttpSession session ,Organizition organizition,PageBean pb ,Model model){
		Long orgId = ((Users)session.getAttribute("platformUser")).getOrgId();
		//判断修改时是否取消了原本的功能权限；如果取消了某个功能权限，则删除该组织机构下的所有用户的该功能权限。
		Organizition org = organizitionService.selectById(organizition.getOrgId());
		if(org.getPages()!=null ) {
			String[] pagesOld = org.getPages().split(",");//原功能列表
			String[] pagesNew=null;
			if (organizition.getPages()!=null) {
				pagesNew = organizition.getPages().split(",");//新提交的功能列表
				for (String old : pagesOld) {
					List<String> newList = Arrays.asList(pagesNew);
					if(!newList.contains(old)) {
						int num = pageRoleService.deleteByPageIdAndOrgId(organizition.getOrgId(),Long.parseLong(old));
					}
				}
			}
		}
		organizitionService.updateByPrimaryKeySelective(organizition);

		if(organizition.getIsSynchOpss()!=null && "1".equals(organizition.getIsSynchOpss())) {
			WorkorderOrganizition wOrgNew = new WorkorderOrganizition();
			wOrgNew.setOrgId(organizition.getOrgId());
			wOrgNew.setOrgName(organizition.getOrgName());
			wOrgNew.setOrgAddress(organizition.getOrgAddress());
			wOrgNew.setOrders(organizition.getOrders());
			wOrgNew.setRemark(organizition.getRemark());
			wOrgNew.setCode1(organizition.getCode1());
			wOrgNew.setCode2(organizition.getCode2());
			wOrgNew.setContactPhone(organizition.getContactPhone());
			wOrgNew.setActRoles("add_paidan,");
			workorderOrganizitionService.updateByPrimaryKeySelective(wOrgNew);
		}
		
		model.addAttribute("pb",organizitionService.queryByConditionMapAndOrgId(new Organizition(),pb,orgId));
		model.addAttribute("orgId",orgId);
		return "system/organizitionList";
	}
	
	@RequestMapping({"/organizitionDelete.htm"})
	public String organizitionDelete(HttpServletRequest request ,HttpSession session ,Organizition organizition,PageBean pb ,Model model){
		Long orgId = ((Users)session.getAttribute("platformUser")).getOrgId();
		int num = organizitionService.deleteByPrimaryKey(organizition.getOrgId());
		
		model.addAttribute("pb",organizitionService.queryByConditionMapAndOrgId(organizition,pb,orgId));
		model.addAttribute("orgId",orgId);
		return "system/organizitionList";
	}
	
	/**
	 * 
	 * 
	 * @Title: organizitionHadUsers
	 * @Description: 查询所属平台是否关联用户或者设备
	 * @param @param request
	 * @param @param session
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY修改	（原接口：周宇）
	 * @throws
	 * @date 2018年3月16日 上午9:24:13
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/organizitionHadUsers.htm")
	public Object organizitionHadUsers(HttpServletRequest request,Long orgId,HttpSession session) {
		Result r=Result.get();
		String devPlatform = request.getParameter("devPlatform");
		Users users = (Users)session.getAttribute("platformUser");
		users.setDevPlatform(devPlatform);
		
		List<Users> list = usersService.queryAllUserList(devPlatform,orgId);
		if(list!=null) {
			r.setR(list.size());
			return r;
		}
		r.setR(0);
		return r;
	}
	
	/**
	 * 设置参数提交之前的保存
	* @Title: organizitionInsert1 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param session
	* @param @param organizition
	* @param @param pb
	* @param @param model
	* @param @return    入参
	* @return String    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月26日 下午2:14:35 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/organizitionInsert1.htm")
	public int organizitionInsert1(HttpServletRequest request ,HttpSession session ,Organizition organizition,PageBean pb ,Model model){
		
		String result = "";
		model.addAttribute("orgId",((Users)session.getAttribute("platformUser")).getOrgId());
		organizition.setCreateData(new Date());
		
		int num = organizitionService.insert(organizition);

		if(organizition.getIsSynchOpss()!=null && "1".equals(organizition.getIsSynchOpss())) {
			WorkorderOrganizition wOrgNew = new WorkorderOrganizition();
			wOrgNew.setOrgId(organizition.getOrgId());
			wOrgNew.setOrgName(organizition.getOrgName());
			wOrgNew.setOrgAddress(organizition.getOrgAddress());
			wOrgNew.setOrders(organizition.getOrders());
			wOrgNew.setRemark(organizition.getRemark());
			wOrgNew.setCode1(organizition.getCode1());
			wOrgNew.setCode2(organizition.getCode2());
			wOrgNew.setContactPhone(organizition.getContactPhone());
			wOrgNew.setActRoles("add_paidan,");
			workorderOrganizitionService.insertSelective(wOrgNew);
		}
		
		
		return num;
	}
	
	/**
	 * 设置参数前,修改参数
	* @Title: organizitionUpdate1 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param session
	* @param @param organizition
	* @param @param pb
	* @param @param model
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月26日 下午2:27:13 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/organizitionUpdate1.htm")
	public int organizitionUpdate1(HttpServletRequest request ,HttpSession session ,Organizition organizition,PageBean pb ,Model model){
		Long orgId = ((Users)session.getAttribute("platformUser")).getOrgId();
		int num=0;
		//判断修改时是否取消了原本的功能权限；如果取消了某个功能权限，则删除该组织机构下的所有用户的该功能权限。
		Organizition org = organizitionService.selectById(organizition.getOrgId());
		if(org.getPages()!=null ) {
			String[] pagesOld = org.getPages().split(",");//原功能列表
			String[] pagesNew=null;
			if (organizition.getPages()!=null) {
				pagesNew = organizition.getPages().split(",");//新提交的功能列表
				for (String old : pagesOld) {
					List<String> newList = Arrays.asList(pagesNew);
					if(!newList.contains(old)) {
						 num = pageRoleService.deleteByPageIdAndOrgId(organizition.getOrgId(),Long.parseLong(old));
					}
				}
			}
		}
		int n = organizitionService.updateByPrimaryKeySelective(organizition);

		if(organizition.getIsSynchOpss()!=null && "1".equals(organizition.getIsSynchOpss())) {
			WorkorderOrganizition wOrgNew = new WorkorderOrganizition();
			wOrgNew.setOrgId(organizition.getOrgId());
			wOrgNew.setOrgName(organizition.getOrgName());
			wOrgNew.setOrgAddress(organizition.getOrgAddress());
			wOrgNew.setOrders(organizition.getOrders());
			wOrgNew.setRemark(organizition.getRemark());
			wOrgNew.setCode1(organizition.getCode1());
			wOrgNew.setCode2(organizition.getCode2());
			wOrgNew.setContactPhone(organizition.getContactPhone());
			wOrgNew.setActRoles("add_paidan,");
			workorderOrganizitionService.updateByPrimaryKeySelective(wOrgNew);
		}
		
		return n;
	}
	
	/**
	 * https提交参数
	* @Title: httpsConection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param session
	* @param @param organizition
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月26日 下午2:42:50 
	* @version V1.0
	 */
	@ResponseBody
	@RequestMapping("/httpsConection.htm")
	public int httpsConection(HttpServletRequest request ,HttpSession session ,Organizition organizition){
		int num=0;
		try {
			num = organizitionService.httpsConection(organizition);
		} catch (FrmsException e1) {
			e1.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	/**
	 * 
	* @Title: 测试 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月27日 上午10:06:03 
	* @version V1.0
	 */
	@RequestMapping("/api/devicecommand.htm")
	public Object 测试() {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("status", "1");
		return map;
	} 
}
