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
	<script  type="text/javascript" src="${path }/js/iam/differentDataStatistics.js"></script>	
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
	   		<li><a href="index.htm">智能设备管理</a></li>
	        <li><a href="differentDataStatistics.htm">差异数据统计</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	        <div class="simpleQuery">
	           <form method="post" id="searchlistform" name="searchlistform">
	           		<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	           		所属区 : <select class="areadevCode" id="areadevCode" name="areadevCode" runat="server" selected="selected">
				     			<option value="">全部</option>
				     			<c:forEach items="${areaList}" var="area" >
					     			<option value="${area.name}" <c:if test="${area.name==ddeBean.areaCode}">selected="selected"</c:if>>
					     				${area.value}
					     			</option>
				     			</c:forEach>
					       </select>
			    	汇聚区 : <select name="areaCode1" id="areaCode1" class="hjq" runat="server" selected="selected"></select>
	           		设施名称：<input name="devName" id="devName"  value="${ddeBean.devName}" style="width: 100px;"  type="text" />
	           		设施编码：<input name="devCode" id="devCode"  value="${ddeBean.devCode}"  style="width: 100px;" type="text" />
	           		<input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr style="white-space:nowrap;">
				        <th>序号</th>
				        <th width="110px;">设施名称</th>
				        <th width="110px;">设施编码</th>
				        <th>设施类型</th>
				        <th>普查总数</th>
				        <th>普查占用</th>
				        <th>普查空闲</th>
				        <th>真实总数</th>
				        <th>真实占用</th>
				        <th>真实空闲</th>
				        <th>占用不一致</th>
				        <th>操作</th>
			       </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="d" varStatus="st">
				        <tr>
						    <td>${st.index + 1}</td>
						    <td>${d.devName}</td>
						    <td>${d.devCode}</td>
						    <td>${d.devTypeName}</td>
						    <td>${d.fibPortNum}</td>
						    <td>${d.fibPortOccupyNum}</td>
						    <td>${d.fibPortFreeNum}</td>
						    <td>${d.portNum}</td>
						    <td>${d.portOccupyNum}</td>
						    <td>${d.portFreeNum}</td>
						    <td>${d.portErrorNum}</td>
					        <td>
					        	<a href="#" op="detail" did="${d.devId}" class="tablelink">详情</a>
					        </td>		  	       				        
				        </tr>
			       	</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
</body>
</html>