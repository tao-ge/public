<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<script  type="text/javascript" src="${path }/js/inspect/inspectList.js?cee"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<title>巡检日志查询</title>

</head>
<body >
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.htm">首页</a></li>
    <li><a href="index.htm">巡检管理</a></li>
    <li><a href="inspectList.htm">巡检日志查询</a></li>
    </ul>
    </div>
    

    
    <div class="rightinfo">
      <div class="tools">
      
      
      		<div class="simpleQuery">
      
		      <form>
		      	          巡检人：<input name="userNameSearch" id="userNameSearch" type="text" value="${inspectList.userName}" />
					设施名称:<input name="devName_Search" id="devNameSearch" type="text" value="${inspectList.devName}"/>
					设施编码:<input name="devCode_Search" id="devCodeSearch" type="text" value="${inspectList.devCode}"/>
			                          巡检状态:<select class="select3" name="inspectStatus_Search" id="inspectStatusSearch">
		      					<option value=''>全部</option>
							<option value='0' <c:if test="${'0' == inspectList.inspectStatus}">selected ="selected"</c:if>>异常</option>
							<option value='1' <c:if test="${'1' == inspectList.inspectStatus}">selected ="selected"</c:if>>正常</option>
					 	</select>
				        时间：
			       <input onClick="WdatePicker()" id="startTimeSearch" name="startTimeSearch" type="text" value="${inspectList.startTime}" />&nbsp;&nbsp;&nbsp;&nbsp;至
			       <input onClick="WdatePicker()" id="endTimeSearch" name="endTimeSearch" type="text" value="${inspectList.endTime}" />
			    		 <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
<!-- 	                	<button id="btnDisplayMoreQuery" type="button">高级查询</button> -->
		      </form>
      		</div>  
	    </div> 
    

	    <table class="tablelist">
	    	<thead>
	    	<tr>
	        <th>设施编号<i class="sort"><img src="images/px.gif" /></i></th>
	        <th>设施名称</th>
	        <th>巡检状态</th>
	        <th>描述</th>
	        <th>巡检人信息</th>
	        <th>巡检时间</th>
	        <th>位置信息</th>
	        <th>图片</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${pb.list}" var="f">
		        <tr>
		        <td>${f.devCode}</td>
		        <td>${f.devName}</td>
		        <td>
		        <c:if test="${f.inspectStatus==1}">正常</c:if>
		        <c:if test="${f.inspectStatus==0}">异常</c:if> 
		        </td>
		        <td>${f.inspectInfo}</td>
		        <td>${f.userName}</td>
		        <td><fmt:formatDate value="${f.inspectTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
		        <td><a href="javascript:;" op="view" lng="${f.baiduX}" lat="${f.baiduY}" class="tablelink">查看</a></td>
		        <td><a href="${path}/inspectImgList.htm?inspectId=${f.inspectId}&devId=${f.devId}&devName=${f.devName}" class="tablelink">查看</a></td>
		        </tr> 
       		</c:forEach>
	        </tbody>
	    </table>
    
    
 
    	<div class="clear"></div>
   	<jsp:include page="/jsp/one/common/page.jsp" />
   </div>
    
    
 
<div id="div_moreSearch"  class="newlayer" style="display:none;">
			 <form action="${path}/inspectList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
			   <table width="100%" border="1" class="table1"> 
			     <tr>
			       <td>编号：</td>
			       <td><input id="devCode" name="devCode" type="text" value="${inspectList.devCode}" /></td>
				   <td>设施名称：</td>
			       <td><input id="devName" name="devName" type="text" value="${inspectList.devName}" /></td>
		         </tr>
			     
		         <tr>
			       <td>巡检状态：</td>
			       <td>
						<select class="select3" name="inspectStatus" id="inspectStatus">
						    <option value=''>全部</option>
						    <option value='0' <c:if test="${'0' == inspectList.inspectStatus}">selected ="selected"</c:if>>异常</option>
						    <option value='1' <c:if test="${'1' == inspectList.inspectStatus}">selected ="selected"</c:if>>正常</option>
					    </select>
					</td>
					<td>用户名称：</td>
			       <td><input name="userName" id="userName" type="text" value="${inspectList.userName}" /></td>
		         </tr>
		         <tr>
			       <td>时间：</td>
			       <td><input onClick="WdatePicker()" id="startTime" name="startTime" type="text" value="${inspectList.startTime}" /></td><td>&nbsp;&nbsp;&nbsp;&nbsp;至</td>
			       <td><input onClick="WdatePicker()" id="endTime" name="endTime" type="text" value="${inspectList.endTime}" /></td>
		         </tr>

			    <tr><td>&nbsp;</td></tr>
			     
			     <tr>
			       <td colspan="4" align="center">
					<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
                   	<button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                <button type="button" name="btnExport" id="btnExport" >导出</button>
	                <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>                                                   
		       </table>
             
        </form>
      </div>
</body>
</html>