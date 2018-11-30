<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script  type="text/javascript" src="${path }/js/deviceStatus/deviceDetailStatusList.js?ces"></script>
<title>设施管理</title>
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
 
		    </ul>
		  	<div class="simpleQuery">
	           <form>
	           	   <input name="devNameSearch" id="devNameSearch"  value="${devName}"   type="text" style="display:none"/>
	               <input name="devId"  id="devId"  value="${devId}"  type="text" style="display:none"/>
	                采集时间：<input name="startTime_search" id="startTimeSearch" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"    value="${devStaCon.startTime}"   type="text" />
	                                     -<input name="endTime_search" id="endTimeSearch" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"    id="endTimeSearch"  value="${devStaCon.endTime}"  type="text" />
	                                      
						
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	                <button id="btnDisplayMoreQuery" type="button">高级查询</button>
	           </form>
	        </div>  
	     </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
			        <th>序号</th>
			        <th>设施名称</th>
			        <th>操作方式</th>
			        <th>锁A状态</th>
			        <th>门A状态</th>
			        <th>锁B状态</th>
			        <th>门B状态</th>
			        <th>温度</th>
			        <th>倾斜</th>
			        <th>电量</th>
			        <th>信号</th>
			        <th>采集时间</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="status">
				        <tr>
							<td>${status.index + 1}</td>
					        <td>${f.devName}</td>
					        <td>
					        	<c:if test="${f.oprStyle=='0'}">关锁</c:if>
					        	<c:if test="${f.oprStyle=='1'}">蓝牙开关锁</c:if>
					        	<c:if test="${f.oprStyle=='2'}">远程开锁</c:if>
					        	<c:if test="${f.oprStyle=='3'}">其他开锁</c:if>
					        	<c:if test="${f.oprStyle=='4'}">定时上报</c:if>
					        	<c:if test="${f.oprStyle=='5'}">报警数据</c:if>
					        </td>
					        <td> 
					        	<c:if test="${f.lockOpen01=='0'}">关</c:if>
					        	<c:if test="${f.lockOpen01=='1'}">开</c:if>
					        	<c:if test="${f.lockOpen01=='2'}">未知</c:if>
					        </td>
					        <td> 
					        	<c:if test="${f.doorOpen01=='0'}">关</c:if>
					        	<c:if test="${f.doorOpen01=='1'}">开</c:if>
					        	<c:if test="${f.doorOpen01=='2'}">未知</c:if>
					        </td>
					        <td> 
					        	<c:if test="${f.lockOpen02=='0'}">关</c:if>
					        	<c:if test="${f.lockOpen02=='1'}">开</c:if>
					        	<c:if test="${f.lockOpen02=='2'}">未知</c:if>
					        </td>
					        <td> 
					        	<c:if test="${f.doorOpen02=='0'}">关</c:if>
					        	<c:if test="${f.doorOpen02=='1'}">开</c:if>
					        	<c:if test="${f.doorOpen02=='2'}">未知</c:if>
					        </td>
					        <td>${f.temp}</td>
					        <td>${f.tilt}</td>
					        <td>${f.battery}</td>
					        <td>${f.signals}</td>
					        <td><fmt:formatDate value="${f.colTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		        		</tr>
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
			       <td>操作方式：</td>
			       <td>
			       		 <select name="oprStyle" id="oprStyle" >
				          <option value="">全部</option>
	                      <c:forEach  items="${oprStyleMap}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == devStaCon.oprStyle}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
			       </td>			     
		         </tr>
			     <tr style="display:none">
			       <td>设施编码：</td>
			       <td><input id="devCode" name="devCode" type="text"  value="${devStaCon.devCode}" /></td>			     
		         </tr>
			     <tr >
			       <td>设施名称：</td>
			       <td><input id="devName" name="devName" type="text"  value="${devStaCon.devName}" /></td>
			     </tr>			
			     <tr>
			       <td>开始时间：</td>
			       <td><input name="startTime" id="startTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   value="${devStaCon.startTime}"  /></td>
			     </tr>
			     <tr>
			       <td>结束时间：</td>
			       <td><input name="endTime" id="endTime" type="text"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   value="${devStaCon.endTime}"  /></td>
			     </tr>			  
<!-- 			     <tr> -->
<!-- 			       <td>是否报警：</td> -->
<!-- 			       <td> -->
<!-- 				       <select name="alarmSign" id="alarmSign" > -->
<!-- 				          <option value="">全部</option> -->
<%-- 	                      <c:forEach  items="${isAlarmMap}" var="item" varStatus="status"> --%>
<%-- 	                            <option value="${item.key}" <c:if test="${item.key == devStaCon.alarmSign}">selected ="selected"</c:if>>${item.value}</option> --%>
<%-- 	                      </c:forEach> --%>
<!-- 			           </select> -->
<!-- 		           </td> -->
<!-- 		         </tr> -->
		         
		         
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
     
	</div>
</body>
</html>