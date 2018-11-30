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
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript" src="${path }/js/inspect/inspectWorkList.js"></script>
<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>

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
			<li><a href="index.htm">巡检管理</a></li>
			<li><a href="inspectWorklist.htm">巡检任务管理</a></li>
		</ul>
	</div>


	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li><a id="hrefAdd" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
			</ul>
			<div>
				<form name="searchlistform" id="searchlistform" action="${path}/inspectWorklist.htm">
					巡检人：<input name="userName" id="userName" type="text" value="${userName}" /> 任务状态： <select name="workType" id="workType">
						<option value="" <c:if test="${empty sign}">selected ="selected"</c:if>>全部</option>
						<c:forEach items="${VaList}" var="item" varStatus="status">
							<option value="${item.value}" <c:if test="${item.value == sign}">selected ="selected"</c:if>>${item.name}</option>
						</c:forEach>
					</select>
					<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
					<button id="btnSearch" name="btnSearch" type="button" class="submit">查询</button>
<!-- 					<button id="btnSerchEx" name="btnSerchEx" type="button">高级查询</button> -->
				</form>
			</div>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
					<th>巡检任务名称</th>
					<th>巡检人</th>
					<th>设施名称</th>
					<th>发布时间</th>
					<th>完成时间</th>
					<th>发布人</th>
					<th>状态</th>
					<th>备注</th> 
					<th>巡检轨迹</th> 
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${DateList}" var="f">
					<tr>
						<td>${f.workId}</td>
						<td>${f.workName}</td>
						<td>${f.userName}</td>
						<td><a href="javascript:;" op="see" ids="${f.devIds}"
							class="tablelink"> 详细</a></td>
						<td><fmt:formatDate value="${f.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td><fmt:formatDate value="${f.endTime}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${f.userNameWork}</td>
						<td><c:choose>
								<c:when test="${f.workType=='0'}">
									未巡检
								</c:when>
								<c:when test="${f.workType=='1'}">
									已巡检
								</c:when>
							</c:choose></td> 
						<td>${f.remark}</td>
						<td><a href="javascript:;" op="view" lng="${f.workId}" class="tablelink">查看</a></td>
						<td>
							<a href="javascript:;" op="pic" aid="${f.workId}" class="tablelink">巡检图片</a>
							<c:if test="${f.workType=='0'}">
								<a href="javascript:;" op="edit" aid="${f.workId}" class="tablelink"> 修改</a>  
							</c:if>
							<a href="javascript:;" op="del" aid="${f.workId}"class="tablelink"> 删除</a>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>


		<div class="clear"></div>
		<jsp:include page="/jsp/one/common/page.jsp" />



	</div>
	
	<!-- 其它页面元素 如：弹出层等-->
		<!-- 高级查询 -->
		<div id="div_moreSearch" class="newlayer" style="display: none;">
			<form action="${path}/facilityaccesslistEx.htm" method="post" id="moreSearchForm" name="moreSearchForm">
				<table width="100%" border="1" class="table1">
					<tr>
						<td>被授权用户：</td>
						<td><input id="username" name="username" type="text" value="" /></td>
					</tr>
					<tr>
						<td>被授权IMEI：</td>
						<td><input id="devimei" name="devimei" type="text" value="" /></td>
					</tr>
					<tr>
						<td>被授权手机号码：</td>
						<td><input id="mobile" name="mobile" type="text" value="" /></td>
					</tr>
					<tr>
						<td>设施：</td>
						<td><input id="devname" name="devname" type="text" value="" /></td>
					</tr>
					<tr>
						<td>开始时间：</td>
						<td><input id="bdate" name="bdate" type="text" onClick="WdatePicker()" value="" /></td>
					</tr>
					<tr>
						<td>结束时间：</td>
						<td><input id="edate" name="edate" type="text" onClick="WdatePicker()" value="" /></td>
					</tr>
					<tr>
						<td>状态：</td>
						<td><select name="status" id="status">
								<option value=''>全部</option>
								<option value='0'>未开启</option>
								<option value='1'>已开启</option>
						</select></td>
						<td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<button type="button" name="btnSearchMore" id="btnSearchMore">查询</button>&nbsp;&nbsp;
							<button type="button" name="btnClear" id="btnClear">清空</button></td>
					</tr>
				</table>
			</form>
		</div>
		
		<!-- 高级查询 -->
		<div id="div_devlist" class="newlayer" style="display: none;">
			<ul id="selectDev">
			</ul>
		</div>

</body>
</html>
