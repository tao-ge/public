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
	<script  type="text/javascript" src="${path }/js/iam/remoteUnlockList.js"></script>
	<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
	<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
	<title>远程开锁申请</title>
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
	    <li><a href="remoteUnlockList.htm">远程开锁</a></li>
	    </ul>
    </div>
        
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	<ul class="toolbar">
		        <li ><a id="hrefApply" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
		    </ul>
	        <div class="simpleQuery">
	           <form name="searchlistform" id="searchlistform">
		                                      设备名称：<input name="devName" id="devName" value="${ru. devName}"  type="text" />
		              <%-- 设施编码：<input name="devCode" id="devCode" value="${ru. devCode}"  type="text" /> --%>
		            <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr style="white-space:nowrap;">
				        <th>设备编码</th>
				        <th width="100px;">设备名称</th>
				        <th>申请人</th>
				        <th>申请开始时间</th>
				        <th>申请结束时间</th>
				        <th>操作人</th>
				        <th>申请时间</th>
				        <th>是否开锁</th>
				        <th>开锁时间</th>
				        <th width="150px;">备注</th>
 				        <th>操作</th> 
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="ru">
				        <tr>
					        <td>${ru.devCode}</td>
					        <td>${ru.devName}</td>
					        <td>${ru.applyUserName}</td>
					        <td>
					        	<fmt:formatDate value="${ru.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					        </td>
					        <td>
					        	<fmt:formatDate value="${ru.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					        </td>
					        <td>${ru.operatUserName}</td>
					        <td>
					        	<fmt:formatDate value="${ru.operatTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					        </td>
					        <td>
					        	<c:choose>
					        		<c:when test="${ru.isOpenlock == '0'}">
					        			否
					        		</c:when>
					        		<c:when test="${ru.isOpenlock == '1'}">
					        			是
					        		</c:when>
					        	</c:choose>
					        </td>
					        <td>
					        	<fmt:formatDate value="${ru.openlockTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					        </td>
					        <td>
					        	${ru.remark }
					        </td>
 					        <td><a href="javascript:;" op="del" did="${ru.applyId}"  class="tablelink">删除</a></td> 
				        </tr>
		       		</c:forEach>
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />    
     </div>
</body>
</html>
