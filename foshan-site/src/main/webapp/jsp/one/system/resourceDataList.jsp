<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<script  type="text/javascript" src="${path }/js/system/resourceDataList.js?oooo"></script>	
	<title>资管数据管理</title>

</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">系统管理</a></li>
	    <li><a href="resourceDataList.htm">资管数据管理</a></li>
	    </ul>
    </div>
        
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
		    	<ul class="toolbar">
		    		<li><a id="goGenerator" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>导入数据</a></li>
		    	</ul>
	        <div class="simpleQuery">
	           <form name="searchlistform" id="searchlistform" action="${path}/resourceDataList.htm">
	           		     所属区:<select class="areaCodes" id="areaCodes" name="areaCodes" runat="server" selected="selected">
		     			<option value="">全部</option>
		     			<c:forEach items="${areaList}" var="area" >
		     			<option value="${area.name}" 
		     				<c:if test="${area.name==areadevCode1}">selected="selected"</c:if>	
		     			>
		     				${area.value}
		     			</option>
		     			</c:forEach>
		     		</select>
					    汇聚区:<select name="areaCode" id="areaCode" class="hjq" runat="server" selected="selected">		     
					     	</select>
			     	<input id="areaCodes1" value="${resourceData.areaCodes }" type="hidden" />
			     	<input id="areaCodeh" value="${resourceData.areaCode }" type="hidden" />
					<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th>序号</th>
				        <th>汇聚区</th>
				        <th>最新时间</th>
				        <th>版本号</th>
				        <th>操作人</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="st">
				        <tr>
				        <td>${st.index + 1}</td>
				        <td>
					        <c:forEach items="${areaList}" var="a" >
					        		<c:if test="${f.areaCode == a.name}">${a.value }</c:if>
					       	</c:forEach>
					       	<c:forEach items="${hjMap}" var="a" >
					        		<c:if test="${f.areaCode == a.key}">${a.value }</c:if>
					       	</c:forEach>
				        </td>
				        <td><fmt:formatDate value="${f.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
				        <td>${f.version}</td>
				        	<td>${f.modifyUserName }</td>
<%-- 				        <td><a href="javascript:;" op="reGenerator" did="${f.resId}" ver="${f.version}" class="tablelink">重新生成</a></td> --%>
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
     
     <!-- 生成数据 -->
     <div  id="div_area"  class="newlayer" style="display:none">
<%-- 	 	<input type="hidden" name="areaCode" id="areaCode2" value="${areaCode}"/> --%>
		<form <%-- action="${path}/importArrange.htm" --%> enctype="multipart/form-data" method="post" id="importForm">
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
			 		<select name="areaCode" id ="areaCode3" class="hjq1">
			 		<option value="">==请选择==</option>
			 		</select>
			 		</td>
		 		</tr>
		 		<tr>
		 			<td>选择文件</td>
				    <td>
				        <input type="file" id="deployFile" name="deployFile" class="deployFile" required="true"/>
				    </td>
				</tr>
		 	</table>
		 </form>
	 </div>
     
     <!-- 重新生成 -->
	<div id="div_reGenerator" class="newlayer" style="display: none;">
		<form action="" method="post" id="form_reGenerator" name="form_reGenerator">
			<table width="100%" border="1" class="table1">
				<tr>
					<td class="odd">备注信息：</td>
					<td><textarea id="remark" name="remark" style="width:400px; height:270px" placeholder="请填写备注信息..."></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input name="version" id="version" type="hidden">
						<input name="resId" id="resId" type="hidden">
						<button type="button" id="btnReGeneratorSave">重新生成数据</button></td>
				</tr>
			</table>
		</form>
	</div>
    
</body>

</html>
