<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var path = "${path}";
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>change password</title>
<script type="text/javascript" src="${path}/js/jquery.js"></script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="index.htm">首页</a></li>
			<li><a href="#">APP下载</a></li>
		</ul>
	</div>
	<div class="formbody">
<!--
 		<div style="text-align:conter; padding: 10% 0;">
	<%-- 	<a href="${path}/GJXPCS229.apk"> --%>
					<div style="float: left;padding-left: 200px">
						<ul>        
							<%-- <li><img src="${path}/images/apk01.png" /></li> --%>
							<li><img src="${path}/images/${imgName02 }.png" /></li>
							<a href="${path}/GJXP182.apk"><li style="color:red;font-size:45px;font-weight:bold;">智能光交箱APP</li></a>
						</ul>
					</div>
					<div style="float: right ;padding-right: 200px">
						<ul>
							<li><img src="${path}/images/${imgName01 }.png" /></li>
							<a href="${path}/SJLR182.apk"><li style="color:red;font-size:45px;font-weight:bold;">资源核查APP</li></a>
						</ul>
					</div>
			</a>
		</div>
-->		
		
		<div style="text-align:conter; padding: 10% 0;">

					<div style="float: left ;padding-left: 200px;">
						<ul>
							<li><img src="${path}/images/zw7.png" /></li>
							<a href="${path}/zw7.apk"><li style="color:red;font-size:45px;font-weight:bold;">装维APP</li></a>
						</ul>
					</div>
					<div style="float: right ;padding-right: 200px">
						<ul>
							<li><img src="${path}/images/cccs7.png" /></li>
							<a href="${path}/cccs7.apk"><li style="color:red;font-size:45px;font-weight:bold;">出厂测试APP</li></a>
						</ul>
					</div>
					
					 <div style="float: left ;padding-left: 200px;">
						<ul>
							<li><img src="${path}/images/cccs7_new.png" /></li>
							<a href="${path}/zw7_new.apk"><li style="color:red;font-size:45px;font-weight:bold;">新版出厂APP</li></a>
						</ul>
					</div>
					<div style="float: right ;padding-right: 200px">
						<ul>
							<li><img src="${path}/images/SJLR7.png" /></li>
							<a href="${path}/SJLR7.apk"><li style="color:red;font-size:45px;font-weight:bold;">资源核查APP</li></a>
						</ul>
					</div>
		</div>
	</div>
</body> 


<script>

</script>
</html>