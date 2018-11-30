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
	<script  type="text/javascript" src="${path }/js/iam/deviceLog.js"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="index.htm">首页</a></li>
	   		<li><a href="index.htm">日志管理</a></li>
	        <li><a href="portDifferentTotals.htm">设备上报日志</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	        <div class="simpleQuery">
	           <form method="post" id="searchlistform" name="searchlistform" action="${path}/deviceLog.htm">
	           		日志内容：<input name="logContent" id="logContent"  value="${transfLogEntityBean.logContent}" style="width: 100px;"  type="text" />
	           		   时间：<input onClick="WdatePicker()"  name="startTime" id="startTime" value="${transfLogEntityBean.startTime}" type="text" />
	           ————<input onClick="WdatePicker()" name="endTime" id="endTime" value="${transfLogEntityBean.endTime}" type="text" />
	           		<input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	           		<input id="pageNo" name="pageNo" value="${pb.pageNo }" type="hidden">
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr style="white-space:nowrap;">
				        <th>序号</th>
				        <th>操作类别</th>
				        <th>操作内容</th>
				        <th>创建时间</th>
				        <th>IP地址</th>
			       </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="d" varStatus="st">
				        <tr style="word-wrap:break-word;word-break:break-all;">
						    <td>${st.index + 1}</td>
						    <td>${d.logType}</td>
						    <td width="70%;">${d.logContent}</td>
						    <td>
						    	<fmt:formatDate value="${d.logDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
						    <td>${d.ip}</td>
				        </tr>
			       	</c:forEach> 
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
</body>
</html>