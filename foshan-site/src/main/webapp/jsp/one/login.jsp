<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 	<c:if test="${ipValue==1}">
		<title>传输网络智慧管理系统${sessionId}</title>
    </c:if>
    <c:if test="${ipValue==0}">
    	<title>传输网络资源普查系统${sessionId}</title>
    </c:if>

<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<script>
	var path = '${path}';
</script>
<script language="JavaScript" src="${path}/js/jquery.js"></script>
<script src="${path}/js/cloud.js" type="text/javascript"></script>
<script src="${path}/js/cookie.js" type="text/javascript"></script>
<script src="${path}/js/userlogin.js" type="text/javascript"></script>


</head>
<body style="background-color:#354f6f; background-image:url(images/light.png); background-repeat:repeat-x; background-position:center top; overflow:hidden;">

    <div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
    </div>  

<div class="logintop">    
    <span id="time"></span>   

 
    <ul>
    <li><a href="#"></a></li>
<!--     <li><a href="http://39.108.48.7:8080/#Contact%20us"  target="_blank">联系我们</a></li>
    <li><a href="http://39.108.48.7:8080/#Partner%20&%20affiliates" target="_blank">关于</a></li> -->
    <li><a href="${path}/conectionUs.htm"  target="_blank">联系我们</a></li>
    <li><a href="${path}/aboutUs.htm" target="_blank">关于</a></li>
      
    </ul>    
    </div>
     
    <div class="loginbody">
    
    <c:if test="${ipValue==1}">
    	<span class="systemlogo1"></span> 
    </c:if>
    <c:if test="${ipValue==0}">
    	<span class="systemlogo0"></span> 
    </c:if>
       
    <div class="loginbox">
    <form name="loginform" onsubmit="return checkSubmit();" action="${path}/userLogin.htm" method="post">
    <ul>
    <li><input name="userCode" id="userCode" type="text" class="loginuser" value="" onclick="JavaScript:this.value=''"/></li>
    <li><input name="userPwd"  id="userPwd" type="password" class="loginpwd" value="" onclick="JavaScript:this.value=''"/></li>
    <li><input name="validCode"  id="validCode" type="text" class="loginvalid" value="" onclick="JavaScript:this.value=''"/><img src="${path}/patchca.htm"  id = "patchca" style="display:inline-block; float:right;width:100px; height:48px;line-height:48px;margin-right:100px;padding:0;"></li>
    <li><input name="loginbtn" type="submit" class="loginbtn" value="登录" />
    <label><input name="saveUS" id="saveUS" type="checkbox" value="" checked="checked" />记住账号</label>&nbsp;&nbsp;<font color="red">${result}${sessionId }</font></li>
    </ul>
    </form>
    
    </div>
    
    </div>
     <c:if test="${ipValue==1}">
   		<div class="loginbm">传输网络智慧管理系统</div>
    </c:if>
    <c:if test="${ipValue==0}">
   		<div class="loginbm">传输网络资源普查系统</div>
    </c:if>
    <!-- <div class="loginbm">版权所有 2016 ycthingnet.com  光纤传输网管理云平台</div> -->
</body>
</html>
