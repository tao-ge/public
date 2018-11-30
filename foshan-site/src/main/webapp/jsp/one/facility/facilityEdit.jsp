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

	<div class="formbody">
		<form name="editForm" id="editForm" method="post"
			action="${path}/facilitySave.htm">
			<div class="formtitle">
				<span>设施基本信息</span>
			</div>
			<input id="pro" name="pro" value="${proId }" style="display: none;">
			<input id="proName" name="proName" value="${proName }" style="display: none;">
			<table class="tableForm">
				<tbody>
					<tr>
					    <td class="odd">设施名称：</td>
						<td colspan="4"><input id="devName" name="devName"
							style="width: 550px" type="text" datatype="*1-200"
							errormsg="设施名称至少1个字符,最多200个字符！" value="${facility.devName}" /><span
							style="color: red; display: inline">* 必填</span></td>
					</tr>
					<tr>

						<td class="odd">设施类型：</td>
						<td><select id="devType" name="devType">
								<c:forEach items="${deviceTypeList}" var="item"
									varStatus="status">
									<option value="${item.name}"
										<c:if test="${item.name == facility.devType}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
						</select></td>
						<td class="odd">设施型号：</td>
						<td><input name="devModel" type="text"
							value="${facility.devModel}" /></td>

					</tr>

					<tr>

						<td class="odd">竣工日期：</td>
						<td><input id="completeDate" name="completeDate" type="text"
							onClick="WdatePicker()" value="${date}" /></td>
						<td class="odd">详情地址：</td>
						<td><input id="devAddr" name="devAddr" type="text"
							value="${facility.devAddr}" /></td>

					</tr>
					<tr>

						<td class="odd">经度：</td>
						<td><input id="longitude" name="longitude" type="text"
							value="${facility.longitude}" /></td>
						<td class="odd">纬度：</td>
						<td><input id="latitude" name="latitude" type="text"
							value="${facility.latitude}" /></td>

					</tr>
					<tr>
						<td >附近特征描述：</td>
						<td><input id="devDesc" name="devDesc" type="text"
							value="${facility.devDesc}" /></td>
					</tr>
					<tr>
						<td class="odd">所属区：</td>
							<td><select class="areaCode1" name="areaCode1" id="areaCode1"  selected="selected">
			     			<option value="">全部</option>
			     			<c:forEach items="${areasList}" var="area" >
			     					<option value="${area.name}" ${area.name == facility.areaCode?"selected":""}>${area.value}</option>
			     			</c:forEach>
			     		</select>
			     		</td>
		     		
			     		<td class="odd">所属汇聚区：</td>
							<td><select name="areaCode2" class="hjq" id="areaCode2" selected="selected">
				     		<c:if test="${name == facility.areaCode2}">selected ="selected"</c:if>
				     	</select>
				     	</td>
					</tr>
					<tr>

						<td class="odd">所属机房：</td>
						<td><input id="room" name="room" type="text"
							value="${facility.room}" /></td>
						<%-- <td class="odd">所属站点：</td>
						<td><input id="site" name="site" type="text"
							value="${facility.site}" /></td> --%>
						<td class="odd">设施状态：</td>
						<td>
						<select name="devState" id="devState">
								<c:forEach items="${devStateList}" var="item" varStatus="status">
									<option value="${item.key}"<c:if test="${item.key == facility.devState}">selected ="selected"</c:if>>
										${item.value}
									</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>

						<td class="odd">所属工程：</td>
						<td><select id="seleProId" style="width: 100%;">
								<c:if test="${proId!=null && proId!='' }">
									<option value="${proId}">${proName }</option>
								</c:if>
								
									<option value="-1">---</option>
								
							</select> 
							<input id="proId" name="proId" type="hidden" value="${facility.proId}" />
						</td>
						<td class="odd">核查状态：</td>
						<td><select name="surveyResult" id="surveyResult">

								<c:forEach items="${surveyResultList}" var="item"
									varStatus="status">
									<option value="${item.name}"
										<c:if test="${item.name == facility.surveyResult}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
						</select></td>

					</tr>

					<tr>
<%-- 						<c:if test="${facility.devId!=null }"> --%>
<!-- 						<td class="odd">有效标志：</td> -->
<!-- 						<td><select name="flag" id="flag"> -->
<%-- 								<c:forEach items="${flagMap}" var="item" --%>
<%-- 									varStatus="status"> --%>
<%-- 									<option value="${item.key}" --%>
<%-- 										<c:if test="${item.key == facility.flag}">selected ="selected"</c:if>>${item.value}</option> --%>
<%-- 								</c:forEach> --%>
								
<!-- 						</select></td> -->
<%-- 						</c:if> --%>
						<td class="odd">巡检状态：</td>
						<td><select name="checkType" id="checkType">
								<c:forEach items="${CheckTypeMap}" var="item"
									varStatus="status">
									<option value="${item.key}"
										<c:if test="${item.key == facility.checkType}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
								
						</select></td>

					</tr>
					

					<tr>
						<td style="text-align: center;" colspan="4">
						    <input id="devId" name="devId" type="hidden"   value="${facility.devId}" />
						    <input id="devCode" name="devCode" type="hidden"   value="${facility.devCode}" />
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