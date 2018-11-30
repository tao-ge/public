<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script  type="text/javascript" src="${path }/js/facility/facilityList.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>设施管理</title>

</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">资源台账管理</a></li>
	    <li><a href="facilityList.htm">设施管理</a></li>
	    </ul>
    </div>
        
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	<ul class="toolbar">
		        <li ><a id="hrefAdd" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
		        <li class="click"><a id="facilityImport" href="javascript:;"><span><img src="${path}/images/t02.png" /></span>导入</a></li>
		        <li ><a id="exportAll" href="javascript:;"><span><img src="${path}/images/f05.png" /></span>导出</a></li>
		    </ul>
	        <div class="simpleQuery">
	           <form>
		                                      设施名称：<input name="devName_search" id="devNameSearch"  value="${facilityCon.devName}"   type="text" />
		                                      设施编码：<input name="devCode_search"  id="devCodeSearch"  value="${facilityCon.devCode}"  type="text" />
		                                      设施类型：<select name="devType_search" id="devTypeSearch">
						      <option value="">全部</option>
	                         <c:forEach  items="${deviceTypeList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == facilityCon.devType}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
						  </select> 
					设施状态：	<select name="devState_all" id="devState_all">
						      <option value="">全部</option>
	                         <c:forEach  items="${DevTypeall}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == facilityCon.devStateall}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
						  </select> 
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	                <button id="btnDisplayMoreQuery" type="button">高级查询</button>
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr style="white-space:nowrap;">
				        <th width="100px;">设施编码<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>设施名称</th>
				        <th width="100px;">设施类型</th>
				        <th width="100px;">设施型号</th>
				        <!-- <th>所属站点</th> -->
				        <th width="100px;">所属机房</th>
				        <th width="100px;">所属设施</th>
				        <th width="100px;">巡检状态</th>
				        <th width="100px;">设施状态</th>
				        <th width="100px;">核查状态</th>
				        <th width="200px;">操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f">
				        <tr style="word-wrap:break-word;word-break:break-all;">
				        <td>${f.devCode}</td>
				        <td>${f.devName}</td>
				        <td>${f.devTypeName}</td>
				        <td>${f.devModel}</td>
				       <%--  <td>${f.site}</td> --%>
				        <td>${f.room}</td>
				        <td>${f.pdevName}</td>
				        <c:if test="${f.checkType=='1'}">
							<td><font color='red'>已锁定</font></td>
						</c:if>
						<c:if test="${f.checkType=='0'}">
							<td><font color='red'>未巡检</font></td>
						</c:if>
						<c:if test="${f.checkType=='2'}">
							<td><font color='red'>已巡检</font></td>
						</c:if>
						<td>
					        <c:choose>
					        	<c:when test="${f.devState eq '0'}">未核对</c:when>
					        	<c:when test="${f.devState eq '1'}">与现场一致</c:when>
					        	<c:when test="${f.devState eq '2'}">新增</c:when>
					        	<c:when test="${f.devState eq '3'}">修改</c:when>
					        	<c:when test="${f.devState eq '4'}">未找到</c:when>
					        	<c:when test="${f.devState eq '5'}">新增删除</c:when>
					        </c:choose>
				        </td>
				        <td>
					        <c:choose>
					        	<c:when test="${f.checkType eq '0'}">未核查</c:when>
					        	<c:when test="${f.checkType eq '1'}">锁定</c:when>
					        	<c:when test="${f.checkType eq '2'}">已核查</c:when>
					        </c:choose>
				        </td>
				        <!-- ${path}/facilityLock.htm?devId=${f.devId} -->
				        <td>
				       <%--  <a href="javascript:;" op="lock" did="${f.devId}"  class="tablelink">设备状态</a> --%>
				        <a href="javascript:;" op="view" lng="${f.baiduX}" lat="${f.baiduY}"   class="tablelink">位置</a>&nbsp;
				        <a href="${path}/fiberdiscChart.htm?devId=${f.devId}" target="rightFrame"  class="tablelink">熔纤盘</a>&nbsp;
				        <a href="${path}/facilityRelationBydevId.htm?devId=${f.devId}"  target="_blank"  class="tablelink">拓扑图</a>&nbsp;
				        <a href="${path}/facilityBaseInfo.htm?devId=${f.devId}" class="tablelink" >详情</a> 
				        <c:if test="${f.isLocking=='0'}">
							<a href="javascript:;" op="modify" did="${f.devId}"  class="tablelink">修改</a>
				        	<%-- <a href="javascript:;" op="del" did="${f.devId}" dt="${f.devName}" class="tablelink"> 删除</a> --%></td>
						</c:if>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
    
    <!-- 其它页面元素 如：弹出层等 zy1/16改-->
	 <!-- 高级查询 -->
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form action="${path}/facilityList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
			   <table width="100%" border="1" class="table1">
			     <tr>
			       
			       <td>所属片区：</td>
			       <td><label for="select"></label>
			         <select class="areadevCode" name="areaCode" id="areaCode" runat="server" selected="selected">
			           <option value="">全部</option>
	                         <c:forEach  items="${areaList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == facilityCon.areaCode}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
                      </select>
                   </td>
                   <td>汇聚区</td>
		 		<td>
		 		<select name="areaCode1" id="areaCode1" class="hjq" runat="server" selected="selected"> 
		 			
		 		</select>
		 		<input name="areaCode2" id="areaCode2" type="hidden" value="${areaCode2}" /> 
		         </tr>
			     <tr>
			       <td>设备名称：</td>
			       <td><input id="devName" name="devName" type="text"  value="${facilityCon.devName}" /></td>
			       <td>设施编码：</td>
			       <td><input id="devCode" name="devCode" type="text"  value="${facilityCon.devCode}" /></td>
				   
		         </tr>
			     <tr>
			      <td>所属设施：</td>
			       <td><input name="pdevName"   value="${facilityCon.pdevName}"  type="text"/></td>
				   
			       <%-- <td>所属站点：</td>
			       <td><input name="site" type="text"   value="${facilityCon.site}"   /></td> --%>
			       <td>设施状态：</td>
			       <td><label for="select"></label>
						<select name="devState" id="devState">
						 <option value="">全部</option>
								<c:forEach items="${devStateList}" var="item" >
									<option value="${item.key}"  <c:if test="${item.key == facilityCon.devState}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
						</select>
                   </td>
			       
		         
		         
			     <tr>
			       
			      <%--  <td>所属机房：</td>
			       <td><input name="room" type="text"   value="${facilityCon.room}"  /></td> --%>
			       <td>型号：</td>
			       <td><input name="devModel" type="text"   value="${facilityCon.devModel}"  /></td>
		        
