<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
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
		
		$("a[op='view']").on("click",openWinMap);
	});
	
	/*
	 * 打开地图窗口 
	 */
	function openWinMap(){
		var lng = $(this).attr("lng");
    	var lat = $(this).attr("lat");
    	var banjing = 500;
	    if(lng == ''&& lat==''){
		    alert("无位置信息！");
		    return;
	    }
		ymPrompt.win({message:'${path }/jsp/one/facility/showMap.jsp?plng='+lng+'&plat='+lat+'&pbanjing='+banjing,
				width:700,height:500,title:'位置信息',handler:null,iframe:true,btn:[['确定','yes'],['关闭','cancel']]});
	}
</script>
</head>
<body>

<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">智能设备管理</a></li>
	    <li><a href="#">故障告警</a></li>
	    <li><a href="#">报警详情</a></li>
	    </ul>
    </div>
	<div class="formbody">
		<ul class="toolbar">
		        <li ><a id="" href="javascript:history.go(-1)"><span></span>返回</a></li>
		 </ul>
	    <div id="usual1" class="usual"> 
		<form name="editForm" id="editForm" method="post"action="${path}/">		
			<table id="newcss" class="tableDetail">
				<tbody >
					<tr >
					    <td colspan="4" style="font-size: large;text-align: left;font-weight: bold;">基本信息：</td>
					</tr>
					<tr>
					    <td >系统版本号：</td>
						<td >${deviceAlarmEntity.hardVersion}</td>
					</tr>
					 <tr>
					    <td class="odd" width="100px">中控器名称：</td>
						<td width="400px">${deviceAlarmEntity.codName}</td>
						 <td class="odd"  width="100px">中控器ID：</td>
						<td >${deviceAlarmEntity.codId}</td>
						
					</tr>
					<tr>

						<td class="odd" width="100px">蓝牙Mac地址：</td>
						<td width="400px">${deviceAlarmEntity.codMac}</td>
						<td class="odd"  width="100px">IMEI：</td>
						<td >${deviceAlarmEntity.codImei}</td>

					</tr>

					<tr>

						<td class="odd">门A状态：</td>
						<td>
							<c:if test="${deviceAlarmEntity.doorOpen01 eq '0'}">
								开
							</c:if>
							<c:if test="${deviceAlarmEntity.doorOpen01 eq '1'}">
								关
							</c:if>
							<c:if test="${deviceAlarmEntity.doorOpen01 eq '2'}">
								未知
							</c:if>
						</td>
						<td class="odd">锁A状态：</td>
						<td>
							<c:if test="${deviceAlarmEntity.lockOpen01 eq '0'}">
								开
							</c:if>
							<c:if test="${deviceAlarmEntity.lockOpen01 eq '1'}">
								关
							</c:if>
							<c:if test="${deviceAlarmEntity.lockOpen01 eq '2'}">
								未知
							</c:if></td>

					</tr>
					<tr>

						<td class="odd">门B状态：</td>
						<td>
							<c:if test="${deviceAlarmEntity.doorOpen02 eq '0'}">
								开
							</c:if>
							<c:if test="${deviceAlarmEntity.doorOpen02 eq '1'}">
								关
							</c:if>
							<c:if test="${deviceAlarmEntity.doorOpen02 eq '2'}">
								未知
							</c:if>
						</td>
						<td class="odd">锁B状态：</td>
						<td>
							<c:if test="${deviceAlarmEntity.lockOpen02 eq '0'}">
								开
							</c:if>
							<c:if test="${deviceAlarmEntity.lockOpen02 eq '1'}">
								关
							</c:if>
							<c:if test="${deviceAlarmEntity.lockOpen02 eq '2'}">
								未知
							</c:if></td>

					</tr>
					<tr>

						<td class="odd">电池电压(V)：</td>
						<td>${deviceAlarmEntity.battery}</td>
						<td class="odd">信号(0-31)：</td>
						<td>${deviceAlarmEntity.signals}</td>

					</tr>
					<tr>

						<td class="odd">温度：</td>
						<td>${deviceAlarmEntity.temp}</td>
						<td class="odd">倾斜：</td>
						<td>${deviceAlarmEntity.tilt}</td>

					</tr>
					<tr>
						<td class="odd">是否水浸：</td>
						<td> ${deviceAlarmEntity.alarmTypes eq '02'?"是":"否"}</td>
						<td class="odd">处理人：</td>
						<td> ${deviceAlarmEntity.userName}</td>
					</tr>
					<tr>

						<td class="odd">上报时间：</td>
						<td>
						<fmt:formatDate value="${deviceAlarmEntity.rptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td class="odd">处理内容：</td>
						<td> ${deviceAlarmEntity.dealSituation}</td>

					</tr>
					<tr>

						<td class="odd">业务点类型：</td>
						<td>${deviceAlarmEntity.devType}</td>
						<td class="odd">所在业务点：</td>
						<td>${deviceAlarmEntity.devName}</td>
						<%-- <td>
						<c:choose>
							<c:when test="${facility.devState eq 0}">未核对</c:when>
							<c:when test="${facility.devState eq 1}">正常</c:when>
							<c:when test="${facility.devState eq 2}">新增</c:when>
							<c:when test="${facility.devState eq 3}">修改</c:when>
							<c:when test="${facility.devState eq 4}">资管删除</c:when>
							<c:when test="${facility.devState eq 5}">新增删除</c:when>
						</c:choose>
						</td> --%>
					</tr>
					<tr>

						<td class="odd">业务点地址：</td>
						<td>${deviceAlarmEntity.devAddr}</td>
						<td class="odd">业务点区域： </td>
						<td>${deviceAlarmEntity.areaName}</td>

					</tr>

					 <tr>

						<td class="odd">初始绑定人：</td>
						<td>${deviceAlarmEntity.createUserName}</td>
						<td class="odd" >初始绑定时间：</td>
						<td>
						<fmt:formatDate value="${deviceAlarmEntity.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>

					</tr>
					<tr>
					
						<td class="odd">最后绑定人：</td>
						<td>${deviceAlarmEntity.lastModifyUserName}</td>
						<td class="odd" >绑定时间：</td>
						<td>
						<fmt:formatDate value="${deviceAlarmEntity.lastModifyTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						
					</tr>
				</tbody>
			</table>

		</form>
	</div>
	</div>
</body>
</html>