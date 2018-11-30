<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>
	<script type="text/javascript" src="${path}/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<script type="text/javascript">
	$(function($){
		/* $('.tablelist tbody tr:odd').addClass('odd'); */
	}) 
	</script>
	
	<title>光缆管理</title>
</head>
<body  >
<!-- 	<div class="place" > -->
<!-- 	    <ul class="placeul"> -->
<!-- 		    <li><a href="#"><font size="5" style="font-weight:white;">井名称</font></a></li> -->
<%-- 		    <li><a href="#"><font size="5" color="red" style="font-weight:white;"> ${wellName}</font></a></li> --%>
<!-- 	    </ul> -->
<!--     </div> -->
    	
    <table class="tablelist" >
    	<thead>
	    	<tr>
	    		<th>列名</th>
		        <th>管道段名称</th>
		        <th>管道段物理长度</th>
		        <th>管道段地图长度</th>
		        <th>管道面</th>
		        <th>管道直径</th>
		        <th>子管数量</th>
		        <th>占用子管数量</th>
		        <th>未名占用子管数量</th>
		        <th>子孔名称</th>
		        <th>颜色名称</th>
	        </tr>
        </thead>
        <tbody>
	        <c:forEach items="${pipingSeclist}" var="f" >
		        <tr>
		        	<td style="color: red;">管道段</td>
			        <td>${f.pipingName}</td>
			        <td>${f.phyLen}</td>
			        <td>${f.mapLen}</td>
	    		</tr>
	    		<c:forEach items="${f.spaceList}" var="g">
		    		<tr class="odd">
		    			<td></td>
		    			<td></td>
		    			<td>${g.surface}</td>
					</tr>
				</c:forEach>	
				<c:forEach items="${f.pipingBeanList}" var="h">
		    		<tr class="odd">
		    			<td style="color: red;">管孔</td>
		    			<td></td>
		    			<td></td>
		    			<td></td>
		    			<td></td>
		    			<td>${h.diameter}</td>
				        <td>${h.subtubeCount}</td>
				        <td>${h.usedsubtubeCount}</td>
				        <td>${h.unusedsubtubeCount}</td>
 					</tr>
					<c:forEach items="${h.subtudeList}" var="k">
						<tr class="odd">
							<td style="color: red;">子孔</td>
			    			<td></td>
			    			<td></td>
			    			<td></td>
			    			<td></td>
			    			<td></td>
			    			<td></td>
			    			<td></td>
			    			<td></td>
		    				<td>${k.subtubeName}</td>
		    				<td>${k.colorName}</td>
						</tr>
					</c:forEach>
				</c:forEach>
       		</c:forEach>
        </tbody>
    </table>
	<div class="clear" ></div>
</body>
</html>
