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
	<script  type="text/javascript" src="${path }/js/jquery.js"></script>	
	<script  type="text/javascript" src="${path }/js/layer.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<script  type="text/javascript" src="${path }/js/deviceAlarm/deviceAlarmList.js"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">智能化设备管理</a></li>
	    <li><a href="deviceAlarmList.htm">记录报表查询</a></li>
	    <li><a href="deviceAlarmList.htm">报警记录查询</a></li>
	    </ul>
    </div>
        <jsp:include page="/jsp/one/facility/deviceReportNav.jsp"/>  
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	<ul class="toolbar">
		        
		        
		    </ul>
	        <div class="simpleQuery">
	           <form>
	                                      设施名称：<input name="devName_search"  style="width: 100px;" id="devNameSearch"  value="${deviceAlarmCon.fName}"   type="text" />
	                                      设施编码：<input name="devCode_search"  style="width: 100px;" id="devCodeSearch"  value="${deviceAlarmCon.devCode}"  type="text" />
	                                      报警类型：<select name="alarmTypes_search" id="alarmTypesSearch">
						      <option value="">全部</option>
	                         <c:forEach  items="${alarmTypesList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == deviceAlarmCon.alarmTypes}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
						  </select> 
	                                      是否处理：<select name="dealSign_search" id="dealSignSearch"> 
						      <option value="">全部</option>
	                         <c:forEach  items="${dealSignList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == deviceAlarmCon.dealSign}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
						  </select> 
					时间：<input id="startTimeSearch"  value="${deviceAlarmCon.alarmTimeStart}" onclick="WdatePicker({skin:'whyGreen',lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" type="text" />
               		-<input id="endTimeSearch"  value="${deviceAlarmCon.alarmTimeEnd}" onclick="WdatePicker({skin:'whyGreen',lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" type="text" />	
               		
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
<!-- 	                <button id="btnDisplayMoreQuery" type="button">高级查询</button> -->
	                <button id="btnImport" type="button">导出</button>
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        
				        <th>设施编码<!-- <i class="sort"><img src="images/px.gif" /></i> --></th>
				        <th>设施名称</th>
				        <th>锁状态</th>
				        <th>门状态</th>
				        <th>温度</th>
				        <th>湿度</th>
				        <th>倾斜</th>
				        <th>电量</th>
				        <th>信号</th>
				        <th>报警时间</th>
				        <th>报警类型</th>
				        <th>报警内容</th>
				        <th>是否处理</th>				        
				        <th>处理情况</th>
				        <th>处理人</th>
				        <th>图片</th>
				        <th>操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="da">
				        <tr>
				        
				        <td>${da.devCode}</td>
				        <td>${da.fdevName}</td>
				        <td>${da.lockStatusName}</td>
				<td>
		        <c:choose>
			             <c:when test="${da.doorOpenErr == 0 }">
			        				<a class="red">${da.doorStatusName}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a>${da.doorStatusName}</a>
			        		</c:otherwise>
			        	</c:choose>	
		        </td>
		        
		        <!-- 设施报警begin -->
					<td>
			  			<c:choose>
			             <c:when test="${da.tempAlarm == 0 }">
			        				<a class="red">${da.temp}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a>${da.temp}</a>
			        		</c:otherwise>
			        	</c:choose>		
					</td>
					
		             <td>
			 			<c:choose>
			             <c:when test="${da.humiAlarm == 0 }">
			        				<a class="red">${da.humidity}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a>${da.humidity}</a>
			        		</c:otherwise>
			        	</c:choose>	
		       		 </td>
					 <td> 
			        	<c:choose>
			     		 <c:when test="${da.tiltAlarm == 0 }">
			        				<a class="red">${da.tilt}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a  >${da.tilt}</a>
			        		</c:otherwise>
						</c:choose>	
		  			</td>
					<td>
						<c:choose>
			             <c:when test="${da.battAlarm == 0 }">
			        				<a class="red">${da.battery}</a>
			        		</c:when>
			        		<c:otherwise>
			        		<a  >${da.battery}</a>
			        		</c:otherwise>
			        	</c:choose>	
		        	</td>
		         <!-- 设施报警end -->
		    
		        <td>${da.signals}</td>
				        <td><fmt:formatDate value="${da.alarmTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				        </td>
				         <td>
					        <c:choose>
					         	<c:when test="${da.alarmTypes eq '01'}">阈值告警</c:when>
					         	<c:when test="${da.alarmTypes eq '02'}">水浸告警</c:when>
					         	<c:when test="${da.alarmTypes eq '03'}">震动告警</c:when>
					         	<c:when test="${da.alarmTypes eq '04'}">门锁异常告警</c:when>
					         	<c:when test="${da.alarmTypes eq '05'}">门超时告警</c:when>
					         	<c:when test="${da.alarmTypes eq '06'}">电量告警</c:when>
					         	<c:when test="${da.alarmTypes eq '07'}">锁超时告警</c:when>
					         	<c:when test="${da.alarmTypes eq '8'}">非法撬锁</c:when>
					         	<c:when test="${da.alarmTypes eq '13'}">布防APP开锁</c:when>
					         	<c:when test="${da.alarmTypes eq '14'}">布防平台远程开锁</c:when>
					         	<c:when test="${da.alarmTypes eq '15'}">布防钥匙开锁</c:when>
					         	<c:when test="${da.alarmTypes eq '16'}">布防蓝牙开锁</c:when>
					         	<c:when test="${da.alarmTypes eq '17'}">通讯终端告警</c:when>
					         	<c:when test="${da.alarmTypes eq '18'}">备用钥匙告警</c:when>
					         	<c:when test="${da.alarmTypes == null or da.alarmTypes eq ''}">未知</c:when>
					        </c:choose>
						</td>
				        <td>${da.alarmContent}</td>
				        <td>${da.dealSignName}</td>
				        
				        <td>${da.dealSituation}</td>
				        <td>${da.userName}</td>
				        <td>
				        	<%-- <c:if test="${fn:contains(da.alarmContent,'强行进入,,')==true'}">
				        		<a href="javascript:;" op="pic" did="${da.alarmProcessId}" class="tablelink">查看</a>
				        	</c:if> --%>
				        	<c:if test="${da.isCheck=='1'}">
				        		<a href="javascript:;" op="pic" did="${da.alarmProcessId}" class="tablelink">查看</a>
				        	</c:if>
				        </td>
				        <td>
				        	<a href="javascript:;" op="ctrl" did="${da.alarmProcessId}" dealSignName="${da.dealSignName}"  dealSituation="${da.dealSituation}" class="tablelink">报警处理</a>
				        </td>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
     
      <!-- 显示图片 -->
     <div id="div_img" class="newlayer" style="display: none;">
			<img  class="lazy" id="img" src=""  height="300" width="300"/>
	 </div>
    
    <!-- 其它页面元素 如：弹出层等-->
	 <!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form action="" method="post" id="moreSearchForm" name="moreSearchForm">
			   <table width="100%" border="1" class="table1">
			     <tr>
			       <td>设施编码：</td>
			       <td><input id="devCode" name="devCode" type="text"  value="${deviceAlarmCon.devCode}" /></td>			     
		         </tr>
			     <tr>
			       <td>设施名称：</td>
			       <td><input id="fName" name="fName" type="text"  value="${deviceAlarmCon.fName}" /></td>
			     </tr>			
			     <tr>
			       <td>开始时间：</td>
			       <td><input id="alarmTimeStart" name="alarmTimeStart" type="text" onClick="WdatePicker()"   value="${deviceAlarmCon.alarmTimeStart}"  /></td>
			     </tr>
			     <tr>
			       <td>结束时间：</td>
			       <td><input id="alarmTimeEnd" name="alarmTimeEnd" type="text"  onClick="WdatePicker()"   value="${deviceAlarmCon.alarmTimeEnd}"  /></td>
			     </tr>
			     <tr>
			       <td>报警类型：</td>
			       <td>
				       <select name="alarmTypes" id="alarmTypes" >
				          <option value="">全部</option>
	                      <c:forEach  items="${alarmTypesList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == deviceAlarmCon.alarmTypes}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
			     <tr>
			       <td>是否处理：</td>
			       <td>
				       <select name="dealSign" id="dealSign" >
				          <option value="">全部</option>
	                      <c:forEach  items="${dealSignList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == deviceAlarmCon.dealSign}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		         </tr>
			     <tr>
			       <td colspan="2" align="center">
				       <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                   <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>
      
      <!-- 报警处理 -->
      <div id="div_deviceAlarmCtrl" class="newlayer" style="display:none;">
	       <form action="" method="post" id="form_deviceAlarmCtrl" name="form_deviceAlarmCtrl">
			   <table width="100%" border="1" class="table1">
			    
			     <tr>
			       <td style="valign:top">处理内容：</td>
			       <td>
			          <textarea rows="5" style="width:450px;" name="dealSituation" id="dealSituation" ></textarea>
				   
				      
		           </td>
		         </tr>
			     <tr>
			       <td colspan="2" align="center">
			           <input id="alarmProcessId" name="alarmProcessId" type="hidden" />
	                   <button type="button" id="btnDevAlaCtrlSave">提交处理</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>
	<div id="div_exportAll" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
   	</div>
</body>
</html>