/**
 * 处理登录相关业务逻辑
 */


function checkSubmit(){
	
	if(document.getElementById("userCode").value.length<1){
		alert("用户名不能为空！");
		document.getElementById("userCode").focus();
		return false;
	}
	
	if(document.getElementById("userPwd").value.length<1){
		alert("密码不能为空！");
		document.getElementById("userPwd").focus();
		return false;
	}
	
	
	//此处判断是否存储账号
	var nameValue=$("#userCode").val();
	if($("#saveUS").attr("checked")){  //如果选择记住，设置cookie值
		setUserNameCookie(nameValue);
	}
	
	return true;
}

function currentTime(){ 
	  var d=new Date(),str=''; 
	  str+=d.getFullYear()+'年'; 
	  str+=d.getMonth() + 1+'月'; 
	  str+=d.getDate()+'日'; 
	  str+=d.getHours()+'时'; 
	  str+=d.getMinutes()+'分'; 
	  str+= d.getSeconds()+'秒'; 
	  return str; 
	} 
	

$(function(){
	
	 $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	    })
	setInterval(function(){$('#time').html(currentTime)},1000); 
 	var userCode = getCookieValue("userCode");    
 	//alert(userNameValue);
 	$("#userCode").focus();
 	if(""!=userCode){
 		$("#userCode").val(userCode);
 		$("#userPwd").focus();
 	}
 	
 	$("#patchca").on("click",function(){
 		$(this).prop("src" , path + "/patchca.htm?" +Math.random() );
 	})
});