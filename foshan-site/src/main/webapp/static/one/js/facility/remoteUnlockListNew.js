/**
 * 新远程开锁js
 */
$(function($) {
	$("#btnSearch").on('click',submitQuery);//查询确认
	$("input[op='unlock']").on("click", unlocking);//开锁点击事件
});

//开锁点击事件
function unlocking(){
	var devImei = $(this).attr("rid");
	var unlockTime = new Date().getTime();//获取时间戳
	var index = layer.msg('开锁中...',{icon: 16,time : (90 * 1000)});//提示
	//查询是否开锁
	$.ajax({
		type : "POST",
		url : path + "/queryLockRecord.htm?unlockTime="+unlockTime+"&devImei="+devImei,
		success : function(data) {
			if(data.r == 1){//开锁成功
				layer.msg(data.r_content, {
					icon : 1,
					time : 3000
				});
			}else if(data.r == 2){//开锁超时
				layer.msg(data.r_content, {icon : 4, time : 3000});
			}else{//开锁失败
				layer.msg(data.r_content, {
					icon : 2,
					time : 3000
				});
			}
			layer.close(index);
		},
		error:function(){
			layer.msg('开锁失败', {
				icon : 2,
				time : 3000
			});
			layer.close(index);
		}
	});
	//页面定时5秒查询测试(废弃)
//	//5秒查询一次
//	var index = setInterval(function(){
//		var now = new Date().getTime();
//		//如果超过90秒,停止查询
//		if((now - unlockTime) > (20 * 1000)){
//			window.clearInterval(index);
//		}
//		console.log(count);
//		count ++;
//	},5 * 1000);
}

//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}

//提交查询表单
function submitQuery()
{
	$("#searchlistform")[0].action = path+'/remoteUnlockNew.htm';   
	$("#searchlistform")[0].submit();
}
