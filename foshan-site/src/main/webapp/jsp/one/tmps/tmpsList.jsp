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
	<script  type="text/javascript" src="${path }/js/layer.js"></script>	
	<script type="text/javascript"> path='${path }'; </script>
	<script  type="text/javascript" src="${path }/js/tmps/tmpsList.js?ce"></script>
	<title>智能锁数据日志</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">日志管理</a></li>
	    <li><a href="tmpsList.htm">智能锁数据日志</a></li>
	    </ul>
    </div>
        
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	
	        <div class="simpleQuery">
	          	<form action="${path}/tmpsList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
	          		<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	          	</form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist"  width="100%" style="table-layout:fixed;">
		    	<thead>
			    	<tr>
				        <th width="5%">序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th width="7%">数据标识</th>
				        <th width="10%">采集时间</th>
				        <th width="7%">数据类型</th>
				        <th width="10%">上报时间</th>
				        <th width="7%">处理标记</th>
				        <th width="10%">处理时间</th>
				        <th width="39%" style="word-wrap:break-word;word-break:break-all;">数据内容</th>
				        <th width="5%">对应表</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f"  varStatus="status">
				        <tr>
				        <td>${status.index + 1}</td>
				        <td>${f.devImei}</td>
				        <td><fmt:formatDate value="${f.colTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				        <td>${f.types}</td>
				        <td><fmt:formatDate value="${f.rptTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				        <td>${f.dealSign}</td>
				        <td><fmt:formatDate value="${f.dealTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				        <td style="word-wrap:break-word;word-break:break-all;">${f.content}</td>
				        <td>${f.ownerId}</td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
    
</body>
</html>