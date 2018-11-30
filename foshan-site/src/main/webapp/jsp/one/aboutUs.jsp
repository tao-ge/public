<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>关于</title>
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<script>
	var path = '${path}';
</script>
<script language="JavaScript" src="${path}/js/jquery.js"></script>
<script src="${path}/js/cloud.js" type="text/javascript"></script>
<script src="${path}/js/cookie.js" type="text/javascript"></script>

</head>
<body style="background-color: #D1D1D1;">
	<div class="footer">
		<div
			style="width: 50%; float: left; margin-top: 70px; text-align: left;"
			id="liu1">
			<div style="padding-left: 70px;">
				<div"="">
					<c:if test="${ipValue==0 }">
						<img alt="" src="images/p2.png">
					</c:if>
					<c:if test="${ipValue==1 }">
						<img alt="" src="images/p1.png">
					</c:if>
				</div>
				<div style="margin-top: 70px; margin-left: 70px; text-align:left;">
					<p style="margin-top: 25px;">版权所有：移创物联科技有限公司</p>
					<p>备&nbsp;案&nbsp;号  ：鄂ICP备16001493号</p>
					<p>软件名称：
					<c:if test="${ipValue==0 }">
						传输网络资源普查系统
					</c:if>
					<c:if test="${ipValue==1 }">
						传输网络智慧管理系统
					</c:if>
					
					</p>
					<p>软件版本：1.0</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
