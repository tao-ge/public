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
	src="${path }/js/facility/sectEdit.js?lyqlly"></script>
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
			action="${path}/sectSave.htm"  >
			<div class="formtitle">
				<span>光缆段信息</span>
			</div>
			<table class="tableForm">
				<tbody>
					<tr>
					    <td class="odd">光缆名称：</td>
						<td><input id="secName" name="secName" type="text" value="${sect.secName}" /></td>
					</tr>
					<tr>
						<td class="odd">纤芯数：</td>
						<td><input name="fiberNum" type="text" value="${sect.fiberNum}" /></td>
					</tr>
					<tr>

						<td class="odd">光缆长度：</td>
						<td><input name="cableLen" type="text" value="${sect.cableLen}" /></td>
					</tr>
					<tr>
						<td class="odd">A端：</td>
						<td><select id="seleDevIdA" style="width: 100%;">
								<option value="-1">---</option>
						</select><input name="devIdA" name="devIdA" type="hidden"
							value="${sect.devAName}" /></td>
					</tr>

					<tr>

						<td class="odd">Z端：</td>
						<td><select id="seleDevIdZ" style="width: 100%;">
								<option value="-1">---</option>
						</select><input name="devIdZ" name="devIdZ" type="hidden"
							value="${sect.devZName}" /></td>
					</tr>
					<tr>
						<td class="odd">所属光缆：</td>
						<td><input id="cableName" name="cableName" type="text"
							value="${sect.cableName}" /></td>

					</tr>
					<tr>

						<td class="odd">核实标识：</td>
						<td><select name="surveyResult" id="surveyResult">

								<c:forEach items="${surveyResultList}" var="item"
									varStatus="status">
									<option value="${item.name}"
										<c:if test="${item.name == sect.surveyResult}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td style="text-align: center;" colspan="4">
						    <input id="sectId" name="sectId" type="hidden"   value="${sect.sectId}" />
						    <input id="secName" name="secName" type="hidden"   value="${sect.secName}" />
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