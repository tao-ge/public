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
	<script  type="text/javascript" src="${path }/js/route/routeManage.js?ddwdadwaa"></script>	
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">资源台账管理</a></li>
	    <li><a href="routeManage.htm">光路管理</a></li>
	    </ul>
    </div>
    	${devStaCon.alarmType }
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	<ul class="toolbar">
	     		<c:if test="${roleOrgId == 0}">
		        	 <li ><a id="rebuildAll" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>全部重新生成</a></li>		        		        		     	
		        </c:if>   
		   		<li><input type="text"  id="anyCode" name="anyCode" value="${anyCode}"style="height:32px"><a  id="rebuildAnyPoint" href="javascript:;">  按点生成</a></li>		        
		    </ul>
	        <div class="simpleQuery">
	           <form>
	           <!--  <td>所属区</td>
		 		<td>
		 		<select class="areadevCode" id="areadevCode" name="areadevCode1" runat="server" selected="selected">
		     			<option value="">全部</option>
		     			<c:forEach items="${areaList}" var="area" >
		     			<option value="${area.name}" 
		     				
		     			>
		     				${area.value}
		     			</option>
		     			</c:forEach>
		     		</select>
		 		</td>
		 		<td>汇聚区</td>
		 		<td>
		 		<select name="areaCode1" id="areaCode" class="hjq" runat="server" selected="selected"> 
		 	
		 		</select>
					
		 		</td>-->
		 		
	              路由文本：<input name="routetextSearch" id="routetextSearch"  value="${routeBean.routetext}"   type="text" />	 
	                      <input id="devd" name="devd" type="hidden" value="${routeBean.devId}" />                          	              	                                      
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	                <input id="areadevCode2" value="${routeBean.areadevCode1}" type="hidden"/>
	                <input id="areadevCode3" value="${routeBean.areaCode1}" type="hidden"/>
	                <button id="btnDisplayMoreQuery" type="button">高级查询</button>
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr>
			        <th  width="60px;">序号</th>
			        <th style="word-wrap:break-word;word-break:break-all;">光路名称</th>
<!-- 			        <th>光路名称</th> -->
<!-- 			        <th>A端设施</th> -->
<!-- 			        <th>Z端设施</th> -->
			        <th  width="150px;">最后修改时间</th>
			        <th  width="80px;">是否审核</th>
			        <th width="80px;">操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="st">
				        <tr style="word-wrap:break-word;word-break:break-all;">
				       
					    <td>${st.index + 1}</td>
					    <td>${f.routeName}</td>
<%-- 				        <td>${f.routeName}</td> --%>
<%-- 				        <td>${f.adevName}</td> --%>
<%-- 				        <td>${f.zdevName}</td>				         --%>
				        <td><fmt:formatDate value="${f.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
				        <td>
				        <c:if test="${f.isentering == 1}">
		        		是
		        	</c:if>
		        	<c:if test="${f.isentering == 0}">
		        		否
		        	</c:if>
				        </td>
				        <td><a href="${path }/lightPathChart.htm?routeId=${f.routeId}" class="tablelink" >查看详情</a> 
				        </td>
				        
				        </tr>
				        <!--  <a href="javascript:;" op="rebuild" did="${f.acode}" dt="${f.routeName}" org="${f.orgId}" class="tablelink">重新生成</a>
				        <a href="javascript:;" op="del" did="${f.routeId}" dt="${f.routeName}" class="tablelink"> 删除</a></td>	-->	       			       
			       	</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
    
    <!-- 其它页面元素 如：弹出层等-->
	 <!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form action="${path }/routeManage.htm" method="post" id="moreSearchForm" name="moreSearchForm">
			     <table width="100%" border="1" class="table1">
			     <tr>
			     
			       <td width="20%">设施：</td>
			       <td ><select id="seledevId" >
								<option value="-1" >---</option>
						</select> <input id="devId" name="devId" type="hidden" value="${routeBean.devId}" /></td>
												   		    
		         </tr>
<!-- 			     <tr> -->
<!-- 			       <td>端子编码：</td> -->
<%-- 			       <td><input id="code" name="code" type="text"  value="${routeBean.code}" /></td>			      --%>
<!-- 		         </tr> -->
			     <tr>
			       <td >文本路由：</td>
			       <td>
				    	<input name="routetextgjSearch" id="routetextgjSearch"  value="${routeBean.routetext}"   type="text" />
				   </td>
			     </tr>
			     	<tr>
			       <td >开始时间：</td>
			       <td><input name="startTime" id="startTime" type="text" onClick="WdatePicker()"     /></td>
			     </tr>
			     <tr>
			       <td >结束时间：</td>
			       <td><input name="endTime" id="endTime" type="text"  onClick="WdatePicker()"    /></td>
			     </tr>					     		         
			     <tr>
			       <td colspan="4" align="center">
			       	  <input type="hidden" name="currentCode" id="currentCode">
			       	  <!--  <input type="hidden" name="areaCode1" id="areaCode1">-->
			       	  <input type="hidden" name="areadevCode1" id="areadevCode1">
			       	  <input type="hidden" name="routetext" id="routetext">
			       	  <input type="hidden" name="startTimes" id="startTimes" value="${routeBean.startTime}">
			       	  <input type="hidden" name="endTimes" id="endTimes" value="${routeBean.endTime}">
			       	  
				       <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                   <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>
	
	 <div  id="div_area"  class="newlayer" style="display:none">
	 	<input type="hidden" name="areaCode" id="areaCode" value="${areaCode}"/>
	 	<table width="100%" border="1" class="table1">
	 		<tr>
		 		<td>所属区</td>
		 		<td>
		 		<select  id="district" class="district">
		 			<option value="">全部</option>
		 			<c:forEach items="${areaList}" var="a"> 	
		 				<option value="${a.name }">${a.value}</option>
		 			</c:forEach>
		 		</select>
		 		</td>
	 		</tr>
	 		<tr>
		 		<td>汇聚区</td>
		 		<td>
		 		<select name="areaCode1" id ="areaCode1" class="hjq">
		 		</select>
		 		</td>
	 		</tr>
	 	</table>
	 </div>
	 
</body>
</html>