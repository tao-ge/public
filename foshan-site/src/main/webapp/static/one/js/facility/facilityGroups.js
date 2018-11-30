/**
 * 设施管理>详情>设施分组信息页面js
 */
$(function($){
	$("a[op='update']").on("click",updateGroup);//加载修改事件
	$("a[op='del']").on("click",deleteGroup);//加载删除事件
})

//提交查询表单
function submitQuery()
{
	$("#SearchForm")[0].submit();
}

//加载删除事件
function deleteGroup (){
	var dt=$(this).attr("dt");
	var did=$(this).attr("did");
	//询问框
	layer.confirm('确认要删除"'+dt+'"？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			   type: "POST",
			   url: path+"/deleteFacilityGroups.htm?groupId="+did,
			   success: function(r){
				   layer.msg(eval(r).r_content, {icon: 1});
				   submitQuery();
			   }
			});   			
	   
	}, null);
}

//加载修改事件
function updateGroup(){
	var groupId = $(this).attr("did");
	var url='';
	url=path+"/modifyfacilityGroups.htm?groupId="+groupId;
	var index = layer.open({
		  type: 2,
		  content: url,
		  area: ['500px', '520px'],
		  maxmin: true
		});
}