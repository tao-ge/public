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
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript" src="${path }/js/facility/facilityaccessList.js"></script>


<title>信息管理系统界面</title>
<script type="text/javascript">
	var path = "${path}";
	$(document).ready(function(e) {
	});
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="index.htm">首页</a></li>
			<li><a href="index.htm">智能化设备管理</a></li>
			<li><a href="facilityaccesslist.htm">用户授权管理</a></li>
		</ul>
	</div>


	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li><a id="hrefAdd" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
				<li><a id="accExport" href="javascript:;"><span><img src="${path}/images/f05.png" /></span>导出</a></li>
			</ul>
			<div>
				<form name="searchlistform" id="searchlistform" >
					被授权人：<input name="userName" id="userNameSearch" value="${accessConditionBean.userName }" type="text" /> 状态： <select name="validateStatus" id="validateStatusSearch">
						<option value="" <c:if test="${empty sign}">selected ="selected"</c:if>>全部</option>
						<c:forEach items="${VaList}" var="item" varStatus="status">
							<option value="${item.value}" <c:if test="${item.value == sign}">selected ="selected"</c:if>>${item.name}</option>
						</c:forEach>
					</select>
					<button id="btnSearch" name="btnSearch" type="button" class="submit">查询</button>
					<button id="btnSerchEx" name="btnSerchEx" type="button">高级查询</button>
				</form>
			</div>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
					<th>授权人</th>
					<th>被授权人</th>
					<th>手机号码</th>
					<th>设施</th>
					<th>授权时间</th>
					<th>结束时间</th>
					<th>状态</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${DateList}" var="f">
					<tr>
						<td>${f.accessId}</td>
						<td>${f.accessUserName}</td>
						<td>${f.userName}</td>
						<td>${f.mobilePhone}</td>
						<td><a href="javascript:;" op="see" ids="${f.devIds}"
							class="tablelink"> 详细</a></td>
						<td><fmt:formatDate value="${f.accessTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${f.validateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><c:choose>
								<c:when test="${f.validateStatus=='0'}">
									未开启
								</c:when>
								<c:when test="${f.validateStatus=='1'}">
									已开启
								</c:when>
							</c:choose></td>
						<td>${f.remark}</td>
						<td><a href="javascript:;" op="ctrl" aid="${f.accessId}" sta="${f.validateStatus}" vlt="<fmt:formatDate value='${f.validateTime}' pattern='yyyy-MM-dd' />" rmk="${f.remark}"class="tablelink">授权处理</a> 
							<a href="javascript:;" op="edit" aid="${f.accessId}" class="tablelink"> 修改</a> 
							<a href="javascript:;" op="del" aid="${f.accessId}" dt="${f.userName}"class="tablelink"> 删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<div class="clear"></div>
		<jsp:include page="/jsp/one/common/page.jsp" />



	</div>

	<!-- 授权处理 -->
	<div id="div_accessCtrl" class="newlayer" style="display: none;">
		<form action="" method="post" id="form_accessCtrl" name="form_accessCtrl">
			<table width="100%" border="1" class="table1">
				<tr>
					<td><input id="accessId" name="accessId" style="display: none" /></td>
				</tr>
				<tr>
					<td class="odd">授权结束时间：</td>
					<td><input id="validateTime" class="Wdate" onfocus="WdatePicker({readOnly:true,minDate:'%y-%M-{%d+1}'})" name="validateTime" type="text" onClick="WdatePicker()" value="<fmt:formatDate value="${fa.validateTime}" pattern="yyyy-MM-dd" />" /></td>
				</tr>
				<tr>
					<td class="odd">状态：</td>
					<td><select name="validateStatus" id="validateStatusCtrl">
							<c:forEach items="${VaList}" var="item" varStatus="status">
								<option value="${item.value}" <c:if test="${item.value == sign}"></c:if>>${item.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="odd">备注：</td>
					<td><input id="remark" name="remark" type="text" value="${fa.remark}" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input id="alarmProcessId" name="alarmProcessId" type="hidden" />
						<button type="button" id="btnAccessCtrlSave">提交处理</button></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<!-- 其它页面元素 如：弹出层等-->
		<!-- 高级查询 -->
		<div id="div_moreSearch" class="newlayer" style="display: none;">
			<form action="${path}/facilityaccesslist.htm" method="post" id="moreSearchForm" name="moreSearchForm">
				<table width="100%" border="1" class="tableForm">
					<tr>
						<td class="odd">被授权人：</td>
						<td><input id="userName" name="userName" type="text" value="${accessConditionBean.userName }" /></td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td>被授权IMEI：</td> -->
<%-- 						<td><input id="devImei" name="devImei" type="text" value="${accessConditionBean.devImei }" /></td> --%>
<!-- 					</tr> -->
					<tr>
						<td class="odd">被授权手机号码：</td>
						<td><input id="mobile" name="mobile" type="text" value="${accessConditionBean.mobile }" /></td>
					</tr>
					<tr>
						<td class="odd">设施名称：</td>
						<td><input id="devName" name="devName" type="text" value="${accessConditionBean.devName }" /></td>
					</tr>
					<tr>
						<td class="odd">查询授权时间：</td>
						<td><input id="bdateEnd" name="bdateEnd" type="text" onClick="WdatePicker()" value="${accessConditionBean.bdateEnd }" /></td>
						<td class="odd">——</td>
						<td><input id="edateEnd" name="edateEnd" type="text" onClick="WdatePicker()" value="${accessConditionBean.edateEnd }" /></td>
					</tr>
					<tr>
						<td class="odd">查询授权结束时间：</td>
						<td><input id="bdate" name="bdate" type="text" onClick="WdatePicker()" value="${accessConditionBean.bdate }" /></td>
						<td class="odd">——</td>
						<td><input id="edate" name="edate" type="text" onClick="WdatePicker()" value="${accessConditionBean.edate }" /></td>
					</tr>
					<tr>
						<td class="odd">状态：</td>
						<td>
<!-- 							<select name="status" id="status"> -->
<!-- 									<option value=''>全部</option> -->
<!-- 									<option value='0'>未开启</option> -->
<!-- 									<option value='1'>已开启</option> -->
<!-- 							</select> -->
							 <select name="validateStatus" id="validateStatus">
								<option value="" <c:if test="${empty sign}">selected ="selected"</c:if>>全部</option>
								<c:forEach items="${VaList}" var="item" varStatus="status">
									<option value="${item.value}" <c:if test="${item.value == sign}">selected ="selected"</c:if>>${item.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
					<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
						<td colspan="4" align="center">
							<button type="button" name="btnSearchMore" id="btnSearchMore">查询</button>&nbsp;&nbsp;
							<button type="button" name="btnClear" id="btnClear">清空</button></td>
					</tr>
				</table>
			</form>
		</div>
		
		<!-- 点击"详细",显示设施列表弹窗 -->
		<div id="div_devlist" class="newlayer" style="display: none;">
			<ul id="selectDev">
			</ul>
		</div>

	<!-- 导出弹层下载 -->
	<div id="div_export" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
    </div>
</body>
</html>
