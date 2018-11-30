/**
 * 远程开锁申请js
 * @param $
 * @returns
 */
$(function($){
	$("#hrefApply").on("click",goApply);//申请事件
	$("#btnSimpleQuery").on('click', submitQuery);// 查询确认
});

//用高级查询进行提交查询
function submitQuery() {
	$("#searchlistform")[0].submit();
}

function goApply(){
	location.href = path+"/goRemoteUnlock.htm";
}

//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}