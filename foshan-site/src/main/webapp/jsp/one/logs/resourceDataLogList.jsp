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
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script  type="text/javascript" src="${path }/js/logs/resourceDataLogList.js"></script>	
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">日志管理</a></li>
	    <li><a href="#">资管校准日志</a></li>
	    </ul>
    </div>
        
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	
	        <div class="simpleQuery">
	           <form method="post" id="searchForm" name="searchForm">
	           		日志类型：<select id="resLogType1" name="resLogType1">
		     			<option value="">全部</option>
		     			<option value="01" <c:if test="${rdl.resLogType == '01'}">selected ="selected"</c:if>>设施</option>
		     			<option value="02" <c:if test="${rdl.resLogType == '02'}">selected ="selected"</c:if>>分组</option>
		     			<option value="03" <c:if test="${rdl.resLogType == '03'}">selected ="selected"</c:if>>熔纤盘</option>
		     		</select>
			     	操作类型：<select name="handleType1" id="handleType1">
			     		<option value="">全部</option>
			     		<option value="0" <c:if test="${rdl.handleType == '0'}">selected ="selected"</c:if>>新增</option>
			     		<option value="1" <c:if test="${rdl.handleType == '1'}">selected ="selected"</c:if>>修改</option>
			     		<option value="2" <c:if test="${rdl.handleType == '2'}">selected ="selected"</c:if>>删除</option>
			     	</select>
	                                         操作人：<select name="modifyUserName1" id="modifyUserName1">
			     		<option value="">全部</option>
			     		<c:forEach items="${userList }" var="u">
			     			<option value="${u.userName }"
			     				<c:if test="${rdl.modifyUserName == u.userName }">selected ="selected"</c:if>
			     			>${u.userName }</option>
			     		</c:forEach>
			     	</select>
	                <button id="btnSearch" name="btnSearch" type="button" class="submit">查询</button>
	                <button id="btnMoreQuery" type="button">高级查询</button>
	            </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr style="white-space:nowrap;">
				        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>日志类型</th>
				        <th>操作类型</th>
				        <th>操作前内容</th>
				        <th>操作后内容</th>
				        <th>操作人</th>
				        <th>操作时间</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="r"  varStatus="status">
				        <tr>
				        <td>${status.index + 1}</td>
				        <td>
				        	<c:if test="${r.resLogType == '01'}">设施</c:if>
				        	<c:if test="${r.resLogType == '02'}">分组</c:if>
				        	<c:if test="${r.resLogType == '03'}">熔纤盘</c:if>
				        </td>
				        <td>
				        	<c:if test="${r.handleType == '0'}">新增</c:if>
				        	<c:if test="${r.handleType == '1'}">修改</c:if>
				        	<c:if test="${r.handleType == '2'}">删除</c:if>
				        </td>
				        <td>${r.hisContent}</td>
				        <td>${r.nowContent}</td>
				        <td>${r.modifyUserName}</td>
				        <td><fmt:formatDate value="${r.modifyTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
	       <form action="${path}/queryResourceDataLogList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
			   <table width="100%" border="1" class="table1">
			     <tr>
			       <td>日志类型：</td>
			       <td><label for="select"></label>
			         <select id="resLogType" name="resLogType">
			           <option value="">全部</option>
			           <option value="01">设施</option>
		     		   <option value="02">分组</option>
		     		   <option value="03">熔纤盘</option>
                     </select>
                   </td>
                   <td>操作类型：</td>
			 		<td>
				 		<select name="handleType" id="handleType"> 
				 			<option value="">全部</option>
				 			<option value="0">新增</option>
				     		<option value="1">修改</option>
				     		<option value="2">删除</option>
				 		</select>
			 		</td>
		         </tr>
			     <tr>
			       <td>操作前内容：</td>
			       <td><input id="hisContent" name="hisContent" type="text"  value="${rdl.hisContent }" /></td>
			       <td>操作后内容：</td>
			       <td><input id="nowContent" name="nowContent" type="text"  value="${rdl.nowContent }" /></td>
		         </tr>
		         <tr>
					<td>开始时间：</td>
					<td><input id="dateStart" name="dateStart" type="text" onClick="WdatePicker()" value="${rdl.dateStart }" /></td>
					<td>结束时间：</td>
					<td><input id="dateEnd" name="dateEnd" type="text" onClick="WdatePicker()" value="${rdl.dateEnd }" /></td>
				 </tr>
			     <tr>
			      <td>操作人名称：</td>
			      <td>
						<select name="modifyUserName" id="modifyUserName">
				     		<option value="">全部</option>
				     		<c:forEach items="${userList }" var="u">
				     			<option value="${u.userName }">${u.userName }</option>
				     		</c:forEach>
			     		</select>
				  </td>
		         </tr>
			     <tr>
				 <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
			       <td colspan="4" align="center">
	                   <button type="button" id="subMoreQuery">查询</button>&nbsp;&nbsp;
	                   <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>

</body>
</html>
