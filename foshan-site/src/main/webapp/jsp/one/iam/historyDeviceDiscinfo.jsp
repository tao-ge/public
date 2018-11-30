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
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
<script type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
<link rel="stylesheet" type="text/css"
	href="${path}/js/prompt/skin/ymPrompt.css" />
	<link href="${path}/css/DisInfoOccup.css" rel="stylesheet" type="text/css" />
<title>设施管理</title>

</head>
<body>
	<!-- 页面导航  -->
		<div class="rightinfo">
	<div class="tools">
			<ul class="toolbar">
		 	<div class="simpleQuery" >
          		&nbsp;中控器上报端子数据：<img src="${path}/images/circle_blue.png" style="width: 9%;height: 9%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;空闲</a>
          		<img src="${path}/images/circle_green.png" style="width: 9%;height: 9%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;占用</a>
		 	</div>
		 	</ul>
	</div>
	<!-- 查询数据列表 -->
	<table class="tablelist">
		<thead>
			<tr>
				<th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				<th>上报时间</th>
				<th>熔纤盘序号</th>
				<th>占用情况</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="f" varStatus="i">
				<tr>
					<td>${i.count}</td>
					<td><fmt:formatDate value="${f.lastReportTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${f.discName}</td>
					<td style="width:auto;">
						<c:forEach items="${f.lastReportDataList}" var="t" varStatus="i">
						<%-- ${t } --%>
							 <c:if test="${i.count <= f.lastReportDataSize }">
								<div style="display: inline;">
									<c:if test="${t eq '0' }">
										<div class="lastReportDataListblue">
											<c:if test="${i.count <10}">
												0${i.count} 
											</c:if>
											<c:if test="${i.count >=10}">
												${i.count} 
											</c:if>
										</div>
									</c:if>
									<c:if test="${t eq '1' }">
										<div class="lastReportDataListgree">
											<c:if test="${i.count <10}">
												0${i.count} 
											</c:if>
											<c:if test="${i.count >=10}">
												${i.count} 
											</c:if>
										</div>
									</c:if>
									<div  class="lastReportDataListgray"></div>
								</div>
							</c:if>  
							<c:if test="${i.count == f.lastReportDataSize }">
								<br>&nbsp;&nbsp;
							</c:if>
							<c:if test="${i.count > f.lastReportDataSize }">
									<div style="display: inline;">
										<c:if test="${t eq '0' }">
											<div class="lastReportDataListblue">
												<c:if test="${i.count <10}">
												0${i.count} 
											</c:if>
											<c:if test="${i.count >=10}">
												${i.count} 
											</c:if>
											</div>
										</c:if>
										<c:if test="${t eq '1' }">
											<div class="lastReportDataListgree">
												<c:if test="${i.count <10}">
												0${i.count} 
											</c:if>
											<c:if test="${i.count >=10}">
												${i.count} 
											</c:if>
											</div>
										</c:if>
										<div  class="lastReportDataListgray"></div>
									</div>
							</c:if>
						</c:forEach>
					</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="clear"></div>
	<%-- <jsp:include page="/jsp/one/common/page.jsp" /> --%>
	</div>

	<!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display: none;">
		<form action="${path}/queryDeviceDisinfo.htm" method="post" id="moreSearchForm" name="moreSearchForm">
			<input name="devId" id="devId" type="hidden" /> 
			<input name="startTime" id="startTime" type="hidden" /> 
			<input name="endTime" id="endTime" type="hidden" />
			<input name="codId" id="codId" type="hidden" />
			<input name="route" id="route" type="hidden">
			<input id="flag" type="hidden" name="flag">
			<table width="100%" border="1" class="table1">

				<%-- <tr>
			       <td>设备名称：</td>
			       <td><input id="devName" name="devName" type="text"  value="${facilityCon.devName}" /></td>
			       <td>设施编码：</td>
			       <td><input id="devCode" name="devCode" type="text"  value="${facilityCon.devCode}" /></td>
				   
		         </tr> --%>
			</table>
		</form>
	</div>
	</div>


</body>

</html>
