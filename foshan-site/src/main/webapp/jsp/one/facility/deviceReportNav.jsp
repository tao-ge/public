<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 
记录报表公共导航
 -->
<%--             <div class="formtitle"><span>${devName}</span></div>  --%>
		    <div class="itab">
			  	<ul> 
			    <li >
				    <a  href="${path}/switchRecordList.htm" 
				    ${!(navFlag eq 'SwitchLockRecord') ? "" : "class=\"selected\""}>开关锁记录查询</a>
				</li> 
				<li >
				    <a  href="${path}/devTimeNewStatusList.htm" 
				    ${!(navFlag eq 'ReportOnTime') ? "" : "class=\"selected\""}>定时上报记录查询</a>
				</li> 
				<li >
				    <a  href="${path}/deviceAlarmList.htm" 
				    ${!(navFlag eq 'AlarmRecord') ? "" : "class=\"selected\""}>报警记录查询</a>
				</li> 
				</ul>
		    </div> 
