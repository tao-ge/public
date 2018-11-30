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
<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
<script type="text/javascript">
	path = '${path }';
</script>
<script  type="text/javascript" src="${path }/js/facility/facilityInspectList.js"></script>
<title>巡检日志查询</title>
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
	           	   <input name="devName" id="devName"  value="${devName}"   type="text" style="display:none"/>
	               <input name="devId"  id="devId"  value="${devId}"  type="text" style="display:none"/>
	                时间：<input name="startTimeSearch" id="startTimeSearch" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"    value="${inspectList.startTime}"   type="text" />
	                                     -<input name="endTimeSearch" id="endTimeSearch" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"   value="${inspectList.endTime}"  type="text" />
	                                      
						
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
			        <th>巡检时间</th>
			        <th>巡检记录信息</th>
			        <th>巡检状态</th>
			        <th>位置信息</th>
			        <th>用户名称</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="status">
				        <tr>
				       
				<td>${status.index + 1}</td>
				<td><fmt:formatDate value="${f.inspectTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		        <td>${f.inspectInfo}</td>
		        <td>
		        <c:if test="${f.inspectStatus==1}">正常</c:if>
		        <c:if test="${f.inspectStatus==0}">异常</c:if> 
		        </td>
		        <td><a href="javascript:;" op="view" lng="${f.longitude}" lat="${f.latitude}"   class="tablelink">查看</a></td>
		        <td>${f.userName}</td>
		        		 </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div> 
		    <jsp:include page="/jsp/one/common/page.jsp" />   
     </div>
        <div id="div_moreSearch"  class="newlayer" style="display:none;">
			 <form action="" method="post" id="moreSearchForm" name="moreSearchForm">
			   <table width="100%" border="1" class="table1"> 			     
		         <tr>
		           <td>用户名称：</td>
			       <td><input name="userName" type="text" value="${inspectList.userName}" /></td>
			       <td>巡检状态：</td>
			       <td>
						<select name="inspectStatus" id="inspectStatus">
						    <option value=''>全部</option>
						    <option value='0' <c:if test="${'0' == inspectList.inspectStatus}">selected ="selected"</c:if>>异常</option>
						    <option value='1' <c:if test="${'1' == inspectList.inspectStatus}">selected ="selected"</c:if>>正常</option>
					    </select>
					</td>

		         </tr>
		         <tr>
			       <td>时间：</td>
			       <td><input id="startTime" name="startTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${inspectList.startTime}" /></td><td>&nbsp;&nbsp;&nbsp;&nbsp;至</td>
			       <td><input id="endTime" name="endTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${inspectList.endTime}" /></td>
		         </tr>

			    <tr><td>&nbsp;</td></tr>
			     
			     <tr>
			       <td colspan="4" align="center">
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