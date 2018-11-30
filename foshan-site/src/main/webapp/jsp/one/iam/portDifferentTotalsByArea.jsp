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
	<script  type="text/javascript" src="${path }/js/iam/portDifferentTotalsByArea.js"></script>
	<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"> path='${path }'; </script>
	<script type="text/javascript">
		$(function(){
    		$('.tablelist tbody tr:odd').addClass('odd');
    	})
	</script>
	<title>设施管理</title>
</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="index.htm">首页</a></li>
	   		<li><a href="index.htm">智能设备管理</a></li>
	        <li><a href="portDifferentTotals.htm">端子数据统计-业务</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	<ul class="toolbar">
		        <li >&nbsp;&nbsp;&nbsp;统计数据截止日期: <a style="color: red;"><fmt:formatDate value="${time }" pattern="yyyy-MM-dd HH:mm:ss" /></a></li>
		 	</ul>
	        <div class="simpleQuery">
		         <form action="${path}/portDifferentTotalsByArea.htm" method="post" id="moreSearchForm" name="moreSearchForm">
		       		<input name="pageNo" id="pageNo" value="${pb.pageNo }" type="hidden">
		       		设施名称 ：<input id="devName" name="devName" value="${differentPortsBean.devName }" type="text"/>
	       			设施类型：<select name="devType" id="devType">
					      <option value="">全部</option>
                          <c:forEach  items="${devTypeList}" var="item" varStatus="status">
                             <option value="${item.name}" <c:if test="${item.name == differentPortsBean.devType}">selected ="selected"</c:if>>${item.value}</option>
                          </c:forEach>
					   </select> 
		       		所属区:<select class="areadevCode" id="areaCode1" name="areaCode1" runat="server" selected="selected">
		     			<option value="" >全部</option>
		     			<c:if test="${areaCode1 eq '0' }">
		     				<option value="0" <c:if test="${areaCode1 eq '0' }">selected="selected"</c:if>>未知</option>
		     			</c:if>
		     			<c:forEach items="${areaList}" var="area" >
		     			<option value="${area.name}" 
		     				<c:if test="${area.name==areaCode1}">selected="selected"</c:if>	
		     			>
		     				${area.value}
		     			</option>
		     			</c:forEach>
		     		</select>
			     	汇聚区:<select name="areaCode2" id="areaCode2" class="hjq" runat="server" selected="selected">
			     
			     	</select> 
			     	<input name="code" id="code" value="${areaCode2}" type="hidden">
	     			<input id="btnSimpleQuery" value="查询" class="submit" type="button">
			       </table>
	       		 </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			       <tr style="white-space:nowrap;">
				        <th>序号</th>
				        <th>业务点</th>
				        <th>业务点类型</th>
				        <th>可用端口数</th>
				        <th>已用端口数</th>
				        <th>端口占用率</th>
				        <th>可用端口数-监测</th>
				        <th>已用端口数-监测</th>
				        <th>端口差异数</th>
				        <th>端口差异率</th>
				        <th>操作</th>
			       </tr>
		        </thead>
		        <tbody>
			       <c:forEach items="${pb.list}" var="d" varStatus="st">
				        <tr>
						    <td>${st.index + 1}</td>
						    <td width="400px;">${d.devName}</td>
						    <td>${d.devTypeName}</td>
						    <td>${d.isNotOccup}</td>
						    <td>${d.isOccup}</td>
						    <td>
						  	  ${d.occupyBat}
						    </td>
						    <td>${d.portFreeNum}</td>
						    <td>${d.portOccupyNum}</td>
						    <td>${d.portErrorNum}</td>
						    <td>
						      ${d.errorNumBat}
						    </td>
						    <td>
								<a href="javascript:;" op="detail" did="${d.devId }">详情</a>
							</td>
				        </tr>
			       	</c:forEach> 
		        </tbody>
		    </table>  
		    <div class="clear"></div>   
		    <jsp:include page="/jsp/one/common/page.jsp" />  
     </div>
</body>
</html>