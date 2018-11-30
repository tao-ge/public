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
	<script  type="text/javascript" src="${path }/js/facility/mobileSwitchList.js?cesa"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>终端开关锁记录</title>

</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="index.htm">首页</a></li>
		    <li><a href="index.htm">智能化设备管理</a></li>
		    <li><a href="deviceAlarmList.htm">记录报表查询</a></li>
		    <li>
		    	<c:if test="${navFlag eq 'SwitchLockRecord'}">
		    		<a href="switchRecordList.htm">
		    		${navFlag eq 'SwitchLockRecord' ? '开关锁记录查询' : (navFlag eq 'ReportOnTime' ? '定时上报记录查询' : '报警记录查询')}</a>
		    	</c:if>
		    	<c:if test="${navFlag eq 'ReportOnTime'}">
		    		<a href="devTimeNewStatusList.htm">
		    		${navFlag eq 'SwitchLockRecord' ? '开关锁记录查询' : (navFlag eq 'ReportOnTime' ? '定时上报记录查询' : '报警记录查询')}</a>
		    	</c:if>
		    </li>
	    </ul>
    </div>
    <jsp:include page="/jsp/one/facility/deviceReportNav.jsp"/>  
    <div class="rightinfo">
	    <div class="tools">
	        <div class="simpleQuery">
	           <form>
	               	用户名称：<input id="userName1" name="userName1" type="text"  value="${ms.userName}" />
					设施编码：<input id="devCode1" name="devCode1" type="text"  value="${ms.devCode}" />
					设施名称：<input id="fName1" name="fName1" type="text"   value="${ms.fName}"   />
	                                        时间：<input id="startTimeSearch"  value="${ms.startTime}" onclick="WdatePicker({skin:'whyGreen',lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" type="text" />
               		-<input id="endTimeSearch"  value="${ms.endTime}" onclick="WdatePicker({skin:'whyGreen',lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" type="text" />
               		
					开锁类型：
					<select id="switchType1" name="switchType1" style="width: 70px;">
			           <option value="" selected="selected">全部</option>
	                         <%-- <c:forEach  items="${areasServiceList}" var="item" varStatus="status">
	                            <option value="${item.areaCode}" <c:if test="${item.areaCode == facilityCon.areaCode1}">selected ="selected"</c:if>>${item.areaName}</option>
	                         </c:forEach> --%>
	                         <option value="0" <c:if test="${'0' == ms.switchType}">selected ="selected"</c:if>>蓝牙开锁</option>
	                         <option value="1" <c:if test="${'1' == ms.switchType}">selected ="selected"</c:if>>远程开锁</option>
	                         <option value="4" <c:if test="${'4' == ms.switchType}">selected ="selected"</c:if>>钥匙开锁</option>
	                         <option value="5" <c:if test="${'5' == ms.switchType}">selected ="selected"</c:if>>关锁</option>
                      </select>
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
				        <th>序号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>设施编号</th>
				        <th>设施名称</th>
				        <th>开锁类型</th>
				        <th>用户名称</th>
				        <th>手机号码</th>
				        <th>作业结果</th>
				        <th>作业时间</th>
				        <th>作业时长(/分钟)</th>
				        <th>图片</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="status">
				        <tr ${f.switchType=='4'?'style="background-color:#eec0ca"':''}>
				        <td>${status.count}</td>
				        <td>${f.devCode}</td>
				        <td>${f.fdevName}</td>
				        <td>
					        <c:choose>
								<c:when test="${f.switchType=='0'}">
									蓝牙开锁
								</c:when>
								<c:when test="${f.switchType=='1'}">
									远程开锁
								</c:when>
								<c:when test="${f.switchType=='2'}">
									扫码开锁
								</c:when>
								<c:when test="${f.switchType=='3'}">
									小程序开锁
								</c:when>
								<c:when test="${f.switchType=='4'}">
									钥匙开锁
								</c:when>
								<c:when test="${f.switchType=='5'}">
									关锁
								</c:when>
								<c:otherwise>
									未知开锁
	   							</c:otherwise>
							</c:choose>
				        </td>
				         <td>${f.userName}</td>
				        <td>${f.mobilePhone}</td>
				        <td>
				        	<c:choose>
								<c:when test="${f.switchType=='5'}">
									作业结束
								</c:when>
								<c:otherwise>
									开始作业
       							</c:otherwise>
							</c:choose>
				        </td>
				         <td>
				         	<fmt:formatDate value="${f.colTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				         </td>
				       <td>${f.workTime}</td>
				        <td><a href="javascript:;" op="pic" did="${f.switchId}"  class="tablelink">查看</a></td>
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
	       <form action="${path}/switchRecordList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
			   <table width="100%" border="1" class="table1">
			     <tr>
			       <td>用户名称：</td>
			       <td><input id="userName" name="userName" type="text"  value="${ms.userName}" /></td>
			       <td>操作类型：</td>
			       <td><label for="select"></label>
			         <select id="switchType" name="switchType" >
			           <option value="">全部</option>
	                         <%-- <c:forEach  items="${areasServiceList}" var="item" varStatus="status">
	                            <option value="${item.areaCode}" <c:if test="${item.areaCode == facilityCon.areaCode1}">selected ="selected"</c:if>>${item.areaName}</option>
	                         </c:forEach> --%>
	                         <option value="0">蓝牙开锁</option>
	                         <option value="1">远程开锁</option>
	                         <option value="4">机械开锁</option>
	                         <option value="5">关锁</option>
                      </select>
                   </td>
		         </tr>
			     <tr>
			       <td>手机号码：</td>
			       <td><input name="mobilePhone" type="text"  value="${ms.mobilePhone}" /></td>
			       <td>IMEI：</td>
			       <td><input name="devImei" value="${ms.devImei}"  type="text"/></td>
		         </tr>
			     <tr>
			       <td>设施编号：</td>
			       <td><input id="devCode" name="devCode" type="text"  value="${ms.devCode}" /></td>
			       <td>设施名称：</td>
			       <td><input id="fName" name="fName" type="text"   value="${ms.fdevName}"   /></td>
		         </tr>
			     <tr>
			       <td>时间：</td>
			       <td><input id="startTime" name="startTime" type="text" onclick="WdatePicker({skin:'whyGreen',lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" value="${ms.startTime}" /></td>
			       <td>-</td>
			       <td><input id="endTime" name="endTime" type="text" onclick="WdatePicker({skin:'whyGreen',lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm'})" value="${ms.endTime}" /></td>
		         </tr>
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
	<div id="div_exportAll" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
   	</div>
</body>

</html>
