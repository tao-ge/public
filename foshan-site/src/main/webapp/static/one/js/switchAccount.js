/**
 * 切换账号
 */
$(function(){
	$("#btnSubmit").on("click",checkSubmit);
 	clearAll();
})

//var users = null;
function checkSubmit(){
	
	if(document.getElementById("userCode").value.length<1){
		layer.msg("账号不能为空！",{icon:2});
		document.getElementById("userCode").focus();
		return false;
	}
	
	if(document.getElementById("userPwd").value.length<1){
		layer.msg("密码不能为空！",{icon:2});
		document.getElementById("userPwd").focus();
		return false;
	}
	
	layer.confirm('确认切换?', {
		btn : [ '确定', '返回' ]
	// 按钮
	}, function() {
		layer.msg('切换账号中...', {
			icon : 16
		});
		var url = path +"/switchAccountLogin.htm";
		var data = {userCode:$("#userCode").val(),userPwd:$("#userPwd").val()};
		$.post(url,data,function(r){
			if (r.r>0) {
				layer.msg(r.r_content,{icon:1,time: 10000});
				parent.window.location.reload();
			}else{
				layer.alert(r.r_content);
				$("#userCode").val("");
				$("#userPwd").val("");
			}
		})
	}, function() {
		location.href = path + "/switchAccount.htm";
	});
}

function clearAll(){
	$("#userCode").val("");
	$("#userPwd").val("");
}

