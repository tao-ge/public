<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script  type="text/javascript" src="${path }/js/deviceStatus/deviceStatusList.js?ces"></script>
<script type="text/javascript" src="${path }/js/facility/facilityWebSwitchList.js"></script>
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

<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">资源台账管理</a></li>
	    <li><a href="#">设施管理</a></li>
	    <li><a href="#">设施详情</a></li>
	    </ul>
    </div>
	<div class="formbody">
	
	    <div id="usual1" class="usual"> 
    	 <jsp:include page="/jsp/one/facility/facilityDetailNav.jsp" />  	
	    <!-- 查询数据列表 -->
	    

		<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
			
			</ul>
			<div>

			</div>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
					<th>智能锁IMEI</th>
					<th>智能锁蓝牙MAC地址</th>
					<th>所属设施</th>
					<th>注册状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${LockList}" var="f">
					<tr>
						<td>${f.regId}</td>
						<td>${f.devImei}</td>
						<td>${f.devMac}</td>
						<td>${f.devName}</td>
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
						<td><a href="javascript:;" op="del" rid="${f.devImei}" dt="${f.validateSign}"
							class="tablelink">   开锁</a></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>


		<div class="clear"></div>
		<jsp:include page="/jsp/one/common/page.jsp" />

	</div>
    
     </div>
	</div>
</body>
</html>