<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<meta http-equiv="x-ua-compatible" content="IE=10">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script  type="text/javascript" src="${path }/js/iam/deviceAlarmHistorySatate.js"></script>	
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
<title>设施管理</title>

</head>
<body>
	<!-- 页面导航  -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="index.htm">首页</a></li>
			<li><a href="index.htm">智能设备管理</a></li>
			<li><a href="queryFacilityState.htm">设备状态列表</a></li>
			<li><a href="#">设备历史状态</a></li>
		</ul>
	</div>
	
	<div class="tools">
			<ul class="toolbar">
		        <li ><a id="upload" href="javascript:;"><span></span>返回</a></li>
		 	</ul>
		<div class="simpleQuery">
			 <form method="post" id="simpleQuery" name="simpleQuery">
			 	<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
			 	<input name="devId" id="devId" value="${deviceEntity.devId }" type="hidden" /> 
			 	<input name="codName" id="codName" value="${deviceEntity.codName }" type="hidden" /> 
			 	<input name="curStatus" id="curStatus" value="${curStatus }" type="hidden" /> 
<!-- 				最后上报时间：<input name="rptTimeSta" id="rptTimeSta" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   value=""   type="text" /> -->
<!-- 	                                     -<input name="rptTimeEnd" id="rptTimeEnd" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value=""  type="text" /> -->
<!-- 				<input id="btnSimpleQuery" value="查询" class="submit" type="button"> -->
			</form> 
		</div>
	</div>
	<!-- 查询数据列表 -->
	<table class="tablelist">
		<thead>
			<tr>
				<th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>中控器编码</th>
				        <th>中控器名称</th>
				        <th>锁A状态</th>
				        <th>门A状态</th>
				        <th>锁B状态</th>
				        <th>门B状态</th>
				        <th>温度</th>
				        <th>倾斜</th>
				        <th>电压(V)</th>
				        <th>信号(0-31)</th>
				        <th>最后一次上报时间</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pb.list}" var="f" varStatus="i">
				        <tr>
				        <td>${i.count}</td>
				        <td>${f.codCode}</td>
				        <td>${f.codName}</td>
				        <td>
				        <c:if test="${f.lockOpen01 eq 0}">关</c:if>
				        <c:if test="${f.lockOpen01 eq 1}">开</c:if>
				        <c:if test="${f.lockOpen01 eq 2}">未知</c:if>
				        </td>
				        <td>
				        <c:if test="${f.doorOpen01 eq 0}">关</c:if>
				        <c:if test="${f.doorOpen01 eq 1}">开</c:if>
				        <c:if test="${f.doorOpen01 eq 2}">未知</c:if>
				        </td>
				        <td>
				        <c:if test="${f.lockOpen02 eq 0}">关</c:if>
				        <c:if test="${f.lockOpen02 eq 1}">开</c:if>
				        <c:if test="${f.lockOpen02 eq 2}">未知</c:if>
				        </td>
				        <td>
				        <c:if test="${f.doorOpen02 eq 0}">关</c:if>
				        <c:if test="${f.doorOpen02 eq 1}">开</c:if>
				        <c:if test="${f.doorOpen02 eq 2}">未知</c:if>
				        </td>
				        <td>${f.temp}</td>
				      	<td>${f.tilt}</td>
				      	<td>${f.battery}</td>
				      	<td>${f.signals}</td>
				      	<td><fmt:formatDate value="${f.rptTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				        
				        </tr>
		       		</c:forEach>
		</tbody>
	</table>
	<div class="clear"></div>
	<jsp:include page="/jsp/one/common/page.jsp" />
</body>

</html>
