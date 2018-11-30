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
	<script  type="text/javascript" src="${path }/js/logs/logsList.js?ce"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">日志管理</a></li>
	    <li><a href="logsList.htm">平台操作日志</a></li>
	    </ul>
    </div>
        
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	
	        <div class="simpleQuery">
	           <form action="${path}/logsList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
	           	操作人: <input name="userName" value="${logsList.userName }" type="text" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/>
	                                      时间：<input onClick="WdatePicker()"  name="startTime" id="startTime_Search" value="${logsList.startTime}" type="text" />
	           ————<input onClick="WdatePicker()" name="endTime" id="endTime_Search" value="${logsList.endTime}" type="text" />
	           		<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                <input name="btnSimpleQuery" value="查询" class="submit" type="submit">
	            </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>操作人</th>
				        <th>手机号码</th>
				        <th>操作日期</th>
				        <th>操作内容</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f"  varStatus="status">
				        <tr>
				        <td>${status.index + 1}</td>
				        <td>${f.userName}</td>
				        <td>${f.mobilePhone}</td>
				        <td><fmt:formatDate value="${f.oprTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				        <td>${f.logContent}</td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
    

</body>
</html>
