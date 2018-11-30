<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<title>信息管理系统界面</title>
<script type="text/javascript">
	var path = "${path}";
	$(document).ready(function(e) {

		$(".select3").uedSelect({
			width : 152
		});
	});
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">资源监控</a></li>
			<li><a href="#">智能锁编辑</a></li>
		</ul>
	</div>
	<div class="formbody">
		<form name="searchform" action="${path}/saveLockReg.htm">
			<div class="formtitle">
				<span>基本信息</span>
			</div>

			<ul class="forminfo">
				<li><label>选择设施</label><input name="" type="text" class="dfinput" /></li>
				<li><label>智能锁IMEI</label><input name="" type="text" class="dfinput" /><i>IMEI相关提示</i></li>
				<li><label>智能锁MAC</label><input name="" type="text" class="dfinput" /><i>智能锁12位蓝牙MAC地址</i></li>
				<li><label>注册状态</label>
					<div class="vocation">
						<select class="select3" name="validateSign" id="validateSign">
							<option value=''>全部</option>
						</select>
					</div>
				</li>
				<li><label>保存并继续录入</label><cite><input name="" type="checkbox" value="goon" /></cite><label>&nbsp;</label></li>
    			<li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存"/></li>
			</ul>
		</form>
	</div>
</body>
</html>