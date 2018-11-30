<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>


<title>信息管理系统界面</title>
<script type="text/javascript">
	var path = "${path}";
	$(document).ready(function(e) {
	});
</script>
</head>
<body>
		<table class="tablelist">
			<thead>
				<tr>
					<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
					<th>智能锁IMEI</th>
					<th>智能锁蓝牙MAC地址</th>
					<th>所属设施</th>
					<th>注册状态</th>
					<th>注册时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${LockList}" var="f">
					<tr>
						<td>${f.regId}</td>
						<td>${f.devImei}</td>
						<td>${f.devMac}</td>
						<td>${f.fName}</td>
						<td><c:choose>
								<c:when test="${f.validateSign=='0'}">
									未注册
								</c:when>
								<c:when test="${f.validateSign=='1'}">
									已注册
								</c:when>
								<c:otherwise>
									已激活
       							</c:otherwise>
							</c:choose></td>
						<td><fmt:formatDate value="${f.regTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						
					</tr>

				</c:forEach>
			</tbody>
		</table>

</body>
</html>
