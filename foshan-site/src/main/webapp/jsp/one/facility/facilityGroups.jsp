<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script  type="text/javascript" src="${path }/js/facility/facilityGroups.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>

<title>信息管理系统界面</title>
<script type="text/javascript">
	var path = "${path}";
	$(function(){
		$('.tablelist tbody tr:odd').addClass('odd');
	})
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
	    <li><a href="#">设施分组信息</a></li>
	    </ul>
    </div>
	<div class="formbody">
	
	    <div id="usual1" class="usual"> 
    	 <jsp:include page="/jsp/one/facility/facilityDetailNav.jsp" />  	
	    <!-- 查询数据列表 -->
	
		<div class="rightinfo">
	   <%--  <div class="tools">
	        <div class="simpleQuery">
	           <form action="${path}/facilityGroups.htm?devId=${devId}"  method="post" id="SearchForm" name="SearchForm">
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	           </form>
	        </div>  
	    </div>   --%>  
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>分组名称</th>
				        <th>分组编码</th>
				        <th>熔纤盘数量</th>
				        <th>单盘端子数量</th>
				        <th>分组描述</th>
				        <th>创建时间</th>
				        <th>创建人</th>
				        <th>最后修改时间</th>
				        <th>最后修改人</th>
				        <th>操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${fiberGroup}" var="fg" varStatus="status">
				        <tr>
					        <td>${status.index + 1}</td>
					        <td>${fg.groupName}</td>
					        <td>${fg.side}</td>
					        <td>${fg.discNum}</td>
					        <td>${fg.portNum}</td>
					        <td>${fg.groupDesc}</td>
					        <td>
					        	<fmt:formatDate value="${fg.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					        </td>
					        <td>${fg.createUserName}</td>
					        <td>
					        	<fmt:formatDate value="${fg.lastModifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					        </td>
					        <td>${fg.lastUserName}</td>
					        <td>
					        	<a href="javascript:;" op="update" did="${fg.groupId}"  class="tablelink">修改</a>
				        		<a href="javascript:;" op="del" did="${fg.groupId}" dt="${fg.groupName}" class="tablelink"> 删除</a>
					        </td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
	</div>
</body>
</html>