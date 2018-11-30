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
<script type="text/javascript">
	path = '${path }';
	rn = "${roles.roleName}";
</script>
<title>角色编辑界面</title>
<script type="text/javascript">
	function checkValue(){
		var roleName = $("#roleName").val();
		
		if(roleName == null || roleName == ""){
			alert("请输入角色名称！");
			return false;
		}
		
		$("#searchform").submit();
	}
	
	/* 选择权限 */
	function selectP(f){
		  if(document.getElementById(f).checked==true) {
			  $("input[id="+f+"]").prop('checked', false);
		  } else {
			  $("input[id="+f+"]").prop('checked', true);
		  }
	}
	
	//全选/全不选
	function selectAll(){
	 $("input[name='checkAll']").each( function() {
		  if($(this).prop("checked")==true) {
		  	$("input[name='chosePages']").prop('checked', true);
		  	return;
		  } else {
		 	$("input[name='chosePages']").prop('checked', false);
		  	return;
		  }
	 });
 	}
	function checkRoleName(){
		var roleName = $("#roleName").val();
// 		alert(rn);
// 		alert(roleName);
// 		alert(path);
		$.ajax({
				   type: "POST",
				   url: path+"/checkRoleName.htm?roleName="+roleName,
				   success: function(r){
					   if(r == "no" ){
						   if(roleName != rn){
							   alert("角色名重复");
						   }
						   $("#qr").attr("disabled",true);
					   }
					   if(r == "yes"){
						   $("#qr").attr("disabled",false);
					   }
				   }
				});  
	}
	
	
</script>
</head>
<body>
    <div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">系统管理</a></li>
			<li><a href="#">角色编辑</a></li>
		</ul>
	</div>
    
    <div class="formbody">
    
    	<div class="formtitle"><span>基本信息</span></div>
    
    	<form id="searchform" name="searchform" action="${path}/rolesUpdate.htm" method="post">
    	<input type="hidden" name="roleId" value="${roles.roleId}">
	    <ul class="forminfo">
	    <li><label>角色名称：</label><input id="roleName" name="roleName" onblur="checkRoleName()" type="text" class="dfinput" value="${roles.roleName}"/><font style="font-size: 25px" color=red>*</font></li>
	    <li><label>角色备注：</label><input name="remark" type="text" class="dfinput" value="${roles.remark}"/></li>
	    <li >
	    	<label>功能权限：</label>
	    	<table >
	    		<tr style="display: block;margin: 2px;">
	    			<td><input type="checkbox" name="checkAll" onclick="selectAll()">全选/全不选</td>
	    		</tr>
	    		<tr>
		    	<c:forEach items="${fristPages}" var="f">
		    		<c:if test="${f.childPages.size()!=0 && f.flag eq 1}">
				    		<td style="width: 100px;vertical-align: top;padding: 2px;">
				    			<input name="" type="button" style="width: 99px;height: 20px;color: blue;background-color: #ABABAB" value="${f.pageName}" onclick="selectP('${f.pageName}');"/>
				    		    <c:forEach items="${f.childPages}"  var="child">
				    		    	<c:if test="${ child.flag  eq  1}">
				    		        	<c:if test="${child.chooseFlag == 'true'}">
		    		                   		<input id="${f.pageName}" name="chosePages" type="checkbox" value="${child.pageId}" checked="checked">${child.pageName} 
		    		                		<br>
		    		                	</c:if>
			    		                <c:if test="${child.chooseFlag != 'true'}">
			    		                    <input id="${f.pageName}" name="chosePages" type="checkbox" value="${child.pageId}">${child.pageName}
			    		                	<br>
			    		                </c:if>
				    		    	</c:if>
				    		    </c:forEach>
			    		    </td>
		    		</c:if>
		    	</c:forEach>
	    	</tr>
	    	</table>
	    </li>
	    <li><label>&nbsp;</label>
    	<input id="qr" name="" type="button" class="btn" value="确认保存" onclick="checkValue();"/></li>
    	</ul>
		</form>
	</div>
    
</body>
</html>