/**
 * 装维远程开锁申请js
 * @param $
 * @returns
 */
$(function($){
	$("#hrefApply").on("click",goApply);//申请事件
	$("#btnSimpleQuery").on('click', submitQuery);// 查询确认
	//$("a[op='deviceDisinfo']").on('click', deviceDisinfo);
	$("a[op='del']").on("click",deleteData);//加载删除事件
});


function deleteData()
{
	var did=$(this).attr("did");
	//询问框
	layer.confirm('确认要删除这条远程开锁权限吗？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			   type: "POST",
			   url: path+"/remoteUnlockDelete.htm?appId="+did,
			
			   success: function(r){
				   layer.msg(eval(r).r_content, {icon: 1});
				   submitQuery();
			   }
			});   			
	   
	}, null);
}

//查看端子日志
function deviceDisinfo(){
	var codId=$(this).attr("did");
	var flag = '1';
	window.location.href= path +"/queryDeviceDisinfo.htm?flag="+flag+"&codId="+codId;
}

//用高级查询进行提交查询
function submitQuery() {
	$("#searchlistform")[0].action = path+"/remoteUnlockList.htm";
	$("#searchlistform")[0].submit();
}

function goApply(){
	location.href = path+"/goAddremoteUnlock.htm";
}

//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}