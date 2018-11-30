<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script  type="text/javascript" src="${path }/js/facility/facilitySwitchRecord.js?cesa"></script>
<script type="text/javascript">
	path = '${path }';
</script>
<script type="text/javascript"
	src="${path }/js/facility/facilityEdit.js?ce"></script>
<title>信息管理系统界面</title>
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
	    <!-- 查询数据列表 -->
	    

<div class="rightinfo">
	    <div class="tools">
	        <div class="simpleQuery">
	           <form>
	           		<input id="devIdSearch" name="devId" value="${ms.devId}" type="hidden" />
			       	<input id="devNameSearch" name="devName" value="${ms.devName}"  type="hidden" />
	                                      时间：
	                <input id="startTimeSearch"  value="${ms.startTime}" onclick="WdatePicker({skin:'whyGreen',lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" type="text" />
               		-<input id="endTimeSearch"  value="${ms.endTime}" onclick="WdatePicker({skin:'whyGreen',lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" type="text" />
						
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	                <button id="btnDisplayMoreQuery" type="button">高级查询</button>
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>用户名称</th>
				        <th>手机号码</th>
				        <th>设施编号</th>
				        <th>设施名称</th>
				        <th>操作类型</th>
				        <th>操作结果</th>
				        <th>开锁时间</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="status">
				        <tr>
				        <td>${status.index + 1}</td>
				        <td>${f.userName}</td>
				        <td>${f.mobilePhone}</td>
				        <td>${f.devCode}</td>
				        <td>${f.devName}</td>
				        <td>
					        <c:choose>
								<c:when test="${f.oprTypes=='0'}">
									关闭
								</c:when>
								<c:when test="${f.oprTypes=='1'}">
									开锁
								</c:when>
								<c:when test="${f.oprTypes=='2'}">
									应急开锁
								</c:when>
								<c:otherwise>
									其它
	   							</c:otherwise>
							</c:choose>
				        </td>
				        <td>
				        	<c:choose>
								<c:when test="${f.resultStatus=='0'}">
									异常
								</c:when>
								<c:when test="${f.resultStatus=='1'}">
									正常
								</c:when>
								<c:otherwise>
									其它
       							</c:otherwise>
							</c:choose>
				        </td>
				        <td>
				        <fmt:formatDate value="${f.colTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				        </td>
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
	       <form action="${path}/facilitySwitchList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
			   <table width="100%" border="1" class="table1">
			     <tr>
			       <td>用户名称：</td>
			       <td><input name="userName" type="text"  value="${ms.userName}" /></td>
			       <td>操作类型：</td>
			       <td><label for="select"></label>
			         <select name="oprTypes" >
			           <option value="">全部</option>
	                         <%-- <c:forEach  items="${areasServiceList}" var="item" varStatus="status">
	                            <option value="${item.areaCode}" <c:if test="${item.areaCode == facilityCon.areaCode1}">selected ="selected"</c:if>>${item.areaName}</option>
	                         </c:forEach> --%>
	                         <option value="0">关闭</option>
	                         <option value="1">开锁</option>
	                         <option value="2">应急开锁</option>
                      </select>
                   </td>
		         </tr>
			     <tr>
			       <td>手机号码：</td>
			       <td><input name="mobilePhone" type="text"  value="${ms.mobilePhone}" /></td>
			       <td>手机IMEI：</td>
			       <td><input name="devImei" value="${ms.devImei}"  type="text"/></td>
		         </tr>
			    <%--  <tr>
			       <td>设施编号：</td>
			       <td><input name="devCode" type="text"  value="${ms.devCode}" /></td>
			       <td>设施名称：</td>
			       <td><input name="devName" type="text"   value="${ms.devName}"   /></td>
		         </tr> --%>
			     <tr>
			       <td>时间：</td>
			       <td><input id="startTime" name="startTime" type="text" onclick="WdatePicker({skin:'whyGreen',lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" value="${ms.startTime}" /></td>
			       <td>-</td>
			       <td><input id="endTime" name="endTime" type="text" onclick="WdatePicker({skin:'whyGreen',lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" value="${ms.endTime}" /></td>
		         </tr>
			     <tr>
			       <td colspan="4" align="center">
			       		<input id="devId" name="devId" value="${ms.devId}" type="hidden" />
			       		<input id="devName" name="devName" value="${ms.devName}"  type="hidden" />
				       <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                   <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>





		    <div class="clear"></div>   
     </div>
	</div>
</body>
</html>