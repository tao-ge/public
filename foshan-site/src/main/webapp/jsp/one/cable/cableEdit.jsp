<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page import="java.text.*" %>
<%@page import="java.util.*" %>
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
	src="${path }/js/cable/cableEdit.js?ce"></script>
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
		<form name="editForm" id="editForm" method="post" action="${path}/cableAdd.htm">
			<div class="formtitle"> 
				<span>光缆基本信息</span>
			</div>

			<table class="tableForm">
				<tbody>
					<tr>
						<td class="odd">光缆编号：</td><td><input id="cableCode" name="cableCode" type="text" value="${cable.cableCode}" /><i><font color="red"> </font></i></td>
					
						<td class="odd">光缆名称：</td><td><input id="cableName" name="cableName" type="text" value="${cable.cableName}" /></td>
					</tr>
					<tr>
						<td>A端设备类型：</td><td>
													
							<select name="typeA" id="typeA">
									<c:forEach items="${deviceTypeList}" var="item"
										varStatus="status">
										<option value="${item.name}"
										<c:if test="${item.name == cable.typeA}">selected ="selected"</c:if>>${item.value}</option>
									</c:forEach>
								</select>
						
						</td>
						<td class="odd">A端设备：</td><td><input name="devA" type="text" value="${cable.devA}" /></td>
					</tr>
					<tr>
						<td>Z端设备类型：</td>
								<td><select name="typeZ" id="typeZ">
									<c:forEach items="${deviceTypeList}" var="item"
										varStatus="status">
										<option value="${item.name}"
										<c:if test="${item.name == cable.typeZ}">selected ="selected"</c:if>>${item.value}</option>
									</c:forEach>
								</select>
						</td>
						<td class="odd">Z端设备：</td><td><input name="devZ" type="text" value="${cable.devZ}" /></td>
					</tr>
					<tr>	
						<td class="odd">纤芯数：</td><td><input name="fiberNum" type="text" value="${cable.fiberNum}" /></td>
						<td class="odd">光缆段数：</td><td><input name="cableSectNum" type="text" value="${cable.cableSectNum}" /></td>
					</tr>
					<tr>	
						<td class="odd">光缆长度：</td><td><input name="cableLen" type="text" value="${cable.cableLen}" /></td>
						<td class="odd">所属工程：</td>
						<td><select id="seleProId" style="width: 100%;">
								<option value="-1">---</option>
						</select> <input id="proId" name="proId" type="hidden"
							value="${cable.proId}" /></td>					
					</tr>
					<tr>	
						<td class="odd">竣工时间：</td><td><input id="completeDate" name="completeDate" type="text"
							onClick="WdatePicker()" value="${date}" /></td>
					</tr>
					
					<tr>	
						<td style="text-align: center;" colspan="4">
						    <input id="cableId" name="cableId" type="hidden"   value="${cable.cableId}" />
				
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
