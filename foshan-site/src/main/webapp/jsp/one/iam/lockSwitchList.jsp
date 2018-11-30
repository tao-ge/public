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
	<script  type="text/javascript" src="${path }/js/iam/lockSwitchList.js?dadwde"></script>	
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<title>开关锁记录</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="index.htm">首页</a></li>
	   		<li><a href="index.htm">智能设备管理</a></li>
	        <li><a href="queryLockSwitchList.htm">开关锁记录</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	        <div class="simpleQuery">
	           <form action="${path}/queryLockSwitchList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
	           		<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	           		
	           		设施编码：<input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'');" name="devCode" id="devCode"  value="${switchVo.devCode}"   type="text" />
	           		设施名称：<input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'');" name="devName" id="devName"  value="${switchVo.devName}"   type="text" />
	           		开锁类型： <select name="switchType" id="switchType">
						       <option value="">全部</option>
		                       <c:forEach  items="${switchType}" var="item" varStatus="status">
		                           <option value="${item.key}" <c:if test="${item.key == switchVo.switchType}">selected ="selected"</c:if>>${item.value}</option>
		                       </c:forEach>
						   </select> 
	           		
	           		<input  id="btnSimpleQuery" value="查询" class="submit" type="button">
<!-- 	                <button id="btnDisplayMoreQuery" type="button">高级查询</button> -->
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr style="white-space:nowrap;">
				        <th>序号</th>
				        <th>设施名称</th>
				        <th>设施编码</th>
				        <th>锁名称</th>
				        <th>开锁时间</th>
				        <th>关锁时间</th>
				        <th>开锁人</th>
				        <th>开锁结果</th>
<!--				        <th>作业时间</th>
-->
				        <th>作业时长(/分钟)</th>
				        <th>最后一次上报时间</th>
				        <th>开锁类型</th>
				        <th>操作</th>
			       </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="st">
				        <tr>
						    <td>${st.index + 1}</td>
						    <td width="100px;">${f.devName}</td>
						    <td>${f.devCode}</td>
						    <td>${f.lockName}</td>
						    <td>
						    	<fmt:formatDate value="${f.openTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
					    	<td>
						    	<fmt:formatDate value="${f.closeTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
					    	</td>
						    <td>
 						    <c:choose> 
 				         		<c:when test="${f.userName ==null or f.userName eq ''}">未知</c:when>
				         		<c:otherwise>${f.userName}</c:otherwise>
				         	</c:choose> 
						    </td> 
						     <td>
					        	 <c:if test="${f.resultStatus eq '0'}">失败</c:if>
					        	 <c:if test="${f.resultStatus eq '1'}">成功</c:if>
				       		 </td>
<!--						    <td>
						    	<fmt:formatDate value="${f.lastModifyTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
-->
						    <td>${f.workTime }</td>
						    <td>
						    	<fmt:formatDate value="${f.openTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						    </td>
						    <td>
						    	<c:if test="${f.switchType=='1'}">蓝牙开锁</c:if>
						    	<c:if test="${f.switchType=='2'}">远程开锁</c:if>
						    	<c:if test="${f.switchType=='3'}">扫码开锁</c:if>
						    	<c:if test="${f.switchType=='4'}">机械开锁</c:if>
						    	<c:if test="${f.switchType=='5'}">其他</c:if>
						    </td>
					        <td>
					        	<a href="${path }/queryDeviceDisinfo.htm?route=${'/queryLockSwitchList.htm'}&flag=${'2'}&codId=${f.codId}"  class="tablelink" >查看端子日志</a> 
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
<!-- 	<div id="div_moreSearch" class="newlayer" style="display:none;"> -->
<%-- 	       <form  method="post" action="${path }/routeList.htm" id="moreSearchForm" name="moreSearchForm"> --%>
	       
<!-- 			     <table width="100%" border="1" class="table1"> -->
<!-- 			     <input type="hidden" id="areadevCode1" name="areadevCode1"> -->
<!-- 			     <input id="areadevCode3" value="" type="hidden"/> -->
<!-- 			      <input type="hidden" name="routetext" id="routetext"> -->
<!-- 					    <tr> -->
			     
<!-- 			       <td>设施：</td> -->
<!-- 			       <td><select id="seledevId" style="width: 100%;"> -->
<!-- 								<option value="-1">---</option> -->
<%-- 						</select> <input id="devId" name="devId" type="hidden" value="${routeBean.devId}" /></td> --%>
												   		    
<!-- 		         </tr> -->
<!-- 			     <tr> -->
<!-- 			       <td>端子编码：</td> -->
<%-- 			       <td><input id="code" name="code" type="text"  value="${routeBean.code}" /></td>			      --%>
<!-- 		         </tr> -->
<!-- 			     <tr> -->
<!-- 			       <td>文本路由：</td> -->
<!-- 			       <td> -->
<%-- 			    <input name="routetextgjSearch" id="routetextgjSearch"  value="${routeBean.routetext}"   type="text" /> --%>
<!-- 			    </td> -->
<!-- 			     </tr>	 -->
<!-- 			     <tr> -->
<!-- 			       <td>开始时间：</td> -->
<!-- 			       <td><input name="startTime" id="startTime" type="text" onClick="WdatePicker()"     /></td> -->
<!-- 			     </tr> -->
<!-- 			     <tr> -->
<!-- 			       <td>结束时间：</td> -->
<!-- 			       <td><input name="endTime" id="endTime" type="text"  onClick="WdatePicker()"    /></td> -->
<!-- 			     </tr>	 -->
<!-- 				         <tr> -->
<!-- 					       <td></td> -->
<%-- 					       <td><input id="areaCode1" name="areaCode1" type="text"  value="${routeBean.areaCode1}" style="display: none;" /></td>			      --%>
<!-- 				         </tr> -->
<!-- 					     <tr> -->
<!-- 					       <td colspan="2" align="center"> -->
<%-- 						       <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" /> --%>
<%-- 						        <input type="hidden" name="startTimes" id="startTimes" value="${routeBean.startTime}"> --%>
<%-- 			       	            <input type="hidden" name="endTimes" id="endTimes" value="${routeBean.endTime}"> --%>
<!-- 			                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp; -->
<!-- 			                    <button type="button" id="btnExportQuery">导出</button>&nbsp;&nbsp; -->
<!-- 			                   <button type="button" name="btnClear" id="btnClear" >清空</button> -->
<!-- 		                   </td> -->
<!-- 				         </tr>                                                    -->
<!-- 		       </table> -->
<!--         </form> -->
<!--    </div> -->
   
   <!-- 导出弹层下载 -->
	<div id="div_export" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
   </div>
   

</body>
</html>