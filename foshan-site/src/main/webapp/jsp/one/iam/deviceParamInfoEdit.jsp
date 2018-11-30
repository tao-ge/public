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
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/iam/deviceParamInfoEdit.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
</script>
<title>信息管理系统界面</title>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">智能设备管理</a></li>
			<li><a href="#">设备配置参数</a></li>
		</ul>
	</div>
	<div class="formbody">
		<form name="addForm" id="addForm" action="${path}/saveDeviceParam.htm">
			<div class="formtitle">
				<span>${orgName }</span>
			</div>
			<table class="tableForm">
				<tbody>
					<tr>
						<input id="paramId" name="paramId" value="${deviceParam.paramId }" type="hidden">
						<td>温度阈值(度)：</td>
						<td><input id="lowTempThd"  name="lowTempThd" type="text"  style="width: 100px;"   value="${deviceParam.lowTempThd }"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;至</td>
						<td><input id="highTempShd"  name="highTempShd" type="text" style="width: 100px;" value="${deviceParam.highTempShd }"/></td>
					</tr>
					<tr>
						<td>电压阈值(伏)：</td>
						<td>
							<input id="batteryThd" name="batteryThd" style="width: 100px;" value="${deviceParam.batteryThd }"  type="text" />
				        </td>
				        <td>倾斜度阈值(度)：</td>
						<td>
							<input id="tilt" name="tilt" style="width: 100px;" value="${deviceParam.tilt }"  type="text" />
				        </td>
					</tr>
					<tr>
						<td>休眠时间(分钟)：</td>
						<td>
							<input id="dormantTime" name="dormantTime" style="width: 100px;" value="${deviceParam.dormantTime }"  type="text" />
				        </td>
				        <td>自检间隔时间(分钟)：</td>
						<td>
							<input id="selfCheckingTime" name="selfCheckingTime" style="width: 100px;" value="${deviceParam.selfCheckingTime }"  type="text" />
				        </td>
					</tr>
					<tr>
						<td>定时上报间隔时间(分钟)：</td>
						<td>
							<input id="reportTime" name="reportTime" style="width: 100px;" value="${deviceParam.reportTime }"  type="text" />
				        </td>
				        <td>门锁异常报警时间(秒)：</td>
						<td>
							<input id="lockAbnorTime" name="lockAbnorTime" style="width: 100px;" value="${deviceParam.lockAbnorTime }"  type="text" />
				        </td>
					</tr>
					<tr>
						<td>轮询时间(秒)：</td>
						<td>
							<input id="pollTime" name="pollTime" style="width: 100px;" value="${deviceParam.pollTime }"   type="text" />
				        </td>
					</tr>
					<tr>
						<td>通讯IP：</td>
						<td>
							<input id="ip" name="ip"  style="width: 100px;" value="${deviceParam.ip }"  type="text" />
				        </td>
				        <td>通讯端口：</td>
						<td>
							<input id="port" name="port" style="width: 100px;" value="${deviceParam.port }"  type="text" />
				        </td>
					</tr>
					<tr>
						<td style="top: 450px; float: left; left: 130px; position: absolute;" class="odd"><label>&nbsp;</label>
						<input name="btnSave" id="btnSave" type="button" class="btn" value="确认保存" />
<!-- 							<button name="btnClose" id="btnClose" type="button" class="btn">取消</button> -->
						</td>
					</tr> 
			</table>
		</form>

		
	</div>


</body>
</html>
