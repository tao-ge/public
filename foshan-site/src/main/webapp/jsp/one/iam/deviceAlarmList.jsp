<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html>
<html>
<head>
 
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
	<meta http-equiv="x-ua-compatible" content="IE=10" >
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<script  type="text/javascript" src="${path }/js/iam/deviceAlarmList.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>设施管理</title>
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
	
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">智能设备管理</a></li>
	    <li><a href="facilityList.htm">设备告警</a></li>
	    </ul>
    </div>
      
       <div class="tools">
	        <div class="simpleQuery">
	           <form>
		          	
		                                      设施名称: <input name="codName_search" id="codNameSearch"  value="${deviceAlarmEntity.codName }"   type="text" />
		                                      设施编码: <input name="codCode_search" id="codCodeSearch"  value="${deviceAlarmEntity.codCode }"   type="text" />
	                                                   处理情况: <select id="dealSignSerach">
					          	<option value="">全部</option>
					         	<option value="1" ${dealSign eq '1'?"selected":"" }>已处理</option>
					         	<option value="0" ${dealSign eq '0'?"selected":"" }>未处理</option>
				          </select>
				             报警类型: <select id="alarm">
				          <option value="">全部</option>
				          <c:forEach items="${alarmTypes}" var="t">
				          	<option value="${t.key }" ${t.key eq alarm?"selected":"" }>${t.value}</option>
				          </c:forEach>
				          </select>
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	               <!--  <button id="btnDisplayMoreQuery" type="button">高级查询</button> -->
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>设施编码</th>
				        <th>设施名称</th>
				       <!--  <th>蓝牙模块Mac地址</th>
				        <th>锁状态</th>
				        <th>门状态</th>
				        <th>温度</th>
				        <th>倾斜</th>
				        <th>电量</th>
				        <th>信号</th> 
				        
				        <th>最后一次上报时间</th>-->
				        <th>报警时间</th>
				        <th>报警类型</th>
				        <th>报警内容</th>
				        <th>处理情况</th>
				       <!--  <th>处理内容</th>
				        <th>处理人</th> -->
				        <th>操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="i">
				        <tr>
				        <td>${i.count}</td>
				        <td>${f.devCode}</td>
				        <td>${f.devName}</td>
				   <%--      <td>${f.codMac}</td>
				        <td>${f.lockOpen eq '0'?"开":f.lockOpen eq '1'?"关":f.lockOpen eq '2'?"未知":""}</td>
				        <td>${f.doorOpen eq '0'?"开":f.doorOpen eq '1'?"关":f.doorOpen eq '2'?"未知":""}</td>
				        <td>${f.temp}</td>
				      	<td>${f.tilt}</td>
				      	<td>${f.battery}</td>
				      	<td>${f.signals}</td> --%>
				      	<td>
				      	<fmt:formatDate value="${f.rptTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				      	</td>
				        <td>
					        <c:choose>
					         	<c:when test="${f.alarmTypes eq '01'}">阈值告警</c:when>
					         	<c:when test="${f.alarmTypes eq '02'}">水浸告警</c:when>
					         	<c:when test="${f.alarmTypes eq '03'}">震动告警</c:when>
					         	<c:when test="${f.alarmTypes eq '04'}">门锁异常告警</c:when>
					         	<c:when test="${f.alarmTypes == null or da.alarmTypes eq ''}">未知</c:when>
					        </c:choose>
						</td>
						<td width="12%;">
<!--  						<c:if test="${fn:length(f.alarmContent)>10}">
								${fn:substring(f.alarmContent,0,10)}...
							</c:if>
							<c:if test="${fn:length(f.alarmContent)<=10 }">
								${f.alarmContent}
							</c:if>
-->	 
							${f.alarmContent}
						</td>
				        <td>${f.dealSign eq '0'?"未处理":f.dealSign eq '1'?"已处理":"未知"}</td>
				        <%-- <td>${f.dealSituation }</td>
				        <td>${f.userName }</td> --%>
				        <td>
				         <c:if test="${f.dealSign eq '0'}">
				        	<a href="javascript:;" op="ctrl" did="${f.alarmId }" class="tablelink">报警处理</a>
				        </c:if>
				        <c:if test="${f.dealSign eq '1'}">
				        	<a href="javascript:;" op="detail" dt="${f.dealSituation}" class="tablelink">处理结果</a>
				        </c:if>
				         <a href="#" op="check" did="${f.alarmId}" class="tablelink" >查看</a> 
				         <a href="#" op="deviceState" did="${f.devId}" dt="${f.devName }" class="tablelink" >上报数据日志</a> 
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
    
	 <!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form action="${path}/queryDeviceAlarmList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
	       		<input name="codName" id="codName"  type="hidden"/>
	       		<input name="codCode" id="codCode"  type="hidden"/>
	       		<input name="alarmTypes" id="alarmTypes"  type="hidden"/>
	       		<input name="dealSign" id="dealSign"  type="hidden"/>
	       		<input name="pageNo" id="pageNo" value="${pb.pageNo }" type="hidden">
			   <table width="100%" border="1" class="table1">
			     
			     <%-- <tr>
			       <td>设备名称：</td>
			       <td><input id="devName" name="devName" type="text"  value="${facilityCon.devName}" /></td>
			       <td>设施编码：</td>
			       <td><input id="devCode" name="devCode" type="text"  value="${facilityCon.devCode}" /></td>
				   
		         </tr> --%>
		       </table>
        </form>
      </div>
      <!-- 报警处理 -->
      <div id="div_deviceAlarmCtrl" class="newlayer" style="display:none;">
	       <form action="" method="post" id="form_deviceAlarmCtrl" name="form_deviceAlarmCtrl">
			   <table width="100%" border="1" class="table1">
			     <tr>
			       <td style="valign:top;">处理内容</td>
			       <td>
			          <textarea rows="4" style="width:450px; height:250px;" name="dealSituation" id="dealSituation" ></textarea>
		           </td>
		         </tr>
			     <tr>
			       <td colspan="2" align="center">
			           <input id="alarmId" name="alarmId" type="hidden" />
	                   <button type="button" id="btnDevAlaCtrlSave">提交处理</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>
       <div id="div_deviceAlarmCtrl1" class="newlayer" style="display:none;">
	       <form action="" method="post" id="" name="">
			   <table width="100%" border="1" class="table1">
			     <tr>
			       <td style="valign:top;">处理内容</td>
			       <td>
			          <textarea rows="4" style="width:450px; height:250px;" id="coent"></textarea>
		           </td>
		         </tr>
			     <tr>
			       <td colspan="2" align="center">
	                   <button type="button" id="close">关闭</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>
	 </div>
	 
	 
</body>

</html>
