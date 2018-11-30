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
	<script  type="text/javascript" src="${path }/js/route/routeList.js?dadwde"></script>	
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
	        <li><a href="routeList.htm">光路查询</a></li>
	    </ul>
    </div>
         ${devStaCon.alarmType }
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    
	        <div class="simpleQuery">
	           <form>
	         <!--    ${areadevCode1 }-->
	           		所属区：<select class="areadevCode" id="areadevCode" runat="server" selected="selected">
		     			<option value="">全部</option>
		     			<c:forEach items="${areaList}" var="area" >
		     					<option value="${area.name}" 
		     						<c:if test="${area.name == areadevCode1}">selected ="selected"</c:if>
		     					>${area.value}</option>
		     			</c:forEach>
		     		</select>
			     	汇聚区：<select name="areaCode1" id="areaCode" class="hjq">
			     		<c:if test="${name == routeBean.areaCode1}">selected ="selected"</c:if>
			     	</select>
	                                         路由名称：<input name="routetextSearch" id="routetextSearch"  value="${routeBean.routetext}"   type="text" />
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
                 <button type="button" id="btnExportONU">导出光交箱</button> 
                <button type="button" id="btnExportCabint">导出机柜</button> 
<!--                 <button type="button" id="btnCreateONU">按光交箱生成</button> -->
<!--                 <button type="button" id="btnCreateCabint">按机柜生成</button> -->
	                <button id="btnDisplayMoreQuery" type="button">高级查询</button>
	               
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr style="white-space:nowrap;">
				        <th width="60px;">序号</th>
				        <th >光路名称</th>
				        <th width="150px;">最后修改时间</th>
				        <th width="60px;">是否审核</th>
				        <th width="80px;">操作</th>
				        
			       </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="st">
				        <tr style="word-wrap:break-word;word-break:break-all;">
				       
					    <td>${st.index + 1}</td>
					  <!--    <td>${f.codes}</td>	-->
					    <td>${f.route_name}</td>
				        <td><fmt:formatDate value="${f.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
				        <td>
				        <c:if test="${f.is_entering == 1}">
		        		是
		        	</c:if>
		        	<c:if test="${f.is_entering == 0}">
		        		否
		        	</c:if>
				        </td>
				        <td><a href="${path }/lightPathChart.htm?routeId=${f.route_id}" class="tablelink" >查看详情</a> </td>		  	       				        </tr>
			       	</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
    
    <!-- 其它页面元素 如：弹出层等-->
	<!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form  method="post" action="${path }/routeList.htm" id="moreSearchForm" name="moreSearchForm">
	       
			     <table width="100%" border="1" class="table1">
			     <input type="hidden" id="areadevCode1" name="areadevCode1">
			     <input id="areadevCode3" value="" type="hidden"/>
			      <input type="hidden" name="routetext" id="routetext">
					    <tr>
			     
			       <td>设施：</td>
			       <td><select id="seledevId" style="width: 100%;">
								<option value="-1">---</option>
						</select> <input id="devId" name="devId" type="hidden" value="${routeBean.devId}" /></td>
												   		    
		         </tr>
			     <tr>
			       <td>端子编码：</td>
			       <td><input id="code" name="code" type="text"  value="${routeBean.code}" /></td>			     
		         </tr>
			     <tr>
			       <td>文本路由：</td>
			       <td>
			    <input name="routetextgjSearch" id="routetextgjSearch"  value="${routeBean.routetext}"   type="text" />
			    </td>
			     </tr>	
			     <tr>
			       <td>开始时间：</td>
			       <td><input name="startTime" id="startTime" type="text" onClick="WdatePicker()"     /></td>
			     </tr>
			     <tr>
			       <td>结束时间：</td>
			       <td><input name="endTime" id="endTime" type="text"  onClick="WdatePicker()"    /></td>
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
			                    <button type="button" id="btnExportQuery">导出</button>&nbsp;&nbsp;
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