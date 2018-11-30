/**
 * 资管校准日志js
 */
$(function($){
	$("#btnSearch").on('click',submitQuery);//查询按钮
	$("#btnMoreQuery").on('click',showMoreQuery);//注册高级查询弹窗
	$("#btnClear").on('click',clearMoreSearchForm); //高级查询清空
	$("#subMoreQuery").on('click',subMoreQuery);//高级查询事件
})

//高级查询确认
function subMoreQuery(){
	$("#moreSearchForm")[0].submit();
}

//显示高级查询窗口
function showMoreQuery()
{
	layer.open(
			{
				type: 1,
				closeBtn: 2,
				title: ['高级查询', 'font-size:18px;'],
				area: ['780px', '450px'],
				shadeClose: true, //点击遮罩关闭
				content:$("#div_moreSearch")
	 		}
	);
}
//提交查询表单
function submitQuery()
{
	$("#resLogType").val($("#resLogType1").val());
	$("#handleType").val($("#handleType1").val());
	$("#modifyUserName").val($("#modifyUserName1").val());
	$("#moreSearchForm")[0].submit();
}


//分页浏览查询
function pageQuery(){
	
	$("#pageNo").val($(this).attr("pages"));
	
	submitQuery();
}
//清空更多查询条件
function clearMoreSearchForm()
{
	$("#resLogType").val("");
	$("#handleType").val("");
	$("#hisContent").val("");
	$("#nowContent").val("");
	$("#dateStart").val("");
	$("#dateEnd").val("");
	$("#modifyUserName").val("");
}