package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.SiteCode;

public interface SiteCodeService {

	String getNewDevCode();
	
	String getNewSectCode();

	//导出数据库  刘沧海 2017/10/13
	List<SiteCode> queryList();
}
