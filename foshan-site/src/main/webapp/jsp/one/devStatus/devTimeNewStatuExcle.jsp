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
	<script type="text/javascript" src="${path }/js/deviceStatus/deviceTimeNewStatusExcle.js"></script>
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
	<jsp:include page="/jsp/one/devStatus/devStatusListnavigation.jsp"/>  
    <div class="rightinfo">
	   
	    <!-- 查询数据列表 -->
	    <div>
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
			        <th>自动上报时间</th>
			        <th>是否报警</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f">
				        <tr>
				       
				       <td>${f.devCode}</td>
		        <td>${f.fdevName}</td>
		        <td>${f.opStyleName}</td>
		        <td>
		        <c:choose>
			             <c:when test="${f.devStatu == 2 and f.lockStatus ==0 and f.isAlarmName eq '是'}">
			        				<a class="red">${f.lockStatusName}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a>${f.lockStatusName}</a>
			        		</c:otherwise>
			        	</c:choose>	
		        </td>
<%-- 		        <td>${f.doorStatusName}</td> --%>
				<td>
		        <c:choose>
			             <c:when test="${f.devStatu == 2 and f.doorStatus ==0 and f.isAlarmName eq '是'}">
			        				<a class="red">${f.doorStatusName}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a>${f.doorStatusName}</a>
			        		</c:otherwise>
			        	</c:choose>	
		        </td>
		        
		        <!-- 设施报警begin -->
					<td>
			  			<c:choose>
			             <c:when test="${f.tempAlarm == 0 }">
			        				<a class="red">${f.temp}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a>${f.temp}</a>
			        		</c:otherwise>
			        	</c:choose>		
					</td>
					
		             <td>
			 			<c:choose>
			             <c:when test="${f.humiAlarm == 0 }">
			        				<a class="red">${f.humidity}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a>${f.humidity}</a>
			        		</c:otherwise>
			        	</c:choose>	
		       		 </td>
					 <td> 
			        	<c:choose>
			     		 <c:when test="${f.tiltAlarm == 0 }">
			        				<a class="red">${f.tilt}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a  >${f.tilt}</a>
			        		</c:otherwise>
						</c:choose>	
		  			</td>
					<td>
						<c:choose>
			             <c:when test="${f.battAlarm == 0 }">
			        				<a class="red">${f.battery}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a  >${f.battery}</a>
			        		</c:otherwise>
			        	</c:choose>	
		        	</td>
		         <!-- 设施报警end -->
		    
		        <td>${f.signals}</td>
		        <td><fmt:formatDate value="${f.colTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		        <td><a href="#" class="tablelink"> 
		        		<c:choose>
		        			<c:when test="${f.opStyleName=='报警数据'}">报警</c:when>
		        			<c:otherwise>${f.isAlarmName}</c:otherwise>
		        		</c:choose>
		        	</a></td>		
		        	 <td>
		        	 <c:choose>
		        			<c:when test="${f.isAlarmName=='是'}">
								${f.alarmContent} 
							</c:when>
		        	</c:choose>
		        	</td>   
		        	</c:forEach>   	
		        </tbody> 
		    </table>  
     </div>
       <div id="div_moreSearch" class="newlayer" style="display:none;">
		        <form action="${path}/querydevTimeNewStatu.htm" method="post" id="moreSearchForm" name="moreSearchForm">
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