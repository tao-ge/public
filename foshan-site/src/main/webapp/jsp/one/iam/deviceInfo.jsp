<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<script type="text/javascript" src="${path}/js/tinyselect.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path}'; </script>
	<script  type="text/javascript" src="${path }/js/iam/deviceEntityList.js?dadwde"></script>	
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="index.htm">首页</a></li>
	   		<li><a href="index.htm">智能设备管理</a></li>
	        <li><a href="routeList.htm">设备管理</a></li>
	        <li><a href="#">设备详情</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
    	
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	<ul class="toolbar">
	    		<li ><a id="" href="${path}/queryDeviceEntityList.htm"><span></span>返回</a></li>
	    	</ul>
	    </div>
	    <!-- 查询数据列表 -->
	    <table id="newcss" class="tableDetail">
		        <tbody>
		        	<tr>
					    <span style="font-weight:bold; font-size: 30px;">基本信息</span>
					</tr>
				    <tr>
					    <td style="width: 170px;">系统版本号：</td>
						<td colspan="8">${info.hardVersion}</td>
					</tr>
					<tr>
					    <td colspan="2">中控器名称：</td>
						<td colspan="4">${info.codName}</td>
						<td colspan="2">中控器ID：</td>
						<td colspan="4">${info.codId}</td>
					</tr>
					<tr>
					    <td colspan="2">蓝牙mac地址：</td>
						<td colspan="4">${info.codMac}</td>
						<td colspan="2">IMEI：</td>
						<td colspan="4">${info.codImei}</td>
					</tr>
					<tr>
					    <td colspan="2">最后一次上报门A状态：</td>
						<td colspan="4">
							<c:if test="${info.doorOpen01=='0'}">关</c:if>
							<c:if test="${info.doorOpen01=='1'}">开</c:if>
						</td>
						<td colspan="2">最后一次上报锁A状态：</td>
						<td colspan="4">
							<c:if test="${info.lockOpen01=='0'}">关</c:if>
							<c:if test="${info.lockOpen01=='1'}">开</c:if>
						</td>
					</tr>
					<tr>
					    <td colspan="2">最后一次上报门B状态：</td>
						<td colspan="4">
							<c:if test="${info.doorOpen02=='0'}">关</c:if>
							<c:if test="${info.doorOpen02=='1'}">开</c:if>
						</td>
						<td colspan="2">最后一次上报锁B状态：</td>
						<td colspan="4">
							<c:if test="${info.lockOpen02=='0'}">关</c:if>
							<c:if test="${info.lockOpen02=='1'}">开</c:if>
						</td>
					</tr>
					<tr>
					    <td colspan="2">最后一次上报电池电压(V)：</td>
						<td colspan="4">${info.battery}</td>
						<td colspan="2">最后一次上报信号(0-31)：</td>
						<td colspan="4">${info.signals}</td>
					</tr>
					<tr>
					    <td colspan="2">最后一次上报温度：</td>
						<td colspan="4">${info.temp}</td>
						<td colspan="2">最后一次上报倾斜：</td>
						<td colspan="4">${info.tilt}</td>
					</tr>
					<tr>
					    <td colspan="2">最后一次上报时间：</td>
						<td colspan="4">
							<fmt:formatDate value="${info.rptTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td colspan="2">硬件更新返回状态：</td>
						<!--0升级成功 1发送HTTP错误 2获取HTTP失败 3下载文件失败 4文件大小错误 5打开文件失败 6文件检验码错误-->
						<td colspan="4">
							<c:if test="${info.hardState=='0'}">升级成功</c:if>
							<c:if test="${info.hardState=='1'}">发送HTTP错误</c:if>
							<c:if test="${info.hardState=='2'}">获取HTTP失败 </c:if>
							<c:if test="${info.hardState=='3'}">下载文件失败</c:if>
							<c:if test="${info.hardState=='4'}">文件大小错误</c:if>
							<c:if test="${info.hardState=='5'}">打开文件失败 </c:if>
							<c:if test="${info.hardState=='6'}">文件检验码错误 </c:if>
						</td>
					</tr>
					<tr>
					    <td colspan="2">所属业务点类型：</td>
						<td colspan="4">
							<c:if test="${info.devType=='01'}">光交箱</c:if>
							<c:if test="${info.devType=='20'}">机房</c:if>
						</td>
						<td colspan="2">所属业务点名称：</td>
						<td colspan="4">${info.devName}</td>
					</tr>
					<tr>
					    <td colspan="2">所属业务点地址：</td>
						<td colspan="4">${info.devAddr}</td>
						<td colspan="2">所属业务点区域：</td>
						<td colspan="4">${info.areaCode1}</td>
					</tr>
					<tr>
					    <td colspan="2">初始绑定人：</td>
						<td colspan="4">${info.createUserName}</td>
						<td colspan="2">初始绑定时间：</td>
						<td colspan="4">
							<fmt:formatDate value="${info.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
					<tr>
					    <td colspan="2">最后一次绑定人：</td>
						<td colspan="4">${info.lastModifyUserName}</td>
						<td colspan="2">最后一次绑定时间：</td>
						<td colspan="4">
							<fmt:formatDate value="${info.lastModifyTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					</tr>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
     </div>
    
   
   <!-- 导出弹层下载 -->
	<div id="div_export" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
   </div>
   

</body>
</html>