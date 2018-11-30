package com.ycnet.mobile.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.vo.AreasBean;
import com.ycnet.frms.vo.AreasVo;
import com.ycnet.frms.vo.Position;
import com.ycnet.mobile.util.Result;


@RestController

@RequestMapping("/m")
public class AreaController {

	@Resource
	private AreasService areasService;
	
	@Resource
	private OrganizitionService orgService;
	
	@RequestMapping("/getAreas.htm")
	public Object getAreas(HttpSession session,String parentAreaCode){
		Result r = Result.get();
		try{
			if(parentAreaCode==null||"".equals(parentAreaCode.trim())){
				Users user=(Users)session.getAttribute("users");
				if(user==null)
				{
					r.setR_content("用户未登录！");
					return r;
				}
				Organizition o = orgService.selectById(user.getOrgId());
				if(o ==null||o.getCode1()==null||"".equals(o.getCode1().trim())){
					r.setR_content("获取用户所属机构错误");
					return r;
				}
				parentAreaCode=o.getCode1().substring(0,4).concat("00");
			}
			
			r.putDtList(areasService.selectByParent(parentAreaCode)).initQueryMessage();
			
		}catch(Exception e){
			 r.setR_content("获取机构错误");
			 return r;
		}
		return r;
	}
	
	@RequestMapping("/v1/getAreas.htm")
	public Object getV1Areas(HttpSession session){
		Result r = Result.get();
		String parentAreaCode = null;
		try{
			Users user=(Users)session.getAttribute("users");
			if(user==null)
			{
				r.setR_content("用户未登录！");
				return r;
			}
			Organizition o = orgService.selectById(user.getOrgId());
			if(o ==null||o.getCode1()==null||"".equals(o.getCode1().trim())){
				r.setR_content("获取用户所属机构错误");
				return r;
			}
			parentAreaCode=o.getCode1().substring(0,4).concat("00");
			
			r.putDtList(areasService.selectVoByParent(parentAreaCode)).initQueryMessage();
			
		}catch(Exception e){
			 r.setR_content("获取机构错误");
			 return r;
		}
		return r;
	}
	
	/**
	 * 
	* @Title: threeAreas 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月14日 下午3:02:18 
	* @version V1.0
	 */
	@RequestMapping("/v1/queryThreeAreas.htm")
	public Object threeAreas(HttpSession session) {
		Result r = Result.get();
		Users user=(Users)session.getAttribute("users");
		if(user==null)
		{
			r.setR(0);
			r.setR_content("用户未登录！");
			return r;
		}
		try {
			List<AreasBean>list =areasService.queryThreeAreas();
			if (list!=null && list.size()>0) {
				r.setR(1);
				r.setDtList(list);
			}
		} catch (Exception e) {
			r.setR(0);
			r.setR_content("查询失败");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 查询省市区镇
	* @Title: queryPosition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月15日 上午10:31:22 
	* @version V1.0
	 */
	@RequestMapping("/v1/queryPosition.htm")
	public Object queryPosition(HttpSession session) {
		Result r = Result.get();
		Users user=(Users)session.getAttribute("users");
		if(user==null)
		{
			r.setR(0);
			r.setR_content("用户未登录！");
			return r;
		}
		try {
			List<Position>list =areasService.queryPosition();
			if (list!=null && list.size()>0) {
				r.setR(1);
				r.setDtList(list);
			}
		} catch (Exception e) {
			r.setR(0);
			r.setR_content("查询失败");
			e.printStackTrace();
		}
		return r;
	}
	
}
