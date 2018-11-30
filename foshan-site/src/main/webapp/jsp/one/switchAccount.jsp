<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script src="${path}/js/switchAccount.js" type="text/javascript"></script>
<script type="text/javascript">
	var path = "${path}";
</script>
<title>切换账号</title>
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<style type="text/css">
	font{
		color: red;
		size: 8;
	};
</style>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="index.htm">首页</a></li>
			<li><a href="#">切换账号</a></li>
		</ul>
	</div>
	<div class="formbody">
		<form id="submitForm" name="submitForm">
			<div class="formtitle">
				<span>切换账号</span>
			</div>
			
			<table class="tableForm">
				<tbody>
					<tr>				
						<td class="odd">账号:</td>
						<td><input class="dfinput" id="userCode" maxlength="30" onclick="JavaScript:this.value=''"></td>
					</tr>
					<tr>
						<td class="odd">密码:</td>
						<td><input class="dfinput" id="userPwd" maxlength="30" type="password" onclick="JavaScript:this.value=''"></td>
					</tr>
					<tr>
						<td colspan="2"><input style="margin-left: 25%;" class="btn" id="btnSubmit" type="button" value="确认切换"></td>
					</tr>
				</tbody>
			</table>
		
		</form>
</div>
</body>
</html>