<!-- 			     <tr> -->
<!-- 			       <td>IMEI：</td> -->
<%-- 			       <td><input name="devImei" type="text"   value="${facilityCon.devImei}" /></td> --%>
<!-- 			       <td>MAC：</td> -->
<%-- 			       <td><input name="devMac" type="text" value="${facilityCon.devMac}" /></td> --%>
<!-- 		         </tr> -->
					
		          <td>按照有无经纬度：</td>
			       <td><label for="select"></label>
						<select name="existLngLat" id="existLngLat">
						 <option value="">全部</option>
								<c:forEach items="${existLngLat}" var="item" >
									<option value="${item.key}"  <c:if test="${item.key == facilityCon.existLngLat}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
						</select>
                   <button type="button" id="btnImport">导出</button>&nbsp;&nbsp;
                   </td>
		         </tr>
			       <td>核查状态：</td>
			       <td>
				       <select name="surveyResult" id="surveyResult" >
				          <option value="">全部</option>
	                      <c:forEach  items="${surveyResultList}" var="item" varStatus="status">
	                            <option value="${item.key}" <c:if test="${item.key == facilityCon.surveyResult}">selected ="selected"</c:if>>${item.value}</option>
	                      </c:forEach>
			           </select>
		           </td>
		           <td>巡检状态：</td>
						<td><select name="checkType" id="checkType">
								<option value="">全部</option>
								<c:forEach items="${CheckTypeMap}" var="item" varStatus="status">
									<option value="${item.key}" <c:if test="${item.key == facilityCon.checkType}">selected ="selected"</c:if>>${item.value}</option>
								</c:forEach>
								
						</select></td>
		         </tr>
		          <tr>
               <td>设施类型：</td>
			       <td>
			            <select  id="devType" name="devType" >						    
						     <option value="">全部</option>
	                        <c:forEach  items="${deviceTypeList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == facilityCon.devType}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
						</select>
					</td>
                   
			     <tr>
			     <tr>
			       <td colspan="4" align="center">
			       	   <input id="devStateall" name="devStateall" value="${facilityCon.devStateall}" type="hidden" />
				       <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                   <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>
<!-- 导入Excel -->
		<div id="div_import" title="导入设施" class="newlayer" style="display:none">
			<form action="${path}/importFacilities.htm" enctype="multipart/form-data" method="post" id="importForm" target="importFrame">
				<table class="table1">
					<tr>
					    <td>下载模板：</td>
					    <td>
					        <span><a style="color:indigo;font-size:medium;font-family:fantasy" href="${path}/exportFacilityTemp.htm">设施模板文件.xls</a></span>
					    </td>
					</tr>
					<!-- 
					<tr>	     
						<td>下载类型id文件：</td>
					    <td>
					        <span><a href="exportType.htm">类型文件.xls</a></span>
					    </td>
				     </tr>
					-->
					<tr>
					    <td>选择设施数据文件：</td>
					    <td>
					        <input type="file" name="importExcel"/>
					    </td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" >导入</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="display:none;"><iframe id="importFrame" name="importFrame"></iframe></div>
		<!-- 导出 -->
		<div  id="div_area"  class="newlayer" style="display:none">
	 	<input type="hidden" name="areaCode2" id="areaCode2" value="${areaCode}"/>
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
		 		<select name="areaCode3" id ="areaCode3" class="hjq1">
		 		</select>
		 		</td>
	 		</tr>
	 	</table>
	 </div>
	 
	  <!-- 导出弹层下载 -->
	<div id="div_export" class="newlayer" style="display:none;">
	     <center> <a id="exportDown" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
   	</div>
	<div id="div_exportFactily" class="newlayer" style="display:none;">
	     <center> <a id="exportDownFactily" href="" target="_blank" style="font-size:18px;margin-top:120px">导出成功，点此下载！</a></center>
   	</div>
</body>

</html>
