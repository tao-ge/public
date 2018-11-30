<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<!-- <script type="text/javascript"> path='${path }'; </script> -->
	<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
	<script  type="text/javascript" src="${path }/js/well/wellList.js"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<%-- <link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" /> --%>
	<title>井管理</title>
	<script type="text/javascript">
		$(function(){
			$('.tablelist tbody tr:odd').addClass('odd');
		})
    	var path="${path}";
    	$(document).ready(function(e) {

    		$(".select3").uedSelect({
    			width : 152
    		});
    	});
    </script>
    <script type="text/javascript">
    
	</script>
</head>
<body>
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">井资源管理</a></li>
    <li><a href="#">井管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
      <div class="tools">
    	<ul class="toolbar">
        </ul>
        <div class="simpleQuery">
        	<form >
        		
		    	井名称：
		    	<input id="well_name" name="well_name" type="text" value="${well.wellName }" class="well_name" />
		    	喷码：
		    	<input id="well_number" name="well_number" type="text" value="${well.wellNumber}" class="well_number" />
		    	现场核查状态：
		    	<select name="well_state" id="well_state" >
		           <option value="">==请选择==</option>
                      <c:forEach  items="${WellState}" var="item" varStatus="status">
                          <option value="${item.key}" <c:if test="${item.key == well.wellState}">selected ="selected"</c:if>>${item.value}</option>
                      </c:forEach>
	            </select>
		    	
				<input id="btnSimpleQuery" name="btnSimpleQuery" value="查询" class="submit" type="button"/>
				<button id="btnDisplayMoreQuery" name="btnDisplayMoreQuery" type="button">高级查询</button>
        	</form>
        </div>
       
    </div>
    
     <ul class="classlist">
    
	    <table class="tablelist">
	    	<thead>
	    	<tr>
		        <th>井名称</th>
		        <th>喷码</th>
		        <th>局前井</th>
		        <th>井类型</th>
		        <th>井种类</th>
		        <th>现场核查状态</th>
		        <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${pb.list}" var="u">
		        <tr>
			        <td>${u.wellName}</td>
			        <td>${u.wellNumber}</td>
			        <td>${u.isFormerbureau}</td>
			        <td>${u.wellType}</td>
			        <td>${u.wellKind}</td>
			        <td>${u.wellState}</td>
			        <td>
			        	<a href="javascript:;" op="change" did="${u.wellId}" class="tablelink"></a>
			        	<a href="javascript:;" op="delete" did="${u.wellId}" class="tablelink"></a>
			        </td>
		        </tr> 
       		</c:forEach>
	        </tbody>
	    </table>
    </ul>
    
    <div class="clear"></div>
    <jsp:include page="/jsp/one/common/page.jsp" />
    </div>
    
	<!-- 其它页面元素 如：弹出层等-->
	 <!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form action="${path}/queryWellList.htm"  method="post" id="moreSearchForm" name="moreSearchForm" >
			   <table width="100%" border="1" class="table1" >
				 <tr>
			       <td>井名称：</td>
			       <td><input id="wellName" name="wellName" type="text"  value="${well.wellName }" class="scinput1"/></td>			     
		         </tr>
			     <tr>
			       <td>喷码：</td>
			       <td><input id="wellNumber" name="wellNumber" type="text"  value="${well.wellNumber}" class="scinput1"/></td>
			     </tr>			
			  	 <tr>
			       <td>是否局前井：</td>
			       <td>
				       <select name="IsFormerbureau" id="IsFormerbureau" >
				          <option value="">==请选择==</option>
	                      <c:forEach  items="${WellIsformerbureau}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == well.isFormerbureau}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
		          <tr>
			       <td>井种类：</td>
			       <td>
				       <select name="wellKind" id="wellKind" >
				          <option value="">==请选择==</option>
	                      <c:forEach  items="${WellKind}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == well.wellKind}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
<!-- 		         <tr> -->
<!-- 			       <td>现场核查状态：</td> -->
<!-- 			       <td> -->
<!-- 				       <select name="wellState" id="wellState" > -->
<!-- 				          <option value="">==请选择==</option> -->
<%-- 	                      <c:forEach  items="${WellState}" var="item" varStatus="status"> --%>
<%-- 	                            <option value="${item.key}" <c:if test="${item.key == well.wellState}">selected ="selected"</c:if>>${item.value}</option> --%>
<%-- 	                      </c:forEach> --%>
<!-- 			           </select> -->
<!-- 		           </td> -->
<!-- 		         </tr> -->
		          <tr>
			       <td>井类型：</td>
			       <td>
				       <select name="wellType" id="wellType" >
				          <option value="">==请选择==</option>
	                      <c:forEach  items="${WellType}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == well.wellType}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
			       <tr>
			       <td>开始时间：</td>
			       <td><input id="times1" name="times1" type="text"   onClick="WdatePicker().toDate()"  value="${well.times1}" /></td>
			     </tr> 
			      <tr>
			       <td>结束时间：</td>
			       <td><input id="times2" name="times2" type="text"   onClick="WdatePicker().toDate()"  value="${well.times2}" /></td>
			     </tr> 
			    
			     <tr>
			       <td colspan="4" align="center">
			       		<input name="wellState" id="wellState" value="${well.wellState }" type="hidden"/>
				        <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                    <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                    <button type="button" id="exportWell">导出</button>&nbsp;&nbsp;
						<button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>           
		       </table>
        </form>
      </div>
      
      <!-- 导出弹层下载 -->
	<div id="div_export" class="newlayer" style="display:none;">
	    <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
   	</div>
</body>
</html>