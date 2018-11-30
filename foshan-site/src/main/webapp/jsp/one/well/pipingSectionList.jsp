<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script  type="text/javascript" src="${path }/js/prompt/ymPrompt.js"></script>
<link rel="stylesheet"  type="text/css" href="${path}/js/prompt/skin/ymPrompt.css" />
<script type="text/javascript" src="${path}/js/well/pipingSection.js"></script>
<script type="text/javascript">
	$(function(){
		$('.tablelist tbody tr:odd').addClass('odd');
	})
	path = '${path }';
</script>
<title>信息管理系统界面</title>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">井资源管理</a></li>
			<li><a href="#">管道段管理</a></li>
		</ul>
	</div>


	<div class="rightinfo">
		<div class="tools">
<!-- 			<ul class="toolbar"> -->
<%-- 				<li><a id="hrefAdd" href=""><span><img src="${path}/images/t01.png" /></span>添加</a></li> --%>
<!-- 			</ul> -->
			<div>
				<form name="searchlistform" id="searchlistform" action="${path}/queryPipingSections.htm" method="post">
					<input name="wellName" style="width:300px;" type="text"  class="dfinput" id="wellName" value="${pipingSection.wellName }" />
					<input id="btnSerchEx" class="submit" value="选择按井查询" type="button"/>
					管道段名称：<input name="pipingName" id="pipingName" value="${pipingSection.pipingName }" type="text" /> 
					管道段状态： <select name="pipingSectType" id="pipingSectTypeSearch">
						<option value="" >请选择</option>
						<c:forEach items="${typeList }" var="u" varStatus="status">
							<option value="${u.key }" <c:if test="${u.key == pipingSection.pipingSectType }">selected ="selected"</c:if>>
							<c:if test="${u.key==0}">
					        	<td>${u.value }</td>
					        </c:if>
					       <c:if test="${u.key==1}">
					       		<td>${u.value }</td>
					       </c:if> 
					        <c:if test="${u.key==2}">
					       		<td>${u.value }</td>
					       </c:if> 
					       <c:if test="${u.key==3}">
					       		<td>${u.value }</td>
					       </c:if> 
					       <c:if test="${u.key==4}">
					        	<td>${u.value }</td>
					        </c:if>
					        <c:if test="${u.key==5}">
					        	<td>${u.value }</td>
					        </c:if>
					         <c:if test="${u.key==6}">
					        	<td>${u.value }</td>
					        </c:if>
							</option>
						</c:forEach>
					</select>
					<input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
					<button id="btnSearch" name="btnSearch" type="submit" class="submit">查询</button>
				</form>
			</div>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>编号<i class="sort"><img src="images/px.gif" /></i></th>
					<th>管道段名字</th>
					
					<th>物理长度</th>
					<th>地图长度</th>
					<th>最后修改人</th>
					<th>最后修改时间</th>
					<th>管道段状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${pb.list }" var="u" varStatus="i">
				<tr>
					<td>${i.count}</td>
			        <td>${u.pipingName}</td>
			      
			        <td>${u.phyLen}</td>
			        <td>${u.mapLen}</td>
			        <td>${u.userName}</td>
			        <td> <fmt:formatDate value="${u.lastModifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			          <c:if test="${u.pipingSectType==0}">
			        <td>未核查</td>
			        </c:if>
			       <c:if test="${u.pipingSectType==1}">
			       <td>与现场一致</td>
			       </c:if> 
			        <c:if test="${u.pipingSectType==2}">
			       <td>新增</td>
			       </c:if> 
			       <c:if test="${u.pipingSectType==3}">
			       <td>修改</td>
			       </c:if> 
			       <c:if test="${u.pipingSectType==4}">
			        <td>未找到</td>
			        </c:if>
			        <c:if test="${u.pipingSectType==5}">
			        <td>新增删除</td>
			        </c:if>
			         <c:if test="${u.pipingSectType==6}">
			        <td>存疑</td>
			        </c:if>
			        <td>
<%-- 				        <a href="${path}/pipingSectionUpdate.htm?pipingSectId=${u.pipingSectId}" class="tablelink">修改</a> --%>
<%-- 				        <a href="javascript:;" op="del" dt="${u.pipingName }" did="${u.pipingSectId}" class="tablelink" >删除</a> --%>
			        </td>
			    </tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div class="clear"></div>
		<jsp:include page="/jsp/one/common/page.jsp" />
		</div>
		
		<!-- 高级查询 -->
		<div id="div_moreSearch" class="newlayer" style="display: none;">
				<table id="tableDev" class="tablelist">
					<thead>
						<tr>
							<th>井名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
	  </div>
</body>
</html>
