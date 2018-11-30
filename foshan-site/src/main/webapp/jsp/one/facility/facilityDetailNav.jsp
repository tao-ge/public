<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 
设施详情公共导航
需要参数：devId  devName
 -->
            <div class="formtitle"><span>${devName}</span></div> 
		    <div class="itab">
			  	<ul> 
			    <li >
				    <a  href="${path}/facilityBaseInfo.htm?devId=${devId}&devName=${devName}" 
				    ${!(pageFlag eq 'baseInfo') ? "" : "class=\"selected\""}>基本信息</a>
				</li> 
			    <li>
				    <a href="${path}/cableSectionList.htm?devId=${devId}&devName=${devName}"
			         ${!(pageFlag eq 'cableSectionList') ? "" : "class=\"selected\""}>接入光缆段</a>
			    </li> 
				<li>
					<a href="${path}/sonFacilityList.htm?devId=${devId}&devName=${devName}" ${!(pageFlag eq 'sonfacility') ? "" : "class=\"selected\""}
					 >子设备信息</a>
			    </li> 

				<li>
					<a target="_blank" href="fiberdiscChart.htm?devId=${devId}&devName=${devName}" ${!(pageFlag eq 'fiberdiscInfo') ? "" : "class=\"selected\""}
					>熔纤盘信息</a>
				</li> 
				<li >
					<a   target="_blank" href="${path}/facilityRelationBydevId.htm?devId=${devId}&devName=${devName}" ${!(pageFlag eq 'topography') ? "" : "class=\"selected\""}
					>拓扑图</a>
				</li>
				<li>
					<a href="${path}/devDetailStatusList.htm?devId=${devId}&devName=${devName}" ${!(pageFlag eq 'devStatus') ? "" : "class=\"selected\""}
					>监控数据</a>
				</li> 
<%-- 				<li><a href="${path}/facilitySwitchList.htm?devId=${devId}&devName=${devName}" ${!(pageFlag eq 'mobileSwitch') ? "" : "class=\"selected\""} --%>
<!-- 			     >终端开关锁</a></li>  -->
				<li >
					<a href="${path}/facilityGroups.htm?devId=${devId}&devName=${devName}" ${!(pageFlag eq 'facilityGroup') ? "" : "class=\"selected\""} 
	 			     >设施分组信息</a>
 			    </li>
 			    <li>
	 			    <a href="${path}/deriveONU.htm?devId=${devId}" ${!(pageFlag eq 'derive') ? "" : "class=\"selected\""}
	 			     >导出Ecxel</a>
 			    </li>
				<li >
					<a href="${path}/facilityImgList.htm?devId=${devId}&devName=${devName}" ${!(pageFlag eq 'facilityImg') ? "" : "class=\"selected\""}
					 >图片信息</a>
				</li> 
				<li >
					<a href="${path}/facilityInspectList.htm?devId=${devId}&devName=${devName}" ${!(pageFlag eq 'facilityInspect') ? "" : "class=\"selected\""}
					>巡检记录</a>
				</li>
				
				</ul>
		    </div> 
