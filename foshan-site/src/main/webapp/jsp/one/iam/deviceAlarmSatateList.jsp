<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html>
<html>
<head>
 
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
	<meta http-equiv="x-ua-compatible" content="IE=10" >
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/tinyselect.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
	<script  type="text/javascript" src="${path }/js/iam/deviceAlarmSatateList.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>设备当前状态</title>
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>

</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">智能设备管理</a></li>
	   <c:choose>
	   		<c:when test="${aphanic eq 1 }"><li><a href="#">设备告警</a></li> <li><a href="#">上报数据日志</a></li></c:when>
	   		<c:when test="${aphanic == null || aphanic eq ''}"><li><a href="#">设备状态列表</a></li></c:when>
	   </c:choose>
	    </ul>
    </div>
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
		    <ul class="toolbar">
		    <c:if test="${aphanic eq 1 }">
		        <li ><a href=${path }${route }><span></span>返回</a></li>
		    </c:if>
		    </ul>
	        <div class="simpleQuery">
	           <form>
	           		<input id="aphanic1" value="${aphanic }" type="hidden">
		         	 状态类型: <select id="state">
		          	<option value="1" ${'1' eq curStatus ?"selected":"" }>当前状态</option>
		          	<option value="0" ${'0' eq curStatus ?"selected":"" }>历史状态</option>
		          </select>
		                                     中控器名称：<input name="devName_search" id="devNameSearch"  value="${deviceEntity.codName}"   type="text" />
<%-- 	                                                  设备编码：<input name="devCode_search" id="devCodeSearch"  value="${deviceEntity.codCode}"   type="text" /> --%>
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	                <input name="devId" id="devId"  value="${deviceEntity.codId}"  type="hidden" /> 
	               <!--  <button id="btnDisplayMoreQuery" type="button">高级查询</button> -->
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr style="white-space:nowrap;">
				        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>中控器名称</th>
				        <th>中控器编码</th>
				        <th>锁A状态</th>
				        <th>门A状态</th>
				        <th>锁B状态</th>
				        <th>门B状态</th>
				        <th>温度(℃)</th>
				        <th>倾斜(°)</th>
				        <th>电压(V)</th>
				        <th>信号(0-31)</th>
				        <th>最后一次上报时间</th>
<!-- 				        <th>设备状态</th>
 -->				         <c:if test="${aphanic != '1' }">
				        <th>查看</th>
				        </c:if>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="i">
				        <tr>
				        <td>${i.count}</td>
				        <td>${f.codName}</td>
				        <td>${f.codCode}</td>
				        <td>
				        <c:if test="${f.lockOpen01 eq 0}">关</c:if>
				        <c:if test="${f.lockOpen01 eq 1}">开</c:if>
				        <c:if test="${f.lockOpen01 eq 2}">未知</c:if>
				        </td>
				        <td>
				        <c:if test="${f.doorOpen01 eq 0}">关</c:if>
				        <c:if test="${f.doorOpen01 eq 1}">开</c:if>
				        <c:if test="${f.doorOpen01 eq 2}">未知</c:if>
				        </td>
				        <td>
				        <c:if test="${f.lockOpen02 eq 0}">关</c:if>
				        <c:if test="${f.lockOpen02 eq 1}">开</c:if>
				        <c:if test="${f.lockOpen02 eq 2}">未知</c:if>
				        </td>
				        <td>
				        <c:if test="${f.doorOpen02 eq 0}">关</c:if>
				        <c:if test="${f.doorOpen02 eq 1}">开</c:if>
				        <c:if test="${f.doorOpen02 eq 2}">未知</c:if>
				        </td>
				        <td>${f.temp}</td>
				      	<td>${f.tilt}</td>
				      	<td>${f.battery}</td>
				      	<td>${f.signals}</td>
				      	<td><fmt:formatDate value="${f.rptTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				        <%-- <td>
				        	<c:choose>
				        		<c:when test="${f.workState == '01'}">离线</c:when>
				        		<c:when test="${f.workState == '02'}">在线</c:when>
				        		<c:when test="${f.workState == '03'}">报警</c:when>
				        		<c:otherwise>未知</c:otherwise>
				        	</c:choose>
				        </td> --%>
				         <c:if test="${aphanic != '1' }">
				        <td>
				        	<a href="${path }/showHistoryStatus.htm?devId=${f.devId}&curStatus=${curStatus}&codName=${deviceEntity.codName}"  class="tablelink" >历史状态</a>
				        </td>
				        </c:if>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
    
	 <!-- 高级查询 -->
	 <div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form action="${path}/queryFacilityState.htm" method="post" id="moreSearchForm" name="moreSearchForm">
	        <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	        <input id="route" name="route" value="${route}" type="hidden" />
	         <input id="aphanic" name="aphanic" type="hidden" />
	        <input id="curStatus" name="curStatus" type="hidden" />
	         <input id="codId" name="codId" type="hidden" />
	         <td><input id="codName" name="codName" type="text"  value="${deviceEntity.codName}" /></td>
		
        </form>
      </div> 
		
		<div style="display:none;"><iframe id="importFrame" name="importFrame"></iframe></div>
		<!-- 导出 -->
		<div  id="div_area"  class="newlayer" style="display:none">
	 	<input type="hidden" name="areaCode2" id="areaCode2" value="${areaCode}"/>
	 	<table width="100%" border="1" class="table1">
	 		<tr>
		 		<td>所属区</td>
		 		<td>
		 		<select  id="district" class="district">
		 			<option value="">全部</option>
		 			<c:forEach items="${areaList}" var="a"> 	
		 				<option value="${a.name }">${a.value}</option>
		 			</c:forEach>
		 		</select>
		 		</td>
	 		</tr>
	 		<tr>
		 		<td>汇聚区</td>
		 		<td>
		 		<select name="areaCode3" id ="areaCode3" class="hjq1">
		 		</select>
		 		</td>
	 		</tr>
	 	</table>
	 </div>
	 
	  <!-- 导出弹层下载 -->
	<div id="div_export" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
   	</div>
	<div id="div_exportFactily" class="newlayer" style="display:none;">
	     <center> <a id="exportDownFactily" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
   	</div>
</body>

</html>
