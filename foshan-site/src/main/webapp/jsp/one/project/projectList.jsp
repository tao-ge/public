<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script  type="text/javascript" src="${path }/js/project/projectList.js?2dwadaw"></script>
	<title>工程管理</title>

</head>
<body>
    <!-- 页面导航  -->
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="index.htm">首页</a></li>
	    <li><a href="index.htm">资源台账管理</a></li>
	    <li><a href="projectList.htm">工程管理</a></li>
	    </ul>
    </div>
        
    <div class="rightinfo">
	    <!-- 操作按钮：新增、导入、导出、查询  -->
	    <div class="tools">
	    	<ul class="toolbar">
		        <li ><a id="hrefAdd" href="javascript:;"><span><img src="${path}/images/t01.png" /></span>添加</a></li>
		        <li class="click" style="display:none;"><a href=""><span><img src="${path}/images/t02.png" /></span>导入</a></li>
		        
		    </ul>
	        <div class="simpleQuery">
	           <form>
	                                      工程编码：<input id="proCodeSearch"  value="${bean.proCode}"   type="text" />
	                                      工程名称：<input id="proNameSearch"  value="${bean.proName}"  type="text" />
	                             <%--          所属机构：<select id="devTypeSearch">
						      <option value="">全部</option>
	                         <c:forEach  items="${deviceTypeList}" var="item" varStatus="status">
	                            <option value="${item.name}" <c:if test="${item.name == facilityCon.devType}">selected ="selected"</c:if>>${item.value}</option>
	                         </c:forEach>
						  </select>  --%>
						
	                <input  id="btnSimpleQuery" value="查询" class="submit" type="button">
	                <button id="btnDisplayMoreQuery" type="button" hidden>高级查询</button>
	           </form>
	        </div>  
	    </div>    
	    <!-- 查询数据列表 -->
	    <table class="tablelist">
		    	<thead>
			    	<tr>
				        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
				        <th>工程编码</th>
				        <th>工程名称</th>
				        <th>所属机构</th>
				        <th>操作</th>
			        </tr>
		        </thead>
		        <tbody>
			        <c:forEach items="${pb.list}" var="f" varStatus="status">
				        <tr>
				        <td>${status.count}</td>
				        <td>${f.proCode}</td>
				        <td>${f.proName}</td>
				        <td>${f.orgName}</td>
				        <td>
				        	<a href="javascript:;" op="modify" pid="${f.proId}"  class="tablelink">修改</a>
				        	<a href="javascript:;" op="del" pid="${f.proId}" dt="${f.proName}" class="tablelink"> 删除</a>
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
	<div id="div_moreSearch" class="newlayer" style="display:none;">
	       <form action="${path}/projectList.htm" method="post" id="moreSearchForm" name="moreSearchForm">
			   <table width="100%" border="1" class="table1">
			     <tr>
			       <td>工程编码：</td>
			       <td><input id="proCode" name="proCode" type="text"  value="${bean.proCode}" /></td>
			       <td> ：</td>
			       <td><input name=" "   value=" "  type="text"/></td>
		         </tr>
			     <tr>
			       <td>工程名称：</td>
			       <td><input id="proName" name="proName" type="text"  value="${bean.proName}" /></td>
			      <%--  <td>所属机构：</td>
			       <td><label for="select"></label>
			         <select name="areaCode1" id="areaCode1" >
			           <option value="">全部</option>
	                         <c:forEach  items="${areasServiceList}" var="item" varStatus="status">
	                            <option value="${item.areaCode}" <c:if test="${item.areaCode == facilityCon.areaCode1}">selected ="selected"</c:if>>${item.areaName}</option>
	                         </c:forEach>
                      </select>
                   </td> --%>
		         </tr>
			     <tr>
			       <td colspan="4" align="center">
				       <input id="pageNo" name="pageNo" value="${pb.pageNo}" type="hidden" />
	                   <button type="button" id="btnMoreQuery">查询</button>&nbsp;&nbsp;
	                   <button type="button" name="btnExport" id="btnExport" >导出</button>
	                   <button type="button" name="btnClear" id="btnClear" >清空</button>
                   </td>
		         </tr>                                                   
		       </table>
        </form>
      </div>

</body>

</html>
