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
	<script  type="text/javascript" src="${path }/js/iam/deviceEntityList.js?dadwde"></script>	
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="index.htm">首页</a></li>
	   		<li><a href="index.htm">智能设备管理</a></li>
	        <li><a href="routeList.htm">设备管理</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	        <div class="simpleQuery">
	           <form action="${path}/queryDeviceEntityList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
	           		<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	           		设施名称：<input name="devNameRoom" id="devNameRoom"  value="${device.devNameRoom}" style="width: 100px;"  type="text" />
	           		设施编码：<input name="devCodeRoom" id="devCodeRoom"  value="${device.devCodeRoom}"  style="width: 100px;" type="text" />
	           		设施类型：<select name="devType" id="devType">
						      <option value="">全部</option>
	                          <c:forEach  items="${devTypeList}" var="item" varStatus="status">
	                             <option value="${item.name}" <c:if test="${item.name == device.devType}">selected ="selected"</c:if>>${item.value}</option>
	                          </c:forEach>
						   </select> 
					设备状态： <select name="codState" id="codState">
					       <option value="">全部</option>
	                         	<c:forEach  items="${stateList}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == device.codState}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
					   </select> 
					最后上报时间：<input name="rptTimeSta" id="rptTimeSta" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"    value="${device.rptTimeSta}"   type="text" />
	                                     -<input name="rptTimeEnd" id="rptTimeEnd" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   value="${device.rptTimeEnd}"  type="text" />
	           		<input  id="btnSimpleQuery" value="查询" class="submit" type="button">
<!-- 	                <button id="btnDisplayMoreQuery" type="button">高级查询</button> -->
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr style="white-space:nowrap;">
				        <th>序号</th>
				        <th width="110px;">设备名称</th>
				        <th width="110px;">设备编码</th>
				        <!-- <th>设备IMEI</th> -->
				        <th>所在设施名称</th>
				        <th>所在设施编码</th>
				        <th>所在设施类型</th>
				        <th>设备状态</th>
				        <th>最后一次上报时间</th>
				        <th>操作</th>
			       </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="st">
				        <tr>
						    <td>${st.index + 1}</td>
						    <td>${f.codName}</td>
						    <td>${f.codCode}</td>
						   <%--  <td>${f.codImei}</td> --%>
						    <td>${f.devNameRoom}</td>
						    <td>${f.devCodeRoom}</td>
					    	<td>
						    	<c:if test="${f.devType == '01'}">光交箱</c:if>
					    		<c:if test="${f.devType == '20'}">机房</c:if>
					    	</td>
						    <td>
						    	<c:if test="${f.codState == '0'}">已绑定</c:if>
						    	<c:if test="${f.codState == '1'}">已激活</c:if>
						    	<c:if test="${f.codState == '2'}">已删除</c:if>
						    </td>
						    <td><fmt:formatDate value="${f.rptTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					        <td>
					        	<a href="${path }/queryDeviceInfo.htm?codId=${f.codId}" class="tablelink" >查看详情</a> 
					        	<a href="#" op="deviceDisinfo" did="${f.codId}" class="tablelink">端子日志</a>
					        	<a href="#" op="deviceDisinfoisOccup" did="${f.codId}" class="tablelink">端子占用情况</a>
					        </td>		  	       				        
				        </tr>
			       	</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
    
   
   
   <!-- 导出弹层下载 -->
	<div id="div_export" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
   </div>
   

</body>
</html>