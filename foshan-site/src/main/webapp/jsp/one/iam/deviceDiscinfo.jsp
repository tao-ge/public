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
<script  type="text/javascript" src="${path }/js/iam/deviceDiscinfo.js?89jjk"></script>	
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
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="index.htm">首页</a></li>
			<li><a href="index.htm">智能设备管理</a></li>
			<c:if test="${flag eq 2 }">
				<li><a href="#">开关锁记录</a></li>
				<li><a href="#">端子日志记录</a></li>
			</c:if>
			<c:if test="${flag eq 1 }">
				<li><a href="#">远程开锁</a></li>
				<li><a href="#">端子日志记录</a></li>
			</c:if>
			<c:if test="${flag eq 0 }">
				<li><a href="#">设备管理</a></li>
				<li><a href="#">端子日志记录</a></li>
			</c:if>
		</ul>
		</div>
		<div class="rightinfo">
	<div class="tools">
			<ul class="toolbar">
		        <li ><a id="upload" href="javascript:;"><span></span>返回</a></li>
		 	<div class="simpleQuery" >
          		&nbsp;中控器上报端子数据： <img src="${path}/images/circle_blue.png" style="width: 9%;height: 9%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;空闲</a>
          		<img src="${path}/images/circle_green.png" style="width: 9%;height: 9%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;占用</a>
		 	</div>
		 	</ul>
		<div class="simpleQuery">
			 <form>
				<%-- 所属设施：<input name="codName_search" id="codNameSearch" value="${deviceAlarmEntity.codName }" type="text" />  --%>
				<%-- 所属分组：<input name="codCode_search" id="codCodeSearch" value="${deviceDiscinfoEntityBean.groupName }" type="text" /> --%>
				设施名称：<select name="devIds" id="devIds">
	                          <c:forEach  items="${devNames}" var="item" varStatus="status">
	                             <option value="${item.devId}" ${item.devId == devId?"selected":""}>${item.devName}</option>
	                          </c:forEach>
					  </select> 
				<!-- 最后上报时间：<input name="rptTimeSta" id="rptTimeSta" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   value=""   type="text" />
	                                     -<input name="rptTimeEnd" id="rptTimeEnd" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value=""  type="text" /> -->
				<input type="hidden" id="codIdSerach" value="${codId }">
				<input id="btnSimpleQuery" value="查询" class="submit" type="button">
				<input id="route1" type="hidden" value="${route }">
				<input id="flag1" type="hidden" value="${flag }">
				<!--  <button id="btnDisplayMoreQuery" type="button">高级查询</button> -->
			</form> 
		</div>
	</div>
	<!-- 查询数据列表 -->
	<table class="tablelist">
		<thead>
			<tr>
				<th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				<th>上报时间</th>
				<th>绑定设施</th>
				<th>熔纤盘分组</th>
				<th>熔纤盘序号</th>
				<th>中控器编码</th>
				<th>端子控制器编码</th>
				<th>占用情况</th>
				<th>端子控制器状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pb.list}" var="f" varStatus="i">
				<tr>
					<td>${i.count}</td>
					<td><fmt:formatDate value="${f.lastReportTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${f.devName}</td>
					<td>${f.groupName}</td>
					<td>${f.discSeq}</td>
					<td>${f.codCode}</td>
					<td>${f.discContrCode}</td>
					<td style="width:auto;">
						<c:forEach items="${f.lastReportDataList}" var="t" varStatus="i">
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
					<td>
					<c:choose>
						<c:when test="${f.discContrState eq '0'}">已绑定</c:when>
						<c:when test="${f.discContrState eq '1'}">已激活</c:when>
						<c:when test="${f.discContrState eq '2'}"><font style="color:red;">已删除</font></c:when>
					</c:choose>
					</td>
			<td>
			 	<a href="#" op="check" did="${f.discId}" class="tablelink">历史数据</a>
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
