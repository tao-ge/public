<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>角色编辑界面</title>
    <script type="text/javascript">
    	var path="${path}";
    	$(document).ready(function(e) {

    		$(".select3").uedSelect({
    			width : 152
    		});
    		
    		var rank = $("#rank").val();
    		if(rank == "1"){
    			$('#parentPage').attr("disabled","disabled");
    		} else {
    			$('#parentPage').removeAttr("disabled");
    		}
    	});
    	
    	function changeRank(){
    		var rank = $("#rank").val();
    		var pagerank;
    		if(rank == "1"){
    			$('#parentPage').attr("disabled","disabled");
    			var a=document.getElementById('pageranks').value;
    			if(a=="1"){
    			var p=document.getElementById('pageOrders1').value;
    			document.getElementById("pageOrders").value=p; 
    			}else{
    				pageranks=1;
    				$.ajax({
            			type:"POST",
            			url:path+"/pageordder.htm?pageRank="+pageranks,
            			success: function(orderby){
            				 
            				  document.getElementById("pageOrders").value=orderby; 
            				 
            			}
            		})
    			}
    		} else {
    			$('#parentPage').removeAttr("disabled");
    			 if(rank == "2"){
     				
     				pagerank=2;
     			
     			}
    			 $.ajax({
    	    			type:"POST",
    	    			url:path+"/pageordder.htm?pageRank="+pagerank,
    	    			success: function(orderby){
    	    				  
    	    				  document.getElementById("pageOrders").value=orderby; 
    	    				 
    	    			}
    	    		})
    		}
    		
    	}
    	
    	function checkValue(){
    		var pageName = $("#pageName").val();
    		var pageUrl = $("#pageUrl").val();
    		var parentPage = $("#parentPage").val();
    		var rank = $("#rank").val();
			
    		if(pageName == null || pageName == ""){
    			alert("请输入功能名称！");
    			return false;
    		}
    		
    		if(pageUrl != null && pageUrl != "" ){
        		var num = pageUrl.indexOf("?");
        		if(num != -1){
            		if(pageUrl.substring(num-4,num) != ".htm"){
            			alert("请输入合格的功能连接！例：xxxx/xxxx.htm?pb.pageSize=20");
            			return false;
            		}
        		} else {
        			if(pageUrl.substring(pageUrl.length-4,pageUrl.length) != ".htm"){
            			alert("请输入合格的功能连接！例：xxxx/xxxx.htm?pb.pageSize=20");
            			return false;
            		}
        		}
    		}else{
    			if(rank!=1){
    				alert("请输入功能连接！");
        			return false;
    			}
    		}
    		
    		if(rank == 2  && (parentPage == null || parentPage == "" ) ){
    			alert("请选择所属功能");
    			return false;
    		}
    		
    		$("#updateInfo").submit();
    	}
    </script>
</head>
<body>
    <div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">系统管理</a></li>
			<li><a href="#">功能编辑</a></li>
		</ul>
	</div>
	
	<div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <form id="updateInfo" name="updateInfo" action="${path}/pageUpdate.htm" method="post" enctype="multipart/form-data">
    <input type="hidden" id="pageId" name="pageId" value="${pages.pageId}">
    <%-- <input type="hidden" id="icon" name="icon" value="${pages.icon}"> --%>
    <ul class="forminfo">
    <li><label>功能名称</label><input id="pageName" name="pageName" type="text" class="dfinput" value="${pages.pageName}"/><font style="font-size: 25px" color=red>*</font></li>
    <li><label>功能连接</label><input id="pageUrl" name="pageUrl" type="text" class="dfinput" value="${pages.pageUrl}" /><font style="font-size: 25px" color=red>*</font></li>
    <li><label>功能图标</label>
    <input id="icon" name="icon" type="text" class="dfinput" value="${pages.icon}" readonly="readonly"/><br/><br/>
    <label>重新选择图标</label><input id="uploadFile" name="uploadFile" type="file" class="dfinput"/><i><font color="red"> ${error} </font></i>
    </li>
    <li><label>功能级别</label>
    <div class="vocation">
	    <select class="select3" name="pageRank" id="rank" onchange="changeRank();">
	    <c:if test="${pages.pageRank == '1'}">
	    <option value='1' selected="selected">一级</option>
	    <option value='2'>二级</option>
	    </c:if>
	    <c:if test="${pages.pageRank == '2'}">
	    <option value='1'>一级</option>
	    <option value='2' selected="selected">二级</option>
	    </c:if>
	    </select>
	     <input id="pageranks" name="pageranks" value="${pages.pageRank}"type="hidden">
	</div>
    </li>
    <li><label>功能排序</label><input name="pageOrders" id="pageOrders"type="text" class="dfinput" value="${pages.pageOrders}" onkeyup="value=value.replace(/[^\d]/g,'')"readonly="readonly"/></li>
    <li><input name="pageOrders1" id="pageOrders1" type="hidden" class="hidden" value="${pages.pageOrders}" onkeyup="value=value.replace(/[^\d]/g,'')" ></li>
    <li><label>功能描述</label><input name="pageDesc" type="text" class="dfinput" value="${pages.pageDesc}" /></li>
    <li><label>所属功能</label>
    <div class="vocation">
	    <select class="select3" name="parentPageId" id="parentPage">
	    <c:if test="${pages.pageRank == '1'}">
	    	<option value=''></option>
	    </c:if>
	    <c:forEach items="${parentList}" var="r">
	    	<c:if test="${pages.parentPageId == r.name}">
	    		<option value="${r.name}" selected="selected">${r.value}</option>
	    	</c:if>
	    	<c:if test="${pages.parentPageId != r.name}">
	    		<option value="${r.name}">${r.value}</option>
	    	</c:if>
	    </c:forEach>
	    </select>
	</div>
    </li>
    <li><label>有效标志</label>
    <div class="vocation">
	    <select class="select3" name="flag" id="areas">
	    <c:if test="${pages.flag == '1'}">
	    <option value='1' selected="selected">有效</option>
	    <option value='0'>无效</option>
	    </c:if>
	    <c:if test="${pages.flag == '0'}">
	    <option value='1'>有效</option>
	    <option value='0' selected="selected">无效</option>
	    </c:if>
	    </select>
	</div>
    </li>
    <li><label>&nbsp;</label>
    <input name="" type="button" class="btn" value="确认保存" onclick="checkValue();"/></li>
    </ul>
    </form>
    
    </div>

</body>
</html>