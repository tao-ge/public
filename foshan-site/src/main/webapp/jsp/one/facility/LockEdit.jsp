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
<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>

<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript" src="${path }/js/facility/lockEdit.js"></script>

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
			<li><a href="#">资源监控</a></li>
			<li><a href="#">智能锁新增</a></li>
		</ul>
	</div>
	<div class="formbody">
		<form name="lockregEditForm" id="lockregEditForm" action="${path}/saveLockReg.htm">
			<div class="formtitle">
				<span>基本信息</span>
			</div>

			<input name="regId" type="text" id="regId" value="${dr.regId}" style="display:none" />
			<input name="devId" type="text" id="devId" value="${dr.devId}" style="display:none" />
			<input name="devCode" type="text" id="devCode" value="${dr.devCode}" style="display:none" />
			<input name="orgId" type="text" id="orgId"  value="${dr.orgId}" style="display:none" />
			<ul class="forminfo" style="width:800px;">
				 
					<li>
						<td class="odd">&nbsp;&nbsp;&nbsp; 选择设施：</td>
						<td>
						
						<select id="selectdevId"  name="devName"  style="width: 100%;">
								<option value="-1">---</option>
						</select>
						</td>
					
					</li>
					<li>
						<td class="odd">智能锁IMEI：</td>
						<td><input name="devImei" type="text" class="dfinput" id="devImei"  value="${dr.devImei}"/></td>
					</li>
					<li>
						<td class="odd">智能锁MAC：</td>
						<td><input name="devMac" type="text" class="dfinput" id="devMac"  value="${dr.devMac}"/></td>
					</li>
					<li style="position: relative;left: 40px;">
						<td class="odd">状态：</td>
						<td><select name="validateSign" id="validateSign" style="opacity:1 !important;    border: 1px #1C91E3 solid;">
								<c:forEach items="${SignList}" var="item">
									<option value="${item.value}" <c:if test="${item.value == sign}">selected ="selected"</c:if>>${item.name}</option>
								</c:forEach>
						</select></td>
					</li>
					<li>
						<td><label>保存并继续录入</label><cite><input name="" type="checkbox" value="goon" /></cite><label>&nbsp;</label></td>
					</li>
					<li>
						<td><label>&nbsp;</label><input name="btnSave" id="btnSave" type="button" class="btn" value="确认保存" />
							<button name="btnClose" id="btnClose" type="button" class="btn">取消</button></td>
					</li>
			</ul>
		</form>

		<!-- 其它页面元素 如：弹出层等-->
		<!-- 高级查询 -->
		<div id="div_devlist" class="newlayer" style="display: none;">
			<!-- 查询数据列表 -->
			<table class="tablelist">
				<thead>
					<tr>
						<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
						<th>名称</th>
						<th>设施类型</th>
						<th>设施型号</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pb.list}" var="f">
						<tr>
							<td>${f.devCode}</td>
							<td>${f.devName}</td>
							<td>${f.devTypeName}</td>
							<td>${f.devModel}</td>
							<td><a href="javascript:;" op="choose" did="${f.devId}" dcd="${f.devCode}" dna="${f.devName}" doi="${f.orgId}" class="tablelink">选择</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clear"></div>
			<jsp:include page="/jsp/one/common/page.jsp" />
		</div>
	</div>


</body>
</html>