<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
	table tr:nth-child(odd){background:#F0F8FF;}
	table td:nth-child(even)
</style>
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript" src="${path }/js/facility/remoteUnlockListNew.js"></script>
<script type="text/javascript">
	path = '${path }';
	$(function(){
		$('.tablelist tbody tr:odd').addClass('odd');
	})
</script>
</head>
<body>
<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">智能化设备管理</a></li>
	    <li><a href="remoteUnlockNew.htm">远程开锁</a></li>
	    </ul>
    </div>	
    <div class="rightinfo">
		<div class="tools">
			<div>
				<form name="searchlistform" id="searchlistform" >
					设施编码：<input name="devCode" id="devCode" value="${dr.devCode }" type="text" />
					设施名称：<input name="devName" id="devName" value="${dr.devName }" type="text" />
					MAC地址：<input name="devMac" id="devMac" value="${dr.devMac }" type="text" />
					状态： 
					<select name="validateSign" id="validateSign">
						<option value="" <c:if test="${empty sign}">selected ="selected"</c:if>>全部</option>
						<c:forEach items="${signList}" var="item" varStatus="status">
							<option value="${item.value}" <c:if test="${item.value == dr.validateSign}">selected ="selected"</c:if>>${item.name}</option>
						</c:forEach>
					</select>
					<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
					<button id="btnSearch" name="btnSearch" type="button" class="submit">查询</button>
				</form>
			</div>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
					<th>设施编码</th>
					<th>设施名称</th>
					<th>锁IMEI</th>
					<th>锁MAC地址</th>
					<th>注册状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${pb.list}" var="f" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${f.devCode}</td>
						<td>${f.devName}</td>
						<td>${f.devImei}</td>
						<td>${f.devMac}</td>
						<td>
							<c:choose>
								<c:when test="${f.validateSign=='0'}">
									未注册
								</c:when>
								<c:when test="${f.validateSign=='1'}">
									已注册
								</c:when>
								<c:otherwise>
									已激活
       							</c:otherwise>
							</c:choose>
						</td>
						<td>
							<input type="button" op="unlock" rid="${f.devImei}" class="tablelink" value="开锁">
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		<div class="clear"></div>
		<jsp:include page="/jsp/one/common/page.jsp" />
	</div>
</body>
</html>