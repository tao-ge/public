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
    	});
		
    	function checkValue(){
    		var userCode = $("#userCode").val();
    		var userName = $("#userName").val();
    		var userPwd = $("#userPwd").val();
    		var mobilePhone = $("#mobilePhone").val();
    		
    		if(userCode == null || userCode == ""){
    			alert("请输入登录用户名！");
    			return false;
    		} else {
    			// 重复check
    			if(!(/^\w+$/.test(userCode))){
    				alert("用户名必须为字母或数字！");
        			return false;
    			}
    		}
    		if(userName == null || userName == ""){
    			alert("请输入真实姓名！");
    			return false;
    		}
    		if(userPwd == null || userPwd == ""){
    			alert("请输入用户密码！");
    			return false;
    		}
    		if(userPwd.length<=5){
    			alert("密码长度必须大于6");
    			return false;
    		}
    		 if(mobilePhone !=null && mobilePhone !='' && !(/^1[34578]\d{9}$/.test(mobilePhone))){
    			alert("手机号码格式不对");
    			return false;
    		}else if(mobilePhone.length==0){
    				alert("手机号码不能为空");
        			return false;
    		}
    		 
    		$("#addform").submit();
    	}
    	function userNameVerify(){
    		
    		var userCode = $("#userCode").val();
    		if(userCode != ''){
	    		$.ajax({
	  		      type: "post",
	  		      url: "userNameVerify.htm",
	  		      cache: false,
	  			  data:{
	  				  "userCode":userCode,  				  
	  			  },
	  			  async: true,
	  		      dataType: "json",
	  		      success: function(data){
	  		    	if(data<=0){
			    	  	$(".btn").removeAttr("disabled");
			    	 }else{
			    		 $("#userCode").val("");
			    		alert("登陆用户名重复，请重新输入！");
			    	  	$(".btn").attr("disabled","disabled");
			    	  }
	  		      }
	    		});
    		}
    	}
    	
    </script>
</head>
<body>
    <div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">系统管理</a></li>
			<li><a href="#">用户编辑</a></li>
		</ul>
	</div>
	
	<div class="formbody">
    
    <div class="formtitle"><span>基本信息</span><font></div>
    
    <form id="addform" name="addform" action="${path}/userInsert.htm" method="post">
    <ul class="forminfo">
    <li><label>登录用户名：</label>
        <input id="userCode" name="userCode" type="text" class="dfinput" onBlur="userNameVerify()" value="${users.userCode}"/><font style="font-size: 25px" color=red>*</font></li>
    <li><label>真实姓名：</label><input id="userName" name="userName" type="text" class="dfinput" value="${users.userName}"/><font style="font-size: 25px" color=red>*</font></li>
    <li><label>用户密码：</label><input id="userPwd" name="userPwd" type="text" class="dfinput" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" value="${users.userPwd}"/><font style="font-size: 25px" color=red>*</font></li>
    <li><label>性别：</label>
    <div class="vocation">
	    <select class="select3" name="sex" id="sex">
	    <c:if test="${users.sex == '0'}">
		<option value='0' selected = "selected">未知</option>
	    <option value='1'>男</option>
	    <option value='2'>女</option>
	    </c:if>
	    <c:if test="${users.sex == null or users.sex == '1'}">
		<option value='0'>未知</option>
	    <option value='1' selected = "selected">男</option>
	    <option value='2'>女</option>
	    </c:if>
	    <c:if test="${users.sex == '2'}">
		<option value='0'>未知</option>
	    <option value='1'>男</option>
	    <option value='2' selected = "selected">女</option>
	    </c:if>
	    </select>
	</div>
    </li>
    <li><label>手机号码：</label><input name="mobilePhone" id="mobilePhone" type="text" class="dfinput" value="${users.mobilePhone}"/><font style="font-size: 25px" color=red>*</font></li>
    <li><label>联系电话：</label><input name="contactPhone" type="text" class="dfinput" value="${users.contactPhone}"/></li>
    <c:if test="${role.roleId==40}">
     <li><label>所属机构：</label>
	    <div class="vocation">
		    <select class="select3" name="orgId" id="orgId">
		        <c:forEach items="${oList}" var="o">
		        <c:if test="${o.orgId == users.orgId}">
		        <option value="${o.orgId}" selected="selected">${o.orgName}</option>
		        </c:if>
		        <c:if test="${o.orgId != users.orgId}">
		        <option value="${o.orgId}">${o.orgName}</option>
		        </c:if>
		        </c:forEach>
		    </select>
		</div>
	    </li>
    </c:if>
    <li><label>手机IMEI：</label><input name="mobileImei" type="text" class="dfinput" value="${users.mobileImei}"/></li>
    <li><label>有效标志：</label>
    <div class="vocation">
	    <select class="select3" name="flag" id="flag">
	    <c:if test="${users.flag == null or users.flag == '1'}">
	    <option value='1' selected = "selected">有效</option>
	    <option value='0'>无效</option>
	    </c:if>
	    <c:if test="${users.flag == '0'}">
	    <option value='1'>有效</option>
	    <option value='0' selected = "selected">无效</option>
	    </c:if>
	    </select>
	</div>
    </li>
    <li><label>角色：</label>
    <div class="vocation">
	    <select class="select3" name="roleId" id="roleId">
<!-- 	        <option value=''></option> -->
	        <c:forEach items="${rList}" var="r">
	        	<c:if test="${r.roleId!=40}">
		       	 	<c:if test="${r.roleId == users.roleId}">
		       			 <option value="${r.roleId}" selected="selected">${r.roleName}</option>
		      	 	 </c:if>
	        		<c:if test="${r.roleId != users.roleId}">
	       				 <option value="${r.roleId}">${r.roleName}</option>
	      			  </c:if>
	        	</c:if>
	        </c:forEach>
	    </select>
	</div>
    </li>
    <c:if test="${role.roleId!=40}">
	    <li><label>所属平台:</label>
	    <div class="vocation">
	    	 <select name="devPlatform" id="devPlatform" class="select3">
	               <c:forEach  items="${orgDevPlatformList}" var="item" varStatus="status">
	                  <option value="${item.valueCode}" <c:if test="${item.valueCode == users.devPlatform}">selected ="selected"</c:if>>${item.valueName}</option>
	               </c:forEach>
			  </select> 
		</div>
	    </li>
    </c:if>
    <li><label>是否同步光调项目:</label>
	    <div class="vocation">
	    	<select class="select3" name="isSynchOpss" id="isSynchOpss">
	    		<option value="0" selected = "selected">否</option>
	    		<option value="1">是</option>
	    	</select>
	    </div>
    </li>
    <li><label>&nbsp;</label><input name="directFlg" type="checkbox" value="1" />保存并继续录入&nbsp;
    <input name="" type="button" class="btn" value="确认保存" onclick="checkValue();"/></li>
    </ul>
    </form>
    </div>
	
</body>
</html>