package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.TempLightPath;

public interface LightPathService {
	
	int save(List<TempLightPath> lpList,String devType,Long orgId,String areaCode1);
	
	String selectMinTimeByParam(String devType,Long orgId,String areaCode1);
}
