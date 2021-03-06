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
<script type="text/javascript" src="${path }/js/facility/facilityGroupsEdit.js?ce"></script>
<script type="text/javascript">
	path = '${path }';
</script>
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
			action="${path}/facilityGroupsSave.htm">
			<div class="formtitle">
				<span>设施分组修改</span>
			</div>
			<table class="tableForm">
				<tbody>
					<tr>
						<td class="odd">分组名称：</td>
						<td><input id="groupName" name="groupName" type="text"
							value="${group.groupName}" /></td>
					</tr>
					<tr>
						<td class="odd">分组描述：</td>
						<td><input id="groupDesc" name="groupDesc" type="text"
							value="${group.groupDesc}" /></td>
					</tr>

					<tr>
						<td style="text-align: center;" colspan="4">
							<input id="groupId" name="groupId" type="hidden"   value="${group.groupId}" />
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