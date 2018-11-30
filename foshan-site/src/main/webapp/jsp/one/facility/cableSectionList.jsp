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
	src="${path }/js/facility/sectList.js?latk"></script>
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
		 <div class="tools" style="margin:5px;">
	    	<ul class="toolbar">
<%-- 	    		<li ><a id="hrefAdd" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>添加</a></li> --%>
		       
		    </ul>
		    <div class="simpleQuery">
	           <form method="post"	action="${path}/cableSectionList.htm?devId=${devId}" id="SearchForm" name="SearchForm">
	               	<button type="button" id="btnExportQuery">导出</button>
	               	<input id="devName" name="devName" value="${devName }" type="hidden">
					光缆编码: <input id="sectCode" name="sectCode" value="${csb.sectCode }" type="text">
					光缆名称: <input id="secName" name="secName" value="${csb.secName }" type="text">
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	                <input type="hidden" id="devId" value="${devId}" name="devId">
	           </form>
	        </div>
	     </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th>编码<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>名称</th>
				        <th>线芯数量</th>
				        <th>A端设施</th>
				        <th>Z端设施</th>
				        <th>所属光缆</th>
				        <th>普查结果</th>
<!-- 				        <th>操作</th> -->
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${csbs}" var="f">
				        <tr>
				         <td>${f.sectCode}</td>
				        <td>${f.secName}</td>
				        <td>${f.fiberNum}</td>
				        <td>${f.devAName}</td>
				        <td>${f.devZName}</td>
				        <td>${f.cableName}</td>
				        <td>${f.surveyResultName}</td>
<%-- 				        <td> <a href="javascript:;" op="modify" did="${f.sectId}"  class="tablelink">修改</a> --%>
<%-- 				        <a href="javascript:;" op="del" did="${f.sectId}" dt="${f.secName}" class="tablelink"> 删除</a></td> --%>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
     </div>
	</div>
	</div>
	
	<!-- 导出弹层下载 -->
	<div id="div_export" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">数据生成成功，点此下载！</a></center>
   	</div>
</body>
</html>