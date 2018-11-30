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
<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>

<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript"
	src="${path }/js/facility/facilityEdit.js?ce"></script>
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
	    <li><a href="#">资源台账管理</a></li>
	    <li><a href="#">光缆段管理</a></li>
	    <li><a href="#">光缆段详情</a></li>
	    </ul> 
    </div>
	<div class="formbody">
		<div id="usual1" class="usual"> 
		<jsp:include page="/jsp/one/cable/cableDetailNav.jsp" /> 
		<form name="editForm" id="editForm" method="post" action="${path}/cableAdd.htm">


			<table class="tableDetail">
				<tbody>
					<tr>
						<td class="odd" >光缆段名称:</td>
						<td colspan="4">${cb.secName}</td>
					</tr>
					<tr>
						<td class="odd" >光缆段编码：</td>
						<td colspan="4">${cb.secCode}</td>
					</tr>
					<tr>
						<td >纤芯数：</td>
						<td colspan="4">${cb.fiberNum}</td>
					</tr>
					<tr>
						<td >光缆段长度:</td>
						<td colspan="4">${cb.cableLen}</td>
					</tr>
				
					<tr>
						<td class="odd" width="100px">A端名称：</td>
						<td width="400px">${cb.adevName}</td>
						<td class="odd" width="100px">Z端名称：</td>
						<td >${cb.zdevName}</td>
					</tr>
					<tr>
						<td class="odd">A端编码：</td><td>${cb.devCodeA}</td>
						<td class="odd">Z端编码：</td><td>${cb.devCodeZ}</td>
					</tr>
					<tr>
						<td class="odd">A端开始位置：</td><td>${cb.acode}</td>
						<td class="odd">Z端开始位置：</td><td>${cb.zcode}</td>
					</tr>
					<tr>
						<td class="odd">A端结束位置：</td><td>${cb.acodeE}</td>
						<td class="odd">Z端结束位置：</td><td>${cb.zcodeE}</td>
					</tr>
					
				</tbody>
			</table>
			
		</form>
	</div>
	</div>
</body>
	 
</html>
