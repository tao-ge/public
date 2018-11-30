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
<script type="text/javascript" src="${path }/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script  type="text/javascript" src="${path }/js/facility/facilityImgList.js?ces"></script>
<title>图片信息</title>
<script type="text/javascript">
$("img").lazyload({
    container: $("#container")
});
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
	           <form action="" method="post" id="moreSearchForm" name="moreSearchForm">
	           	   <input name="devName" id="devName"  value="${devName}"   type="text" style="display:none"/>
	               <input name="devId"  id="devId"  value="${devId}"  type="text" style="display:none"/>
	               <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	               <input id="imgTypesList" name="imgTypesList" value="" type="hidden" />
	               <label><input name="imgTypes1" id="imgTypes1" type="checkbox" value="01"  <c:if test="${'01' == facilityImgCon.imgTypes1}">checked="checked"</c:if>/>普查前</label>
	               <label><input name="imgTypes2" id="imgTypes2" type="checkbox" value="02"  <c:if test="${'02' == facilityImgCon.imgTypes2}">checked="checked"</c:if>/>普查后</label>
	               <label><input name="imgTypes3" id="imgTypes3" type="checkbox" value="03"  <c:if test="${'03' == facilityImgCon.imgTypes3}">checked="checked"</c:if>/>巡检</label>
	                时间：<input name="startTime" id="startTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"    value="${facilityImgCon.startTime}"   type="text" />
	                                     -<input name="endTime" id="endTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   value="${facilityImgCon.endTime}"  type="text" />
	                                      
						
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">	                
	           </form>
	        </div>  
	     </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist" id="container">
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="status">				     
				        <c:if test="${status.count eq 1 || (status.count-1) % 3 eq 0}">    
					      <tr >    
					    </c:if>    
							<td width="33%"  >
							<img id="lazy"  class="lazy" src="${path}${f.imgUrl}"  height="150" width="150"/>      				
					        <span>${f.imgDesc}</span>		        
					        <span><fmt:formatDate value="${f.photoTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					        </td>
					        					         
					    <c:if test="${status.last}" >
					    			<c:set var="num" value="${3-(status.count % 3)}"></c:set>
					    			<c:forEach begin="1" end="${num}">
					    				<td width="33%"></td>
					    			</c:forEach>
					    			</tr>
					    </c:if> 					   
					           
					    <c:if test="${status.count % 3 eq 0 || status.count eq 3}">    
					      </tr>    
					    </c:if>    
					   
		       		</c:forEach>
		       		
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" /> 
     </div>
       
     
	</div>
</body>
</html>