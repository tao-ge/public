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
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript" src="${path }/js/facility/lockList.js"></script>
<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
</script>
<style type="text/css">
	
    #yanzheng {
    position: relative;
    display: inline-block;
    background: #AAAAFF;
    border: 1px solid #4A4AFF;
    border-radius: 4px;
    padding: 7px 25px;
    overflow: hidden;
    color: #000000;
    text-decoration: none;
    text-indent: 1;
    line-height: 20px;
    cursor:pointer;
	}
	.file input {
	    position: absolute;
	    font-size: 100px;
	    right: 0;
	    top: 0;
	    opacity: 0;
	}
	.file:hover {
	    background: #AADFFD;
	    border-color: #78C3F3;
	    color: #004974;
	    text-decoration: none;
	}
</style>
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
			<li><a href="LockList.htm">设备管理</a></li>
		</ul>
	</div>


	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
<%-- 				<li class="click"><a id="facilityImport" href="javascript:;"><span><img src="${path}/images/t02.png" /></span>选择程序文件</a></li> --%>
			</ul>
			<div>
				<form name="searchlistform" id="searchlistform" action="${path}/LockList.htm">
					设备名称：<input id="devName" name="devName" type="text" value="${dr.devName}" />
					IMEI：<input id="devImei" name="devImei" type="text" value="${dr.devImei}" />
					MAC地址：<input id="devMac" name="devMac" type="text" value="${dr.devMac}" />
					所属设施：<input id="fName" name="fName" type="text" value="${dr.fName}" />
					设备状态： <%-- <select name="validateSign" id="validateSign">
						<option value="" <c:if test="${empty sign}">selected ="selected"</c:if>>全部</option>
						<c:forEach items="${SignList}" var="item" varStatus="status">
							<option value="${item.value}" <c:if test="${item.value == sign}">selected ="selected"</c:if>>${item.name}</option>
						</c:forEach>
					</select> --%>
					<select name="validateSign" id="validateSign" style="width: 100px;">
						<option value="" <c:if test="${empty sign}">selected ="selected"</c:if>>全部</option>
						<option value="1" <c:if test="${'1' == sign}">selected ="selected"</c:if>>已注册</option>
						<option value="2" <c:if test="${'2' == sign}">selected ="selected"</c:if>>已激活</option>
					</select>
					布防状态：
					<select name="redeployingType" id="redeployingType" style="width: 100px;">
						<option value="" <c:if test="${empty redeployingType}">selected ="selected"</c:if>>全部</option>
						<option value="0" <c:if test="${'0' == redeployingType}">selected ="selected"</c:if>>未布防</option>
						<option value="1" <c:if test="${'1' == redeployingType}">selected ="selected"</c:if>>布防</option>
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
					<th>所属设施名称</th>
					<th>设备名称</th>
					<th>智能锁IMEI</th>
					<th>智能锁蓝牙MAC地址</th>
					<th>设备状态</th>
					<th>布防状态</th>
					<th>注册激活时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${LockList}" var="f">
					<tr>
						<td>${f.regId}</td>
						<td>${f.fName}</td>
						<td>${f.devName}</td>
						<td>${f.devImei}</td>
						<td>${f.devMac}</td>
						<td><c:choose>
								<c:when test="${f.validateSign=='0'}">
									未注册
								</c:when>
								<c:when test="${f.validateSign=='1'}">
									已注册
								</c:when>
								<c:otherwise>
									已激活
       							</c:otherwise>
							</c:choose></td>
						<td>${f.redeployingType eq '0'?"未布防":"布防"}</td>	
						<td><fmt:formatDate value="${f.regTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>
							<!-- <a href="javascript:;" op="import" class="tablelink"> 更新硬件文件</a> -->
							
							<a href="javascript:;" op="redeploying" rid="${f.regId}" dt="${f.devName}" tid="${f.redeployingType }" imei="${f.devImei }" pal="${f.devPlatform }" class="tablelink"> ${f.redeployingType eq '0'?"布防":"撤防"}</a>
							<a href="javascript:;" op="del" rid="${f.regId}" dt="${f.devName}" class="tablelink"> 删除</a>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
<!-- 导入Excel -->
		<div id="div_import" title="导入设施" class="newlayer" style="display:none">
			<table class="table1">
				<tr>
				    <td>选择文件：</td>
				    <td>
				        <input type="file" id="importExcel" name="importExcel" />
				    </td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input  id="yanzheng" value="确定" class="submit" type="button" >
					</td>
				</tr>
			</table>
		</div>

		<div class="clear"></div>
		<jsp:include page="/jsp/one/common/page.jsp" />



		<!-- 其它页面元素 如：弹出层等-->
		<!-- 高级查询 -->
		<div id="div_moreSearch" class="newlayer" style="display: none;">
			<form action="${path}/LockList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
				<table width="100%" border="1" class="table1">
					<tr>
						<td>IMEI：</td>
						<td><input id="devImei" name="devImei" type="text" value="" /></td>
					</tr>
					<tr>
						<td>MAC地址：</td>
						<td><input id="devMac" name="devMac" type="text" value="" /></td>
					</tr>
					<tr>
						<td>所属设施：</td>
						<td><input id="fName" name="fName" type="text" value="" /></td>
					</tr>
					<tr>
						<td>注册状态：</td>
						<td><select name="validateSign" id="validateSign">
								<option value=''>全部</option>
								<option value='0'>未注册</option>
								<option value='1'>已注册</option>
								<option value='2'>已激活</option>
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
	</div>


</body>
</html>
