<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${path}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path }/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${path}/js/tinyselect.js"></script>
<script type="text/javascript" src="${path }/js/layer.js"></script>
<script type="text/javascript" src="${path}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	var path = "${path}";
</script>
<title>change password</title>
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<style type="text/css">
	font{
		color: red;
		size: 8;
	};
</style>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="index.htm">首页</a></li>
			<li><a href="#">修改密码</a></li>
		</ul>
	</div>
	<div class="formbody">
		<form id="submitForm" name="submitForm">
			<div class="formtitle">
				<span>输入密码</span>
			</div>
			
			<table class="tableForm">
				<tbody>
					<tr>				
						<td class="odd">原始密码:</td>
						<td><input class="dfinput" id="oriPassword" maxlength="30" name="oriPassword" onkeyup="yanzheng();" type="password"></td>
						<td><span id="oldPwd"></span></td>
					</tr>
					<tr>
						<td class="odd">新密码:</td>
						<td><input class="dfinput" id="newPassword" maxlength="30" name="newPassword" type="password"></td>
					</tr>
					<tr>
						<td class="odd">新密码确认:</td>
						<td><input class="dfinput" id="confirmPassword" maxlength="30" name="confirmPassword" onkeyup="checkNewPwd();" type="password"></td>
						<td><span id="message"></span></td>
					</tr>
					<tr>
						<td colspan="2"><input style="margin-left: 40%;" class="btn" id="btnSubmit" type="button" value="确认修改"></td>
					</tr>
				</tbody>
			</table>
		
		</form>
</div>
</body>


<script>
$("#btnSubmit").on("click", btnSubmit);
function btnSubmit(){
	if($("#oriPassword").val() == null || $("#oriPassword").val() == "")
	{
		alert("原始密码不能为空");
		return false;
	}
	if($("#newPassword").val() == null || $("#newPassword").val() == "")
	{
		alert("新密码不能为空");
		return false;
	}
	if($("#confirmPassword").val() == null || $("#confirmPassword").val() == "")
	{
		alert("确认密码不能为空");
		return false;
	};
	
	$.ajax({
		type:"POST",
		url: path + "/submitPWD.htm",
		dataType:"json",
		data:{
			oriPassword : $("#oriPassword").val(),
			newPassword : $("#newPassword").val()
		},
		success:function(data)
		{
			if(data.r > 0){
				layer.confirm('修改成功，回到首页？', {
					btn : [ '是', '否' ]
				}, function() {
					location.href = path + "/index.htm";
				}, function() {
					$("#oriPassword").val("");
					$("#newPassword").val("");
					$("#confirmPassword").val("");
					document.getElementById('ft').innerText='';
					document.getElementById('oldPwd').innerText='';
				});
			}else{
				layer.msg(data.r_content, {
					icon : 1
				});
			}
		}
	});
}

function checkNewPwd(){
	var newPassword = document.getElementById("newPassword").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
	if(newPassword != confirmPassword){
		document.getElementById("message").innerHTML="<font id='ft'>*两次输入不一致</font>";
		document.getElementById("btnSubmit").disabled = true;
	}else{
		document.getElementById("message").innerHTML="<font id='ft'>*通过</font>";
		document.getElementById("btnSubmit").disabled = false;
	}
}

function yanzheng(){
	var qrPassword = $("#oriPassword").val();
	$.ajax({
	      type: "post",
	      url: "userPassword.htm",
	      cache: false,
		  data:{
			  "qrPassword":qrPassword,  				  
		  },
		  async: true,
	      dataType: "json",
	      success: function(data){
	    	if(data==1){
	    		document.getElementById("oldPwd").innerHTML="<font id='oldPwd'>*通过</font>";
	    	  	$(".btn").removeAttr("disabled");
	    	 }else{
	    		 document.getElementById("oldPwd").innerHTML="<font id='oldPwd'>*原始密码不正确</font>";
	    	  	$(".btn").attr("disabled","disabled");
	    	  }
	      }
		});
}
</script>
</html>