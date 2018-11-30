/**
 * 设备上报日志js
 * @param $
 * @returns
 */
$(function($){
	$(".areadevCode").on("change",function(){
		changeArea($(this));
	})
	$(".areadevCode").change();
	$("#btnSimpleQuery").on('click', simpleQuery);// 查询确认
	$("a[op='detail']").on('click',detail);//注册详情
});

function detail (){
	var devId= $(this).attr("did");
	location.href = path + "/portOccupyCodition.htm?devId="+devId;
}

function changeArea(districtObj){
	var areaCode2=$("#code").val();
	var  value = $(districtObj).val();
	
	$("#areaCode2").html("");
	$("#areaCode2").append("<option value=''>==请选择==</option>");
	$.ajax({
		type:"POST",
		url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
		dataType:"json",
		success:function(data)
		{
			$.each(data,function(index,value){
				 if(areaCode2==value.name)
				{
					 $("#areaCode2").append("<option value='"+data[index].name+"'' selected>"+data[index].value+"</option>");
				}else{
					$("#areaCode2").append($("<option>").prop("value",data[index].name).append(data[index].value));
				}
			});
		}
	});
	
}

//简单查询提交
function simpleQuery()
{
	$("#pageNo").val('1');
	submitQuery();
}

//查询
function submitQuery() {
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
	$(':input','#moreSearchForm')    		
	.val('') 
	.removeAttr('checked') 
	.removeAttr('selected');
    //.not(':button, :submit, :reset, :hidden') 
}