/**
 * 设备上报日志js
 * @param $
 * @returns
 */
$(function($){
	$("#btnSimpleQuery").on('click',simpleQuery);//注册简单查询事件
});


//简单查询提交
function simpleQuery()
{
	//$("#btnClear").click();
	$("#pageNo").val('1');
	
	submitQuery();
}

//查询
function submitQuery() {
	$("#searchlistform")[0].submit();
}

//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}

//清空更多查询条件
function clearMoreSearchForm()
{
	$(':input','#moreSearchForm')    		
	.val('') 
	.removeAttr('checked') 
	.removeAttr('selected');
    //.not(':button, :submit, :reset, :hidden') 
}