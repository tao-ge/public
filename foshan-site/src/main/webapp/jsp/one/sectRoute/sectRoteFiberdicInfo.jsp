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
	<script  type="text/javascript" src="${path }/js/sectRoute/sectRouteList.js?dawdaww"></script>	
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div >
    </div>
        <div >
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr>
			       		<th width="10%">占用情况</th>
				        <th width="10%">是否铠装尾纤</th>
				        <th width="10%">第一次是否有光</th>
				        <th width="10%">第二次是否有光</th>
				        <th width="10%">光路长度</th>
				        <th width="10%">光衰</th>
			       </tr>
		        </thead>
		        <tbody>
			     <tr>
			     	<td>
			     	<c:if test="${fiberdisc.isOccup eq 0}">未占用</c:if>
			     	<c:if test="${fiberdisc.isOccup eq 1}">已占用</c:if>
			      	</td>
			      	<td>
			      	<c:if test="${fiberdisc.isSheath eq 0}">否</c:if>
			     	<c:if test="${fiberdisc.isSheath eq 1}">是</c:if>
			     	<c:if test="${fiberdisc.isSheath eq 2}">未审核</c:if>
			      	</td>
			      	<td>
			      	<c:if test="${fiberdisc.isGlazed1 eq 0}">否</c:if>
			     	<c:if test="${fiberdisc.isGlazed1 eq 1}">是</c:if>
			     	<c:if test="${fiberdisc.isGlazed1 eq 2}">未审核</c:if>
			      	</td>
			      	<td>
			      	<c:if test="${fiberdisc.isGlazed2 eq 0}">否</c:if>
			     	<c:if test="${fiberdisc.isGlazed2 eq 1}">是</c:if>
			     	<c:if test="${fiberdisc.isGlazed2 eq 2}">未审核</c:if>
			      	</td>
			      	<td>
			      		${fiberdisc.lightLen }
			      	</td>
			      	<td>
			      		${fiberdisc.lightWane }
			      	</td>
			     </tr>
		        </tbody>
		    </table> 
		    <table class="tablelist">
		    <tr>
		    <td>文本路由</td>
		    </tr>
		    	<tr><td>${fiberdisc.routeText }<td/></tr>
		    </table>
			     
     </div>
</body>
</html>