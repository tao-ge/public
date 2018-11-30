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
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<script  type="text/javascript" src="${path }/js/deviceStatus/deviceStatusList.js?ces"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">监控管理</a></li>
	    <li><a href="devNewStatusList.htm">设施最新状态</a></li>
	    </ul>
    </div>
        
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	<ul class="toolbar">
		     	        
		    </ul>
	        <div class="simpleQuery">
	           <form>
	                                       设施名称：<input name="devName_search" id="devNameSearch"  value="${devStaCon.devName}"   type="text" />
	                                      设施编码：<input name="devCode_search"  id="devCodeSearch"  value="${devStaCon.devCode}"  type="text" />
	                采集时间：<input name="startTime_search" id="startTimeSearch" onClick="WdatePicker()"    value="${devStaCon.startTime}"   type="text" />
	                                     -<input name="endTime_search" id="endTimeSearch" onClick="WdatePicker()"    id="endTimeSearch"  value="${devStaCon.endTime}"  type="text" />
	                                      
						
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	                <button id="btnDisplayMoreQuery" type="button">高级查询</button>
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr>
			        <th>设施编码</i></th>
			        <th>设施名称</th>
			        <th>数据类型</th>
			        <th>锁状态</th>
			        <th>门状态</th>
			        <th>温度</th>
			        <th>湿度</th>
			        <th>倾斜</th>
			        <th>电量</th>
			        <th>信号</th>
			        <th>采集时间</th>
			        <th>是否报警</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f">
				        <tr>
				       
				       <td>${f.devCode}</td>
		        <td>${f.devName}</td>
		        <td>${f.opStyleName}</td>
		        <td>${f.lockStatusName}</td>
		        <td>${f.doorStatusName}</td>
		        <td>${f.temp}</td>
		        <td>${f.humidity}</td>
		        <td>${f.tilt}</td>
		        <td>${f.battery}</td>
		        <td>${f.signals}</td>
		        <td>
		        	<fmt:formatDate value="${f.colTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		        	
		        </td> 
		        <td><a href="#" class="tablelink"> ${f.isAlarmName}</a></td>		       				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
    
    <!-- 其它页面元素 如：弹出层等-->
	 <!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form action="" method="post" id="moreSearchForm" name="moreSearchForm">
			     <table width="100%" border="1" class="table1">
			     <tr>
			       <td>数据类型：</td>
			       <td>
			       		 <select name="oprStyle" id="oprStyle" >
				          <option value="">全部</option>
	                      <c:forEach  items="${oprStyleMap}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == devStaCon.oprStyle}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
			       </td>			     
		         </tr>
			     <tr>
			       <td>设施编码：</td>
			       <td><input id="devCode" name="devCode" type="text"  value="${devStaCon.devCode}" /></td>			     
		         </tr>
			     <tr>
			       <td>设施名称：</td>
			       <td><input id="devName" name="devName" type="text"  value="${devStaCon.devName}" /></td>
			     </tr>			
			     <tr>
			       <td>开始时间：</td>
			       <td><input name="startTime" id="startTime" type="text" onClick="WdatePicker()"   value="${devStaCon.startTime}"  /></td>
			     </tr>
			     <tr>
			       <td>结束时间：</td>
			       <td><input name="endTime" id="endTime" type="text"  onClick="WdatePicker()"   value="${devStaCon.endTime}"  /></td>
			     </tr>
			      <tr>
			       <td>门状态：</td>
			       <td>
				       <select name="doorStatus" id="doorStatus" >
				          <option value="">全部</option>
	                      <c:forEach  items="${doorAndLockStatusMap}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == devStaCon.doorStatus}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
		         <tr>
			       <td>锁状态：</td>
			       <td>
				       <select name="lockStatus" id="lockStatus" >
				          <option value="">全部</option>
	                      <c:forEach  items="${doorAndLockStatusMap}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == devStaCon.lockStatus}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
			     <tr>
			       <td>是否报警：</td>
			       <td>
				       <select name="alarmSign" id="alarmSign" >
				          <option value="">全部</option>
	                      <c:forEach  items="${isAlarmMap}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == devStaCon.alarmSign}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
		          <tr>
			       <td>报警类型：</td>
			       <td>
			       
				       <select name="alarmType" id="alarmType" >
				          <option value="">全部</option>
	                      <c:forEach  items="${alarmTypeMap}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == devStaCon.alarmType}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
			     <tr>
			       <td colspan="2" align="center">
				       <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
				       
	                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                   <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>

</body>
</html>