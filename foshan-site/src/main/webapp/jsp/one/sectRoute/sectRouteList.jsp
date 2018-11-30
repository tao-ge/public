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
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="index.htm">首页</a></li>
	   		<li><a href="index.htm">资源台账管理</a></li>
	        <li><a href="sectRouteList.htm">光缆数据展示</a></li>
	    </ul>
    </div>
        <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	
	        <div class="simpleQuery">
	           <form action="${path}/sectRouteList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
					光缆名称：<input  name="secName" id="secName" value="${logsList.secName}" type="text" />                   
					机房名称：<input  name="devName" id="devName_Search" value="${logsList.devName}" type="text" />
					A机柜名称：<input name="adevName" id="adevName_Search" value="${logsList.adevName}" type="text" />
	           		核查状态：<select name="sectStateall" id="sectState_all">
	                         <c:forEach  items="${SectTypeall}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == logsList.sectStateall}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
						   </select> 
					
<%-- 	           		<input id="sectStateall" name="sectStateall" value="${logsList.sectStateall}" type="hidden" /> --%>
	           		<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                <input name="btnSimpleQuery" value="查询" class="submit" type="submit">
	            </form>
	        </div>  
	    </div>  
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr style="white-space:nowrap;">
			       		<th>序号</th>
				        <th>A机房名称</th>
				        <th>A端设施名称</th>
				        <th>光缆名称</th>
				        <th>Z机房名称</th>
				        <th>Z端设施名称</th>
				        <th>光缆状态</th>
				        <th>纤芯数量</th>
<!--  				        <th>A端分组编码</th>
				        <th>A端盘数</th> -->
				        <th>操作</th>
			       </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="st">
				        <tr style="word-wrap:break-word;word-break:break-all;">     
					    <td>${st.index + 1}</td>
				        <td>${f.devName}</td>	
				        <td>${f.adevName}</td>			        
				        <td width="300px;">${f.secName}</td>
				        <td>${f.devName1}</td>
				        <td>${f.zdevName}</td>
				        <td>${f.sectState}</td>
				        <td>${f.fiberNum}</td>
<%-- 				        <td>${f.side}</td>
				        <td>${f.discNum}</td>  --%>
				        <td><a href="javascript:;" op="adevInfoEdit" did="${f.sectId}" dids="A"  class="tablelink">A端详情</a> 
				        <a href="javascript:;" op="adevInfoEdit" did="${f.sectId}" dids="Z" class="tablelink">Z端详情</a></td>		       				        </tr>
			       	</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
</body>
</html>