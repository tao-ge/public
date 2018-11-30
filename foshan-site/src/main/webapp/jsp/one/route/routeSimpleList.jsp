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
	<script  type="text/javascript" src="${path }/js/route/routeList.js?ces"></script>	
	<title>设施管理</title>
</head>
<body>

       
    <div class="rightinfo">
<c:if test="${flag=='0'}">
<center>没有光路数据！</center>
</c:if>
<c:if test="${flag=='1'}">
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr>
			        <th>序号</th>
			        <th>光路名称</th>
			        <th>A端设施</th>
			        <th>Z端设施</th>
			        <th>最后修改时间</th>
			        <th>操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="st">
				        <tr>
				       
					    <td>${st.index + 1}</td>
				        <td>${f.routeName}</td>
				        <td>${f.adevName}</td>
				        <td>${f.zdevName}</td>				        
				        <td><fmt:formatDate value="${f.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
				        <td><a href="${path }/lightPathChart.htm?routeId=${f.routeId}" class="tablelink" >查看</a> </td>		       				        </tr>
			       	</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		  </c:if> 
     </div>
    


</body>
</html>