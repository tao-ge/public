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
	<script type="text/javascript" src="${path }/js/iam/imdeviceLockStatus.js"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
</head>
<body>
	 <jsp:include page="/jsp/one/iam/deviceNavigation.jsp"/>  
    <div class="rightinfo">
	   
	    <!-- 查询数据列表 -->
	    <div>
	    <table class="tablelist">
		    	<thead>
			       <tr>
			        <th>设施编码</i></th>
			        <th>设施名称</th>
			        <th>数据类型</th>
			        <th>锁A状态</th>
			        <th>门A状态</th>
			        <th>锁B状态</th>
			        <th>门B状态</th>
			        <th>温度(℃)</th>
			        <th>倾斜(°)</th>
			        <th>电压(V)</th>
			        <th>信号(0-31)</th>
			        <th>自动上报时间</th>
			        <th>是否报警</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f">
				        <tr>
				<td>${f.devCode}</td>
		        <td>${f.devName}</td>
		        <td>
		        <c:choose>
		        	<c:when test="${f.oprStyle eq '0'}">关锁</c:when>
		        	<c:when test="${f.oprStyle eq '1'}">蓝牙开关锁</c:when>
		        	<c:when test="${f.oprStyle eq '2'}">远程开锁</c:when>
		        	<c:when test="${f.oprStyle eq '3'}">其他开锁</c:when>
		        	<c:when test="${f.oprStyle eq '4'}">定时上报</c:when>
		        	<c:when test="${f.oprStyle eq '5'}">报警数据</c:when>
		        </c:choose>
		        
		        </td>
		        <td>
		         <c:choose>
		        	<c:when test="${f.lockOpen01 eq '0'}">关</c:when>
		        	<c:when test="${f.lockOpen01 eq '1'}">开</c:when>
		        	<c:when test="${f.lockOpen01 eq '2'}">未知</c:when>
		        </c:choose>
		        </td>
		        <td>
		        <c:choose>
		        	<c:when test="${f.doorOpen01 eq '0'}">关</c:when>
		        	<c:when test="${f.doorOpen01 eq '1'}">开</c:when>
		        	<c:when test="${f.doorOpen01 eq '2'}">未知</c:when>
		        </c:choose>
		         </td>
		        <td>
		         <c:choose>
		        	<c:when test="${f.lockOpen02 eq '0'}">关</c:when>
		        	<c:when test="${f.lockOpen02 eq '1'}">开</c:when>
		        	<c:when test="${f.lockOpen02 eq '2'}">未知</c:when>
		        </c:choose>
		        </td>
		        <td>
		        <c:choose>
		        	<c:when test="${f.doorOpen02 eq '0'}">关</c:when>
		        	<c:when test="${f.doorOpen02 eq '1'}">开</c:when>
		        	<c:when test="${f.doorOpen02 eq '2'}">未知</c:when>
		        </c:choose>
		         </td>
		        
		        <!-- 设施报警begin -->
					<td>
			        	<a>${f.temp}</a>
					</td>
					 <td> 
			        	<a  >${f.tilt}</a>
		  			</td>
					<td>
			        	<a  >${f.battery}</a>
		        	</td>
		         <!-- 设施报警end -->
		        <td>${f.signals}</td>
		        <td><fmt:formatDate value="${f.rptTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		        	 <td>
		        	 	<c:if test="${f.alarm eq '0'}">否</c:if>
		        	 	<c:if test="${f.alarm eq '1'}">是</c:if>
		        	</td>   
		        	</c:forEach>   	
		        </tbody> 
		    </table>  
     </div>
       <div id="div_moreSearch" class="newlayer" style="display:none;">
		        <form action="${path}/queryDeviceLockStatus.htm" method="post" id="moreSearchForm" name="moreSearchForm">
				 <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
				 <input id="devId" name="devId" value="${devId}" type="hidden" />
				 <input id="devName" name="devName" value="${devName}" type="hidden" />
	        	</form>
     		</div> 
     <div class="clear"></div> 
    <jsp:include page="/jsp/one/common/page.jsp" />
    </div>
	
</body>
</html>