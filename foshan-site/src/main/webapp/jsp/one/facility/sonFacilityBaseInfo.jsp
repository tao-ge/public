<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
	src="${path }/js/facility/sonEdit.js?lyqlly"></script>
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
	    <li><a href="#">设施管理</a></li>
	    <li><a href="#">设施详情</a></li>
	    </ul>
    </div>
	<div class="formbody">
	
	    <div id="usual1" class="usual"> 
    	 <jsp:include page="/jsp/one/facility/facilityDetailNav.jsp" /> 
		
    	 <form name="editForm" id="editForm" method="post"
			action="${path}/sonSave.htm"  >
			<div class="formtitle">
				<span>子设备信息</span>
			</div>
			<table class="tableForm">
				<tbody>
					<tr>
					    <td class="odd">类型：</td>
						<td>${son.devModel}</td>
					</tr>
					<tr>
						<td class="odd">名称：</td>
						<td>${son.devName}</td>
					</tr>
					<tr>

						<td class="odd">核实标识：</td>
						<td>${son.surveyResultName}
						</td>
						
					</tr>
					<tr>
						<td style="text-align: center;" colspan="4">
							<input id="pdevId" name="pdevId" type="hidden"   value="${devId}" />
						    <input id="devId" name="devId" type="hidden"   value="${son.devId}" />
						    <input id="devName" name="devName" type="hidden"   value="${son.devName}" />
							<button name="btnSave" id="btnSave" type="button" class="btn">确认保存</button>
							<button name="btnClose" id="btnClose" type="button" class="btn">关闭</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
     </div>
     </div>
     </div>
</body>
</html>