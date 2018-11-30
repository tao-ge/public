<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript"
	src="${path }/js/facility/sonList.js?lgaaa"></script>
<title>光纤传输网管理云平台</title>
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
		 <div class="tools" style="margin:5px;">
	    	<ul class="toolbar">
		        <li ><a id="hrefAdd" href="javascript:;" dids="${devId}"><span><img src="${path}/images/t01.png" /></span>添加分光器</a></li>
		    </ul>
		    <div class="simpleQuery">
	           <form method="post"	action="${path}/sonFacilityList.htm?devId=${devId}" id="SearchForm" name="SearchForm">
	           		<input id="devName" name="devName" type="hidden" value="${devName }">
	           		设施名称: <input id="name" name="name" type="text" value="${name }">
	           		设施编码: <input id="devCode" name="devCode" type="text" value="${devCode }">
	           		   设施类型：<select name="devType" id="devType">
						      <option value="">全部</option>
	                         <c:forEach  items="${deviceTypeList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == devType}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
						  </select> 
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	           </form>
	        </div>
	     </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th>设施编码<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>设施名称</th>
				        <th>设施类型</th>
				        <th>设施型号</th>				       
				        <th>操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f">
				        <tr>
					        <td>${f.devCode}</td>
					        <td>${f.devName}</td>
					        <td>${f.devTypeName}</td>
					        <td>${f.devModel}</td>
						    <td>
						        <c:if test="${f.devType==06 }">
						        	<a href="javascript:;" op="modify" did="${f.devId}" dids="${devId}" class="tablelink">修改</a>
						        	<a href="javascript:;" op="del" did="${f.devId}" dt="${f.devName}" dids="${devId}" class="tablelink"> 删除</a>
						        </c:if>	
						        <a href="${path}/facilityList.htm?devCode=${f.devCode}&devName=${f.devName}"   class="tablelink">设施详情</a>
						    </td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
	</div>
</body>
</html>
