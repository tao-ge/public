package com.ycnet.site.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.util.ImportData;
import com.ycnet.frms.bean.Users;

@RestController
public class ImportDataController {

	@Autowired
	private ImportData importData;
	
	
	@RequestMapping("/import/step1.htm")
	public Object addFiber(HttpSession session)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		if(user!=null)
			importData.addFiber(user.getOrgId());
		else
			return"No Login.";
		return "Ok";
	}
	
	@RequestMapping("/import/step2.htm")
	public Object genDisc(HttpSession session)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		if(user!=null)
			importData.genDisc(user.getOrgId());
		else
			return"No Login.";
		return "Ok";
	}
	
	@RequestMapping("/import/step3.htm")
	public Object genLocation(HttpSession session)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		if(user!=null)
			importData.genLocation(user.getOrgId());
		else
			return"No Login.";
		return "Ok";
	}
	
	
	@RequestMapping("/import/step4.htm")
	public Object genJumper(HttpSession session)
	{
		Users user=(Users)session.getAttribute("platformUser");	
		if(user!=null)
			importData.genJumper(user.getOrgId(),user.getUserId());
		else
			return"No Login.";
		return "Ok";
	}
}
