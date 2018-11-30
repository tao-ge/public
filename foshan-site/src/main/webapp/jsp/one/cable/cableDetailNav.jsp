<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 
光缆段详情公共导航
需要参数：devId  devName
 --> 
            <div class="formtitle"><span>${cb.secName}</span></div>
		    <div class="itab">
			  	<ul> 
			    <li>
			    <a href="${path}/cableInfo.htm?sectId=${sectId}" 
			    ${!(pageFlag eq 'baseInfo') ? "" : "class=\"selected\""}
				>基本信息</a></li> 
			    <li>
			    <a href="${path}/cableInfoMap.htm?sectId=${sectId}"
		         ${!(pageFlag eq 'cableMap') ? "" : "class=\"selected\""}
			      >光缆段位置</a></li> 
				
				
				</ul>
		    </div> 
