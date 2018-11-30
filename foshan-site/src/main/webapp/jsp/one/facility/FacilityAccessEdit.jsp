<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript" src="${path }/js/deviceStatus/deviceStatusList.js?ces"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript" src="${path }/js/facility/facilityaccessEdit.js"></script>
<title>信息管理系统界面</title>
<script type="text/javascript">
	var path = "${path}";
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">智能化设备管理</a></li>
			<li><a href="#">用户授权编辑</a></li>
		</ul>
	</div>
	<div class="formbody">
		<form name="accessEditForm" id="accessEditForm" action="${path}/saveAccess.htm">
			<div class="formtitle">
				<span>基本信息</span>
			</div>

			<input name="accessId" type="text" id="accessId" value="${fa.accessId}" style="display: none" /> <input name="userId" type="text" id="userId" value="${fa.userId}" style="display: none" /> <input
				name="devIds" type="text" id="devIds" value="${fa.devIds}" style="display: none" />
				<input name="accessTime" type="text" id="accessTime" value="<fmt:formatDate value="${fa.accessTime}" pattern="yyyy-MM-dd" />" style="display: none" />
			<table class="tableForm">
				<tbody>
					<tr>
						<td>选择用户：</td>
						<td><input name="userName" type="text" class="dfinput" id="userName" value="${fa.userName}" readonly="readonly" />&nbsp;&nbsp;<a id="hrefAddUser" href="javascript:;"><u>选择</u></a></td>


					</tr>
					<tr>
						<td>授权结束时间：</td>
						<td><input id="validateTime" name="validateTime" type="text" onClick="WdatePicker()" value="<fmt:formatDate value="${fa.validateTime}" pattern="yyyy-MM-dd" />" /></td>
					</tr>
					<tr>
						<td>用户状态：</td>
						<td><select name="validateStatus" id="validateStatus">
								<c:forEach items="${VaList}" var="item">
									<option value="${item.value}" <c:if test="${item.value == sign}">selected ="selected"</c:if>>${item.name}</option>
								</c:forEach>
						</select></td>

					</tr>
					<tr>
						<td>备注：</td>
						<td><input id="remark" name="remark" type="text" value="${fa.remark}" /></td>
					</tr>
					<tr>
						<td>设施列表：</td>
						<td rowspan="5" style="vertical-align: top;"><select name="devSelect" id="devSelect" multiple="multiple" style="height: 150px; width: 348px; overflow-y: auto;">
								<c:forEach items="${DevList}" var="item">
									<option value="${item.value}" <c:if test="${item.value == sign}"></c:if>>${item.name}</option>
								</c:forEach>
						</select></td>
						<td style="float: left !important; position: absolute !important; left: 470px !important;"><a id="hrefAddDev" href="javascript:;"><u>添加</br><a style="color:red; display:block;">(双击所选设施删除)</a></u></a></td>
					</tr>
					<tr>
						<td style="top: 495px; float: left; left: 110px; position: absolute;" class="odd"><label>&nbsp;</label><input name="btnSave" id="btnSave" type="button" class="btn" value="确认保存" />
							<button name="btnClose" id="btnClose" type="button" class="btn">取消</button></td>
					</tr>
			</table>
		</form>

		<!-- 其它页面元素 如：弹出层等-->
		<!-- 设施列表 -->
		<div id="div_devlist" class="newlayer" style="display: none;">
			<div class="tools">
				<div>
					名称或编号：<input name="textSearch" id="textSearch" type="text" />
					<button id="btnSearch" name="btnSearch" type="button" class="submit">查询</button>
				</div>
			</div>
			<!-- 查询数据列表 -->
			<table class="tablelist" id="tableDev">
				<thead>
					<tr>
						<th>设施代码<i class="sort"><img src="images/px.gif" /></i></th>
						<th>设施名称</th>
						<th>设施类型</th>
						<th>设施型号</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div class="clear"></div>
		</div>

		<!-- 用户列表 -->
		<div id="div_userlist" class="newlayer" style="display: none;">
			<!-- 查询数据列表 -->
			<table class="tablelist">
				<thead>
					<tr>
						<th>姓名</th>
						<th>性别</th>
						<th>手机号码</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userList}" var="f">
						<tr>
							<td>${f.userName}</td>
							<td>
								<c:if test="${ f.sex == '0'}">未知</c:if>
								<c:if test="${ f.sex == '1'}">男</c:if>
								<c:if test="${ f.sex == '2'}">女</c:if>
							</td>
							<td>${f.mobilePhone}</td>
							<td><a href="javascript:;" op="select" dui="${f.userId}" dun="${f.userName}" class="tablelink">选择</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clear"></div>
<%-- 			<jsp:include page="/jsp/one/common/page.jsp" /> --%>
		</div>
	</div>


</body>
</html>
