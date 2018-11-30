/**
 * 差异数据统计js
 * @param $
 * @returns
 */
$(function($){
	$("#btnSimpleQuery").on('click', submitQuery);// 查询确认
	$(".areadevCode").on("change",function(){
		changeArea($(this));
	})
	$("a[op='detail']").on('click', discDetail);
});

//熔纤盘详情
function discDetail(){
	var devId=$(this).attr("did");
	var flag = "1";
	window.location.href= path+"/fiberdiscChart.htm?flag="+flag+"&devId="+devId;
}


//查询
function submitQuery() {
	var areaCode1;
	var areadevCode = $("#areadevCode").val();
	var areaCode = $("#areaCode1").val();
	//如果汇聚区为空则用所属区查询
	if(areaCode != null && areaCode != ''){
		areaCode1 = areaCode;
	}else{
		areaCode1 = areadevCode;
	}
	$("#searchlistform")[0].action = path+"/differentDataStatistics.htm?areaCode="+areaCode1;
	$("#searchlistform")[0].submit();
}

//分页浏览查询
function pageQuery(){
	$("#pageNo").val($(this).attr("pages"));
	submitQuery();
}

//汇聚区下拉列表
function changeArea(districtObj){
	var areaCode1=$("#areaCode1").val();
	var  value = $(districtObj).val();
	var areas = $(districtObj).parents("div").find("select.hjq");
	
	areas.children("option").remove();
	areas.append($("<option>").prop("value",'').append("==请选择=="));
	$.ajax({
		type:"POST",
		url: path+"/ajax/getAreas.htm?areaRank=4&parentAreaCode="+value,
		dataType:"json",
		success:function(data)
		{
			$.each(data,function(index,value){
				 if(areaCode1==value.name)
				{
					 
					 //回显
					areas.append($("<option>").prop("value",data[index].name).prop("selected",true)
					.append(data[index].value));
					
					areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
					//删除重复数据
					var obj=document.getElementById("areaCode");
 					//index,要删除选项的序号，这里取当前选中选项的序号
 					var index=obj.selectedIndex;
 					obj.options.remove(index+1); 
				}else{
				 areas.append($("<option>").prop("value",data[index].name).append(data[index].value));
				}
			});
		}
	});
	
}