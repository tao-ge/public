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

	<div class="formbody">
		
    	 <form name="editForm" id="editForm" method="post"
			action="${path}/sonSave.htm"  >
			<div class="formtitle">
				<span>子设备信息</span>
				
			</div>
			<table class="tableForm">
				<tbody>
					<tr>
						<c:if test="${son.devId == null}">
							<td class="odd">类型：</td>
							<td><select id="devModel" name="devModel">
								<c:forEach items="${obdTypeList}" var="item"
									varStatus="status">
									<option value="${item.name}"
										<c:if test="${item.value == son.devModel}">selected ="selected"</c:if>
										>${item.value}</option>
								</c:forEach></select>
							</td>
						</c:if>
						<c:if test="${son.devId != null}">
							<td class="odd">类型：</td>
							<td><select disabled="true" id="devModel" name="devModel">
								<c:forEach items="${obdTypeList}" var="item"
									varStatus="status">
									<option value="${item.name}"
										<c:if test="${item.value == son.devModel}">selected ="selected"</c:if>
										>${item.value}</option>
								</c:forEach></select>
							</td>
						</c:if>
					</tr>
					<tr>
						<td class="odd">名称：</td>
						<td><input name="devName" type="text" value="${son.devName}" /></td>
					</tr>
					<tr>

						<td class="odd">核实标识：</td>
						<td><select name="surveyResult" id="surveyResult">

								<c:forEach items="${surveyResultList}" var="item"
									varStatus="status">
									<option value="${item.name}"
										<c:if test="${item.name == son.surveyResult}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td style="text-align: center;" colspan="4">
							<input id="pdevId" name="pdevId" type="hidden"   value="${devId}" />
						    <input id="devId" name="devId" type="hidden"   value="${son.devId}" />
							<button name="btnSave" id="btnSave" type="button" class="btn">确认保存</button>
							<button name="btnClose" id="btnClose" type="button" class="btn">关闭</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
     </div>
</body>
</html>