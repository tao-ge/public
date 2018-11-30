/**
 * 设备上报日志js
 * @param $
 * @returns
 */
$(function($){
	$("a[op='detail']").on('click',detail);//注册详情
});

function detail (){
	var areaCode1= $(this).attr("did");
	if (areaCode1==null || areaCode1=='') {
		areaCode1='0';
	}
	location.href = path + "/portDifferentTotalsByArea.htm?areaCode1="+areaCode1;
}

//简单查询提交
function simpleQuery()
{
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