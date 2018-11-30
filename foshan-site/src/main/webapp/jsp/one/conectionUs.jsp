<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>联系我们</title>
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<script>
	var path = '${path}';
</script>
<script language="JavaScript" src="${path}/js/jquery.js"></script>
<script src="${path}/js/cloud.js" type="text/javascript"></script>
<script src="${path}/js/cookie.js" type="text/javascript"></script>

</head>
<body style="background-color:#D1D1D1;">
	<div class="footer">
		<div style="width: 50%; float: left;" id="liu2">
			<div style="margin-top: 70px; margin-left: 150px; text-align: left;">
				<p style="font-size: 18px; line-height: 65px;">
					<a name="Contact us"></a>移创物联科技有限公司
				</p>
				<p>电话：0517-84810909</p>
				<p>手机：15805314222</p>
				<p>邮箱：zhaochun@ycthingnet.com</p>
				<p>地址：江苏省淮安市淮阴区珠江路169号欧蓓莎大厦6楼</p>
				<c:if test="${ipValue==0 }">
					<p>传输网络资源普查（佛山、惠州的软件功能）：传输网络资源普查系统</p>
				</c:if>
				<c:if test="${ipValue==1 }">
					<p>传输网络智能管理（含电子工单）：传输网络智慧管理系统</p>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
