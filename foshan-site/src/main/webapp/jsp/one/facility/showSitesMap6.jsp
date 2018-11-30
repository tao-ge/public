<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>分光器地图</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

<style type="text/css">
body, html, #map_canvas {
	width: 100%;
	height: 100%;
	margin: 0;
}
.button {
    margin: 5px;
    padding: 13px 23px;
    border-radius: 10px;
    box-shadow: 0 3px 14px rgba(0, 0, 0, 0.4);
    font: 16px/14px Tahoma, Verdana, sans-serif;
    text-align: center;
    color: #fefefe;
    background: #1e90ff;
}

#control {
    position: absolute;
    bottom: 15px;
}
</style>
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZLT8TTF1xGqIslUb0t659pGWnduCdGVf"></script>
<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/data/points-sample-data.js"></script>
<script src="${path}/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/js/toast.js"></script>
<script type="text/javascript" src="${path}/js/layer.js"></script>
<script type="text/javascript">
	var path = "${path}";
	var areacode = '${areacode}';
	var areaName = '${areaName}';
</script>
<script type="text/javascript" src="${path}/js/webgis/bdmap.js"></script>
</head>
<body>
	<div id="map_canvas"></div>
	<div id="control">
	    <div onclick="showSearchForm(this)" class="button">高级查询</div>
	</div>
	
	<div id="div_list" class="newlayer" style="display: none;">
		<form id="mapSearchForm" action="">
		<table width="100%" border="1" class="table1" id="layterTable">
			<tr>
				<td>所属片区：</td>
				<td>
					<label for="select"></label> 
					<select class="areadevCode" name="areadevCode" id="areadevCode" runat="server" selected="selected">
							<option value="">全部</option>
							<c:forEach items="${areaList}" var="item" varStatus="status">
								<option value="${item.name}"
									<c:if test="${item.name == facilityCon.areaCode}">selected ="selected"</c:if>>${item.value}</option>
							</c:forEach>
					</select>
				</td>
				<td>汇聚区</td>
				<td><select name="areaCode1" id="areaCode1" class="hjq" runat="server" selected="selected">

				</select> 
				<input name="areaCode2" id="areaCode2" type="hidden" value="${areaCode2}" />
			</tr>
			<tr>
				<td>设施类型：</td>
				<td>
					 <select  id="devType" name="devType" >						    
						     <option value="">全部</option>
	                        <c:forEach  items="${deviceTypeList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == facilityCon.devType}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
						</select>
				</td>
				<td>设施名称：</td>
			    <td><input id="devName" name="devName" type="text"/></td>
			</tr>
			<tr>
			   <td>设施编码：</td>
			   <td><input id="devCode" name="devCode" type="text"/></td>
		       <td align="right">
                   <button type="button" id="devSearchBtn">查询</button>&nbsp;&nbsp;
                   <button type="button" name="btnClear" id="btnClear" onclick="closeSearchForm()">关闭</button>
               </td>
	         </tr> 
		</table>
		</form>
			<!-- 查询数据列表 -->
			<table class="tablelist" id="tableFacility">
				<thead>
					<tr>
						<th>设施名称</th>
						<th>设施编码</th>
						<th>设施类型</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div class="clear"></div>   
		</div>
</body>
</html>