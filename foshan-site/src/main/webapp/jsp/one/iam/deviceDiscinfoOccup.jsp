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
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
	<link href="${path}/css/DisInfoOccup.css" rel="stylesheet" type="text/css" />
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="index.htm">首页</a></li>
	   		<li><a href="index.htm">智能设备管理</a></li>
	        <li><a href="#">设备管理</a></li>
	        <li><a href="#">端子占用情况</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	   		 <ul class="toolbar">
		        <li ><a id="" href="${path}${route}"><span></span>返回</a></li>
		        <div class="simpleQuery" >
		        	&nbsp;该设施端子状态： <img src="${path}/images/circle_green.png" style="width: 3%;height: 3%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;上报端子占用</a>
		        	<img src="${path}/images/circle_red.png" style="width: 3%;height: 3%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;中控器未上报</a>
		        	<img src="${path}/images/circle_blue.png" style="width: 3%;height: 3%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;上报该端子空闲</a>
		        	<img src="${path}/images/circle_gray.png" style="width: 3%;height: 3%;vertical-align: middle;" ><a style="vertical-align: middle;">&nbsp;端子未绑定中控器</a>
	          	</div>
		 	</ul>
	        <div class="simpleQuery">
	           <form action="${path}/queryDeviceDisinfoOccup.htm" method="post" id="moreSearchForm" name="moreSearchForm">
	          		<%-- <img src="${path}/images/circle_green.png" style="width: 4%;height: 4%;" ><a>空闲</a> --%>
	          		<input id="route" name="route" value="${route }" type="hidden">
	           		设施名称：<select name="devIds" id="devIds">
	                          <c:forEach  items="${devNames}" var="item" varStatus="status">
	                             <option value="${item.devId}" ${item.devId == devId?"selected":""}>${item.devName}</option>
	                          </c:forEach>
					  </select> 
					  <input type="hidden" id="codId" value="${codId }">
	           		<input  id="" value="查询" class="submit" onclick="Query()" type="button">
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <div>
	    </div>
	    <div>
			  <table class="tablelist" style="float: left;">
			     <thead>
					<tr>
						<th>序号<i class="sort"><img src="images/px.gif" /></i></th>
						<th>熔纤盘分组</th>
						<th>熔纤盘序号</th>
						<th>熔纤盘编码</th>
						<th>占用情况</th>
						<th>上报时间</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}" var="f" varStatus="i">
					<tr>
						<td>${i.count}</td>
						<td>${f.groupName}</td>
						<td>${f.discName}</td>
						<td>${f.discCode}</td>
						<td>
						<c:if test="${f.discId == null }"><!--未绑定  -->
							<c:forEach begin="1" end="${f.discPortNum }" step="1" varStatus="i" >
								 <c:if test="${i.count <= f.discPortNum/2 }">
									<div style="display: inline;">
											<div class="lastReportDataListgray">
												<c:if test="${i.count <10}">
													0${i.count} 
												</c:if>
												<c:if test="${i.count >=10}">
													${i.count} 
												</c:if>
											</div>
											<div  class="lastReportDataListgray"></div>
									</div>
								</c:if>
								<c:if test="${i.count == f.discPortNum/2 }">
									<br>&nbsp;&nbsp;
								</c:if>
								<c:if test="${i.count > f.discPortNum/2 }">
									<div style="display: inline;">
											<div class="lastReportDataListgray">
												<c:if test="${i.count <10}">
													0${i.count} 
												</c:if>
												<c:if test="${i.count >=10}">
													${i.count} 
												</c:if>
											</div>
											<div  class="lastReportDataListgray"></div>
									</div>
								</c:if>
							</c:forEach>
						</c:if> 
						<c:if test="${f.discId != null }"><!-- 已绑定 -->
							<c:if test="${f.lastReportDataList ==null or f.lastReportDataList.size()==0}"><!-- 未上报 -->
								<c:forEach begin="1" end="${f.discPortNum }" step="1" varStatus="i" >
								 <c:if test="${i.count <= f.discPortNum/2 }">
									<div style="display: inline;">
											<div class="lastReportDataListred">
												<c:if test="${i.count <10}">
													0${i.count} 
												</c:if>
												<c:if test="${i.count >=10}">
													${i.count} 
												</c:if>
											</div>
											<div  class="lastReportDataListgray"></div>
									</div>
								</c:if>
								<c:if test="${i.count == f.discPortNum/2 }">
									<br>&nbsp;&nbsp;
								</c:if>
								<c:if test="${i.count > f.discPortNum/2 }">
									<div style="display: inline;">
											<div class="lastReportDataListred">
												<c:if test="${i.count <10}">
													0${i.count} 
												</c:if>
												<c:if test="${i.count >=10}">
													${i.count} 
												</c:if>
											</div>
											<div  class="lastReportDataListgray"></div>
									</div>
								</c:if>
							</c:forEach>
							</c:if>
							<c:forEach items="${f.lastReportDataList}" var="t" varStatus="i">
								 <c:if test="${f.lastReportDataList !=null and f.lastReportDataList.size()>0}"><!-- 已上报 -->
								 <c:if test="${i.count <= f.lastReportDataSize }">
									<div style="display: inline;">
										<c:if test="${t eq '0' }">
											<div class="lastReportDataListblue">
												<c:if test="${i.count <10}">
													0${i.count} 
												</c:if>
												<c:if test="${i.count >=10}">
													${i.count} 
												</c:if>
											</div>
										</c:if>
										<c:if test="${t eq '1' }">
											<div class="lastReportDataListgree" >
												<c:if test="${i.count <10}">
													0${i.count} 
												</c:if>
												<c:if test="${i.count >=10}">
													${i.count} 
												</c:if>
											</div>
										</c:if>
										<div  class="lastReportDataListgray"></div>
									</div>
								</c:if>  
								<c:if test="${i.count == f.lastReportDataSize }">
									<br>&nbsp;&nbsp;
								</c:if>
								<c:if test="${i.count > f.lastReportDataSize }">
										<div style="display: inline;">
											<c:if test="${t eq '0' }">
												<div class="lastReportDataListblue">
													<c:if test="${i.count <10}">
													0${i.count} 
												</c:if>
												<c:if test="${i.count >=10}">
													${i.count} 
												</c:if>
												</div>
											</c:if>
											<c:if test="${t eq '1' }">
												<div class="lastReportDataListgree">
													<c:if test="${i.count <10}">
													0${i.count} 
												</c:if>
												<c:if test="${i.count >=10}">
													${i.count} 
												</c:if>
												</div>
											</c:if>
											<div  class="lastReportDataListgray"></div>
										</div>
								</c:if>
								</c:if> 
							</c:forEach>
						</c:if>
						</td>
						<td><fmt:formatDate value="${f.lastReportTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
				</tbody>
			  </table>
     </div>
    
</body>
<script type="text/javascript">
function Query(){
	var devId=$("#devIds").val();
	var codId = $("#codId").val();
	var route = $("#route").val();
	if(devId==null){
		layer.alert("该中控器未绑定检测板！",{icon:1});
	}else{
		window.location.href= path+"/queryDeviceDisinfoOccup.htm?route="+route+"&codId="+codId+"&devId="+devId;
	}
}
</script>
</html>