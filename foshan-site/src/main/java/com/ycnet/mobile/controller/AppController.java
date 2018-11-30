package com.ycnet.mobile.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.mobile.util.Result;

@RestController
public class AppController {

	@Resource(name="organizitionService")
	private OrganizitionService organizitionService ;
	
	@RequestMapping("/m/updateMobileVer")
	public Object updateMobileVer(HttpSession session,String curVer)
	{
		Result r = Result.get().putRContent("无新版本。");
		Users user =(Users)session.getAttribute("users");
		if(user!=null)
		{
			Organizition o = organizitionService.selectById(user.getOrgId());
			String ver = o.getMobileVer();
			ver = ver==null?"":ver;
			if(curVer.compareTo(ver)<0)
			{
				r.putR(1).putDT(o.getMobileVerUrl());
			}
		}
		return r;
	}
	
	public static void main(String[] args) {
		String str ="R234.1";
		System.out.println(str.compareTo(""));
	}
}
