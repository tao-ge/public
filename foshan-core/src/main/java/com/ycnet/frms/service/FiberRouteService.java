package com.ycnet.frms.service;



public interface FiberRouteService {
	
	int getAllFiberRouteText(Long orgId,String areaCode);
	
	int getFiberRouteText(String code,Long orgId);
	
	int getAnyPortFiberRouteText(String code,Long orgId);
	
	int getDeviceFiberRouteText(Long devId);
}
