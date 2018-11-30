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
	<script  type="text/javascript" src="${path }/js/task/taskrecordList.js?dwdFe"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	</script>
	
	<title>光缆管理</title>
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
</head>
<body  >
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.htm">首页</a></li>
    <li><a href="index.htm">任务管理</a></li>
    <li><a href="queryTaskrecordrzList.htm">任务日志</a></li>
    </ul>
    </div>
 <div class="rightinfo" id="show">
      <div class="tools">
    	<ul class="toolbar">
       
        </ul>
        <div style="float:right;">
            <form id="searchform"  action="${path}/queryTaskrecordrzList.htm" method="post">
            所属区:<select class="areadevCode" id="areadevCode" name="areadevCode" runat="server" selected="selected">
		     			<option value="">全部</option>
		     			<c:forEach items="${areaList}" var="area" >
		     			<option value="${area.name}" 
		     				<c:if test="${area.name==areadevCode1}">selected="selected"</c:if>	
		     			>
		     				${area.value}
		     			</option>
		     			</c:forEach>
		     		</select>
			    汇聚区:<select name="areaCode1" id="areaCode" class="hjq" runat="server" selected="selected">		     
			     	</select>
                         <input name="logcontent" id="logcontent" value="${record.logcontent}"  type="hidden"  />
	    	<label>录入类型：</label> 
	    	
 	    	<select name="logtype" id="logtype"> 
 	    	<option value="">全部</option>
 	    	</select> 
	    	
	    	   <input name="username" id="username" value="${record.username}"  type="hidden" /> 
	    	   <input name="startTime" id="startTime" value="${record.startTime}"  type="hidden" /> 
	    	   <input name="endTime" id="endTime" value="${record.endTime}"  type="hidden" /> 
	    	    <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	    	    <input  id="areadevCode1" value="${record.areadevCode}"type="hidden" />
	    	    <input  id="areaCode2" value="${record.areaCode1}"type="hidden" />
	    	    <input  id="logtype1" value="${record.logtype}"type="hidden" />
    	    <input id="btnSimpleQuery" name="" type="button" class="submit" value="查询"/>
    	    <button id="btnDisplayMoreQuery" type="button">高级查询</button>
    	    </form>
    	  </div> 
      </div>
	    <table class="tablelist">
	    	<thead >
	    	<tr>
	        
	        <th  width="60px;">录入类型</th>
	        <th width="60px;">核对对象</th>
	        <th  width="60px;">录入内容</th>
	        <th  width="60px;">录入时间</th>
	        <th  width="60px;">录入人</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${pb.list}" var="r" varStatus="a">
		        <tr>		     
		        <td>${r.valuename }</td>
		        <td><c:if test="${ r.sectid!='' and r.sectid!=0}">
		        		${r.secname }
		        		
		        	</c:if>
		        	<c:if test="${r.routeid!='' and r.routeid!=0}">
		        		${r.routename }
		        		
		        	</c:if>
		        	</td>
		        <td>${r.logcontent }</td>
		        <td> <fmt:formatDate value="${r.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		         <td>${r.username }</td>
		        </tr> 
		        </c:forEach>
		        </tbody>
	    </table>
   
    <div class="clear"></div> 
    <jsp:include page="/jsp/one/common/page.jsp" />
    </div>
    <!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form >
	       
			     <table width="100%" border="1" class="table1">
					    <tr>
			      <td>录入内容：</td>
			       <td><input name="logcontents" id="logcontents"   type="text" /></td>
		         </tr>
			     <tr>
			       <td>录入人：</td>
			       <td><input name="usernames" id="usernames"   type="text" /></td>		     
		         </tr>
			     <tr>
			       <td>开始时间：</td>
			       <td><input name="startTimes" id="startTimes" type="text" onClick="WdatePicker()"     /></td>
			     </tr>
			     <tr>
			       <td>结束时间：</td>
			       <td><input name="endTimes" id="endTimes" type="text"  onClick="WdatePicker()"    /></td>
			     </tr>	
				         <tr>
					       <td></td>
					       <td><input id="areaCode1" name="areaCode1" type="text"  value="${routeBean.areaCode1}" style="display: none;" /></td>			     
				         </tr>
					     <tr>
					       <td colspan="2" align="center">
						       <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
						        <input type="hidden" name="startTimes" id="startTimes" value="${routeBean.startTime}">
			       	            <input type="hidden" name="endTimes" id="endTimes" value="${routeBean.endTime}">
			                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
			                   <button type="button" name="btnClear" id="btnClear" >清空</button>
		                   </td>
				         </tr>                                                   
		       </table>
        </form>
   </div>
</body>
</html>
