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
	src="${path }/js/project/projectEdit.js?ce"></script>
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
			action="${path}/projectSave.htm">
			<div class="formtitle">
				<span>工程基本信息</span>
			</div>


			<table class="tableForm">
				<tbody>
					<tr>

						<td class="odd">工程编码：</td>
						<td>
							<input id="proCode" name="proCode" type="text" value="${pro.proCode }" />
						</td>

					</tr>
					<tr>
					    <td class="odd">工程名称：</td>
						<td colspan="4"><input id="proName" name="proName"
							style="width: 350px" type="text" datatype="*1-200"
							errormsg="设施名称至少1个字符,最多200个字符！" value="${pro.proName } " /><span
							style="color: red; display: inline">* 必填</span></td>
					</tr>

					<tr>

						<%-- <td class="odd">所属机构：</td>
						<td>
							<select id="orgId" name="orgId">
								<c:forEach items="${deviceTypeList}" var="item"
									varStatus="status">
									<option value="${item.name}"
										<c:if test="${item.name == facility.devType}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
							</select>
						</td> --%>
						<input type="hidden" name="orgId" value="${orgId}" >

					</tr>

					<tr>
						<td style="text-align: center;" colspan="4">
						    <%-- <input id="devId" name="devId" type="hidden"   value="${facility.devId}" />
						    <input id="devCode" name="devCode" type="hidden"   value="${facility.devCode}" /> --%>
						    <input id="proId" name="proId" type="hidden"   value="${pro.proId}" />
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